package ojt.bulletin.app.persistence.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ojt.bulletin.app.web.form.UsersForm;

/**
 * <h2>Users Class</h2>
 * <p>
 * Process for Displaying Users
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
@Table(name = "Users")
public class Users {
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;
    /**
     * <h2>userName</h2>
     * <p>
     * userName
     * </p>
     */
    @Column(name = "name")
    private String userName;
    /**
     * <h2>userEmail</h2>
     * <p>
     * userEmail
     * </p>
     */
    @Column(name = "email")
    private String userEmail;
    /**
     * <h2>userPassword</h2>
     * <p>
     * userPassword
     * </p>
     */
    @Column(name = "password")
    private String userPassword;
    /**
     * <h2>userProfile</h2>
     * <p>
     * userProfile
     * </p>
     */
    /**
     * <h2>userProfile</h2>
     * <p>
     * userProfile
     * </p>
     */
    @Column(name = "profile")
    private String userProfile;
    /**
     * <h2>userType</h2>
     * <p>
     * userType
     * </p>
     */
    @Column(name = "type")
    private String userType;
    /**
     * <h2>userPhone</h2>
     * <p>
     * userPhone
     * </p>
     */
    @Column(name = "phone")
    private String userPhone;
    /**
     * <h2>userAddress</h2>
     * <p>
     * userAddress
     * </p>
     */
    @Column(name = "address")
    private String userAddress;
    /**
     * <h2>userDob</h2>
     * <p>
     * userDob
     * </p>
     */
    @Column(name = "dob", columnDefinition = "DATE")
    private LocalDate userDob;
    /**
     * <h2>createdUserId</h2>
     * <p>
     * createdUserId
     * </p>
     */
    @Column(name = "created_user_id", nullable = false, updatable = false)
    private Integer createdUserId;
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
     * <h2>Constructor for Users</h2>
     * <p>
     * Constructor for Users
     * </p>
     * 
     * @param usersForm
     */
    public Users(UsersForm usersForm) {
        super();
        this.userId = usersForm.getUserId();
        this.userName = usersForm.getUserName();
        this.userEmail = usersForm.getUserEmail();
        this.userPassword = usersForm.getUserPassword();
        this.userProfile = usersForm.getUserProfile();
        this.userType = usersForm.getUserType();
        this.userPhone = usersForm.getUserPhone();
        this.userAddress = usersForm.getUserAddress();
        this.userDob = usersForm.getUserDob();
    }
}
