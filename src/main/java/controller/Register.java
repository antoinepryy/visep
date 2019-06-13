package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.DBConnector;
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
        String action = request.getParameter("action");
        if (action.equals("checkCode")) {
            int code = Integer.parseInt(request.getParameter("code"));
            Boolean isAvailable = DBConnector.isAvailable(code);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            Gson gson = new GsonBuilder().create();
            gson.toJson(isAvailable, out);
            out.close();
        }
        else {
            request.setCharacterEncoding("UTF-8");
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            String pw = request.getParameter("password");
            String cPw = request.getParameter("confPassword");
            int code = Integer.parseInt(request.getParameter("code"));
            String mail = request.getParameter("mail");
            response.setContentType("text/html");

            if ((null != fName) && (lName != null) && (pw != null) && (cPw != null) && (mail != null)) {
                User usr = new User(fName, lName, pw, code, mail, false);
                usr.persist();
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
