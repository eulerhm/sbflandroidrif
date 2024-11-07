/*
 * Copyright (C) 2010 The Android Open Source Project
 * Copyright (C) 2011 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.view;

import org.onebusaway.android.R;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Very basic version of a search box, lightly copied/inspired by
 * the Honeycomb SearchView.
 *
 * @author paulw
 * @deprecated
 */
public class SearchViewV1 extends LinearLayout {

    private OnQueryTextListener mOnQueryChangeListener;

    private OnFocusChangeListener mOnQueryTextFocusChangeListener;

    private View mSearchButton;

    private EditText mQueryTextView;

    private CharSequence mQueryHint;

    private CharSequence mOldQueryText;

    /**
     * Callbacks for changes to the query text.
     */
    public interface OnQueryTextListener {

        /**
         * Called when the user submits the query. This could be due to a key press on the
         * keyboard or due to pressing a submit button.
         * The listener can override the standard behavior by returning true
         * to indicate that it has handled the submit request. Otherwise return false to
         * let the SearchView handle the submission by launching any associated intent.
         *
         * @param query the query text that is to be submitted
         * @return true if the query has been handled by the listener, false to let the
         * SearchView perform the default action.
         */
        boolean onQueryTextSubmit(String query);

        /**
         * Called when the query text is changed by the user.
         *
         * @param newText the new content of the query text field.
         * @return false if the SearchView should perform the default action of showing any
         * suggestions if available, true if the action was handled by the listener.
         */
        boolean onQueryTextChange(String newText);
    }

    public SearchViewV1(Context context) {
        this(context, null);
    }

