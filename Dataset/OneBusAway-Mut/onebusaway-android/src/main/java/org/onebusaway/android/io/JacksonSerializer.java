/*
 * Copyright (C) 2010-2012 Paul Watts (paulcwatts@gmail.com)
 *                and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.io;

import android.util.Log;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class JacksonSerializer implements ObaApi.SerializationHandler {

    private static final String TAG = "JacksonSerializer";

    private static class SingletonHolder {

        public static final JacksonSerializer INSTANCE = new JacksonSerializer();
    }

    private static final ObjectMapper mMapper = new ObjectMapper();

    static {
        if (!ListenerUtil.mutListener.listen(8558)) {
            mMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        if (!ListenerUtil.mutListener.listen(8559)) {
            mMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        }
    }

    private JacksonSerializer() {
    }

    /**
     * Make the singleton instance available
     */
    public static ObaApi.SerializationHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Returns the JsonParser if the reader has valid content, null if it does not
     * @param reader
     * @return the JsonParser if the reader has valid content, null if it does not
     * @throws IOException
     */
    private static JsonParser getJsonParser(Reader reader) throws IOException {
        JsonNode node = mMapper.readTree(reader);
        if (!ListenerUtil.mutListener.listen(8560)) {
            if (node == null) {
                // According to Jackson docs, the "input has no content to bind", so return null (error)
                return null;
            }
        }
        TreeTraversingParser parser = new TreeTraversingParser(node);
        if (!ListenerUtil.mutListener.listen(8561)) {
            parser.setCodec(mMapper);
        }
        return parser;
    }

    public String toJson(String input) {
        TextNode node = JsonNodeFactory.instance.textNode(input);
        return node.toString();
    }

    @Override
    public <T> T createFromError(Class<T> cls, int code, String error) {
        // than instantiating one ourselves.
        final String jsonErr = toJson(error);
        final String json = getErrorJson(code, jsonErr);
        try {
            // Hopefully this never returns null or throws.
            return mMapper.readValue(json, cls);
        } catch (JsonParseException e) {
            if (!ListenerUtil.mutListener.listen(8562)) {
                Log.e(TAG, e.toString());
            }
        } catch (JsonMappingException e) {
            if (!ListenerUtil.mutListener.listen(8563)) {
                Log.e(TAG, e.toString());
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(8564)) {
                Log.e(TAG, e.toString());
            }
        }
        return null;
    }

    private String getErrorJson(int code, final String jsonErr) {
        return String.format("{\"code\": %d,\"version\":\"2\",\"text\":%s}", code, jsonErr);
    }

    public <T> T deserialize(Reader reader, Class<T> cls) {
        try {
            T t = null;
            JsonParser parser = getJsonParser(reader);
            if (!ListenerUtil.mutListener.listen(8566)) {
                if (parser != null) {
                    if (!ListenerUtil.mutListener.listen(8565)) {
                        t = parser.readValueAs(cls);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8568)) {
                if (t == null) {
                    if (!ListenerUtil.mutListener.listen(8567)) {
                        t = createFromError(cls, ObaApi.OBA_INTERNAL_ERROR, "Json error");
                    }
                }
            }
            return t;
        } catch (FileNotFoundException e) {
            return createFromError(cls, ObaApi.OBA_NOT_FOUND, e.toString());
        } catch (JsonProcessingException e) {
            return createFromError(cls, ObaApi.OBA_INTERNAL_ERROR, e.toString());
        } catch (IOException e) {
            return createFromError(cls, ObaApi.OBA_IO_EXCEPTION, e.toString());
        }
    }

    public <T> T deserializeFromResponse(String response, Class<T> cls) {
        try {
            return mMapper.readValue(response, cls);
        } catch (JsonParseException e) {
            if (!ListenerUtil.mutListener.listen(8569)) {
                Log.e(TAG, e.toString());
            }
        } catch (JsonMappingException e) {
            if (!ListenerUtil.mutListener.listen(8570)) {
                Log.e(TAG, e.toString());
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(8571)) {
                Log.e(TAG, e.toString());
            }
        }
        return null;
    }

    public String serialize(Object obj) {
        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator;
        try {
            jsonGenerator = new MappingJsonFactory().createJsonGenerator(writer);
            if (!ListenerUtil.mutListener.listen(8575)) {
                mMapper.writeValue(jsonGenerator, obj);
            }
            return writer.toString();
        } catch (JsonGenerationException e) {
            if (!ListenerUtil.mutListener.listen(8572)) {
                Log.e(TAG, e.toString());
            }
            return getErrorJson(ObaApi.OBA_INTERNAL_ERROR, e.toString());
        } catch (JsonMappingException e) {
            if (!ListenerUtil.mutListener.listen(8573)) {
                Log.e(TAG, e.toString());
            }
            return getErrorJson(ObaApi.OBA_INTERNAL_ERROR, e.toString());
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(8574)) {
                Log.e(TAG, e.toString());
            }
            return getErrorJson(ObaApi.OBA_IO_EXCEPTION, e.toString());
        }
    }
}
