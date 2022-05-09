package ojt.bulletin.app.bl.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import ojt.bulletin.app.bl.dto.PostDTO;
import ojt.bulletin.app.web.form.PostForm;

/**
 * <h2>PostService Class</h2>
 * <p>
 * Process for Displaying PostService
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface PostService {
    /**
     * <h2>doSavePost</h2>
     * <p>
     * Save Post
     * </p>
     *
     * @param postForm PostForm
     * @return void
     */
    public void doSavePost(PostForm postForm);

    /**
     * <h2>doListPost</h2>
     * <p>
     * Post Lists
     * </p>
     *
     * @return List<PostDTO>
     */
    public List<PostDTO> doListPost();

    /**
     * <h2>doSearchPost</h2>
     * <p>
     * Search Post
     * </p>
     *
     * @param search String
     * @return List<PostDTO>
     */
    public List<PostDTO> doSearchPost(String search);

    /**
     * <h2>doGetPostById</h2>
     * <p>
     * Get Post By ID
     * </p>
     *
     * @param id int
     * @return PostDTO
     */
    public PostDTO doGetPostById(int id);

    /**
     * <h2>doUpdatePost</h2>
     * <p>
     * Update Post
     * </p>
     *
     * @param postForm PostForm
     * @return void
     */
    public void doUpdatePost(PostForm postForm);

    /**
     * <h2>doDeletePost</h2>
     * <p>
     * Delete Post
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doDeletePost(int id);

    /**
     * <h2>doDownlaodPost</h2>
     * <p>
     * Download Post
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @return void
     */
    public void doDownlaodPost(HttpServletResponse response) throws IOException;

    /**
     * <h2>doUploadPost</h2>
     * <p>
     * Upload Post
     * </p>
     *
     * @param file MultipartFile
     * @throws IOException
     * @return String
     */
    public String doUploadPost(MultipartFile file) throws IOException;
}
