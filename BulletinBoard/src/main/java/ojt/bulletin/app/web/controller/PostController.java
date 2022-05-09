package ojt.bulletin.app.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ojt.bulletin.app.bl.dto.PostDTO;
import ojt.bulletin.app.bl.service.PostService;
import ojt.bulletin.app.web.form.PostForm;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for Displaying PostController
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Controller
public class PostController {

    /**
     * <h2>postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;
    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    private HttpSession session;

    /**
     * <h2>index</h2>
     * <p>
     * Index Page
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * <h2>postRegistration</h2>
     * <p>
     * Post Register
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/postRegistration")
    public ModelAndView postRegistration() {
        ModelAndView mv = new ModelAndView("postRegistration");
        mv.addObject("postForm", new PostForm());
        return mv;
    }

    /**
     * <h2>createPostConfirm</h2>
     * <p>
     * Create Post Confirm
     * </p>
     *
     * @param postForm PostForm
     * @param br       BindingResult
     * @return ModelAndView
     */
    @RequestMapping(value = "/createPostConfirm", method = RequestMethod.POST)
    public ModelAndView createPostConfirm(@Valid @ModelAttribute("postForm") PostForm postForm, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("postRegistration");
        } else {
            mv.setViewName("postConfirm");
        }
        return mv;
    }

    /**
     * <h2>savePost</h2>
     * <p>
     * Save or Update Post
     * </p>
     *
     * @param postForm postForm
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public ModelAndView savePost(@ModelAttribute("postForm") PostForm postForm) throws IOException {
        ModelAndView mv = new ModelAndView("redirect:/postView");
        if (postForm.getPostId() == null || postForm.getPostId() == 0) {
            postService.doSavePost(postForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0009", null, null));
        } else {
            postService.doUpdatePost(postForm);
            session.setAttribute("msg", messageSource.getMessage("M_SC_0010", null, null));
        }
        return mv;
    }

    /**
     * <h2>postView</h2>
     * <p>
     * Post Lists
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping("/postView")
    public ModelAndView postView() {
        ModelAndView mv = new ModelAndView("postView");
        mv.addObject("postList", postService.doListPost());
        return mv;
    }

    /**
     * <h2>searchPost</h2>
     * <p>
     * Search Post
     * </p>
     *
     * @param search String
     * @return ModelAndView
     */
    @RequestMapping(value = "/searchPost", method = RequestMethod.POST)
    public ModelAndView searchPost(@RequestParam("search") String search) {
        ModelAndView mv = new ModelAndView("postView");
        java.util.List<PostDTO> postList = postService.doSearchPost(search);
        mv.addObject("postList", postList);
        mv.addObject("msg", postList.size() + " Results Found");
        return mv;
    }

    /**
     * <h2>editPost</h2>
     * <p>
     * Edit Post
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/editPost/{id}")
    public ModelAndView editPost(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("postRegistration");
        PostDTO post = postService.doGetPostById(id);
        mv.addObject("postForm", post);
        mv.addObject("createdUserId", post.getUser().getUserId());
        return mv;
    }

    /**
     * <h2>deletePost</h2>
     * <p>
     * Delete Post
     * </p>
     *
     * @param id int
     * @return ModelAndView
     */
    @RequestMapping("/deletePost/{id}")
    public ModelAndView deletePost(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("redirect:/postView");
        postService.doDeletePost(id);
        session.setAttribute("msg", messageSource.getMessage("M_SC_0011", null, null));
        return mv;
    }

    /**
     * <h2>downloadPost</h2>
     * <p>
     * Download Post
     * </p>
     *
     * @param response HttpServletResponse
     * @throws IOException
     * @return void
     */
    @RequestMapping(value = "/downloadPost")
    public void downloadPost(HttpServletResponse response) throws IOException {
        postService.doDownlaodPost(response);
    }

    /**
     * <h2>uploadPostChoose</h2>
     * <p>
     * Upload File Choose
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadPostChoose")
    public ModelAndView uploadPostChoose() {
        ModelAndView mv = new ModelAndView("postUpload");
        return mv;
    }

    /**
     * <h2>uploadPost</h2>
     * <p>
     * Upload Post
     * </p>
     *
     * @param file MultipartFile
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadPost", method = RequestMethod.POST)
    public ModelAndView uploadPost(@RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("redirect:/postView");
        String msg = "";
        try {
            msg = postService.doUploadPost(file);
            session.setAttribute("msg", msg);
            if (!msg.equalsIgnoreCase("File Upload Successful")) {
                mv.setViewName("postUpload");
                session.setAttribute("msg", msg);
            }
        } catch (IOException e) {

        }
        return mv;
    }

    /**
     * <h2>backToPostRegistration</h2>
     * <p>
     * Back To Post Registration
     * </p>
     *
     * @param postForm postForm
     * @return ModelAndView
     */
    @RequestMapping(value = "/savePost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView backToPostRegistration(@ModelAttribute("postForm") PostForm postForm) {
        ModelAndView mv = new ModelAndView("postRegistration");
        mv.addObject("postForm", postForm);
        return mv;
    }

    /**
     * <h2>backToPostView</h2>
     * <p>
     * Back To Post View
     * </p>
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/uploadPost", params = "cancel", method = RequestMethod.POST)
    public ModelAndView backToPostView() {
        ModelAndView mv = new ModelAndView("redirect:/postView");
        return mv;
    }
}
