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

/**
 * <h2>PostDaoImpl Class</h2>
 * <p>
 * Process for Displaying PostDaoImpl
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Repository
@Transactional
public class PostDaoImpl implements PostDao {
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * <h2>SELECT_POST_HQL</h2>
     * <p>
     * SELECT_POST_HQL
     * </p>
     */
    private static final String SELECT_POST_HQL = "SELECT p FROM Post p WHERE p.deletedAt IS NULL";
    /**
     * <h2>SEARCH_POST_HQL</h2>
     * <p>
     * SEARCH_POST_HQL
     * </p>
     */
    private static final String SEARCH_POST_HQL = "SELECT p FROM Post p INNER JOIN p.user u WHERE (p.postTitle like :search AND p.deletedAt IS NULL) OR (p.postDescription like :search AND p.deletedAt IS NULL)  OR (p.createdAt like :search AND p.deletedAt IS NULL) OR (u.userName like :search AND p.deletedAt IS NULL)";
    /**
     * <h2>DELETE_POST_HQL</h2>
     * <p>
     * DELETE_POST_HQL
     * </p>
     */
    private static final String DELETE_POST_HQL = "UPDATE Post p SET p.deletedAt = :date , p.deletedUserId = :uid  WHERE p.postId = :id";

    /**
     * <h2>dbSavePost</h2>
     * <p>
     * Save Post
     * </p>
     * 
     * @param post Post
     */
    @Override
    public void dbSavePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(post);
    }

    /**
     * <h2>dbListPost</h2>
     * <p>
     * Post Lists
     * </p>
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> dbListPost() {
        return this.sessionFactory.getCurrentSession().createQuery(SELECT_POST_HQL).list();
    }

    /**
     * <h2>dbSearchPost</h2>
     * <p>
     * Search Post
     * </p>
     * 
     * @param search String
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> dbSearchPost(String search) {
        return this.sessionFactory.getCurrentSession().createQuery(SEARCH_POST_HQL)
                .setParameter("search", "%" + search + "%").list();
    }

    /**
     * <h2>dbGetPostById</h2>
     * <p>
     * Get Post By ID
     * </p>
     * 
     * @param id int
     */
    @SuppressWarnings("deprecation")
    @Override
    public Post dbGetPostById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Post post = (Post) session.get(Post.class, new Integer(id));
        return post;
    }

    /**
     * <h2>dbUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     * 
     * @param post Post
     */
    @Override
    public void dbUpdatePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    /**
     * <h2>dbDeletePost</h2>
     * <p>
     * Delete Post
     * </p>
     * 
     * @param id   int
     * @param date Date
     * @param uid  int
     */
    @Override
    public void dbDeletePost(int id, Date date, int uid) {
        this.sessionFactory.getCurrentSession().createQuery(DELETE_POST_HQL).setParameter("date", date)
                .setParameter("id", id).setParameter("uid", uid).executeUpdate();
    }
}
