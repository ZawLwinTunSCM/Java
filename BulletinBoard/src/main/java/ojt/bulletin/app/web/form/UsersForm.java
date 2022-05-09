package ojt.bulletin.app.web.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>UsersForm Class</h2>
 * <p>
 * Process for Displaying UsersForm
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersForm {
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    private Integer userId;
    /**
     * <h2>userName</h2>
     * <p>
     * userName
     * </p>
     */
    @NotEmpty
    private String userName;
    /**
     * <h2>userEmail</h2>
     * <p>
     * userEmail
     * </p>
     */
    @NotEmpty
    private String userEmail;
    /**
     * <h2>userPassword</h2>
     * <p>
     * userPassword
     * </p>
     */
    @NotEmpty
    private String userPassword;
    /**
     * <h2>userConfirmPassword</h2>
     * <p>
     * userConfirmPassword
     * </p>
     */
    private String userConfirmPassword;
    /**
     * <h2>userProfile</h2>
     * <p>
     * userProfile
     * </p>
     */
    private String userProfile;
    /**
     * <h2>userType</h2>
     * <p>
     * userType
     * </p>
     */
    @NotEmpty
    private String userType;
    /**
     * <h2>userPhone</h2>
     * <p>
     * userPhone
     * </p>
     */
    @NotEmpty
    private String userPhone;
    /**
     * <h2>userAddress</h2>
     * <p>
     * userAddress
     * </p>
     */
    @NotEmpty
    private String userAddress;
    /**
     * <h2>userDob</h2>
     * <p>
     * userDob
     * </p>
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate userDob;
}
