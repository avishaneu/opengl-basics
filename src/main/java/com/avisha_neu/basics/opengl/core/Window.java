package com.avisha_neu.basics.opengl.core;

import com.avisha_neu.basics.opengl.properties.WindowProperties;
import com.avisha_neu.basics.opengl.shaders.Shader;
import com.avisha_neu.basics.opengl.utils.Color;
import com.avisha_neu.basics.opengl.utils.Matrix4f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindFragDataLocation;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by avisha.neu on 25.01.2017.
 * https://github.com/SilverTiger/lwjgl3-tutorial/blob/master/src/silvertiger/tutorial/lwjgl/state/ExampleState.java
 */
public class Window {

    static int shaderProgram;
    private long windowID;
    private Window() {
    }

    public static Window createNew(WindowProperties properties) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        Window window = new Window();
        window.windowID = glfwCreateWindow(properties.getWidth(),
                properties.getHeight(), properties.getTitle(), NULL, NULL);
        glfwSetKeyCallback(window.windowID, (windowObject, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(windowObject, true);
        });
        window.makeCurrent();
        GL.createCapabilities();
        window.setClearColor(properties.getClearColor());
        //GL11.glMatrixMode(GL11.GL_PROJECTION_MATRIX);
        //GL11.glLoadIdentity();
        //GL11.glLoadMatrixf(Matrix4f.createPerspective(-1, 1, -1, 1, 0.1f, 20).getFloatBuffer());
        // GL11.glMatrixMode(GL11.GL_MODELVIEW_MATRIX);

        Shader vertexShader = Shader.loadShader(GL_VERTEX_SHADER, "screen.vert");
        Shader fragmentShader = Shader.loadShader(GL_FRAGMENT_SHADER, "screen.frag");

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader.getId());
        glAttachShader(shaderProgram, fragmentShader.getId());
        glBindFragDataLocation(shaderProgram, 0, "fragColor");
        glLinkProgram(shaderProgram);
        // int  vaoID = glGenVertexArrays();
        //glBindVertexArray(vaoID);

        glUseProgram(shaderProgram);


        return window;
    }

    public boolean exists() {
        return windowID != NULL;
    }

    public void show() {
        glfwShowWindow(windowID);
    }

    public void makeCurrent() {
        glfwMakeContextCurrent(windowID);
    }

    public void center() {
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(windowID, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    windowID,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }
    }

    public void setClearColor(Color color) {
        glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void destroy() {
        glfwFreeCallbacks(windowID);
        glfwDestroyWindow(windowID);
    }

    public boolean isClosing() {
        return glfwWindowShouldClose(windowID);
    }

    private void swapBuffers() {
        glfwSwapBuffers(windowID);
    }

    public void renderScene(Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer vertices = stack.mallocFloat(3 * 6);
            vertices.put(-0.6f).put(-0.4f).put(-1f).put(1f).put(0f).put(0f);
            vertices.put(0.6f).put(-0.4f).put(-1f).put(0f).put(1f).put(0f);
            vertices.put(0f).put(0.6f).put(-1f).put(0f).put(0f).put(1f);
            vertices.flip();

            int vbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        }

        int floatSize = 4;

        int posAttrib = glGetAttribLocation(shaderProgram, "position");
        glEnableVertexAttribArray(posAttrib);
        glVertexAttribPointer(posAttrib, 3, GL11.GL_FLOAT, false, 6 * floatSize, 0);

        int colAttrib = glGetAttribLocation(shaderProgram, "color");
        glEnableVertexAttribArray(colAttrib);
        glVertexAttribPointer(colAttrib, 3, GL11.GL_FLOAT, false, 6 * floatSize, 3 * floatSize);


        int uniModel = glGetUniformLocation(shaderProgram, "model");
        Matrix4f model = Matrix4f.createIdentity();
        glUniformMatrix4fv(uniModel, false, model.getFloatBuffer());

        int uniView = glGetUniformLocation(shaderProgram, "view");
        Matrix4f view = Matrix4f.createIdentity();
        glUniformMatrix4fv(uniView, false, view.getFloatBuffer());

        int uniProjection = glGetUniformLocation(shaderProgram, "projection");
        Matrix4f projection = Matrix4f.createOrtho(-10, 10, -10, 10, 0.1f, 100);
        glUniformMatrix4fv(uniProjection, false, projection.getFloatBuffer());

        glDrawArrays(GL_TRIANGLES, 0, 3);

        swapBuffers();
        glfwPollEvents();
    }
}
