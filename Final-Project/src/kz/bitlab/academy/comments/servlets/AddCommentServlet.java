package kz.bitlab.academy.comments.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kz.bitlab.academy.comments.entity.CommentEntity;
import kz.bitlab.academy.comments.service.CommentService;
import kz.bitlab.academy.news.service.NewsService;
import kz.bitlab.academy.users.entity.UserEntity;

import java.io.IOException;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");

        String comment = req.getParameter("comment");

        Long newsId = Long.parseLong(req.getParameter("newsId"));

        var news = NewsService.findById(newsId);
        CommentService.create(new CommentEntity(comment, currentUser, news));
        resp.sendRedirect("/details?id=" + newsId);
     }
}
