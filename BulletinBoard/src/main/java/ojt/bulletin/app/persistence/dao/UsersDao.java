package ojt.bulletin.app.persistence.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ojt.bulletin.app.persistence.entity.Users;
import ojt.bulletin.app.web.form.SearchForm;

/**
 * <h2>UsersDao Class</h2>
 * <p>
 * Process for Displaying UsersDao
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
public interface UsersDao {
    /**
     * <h2>dbSaveUser</h2>
     * <p>
     * Save User
     * </p>
     *
     * @param user Users
     * @return void
     */
    public void dbSaveUser(Users user);

    /**
     * <h2>dbListUser</h2>
     * <p>
     * User List
     * </p>
     *
     * @return List<Users>
     */
    public List<Users> dbListUser();

    /**
     * <h2>dbSearchUser</h2>
     * <p>
     * Search User
     * </p>
     *
     * @param searchForm SearchForm
     * @throws ParseException
     * @return List<Users>
     */
    public List<Users> dbSearchUser(SearchForm searchForm) throws ParseException;

    /**
     * <h2>dbGetUserById</h2>
     * <p>
     * Get User By ID
     * </p>
     *
     * @param id int
     * @return Users
     */
    public Users dbGetUserById(int id);

    /**
     * <h2>dbGetUserByEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param email String
     * @return Users
     */
    public Users dbGetUserByEmail(String email);

    /**
     * <h2>dbGetUserCountByEmail</h2>
     * <p>
     * Get User Count By Email
     * </p>
     *
     * @param id    int
     * @param email String
     * @return long
     */
    public long dbGetUserCountByEmail(int id, String email);

    /**
     * <h2>dbUpdateUser</h2>
     * <p>
     * Update User
     * </p>
     *
     * @param user Users
     * @return void
     */
    public void dbUpdateUser(Users user);

    /**
     * <h2>dbChangePassword</h2>
     * <p>
     * Change User Password
     * </p>
     *
     * @param id       int
     * @param password String
     * @return void
     */
    public void dbChangePassword(int id, String password);

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id   int
     * @param date Date
     * @param uid  int
     * @return void
     */
    public void dbDeleteUser(int id, Date date, int uid);
}
