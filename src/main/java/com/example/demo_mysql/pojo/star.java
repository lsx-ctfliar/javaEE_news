package com.example.demo_mysql.pojo;

/**
 * @Classname star
 * @Description TODO
 * @Date 2023/2/18 13:26
 * @Created by 余
 */
public class star {
    @Override
    public String toString() {
        return "star{" +
                "userId=" + userId +
                ", newsId=" + newsId +
                ", id=" + id +
                '}';
    }

    private int userId;
    private int newsId;
    private int id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }


}
