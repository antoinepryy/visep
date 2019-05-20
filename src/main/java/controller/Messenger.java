package controller;

import model.DBConnector;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Messenger")
public class Messenger extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        int userId = Integer.parseInt(String.valueOf(session.getAttribute("user")));

        String dest = request.getParameter("user-select");
        String msg = request.getParameter("text");

        DBConnector.sendMessage(DBConnector.getUserId(userId),Integer.parseInt(dest),msg);
        out.println(userId);
        out.println(dest);
        out.println(msg);
        response.sendRedirect("messenger?action=list");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "send":
                List<User> listUser = DBConnector.getAllUsers();
                request.setAttribute("users", listUser);

            case "list":
                HttpSession session=request.getSession();
                int userId = Integer.parseInt(String.valueOf(session.getAttribute("user")));

                List<Message> listMsg = DBConnector.getAllMessagesReceivedByUser(DBConnector.getUserId(userId));
                request.setAttribute("messages", listMsg);

                break;
            case "read":
                break;

        }
        request.setAttribute("action", action);
        request.getRequestDispatcher("/WEB-INF/messenger.jsp").forward(request, response);
    }
}
