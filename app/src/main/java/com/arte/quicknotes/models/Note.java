package com.arte.quicknotes.models;

/**
 * Created by Sandra on 27/04/2016.
 */
public class Note {

    private String title;
    private String content;

    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getExcept() {
        if (content == null) {
            return "";
        }
        if (content.length() > 50) {
            return content;
        }
        return content.substring(0, 50);
    }
}
