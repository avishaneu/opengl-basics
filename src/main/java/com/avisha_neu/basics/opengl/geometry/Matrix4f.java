package com.avisha_neu.basics.opengl.geometry;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by avisha.neu on 03.02.2017.
 */
public class Matrix4f {

    private Vector4f firstRow;
    private Vector4f secondRow;
    private Vector4f thirdRow;
    private Vector4f forthRow;

    private FloatBuffer buffer = BufferUtils.createFloatBuffer(16);

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

    public static Matrix4f createOrtho(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f scaling =
                createScaling(new Vector4f(2.0f / (right - left), 2.0f / (top - bottom), -2.0f / (far - near), 0.0f));
        Matrix4f translation =
                createTranslation(new Vector4f(-(right + left) / 2.0f, -(top + bottom) / 2.0f, (far + near) / 2.0f, 0.0f));
        return scaling.multiply(translation);
    }

    public static Matrix4f createScaling(Vector4f scalingVector) {
        Matrix4f matrix = new Matrix4f();
        matrix.firstRow = new Vector4f(scalingVector.getX(), 0.0f, 0.0f, 0.0f);
        matrix.secondRow = new Vector4f(0.0f, scalingVector.getY(), 0.0f, 0.0f);
        matrix.thirdRow = new Vector4f(0.0f, 0.0f, scalingVector.getZ(), 0.0f);
        matrix.forthRow = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
        return matrix;
    }

    public static Matrix4f createTranslation(Vector4f translationVector) {
        Matrix4f matrix = new Matrix4f();
        matrix.firstRow = new Vector4f(1.0f, 0.0f, 0.0f, translationVector.getX());
        matrix.secondRow = new Vector4f(0.0f, 1.0f, 0.0f, translationVector.getY());
        matrix.thirdRow = new Vector4f(0.0f, 0.0f, 1.0f, translationVector.getZ());
        matrix.forthRow = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
        return matrix;
    }

    public void setIdentity() {
        firstRow = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f);
        secondRow = new Vector4f(0.0f, 1.0f, 0.0f, 0.0f);
        thirdRow = new Vector4f(0.0f, 0.0f, 1.0f, 0.0f);
        forthRow = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    }

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

    private Vector4f getFirstCol() {
        return new Vector4f(this.firstRow.getX(),
                this.secondRow.getX(), this.thirdRow.getX(), this.forthRow.getX());
    }

    private Vector4f getSecondCol() {
        return new Vector4f(this.firstRow.getY(),
                this.secondRow.getY(), this.thirdRow.getY(), this.forthRow.getY());
    }

    private Vector4f getThirdCol() {
        return new Vector4f(this.firstRow.getZ(),
                this.secondRow.getZ(), this.thirdRow.getZ(), this.forthRow.getZ());
    }

    private Vector4f getForthCol() {
        return new Vector4f(this.firstRow.getW(),
                this.secondRow.getW(), this.thirdRow.getW(), this.forthRow.getW());
    }


    private Matrix4f multiply(Matrix4f matrix) {
        Vector4f resultFirstRow = new Vector4f();
        resultFirstRow.setX(this.firstRow.multiplyScalar(matrix.getFirstCol()));
        resultFirstRow.setY(this.firstRow.multiplyScalar(matrix.getSecondCol()));
        resultFirstRow.setZ(this.firstRow.multiplyScalar(matrix.getThirdCol()));
        resultFirstRow.setW(this.firstRow.multiplyScalar(matrix.getForthCol()));

        Vector4f resultSecondRow = new Vector4f();
        resultSecondRow.setX(this.secondRow.multiplyScalar(matrix.getFirstCol()));
        resultSecondRow.setY(this.secondRow.multiplyScalar(matrix.getSecondCol()));
        resultSecondRow.setZ(this.secondRow.multiplyScalar(matrix.getThirdCol()));
        resultSecondRow.setW(this.secondRow.multiplyScalar(matrix.getForthCol()));

        Vector4f resultThirdRow = new Vector4f();
        resultThirdRow.setX(this.thirdRow.multiplyScalar(matrix.getFirstCol()));
        resultThirdRow.setY(this.thirdRow.multiplyScalar(matrix.getSecondCol()));
        resultThirdRow.setZ(this.thirdRow.multiplyScalar(matrix.getThirdCol()));
        resultThirdRow.setW(this.thirdRow.multiplyScalar(matrix.getForthCol()));

        Vector4f resultForthRow = new Vector4f();
        resultForthRow.setX(this.forthRow.multiplyScalar(matrix.getFirstCol()));
        resultForthRow.setY(this.forthRow.multiplyScalar(matrix.getSecondCol()));
        resultForthRow.setZ(this.forthRow.multiplyScalar(matrix.getThirdCol()));
        resultForthRow.setW(this.forthRow.multiplyScalar(matrix.getForthCol()));

        return new Matrix4f(resultFirstRow, resultSecondRow, resultThirdRow, resultForthRow);
    }

    public FloatBuffer getFloatBuffer() {
        buffer.clear();

        buffer.put(firstRow.getX());
        buffer.put(secondRow.getX());
        buffer.put(thirdRow.getX());
        buffer.put(forthRow.getX());

        buffer.put(firstRow.getY());
        buffer.put(secondRow.getY());
        buffer.put(thirdRow.getY());
        buffer.put(forthRow.getY());

        buffer.put(firstRow.getZ());
        buffer.put(secondRow.getZ());
        buffer.put(thirdRow.getZ());
        buffer.put(forthRow.getZ());

        buffer.put(firstRow.getW());
        buffer.put(secondRow.getW());
        buffer.put(thirdRow.getW());
        buffer.put(forthRow.getW());

        buffer.flip();
        buffer.rewind();

        return buffer;

    }
}
