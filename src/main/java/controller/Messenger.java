package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Messenger")
public class Messenger extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "new";
        }
        if (action.equals("respond")) {
            HttpSession session = request.getSession();
            int userCode = Integer.parseInt(String.valueOf(session.getAttribute("user")));
            int userId = DBConnector.getUserId(userCode);
            int idConv = Integer.parseInt(String.valueOf(request.getParameter("idconv")));
            String msg = request.getParameter("text");
            DBConnector.sendMessage(userId, idConv, msg);
            response.sendRedirect("messenger?action=see&idconv=" + idConv);
        }
        else {
            HttpSession session = request.getSession();
            int userId = Integer.parseInt(String.valueOf(session.getAttribute("user")));
            String dest = request.getParameter("user-select");
            String msg = request.getParameter("text");
            DBConnector.sendMessage(DBConnector.getUserId(userId), Integer.parseInt(dest), msg);
            response.sendRedirect("messenger?action=list");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session=request.getSession();
        int userCode = Integer.parseInt(String.valueOf(session.getAttribute("user")));
        int userId = DBConnector.getUserId(userCode);
        if (action == null){action = "list";}
        switch (action){
            case "see":
                int idConv = Integer.parseInt(request.getParameter("idconv"));
                List<Message> msgList = DBConnector.getMessagesConv(userId, idConv);
                request.setAttribute("msgList", msgList);
                User friend = DBConnector.getUserById(idConv);
                request.setAttribute("friend", friend);

            case "send":
                List<User> listUser = DBConnector.getAllUsers();
                request.setAttribute("users", listUser);

            case "list":
                List<Message> listMsg = DBConnector.getAllMessagesReceivedByUser(userId);
                if (listMsg != null && !listMsg.isEmpty()){
                    List<List<Message>> listToSend = new ArrayList<>();
                    listToSend.add(new ArrayList<Message>());
                    listToSend.get(0).add(listMsg.get(0));
                    for(int i = 1; i<listMsg.size(); i++){

                        int messageSubject = -1;
                        if (listMsg.get(i).getSenderId() == userId){
                            messageSubject = listMsg.get(i).getRecipientId();
                        }
                        else if (listMsg.get(i).getRecipientId() == userId){
                            messageSubject = listMsg.get(i).getSenderId();
                        }

                        for (int j = 0 ; j<listToSend.size(); j++){
                            if (listToSend.get(j).get(0).getSenderId() == messageSubject || listToSend.get(j).get(0).getRecipientId() == messageSubject){

                                listToSend.get(j).add(listMsg.get(i));
                                break;
                            }
                            else if(j == listToSend.size()-1){
                                List<Message> newRow = new ArrayList<>();
                                newRow.add(listMsg.get(i));
                                listToSend.add(newRow);
                                break;
                            }
                        }
                    }
                    List<User> listUsr = new ArrayList<>();
                    for (int l = 0; l<listToSend.size(); l++){
                        if (listToSend.get(l).get(0).getSenderId() == userId){
                            listUsr.add(User.getUserById(listToSend.get(l).get(0).getRecipientId()));

                        }
                        else if (listToSend.get(l).get(0).getRecipientId() == userId){
                            listUsr.add(User.getUserById(listMsg.get(l).getSenderId()));
                        }

                    }
                    request.setAttribute("messages", listToSend);
                    request.setAttribute("listUsr", listUsr);
                }
                else{
                    List<List<Message>> listToSend = new ArrayList<>();
                    List<User> listUsr = new ArrayList<>();
                    request.setAttribute("messages",listToSend);
                    request.setAttribute("listUsr", listUsr);
                }
                break;
            case "read":
                break;

        }
        request.setAttribute("userId", userId);
        request.setAttribute("action", action);
        request.getRequestDispatcher("/WEB-INF/messenger.jsp").forward(request, response);
    }
}
