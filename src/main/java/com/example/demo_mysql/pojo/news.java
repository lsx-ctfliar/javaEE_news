package com.example.demo_mysql.pojo;

public class news {
    private int newsId;
    private int userId;
    private String newsTitle;
    private String newsArticle;
    private String newsImage;
    private String newsTime;
    private int newsYesCount;
    private int newsStarCount;
    private String newsType;

    private String userName;

    @Override
    public String toString() {
        return "news{" +
                "newsId=" + newsId +
                ", userId=" + userId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsArticle='" + newsArticle + '\'' +
                ", newsImage='" + newsImage + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", newsYesCount=" + newsYesCount +
                ", newsStarCount=" + newsStarCount +
                ", newsType='" + newsType + '\'' +
                ", userName='" + userName + '\'' +
                ", newsKind='" + newsKind + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String newsKind;

    public String getNewsKind() {
        return newsKind;
    }

    public void setNewsKind(String newsKind) {
        this.newsKind = newsKind;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsArticle() {
        return newsArticle;
    }

    public void setNewsArticle(String newsArticle) {
        this.newsArticle = newsArticle;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public int getNewsYesCount() {
        return newsYesCount;
    }

    public void setNewsYesCount(int newsYesCount) {
        this.newsYesCount = newsYesCount;
    }

    public int getNewsStarCount() {
        return newsStarCount;
    }

    public void setNewsStarCount(int newsStarCount) {
        this.newsStarCount = newsStarCount;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

}
