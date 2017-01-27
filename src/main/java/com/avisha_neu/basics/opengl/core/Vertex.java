package com.avisha_neu.basics.opengl.core;


import com.avisha_neu.basics.opengl.geometry.Point;

/**
 * Created by avisha.neu on 27.01.2017.
 */
public class Vertex {

    private Point position;
    private Color color;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
