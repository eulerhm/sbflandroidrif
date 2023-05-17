package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.SwitchPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPSwitchPreference extends SwitchPreference implements PreferenceHint {

    private String mHint;

    private ColorStateList mTint;

    private ColorStateList mThumbTint;

    private int mStartOffset = 0;

    public WPSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SummaryEditTextPreference);
        if (!ListenerUtil.mutListener.listen(17066)) {
            {
                long _loopCounter279 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(17065) ? (i >= array.getIndexCount()) : (ListenerUtil.mutListener.listen(17064) ? (i <= array.getIndexCount()) : (ListenerUtil.mutListener.listen(17063) ? (i > array.getIndexCount()) : (ListenerUtil.mutListener.listen(17062) ? (i != array.getIndexCount()) : (ListenerUtil.mutListener.listen(17061) ? (i == array.getIndexCount()) : (i < array.getIndexCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter279", ++_loopCounter279);
                    int index = array.getIndex(i);
                    if (!ListenerUtil.mutListener.listen(17060)) {
                        if (index == R.styleable.SummaryEditTextPreference_longClickHint) {
                            if (!ListenerUtil.mutListener.listen(17059)) {
                                mHint = array.getString(index);
                            }
                        } else if (index == R.styleable.SummaryEditTextPreference_iconTint) {
                            int resourceId = array.getResourceId(index, 0);
                            if (!ListenerUtil.mutListener.listen(17058)) {
                                if ((ListenerUtil.mutListener.listen(17056) ? (resourceId >= 0) : (ListenerUtil.mutListener.listen(17055) ? (resourceId <= 0) : (ListenerUtil.mutListener.listen(17054) ? (resourceId > 0) : (ListenerUtil.mutListener.listen(17053) ? (resourceId < 0) : (ListenerUtil.mutListener.listen(17052) ? (resourceId == 0) : (resourceId != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(17057)) {
                                        mTint = AppCompatResources.getColorStateList(context, resourceId);
                                    }
                                }
                            }
                        } else if (index == R.styleable.SummaryEditTextPreference_switchThumbTint) {
                            if (!ListenerUtil.mutListener.listen(17051)) {
                                mThumbTint = array.getColorStateList(index);
                            }
                        } else if (index == R.styleable.SummaryEditTextPreference_startOffset) {
                            if (!ListenerUtil.mutListener.listen(17050)) {
                                mStartOffset = array.getDimensionPixelSize(index, 0);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17067)) {
            array.recycle();
        }
    }

    @Override
    protected void onBindView(@NonNull View view) {
        if (!ListenerUtil.mutListener.listen(17068)) {
            super.onBindView(view);
        }
        ImageView icon = view.findViewById(android.R.id.icon);
        if (!ListenerUtil.mutListener.listen(17071)) {
            if ((ListenerUtil.mutListener.listen(17069) ? (icon != null || mTint != null) : (icon != null && mTint != null))) {
                if (!ListenerUtil.mutListener.listen(17070)) {
                    icon.setImageTintList(mTint);
                }
            }
        }
        TextView titleView = view.findViewById(android.R.id.title);
        if (!ListenerUtil.mutListener.listen(17079)) {
            if (titleView != null) {
                Resources res = getContext().getResources();
                if (!ListenerUtil.mutListener.listen(17078)) {
                    // add padding to the start of nested preferences
                    if (!TextUtils.isEmpty(getDependency())) {
                        int margin = res.getDimensionPixelSize(R.dimen.margin_large);
                        if (!ListenerUtil.mutListener.listen(17077)) {
                            ViewCompat.setPaddingRelative(titleView, (ListenerUtil.mutListener.listen(17076) ? (margin % mStartOffset) : (ListenerUtil.mutListener.listen(17075) ? (margin / mStartOffset) : (ListenerUtil.mutListener.listen(17074) ? (margin * mStartOffset) : (ListenerUtil.mutListener.listen(17073) ? (margin - mStartOffset) : (margin + mStartOffset))))), 0, 0, 0);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17072)) {
                            ViewCompat.setPaddingRelative(titleView, mStartOffset, 0, 0, 0);
                        }
                    }
                }
            }
        }
        // style custom switch preference
        Switch switchControl = getSwitch((ViewGroup) view);
        if (!ListenerUtil.mutListener.listen(17082)) {
            if (switchControl != null) {
                if (!ListenerUtil.mutListener.listen(17081)) {
                    if (mThumbTint != null) {
                        if (!ListenerUtil.mutListener.listen(17080)) {
                            switchControl.setThumbTintList(mThumbTint);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17083)) {
            // Add padding to start of switch.
            ViewCompat.setPaddingRelative(getSwitch((ViewGroup) view), getContext().getResources().getDimensionPixelSize(R.dimen.margin_extra_large), 0, 0, 0);
        }
    }

    private Switch getSwitch(ViewGroup parentView) {
        if (!ListenerUtil.mutListener.listen(17091)) {
            {
                long _loopCounter280 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(17090) ? (i >= parentView.getChildCount()) : (ListenerUtil.mutListener.listen(17089) ? (i <= parentView.getChildCount()) : (ListenerUtil.mutListener.listen(17088) ? (i > parentView.getChildCount()) : (ListenerUtil.mutListener.listen(17087) ? (i != parentView.getChildCount()) : (ListenerUtil.mutListener.listen(17086) ? (i == parentView.getChildCount()) : (i < parentView.getChildCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter280", ++_loopCounter280);
                    View childView = parentView.getChildAt(i);
                    if (!ListenerUtil.mutListener.listen(17085)) {
                        if (childView instanceof Switch) {
                            return (Switch) childView;
                        } else if (childView instanceof ViewGroup) {
                            Switch theSwitch = getSwitch((ViewGroup) childView);
                            if (!ListenerUtil.mutListener.listen(17084)) {
                                if (theSwitch != null) {
                                    return theSwitch;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
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
        if (!ListenerUtil.mutListener.listen(17092)) {
            mHint = hint;
        }
    }
}
