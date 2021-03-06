package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.DBConnector;
import model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Home")
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userCode = Integer.parseInt((String) session.getAttribute("user"));
        int userId = DBConnector.getUserId(userCode);
        List<Event> followedEvents = DBConnector.getEvents(userId, true);
        List<Event> otherEvents = DBConnector.getEvents(userId, false);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        gson.toJson(new List[]{followedEvents, otherEvents}, out);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Event> events = DBConnector.getAllEvents();
        request.setAttribute("events", events);
        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

}
