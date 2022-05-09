package ojt.bulletin.app.bl.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import ojt.bulletin.app.bl.dto.UsersDTO;
import ojt.bulletin.app.persistence.entity.Users;
import ojt.bulletin.app.web.form.SearchForm;
import ojt.bulletin.app.web.form.UsersForm;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Process for Displaying UserService
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface UserService {
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
     * @return void
     */
    public void doSaveUser(UsersForm usersForm, String userProfilePath, String imgName) throws IOException;

    /**
     * <h2>doListUser</h2>
     * <p>
     * User Lists
     * </p>
     *
     * @return List<UsersDTO>
     */
    public List<UsersDTO> doListUser();

    /**
     * <h2>doSearchUser</h2>
     * <p>
     * Search User
     * </p>
     *
     * @param searchForm SearchForm
     * @throws ParseException
     * @return List<UsersDTO>
     */
    public List<UsersDTO> doSearchUser(SearchForm searchForm) throws ParseException;

    /**
     * <h2>doGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     *
     * @param id int
     * @return UsersDTO
     */
    public UsersDTO doGetUserById(int id);

    /**
     * <h2>doGetUserByEmail</h2>
     * <p>
     * Get User By Email
     * </p>
     *
     * @param email String
     * @return Users
     */
    public Users doGetUserByEmail(String email);

    /**
     * <h2>doCheckEmail</h2>
     * <p>
     * Check Email If Existed?
     * </p>
     *
     * @param email String
     * @param id    int
     * @return boolean
     */
    public boolean doCheckEmail(String email, int id);

    /**
     * <h2>doUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param usersForm UsersForm
     * @return void
     */
    public void doUpdateUser(UsersForm usersForm);

    /**
     * <h2>doChangePassword</h2>
     * <p>
     * Change User Password
     * </p>
     *
     * @param id       int
     * @param password String
     * @return void
     */
    public void doChangePassword(int id, String password);

    /**
     * <h2>doDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id int
     * @return void
     */
    public void doDeleteUser(int id);
}
