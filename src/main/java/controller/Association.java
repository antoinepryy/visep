package controller;

import model.DBConnector;
import model.Event;
import model.Membership;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Association")
public class Association extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("change-description")) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String recruitment = request.getParameter("recruitment");
            model.Association association = new model.Association(name, description, recruitment, null);
            association.updateInfos();
            response.sendRedirect("association?name=" + name + "&action=description");
        }
        else if (action.equals("del-member")) {
            String name = request.getParameter("name");
            int memberCode = Integer.parseInt(request.getParameter("memberCode"));
            Membership.deleteMember(name, memberCode);
            response.sendRedirect("association?name=" + name + "&action=members");
        }
        else if (action.equals("add-member")) {
            String name = request.getParameter("name");
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            Membership.addMember(name, fName, lName);
            response.sendRedirect("association?name=" + name + "&action=members");
        }
        else if (action.equals("del-event")) {
            String name = request.getParameter("name");
            int event_id = Integer.parseInt(request.getParameter("event_id"));
            Event.delete(event_id);
            response.sendRedirect("association?name=" + name + "&action=events");
        }
        else if (action.equals("add-event")) {
            String name = request.getParameter("name");
            java.util.Date date;
            java.sql.Date dateSQL;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
                dateSQL = new java.sql.Date(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                dateSQL = null;
            }
            String description = request.getParameter("description");
            Event.create(name, dateSQL, description);
            response.sendRedirect("association?name=" + name + "&action=events");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String asso = request.getParameter("name");
        if (asso == null) {
            List<model.Association> associations = DBConnector.getAssociationsFromDB();
            request.setAttribute("associations", associations);
            request.getRequestDispatcher("/WEB-INF/associations.jsp").forward(request, response);
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
                else if (action.equals("events")) {
                    List<Event> events = model.Association.getEvents(asso);
                    request.setAttribute("events", events);
                }
                Boolean isAdmin = association.isAdmin(Integer.parseInt((String) session.getAttribute("user")));
                request.setAttribute("isAdmin", isAdmin);
                request.setAttribute("action", action);
                request.setAttribute("association", association);
                request.getRequestDispatcher("/WEB-INF/association.jsp").forward(request, response);
            }
        }
    }
}
