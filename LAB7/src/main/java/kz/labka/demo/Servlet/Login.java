package kz.javaee.demo.Servlet;

import kz.javaee.demo.Controller.ForumController;
import kz.javaee.demo.Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Login extends HttpServlet {
    private ForumController forumController = new ForumController();
    private User user;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        user = forumController.signIn(username, password);

        if (user == null) {
            resp.getWriter().println("Invalid login, or password!");
        } else {
            String tok = forumController.createToken(username);
            req.getSession().setAttribute("token", tok);
            req.getRequestDispatcher("forum.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String tok1 = (String) session.getAttribute("token");
        if (tok1 != null ) {
            int token1 = Integer.parseInt(tok1);
            user = forumController.auth(token1);
            if (user != null) {
                    req.getRequestDispatcher("forum.jsp").forward(req, resp);
            }
        }
    }
}
