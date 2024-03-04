package kz.bitlab.academy.comments.service;

import kz.bitlab.academy.comments.entity.CommentEntity;
import kz.bitlab.academy.core.DBManager;
import kz.bitlab.academy.news.service.NewsService;
import kz.bitlab.academy.users.service.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentService extends DBManager {

    public static void create(CommentEntity comment){
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into comments (comment, news_id, user_id) " +
                    "values(?, ?, ?)");

            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getNews().getId());
            statement.setLong(3, comment.getUser().getId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteAllByNewsId(Long newsId){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "delete from comments where news_id = ?");
            statement.setLong(1, newsId);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<CommentEntity> findAllByNews(Long newsId){
        List<CommentEntity> commentList = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from comments where news_id = ?");
            statement.setLong(1, newsId);
            ResultSet resultSet = statement.executeQuery();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            while(resultSet.next()){


                CommentEntity comment = new CommentEntity();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                var createdAt = resultSet.getTimestamp("post_date").toLocalDateTime();
                comment.setPost_date(LocalDateTime.parse(createdAt.format(formatter)));
                comment.setNews(NewsService.findById(resultSet.getLong("news_id")));
                comment.setUser(UserService.findById(resultSet.getLong("user_id")));

                commentList.add(comment);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return commentList;
    }
}
