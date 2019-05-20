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
import java.util.ArrayList;
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
                int userCode = Integer.parseInt(String.valueOf(session.getAttribute("user")));
                int userId = DBConnector.getUserId(userCode);

                List<Message> listMsg = DBConnector.getAllMessagesReceivedByUser(userId);
                if (listMsg != null){
                    List<List<Message>> listToSend = new ArrayList<>();
                    for(int i = 0; i<listMsg.size(); i++){
                        int messageSubject;
                        if (listMsg.get(i).getSenderId() == userId){
                            messageSubject = listMsg.get(i).getRecipientId();
                        }
                        else if (listMsg.get(i).getRecipientId() == userId){
                            messageSubject = listMsg.get(i).getSenderId();
                        }
                        for (int j = 0 ; j<listToSend.size(); j++){


                            if (listToSend.get(j).get(0).getSenderId() == userId){

                            }
                            else if (listToSend.get(j).get(0).getRecipientId() == userId){

                            }
                        }
                    }
                    request.setAttribute("messages", listToSend);
                }

                break;
            case "read":
                break;

        }
        request.setAttribute("action", action);
        request.getRequestDispatcher("/WEB-INF/messenger.jsp").forward(request, response);
    }
}
