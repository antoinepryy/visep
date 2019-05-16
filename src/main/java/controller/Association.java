package controller;

import model.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Association")
public class Association extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assoId = request.getParameter("asso");
        if (assoId != null){
            model.Association asso = DBConnector.getAssociationById(Integer.parseInt(assoId));
            log(asso.getDescription());
            request.setAttribute("association", asso);
            request.getRequestDispatcher("/WEB-INF/association.jsp").forward(request, response);

        }
        else{
            List<model.Association> associations = DBConnector.getAssociationsFromDB();
            request.setAttribute("associations", associations);
            request.getRequestDispatcher("/WEB-INF/associations.jsp").forward(request, response);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
