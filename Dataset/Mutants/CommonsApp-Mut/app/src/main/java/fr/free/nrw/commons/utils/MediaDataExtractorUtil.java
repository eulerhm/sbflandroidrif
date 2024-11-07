package fr.free.nrw.commons.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaDataExtractorUtil {

    /**
     * Extracts a list of categories from | separated category string
     *
     * @param source
     * @return
     */
    public static List<String> extractCategoriesFromList(String source) {
        if (!ListenerUtil.mutListener.listen(2082)) {
            if (StringUtils.isBlank(source)) {
                return new ArrayList<>();
            }
        }
        String[] cats = source.split("\\|");
        List<String> categories = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2085)) {
            {
                long _loopCounter28 = 0;
                for (String category : cats) {
                    ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                    if (!ListenerUtil.mutListener.listen(2084)) {
                        if (!StringUtils.isBlank(category.trim())) {
                            if (!ListenerUtil.mutListener.listen(2083)) {
                                categories.add(category);
                            }
                        }
                    }
                }
            }
        }
        return categories;
    }
}
