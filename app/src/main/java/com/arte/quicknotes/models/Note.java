package com.arte.quicknotes.models;

import java.io.Serializable;

/**
 * Created by Sandra on 27/04/2016.
 */
public class Note implements Serializable {

    private static final int EXCEPT_MAX_LENGTH = 100;

    private int id;
    private String title;
    private String content;

    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (content.length() < EXCEPT_MAX_LENGTH) {
            return content;
        }
        return content.substring(0, EXCEPT_MAX_LENGTH);
    }
}
