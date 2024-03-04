package kz.bitlab.academy.comments.entity;

import kz.bitlab.academy.news.entity.NewsEntity;
import kz.bitlab.academy.users.entity.UserEntity;

import java.time.LocalDateTime;

public class CommentEntity {

    private Long id;
    private String comment;

    private LocalDateTime post_date;

    private UserEntity user;

    private NewsEntity news;

    public CommentEntity(){

    }

    public CommentEntity(Long id, String comment, LocalDateTime post_date, UserEntity user, NewsEntity news) {
        this.id = id;
        this.comment = comment;
        this.post_date = post_date;
        this.user = user;
        this.news = news;
    }

    public CommentEntity(String comment, LocalDateTime post_date, UserEntity user, NewsEntity news) {
        this.comment = comment;
        this.post_date = post_date;
        this.user = user;
        this.news = news;
    }

    public CommentEntity(String comment, UserEntity user, NewsEntity news) {
        this.comment = comment;
        this.user = user;
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDateTime post_date) {
        this.post_date = post_date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public NewsEntity getNews() {
        return news;
    }

    public void setNews(NewsEntity news) {
        this.news = news;
    }
}
