package com.ichi2.anki;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.libanki.Utils;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Lookup {

    // use no dictionary
    private static final int DICTIONARY_NONE = 0;

    // Japanese dictionary
    private static final int DICTIONARY_AEDICT = 1;

    // japanese web dictionary
    private static final int DICTIONARY_EIJIRO_WEB = 2;

    // German web dictionary for English, French, Spanish, Italian,
    private static final int DICTIONARY_LEO_WEB = 3;

    // German web dictionary for English, French, Spanish, Italian,
    private static final int DICTIONARY_LEO_APP = 4;

    // Chinese, Russian
    private static final int DICTIONARY_COLORDICT = 5;

    private static final int DICTIONARY_FORA = 6;

    // chinese web dictionary
    private static final int DICTIONARY_NCIKU_WEB = 7;

    private static Context mContext;

    private static boolean mIsDictionaryAvailable;

    private static String mDictionaryAction;

    private static int mDictionary;

    private static String mLookupText;

    public static boolean initialize(Context context) {
        if (!ListenerUtil.mutListener.listen(8636)) {
            mContext = context;
        }
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(AnkiDroidApp.getInstance().getBaseContext());
        if (!ListenerUtil.mutListener.listen(8637)) {
            mDictionary = Integer.parseInt(preferences.getString("dictionary", Integer.toString(DICTIONARY_NONE)));
        }
        if (!ListenerUtil.mutListener.listen(8649)) {
            switch(mDictionary) {
                case DICTIONARY_AEDICT:
                    if (!ListenerUtil.mutListener.listen(8638)) {
                        mDictionaryAction = "sk.baka.aedict.action.ACTION_SEARCH_EDICT";
                    }
                    if (!ListenerUtil.mutListener.listen(8639)) {
                        mIsDictionaryAvailable = Utils.isIntentAvailable(mContext, mDictionaryAction);
                    }
                    break;
                case DICTIONARY_LEO_WEB:
                case DICTIONARY_NCIKU_WEB:
                case DICTIONARY_EIJIRO_WEB:
                    if (!ListenerUtil.mutListener.listen(8640)) {
                        mDictionaryAction = "android.intent.action.VIEW";
                    }
                    if (!ListenerUtil.mutListener.listen(8641)) {
                        mIsDictionaryAvailable = Utils.isIntentAvailable(mContext, mDictionaryAction);
                    }
                    break;
                case DICTIONARY_LEO_APP:
                    if (!ListenerUtil.mutListener.listen(8642)) {
                        mDictionaryAction = "android.intent.action.SEND";
                    }
                    if (!ListenerUtil.mutListener.listen(8643)) {
                        mIsDictionaryAvailable = Utils.isIntentAvailable(mContext, mDictionaryAction, new ComponentName("org.leo.android.dict", "org.leo.android.dict.LeoDict"));
                    }
                    break;
                case DICTIONARY_COLORDICT:
                    if (!ListenerUtil.mutListener.listen(8644)) {
                        mDictionaryAction = "colordict.intent.action.SEARCH";
                    }
                    if (!ListenerUtil.mutListener.listen(8645)) {
                        mIsDictionaryAvailable = Utils.isIntentAvailable(mContext, mDictionaryAction);
                    }
                    break;
                case DICTIONARY_FORA:
                    if (!ListenerUtil.mutListener.listen(8646)) {
                        mDictionaryAction = "com.ngc.fora.action.LOOKUP";
                    }
                    if (!ListenerUtil.mutListener.listen(8647)) {
                        mIsDictionaryAvailable = Utils.isIntentAvailable(mContext, mDictionaryAction);
                    }
                    break;
                case DICTIONARY_NONE:
                default:
                    if (!ListenerUtil.mutListener.listen(8648)) {
                        mIsDictionaryAvailable = false;
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(8650)) {
            Timber.v("Is intent available = %b", mIsDictionaryAvailable);
        }
        return mIsDictionaryAvailable;
    }

    public static boolean lookUp(String text) {
        if (!ListenerUtil.mutListener.listen(8651)) {
            if (!mIsDictionaryAvailable) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(8652)) {
            // clear text from leading and closing dots, commas, brackets etc.
            text = text.trim().replaceAll("[,;:\\s(\\[)\\].]*$", "").replaceAll("^[,;:\\s(\\[)\\].]*", "");
        }
        if (!ListenerUtil.mutListener.listen(8673)) {
            switch(mDictionary) {
                case DICTIONARY_NONE:
                    return false;
                case DICTIONARY_AEDICT:
                    Intent aedictSearchIntent = new Intent(mDictionaryAction);
                    if (!ListenerUtil.mutListener.listen(8653)) {
                        aedictSearchIntent.putExtra("kanjis", text);
                    }
                    if (!ListenerUtil.mutListener.listen(8654)) {
                        mContext.startActivity(aedictSearchIntent);
                    }
                    return true;
                case DICTIONARY_LEO_WEB:
                case DICTIONARY_LEO_APP:
                    if (!ListenerUtil.mutListener.listen(8655)) {
                        mLookupText = text;
                    }
                    // localisation is needless here since leo.org translates only into or out of German
                    final CharSequence[] itemValues = { "en", "fr", "es", "it", "ch", "ru" };
                    String language = getLanguage(MetaDB.LANGUAGES_QA_UNDEFINED);
                    if (!ListenerUtil.mutListener.listen(8665)) {
                        if ((ListenerUtil.mutListener.listen(8660) ? (language.length() >= 0) : (ListenerUtil.mutListener.listen(8659) ? (language.length() <= 0) : (ListenerUtil.mutListener.listen(8658) ? (language.length() < 0) : (ListenerUtil.mutListener.listen(8657) ? (language.length() != 0) : (ListenerUtil.mutListener.listen(8656) ? (language.length() == 0) : (language.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(8664)) {
                                {
                                    long _loopCounter140 = 0;
                                    for (CharSequence itemValue : itemValues) {
                                        ListenerUtil.loopListener.listen("_loopCounter140", ++_loopCounter140);
                                        if (!ListenerUtil.mutListener.listen(8663)) {
                                            if (language.contentEquals(itemValue)) {
                                                if (!ListenerUtil.mutListener.listen(8661)) {
                                                    lookupLeo(language, mLookupText);
                                                }
                                                if (!ListenerUtil.mutListener.listen(8662)) {
                                                    mLookupText = "";
                                                }
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    final String[] items = { "Englisch", "Französisch", "Spanisch", "Italienisch", "Chinesisch", "Russisch" };
                    if (!ListenerUtil.mutListener.listen(8666)) {
                        new MaterialDialog.Builder(mContext).title("\"" + mLookupText + "\" nachschlagen").items(items).itemsCallback((materialDialog, view, item, charSequence) -> {
                            String language1 = itemValues[item].toString();
                            storeLanguage(language1, MetaDB.LANGUAGES_QA_UNDEFINED);
                            lookupLeo(language1, mLookupText);
                            mLookupText = "";
                        }).build().show();
                    }
                    return true;
                case DICTIONARY_COLORDICT:
                    Intent colordictSearchIntent = new Intent(mDictionaryAction);
                    if (!ListenerUtil.mutListener.listen(8667)) {
                        colordictSearchIntent.putExtra("EXTRA_QUERY", text);
                    }
                    if (!ListenerUtil.mutListener.listen(8668)) {
                        mContext.startActivity(colordictSearchIntent);
                    }
                    return true;
                case DICTIONARY_FORA:
                    Intent foraSearchIntent = new Intent(mDictionaryAction);
                    if (!ListenerUtil.mutListener.listen(8669)) {
                        foraSearchIntent.putExtra("HEADWORD", text.trim());
                    }
                    if (!ListenerUtil.mutListener.listen(8670)) {
                        mContext.startActivity(foraSearchIntent);
                    }
                    return true;
                case DICTIONARY_NCIKU_WEB:
                    Intent ncikuWebIntent = new Intent(mDictionaryAction, Uri.parse("http://m.nciku.com/en/entry/?query=" + text));
                    if (!ListenerUtil.mutListener.listen(8671)) {
                        mContext.startActivity(ncikuWebIntent);
                    }
                    return true;
                case DICTIONARY_EIJIRO_WEB:
                    Intent eijiroWebIntent = new Intent(mDictionaryAction, Uri.parse("http://eow.alc.co.jp/" + text));
                    if (!ListenerUtil.mutListener.listen(8672)) {
                        mContext.startActivity(eijiroWebIntent);
                    }
                    return true;
            }
        }
        return false;
    }

    private static void lookupLeo(String language, CharSequence text) {
        if (!ListenerUtil.mutListener.listen(8679)) {
            switch(mDictionary) {
                case DICTIONARY_LEO_WEB:
                    Intent leoSearchIntent = new Intent(mDictionaryAction, Uri.parse("http://pda.leo.org/?lp=" + language + "de&search=" + text));
                    if (!ListenerUtil.mutListener.listen(8674)) {
                        mContext.startActivity(leoSearchIntent);
                    }
                    break;
                case DICTIONARY_LEO_APP:
                    Intent leoAppSearchIntent = new Intent(mDictionaryAction);
                    if (!ListenerUtil.mutListener.listen(8675)) {
                        leoAppSearchIntent.putExtra("org.leo.android.dict.DICTIONARY", language + "de");
                    }
                    if (!ListenerUtil.mutListener.listen(8676)) {
                        leoAppSearchIntent.putExtra(Intent.EXTRA_TEXT, text);
                    }
                    if (!ListenerUtil.mutListener.listen(8677)) {
                        leoAppSearchIntent.setComponent(new ComponentName("org.leo.android.dict", "org.leo.android.dict.LeoDict"));
                    }
                    if (!ListenerUtil.mutListener.listen(8678)) {
                        mContext.startActivity(leoAppSearchIntent);
                    }
                    break;
                default:
            }
        }
    }

    public static String getSearchStringTitle() {
        return String.format(mContext.getString(R.string.menu_search), mContext.getResources().getStringArray(R.array.dictionary_labels)[mDictionary]);
    }

    public static boolean isAvailable() {
        return mIsDictionaryAvailable;
    }

    private static String getLanguage(int questionAnswer) {
        // if (mCurrentCard == null) {
        return "";
    }

    private static void storeLanguage(String language, int questionAnswer) {
    }
}
