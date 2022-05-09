package ojt.bulletin.app.persistence.dao.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ojt.bulletin.app.persistence.dao.UsersDao;
import ojt.bulletin.app.persistence.entity.Users;
import ojt.bulletin.app.web.form.SearchForm;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static final String SELECT_USERS_HQL = "SELECT u FROM Users u WHERE u.deletedAt IS NULL";
    private static final String SEARCH_USERS_WITH_DATE_HQL = "SELECT u FROM Users u WHERE (u.deletedAt IS NULL AND u.userName like :name) OR (u.deletedAt IS NULL AND u.userEmail like :email) OR (u.deletedAt IS NULL AND u.createdAt BETWEEN :createdFrom AND :createdTo)";
    private static final String SEARCH_USERS_WITHOUT_DATE_HQL = "SELECT u FROM Users u WHERE (u.deletedAt IS NULL AND u.userName like :name) OR (u.deletedAt IS NULL AND u.userEmail like :email)";
    private static final String COUNT_USERS_EMAIL_HQL = "SELECT count(*) FROM Users u WHERE u.deletedAt IS NULL AND u.userId= :uId  AND u.userEmail = : uEmail";
    private static final String DELETE_USERS_HQL = "UPDATE Users u SET u.deletedAt = :date , u.deletedUserId = :uid  where u.userId = :id";
    private static final String CHANGE_PASSWORD_HQL = "UPDATE Users u SET u.userPassword = :pass  where u.userId = :id";
    private static final String SELECT_USERS_EMAIL_HQL = "SELECT u FROM Users u WHERE u.deletedAt IS NULL AND u.userEmail = :uEmail";

    @Override
    public void dbSaveUser(Users user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> dbListUser() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_USERS_HQL).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> dbSearchUser(SearchForm searchForm) throws ParseException {
        if (searchForm.getName().equals("") && searchForm.getEmail().equals("")
                && searchForm.getCreatedFrom().equals("") && searchForm.getCreatedTo().equals("")) {
            return this.sessionFactory.getCurrentSession().createQuery(SEARCH_USERS_WITHOUT_DATE_HQL)
                    .setParameter("name", "%" + searchForm.getName() + "%")
                    .setParameter("email", "%" + searchForm.getEmail() + "%").list();
        } else {
            Date createdFrom = null;
            Date createdTo = null;
            if (!searchForm.getCreatedFrom().equals("") && !searchForm.getCreatedTo().equals("")) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                createdFrom = formatter.parse(searchForm.getCreatedFrom());
                createdTo = formatter.parse(searchForm.getCreatedTo());
            }
            if (searchForm.getName().equals("")) {
                return this.sessionFactory.getCurrentSession().createQuery(SEARCH_USERS_WITH_DATE_HQL)
                        .setParameter("name", searchForm.getName()).setParameter("email", searchForm.getEmail())
                        .setParameter("createdFrom", createdFrom).setParameter("createdTo", createdTo).list();
            } else {
                return this.sessionFactory.getCurrentSession().createQuery(SEARCH_USERS_WITH_DATE_HQL)
                        .setParameter("name", "%" + searchForm.getName() + "%")
                        .setParameter("email", searchForm.getEmail()).setParameter("createdFrom", createdFrom)
                        .setParameter("createdTo", createdTo).list();

            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Users dbGetUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Users user = (Users) session.get(Users.class, new Integer(id));
        return user;
    }

    @Override
    public long dbGetUserCountByEmail(int id, String email) {
        long count = (long) this.sessionFactory.getCurrentSession().createQuery(COUNT_USERS_EMAIL_HQL)
                .setParameter("uId", id).setParameter("uEmail", email).getSingleResult();
        return count;
    }

    @Override
    public Users dbGetUserByEmail(String email) {
        return (Users) this.sessionFactory.getCurrentSession().createQuery(SELECT_USERS_EMAIL_HQL)
                .setParameter("uEmail", email).uniqueResult();
    }

    @Override
    public void dbUpdateUser(Users user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void dbChangePassword(int id, String password) {
        this.sessionFactory.getCurrentSession().createQuery(CHANGE_PASSWORD_HQL).setParameter("pass", password)
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void dbDeleteUser(int id, Date date, int uid) {
        this.sessionFactory.getCurrentSession().createQuery(DELETE_USERS_HQL).setParameter("date", date)
                .setParameter("id", id).setParameter("uid", uid).executeUpdate();
    }
}
