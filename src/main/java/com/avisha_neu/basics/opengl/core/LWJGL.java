package com.avisha_neu.basics.opengl.core;

import com.avisha_neu.basics.opengl.Window;
import com.avisha_neu.basics.opengl.WindowProperties;
import org.apache.log4j.Logger;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by avisha.neu on 25.01.2017.
 */
public class LWJGL {
    private static final Logger log = Logger.getLogger(LWJGL.class);
    private Window window;

    public void run() {
        log.info("Starting LWJGL " + Version.getVersion());
        init();
        loop();
        terminate();
    }

    private void terminate() {
        window.destroy();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        window = Window.createNew(new WindowProperties());
        if (!window.exists()) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        window.center();
        window.makeCurrent();
        // Enable v-sync
        glfwSwapInterval(1);
        window.show();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!window.isClosing()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            window.swapBuffers(); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

}
