package com.avisha_neu.basics.opengl.geometry;

import com.avisha_neu.basics.opengl.core.Vertex;

import java.util.Collection;

/**
 * Created by avisha.neu on 26.01.2017.
 */
public class SceneObject {

    Collection<Vertex> vertexes;
    //матрица смежности или готовая реализация графа?

    public Collection<Vertex> getVertexes() {
        return vertexes;
    }

    public void setVertexes(Collection<Vertex> vertexes) {
        this.vertexes = vertexes;
    }
}
