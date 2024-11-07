/**
 * *************************************************************************************
 *  Copyright (c) 2011 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2014 Bruno Romero de Azevedo <brunodea@inf.ufsm.br>                    *
 *  Copyright (c) 2014–15 Roland Sieker <ospalh@gmail.com>                               *
 *  Copyright (c) 2015 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *  Copyright (c) 2016 Mark Carter <mark@marcardar.com>                                  *
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

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.CheckResult;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.webkit.internal.AssetHelper;
import androidx.webkit.WebViewAssetLoader;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.snackbar.Snackbar;
import com.ichi2.anim.ViewAnimation;
import com.ichi2.anki.cardviewer.GestureTapProcessor;
import com.ichi2.anki.cardviewer.MissingImageHandler;
import com.ichi2.anki.dialogs.TagsDialog;
import com.ichi2.anki.multimediacard.AudioView;
import com.ichi2.anki.cardviewer.CardAppearance;
import com.ichi2.anki.receiver.SdCardReceiver;
import com.ichi2.anki.reviewer.CardMarker;
import com.ichi2.anki.cardviewer.CardTemplate;
import com.ichi2.anki.reviewer.ReviewerCustomFonts;
import com.ichi2.anki.reviewer.ReviewerUi;
import com.ichi2.anki.cardviewer.TypedAnswer;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListener;
import com.ichi2.async.TaskManager;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.sched.AbstractSched;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.Sound;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.template.MathJax;
import com.ichi2.libanki.template.TemplateFilters;
import com.ichi2.themes.HtmlColors;
import com.ichi2.themes.Themes;
import com.ichi2.utils.AdaptionUtil;
import com.ichi2.utils.AndroidUiUtils;
import com.ichi2.utils.ClipboardUtil;
import com.ichi2.utils.BooleanGetter;
import com.ichi2.utils.CardGetter;
import com.ichi2.utils.DiffEngine;
import com.ichi2.utils.FunctionalInterfaces.Consumer;
import com.ichi2.utils.FunctionalInterfaces.Function;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import com.ichi2.utils.WebViewDebugging;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import timber.log.Timber;
import static com.ichi2.anki.cardviewer.CardAppearance.calculateDynamicFontSize;
import static com.ichi2.anki.cardviewer.ViewerCommand.*;
import static com.ichi2.anki.reviewer.CardMarker.*;
import static com.ichi2.libanki.Sound.SoundSide;
import com.github.zafarkhaja.semver.Version;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.FieldDeclarationsShouldBeAtStartOfClass" })
public abstract class AbstractFlashcardViewer extends NavigationDrawerActivity implements ReviewerUi, CommandProcessor {

    /**
     * Result codes that are returned when this activity finishes.
     */
    public static final int RESULT_DEFAULT = 50;

    public static final int RESULT_NO_MORE_CARDS = 52;

    public static final int RESULT_ABORT_AND_SYNC = 53;

    /**
     * Available options performed by other activities.
     */
    public static final int EDIT_CURRENT_CARD = 0;

    public static final int DECK_OPTIONS = 1;

    public static final int EASE_1 = 1;

    public static final int EASE_2 = 2;

    public static final int EASE_3 = 3;

    public static final int EASE_4 = 4;

    /**
     * Maximum time in milliseconds to wait before accepting answer button presses.
     */
    @VisibleForTesting
    protected static final int DOUBLE_TAP_IGNORE_THRESHOLD = 200;

    /**
     * Time to wait in milliseconds before resuming fullscreen mode *
     */
    protected static final int INITIAL_HIDE_DELAY = 200;

    // Type answer patterns
    private static final Pattern sTypeAnsPat = Pattern.compile("\\[\\[type:(.+?)]]");

    /**
     * to be sent to and from the card editor
     */
    private static Card sEditorCard;

    protected static boolean sDisplayAnswer = false;

    private boolean mTtsInitialized = false;

    private boolean mReplayOnTtsInit = false;

    protected static final int MENU_DISABLED = 3;

    // js api developer contact
    private String mCardSuppliedDeveloperContact = "";

    private String mCardSuppliedApiVersion = "";

    private static final String sCurrentJsApiVersion = "0.0.1";

    private static final String sMinimumJsApiVersion = "0.0.1";

    // JS API ERROR CODE
    private static final int ankiJsErrorCodeDefault = 0;

    private static final int ankiJsErrorCodeMarkCard = 1;

    private static final int ankiJsErrorCodeFlagCard = 2;

    /**
     * Broadcast that informs us when the sd card is about to be unmounted
     */
    private BroadcastReceiver mUnmountReceiver = null;

    /**
     * Variables to hold preferences
     */
    private CardAppearance mCardAppearance;

    private boolean mPrefShowTopbar;

    private boolean mShowTimer;

    protected boolean mPrefWhiteboard;

    private int mPrefFullscreenReview;

    private int mRelativeButtonSize;

    private boolean mDoubleScrolling;

    private boolean mScrollingButtons;

    private boolean mGesturesEnabled;

    // Android WebView
    protected boolean mSpeakText;

    protected boolean mDisableClipboard = false;

    protected boolean mOptUseGeneralTimerSettings;

    protected boolean mUseTimer;

    protected int mWaitAnswerSecond;

    protected int mWaitQuestionSecond;

    protected boolean mPrefUseTimer;

    protected boolean mOptUseTimer;

    protected int mOptWaitAnswerSecond;

    protected int mOptWaitQuestionSecond;

    protected boolean mUseInputTag;

    private boolean mDoNotUseCodeFormatting;

    // Default short animation duration, provided by Android framework
    protected int mShortAnimDuration;

    // Preferences from the collection
    private boolean mShowNextReviewTime;

    // Answer card & cloze deletion variables
    private String mTypeCorrect = null;

    // What the learner actually typed
    private String mTypeInput = "";

    // Font face of the compare to field
    private String mTypeFont = "";

    // Its font size
    private int mTypeSize = 0;

    private String mTypeWarning;

    private boolean mIsSelecting = false;

    private boolean mTouchStarted = false;

    private boolean mInAnswer = false;

    private boolean mAnswerSoundsAdded = false;

    private CardTemplate mCardTemplate;

    /**
     * Variables to hold layout objects that we need to update or handle events for
     */
    private View mLookUpIcon;

    private WebView mCardWebView;

    private FrameLayout mCardFrame;

    private FrameLayout mTouchLayer;

    private TextView mChosenAnswer;

    protected TextView mNext1;

    protected TextView mNext2;

    protected TextView mNext3;

    protected TextView mNext4;

    protected EditText mAnswerField;

    protected TextView mEase1;

    protected TextView mEase2;

    protected TextView mEase3;

    protected TextView mEase4;

    protected LinearLayout mFlipCardLayout;

    protected LinearLayout mEaseButtonsLayout;

    protected LinearLayout mEase1Layout;

    protected LinearLayout mEase2Layout;

    protected LinearLayout mEase3Layout;

    protected LinearLayout mEase4Layout;

    protected FrameLayout mPreviewButtonsLayout;

    protected ImageView mPreviewPrevCard;

    protected ImageView mPreviewNextCard;

    protected TextView mPreviewToggleAnswerText;

    protected RelativeLayout mTopBarLayout;

    private Chronometer mCardTimer;

    protected Whiteboard mWhiteboard;

    private android.content.ClipboardManager mClipboard;

    protected Card mCurrentCard;

    private int mCurrentEase;

    private boolean mButtonHeightSet = false;

    private static final int sShowChosenAnswerLength = 2000;

    /**
     * A record of the last time the "show answer" or ease buttons were pressed. We keep track
     * of this time to ignore accidental button presses.
     */
    @VisibleForTesting
    protected long mLastClickTime;

    /**
     * Swipe Detection
     */
    private GestureDetector gestureDetector;

    private MyGestureDetector mGestureDetectorImpl;

    private boolean mLinkOverridesTouchGesture;

    private boolean mIsXScrolling = false;

    private boolean mIsYScrolling = false;

    /**
     * Gesture Allocation
     */
    private int mGestureSwipeUp;

    private int mGestureSwipeDown;

    private int mGestureSwipeLeft;

    private int mGestureSwipeRight;

    private int mGestureDoubleTap;

    private int mGestureLongclick;

    private int mGestureVolumeUp;

    private int mGestureVolumeDown;

    private GestureTapProcessor mGestureTapProcessor = new GestureTapProcessor();

    private String mCardContent;

    private String mBaseUrl;

    private String mViewerUrl;

    private WebViewAssetLoader mAssetLoader;

    private final int mFadeDuration = 300;

    protected AbstractSched mSched;

    private final Sound mSoundPlayer = new Sound();

    /**
     * Time taken o play all medias in mSoundPlayer
     */
    private long mUseTimerDynamicMS;

    /**
     * File of the temporary mic record *
     */
    protected AudioView mMicToolBar;

    protected String mTempAudioPath;

    /**
     * Last card that the WebView Renderer crashed on.
     * If we get 2 crashes on the same card, then we likely have an infinite loop and want to exit gracefully.
     */
    @Nullable
    private Long lastCrashingCardId = null;

    /**
     * Reference to the parent of the cardFrame to allow regeneration of the cardFrame in case of crash
     */
    private ViewGroup mCardFrameParent;

    /**
     * Lock to allow thread-safe regeneration of mCard
     */
    private final ReadWriteLock mCardLock = new ReentrantReadWriteLock();

    /**
     * whether controls are currently blocked, and how long we expect them to be
     */
    private ReviewerUi.ControlBlock mControlBlocked = ControlBlock.SLOW;

    /**
     * Handle Mark/Flag state of cards
     */
    private CardMarker mCardMarker;

    /**
     * Handle providing help for "Image Not Found"
     */
    private static final MissingImageHandler mMissingImageHandler = new MissingImageHandler();

    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (!ListenerUtil.mutListener.listen(4172)) {
                mSoundPlayer.stopSounds();
            }
            if (!ListenerUtil.mutListener.listen(4173)) {
                mSoundPlayer.playSound((String) msg.obj, null, null, getSoundErrorListener());
            }
        }
    };

    private final Handler longClickHandler = new Handler();

    private final Runnable longClickTestRunnable = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(4174)) {
                Timber.i("AbstractFlashcardViewer:: onEmulatedLongClick");
            }
            if (!ListenerUtil.mutListener.listen(4177)) {
                // Show hint about lookup function if dictionary available
                if ((ListenerUtil.mutListener.listen(4175) ? (!mDisableClipboard || Lookup.isAvailable()) : (!mDisableClipboard && Lookup.isAvailable()))) {
                    String lookupHint = getResources().getString(R.string.lookup_hint);
                    if (!ListenerUtil.mutListener.listen(4176)) {
                        UIUtils.showThemedToast(AbstractFlashcardViewer.this, lookupHint, false);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4178)) {
                CompatHelper.getCompat().vibrate(AnkiDroidApp.getInstance().getApplicationContext(), 50);
            }
            if (!ListenerUtil.mutListener.listen(4179)) {
                longClickHandler.postDelayed(startLongClickAction, 300);
            }
        }
    };

    private final Runnable startLongClickAction = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(4180)) {
                executeCommand(mGestureLongclick);
            }
        }
    };

    // Handler for the "show answer" button
    private final View.OnClickListener mFlipCardListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (!ListenerUtil.mutListener.listen(4181)) {
                Timber.i("AbstractFlashcardViewer:: Show answer button pressed");
            }
            if (!ListenerUtil.mutListener.listen(4191)) {
                // Ignore what is most likely an accidental double-tap.
                if ((ListenerUtil.mutListener.listen(4190) ? ((ListenerUtil.mutListener.listen(4185) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4184) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4183) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4182) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) >= DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4189) ? ((ListenerUtil.mutListener.listen(4185) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4184) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4183) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4182) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) <= DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4188) ? ((ListenerUtil.mutListener.listen(4185) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4184) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4183) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4182) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) > DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4187) ? ((ListenerUtil.mutListener.listen(4185) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4184) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4183) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4182) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) != DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4186) ? ((ListenerUtil.mutListener.listen(4185) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4184) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4183) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4182) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) == DOUBLE_TAP_IGNORE_THRESHOLD) : ((ListenerUtil.mutListener.listen(4185) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4184) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4183) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4182) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) < DOUBLE_TAP_IGNORE_THRESHOLD))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4192)) {
                mLastClickTime = SystemClock.elapsedRealtime();
            }
            if (!ListenerUtil.mutListener.listen(4193)) {
                mTimeoutHandler.removeCallbacks(mShowAnswerTask);
            }
            if (!ListenerUtil.mutListener.listen(4194)) {
                displayCardAnswer();
            }
        }
    };

    private final View.OnClickListener mSelectEaseHandler = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (!ListenerUtil.mutListener.listen(4204)) {
                // Ignore what is most likely an accidental double-tap.
                if ((ListenerUtil.mutListener.listen(4203) ? ((ListenerUtil.mutListener.listen(4198) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4197) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4196) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4195) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) >= DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4202) ? ((ListenerUtil.mutListener.listen(4198) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4197) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4196) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4195) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) <= DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4201) ? ((ListenerUtil.mutListener.listen(4198) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4197) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4196) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4195) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) > DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4200) ? ((ListenerUtil.mutListener.listen(4198) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4197) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4196) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4195) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) != DOUBLE_TAP_IGNORE_THRESHOLD) : (ListenerUtil.mutListener.listen(4199) ? ((ListenerUtil.mutListener.listen(4198) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4197) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4196) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4195) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) == DOUBLE_TAP_IGNORE_THRESHOLD) : ((ListenerUtil.mutListener.listen(4198) ? (SystemClock.elapsedRealtime() % mLastClickTime) : (ListenerUtil.mutListener.listen(4197) ? (SystemClock.elapsedRealtime() / mLastClickTime) : (ListenerUtil.mutListener.listen(4196) ? (SystemClock.elapsedRealtime() * mLastClickTime) : (ListenerUtil.mutListener.listen(4195) ? (SystemClock.elapsedRealtime() + mLastClickTime) : (SystemClock.elapsedRealtime() - mLastClickTime))))) < DOUBLE_TAP_IGNORE_THRESHOLD))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4205)) {
                mLastClickTime = SystemClock.elapsedRealtime();
            }
            if (!ListenerUtil.mutListener.listen(4206)) {
                mTimeoutHandler.removeCallbacks(mShowQuestionTask);
            }
            int id = view.getId();
            if (!ListenerUtil.mutListener.listen(4216)) {
                if (id == R.id.flashcard_layout_ease1) {
                    if (!ListenerUtil.mutListener.listen(4214)) {
                        Timber.i("AbstractFlashcardViewer:: EASE_1 pressed");
                    }
                    if (!ListenerUtil.mutListener.listen(4215)) {
                        answerCard(Consts.BUTTON_ONE);
                    }
                } else if (id == R.id.flashcard_layout_ease2) {
                    if (!ListenerUtil.mutListener.listen(4212)) {
                        Timber.i("AbstractFlashcardViewer:: EASE_2 pressed");
                    }
                    if (!ListenerUtil.mutListener.listen(4213)) {
                        answerCard(Consts.BUTTON_TWO);
                    }
                } else if (id == R.id.flashcard_layout_ease3) {
                    if (!ListenerUtil.mutListener.listen(4210)) {
                        Timber.i("AbstractFlashcardViewer:: EASE_3 pressed");
                    }
                    if (!ListenerUtil.mutListener.listen(4211)) {
                        answerCard(Consts.BUTTON_THREE);
                    }
                } else if (id == R.id.flashcard_layout_ease4) {
                    if (!ListenerUtil.mutListener.listen(4208)) {
                        Timber.i("AbstractFlashcardViewer:: EASE_4 pressed");
                    }
                    if (!ListenerUtil.mutListener.listen(4209)) {
                        answerCard(Consts.BUTTON_FOUR);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(4207)) {
                        mCurrentEase = 0;
                    }
                }
            }
        }
    };

    private final View.OnTouchListener mGestureListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!ListenerUtil.mutListener.listen(4217)) {
                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(4226)) {
                if (!mDisableClipboard) {
                    if (!ListenerUtil.mutListener.listen(4225)) {
                        switch(event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                if (!ListenerUtil.mutListener.listen(4218)) {
                                    mTouchStarted = true;
                                }
                                if (!ListenerUtil.mutListener.listen(4219)) {
                                    longClickHandler.postDelayed(longClickTestRunnable, 800);
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_MOVE:
                                if (!ListenerUtil.mutListener.listen(4222)) {
                                    if (mTouchStarted) {
                                        if (!ListenerUtil.mutListener.listen(4220)) {
                                            longClickHandler.removeCallbacks(longClickTestRunnable);
                                        }
                                        if (!ListenerUtil.mutListener.listen(4221)) {
                                            mTouchStarted = false;
                                        }
                                    }
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(4223)) {
                                    longClickHandler.removeCallbacks(longClickTestRunnable);
                                }
                                if (!ListenerUtil.mutListener.listen(4224)) {
                                    mTouchStarted = false;
                                }
                                break;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4227)) {
                if (!mGestureDetectorImpl.eventCanBeSentToWebView(event)) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(4228)) {
                // Gesture listener is added before mCard is set
                processCardAction(cardWebView -> {
                    if (cardWebView == null)
                        return;
                    cardWebView.dispatchTouchEvent(event);
                });
            }
            return false;
        }
    };

    @SuppressLint("CheckResult")
    // This is intentionally package-private as it removes the need for synthetic accessors
    void processCardAction(Consumer<WebView> cardConsumer) {
        if (!ListenerUtil.mutListener.listen(4229)) {
            processCardFunction(cardWebView -> {
                cardConsumer.consume(cardWebView);
                return true;
            });
        }
    }

    @CheckResult
    private <T> T processCardFunction(Function<WebView, T> cardFunction) {
        Lock readLock = mCardLock.readLock();
        try {
            if (!ListenerUtil.mutListener.listen(4231)) {
                readLock.lock();
            }
            return cardFunction.apply(mCardWebView);
        } finally {
            if (!ListenerUtil.mutListener.listen(4230)) {
                readLock.unlock();
            }
        }
    }

    protected final NextCardHandler<BooleanGetter> mDismissCardHandler = new NextCardHandler() {
    };

    private final TaskListener<CardGetter, BooleanGetter> mUpdateCardHandler = new TaskListener<CardGetter, BooleanGetter>() {

        private boolean mNoMoreCards;

        @Override
        public void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(4232)) {
                showProgressBar();
            }
        }

        @Override
        public void onProgressUpdate(CardGetter cardGetter) {
            Card card = cardGetter.getCard();
            if (!ListenerUtil.mutListener.listen(4234)) {
                if (mCurrentCard != card) {
                    if (!ListenerUtil.mutListener.listen(4233)) {
                        /*
                 * Before updating mCurrentCard, we check whether it is changing or not. If the current card changes,
                 * then we need to display it as a new card, without showing the answer.
                 */
                        sDisplayAnswer = false;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4235)) {
                mCurrentCard = card;
            }
            if (!ListenerUtil.mutListener.listen(4236)) {
                // Tasks should always be launched from GUI. So in
                TaskManager.launchCollectionTask(new CollectionTask.PreloadNextCard());
            }
            if (!ListenerUtil.mutListener.listen(4239)) {
                // listener and not in background
                if (mCurrentCard == null) {
                    if (!ListenerUtil.mutListener.listen(4237)) {
                        // If the card is null means that there are no more cards scheduled for review.
                        mNoMoreCards = true;
                    }
                    if (!ListenerUtil.mutListener.listen(4238)) {
                        showProgressBar();
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4242)) {
                if ((ListenerUtil.mutListener.listen(4240) ? (mPrefWhiteboard || mWhiteboard != null) : (mPrefWhiteboard && mWhiteboard != null))) {
                    if (!ListenerUtil.mutListener.listen(4241)) {
                        mWhiteboard.clear();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4243)) {
                updateTypeAnswerInfo();
            }
            if (!ListenerUtil.mutListener.listen(4251)) {
                if (sDisplayAnswer) {
                    if (!ListenerUtil.mutListener.listen(4247)) {
                        // load sounds from scratch, to expose any edit changes
                        mSoundPlayer.resetSounds();
                    }
                    if (!ListenerUtil.mutListener.listen(4248)) {
                        // causes answer sounds to be reloaded
                        mAnswerSoundsAdded = false;
                    }
                    if (!ListenerUtil.mutListener.listen(4249)) {
                        // questions must be intentionally regenerated
                        generateQuestionSoundList();
                    }
                    if (!ListenerUtil.mutListener.listen(4250)) {
                        displayCardAnswer();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(4244)) {
                        displayCardQuestion();
                    }
                    if (!ListenerUtil.mutListener.listen(4245)) {
                        mCurrentCard.startTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(4246)) {
                        initTimer();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4252)) {
                hideProgressBar();
            }
        }

        @Override
        public void onPostExecute(BooleanGetter result) {
            if (!ListenerUtil.mutListener.listen(4254)) {
                if (!result.getBoolean()) {
                    if (!ListenerUtil.mutListener.listen(4253)) {
                        // RuntimeException occurred on update cards
                        closeReviewer(DeckPicker.RESULT_DB_ERROR, false);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4256)) {
                if (mNoMoreCards) {
                    if (!ListenerUtil.mutListener.listen(4255)) {
                        closeReviewer(RESULT_NO_MORE_CARDS, true);
                    }
                }
            }
        }
    };

    abstract class NextCardHandler<Result extends BooleanGetter> extends TaskListener<Card, Result> {

        private boolean mNoMoreCards;

        @Override
        public void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(4257)) {
                dealWithTimeBox();
            }
        }

        @Override
        public void onProgressUpdate(Card card) {
            if (!ListenerUtil.mutListener.listen(4258)) {
                displayNext(card);
            }
        }

        protected void displayNext(Card nextCard) {
            Resources res = getResources();
            if (!ListenerUtil.mutListener.listen(4260)) {
                if (mSched == null) {
                    if (!ListenerUtil.mutListener.listen(4259)) {
                        // TODO: proper testing for restored activity
                        finishWithoutAnimation();
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4261)) {
                mCurrentCard = nextCard;
            }
            if (!ListenerUtil.mutListener.listen(4268)) {
                if (mCurrentCard == null) {
                    if (!ListenerUtil.mutListener.listen(4267)) {
                        // other handlers use this, toggle state every time through
                        mNoMoreCards = true;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(4262)) {
                        // other handlers use this, toggle state every time through
                        mNoMoreCards = false;
                    }
                    if (!ListenerUtil.mutListener.listen(4263)) {
                        // Start reviewing next card
                        updateTypeAnswerInfo();
                    }
                    if (!ListenerUtil.mutListener.listen(4264)) {
                        hideProgressBar();
                    }
                    if (!ListenerUtil.mutListener.listen(4265)) {
                        AbstractFlashcardViewer.this.unblockControls();
                    }
                    if (!ListenerUtil.mutListener.listen(4266)) {
                        AbstractFlashcardViewer.this.displayCardQuestion();
                    }
                }
            }
        }

        private void dealWithTimeBox() {
            Resources res = getResources();
            Pair<Integer, Integer> elapsed = getCol().timeboxReached();
            if (!ListenerUtil.mutListener.listen(4275)) {
                if (elapsed != null) {
                    int nCards = elapsed.second;
                    int nMins = (ListenerUtil.mutListener.listen(4272) ? (elapsed.first % 60) : (ListenerUtil.mutListener.listen(4271) ? (elapsed.first * 60) : (ListenerUtil.mutListener.listen(4270) ? (elapsed.first - 60) : (ListenerUtil.mutListener.listen(4269) ? (elapsed.first + 60) : (elapsed.first / 60)))));
                    String mins = res.getQuantityString(R.plurals.in_minutes, nMins, nMins);
                    String timeboxMessage = res.getQuantityString(R.plurals.timebox_reached, nCards, nCards, mins);
                    if (!ListenerUtil.mutListener.listen(4273)) {
                        UIUtils.showThemedToast(AbstractFlashcardViewer.this, timeboxMessage, true);
                    }
                    if (!ListenerUtil.mutListener.listen(4274)) {
                        getCol().startTimebox();
                    }
                }
            }
        }

        @Override
        public void onPostExecute(Result result) {
            if (!ListenerUtil.mutListener.listen(4276)) {
                postNextCardDisplay(result.getBoolean());
            }
        }

        protected void postNextCardDisplay(boolean displaySuccess) {
            if (!ListenerUtil.mutListener.listen(4278)) {
                if (!displaySuccess) {
                    if (!ListenerUtil.mutListener.listen(4277)) {
                        // RuntimeException occurred on answering cards
                        closeReviewer(DeckPicker.RESULT_DB_ERROR, false);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4280)) {
                // precedence when returning to study options.
                if (mNoMoreCards) {
                    if (!ListenerUtil.mutListener.listen(4279)) {
                        closeReviewer(RESULT_NO_MORE_CARDS, true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4281)) {
                // set the correct mark/unmark icon on action bar
                refreshActionBar();
            }
            if (!ListenerUtil.mutListener.listen(4282)) {
                focusDefaultLayout();
            }
        }
    }

    private void focusDefaultLayout() {
        if (!ListenerUtil.mutListener.listen(4287)) {
            if (!AndroidUiUtils.isRunningOnTv(this)) {
                if (!ListenerUtil.mutListener.listen(4286)) {
                    findViewById(R.id.root_layout).requestFocus();
                }
            } else {
                View flip = findViewById(R.id.answer_options_layout);
                if (!ListenerUtil.mutListener.listen(4283)) {
                    if (flip == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(4284)) {
                    Timber.d("Requesting focus for flip button");
                }
                if (!ListenerUtil.mutListener.listen(4285)) {
                    flip.requestFocus();
                }
            }
        }
    }

    protected NextCardHandler<BooleanGetter> mAnswerCardHandler(boolean quick) {
        return new NextCardHandler() {

            @Override
            public void onPreExecute() {
                if (!ListenerUtil.mutListener.listen(4288)) {
                    super.onPreExecute();
                }
                if (!ListenerUtil.mutListener.listen(4289)) {
                    blockControls(quick);
                }
            }
        };
    }

    /**
     * Extract type answer/cloze text and font/size
     */
    private void updateTypeAnswerInfo() {
        if (!ListenerUtil.mutListener.listen(4290)) {
            mTypeCorrect = null;
        }
        if (!ListenerUtil.mutListener.listen(4291)) {
            mTypeInput = "";
        }
        String q = mCurrentCard.q(false);
        Matcher m = sTypeAnsPat.matcher(q);
        int clozeIdx = 0;
        if (!ListenerUtil.mutListener.listen(4292)) {
            if (!m.find()) {
                return;
            }
        }
        String fldTag = m.group(1);
        if (!ListenerUtil.mutListener.listen(4299)) {
            // if it's a cloze, extract data
            if (fldTag.startsWith("cloze:")) {
                if (!ListenerUtil.mutListener.listen(4297)) {
                    // get field and cloze position
                    clozeIdx = (ListenerUtil.mutListener.listen(4296) ? (mCurrentCard.getOrd() % 1) : (ListenerUtil.mutListener.listen(4295) ? (mCurrentCard.getOrd() / 1) : (ListenerUtil.mutListener.listen(4294) ? (mCurrentCard.getOrd() * 1) : (ListenerUtil.mutListener.listen(4293) ? (mCurrentCard.getOrd() - 1) : (mCurrentCard.getOrd() + 1)))));
                }
                if (!ListenerUtil.mutListener.listen(4298)) {
                    fldTag = fldTag.split(":")[1];
                }
            }
        }
        // loop through fields for a match
        JSONArray flds = mCurrentCard.model().getJSONArray("flds");
        if (!ListenerUtil.mutListener.listen(4311)) {
            {
                long _loopCounter99 = 0;
                for (JSONObject fld : flds.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter99", ++_loopCounter99);
                    String name = fld.getString("name");
                    if (!ListenerUtil.mutListener.listen(4310)) {
                        if (name.equals(fldTag)) {
                            if (!ListenerUtil.mutListener.listen(4300)) {
                                mTypeCorrect = mCurrentCard.note().getItem(name);
                            }
                            if (!ListenerUtil.mutListener.listen(4307)) {
                                if ((ListenerUtil.mutListener.listen(4305) ? (clozeIdx >= 0) : (ListenerUtil.mutListener.listen(4304) ? (clozeIdx <= 0) : (ListenerUtil.mutListener.listen(4303) ? (clozeIdx > 0) : (ListenerUtil.mutListener.listen(4302) ? (clozeIdx < 0) : (ListenerUtil.mutListener.listen(4301) ? (clozeIdx == 0) : (clozeIdx != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(4306)) {
                                        // narrow to cloze
                                        mTypeCorrect = contentForCloze(mTypeCorrect, clozeIdx);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4308)) {
                                mTypeFont = fld.getString("font");
                            }
                            if (!ListenerUtil.mutListener.listen(4309)) {
                                mTypeSize = fld.getInt("size");
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4322)) {
            if (mTypeCorrect == null) {
                if (!ListenerUtil.mutListener.listen(4321)) {
                    if ((ListenerUtil.mutListener.listen(4318) ? (clozeIdx >= 0) : (ListenerUtil.mutListener.listen(4317) ? (clozeIdx <= 0) : (ListenerUtil.mutListener.listen(4316) ? (clozeIdx > 0) : (ListenerUtil.mutListener.listen(4315) ? (clozeIdx < 0) : (ListenerUtil.mutListener.listen(4314) ? (clozeIdx == 0) : (clozeIdx != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(4320)) {
                            mTypeWarning = getResources().getString(R.string.empty_card_warning);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4319)) {
                            mTypeWarning = getResources().getString(R.string.unknown_type_field_warning, fldTag);
                        }
                    }
                }
            } else if ("".equals(mTypeCorrect)) {
                if (!ListenerUtil.mutListener.listen(4313)) {
                    mTypeCorrect = null;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4312)) {
                    mTypeWarning = null;
                }
            }
        }
    }

    /**
     * Format question field when it contains typeAnswer or clozes. If there was an error during type text extraction, a
     * warning is displayed
     *
     * @param buf The question text
     * @return The formatted question text
     */
    private String typeAnsQuestionFilter(String buf) {
        Matcher m = sTypeAnsPat.matcher(buf);
        if (!ListenerUtil.mutListener.listen(4323)) {
            if (mTypeWarning != null) {
                return m.replaceFirst(mTypeWarning);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(4339)) {
            if (mUseInputTag) {
                if (!ListenerUtil.mutListener.listen(4328)) {
                    // shouldOverrideUrlLoading() in createWebView() in this file.
                    sb.append("<center>\n<input type=\"text\" name=\"typed\" id=\"typeans\" onfocus=\"taFocus();\" " + "onblur=\"taBlur(this);\" onKeyPress=\"return taKey(this, event)\" autocomplete=\"off\" ");
                }
                if (!ListenerUtil.mutListener.listen(4337)) {
                    // desktop just doesn’t show the input tag there. Do it with standard values here instead.)
                    if ((ListenerUtil.mutListener.listen(4335) ? ((ListenerUtil.mutListener.listen(4329) ? (mTypeFont != null || !TextUtils.isEmpty(mTypeFont)) : (mTypeFont != null && !TextUtils.isEmpty(mTypeFont))) || (ListenerUtil.mutListener.listen(4334) ? (mTypeSize >= 0) : (ListenerUtil.mutListener.listen(4333) ? (mTypeSize <= 0) : (ListenerUtil.mutListener.listen(4332) ? (mTypeSize < 0) : (ListenerUtil.mutListener.listen(4331) ? (mTypeSize != 0) : (ListenerUtil.mutListener.listen(4330) ? (mTypeSize == 0) : (mTypeSize > 0))))))) : ((ListenerUtil.mutListener.listen(4329) ? (mTypeFont != null || !TextUtils.isEmpty(mTypeFont)) : (mTypeFont != null && !TextUtils.isEmpty(mTypeFont))) && (ListenerUtil.mutListener.listen(4334) ? (mTypeSize >= 0) : (ListenerUtil.mutListener.listen(4333) ? (mTypeSize <= 0) : (ListenerUtil.mutListener.listen(4332) ? (mTypeSize < 0) : (ListenerUtil.mutListener.listen(4331) ? (mTypeSize != 0) : (ListenerUtil.mutListener.listen(4330) ? (mTypeSize == 0) : (mTypeSize > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(4336)) {
                            sb.append("style=\"font-family: '").append(mTypeFont).append("'; font-size: ").append(mTypeSize).append("px;\" ");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4338)) {
                    sb.append(">\n</center>\n");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4324)) {
                    sb.append("<span id=\"typeans\" class=\"typePrompt");
                }
                if (!ListenerUtil.mutListener.listen(4326)) {
                    if (mUseInputTag) {
                        if (!ListenerUtil.mutListener.listen(4325)) {
                            sb.append(" typeOff");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4327)) {
                    sb.append("\">........</span>");
                }
            }
        }
        return m.replaceAll(sb.toString());
    }

    /**
     * Fill the placeholder for the type comparison. Show the correct answer, and the comparison if appropriate.
     *
     * @param buf The answer text
     * @param userAnswer Text typed by the user, or empty.
     * @param correctAnswer The correct answer, taken from the note.
     * @return The formatted answer text
     */
    @VisibleForTesting
    String typeAnsAnswerFilter(String buf, String userAnswer, String correctAnswer) {
        Matcher m = sTypeAnsPat.matcher(buf);
        DiffEngine diffEngine = new DiffEngine();
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(4340)) {
            sb.append(mDoNotUseCodeFormatting ? "<div><span id=\"typeans\">" : "<div><code id=\"typeans\">");
        }
        if (!ListenerUtil.mutListener.listen(4350)) {
            if (!TextUtils.isEmpty(userAnswer)) {
                if (!ListenerUtil.mutListener.listen(4349)) {
                    // The user did type something.
                    if (userAnswer.equals(correctAnswer)) {
                        if (!ListenerUtil.mutListener.listen(4347)) {
                            // and it was right.
                            sb.append(Matcher.quoteReplacement(DiffEngine.wrapGood(correctAnswer)));
                        }
                        if (!ListenerUtil.mutListener.listen(4348)) {
                            // Heavy check mark
                            sb.append("<span id=\"typecheckmark\">\u2714</span>");
                        }
                    } else {
                        // exactly the same as the correct text.
                        String[] diffedStrings = diffEngine.diffedHtmlStrings(correctAnswer, userAnswer);
                        if (!ListenerUtil.mutListener.listen(4344)) {
                            // We know we get back two strings.
                            sb.append(Matcher.quoteReplacement(diffedStrings[0]));
                        }
                        if (!ListenerUtil.mutListener.listen(4345)) {
                            sb.append("<br><span id=\"typearrow\">&darr;</span><br>");
                        }
                        if (!ListenerUtil.mutListener.listen(4346)) {
                            sb.append(Matcher.quoteReplacement(diffedStrings[1]));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4343)) {
                    if (!mUseInputTag) {
                        if (!ListenerUtil.mutListener.listen(4342)) {
                            sb.append(Matcher.quoteReplacement(DiffEngine.wrapMissing(correctAnswer)));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4341)) {
                            sb.append(Matcher.quoteReplacement(correctAnswer));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4351)) {
            sb.append(mDoNotUseCodeFormatting ? "</span></div>" : "</code></div>");
        }
        return m.replaceAll(sb.toString());
    }

    private String contentForCloze(String txt, int idx) {
        // In Android, } should be escaped
        @SuppressWarnings("RegExpRedundantEscape")
        Pattern re = Pattern.compile("\\{\\{c" + idx + "::(.+?)\\}\\}");
        Matcher m = re.matcher(txt);
        // Size can't be known in advance
        Set<String> matches = new LinkedHashSet<>();
        // they appear in.
        String groupOne;
        int colonColonIndex = -1;
        {
            long _loopCounter100 = 0;
            while (m.find()) {
                ListenerUtil.loopListener.listen("_loopCounter100", ++_loopCounter100);
                groupOne = m.group(1);
                if (!ListenerUtil.mutListener.listen(4352)) {
                    colonColonIndex = groupOne.indexOf("::");
                }
                if ((ListenerUtil.mutListener.listen(4357) ? (colonColonIndex >= -1) : (ListenerUtil.mutListener.listen(4356) ? (colonColonIndex <= -1) : (ListenerUtil.mutListener.listen(4355) ? (colonColonIndex < -1) : (ListenerUtil.mutListener.listen(4354) ? (colonColonIndex != -1) : (ListenerUtil.mutListener.listen(4353) ? (colonColonIndex == -1) : (colonColonIndex > -1))))))) {
                    // Cut out the hint.
                    groupOne = groupOne.substring(0, colonColonIndex);
                }
                if (!ListenerUtil.mutListener.listen(4358)) {
                    matches.add(groupOne);
                }
            }
        }
        // Now do what the pythonic ", ".join(matches) does in a tricky way
        String prefix = "";
        StringBuilder resultBuilder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(4362)) {
            {
                long _loopCounter101 = 0;
                for (String match : matches) {
                    ListenerUtil.loopListener.listen("_loopCounter101", ++_loopCounter101);
                    if (!ListenerUtil.mutListener.listen(4359)) {
                        resultBuilder.append(prefix);
                    }
                    if (!ListenerUtil.mutListener.listen(4360)) {
                        resultBuilder.append(match);
                    }
                    if (!ListenerUtil.mutListener.listen(4361)) {
                        prefix = ", ";
                    }
                }
            }
        }
        return resultBuilder.toString();
    }

    private final Handler mTimerHandler = new Handler();

    private final Runnable removeChosenAnswerText = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(4363)) {
                mChosenAnswer.setText("");
            }
        }
    };

    protected int mPrefWaitAnswerSecond;

    protected int mPrefWaitQuestionSecond;

    protected int getAnswerButtonCount() {
        return getCol().getSched().answerButtons(mCurrentCard);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4364)) {
            Timber.d("onCreate()");
        }
        SharedPreferences preferences = restorePreferences();
        if (!ListenerUtil.mutListener.listen(4365)) {
            mCardAppearance = CardAppearance.create(new ReviewerCustomFonts(this.getBaseContext()), preferences);
        }
        if (!ListenerUtil.mutListener.listen(4366)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4367)) {
            setContentView(getContentViewAttr(mPrefFullscreenReview));
        }
        if (!ListenerUtil.mutListener.listen(4368)) {
            // Make ACTION_PROCESS_TEXT for in-app searching possible on > Android 4.0
            getDelegate().setHandleNativeActionModesEnabled(true);
        }
        View mainView = findViewById(android.R.id.content);
        if (!ListenerUtil.mutListener.listen(4369)) {
            initNavigationDrawer(mainView);
        }
        if (!ListenerUtil.mutListener.listen(4370)) {
            mShortAnimDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        }
    }

    protected int getContentViewAttr(int fullscreenMode) {
        return R.layout.reviewer;
    }

    protected boolean isFullscreen() {
        return !getSupportActionBar().isShowing();
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        if (!ListenerUtil.mutListener.listen(4371)) {
            // called when screen rotated, etc, since recreating the Webview is too expensive
            super.onConfigurationChanged(config);
        }
        if (!ListenerUtil.mutListener.listen(4372)) {
            refreshActionBar();
        }
    }

    protected abstract void setTitle();

    // Finish initializing the activity after the collection has been correctly loaded
    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(4373)) {
            super.onCollectionLoaded(col);
        }
        if (!ListenerUtil.mutListener.listen(4374)) {
            mSched = col.getSched();
        }
        String mediaDir = col.getMedia().dir();
        if (!ListenerUtil.mutListener.listen(4375)) {
            mBaseUrl = Utils.getBaseUrl(mediaDir);
        }
        if (!ListenerUtil.mutListener.listen(4376)) {
            mViewerUrl = mBaseUrl + "__viewer__.html";
        }
        if (!ListenerUtil.mutListener.listen(4380)) {
            mAssetLoader = new WebViewAssetLoader.Builder().addPathHandler("/", new WebViewAssetLoader.PathHandler() {

                @Override
                public WebResourceResponse handle(String path) {
                    try {
                        File file = new File(mediaDir, path);
                        FileInputStream is = new FileInputStream(file);
                        String mimeType = AssetHelper.guessMimeType(path);
                        HashMap<String, String> headers = new HashMap<String, String>();
                        if (!ListenerUtil.mutListener.listen(4378)) {
                            headers.put("Access-Control-Allow-Origin", "*");
                        }
                        WebResourceResponse response = new WebResourceResponse(mimeType, null, is);
                        if (!ListenerUtil.mutListener.listen(4379)) {
                            response.setResponseHeaders(headers);
                        }
                        return response;
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(4377)) {
                            Timber.e(e, "Error trying to open path in asset loader");
                        }
                    }
                    return null;
                }
            }).build();
        }
        if (!ListenerUtil.mutListener.listen(4381)) {
            registerExternalStorageListener();
        }
        if (!ListenerUtil.mutListener.listen(4382)) {
            restoreCollectionPreferences();
        }
        if (!ListenerUtil.mutListener.listen(4383)) {
            initLayout();
        }
        if (!ListenerUtil.mutListener.listen(4384)) {
            setTitle();
        }
        if (!ListenerUtil.mutListener.listen(4386)) {
            if (!mDisableClipboard) {
                if (!ListenerUtil.mutListener.listen(4385)) {
                    clearClipboard();
                }
            }
        }
        // Load the template for the card
        try {
            String data = Utils.convertStreamToString(getAssets().open("card_template.html"));
            if (!ListenerUtil.mutListener.listen(4388)) {
                mCardTemplate = new CardTemplate(data);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(4387)) {
                e.printStackTrace();
            }
        }
        if (!ListenerUtil.mutListener.listen(4390)) {
            // Initialize text-to-speech. This is an asynchronous operation.
            if (mSpeakText) {
                if (!ListenerUtil.mutListener.listen(4389)) {
                    ReadText.initializeTts(this, new ReadTextListener());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4391)) {
            // Initialize dictionary lookup feature
            Lookup.initialize(this);
        }
        if (!ListenerUtil.mutListener.listen(4392)) {
            updateActionBar();
        }
        if (!ListenerUtil.mutListener.listen(4393)) {
            supportInvalidateOptionsMenu();
        }
    }

    // Saves deck each time Reviewer activity loses focus
    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(4394)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(4395)) {
            Timber.d("onPause()");
        }
        if (!ListenerUtil.mutListener.listen(4396)) {
            mTimeoutHandler.removeCallbacks(mShowAnswerTask);
        }
        if (!ListenerUtil.mutListener.listen(4397)) {
            mTimeoutHandler.removeCallbacks(mShowQuestionTask);
        }
        if (!ListenerUtil.mutListener.listen(4398)) {
            longClickHandler.removeCallbacks(longClickTestRunnable);
        }
        if (!ListenerUtil.mutListener.listen(4399)) {
            longClickHandler.removeCallbacks(startLongClickAction);
        }
        if (!ListenerUtil.mutListener.listen(4400)) {
            pauseTimer();
        }
        if (!ListenerUtil.mutListener.listen(4401)) {
            mSoundPlayer.stopSounds();
        }
        if (!ListenerUtil.mutListener.listen(4402)) {
            // Prevent loss of data in Cookies
            CookieManager.getInstance().flush();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(4403)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(4404)) {
            resumeTimer();
        }
        if (!ListenerUtil.mutListener.listen(4405)) {
            // Set the context for the Sound manager
            mSoundPlayer.setContext(new WeakReference<>(this));
        }
        if (!ListenerUtil.mutListener.listen(4406)) {
            // Reset the activity title
            setTitle();
        }
        if (!ListenerUtil.mutListener.listen(4407)) {
            updateActionBar();
        }
        if (!ListenerUtil.mutListener.listen(4408)) {
            selectNavigationItem(-1);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4409)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(4411)) {
            // not a valid id.
            if (mSched != null) {
                if (!ListenerUtil.mutListener.listen(4410)) {
                    mSched.discardCurrentCard();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4412)) {
            Timber.d("onDestroy()");
        }
        if (!ListenerUtil.mutListener.listen(4414)) {
            if (mSpeakText) {
                if (!ListenerUtil.mutListener.listen(4413)) {
                    ReadText.releaseTts();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4416)) {
            if (mUnmountReceiver != null) {
                if (!ListenerUtil.mutListener.listen(4415)) {
                    unregisterReceiver(mUnmountReceiver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4418)) {
            // http://developer.android.com/reference/android/webkit/WebView.html#destroy()
            if (mCardFrame != null) {
                if (!ListenerUtil.mutListener.listen(4417)) {
                    mCardFrame.removeAllViews();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4419)) {
            // OK to do without a lock
            destroyWebView(mCardWebView);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(4423)) {
            if (isDrawerOpen()) {
                if (!ListenerUtil.mutListener.listen(4422)) {
                    super.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4420)) {
                    Timber.i("Back key pressed");
                }
                if (!ListenerUtil.mutListener.listen(4421)) {
                    closeReviewer(RESULT_DEFAULT, false);
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(4424)) {
            if (processCardFunction(cardWebView -> processHardwareButtonScroll(keyCode, cardWebView))) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    @Nullable
    protected Long getCurrentCardId() {
        if (!ListenerUtil.mutListener.listen(4425)) {
            if (mCurrentCard == null) {
                return null;
            }
        }
        return mCurrentCard.getId();
    }

    private boolean processHardwareButtonScroll(int keyCode, WebView card) {
        if (!ListenerUtil.mutListener.listen(4429)) {
            if (keyCode == KeyEvent.KEYCODE_PAGE_UP) {
                if (!ListenerUtil.mutListener.listen(4426)) {
                    card.pageUp(false);
                }
                if (!ListenerUtil.mutListener.listen(4428)) {
                    if (mDoubleScrolling) {
                        if (!ListenerUtil.mutListener.listen(4427)) {
                            card.pageUp(false);
                        }
                    }
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(4433)) {
            if (keyCode == KeyEvent.KEYCODE_PAGE_DOWN) {
                if (!ListenerUtil.mutListener.listen(4430)) {
                    card.pageDown(false);
                }
                if (!ListenerUtil.mutListener.listen(4432)) {
                    if (mDoubleScrolling) {
                        if (!ListenerUtil.mutListener.listen(4431)) {
                            card.pageDown(false);
                        }
                    }
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(4438)) {
            if ((ListenerUtil.mutListener.listen(4434) ? (mScrollingButtons || keyCode == KeyEvent.KEYCODE_PICTSYMBOLS) : (mScrollingButtons && keyCode == KeyEvent.KEYCODE_PICTSYMBOLS))) {
                if (!ListenerUtil.mutListener.listen(4435)) {
                    card.pageUp(false);
                }
                if (!ListenerUtil.mutListener.listen(4437)) {
                    if (mDoubleScrolling) {
                        if (!ListenerUtil.mutListener.listen(4436)) {
                            card.pageUp(false);
                        }
                    }
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(4443)) {
            if ((ListenerUtil.mutListener.listen(4439) ? (mScrollingButtons || keyCode == KeyEvent.KEYCODE_SWITCH_CHARSET) : (mScrollingButtons && keyCode == KeyEvent.KEYCODE_SWITCH_CHARSET))) {
                if (!ListenerUtil.mutListener.listen(4440)) {
                    card.pageDown(false);
                }
                if (!ListenerUtil.mutListener.listen(4442)) {
                    if (mDoubleScrolling) {
                        if (!ListenerUtil.mutListener.listen(4441)) {
                            card.pageDown(false);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(4444)) {
            if (answerFieldIsFocused()) {
                return super.onKeyUp(keyCode, event);
            }
        }
        if (!ListenerUtil.mutListener.listen(4449)) {
            if (!sDisplayAnswer) {
                if (!ListenerUtil.mutListener.listen(4448)) {
                    if ((ListenerUtil.mutListener.listen(4446) ? ((ListenerUtil.mutListener.listen(4445) ? (keyCode == KeyEvent.KEYCODE_SPACE && keyCode == KeyEvent.KEYCODE_ENTER) : (keyCode == KeyEvent.KEYCODE_SPACE || keyCode == KeyEvent.KEYCODE_ENTER)) && keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) : ((ListenerUtil.mutListener.listen(4445) ? (keyCode == KeyEvent.KEYCODE_SPACE && keyCode == KeyEvent.KEYCODE_ENTER) : (keyCode == KeyEvent.KEYCODE_SPACE || keyCode == KeyEvent.KEYCODE_ENTER)) || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER))) {
                        if (!ListenerUtil.mutListener.listen(4447)) {
                            displayCardAnswer();
                        }
                        return true;
                    }
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    protected boolean answerFieldIsFocused() {
        return (ListenerUtil.mutListener.listen(4450) ? (mAnswerField != null || mAnswerField.isFocused()) : (mAnswerField != null && mAnswerField.isFocused()));
    }

    protected boolean clipboardHasText() {
        return !TextUtils.isEmpty(ClipboardUtil.getText(mClipboard));
    }

    /**
     * We use the clipboard here for the lookup dictionary functionality
     * If the clipboard has data and we're using the functionality, then
     */
    private void clearClipboard() {
        if (!ListenerUtil.mutListener.listen(4451)) {
            if (mClipboard == null) {
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(4463)) {
                if ((ListenerUtil.mutListener.listen(4458) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(4457) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(4456) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(4455) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(4454) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) {
                    if (!ListenerUtil.mutListener.listen(4462)) {
                        mClipboard.clearPrimaryClip();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(4459)) {
                        if (!mClipboard.hasPrimaryClip()) {
                            return;
                        }
                    }
                    CharSequence descriptionLabel = ClipboardUtil.getDescriptionLabel(mClipboard.getPrimaryClip());
                    if (!ListenerUtil.mutListener.listen(4461)) {
                        if (!"Cleared".contentEquals(descriptionLabel)) {
                            if (!ListenerUtil.mutListener.listen(4460)) {
                                mClipboard.setPrimaryClip(ClipData.newPlainText("Cleared", ""));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(4452)) {
                // must disable it or AnkiDroid will crash if it tries to use it.
                Timber.e("Clipboard error. Disabling text selection setting.");
            }
            if (!ListenerUtil.mutListener.listen(4453)) {
                mDisableClipboard = true;
            }
        }
    }

    /**
     * Returns the text stored in the clipboard or the empty string if the clipboard is empty or contains something that
     * cannot be convered to text.
     *
     * @return the text in clipboard or the empty string.
     */
    private CharSequence clipboardGetText() {
        CharSequence text = ClipboardUtil.getText(mClipboard);
        return text != null ? text : "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(4464)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(4471)) {
            if ((ListenerUtil.mutListener.listen(4469) ? (resultCode >= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(4468) ? (resultCode <= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(4467) ? (resultCode > DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(4466) ? (resultCode < DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(4465) ? (resultCode != DeckPicker.RESULT_DB_ERROR) : (resultCode == DeckPicker.RESULT_DB_ERROR))))))) {
                if (!ListenerUtil.mutListener.listen(4470)) {
                    closeReviewer(DeckPicker.RESULT_DB_ERROR, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4478)) {
            if ((ListenerUtil.mutListener.listen(4476) ? (resultCode >= DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(4475) ? (resultCode <= DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(4474) ? (resultCode > DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(4473) ? (resultCode < DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(4472) ? (resultCode != DeckPicker.RESULT_MEDIA_EJECTED) : (resultCode == DeckPicker.RESULT_MEDIA_EJECTED))))))) {
                if (!ListenerUtil.mutListener.listen(4477)) {
                    finishNoStorageAvailable();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4481)) {
            /* Reset the schedule and reload the latest card off the top of the stack if required.
           The card could have been rescheduled, the deck could have changed, or a change of
           note type could have lead to the card being deleted */
            if ((ListenerUtil.mutListener.listen(4479) ? (data != null || data.hasExtra("reloadRequired")) : (data != null && data.hasExtra("reloadRequired")))) {
                if (!ListenerUtil.mutListener.listen(4480)) {
                    performReload();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4501)) {
            if ((ListenerUtil.mutListener.listen(4486) ? (requestCode >= EDIT_CURRENT_CARD) : (ListenerUtil.mutListener.listen(4485) ? (requestCode <= EDIT_CURRENT_CARD) : (ListenerUtil.mutListener.listen(4484) ? (requestCode > EDIT_CURRENT_CARD) : (ListenerUtil.mutListener.listen(4483) ? (requestCode < EDIT_CURRENT_CARD) : (ListenerUtil.mutListener.listen(4482) ? (requestCode != EDIT_CURRENT_CARD) : (requestCode == EDIT_CURRENT_CARD))))))) {
                if (!ListenerUtil.mutListener.listen(4500)) {
                    if (resultCode == RESULT_OK) {
                        if (!ListenerUtil.mutListener.listen(4497)) {
                            // content of note was changed so update the note and current card
                            Timber.i("AbstractFlashcardViewer:: Saving card...");
                        }
                        if (!ListenerUtil.mutListener.listen(4498)) {
                            TaskManager.launchCollectionTask(new CollectionTask.UpdateNote(sEditorCard, true, canAccessScheduler()), mUpdateCardHandler);
                        }
                        if (!ListenerUtil.mutListener.listen(4499)) {
                            onEditedNoteChanged();
                        }
                    } else if ((ListenerUtil.mutListener.listen(4495) ? (resultCode == RESULT_CANCELED || !((ListenerUtil.mutListener.listen(4494) ? (data != null || data.hasExtra("reloadRequired")) : (data != null && data.hasExtra("reloadRequired"))))) : (resultCode == RESULT_CANCELED && !((ListenerUtil.mutListener.listen(4494) ? (data != null || data.hasExtra("reloadRequired")) : (data != null && data.hasExtra("reloadRequired"))))))) {
                        if (!ListenerUtil.mutListener.listen(4496)) {
                            // nothing was changed by the note editor so just redraw the card
                            redrawCard();
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(4492) ? ((ListenerUtil.mutListener.listen(4491) ? (requestCode >= DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4490) ? (requestCode <= DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4489) ? (requestCode > DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4488) ? (requestCode < DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4487) ? (requestCode != DECK_OPTIONS) : (requestCode == DECK_OPTIONS)))))) || resultCode == RESULT_OK) : ((ListenerUtil.mutListener.listen(4491) ? (requestCode >= DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4490) ? (requestCode <= DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4489) ? (requestCode > DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4488) ? (requestCode < DECK_OPTIONS) : (ListenerUtil.mutListener.listen(4487) ? (requestCode != DECK_OPTIONS) : (requestCode == DECK_OPTIONS)))))) && resultCode == RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(4493)) {
                    performReload();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4503)) {
            if (!mDisableClipboard) {
                if (!ListenerUtil.mutListener.listen(4502)) {
                    clearClipboard();
                }
            }
        }
    }

    /**
     * Whether the class should use collection.getSched() when performing tasks.
     * The aim of this method is to completely distinguish FlashcardViewer from Reviewer
     *
     * This is partially implemented, the end goal is that the FlashcardViewer will not have any coupling to getSched
     *
     * Currently, this is used for note edits - in a reviewing context, this should show the next card.
     * In a previewing context, the card should not change.
     */
    protected boolean canAccessScheduler() {
        return false;
    }

    protected void onEditedNoteChanged() {
    }

    /**
     * An action which may invalidate the current list of cards has been performed
     */
    protected abstract void performReload();

    // Get the did of the parent deck (ignoring any subdecks)
    protected long getParentDid() {
        return getCol().getDecks().selected();
    }

    private void redrawCard() {
        if (!ListenerUtil.mutListener.listen(4506)) {
            // if the activity was destroyed. In this case, just wait until onCollectionLoaded callback succeeds.
            if (hasLoadedCardContent()) {
                if (!ListenerUtil.mutListener.listen(4505)) {
                    fillFlashcard();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4504)) {
                    Timber.i("Skipping card redraw - card still initialising.");
                }
            }
        }
    }

    /**
     * Whether the callback to onCollectionLoaded has loaded card content
     */
    private boolean hasLoadedCardContent() {
        return mCardContent != null;
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    /**
     * Show/dismiss dialog when sd card is ejected/remounted (collection is saved by SdCardReceiver)
     */
    private void registerExternalStorageListener() {
        if (!ListenerUtil.mutListener.listen(4512)) {
            if (mUnmountReceiver == null) {
                if (!ListenerUtil.mutListener.listen(4509)) {
                    mUnmountReceiver = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if (!ListenerUtil.mutListener.listen(4508)) {
                                if (intent.getAction().equals(SdCardReceiver.MEDIA_EJECT)) {
                                    if (!ListenerUtil.mutListener.listen(4507)) {
                                        finishWithoutAnimation();
                                    }
                                }
                            }
                        }
                    };
                }
                IntentFilter iFilter = new IntentFilter();
                if (!ListenerUtil.mutListener.listen(4510)) {
                    iFilter.addAction(SdCardReceiver.MEDIA_EJECT);
                }
                if (!ListenerUtil.mutListener.listen(4511)) {
                    registerReceiver(mUnmountReceiver, iFilter);
                }
            }
        }
    }

    private void pauseTimer() {
        if (!ListenerUtil.mutListener.listen(4514)) {
            if (mCurrentCard != null) {
                if (!ListenerUtil.mutListener.listen(4513)) {
                    mCurrentCard.stopTimer();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4516)) {
            // it run would trigger the time limit condition (red, stopped timer) in the background.
            if (mCardTimer != null) {
                if (!ListenerUtil.mutListener.listen(4515)) {
                    mCardTimer.stop();
                }
            }
        }
    }

    private void resumeTimer() {
        if (!ListenerUtil.mutListener.listen(4534)) {
            if (mCurrentCard != null) {
                if (!ListenerUtil.mutListener.listen(4517)) {
                    // suspend and resume.
                    mCurrentCard.resumeTimer();
                }
                if (!ListenerUtil.mutListener.listen(4522)) {
                    // timeTaken() seconds ago.
                    mCardTimer.setBase((ListenerUtil.mutListener.listen(4521) ? (SystemClock.elapsedRealtime() % mCurrentCard.timeTaken()) : (ListenerUtil.mutListener.listen(4520) ? (SystemClock.elapsedRealtime() / mCurrentCard.timeTaken()) : (ListenerUtil.mutListener.listen(4519) ? (SystemClock.elapsedRealtime() * mCurrentCard.timeTaken()) : (ListenerUtil.mutListener.listen(4518) ? (SystemClock.elapsedRealtime() + mCurrentCard.timeTaken()) : (SystemClock.elapsedRealtime() - mCurrentCard.timeTaken()))))));
                }
                if (!ListenerUtil.mutListener.listen(4533)) {
                    // Don't start the timer if we have already reached the time limit or it will tick over
                    if ((ListenerUtil.mutListener.listen(4531) ? (((ListenerUtil.mutListener.listen(4526) ? (SystemClock.elapsedRealtime() % mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4525) ? (SystemClock.elapsedRealtime() / mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4524) ? (SystemClock.elapsedRealtime() * mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4523) ? (SystemClock.elapsedRealtime() + mCardTimer.getBase()) : (SystemClock.elapsedRealtime() - mCardTimer.getBase())))))) >= mCurrentCard.timeLimit()) : (ListenerUtil.mutListener.listen(4530) ? (((ListenerUtil.mutListener.listen(4526) ? (SystemClock.elapsedRealtime() % mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4525) ? (SystemClock.elapsedRealtime() / mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4524) ? (SystemClock.elapsedRealtime() * mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4523) ? (SystemClock.elapsedRealtime() + mCardTimer.getBase()) : (SystemClock.elapsedRealtime() - mCardTimer.getBase())))))) <= mCurrentCard.timeLimit()) : (ListenerUtil.mutListener.listen(4529) ? (((ListenerUtil.mutListener.listen(4526) ? (SystemClock.elapsedRealtime() % mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4525) ? (SystemClock.elapsedRealtime() / mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4524) ? (SystemClock.elapsedRealtime() * mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4523) ? (SystemClock.elapsedRealtime() + mCardTimer.getBase()) : (SystemClock.elapsedRealtime() - mCardTimer.getBase())))))) > mCurrentCard.timeLimit()) : (ListenerUtil.mutListener.listen(4528) ? (((ListenerUtil.mutListener.listen(4526) ? (SystemClock.elapsedRealtime() % mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4525) ? (SystemClock.elapsedRealtime() / mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4524) ? (SystemClock.elapsedRealtime() * mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4523) ? (SystemClock.elapsedRealtime() + mCardTimer.getBase()) : (SystemClock.elapsedRealtime() - mCardTimer.getBase())))))) != mCurrentCard.timeLimit()) : (ListenerUtil.mutListener.listen(4527) ? (((ListenerUtil.mutListener.listen(4526) ? (SystemClock.elapsedRealtime() % mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4525) ? (SystemClock.elapsedRealtime() / mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4524) ? (SystemClock.elapsedRealtime() * mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4523) ? (SystemClock.elapsedRealtime() + mCardTimer.getBase()) : (SystemClock.elapsedRealtime() - mCardTimer.getBase())))))) == mCurrentCard.timeLimit()) : (((ListenerUtil.mutListener.listen(4526) ? (SystemClock.elapsedRealtime() % mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4525) ? (SystemClock.elapsedRealtime() / mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4524) ? (SystemClock.elapsedRealtime() * mCardTimer.getBase()) : (ListenerUtil.mutListener.listen(4523) ? (SystemClock.elapsedRealtime() + mCardTimer.getBase()) : (SystemClock.elapsedRealtime() - mCardTimer.getBase())))))) < mCurrentCard.timeLimit()))))))) {
                        if (!ListenerUtil.mutListener.listen(4532)) {
                            mCardTimer.start();
                        }
                    }
                }
            }
        }
    }

    protected void undo() {
        if (!ListenerUtil.mutListener.listen(4536)) {
            if (isUndoAvailable()) {
                if (!ListenerUtil.mutListener.listen(4535)) {
                    TaskManager.launchCollectionTask(new CollectionTask.Undo(), mAnswerCardHandler(false));
                }
            }
        }
    }

    private void finishNoStorageAvailable() {
        if (!ListenerUtil.mutListener.listen(4537)) {
            AbstractFlashcardViewer.this.setResult(DeckPicker.RESULT_MEDIA_EJECTED);
        }
        if (!ListenerUtil.mutListener.listen(4538)) {
            finishWithoutAnimation();
        }
    }

    protected void editCard() {
        if (!ListenerUtil.mutListener.listen(4539)) {
            if (mCurrentCard == null) {
                // This should never occurs. It means the review button was pressed while there is no more card in the reviewer.
                return;
            }
        }
        Intent editCard = new Intent(AbstractFlashcardViewer.this, NoteEditor.class);
        if (!ListenerUtil.mutListener.listen(4540)) {
            editCard.putExtra(NoteEditor.EXTRA_CALLER, NoteEditor.CALLER_REVIEWER);
        }
        if (!ListenerUtil.mutListener.listen(4541)) {
            sEditorCard = mCurrentCard;
        }
        if (!ListenerUtil.mutListener.listen(4542)) {
            startActivityForResultWithAnimation(editCard, EDIT_CURRENT_CARD, LEFT);
        }
    }

    protected void generateQuestionSoundList() {
        if (!ListenerUtil.mutListener.listen(4543)) {
            mSoundPlayer.addSounds(mBaseUrl, mCurrentCard.qSimple(), SoundSide.QUESTION);
        }
    }

    protected void lookUpOrSelectText() {
        if (!ListenerUtil.mutListener.listen(4547)) {
            if (clipboardHasText()) {
                if (!ListenerUtil.mutListener.listen(4545)) {
                    Timber.d("Clipboard has text = %b", clipboardHasText());
                }
                if (!ListenerUtil.mutListener.listen(4546)) {
                    lookUp();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4544)) {
                    selectAndCopyText();
                }
            }
        }
    }

    private void lookUp() {
        if (!ListenerUtil.mutListener.listen(4548)) {
            mLookUpIcon.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4549)) {
            mIsSelecting = false;
        }
        if (!ListenerUtil.mutListener.listen(4551)) {
            if (Lookup.lookUp(clipboardGetText().toString())) {
                if (!ListenerUtil.mutListener.listen(4550)) {
                    clearClipboard();
                }
            }
        }
    }

    private void showLookupButtonIfNeeded() {
        if (!ListenerUtil.mutListener.listen(4560)) {
            if ((ListenerUtil.mutListener.listen(4552) ? (!mDisableClipboard || mClipboard != null) : (!mDisableClipboard && mClipboard != null))) {
                if (!ListenerUtil.mutListener.listen(4559)) {
                    if ((ListenerUtil.mutListener.listen(4554) ? ((ListenerUtil.mutListener.listen(4553) ? (clipboardGetText().length() != 0 || Lookup.isAvailable()) : (clipboardGetText().length() != 0 && Lookup.isAvailable())) || mLookUpIcon.getVisibility() != View.VISIBLE) : ((ListenerUtil.mutListener.listen(4553) ? (clipboardGetText().length() != 0 || Lookup.isAvailable()) : (clipboardGetText().length() != 0 && Lookup.isAvailable())) && mLookUpIcon.getVisibility() != View.VISIBLE))) {
                        if (!ListenerUtil.mutListener.listen(4557)) {
                            mLookUpIcon.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(4558)) {
                            enableViewAnimation(mLookUpIcon, ViewAnimation.fade(ViewAnimation.FADE_IN, mFadeDuration, 0));
                        }
                    } else if (mLookUpIcon.getVisibility() == View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(4555)) {
                            mLookUpIcon.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(4556)) {
                            enableViewAnimation(mLookUpIcon, ViewAnimation.fade(ViewAnimation.FADE_OUT, mFadeDuration, 0));
                        }
                    }
                }
            }
        }
    }

    private void hideLookupButton() {
        if (!ListenerUtil.mutListener.listen(4565)) {
            if ((ListenerUtil.mutListener.listen(4561) ? (!mDisableClipboard || mLookUpIcon.getVisibility() != View.GONE) : (!mDisableClipboard && mLookUpIcon.getVisibility() != View.GONE))) {
                if (!ListenerUtil.mutListener.listen(4562)) {
                    mLookUpIcon.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4563)) {
                    enableViewAnimation(mLookUpIcon, ViewAnimation.fade(ViewAnimation.FADE_OUT, mFadeDuration, 0));
                }
                if (!ListenerUtil.mutListener.listen(4564)) {
                    clearClipboard();
                }
            }
        }
    }

    protected void showDeleteNoteDialog() {
        Resources res = getResources();
        if (!ListenerUtil.mutListener.listen(4566)) {
            new MaterialDialog.Builder(this).title(res.getString(R.string.delete_card_title)).iconAttr(R.attr.dialogErrorIcon).content(res.getString(R.string.delete_note_message, Utils.stripHTML(mCurrentCard.q(true)))).positiveText(R.string.dialog_positive_delete).negativeText(R.string.dialog_cancel).onPositive((dialog, which) -> {
                Timber.i("AbstractFlashcardViewer:: OK button pressed to delete note %d", mCurrentCard.getNid());
                mSoundPlayer.stopSounds();
                dismiss(Collection.DismissType.DELETE_NOTE);
            }).build().show();
        }
    }

    private int getRecommendedEase(boolean easy) {
        try {
            switch(getAnswerButtonCount()) {
                case 2:
                    return EASE_2;
                case 3:
                    return easy ? EASE_3 : EASE_2;
                case 4:
                    return easy ? EASE_4 : EASE_3;
                default:
                    return 0;
            }
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(4567)) {
                AnkiDroidApp.sendExceptionReport(e, "AbstractReviewer-getRecommendedEase");
            }
            if (!ListenerUtil.mutListener.listen(4568)) {
                closeReviewer(DeckPicker.RESULT_DB_ERROR, true);
            }
            return 0;
        }
    }

    protected void answerCard(@Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(4569)) {
            if (mInAnswer) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4570)) {
            mIsSelecting = false;
        }
        if (!ListenerUtil.mutListener.listen(4571)) {
            hideLookupButton();
        }
        int buttonNumber = getCol().getSched().answerButtons(mCurrentCard);
        if (!ListenerUtil.mutListener.listen(4577)) {
            // Detect invalid ease for current card (e.g. by using keyboard shortcut or gesture).
            if ((ListenerUtil.mutListener.listen(4576) ? (buttonNumber >= ease) : (ListenerUtil.mutListener.listen(4575) ? (buttonNumber <= ease) : (ListenerUtil.mutListener.listen(4574) ? (buttonNumber > ease) : (ListenerUtil.mutListener.listen(4573) ? (buttonNumber != ease) : (ListenerUtil.mutListener.listen(4572) ? (buttonNumber == ease) : (buttonNumber < ease))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4597)) {
            // Set the dots appearing below the toolbar
            switch(ease) {
                case EASE_1:
                    if (!ListenerUtil.mutListener.listen(4578)) {
                        mChosenAnswer.setText("\u2022");
                    }
                    if (!ListenerUtil.mutListener.listen(4579)) {
                        mChosenAnswer.setTextColor(ContextCompat.getColor(this, R.color.material_red_500));
                    }
                    break;
                case EASE_2:
                    if (!ListenerUtil.mutListener.listen(4580)) {
                        mChosenAnswer.setText("\u2022\u2022");
                    }
                    if (!ListenerUtil.mutListener.listen(4586)) {
                        mChosenAnswer.setTextColor(ContextCompat.getColor(this, (ListenerUtil.mutListener.listen(4585) ? (buttonNumber >= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4584) ? (buttonNumber <= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4583) ? (buttonNumber > Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4582) ? (buttonNumber < Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4581) ? (buttonNumber != Consts.BUTTON_FOUR) : (buttonNumber == Consts.BUTTON_FOUR)))))) ? R.color.material_blue_grey_600 : R.color.material_green_500));
                    }
                    break;
                case EASE_3:
                    if (!ListenerUtil.mutListener.listen(4587)) {
                        mChosenAnswer.setText("\u2022\u2022\u2022");
                    }
                    if (!ListenerUtil.mutListener.listen(4593)) {
                        mChosenAnswer.setTextColor(ContextCompat.getColor(this, (ListenerUtil.mutListener.listen(4592) ? (buttonNumber >= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4591) ? (buttonNumber <= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4590) ? (buttonNumber > Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4589) ? (buttonNumber < Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(4588) ? (buttonNumber != Consts.BUTTON_FOUR) : (buttonNumber == Consts.BUTTON_FOUR)))))) ? R.color.material_green_500 : R.color.material_light_blue_500));
                    }
                    break;
                case EASE_4:
                    if (!ListenerUtil.mutListener.listen(4594)) {
                        mChosenAnswer.setText("\u2022\u2022\u2022\u2022");
                    }
                    if (!ListenerUtil.mutListener.listen(4595)) {
                        mChosenAnswer.setTextColor(ContextCompat.getColor(this, R.color.material_light_blue_500));
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(4596)) {
                        Timber.w("Unknown easy type %s", ease);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(4598)) {
            // remove chosen answer hint after a while
            mTimerHandler.removeCallbacks(removeChosenAnswerText);
        }
        if (!ListenerUtil.mutListener.listen(4599)) {
            mTimerHandler.postDelayed(removeChosenAnswerText, sShowChosenAnswerLength);
        }
        if (!ListenerUtil.mutListener.listen(4600)) {
            mSoundPlayer.stopSounds();
        }
        if (!ListenerUtil.mutListener.listen(4601)) {
            mCurrentEase = ease;
        }
        if (!ListenerUtil.mutListener.listen(4602)) {
            TaskManager.launchCollectionTask(new CollectionTask.AnswerAndGetCard(mCurrentCard, mCurrentEase), mAnswerCardHandler(true));
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(4613)) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                // assign correct gesture code
                int gesture = COMMAND_NOTHING;
                if (!ListenerUtil.mutListener.listen(4605)) {
                    switch(event.getKeyCode()) {
                        case KeyEvent.KEYCODE_VOLUME_UP:
                            if (!ListenerUtil.mutListener.listen(4603)) {
                                gesture = mGestureVolumeUp;
                            }
                            break;
                        case KeyEvent.KEYCODE_VOLUME_DOWN:
                            if (!ListenerUtil.mutListener.listen(4604)) {
                                gesture = mGestureVolumeDown;
                            }
                            break;
                    }
                }
                if (!ListenerUtil.mutListener.listen(4612)) {
                    // Execute gesture's command, but only consume event if action is assigned. We want the volume buttons to work normally otherwise.
                    if ((ListenerUtil.mutListener.listen(4610) ? (gesture >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(4609) ? (gesture <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(4608) ? (gesture > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(4607) ? (gesture < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(4606) ? (gesture == COMMAND_NOTHING) : (gesture != COMMAND_NOTHING))))))) {
                        if (!ListenerUtil.mutListener.listen(4611)) {
                            executeCommand(gesture);
                        }
                        return true;
                    }
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    // Set the content view to the one provided and initialize accessors.
    protected void initLayout() {
        FrameLayout mCardContainer = findViewById(R.id.flashcard_frame);
        if (!ListenerUtil.mutListener.listen(4614)) {
            mTopBarLayout = findViewById(R.id.top_bar);
        }
        ImageView mark = mTopBarLayout.findViewById(R.id.mark_icon);
        ImageView flag = mTopBarLayout.findViewById(R.id.flag_icon);
        if (!ListenerUtil.mutListener.listen(4615)) {
            mCardMarker = new CardMarker(mark, flag);
        }
        if (!ListenerUtil.mutListener.listen(4616)) {
            mCardFrame = findViewById(R.id.flashcard);
        }
        if (!ListenerUtil.mutListener.listen(4617)) {
            mCardFrameParent = (ViewGroup) mCardFrame.getParent();
        }
        if (!ListenerUtil.mutListener.listen(4618)) {
            mTouchLayer = findViewById(R.id.touch_layer);
        }
        if (!ListenerUtil.mutListener.listen(4619)) {
            mTouchLayer.setOnTouchListener(mGestureListener);
        }
        if (!ListenerUtil.mutListener.listen(4621)) {
            if (!mDisableClipboard) {
                if (!ListenerUtil.mutListener.listen(4620)) {
                    mClipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4622)) {
            mCardFrame.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(4623)) {
            // Initialize swipe
            mGestureDetectorImpl = mLinkOverridesTouchGesture ? new LinkDetectingGestureDetector() : new MyGestureDetector();
        }
        if (!ListenerUtil.mutListener.listen(4624)) {
            gestureDetector = new GestureDetector(this, mGestureDetectorImpl);
        }
        if (!ListenerUtil.mutListener.listen(4625)) {
            mEaseButtonsLayout = findViewById(R.id.ease_buttons);
        }
        if (!ListenerUtil.mutListener.listen(4626)) {
            mEase1 = findViewById(R.id.ease1);
        }
        if (!ListenerUtil.mutListener.listen(4627)) {
            mEase1Layout = findViewById(R.id.flashcard_layout_ease1);
        }
        if (!ListenerUtil.mutListener.listen(4628)) {
            mEase1Layout.setOnClickListener(mSelectEaseHandler);
        }
        if (!ListenerUtil.mutListener.listen(4629)) {
            mEase2 = findViewById(R.id.ease2);
        }
        if (!ListenerUtil.mutListener.listen(4630)) {
            mEase2Layout = findViewById(R.id.flashcard_layout_ease2);
        }
        if (!ListenerUtil.mutListener.listen(4631)) {
            mEase2Layout.setOnClickListener(mSelectEaseHandler);
        }
        if (!ListenerUtil.mutListener.listen(4632)) {
            mEase3 = findViewById(R.id.ease3);
        }
        if (!ListenerUtil.mutListener.listen(4633)) {
            mEase3Layout = findViewById(R.id.flashcard_layout_ease3);
        }
        if (!ListenerUtil.mutListener.listen(4634)) {
            mEase3Layout.setOnClickListener(mSelectEaseHandler);
        }
        if (!ListenerUtil.mutListener.listen(4635)) {
            mEase4 = findViewById(R.id.ease4);
        }
        if (!ListenerUtil.mutListener.listen(4636)) {
            mEase4Layout = findViewById(R.id.flashcard_layout_ease4);
        }
        if (!ListenerUtil.mutListener.listen(4637)) {
            mEase4Layout.setOnClickListener(mSelectEaseHandler);
        }
        if (!ListenerUtil.mutListener.listen(4638)) {
            mNext1 = findViewById(R.id.nextTime1);
        }
        if (!ListenerUtil.mutListener.listen(4639)) {
            mNext2 = findViewById(R.id.nextTime2);
        }
        if (!ListenerUtil.mutListener.listen(4640)) {
            mNext3 = findViewById(R.id.nextTime3);
        }
        if (!ListenerUtil.mutListener.listen(4641)) {
            mNext4 = findViewById(R.id.nextTime4);
        }
        if (!ListenerUtil.mutListener.listen(4646)) {
            if (!mShowNextReviewTime) {
                if (!ListenerUtil.mutListener.listen(4642)) {
                    mNext1.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4643)) {
                    mNext2.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4644)) {
                    mNext3.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4645)) {
                    mNext4.setVisibility(View.GONE);
                }
            }
        }
        Button mFlipCard = findViewById(R.id.flip_card);
        if (!ListenerUtil.mutListener.listen(4647)) {
            mFlipCardLayout = findViewById(R.id.flashcard_layout_flip);
        }
        if (!ListenerUtil.mutListener.listen(4648)) {
            mFlipCardLayout.setOnClickListener(mFlipCardListener);
        }
        if (!ListenerUtil.mutListener.listen(4650)) {
            if (animationEnabled()) {
                if (!ListenerUtil.mutListener.listen(4649)) {
                    mFlipCard.setBackgroundResource(Themes.getResFromAttr(this, R.attr.hardButtonRippleRef));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4707)) {
            if ((ListenerUtil.mutListener.listen(4656) ? (!mButtonHeightSet || (ListenerUtil.mutListener.listen(4655) ? (mRelativeButtonSize >= 100) : (ListenerUtil.mutListener.listen(4654) ? (mRelativeButtonSize <= 100) : (ListenerUtil.mutListener.listen(4653) ? (mRelativeButtonSize > 100) : (ListenerUtil.mutListener.listen(4652) ? (mRelativeButtonSize < 100) : (ListenerUtil.mutListener.listen(4651) ? (mRelativeButtonSize == 100) : (mRelativeButtonSize != 100))))))) : (!mButtonHeightSet && (ListenerUtil.mutListener.listen(4655) ? (mRelativeButtonSize >= 100) : (ListenerUtil.mutListener.listen(4654) ? (mRelativeButtonSize <= 100) : (ListenerUtil.mutListener.listen(4653) ? (mRelativeButtonSize > 100) : (ListenerUtil.mutListener.listen(4652) ? (mRelativeButtonSize < 100) : (ListenerUtil.mutListener.listen(4651) ? (mRelativeButtonSize == 100) : (mRelativeButtonSize != 100))))))))) {
                ViewGroup.LayoutParams params = mFlipCardLayout.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(4665)) {
                    params.height = (ListenerUtil.mutListener.listen(4664) ? ((ListenerUtil.mutListener.listen(4660) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4659) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4658) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4657) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) % 100) : (ListenerUtil.mutListener.listen(4663) ? ((ListenerUtil.mutListener.listen(4660) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4659) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4658) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4657) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) * 100) : (ListenerUtil.mutListener.listen(4662) ? ((ListenerUtil.mutListener.listen(4660) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4659) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4658) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4657) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) - 100) : (ListenerUtil.mutListener.listen(4661) ? ((ListenerUtil.mutListener.listen(4660) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4659) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4658) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4657) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) + 100) : ((ListenerUtil.mutListener.listen(4660) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4659) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4658) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4657) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) / 100)))));
                }
                if (!ListenerUtil.mutListener.listen(4666)) {
                    params = mEase1Layout.getLayoutParams();
                }
                if (!ListenerUtil.mutListener.listen(4675)) {
                    params.height = (ListenerUtil.mutListener.listen(4674) ? ((ListenerUtil.mutListener.listen(4670) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4669) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4668) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4667) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) % 100) : (ListenerUtil.mutListener.listen(4673) ? ((ListenerUtil.mutListener.listen(4670) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4669) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4668) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4667) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) * 100) : (ListenerUtil.mutListener.listen(4672) ? ((ListenerUtil.mutListener.listen(4670) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4669) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4668) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4667) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) - 100) : (ListenerUtil.mutListener.listen(4671) ? ((ListenerUtil.mutListener.listen(4670) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4669) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4668) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4667) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) + 100) : ((ListenerUtil.mutListener.listen(4670) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4669) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4668) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4667) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) / 100)))));
                }
                if (!ListenerUtil.mutListener.listen(4676)) {
                    params = mEase2Layout.getLayoutParams();
                }
                if (!ListenerUtil.mutListener.listen(4685)) {
                    params.height = (ListenerUtil.mutListener.listen(4684) ? ((ListenerUtil.mutListener.listen(4680) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4679) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4678) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4677) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) % 100) : (ListenerUtil.mutListener.listen(4683) ? ((ListenerUtil.mutListener.listen(4680) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4679) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4678) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4677) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) * 100) : (ListenerUtil.mutListener.listen(4682) ? ((ListenerUtil.mutListener.listen(4680) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4679) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4678) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4677) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) - 100) : (ListenerUtil.mutListener.listen(4681) ? ((ListenerUtil.mutListener.listen(4680) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4679) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4678) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4677) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) + 100) : ((ListenerUtil.mutListener.listen(4680) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4679) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4678) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4677) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) / 100)))));
                }
                if (!ListenerUtil.mutListener.listen(4686)) {
                    params = mEase3Layout.getLayoutParams();
                }
                if (!ListenerUtil.mutListener.listen(4695)) {
                    params.height = (ListenerUtil.mutListener.listen(4694) ? ((ListenerUtil.mutListener.listen(4690) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4689) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4688) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4687) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) % 100) : (ListenerUtil.mutListener.listen(4693) ? ((ListenerUtil.mutListener.listen(4690) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4689) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4688) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4687) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) * 100) : (ListenerUtil.mutListener.listen(4692) ? ((ListenerUtil.mutListener.listen(4690) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4689) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4688) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4687) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) - 100) : (ListenerUtil.mutListener.listen(4691) ? ((ListenerUtil.mutListener.listen(4690) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4689) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4688) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4687) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) + 100) : ((ListenerUtil.mutListener.listen(4690) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4689) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4688) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4687) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) / 100)))));
                }
                if (!ListenerUtil.mutListener.listen(4696)) {
                    params = mEase4Layout.getLayoutParams();
                }
                if (!ListenerUtil.mutListener.listen(4705)) {
                    params.height = (ListenerUtil.mutListener.listen(4704) ? ((ListenerUtil.mutListener.listen(4700) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4699) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4698) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4697) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) % 100) : (ListenerUtil.mutListener.listen(4703) ? ((ListenerUtil.mutListener.listen(4700) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4699) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4698) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4697) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) * 100) : (ListenerUtil.mutListener.listen(4702) ? ((ListenerUtil.mutListener.listen(4700) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4699) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4698) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4697) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) - 100) : (ListenerUtil.mutListener.listen(4701) ? ((ListenerUtil.mutListener.listen(4700) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4699) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4698) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4697) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) + 100) : ((ListenerUtil.mutListener.listen(4700) ? (params.height % mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4699) ? (params.height / mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4698) ? (params.height - mRelativeButtonSize) : (ListenerUtil.mutListener.listen(4697) ? (params.height + mRelativeButtonSize) : (params.height * mRelativeButtonSize))))) / 100)))));
                }
                if (!ListenerUtil.mutListener.listen(4706)) {
                    mButtonHeightSet = true;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4708)) {
            mPreviewButtonsLayout = findViewById(R.id.preview_buttons_layout);
        }
        if (!ListenerUtil.mutListener.listen(4709)) {
            mPreviewPrevCard = findViewById(R.id.preview_previous_flashcard);
        }
        if (!ListenerUtil.mutListener.listen(4710)) {
            mPreviewNextCard = findViewById(R.id.preview_next_flashcard);
        }
        if (!ListenerUtil.mutListener.listen(4711)) {
            mPreviewToggleAnswerText = findViewById(R.id.preview_flip_flashcard);
        }
        if (!ListenerUtil.mutListener.listen(4712)) {
            mCardTimer = findViewById(R.id.card_time);
        }
        if (!ListenerUtil.mutListener.listen(4713)) {
            mChosenAnswer = findViewById(R.id.choosen_answer);
        }
        if (!ListenerUtil.mutListener.listen(4714)) {
            mAnswerField = findViewById(R.id.answer_field);
        }
        if (!ListenerUtil.mutListener.listen(4715)) {
            mLookUpIcon = findViewById(R.id.lookup_button);
        }
        if (!ListenerUtil.mutListener.listen(4716)) {
            mLookUpIcon.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4717)) {
            mLookUpIcon.setOnClickListener(arg0 -> {
                Timber.i("AbstractFlashcardViewer:: Lookup button pressed");
                if (clipboardHasText()) {
                    lookUp();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4718)) {
            initControls();
        }
        // Position answer buttons
        String answerButtonsPosition = AnkiDroidApp.getSharedPrefs(this).getString(getString(R.string.answer_buttons_position_preference), "bottom");
        LinearLayout answerArea = findViewById(R.id.bottom_area_layout);
        RelativeLayout.LayoutParams answerAreaParams = (RelativeLayout.LayoutParams) answerArea.getLayoutParams();
        RelativeLayout.LayoutParams cardContainerParams = (RelativeLayout.LayoutParams) mCardContainer.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(4727)) {
            switch(answerButtonsPosition) {
                case "top":
                    if (!ListenerUtil.mutListener.listen(4719)) {
                        cardContainerParams.addRule(RelativeLayout.BELOW, R.id.bottom_area_layout);
                    }
                    if (!ListenerUtil.mutListener.listen(4720)) {
                        answerAreaParams.addRule(RelativeLayout.BELOW, R.id.mic_tool_bar_layer);
                    }
                    if (!ListenerUtil.mutListener.listen(4721)) {
                        answerArea.removeView(mAnswerField);
                    }
                    if (!ListenerUtil.mutListener.listen(4722)) {
                        answerArea.addView(mAnswerField, 1);
                    }
                    break;
                case "bottom":
                    if (!ListenerUtil.mutListener.listen(4723)) {
                        cardContainerParams.addRule(RelativeLayout.ABOVE, R.id.bottom_area_layout);
                    }
                    if (!ListenerUtil.mutListener.listen(4724)) {
                        cardContainerParams.addRule(RelativeLayout.BELOW, R.id.mic_tool_bar_layer);
                    }
                    if (!ListenerUtil.mutListener.listen(4725)) {
                        answerAreaParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(4726)) {
                        Timber.w("Unknown answerButtonsPosition: %s", answerButtonsPosition);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(4728)) {
            answerArea.setLayoutParams(answerAreaParams);
        }
        if (!ListenerUtil.mutListener.listen(4729)) {
            mCardContainer.setLayoutParams(cardContainerParams);
        }
    }

    // they request we review carefully because of XSS security, we have
    @SuppressLint("SetJavaScriptEnabled")
    protected WebView createWebView() {
        WebView webView = new MyWebView(this);
        if (!ListenerUtil.mutListener.listen(4730)) {
            webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        }
        if (!ListenerUtil.mutListener.listen(4731)) {
            webView.getSettings().setDisplayZoomControls(false);
        }
        if (!ListenerUtil.mutListener.listen(4732)) {
            webView.getSettings().setBuiltInZoomControls(true);
        }
        if (!ListenerUtil.mutListener.listen(4733)) {
            webView.getSettings().setSupportZoom(true);
        }
        if (!ListenerUtil.mutListener.listen(4734)) {
            // Start at the most zoomed-out level
            webView.getSettings().setLoadWithOverviewMode(true);
        }
        if (!ListenerUtil.mutListener.listen(4735)) {
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(4736)) {
            webView.setWebChromeClient(new AnkiDroidWebChromeClient());
        }
        if (!ListenerUtil.mutListener.listen(4737)) {
            // Problems with focus and input tags is the reason we keep the old type answer mechanism for old Androids.
            webView.setFocusableInTouchMode(mUseInputTag);
        }
        if (!ListenerUtil.mutListener.listen(4738)) {
            webView.setScrollbarFadingEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(4739)) {
            Timber.d("Focusable = %s, Focusable in touch mode = %s", webView.isFocusable(), webView.isFocusableInTouchMode());
        }
        if (!ListenerUtil.mutListener.listen(4740)) {
            webView.setWebViewClient(new CardViewerWebClient(mAssetLoader));
        }
        if (!ListenerUtil.mutListener.listen(4741)) {
            // Set transparent color to prevent flashing white when night mode enabled
            webView.setBackgroundColor(Color.argb(1, 0, 0, 0));
        }
        if (!ListenerUtil.mutListener.listen(4742)) {
            // Javascript interface for calling AnkiDroid functions in webview, see card.js
            webView.addJavascriptInterface(javaScriptFunction(), "AnkiDroidJS");
        }
        return webView;
    }

    /**
     * If a card is displaying the question, flip it, otherwise answer it
     */
    private void flipOrAnswerCard(int cardOrdinal) {
        if (!ListenerUtil.mutListener.listen(4744)) {
            if (!sDisplayAnswer) {
                if (!ListenerUtil.mutListener.listen(4743)) {
                    displayCardAnswer();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4745)) {
            performClickWithVisualFeedback(cardOrdinal);
        }
    }

    private boolean webViewRendererLastCrashedOnCard(long cardId) {
        return (ListenerUtil.mutListener.listen(4751) ? (lastCrashingCardId != null || (ListenerUtil.mutListener.listen(4750) ? (lastCrashingCardId >= cardId) : (ListenerUtil.mutListener.listen(4749) ? (lastCrashingCardId <= cardId) : (ListenerUtil.mutListener.listen(4748) ? (lastCrashingCardId > cardId) : (ListenerUtil.mutListener.listen(4747) ? (lastCrashingCardId < cardId) : (ListenerUtil.mutListener.listen(4746) ? (lastCrashingCardId != cardId) : (lastCrashingCardId == cardId))))))) : (lastCrashingCardId != null && (ListenerUtil.mutListener.listen(4750) ? (lastCrashingCardId >= cardId) : (ListenerUtil.mutListener.listen(4749) ? (lastCrashingCardId <= cardId) : (ListenerUtil.mutListener.listen(4748) ? (lastCrashingCardId > cardId) : (ListenerUtil.mutListener.listen(4747) ? (lastCrashingCardId < cardId) : (ListenerUtil.mutListener.listen(4746) ? (lastCrashingCardId != cardId) : (lastCrashingCardId == cardId))))))));
    }

    private boolean canRecoverFromWebViewRendererCrash() {
        // Revisit webViewCrashedOnCard() if changing this. Logic currently assumes we have a card.
        return mCurrentCard != null;
    }

    // #5780 - Users could OOM the WebView Renderer. This triggers the same symptoms
    @VisibleForTesting()
    @SuppressWarnings("unused")
    public void crashWebViewRenderer() {
        if (!ListenerUtil.mutListener.listen(4752)) {
            loadUrlInViewer("chrome://crash");
        }
    }

    /**
     * Used to set the "javascript:" URIs for IPC
     */
    private void loadUrlInViewer(final String url) {
        if (!ListenerUtil.mutListener.listen(4753)) {
            processCardAction(cardWebView -> cardWebView.loadUrl(url));
        }
    }

    private <T extends View> T inflateNewView(@IdRes int id) {
        int layoutId = getContentViewAttr(mPrefFullscreenReview);
        ViewGroup content = (ViewGroup) LayoutInflater.from(AbstractFlashcardViewer.this).inflate(layoutId, null, false);
        T ret = content.findViewById(id);
        if (!ListenerUtil.mutListener.listen(4754)) {
            // detach the view from its parent
            ((ViewGroup) ret.getParent()).removeView(ret);
        }
        if (!ListenerUtil.mutListener.listen(4755)) {
            content.removeAllViews();
        }
        return ret;
    }

    private void destroyWebView(WebView webView) {
        try {
            if (!ListenerUtil.mutListener.listen(4761)) {
                if (webView != null) {
                    if (!ListenerUtil.mutListener.listen(4757)) {
                        webView.stopLoading();
                    }
                    if (!ListenerUtil.mutListener.listen(4758)) {
                        webView.setWebChromeClient(null);
                    }
                    if (!ListenerUtil.mutListener.listen(4759)) {
                        webView.setWebViewClient(null);
                    }
                    if (!ListenerUtil.mutListener.listen(4760)) {
                        webView.destroy();
                    }
                }
            }
        } catch (NullPointerException npe) {
            if (!ListenerUtil.mutListener.listen(4756)) {
                Timber.e(npe, "WebView became null on destruction");
            }
        }
    }

    protected boolean shouldShowNextReviewTime() {
        return mShowNextReviewTime;
    }

    protected void displayAnswerBottomBar() {
        if (!ListenerUtil.mutListener.listen(4762)) {
            mFlipCardLayout.setClickable(false);
        }
        if (!ListenerUtil.mutListener.listen(4763)) {
            mEaseButtonsLayout.setVisibility(View.VISIBLE);
        }
        Runnable after = () -> mFlipCardLayout.setVisibility(View.GONE);
        if (!ListenerUtil.mutListener.listen(4767)) {
            // hide "Show Answer" button
            if (animationDisabled()) {
                if (!ListenerUtil.mutListener.listen(4766)) {
                    after.run();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4764)) {
                    mFlipCardLayout.setAlpha(1);
                }
                if (!ListenerUtil.mutListener.listen(4765)) {
                    mFlipCardLayout.animate().alpha(0).setDuration(mShortAnimDuration).withEndAction(after);
                }
            }
        }
    }

    protected void hideEaseButtons() {
        Runnable after = this::actualHideEaseButtons;
        boolean easeButtonsVisible = mEaseButtonsLayout.getVisibility() == View.VISIBLE;
        if (!ListenerUtil.mutListener.listen(4768)) {
            mFlipCardLayout.setClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(4769)) {
            mFlipCardLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4774)) {
            if ((ListenerUtil.mutListener.listen(4770) ? (animationDisabled() && !easeButtonsVisible) : (animationDisabled() || !easeButtonsVisible))) {
                if (!ListenerUtil.mutListener.listen(4773)) {
                    after.run();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4771)) {
                    mFlipCardLayout.setAlpha(0);
                }
                if (!ListenerUtil.mutListener.listen(4772)) {
                    mFlipCardLayout.animate().alpha(1).setDuration(mShortAnimDuration).withEndAction(after);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4775)) {
            focusAnswerCompletionField();
        }
    }

    private void actualHideEaseButtons() {
        if (!ListenerUtil.mutListener.listen(4776)) {
            mEaseButtonsLayout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4777)) {
            mEase1Layout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4778)) {
            mEase2Layout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4779)) {
            mEase3Layout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4780)) {
            mEase4Layout.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4781)) {
            mNext1.setText("");
        }
        if (!ListenerUtil.mutListener.listen(4782)) {
            mNext2.setText("");
        }
        if (!ListenerUtil.mutListener.listen(4783)) {
            mNext3.setText("");
        }
        if (!ListenerUtil.mutListener.listen(4784)) {
            mNext4.setText("");
        }
    }

    /**
     * Focuses the appropriate field for an answer
     * And allows keyboard shortcuts to go to the default handlers.
     */
    private void focusAnswerCompletionField() {
        if (!ListenerUtil.mutListener.listen(4787)) {
            // In this case, the user can use touch to focus the field if necessary.
            if (typeAnswer()) {
                if (!ListenerUtil.mutListener.listen(4786)) {
                    mAnswerField.requestFocus();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4785)) {
                    mFlipCardLayout.requestFocus();
                }
            }
        }
    }

    protected void switchTopBarVisibility(int visible) {
        if (!ListenerUtil.mutListener.listen(4789)) {
            if (mShowTimer) {
                if (!ListenerUtil.mutListener.listen(4788)) {
                    mCardTimer.setVisibility(visible);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4790)) {
            mChosenAnswer.setVisibility(visible);
        }
    }

    protected void initControls() {
        if (!ListenerUtil.mutListener.listen(4791)) {
            mCardFrame.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4792)) {
            mChosenAnswer.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4793)) {
            mFlipCardLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4794)) {
            mAnswerField.setVisibility(typeAnswer() ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4795)) {
            mAnswerField.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    displayCardAnswer();
                    return true;
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(4796)) {
            mAnswerField.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_UP && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER)) {
                    displayCardAnswer();
                    return true;
                }
                return false;
            });
        }
    }

    protected SharedPreferences restorePreferences() {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        if (!ListenerUtil.mutListener.listen(4797)) {
            mUseInputTag = preferences.getBoolean("useInputTag", false);
        }
        if (!ListenerUtil.mutListener.listen(4798)) {
            mDoNotUseCodeFormatting = preferences.getBoolean("noCodeFormatting", false);
        }
        if (!ListenerUtil.mutListener.listen(4799)) {
            // On newer Androids, ignore this setting, which should be hidden in the prefs anyway.
            mDisableClipboard = "0".equals(preferences.getString("dictionary", "0"));
        }
        if (!ListenerUtil.mutListener.listen(4800)) {
            // mDeckFilename = preferences.getString("deckFilename", "");
            mPrefFullscreenReview = Integer.parseInt(preferences.getString("fullscreenMode", "0"));
        }
        if (!ListenerUtil.mutListener.listen(4801)) {
            mRelativeButtonSize = preferences.getInt("answerButtonSize", 100);
        }
        if (!ListenerUtil.mutListener.listen(4802)) {
            mSpeakText = preferences.getBoolean("tts", false);
        }
        if (!ListenerUtil.mutListener.listen(4803)) {
            mPrefUseTimer = preferences.getBoolean("timeoutAnswer", false);
        }
        if (!ListenerUtil.mutListener.listen(4804)) {
            mPrefWaitAnswerSecond = preferences.getInt("timeoutAnswerSeconds", 20);
        }
        if (!ListenerUtil.mutListener.listen(4805)) {
            mPrefWaitQuestionSecond = preferences.getInt("timeoutQuestionSeconds", 60);
        }
        if (!ListenerUtil.mutListener.listen(4806)) {
            mScrollingButtons = preferences.getBoolean("scrolling_buttons", false);
        }
        if (!ListenerUtil.mutListener.listen(4807)) {
            mDoubleScrolling = preferences.getBoolean("double_scrolling", false);
        }
        if (!ListenerUtil.mutListener.listen(4808)) {
            mPrefShowTopbar = preferences.getBoolean("showTopbar", true);
        }
        if (!ListenerUtil.mutListener.listen(4809)) {
            mGesturesEnabled = AnkiDroidApp.initiateGestures(preferences);
        }
        if (!ListenerUtil.mutListener.listen(4810)) {
            mLinkOverridesTouchGesture = preferences.getBoolean("linkOverridesTouchGesture", false);
        }
        if (!ListenerUtil.mutListener.listen(4820)) {
            if (mGesturesEnabled) {
                if (!ListenerUtil.mutListener.listen(4811)) {
                    mGestureSwipeUp = Integer.parseInt(preferences.getString("gestureSwipeUp", "9"));
                }
                if (!ListenerUtil.mutListener.listen(4812)) {
                    mGestureSwipeDown = Integer.parseInt(preferences.getString("gestureSwipeDown", "0"));
                }
                if (!ListenerUtil.mutListener.listen(4813)) {
                    mGestureSwipeLeft = Integer.parseInt(preferences.getString("gestureSwipeLeft", "8"));
                }
                if (!ListenerUtil.mutListener.listen(4814)) {
                    mGestureSwipeRight = Integer.parseInt(preferences.getString("gestureSwipeRight", "17"));
                }
                if (!ListenerUtil.mutListener.listen(4815)) {
                    mGestureDoubleTap = Integer.parseInt(preferences.getString("gestureDoubleTap", "7"));
                }
                if (!ListenerUtil.mutListener.listen(4816)) {
                    mGestureTapProcessor.init(preferences);
                }
                if (!ListenerUtil.mutListener.listen(4817)) {
                    mGestureLongclick = Integer.parseInt(preferences.getString("gestureLongclick", "11"));
                }
                if (!ListenerUtil.mutListener.listen(4818)) {
                    mGestureVolumeUp = Integer.parseInt(preferences.getString("gestureVolumeUp", "0"));
                }
                if (!ListenerUtil.mutListener.listen(4819)) {
                    mGestureVolumeDown = Integer.parseInt(preferences.getString("gestureVolumeDown", "0"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4822)) {
            if (preferences.getBoolean("keepScreenOn", false)) {
                if (!ListenerUtil.mutListener.listen(4821)) {
                    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            }
        }
        return preferences;
    }

    protected void restoreCollectionPreferences() {
        // These are preferences we pull out of the collection instead of SharedPreferences
        try {
            if (!ListenerUtil.mutListener.listen(4827)) {
                mShowNextReviewTime = getCol().getConf().getBoolean("estTimes");
            }
            // but be prepared to go with all default if it's a dynamic deck
            JSONObject revOptions = new JSONObject();
            long selectedDid = getCol().getDecks().selected();
            if (!ListenerUtil.mutListener.listen(4829)) {
                if (!getCol().getDecks().isDyn(selectedDid)) {
                    if (!ListenerUtil.mutListener.listen(4828)) {
                        revOptions = getCol().getDecks().confForDid(selectedDid).getJSONObject("rev");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4830)) {
                mOptUseGeneralTimerSettings = revOptions.optBoolean("useGeneralTimeoutSettings", true);
            }
            if (!ListenerUtil.mutListener.listen(4831)) {
                mOptUseTimer = revOptions.optBoolean("timeoutAnswer", false);
            }
            if (!ListenerUtil.mutListener.listen(4832)) {
                mOptWaitAnswerSecond = revOptions.optInt("timeoutAnswerSeconds", 20);
            }
            if (!ListenerUtil.mutListener.listen(4833)) {
                mOptWaitQuestionSecond = revOptions.optInt("timeoutQuestionSeconds", 60);
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(4823)) {
                Timber.e(e, "Unable to restoreCollectionPreferences");
            }
            throw new RuntimeException(e);
        } catch (NullPointerException npe) {
            // NPE on collection only happens if the Collection is broken, follow AnkiActivity example
            Intent deckPicker = new Intent(this, DeckPicker.class);
            if (!ListenerUtil.mutListener.listen(4824)) {
                // don't currently do anything with this
                deckPicker.putExtra("collectionLoadError", true);
            }
            if (!ListenerUtil.mutListener.listen(4825)) {
                deckPicker.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (!ListenerUtil.mutListener.listen(4826)) {
                startActivityWithAnimation(deckPicker, LEFT);
            }
        }
    }

    private void setInterface() {
        if (!ListenerUtil.mutListener.listen(4834)) {
            if (mCurrentCard == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4835)) {
            recreateWebView();
        }
    }

    private void recreateWebView() {
        if (!ListenerUtil.mutListener.listen(4840)) {
            if (mCardWebView == null) {
                if (!ListenerUtil.mutListener.listen(4836)) {
                    mCardWebView = createWebView();
                }
                if (!ListenerUtil.mutListener.listen(4837)) {
                    WebViewDebugging.initializeDebugging(AnkiDroidApp.getSharedPrefs(this));
                }
                if (!ListenerUtil.mutListener.listen(4838)) {
                    mCardFrame.addView(mCardWebView);
                }
                if (!ListenerUtil.mutListener.listen(4839)) {
                    mGestureDetectorImpl.onWebViewCreated(mCardWebView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4842)) {
            if (mCardWebView.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(4841)) {
                    mCardWebView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateForNewCard() {
        if (!ListenerUtil.mutListener.listen(4843)) {
            updateActionBar();
        }
        if (!ListenerUtil.mutListener.listen(4845)) {
            // Clean answer field
            if (typeAnswer()) {
                if (!ListenerUtil.mutListener.listen(4844)) {
                    mAnswerField.setText("");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4848)) {
            if ((ListenerUtil.mutListener.listen(4846) ? (mPrefWhiteboard || mWhiteboard != null) : (mPrefWhiteboard && mWhiteboard != null))) {
                if (!ListenerUtil.mutListener.listen(4847)) {
                    mWhiteboard.clear();
                }
            }
        }
    }

    protected void updateActionBar() {
        if (!ListenerUtil.mutListener.listen(4849)) {
            updateDeckName();
        }
    }

    protected void updateDeckName() {
        if (!ListenerUtil.mutListener.listen(4850)) {
            if (mCurrentCard == null)
                return;
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4852)) {
            if (actionBar != null) {
                String title = Decks.basename(getCol().getDecks().get(mCurrentCard.getDid()).getString("name"));
                if (!ListenerUtil.mutListener.listen(4851)) {
                    actionBar.setTitle(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4854)) {
            if (!mPrefShowTopbar) {
                if (!ListenerUtil.mutListener.listen(4853)) {
                    mTopBarLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    /*
     * Handler for the delay in auto showing question and/or answer One toggle for both question and answer, could set
     * longer delay for auto next question
     */
    protected final Handler mTimeoutHandler = new Handler();

    protected final Runnable mShowQuestionTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(4857)) {
                // Assume hitting the "Again" button when auto next question
                if ((ListenerUtil.mutListener.listen(4855) ? (mEase1Layout.isEnabled() || mEase1Layout.getVisibility() == View.VISIBLE) : (mEase1Layout.isEnabled() && mEase1Layout.getVisibility() == View.VISIBLE))) {
                    if (!ListenerUtil.mutListener.listen(4856)) {
                        mEase1Layout.performClick();
                    }
                }
            }
        }
    };

    protected final Runnable mShowAnswerTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(4860)) {
                if ((ListenerUtil.mutListener.listen(4858) ? (mFlipCardLayout.isEnabled() || mFlipCardLayout.getVisibility() == View.VISIBLE) : (mFlipCardLayout.isEnabled() && mFlipCardLayout.getVisibility() == View.VISIBLE))) {
                    if (!ListenerUtil.mutListener.listen(4859)) {
                        mFlipCardLayout.performClick();
                    }
                }
            }
        }
    };

    class ReadTextListener implements ReadText.ReadTextListener {

        public void onDone() {
            if (!ListenerUtil.mutListener.listen(4861)) {
                if (!mUseTimer) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4884)) {
                if (ReadText.getmQuestionAnswer() == SoundSide.QUESTION) {
                    long delay = (ListenerUtil.mutListener.listen(4876) ? (mWaitAnswerSecond % 1000) : (ListenerUtil.mutListener.listen(4875) ? (mWaitAnswerSecond / 1000) : (ListenerUtil.mutListener.listen(4874) ? (mWaitAnswerSecond - 1000) : (ListenerUtil.mutListener.listen(4873) ? (mWaitAnswerSecond + 1000) : (mWaitAnswerSecond * 1000)))));
                    if (!ListenerUtil.mutListener.listen(4883)) {
                        if ((ListenerUtil.mutListener.listen(4881) ? (delay >= 0) : (ListenerUtil.mutListener.listen(4880) ? (delay <= 0) : (ListenerUtil.mutListener.listen(4879) ? (delay < 0) : (ListenerUtil.mutListener.listen(4878) ? (delay != 0) : (ListenerUtil.mutListener.listen(4877) ? (delay == 0) : (delay > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(4882)) {
                                mTimeoutHandler.postDelayed(mShowAnswerTask, delay);
                            }
                        }
                    }
                } else if (ReadText.getmQuestionAnswer() == SoundSide.ANSWER) {
                    long delay = (ListenerUtil.mutListener.listen(4865) ? (mWaitQuestionSecond % 1000) : (ListenerUtil.mutListener.listen(4864) ? (mWaitQuestionSecond / 1000) : (ListenerUtil.mutListener.listen(4863) ? (mWaitQuestionSecond - 1000) : (ListenerUtil.mutListener.listen(4862) ? (mWaitQuestionSecond + 1000) : (mWaitQuestionSecond * 1000)))));
                    if (!ListenerUtil.mutListener.listen(4872)) {
                        if ((ListenerUtil.mutListener.listen(4870) ? (delay >= 0) : (ListenerUtil.mutListener.listen(4869) ? (delay <= 0) : (ListenerUtil.mutListener.listen(4868) ? (delay < 0) : (ListenerUtil.mutListener.listen(4867) ? (delay != 0) : (ListenerUtil.mutListener.listen(4866) ? (delay == 0) : (delay > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(4871)) {
                                mTimeoutHandler.postDelayed(mShowQuestionTask, delay);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void initTimer() {
        final TypedValue typedValue = new TypedValue();
        if (!ListenerUtil.mutListener.listen(4885)) {
            mShowTimer = mCurrentCard.showTimer();
        }
        if (!ListenerUtil.mutListener.listen(4890)) {
            if ((ListenerUtil.mutListener.listen(4886) ? (mShowTimer || mCardTimer.getVisibility() == View.INVISIBLE) : (mShowTimer && mCardTimer.getVisibility() == View.INVISIBLE))) {
                if (!ListenerUtil.mutListener.listen(4889)) {
                    mCardTimer.setVisibility(View.VISIBLE);
                }
            } else if ((ListenerUtil.mutListener.listen(4887) ? (!mShowTimer || mCardTimer.getVisibility() != View.INVISIBLE) : (!mShowTimer && mCardTimer.getVisibility() != View.INVISIBLE))) {
                if (!ListenerUtil.mutListener.listen(4888)) {
                    mCardTimer.setVisibility(View.INVISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4891)) {
            // Set normal timer color
            getTheme().resolveAttribute(android.R.attr.textColor, typedValue, true);
        }
        if (!ListenerUtil.mutListener.listen(4892)) {
            mCardTimer.setTextColor(typedValue.data);
        }
        if (!ListenerUtil.mutListener.listen(4893)) {
            mCardTimer.setBase(SystemClock.elapsedRealtime());
        }
        if (!ListenerUtil.mutListener.listen(4894)) {
            mCardTimer.start();
        }
        if (!ListenerUtil.mutListener.listen(4895)) {
            // Stop and highlight the timer if it reaches the time limit.
            getTheme().resolveAttribute(R.attr.maxTimerColor, typedValue, true);
        }
        final int limit = mCurrentCard.timeLimit();
        if (!ListenerUtil.mutListener.listen(4896)) {
            mCardTimer.setOnChronometerTickListener(chronometer -> {
                long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
                if (elapsed >= limit) {
                    chronometer.setTextColor(typedValue.data);
                    chronometer.stop();
                }
            });
        }
    }

    protected void displayCardQuestion() {
        if (!ListenerUtil.mutListener.listen(4897)) {
            displayCardQuestion(false);
        }
        if (!ListenerUtil.mutListener.listen(4898)) {
            // js api initialisation / reset
            jsApiInit();
        }
    }

    /**
     * String, as it will be displayed in the web viewer. Sound/video removed, image escaped...
     *     Or warning if required
     */
    private String displayString(boolean reload) {
        if (mCurrentCard.isEmpty()) {
            return getResources().getString(R.string.empty_card_warning);
        } else {
            String question = mCurrentCard.q(reload);
            if (!ListenerUtil.mutListener.listen(4899)) {
                question = getCol().getMedia().escapeImages(question);
            }
            if (!ListenerUtil.mutListener.listen(4900)) {
                question = typeAnsQuestionFilter(question);
            }
            if (!ListenerUtil.mutListener.listen(4901)) {
                Timber.v("question: '%s'", question);
            }
            return CardAppearance.enrichWithQADiv(question, false);
        }
    }

    protected void displayCardQuestion(boolean reload) {
        if (!ListenerUtil.mutListener.listen(4902)) {
            Timber.d("displayCardQuestion()");
        }
        if (!ListenerUtil.mutListener.listen(4903)) {
            sDisplayAnswer = false;
        }
        if (!ListenerUtil.mutListener.listen(4904)) {
            setInterface();
        }
        String displayString = displayString(reload);
        if (!ListenerUtil.mutListener.listen(4908)) {
            if ((ListenerUtil.mutListener.listen(4905) ? (!mCurrentCard.isEmpty() || typeAnswer()) : (!mCurrentCard.isEmpty() && typeAnswer()))) {
                if (!ListenerUtil.mutListener.listen(4907)) {
                    // Show text entry based on if the user wants to write the answer
                    mAnswerField.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4906)) {
                    mAnswerField.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4909)) {
            updateCard(displayString);
        }
        if (!ListenerUtil.mutListener.listen(4910)) {
            hideEaseButtons();
        }
        if (!ListenerUtil.mutListener.listen(4917)) {
            // Check if it should use the general 'Timeout settings' or the ones specific to this deck
            if (mOptUseGeneralTimerSettings) {
                if (!ListenerUtil.mutListener.listen(4914)) {
                    mUseTimer = mPrefUseTimer;
                }
                if (!ListenerUtil.mutListener.listen(4915)) {
                    mWaitAnswerSecond = mPrefWaitAnswerSecond;
                }
                if (!ListenerUtil.mutListener.listen(4916)) {
                    mWaitQuestionSecond = mPrefWaitQuestionSecond;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4911)) {
                    mUseTimer = mOptUseTimer;
                }
                if (!ListenerUtil.mutListener.listen(4912)) {
                    mWaitAnswerSecond = mOptWaitAnswerSecond;
                }
                if (!ListenerUtil.mutListener.listen(4913)) {
                    mWaitQuestionSecond = mOptWaitQuestionSecond;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4935)) {
            // If the user wants to show the answer automatically
            if (mUseTimer) {
                long delay = (ListenerUtil.mutListener.listen(4925) ? ((ListenerUtil.mutListener.listen(4921) ? (mWaitAnswerSecond % 1000) : (ListenerUtil.mutListener.listen(4920) ? (mWaitAnswerSecond / 1000) : (ListenerUtil.mutListener.listen(4919) ? (mWaitAnswerSecond - 1000) : (ListenerUtil.mutListener.listen(4918) ? (mWaitAnswerSecond + 1000) : (mWaitAnswerSecond * 1000))))) % mUseTimerDynamicMS) : (ListenerUtil.mutListener.listen(4924) ? ((ListenerUtil.mutListener.listen(4921) ? (mWaitAnswerSecond % 1000) : (ListenerUtil.mutListener.listen(4920) ? (mWaitAnswerSecond / 1000) : (ListenerUtil.mutListener.listen(4919) ? (mWaitAnswerSecond - 1000) : (ListenerUtil.mutListener.listen(4918) ? (mWaitAnswerSecond + 1000) : (mWaitAnswerSecond * 1000))))) / mUseTimerDynamicMS) : (ListenerUtil.mutListener.listen(4923) ? ((ListenerUtil.mutListener.listen(4921) ? (mWaitAnswerSecond % 1000) : (ListenerUtil.mutListener.listen(4920) ? (mWaitAnswerSecond / 1000) : (ListenerUtil.mutListener.listen(4919) ? (mWaitAnswerSecond - 1000) : (ListenerUtil.mutListener.listen(4918) ? (mWaitAnswerSecond + 1000) : (mWaitAnswerSecond * 1000))))) * mUseTimerDynamicMS) : (ListenerUtil.mutListener.listen(4922) ? ((ListenerUtil.mutListener.listen(4921) ? (mWaitAnswerSecond % 1000) : (ListenerUtil.mutListener.listen(4920) ? (mWaitAnswerSecond / 1000) : (ListenerUtil.mutListener.listen(4919) ? (mWaitAnswerSecond - 1000) : (ListenerUtil.mutListener.listen(4918) ? (mWaitAnswerSecond + 1000) : (mWaitAnswerSecond * 1000))))) - mUseTimerDynamicMS) : ((ListenerUtil.mutListener.listen(4921) ? (mWaitAnswerSecond % 1000) : (ListenerUtil.mutListener.listen(4920) ? (mWaitAnswerSecond / 1000) : (ListenerUtil.mutListener.listen(4919) ? (mWaitAnswerSecond - 1000) : (ListenerUtil.mutListener.listen(4918) ? (mWaitAnswerSecond + 1000) : (mWaitAnswerSecond * 1000))))) + mUseTimerDynamicMS)))));
                if (!ListenerUtil.mutListener.listen(4934)) {
                    if ((ListenerUtil.mutListener.listen(4930) ? (delay >= 0) : (ListenerUtil.mutListener.listen(4929) ? (delay <= 0) : (ListenerUtil.mutListener.listen(4928) ? (delay < 0) : (ListenerUtil.mutListener.listen(4927) ? (delay != 0) : (ListenerUtil.mutListener.listen(4926) ? (delay == 0) : (delay > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(4931)) {
                            mTimeoutHandler.removeCallbacks(mShowAnswerTask);
                        }
                        if (!ListenerUtil.mutListener.listen(4933)) {
                            if (!mSpeakText) {
                                if (!ListenerUtil.mutListener.listen(4932)) {
                                    mTimeoutHandler.postDelayed(mShowAnswerTask, delay);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4936)) {
            Timber.i("AbstractFlashcardViewer:: Question successfully shown for card id %d", mCurrentCard.getId());
        }
    }

    /**
     * Clean up the correct answer text, so it can be used for the comparison with the typed text
     *
     * @param answer The content of the field the text typed by the user is compared to.
     * @return The correct answer text, with actual HTML and media references removed, and HTML entities unescaped.
     */
    protected String cleanCorrectAnswer(String answer) {
        return TypedAnswer.cleanCorrectAnswer(answer);
    }

    /**
     * Clean up the typed answer text, so it can be used for the comparison with the correct answer
     *
     * @param answer The answer text typed by the user.
     * @return The typed answer text, cleaned up.
     */
    protected String cleanTypedAnswer(String answer) {
        if (!ListenerUtil.mutListener.listen(4938)) {
            if ((ListenerUtil.mutListener.listen(4937) ? (answer == null && "".equals(answer)) : (answer == null || "".equals(answer)))) {
                return "";
            }
        }
        return Utils.nfcNormalized(answer.trim());
    }

    protected void displayCardAnswer() {
        if (!ListenerUtil.mutListener.listen(4939)) {
            // #7294 Required in case the animation end action does not fire:
            actualHideEaseButtons();
        }
        if (!ListenerUtil.mutListener.listen(4940)) {
            Timber.d("displayCardAnswer()");
        }
        if (!ListenerUtil.mutListener.listen(4941)) {
            mMissingImageHandler.onCardSideChange();
        }
        if (!ListenerUtil.mutListener.listen(4942)) {
            // prevent answering (by e.g. gestures) before card is loaded
            if (mCurrentCard == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4944)) {
            // but sometimes failed to do so (e.g. if an OnKeyListener is attached).
            if (typeAnswer()) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (!ListenerUtil.mutListener.listen(4943)) {
                    inputMethodManager.hideSoftInputFromWindow(mAnswerField.getWindowToken(), 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4945)) {
            sDisplayAnswer = true;
        }
        String answer = mCurrentCard.a();
        if (!ListenerUtil.mutListener.listen(4946)) {
            mSoundPlayer.stopSounds();
        }
        if (!ListenerUtil.mutListener.listen(4947)) {
            answer = getCol().getMedia().escapeImages(answer);
        }
        if (!ListenerUtil.mutListener.listen(4948)) {
            mAnswerField.setVisibility(View.GONE);
        }
        // Clean up the user answer and the correct answer
        String userAnswer;
        if (mUseInputTag) {
            userAnswer = cleanTypedAnswer(mTypeInput);
        } else {
            userAnswer = cleanTypedAnswer(mAnswerField.getText().toString());
        }
        String correctAnswer = cleanCorrectAnswer(mTypeCorrect);
        if (!ListenerUtil.mutListener.listen(4949)) {
            Timber.d("correct answer = %s", correctAnswer);
        }
        if (!ListenerUtil.mutListener.listen(4950)) {
            Timber.d("user answer = %s", userAnswer);
        }
        if (!ListenerUtil.mutListener.listen(4951)) {
            answer = typeAnsAnswerFilter(answer, userAnswer, correctAnswer);
        }
        if (!ListenerUtil.mutListener.listen(4952)) {
            mIsSelecting = false;
        }
        if (!ListenerUtil.mutListener.listen(4953)) {
            updateCard(CardAppearance.enrichWithQADiv(answer, true));
        }
        if (!ListenerUtil.mutListener.listen(4954)) {
            displayAnswerBottomBar();
        }
        if (!ListenerUtil.mutListener.listen(4972)) {
            // If the user wants to show the next question automatically
            if (mUseTimer) {
                long delay = (ListenerUtil.mutListener.listen(4962) ? ((ListenerUtil.mutListener.listen(4958) ? (mWaitQuestionSecond % 1000) : (ListenerUtil.mutListener.listen(4957) ? (mWaitQuestionSecond / 1000) : (ListenerUtil.mutListener.listen(4956) ? (mWaitQuestionSecond - 1000) : (ListenerUtil.mutListener.listen(4955) ? (mWaitQuestionSecond + 1000) : (mWaitQuestionSecond * 1000))))) % mUseTimerDynamicMS) : (ListenerUtil.mutListener.listen(4961) ? ((ListenerUtil.mutListener.listen(4958) ? (mWaitQuestionSecond % 1000) : (ListenerUtil.mutListener.listen(4957) ? (mWaitQuestionSecond / 1000) : (ListenerUtil.mutListener.listen(4956) ? (mWaitQuestionSecond - 1000) : (ListenerUtil.mutListener.listen(4955) ? (mWaitQuestionSecond + 1000) : (mWaitQuestionSecond * 1000))))) / mUseTimerDynamicMS) : (ListenerUtil.mutListener.listen(4960) ? ((ListenerUtil.mutListener.listen(4958) ? (mWaitQuestionSecond % 1000) : (ListenerUtil.mutListener.listen(4957) ? (mWaitQuestionSecond / 1000) : (ListenerUtil.mutListener.listen(4956) ? (mWaitQuestionSecond - 1000) : (ListenerUtil.mutListener.listen(4955) ? (mWaitQuestionSecond + 1000) : (mWaitQuestionSecond * 1000))))) * mUseTimerDynamicMS) : (ListenerUtil.mutListener.listen(4959) ? ((ListenerUtil.mutListener.listen(4958) ? (mWaitQuestionSecond % 1000) : (ListenerUtil.mutListener.listen(4957) ? (mWaitQuestionSecond / 1000) : (ListenerUtil.mutListener.listen(4956) ? (mWaitQuestionSecond - 1000) : (ListenerUtil.mutListener.listen(4955) ? (mWaitQuestionSecond + 1000) : (mWaitQuestionSecond * 1000))))) - mUseTimerDynamicMS) : ((ListenerUtil.mutListener.listen(4958) ? (mWaitQuestionSecond % 1000) : (ListenerUtil.mutListener.listen(4957) ? (mWaitQuestionSecond / 1000) : (ListenerUtil.mutListener.listen(4956) ? (mWaitQuestionSecond - 1000) : (ListenerUtil.mutListener.listen(4955) ? (mWaitQuestionSecond + 1000) : (mWaitQuestionSecond * 1000))))) + mUseTimerDynamicMS)))));
                if (!ListenerUtil.mutListener.listen(4971)) {
                    if ((ListenerUtil.mutListener.listen(4967) ? (delay >= 0) : (ListenerUtil.mutListener.listen(4966) ? (delay <= 0) : (ListenerUtil.mutListener.listen(4965) ? (delay < 0) : (ListenerUtil.mutListener.listen(4964) ? (delay != 0) : (ListenerUtil.mutListener.listen(4963) ? (delay == 0) : (delay > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(4968)) {
                            mTimeoutHandler.removeCallbacks(mShowQuestionTask);
                        }
                        if (!ListenerUtil.mutListener.listen(4970)) {
                            if (!mSpeakText) {
                                if (!ListenerUtil.mutListener.listen(4969)) {
                                    mTimeoutHandler.postDelayed(mShowQuestionTask, delay);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Scroll the currently shown flashcard vertically
     *
     * @param dy amount to be scrolled
     */
    public void scrollCurrentCardBy(int dy) {
        if (!ListenerUtil.mutListener.listen(4973)) {
            processCardAction(cardWebView -> {
                if (dy != 0 && cardWebView.canScrollVertically(dy)) {
                    cardWebView.scrollBy(0, dy);
                }
            });
        }
    }

    /**
     * Tap onto the currently shown flashcard at position x and y
     *
     * @param x horizontal position of the event
     * @param y vertical position of the event
     */
    public void tapOnCurrentCard(int x, int y) {
        // assemble suitable ACTION_DOWN and ACTION_UP events and forward them to the card's handler
        MotionEvent eDown = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 1, 1, 0, 1, 1, 0, 0);
        if (!ListenerUtil.mutListener.listen(4974)) {
            processCardAction(cardWebView -> cardWebView.dispatchTouchEvent(eDown));
        }
        MotionEvent eUp = MotionEvent.obtain(eDown.getDownTime(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 1, 1, 0, 1, 1, 0, 0);
        if (!ListenerUtil.mutListener.listen(4975)) {
            processCardAction(cardWebView -> cardWebView.dispatchTouchEvent(eUp));
        }
    }

    /**
     * getAnswerFormat returns the answer part of this card's template as entered by user, without any parsing
     */
    public String getAnswerFormat() {
        Model model = mCurrentCard.model();
        JSONObject template;
        if (model.isStd()) {
            template = model.getJSONArray("tmpls").getJSONObject(mCurrentCard.getOrd());
        } else {
            template = model.getJSONArray("tmpls").getJSONObject(0);
        }
        return template.getString("afmt");
    }

    private void addAnswerSounds(String answer) {
        if (!ListenerUtil.mutListener.listen(4978)) {
            // additionally, this condition reduces computation time
            if (!mAnswerSoundsAdded) {
                String answerSoundSource = removeFrontSideAudio(answer);
                if (!ListenerUtil.mutListener.listen(4976)) {
                    mSoundPlayer.addSounds(mBaseUrl, answerSoundSource, SoundSide.ANSWER);
                }
                if (!ListenerUtil.mutListener.listen(4977)) {
                    mAnswerSoundsAdded = true;
                }
            }
        }
    }

    protected boolean isInNightMode() {
        return mCardAppearance.isNightMode();
    }

    private void updateCard(final String newContent) {
        if (!ListenerUtil.mutListener.listen(4979)) {
            Timber.d("updateCard()");
        }
        if (!ListenerUtil.mutListener.listen(4980)) {
            mUseTimerDynamicMS = 0;
        }
        if (!ListenerUtil.mutListener.listen(4982)) {
            // Add CSS for font color and font size
            if (mCurrentCard == null) {
                if (!ListenerUtil.mutListener.listen(4981)) {
                    processCardAction(cardWebView -> cardWebView.getSettings().setDefaultFontSize(calculateDynamicFontSize(newContent)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4991)) {
            if (sDisplayAnswer) {
                if (!ListenerUtil.mutListener.listen(4990)) {
                    addAnswerSounds(newContent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4983)) {
                    // leaving the card (such as when edited)
                    mSoundPlayer.resetSounds();
                }
                if (!ListenerUtil.mutListener.listen(4984)) {
                    mAnswerSoundsAdded = false;
                }
                if (!ListenerUtil.mutListener.listen(4985)) {
                    mSoundPlayer.addSounds(mBaseUrl, newContent, SoundSide.QUESTION);
                }
                if (!ListenerUtil.mutListener.listen(4989)) {
                    if ((ListenerUtil.mutListener.listen(4987) ? ((ListenerUtil.mutListener.listen(4986) ? (mUseTimer || !mAnswerSoundsAdded) : (mUseTimer && !mAnswerSoundsAdded)) || getConfigForCurrentCard().optBoolean("autoplay", false)) : ((ListenerUtil.mutListener.listen(4986) ? (mUseTimer || !mAnswerSoundsAdded) : (mUseTimer && !mAnswerSoundsAdded)) && getConfigForCurrentCard().optBoolean("autoplay", false)))) {
                        if (!ListenerUtil.mutListener.listen(4988)) {
                            addAnswerSounds(mCurrentCard.a());
                        }
                    }
                }
            }
        }
        String content = Sound.expandSounds(mBaseUrl, newContent);
        if (!ListenerUtil.mutListener.listen(4992)) {
            content = CardAppearance.fixBoldStyle(content);
        }
        if (!ListenerUtil.mutListener.listen(4993)) {
            Timber.v("content card = \n %s", content);
        }
        String style = mCardAppearance.getStyle();
        if (!ListenerUtil.mutListener.listen(4994)) {
            Timber.v("::style:: / %s", style);
        }
        // CSS class for card-specific styling
        String cardClass = mCardAppearance.getCardClass((ListenerUtil.mutListener.listen(4998) ? (mCurrentCard.getOrd() % 1) : (ListenerUtil.mutListener.listen(4997) ? (mCurrentCard.getOrd() / 1) : (ListenerUtil.mutListener.listen(4996) ? (mCurrentCard.getOrd() * 1) : (ListenerUtil.mutListener.listen(4995) ? (mCurrentCard.getOrd() - 1) : (mCurrentCard.getOrd() + 1))))), Themes.getCurrentTheme(this));
        if (!ListenerUtil.mutListener.listen(5000)) {
            if (MathJax.textContainsMathjax(content)) {
                if (!ListenerUtil.mutListener.listen(4999)) {
                    cardClass += " mathjax-needs-to-render";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5003)) {
            if (isInNightMode()) {
                if (!ListenerUtil.mutListener.listen(5002)) {
                    if (!mCardAppearance.hasUserDefinedNightMode(mCurrentCard)) {
                        if (!ListenerUtil.mutListener.listen(5001)) {
                            content = HtmlColors.invertColors(content);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5004)) {
            mCardContent = mCardTemplate.render(content, style, cardClass);
        }
        if (!ListenerUtil.mutListener.listen(5005)) {
            Timber.d("base url = %s", mBaseUrl);
        }
        if (!ListenerUtil.mutListener.listen(5008)) {
            if (AnkiDroidApp.getSharedPrefs(this).getBoolean("html_javascript_debugging", false)) {
                try {
                    try (FileOutputStream f = new FileOutputStream(new File(CollectionHelper.getCurrentAnkiDroidDirectory(this), "card.html"))) {
                        if (!ListenerUtil.mutListener.listen(5007)) {
                            f.write(mCardContent.getBytes());
                        }
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(5006)) {
                        Timber.d(e, "failed to save card");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5009)) {
            fillFlashcard();
        }
        if (!ListenerUtil.mutListener.listen(5010)) {
            // Play sounds if appropriate
            playSounds(false);
        }
    }

    /**
     * Plays sounds (or TTS, if configured) for currently shown side of card.
     *
     * @param doAudioReplay indicates an anki desktop-like replay call is desired, whose behavior is identical to
     *            pressing the keyboard shortcut R on the desktop
     */
    protected void playSounds(boolean doAudioReplay) {
        boolean replayQuestion = getConfigForCurrentCard().optBoolean("replayq", true);
        if (!ListenerUtil.mutListener.listen(5035)) {
            if ((ListenerUtil.mutListener.listen(5011) ? (getConfigForCurrentCard().optBoolean("autoplay", false) && doAudioReplay) : (getConfigForCurrentCard().optBoolean("autoplay", false) || doAudioReplay))) {
                // Use TTS if TTS preference enabled and no other sound source
                boolean useTTS = (ListenerUtil.mutListener.listen(5015) ? ((ListenerUtil.mutListener.listen(5013) ? (mSpeakText || !((ListenerUtil.mutListener.listen(5012) ? (sDisplayAnswer || mSoundPlayer.hasAnswer()) : (sDisplayAnswer && mSoundPlayer.hasAnswer())))) : (mSpeakText && !((ListenerUtil.mutListener.listen(5012) ? (sDisplayAnswer || mSoundPlayer.hasAnswer()) : (sDisplayAnswer && mSoundPlayer.hasAnswer()))))) || !((ListenerUtil.mutListener.listen(5014) ? (!sDisplayAnswer || mSoundPlayer.hasQuestion()) : (!sDisplayAnswer && mSoundPlayer.hasQuestion())))) : ((ListenerUtil.mutListener.listen(5013) ? (mSpeakText || !((ListenerUtil.mutListener.listen(5012) ? (sDisplayAnswer || mSoundPlayer.hasAnswer()) : (sDisplayAnswer && mSoundPlayer.hasAnswer())))) : (mSpeakText && !((ListenerUtil.mutListener.listen(5012) ? (sDisplayAnswer || mSoundPlayer.hasAnswer()) : (sDisplayAnswer && mSoundPlayer.hasAnswer()))))) && !((ListenerUtil.mutListener.listen(5014) ? (!sDisplayAnswer || mSoundPlayer.hasQuestion()) : (!sDisplayAnswer && mSoundPlayer.hasQuestion())))));
                if (!ListenerUtil.mutListener.listen(5034)) {
                    // We need to play the sounds from the proper side of the card
                    if (!useTTS) {
                        if (!ListenerUtil.mutListener.listen(5033)) {
                            // Text to speech not in effect here
                            if ((ListenerUtil.mutListener.listen(5025) ? ((ListenerUtil.mutListener.listen(5024) ? (doAudioReplay || replayQuestion) : (doAudioReplay && replayQuestion)) || sDisplayAnswer) : ((ListenerUtil.mutListener.listen(5024) ? (doAudioReplay || replayQuestion) : (doAudioReplay && replayQuestion)) && sDisplayAnswer))) {
                                if (!ListenerUtil.mutListener.listen(5032)) {
                                    // only when all of the above are true will question be played with answer, to match desktop
                                    playSounds(SoundSide.QUESTION_AND_ANSWER);
                                }
                            } else if (sDisplayAnswer) {
                                if (!ListenerUtil.mutListener.listen(5029)) {
                                    playSounds(SoundSide.ANSWER);
                                }
                                if (!ListenerUtil.mutListener.listen(5031)) {
                                    if (mUseTimer) {
                                        if (!ListenerUtil.mutListener.listen(5030)) {
                                            mUseTimerDynamicMS = mSoundPlayer.getSoundsLength(SoundSide.ANSWER);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5026)) {
                                    // question is displayed
                                    playSounds(SoundSide.QUESTION);
                                }
                                if (!ListenerUtil.mutListener.listen(5028)) {
                                    // If the user wants to show the answer automatically
                                    if (mUseTimer) {
                                        if (!ListenerUtil.mutListener.listen(5027)) {
                                            mUseTimerDynamicMS = mSoundPlayer.getSoundsLength(SoundSide.QUESTION_AND_ANSWER);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5023)) {
                            // If the question is displayed or if the question should be replayed, read the question
                            if (mTtsInitialized) {
                                if (!ListenerUtil.mutListener.listen(5020)) {
                                    if ((ListenerUtil.mutListener.listen(5018) ? (!sDisplayAnswer && (ListenerUtil.mutListener.listen(5017) ? (doAudioReplay || replayQuestion) : (doAudioReplay && replayQuestion))) : (!sDisplayAnswer || (ListenerUtil.mutListener.listen(5017) ? (doAudioReplay || replayQuestion) : (doAudioReplay && replayQuestion))))) {
                                        if (!ListenerUtil.mutListener.listen(5019)) {
                                            readCardText(mCurrentCard, SoundSide.QUESTION);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(5022)) {
                                    if (sDisplayAnswer) {
                                        if (!ListenerUtil.mutListener.listen(5021)) {
                                            readCardText(mCurrentCard, SoundSide.ANSWER);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5016)) {
                                    mReplayOnTtsInit = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void playSounds(SoundSide questionAndAnswer) {
        if (!ListenerUtil.mutListener.listen(5036)) {
            mSoundPlayer.playSounds(questionAndAnswer, getSoundErrorListener());
        }
    }

    private Sound.OnErrorListener getSoundErrorListener() {
        return (mp, what, extra, path) -> {
            Timber.w("Media Error: (%d, %d). Calling OnCompletionListener", what, extra);
            try {
                File file = new File(path);
                if (!file.exists()) {
                    mMissingImageHandler.processMissingSound(file, this::displayCouldNotFindMediaSnackbar);
                }
            } catch (Exception e) {
                return false;
            }
            return false;
        };
    }

    /**
     * Reads the text (using TTS) for the given side of a card.
     *
     * @param card     The card to play TTS for
     * @param cardSide The side of the current card to play TTS for
     */
    private void readCardText(final Card card, final SoundSide cardSide) {
        final String cardSideContent;
        if (SoundSide.QUESTION == cardSide) {
            cardSideContent = card.q(true);
        } else if (SoundSide.ANSWER == cardSide) {
            cardSideContent = card.getPureAnswer();
        } else {
            if (!ListenerUtil.mutListener.listen(5037)) {
                Timber.w("Unrecognised cardSide");
            }
            return;
        }
        String clozeReplacement = this.getString(R.string.reviewer_tts_cloze_spoken_replacement);
        if (!ListenerUtil.mutListener.listen(5038)) {
            ReadText.readCardSide(cardSide, cardSideContent, getDeckIdForCard(card), card.getOrd(), clozeReplacement);
        }
    }

    /**
     * Shows the dialogue for selecting TTS for the current card and cardside.
     */
    protected void showSelectTtsDialogue() {
        if (!ListenerUtil.mutListener.listen(5042)) {
            if (mTtsInitialized) {
                if (!ListenerUtil.mutListener.listen(5041)) {
                    if (!sDisplayAnswer) {
                        if (!ListenerUtil.mutListener.listen(5040)) {
                            ReadText.selectTts(getTextForTts(mCurrentCard.q(true)), getDeckIdForCard(mCurrentCard), mCurrentCard.getOrd(), SoundSide.QUESTION);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5039)) {
                            ReadText.selectTts(getTextForTts(mCurrentCard.getPureAnswer()), getDeckIdForCard(mCurrentCard), mCurrentCard.getOrd(), SoundSide.ANSWER);
                        }
                    }
                }
            }
        }
    }

    private String getTextForTts(String text) {
        String clozeReplacement = this.getString(R.string.reviewer_tts_cloze_spoken_replacement);
        String clozeReplaced = text.replace(TemplateFilters.CLOZE_DELETION_REPLACEMENT, clozeReplacement);
        return Utils.stripHTML(clozeReplaced);
    }

    /**
     * Returns the configuration for the current {@link Card}.
     *
     * @return The configuration for the current {@link Card}
     */
    private DeckConfig getConfigForCurrentCard() {
        return getCol().getDecks().confForDid(getDeckIdForCard(mCurrentCard));
    }

    /**
     * Returns the deck ID of the given {@link Card}.
     *
     * @param card The {@link Card} to get the deck ID
     * @return The deck ID of the {@link Card}
     */
    private static long getDeckIdForCard(final Card card) {
        // else use the direct deck ID (in case of a 'normal' deck.
        return (ListenerUtil.mutListener.listen(5047) ? (card.getODid() >= 0) : (ListenerUtil.mutListener.listen(5046) ? (card.getODid() <= 0) : (ListenerUtil.mutListener.listen(5045) ? (card.getODid() > 0) : (ListenerUtil.mutListener.listen(5044) ? (card.getODid() < 0) : (ListenerUtil.mutListener.listen(5043) ? (card.getODid() != 0) : (card.getODid() == 0)))))) ? card.getDid() : card.getODid();
    }

    public void fillFlashcard() {
        if (!ListenerUtil.mutListener.listen(5048)) {
            Timber.d("fillFlashcard()");
        }
        if (!ListenerUtil.mutListener.listen(5049)) {
            Timber.d("base url = %s", mBaseUrl);
        }
        if (!ListenerUtil.mutListener.listen(5051)) {
            if (mCardContent == null) {
                if (!ListenerUtil.mutListener.listen(5050)) {
                    Timber.w("fillFlashCard() called with no card content");
                }
                return;
            }
        }
        final String cardContent = mCardContent;
        if (!ListenerUtil.mutListener.listen(5052)) {
            processCardAction(cardWebView -> loadContentIntoCard(cardWebView, cardContent));
        }
        if (!ListenerUtil.mutListener.listen(5053)) {
            mGestureDetectorImpl.onFillFlashcard();
        }
        if (!ListenerUtil.mutListener.listen(5056)) {
            if ((ListenerUtil.mutListener.listen(5054) ? (mShowTimer || mCardTimer.getVisibility() == View.INVISIBLE) : (mShowTimer && mCardTimer.getVisibility() == View.INVISIBLE))) {
                if (!ListenerUtil.mutListener.listen(5055)) {
                    switchTopBarVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5058)) {
            if (!sDisplayAnswer) {
                if (!ListenerUtil.mutListener.listen(5057)) {
                    updateForNewCard();
                }
            }
        }
    }

    private void loadContentIntoCard(WebView card, String content) {
        if (!ListenerUtil.mutListener.listen(5061)) {
            if (card != null) {
                if (!ListenerUtil.mutListener.listen(5059)) {
                    card.getSettings().setMediaPlaybackRequiresUserGesture(!getConfigForCurrentCard().optBoolean("autoplay"));
                }
                if (!ListenerUtil.mutListener.listen(5060)) {
                    card.loadDataWithBaseURL(mViewerUrl, content, "text/html", "utf-8", null);
                }
            }
        }
    }

    public static Card getEditorCard() {
        return sEditorCard;
    }

    /**
     * @return true if the AnkiDroid preference for writing answer is true and if the Anki Deck CardLayout specifies a
     *         field to query
     */
    private boolean typeAnswer() {
        return (ListenerUtil.mutListener.listen(5062) ? (!mUseInputTag || null != mTypeCorrect) : (!mUseInputTag && null != mTypeCorrect));
    }

    private void unblockControls() {
        if (!ListenerUtil.mutListener.listen(5063)) {
            mControlBlocked = ControlBlock.UNBLOCKED;
        }
        if (!ListenerUtil.mutListener.listen(5064)) {
            mCardFrame.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(5065)) {
            mFlipCardLayout.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(5086)) {
            switch(mCurrentEase) {
                case EASE_1:
                    if (!ListenerUtil.mutListener.listen(5066)) {
                        mEase1Layout.setClickable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5067)) {
                        mEase2Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5068)) {
                        mEase3Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5069)) {
                        mEase4Layout.setEnabled(true);
                    }
                    break;
                case EASE_2:
                    if (!ListenerUtil.mutListener.listen(5070)) {
                        mEase1Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5071)) {
                        mEase2Layout.setClickable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5072)) {
                        mEase3Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5073)) {
                        mEase4Layout.setEnabled(true);
                    }
                    break;
                case EASE_3:
                    if (!ListenerUtil.mutListener.listen(5074)) {
                        mEase1Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5075)) {
                        mEase2Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5076)) {
                        mEase3Layout.setClickable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5077)) {
                        mEase4Layout.setEnabled(true);
                    }
                    break;
                case EASE_4:
                    if (!ListenerUtil.mutListener.listen(5078)) {
                        mEase1Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5079)) {
                        mEase2Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5080)) {
                        mEase3Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5081)) {
                        mEase4Layout.setClickable(true);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(5082)) {
                        mEase1Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5083)) {
                        mEase2Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5084)) {
                        mEase3Layout.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(5085)) {
                        mEase4Layout.setEnabled(true);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(5089)) {
            if ((ListenerUtil.mutListener.listen(5087) ? (mPrefWhiteboard || mWhiteboard != null) : (mPrefWhiteboard && mWhiteboard != null))) {
                if (!ListenerUtil.mutListener.listen(5088)) {
                    mWhiteboard.setEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5091)) {
            if (typeAnswer()) {
                if (!ListenerUtil.mutListener.listen(5090)) {
                    mAnswerField.setEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5092)) {
            mTouchLayer.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5093)) {
            mInAnswer = false;
        }
        if (!ListenerUtil.mutListener.listen(5094)) {
            invalidateOptionsMenu();
        }
    }

    /**
     * @param quick Whether we expect the control to come back quickly
     */
    @VisibleForTesting
    protected void blockControls(boolean quick) {
        if (!ListenerUtil.mutListener.listen(5097)) {
            if (quick) {
                if (!ListenerUtil.mutListener.listen(5096)) {
                    mControlBlocked = ControlBlock.QUICK;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5095)) {
                    mControlBlocked = ControlBlock.SLOW;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5098)) {
            mCardFrame.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(5099)) {
            mFlipCardLayout.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(5100)) {
            mTouchLayer.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5101)) {
            mInAnswer = true;
        }
        if (!ListenerUtil.mutListener.listen(5122)) {
            switch(mCurrentEase) {
                case EASE_1:
                    if (!ListenerUtil.mutListener.listen(5102)) {
                        mEase1Layout.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5103)) {
                        mEase2Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5104)) {
                        mEase3Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5105)) {
                        mEase4Layout.setEnabled(false);
                    }
                    break;
                case EASE_2:
                    if (!ListenerUtil.mutListener.listen(5106)) {
                        mEase1Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5107)) {
                        mEase2Layout.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5108)) {
                        mEase3Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5109)) {
                        mEase4Layout.setEnabled(false);
                    }
                    break;
                case EASE_3:
                    if (!ListenerUtil.mutListener.listen(5110)) {
                        mEase1Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5111)) {
                        mEase2Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5112)) {
                        mEase3Layout.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5113)) {
                        mEase4Layout.setEnabled(false);
                    }
                    break;
                case EASE_4:
                    if (!ListenerUtil.mutListener.listen(5114)) {
                        mEase1Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5115)) {
                        mEase2Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5116)) {
                        mEase3Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5117)) {
                        mEase4Layout.setClickable(false);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(5118)) {
                        mEase1Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5119)) {
                        mEase2Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5120)) {
                        mEase3Layout.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5121)) {
                        mEase4Layout.setEnabled(false);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(5125)) {
            if ((ListenerUtil.mutListener.listen(5123) ? (mPrefWhiteboard || mWhiteboard != null) : (mPrefWhiteboard && mWhiteboard != null))) {
                if (!ListenerUtil.mutListener.listen(5124)) {
                    mWhiteboard.setEnabled(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5127)) {
            if (typeAnswer()) {
                if (!ListenerUtil.mutListener.listen(5126)) {
                    mAnswerField.setEnabled(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5128)) {
            invalidateOptionsMenu();
        }
    }

    /**
     * Select Text in the webview and automatically sends the selected text to the clipboard. From
     * http://cosmez.blogspot.com/2010/04/webview-emulateshiftheld-on-android.html
     */
    // Tracked separately in Github as #5024
    @SuppressWarnings("deprecation")
    private void selectAndCopyText() {
        try {
            KeyEvent shiftPressEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT, 0, 0);
            if (!ListenerUtil.mutListener.listen(5129)) {
                processCardAction(shiftPressEvent::dispatch);
            }
            if (!ListenerUtil.mutListener.listen(5130)) {
                shiftPressEvent.isShiftPressed();
            }
            if (!ListenerUtil.mutListener.listen(5131)) {
                mIsSelecting = true;
            }
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public boolean executeCommand(@ViewerCommandDef int which) {
        if ((ListenerUtil.mutListener.listen(5137) ? (isControlBlocked() || (ListenerUtil.mutListener.listen(5136) ? (which >= COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5135) ? (which <= COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5134) ? (which > COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5133) ? (which < COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5132) ? (which == COMMAND_EXIT) : (which != COMMAND_EXIT))))))) : (isControlBlocked() && (ListenerUtil.mutListener.listen(5136) ? (which >= COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5135) ? (which <= COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5134) ? (which > COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5133) ? (which < COMMAND_EXIT) : (ListenerUtil.mutListener.listen(5132) ? (which == COMMAND_EXIT) : (which != COMMAND_EXIT))))))))) {
            return false;
        }
        switch(which) {
            case COMMAND_NOTHING:
                return true;
            case COMMAND_SHOW_ANSWER:
                if (sDisplayAnswer) {
                    return false;
                }
                if (!ListenerUtil.mutListener.listen(5138)) {
                    displayCardAnswer();
                }
                return true;
            case COMMAND_FLIP_OR_ANSWER_EASE1:
                if (!ListenerUtil.mutListener.listen(5139)) {
                    flipOrAnswerCard(EASE_1);
                }
                return true;
            case COMMAND_FLIP_OR_ANSWER_EASE2:
                if (!ListenerUtil.mutListener.listen(5140)) {
                    flipOrAnswerCard(EASE_2);
                }
                return true;
            case COMMAND_FLIP_OR_ANSWER_EASE3:
                if (!ListenerUtil.mutListener.listen(5141)) {
                    flipOrAnswerCard(EASE_3);
                }
                return true;
            case COMMAND_FLIP_OR_ANSWER_EASE4:
                if (!ListenerUtil.mutListener.listen(5142)) {
                    flipOrAnswerCard(EASE_4);
                }
                return true;
            case COMMAND_FLIP_OR_ANSWER_RECOMMENDED:
                if (!ListenerUtil.mutListener.listen(5143)) {
                    flipOrAnswerCard(getRecommendedEase(false));
                }
                return true;
            case COMMAND_FLIP_OR_ANSWER_BETTER_THAN_RECOMMENDED:
                if (!ListenerUtil.mutListener.listen(5144)) {
                    flipOrAnswerCard(getRecommendedEase(true));
                }
                return true;
            case COMMAND_EXIT:
                if (!ListenerUtil.mutListener.listen(5145)) {
                    closeReviewer(RESULT_DEFAULT, false);
                }
                return true;
            case COMMAND_UNDO:
                if (!isUndoAvailable()) {
                    return false;
                }
                if (!ListenerUtil.mutListener.listen(5146)) {
                    undo();
                }
                return true;
            case COMMAND_EDIT:
                if (!ListenerUtil.mutListener.listen(5147)) {
                    editCard();
                }
                return true;
            case COMMAND_CARD_INFO:
                if (!ListenerUtil.mutListener.listen(5148)) {
                    openCardInfo();
                }
                return true;
            case COMMAND_TAG:
                if (!ListenerUtil.mutListener.listen(5149)) {
                    showTagsDialog();
                }
                return true;
            case COMMAND_MARK:
                if (!ListenerUtil.mutListener.listen(5150)) {
                    onMark(mCurrentCard);
                }
                return true;
            case COMMAND_LOOKUP:
                if (!ListenerUtil.mutListener.listen(5151)) {
                    lookUpOrSelectText();
                }
                return true;
            case COMMAND_BURY_CARD:
                if (!ListenerUtil.mutListener.listen(5152)) {
                    dismiss(Collection.DismissType.BURY_CARD);
                }
                return true;
            case COMMAND_BURY_NOTE:
                if (!ListenerUtil.mutListener.listen(5153)) {
                    dismiss(Collection.DismissType.BURY_NOTE);
                }
                return true;
            case COMMAND_SUSPEND_CARD:
                if (!ListenerUtil.mutListener.listen(5154)) {
                    dismiss(Collection.DismissType.SUSPEND_CARD);
                }
                return true;
            case COMMAND_SUSPEND_NOTE:
                if (!ListenerUtil.mutListener.listen(5155)) {
                    dismiss(Collection.DismissType.SUSPEND_NOTE);
                }
                return true;
            case COMMAND_DELETE:
                if (!ListenerUtil.mutListener.listen(5156)) {
                    showDeleteNoteDialog();
                }
                return true;
            case COMMAND_PLAY_MEDIA:
                if (!ListenerUtil.mutListener.listen(5157)) {
                    playSounds(true);
                }
                return true;
            case COMMAND_TOGGLE_FLAG_RED:
                if (!ListenerUtil.mutListener.listen(5158)) {
                    toggleFlag(FLAG_RED);
                }
                return true;
            case COMMAND_TOGGLE_FLAG_ORANGE:
                if (!ListenerUtil.mutListener.listen(5159)) {
                    toggleFlag(FLAG_ORANGE);
                }
                return true;
            case COMMAND_TOGGLE_FLAG_GREEN:
                if (!ListenerUtil.mutListener.listen(5160)) {
                    toggleFlag(FLAG_GREEN);
                }
                return true;
            case COMMAND_TOGGLE_FLAG_BLUE:
                if (!ListenerUtil.mutListener.listen(5161)) {
                    toggleFlag(FLAG_BLUE);
                }
                return true;
            case COMMAND_UNSET_FLAG:
                if (!ListenerUtil.mutListener.listen(5162)) {
                    onFlag(mCurrentCard, FLAG_NONE);
                }
                return true;
            case COMMAND_ANSWER_FIRST_BUTTON:
                return answerCardIfVisible(Consts.BUTTON_ONE);
            case COMMAND_ANSWER_SECOND_BUTTON:
                return answerCardIfVisible(Consts.BUTTON_TWO);
            case COMMAND_ANSWER_THIRD_BUTTON:
                return answerCardIfVisible(Consts.BUTTON_THREE);
            case COMMAND_ANSWER_FOURTH_BUTTON:
                return answerCardIfVisible(Consts.BUTTON_FOUR);
            case COMMAND_ANSWER_RECOMMENDED:
                return answerCardIfVisible(getRecommendedEase(false));
            case COMMAND_PAGE_UP:
                if (!ListenerUtil.mutListener.listen(5163)) {
                    onPageUp();
                }
                return true;
            case COMMAND_PAGE_DOWN:
                if (!ListenerUtil.mutListener.listen(5164)) {
                    onPageDown();
                }
                return true;
            case COMMAND_ABORT_AND_SYNC:
                if (!ListenerUtil.mutListener.listen(5165)) {
                    abortAndSync();
                }
                return true;
            case COMMAND_RECORD_VOICE:
                if (!ListenerUtil.mutListener.listen(5166)) {
                    recordVoice();
                }
                return true;
            case COMMAND_REPLAY_VOICE:
                if (!ListenerUtil.mutListener.listen(5167)) {
                    replayVoice();
                }
                return true;
            case COMMAND_TOGGLE_WHITEBOARD:
                if (!ListenerUtil.mutListener.listen(5168)) {
                    toggleWhiteboard();
                }
                return true;
            default:
                if (!ListenerUtil.mutListener.listen(5169)) {
                    Timber.w("Unknown command requested: %s", which);
                }
                return false;
        }
    }

    protected void replayVoice() {
    }

    protected void recordVoice() {
    }

    protected void toggleWhiteboard() {
    }

    private void abortAndSync() {
        if (!ListenerUtil.mutListener.listen(5170)) {
            closeReviewer(RESULT_ABORT_AND_SYNC, true);
        }
    }

    protected void openCardInfo() {
        if (!ListenerUtil.mutListener.listen(5172)) {
            if (mCurrentCard == null) {
                if (!ListenerUtil.mutListener.listen(5171)) {
                    UIUtils.showThemedToast(this, getString(R.string.multimedia_editor_something_wrong), true);
                }
                return;
            }
        }
        Intent intent = new Intent(this, CardInfo.class);
        if (!ListenerUtil.mutListener.listen(5173)) {
            intent.putExtra("cardId", mCurrentCard.getId());
        }
        if (!ListenerUtil.mutListener.listen(5174)) {
            startActivityWithAnimation(intent, FADE);
        }
    }

    /**
     * Displays a snackbar which does not obscure the answer buttons
     */
    protected void showSnackbar(String mainText, @StringRes int buttonText, OnClickListener onClickListener) {
        Snackbar sb = UIUtils.getSnackbar(this, mainText, Snackbar.LENGTH_LONG, buttonText, onClickListener, mCardWebView, null);
        View easeButtons = findViewById(R.id.answer_options_layout);
        View previewButtons = findViewById(R.id.preview_buttons_layout);
        View upperView = (ListenerUtil.mutListener.listen(5175) ? (previewButtons != null || previewButtons.getVisibility() != View.GONE) : (previewButtons != null && previewButtons.getVisibility() != View.GONE)) ? previewButtons : easeButtons;
        if (!ListenerUtil.mutListener.listen(5181)) {
            if ((ListenerUtil.mutListener.listen(5176) ? (upperView != null || upperView.getVisibility() != View.GONE) : (upperView != null && upperView.getVisibility() != View.GONE))) {
                View sbView = sb.getView();
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) sbView.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(5177)) {
                    layoutParams.setAnchorId(upperView.getId());
                }
                if (!ListenerUtil.mutListener.listen(5178)) {
                    layoutParams.anchorGravity = Gravity.TOP;
                }
                if (!ListenerUtil.mutListener.listen(5179)) {
                    layoutParams.gravity = Gravity.TOP;
                }
                if (!ListenerUtil.mutListener.listen(5180)) {
                    sbView.setLayoutParams(layoutParams);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5182)) {
            sb.show();
        }
    }

    private void onPageUp() {
        if (!ListenerUtil.mutListener.listen(5183)) {
            // pageUp performs a half scroll, we want a full page
            processCardAction(cardWebView -> {
                cardWebView.pageUp(false);
                cardWebView.pageUp(false);
            });
        }
    }

    private void onPageDown() {
        if (!ListenerUtil.mutListener.listen(5184)) {
            processCardAction(cardWebView -> {
                cardWebView.pageDown(false);
                cardWebView.pageDown(false);
            });
        }
    }

    private void toggleFlag(@FlagDef int flag) {
        if (!ListenerUtil.mutListener.listen(5194)) {
            if ((ListenerUtil.mutListener.listen(5189) ? (mCurrentCard.userFlag() >= flag) : (ListenerUtil.mutListener.listen(5188) ? (mCurrentCard.userFlag() <= flag) : (ListenerUtil.mutListener.listen(5187) ? (mCurrentCard.userFlag() > flag) : (ListenerUtil.mutListener.listen(5186) ? (mCurrentCard.userFlag() < flag) : (ListenerUtil.mutListener.listen(5185) ? (mCurrentCard.userFlag() != flag) : (mCurrentCard.userFlag() == flag))))))) {
                if (!ListenerUtil.mutListener.listen(5192)) {
                    Timber.i("Toggle flag: unsetting flag");
                }
                if (!ListenerUtil.mutListener.listen(5193)) {
                    onFlag(mCurrentCard, FLAG_NONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5190)) {
                    Timber.i("Toggle flag: Setting flag to %d", flag);
                }
                if (!ListenerUtil.mutListener.listen(5191)) {
                    onFlag(mCurrentCard, flag);
                }
            }
        }
    }

    private boolean answerCardIfVisible(@Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(5195)) {
            if (!sDisplayAnswer) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5196)) {
            performClickWithVisualFeedback(ease);
        }
        return true;
    }

    protected void performClickWithVisualFeedback(int ease) {
        if (!ListenerUtil.mutListener.listen(5201)) {
            // Delay could potentially be lower - testing with 20 left a visible "click"
            switch(ease) {
                case EASE_1:
                    if (!ListenerUtil.mutListener.listen(5197)) {
                        performClickWithVisualFeedback(mEase1Layout);
                    }
                    break;
                case EASE_2:
                    if (!ListenerUtil.mutListener.listen(5198)) {
                        performClickWithVisualFeedback(mEase2Layout);
                    }
                    break;
                case EASE_3:
                    if (!ListenerUtil.mutListener.listen(5199)) {
                        performClickWithVisualFeedback(mEase3Layout);
                    }
                    break;
                case EASE_4:
                    if (!ListenerUtil.mutListener.listen(5200)) {
                        performClickWithVisualFeedback(mEase4Layout);
                    }
                    break;
            }
        }
    }

    private void performClickWithVisualFeedback(LinearLayout easeLayout) {
        if (!ListenerUtil.mutListener.listen(5202)) {
            easeLayout.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(5203)) {
            easeLayout.postDelayed(easeLayout::performClick, 20);
        }
    }

    @VisibleForTesting
    protected boolean isUndoAvailable() {
        return getCol().undoAvailable();
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for debugging your javascript.
     */
    public static final class AnkiDroidWebChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            if (!ListenerUtil.mutListener.listen(5204)) {
                Timber.i("AbstractFlashcardViewer:: onJsAlert: %s", message);
            }
            if (!ListenerUtil.mutListener.listen(5205)) {
                result.confirm();
            }
            return true;
        }
    }

    protected void closeReviewer(int result, boolean saveDeck) {
        if (!ListenerUtil.mutListener.listen(5207)) {
            // Stop the mic recording if still pending
            if (mMicToolBar != null) {
                if (!ListenerUtil.mutListener.listen(5206)) {
                    mMicToolBar.notifyStopRecord();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5210)) {
            // Remove the temporary audio file
            if (mTempAudioPath != null) {
                File tempAudioPathToDelete = new File(mTempAudioPath);
                if (!ListenerUtil.mutListener.listen(5209)) {
                    if (tempAudioPathToDelete.exists()) {
                        if (!ListenerUtil.mutListener.listen(5208)) {
                            tempAudioPathToDelete.delete();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5211)) {
            mTimeoutHandler.removeCallbacks(mShowAnswerTask);
        }
        if (!ListenerUtil.mutListener.listen(5212)) {
            mTimeoutHandler.removeCallbacks(mShowQuestionTask);
        }
        if (!ListenerUtil.mutListener.listen(5213)) {
            mTimerHandler.removeCallbacks(removeChosenAnswerText);
        }
        if (!ListenerUtil.mutListener.listen(5214)) {
            longClickHandler.removeCallbacks(longClickTestRunnable);
        }
        if (!ListenerUtil.mutListener.listen(5215)) {
            longClickHandler.removeCallbacks(startLongClickAction);
        }
        if (!ListenerUtil.mutListener.listen(5216)) {
            AbstractFlashcardViewer.this.setResult(result);
        }
        if (!ListenerUtil.mutListener.listen(5218)) {
            if (saveDeck) {
                if (!ListenerUtil.mutListener.listen(5217)) {
                    UIUtils.saveCollectionInBackground();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5219)) {
            finishWithAnimation(RIGHT);
        }
    }

    protected void refreshActionBar() {
        if (!ListenerUtil.mutListener.listen(5220)) {
            supportInvalidateOptionsMenu();
        }
    }

    /**
     * Fixing bug 720: <input> focus, thanks to pablomouzo on android issue 7189
     */
    class MyWebView extends WebView {

        public MyWebView(Context context) {
            super(context);
        }

        @Override
        public void loadDataWithBaseURL(@Nullable String baseUrl, String data, @Nullable String mimeType, @Nullable String encoding, @Nullable String historyUrl) {
            if (!ListenerUtil.mutListener.listen(5223)) {
                if (!AbstractFlashcardViewer.this.isDestroyed()) {
                    if (!ListenerUtil.mutListener.listen(5222)) {
                        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(5221)) {
                        Timber.w("Not loading card - Activity is in the process of being destroyed.");
                    }
                }
            }
        }

        @Override
        protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
            if (!ListenerUtil.mutListener.listen(5224)) {
                super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
            }
            if (!ListenerUtil.mutListener.listen(5244)) {
                if ((ListenerUtil.mutListener.listen(5237) ? (Math.abs((ListenerUtil.mutListener.listen(5228) ? (horiz % oldHoriz) : (ListenerUtil.mutListener.listen(5227) ? (horiz / oldHoriz) : (ListenerUtil.mutListener.listen(5226) ? (horiz * oldHoriz) : (ListenerUtil.mutListener.listen(5225) ? (horiz + oldHoriz) : (horiz - oldHoriz)))))) >= Math.abs((ListenerUtil.mutListener.listen(5232) ? (vert % oldVert) : (ListenerUtil.mutListener.listen(5231) ? (vert / oldVert) : (ListenerUtil.mutListener.listen(5230) ? (vert * oldVert) : (ListenerUtil.mutListener.listen(5229) ? (vert + oldVert) : (vert - oldVert))))))) : (ListenerUtil.mutListener.listen(5236) ? (Math.abs((ListenerUtil.mutListener.listen(5228) ? (horiz % oldHoriz) : (ListenerUtil.mutListener.listen(5227) ? (horiz / oldHoriz) : (ListenerUtil.mutListener.listen(5226) ? (horiz * oldHoriz) : (ListenerUtil.mutListener.listen(5225) ? (horiz + oldHoriz) : (horiz - oldHoriz)))))) <= Math.abs((ListenerUtil.mutListener.listen(5232) ? (vert % oldVert) : (ListenerUtil.mutListener.listen(5231) ? (vert / oldVert) : (ListenerUtil.mutListener.listen(5230) ? (vert * oldVert) : (ListenerUtil.mutListener.listen(5229) ? (vert + oldVert) : (vert - oldVert))))))) : (ListenerUtil.mutListener.listen(5235) ? (Math.abs((ListenerUtil.mutListener.listen(5228) ? (horiz % oldHoriz) : (ListenerUtil.mutListener.listen(5227) ? (horiz / oldHoriz) : (ListenerUtil.mutListener.listen(5226) ? (horiz * oldHoriz) : (ListenerUtil.mutListener.listen(5225) ? (horiz + oldHoriz) : (horiz - oldHoriz)))))) < Math.abs((ListenerUtil.mutListener.listen(5232) ? (vert % oldVert) : (ListenerUtil.mutListener.listen(5231) ? (vert / oldVert) : (ListenerUtil.mutListener.listen(5230) ? (vert * oldVert) : (ListenerUtil.mutListener.listen(5229) ? (vert + oldVert) : (vert - oldVert))))))) : (ListenerUtil.mutListener.listen(5234) ? (Math.abs((ListenerUtil.mutListener.listen(5228) ? (horiz % oldHoriz) : (ListenerUtil.mutListener.listen(5227) ? (horiz / oldHoriz) : (ListenerUtil.mutListener.listen(5226) ? (horiz * oldHoriz) : (ListenerUtil.mutListener.listen(5225) ? (horiz + oldHoriz) : (horiz - oldHoriz)))))) != Math.abs((ListenerUtil.mutListener.listen(5232) ? (vert % oldVert) : (ListenerUtil.mutListener.listen(5231) ? (vert / oldVert) : (ListenerUtil.mutListener.listen(5230) ? (vert * oldVert) : (ListenerUtil.mutListener.listen(5229) ? (vert + oldVert) : (vert - oldVert))))))) : (ListenerUtil.mutListener.listen(5233) ? (Math.abs((ListenerUtil.mutListener.listen(5228) ? (horiz % oldHoriz) : (ListenerUtil.mutListener.listen(5227) ? (horiz / oldHoriz) : (ListenerUtil.mutListener.listen(5226) ? (horiz * oldHoriz) : (ListenerUtil.mutListener.listen(5225) ? (horiz + oldHoriz) : (horiz - oldHoriz)))))) == Math.abs((ListenerUtil.mutListener.listen(5232) ? (vert % oldVert) : (ListenerUtil.mutListener.listen(5231) ? (vert / oldVert) : (ListenerUtil.mutListener.listen(5230) ? (vert * oldVert) : (ListenerUtil.mutListener.listen(5229) ? (vert + oldVert) : (vert - oldVert))))))) : (Math.abs((ListenerUtil.mutListener.listen(5228) ? (horiz % oldHoriz) : (ListenerUtil.mutListener.listen(5227) ? (horiz / oldHoriz) : (ListenerUtil.mutListener.listen(5226) ? (horiz * oldHoriz) : (ListenerUtil.mutListener.listen(5225) ? (horiz + oldHoriz) : (horiz - oldHoriz)))))) > Math.abs((ListenerUtil.mutListener.listen(5232) ? (vert % oldVert) : (ListenerUtil.mutListener.listen(5231) ? (vert / oldVert) : (ListenerUtil.mutListener.listen(5230) ? (vert * oldVert) : (ListenerUtil.mutListener.listen(5229) ? (vert + oldVert) : (vert - oldVert))))))))))))) {
                    if (!ListenerUtil.mutListener.listen(5241)) {
                        mIsXScrolling = true;
                    }
                    if (!ListenerUtil.mutListener.listen(5242)) {
                        scrollHandler.removeCallbacks(scrollXRunnable);
                    }
                    if (!ListenerUtil.mutListener.listen(5243)) {
                        scrollHandler.postDelayed(scrollXRunnable, 300);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(5238)) {
                        mIsYScrolling = true;
                    }
                    if (!ListenerUtil.mutListener.listen(5239)) {
                        scrollHandler.removeCallbacks(scrollYRunnable);
                    }
                    if (!ListenerUtil.mutListener.listen(5240)) {
                        scrollHandler.postDelayed(scrollYRunnable, 300);
                    }
                }
            }
        }

        private final Handler scrollHandler = new Handler();

        private final Runnable scrollXRunnable = () -> mIsXScrolling = false;

        private final Runnable scrollYRunnable = () -> mIsYScrolling = false;
    }

    class MyGestureDetector extends SimpleOnGestureListener {

        // Android design spec for the size of the status bar.
        private static final int NO_GESTURE_BORDER_DIP = 24;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (!ListenerUtil.mutListener.listen(5245)) {
                Timber.d("onFling");
            }
            if (!ListenerUtil.mutListener.listen(5247)) {
                // it would be lovely to use e1.getEdgeFlags(), but alas, it doesn't work.
                if (isTouchingEdge(e1)) {
                    if (!ListenerUtil.mutListener.listen(5246)) {
                        Timber.d("ignoring edge fling");
                    }
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(5248)) {
                // Go back to immersive mode if the user had temporarily exited it (and then execute swipe gesture)
                AbstractFlashcardViewer.this.onFling();
            }
            if (!ListenerUtil.mutListener.listen(5320)) {
                if (mGesturesEnabled) {
                    try {
                        float dy = (ListenerUtil.mutListener.listen(5253) ? (e2.getY() % e1.getY()) : (ListenerUtil.mutListener.listen(5252) ? (e2.getY() / e1.getY()) : (ListenerUtil.mutListener.listen(5251) ? (e2.getY() * e1.getY()) : (ListenerUtil.mutListener.listen(5250) ? (e2.getY() + e1.getY()) : (e2.getY() - e1.getY())))));
                        float dx = (ListenerUtil.mutListener.listen(5257) ? (e2.getX() % e1.getX()) : (ListenerUtil.mutListener.listen(5256) ? (e2.getX() / e1.getX()) : (ListenerUtil.mutListener.listen(5255) ? (e2.getX() * e1.getX()) : (ListenerUtil.mutListener.listen(5254) ? (e2.getX() + e1.getX()) : (e2.getX() - e1.getX())))));
                        if (!ListenerUtil.mutListener.listen(5319)) {
                            if ((ListenerUtil.mutListener.listen(5262) ? (Math.abs(dx) >= Math.abs(dy)) : (ListenerUtil.mutListener.listen(5261) ? (Math.abs(dx) <= Math.abs(dy)) : (ListenerUtil.mutListener.listen(5260) ? (Math.abs(dx) < Math.abs(dy)) : (ListenerUtil.mutListener.listen(5259) ? (Math.abs(dx) != Math.abs(dy)) : (ListenerUtil.mutListener.listen(5258) ? (Math.abs(dx) == Math.abs(dy)) : (Math.abs(dx) > Math.abs(dy)))))))) {
                                if (!ListenerUtil.mutListener.listen(5318)) {
                                    // horizontal swipe if moved further in x direction than y direction
                                    if ((ListenerUtil.mutListener.listen(5302) ? ((ListenerUtil.mutListener.listen(5301) ? ((ListenerUtil.mutListener.listen(5300) ? ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) || !mIsXScrolling) : ((ListenerUtil.mutListener.listen(5300) ? ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) && !mIsXScrolling)) || !mIsSelecting) : ((ListenerUtil.mutListener.listen(5301) ? ((ListenerUtil.mutListener.listen(5300) ? ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) || !mIsXScrolling) : ((ListenerUtil.mutListener.listen(5300) ? ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5294) ? (dx >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5293) ? (dx <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5292) ? (dx < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5291) ? (dx != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5290) ? (dx == AnkiDroidApp.sSwipeMinDistance) : (dx > AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5299) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5298) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5297) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5296) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5295) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) && !mIsXScrolling)) && !mIsSelecting))) {
                                        if (!ListenerUtil.mutListener.listen(5317)) {
                                            // right
                                            executeCommand(mGestureSwipeRight);
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(5315) ? ((ListenerUtil.mutListener.listen(5314) ? ((ListenerUtil.mutListener.listen(5313) ? ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) || !mIsXScrolling) : ((ListenerUtil.mutListener.listen(5313) ? ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) && !mIsXScrolling)) || !mIsSelecting) : ((ListenerUtil.mutListener.listen(5314) ? ((ListenerUtil.mutListener.listen(5313) ? ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) || !mIsXScrolling) : ((ListenerUtil.mutListener.listen(5313) ? ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5307) ? (dx >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5306) ? (dx <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5305) ? (dx > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5304) ? (dx != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5303) ? (dx == -AnkiDroidApp.sSwipeMinDistance) : (dx < -AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5312) ? (Math.abs(velocityX) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5311) ? (Math.abs(velocityX) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5310) ? (Math.abs(velocityX) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5309) ? (Math.abs(velocityX) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5308) ? (Math.abs(velocityX) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityX) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) && !mIsXScrolling)) && !mIsSelecting))) {
                                        if (!ListenerUtil.mutListener.listen(5316)) {
                                            // left
                                            executeCommand(mGestureSwipeLeft);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5289)) {
                                    // otherwise vertical swipe
                                    if ((ListenerUtil.mutListener.listen(5274) ? ((ListenerUtil.mutListener.listen(5273) ? ((ListenerUtil.mutListener.listen(5267) ? (dy >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5266) ? (dy <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5265) ? (dy < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5264) ? (dy != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5263) ? (dy == AnkiDroidApp.sSwipeMinDistance) : (dy > AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5272) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5271) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5270) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5269) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5268) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5267) ? (dy >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5266) ? (dy <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5265) ? (dy < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5264) ? (dy != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5263) ? (dy == AnkiDroidApp.sSwipeMinDistance) : (dy > AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5272) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5271) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5270) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5269) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5268) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) || !mIsYScrolling) : ((ListenerUtil.mutListener.listen(5273) ? ((ListenerUtil.mutListener.listen(5267) ? (dy >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5266) ? (dy <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5265) ? (dy < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5264) ? (dy != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5263) ? (dy == AnkiDroidApp.sSwipeMinDistance) : (dy > AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5272) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5271) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5270) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5269) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5268) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5267) ? (dy >= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5266) ? (dy <= AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5265) ? (dy < AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5264) ? (dy != AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5263) ? (dy == AnkiDroidApp.sSwipeMinDistance) : (dy > AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5272) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5271) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5270) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5269) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5268) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) && !mIsYScrolling))) {
                                        if (!ListenerUtil.mutListener.listen(5288)) {
                                            // down
                                            executeCommand(mGestureSwipeDown);
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(5286) ? ((ListenerUtil.mutListener.listen(5285) ? ((ListenerUtil.mutListener.listen(5279) ? (dy >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5278) ? (dy <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5277) ? (dy > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5276) ? (dy != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5275) ? (dy == -AnkiDroidApp.sSwipeMinDistance) : (dy < -AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5284) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5283) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5282) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5281) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5280) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5279) ? (dy >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5278) ? (dy <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5277) ? (dy > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5276) ? (dy != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5275) ? (dy == -AnkiDroidApp.sSwipeMinDistance) : (dy < -AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5284) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5283) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5282) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5281) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5280) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) || !mIsYScrolling) : ((ListenerUtil.mutListener.listen(5285) ? ((ListenerUtil.mutListener.listen(5279) ? (dy >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5278) ? (dy <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5277) ? (dy > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5276) ? (dy != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5275) ? (dy == -AnkiDroidApp.sSwipeMinDistance) : (dy < -AnkiDroidApp.sSwipeMinDistance)))))) || (ListenerUtil.mutListener.listen(5284) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5283) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5282) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5281) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5280) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity))))))) : ((ListenerUtil.mutListener.listen(5279) ? (dy >= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5278) ? (dy <= -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5277) ? (dy > -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5276) ? (dy != -AnkiDroidApp.sSwipeMinDistance) : (ListenerUtil.mutListener.listen(5275) ? (dy == -AnkiDroidApp.sSwipeMinDistance) : (dy < -AnkiDroidApp.sSwipeMinDistance)))))) && (ListenerUtil.mutListener.listen(5284) ? (Math.abs(velocityY) >= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5283) ? (Math.abs(velocityY) <= AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5282) ? (Math.abs(velocityY) < AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5281) ? (Math.abs(velocityY) != AnkiDroidApp.sSwipeThresholdVelocity) : (ListenerUtil.mutListener.listen(5280) ? (Math.abs(velocityY) == AnkiDroidApp.sSwipeThresholdVelocity) : (Math.abs(velocityY) > AnkiDroidApp.sSwipeThresholdVelocity)))))))) && !mIsYScrolling))) {
                                        if (!ListenerUtil.mutListener.listen(5287)) {
                                            // up
                                            executeCommand(mGestureSwipeUp);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(5249)) {
                            Timber.e(e, "onFling Exception");
                        }
                    }
                }
            }
            return false;
        }

        private boolean isTouchingEdge(MotionEvent e1) {
            int height = mTouchLayer.getHeight();
            int width = mTouchLayer.getWidth();
            float margin = (ListenerUtil.mutListener.listen(5324) ? (NO_GESTURE_BORDER_DIP % getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(5323) ? (NO_GESTURE_BORDER_DIP / getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(5322) ? (NO_GESTURE_BORDER_DIP - getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(5321) ? (NO_GESTURE_BORDER_DIP + getResources().getDisplayMetrics().density) : (NO_GESTURE_BORDER_DIP * getResources().getDisplayMetrics().density))))) + 0.5f;
            return (ListenerUtil.mutListener.listen(5355) ? ((ListenerUtil.mutListener.listen(5345) ? ((ListenerUtil.mutListener.listen(5335) ? ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) && (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin))))))) : ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) || (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin)))))))) && (ListenerUtil.mutListener.listen(5344) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) >= margin) : (ListenerUtil.mutListener.listen(5343) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) <= margin) : (ListenerUtil.mutListener.listen(5342) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) > margin) : (ListenerUtil.mutListener.listen(5341) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) != margin) : (ListenerUtil.mutListener.listen(5340) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) == margin) : ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) < margin))))))) : ((ListenerUtil.mutListener.listen(5335) ? ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) && (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin))))))) : ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) || (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin)))))))) || (ListenerUtil.mutListener.listen(5344) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) >= margin) : (ListenerUtil.mutListener.listen(5343) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) <= margin) : (ListenerUtil.mutListener.listen(5342) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) > margin) : (ListenerUtil.mutListener.listen(5341) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) != margin) : (ListenerUtil.mutListener.listen(5340) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) == margin) : ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) < margin)))))))) && (ListenerUtil.mutListener.listen(5354) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) >= margin) : (ListenerUtil.mutListener.listen(5353) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) <= margin) : (ListenerUtil.mutListener.listen(5352) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) > margin) : (ListenerUtil.mutListener.listen(5351) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) != margin) : (ListenerUtil.mutListener.listen(5350) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) == margin) : ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) < margin))))))) : ((ListenerUtil.mutListener.listen(5345) ? ((ListenerUtil.mutListener.listen(5335) ? ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) && (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin))))))) : ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) || (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin)))))))) && (ListenerUtil.mutListener.listen(5344) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) >= margin) : (ListenerUtil.mutListener.listen(5343) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) <= margin) : (ListenerUtil.mutListener.listen(5342) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) > margin) : (ListenerUtil.mutListener.listen(5341) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) != margin) : (ListenerUtil.mutListener.listen(5340) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) == margin) : ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) < margin))))))) : ((ListenerUtil.mutListener.listen(5335) ? ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) && (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin))))))) : ((ListenerUtil.mutListener.listen(5329) ? (e1.getX() >= margin) : (ListenerUtil.mutListener.listen(5328) ? (e1.getX() <= margin) : (ListenerUtil.mutListener.listen(5327) ? (e1.getX() > margin) : (ListenerUtil.mutListener.listen(5326) ? (e1.getX() != margin) : (ListenerUtil.mutListener.listen(5325) ? (e1.getX() == margin) : (e1.getX() < margin)))))) || (ListenerUtil.mutListener.listen(5334) ? (e1.getY() >= margin) : (ListenerUtil.mutListener.listen(5333) ? (e1.getY() <= margin) : (ListenerUtil.mutListener.listen(5332) ? (e1.getY() > margin) : (ListenerUtil.mutListener.listen(5331) ? (e1.getY() != margin) : (ListenerUtil.mutListener.listen(5330) ? (e1.getY() == margin) : (e1.getY() < margin)))))))) || (ListenerUtil.mutListener.listen(5344) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) >= margin) : (ListenerUtil.mutListener.listen(5343) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) <= margin) : (ListenerUtil.mutListener.listen(5342) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) > margin) : (ListenerUtil.mutListener.listen(5341) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) != margin) : (ListenerUtil.mutListener.listen(5340) ? ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) == margin) : ((ListenerUtil.mutListener.listen(5339) ? (height % e1.getY()) : (ListenerUtil.mutListener.listen(5338) ? (height / e1.getY()) : (ListenerUtil.mutListener.listen(5337) ? (height * e1.getY()) : (ListenerUtil.mutListener.listen(5336) ? (height + e1.getY()) : (height - e1.getY()))))) < margin)))))))) || (ListenerUtil.mutListener.listen(5354) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) >= margin) : (ListenerUtil.mutListener.listen(5353) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) <= margin) : (ListenerUtil.mutListener.listen(5352) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) > margin) : (ListenerUtil.mutListener.listen(5351) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) != margin) : (ListenerUtil.mutListener.listen(5350) ? ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) == margin) : ((ListenerUtil.mutListener.listen(5349) ? (width % e1.getX()) : (ListenerUtil.mutListener.listen(5348) ? (width / e1.getX()) : (ListenerUtil.mutListener.listen(5347) ? (width * e1.getX()) : (ListenerUtil.mutListener.listen(5346) ? (width + e1.getX()) : (width - e1.getX()))))) < margin))))))));
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(5357)) {
                if (mGesturesEnabled) {
                    if (!ListenerUtil.mutListener.listen(5356)) {
                        executeCommand(mGestureDoubleTap);
                    }
                }
            }
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(5360)) {
                if (mTouchStarted) {
                    if (!ListenerUtil.mutListener.listen(5358)) {
                        longClickHandler.removeCallbacks(longClickTestRunnable);
                    }
                    if (!ListenerUtil.mutListener.listen(5359)) {
                        mTouchStarted = false;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(5361)) {
                // Go back to immersive mode if the user had temporarily exited it (and ignore the tap gesture)
                if (onSingleTap()) {
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5362)) {
                executeTouchCommand(e);
            }
            return false;
        }

        protected void executeTouchCommand(@NonNull MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(5365)) {
                if ((ListenerUtil.mutListener.listen(5363) ? (mGesturesEnabled || !mIsSelecting) : (mGesturesEnabled && !mIsSelecting))) {
                    int height = mTouchLayer.getHeight();
                    int width = mTouchLayer.getWidth();
                    float posX = e.getX();
                    float posY = e.getY();
                    int gesture = mGestureTapProcessor.getCommandFromTap(height, width, posX, posY);
                    if (!ListenerUtil.mutListener.listen(5364)) {
                        executeCommand(gesture);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5366)) {
                mIsSelecting = false;
            }
            if (!ListenerUtil.mutListener.listen(5367)) {
                showLookupButtonIfNeeded();
            }
        }

        public void onWebViewCreated(@NonNull WebView webView) {
        }

        public void onFillFlashcard() {
        }

        public boolean eventCanBeSentToWebView(@NonNull MotionEvent event) {
            return true;
        }
    }

    protected boolean onSingleTap() {
        return false;
    }

    protected void onFling() {
    }

    /**
     * #6141 - blocks clicking links from executing "touch" gestures.
     * COULD_BE_BETTER: Make base class static and move this out of the CardViewer
     */
    class LinkDetectingGestureDetector extends AbstractFlashcardViewer.MyGestureDetector {

        /**
         * A list of events to process when listening to WebView touches
         */
        private final HashSet<MotionEvent> mDesiredTouchEvents = new HashSet<>(2);

        /**
         * A list of events we sent to the WebView (to block double-processing)
         */
        private final HashSet<MotionEvent> mDispatchedTouchEvents = new HashSet<>(2);

        @Override
        public void onFillFlashcard() {
            if (!ListenerUtil.mutListener.listen(5368)) {
                Timber.d("Removing pending touch events for gestures");
            }
            if (!ListenerUtil.mutListener.listen(5369)) {
                mDesiredTouchEvents.clear();
            }
            if (!ListenerUtil.mutListener.listen(5370)) {
                mDispatchedTouchEvents.clear();
            }
        }

        @Override
        public boolean eventCanBeSentToWebView(@NonNull MotionEvent event) {
            // if we processed the event, we don't want to perform it again
            return !mDispatchedTouchEvents.remove(event);
        }

        @Override
        protected void executeTouchCommand(@NonNull MotionEvent downEvent) {
            if (!ListenerUtil.mutListener.listen(5371)) {
                downEvent.setAction(MotionEvent.ACTION_DOWN);
            }
            MotionEvent upEvent = MotionEvent.obtainNoHistory(downEvent);
            if (!ListenerUtil.mutListener.listen(5372)) {
                upEvent.setAction(MotionEvent.ACTION_UP);
            }
            if (!ListenerUtil.mutListener.listen(5373)) {
                // mark the events we want to process
                mDesiredTouchEvents.add(downEvent);
            }
            if (!ListenerUtil.mutListener.listen(5374)) {
                mDesiredTouchEvents.add(upEvent);
            }
            if (!ListenerUtil.mutListener.listen(5375)) {
                // mark the events to can guard against double-processing
                mDispatchedTouchEvents.add(downEvent);
            }
            if (!ListenerUtil.mutListener.listen(5376)) {
                mDispatchedTouchEvents.add(upEvent);
            }
            if (!ListenerUtil.mutListener.listen(5377)) {
                Timber.d("Dispatching touch events");
            }
            if (!ListenerUtil.mutListener.listen(5378)) {
                processCardAction(cardWebView -> {
                    cardWebView.dispatchTouchEvent(downEvent);
                    cardWebView.dispatchTouchEvent(upEvent);
                });
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onWebViewCreated(@NonNull WebView webView) {
            if (!ListenerUtil.mutListener.listen(5379)) {
                Timber.d("Initializing WebView touch handler");
            }
            if (!ListenerUtil.mutListener.listen(5380)) {
                webView.setOnTouchListener((webViewAsView, motionEvent) -> {
                    if (!mDesiredTouchEvents.remove(motionEvent)) {
                        return false;
                    }
                    // But we don't want to handle this as a touch event.
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        return true;
                    }
                    WebView cardWebView = (WebView) webViewAsView;
                    HitTestResult result;
                    try {
                        result = cardWebView.getHitTestResult();
                    } catch (Exception e) {
                        Timber.w(e, "Cannot obtain HitTest result");
                        return true;
                    }
                    if (isLinkClick(result)) {
                        Timber.v("Detected link click - ignoring gesture dispatch");
                        return true;
                    }
                    Timber.v("Executing continuation for click type: %d", result == null ? -178 : result.getType());
                    super.executeTouchCommand(motionEvent);
                    return true;
                });
            }
        }

        private boolean isLinkClick(HitTestResult result) {
            if (!ListenerUtil.mutListener.listen(5381)) {
                if (result == null) {
                    return false;
                }
            }
            int type = result.getType();
            return (ListenerUtil.mutListener.listen(5382) ? (type == HitTestResult.SRC_ANCHOR_TYPE && type == HitTestResult.SRC_IMAGE_ANCHOR_TYPE) : (type == HitTestResult.SRC_ANCHOR_TYPE || type == HitTestResult.SRC_IMAGE_ANCHOR_TYPE));
        }
    }

    /**
     * Removes first occurrence in answerContent of any audio that is present due to use of
     * {{FrontSide}} on the answer.
     * @param answerContent     The content from which to remove front side audio.
     * @return                  The content stripped of audio due to {{FrontSide}} inclusion.
     */
    private String removeFrontSideAudio(String answerContent) {
        String answerFormat = getAnswerFormat();
        String newAnswerContent = answerContent;
        if (!ListenerUtil.mutListener.listen(5385)) {
            if (answerFormat.contains("{{FrontSide}}")) {
                // possible audio removal necessary
                String frontSideFormat = mCurrentCard._getQA(false).get("q");
                Matcher audioReferences = Sound.sSoundPattern.matcher(frontSideFormat);
                if (!ListenerUtil.mutListener.listen(5384)) {
                    {
                        long _loopCounter102 = 0;
                        // remove the first instance of audio contained in "{{FrontSide}}"
                        while (audioReferences.find()) {
                            ListenerUtil.loopListener.listen("_loopCounter102", ++_loopCounter102);
                            if (!ListenerUtil.mutListener.listen(5383)) {
                                newAnswerContent = newAnswerContent.replaceFirst(Pattern.quote(audioReferences.group()), "");
                            }
                        }
                    }
                }
            }
        }
        return newAnswerContent;
    }

    /**
     * Public method to start new video player activity
     */
    public void playVideo(String path) {
        if (!ListenerUtil.mutListener.listen(5386)) {
            Timber.i("Launching Video: %s", path);
        }
        Intent videoPlayer = new Intent(this, VideoPlayer.class);
        if (!ListenerUtil.mutListener.listen(5387)) {
            videoPlayer.putExtra("path", path);
        }
        if (!ListenerUtil.mutListener.listen(5388)) {
            startActivityWithoutAnimation(videoPlayer);
        }
    }

    /**
     * Callback for when TTS has been initialized.
     */
    public void ttsInitialized() {
        if (!ListenerUtil.mutListener.listen(5389)) {
            mTtsInitialized = true;
        }
        if (!ListenerUtil.mutListener.listen(5391)) {
            if (mReplayOnTtsInit) {
                if (!ListenerUtil.mutListener.listen(5390)) {
                    playSounds(true);
                }
            }
        }
    }

    private void drawMark() {
        if (!ListenerUtil.mutListener.listen(5392)) {
            if (mCurrentCard == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5393)) {
            mCardMarker.displayMark(shouldDisplayMark());
        }
    }

    protected boolean shouldDisplayMark() {
        return mCurrentCard.note().hasTag("marked");
    }

    protected void onMark(Card card) {
        if (!ListenerUtil.mutListener.listen(5394)) {
            if (card == null) {
                return;
            }
        }
        Note note = card.note();
        if (!ListenerUtil.mutListener.listen(5397)) {
            if (note.hasTag("marked")) {
                if (!ListenerUtil.mutListener.listen(5396)) {
                    note.delTag("marked");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5395)) {
                    note.addTag("marked");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5398)) {
            note.flush();
        }
        if (!ListenerUtil.mutListener.listen(5399)) {
            refreshActionBar();
        }
        if (!ListenerUtil.mutListener.listen(5400)) {
            drawMark();
        }
    }

    private void drawFlag() {
        if (!ListenerUtil.mutListener.listen(5401)) {
            if (mCurrentCard == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5402)) {
            mCardMarker.displayFlag(getFlagToDisplay());
        }
    }

    @FlagDef
    protected int getFlagToDisplay() {
        return mCurrentCard.userFlag();
    }

    protected void onFlag(Card card, @FlagDef int flag) {
        if (!ListenerUtil.mutListener.listen(5403)) {
            if (card == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5404)) {
            card.setUserFlag(flag);
        }
        if (!ListenerUtil.mutListener.listen(5405)) {
            card.flush();
        }
        if (!ListenerUtil.mutListener.listen(5406)) {
            refreshActionBar();
        }
        if (!ListenerUtil.mutListener.listen(5407)) {
            drawFlag();
        }
    }

    protected void dismiss(Collection.DismissType type) {
        if (!ListenerUtil.mutListener.listen(5408)) {
            blockControls(false);
        }
        if (!ListenerUtil.mutListener.listen(5409)) {
            TaskManager.launchCollectionTask(new CollectionTask.DismissNote(mCurrentCard, type), mDismissCardHandler);
        }
    }

    /**
     * Signals from a WebView represent actions with no parameters
     */
    @VisibleForTesting
    static class WebViewSignalParserUtils {

        /**
         * A signal which we did not know how to handle
         */
        public static final int SIGNAL_UNHANDLED = 0;

        /**
         * A known signal which should perform a noop
         */
        public static final int SIGNAL_NOOP = 1;

        public static final int TYPE_FOCUS = 2;

        /**
         * Tell the app that we no longer want to focus the WebView and should instead return keyboard focus to a
         * native answer input method.
         */
        public static final int RELINQUISH_FOCUS = 3;

        public static final int SHOW_ANSWER = 4;

        public static final int ANSWER_ORDINAL_1 = 5;

        public static final int ANSWER_ORDINAL_2 = 6;

        public static final int ANSWER_ORDINAL_3 = 7;

        public static final int ANSWER_ORDINAL_4 = 8;

        public static int getSignalFromUrl(String url) {
            if (!ListenerUtil.mutListener.listen(5410)) {
                switch(url) {
                    case "signal:typefocus":
                        return TYPE_FOCUS;
                    case "signal:relinquishFocus":
                        return RELINQUISH_FOCUS;
                    case "signal:show_answer":
                        return SHOW_ANSWER;
                    case "signal:answer_ease1":
                        return ANSWER_ORDINAL_1;
                    case "signal:answer_ease2":
                        return ANSWER_ORDINAL_2;
                    case "signal:answer_ease3":
                        return ANSWER_ORDINAL_3;
                    case "signal:answer_ease4":
                        return ANSWER_ORDINAL_4;
                    default:
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(5412)) {
                if (url.startsWith("signal:answer_ease")) {
                    if (!ListenerUtil.mutListener.listen(5411)) {
                        Timber.w("Unhandled signal: ease value: %s", url);
                    }
                    return SIGNAL_NOOP;
                }
            }
            // unknown, or not a signal.
            return SIGNAL_UNHANDLED;
        }
    }

    protected class CardViewerWebClient extends WebViewClient {

        private WebViewAssetLoader mLoader;

        CardViewerWebClient(WebViewAssetLoader loader) {
            super();
            if (!ListenerUtil.mutListener.listen(5413)) {
                mLoader = loader;
            }
        }

        @Override
        @TargetApi(Build.VERSION_CODES.N)
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            if (!ListenerUtil.mutListener.listen(5414)) {
                Timber.d("Obtained URL from card: '%s'", url);
            }
            return filterUrl(url);
        }

        @Nullable
        @Override
        // required for lower APIs (I think)
        @SuppressWarnings("deprecation")
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (!ListenerUtil.mutListener.listen(5416)) {
                // response is null if nothing required
                if (isLoadedFromProtocolRelativeUrl(url)) {
                    if (!ListenerUtil.mutListener.listen(5415)) {
                        mMissingImageHandler.processInefficientImage(AbstractFlashcardViewer.this::displayMediaUpgradeRequiredSnackbar);
                    }
                }
            }
            return null;
        }

        @Override
        @TargetApi(Build.VERSION_CODES.N)
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            WebResourceResponse result = mLoader.shouldInterceptRequest(request.getUrl());
            if (!ListenerUtil.mutListener.listen(5417)) {
                if (result != null) {
                    return result;
                }
            }
            if (!ListenerUtil.mutListener.listen(5420)) {
                if (!AdaptionUtil.hasWebBrowser(getBaseContext())) {
                    String scheme = request.getUrl().getScheme().trim();
                    if (!ListenerUtil.mutListener.listen(5419)) {
                        if ((ListenerUtil.mutListener.listen(5418) ? ("http".equalsIgnoreCase(scheme) && "https".equalsIgnoreCase(scheme)) : ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)))) {
                            String response = getResources().getString(R.string.no_outgoing_link_in_cardbrowser);
                            return new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(response.getBytes()));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5422)) {
                if (isLoadedFromProtocolRelativeUrl(request.getUrl().toString())) {
                    if (!ListenerUtil.mutListener.listen(5421)) {
                        mMissingImageHandler.processInefficientImage(AbstractFlashcardViewer.this::displayMediaUpgradeRequiredSnackbar);
                    }
                }
            }
            return null;
        }

        protected boolean isLoadedFromProtocolRelativeUrl(String url) {
            // because <img src="x.png"> maps to file:///.../x.png
            return (ListenerUtil.mutListener.listen(5423) ? (url.startsWith("file://") || !url.startsWith("file:///")) : (url.startsWith("file://") && !url.startsWith("file:///")));
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            if (!ListenerUtil.mutListener.listen(5424)) {
                super.onReceivedError(view, request, error);
            }
            if (!ListenerUtil.mutListener.listen(5425)) {
                mMissingImageHandler.processFailure(request, AbstractFlashcardViewer.this::displayCouldNotFindMediaSnackbar);
            }
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            if (!ListenerUtil.mutListener.listen(5426)) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
            if (!ListenerUtil.mutListener.listen(5427)) {
                mMissingImageHandler.processFailure(request, AbstractFlashcardViewer.this::displayCouldNotFindMediaSnackbar);
            }
        }

        @Override
        // tracked as #5017 in github
        @SuppressWarnings("deprecation")
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return filterUrl(url);
        }

        // We play sounds through these links when a user taps the sound icon.
        private boolean filterUrl(String url) {
            if (!ListenerUtil.mutListener.listen(5430)) {
                if (url.startsWith("playsound:")) {
                    // Send a message that will be handled on the UI thread.
                    Message msg = Message.obtain();
                    if (!ListenerUtil.mutListener.listen(5428)) {
                        msg.obj = url.replaceFirst("playsound:", "");
                    }
                    if (!ListenerUtil.mutListener.listen(5429)) {
                        mHandler.sendMessage(msg);
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5432)) {
                if ((ListenerUtil.mutListener.listen(5431) ? (url.startsWith("file") && url.startsWith("data:")) : (url.startsWith("file") || url.startsWith("data:")))) {
                    // Let the webview load files, i.e. local images.
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(5435)) {
                if (url.startsWith("typeblurtext:")) {
                    if (!ListenerUtil.mutListener.listen(5433)) {
                        // Store the text the javascript has send us…
                        mTypeInput = decodeUrl(url.replaceFirst("typeblurtext:", ""));
                    }
                    if (!ListenerUtil.mutListener.listen(5434)) {
                        // … and show the “SHOW ANSWER” button again.
                        mFlipCardLayout.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5438)) {
                if (url.startsWith("typeentertext:")) {
                    if (!ListenerUtil.mutListener.listen(5436)) {
                        // Store the text the javascript has send us…
                        mTypeInput = decodeUrl(url.replaceFirst("typeentertext:", ""));
                    }
                    if (!ListenerUtil.mutListener.listen(5437)) {
                        // … and show the answer.
                        mFlipCardLayout.performClick();
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5442)) {
                // Show options menu from WebView
                if (url.startsWith("signal:anki_show_options_menu")) {
                    if (!ListenerUtil.mutListener.listen(5441)) {
                        if (isFullscreen()) {
                            if (!ListenerUtil.mutListener.listen(5440)) {
                                openOptionsMenu();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5439)) {
                                UIUtils.showThemedToast(AbstractFlashcardViewer.this, getString(R.string.ankidroid_turn_on_fullscreen_options_menu), true);
                            }
                        }
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5446)) {
                // Show Navigation Drawer from WebView
                if (url.startsWith("signal:anki_show_navigation_drawer")) {
                    if (!ListenerUtil.mutListener.listen(5445)) {
                        if (isFullscreen()) {
                            if (!ListenerUtil.mutListener.listen(5444)) {
                                AbstractFlashcardViewer.this.onNavigationPressed();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5443)) {
                                UIUtils.showThemedToast(AbstractFlashcardViewer.this, getString(R.string.ankidroid_turn_on_fullscreen_nav_drawer), true);
                            }
                        }
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5448)) {
                // card.html reload
                if (url.startsWith("signal:reload_card_html")) {
                    if (!ListenerUtil.mutListener.listen(5447)) {
                        redrawCard();
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5453)) {
                // mark card using javascript
                if (url.startsWith("signal:mark_current_card")) {
                    if (!ListenerUtil.mutListener.listen(5452)) {
                        if (isAnkiApiNull("markCard")) {
                            if (!ListenerUtil.mutListener.listen(5451)) {
                                showDeveloperContact(ankiJsErrorCodeDefault);
                            }
                            return true;
                        } else if (mJsApiListMap.get("markCard")) {
                            if (!ListenerUtil.mutListener.listen(5450)) {
                                executeCommand(COMMAND_MARK);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5449)) {
                                // see 02-string.xml
                                showDeveloperContact(ankiJsErrorCodeMarkCard);
                            }
                        }
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(5464)) {
                // flag card (blue, green, orange, red) using javascript from AnkiDroid webview
                if (url.startsWith("signal:flag_")) {
                    if (!ListenerUtil.mutListener.listen(5456)) {
                        if (isAnkiApiNull("toggleFlag")) {
                            if (!ListenerUtil.mutListener.listen(5455)) {
                                showDeveloperContact(ankiJsErrorCodeDefault);
                            }
                            return true;
                        } else if (!mJsApiListMap.get("toggleFlag")) {
                            if (!ListenerUtil.mutListener.listen(5454)) {
                                // see 02-string.xml
                                showDeveloperContact(ankiJsErrorCodeFlagCard);
                            }
                            return true;
                        }
                    }
                    String mFlag = url.replaceFirst("signal:flag_", "");
                    if (!ListenerUtil.mutListener.listen(5463)) {
                        switch(mFlag) {
                            case "none":
                                if (!ListenerUtil.mutListener.listen(5457)) {
                                    executeCommand(COMMAND_UNSET_FLAG);
                                }
                                return true;
                            case "red":
                                if (!ListenerUtil.mutListener.listen(5458)) {
                                    executeCommand(COMMAND_TOGGLE_FLAG_RED);
                                }
                                return true;
                            case "orange":
                                if (!ListenerUtil.mutListener.listen(5459)) {
                                    executeCommand(COMMAND_TOGGLE_FLAG_ORANGE);
                                }
                                return true;
                            case "green":
                                if (!ListenerUtil.mutListener.listen(5460)) {
                                    executeCommand(COMMAND_TOGGLE_FLAG_GREEN);
                                }
                                return true;
                            case "blue":
                                if (!ListenerUtil.mutListener.listen(5461)) {
                                    executeCommand(COMMAND_TOGGLE_FLAG_BLUE);
                                }
                                return true;
                            default:
                                if (!ListenerUtil.mutListener.listen(5462)) {
                                    Timber.d("No such Flag found.");
                                }
                                return true;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5466)) {
                // Show toast using JS
                if (url.startsWith("signal:anki_show_toast:")) {
                    String msg = url.replaceFirst("signal:anki_show_toast:", "");
                    String msgDecode = decodeUrl(msg);
                    if (!ListenerUtil.mutListener.listen(5465)) {
                        UIUtils.showThemedToast(AbstractFlashcardViewer.this, msgDecode, true);
                    }
                    return true;
                }
            }
            int signalOrdinal = WebViewSignalParserUtils.getSignalFromUrl(url);
            if (!ListenerUtil.mutListener.listen(5476)) {
                switch(signalOrdinal) {
                    case WebViewSignalParserUtils.SIGNAL_UNHANDLED:
                        // continue parsing
                        break;
                    case WebViewSignalParserUtils.SIGNAL_NOOP:
                        return true;
                    case WebViewSignalParserUtils.TYPE_FOCUS:
                        if (!ListenerUtil.mutListener.listen(5467)) {
                            // space by itself.
                            mFlipCardLayout.setVisibility(View.GONE);
                        }
                        return true;
                    case WebViewSignalParserUtils.RELINQUISH_FOCUS:
                        if (!ListenerUtil.mutListener.listen(5468)) {
                            // #5811 - The WebView could be focused via mouse. Allow components to return focus to Android.
                            focusAnswerCompletionField();
                        }
                        return true;
                    /*
                 *  Call displayCardAnswer() and answerCard() from anki deck template using javascript
                 *  See card.js in assets/scripts folder
                 */
                    case WebViewSignalParserUtils.SHOW_ANSWER:
                        if (!ListenerUtil.mutListener.listen(5470)) {
                            // display answer when showAnswer() called from card.js
                            if (!sDisplayAnswer) {
                                if (!ListenerUtil.mutListener.listen(5469)) {
                                    displayCardAnswer();
                                }
                            }
                        }
                        return true;
                    case WebViewSignalParserUtils.ANSWER_ORDINAL_1:
                        if (!ListenerUtil.mutListener.listen(5471)) {
                            flipOrAnswerCard(EASE_1);
                        }
                        return true;
                    case WebViewSignalParserUtils.ANSWER_ORDINAL_2:
                        if (!ListenerUtil.mutListener.listen(5472)) {
                            flipOrAnswerCard(EASE_2);
                        }
                        return true;
                    case WebViewSignalParserUtils.ANSWER_ORDINAL_3:
                        if (!ListenerUtil.mutListener.listen(5473)) {
                            flipOrAnswerCard(EASE_3);
                        }
                        return true;
                    case WebViewSignalParserUtils.ANSWER_ORDINAL_4:
                        if (!ListenerUtil.mutListener.listen(5474)) {
                            flipOrAnswerCard(EASE_4);
                        }
                        return true;
                    default:
                        if (!ListenerUtil.mutListener.listen(5475)) {
                            // This is not the same as SIGNAL_UNHANDLED, where it isn't a known signal.
                            Timber.w("Unhandled signal case: %d", signalOrdinal);
                        }
                        return true;
                }
            }
            Intent intent = null;
            try {
                if (!ListenerUtil.mutListener.listen(5489)) {
                    if (url.startsWith("intent:")) {
                        if (!ListenerUtil.mutListener.listen(5488)) {
                            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        }
                    } else if (url.startsWith("android-app:")) {
                        if (!ListenerUtil.mutListener.listen(5487)) {
                            if ((ListenerUtil.mutListener.listen(5482) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(5481) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(5480) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(5479) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(5478) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1))))))) {
                                if (!ListenerUtil.mutListener.listen(5484)) {
                                    intent = Intent.parseUri(url, 0);
                                }
                                if (!ListenerUtil.mutListener.listen(5485)) {
                                    intent.setData(null);
                                }
                                if (!ListenerUtil.mutListener.listen(5486)) {
                                    intent.setPackage(Uri.parse(url).getHost());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5483)) {
                                    intent = Intent.parseUri(url, Intent.URI_ANDROID_APP_SCHEME);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(5499)) {
                    if (intent != null) {
                        if (!ListenerUtil.mutListener.listen(5498)) {
                            if (getPackageManager().resolveActivity(intent, 0) == null) {
                                String packageName = intent.getPackage();
                                if (!ListenerUtil.mutListener.listen(5497)) {
                                    if (packageName == null) {
                                        if (!ListenerUtil.mutListener.listen(5495)) {
                                            Timber.d("Not using resolved intent uri because not available: %s", intent);
                                        }
                                        if (!ListenerUtil.mutListener.listen(5496)) {
                                            intent = null;
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(5491)) {
                                            Timber.d("Resolving intent uri to market uri because not available: %s", intent);
                                        }
                                        if (!ListenerUtil.mutListener.listen(5492)) {
                                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
                                        }
                                        if (!ListenerUtil.mutListener.listen(5494)) {
                                            if (getPackageManager().resolveActivity(intent, 0) == null) {
                                                if (!ListenerUtil.mutListener.listen(5493)) {
                                                    intent = null;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(5490)) {
                                    // https://developer.chrome.com/multidevice/android/intents says that we should remove this
                                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                }
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                if (!ListenerUtil.mutListener.listen(5477)) {
                    Timber.w("Unable to parse intent uri: %s because: %s", url, t.getMessage());
                }
            }
            if (!ListenerUtil.mutListener.listen(5503)) {
                if (intent == null) {
                    if (!ListenerUtil.mutListener.listen(5501)) {
                        Timber.d("Opening external link \"%s\" with an Intent", url);
                    }
                    if (!ListenerUtil.mutListener.listen(5502)) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(5500)) {
                        Timber.d("Opening resolved external link \"%s\" with an Intent: %s", url, intent);
                    }
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(5505)) {
                    startActivityWithoutAnimation(intent);
                }
            } catch (ActivityNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(5504)) {
                    // Don't crash if the intent is not handled
                    e.printStackTrace();
                }
            }
            return true;
        }

        private String decodeUrl(String url) {
            try {
                return URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                if (!ListenerUtil.mutListener.listen(5506)) {
                    Timber.e(e, "UTF-8 isn't supported as an encoding?");
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(5507)) {
                    Timber.e(e, "Exception decoding: '%s'", url);
                }
                if (!ListenerUtil.mutListener.listen(5508)) {
                    UIUtils.showThemedToast(AbstractFlashcardViewer.this, getString(R.string.card_viewer_url_decode_error), true);
                }
            }
            return "";
        }

        // Run any post-load events in javascript that rely on the window being completely loaded.
        @Override
        public void onPageFinished(WebView view, String url) {
            if (!ListenerUtil.mutListener.listen(5509)) {
                Timber.d("Java onPageFinished triggered: %s", url);
            }
            if (!ListenerUtil.mutListener.listen(5514)) {
                // onPageFinished will be called multiple times if the WebView redirects by setting window.location.href
                if (url.equals(mViewerUrl)) {
                    if (!ListenerUtil.mutListener.listen(5510)) {
                        Timber.d("New URL, drawing flags, marks, and triggering JS onPageFinished: %s", url);
                    }
                    if (!ListenerUtil.mutListener.listen(5511)) {
                        drawFlag();
                    }
                    if (!ListenerUtil.mutListener.listen(5512)) {
                        drawMark();
                    }
                    if (!ListenerUtil.mutListener.listen(5513)) {
                        view.loadUrl("javascript:onPageFinished();");
                    }
                }
            }
        }

        /**
         * Fix: #5780 - WebView Renderer OOM crashes reviewer
         */
        @Override
        @TargetApi(Build.VERSION_CODES.O)
        public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
            if (!ListenerUtil.mutListener.listen(5515)) {
                Timber.i("Obtaining write lock for card");
            }
            Lock writeLock = mCardLock.writeLock();
            if (!ListenerUtil.mutListener.listen(5516)) {
                Timber.i("Obtained write lock for card");
            }
            try {
                if (!ListenerUtil.mutListener.listen(5519)) {
                    writeLock.lock();
                }
                if (!ListenerUtil.mutListener.listen(5522)) {
                    if ((ListenerUtil.mutListener.listen(5520) ? (mCardWebView == null && !mCardWebView.equals(view)) : (mCardWebView == null || !mCardWebView.equals(view)))) {
                        if (!ListenerUtil.mutListener.listen(5521)) {
                            // We have nothing to handle. Returning false is a desire to crash, so return true.
                            Timber.i("Unrelated WebView Renderer terminated. Crashed: %b", detail.didCrash());
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(5523)) {
                    Timber.e("WebView Renderer process terminated. Crashed: %b", detail.didCrash());
                }
                if (!ListenerUtil.mutListener.listen(5524)) {
                    // "crash wasn't handled by all associated webviews, triggering application crash"
                    mCardFrame.removeAllViews();
                }
                if (!ListenerUtil.mutListener.listen(5525)) {
                    mCardFrameParent.removeView(mCardFrame);
                }
                if (!ListenerUtil.mutListener.listen(5526)) {
                    // destroy after removal from the view - produces logcat warnings otherwise
                    destroyWebView(mCardWebView);
                }
                if (!ListenerUtil.mutListener.listen(5527)) {
                    mCardWebView = null;
                }
                if (!ListenerUtil.mutListener.listen(5528)) {
                    // inflate a new instance of mCardFrame
                    mCardFrame = inflateNewView(R.id.flashcard);
                }
                if (!ListenerUtil.mutListener.listen(5529)) {
                    // I'll keep this line unless I see another crash, which would point to another underlying issue.
                    System.gc();
                }
                // It's not necessarily an OOM crash, false implies a general code which is for "system terminated".
                int errorCauseId = detail.didCrash() ? R.string.webview_crash_unknown : R.string.webview_crash_oom;
                String errorCauseString = getResources().getString(errorCauseId);
                if (!ListenerUtil.mutListener.listen(5533)) {
                    if (!canRecoverFromWebViewRendererCrash()) {
                        if (!ListenerUtil.mutListener.listen(5530)) {
                            Timber.e("Unrecoverable WebView Render crash");
                        }
                        String errorMessage = getResources().getString(R.string.webview_crash_fatal, errorCauseString);
                        if (!ListenerUtil.mutListener.listen(5531)) {
                            UIUtils.showThemedToast(AbstractFlashcardViewer.this, errorMessage, false);
                        }
                        if (!ListenerUtil.mutListener.listen(5532)) {
                            finishWithoutAnimation();
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(5536)) {
                    if (webViewRendererLastCrashedOnCard(mCurrentCard.getId())) {
                        if (!ListenerUtil.mutListener.listen(5534)) {
                            Timber.e("Web Renderer crash loop on card: %d", mCurrentCard.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(5535)) {
                            displayRenderLoopDialog(mCurrentCard, detail);
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(5537)) {
                    // This logic may need to be better defined. The card could have changed by the time we get here.
                    lastCrashingCardId = mCurrentCard.getId();
                }
                String nonFatalError = getResources().getString(R.string.webview_crash_nonfatal, errorCauseString);
                if (!ListenerUtil.mutListener.listen(5538)) {
                    UIUtils.showThemedToast(AbstractFlashcardViewer.this, nonFatalError, false);
                }
                if (!ListenerUtil.mutListener.listen(5539)) {
                    // we need to add at index 0 so gestures still go through.
                    mCardFrameParent.addView(mCardFrame, 0);
                }
                if (!ListenerUtil.mutListener.listen(5540)) {
                    recreateWebView();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(5517)) {
                    writeLock.unlock();
                }
                if (!ListenerUtil.mutListener.listen(5518)) {
                    Timber.d("Relinquished writeLock");
                }
            }
            if (!ListenerUtil.mutListener.listen(5541)) {
                displayCardQuestion();
            }
            // We handled the crash and can continue.
            return true;
        }

        @TargetApi(Build.VERSION_CODES.O)
        private void displayRenderLoopDialog(Card currentCard, RenderProcessGoneDetail detail) {
            String cardInformation = Long.toString(currentCard.getId());
            Resources res = getResources();
            String errorDetails = detail.didCrash() ? res.getString(R.string.webview_crash_unknwon_detailed) : res.getString(R.string.webview_crash_oom_details);
            if (!ListenerUtil.mutListener.listen(5542)) {
                new MaterialDialog.Builder(AbstractFlashcardViewer.this).title(res.getString(R.string.webview_crash_loop_dialog_title)).content(res.getString(R.string.webview_crash_loop_dialog_content, cardInformation, errorDetails)).positiveText(R.string.dialog_ok).cancelable(false).canceledOnTouchOutside(false).onPositive((materialDialog, dialogAction) -> finishWithoutAnimation()).show();
            }
        }
    }

    private void displayCouldNotFindMediaSnackbar(String filename) {
        OnClickListener onClickListener = (v) -> openUrl(Uri.parse(getString(R.string.link_faq_missing_media)));
        if (!ListenerUtil.mutListener.listen(5543)) {
            showSnackbar(getString(R.string.card_viewer_could_not_find_image, filename), R.string.help, onClickListener);
        }
    }

    private void displayMediaUpgradeRequiredSnackbar() {
        OnClickListener onClickListener = (v) -> openUrl(Uri.parse(getString(R.string.link_faq_invalid_protocol_relative)));
        if (!ListenerUtil.mutListener.listen(5544)) {
            showSnackbar(getString(R.string.card_viewer_media_relative_protocol), R.string.help, onClickListener);
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    protected String getTypedInputText() {
        return mTypeInput;
    }

    @SuppressLint("WebViewApiAvailability")
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    void handleUrlFromJavascript(String url) {
        if (!ListenerUtil.mutListener.listen(5552)) {
            if ((ListenerUtil.mutListener.listen(5549) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(5548) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(5547) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(5546) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(5545) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                // WebViewCompat recommended here, but I'll avoid the dependency as it's test code
                CardViewerWebClient c = ((CardViewerWebClient) this.mCardWebView.getWebViewClient());
                if (!ListenerUtil.mutListener.listen(5550)) {
                    if (c == null) {
                        throw new IllegalStateException("Couldn't obtain WebView - maybe it wasn't created yet");
                    }
                }
                if (!ListenerUtil.mutListener.listen(5551)) {
                    c.filterUrl(url);
                }
            } else {
                throw new IllegalStateException("Can't get WebViewClient due to Android API");
            }
        }
    }

    // Check if value null
    private boolean isAnkiApiNull(String api) {
        return mJsApiListMap.get(api) == null;
    }

    /*
     * see 02-strings.xml
     * Show Error code when mark card or flag card unsupported
     * 1 - mark card
     * 2 - flag card
     *
     * show developer contact if js api used in card is deprecated
     */
    private void showDeveloperContact(int errorCode) {
        String errorMsg = getString(R.string.anki_js_error_code, errorCode);
        View parentLayout = findViewById(android.R.id.content);
        String snackbarMsg = getString(R.string.api_version_developer_contact, mCardSuppliedDeveloperContact, errorMsg);
        Snackbar snackbar = Snackbar.make(parentLayout, snackbarMsg, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView snackTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        if (!ListenerUtil.mutListener.listen(5553)) {
            snackTextView.setTextColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(5554)) {
            snackTextView.setMaxLines(3);
        }
        if (!ListenerUtil.mutListener.listen(5555)) {
            snackbar.setActionTextColor(Color.MAGENTA).setAction(getString(R.string.reviewer_invalid_api_version_visit_documentation), view -> openUrl(Uri.parse("https://github.com/ankidroid/Anki-Android/wiki")));
        }
        if (!ListenerUtil.mutListener.listen(5556)) {
            snackbar.show();
        }
    }

    /**
     * Supplied api version must be equal to current api version to call mark card, toggle flag functions etc.
     */
    private boolean requireApiVersion(String apiVer, String apiDevContact) {
        try {
            if (!ListenerUtil.mutListener.listen(5558)) {
                if (TextUtils.isEmpty(apiDevContact)) {
                    return false;
                }
            }
            Version mVersionCurrent = Version.valueOf(sCurrentJsApiVersion);
            Version mVersionSupplied = Version.valueOf(apiVer);
            if (!ListenerUtil.mutListener.listen(5561)) {
                /*
            * if api major version equals to supplied major version then return true and also check for minor version and patch version
            * show toast for update and contact developer if need updates
            * otherwise return false
            */
                if (mVersionSupplied.equals(mVersionCurrent)) {
                    return true;
                } else if (mVersionSupplied.lessThan(mVersionCurrent)) {
                    if (!ListenerUtil.mutListener.listen(5560)) {
                        UIUtils.showThemedToast(AbstractFlashcardViewer.this, getString(R.string.update_js_api_version, mCardSuppliedDeveloperContact), false);
                    }
                    return mVersionSupplied.greaterThanOrEqualTo(Version.valueOf(sMinimumJsApiVersion));
                } else {
                    if (!ListenerUtil.mutListener.listen(5559)) {
                        UIUtils.showThemedToast(AbstractFlashcardViewer.this, getString(R.string.valid_js_api_version, mCardSuppliedDeveloperContact), false);
                    }
                    return false;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(5557)) {
                Timber.w(e, "requireApiVersion::exception");
            }
        }
        return false;
    }

    @VisibleForTesting
    void loadInitialCard() {
        if (!ListenerUtil.mutListener.listen(5562)) {
            TaskManager.launchCollectionTask(new CollectionTask.GetCard(), mAnswerCardHandler(false));
        }
    }

    public ReviewerUi.ControlBlock getControlBlocked() {
        return mControlBlocked;
    }

    public boolean isDisplayingAnswer() {
        return sDisplayAnswer;
    }

    public boolean isControlBlocked() {
        return getControlBlocked() != ControlBlock.UNBLOCKED;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    static void setEditorCard(Card card) {
        if (!ListenerUtil.mutListener.listen(5563)) {
            // I don't see why we don't do this by intent.
            sEditorCard = card;
        }
    }

    @VisibleForTesting
    String getCorrectTypedAnswer() {
        return mTypeCorrect;
    }

    @VisibleForTesting
    String getCardContent() {
        return mCardContent;
    }

    protected void showTagsDialog() {
        ArrayList<String> tags = new ArrayList<>(getCol().getTags().all());
        ArrayList<String> selTags = new ArrayList<>(mCurrentCard.note().getTags());
        TagsDialog.TagsDialogListener tagsDialogListener = (selectedTags, option) -> {
            if (!mCurrentCard.note().getTags().equals(selectedTags)) {
                String tagString = TextUtils.join(" ", selectedTags);
                Note note = mCurrentCard.note();
                note.setTagsFromStr(tagString);
                note.flush();
                // Reload current card to reflect tag changes
                displayCardQuestion(true);
            }
        };
        TagsDialog dialog = TagsDialog.newInstance(TagsDialog.TYPE_ADD_TAG, selTags, tags);
        if (!ListenerUtil.mutListener.listen(5564)) {
            dialog.setTagsDialogListener(tagsDialogListener);
        }
        if (!ListenerUtil.mutListener.listen(5565)) {
            showDialogFragment(dialog);
        }
    }

    // init or reset api list
    private void jsApiInit() {
        if (!ListenerUtil.mutListener.listen(5566)) {
            mCardSuppliedApiVersion = "";
        }
        if (!ListenerUtil.mutListener.listen(5567)) {
            mCardSuppliedDeveloperContact = "";
        }
        if (!ListenerUtil.mutListener.listen(5569)) {
            {
                long _loopCounter103 = 0;
                for (String api : mApiList) {
                    ListenerUtil.loopListener.listen("_loopCounter103", ++_loopCounter103);
                    if (!ListenerUtil.mutListener.listen(5568)) {
                        mJsApiListMap.put(api, false);
                    }
                }
            }
        }
    }

    // list of api that can be accessed
    private final String[] mApiList = { "toggleFlag", "markCard" };

    // JS api list enable/disable status
    private final HashMap<String, Boolean> mJsApiListMap = new HashMap<>(mApiList.length);

    public JavaScriptFunction javaScriptFunction() {
        return new JavaScriptFunction();
    }

    public class JavaScriptFunction {

        // if supplied api version match then enable api
        private void enableJsApi() {
            if (!ListenerUtil.mutListener.listen(5571)) {
                {
                    long _loopCounter104 = 0;
                    for (String api : mApiList) {
                        ListenerUtil.loopListener.listen("_loopCounter104", ++_loopCounter104);
                        if (!ListenerUtil.mutListener.listen(5570)) {
                            mJsApiListMap.put(api, true);
                        }
                    }
                }
            }
        }

        @JavascriptInterface
        public String init(String jsonData) {
            JSONObject data;
            String apiStatusJson = "";
            try {
                data = new JSONObject(jsonData);
                if (!ListenerUtil.mutListener.listen(5578)) {
                    if (!(data == JSONObject.NULL)) {
                        if (!ListenerUtil.mutListener.listen(5573)) {
                            mCardSuppliedApiVersion = data.optString("version", "");
                        }
                        if (!ListenerUtil.mutListener.listen(5574)) {
                            mCardSuppliedDeveloperContact = data.optString("developer", "");
                        }
                        if (!ListenerUtil.mutListener.listen(5576)) {
                            if (requireApiVersion(mCardSuppliedApiVersion, mCardSuppliedDeveloperContact)) {
                                if (!ListenerUtil.mutListener.listen(5575)) {
                                    enableJsApi();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(5577)) {
                            apiStatusJson = JSONObject.fromMap(mJsApiListMap).toString();
                        }
                    }
                }
            } catch (JSONException j) {
                if (!ListenerUtil.mutListener.listen(5572)) {
                    UIUtils.showThemedToast(AbstractFlashcardViewer.this, getString(R.string.invalid_json_data, j.getLocalizedMessage()), false);
                }
            }
            return apiStatusJson;
        }

        // It's overridden in the Reviewer, where those values are actually defined.
        @JavascriptInterface
        public String ankiGetNewCardCount() {
            return "-1";
        }

        @JavascriptInterface
        public String ankiGetLrnCardCount() {
            return "-1";
        }

        @JavascriptInterface
        public String ankiGetRevCardCount() {
            return "-1";
        }

        @JavascriptInterface
        public int ankiGetETA() {
            return -1;
        }

        @JavascriptInterface
        public boolean ankiGetCardMark() {
            return shouldDisplayMark();
        }

        @JavascriptInterface
        public int ankiGetCardFlag() {
            return mCurrentCard.userFlag();
        }

        @JavascriptInterface
        public String ankiGetNextTime1() {
            return (String) mNext1.getText();
        }

        @JavascriptInterface
        public String ankiGetNextTime2() {
            return (String) mNext2.getText();
        }

        @JavascriptInterface
        public String ankiGetNextTime3() {
            return (String) mNext3.getText();
        }

        @JavascriptInterface
        public String ankiGetNextTime4() {
            return (String) mNext4.getText();
        }

        @JavascriptInterface
        public int ankiGetCardReps() {
            return mCurrentCard.getReps();
        }

        @JavascriptInterface
        public int ankiGetCardInterval() {
            return mCurrentCard.getIvl();
        }

        /**
         * Returns the ease as an int (percentage * 10). Default: 2500 (250%). Minimum: 1300 (130%)
         */
        @JavascriptInterface
        public int ankiGetCardFactor() {
            return mCurrentCard.getFactor();
        }

        /**
         * Returns the last modified time as a Unix timestamp in seconds. Example: 1477384099
         */
        @JavascriptInterface
        public long ankiGetCardMod() {
            return mCurrentCard.getMod();
        }

        /**
         * Returns the ID of the card. Example: 1477380543053
         */
        @JavascriptInterface
        public long ankiGetCardId() {
            return mCurrentCard.getId();
        }

        /**
         * Returns the ID of the note which generated the card. Example: 1590418157630
         */
        @JavascriptInterface
        public long ankiGetCardNid() {
            return mCurrentCard.getNid();
        }

        @JavascriptInterface
        @Consts.CARD_TYPE
        public int ankiGetCardType() {
            return mCurrentCard.getType();
        }

        /**
         * Returns the ID of the deck which contains the card. Example: 1595967594978
         */
        @JavascriptInterface
        public long ankiGetCardDid() {
            return mCurrentCard.getDid();
        }

        @JavascriptInterface
        public int ankiGetCardLeft() {
            return mCurrentCard.getLeft();
        }

        /**
         * Returns the ID of the home deck for the card if it is filtered, or 0 if not filtered. Example: 1595967594978
         */
        @JavascriptInterface
        public long ankiGetCardODid() {
            return mCurrentCard.getODid();
        }

        @JavascriptInterface
        public long ankiGetCardODue() {
            return mCurrentCard.getODue();
        }

        @JavascriptInterface
        @Consts.CARD_QUEUE
        public int ankiGetCardQueue() {
            return mCurrentCard.getQueue();
        }

        @JavascriptInterface
        public int ankiGetCardLapses() {
            return mCurrentCard.getLapses();
        }

        @JavascriptInterface
        public long ankiGetCardDue() {
            return mCurrentCard.getDue();
        }

        @JavascriptInterface
        public boolean ankiIsInFullscreen() {
            return isFullscreen();
        }

        @JavascriptInterface
        public boolean ankiIsTopbarShown() {
            return mPrefShowTopbar;
        }

        @JavascriptInterface
        public boolean ankiIsInNightMode() {
            return isInNightMode();
        }

        @JavascriptInterface
        public boolean ankiIsActiveNetworkMetered() {
            try {
                ConnectivityManager cm = (ConnectivityManager) AnkiDroidApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                if (cm == null) {
                    if (!ListenerUtil.mutListener.listen(5580)) {
                        Timber.w("ConnectivityManager not found - assuming metered connection");
                    }
                    return true;
                }
                return cm.isActiveNetworkMetered();
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(5579)) {
                    Timber.w(e, "Exception obtaining metered connection - assuming metered connection");
                }
                return true;
            }
        }
    }
}
