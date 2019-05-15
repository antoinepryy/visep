package controller;

import model.Association;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Admin")
public class Admin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String description=request.getParameter("description");
        String recruitment=request.getParameter("recruitment");
        Association association = new Association(name, description, recruitment);
        association.persist();
        response.sendRedirect("association");
//        PrintWriter out = response.getWriter();;
//        String action = request.getParameter("action");
//        switch(action){
//            case "addassociation":
//                out.println(request.getParameter("fName"));
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action){
            case "addassociation":
                request.getRequestDispatcher("/WEB-INF/addassociation.jsp").forward(request, response);
            case "addadmin":
                request.getRequestDispatcher("/WEB-INF/addadmin.jsp").forward(request, response);

        }
        PrintWriter out = response.getWriter();
        out.println(action);


        request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }
}
