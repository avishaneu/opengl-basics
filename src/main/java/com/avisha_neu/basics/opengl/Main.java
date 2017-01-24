package com.avisha_neu.basics.opengl;

import com.jogamp.newt.Display;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.Animator;

/**
 * Created by avisha.neu on 24.01.2017.
 */
public class Main implements GLEventListener, KeyListener {
    public static GLWindow glWindow;
    public static Animator animator;

    public static void main(String[] args) {
        Display display = NewtFactory.createDisplay(null);
        Screen screen = NewtFactory.createScreen(display, 0);
        GLProfile glProfile = GLProfile.get(GLProfile.GL4);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glWindow = GLWindow.create(screen, glCapabilities);

        glWindow.setSize(1024, 768);
        glWindow.setPosition(50, 50);
        glWindow.setUndecorated(false);
        glWindow.setAlwaysOnTop(false);
        glWindow.setFullscreen(false);
        glWindow.setPointerVisible(true);
        glWindow.confinePointer(false);
        glWindow.setTitle("HelloWorld!");
        glWindow.setContextCreationFlags(GLContext.CTX_OPTION_DEBUG);
        glWindow.setVisible(true);

        Main main = new Main();
        glWindow.addGLEventListener(main);
        glWindow.addKeyListener(main);

        animator = new Animator(glWindow);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
    }

    public void display(GLAutoDrawable drawable) {
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }


    public void keyPressed(KeyEvent e) {
    }


    public void keyReleased(KeyEvent e) {

    }

    public void dispose(GLAutoDrawable drawable) {
    }
}
