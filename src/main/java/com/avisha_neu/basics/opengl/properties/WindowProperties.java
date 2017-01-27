package com.avisha_neu.basics.opengl.properties;

import com.avisha_neu.basics.opengl.core.Color;

/**
 * Created by avisha.neu on 25.01.2017.
 */
public class WindowProperties {

    private int width = 300;
    private int height = 300;
    private String title = "Hello World!";
    private Color clearColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getClearColor() {
        return clearColor;
    }

    public void setClearColor(Color clearColor) {
        this.clearColor = clearColor;
    }
}
