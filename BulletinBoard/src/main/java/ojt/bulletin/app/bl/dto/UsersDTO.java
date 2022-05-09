package ojt.bulletin.app.bl.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.bulletin.app.persistence.entity.Users;

/**
 * <h2>UsersDTO Class</h2>
 * <p>
 * Process for Displaying UsersDTO
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
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
    private String userName;
    /**
     * <h2>userEmail</h2>
     * <p>
     * userEmail
     * </p>
     */
    private String userEmail;
    /**
     * <h2>userPassword</h2>
     * <p>
     * userPassword
     * </p>
     */
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
    private String userType;
    /**
     * <h2>userPhone</h2>
     * <p>
     * userPhone
     * </p>
     */
    private String userPhone;
    /**
     * <h2>userAddress</h2>
     * <p>
     * userAddress
     * </p>
     */
    private String userAddress;
    /**
     * <h2>userDob</h2>
     * <p>
     * userDob
     * </p>
     */
    private LocalDate userDob;
    /**
     * <h2>createdUserId</h2>
     * <p>
     * createdUserId
     * </p>
     */
    private Integer createdUserId;
    /**
     * <h2>createdUser</h2>
     * <p>
     * createdUser
     * </p>
     */
    private String createdUser;
    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    private Date createdAt;
    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    private Date updatedAt;

    /**
     * <h2>Constructor for UsersDTO</h2>
     * <p>
     * Constructor for UsersDTO
     * </p>
     * 
     * @param users
     */
    public UsersDTO(Users users) {
        super();
        this.userId = users.getUserId();
        this.userName = users.getUserName();
        this.userEmail = users.getUserEmail();
        this.userPassword = users.getUserPassword();
        this.userProfile = users.getUserProfile();
        this.userType = users.getUserType();
        this.userPhone = users.getUserPhone();
        this.userAddress = users.getUserAddress();
        this.userDob = users.getUserDob();
        this.createdUserId = users.getCreatedUserId();
        this.createdAt = users.getCreatedAt();
        this.updatedAt = users.getUpdatedAt();
    }
}
