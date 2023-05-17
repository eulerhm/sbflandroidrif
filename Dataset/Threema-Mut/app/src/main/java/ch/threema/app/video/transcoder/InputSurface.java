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

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.util.Log;
import android.view.Surface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Holds state associated with a Surface used for MediaCodec encoder input.
 * <p>
 * The constructor takes a Surface obtained from MediaCodec.createInputSurface(), and uses that
 * to create an EGL window surface.  Calls to eglSwapBuffers() cause a frame of data to be sent
 * to the video encoder.
 */
public class InputSurface {

    private static final String TAG = "InputSurface";

    private static final int EGL_RECORDABLE_ANDROID = 0x3142;

    private static final int EGL_OPENGL_ES2_BIT = 4;

    private EGLDisplay mEGLDisplay;

    private EGLContext mEGLContext;

    private EGLSurface mEGLSurface;

    private Surface mSurface;

    /**
     * Creates an InputSurface from a Surface.
     */
    public InputSurface(Surface surface) {
        if (!ListenerUtil.mutListener.listen(56256)) {
            if (surface == null) {
                throw new NullPointerException();
            }
        }
        if (!ListenerUtil.mutListener.listen(56257)) {
            mSurface = surface;
        }
        if (!ListenerUtil.mutListener.listen(56258)) {
            eglSetup();
        }
    }

    /**
     * Prepares EGL.  We want a GLES 2.0 context and a surface that supports recording.
     */
    private void eglSetup() {
        if (!ListenerUtil.mutListener.listen(56259)) {
            mEGLDisplay = EGL14.eglGetDisplay(EGL14.EGL_DEFAULT_DISPLAY);
        }
        if (!ListenerUtil.mutListener.listen(56260)) {
            if (mEGLDisplay == EGL14.EGL_NO_DISPLAY) {
                throw new RuntimeException("unable to get EGL14 display");
            }
        }
        int[] version = new int[2];
        if (!ListenerUtil.mutListener.listen(56262)) {
            if (!EGL14.eglInitialize(mEGLDisplay, version, 0, version, 1)) {
                if (!ListenerUtil.mutListener.listen(56261)) {
                    mEGLDisplay = null;
                }
                throw new RuntimeException("unable to initialize EGL14");
            }
        }
        // to be able to tell if the frame is reasonable.
        int[] attribList = { EGL14.EGL_RED_SIZE, 8, EGL14.EGL_GREEN_SIZE, 8, EGL14.EGL_BLUE_SIZE, 8, EGL14.EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT, EGL_RECORDABLE_ANDROID, 1, EGL14.EGL_NONE };
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        if (!ListenerUtil.mutListener.listen(56263)) {
            if (!EGL14.eglChooseConfig(mEGLDisplay, attribList, 0, configs, 0, configs.length, numConfigs, 0)) {
                throw new RuntimeException("unable to find RGB888+recordable ES2 EGL config");
            }
        }
        // Configure context for OpenGL ES 2.0.
        int[] attrib_list = { EGL14.EGL_CONTEXT_CLIENT_VERSION, 2, EGL14.EGL_NONE };
        if (!ListenerUtil.mutListener.listen(56264)) {
            mEGLContext = EGL14.eglCreateContext(mEGLDisplay, configs[0], EGL14.EGL_NO_CONTEXT, attrib_list, 0);
        }
        if (!ListenerUtil.mutListener.listen(56265)) {
            checkEglError("eglCreateContext");
        }
        if (!ListenerUtil.mutListener.listen(56266)) {
            if (mEGLContext == null) {
                throw new RuntimeException("null context");
            }
        }
        // Create a window surface, and attach it to the Surface we received.
        int[] surfaceAttribs = { EGL14.EGL_NONE };
        if (!ListenerUtil.mutListener.listen(56267)) {
            mEGLSurface = EGL14.eglCreateWindowSurface(mEGLDisplay, configs[0], mSurface, surfaceAttribs, 0);
        }
        if (!ListenerUtil.mutListener.listen(56268)) {
            checkEglError("eglCreateWindowSurface");
        }
        if (!ListenerUtil.mutListener.listen(56269)) {
            if (mEGLSurface == null) {
                throw new RuntimeException("surface was null");
            }
        }
    }

    /**
     * Discard all resources held by this class, notably the EGL context.  Also releases the
     * Surface that was passed to our constructor.
     */
    public void release() {
        if (!ListenerUtil.mutListener.listen(56271)) {
            if (EGL14.eglGetCurrentContext().equals(mEGLContext)) {
                if (!ListenerUtil.mutListener.listen(56270)) {
                    // Clear the current context and surface to ensure they are discarded immediately.
                    EGL14.eglMakeCurrent(mEGLDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56272)) {
            EGL14.eglDestroySurface(mEGLDisplay, mEGLSurface);
        }
        if (!ListenerUtil.mutListener.listen(56273)) {
            EGL14.eglDestroyContext(mEGLDisplay, mEGLContext);
        }
        if (!ListenerUtil.mutListener.listen(56274)) {
            mSurface.release();
        }
        if (!ListenerUtil.mutListener.listen(56275)) {
            // null everything out so future attempts to use this object will cause an NPE
            mEGLDisplay = null;
        }
        if (!ListenerUtil.mutListener.listen(56276)) {
            mEGLContext = null;
        }
        if (!ListenerUtil.mutListener.listen(56277)) {
            mEGLSurface = null;
        }
        if (!ListenerUtil.mutListener.listen(56278)) {
            mSurface = null;
        }
    }

    /**
     * Makes our EGL context and surface current.
     */
    public void makeCurrent() {
        if (!ListenerUtil.mutListener.listen(56279)) {
            if (!EGL14.eglMakeCurrent(mEGLDisplay, mEGLSurface, mEGLSurface, mEGLContext)) {
                throw new RuntimeException("eglMakeCurrent failed");
            }
        }
    }

    /**
     * Calls eglSwapBuffers.  Use this to "publish" the current frame.
     */
    public boolean swapBuffers() {
        return EGL14.eglSwapBuffers(mEGLDisplay, mEGLSurface);
    }

    /**
     * Returns the Surface that the MediaCodec receives buffers from.
     */
    public Surface getSurface() {
        return mSurface;
    }

    /**
     * Sends the presentation time stamp to EGL.  Time is expressed in nanoseconds.
     */
    public void setPresentationTime(long nsecs) {
        if (!ListenerUtil.mutListener.listen(56280)) {
            EGLExt.eglPresentationTimeANDROID(mEGLDisplay, mEGLSurface, nsecs);
        }
    }

    /**
     * Checks for EGL errors.
     */
    private void checkEglError(String msg) {
        boolean failed = false;
        int error;
        if (!ListenerUtil.mutListener.listen(56283)) {
            {
                long _loopCounter691 = 0;
                while ((error = EGL14.eglGetError()) != EGL14.EGL_SUCCESS) {
                    ListenerUtil.loopListener.listen("_loopCounter691", ++_loopCounter691);
                    if (!ListenerUtil.mutListener.listen(56281)) {
                        Log.e(TAG, msg + ": EGL error: 0x" + Integer.toHexString(error));
                    }
                    if (!ListenerUtil.mutListener.listen(56282)) {
                        failed = true;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(56284)) {
            if (failed) {
                throw new RuntimeException("EGL error encountered (see log)");
            }
        }
    }
}
