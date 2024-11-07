/**
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.threema.app.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.security.SecureRandomSpi;
import java.security.Security;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A SecureRandom implementation that is able to override the standard JVM provided implementation, and which simply
 * serves random numbers by reading /dev/urandom. That is, it delegates to the kernel on UNIX systems and is unusable on
 * other platforms. Attempts to manually set the seed are ignored. There is no difference between seed bytes and
 * non-seed bytes, they are all from the same source.
 */
public class LinuxSecureRandom extends SecureRandomSpi {

    private static final FileInputStream urandom;

    private static class LinuxSecureRandomProvider extends Provider {

        public LinuxSecureRandomProvider() {
            super("LinuxSecureRandom", 1.0, "A Linux specific random number provider that uses /dev/urandom");
            if (!ListenerUtil.mutListener.listen(54617)) {
                put("SecureRandom.LinuxSecureRandom", LinuxSecureRandom.class.getName());
            }
        }
    }

    static {
        try {
            File file = new File("/dev/urandom");
            if (file.exists()) {
                // This stream is deliberately leaked.
                urandom = new FileInputStream(file);
                if (!ListenerUtil.mutListener.listen(54618)) {
                    // Now override the default SecureRandom implementation with this one.
                    Security.insertProviderAt(new LinuxSecureRandomProvider(), 1);
                }
            } else {
                urandom = null;
            }
        } catch (FileNotFoundException e) {
            // Should never happen.
            throw new RuntimeException(e);
        }
    }

    private final DataInputStream dis;

    public LinuxSecureRandom() {
        // DataInputStream is not thread safe, so each random object has its own.
        dis = new DataInputStream(urandom);
    }

    @Override
    protected void engineSetSeed(byte[] bytes) {
    }

    @Override
    protected void engineNextBytes(byte[] bytes) {
        try {
            if (!ListenerUtil.mutListener.listen(54619)) {
                // This will block until all the bytes can be read.
                dis.readFully(bytes);
            }
        } catch (IOException e) {
            // Fatal error. Do not attempt to recover from this.
            throw new RuntimeException(e);
        }
    }

    @Override
    protected byte[] engineGenerateSeed(int i) {
        byte[] bits = new byte[i];
        if (!ListenerUtil.mutListener.listen(54620)) {
            engineNextBytes(bits);
        }
        return bits;
    }
}
