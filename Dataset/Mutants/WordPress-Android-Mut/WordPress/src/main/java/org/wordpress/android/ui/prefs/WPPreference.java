package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.TextViewCompat;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPPreference extends Preference implements PreferenceHint {

    private String mHint;

    public WPPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DetailListPreference);
        if (!ListenerUtil.mutListener.listen(17010)) {
            {
                long _loopCounter277 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(17009) ? (i >= array.getIndexCount()) : (ListenerUtil.mutListener.listen(17008) ? (i <= array.getIndexCount()) : (ListenerUtil.mutListener.listen(17007) ? (i > array.getIndexCount()) : (ListenerUtil.mutListener.listen(17006) ? (i != array.getIndexCount()) : (ListenerUtil.mutListener.listen(17005) ? (i == array.getIndexCount()) : (i < array.getIndexCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter277", ++_loopCounter277);
                    int index = array.getIndex(i);
                    if (!ListenerUtil.mutListener.listen(17004)) {
                        if (index == R.styleable.DetailListPreference_longClickHint) {
                            if (!ListenerUtil.mutListener.listen(17003)) {
                                mHint = array.getString(index);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17011)) {
            array.recycle();
        }
    }

    @Override
    protected void onBindView(@NonNull View view) {
        if (!ListenerUtil.mutListener.listen(17012)) {
            super.onBindView(view);
        }
        Resources res = getContext().getResources();
        TextView titleView = view.findViewById(android.R.id.title);
        TextView summaryView = view.findViewById(android.R.id.summary);
        if (!ListenerUtil.mutListener.listen(17017)) {
            if (titleView != null) {
                if (!ListenerUtil.mutListener.listen(17013)) {
                    TextViewCompat.setTextAppearance(titleView, R.style.TextAppearance_MaterialComponents_Subtitle1);
                }
                if (!ListenerUtil.mutListener.listen(17016)) {
                    if (!isEnabled()) {
                        if (!ListenerUtil.mutListener.listen(17015)) {
                            titleView.setAlpha(ResourcesCompat.getFloat(res, R.dimen.material_emphasis_disabled));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17014)) {
                            titleView.setAlpha(1f);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17022)) {
            if (summaryView != null) {
                if (!ListenerUtil.mutListener.listen(17018)) {
                    TextViewCompat.setTextAppearance(summaryView, R.style.TextAppearance_MaterialComponents_Body2);
                }
                if (!ListenerUtil.mutListener.listen(17021)) {
                    if (!isEnabled()) {
                        if (!ListenerUtil.mutListener.listen(17020)) {
                            summaryView.setAlpha(ResourcesCompat.getFloat(res, R.dimen.material_emphasis_disabled));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17019)) {
                            summaryView.setAlpha(ResourcesCompat.getFloat(res, R.dimen.material_emphasis_medium));
                        }
                    }
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
        if (!ListenerUtil.mutListener.listen(17023)) {
            mHint = hint;
        }
    }
}
