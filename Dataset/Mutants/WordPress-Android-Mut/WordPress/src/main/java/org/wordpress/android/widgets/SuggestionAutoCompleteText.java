package org.wordpress.android.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import org.wordpress.android.ui.suggestion.util.SuggestionTokenizer;
import org.wordpress.android.util.DeviceUtils;
import org.wordpress.persistentedittext.PersistentEditTextHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SuggestionAutoCompleteText extends AppCompatMultiAutoCompleteTextView {

    PersistentEditTextHelper mPersistentEditTextHelper;

    private OnEditTextBackListener mBackListener;

    private SuggestionTokenizer mSuggestionTokenizer;

    private char mPrefix;

    public interface OnEditTextBackListener {

        void onEditTextBack();
    }

    public SuggestionAutoCompleteText(Context context) {
        super(context, null);
        if (!ListenerUtil.mutListener.listen(28793)) {
            init(context);
        }
    }

    public SuggestionAutoCompleteText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(28794)) {
            init(context);
        }
    }

    public SuggestionAutoCompleteText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(28795)) {
            init(context);
        }
    }

    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(28796)) {
            mPersistentEditTextHelper = new PersistentEditTextHelper(context);
        }
        if (!ListenerUtil.mutListener.listen(28797)) {
            // When TYPE_TEXT_FLAG_AUTO_COMPLETE is set, autocorrection is disabled.
            setRawInputType(getInputType() & ~EditorInfo.TYPE_TEXT_FLAG_AUTO_COMPLETE);
        }
    }

    public void initializeWithPrefix(char prefix) {
        if (!ListenerUtil.mutListener.listen(28798)) {
            mPrefix = prefix;
        }
        if (!ListenerUtil.mutListener.listen(28799)) {
            mSuggestionTokenizer = new SuggestionTokenizer(mPrefix);
        }
        if (!ListenerUtil.mutListener.listen(28800)) {
            setTokenizer(mSuggestionTokenizer);
        }
        if (!ListenerUtil.mutListener.listen(28801)) {
            setThreshold(1);
        }
    }

    @Override
    public boolean enoughToFilter() {
        Editable text = getText();
        int end = getSelectionEnd();
        if (!ListenerUtil.mutListener.listen(28807)) {
            if ((ListenerUtil.mutListener.listen(28806) ? (end >= 0) : (ListenerUtil.mutListener.listen(28805) ? (end <= 0) : (ListenerUtil.mutListener.listen(28804) ? (end > 0) : (ListenerUtil.mutListener.listen(28803) ? (end != 0) : (ListenerUtil.mutListener.listen(28802) ? (end == 0) : (end < 0))))))) {
                return false;
            }
        }
        int start = mSuggestionTokenizer.findTokenStart(text, end);
        return (ListenerUtil.mutListener.listen(28833) ? ((ListenerUtil.mutListener.listen(28812) ? (start >= 0) : (ListenerUtil.mutListener.listen(28811) ? (start <= 0) : (ListenerUtil.mutListener.listen(28810) ? (start < 0) : (ListenerUtil.mutListener.listen(28809) ? (start != 0) : (ListenerUtil.mutListener.listen(28808) ? (start == 0) : (start > 0)))))) || ((ListenerUtil.mutListener.listen(28832) ? ((ListenerUtil.mutListener.listen(28821) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) <= 1) : (ListenerUtil.mutListener.listen(28820) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) > 1) : (ListenerUtil.mutListener.listen(28819) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) < 1) : (ListenerUtil.mutListener.listen(28818) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) != 1) : (ListenerUtil.mutListener.listen(28817) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) == 1) : ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) >= 1)))))) && ((ListenerUtil.mutListener.listen(28831) ? ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) || text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix) : ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) && text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix)))) : ((ListenerUtil.mutListener.listen(28821) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) <= 1) : (ListenerUtil.mutListener.listen(28820) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) > 1) : (ListenerUtil.mutListener.listen(28819) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) < 1) : (ListenerUtil.mutListener.listen(28818) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) != 1) : (ListenerUtil.mutListener.listen(28817) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) == 1) : ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) >= 1)))))) || ((ListenerUtil.mutListener.listen(28831) ? ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) || text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix) : ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) && text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix))))))) : ((ListenerUtil.mutListener.listen(28812) ? (start >= 0) : (ListenerUtil.mutListener.listen(28811) ? (start <= 0) : (ListenerUtil.mutListener.listen(28810) ? (start < 0) : (ListenerUtil.mutListener.listen(28809) ? (start != 0) : (ListenerUtil.mutListener.listen(28808) ? (start == 0) : (start > 0)))))) && ((ListenerUtil.mutListener.listen(28832) ? ((ListenerUtil.mutListener.listen(28821) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) <= 1) : (ListenerUtil.mutListener.listen(28820) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) > 1) : (ListenerUtil.mutListener.listen(28819) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) < 1) : (ListenerUtil.mutListener.listen(28818) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) != 1) : (ListenerUtil.mutListener.listen(28817) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) == 1) : ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) >= 1)))))) && ((ListenerUtil.mutListener.listen(28831) ? ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) || text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix) : ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) && text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix)))) : ((ListenerUtil.mutListener.listen(28821) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) <= 1) : (ListenerUtil.mutListener.listen(28820) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) > 1) : (ListenerUtil.mutListener.listen(28819) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) < 1) : (ListenerUtil.mutListener.listen(28818) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) != 1) : (ListenerUtil.mutListener.listen(28817) ? ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) == 1) : ((ListenerUtil.mutListener.listen(28816) ? (end % start) : (ListenerUtil.mutListener.listen(28815) ? (end / start) : (ListenerUtil.mutListener.listen(28814) ? (end * start) : (ListenerUtil.mutListener.listen(28813) ? (end + start) : (end - start))))) >= 1)))))) || ((ListenerUtil.mutListener.listen(28831) ? ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) || text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix) : ((ListenerUtil.mutListener.listen(28826) ? (start >= end) : (ListenerUtil.mutListener.listen(28825) ? (start <= end) : (ListenerUtil.mutListener.listen(28824) ? (start > end) : (ListenerUtil.mutListener.listen(28823) ? (start < end) : (ListenerUtil.mutListener.listen(28822) ? (start != end) : (start == end)))))) && text.charAt((ListenerUtil.mutListener.listen(28830) ? (start % 1) : (ListenerUtil.mutListener.listen(28829) ? (start / 1) : (ListenerUtil.mutListener.listen(28828) ? (start * 1) : (ListenerUtil.mutListener.listen(28827) ? (start + 1) : (start - 1)))))) == mPrefix))))))));
    }

    public void forceFiltering(CharSequence text) {
        if (!ListenerUtil.mutListener.listen(28834)) {
            performFiltering(text, 0);
        }
    }

    public PersistentEditTextHelper getAutoSaveTextHelper() {
        return mPersistentEditTextHelper;
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(28835)) {
            super.onAttachedToWindow();
        }
        if (!ListenerUtil.mutListener.listen(28836)) {
            if (getAutoSaveTextHelper().getUniqueId() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28837)) {
            getAutoSaveTextHelper().loadString(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(28838)) {
            super.onDetachedFromWindow();
        }
        if (!ListenerUtil.mutListener.listen(28839)) {
            if (getAutoSaveTextHelper().getUniqueId() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28840)) {
            getAutoSaveTextHelper().saveString(this);
        }
    }

    public void setOnBackListener(OnEditTextBackListener listener) {
        if (!ListenerUtil.mutListener.listen(28841)) {
            mBackListener = listener;
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (!ListenerUtil.mutListener.listen(28842)) {
            // store the current Focused state
            savedState.mIsFocused = isFocused();
        }
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!ListenerUtil.mutListener.listen(28844)) {
            if (!(state instanceof SavedState)) {
                if (!ListenerUtil.mutListener.listen(28843)) {
                    super.onRestoreInstanceState(state);
                }
                return;
            }
        }
        SavedState savedState = (SavedState) state;
        if (!ListenerUtil.mutListener.listen(28845)) {
            super.onRestoreInstanceState(savedState.getSuperState());
        }
        if (!ListenerUtil.mutListener.listen(28847)) {
            // if we were focused, setup a properly timed future request for focus
            if (savedState.mIsFocused) {
                if (!ListenerUtil.mutListener.listen(28846)) {
                    // on-screen IME opening
                    addOnLayoutChangeListener(mOneoffFocusRequest);
                }
            }
        }
    }

    private final OnLayoutChangeListener mOneoffFocusRequest = new OnLayoutChangeListener() {

        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (!ListenerUtil.mutListener.listen(28851)) {
                // we're now at a good point in time to launch a focus request
                post(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(28848)) {
                            // self unregister so we won't auto-request focus again
                            removeOnLayoutChangeListener(mOneoffFocusRequest);
                        }
                        if (!ListenerUtil.mutListener.listen(28849)) {
                            // request focus
                            setFocusableInTouchMode(true);
                        }
                        if (!ListenerUtil.mutListener.listen(28850)) {
                            requestFocus();
                        }
                    }
                });
            }
        }
    };

    @Override
    public boolean performClick() {
        if (!ListenerUtil.mutListener.listen(28852)) {
            // make sure we are focusable otherwise we will not get focused
            setFocusableInTouchMode(true);
        }
        if (!ListenerUtil.mutListener.listen(28853)) {
            requestFocus();
        }
        return super.performClick();
    }

    /*
     * detect when user hits the back button while soft keyboard is showing (hiding the keyboard)
     */
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(28859)) {
            if ((ListenerUtil.mutListener.listen(28854) ? (event.getKeyCode() == KeyEvent.KEYCODE_BACK || event.getAction() == KeyEvent.ACTION_UP) : (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP))) {
                if (!ListenerUtil.mutListener.listen(28855)) {
                    // widget on the page
                    setFocusableInTouchMode(false);
                }
                if (!ListenerUtil.mutListener.listen(28856)) {
                    clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(28858)) {
                    if (mBackListener != null) {
                        if (!ListenerUtil.mutListener.listen(28857)) {
                            mBackListener.onEditTextBack();
                        }
                    }
                }
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (!ListenerUtil.mutListener.listen(28860)) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
        if (!ListenerUtil.mutListener.listen(28865)) {
            // if no hardware keys are present, associate being focused to having the on-screen keyboard visible
            if (!DeviceUtils.getInstance().hasHardwareKeyboard(getContext())) {
                InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (!ListenerUtil.mutListener.listen(28864)) {
                    if (focused) {
                        if (!ListenerUtil.mutListener.listen(28863)) {
                            // show the on-screen keybpoard if we got focused
                            inputMethodManager.showSoftInput(this, 0);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(28861)) {
                            // stop being focusable so closing the keyboard won't focus us
                            setFocusableInTouchMode(false);
                        }
                        if (!ListenerUtil.mutListener.listen(28862)) {
                            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                        }
                    }
                }
            }
        }
    }

    /**
     * Local class for holding the EditBox's focused or not state
     */
    static class SavedState extends BaseSavedState {

        private boolean mIsFocused;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            if (!ListenerUtil.mutListener.listen(28866)) {
                this.mIsFocused = (in.readInt() == 1);
            }
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            if (!ListenerUtil.mutListener.listen(28867)) {
                super.writeToParcel(out, flags);
            }
            if (!ListenerUtil.mutListener.listen(28868)) {
                out.writeInt(this.mIsFocused ? 1 : 0);
            }
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
