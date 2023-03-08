package com.example.demo_mysql.pojo;

/**
 * @Classname content
 * @Description TODO
 * @Date 2023/2/18 13:35
 * @Created by 余
 */
public class content {

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    private String userImage;

    @Override
    public String toString() {
        return "content{" +
                "userImage='" + userImage + '\'' +
                ", id=" + id +
                ", userId=" + userId +
                ", newsId=" + newsId +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    private int id;
    private int userId;

    private int newsId;

    private String username;
    private String time;
    private String content;

    public int getNewsId() {
        return newsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
