package com.avisha_neu.basics.opengl.geometry;

/**
 * Created by avisha.neu on 03.02.2017.
 */
public class Matrix4f {

    /*private float m00;
    private float m10;
    private float m20;
    private float m30;

    private float m01;
    private float m11;
    private float m21;
    private float m31;

    private float m02;
    private float m12;
    private float m22;
    private float m32;

    private float m03;
    private float m13;
    private float m23;
    private float m33;*/

    Vector4f firstRow;
    Vector4f secondRow;
    Vector4f thirdRow;
    Vector4f forthRow;


    public Matrix4f() {
    }

    public Matrix4f(Vector4f firstRow, Vector4f secondRow, Vector4f thirdRow, Vector4f forthRow) {
        this.firstRow = firstRow;
        this.secondRow = secondRow;
        this.thirdRow = thirdRow;
        this.forthRow = forthRow;
    }


    public static Matrix4f createIdentity() {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        return matrix;
    }

    public void setIdentity() {
        firstRow = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f);
        secondRow = new Vector4f(0.0f, 1.0f, 0.0f, 0.0f);
        thirdRow = new Vector4f(0.0f, 0.0f, 1.0f, 0.0f);
        forthRow = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    }

   /* public static Matrix4f createOrtho(){

    }*/

    public Matrix4f multiplty(float scalar) {
        Matrix4f matrix = new Matrix4f();
        matrix.firstRow = this.firstRow.multiply(scalar);
        matrix.secondRow = this.secondRow.multiply(scalar);
        matrix.thirdRow = this.thirdRow.multiply(scalar);
        matrix.forthRow = this.forthRow.multiply(scalar);
        return matrix;
    }

    public Vector4f multiplty(Vector4f vector) {
        Vector4f resultVector = new Vector4f();
        resultVector.setX(this.firstRow.multiplyScalar(vector));
        resultVector.setY(this.secondRow.multiplyScalar(vector));
        resultVector.setZ(this.thirdRow.multiplyScalar(vector));
        resultVector.setW(this.forthRow.multiplyScalar(vector));
        return resultVector;
    }
}
