package org.wordpress.android.ui.prefs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SummaryEditTextPreference extends EditTextPreference implements PreferenceHint {

    private int mLines;

    private int mMaxLines;

    private String mHint;

    private String mDialogMessage;

    private AlertDialog mDialog;

    private int mWhichButtonClicked;

    public SummaryEditTextPreference(Context context) {
        super(context);
    }

    public SummaryEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SummaryEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(16443)) {
            mLines = -1;
        }
        if (!ListenerUtil.mutListener.listen(16444)) {
            mMaxLines = -1;
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SummaryEditTextPreference);
        if (!ListenerUtil.mutListener.listen(16455)) {
            {
                long _loopCounter269 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(16454) ? (i >= array.getIndexCount()) : (ListenerUtil.mutListener.listen(16453) ? (i <= array.getIndexCount()) : (ListenerUtil.mutListener.listen(16452) ? (i > array.getIndexCount()) : (ListenerUtil.mutListener.listen(16451) ? (i != array.getIndexCount()) : (ListenerUtil.mutListener.listen(16450) ? (i == array.getIndexCount()) : (i < array.getIndexCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter269", ++_loopCounter269);
                    int index = array.getIndex(i);
                    if (!ListenerUtil.mutListener.listen(16449)) {
                        if (index == R.styleable.SummaryEditTextPreference_summaryLines) {
                            if (!ListenerUtil.mutListener.listen(16448)) {
                                mLines = array.getInt(index, -1);
                            }
                        } else if (index == R.styleable.SummaryEditTextPreference_maxSummaryLines) {
                            if (!ListenerUtil.mutListener.listen(16447)) {
                                mMaxLines = array.getInt(index, -1);
                            }
                        } else if (index == R.styleable.SummaryEditTextPreference_longClickHint) {
                            if (!ListenerUtil.mutListener.listen(16446)) {
                                mHint = array.getString(index);
                            }
                        } else if (index == R.styleable.SummaryEditTextPreference_dialogMessage) {
                            if (!ListenerUtil.mutListener.listen(16445)) {
                                mDialogMessage = array.getString(index);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16456)) {
            array.recycle();
        }
    }

    @Override
    protected void onBindView(@NonNull View view) {
        if (!ListenerUtil.mutListener.listen(16457)) {
            super.onBindView(view);
        }
        TextView summaryView = view.findViewById(android.R.id.summary);
        if (!ListenerUtil.mutListener.listen(16474)) {
            if (summaryView != null) {
                if (!ListenerUtil.mutListener.listen(16458)) {
                    summaryView.setEllipsize(TextUtils.TruncateAt.END);
                }
                if (!ListenerUtil.mutListener.listen(16459)) {
                    summaryView.setInputType(getEditText().getInputType());
                }
                if (!ListenerUtil.mutListener.listen(16466)) {
                    if ((ListenerUtil.mutListener.listen(16464) ? (mLines >= -1) : (ListenerUtil.mutListener.listen(16463) ? (mLines <= -1) : (ListenerUtil.mutListener.listen(16462) ? (mLines > -1) : (ListenerUtil.mutListener.listen(16461) ? (mLines < -1) : (ListenerUtil.mutListener.listen(16460) ? (mLines == -1) : (mLines != -1)))))))
                        if (!ListenerUtil.mutListener.listen(16465)) {
                            summaryView.setLines(mLines);
                        }
                }
                if (!ListenerUtil.mutListener.listen(16473)) {
                    if ((ListenerUtil.mutListener.listen(16471) ? (mMaxLines >= -1) : (ListenerUtil.mutListener.listen(16470) ? (mMaxLines <= -1) : (ListenerUtil.mutListener.listen(16469) ? (mMaxLines > -1) : (ListenerUtil.mutListener.listen(16468) ? (mMaxLines < -1) : (ListenerUtil.mutListener.listen(16467) ? (mMaxLines == -1) : (mMaxLines != -1)))))))
                        if (!ListenerUtil.mutListener.listen(16472)) {
                            summaryView.setMaxLines(mMaxLines);
                        }
                }
            }
        }
    }

    @Override
    public Dialog getDialog() {
        return mDialog;
    }

    @Override
    protected void showDialog(Bundle state) {
        Context context = getContext();
        Resources res = context.getResources();
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        View titleView = View.inflate(getContext(), R.layout.detail_list_preference_title, null);
        if (!ListenerUtil.mutListener.listen(16475)) {
            mWhichButtonClicked = DialogInterface.BUTTON_NEGATIVE;
        }
        if (!ListenerUtil.mutListener.listen(16476)) {
            builder.setPositiveButton(android.R.string.ok, this);
        }
        if (!ListenerUtil.mutListener.listen(16477)) {
            builder.setNegativeButton(res.getString(android.R.string.cancel).toUpperCase(Locale.getDefault()), this);
        }
        if (!ListenerUtil.mutListener.listen(16482)) {
            if (titleView != null) {
                TextView titleText = titleView.findViewById(R.id.title);
                if (!ListenerUtil.mutListener.listen(16480)) {
                    if (titleText != null) {
                        if (!ListenerUtil.mutListener.listen(16479)) {
                            titleText.setText(getDialogTitle());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16481)) {
                    builder.setCustomTitle(titleView);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16478)) {
                    builder.setTitle(getTitle());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16484)) {
            if (mDialogMessage != null) {
                if (!ListenerUtil.mutListener.listen(16483)) {
                    builder.setMessage(mDialogMessage);
                }
            }
        }
        View view = View.inflate(getContext(), getDialogLayoutResource(), null);
        if (!ListenerUtil.mutListener.listen(16487)) {
            if (view != null) {
                if (!ListenerUtil.mutListener.listen(16485)) {
                    onBindDialogView(view);
                }
                if (!ListenerUtil.mutListener.listen(16486)) {
                    builder.setView(view);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16488)) {
            mDialog = builder.create();
        }
        if (!ListenerUtil.mutListener.listen(16490)) {
            if (state != null) {
                if (!ListenerUtil.mutListener.listen(16489)) {
                    mDialog.onRestoreInstanceState(state);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16491)) {
            mDialog.setOnDismissListener(this);
        }
        if (!ListenerUtil.mutListener.listen(16492)) {
            mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(16493)) {
            mDialog.show();
        }
    }

    @Override
    protected void onBindDialogView(final View view) {
        if (!ListenerUtil.mutListener.listen(16494)) {
            super.onBindDialogView(view);
        }
        if (!ListenerUtil.mutListener.listen(16495)) {
            if (view == null)
                return;
        }
        EditText editText = getEditText();
        ViewParent oldParent = editText.getParent();
        if (!ListenerUtil.mutListener.listen(16500)) {
            if (oldParent != view) {
                if (!ListenerUtil.mutListener.listen(16498)) {
                    if (oldParent instanceof ViewGroup) {
                        ViewGroup groupParent = (ViewGroup) oldParent;
                        if (!ListenerUtil.mutListener.listen(16496)) {
                            groupParent.removeView(editText);
                        }
                        if (!ListenerUtil.mutListener.listen(16497)) {
                            ViewCompat.setPaddingRelative(groupParent, ViewCompat.getPaddingStart(groupParent), 0, ViewCompat.getPaddingEnd(groupParent), groupParent.getPaddingBottom());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16499)) {
                    onAddEditTextToDialogView(view, editText);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16501)) {
            editText.setSelection(editText.getText().length());
        }
        if (!ListenerUtil.mutListener.listen(16502)) {
            // RtL language support
            editText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        }
        TextView message = view.findViewById(android.R.id.message);
        // Dialog message has some extra bottom margin we don't want
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) message.getLayoutParams();
        int leftMargin = 0;
        int bottomMargin = view.getResources().getDimensionPixelSize(R.dimen.margin_small);
        if (!ListenerUtil.mutListener.listen(16503)) {
            layoutParams.setMargins(0, layoutParams.topMargin, 0, bottomMargin);
        }
        if (!ListenerUtil.mutListener.listen(16504)) {
            MarginLayoutParamsCompat.setMarginStart(layoutParams, leftMargin);
        }
        if (!ListenerUtil.mutListener.listen(16505)) {
            MarginLayoutParamsCompat.setMarginEnd(layoutParams, layoutParams.rightMargin);
        }
        if (!ListenerUtil.mutListener.listen(16506)) {
            message.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(16507)) {
            mWhichButtonClicked = which;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(16508)) {
            mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(16509)) {
            onDialogClosed(mWhichButtonClicked == DialogInterface.BUTTON_POSITIVE);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (!ListenerUtil.mutListener.listen(16510)) {
            super.onDialogClosed(positiveResult);
        }
        if (!ListenerUtil.mutListener.listen(16512)) {
            if (positiveResult) {
                if (!ListenerUtil.mutListener.listen(16511)) {
                    callChangeListener(getEditText().getText());
                }
            }
        }
    }

    @Override
    public boolean hasHint() {
        return !TextUtils.isEmpty(mHint);
    }

    @Override
    public String getHint() {
        return mHint;
    }

    @Override
    public void setHint(String hint) {
        if (!ListenerUtil.mutListener.listen(16513)) {
            mHint = hint;
        }
    }
}
