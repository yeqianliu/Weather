package com.example.administrator.weather.List;

public class GridItem  {
    private String imageUrl;
    private String title;
    private  String text;

    public GridItem(String imageUrl,String title,String text) {
        super();
        this.imageUrl = imageUrl;
        this.title = title;
        this.text = text;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
