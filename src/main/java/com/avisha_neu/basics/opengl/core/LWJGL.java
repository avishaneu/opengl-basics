package com.avisha_neu.basics.opengl.core;

import com.avisha_neu.basics.opengl.properties.WindowProperties;
import org.apache.log4j.Logger;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;

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
        Scene scene = new Scene();
        while (!window.isClosing()) {
            //scene = SceneGenerator.next();
            window.drawScene(scene);
        }
    }

}
