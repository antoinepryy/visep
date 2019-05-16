package controller;

import model.DBConnector;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Association")
public class Association extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String asso = request.getParameter("name");
        if (asso == null) {
            List<model.Association> associations = DBConnector.getAssociationsFromDB();
            request.setAttribute("associations", associations);
            request.getRequestDispatcher("/WEB-INF/page.jsp").forward(request, response);
        }
        else {
            model.Association association = model.Association.getInfos(asso);
            if (association == null) {
                response.sendRedirect("association");
            }
            else {
                String action = request.getParameter("action");
                if (action == null) {
                    action = "description";
                }
                else if (action.equals("members")) {
                    List<User> members = model.Association.getMembers(asso);
                    request.setAttribute("members", members);
                }
                request.setAttribute("action", action);
                request.setAttribute("association", association);
                request.getRequestDispatcher("/WEB-INF/association.jsp").forward(request, response);
            }
        }
    }
}
