/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.video.transcoder;

import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextureRenderer {

    private static final int FLOAT_SIZE_BYTES = 4;

    private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 5 * FLOAT_SIZE_BYTES;

    private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;

    private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;

    private FloatBuffer mTriangleVertices;

    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\n" + "uniform mat4 uSTMatrix;\n" + "attribute vec4 aPosition;\n" + "attribute vec4 aTextureCoord;\n" + "varying vec2 vTextureCoord;\n" + "void main() {\n" + "  gl_Position = uMVPMatrix * aPosition;\n" + "  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n" + "}\n";

    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\n" + "precision highp float;\n" + "varying vec2 vTextureCoord;\n" + "uniform samplerExternalOES sTexture;\n" + "void main() {\n" + "  gl_FragColor = texture2D(sTexture, vTextureCoord);\n" + "}\n";

    private float[] mMVPMatrix = new float[16];

    private float[] mSTMatrix = new float[16];

    private int mTextureID = -12345;

    private int mProgram;

    private int muMVPMatrixHandle;

    private int muSTMatrixHandle;

    private int maPositionHandle;

    private int maTextureHandle;

    private int rotationAngle;

    public TextureRenderer(int rotation) {
        if (!ListenerUtil.mutListener.listen(56417)) {
            rotationAngle = rotation;
        }
        float[] mTriangleVerticesData = { -1.0f, -1.0f, 0, 0.f, 0.f, 1.0f, -1.0f, 0, 1.f, 0.f, -1.0f, 1.0f, 0, 0.f, 1.f, 1.0f, 1.0f, 0, 1.f, 1.f };
        if (!ListenerUtil.mutListener.listen(56422)) {
            mTriangleVertices = ByteBuffer.allocateDirect((ListenerUtil.mutListener.listen(56421) ? (mTriangleVerticesData.length % FLOAT_SIZE_BYTES) : (ListenerUtil.mutListener.listen(56420) ? (mTriangleVerticesData.length / FLOAT_SIZE_BYTES) : (ListenerUtil.mutListener.listen(56419) ? (mTriangleVerticesData.length - FLOAT_SIZE_BYTES) : (ListenerUtil.mutListener.listen(56418) ? (mTriangleVerticesData.length + FLOAT_SIZE_BYTES) : (mTriangleVerticesData.length * FLOAT_SIZE_BYTES)))))).order(ByteOrder.nativeOrder()).asFloatBuffer();
        }
        if (!ListenerUtil.mutListener.listen(56423)) {
            mTriangleVertices.put(mTriangleVerticesData).position(0);
        }
        if (!ListenerUtil.mutListener.listen(56424)) {
            Matrix.setIdentityM(mSTMatrix, 0);
        }
    }

    public int getTextureId() {
        return mTextureID;
    }

    public void drawFrame(SurfaceTexture st, boolean invert) {
        if (!ListenerUtil.mutListener.listen(56425)) {
            checkGlError("onDrawFrame start");
        }
        if (!ListenerUtil.mutListener.listen(56426)) {
            st.getTransformMatrix(mSTMatrix);
        }
        if (!ListenerUtil.mutListener.listen(56433)) {
            if (invert) {
                if (!ListenerUtil.mutListener.listen(56427)) {
                    mSTMatrix[5] = -mSTMatrix[5];
                }
                if (!ListenerUtil.mutListener.listen(56432)) {
                    mSTMatrix[13] = (ListenerUtil.mutListener.listen(56431) ? (1.0f % mSTMatrix[13]) : (ListenerUtil.mutListener.listen(56430) ? (1.0f / mSTMatrix[13]) : (ListenerUtil.mutListener.listen(56429) ? (1.0f * mSTMatrix[13]) : (ListenerUtil.mutListener.listen(56428) ? (1.0f + mSTMatrix[13]) : (1.0f - mSTMatrix[13])))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56434)) {
            GLES20.glUseProgram(mProgram);
        }
        if (!ListenerUtil.mutListener.listen(56435)) {
            checkGlError("glUseProgram");
        }
        if (!ListenerUtil.mutListener.listen(56436)) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        }
        if (!ListenerUtil.mutListener.listen(56437)) {
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureID);
        }
        if (!ListenerUtil.mutListener.listen(56438)) {
            mTriangleVertices.position(TRIANGLE_VERTICES_DATA_POS_OFFSET);
        }
        if (!ListenerUtil.mutListener.listen(56439)) {
            GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT, false, TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);
        }
        if (!ListenerUtil.mutListener.listen(56440)) {
            checkGlError("glVertexAttribPointer maPosition");
        }
        if (!ListenerUtil.mutListener.listen(56441)) {
            GLES20.glEnableVertexAttribArray(maPositionHandle);
        }
        if (!ListenerUtil.mutListener.listen(56442)) {
            checkGlError("glEnableVertexAttribArray maPositionHandle");
        }
        if (!ListenerUtil.mutListener.listen(56443)) {
            mTriangleVertices.position(TRIANGLE_VERTICES_DATA_UV_OFFSET);
        }
        if (!ListenerUtil.mutListener.listen(56444)) {
            GLES20.glVertexAttribPointer(maTextureHandle, 2, GLES20.GL_FLOAT, false, TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);
        }
        if (!ListenerUtil.mutListener.listen(56445)) {
            checkGlError("glVertexAttribPointer maTextureHandle");
        }
        if (!ListenerUtil.mutListener.listen(56446)) {
            GLES20.glEnableVertexAttribArray(maTextureHandle);
        }
        if (!ListenerUtil.mutListener.listen(56447)) {
            checkGlError("glEnableVertexAttribArray maTextureHandle");
        }
        if (!ListenerUtil.mutListener.listen(56448)) {
            GLES20.glUniformMatrix4fv(muSTMatrixHandle, 1, false, mSTMatrix, 0);
        }
        if (!ListenerUtil.mutListener.listen(56449)) {
            GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        }
        if (!ListenerUtil.mutListener.listen(56450)) {
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
        }
        if (!ListenerUtil.mutListener.listen(56451)) {
            checkGlError("glDrawArrays");
        }
        if (!ListenerUtil.mutListener.listen(56452)) {
            GLES20.glFinish();
        }
    }

    public void surfaceCreated() {
        if (!ListenerUtil.mutListener.listen(56453)) {
            mProgram = createProgram(VERTEX_SHADER, FRAGMENT_SHADER);
        }
        if (!ListenerUtil.mutListener.listen(56459)) {
            if ((ListenerUtil.mutListener.listen(56458) ? (mProgram >= 0) : (ListenerUtil.mutListener.listen(56457) ? (mProgram <= 0) : (ListenerUtil.mutListener.listen(56456) ? (mProgram > 0) : (ListenerUtil.mutListener.listen(56455) ? (mProgram < 0) : (ListenerUtil.mutListener.listen(56454) ? (mProgram != 0) : (mProgram == 0))))))) {
                throw new RuntimeException("failed creating program");
            }
        }
        if (!ListenerUtil.mutListener.listen(56460)) {
            maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
        }
        if (!ListenerUtil.mutListener.listen(56461)) {
            checkGlError("glGetAttribLocation aPosition");
        }
        if (!ListenerUtil.mutListener.listen(56467)) {
            if ((ListenerUtil.mutListener.listen(56466) ? (maPositionHandle >= -1) : (ListenerUtil.mutListener.listen(56465) ? (maPositionHandle <= -1) : (ListenerUtil.mutListener.listen(56464) ? (maPositionHandle > -1) : (ListenerUtil.mutListener.listen(56463) ? (maPositionHandle < -1) : (ListenerUtil.mutListener.listen(56462) ? (maPositionHandle != -1) : (maPositionHandle == -1))))))) {
                throw new RuntimeException("Could not get attrib location for aPosition");
            }
        }
        if (!ListenerUtil.mutListener.listen(56468)) {
            maTextureHandle = GLES20.glGetAttribLocation(mProgram, "aTextureCoord");
        }
        if (!ListenerUtil.mutListener.listen(56469)) {
            checkGlError("glGetAttribLocation aTextureCoord");
        }
        if (!ListenerUtil.mutListener.listen(56475)) {
            if ((ListenerUtil.mutListener.listen(56474) ? (maTextureHandle >= -1) : (ListenerUtil.mutListener.listen(56473) ? (maTextureHandle <= -1) : (ListenerUtil.mutListener.listen(56472) ? (maTextureHandle > -1) : (ListenerUtil.mutListener.listen(56471) ? (maTextureHandle < -1) : (ListenerUtil.mutListener.listen(56470) ? (maTextureHandle != -1) : (maTextureHandle == -1))))))) {
                throw new RuntimeException("Could not get attrib location for aTextureCoord");
            }
        }
        if (!ListenerUtil.mutListener.listen(56476)) {
            muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        }
        if (!ListenerUtil.mutListener.listen(56477)) {
            checkGlError("glGetUniformLocation uMVPMatrix");
        }
        if (!ListenerUtil.mutListener.listen(56483)) {
            if ((ListenerUtil.mutListener.listen(56482) ? (muMVPMatrixHandle >= -1) : (ListenerUtil.mutListener.listen(56481) ? (muMVPMatrixHandle <= -1) : (ListenerUtil.mutListener.listen(56480) ? (muMVPMatrixHandle > -1) : (ListenerUtil.mutListener.listen(56479) ? (muMVPMatrixHandle < -1) : (ListenerUtil.mutListener.listen(56478) ? (muMVPMatrixHandle != -1) : (muMVPMatrixHandle == -1))))))) {
                throw new RuntimeException("Could not get attrib location for uMVPMatrix");
            }
        }
        if (!ListenerUtil.mutListener.listen(56484)) {
            muSTMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uSTMatrix");
        }
        if (!ListenerUtil.mutListener.listen(56485)) {
            checkGlError("glGetUniformLocation uSTMatrix");
        }
        if (!ListenerUtil.mutListener.listen(56491)) {
            if ((ListenerUtil.mutListener.listen(56490) ? (muSTMatrixHandle >= -1) : (ListenerUtil.mutListener.listen(56489) ? (muSTMatrixHandle <= -1) : (ListenerUtil.mutListener.listen(56488) ? (muSTMatrixHandle > -1) : (ListenerUtil.mutListener.listen(56487) ? (muSTMatrixHandle < -1) : (ListenerUtil.mutListener.listen(56486) ? (muSTMatrixHandle != -1) : (muSTMatrixHandle == -1))))))) {
                throw new RuntimeException("Could not get attrib location for uSTMatrix");
            }
        }
        int[] textures = new int[1];
        if (!ListenerUtil.mutListener.listen(56492)) {
            GLES20.glGenTextures(1, textures, 0);
        }
        if (!ListenerUtil.mutListener.listen(56493)) {
            mTextureID = textures[0];
        }
        if (!ListenerUtil.mutListener.listen(56494)) {
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextureID);
        }
        if (!ListenerUtil.mutListener.listen(56495)) {
            checkGlError("glBindTexture mTextureID");
        }
        if (!ListenerUtil.mutListener.listen(56496)) {
            GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        }
        if (!ListenerUtil.mutListener.listen(56497)) {
            GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        }
        if (!ListenerUtil.mutListener.listen(56498)) {
            GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        }
        if (!ListenerUtil.mutListener.listen(56499)) {
            GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        }
        if (!ListenerUtil.mutListener.listen(56500)) {
            checkGlError("glTexParameter");
        }
        if (!ListenerUtil.mutListener.listen(56501)) {
            Matrix.setIdentityM(mMVPMatrix, 0);
        }
        if (!ListenerUtil.mutListener.listen(56508)) {
            if ((ListenerUtil.mutListener.listen(56506) ? (rotationAngle >= 0) : (ListenerUtil.mutListener.listen(56505) ? (rotationAngle <= 0) : (ListenerUtil.mutListener.listen(56504) ? (rotationAngle > 0) : (ListenerUtil.mutListener.listen(56503) ? (rotationAngle < 0) : (ListenerUtil.mutListener.listen(56502) ? (rotationAngle == 0) : (rotationAngle != 0))))))) {
                if (!ListenerUtil.mutListener.listen(56507)) {
                    Matrix.rotateM(mMVPMatrix, 0, rotationAngle, 0, 0, 1);
                }
            }
        }
    }

    private int loadShader(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        if (!ListenerUtil.mutListener.listen(56509)) {
            checkGlError("glCreateShader type=" + shaderType);
        }
        if (!ListenerUtil.mutListener.listen(56510)) {
            GLES20.glShaderSource(shader, source);
        }
        if (!ListenerUtil.mutListener.listen(56511)) {
            GLES20.glCompileShader(shader);
        }
        int[] compiled = new int[1];
        if (!ListenerUtil.mutListener.listen(56512)) {
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        }
        if (!ListenerUtil.mutListener.listen(56520)) {
            if ((ListenerUtil.mutListener.listen(56517) ? (compiled[0] >= 0) : (ListenerUtil.mutListener.listen(56516) ? (compiled[0] <= 0) : (ListenerUtil.mutListener.listen(56515) ? (compiled[0] > 0) : (ListenerUtil.mutListener.listen(56514) ? (compiled[0] < 0) : (ListenerUtil.mutListener.listen(56513) ? (compiled[0] != 0) : (compiled[0] == 0))))))) {
                if (!ListenerUtil.mutListener.listen(56518)) {
                    GLES20.glDeleteShader(shader);
                }
                if (!ListenerUtil.mutListener.listen(56519)) {
                    shader = 0;
                }
            }
        }
        return shader;
    }

    private int createProgram(String vertexSource, String fragmentSource) {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        if (!ListenerUtil.mutListener.listen(56526)) {
            if ((ListenerUtil.mutListener.listen(56525) ? (vertexShader >= 0) : (ListenerUtil.mutListener.listen(56524) ? (vertexShader <= 0) : (ListenerUtil.mutListener.listen(56523) ? (vertexShader > 0) : (ListenerUtil.mutListener.listen(56522) ? (vertexShader < 0) : (ListenerUtil.mutListener.listen(56521) ? (vertexShader != 0) : (vertexShader == 0))))))) {
                return 0;
            }
        }
        int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        if (!ListenerUtil.mutListener.listen(56532)) {
            if ((ListenerUtil.mutListener.listen(56531) ? (pixelShader >= 0) : (ListenerUtil.mutListener.listen(56530) ? (pixelShader <= 0) : (ListenerUtil.mutListener.listen(56529) ? (pixelShader > 0) : (ListenerUtil.mutListener.listen(56528) ? (pixelShader < 0) : (ListenerUtil.mutListener.listen(56527) ? (pixelShader != 0) : (pixelShader == 0))))))) {
                return 0;
            }
        }
        int program = GLES20.glCreateProgram();
        if (!ListenerUtil.mutListener.listen(56533)) {
            checkGlError("glCreateProgram");
        }
        if (!ListenerUtil.mutListener.listen(56539)) {
            if ((ListenerUtil.mutListener.listen(56538) ? (program >= 0) : (ListenerUtil.mutListener.listen(56537) ? (program <= 0) : (ListenerUtil.mutListener.listen(56536) ? (program > 0) : (ListenerUtil.mutListener.listen(56535) ? (program < 0) : (ListenerUtil.mutListener.listen(56534) ? (program != 0) : (program == 0))))))) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(56540)) {
            GLES20.glAttachShader(program, vertexShader);
        }
        if (!ListenerUtil.mutListener.listen(56541)) {
            checkGlError("glAttachShader");
        }
        if (!ListenerUtil.mutListener.listen(56542)) {
            GLES20.glAttachShader(program, pixelShader);
        }
        if (!ListenerUtil.mutListener.listen(56543)) {
            checkGlError("glAttachShader");
        }
        if (!ListenerUtil.mutListener.listen(56544)) {
            GLES20.glLinkProgram(program);
        }
        int[] linkStatus = new int[1];
        if (!ListenerUtil.mutListener.listen(56545)) {
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        }
        if (!ListenerUtil.mutListener.listen(56548)) {
            if (linkStatus[0] != GLES20.GL_TRUE) {
                if (!ListenerUtil.mutListener.listen(56546)) {
                    GLES20.glDeleteProgram(program);
                }
                if (!ListenerUtil.mutListener.listen(56547)) {
                    program = 0;
                }
            }
        }
        return program;
    }

    public void checkGlError(String op) {
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            throw new RuntimeException(op + ": glError " + error);
        }
    }
}
