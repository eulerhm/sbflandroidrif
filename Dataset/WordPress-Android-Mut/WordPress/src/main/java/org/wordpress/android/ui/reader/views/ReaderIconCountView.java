package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import org.wordpress.android.R;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * used when showing comment + comment count, like + like count
 */
public class ReaderIconCountView extends LinearLayout {

    private ImageView mImageView;

    private TextView mTextCount;

    private int mIconType;

    // these must match the same values in attrs.xml
    private static final int ICON_LIKE = 0;

    private static final int ICON_COMMENT = 1;

    private static final int ICON_REBLOG = 2;

    public ReaderIconCountView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(19954)) {
            initView(context, null);
        }
    }

    public ReaderIconCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(19955)) {
            initView(context, attrs);
        }
    }

    public ReaderIconCountView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(19956)) {
            initView(context, attrs);
        }
    }

    private void initView(Context context, AttributeSet attrs) {
        if (!ListenerUtil.mutListener.listen(19957)) {
            inflate(context, R.layout.reader_icon_count_view, this);
        }
        if (!ListenerUtil.mutListener.listen(19958)) {
            mImageView = findViewById(R.id.image_count);
        }
        if (!ListenerUtil.mutListener.listen(19959)) {
            mTextCount = findViewById(R.id.text_count);
        }
        if (!ListenerUtil.mutListener.listen(19972)) {
            if (attrs != null) {
                TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ReaderIconCountView, 0, 0);
                try {
                    if (!ListenerUtil.mutListener.listen(19961)) {
                        mIconType = a.getInteger(R.styleable.ReaderIconCountView_readerIcon, ICON_LIKE);
                    }
                    if (!ListenerUtil.mutListener.listen(19971)) {
                        switch(mIconType) {
                            case ICON_LIKE:
                                ColorStateList likeColor = AppCompatResources.getColorStateList(context, R.color.on_surface_medium_secondary_selector);
                                if (!ListenerUtil.mutListener.listen(19962)) {
                                    mImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.reader_button_like));
                                }
                                if (!ListenerUtil.mutListener.listen(19963)) {
                                    ImageViewCompat.setImageTintList(mImageView, likeColor);
                                }
                                if (!ListenerUtil.mutListener.listen(19964)) {
                                    mTextCount.setTextColor(likeColor);
                                }
                                break;
                            case ICON_COMMENT:
                                ColorStateList commentColor = AppCompatResources.getColorStateList(context, R.color.on_surface_primary_selector);
                                if (!ListenerUtil.mutListener.listen(19965)) {
                                    ImageViewCompat.setImageTintList(mImageView, commentColor);
                                }
                                if (!ListenerUtil.mutListener.listen(19966)) {
                                    mImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_comment_white_24dp));
                                }
                                if (!ListenerUtil.mutListener.listen(19967)) {
                                    mTextCount.setTextColor(commentColor);
                                }
                                break;
                            case ICON_REBLOG:
                                ColorStateList reblogColor = AppCompatResources.getColorStateList(context, R.color.on_surface_primary_selector);
                                if (!ListenerUtil.mutListener.listen(19968)) {
                                    ImageViewCompat.setImageTintList(mImageView, reblogColor);
                                }
                                if (!ListenerUtil.mutListener.listen(19969)) {
                                    mImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_reblog_white_24dp));
                                }
                                if (!ListenerUtil.mutListener.listen(19970)) {
                                    mTextCount.setTextColor(reblogColor);
                                }
                                break;
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(19960)) {
                        a.recycle();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19979)) {
            // move the comment icon down a bit so it aligns with the text baseline
            if ((ListenerUtil.mutListener.listen(19977) ? (mIconType >= ICON_COMMENT) : (ListenerUtil.mutListener.listen(19976) ? (mIconType <= ICON_COMMENT) : (ListenerUtil.mutListener.listen(19975) ? (mIconType > ICON_COMMENT) : (ListenerUtil.mutListener.listen(19974) ? (mIconType < ICON_COMMENT) : (ListenerUtil.mutListener.listen(19973) ? (mIconType != ICON_COMMENT) : (mIconType == ICON_COMMENT))))))) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(19978)) {
                    params.topMargin = context.getResources().getDimensionPixelSize(R.dimen.margin_extra_extra_small);
                }
            }
        }
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setSelected(boolean selected) {
        if (!ListenerUtil.mutListener.listen(19980)) {
            mImageView.setSelected(selected);
        }
        if (!ListenerUtil.mutListener.listen(19981)) {
            mTextCount.setSelected(selected);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(19982)) {
            super.setEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(19983)) {
            mImageView.setEnabled(enabled);
        }
        if (!ListenerUtil.mutListener.listen(19984)) {
            mTextCount.setEnabled(enabled);
        }
    }

    public void setCount(int count) {
        if (!ListenerUtil.mutListener.listen(19990)) {
            mTextCount.setText((ListenerUtil.mutListener.listen(19989) ? (count >= 0) : (ListenerUtil.mutListener.listen(19988) ? (count <= 0) : (ListenerUtil.mutListener.listen(19987) ? (count > 0) : (ListenerUtil.mutListener.listen(19986) ? (count < 0) : (ListenerUtil.mutListener.listen(19985) ? (count == 0) : (count != 0)))))) ? String.valueOf(count) : "");
        }
    }

    // Sets the count using a text counter - Like, 1 Like, % Likes
    public void setTextCount(int count) {
        if (!ListenerUtil.mutListener.listen(19991)) {
            mTextCount.setText(ReaderUtils.getShortLikeLabelText(mTextCount.getContext(), count));
        }
    }
}
