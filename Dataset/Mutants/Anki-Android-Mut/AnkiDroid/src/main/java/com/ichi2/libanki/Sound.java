/**
 * *************************************************************************************
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2014 Timothy rae <perceptualchaos2@gmail.com>                          *
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
package com.ichi2.libanki;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.VideoView;
import com.ichi2.anki.AbstractFlashcardViewer;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.ReadText;
import com.ichi2.utils.StringUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Class used to parse, load and play sound files on AnkiDroid.
 */
@SuppressWarnings({ "PMD.NPathComplexity", "PMD.CollapsibleIfStatements" })
public class Sound {

    /**
     * Pattern used to identify the markers for sound files
     */
    public static final Pattern sSoundPattern = Pattern.compile("\\[sound:([^\\[\\]]*)]");

    /**
     * Pattern used to parse URI (according to http://tools.ietf.org/html/rfc3986#page-50)
     */
    private static final Pattern sUriPattern = Pattern.compile("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?$");

    /**
     * Media player used to play the sounds
     */
    private MediaPlayer mMediaPlayer;

    /**
     * AudioManager to request/release audio focus
     */
    private AudioManager mAudioManager;

    /**
     * OnCompletionListener so that external video player can notify to play next sound
     */
    private static OnCompletionListener mPlayAllListener;

    /**
     * Weak reference to the activity which is attempting to play the sound
     */
    private WeakReference<Activity> mCallingActivity;

    /**
     * Subset Flags: Flags that indicate the subset of sounds to involve
     */
    public enum SoundSide {

        QUESTION(0), ANSWER(1), QUESTION_AND_ANSWER(2);

        private final int mInt;

        SoundSide(int i) {
            mInt = i;
        }

        public int getInt() {
            return mInt;
        }
    }

    /**
     * Stores sounds for the current card, key is one of the subset flags. It is intended that it not contain empty lists, and code assumes this will be true.
     */
    private final HashMap<SoundSide, ArrayList<String>> mSoundPaths = new HashMap<>();

    /**
     * Whitelist for video extensions
     */
    private static final String[] VIDEO_WHITELIST = { "3gp", "mp4", "webm", "mkv", "flv" };

    /**
     * Listener to handle audio focus. Currently blank because we're not respecting losing focus from other apps.
     */
    private static final AudioManager.OnAudioFocusChangeListener afChangeListener = focusChange -> {
    };

    // Clears current sound paths; call before parseSounds() calls
    public void resetSounds() {
        if (!ListenerUtil.mutListener.listen(23694)) {
            mSoundPaths.clear();
        }
    }

