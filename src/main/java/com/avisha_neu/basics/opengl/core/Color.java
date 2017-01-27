package com.avisha_neu.basics.opengl.core;

/**
 * Created by avisha.neu on 26.01.2017.
 */
public class Color {
    private float red;
    private float green;
    private float blue;
    private float alpha;

    public Color(float red, float green, float blue) {
        this(red, green, blue, 1.0f);
    }

    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (Float.compare(color.red, red) != 0) return false;
        if (Float.compare(color.green, green) != 0) return false;
        if (Float.compare(color.blue, blue) != 0) return false;
        return Float.compare(color.alpha, alpha) == 0;

    }

    @Override
    public int hashCode() {
        int result = (red != +0.0f ? Float.floatToIntBits(red) : 0);
        result = 31 * result + (green != +0.0f ? Float.floatToIntBits(green) : 0);
        result = 31 * result + (blue != +0.0f ? Float.floatToIntBits(blue) : 0);
        result = 31 * result + (alpha != +0.0f ? Float.floatToIntBits(alpha) : 0);
        return result;
    }
}
