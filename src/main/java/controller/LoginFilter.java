package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        String[] userPages = {"/home", "/association", "/messenger"};
        String[] adminPages = {"/admin"};
        String[] guestPages = {"/login", "/register"};

        String loginURI = request.getContextPath() + "/login";
        String homeURI = request.getContextPath() + "/home";

        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (loggedIn) {
            boolean userRequest = Arrays.asList(userPages).contains(request.getServletPath());
            boolean adminRequest = Arrays.asList(adminPages).contains(request.getServletPath());
            boolean loggedAsAdmin = session.getAttribute("isAdmin").equals(true);
            if (userRequest || (adminRequest && loggedAsAdmin)) {
                chain.doFilter(request, response);
            }
            else {
                response.sendRedirect(homeURI);
            }
        }
        else {
            boolean guestRequest = Arrays.asList(guestPages).contains(request.getServletPath());
            if (guestRequest) {
                chain.doFilter(request, response);
            }
            else {
                response.sendRedirect(loginURI);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
