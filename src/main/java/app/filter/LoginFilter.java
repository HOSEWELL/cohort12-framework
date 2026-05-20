package app.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/app/*"})
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);


        String loginUri = httpRequest.getContextPath() + "/login";
        boolean loggedIn = session != null && session.getAttribute("SESSION_ID") != null;

        if(loggedIn){
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            if (session != null)
                session.invalidate();

            httpResponse.sendRedirect(loginUri);
        }

    }
}
