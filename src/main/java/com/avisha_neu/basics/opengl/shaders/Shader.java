package com.avisha_neu.basics.opengl.shaders;

import org.lwjgl.opengl.GL11;

import java.io.*;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by avisha.neu on 15.02.2017.
 */
public class Shader {

    private final int id;


    private Shader(int type, CharSequence source) {
        id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);

        checkStatus();
    }

    public static Shader loadShader(int type, String path) {
        StringBuilder builder = new StringBuilder();

        try (InputStream in = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load a shader file!"
                    + System.lineSeparator() + ex.getMessage());
        }

        CharSequence source = builder.toString();
        return new Shader(type, source);
    }

    public int getId() {
        return id;
    }

    private void checkStatus() {
        int status = glGetShaderi(id, GL_COMPILE_STATUS);
        if (status != GL11.GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(id));
        }
    }

    public void delete() {
        glDeleteShader(id);
    }

}