    /**
     * The function addSounds() parses content for sound files, and stores entries to the filepaths for them,
     * categorized as belonging to the front (question) or back (answer) of cards. Note that all sounds embedded in
     * the content will be given the same base categorization of question or answer. Additionally, the result is to be
     * sorted by the order of appearance on the card.
     * @param soundDir -- base path to the media files
     * @param content -- parsed for sound entries, the entries expected in display order
     * @param qa -- the base categorization of the sounds in the content, SoundSide.SOUNDS_QUESTION or SoundSide.SOUNDS_ANSWER
     */
    public void addSounds(String soundDir, String content, SoundSide qa) {
        Matcher matcher = sSoundPattern.matcher(content);
        if (!ListenerUtil.mutListener.listen(23699)) {
            {
                long _loopCounter609 = 0;
                // While there is matches of the pattern for sound markers
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter609", ++_loopCounter609);
                    if (!ListenerUtil.mutListener.listen(23696)) {
                        // Create appropriate list if needed; list must not be empty so long as code does no check
                        if (!mSoundPaths.containsKey(qa)) {
                            if (!ListenerUtil.mutListener.listen(23695)) {
                                mSoundPaths.put(qa, new ArrayList<>(0));
                            }
                        }
                    }
                    // Get the sound file name
                    String sound = matcher.group(1);
                    if (!ListenerUtil.mutListener.listen(23697)) {
                        // Construct the sound path and store it
                        Timber.d("Adding Sound to side: %s", qa);
                    }
                    if (!ListenerUtil.mutListener.listen(23698)) {
                        mSoundPaths.get(qa).add(getSoundPath(soundDir, sound));
                    }
                }
            }
        }
    }

    /**
     * makeQuestionAnswerSoundList creates a single list of both the question and answer audio only if it does not
     * already exist. It's intended for lazy evaluation, only in the rare cases when both sides are fully played
     * together, which even when configured as supported may not be instigated
     * @return True if a non-null list was created, or false otherwise
     */
    private Boolean makeQuestionAnswerList() {
        if (!ListenerUtil.mutListener.listen(23700)) {
            // if combined list already exists, don't recreate
            if (mSoundPaths.containsKey(SoundSide.QUESTION_AND_ANSWER)) {
                // combined list already exists
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(23703)) {
            // make combined list only if necessary to avoid an empty combined list
            if ((ListenerUtil.mutListener.listen(23701) ? (mSoundPaths.containsKey(SoundSide.QUESTION) && mSoundPaths.containsKey(SoundSide.ANSWER)) : (mSoundPaths.containsKey(SoundSide.QUESTION) || mSoundPaths.containsKey(SoundSide.ANSWER)))) {
                if (!ListenerUtil.mutListener.listen(23702)) {
                    // some list exists to place into combined list
                    mSoundPaths.put(SoundSide.QUESTION_AND_ANSWER, new ArrayList<>(0));
                }
            } else {
                // no need to make list
                return false;
            }
        }
        ArrayList<String> combinedSounds = mSoundPaths.get(SoundSide.QUESTION_AND_ANSWER);
        if (!ListenerUtil.mutListener.listen(23705)) {
            if (mSoundPaths.containsKey(SoundSide.QUESTION)) {
                if (!ListenerUtil.mutListener.listen(23704)) {
                    combinedSounds.addAll(mSoundPaths.get(SoundSide.QUESTION));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23707)) {
            if (mSoundPaths.containsKey(SoundSide.ANSWER)) {
                if (!ListenerUtil.mutListener.listen(23706)) {
                    combinedSounds.addAll(mSoundPaths.get(SoundSide.ANSWER));
                }
            }
        }
        return true;
    }

    /**
     * expandSounds takes content with embedded sound file placeholders and expands them to reference the actual media
     * file
     *
     * @param soundDir -- the base path of the media files
     * @param content -- card content to be rendered that may contain embedded audio
     * @return -- the same content but in a format that will render working play buttons when audio was embedded
     */
    public static String expandSounds(String soundDir, String content) {
        StringBuilder stringBuilder = new StringBuilder();
        String contentLeft = content;
        if (!ListenerUtil.mutListener.listen(23708)) {
            Timber.d("expandSounds");
        }
        Matcher matcher = sSoundPattern.matcher(content);
        if (!ListenerUtil.mutListener.listen(23717)) {
            {
                long _loopCounter610 = 0;
                // While there is matches of the pattern for sound markers
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter610", ++_loopCounter610);
                    // Get the sound file name
                    String sound = matcher.group(1);
                    // Construct the sound path
                    String soundPath = getSoundPath(soundDir, sound);
                    // and then appending the html code to add the play button
                    String button = "<svg viewBox=\"0 0 32 32\"><polygon points=\"11,25 25,16 11,7\"/>Replay</svg>";
                    String soundMarker = matcher.group();
                    int markerStart = contentLeft.indexOf(soundMarker);
                    if (!ListenerUtil.mutListener.listen(23709)) {
                        stringBuilder.append(contentLeft.substring(0, markerStart));
                    }
                    if (!ListenerUtil.mutListener.listen(23710)) {
                        // The <span> around the button (SVG or PNG image) is needed to make the vertical alignment work.
                        stringBuilder.append("<a class='replaybutton' href=\"playsound:").append(soundPath).append("\">").append("<span>").append(button).append("</span></a>");
                    }
                    if (!ListenerUtil.mutListener.listen(23715)) {
                        contentLeft = contentLeft.substring((ListenerUtil.mutListener.listen(23714) ? (markerStart % soundMarker.length()) : (ListenerUtil.mutListener.listen(23713) ? (markerStart / soundMarker.length()) : (ListenerUtil.mutListener.listen(23712) ? (markerStart * soundMarker.length()) : (ListenerUtil.mutListener.listen(23711) ? (markerStart - soundMarker.length()) : (markerStart + soundMarker.length()))))));
                    }
                    if (!ListenerUtil.mutListener.listen(23716)) {
                        Timber.v("Content left = %s", contentLeft);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23718)) {
            stringBuilder.append(contentLeft);
        }
        return stringBuilder.toString();
    }

    /**
     * Plays the sounds for the indicated sides
     * @param qa -- One of SoundSide.SOUNDS_QUESTION, SoundSide.SOUNDS_ANSWER, or SoundSide.SOUNDS_QUESTION_AND_ANSWER
     */
    public void playSounds(SoundSide qa, @Nullable OnErrorListener errorListener) {
        if (!ListenerUtil.mutListener.listen(23727)) {
            // If there are sounds to play for the current card, start with the first one
            if ((ListenerUtil.mutListener.listen(23719) ? (mSoundPaths != null || mSoundPaths.containsKey(qa)) : (mSoundPaths != null && mSoundPaths.containsKey(qa)))) {
                if (!ListenerUtil.mutListener.listen(23725)) {
                    Timber.d("playSounds %s", qa);
                }
                if (!ListenerUtil.mutListener.listen(23726)) {
                    playSoundInternal(mSoundPaths.get(qa).get(0), new PlayAllCompletionListener(qa, errorListener), null, errorListener);
                }
            } else if ((ListenerUtil.mutListener.listen(23720) ? (mSoundPaths != null || qa == SoundSide.QUESTION_AND_ANSWER) : (mSoundPaths != null && qa == SoundSide.QUESTION_AND_ANSWER))) {
                if (!ListenerUtil.mutListener.listen(23724)) {
                    if (makeQuestionAnswerList()) {
                        if (!ListenerUtil.mutListener.listen(23722)) {
                            Timber.d("playSounds: playing both question and answer");
                        }
                        if (!ListenerUtil.mutListener.listen(23723)) {
                            playSoundInternal(mSoundPaths.get(qa).get(0), new PlayAllCompletionListener(qa, errorListener), null, errorListener);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23721)) {
                            Timber.d("playSounds: No question answer list, not playing sound");
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns length in milliseconds.
     * @param qa -- One of SoundSide.SOUNDS_QUESTION, SoundSide.SOUNDS_ANSWER, or SoundSide.SOUNDS_QUESTION_AND_ANSWER
     */
    public long getSoundsLength(SoundSide qa) {
        long length = 0;
        if (!ListenerUtil.mutListener.listen(23735)) {
            if ((ListenerUtil.mutListener.listen(23730) ? (mSoundPaths != null || ((ListenerUtil.mutListener.listen(23729) ? ((ListenerUtil.mutListener.listen(23728) ? (qa == SoundSide.QUESTION_AND_ANSWER || makeQuestionAnswerList()) : (qa == SoundSide.QUESTION_AND_ANSWER && makeQuestionAnswerList())) && mSoundPaths.containsKey(qa)) : ((ListenerUtil.mutListener.listen(23728) ? (qa == SoundSide.QUESTION_AND_ANSWER || makeQuestionAnswerList()) : (qa == SoundSide.QUESTION_AND_ANSWER && makeQuestionAnswerList())) || mSoundPaths.containsKey(qa))))) : (mSoundPaths != null && ((ListenerUtil.mutListener.listen(23729) ? ((ListenerUtil.mutListener.listen(23728) ? (qa == SoundSide.QUESTION_AND_ANSWER || makeQuestionAnswerList()) : (qa == SoundSide.QUESTION_AND_ANSWER && makeQuestionAnswerList())) && mSoundPaths.containsKey(qa)) : ((ListenerUtil.mutListener.listen(23728) ? (qa == SoundSide.QUESTION_AND_ANSWER || makeQuestionAnswerList()) : (qa == SoundSide.QUESTION_AND_ANSWER && makeQuestionAnswerList())) || mSoundPaths.containsKey(qa))))))) {
                MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
                if (!ListenerUtil.mutListener.listen(23734)) {
                    {
                        long _loopCounter611 = 0;
                        for (String uri_string : mSoundPaths.get(qa)) {
                            ListenerUtil.loopListener.listen("_loopCounter611", ++_loopCounter611);
                            Uri soundUri = Uri.parse(uri_string);
                            try {
                                if (!ListenerUtil.mutListener.listen(23732)) {
                                    metaRetriever.setDataSource(AnkiDroidApp.getInstance().getApplicationContext(), soundUri);
                                }
                                if (!ListenerUtil.mutListener.listen(23733)) {
                                    length += Long.parseLong(metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(23731)) {
                                    Timber.e(e, "metaRetriever - Error setting Data Source for mediaRetriever (media doesn't exist or forbidden?).");
                                }
                            }
                        }
                    }
                }
            }
        }
        return length;
    }

    /**
     * Plays the given sound or video and sets playAllListener if available on media player to start next media.
     * If videoView is null and the media is a video, then a request is sent to start the VideoPlayer Activity
     */
    public void playSound(String soundPath, OnCompletionListener playAllListener, final VideoView videoView, @Nullable OnErrorListener errorListener) {
        if (!ListenerUtil.mutListener.listen(23736)) {
            Timber.d("Playing single sound");
        }
        SingleSoundCompletionListener completionListener = new SingleSoundCompletionListener(playAllListener);
        if (!ListenerUtil.mutListener.listen(23737)) {
            playSoundInternal(soundPath, completionListener, videoView, errorListener);
        }
    }

    /**
     * Plays a sound without ensuring that the playAllListener will release the audio
     */
    // audio API deprecation tracked on github as #5022
    @SuppressWarnings({ "PMD.EmptyIfStmt", "PMD.CollapsibleIfStatements", "deprecation" })
    private void playSoundInternal(String soundPath, OnCompletionListener playAllListener, VideoView videoView, OnErrorListener errorListener) {
        if (!ListenerUtil.mutListener.listen(23738)) {
            Timber.d("Playing %s has listener? %b", soundPath, playAllListener != null);
        }
        Uri soundUri = Uri.parse(soundPath);
        final OnErrorListener errorHandler = errorListener == null ? (mp, what, extra, path) -> {
            Timber.w("Media Error: (%d, %d). Calling OnCompletionListener", what, extra);
            return false;
        } : errorListener;
        if (!ListenerUtil.mutListener.listen(23777)) {
            if ("tts".equals(soundPath.substring(0, 3))) {
            } else {
                // Check if the file extension is that of a known video format
                final String extension = soundPath.substring((ListenerUtil.mutListener.listen(23742) ? (soundPath.lastIndexOf(".") % 1) : (ListenerUtil.mutListener.listen(23741) ? (soundPath.lastIndexOf(".") / 1) : (ListenerUtil.mutListener.listen(23740) ? (soundPath.lastIndexOf(".") * 1) : (ListenerUtil.mutListener.listen(23739) ? (soundPath.lastIndexOf(".") - 1) : (soundPath.lastIndexOf(".") + 1)))))).toLowerCase(Locale.getDefault());
                boolean isVideo = Arrays.asList(VIDEO_WHITELIST).contains(extension);
                if (!ListenerUtil.mutListener.listen(23745)) {
                    if (!isVideo) {
                        final String guessedType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                        if (!ListenerUtil.mutListener.listen(23744)) {
                            isVideo = (ListenerUtil.mutListener.listen(23743) ? ((guessedType != null) || guessedType.startsWith("video/")) : ((guessedType != null) && guessedType.startsWith("video/")));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23747)) {
                    // Also check that there is a video thumbnail, as some formats like mp4 can be audio only
                    isVideo = (ListenerUtil.mutListener.listen(23746) ? (isVideo || ThumbnailUtils.createVideoThumbnail(soundUri.getPath(), MediaStore.Images.Thumbnails.MINI_KIND) != null) : (isVideo && ThumbnailUtils.createVideoThumbnail(soundUri.getPath(), MediaStore.Images.Thumbnails.MINI_KIND) != null));
                }
                if (!ListenerUtil.mutListener.listen(23754)) {
                    // holder
                    if ((ListenerUtil.mutListener.listen(23750) ? ((ListenerUtil.mutListener.listen(23749) ? ((ListenerUtil.mutListener.listen(23748) ? (isVideo || videoView == null) : (isVideo && videoView == null)) || mCallingActivity != null) : ((ListenerUtil.mutListener.listen(23748) ? (isVideo || videoView == null) : (isVideo && videoView == null)) && mCallingActivity != null)) || mCallingActivity.get() != null) : ((ListenerUtil.mutListener.listen(23749) ? ((ListenerUtil.mutListener.listen(23748) ? (isVideo || videoView == null) : (isVideo && videoView == null)) || mCallingActivity != null) : ((ListenerUtil.mutListener.listen(23748) ? (isVideo || videoView == null) : (isVideo && videoView == null)) && mCallingActivity != null)) && mCallingActivity.get() != null))) {
                        if (!ListenerUtil.mutListener.listen(23751)) {
                            Timber.d("Requesting AbstractFlashcardViewer play video - no SurfaceHolder");
                        }
                        if (!ListenerUtil.mutListener.listen(23752)) {
                            mPlayAllListener = playAllListener;
                        }
                        if (!ListenerUtil.mutListener.listen(23753)) {
                            ((AbstractFlashcardViewer) mCallingActivity.get()).playVideo(soundPath);
                        }
                        return;
                    }
                }
                // Play media
                try {
                    if (!ListenerUtil.mutListener.listen(23761)) {
                        // Create media player
                        if (mMediaPlayer == null) {
                            if (!ListenerUtil.mutListener.listen(23759)) {
                                Timber.d("Creating media player for playback");
                            }
                            if (!ListenerUtil.mutListener.listen(23760)) {
                                mMediaPlayer = new MediaPlayer();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23757)) {
                                Timber.d("Resetting media for playback");
                            }
                            if (!ListenerUtil.mutListener.listen(23758)) {
                                mMediaPlayer.reset();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23763)) {
                        if (mAudioManager == null) {
                            if (!ListenerUtil.mutListener.listen(23762)) {
                                mAudioManager = (AudioManager) AnkiDroidApp.getInstance().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23767)) {
                        // Provide a VideoView to the MediaPlayer if valid video file
                        if ((ListenerUtil.mutListener.listen(23764) ? (isVideo || videoView != null) : (isVideo && videoView != null))) {
                            if (!ListenerUtil.mutListener.listen(23765)) {
                                mMediaPlayer.setDisplay(videoView.getHolder());
                            }
                            if (!ListenerUtil.mutListener.listen(23766)) {
                                mMediaPlayer.setOnVideoSizeChangedListener((mp, width, height) -> configureVideo(videoView, width, height));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23768)) {
                        mMediaPlayer.setOnErrorListener((mp, which, extra) -> errorHandler.onError(mp, which, extra, soundPath));
                    }
                    if (!ListenerUtil.mutListener.listen(23769)) {
                        // Setup the MediaPlayer
                        mMediaPlayer.setDataSource(AnkiDroidApp.getInstance().getApplicationContext(), soundUri);
                    }
                    if (!ListenerUtil.mutListener.listen(23770)) {
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    }
                    if (!ListenerUtil.mutListener.listen(23771)) {
                        mMediaPlayer.setOnPreparedListener(mp -> {
                            Timber.d("Starting media player");
                            mMediaPlayer.start();
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(23773)) {
                        if (playAllListener != null) {
                            if (!ListenerUtil.mutListener.listen(23772)) {
                                mMediaPlayer.setOnCompletionListener(playAllListener);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23774)) {
                        mMediaPlayer.prepareAsync();
                    }
                    if (!ListenerUtil.mutListener.listen(23775)) {
                        Timber.d("Requesting audio focus");
                    }
                    if (!ListenerUtil.mutListener.listen(23776)) {
                        mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(23755)) {
                        Timber.e(e, "playSounds - Error reproducing sound %s", soundPath);
                    }
                    if (!ListenerUtil.mutListener.listen(23756)) {
                        releaseSound();
                    }
                }
            }
        }
    }

    private static void configureVideo(VideoView videoView, int videoWidth, int videoHeight) {
        // get the display
        Context context = AnkiDroidApp.getInstance().getApplicationContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // adjust the size of the video so it fits on the screen
        float videoProportion = (ListenerUtil.mutListener.listen(23781) ? ((float) videoWidth % (float) videoHeight) : (ListenerUtil.mutListener.listen(23780) ? ((float) videoWidth * (float) videoHeight) : (ListenerUtil.mutListener.listen(23779) ? ((float) videoWidth - (float) videoHeight) : (ListenerUtil.mutListener.listen(23778) ? ((float) videoWidth + (float) videoHeight) : ((float) videoWidth / (float) videoHeight)))));
        Point point = new Point();
        if (!ListenerUtil.mutListener.listen(23782)) {
            display.getSize(point);
        }
        int screenWidth = point.x;
        int screenHeight = point.y;
        float screenProportion = (ListenerUtil.mutListener.listen(23786) ? ((float) screenWidth % (float) screenHeight) : (ListenerUtil.mutListener.listen(23785) ? ((float) screenWidth * (float) screenHeight) : (ListenerUtil.mutListener.listen(23784) ? ((float) screenWidth - (float) screenHeight) : (ListenerUtil.mutListener.listen(23783) ? ((float) screenWidth + (float) screenHeight) : ((float) screenWidth / (float) screenHeight)))));
        android.view.ViewGroup.LayoutParams lp = videoView.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(23804)) {
            if ((ListenerUtil.mutListener.listen(23791) ? (videoProportion >= screenProportion) : (ListenerUtil.mutListener.listen(23790) ? (videoProportion <= screenProportion) : (ListenerUtil.mutListener.listen(23789) ? (videoProportion < screenProportion) : (ListenerUtil.mutListener.listen(23788) ? (videoProportion != screenProportion) : (ListenerUtil.mutListener.listen(23787) ? (videoProportion == screenProportion) : (videoProportion > screenProportion))))))) {
                if (!ListenerUtil.mutListener.listen(23798)) {
                    lp.width = screenWidth;
                }
                if (!ListenerUtil.mutListener.listen(23803)) {
                    lp.height = (int) ((ListenerUtil.mutListener.listen(23802) ? ((float) screenWidth % videoProportion) : (ListenerUtil.mutListener.listen(23801) ? ((float) screenWidth * videoProportion) : (ListenerUtil.mutListener.listen(23800) ? ((float) screenWidth - videoProportion) : (ListenerUtil.mutListener.listen(23799) ? ((float) screenWidth + videoProportion) : ((float) screenWidth / videoProportion))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23796)) {
                    lp.width = (int) ((ListenerUtil.mutListener.listen(23795) ? (videoProportion % (float) screenHeight) : (ListenerUtil.mutListener.listen(23794) ? (videoProportion / (float) screenHeight) : (ListenerUtil.mutListener.listen(23793) ? (videoProportion - (float) screenHeight) : (ListenerUtil.mutListener.listen(23792) ? (videoProportion + (float) screenHeight) : (videoProportion * (float) screenHeight))))));
                }
                if (!ListenerUtil.mutListener.listen(23797)) {
                    lp.height = screenHeight;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23805)) {
            videoView.setLayoutParams(lp);
        }
    }

    public void notifyConfigurationChanged(VideoView videoView) {
        if (!ListenerUtil.mutListener.listen(23807)) {
            if (mMediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23806)) {
                    configureVideo(videoView, mMediaPlayer.getVideoWidth(), mMediaPlayer.getVideoHeight());
                }
            }
        }
    }

    /**
     * #5414 - Ensures playing a single sound performs cleanup
     */
    private final class SingleSoundCompletionListener implements OnCompletionListener {

        @Nullable
        private final OnCompletionListener userCallback;

        public SingleSoundCompletionListener(@Nullable OnCompletionListener userCallback) {
            this.userCallback = userCallback;
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            if (!ListenerUtil.mutListener.listen(23808)) {
                Timber.d("Single Sound completed");
            }
            if (!ListenerUtil.mutListener.listen(23811)) {
                if (userCallback != null) {
                    if (!ListenerUtil.mutListener.listen(23810)) {
                        userCallback.onCompletion(mp);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(23809)) {
                        releaseSound();
                    }
                }
            }
        }
    }

    /**
     * Class used to play all sounds for a given card side
     */
    private final class PlayAllCompletionListener implements OnCompletionListener {

        /**
         * Question/Answer
         */
        private final SoundSide mQa;

        private final OnErrorListener mErrorListener;

        /**
         * next sound to play (onCompletion() is first called after the first (0) has been played)
         */
        private int mNextToPlay = 1;

        private PlayAllCompletionListener(SoundSide qa, @Nullable OnErrorListener errorListener) {
            mQa = qa;
            mErrorListener = errorListener;
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            if (!ListenerUtil.mutListener.listen(23822)) {
                // If there is still more sounds to play for the current card, play the next one
                if ((ListenerUtil.mutListener.listen(23817) ? (mSoundPaths.containsKey(mQa) || (ListenerUtil.mutListener.listen(23816) ? (mNextToPlay >= mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23815) ? (mNextToPlay <= mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23814) ? (mNextToPlay > mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23813) ? (mNextToPlay != mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23812) ? (mNextToPlay == mSoundPaths.get(mQa).size()) : (mNextToPlay < mSoundPaths.get(mQa).size()))))))) : (mSoundPaths.containsKey(mQa) && (ListenerUtil.mutListener.listen(23816) ? (mNextToPlay >= mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23815) ? (mNextToPlay <= mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23814) ? (mNextToPlay > mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23813) ? (mNextToPlay != mSoundPaths.get(mQa).size()) : (ListenerUtil.mutListener.listen(23812) ? (mNextToPlay == mSoundPaths.get(mQa).size()) : (mNextToPlay < mSoundPaths.get(mQa).size()))))))))) {
                    if (!ListenerUtil.mutListener.listen(23820)) {
                        Timber.i("Play all: Playing next sound");
                    }
                    if (!ListenerUtil.mutListener.listen(23821)) {
                        playSound(mSoundPaths.get(mQa).get(mNextToPlay++), this, null, mErrorListener);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(23818)) {
                        Timber.i("Play all: Completed - releasing sound");
                    }
                    if (!ListenerUtil.mutListener.listen(23819)) {
                        releaseSound();
                    }
                }
            }
        }
    }

    /**
     * Releases the sound.
     */
    // Tracked on github as #5022
    @SuppressWarnings("deprecation")
    private void releaseSound() {
        if (!ListenerUtil.mutListener.listen(23823)) {
            Timber.d("Releasing sounds and abandoning audio focus");
        }
        if (!ListenerUtil.mutListener.listen(23827)) {
            if (mMediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23824)) {
                    // https://stackoverflow.com/questions/9609479/android-mediaplayer-went-away-with-unhandled-events
                    mMediaPlayer.reset();
                }
                if (!ListenerUtil.mutListener.listen(23825)) {
                    mMediaPlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(23826)) {
                    mMediaPlayer = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23830)) {
            if (mAudioManager != null) {
                if (!ListenerUtil.mutListener.listen(23828)) {
                    mAudioManager.abandonAudioFocus(afChangeListener);
                }
                if (!ListenerUtil.mutListener.listen(23829)) {
                    mAudioManager = null;
                }
            }
        }
    }

    /**
     * Stops the playing sounds.
     */
    public void stopSounds() {
        if (!ListenerUtil.mutListener.listen(23833)) {
            if (mMediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23831)) {
                    mMediaPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(23832)) {
                    releaseSound();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23834)) {
            ReadText.stopTts();
        }
    }

    /**
     * @param soundDir -- base path to the media files.
     * @param sound -- path to the sound file from the card content.
     * @return absolute URI to the sound file.
     */
    private static String getSoundPath(String soundDir, String sound) {
        String trimmedSound = sound.trim();
        if (!ListenerUtil.mutListener.listen(23835)) {
            if (hasURIScheme(trimmedSound)) {
                return trimmedSound;
            }
        }
        return soundDir + Uri.encode(StringUtil.trimRight(sound));
    }

    /**
     * @param path -- path to the sound file from the card content.
     * @return true if path is well-formed URI and contains URI scheme.
     */
    private static boolean hasURIScheme(String path) {
        Matcher uriMatcher = sUriPattern.matcher(path.trim());
        return (ListenerUtil.mutListener.listen(23836) ? (uriMatcher.matches() || uriMatcher.group(2) != null) : (uriMatcher.matches() && uriMatcher.group(2) != null));
    }

    /**
     * Set the context for the calling activity (necessary for playing videos)
     */
    public void setContext(WeakReference<Activity> activityRef) {
        if (!ListenerUtil.mutListener.listen(23837)) {
            mCallingActivity = activityRef;
        }
    }

    public OnCompletionListener getMediaCompletionListener() {
        return mPlayAllListener;
    }

    public boolean hasQuestion() {
        return mSoundPaths.containsKey(SoundSide.QUESTION);
    }

    public boolean hasAnswer() {
        return mSoundPaths.containsKey(SoundSide.ANSWER);
    }

    public interface OnErrorListener {

        boolean onError(MediaPlayer mp, int which, int extra, String path);
    }
}
