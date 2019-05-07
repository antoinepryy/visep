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
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();


        String fName=request.getParameter("fName");
        String lName=request.getParameter("lName");
        String pw=request.getParameter("password");
        String cPw=request.getParameter("confPassword");
        int code=Integer.parseInt(request.getParameter("code"));
        String mail=request.getParameter("mail");
        response.setContentType("text/html");

        if ((null != fName) && (lName != null) && (pw != null) && (cPw != null) && (mail != null)){
            User usr = new User(fName, lName, pw, code, mail);
            out.println(usr.getFirstName());
            out.println(usr.getLastName());
            out.println(usr.getPassword());
            out.println(usr.getCode());
            out.println(usr.getEmail());
            out.println("user saved");

            usr.persist();

        }

        else{
            out.println(fName);
            out.println(lName);
            out.println(pw);
            out.println(cPw);
            out.println(mail);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);

    }
}
