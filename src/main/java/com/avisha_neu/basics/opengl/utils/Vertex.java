package com.avisha_neu.basics.opengl.utils;


/**
 * Created by avisha.neu on 27.01.2017.
 */
public class Vertex {

    private Point position;
    private Color color;

    public Vertex() {
    }

    public Vertex(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (!position.equals(vertex.position)) return false;
        return color.equals(vertex.color);

    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}
