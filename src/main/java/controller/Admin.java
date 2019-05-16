package controller;

import model.Association;
import model.DBConnector;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin")
public class Admin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add-asso")) {
            String name=request.getParameter("name");
            String description=request.getParameter("description");
            String recruitment=request.getParameter("recruitment");
            Association association = new Association(name, description, recruitment, null);
            association.persist();
            response.sendRedirect("admin?action=assos");
        }
        else if (action.equals("add-visep-admin")) {
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            User.addVisepAdmin(fName, lName);
            response.sendRedirect("admin?action=admins-visep");
        }
        else if (action.equals("del-visep-admin")) {
            int code = Integer.parseInt(request.getParameter("code"));
            User.deleteVisepAdmin(code);
            response.sendRedirect("admin?action=admins-visep");
        }
        else if (action.equals("change-admin-asso")) {
            String association = request.getParameter("association");
            String adminFirstName = request.getParameter("fName");
            String adminLastName = request.getParameter("lName");
            Association.changeAdmin(association, adminFirstName, adminLastName);
            response.sendRedirect("admin?action=assos");
        }
        else if (action.equals("del-asso")) {
            String association = request.getParameter("association");
            Association.delete(association);
            response.sendRedirect("admin?action=assos");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "assos";
        }
        request.setAttribute("action", action);
        if (action.equals("admins-visep")) {
            List<User> visepAdmins = DBConnector.getVisepAdmins();
            request.setAttribute("visepAdmins", visepAdmins);
        }
        else if (action.equals("assos")) {
            List<model.Association> associations = DBConnector.getAssociationsFromDB();
            request.setAttribute("associations", associations);
        }
        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }
}