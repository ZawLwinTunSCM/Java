package ojt.bulletin.app.bl.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.bulletin.app.persistence.entity.Post;
import ojt.bulletin.app.persistence.entity.Users;

/**
 * <h2>PostDTO Class</h2>
 * <p>
 * Process for Displaying PostDTO
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    /**
     * <h2>postId</h2>
     * <p>
     * postId
     * </p>
     */
    private Integer postId;
    /**
     * <h2>postTitle</h2>
     * <p>
     * postTitle
     * </p>
     */
    private String postTitle;
    /**
     * <h2>postDescription</h2>
     * <p>
     * postDescription
     * </p>
     */
    private String postDescription;
    /**
     * <h2>postStatus</h2>
     * <p>
     * postStatus
     * </p>
     */
    private Boolean postStatus;
    /**
     * <h2>user</h2>
     * <p>
     * user
     * </p>
     */
    private Users user;
    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    private Date createdAt;

    /**
     * <h2>Constructor for PostDTO</h2>
     * <p>
     * Constructor for PostDTO
     * </p>
     * 
     * @param post
     */
    public PostDTO(Post post) {
        super();
        this.postId = post.getPostId();
        this.postTitle = post.getPostTitle();
        this.postDescription = post.getPostDescription();
        this.postStatus = post.getPostStatus();
        this.user = post.getUser();
        this.createdAt = post.getCreatedAt();
    }
}
