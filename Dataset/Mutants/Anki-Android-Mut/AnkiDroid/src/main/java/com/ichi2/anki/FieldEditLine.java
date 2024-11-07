/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.AbsSavedState;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.ichi2.ui.AnimationUtil;
import java.util.Locale;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FieldEditLine extends FrameLayout {

    private FieldEditText mEditText;

    private TextView mLabel;

    private ImageButton mMediaButton;

    private ImageButton mExpandButton;

    private String mName;

    private ExpansionState mExpansionState;

    private boolean mEnableAnimation = true;

    public FieldEditLine(@NonNull Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(8250)) {
            init();
        }
    }

    public FieldEditLine(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(8251)) {
            init();
        }
    }

    public FieldEditLine(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(8252)) {
            init();
        }
    }

    public FieldEditLine(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!ListenerUtil.mutListener.listen(8253)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(8254)) {
            LayoutInflater.from(getContext()).inflate(R.layout.card_multimedia_editline, this, true);
        }
        if (!ListenerUtil.mutListener.listen(8255)) {
            this.mEditText = findViewById(R.id.id_note_editText);
        }
        if (!ListenerUtil.mutListener.listen(8256)) {
            this.mLabel = findViewById(R.id.id_label);
        }
        if (!ListenerUtil.mutListener.listen(8257)) {
            this.mMediaButton = findViewById(R.id.id_media_button);
        }
        ConstraintLayout mConstraintLayout = findViewById(R.id.constraint_layout);
        if (!ListenerUtil.mutListener.listen(8258)) {
            this.mExpandButton = findViewById(R.id.id_expand_button);
        }
        if (!ListenerUtil.mutListener.listen(8272)) {
            // 7433 -
            if ((ListenerUtil.mutListener.listen(8263) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8262) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8261) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8260) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8259) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                if (!ListenerUtil.mutListener.listen(8264)) {
                    mEditText.setId(ViewCompat.generateViewId());
                }
                if (!ListenerUtil.mutListener.listen(8265)) {
                    mMediaButton.setId(ViewCompat.generateViewId());
                }
                if (!ListenerUtil.mutListener.listen(8266)) {
                    mExpandButton.setId(ViewCompat.generateViewId());
                }
                if (!ListenerUtil.mutListener.listen(8267)) {
                    mEditText.setNextFocusForwardId(mMediaButton.getId());
                }
                if (!ListenerUtil.mutListener.listen(8268)) {
                    mMediaButton.setNextFocusForwardId(mExpandButton.getId());
                }
                ConstraintSet constraintSet = new ConstraintSet();
                if (!ListenerUtil.mutListener.listen(8269)) {
                    constraintSet.clone(mConstraintLayout);
                }
                if (!ListenerUtil.mutListener.listen(8270)) {
                    constraintSet.connect(mMediaButton.getId(), ConstraintSet.END, mExpandButton.getId(), ConstraintSet.START);
                }
                if (!ListenerUtil.mutListener.listen(8271)) {
                    constraintSet.applyTo(mConstraintLayout);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8273)) {
            this.mExpansionState = ExpansionState.EXPANDED;
        }
        if (!ListenerUtil.mutListener.listen(8274)) {
            setExpanderBackgroundImage();
        }
        if (!ListenerUtil.mutListener.listen(8275)) {
            mExpandButton.setOnClickListener((v) -> toggleExpansionState());
        }
        if (!ListenerUtil.mutListener.listen(8276)) {
            mEditText.init();
        }
        if (!ListenerUtil.mutListener.listen(8277)) {
            mLabel.setPadding((int) UIUtils.getDensityAdjustedValue(getContext(), 3.4f), 0, 0, 0);
        }
    }

    private void toggleExpansionState() {
        if (!ListenerUtil.mutListener.listen(8282)) {
            switch(mExpansionState) {
                case EXPANDED:
                    {
                        if (!ListenerUtil.mutListener.listen(8278)) {
                            AnimationUtil.collapseView(mEditText, mEnableAnimation);
                        }
                        if (!ListenerUtil.mutListener.listen(8279)) {
                            mExpansionState = ExpansionState.COLLAPSED;
                        }
                        break;
                    }
                case COLLAPSED:
                    {
                        if (!ListenerUtil.mutListener.listen(8280)) {
                            AnimationUtil.expandView(mEditText, mEnableAnimation);
                        }
                        if (!ListenerUtil.mutListener.listen(8281)) {
                            mExpansionState = ExpansionState.EXPANDED;
                        }
                        break;
                    }
                default:
            }
        }
        if (!ListenerUtil.mutListener.listen(8283)) {
            setExpanderBackgroundImage();
        }
    }

    private void setExpanderBackgroundImage() {
        if (!ListenerUtil.mutListener.listen(8286)) {
            switch(mExpansionState) {
                case COLLAPSED:
                    if (!ListenerUtil.mutListener.listen(8284)) {
                        mExpandButton.setBackground(getBackgroundImage(R.drawable.ic_expand_more_black_24dp_xml));
                    }
                    break;
                case EXPANDED:
                    if (!ListenerUtil.mutListener.listen(8285)) {
                        mExpandButton.setBackground(getBackgroundImage(R.drawable.ic_expand_less_black_24dp));
                    }
                    break;
            }
        }
    }

    private Drawable getBackgroundImage(@DrawableRes int idRes) {
        return VectorDrawableCompat.create(this.getResources(), idRes, getContext().getTheme());
    }

    public void setActionModeCallbacks(ActionMode.Callback callback) {
        if (!ListenerUtil.mutListener.listen(8287)) {
            mEditText.setCustomSelectionActionModeCallback(callback);
        }
        if (!ListenerUtil.mutListener.listen(8294)) {
            if ((ListenerUtil.mutListener.listen(8292) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(8291) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(8290) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(8289) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(8288) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(8293)) {
                    mEditText.setCustomInsertionActionModeCallback(callback);
                }
            }
        }
    }

    public void setTypeface(@Nullable Typeface typeface) {
        if (!ListenerUtil.mutListener.listen(8296)) {
            if (typeface != null) {
                if (!ListenerUtil.mutListener.listen(8295)) {
                    mEditText.setTypeface(typeface);
                }
            }
        }
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(8297)) {
            mName = name;
        }
        if (!ListenerUtil.mutListener.listen(8298)) {
            mEditText.setContentDescription(name);
        }
        if (!ListenerUtil.mutListener.listen(8299)) {
            mLabel.setText(name);
        }
    }

    public void setHintLocale(@Nullable Locale hintLocale) {
        if (!ListenerUtil.mutListener.listen(8307)) {
            if ((ListenerUtil.mutListener.listen(8305) ? ((ListenerUtil.mutListener.listen(8304) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8303) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8302) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8301) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8300) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) || hintLocale != null) : ((ListenerUtil.mutListener.listen(8304) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8303) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8302) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8301) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(8300) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) && hintLocale != null))) {
                if (!ListenerUtil.mutListener.listen(8306)) {
                    mEditText.setHintLocale(hintLocale);
                }
            }
        }
    }

    public void setContent(String content, boolean replaceNewline) {
        if (!ListenerUtil.mutListener.listen(8308)) {
            mEditText.setContent(content, replaceNewline);
        }
    }

    public void setOrd(int i) {
        if (!ListenerUtil.mutListener.listen(8309)) {
            mEditText.setOrd(i);
        }
    }

    public void setEnableAnimation(boolean value) {
        if (!ListenerUtil.mutListener.listen(8310)) {
            this.mEnableAnimation = value;
        }
    }

    public String getName() {
        return mName;
    }

    public ImageButton getMediaButton() {
        return mMediaButton;
    }

    public View getLastViewInTabOrder() {
        return mExpandButton;
    }

    public FieldEditText getEditText() {
        return mEditText;
    }

    public void loadState(AbsSavedState state) {
        if (!ListenerUtil.mutListener.listen(8311)) {
            this.onRestoreInstanceState(state);
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        if (!ListenerUtil.mutListener.listen(8312)) {
            dispatchFreezeSelfOnly(container);
        }
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        if (!ListenerUtil.mutListener.listen(8313)) {
            dispatchThawSelfOnly(container);
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable state = super.onSaveInstanceState();
        SavedState savedState = new SavedState(state);
        if (!ListenerUtil.mutListener.listen(8314)) {
            savedState.mChildrenStates = new SparseArray<>();
        }
        if (!ListenerUtil.mutListener.listen(8315)) {
            savedState.mEditTextId = getEditText().getId();
        }
        if (!ListenerUtil.mutListener.listen(8316)) {
            savedState.mMediaButtonId = getMediaButton().getId();
        }
        if (!ListenerUtil.mutListener.listen(8317)) {
            savedState.mExpandButtonId = mExpandButton.getId();
        }
        if (!ListenerUtil.mutListener.listen(8324)) {
            {
                long _loopCounter134 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8323) ? (i >= getChildCount()) : (ListenerUtil.mutListener.listen(8322) ? (i <= getChildCount()) : (ListenerUtil.mutListener.listen(8321) ? (i > getChildCount()) : (ListenerUtil.mutListener.listen(8320) ? (i != getChildCount()) : (ListenerUtil.mutListener.listen(8319) ? (i == getChildCount()) : (i < getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter134", ++_loopCounter134);
                    if (!ListenerUtil.mutListener.listen(8318)) {
                        getChildAt(i).saveHierarchyState(savedState.mChildrenStates);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8325)) {
            savedState.mExpansionState = mExpansionState;
        }
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!ListenerUtil.mutListener.listen(8327)) {
            if (!(state instanceof SavedState)) {
                if (!ListenerUtil.mutListener.listen(8326)) {
                    super.onRestoreInstanceState(state);
                }
                return;
            }
        }
        SavedState ss = (SavedState) state;
        int editTextId = mEditText.getId();
        int mediaButtonId = mMediaButton.getId();
        int expandButtonId = mExpandButton.getId();
        if (!ListenerUtil.mutListener.listen(8328)) {
            mEditText.setId(ss.mEditTextId);
        }
        if (!ListenerUtil.mutListener.listen(8329)) {
            mMediaButton.setId(ss.mMediaButtonId);
        }
        if (!ListenerUtil.mutListener.listen(8330)) {
            mExpandButton.setId(ss.mExpandButtonId);
        }
        if (!ListenerUtil.mutListener.listen(8331)) {
            super.onRestoreInstanceState(ss.getSuperState());
        }
        if (!ListenerUtil.mutListener.listen(8338)) {
            {
                long _loopCounter135 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8337) ? (i >= getChildCount()) : (ListenerUtil.mutListener.listen(8336) ? (i <= getChildCount()) : (ListenerUtil.mutListener.listen(8335) ? (i > getChildCount()) : (ListenerUtil.mutListener.listen(8334) ? (i != getChildCount()) : (ListenerUtil.mutListener.listen(8333) ? (i == getChildCount()) : (i < getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter135", ++_loopCounter135);
                    if (!ListenerUtil.mutListener.listen(8332)) {
                        getChildAt(i).restoreHierarchyState(ss.mChildrenStates);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8339)) {
            mEditText.setId(editTextId);
        }
        if (!ListenerUtil.mutListener.listen(8340)) {
            mMediaButton.setId(mediaButtonId);
        }
        if (!ListenerUtil.mutListener.listen(8341)) {
            mExpandButton.setId(expandButtonId);
        }
        if (!ListenerUtil.mutListener.listen(8343)) {
            if (mExpansionState != ss.mExpansionState) {
                if (!ListenerUtil.mutListener.listen(8342)) {
                    toggleExpansionState();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8344)) {
            this.mExpansionState = ss.mExpansionState;
        }
    }

    static class SavedState extends BaseSavedState {

        private SparseArray<Parcelable> mChildrenStates;

        private int mEditTextId;

        private int mMediaButtonId;

        public int mExpandButtonId;

        private ExpansionState mExpansionState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            if (!ListenerUtil.mutListener.listen(8345)) {
                super.writeToParcel(out, flags);
            }
            if (!ListenerUtil.mutListener.listen(8346)) {
                out.writeSparseArray(mChildrenStates);
            }
            if (!ListenerUtil.mutListener.listen(8347)) {
                out.writeInt(mEditTextId);
            }
            if (!ListenerUtil.mutListener.listen(8348)) {
                out.writeInt(mMediaButtonId);
            }
            if (!ListenerUtil.mutListener.listen(8349)) {
                out.writeInt(mExpandButtonId);
            }
            if (!ListenerUtil.mutListener.listen(8350)) {
                out.writeSerializable(mExpansionState);
            }
        }

        // required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            @Override
            public SavedState createFromParcel(Parcel source) {
                throw new IllegalStateException();
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        private SavedState(Parcel in, ClassLoader loader) {
            super(in);
            if (!ListenerUtil.mutListener.listen(8351)) {
                this.mChildrenStates = in.readSparseArray(loader);
            }
            if (!ListenerUtil.mutListener.listen(8352)) {
                this.mEditTextId = in.readInt();
            }
            if (!ListenerUtil.mutListener.listen(8353)) {
                this.mMediaButtonId = in.readInt();
            }
            if (!ListenerUtil.mutListener.listen(8354)) {
                this.mExpandButtonId = in.readInt();
            }
            if (!ListenerUtil.mutListener.listen(8355)) {
                this.mExpansionState = (ExpansionState) in.readSerializable();
            }
        }
    }

    public enum ExpansionState {

        EXPANDED, COLLAPSED
    }
}
