/**
 * *************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.WindowManager;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.snackbar.Snackbar;
import com.ichi2.libanki.Sound;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReadText {

    private static TextToSpeech mTts;

    private static final ArrayList<Locale> availableTtsLocales = new ArrayList<>();

    private static String mTextToSpeak;

    private static WeakReference<Context> mReviewer;

    private static long mDid;

    private static int mOrd;

    private static Sound.SoundSide mQuestionAnswer;

    public static final String NO_TTS = "0";

    private static final Bundle mTtsParams = new Bundle();

    public static Sound.SoundSide getmQuestionAnswer() {
        return mQuestionAnswer;
    }

    public static void speak(String text, String loc, int queueMode) {
        int result = mTts.setLanguage(localeFromStringIgnoringScriptAndExtensions(loc));
        if (!ListenerUtil.mutListener.listen(10872)) {
            if ((ListenerUtil.mutListener.listen(10863) ? (result == TextToSpeech.LANG_MISSING_DATA && result == TextToSpeech.LANG_NOT_SUPPORTED) : (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED))) {
                if (!ListenerUtil.mutListener.listen(10870)) {
                    Toast.makeText(mReviewer.get(), mReviewer.get().getString(R.string.no_tts_available_message) + " (" + loc + ")", Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(10871)) {
                    Timber.e("Error loading locale %s", loc);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10867)) {
                    if ((ListenerUtil.mutListener.listen(10864) ? (mTts.isSpeaking() || queueMode == TextToSpeech.QUEUE_FLUSH) : (mTts.isSpeaking() && queueMode == TextToSpeech.QUEUE_FLUSH))) {
                        if (!ListenerUtil.mutListener.listen(10865)) {
                            Timber.d("tts engine appears to be busy... clearing queue");
                        }
                        if (!ListenerUtil.mutListener.listen(10866)) {
                            stopTts();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10868)) {
                    Timber.d("tts text '%s' to be played for locale (%s)", text, loc);
                }
                if (!ListenerUtil.mutListener.listen(10869)) {
                    mTts.speak(mTextToSpeak, queueMode, mTtsParams, "stringId");
                }
            }
        }
    }

    public static String getLanguage(long did, int ord, Sound.SoundSide qa) {
        return MetaDB.getLanguage(mReviewer.get(), did, ord, qa);
    }

    /**
     * Ask the user what language they want.
     *
     * @param text The text to be read
     * @param did  The deck id
     * @param ord  The card template ordinal
     * @param qa   The card question or card answer
     */
    public static void selectTts(String text, long did, int ord, Sound.SoundSide qa) {
        if (!ListenerUtil.mutListener.listen(10873)) {
            // TODO: Consolidate with ReadText.readCardSide
            mTextToSpeak = text;
        }
        if (!ListenerUtil.mutListener.listen(10874)) {
            mQuestionAnswer = qa;
        }
        if (!ListenerUtil.mutListener.listen(10875)) {
            mDid = did;
        }
        if (!ListenerUtil.mutListener.listen(10876)) {
            mOrd = ord;
        }
        Resources res = mReviewer.get().getResources();
        final MaterialDialog.Builder builder = new MaterialDialog.Builder(mReviewer.get());
        if (!ListenerUtil.mutListener.listen(10878)) {
            // Build the language list if it's empty
            if (availableTtsLocales.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(10877)) {
                    buildAvailableLanguages();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10898)) {
            if ((ListenerUtil.mutListener.listen(10883) ? (availableTtsLocales.size() >= 0) : (ListenerUtil.mutListener.listen(10882) ? (availableTtsLocales.size() <= 0) : (ListenerUtil.mutListener.listen(10881) ? (availableTtsLocales.size() > 0) : (ListenerUtil.mutListener.listen(10880) ? (availableTtsLocales.size() < 0) : (ListenerUtil.mutListener.listen(10879) ? (availableTtsLocales.size() != 0) : (availableTtsLocales.size() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(10896)) {
                    Timber.w("ReadText.textToSpeech() no TTS languages available");
                }
                if (!ListenerUtil.mutListener.listen(10897)) {
                    builder.content(res.getString(R.string.no_tts_available_message)).iconAttr(R.attr.dialogErrorIcon).positiveText(R.string.dialog_ok);
                }
            } else {
                ArrayList<CharSequence> dialogItems = new ArrayList<>(availableTtsLocales.size());
                final ArrayList<String> dialogIds = new ArrayList<>(availableTtsLocales.size());
                if (!ListenerUtil.mutListener.listen(10884)) {
                    // Add option: "no tts"
                    dialogItems.add(res.getString(R.string.tts_no_tts));
                }
                if (!ListenerUtil.mutListener.listen(10885)) {
                    dialogIds.add(NO_TTS);
                }
                if (!ListenerUtil.mutListener.listen(10893)) {
                    {
                        long _loopCounter182 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(10892) ? (i >= availableTtsLocales.size()) : (ListenerUtil.mutListener.listen(10891) ? (i <= availableTtsLocales.size()) : (ListenerUtil.mutListener.listen(10890) ? (i > availableTtsLocales.size()) : (ListenerUtil.mutListener.listen(10889) ? (i != availableTtsLocales.size()) : (ListenerUtil.mutListener.listen(10888) ? (i == availableTtsLocales.size()) : (i < availableTtsLocales.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter182", ++_loopCounter182);
                            if (!ListenerUtil.mutListener.listen(10886)) {
                                dialogItems.add(availableTtsLocales.get(i).getDisplayName());
                            }
                            if (!ListenerUtil.mutListener.listen(10887)) {
                                dialogIds.add(availableTtsLocales.get(i).getISO3Language());
                            }
                        }
                    }
                }
                String[] items = new String[dialogItems.size()];
                if (!ListenerUtil.mutListener.listen(10894)) {
                    dialogItems.toArray(items);
                }
                if (!ListenerUtil.mutListener.listen(10895)) {
                    builder.title(res.getString(R.string.select_locale_title)).items(items).itemsCallback((materialDialog, view, which, charSequence) -> {
                        String locale = dialogIds.get(which);
                        Timber.d("ReadText.selectTts() user chose locale '%s'", locale);
                        if (!locale.equals(NO_TTS)) {
                            speak(mTextToSpeak, locale, TextToSpeech.QUEUE_FLUSH);
                        }
                        MetaDB.storeLanguage(mReviewer.get(), mDid, mOrd, mQuestionAnswer, locale);
                    });
                }
            }
        }
        // Show the dialog after short delay so that user gets a chance to preview the card
        final Handler handler = new Handler();
        final int delay = 500;
        if (!ListenerUtil.mutListener.listen(10899)) {
            handler.postDelayed(() -> {
                try {
                    builder.build().show();
                } catch (WindowManager.BadTokenException e) {
                    Timber.w("Activity invalidated before TTS language dialog could display");
                }
            }, delay);
        }
    }

    /**
     * Read a card side using a TTS service.
     *
     * @param cardSide         Card side to be read; SoundSide.SOUNDS_QUESTION or SoundSide.SOUNDS_ANSWER.
     * @param cardSideContents Contents of the card side to be read, in HTML format. If it contains
     *                         any &lt;tts service="android"&gt; elements, only their contents is
     *                         read; otherwise, all text is read. See TtsParser for more details.
     * @param did              Index of the deck containing the card.
     * @param ord              The card template ordinal.
     */
    public static void readCardSide(Sound.SoundSide cardSide, String cardSideContents, long did, int ord, String clozeReplacement) {
        boolean isFirstText = true;
        if (!ListenerUtil.mutListener.listen(10903)) {
            {
                long _loopCounter183 = 0;
                for (TtsParser.LocalisedText textToRead : TtsParser.getTextsToRead(cardSideContents, clozeReplacement)) {
                    ListenerUtil.loopListener.listen("_loopCounter183", ++_loopCounter183);
                    if (!ListenerUtil.mutListener.listen(10902)) {
                        if (!textToRead.getText().isEmpty()) {
                            if (!ListenerUtil.mutListener.listen(10900)) {
                                textToSpeech(textToRead.getText(), did, ord, cardSide, textToRead.getLocaleCode(), isFirstText ? TextToSpeech.QUEUE_FLUSH : TextToSpeech.QUEUE_ADD);
                            }
                            if (!ListenerUtil.mutListener.listen(10901)) {
                                isFirstText = false;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Read the given text using an appropriate TTS voice.
     * <p>
     * The voice is chosen as follows:
     * <p>
     * 1. If localeCode is a non-empty string representing a locale in the format returned
     * by Locale.toString(), and a voice matching the language of this locale (and ideally,
     * but not necessarily, also the country and variant of the locale) is available, then this
     * voice is used.
     * 2. Otherwise, if the database contains a saved language for the given 'did', 'ord' and 'qa'
     * arguments, and a TTS voice matching that language is available, then this voice is used
     * (unless the saved language is NO_TTS, in which case the text is not read at all).
     * 3. Otherwise, the user is asked to select a language from among those for which a voice is
     * available.
     *
     * @param queueMode TextToSpeech.QUEUE_ADD or TextToSpeech.QUEUE_FLUSH.
     */
    private static void textToSpeech(String text, long did, int ord, Sound.SoundSide qa, String localeCode, int queueMode) {
        if (!ListenerUtil.mutListener.listen(10904)) {
            mTextToSpeak = text;
        }
        if (!ListenerUtil.mutListener.listen(10905)) {
            mQuestionAnswer = qa;
        }
        if (!ListenerUtil.mutListener.listen(10906)) {
            mDid = did;
        }
        if (!ListenerUtil.mutListener.listen(10907)) {
            mOrd = ord;
        }
        if (!ListenerUtil.mutListener.listen(10908)) {
            Timber.d("ReadText.textToSpeech() method started for string '%s', locale '%s'", text, localeCode);
        }
        final String originalLocaleCode = localeCode;
        if (!ListenerUtil.mutListener.listen(10911)) {
            if (!localeCode.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(10910)) {
                    if (!isLanguageAvailable(localeCode)) {
                        if (!ListenerUtil.mutListener.listen(10909)) {
                            localeCode = "";
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10914)) {
            if (localeCode.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(10912)) {
                    // get the user's existing language preference
                    localeCode = getLanguage(mDid, mOrd, mQuestionAnswer);
                }
                if (!ListenerUtil.mutListener.listen(10913)) {
                    Timber.d("ReadText.textToSpeech() method found language choice '%s'", localeCode);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10915)) {
            if (localeCode.equals(NO_TTS)) {
                // user has chosen not to read the text
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10918)) {
            if ((ListenerUtil.mutListener.listen(10916) ? (!localeCode.isEmpty() || isLanguageAvailable(localeCode)) : (!localeCode.isEmpty() && isLanguageAvailable(localeCode)))) {
                if (!ListenerUtil.mutListener.listen(10917)) {
                    speak(mTextToSpeak, localeCode, queueMode);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10920)) {
            // Otherwise ask the user what language they want to use
            if (!originalLocaleCode.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(10919)) {
                    // they originally requested)
                    Toast.makeText(mReviewer.get(), mReviewer.get().getString(R.string.no_tts_available_message) + " (" + originalLocaleCode + ")", Toast.LENGTH_LONG).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10921)) {
            selectTts(mTextToSpeak, mDid, mOrd, mQuestionAnswer);
        }
    }

    /**
     * Convert a string representation of a locale, in the format returned by Locale.toString(),
     * into a Locale object, disregarding any script and extensions fields (i.e. using solely the
     * language, country and variant fields).
     * <p>
     * Returns a Locale object constructed from an empty string if the input string is null, empty
     * or contains more than 3 fields separated by underscores.
     */
    private static Locale localeFromStringIgnoringScriptAndExtensions(String localeCode) {
        if (localeCode == null) {
            return new Locale("");
        }
        if (!ListenerUtil.mutListener.listen(10922)) {
            localeCode = stripScriptAndExtensions(localeCode);
        }
        String[] fields = localeCode.split("_");
        switch(fields.length) {
            case 1:
                return new Locale(fields[0]);
            case 2:
                return new Locale(fields[0], fields[1]);
            case 3:
                return new Locale(fields[0], fields[1], fields[2]);
            default:
                return new Locale("");
        }
    }

    private static String stripScriptAndExtensions(String localeCode) {
        int hashPos = localeCode.indexOf('#');
        if (!ListenerUtil.mutListener.listen(10929)) {
            if ((ListenerUtil.mutListener.listen(10927) ? (hashPos <= 0) : (ListenerUtil.mutListener.listen(10926) ? (hashPos > 0) : (ListenerUtil.mutListener.listen(10925) ? (hashPos < 0) : (ListenerUtil.mutListener.listen(10924) ? (hashPos != 0) : (ListenerUtil.mutListener.listen(10923) ? (hashPos == 0) : (hashPos >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(10928)) {
                    localeCode = localeCode.substring(0, hashPos);
                }
            }
        }
        return localeCode;
    }

    /**
     * Returns true if the TTS engine supports the language of the locale represented by localeCode
     * (which should be in the format returned by Locale.toString()), false otherwise.
     */
    private static boolean isLanguageAvailable(String localeCode) {
        return (ListenerUtil.mutListener.listen(10934) ? (mTts.isLanguageAvailable(localeFromStringIgnoringScriptAndExtensions(localeCode)) <= TextToSpeech.LANG_AVAILABLE) : (ListenerUtil.mutListener.listen(10933) ? (mTts.isLanguageAvailable(localeFromStringIgnoringScriptAndExtensions(localeCode)) > TextToSpeech.LANG_AVAILABLE) : (ListenerUtil.mutListener.listen(10932) ? (mTts.isLanguageAvailable(localeFromStringIgnoringScriptAndExtensions(localeCode)) < TextToSpeech.LANG_AVAILABLE) : (ListenerUtil.mutListener.listen(10931) ? (mTts.isLanguageAvailable(localeFromStringIgnoringScriptAndExtensions(localeCode)) != TextToSpeech.LANG_AVAILABLE) : (ListenerUtil.mutListener.listen(10930) ? (mTts.isLanguageAvailable(localeFromStringIgnoringScriptAndExtensions(localeCode)) == TextToSpeech.LANG_AVAILABLE) : (mTts.isLanguageAvailable(localeFromStringIgnoringScriptAndExtensions(localeCode)) >= TextToSpeech.LANG_AVAILABLE))))));
    }

    public static void initializeTts(Context context, @NonNull ReadTextListener listener) {
        if (!ListenerUtil.mutListener.listen(10935)) {
            // Store weak reference to Activity to prevent memory leak
            mReviewer = new WeakReference<>(context);
        }
        if (!ListenerUtil.mutListener.listen(10936)) {
            // Create new TTS object and setup its onInit Listener
            mTts = new TextToSpeech(context, status -> {
                if (status == TextToSpeech.SUCCESS) {
                    // build list of available languages
                    buildAvailableLanguages();
                    if (availableTtsLocales.size() > 0) {
                        // notify the reviewer that TTS has been initialized
                        Timber.d("TTS initialized and available languages found");
                        ((AbstractFlashcardViewer) mReviewer.get()).ttsInitialized();
                    } else {
                        Toast.makeText(mReviewer.get(), mReviewer.get().getString(R.string.no_tts_available_message), Toast.LENGTH_LONG).show();
                        Timber.w("TTS initialized but no available languages found");
                    }
                    mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {

                        @Override
                        public void onDone(String arg0) {
                            listener.onDone();
                        }

                        @Override
                        @Deprecated
                        public void onError(String utteranceId) {
                            Timber.v("Andoid TTS failed. Check logcat for error. Indicates a problem with Android TTS engine.");
                            final Uri helpUrl = Uri.parse(mReviewer.get().getString(R.string.link_faq_tts));
                            final AnkiActivity ankiActivity = (AnkiActivity) mReviewer.get();
                            ankiActivity.mayOpenUrl(helpUrl);
                            UIUtils.showSnackbar(ankiActivity, R.string.no_tts_available_message, false, R.string.help, v -> openTtsHelpUrl(helpUrl), ankiActivity.findViewById(R.id.root_layout), new Snackbar.Callback());
                        }

                        @Override
                        public void onStart(String arg0) {
                        }
                    });
                } else {
                    Toast.makeText(mReviewer.get(), mReviewer.get().getString(R.string.no_tts_available_message), Toast.LENGTH_LONG).show();
                    Timber.w("TTS not successfully initialized");
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10937)) {
            // Show toast that it's getting initialized, as it can take a while before the sound plays the first time
            Toast.makeText(context, context.getString(R.string.initializing_tts), Toast.LENGTH_LONG).show();
        }
    }

    private static void openTtsHelpUrl(Uri helpUrl) {
        AnkiActivity activity = (AnkiActivity) mReviewer.get();
        if (!ListenerUtil.mutListener.listen(10938)) {
            activity.openUrl(helpUrl);
        }
    }

    public static void buildAvailableLanguages() {
        if (!ListenerUtil.mutListener.listen(10939)) {
            availableTtsLocales.clear();
        }
        Locale[] systemLocales = Locale.getAvailableLocales();
        if (!ListenerUtil.mutListener.listen(10940)) {
            availableTtsLocales.ensureCapacity(systemLocales.length);
        }
        if (!ListenerUtil.mutListener.listen(10950)) {
            {
                long _loopCounter184 = 0;
                for (Locale loc : systemLocales) {
                    ListenerUtil.loopListener.listen("_loopCounter184", ++_loopCounter184);
                    try {
                        int retCode = mTts.isLanguageAvailable(loc);
                        if (!ListenerUtil.mutListener.listen(10949)) {
                            if ((ListenerUtil.mutListener.listen(10946) ? (retCode <= TextToSpeech.LANG_COUNTRY_AVAILABLE) : (ListenerUtil.mutListener.listen(10945) ? (retCode > TextToSpeech.LANG_COUNTRY_AVAILABLE) : (ListenerUtil.mutListener.listen(10944) ? (retCode < TextToSpeech.LANG_COUNTRY_AVAILABLE) : (ListenerUtil.mutListener.listen(10943) ? (retCode != TextToSpeech.LANG_COUNTRY_AVAILABLE) : (ListenerUtil.mutListener.listen(10942) ? (retCode == TextToSpeech.LANG_COUNTRY_AVAILABLE) : (retCode >= TextToSpeech.LANG_COUNTRY_AVAILABLE))))))) {
                                if (!ListenerUtil.mutListener.listen(10948)) {
                                    availableTtsLocales.add(loc);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(10947)) {
                                    Timber.v("ReadText.buildAvailableLanguages() :: %s  not available (error code %d)", loc.getDisplayName(), retCode);
                                }
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        if (!ListenerUtil.mutListener.listen(10941)) {
                            Timber.e("Error checking if language %s available", loc.getDisplayName());
                        }
                    }
                }
            }
        }
    }

    public static void releaseTts() {
        if (!ListenerUtil.mutListener.listen(10953)) {
            if (mTts != null) {
                if (!ListenerUtil.mutListener.listen(10951)) {
                    mTts.stop();
                }
                if (!ListenerUtil.mutListener.listen(10952)) {
                    mTts.shutdown();
                }
            }
        }
    }

    public static void stopTts() {
        if (!ListenerUtil.mutListener.listen(10955)) {
            if (mTts != null) {
                if (!ListenerUtil.mutListener.listen(10954)) {
                    mTts.stop();
                }
            }
        }
    }

    public static void closeForTests() {
        if (!ListenerUtil.mutListener.listen(10957)) {
            if (mTts != null) {
                if (!ListenerUtil.mutListener.listen(10956)) {
                    mTts.shutdown();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10958)) {
            mTts = null;
        }
        if (!ListenerUtil.mutListener.listen(10959)) {
            MetaDB.close();
        }
        if (!ListenerUtil.mutListener.listen(10960)) {
            System.gc();
        }
    }

    interface ReadTextListener {

        void onDone();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @Nullable
    public static String getTextToSpeak() {
        return mTextToSpeak;
    }
}
