/*
 * Copyright 2013 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.directions.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.opentripplanner.api.ws.Response;
import org.opentripplanner.routing.patch.AlertHeaderText;
import android.content.Context;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class holds a static instance of a Jackson ObjectMapper and ObjectReader
 * that are configured for parsing server JSON responses.
 *
 * The ObjectMapper, ObjectReader, and XmlMapper are thread-safe after it is
 * configured: http://wiki.fasterxml.com/JacksonFAQThreadSafety
 *
 * ...so we can configure it once here and then use it in multiple fragments.
 *
 * @author Sean J. Barbeau
 */
public class JacksonConfig {

    // For JSON
    private static ObjectMapper mapper = null;

    private static ObjectReader reader = null;

    // desired
    private static Context context = null;

    // Used to time cache read and write
    private static long cacheReadStartTime = 0;

    private static long cacheReadEndTime = 0;

    private static long cacheWriteStartTime = 0;

    private static long cacheWriteEndTime = 0;

    private static boolean usingCache = false;

    // Constants for defining which object type to read/write from/to cache
    private static final String OBJECT_READER = "ObjectReader";

    private static final String OBJECT_MAPPER = "ObjectMapper";

    private static final String CACHE_FILE_EXTENSION = ".cache";

    private static final String TAG = "JacksonConfig";

    // Used to format decimals to 3 places
    static DecimalFormat df = new DecimalFormat("#,###.###");

    // Private empty constructor since this object shouldn't be instantiated
    private JacksonConfig() {
    }

    /**
     * Returns true if the application is using a cache to read/write serialized
     * Jackson ObjectMapper/ObjectReader/XmlMapper to reduce cold-start latency,
     * false if it is not
     *
     * @return true if the application is using a cache to read/write serialized
     * Jackson ObjectMapper/ObjectReader/XmlMapper to reduce cold-start
     * latency, false if it is not
     */
    public static boolean isUsingCache() {
        if (!ListenerUtil.mutListener.listen(5310)) {
            // Check to see if the context is null. If it is, we can't cache data.
            if ((ListenerUtil.mutListener.listen(5307) ? (usingCache || context != null) : (usingCache && context != null))) {
                return true;
            } else {
                if (!ListenerUtil.mutListener.listen(5309)) {
                    if ((ListenerUtil.mutListener.listen(5308) ? (!usingCache && context == null) : (!usingCache || context == null))) {
                        return false;
                    }
                }
            }
        }
        // Should never reach here
        return usingCache;
    }

    /**
     * True if the application should use a cache to read/write serialized
     * Jackson ObjectMapper/ObjectReader/XmlMapper to reduce cold-start latency,
     * false if it should not
     *
     * @param usingCache True if the application should use a cache to read/write
     *                   serialized Jackson ObjectMapper/ObjectReader/XmlMapper to
     *                   reduce cold-start latency, false if it should not
     * @param context    Context that should be used to access the cache location.
     *                   getApplicationContext() is suggested, since the Jackson
     *                   Objects are thread-safe and static
     */
    public static void setUsingCache(boolean usingCache, Context context) {
        if (!ListenerUtil.mutListener.listen(5311)) {
            JacksonConfig.usingCache = usingCache;
        }
        if (!ListenerUtil.mutListener.listen(5312)) {
            JacksonConfig.context = context;
        }
    }

    /**
     * Returns a benchmark of the amount of time the last cache read took for
     * the ObjectMapper or ObjectReader or XmlReader (in nanoseconds)
     *
     * @return a benchmark of the amount of time the last cache read took for
     * the ObjectMapper or ObjectReader or XmlReader (in nanoseconds)
     */
    public static long getLastCacheReadTime() {
        return (ListenerUtil.mutListener.listen(5316) ? (cacheReadEndTime % cacheReadStartTime) : (ListenerUtil.mutListener.listen(5315) ? (cacheReadEndTime / cacheReadStartTime) : (ListenerUtil.mutListener.listen(5314) ? (cacheReadEndTime * cacheReadStartTime) : (ListenerUtil.mutListener.listen(5313) ? (cacheReadEndTime + cacheReadStartTime) : (cacheReadEndTime - cacheReadStartTime)))));
    }

