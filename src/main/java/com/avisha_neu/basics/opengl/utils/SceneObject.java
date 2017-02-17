package com.avisha_neu.basics.opengl.utils;

import org.lwjgl.opengl.GL11;

import java.util.HashSet;

/**
 * Created by avisha.neu on 26.01.2017.
 */
public class SceneObject {

    HashSet<Edge> edges;

    public SceneObject() {
    }

    public SceneObject(HashSet<Edge> edges) {
        this.edges = edges;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    public void render() {
        GL11.glBegin(GL11.GL_LINES);
        for (Edge edge : edges) {
            Vertex v1 = edge.getVertex1();
            Vertex v2 = edge.getVertex2();
            GL11.glColor3f(v1.getColor().getRed(), v1.getColor().getGreen(), v1.getColor().getBlue());
            GL11.glVertex3f(v1.getPosition().getX(), v1.getPosition().getY(), v1.getPosition().getZ());
            GL11.glColor3f(v2.getColor().getRed(), v2.getColor().getGreen(), v2.getColor().getBlue());
            GL11.glVertex3f(v2.getPosition().getX(), v2.getPosition().getY(), v2.getPosition().getZ());
        }
        GL11.glEnd();
    }
}
