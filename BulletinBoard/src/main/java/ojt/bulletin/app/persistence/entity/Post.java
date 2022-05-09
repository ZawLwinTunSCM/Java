package ojt.bulletin.app.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.bulletin.app.web.form.PostForm;

/**
 * <h2>Post Class</h2>
 * <p>
 * Process for Displaying Post
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Post")
public class Post {
    /**
     * <h2>postId</h2>
     * <p>
     * postId
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer postId;
    /**
     * <h2>postTitle</h2>
     * <p>
     * postTitle
     * </p>
     */
    @Column(name = "title")
    private String postTitle;
    /**
     * <h2>postDescription</h2>
     * <p>
     * postDescription
     * </p>
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String postDescription;
    /**
     * <h2>postStatus</h2>
     * <p>
     * postStatus
     * </p>
     */
    @Column(name = "status")
    private Boolean postStatus;
    /**
     * <h2>user</h2>
     * <p>
     * user
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "created_user_id", nullable = false, updatable = false)
    private Users user;
    /**
     * <h2>updatedUserId</h2>
     * <p>
     * updatedUserId
     * </p>
     */
    @Column(name = "updated_user_id")
    private Integer updatedUserId;
    /**
     * <h2>deletedUserId</h2>
     * <p>
     * deletedUserId
     * </p>
     */
    @Column(name = "deleted_user_id")
    private Integer deletedUserId;
    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "update_at")
    private Date updatedAt;
    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deletedAt;

    /**
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     * 
     * @param postForm
     */
    public Post(PostForm postForm) {
        super();
        this.postId = postForm.getPostId();
        this.postTitle = postForm.getPostTitle();
        this.postDescription = postForm.getPostDescription();
        this.postStatus = postForm.getPostStatus();
    }
}