    /**
     * Returns a benchmark of the amount of time the last cache write took for
     * the ObjectMapper or ObjectReader or XmlReader (in nanoseconds)
     *
     * @return a benchmark of the amount of time the last cache write took for
     * the ObjectMapper or ObjectReader or XmlReader (in nanoseconds)
     */
    public static long getLastCacheWriteTime() {
        return (ListenerUtil.mutListener.listen(5320) ? (cacheWriteEndTime % cacheWriteStartTime) : (ListenerUtil.mutListener.listen(5319) ? (cacheWriteEndTime / cacheWriteStartTime) : (ListenerUtil.mutListener.listen(5318) ? (cacheWriteEndTime * cacheWriteStartTime) : (ListenerUtil.mutListener.listen(5317) ? (cacheWriteEndTime + cacheWriteStartTime) : (cacheWriteEndTime - cacheWriteStartTime)))));
    }

    /**
     * Constructs a thread-safe instance of a Jackson ObjectMapper configured to
     * parse JSON responses from a OTP REST API.
     *
     * According to Jackson Best Practices
     * (http://wiki.fasterxml.com/JacksonBestPracticesPerformance), for
     * efficiency reasons you should use the ObjectReader (via
     * getObjectReaderInstance()) instead of the ObjectMapper.
     *
     * @return thread-safe ObjectMapper configured for OTP JSON responses
     * @deprecated
     */
    public static synchronized ObjectMapper getObjectMapperInstance() {
        return initObjectMapper();
    }

