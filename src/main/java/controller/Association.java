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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<model.Association> associations = DBConnector.getAssociationsFromDB();
        request.setAttribute("associations", associations);
        request.getRequestDispatcher("/WEB-INF/page.jsp").forward(request, response);
    }
}
