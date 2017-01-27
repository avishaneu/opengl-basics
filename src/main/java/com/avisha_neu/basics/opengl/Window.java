package com.avisha_neu.basics.opengl;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by avisha.neu on 25.01.2017.
 */
public class Window {

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

    public void drawScene(Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        scene.draw();
        swapBuffers();
        glfwPollEvents();
    }
}
