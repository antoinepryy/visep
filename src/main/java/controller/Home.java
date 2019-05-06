package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Home")
public class Home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html") ;

        PrintWriter out = response.getWriter() ;
        out.println("<html>") ;
        out.println("<head>") ;
        out.println("<title>Bonjour le monde !</title>") ;
        out.println("</head>") ;
        out.println("<body>") ;
        out.println("<h1>Bonjour le monde !</h1>") ;
        out.println("</body>") ;
        out.println("</html>") ;
    }
}