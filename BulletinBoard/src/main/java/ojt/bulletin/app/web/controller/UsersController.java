package ojt.bulletin.app.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ojt.bulletin.app.bl.dto.UsersDTO;
import ojt.bulletin.app.bl.service.UserService;
import ojt.bulletin.app.web.form.SearchForm;
import ojt.bulletin.app.web.form.UsersForm;

/**
 * <h2>UsersController Class</h2>
 * <p>
 * Process for Displaying UsersController
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Controller
public class UsersController {

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;
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
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>login</h2>
     * <p>
     * Login Page
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    /**
     * <h2>error</h2>
     * <p>
     * Error Page
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/error")
    public ModelAndView error() {
        ModelAndView mv = new ModelAndView("error");
        return mv;
    }

    /**
     * <h2>passwordChange</h2>
     * <p>
     * User Password Change Page
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/passwordChange")
    public ModelAndView passwordChange() {
        ModelAndView mv = new ModelAndView();
        mv = new ModelAndView("passwordChange");
        return mv;
    }

    /**
     * <h2>userRegistration</h2>
     * <p>
     * User Registration Page
     * </p>
     *
     * @param mv ModelAndView
     * @return ModelAndView
     */
    @RequestMapping("/userRegistration")
    public ModelAndView userRegistration(ModelAndView mv) {
        mv.addObject("usersForm", new UsersForm());
        mv.setViewName("userRegistration");
        return mv;
    }

    /**
     * <h2>createUserConfirm</h2>
     * <p>
     * Create User Confirm
     * </p>
     *
     * @param usersForm UsersForm
     * @param br        BindingResult
     * @return ModelAndView
     */
    @RequestMapping(value = "/createUserConfirm", method = RequestMethod.POST)
    public ModelAndView createUserConfirm(@Valid @ModelAttribute("usersForm") UsersForm usersForm, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        boolean isEmailExist;
        mv.setViewName("userRegistration");
        if (usersForm.getUserId() == null || usersForm.getUserId() == 0) {
            isEmailExist = userService.doCheckEmail(usersForm.getUserEmail(), 0);
        } else {
            isEmailExist = userService.doCheckEmail(usersForm.getUserEmail(), usersForm.getUserId());
        }
        if (br.hasErrors() || isEmailExist == true) {
            if (isEmailExist == true) {
                mv.addObject("msg", messageSource.getMessage("M_SC_0004", null, null));
            }
        } else if (usersForm.getUserId() == null || usersForm.getUserId() == 0) {
            if (usersForm.getUserPassword().equals(usersForm.getUserConfirmPassword())) {
                mv.setViewName("userConfirm");
            } else {
                mv.addObject("msg", messageSource.getMessage("M_SC_0005", null, null));
            }
        } else {
            mv.setViewName("userConfirm");
        }
        return mv;
    }

    /**
     * <h2>saveUser</h2>
     * <p>
     * Save User
     * </p>
     *
     * @param usersForm UsersForm
     * @param request   HttpServletRequest
     * @param session   HttpSession
     * @throws IOException
     * @return ModelAndView
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("usersForm") UsersForm usersForm, HttpServletRequest request,
            HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/users");
        if (usersForm.getUserId() == null) {
            userService.doSaveUser(usersForm);
            mv.addObject("msg", messageSource.getMessage("M_SC_0001", null, null));
        } else {
            userService.doUpdateUser(usersForm);
            mv.addObject("msg", messageSource.getMessage("M_SC_0002", null, null));
        }
        return mv;
    }

    /**
     * <h2>userView</h2>
     * <p>
     * User View
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/users")
    public ModelAndView userView() {
        ModelAndView mv = new ModelAndView("userView");
        mv.addObject("searchForm", new SearchForm());
        mv.addObject("usersList", userService.doListUser());
        return mv;
    }

    /**
     * <h2>searchUsers</h2>
     * <p>
     * Search Users
     * </p>
     *
     * @param searchForm SearchForm
     * @param br         BindingResult
     * @throws ParseException
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
    public ModelAndView searchUsers(@Valid @ModelAttribute("searchForm") SearchForm searchForm, BindingResult br)
            throws ParseException {
        ModelAndView mv = new ModelAndView("userView");
        if (searchForm.getCreatedFrom().equals("") && !searchForm.getCreatedTo().equals("")
                || !searchForm.getCreatedFrom().equals("") && searchForm.getCreatedTo().equals("")) {
            mv.addObject("msg", messageSource.getMessage("M_SC_0008", null, null));
        } else if (br.hasErrors()) {
            mv.addObject("usersList", userService.doListUser());
        } else {
            List<UsersDTO> users = userService.doSearchUser(searchForm);
            mv.addObject("usersList", users);
            mv.addObject("msg", users.size() + " Results Found");
        }
        return mv;
    }

    /**
     * <h2>userDetailProfile</h2>
     * <p>
     * User Detail Profile
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/userDetailProfile")
    public ModelAndView userDetailProfile() {
        ModelAndView mv = new ModelAndView("userProfile");
        int id = (int) session.getAttribute("loggedInId");
        mv.addObject("userProfile", userService.doGetUserById(id));
        return mv;
    }

    /**
     * <h2>editUser</h2>
     * <p>
     * Edit User
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/editUser/{id}")
    public ModelAndView editUser(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("userRegistration");
        mv.addObject("usersForm", userService.doGetUserById(id));
        return mv;
    }

    /**
     * <h2>userPasswordChange</h2>
     * <p>
     * User Password Change
     * </p>
     *
     * @param password           String
     * @param newPassword        String
     * @param newConfirmPassword String
     * @return ModelAndView
     */
    @RequestMapping(value = "/userPasswordChange", method = RequestMethod.POST)
    public ModelAndView userPasswordChange(@RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("newConfirmPassword") String newConfirmPassword) {
        ModelAndView mv = new ModelAndView();
        int id = (int) session.getAttribute("loggedInId");
        if (passwordEncoder.matches(password, userService.doGetUserById(id).getUserPassword())) {
            if (newPassword.equals(newConfirmPassword)) {
                userService.doChangePassword(id, newPassword);
                mv.setViewName("redirect:/userDetailProfile");
            } else {
                mv.setViewName("passwordChange");
                mv.addObject("msg", messageSource.getMessage("M_SC_0006", null, null));
            }
        } else {
            mv.setViewName("passwordChange");
            mv.addObject("msg", messageSource.getMessage("M_SC_0007", null, null));
        }
        return mv;
    }

    /**
     * <h2>deleteUser</h2>
     * <p>
     * Delete User
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("redirect:/users");
        session.setAttribute("msg", messageSource.getMessage("M_SC_0004", null, null));
        userService.doDeleteUser(id);
        return mv;
    }

    /**
     * <h2>backToUserRegistration</h2>
     * <p>
     * Back To User Registration
     * </p>
     *
     * @param usersForm UsersForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, params = "cancel")
    public ModelAndView backToUserRegistration(@ModelAttribute("usersForm") UsersForm usersForm) {
        ModelAndView mv = new ModelAndView("userRegistration");
        mv.addObject("usersForm", usersForm);
        return mv;
    }
}
