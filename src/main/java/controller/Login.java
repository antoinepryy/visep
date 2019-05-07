package controller;

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
        String uname=request.getParameter("username");
        String pword=request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();



        if(uname.equals("test") && pword.equals("test")) {
            out.println("ok");
            HttpSession session=request.getSession();
            session.setAttribute("user",uname);
//            RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
//            dispatcher.forward(request,response);
        }
        else{
            out.println("not ok");

//            RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
//            dispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }
}
