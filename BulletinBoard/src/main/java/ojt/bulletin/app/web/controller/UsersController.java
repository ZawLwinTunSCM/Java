package ojt.bulletin.app.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

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

import ojt.bulletin.app.bl.service.UserService;
import ojt.bulletin.app.web.form.SearchForm;
import ojt.bulletin.app.web.form.UsersForm;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private HttpSession session;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("/error")
    public ModelAndView error() {
        ModelAndView mv = new ModelAndView("error");
        return mv;
    }

    @RequestMapping("/passwordChange")
    public ModelAndView passwordChange() {
        ModelAndView mv = new ModelAndView();
        mv = new ModelAndView("passwordChange");
        return mv;
    }

    @RequestMapping("/userRegistration")
    public ModelAndView userRegistration(ModelAndView mv) {
        mv.addObject("usersForm", new UsersForm());
        mv.setViewName("userRegistration");
        return mv;
    }

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
                mv.addObject("msg", "Email is already exists");
            }
        } else if (usersForm.getUserId() == null || usersForm.getUserId() == 0) {
            if (usersForm.getUserPassword().equals(usersForm.getUserConfirmPassword())) {
                mv.setViewName("userConfirm");
            } else {
                mv.addObject("msg", "Password and Confirm Password must be the same");
            }
        } else {
            mv.setViewName("userConfirm");
        }
        return mv;
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("usersForm") UsersForm usersForm, HttpServletRequest request,
            HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/users");
        if (usersForm.getUserId() == null) {
            String userProfilePath = null;
            String imgName = null;
            boolean isEmpty = false;
            if (usersForm.getUserProfile().equals(null) || usersForm.getUserProfile().isEmpty()) {
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
            }
            userService.doSaveUser(usersForm, userProfilePath, imgName);
            mv.addObject("msg", messageSource.getMessage("M_SC_0001", null, null));
        } else {
            userService.doUpdateUser(usersForm);
            mv.addObject("msg", messageSource.getMessage("M_SC_0002", null, null));
        }
        return mv;
    }

    @RequestMapping("/users")
    public ModelAndView userView() {
        ModelAndView mv = new ModelAndView("userView");
        mv.addObject("searchForm", new SearchForm());
        mv.addObject("usersList", userService.doListUser());
        return mv;
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
    public ModelAndView searchUsers(@Valid @ModelAttribute("searchForm") SearchForm searchForm, BindingResult br)
            throws ParseException {
        ModelAndView mv = new ModelAndView("userView");
        if (searchForm.getCreatedFrom().equals("") && !searchForm.getCreatedTo().equals("")
                || !searchForm.getCreatedFrom().equals("") && searchForm.getCreatedTo().equals("")) {
            mv.addObject("msg", messageSource.getMessage("M_SC_0004", null, null));
        } else if (br.hasErrors()) {
            mv.addObject("usersList", userService.doListUser());
        } else {
            mv.addObject("usersList", userService.doSearchUser(searchForm));
        }
        return mv;
    }

    @RequestMapping("/userDetailProfile")
    public ModelAndView userDetailProfile() {
        ModelAndView mv = new ModelAndView("userProfile");
        int id = (int) session.getAttribute("loggedInId");
        mv.addObject("userProfile", userService.doGetUserById(id));
        return mv;
    }

    @RequestMapping("/editUser/{id}")
    public ModelAndView editUser(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("userRegistration");
        mv.addObject("usersForm", userService.doGetUserById(id));
        return mv;
    }

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
                mv.addObject("msg", "New Password and New Confirm Password aren't the same");
            }
        } else {
            mv.setViewName("passwordChange");
            mv.addObject("msg", "Old Password is Incorrect");
        }
        return mv;
    }

    @RequestMapping("/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("redirect:/users");
        session.setAttribute("msg", messageSource.getMessage("M_SC_0004", null, null));
        userService.doDeleteUser(id);
        return mv;
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, params = "cancel")
    public ModelAndView backToUserRegistration(@ModelAttribute("usersForm") UsersForm usersForm) {
        ModelAndView mv = new ModelAndView("userRegistration");
        mv.addObject("usersForm", usersForm);
        return mv;
    }
}
