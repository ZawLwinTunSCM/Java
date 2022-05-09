package ojt.bulletin.app.config;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ojt.bulletin.app.common.Constants;
import ojt.bulletin.app.persistence.dao.UsersDao;
import ojt.bulletin.app.persistence.entity.Users;

/**
 * <h2>DeploymentListener Class</h2>
 * <p>
 * Process for Displaying DeploymentListener
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Component
public class DeploymentListener {
    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * <h2>usersDao</h2>
     * <p>
     * usersDao
     * </p>
     */
    @Autowired
    private UsersDao usersDao;

    /**
     * <h2>addInitialData</h2>
     * <p>
     * Add Initial Data
     * </p>
     *
     * @return void
     * @throws ParseException
     */
    @PostConstruct
    public void addInitialData() throws ParseException {
        Date date = new Date();
        LocalDate dob = LocalDate.parse("1999-08-18");
        if (this.usersDao.dbListUser().size() <= 0) {
            Users admin = new Users();
            admin.setCreatedAt(date);
            admin.setUpdatedAt(date);
            admin.setUserName("Admin");
            admin.setUserEmail("admin@gmail.com");
            admin.setUserPassword(passwordEncoder.encode("123"));
            admin.setUserPhone("09123456789");
            admin.setUserType(Constants.ADMIN);
            admin.setUserAddress("Yangon");
            admin.setUserDob(dob);
            admin.setCreatedUserId(1);
            this.usersDao.dbSaveUser(admin);
        }
    }
}