    /**
     * Constructs a thread-safe instance of a Jackson ObjectReader configured to
     * parse JSON responses from a Mobile OTP API
     *
     * According to Jackson Best Practices
     * (http://wiki.fasterxml.com/JacksonBestPracticesPerformance), this should
     * be more efficient than the ObjectMapper.
     *
     * @return thread-safe ObjectMapper configured for OTP JSON responses
     */
    public static synchronized ObjectReader getObjectReaderInstance() {
        if (!ListenerUtil.mutListener.listen(5325)) {
            if (reader == null) {
                if (!ListenerUtil.mutListener.listen(5323)) {
                    /**
                     * We don't have a reference to an ObjectReader, so we need to read
                     * from cache or instantiate a new one
                     */
                    if (usingCache) {
                        if (!ListenerUtil.mutListener.listen(5321)) {
                            reader = (ObjectReader) readFromCache(OBJECT_READER);
                        }
                        if (!ListenerUtil.mutListener.listen(5322)) {
                            if (reader != null) {
                                // Successful read from the cache
                                return reader;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5324)) {
                    /**
                     * If we reach this point then we're either not reading from the
                     * cache, there was nothing in the cache to retrieve, or there was
                     * an error reading from the cache.
                     *
                     * Instantiate the object like normal.
                     */
                    reader = initObjectMapper().reader(Response.class);
                }
            }
        }
        return reader;
    }

    /**
     * Internal method used to init main ObjectMapper for JSON parsing
     *
     * @return initialized ObjectMapper ready for JSON parsing
     */
    private static ObjectMapper initObjectMapper() {
        if (!ListenerUtil.mutListener.listen(5338)) {
            if (mapper == null) {
                if (!ListenerUtil.mutListener.listen(5328)) {
                    /**
                     * We don't have a reference to an ObjectMapper, so we need to read
                     * from cache or instantiate a new one
                     */
                    if (usingCache) {
                        if (!ListenerUtil.mutListener.listen(5326)) {
                            mapper = (ObjectMapper) readFromCache(OBJECT_MAPPER);
                        }
                        if (!ListenerUtil.mutListener.listen(5327)) {
                            if (mapper != null) {
                                // Successful read from the cache
                                return mapper;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5329)) {
                    // Jackson configuration
                    mapper = new ObjectMapper();
                }
                if (!ListenerUtil.mutListener.listen(5330)) {
                    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                }
                if (!ListenerUtil.mutListener.listen(5331)) {
                    mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
                }
                if (!ListenerUtil.mutListener.listen(5332)) {
                    mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
                }
                if (!ListenerUtil.mutListener.listen(5333)) {
                    mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
                }
                if (!ListenerUtil.mutListener.listen(5334)) {
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                }
                // from latest OTP. Simply ignore for now.
                SimpleModule module = new SimpleModule();
                if (!ListenerUtil.mutListener.listen(5336)) {
                    module.addDeserializer(AlertHeaderText.class, new JsonDeserializer<AlertHeaderText>() {

                        @Override
                        public AlertHeaderText deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                            if (!ListenerUtil.mutListener.listen(5335)) {
                                Log.d(TAG, "Ignoring AlertHeaderText object.");
                            }
                            return null;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(5337)) {
                    mapper.registerModule(module);
                }
            }
        }
        return mapper;
    }

    /**
     * Forces the write of a ObjectMapper or ObjectReader to the app
     * cache. The cache is used to reduce the cold-start delay for Jackson
     * parsing on future runs, after this VM instance is destroyed.
     *
     * Applications may call this after a JSON or XML call to the server to
     * attempt to hide the cache write latency from the user, instead of having
     * the cache write occur as part of the first request to use the
     * ObjectMapper or ObjectReader.
     *
     * This method is non-blocking.
     *
     * @param object object to be written to the cache
     */
    public static void forceCacheWrite(final Serializable object) {
        if (!ListenerUtil.mutListener.listen(5342)) {
            if (isUsingCache()) {
                if (!ListenerUtil.mutListener.listen(5341)) {
                    new Thread() {

                        public void run() {
                            if (!ListenerUtil.mutListener.listen(5340)) {
                                writeToCache(object);
                            }
                        }
                    }.start();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5339)) {
                    Log.w(TAG, "App tried to force a cache write but caching is not activated.  If you want to use the cache, call JacksonConfig.setUsingCache(true, context) with a reference to your context.");
                }
            }
        }
    }

    /**
     * Forces the read of a ObjectMapper or ObjectReader from the
     * app cache to be stored as a static instance in this object. The cache is
     * used to reduce the cold-start delay for Jackson parsing on future runs,
     * after this VM instance is destroyed.
     *
     * Applications should call this on startup to attempt to hide the cache
     * read latency from the user, instead of having the cache read occur on the
     * first request to use the ObjectMapper or ObjectReader.
     *
     * This method is non-blocking.
     */
    public static void forceCacheRead() {
        if (!ListenerUtil.mutListener.listen(5347)) {
            if (isUsingCache()) {
                if (!ListenerUtil.mutListener.listen(5346)) {
                    new Thread() {

                        public void run() {
                            if (!ListenerUtil.mutListener.listen(5344)) {
                                readFromCache(OBJECT_MAPPER);
                            }
                            if (!ListenerUtil.mutListener.listen(5345)) {
                                readFromCache(OBJECT_READER);
                            }
                        }
                    }.start();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5343)) {
                    Log.w(TAG, "App tried to force a cache write but caching is not activated.  If you want to use the cache, call JacksonConfig.setUsingCache(true, context) with a reference to your context.");
                }
            }
        }
    }

    /**
     * Write the given object to Android internal storage for this app
     *
     * @param object serializable object to be written to cache (ObjectReader,
     *               ObjectMapper, or XmlReader)
     * @return true if object was successfully written to cache, false if it was
     * not
     */
    private static synchronized boolean writeToCache(Serializable object) {
        FileOutputStream fileStream = null;
        ObjectOutputStream objectStream = null;
        String fileName = "";
        boolean success = false;
        if (!ListenerUtil.mutListener.listen(5370)) {
            if (context != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(5358)) {
                        if (object instanceof ObjectMapper) {
                            if (!ListenerUtil.mutListener.listen(5357)) {
                                fileName = OBJECT_MAPPER + CACHE_FILE_EXTENSION;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5360)) {
                        if (object instanceof ObjectReader) {
                            if (!ListenerUtil.mutListener.listen(5359)) {
                                fileName = OBJECT_READER + CACHE_FILE_EXTENSION;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5361)) {
                        cacheWriteStartTime = System.nanoTime();
                    }
                    if (!ListenerUtil.mutListener.listen(5362)) {
                        fileStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                    }
                    if (!ListenerUtil.mutListener.listen(5363)) {
                        objectStream = new ObjectOutputStream(fileStream);
                    }
                    if (!ListenerUtil.mutListener.listen(5364)) {
                        objectStream.writeObject(object);
                    }
                    if (!ListenerUtil.mutListener.listen(5365)) {
                        objectStream.flush();
                    }
                    if (!ListenerUtil.mutListener.listen(5366)) {
                        fileStream.getFD().sync();
                    }
                    if (!ListenerUtil.mutListener.listen(5367)) {
                        cacheWriteEndTime = System.nanoTime();
                    }
                    if (!ListenerUtil.mutListener.listen(5368)) {
                        success = true;
                    }
                    // Get size of serialized object
                    long fileSize = context.getFileStreamPath(fileName).length();
                    if (!ListenerUtil.mutListener.listen(5369)) {
                        Log.d("TAG", "Wrote " + fileName + " to cache (" + fileSize + " bytes) in " + df.format(getLastCacheWriteTime()) + " ms.");
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(5349)) {
                        // Reset timestamps to show there was an error
                        cacheWriteStartTime = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(5350)) {
                        cacheWriteEndTime = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(5351)) {
                        Log.e(TAG, "Couldn't write Jackson object '" + fileName + "' to cache: " + e);
                    }
                } finally {
                    try {
                        if (!ListenerUtil.mutListener.listen(5354)) {
                            if (objectStream != null) {
                                if (!ListenerUtil.mutListener.listen(5353)) {
                                    objectStream.close();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(5356)) {
                            if (fileStream != null) {
                                if (!ListenerUtil.mutListener.listen(5355)) {
                                    fileStream.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(5352)) {
                            Log.e(TAG, "Error closing file connections: " + e);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5348)) {
                    Log.w(TAG, "Can't write to cache - no context provided.  If you want to use the cache, call JacksonConfig.setUsingCache(true, context) with a reference to your context.");
                }
            }
        }
        return success;
    }

    /**
     * Read the given object from Android internal storage for this app
     *
     * @param objectType object type, defined by class constant Strings, to retrieve
     *                   from cache (ObjectReader, ObjectMapper, or XmlReader)
     * @return deserialized Object, or null if object couldn't be deserialized
     */
    private static synchronized Serializable readFromCache(String objectType) {
        FileInputStream fileStream = null;
        ObjectInputStream objectStream = null;
        // Holds object to be read from cache
        Serializable object = null;
        // have the requested object in memory
        if ((ListenerUtil.mutListener.listen(5371) ? (objectType.equalsIgnoreCase(OBJECT_MAPPER) || mapper != null) : (objectType.equalsIgnoreCase(OBJECT_MAPPER) && mapper != null))) {
            return mapper;
        }
        if ((ListenerUtil.mutListener.listen(5372) ? (objectType.equalsIgnoreCase(OBJECT_READER) || reader != null) : (objectType.equalsIgnoreCase(OBJECT_READER) && reader != null))) {
            return reader;
        }
        if (context != null) {
            try {
                String fileName = objectType + CACHE_FILE_EXTENSION;
                if (!ListenerUtil.mutListener.listen(5383)) {
                    cacheReadStartTime = System.nanoTime();
                }
                if (!ListenerUtil.mutListener.listen(5384)) {
                    fileStream = context.openFileInput(fileName);
                }
                if (!ListenerUtil.mutListener.listen(5385)) {
                    objectStream = new ObjectInputStream(fileStream);
                }
                if (!ListenerUtil.mutListener.listen(5386)) {
                    object = (Serializable) objectStream.readObject();
                }
                if (!ListenerUtil.mutListener.listen(5387)) {
                    cacheReadEndTime = System.nanoTime();
                }
                // Get size of serialized object
                long fileSize = context.getFileStreamPath(fileName).length();
                if (!ListenerUtil.mutListener.listen(5388)) {
                    Log.d("TAG", "Read " + fileName + " from cache (" + fileSize + " bytes) in " + df.format(getLastCacheReadTime()) + " ms.");
                }
            } catch (FileNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(5374)) {
                    Log.w(TAG, "Cache miss - Jackson object '" + objectType + "' does not exist in app cache: " + e);
                }
                return null;
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(5375)) {
                    // Reset timestamps to show there was an error
                    cacheReadStartTime = 0;
                }
                if (!ListenerUtil.mutListener.listen(5376)) {
                    cacheReadEndTime = 0;
                }
                if (!ListenerUtil.mutListener.listen(5377)) {
                    Log.e(TAG, "Couldn't read Jackson object '" + objectType + "' from cache: " + e);
                }
            } finally {
                try {
                    if (!ListenerUtil.mutListener.listen(5380)) {
                        if (objectStream != null) {
                            if (!ListenerUtil.mutListener.listen(5379)) {
                                objectStream.close();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5382)) {
                        if (fileStream != null) {
                            if (!ListenerUtil.mutListener.listen(5381)) {
                                fileStream.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(5378)) {
                        Log.e(TAG, "Error closing cache file connections: " + e);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5390)) {
                if (object instanceof ObjectMapper) {
                    if (!ListenerUtil.mutListener.listen(5389)) {
                        mapper = (ObjectMapper) object;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5392)) {
                if (object instanceof ObjectReader) {
                    if (!ListenerUtil.mutListener.listen(5391)) {
                        reader = (ObjectReader) object;
                    }
                }
            }
            return object;
        } else {
            if (!ListenerUtil.mutListener.listen(5373)) {
                Log.w(TAG, "Couldn't read from cache - no context provided.  If you want to use the cache, call JacksonConfig.setUsingCache(true, context) with a reference to your context.");
            }
            return null;
        }
    }
}
