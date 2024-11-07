/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
import android.opengl.GLES20;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class OutputSurface implements SurfaceTexture.OnFrameAvailableListener {

    private static final Logger logger = LoggerFactory.getLogger(OutputSurface.class);

    private static final int EGL_OPENGL_ES2_BIT = 4;

    private static final int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

    private EGL10 mEGL;

    private EGLDisplay mEGLDisplay = null;

    private EGLContext mEGLContext = null;

    private EGLSurface mEGLSurface = null;

    private SurfaceTexture mSurfaceTexture;

    private Surface mSurface;

    private final Object mFrameSyncObject = new Object();

    private boolean mFrameAvailable;

    private TextureRenderer mTextureRender;

    private int mWidth;

    private int mHeight;

    private int rotateRender = 0;

    private ByteBuffer mPixelBuf;

    private HandlerThread mHandlerThread;

    private Handler mHandler;

    public OutputSurface(int width, int height, int rotate) {
        if (!ListenerUtil.mutListener.listen(56342)) {
            if ((ListenerUtil.mutListener.listen(56341) ? ((ListenerUtil.mutListener.listen(56335) ? (width >= 0) : (ListenerUtil.mutListener.listen(56334) ? (width > 0) : (ListenerUtil.mutListener.listen(56333) ? (width < 0) : (ListenerUtil.mutListener.listen(56332) ? (width != 0) : (ListenerUtil.mutListener.listen(56331) ? (width == 0) : (width <= 0)))))) && (ListenerUtil.mutListener.listen(56340) ? (height >= 0) : (ListenerUtil.mutListener.listen(56339) ? (height > 0) : (ListenerUtil.mutListener.listen(56338) ? (height < 0) : (ListenerUtil.mutListener.listen(56337) ? (height != 0) : (ListenerUtil.mutListener.listen(56336) ? (height == 0) : (height <= 0))))))) : ((ListenerUtil.mutListener.listen(56335) ? (width >= 0) : (ListenerUtil.mutListener.listen(56334) ? (width > 0) : (ListenerUtil.mutListener.listen(56333) ? (width < 0) : (ListenerUtil.mutListener.listen(56332) ? (width != 0) : (ListenerUtil.mutListener.listen(56331) ? (width == 0) : (width <= 0)))))) || (ListenerUtil.mutListener.listen(56340) ? (height >= 0) : (ListenerUtil.mutListener.listen(56339) ? (height > 0) : (ListenerUtil.mutListener.listen(56338) ? (height < 0) : (ListenerUtil.mutListener.listen(56337) ? (height != 0) : (ListenerUtil.mutListener.listen(56336) ? (height == 0) : (height <= 0))))))))) {
                throw new IllegalArgumentException();
            }
        }
        if (!ListenerUtil.mutListener.listen(56343)) {
            mWidth = width;
        }
        if (!ListenerUtil.mutListener.listen(56344)) {
            mHeight = height;
        }
        if (!ListenerUtil.mutListener.listen(56345)) {
            rotateRender = rotate;
        }
        if (!ListenerUtil.mutListener.listen(56354)) {
            mPixelBuf = ByteBuffer.allocateDirect((ListenerUtil.mutListener.listen(56353) ? ((ListenerUtil.mutListener.listen(56349) ? (mWidth % mHeight) : (ListenerUtil.mutListener.listen(56348) ? (mWidth / mHeight) : (ListenerUtil.mutListener.listen(56347) ? (mWidth - mHeight) : (ListenerUtil.mutListener.listen(56346) ? (mWidth + mHeight) : (mWidth * mHeight))))) % 4) : (ListenerUtil.mutListener.listen(56352) ? ((ListenerUtil.mutListener.listen(56349) ? (mWidth % mHeight) : (ListenerUtil.mutListener.listen(56348) ? (mWidth / mHeight) : (ListenerUtil.mutListener.listen(56347) ? (mWidth - mHeight) : (ListenerUtil.mutListener.listen(56346) ? (mWidth + mHeight) : (mWidth * mHeight))))) / 4) : (ListenerUtil.mutListener.listen(56351) ? ((ListenerUtil.mutListener.listen(56349) ? (mWidth % mHeight) : (ListenerUtil.mutListener.listen(56348) ? (mWidth / mHeight) : (ListenerUtil.mutListener.listen(56347) ? (mWidth - mHeight) : (ListenerUtil.mutListener.listen(56346) ? (mWidth + mHeight) : (mWidth * mHeight))))) - 4) : (ListenerUtil.mutListener.listen(56350) ? ((ListenerUtil.mutListener.listen(56349) ? (mWidth % mHeight) : (ListenerUtil.mutListener.listen(56348) ? (mWidth / mHeight) : (ListenerUtil.mutListener.listen(56347) ? (mWidth - mHeight) : (ListenerUtil.mutListener.listen(56346) ? (mWidth + mHeight) : (mWidth * mHeight))))) + 4) : ((ListenerUtil.mutListener.listen(56349) ? (mWidth % mHeight) : (ListenerUtil.mutListener.listen(56348) ? (mWidth / mHeight) : (ListenerUtil.mutListener.listen(56347) ? (mWidth - mHeight) : (ListenerUtil.mutListener.listen(56346) ? (mWidth + mHeight) : (mWidth * mHeight))))) * 4))))));
        }
        if (!ListenerUtil.mutListener.listen(56355)) {
            mPixelBuf.order(ByteOrder.LITTLE_ENDIAN);
        }
        if (!ListenerUtil.mutListener.listen(56356)) {
            eglSetup(width, height);
        }
        if (!ListenerUtil.mutListener.listen(56357)) {
            makeCurrent();
        }
        if (!ListenerUtil.mutListener.listen(56358)) {
            setup();
        }
    }

    public OutputSurface() {
        if (!ListenerUtil.mutListener.listen(56359)) {
            setup();
        }
    }

    private void setup() {
        if (!ListenerUtil.mutListener.listen(56360)) {
            mTextureRender = new TextureRenderer(rotateRender);
        }
        if (!ListenerUtil.mutListener.listen(56361)) {
            mTextureRender.surfaceCreated();
        }
        if (!ListenerUtil.mutListener.listen(56362)) {
            // https://stackoverflow.com/a/55968224/284318
            mHandlerThread = new HandlerThread("OutputSurfaceCallback");
        }
        if (!ListenerUtil.mutListener.listen(56363)) {
            mHandlerThread.start();
        }
        if (!ListenerUtil.mutListener.listen(56364)) {
            mHandler = new Handler(mHandlerThread.getLooper());
        }
        if (!ListenerUtil.mutListener.listen(56365)) {
            // causes the native finalizer to run.
            logger.debug("textureID=" + mTextureRender.getTextureId());
        }
        if (!ListenerUtil.mutListener.listen(56366)) {
            mSurfaceTexture = new SurfaceTexture(mTextureRender.getTextureId());
        }
        if (!ListenerUtil.mutListener.listen(56374)) {
            if ((ListenerUtil.mutListener.listen(56371) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56370) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56369) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56368) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(56367) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(56373)) {
                    mSurfaceTexture.setOnFrameAvailableListener(this, mHandler);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(56372)) {
                    mSurfaceTexture.setOnFrameAvailableListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56375)) {
            mSurface = new Surface(mSurfaceTexture);
        }
    }

    private void eglSetup(int width, int height) {
        if (!ListenerUtil.mutListener.listen(56376)) {
            mEGL = (EGL10) EGLContext.getEGL();
        }
        if (!ListenerUtil.mutListener.listen(56377)) {
            mEGLDisplay = mEGL.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        }
        if (!ListenerUtil.mutListener.listen(56378)) {
            if (mEGLDisplay == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("unable to get EGL10 display");
            }
        }
        if (!ListenerUtil.mutListener.listen(56380)) {
            if (!mEGL.eglInitialize(mEGLDisplay, null)) {
                if (!ListenerUtil.mutListener.listen(56379)) {
                    mEGLDisplay = null;
                }
                throw new RuntimeException("unable to initialize EGL10");
            }
        }
        int[] attribList = { EGL10.EGL_RED_SIZE, 8, EGL10.EGL_GREEN_SIZE, 8, EGL10.EGL_BLUE_SIZE, 8, EGL10.EGL_ALPHA_SIZE, 8, EGL10.EGL_SURFACE_TYPE, EGL10.EGL_PBUFFER_BIT, EGL10.EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT, EGL10.EGL_NONE };
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        if (!ListenerUtil.mutListener.listen(56381)) {
            if (!mEGL.eglChooseConfig(mEGLDisplay, attribList, configs, configs.length, numConfigs)) {
                throw new RuntimeException("unable to find RGB888+pbuffer EGL config");
            }
        }
        int[] attrib_list = { EGL_CONTEXT_CLIENT_VERSION, 2, EGL10.EGL_NONE };
        if (!ListenerUtil.mutListener.listen(56382)) {
            mEGLContext = mEGL.eglCreateContext(mEGLDisplay, configs[0], EGL10.EGL_NO_CONTEXT, attrib_list);
        }
        if (!ListenerUtil.mutListener.listen(56383)) {
            checkEglError("eglCreateContext");
        }
        if (!ListenerUtil.mutListener.listen(56384)) {
            if (mEGLContext == null) {
                throw new RuntimeException("null context");
            }
        }
        int[] surfaceAttribs = { EGL10.EGL_WIDTH, width, EGL10.EGL_HEIGHT, height, EGL10.EGL_NONE };
        if (!ListenerUtil.mutListener.listen(56385)) {
            mEGLSurface = mEGL.eglCreatePbufferSurface(mEGLDisplay, configs[0], surfaceAttribs);
        }
        if (!ListenerUtil.mutListener.listen(56386)) {
            checkEglError("eglCreatePbufferSurface");
        }
        if (!ListenerUtil.mutListener.listen(56387)) {
            if (mEGLSurface == null) {
                throw new RuntimeException("surface was null");
            }
        }
    }

    public void release() {
        if (!ListenerUtil.mutListener.listen(56392)) {
            if (mEGL != null) {
                if (!ListenerUtil.mutListener.listen(56389)) {
                    if (mEGL.eglGetCurrentContext().equals(mEGLContext)) {
                        if (!ListenerUtil.mutListener.listen(56388)) {
                            mEGL.eglMakeCurrent(mEGLDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(56390)) {
                    mEGL.eglDestroySurface(mEGLDisplay, mEGLSurface);
                }
                if (!ListenerUtil.mutListener.listen(56391)) {
                    mEGL.eglDestroyContext(mEGLDisplay, mEGLContext);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56393)) {
            mSurface.release();
        }
        if (!ListenerUtil.mutListener.listen(56394)) {
            mEGLDisplay = null;
        }
        if (!ListenerUtil.mutListener.listen(56395)) {
            mEGLContext = null;
        }
        if (!ListenerUtil.mutListener.listen(56396)) {
            mEGLSurface = null;
        }
        if (!ListenerUtil.mutListener.listen(56397)) {
            mEGL = null;
        }
        if (!ListenerUtil.mutListener.listen(56398)) {
            mTextureRender = null;
        }
        if (!ListenerUtil.mutListener.listen(56399)) {
            mSurface = null;
        }
        if (!ListenerUtil.mutListener.listen(56400)) {
            mSurfaceTexture = null;
        }
    }

    public void makeCurrent() {
        if (!ListenerUtil.mutListener.listen(56401)) {
            if (mEGL == null) {
                throw new RuntimeException("not configured for makeCurrent");
            }
        }
        if (!ListenerUtil.mutListener.listen(56402)) {
            checkEglError("before makeCurrent");
        }
        if (!ListenerUtil.mutListener.listen(56403)) {
            if (!mEGL.eglMakeCurrent(mEGLDisplay, mEGLSurface, mEGLSurface, mEGLContext)) {
                throw new RuntimeException("eglMakeCurrent failed");
            }
        }
    }

    public Surface getSurface() {
        return mSurface;
    }

    public void awaitNewImage() {
        final int TIMEOUT_MS = 2500;
        synchronized (mFrameSyncObject) {
            if (!ListenerUtil.mutListener.listen(56406)) {
                {
                    long _loopCounter693 = 0;
                    while (!mFrameAvailable) {
                        ListenerUtil.loopListener.listen("_loopCounter693", ++_loopCounter693);
                        try {
                            if (!ListenerUtil.mutListener.listen(56404)) {
                                mFrameSyncObject.wait(TIMEOUT_MS);
                            }
                            if (!ListenerUtil.mutListener.listen(56405)) {
                                if (!mFrameAvailable) {
                                    throw new RuntimeException("Surface frame wait timed out");
                                }
                            }
                        } catch (InterruptedException ie) {
                            throw new RuntimeException(ie);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(56407)) {
                mFrameAvailable = false;
            }
        }
        if (!ListenerUtil.mutListener.listen(56408)) {
            mTextureRender.checkGlError("before updateTexImage");
        }
        if (!ListenerUtil.mutListener.listen(56409)) {
            mSurfaceTexture.updateTexImage();
        }
    }

    public void drawImage(boolean invert) {
        if (!ListenerUtil.mutListener.listen(56410)) {
            mTextureRender.drawFrame(mSurfaceTexture, invert);
        }
    }

    @Override
    public void onFrameAvailable(SurfaceTexture st) {
        synchronized (mFrameSyncObject) {
            if (!ListenerUtil.mutListener.listen(56411)) {
                if (mFrameAvailable) {
                    throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
                }
            }
            if (!ListenerUtil.mutListener.listen(56412)) {
                mFrameAvailable = true;
            }
            if (!ListenerUtil.mutListener.listen(56413)) {
                mFrameSyncObject.notifyAll();
            }
        }
    }

    public ByteBuffer getFrame() {
        if (!ListenerUtil.mutListener.listen(56414)) {
            mPixelBuf.rewind();
        }
        if (!ListenerUtil.mutListener.listen(56415)) {
            GLES20.glReadPixels(0, 0, mWidth, mHeight, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, mPixelBuf);
        }
        return mPixelBuf;
    }

    private void checkEglError(String msg) {
        if (!ListenerUtil.mutListener.listen(56416)) {
            if (mEGL.eglGetError() != EGL10.EGL_SUCCESS) {
                throw new RuntimeException("EGL error encountered (see log)");
            }
        }
    }
}
