package ojt.bulletin.app.persistence.dao;

import java.util.Date;
import java.util.List;

import ojt.bulletin.app.persistence.entity.Post;

/**
 * <h2>PostDao Class</h2>
 * <p>
 * Process for Displaying PostDao
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface PostDao {
    /**
     * <h2>dbSavePost</h2>
     * <p>
     * Save Post
     * </p>
     *
     * @param post Post
     * @return void
     */
    public void dbSavePost(Post post);

    /**
     * <h2>dbListPost</h2>
     * <p>
     * Post List
     * </p>
     *
     * @return List<Post>
     */
    public List<Post> dbListPost();

    /**
     * <h2>dbSearchPost</h2>
     * <p>
     * Search Post
     * </p>
     *
     * @param search String
     * @return List<Post>
     */
    public List<Post> dbSearchPost(String search);

    /**
     * <h2>dbGetPostById</h2>
     * <p>
     * Get Post By ID
     * </p>
     *
     * @param id int
     * @return Post
     */
    public Post dbGetPostById(int id);

    /**
     * <h2>dbUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param post Post
     * @return void
     */
    public void dbUpdatePost(Post post);

    /**
     * <h2>dbDeletePost</h2>
     * <p>
     * Delete Post
     * </p>
     *
     * @param id   int
     * @param date Date
     * @param uid  int
     * @return void
     */
    public void dbDeletePost(int id, Date date, int uid);
}
