package com.ichi2.anki;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.widget.Toast;
import com.ichi2.libanki.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AnkiFont {

    private final String mName;

    private final String mFamily;

    private final List<String> mAttributes;

    private final String mPath;

    private Boolean mIsDefault;

    private Boolean mIsOverride;

    private static final String fAssetPathPrefix = "/android_asset/fonts/";

    private static final Set<String> corruptFonts = new HashSet<>();

    private AnkiFont(String name, String family, List<String> attributes, String path) {
        mName = name;
        mFamily = family;
        mAttributes = attributes;
        mPath = path;
        if (!ListenerUtil.mutListener.listen(5875)) {
            mIsDefault = false;
        }
        if (!ListenerUtil.mutListener.listen(5876)) {
            mIsOverride = false;
        }
    }

    /**
     * Factory for AnkiFont creation. Creates a typeface wrapper from a font file representing.
     *
     * @param ctx Activity context, needed to access assets
     * @param path Path to typeface file, needed when this is a custom font.
     * @param fromAssets True if the font is to be found in assets of application
     * @return A new AnkiFont object or null if the file can't be interpreted as typeface.
     */
    public static AnkiFont createAnkiFont(Context ctx, String path, boolean fromAssets) {
        File fontfile = new File(path);
        String name = Utils.splitFilename(fontfile.getName())[0];
        String family = name;
        List<String> attributes = new ArrayList<>(2);
        if (!ListenerUtil.mutListener.listen(5878)) {
            if (fromAssets) {
                if (!ListenerUtil.mutListener.listen(5877)) {
                    path = fAssetPathPrefix + fontfile.getName();
                }
            }
        }
        Typeface tf = getTypeface(ctx, path);
        if (!ListenerUtil.mutListener.listen(5879)) {
            if (tf == null) {
                // unable to create typeface
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(5886)) {
            if ((ListenerUtil.mutListener.listen(5880) ? (tf.isBold() && name.toLowerCase(Locale.ROOT).contains("bold")) : (tf.isBold() || name.toLowerCase(Locale.ROOT).contains("bold")))) {
                if (!ListenerUtil.mutListener.listen(5884)) {
                    attributes.add("font-weight: bolder;");
                }
                if (!ListenerUtil.mutListener.listen(5885)) {
                    family = family.replaceFirst("(?i)-?Bold", "");
                }
            } else if (name.toLowerCase(Locale.ROOT).contains("light")) {
                if (!ListenerUtil.mutListener.listen(5882)) {
                    attributes.add("font-weight: lighter;");
                }
                if (!ListenerUtil.mutListener.listen(5883)) {
                    family = family.replaceFirst("(?i)-?Light", "");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5881)) {
                    attributes.add("font-weight: normal;");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5893)) {
            if ((ListenerUtil.mutListener.listen(5887) ? (tf.isItalic() && name.toLowerCase(Locale.ROOT).contains("italic")) : (tf.isItalic() || name.toLowerCase(Locale.ROOT).contains("italic")))) {
                if (!ListenerUtil.mutListener.listen(5891)) {
                    attributes.add("font-style: italic;");
                }
                if (!ListenerUtil.mutListener.listen(5892)) {
                    family = family.replaceFirst("(?i)-?Italic", "");
                }
            } else if (name.toLowerCase(Locale.ROOT).contains("oblique")) {
                if (!ListenerUtil.mutListener.listen(5889)) {
                    attributes.add("font-style: oblique;");
                }
                if (!ListenerUtil.mutListener.listen(5890)) {
                    family = family.replaceFirst("(?i)-?Oblique", "");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5888)) {
                    attributes.add("font-style: normal;");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5902)) {
            if ((ListenerUtil.mutListener.listen(5894) ? (name.toLowerCase(Locale.ROOT).contains("condensed") && name.toLowerCase(Locale.ROOT).contains("narrow")) : (name.toLowerCase(Locale.ROOT).contains("condensed") || name.toLowerCase(Locale.ROOT).contains("narrow")))) {
                if (!ListenerUtil.mutListener.listen(5899)) {
                    attributes.add("font-stretch: condensed;");
                }
                if (!ListenerUtil.mutListener.listen(5900)) {
                    family = family.replaceFirst("(?i)-?Condensed", "");
                }
                if (!ListenerUtil.mutListener.listen(5901)) {
                    family = family.replaceFirst("(?i)-?Narrow(er)?", "");
                }
            } else if ((ListenerUtil.mutListener.listen(5895) ? (name.toLowerCase(Locale.ROOT).contains("expanded") && name.toLowerCase(Locale.ROOT).contains("wide")) : (name.toLowerCase(Locale.ROOT).contains("expanded") || name.toLowerCase(Locale.ROOT).contains("wide")))) {
                if (!ListenerUtil.mutListener.listen(5896)) {
                    attributes.add("font-stretch: expanded;");
                }
                if (!ListenerUtil.mutListener.listen(5897)) {
                    family = family.replaceFirst("(?i)-?Expanded", "");
                }
                if (!ListenerUtil.mutListener.listen(5898)) {
                    family = family.replaceFirst("(?i)-?Wide(r)?", "");
                }
            }
        }
        AnkiFont createdFont = new AnkiFont(name, family, attributes, path);
        // determine if override font or default font
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(ctx);
        String defaultFont = preferences.getString("defaultFont", "");
        boolean overrideFont = "1".equals(preferences.getString("overrideFontBehavior", "0"));
        if (!ListenerUtil.mutListener.listen(5906)) {
            if (defaultFont.equalsIgnoreCase(name)) {
                if (!ListenerUtil.mutListener.listen(5905)) {
                    if (overrideFont) {
                        if (!ListenerUtil.mutListener.listen(5904)) {
                            createdFont.setAsOverride();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5903)) {
                            createdFont.setAsDefault();
                        }
                    }
                }
            }
        }
        return createdFont;
    }

    public String getDeclaration() {
        return "@font-face {" + getCSS(false) + " src: url(\"file://" + mPath + "\");}";
    }

    public String getCSS(boolean override) {
        StringBuilder sb = new StringBuilder("font-family: \"").append(mFamily);
        if (!ListenerUtil.mutListener.listen(5909)) {
            if (override) {
                if (!ListenerUtil.mutListener.listen(5908)) {
                    sb.append("\" !important;");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5907)) {
                    sb.append("\";");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5924)) {
            {
                long _loopCounter106 = 0;
                for (String attr : mAttributes) {
                    ListenerUtil.loopListener.listen("_loopCounter106", ++_loopCounter106);
                    if (!ListenerUtil.mutListener.listen(5910)) {
                        sb.append(" ").append(attr);
                    }
                    if (!ListenerUtil.mutListener.listen(5923)) {
                        if (override) {
                            if (!ListenerUtil.mutListener.listen(5922)) {
                                if (sb.charAt((ListenerUtil.mutListener.listen(5914) ? (sb.length() % 1) : (ListenerUtil.mutListener.listen(5913) ? (sb.length() / 1) : (ListenerUtil.mutListener.listen(5912) ? (sb.length() * 1) : (ListenerUtil.mutListener.listen(5911) ? (sb.length() + 1) : (sb.length() - 1)))))) == ';') {
                                    if (!ListenerUtil.mutListener.listen(5920)) {
                                        sb.deleteCharAt((ListenerUtil.mutListener.listen(5919) ? (sb.length() % 1) : (ListenerUtil.mutListener.listen(5918) ? (sb.length() / 1) : (ListenerUtil.mutListener.listen(5917) ? (sb.length() * 1) : (ListenerUtil.mutListener.listen(5916) ? (sb.length() + 1) : (sb.length() - 1))))));
                                    }
                                    if (!ListenerUtil.mutListener.listen(5921)) {
                                        sb.append(" !important;");
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(5915)) {
                                        Timber.d("AnkiFont.getCSS() - unable to set a font attribute important while override is set.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    public String getName() {
        return mName;
    }

    public String getPath() {
        return mPath;
    }

    public static Typeface getTypeface(Context ctx, String path) {
        try {
            if (path.startsWith(fAssetPathPrefix)) {
                return Typeface.createFromAsset(ctx.getAssets(), path.replaceFirst("/android_asset/", ""));
            } else {
                return Typeface.createFromFile(path);
            }
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(5925)) {
                Timber.w(e, "Runtime error in getTypeface for File: %s", path);
            }
            if (!ListenerUtil.mutListener.listen(5928)) {
                if (!corruptFonts.contains(path)) {
                    // Show warning toast
                    String name = new File(path).getName();
                    Resources res = AnkiDroidApp.getAppResources();
                    Toast toast = Toast.makeText(ctx, res.getString(R.string.corrupt_font, name), Toast.LENGTH_LONG);
                    if (!ListenerUtil.mutListener.listen(5926)) {
                        toast.show();
                    }
                    if (!ListenerUtil.mutListener.listen(5927)) {
                        // Don't warn again in this session
                        corruptFonts.add(path);
                    }
                }
            }
            return null;
        }
    }

    private void setAsDefault() {
        if (!ListenerUtil.mutListener.listen(5929)) {
            mIsDefault = true;
        }
        if (!ListenerUtil.mutListener.listen(5930)) {
            mIsOverride = false;
        }
    }

    private void setAsOverride() {
        if (!ListenerUtil.mutListener.listen(5931)) {
            mIsOverride = true;
        }
        if (!ListenerUtil.mutListener.listen(5932)) {
            mIsDefault = false;
        }
    }
}
