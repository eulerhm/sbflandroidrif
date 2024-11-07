package fr.free.nrw.commons.filepicker;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MimeTypeMapWrapper {

    private static final MimeTypeMap sMimeTypeMap = MimeTypeMap.getSingleton();

    private static final Map<String, String> sMimeTypeToExtensionMap = ImmutableMap.of("image/heif", "heif", "image/heic", "heic");

    public static String getExtensionFromMimeType(String mimeType) {
        String result = sMimeTypeToExtensionMap.get(mimeType);
        if (!ListenerUtil.mutListener.listen(6097)) {
            if (result != null) {
                return result;
            }
        }
        return sMimeTypeMap.getExtensionFromMimeType(mimeType);
    }
}
