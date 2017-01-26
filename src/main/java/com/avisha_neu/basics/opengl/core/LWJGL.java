package com.avisha_neu.basics.opengl.core;

import com.avisha_neu.basics.opengl.Scene;
import com.avisha_neu.basics.opengl.Window;
import com.avisha_neu.basics.opengl.WindowProperties;
import org.apache.log4j.Logger;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.io.PrintStream;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by avisha.neu on 25.01.2017.
 */
public class LWJGL {
    private static final Logger log = Logger.getLogger(LWJGL.class);
    private Window window;
    private WindowProperties windowProperties = new WindowProperties();

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
        setErrorCallback();
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        window = Window.createNew(windowProperties);
        if (!window.exists()) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        window.center();
        window.makeCurrent();
        // Enable v-sync
        glfwSwapInterval(1);
        window.show();
    }

    private void setErrorCallback() {
        GLFWErrorCallback.createPrint(new PrintStream(System.err) {
            public void print(final String string) {
                log.error(string);
            }
        }).set();
    }

    private void loop() {
        GL.createCapabilities();
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.

        window.setClearColor(windowProperties.getClearColor());
        Scene scene = new Scene();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!window.isClosing()) {
            //scene = SceneGenerator.next();
            window.drawScene(scene);
        }
    }

}
