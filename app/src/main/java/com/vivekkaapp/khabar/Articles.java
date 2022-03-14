package com.vivekkaapp.khabar;

public class Articles {
    private String title;
    private String description;
    private String url;
    private String content;
    private String urlToImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Articles(String title, String description, String url, String content, String urlToImage) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.content = content;
        this.urlToImage = urlToImage;
    }
}
