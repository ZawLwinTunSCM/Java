package ojt.bulletin.app.web.form;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>PostForm Class</h2>
 * <p>
 * Process for Displaying PostForm
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {
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
    @NotEmpty
    private String postTitle;
    /**
     * <h2>postDescription</h2>
     * <p>
     * postDescription
     * </p>
     */
    @NotEmpty
    private String postDescription;
    /**
     * <h2>postStatus</h2>
     * <p>
     * postStatus
     * </p>
     */
    private Boolean postStatus;
}