    public SearchViewV1(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(10723)) {
            inflater.inflate(R.layout.search_box, this, true);
        }
        if (!ListenerUtil.mutListener.listen(10724)) {
            mSearchButton = findViewById(R.id.search_button);
        }
        if (!ListenerUtil.mutListener.listen(10725)) {
            mQueryTextView = (EditText) findViewById(R.id.search_text);
        }
        if (!ListenerUtil.mutListener.listen(10726)) {
            mSearchButton.setOnClickListener(mOnClickListener);
        }
        if (!ListenerUtil.mutListener.listen(10727)) {
            mQueryTextView.setOnClickListener(mOnClickListener);
        }
        if (!ListenerUtil.mutListener.listen(10728)) {
            mQueryTextView.addTextChangedListener(mTextWatcher);
        }
        if (!ListenerUtil.mutListener.listen(10729)) {
            mQueryTextView.setOnEditorActionListener(mOnEditorActionListener);
        }
        if (!ListenerUtil.mutListener.listen(10732)) {
            // Inform any listener of focus changes
            mQueryTextView.setOnFocusChangeListener(new OnFocusChangeListener() {

                public void onFocusChange(View v, boolean hasFocus) {
                    if (!ListenerUtil.mutListener.listen(10731)) {
                        if (mOnQueryTextFocusChangeListener != null) {
                            if (!ListenerUtil.mutListener.listen(10730)) {
                                mOnQueryTextFocusChangeListener.onFocusChange(SearchViewV1.this, hasFocus);
                            }
                        }
                    }
                }
            });
        }
    }

    /**
     * Sets a listener for user actions within the SearchView.
     *
     * @param listener the listener object that receives callbacks when the user performs
     *                 actions in the SearchView such as clicking on buttons or typing a query.
     */
    public void setOnQueryTextListener(OnQueryTextListener listener) {
        if (!ListenerUtil.mutListener.listen(10733)) {
            mOnQueryChangeListener = listener;
        }
    }

    /**
     * Sets a listener to inform when the focus of the query text field changes.
     *
     * @param listener the listener to inform of focus changes.
     */
    public void setOnQueryTextFocusChangeListener(OnFocusChangeListener listener) {
        if (!ListenerUtil.mutListener.listen(10734)) {
            mOnQueryTextFocusChangeListener = listener;
        }
    }

    /**
     * Returns the query string currently in the text field.
     *
     * @return the query string
     */
    public CharSequence getQuery() {
        return mQueryTextView.getText();
    }

    /**
     * Sets a query string in the text field and optionally submits the query as well.
     *
     * @param query  the query string. This replaces any query text already present in the
     *               text field.
     * @param submit whether to submit the query right now or only update the contents of
     *               text field.
     */
    public void setQuery(CharSequence query, boolean submit) {
        if (!ListenerUtil.mutListener.listen(10735)) {
            mQueryTextView.setText(query);
        }
        if (!ListenerUtil.mutListener.listen(10737)) {
            if (query != null) {
                if (!ListenerUtil.mutListener.listen(10736)) {
                    mQueryTextView.setSelection(query.length());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10740)) {
            // If the query is not empty and submit is requested, submit the query
            if ((ListenerUtil.mutListener.listen(10738) ? (submit || !TextUtils.isEmpty(query)) : (submit && !TextUtils.isEmpty(query)))) {
                if (!ListenerUtil.mutListener.listen(10739)) {
                    onSubmitQuery();
                }
            }
        }
    }

    /**
     * Sets the hint text to display in the query text field. This overrides any hint specified
     * in the SearchableInfo.
     *
     * @param hint the hint text to display
     * @attr ref android.R.styleable#SearchView_queryHint
     */
    public void setQueryHint(CharSequence hint) {
        if (!ListenerUtil.mutListener.listen(10741)) {
            mQueryHint = hint;
        }
        if (!ListenerUtil.mutListener.listen(10742)) {
            updateQueryHint();
        }
    }

    /**
     * Callback to watch the text field for empty/non-empty
     */
    private TextWatcher mTextWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int before, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int after) {
            if (!ListenerUtil.mutListener.listen(10743)) {
                SearchViewV1.this.onTextChanged(s);
            }
        }

        public void afterTextChanged(Editable s) {
        }
    };

    private final OnEditorActionListener mOnEditorActionListener = new OnEditorActionListener() {

        /**
         * Called when the input method default action key is pressed.
         */
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (!ListenerUtil.mutListener.listen(10744)) {
                onSubmitQuery();
            }
            return true;
        }
    };

    private void onTextChanged(CharSequence newText) {
        if (!ListenerUtil.mutListener.listen(10747)) {
            /*
        boolean hasText = !TextUtils.isEmpty(text);
        if (isSubmitButtonEnabled()) {
            updateSubmitButton(hasText);
        }
        updateVoiceButton(!hasText);
        updateCloseButton();
        updateSubmitArea();
        */
            if ((ListenerUtil.mutListener.listen(10745) ? (mOnQueryChangeListener != null || !TextUtils.equals(newText, mOldQueryText)) : (mOnQueryChangeListener != null && !TextUtils.equals(newText, mOldQueryText)))) {
                if (!ListenerUtil.mutListener.listen(10746)) {
                    mOnQueryChangeListener.onQueryTextChange(newText.toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10748)) {
            mOldQueryText = newText.toString();
        }
    }

    private void onSubmitQuery() {
        CharSequence query = mQueryTextView.getText();
        if (!ListenerUtil.mutListener.listen(10757)) {
            if ((ListenerUtil.mutListener.listen(10754) ? (query != null || (ListenerUtil.mutListener.listen(10753) ? (TextUtils.getTrimmedLength(query) >= 0) : (ListenerUtil.mutListener.listen(10752) ? (TextUtils.getTrimmedLength(query) <= 0) : (ListenerUtil.mutListener.listen(10751) ? (TextUtils.getTrimmedLength(query) < 0) : (ListenerUtil.mutListener.listen(10750) ? (TextUtils.getTrimmedLength(query) != 0) : (ListenerUtil.mutListener.listen(10749) ? (TextUtils.getTrimmedLength(query) == 0) : (TextUtils.getTrimmedLength(query) > 0))))))) : (query != null && (ListenerUtil.mutListener.listen(10753) ? (TextUtils.getTrimmedLength(query) >= 0) : (ListenerUtil.mutListener.listen(10752) ? (TextUtils.getTrimmedLength(query) <= 0) : (ListenerUtil.mutListener.listen(10751) ? (TextUtils.getTrimmedLength(query) < 0) : (ListenerUtil.mutListener.listen(10750) ? (TextUtils.getTrimmedLength(query) != 0) : (ListenerUtil.mutListener.listen(10749) ? (TextUtils.getTrimmedLength(query) == 0) : (TextUtils.getTrimmedLength(query) > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(10756)) {
                    if ((ListenerUtil.mutListener.listen(10755) ? (mOnQueryChangeListener == null && !mOnQueryChangeListener.onQueryTextSubmit(query.toString())) : (mOnQueryChangeListener == null || !mOnQueryChangeListener.onQueryTextSubmit(query.toString())))) {
                    }
                }
            }
        }
    }

    private final OnClickListener mOnClickListener = new OnClickListener() {

        public void onClick(View v) {
        }
    };

    private CharSequence getDecoratedHint(CharSequence hintText) {
        return hintText;
    }

    private void updateQueryHint() {
        if (!ListenerUtil.mutListener.listen(10760)) {
            if (mQueryHint != null) {
                if (!ListenerUtil.mutListener.listen(10759)) {
                    mQueryTextView.setHint(getDecoratedHint(mQueryHint));
                }
            } else /*else if (mSearchable != null) {
            CharSequence hint = null;
            int hintId = mSearchable.getHintId();
            if (hintId != 0) {
                hint = getContext().getString(hintId);
            }
            if (hint != null) {
                mQueryTextView.setHint(getDecoratedHint(hint));
            }
        } */
            {
                if (!ListenerUtil.mutListener.listen(10758)) {
                    mQueryTextView.setHint(getDecoratedHint(""));
                }
            }
        }
    }
}
