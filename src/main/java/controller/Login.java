package controller;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uname=request.getParameter("username");
        String pword=request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();




        if(User.isAuthenticationValidated(uname, pword) != null) {
            HttpSession session=request.getSession();
            session.setAttribute("user",uname);
            session.setAttribute("isAdmin", User.isAdmin(uname));
            response.sendRedirect("home");
        }
        else{
            response.sendRedirect("login");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
