package com.avisha_neu.basics.opengl.core;

import com.avisha_neu.basics.opengl.geometry.Edge;
import com.avisha_neu.basics.opengl.geometry.Point2D;
import com.avisha_neu.basics.opengl.geometry.SceneObject;
import com.avisha_neu.basics.opengl.geometry.Vertex;

import java.util.HashSet;

/**
 * Created by avisha.neu on 26.01.2017.
 */
public class Scene {

    HashSet<SceneObject> sceneObjects;

    {
        sceneObjects = new HashSet<>();
        HashSet<Edge> edges = new HashSet<>();
        Vertex vertex1 = new Vertex(new Point2D(100, 100), new Color(0.5f, 0.5f, 1.0f));
        Vertex vertex2 = new Vertex(new Point2D(100 + 200, 100), new Color(1.0f, 0.5f, 1.0f));
        Vertex vertex3 = new Vertex(new Point2D(100 + 200, 100 + 200), new Color(1.0f, 0.5f, 1.0f));
        Vertex vertex4 = new Vertex(new Point2D(100, 100 + 200), new Color(1.0f, 0.5f, 1.0f));
        Edge edge1 = new Edge(vertex1, vertex2);
        Edge edge2 = new Edge(vertex2, vertex3);
        Edge edge3 = new Edge(vertex3, vertex4);
        Edge edge4 = new Edge(vertex4, vertex1);
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);

        SceneObject sceneObject = new SceneObject(edges);
        sceneObjects.add(sceneObject);
    }

    public void render() {
        sceneObjects.forEach(SceneObject::render);
    }
}
