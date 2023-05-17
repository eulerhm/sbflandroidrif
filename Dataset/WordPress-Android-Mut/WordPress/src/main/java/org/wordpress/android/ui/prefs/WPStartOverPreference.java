package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPStartOverPreference extends WPPreference {

    private String mButtonText;

    private int mButtonTextColor;

    private boolean mButtonTextAllCaps;

    private Drawable mPrefIcon;

    public WPStartOverPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WPStartOverPreference);
        if (!ListenerUtil.mutListener.listen(17034)) {
            {
                long _loopCounter278 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(17033) ? (i >= array.getIndexCount()) : (ListenerUtil.mutListener.listen(17032) ? (i <= array.getIndexCount()) : (ListenerUtil.mutListener.listen(17031) ? (i > array.getIndexCount()) : (ListenerUtil.mutListener.listen(17030) ? (i != array.getIndexCount()) : (ListenerUtil.mutListener.listen(17029) ? (i == array.getIndexCount()) : (i < array.getIndexCount())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter278", ++_loopCounter278);
                    int index = array.getIndex(i);
                    if (!ListenerUtil.mutListener.listen(17028)) {
                        if (index == R.styleable.WPStartOverPreference_buttonText) {
                            if (!ListenerUtil.mutListener.listen(17027)) {
                                mButtonText = array.getString(index);
                            }
                        } else if (index == R.styleable.WPStartOverPreference_buttonTextColor) {
                            if (!ListenerUtil.mutListener.listen(17026)) {
                                mButtonTextColor = array.getColor(index, ContextCompat.getColor(context, 0));
                            }
                        } else if (index == R.styleable.WPStartOverPreference_buttonTextAllCaps) {
                            if (!ListenerUtil.mutListener.listen(17025)) {
                                mButtonTextAllCaps = array.getBoolean(index, false);
                            }
                        } else if (index == R.styleable.WPStartOverPreference_preficon) {
                            if (!ListenerUtil.mutListener.listen(17024)) {
                                mPrefIcon = VectorDrawableCompat.create(context.getResources(), array.getResourceId(index, 0), null);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17035)) {
            array.recycle();
        }
    }

    @Override
    protected void onBindView(@NonNull View view) {
        if (!ListenerUtil.mutListener.listen(17036)) {
            super.onBindView(view);
        }
        if (!ListenerUtil.mutListener.listen(17038)) {
            if (view.findViewById(R.id.pref_icon) != null) {
                ImageView imageView = view.findViewById(R.id.pref_icon);
                if (!ListenerUtil.mutListener.listen(17037)) {
                    imageView.setImageDrawable(mPrefIcon);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17049)) {
            if (view.findViewById(R.id.button) != null) {
                final WPStartOverPreference wpStartOverPreference = this;
                Button button = view.findViewById(R.id.button);
                if (!ListenerUtil.mutListener.listen(17039)) {
                    button.setText(mButtonText);
                }
                if (!ListenerUtil.mutListener.listen(17046)) {
                    if ((ListenerUtil.mutListener.listen(17044) ? (mButtonTextColor >= 0) : (ListenerUtil.mutListener.listen(17043) ? (mButtonTextColor <= 0) : (ListenerUtil.mutListener.listen(17042) ? (mButtonTextColor < 0) : (ListenerUtil.mutListener.listen(17041) ? (mButtonTextColor != 0) : (ListenerUtil.mutListener.listen(17040) ? (mButtonTextColor == 0) : (mButtonTextColor > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(17045)) {
                            button.setTextColor(mButtonTextColor);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17047)) {
                    button.setAllCaps(mButtonTextAllCaps);
                }
                if (!ListenerUtil.mutListener.listen(17048)) {
                    button.setOnClickListener(v -> getOnPreferenceClickListener().onPreferenceClick(wpStartOverPreference));
                }
            }
        }
    }
}
