package ojt.bulletin.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ojt.bulletin.app.bl.dto.UserAuthorityDetail;
import ojt.bulletin.app.persistence.dao.UsersDao;

/**
 * <h2>LoginSuccessHandler Class</h2>
 * <p>
 * Process for Displaying LoginSuccessHandler
 * </p>
 * 
 * @author ZawLwinTun
 *
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * <h2>usersDao</h2>
     * <p>
     * usersDao
     * </p>
     */
    @Autowired
    private UsersDao usersDao;

    /**
     * <h2>onAuthenticationSuccess</h2>
     * <p>
     * Authentication Success
     * </p>
     * 
     * @param request        HttpServletRequest
     * @param response       HttpServletResponse
     * @param authentication Authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserAuthorityDetail userDetails = (UserAuthorityDetail) authentication.getPrincipal();
        int userId = userDetails.getUserId();
        String userName = usersDao.dbGetUserById(userId).getUserName();
        HttpSession session = request.getSession();
        session.setAttribute("loggedInId", userId);
        session.setAttribute("loggedInName", userName);
        String success = request.getContextPath() + "/postView";
        response.sendRedirect(success);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}