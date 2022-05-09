package ojt.bulletin.app.bl.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ojt.bulletin.app.bl.dto.UserAuthorityDetail;
import ojt.bulletin.app.bl.dto.UsersDTO;
import ojt.bulletin.app.bl.service.UserService;
import ojt.bulletin.app.persistence.dao.UsersDao;
import ojt.bulletin.app.persistence.entity.Users;
import ojt.bulletin.app.web.form.SearchForm;
import ojt.bulletin.app.web.form.UsersForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     * <h2>usersDao</h2>
     * <p>
     * usersDao
     * </p>
     */
    @Autowired
    private UsersDao usersDao;
    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>doSaveUser</h2>
     * <p>
     * Save User
     * </p>
     * 
     * @param usersForm       UsersForm
     * @param userProfilePath String
     * @param imgName         String
     * @throws IOException
     */
    @SuppressWarnings("unused")
    @Override
    public void doSaveUser(UsersForm usersForm) throws IOException {
        Date date = new Date();
        Users user = new Users(usersForm);
        int uid = (int) session.getAttribute("loggedInId");
        user.setUserPassword(passwordEncoder.encode(usersForm.getUserPassword()));
        user.setCreatedUserId(uid);
        user.setUpdatedUserId(uid);
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        String userProfilePath = null;
        String imgName = null;
        boolean isEmpty = false;
        if (user.getUserProfile().equals(null) || user.getUserProfile().isEmpty()) {
            isEmpty = true;
        }
        if (isEmpty = false) {
            String contextPath = session.getServletContext().getRealPath("/");
            userProfilePath = contextPath.concat("resources/profiles/");
            Path uploadPath = Paths.get(userProfilePath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            userProfilePath = userProfilePath.concat(usersForm.getUserEmail()).concat(".png");
            imgName = usersForm.getUserEmail().concat(".png");

            user.setUserProfile("resources/profiles/" + imgName);
            String imageBase64 = usersForm.getUserProfile();
            if (!imageBase64.isEmpty() && !imageBase64.equals("") && !imageBase64.equals(null)) {
                String[] block = imageBase64.split(",");
                String realData = block[1];
                byte[] data = Base64.decodeBase64(realData);
                try (FileOutputStream stream = new FileOutputStream(userProfilePath)) {
                    stream.write(data);
                }
            }
        }
        usersDao.dbSaveUser(user);
    }

    /**
     * <h2>doListUser</h2>
     * <p>
     * User Lists
     * </p>
     * 
     */
    @Override
    public List<UsersDTO> doListUser() {
        List<UsersDTO> usersList = new ArrayList<UsersDTO>();
        for (Users users : this.usersDao.dbListUser()) {
            UsersDTO usersDto = new UsersDTO(users);
            usersDto.setCreatedUser(usersDao.dbGetUserById(usersDto.getCreatedUserId()).getUserName());
            usersList.add(usersDto);
        }
        return usersList;
    }

    /**
     * <h2>doSearchUser</h2>
     * <p>
     * Search Users
     * </p>
     * 
     * @param searchForm SearchForm
     * @throws ParseException
     */
    @Override
    public List<UsersDTO> doSearchUser(SearchForm searchForm) throws ParseException {
        List<UsersDTO> usersList = new ArrayList<UsersDTO>();
        for (Users users : this.usersDao.dbSearchUser(searchForm)) {
            UsersDTO usersDto = new UsersDTO(users);
            usersDto.setCreatedUser(usersDao.dbGetUserById(usersDto.getCreatedUserId()).getUserName());
            usersList.add(usersDto);
        }
        return usersList;
    }

    /**
     * <h2>doGetUserById</h2>
     * <p>
     * Get user By ID
     * </p>
     * 
     * @param id int
     */
    @Override
    public UsersDTO doGetUserById(int id) {
        Users user = this.usersDao.dbGetUserById(id);
        UsersDTO usersForm = user != null ? new UsersDTO(user) : null;
        usersForm.setCreatedUser(usersDao.dbGetUserById(usersForm.getCreatedUserId()).getUserName());
        return usersForm;
    }

    /**
     * <h2>doGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     * 
     * @param email String
     */
    @Override
    public Users doGetUserByEmail(String email) {
        return this.usersDao.dbGetUserByEmail(email);
    }

    /**
     * <h2>doCheckEmail</h2>
     * <p>
     * Check Email if Exist
     * </p>
     * 
     * @param email String
     * @param id    int
     */
    @Override
    public boolean doCheckEmail(String email, int id) {
        boolean isExist = false;
        List<Users> userList = usersDao.dbListUser();
        if (id == 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserEmail().equals(email)) {
                    isExist = true;
                }
            }
        } else {
            long count = (int) usersDao.dbGetUserCountByEmail(id, email);
            if (count == 1 || count == 0) {
                isExist = false;
            } else if (count > 1) {
                isExist = true;
            }
        }
        return isExist;
    }

    /**
     * <h2>doUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     * 
     * @param usersForm usersForm
     */
    @Override
    public void doUpdateUser(UsersForm usersForm) {
        Date date = new Date();
        Users user = new Users(usersForm);
        int uid = (int) session.getAttribute("loggedInId");
        user.setUpdatedUserId(uid);
        user.setUpdatedAt(date);
        usersDao.dbUpdateUser(user);
    }

    /**
     * <h2>doChangePassword</h2>
     * <p>
     * Change User Password
     * </p>
     * 
     * @param id       int
     * @param password String
     */
    @Override
    public void doChangePassword(int id, String password) {
        usersDao.dbChangePassword(id, passwordEncoder.encode(password));
    }

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     * 
     * @param id int
     */
    @Override
    public void doDeleteUser(int id) {
        Date date = new Date();
        int uid = (int) session.getAttribute("loggedInId");
        usersDao.dbDeleteUser(id, date, uid);
    }

    /**
     * <h2>loadUserByUsername</h2>
     * <p>
     * Load user By Name
     * </p>
     * 
     * @param email String
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users userInfo = this.usersDao.dbGetUserByEmail(email);
        if (userInfo == null) {
            throw new UsernameNotFoundException("Invalid Username or Password!");
        }
        UserDetails user = new UserAuthorityDetail(userInfo.getUserId(), userInfo.getUserEmail(),
                userInfo.getUserPassword(), userInfo.getUserType());
        return user;
    }
}
