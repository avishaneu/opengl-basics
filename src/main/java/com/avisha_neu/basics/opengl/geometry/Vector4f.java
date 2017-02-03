package com.avisha_neu.basics.opengl.geometry;

/**
 * Created by avisha.neu on 03.02.2017.
 */
public class Vector4f {
    private float x;
    private float y;
    private float z;
    private float w;

    public Vector4f() {
    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public Vector4f multiply(float scalar) {
        return new Vector4f(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    public float multiplyScalar(Vector4f vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z + this.w * vector.w;
    }
}
