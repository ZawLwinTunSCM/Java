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

/**
 * <h2>UsersDaoImpl Class</h2>
 * <p>
 * Process for Displaying UsersDaoImpl
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>SELECT_USERS_HQL</h2>
     * <p>
     * SELECT_USERS_HQL
     * </p>
     */
    private static final String SELECT_USERS_HQL = "SELECT u FROM Users u WHERE u.deletedAt IS NULL";
    /**
     * <h2>SEARCH_USERS_WITH_DATE_HQL</h2>
     * <p>
     * SEARCH_USERS_WITH_DATE_HQL
     * </p>
     */
    private static final String SEARCH_USERS_WITH_DATE_HQL = "SELECT u FROM Users u WHERE (u.deletedAt IS NULL AND u.userName like :name) OR (u.deletedAt IS NULL AND u.userEmail like :email) OR (u.deletedAt IS NULL AND u.createdAt BETWEEN :createdFrom AND :createdTo)";
    /**
     * <h2>SEARCH_USERS_WITHOUT_DATE_HQL</h2>
     * <p>
     * SEARCH_USERS_WITHOUT_DATE_HQL
     * </p>
     */
    private static final String SEARCH_USERS_WITHOUT_DATE_HQL = "SELECT u FROM Users u WHERE (u.deletedAt IS NULL AND u.userName like :name) OR (u.deletedAt IS NULL AND u.userEmail like :email)";
    /**
     * <h2>COUNT_USERS_EMAIL_HQL</h2>
     * <p>
     * COUNT_USERS_EMAIL_HQL
     * </p>
     */
    private static final String COUNT_USERS_EMAIL_HQL = "SELECT count(*) FROM Users u WHERE u.deletedAt IS NULL AND u.userId= :uId  AND u.userEmail = : uEmail";
    /**
     * <h2>DELETE_USERS_HQL</h2>
     * <p>
     * DELETE_USERS_HQL
     * </p>
     */
    private static final String DELETE_USERS_HQL = "UPDATE Users u SET u.deletedAt = :date , u.deletedUserId = :uid  WHERE u.userId = :id";
    /**
     * <h2>CHANGE_PASSWORD_HQL</h2>
     * <p>
     * CHANGE_PASSWORD_HQL
     * </p>
     */
    private static final String CHANGE_PASSWORD_HQL = "UPDATE Users u SET u.userPassword = :pass  WHERE u.userId = :id";
    /**
     * <h2>SELECT_USERS_EMAIL_HQL</h2>
     * <p>
     * SELECT_USERS_EMAIL_HQL
     * </p>
     */
    private static final String SELECT_USERS_EMAIL_HQL = "SELECT u FROM Users u WHERE u.deletedAt IS NULL AND u.userEmail = :uEmail";

    /**
     * <h2>dbSaveUser</h2>
     * <p>
     * Save User
     * </p>
     * 
     * @param user User
     */
    @Override
    public void dbSaveUser(Users user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    /**
     * <h2>dbListUser</h2>
     * <p>
     * User Lists
     * </p>
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Users> dbListUser() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_USERS_HQL).list();
    }

    /**
     * <h2>dbSearchUser</h2>
     * <p>
     * Search Users
     * </p>
     * 
     * @param searchForm SearchForm
     * @throws ParseException
     */
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

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     * 
     * @param id int
     */
    @SuppressWarnings("deprecation")
    @Override
    public Users dbGetUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Users user = (Users) session.get(Users.class, new Integer(id));
        return user;
    }

    /**
     * <h2>dbGetUserCountByEmail</h2>
     * <p>
     * Get user Count By Email
     * </p>
     * 
     * @param id    int
     * @param email String
     */
    @Override
    public long dbGetUserCountByEmail(int id, String email) {
        long count = (long) this.sessionFactory.getCurrentSession().createQuery(COUNT_USERS_EMAIL_HQL)
                .setParameter("uId", id).setParameter("uEmail", email).getSingleResult();
        return count;
    }

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * Get User Email By ID
     * </p>
     * 
     * @param email String
     */
    @Override
    public Users dbGetUserByEmail(String email) {
        return (Users) this.sessionFactory.getCurrentSession().createQuery(SELECT_USERS_EMAIL_HQL)
                .setParameter("uEmail", email).uniqueResult();
    }

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     * 
     * @param user User
     */
    @Override
    public void dbUpdateUser(Users user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    /**
     * <h2>dbChangePassword</h2>
     * <p>
     * Change User Password
     * </p>
     * 
     * @param id       int
     * @param password String
     */
    @Override
    public void dbChangePassword(int id, String password) {
        this.sessionFactory.getCurrentSession().createQuery(CHANGE_PASSWORD_HQL).setParameter("pass", password)
                .setParameter("id", id).executeUpdate();
    }

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     * 
     * @param id   int
     * @param date Date
     * @param uid  int
     */
    @Override
    public void dbDeleteUser(int id, Date date, int uid) {
        this.sessionFactory.getCurrentSession().createQuery(DELETE_USERS_HQL).setParameter("date", date)
                .setParameter("id", id).setParameter("uid", uid).executeUpdate();
    }
}
