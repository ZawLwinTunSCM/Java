package ojt.bulletin.app.bl.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ojt.bulletin.app.persistence.entity.Users;

/**
 * <h2>UserAuthorityDetail Class</h2>
 * <p>
 * Process for Displaying UserAuthorityDetail
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorityDetail implements UserDetails {

    /**
     * <h2>serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 7376528184215268346L;
    /**
     * <h2>userId</h2>
     * <p>
     * userId
     * </p>
     */
    private int userId;
    /**
     * <h2>userName</h2>
     * <p>
     * userName
     * </p>
     */
    private String userName;
    /**
     * <h2>userPassword</h2>
     * <p>
     * userPassword
     * </p>
     */
    private String userPassword;
    /**
     * <h2>userType</h2>
     * <p>
     * userType
     * </p>
     */
    private String userType;

    /**
     * <h2>getAuthorities</h2>
     * <p>
     * Grant Authorities
     * </p>
     * 
     * @return list List<GrantAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(this.userType));
        return list;
    }

    /**
     * <h2>getCurrentUser</h2>
     * <p>
     * Get Current Login User
     * </p>
     *
     * @return UserAuthorityDetail
     */
    public UserAuthorityDetail getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Users) {
                return ((UserAuthorityDetail) principal);
            }
        }
        return null;
    }

    /**
     * <h2>getUserId</h2>
     * <p>
     * Get Current Login User Id
     * </p>
     *
     * @return int
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * <h2>getUsername</h2>
     * <p>
     * Get Current Login User name
     * </p>
     * 
     */
    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * <h2>getPassword</h2>
     * <p>
     * Get Current Login User Password
     * </p>
     * 
     */
    @Override
    public String getPassword() {
        return this.userPassword;
    }

    /**
     * <h2>isAccountNonExpired</h2>
     * <p>
     * Account Is Non Expired
     * </p>
     * 
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * <h2>isAccountNonLocked</h2>
     * <p>
     * Account Is Non Locked
     * </p>
     * 
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * <h2>isCredentialsNonExpired</h2>
     * <p>
     * Credentials Is Non Expire
     * </p>
     * 
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * <h2>isEnabled</h2>
     * <p>
     * Is Enabled
     * </p>
     * 
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}