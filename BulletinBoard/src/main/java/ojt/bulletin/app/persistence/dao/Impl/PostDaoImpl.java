package ojt.bulletin.app.persistence.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ojt.bulletin.app.persistence.dao.PostDao;
import ojt.bulletin.app.persistence.entity.Post;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {
    @Autowired
    private SessionFactory sessionFactory;
    private static final String SELECT_POST_HQL = "SELECT p FROM Post p WHERE p.deletedAt IS NULL";
    private static final String SEARCH_POST_HQL = "SELECT p FROM Post p INNER JOIN p.user u WHERE (p.postTitle like :search AND p.deletedAt IS NULL) OR (p.postDescription like :search AND p.deletedAt IS NULL)  OR (p.createdAt like :search AND p.deletedAt IS NULL) OR (u.userName like :search AND p.deletedAt IS NULL)";
    private static final String DELETE_POST_HQL = "UPDATE Post p SET p.deletedAt = :date , p.deletedUserId = :uid  where p.postId = :id";

    @Override
    public void dbSavePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Post> dbListPost() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_HQL).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Post> dbSearchPost(String search) {
        return this.sessionFactory.getCurrentSession().createQuery(SEARCH_POST_HQL)
                .setParameter("search", "%" + search + "%").list();
    }

    @SuppressWarnings("deprecation")
    @Override
    public Post dbGetPostById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, new Integer(id));
        return post;
    }

    @Override
    public void dbUpdatePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    public void dbDeletePost(int id, Date date,int uid) {
        this.sessionFactory.getCurrentSession().createQuery(DELETE_POST_HQL).setParameter("date", date)
                .setParameter("id", id).setParameter("uid", uid).executeUpdate();
    }
}
