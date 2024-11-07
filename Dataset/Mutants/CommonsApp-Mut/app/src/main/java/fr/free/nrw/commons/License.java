package fr.free.nrw.commons;

import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * represents Licence object
 */
public class License {

    private String key;

    private String template;

    private String url;

    private String name;

    /**
     * Constructs a new instance of License.
     *
     * @param key       license key
     * @param template  license template
     * @param url       license URL
     * @param name      licence name
     *
     * @throws RuntimeException if License.key or Licence.template is null
     */
    public License(String key, String template, String url, String name) {
        if (!ListenerUtil.mutListener.listen(9558)) {
            if (key == null) {
                throw new RuntimeException("License.key must not be null");
            }
        }
        if (!ListenerUtil.mutListener.listen(9559)) {
            if (template == null) {
                throw new RuntimeException("License.template must not be null");
            }
        }
        if (!ListenerUtil.mutListener.listen(9560)) {
            this.key = key;
        }
        if (!ListenerUtil.mutListener.listen(9561)) {
            this.template = template;
        }
        if (!ListenerUtil.mutListener.listen(9562)) {
            this.url = url;
        }
        if (!ListenerUtil.mutListener.listen(9563)) {
            this.name = name;
        }
    }

    /**
     * Gets the license key.
     * @return license key as a String.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the license template.
     * @return license template as a String.
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Gets the license name. If name is null, return license key.
     * @return license name as string. if name null, license key as String
     */
    public String getName() {
        if (name == null) {
            // hack
            return getKey();
        } else {
            return name;
        }
    }

    /**
     * Gets the license URL
     *
     * @param language license language
     * @return URL
     */
    @Nullable
    public String getUrl(String language) {
        if (url == null) {
            return null;
        } else {
            return url.replace("$lang", language);
        }
    }
}
