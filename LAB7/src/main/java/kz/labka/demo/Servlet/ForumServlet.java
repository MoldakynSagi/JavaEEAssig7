package kz.javaee.demo.Servlet;

import kz.javaee.demo.Controller.ForumController;
import kz.javaee.demo.Model.Message;
import kz.javaee.demo.Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ForumServlet extends HttpServlet {

    private User user;
    private ForumController forumController = new ForumController();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("forum.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");

        HttpSession session = req.getSession();
        String tok1 = (String) session.getAttribute("token");
        if (tok1 != null ) {
            int token1 = Integer.parseInt(tok1);
            user = forumController.auth(token1);
            if (user != null) {
                Message mess = new Message(0, message, user.getUsername(), 0);
                forumController.getForum_messages().insertMessage(1, mess, forumController.getUsersCollection().getConn());
                req.getRequestDispatcher("forum.jsp").forward(req, resp);

            }
            else
                req.getRequestDispatcher("error_page.jsp").forward(req, resp);

        } else
            req.getRequestDispatcher("error_page.jsp").forward(req, resp);


    }
}