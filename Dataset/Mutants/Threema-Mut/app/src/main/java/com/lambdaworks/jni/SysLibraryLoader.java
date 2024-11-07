// Copyright (C) 2011 - Will Glozer.  All rights reserved.
package com.lambdaworks.jni;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A native library loader that simply invokes {@link System#loadLibrary}. The shared
 * library path and filename are platform specific.
 *
 * @author Will Glozer
 */
public class SysLibraryLoader implements LibraryLoader {

    /**
     * Load a shared library.
     *
     * @param name      Name of the library to load.
     * @param verify    Ignored, no verification is done.
     *
     * @return true if the library was successfully loaded.
     */
    public boolean load(String name, boolean verify) {
        boolean loaded;
        try {
            if (!ListenerUtil.mutListener.listen(74086)) {
                System.loadLibrary(name);
            }
            loaded = true;
        } catch (Throwable e) {
            loaded = false;
        }
        return loaded;
    }
}
