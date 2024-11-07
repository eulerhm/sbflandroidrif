package org.wordpress.android.ui.notifications.blocks;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.tools.FormattableRange;
import org.wordpress.android.fluxc.tools.FormattableRangeType;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A clickable span that includes extra ids/urls
 * Maps to a 'range' in a WordPress.com note object
 */
public class NoteBlockClickableSpan extends ClickableSpan {

    private long mId;

    private long mSiteId;

    private long mPostId;

    private FormattableRangeType mRangeType;

    private FormattableRange mFormattableRange;

    private String mUrl;

    private List<Integer> mIndices;

    private boolean mPressed;

    private boolean mShouldLink;

    private boolean mIsFooter;

    private int mTextColor;

    private int mBackgroundColor;

    private int mLinkColor;

    private int mLightTextColor;

    public NoteBlockClickableSpan(FormattableRange range, boolean shouldLink, boolean isFooter) {
        if (!ListenerUtil.mutListener.listen(8500)) {
            mShouldLink = shouldLink;
        }
        if (!ListenerUtil.mutListener.listen(8501)) {
            mIsFooter = isFooter;
        }
        if (!ListenerUtil.mutListener.listen(8502)) {
            processRangeData(range);
        }
    }

    // difficult to get right type of context to this span to style the colors. We are doing it in this method instead.
    public void enableColors(Context context) {
        if (!ListenerUtil.mutListener.listen(8503)) {
            mTextColor = ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorOnSurface);
        }
        if (!ListenerUtil.mutListener.listen(8504)) {
            mBackgroundColor = ContextCompat.getColor(context, R.color.primary_5);
        }
        if (!ListenerUtil.mutListener.listen(8505)) {
            mLinkColor = ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorPrimary);
        }
        if (!ListenerUtil.mutListener.listen(8506)) {
            mLightTextColor = ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorOnSurface);
        }
    }

    public void setColors(@ColorInt int textColor, @ColorInt int backgroundColor, @ColorInt int linkColor, @ColorInt int lightTextColor) {
        if (!ListenerUtil.mutListener.listen(8507)) {
            mTextColor = textColor;
        }
        if (!ListenerUtil.mutListener.listen(8508)) {
            mBackgroundColor = backgroundColor;
        }
        if (!ListenerUtil.mutListener.listen(8509)) {
            mLinkColor = linkColor;
        }
        if (!ListenerUtil.mutListener.listen(8510)) {
            mLightTextColor = lightTextColor;
        }
    }

    private void processRangeData(FormattableRange range) {
        if (!ListenerUtil.mutListener.listen(8523)) {
            if (range != null) {
                if (!ListenerUtil.mutListener.listen(8511)) {
                    mFormattableRange = range;
                }
                if (!ListenerUtil.mutListener.listen(8512)) {
                    mId = range.getId() == null ? 0 : range.getId();
                }
                if (!ListenerUtil.mutListener.listen(8513)) {
                    mSiteId = range.getSiteId() == null ? 0 : range.getSiteId();
                }
                if (!ListenerUtil.mutListener.listen(8514)) {
                    mPostId = range.getPostId() == null ? 0 : range.getPostId();
                }
                if (!ListenerUtil.mutListener.listen(8515)) {
                    mRangeType = range.rangeType();
                }
                if (!ListenerUtil.mutListener.listen(8516)) {
                    mUrl = range.getUrl();
                }
                if (!ListenerUtil.mutListener.listen(8517)) {
                    mIndices = range.getIndices();
                }
                if (!ListenerUtil.mutListener.listen(8518)) {
                    mShouldLink = shouldLinkRangeType();
                }
                if (!ListenerUtil.mutListener.listen(8522)) {
                    // Apply grey color to some types
                    if ((ListenerUtil.mutListener.listen(8520) ? ((ListenerUtil.mutListener.listen(8519) ? (mIsFooter && getRangeType() == FormattableRangeType.BLOCKQUOTE) : (mIsFooter || getRangeType() == FormattableRangeType.BLOCKQUOTE)) && getRangeType() == FormattableRangeType.POST) : ((ListenerUtil.mutListener.listen(8519) ? (mIsFooter && getRangeType() == FormattableRangeType.BLOCKQUOTE) : (mIsFooter || getRangeType() == FormattableRangeType.BLOCKQUOTE)) || getRangeType() == FormattableRangeType.POST))) {
                        if (!ListenerUtil.mutListener.listen(8521)) {
                            mTextColor = mLightTextColor;
                        }
                    }
                }
            }
        }
    }

    // Don't link certain range types, or unknown ones, unless we have a URL
    private boolean shouldLinkRangeType() {
        return (ListenerUtil.mutListener.listen(8528) ? ((ListenerUtil.mutListener.listen(8526) ? ((ListenerUtil.mutListener.listen(8525) ? ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) || mRangeType != FormattableRangeType.MATCH) : ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) && mRangeType != FormattableRangeType.MATCH)) || mRangeType != FormattableRangeType.B) : ((ListenerUtil.mutListener.listen(8525) ? ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) || mRangeType != FormattableRangeType.MATCH) : ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) && mRangeType != FormattableRangeType.MATCH)) && mRangeType != FormattableRangeType.B)) || ((ListenerUtil.mutListener.listen(8527) ? (mRangeType != FormattableRangeType.UNKNOWN && !TextUtils.isEmpty(mUrl)) : (mRangeType != FormattableRangeType.UNKNOWN || !TextUtils.isEmpty(mUrl))))) : ((ListenerUtil.mutListener.listen(8526) ? ((ListenerUtil.mutListener.listen(8525) ? ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) || mRangeType != FormattableRangeType.MATCH) : ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) && mRangeType != FormattableRangeType.MATCH)) || mRangeType != FormattableRangeType.B) : ((ListenerUtil.mutListener.listen(8525) ? ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) || mRangeType != FormattableRangeType.MATCH) : ((ListenerUtil.mutListener.listen(8524) ? (mShouldLink || mRangeType != FormattableRangeType.BLOCKQUOTE) : (mShouldLink && mRangeType != FormattableRangeType.BLOCKQUOTE)) && mRangeType != FormattableRangeType.MATCH)) && mRangeType != FormattableRangeType.B)) && ((ListenerUtil.mutListener.listen(8527) ? (mRangeType != FormattableRangeType.UNKNOWN && !TextUtils.isEmpty(mUrl)) : (mRangeType != FormattableRangeType.UNKNOWN || !TextUtils.isEmpty(mUrl))))));
    }

    @Override
    public void updateDrawState(@NonNull TextPaint textPaint) {
        if (!ListenerUtil.mutListener.listen(8531)) {
            // Set background color
            textPaint.bgColor = (ListenerUtil.mutListener.listen(8530) ? ((ListenerUtil.mutListener.listen(8529) ? (mShouldLink || mPressed) : (mShouldLink && mPressed)) || !isBlockquoteType()) : ((ListenerUtil.mutListener.listen(8529) ? (mShouldLink || mPressed) : (mShouldLink && mPressed)) && !isBlockquoteType())) ? mBackgroundColor : Color.TRANSPARENT;
        }
        if (!ListenerUtil.mutListener.listen(8533)) {
            textPaint.setColor((ListenerUtil.mutListener.listen(8532) ? (mShouldLink || !mIsFooter) : (mShouldLink && !mIsFooter)) ? mLinkColor : mTextColor);
        }
        if (!ListenerUtil.mutListener.listen(8534)) {
            // No underlines
            textPaint.setUnderlineText(mIsFooter);
        }
    }

    private boolean isBlockquoteType() {
        return getRangeType() == FormattableRangeType.BLOCKQUOTE;
    }

    // return the desired style for this id type
    public int getSpanStyle() {
        if (mIsFooter) {
            return Typeface.BOLD;
        }
        switch(getRangeType()) {
            case USER:
            case MATCH:
            case SITE:
            case POST:
            case COMMENT:
            case REWIND_DOWNLOAD_READY:
            case B:
                return Typeface.BOLD;
            case BLOCKQUOTE:
                return Typeface.ITALIC;
            case STAT:
            case FOLLOW:
            case NOTICON:
            case LIKE:
            case UNKNOWN:
            default:
                return Typeface.NORMAL;
        }
    }

    @Override
    public void onClick(View widget) {
    }

    public FormattableRangeType getRangeType() {
        return mRangeType;
    }

    public FormattableRange getFormattableRange() {
        return mFormattableRange;
    }

    public List<Integer> getIndices() {
        return mIndices;
    }

    public long getId() {
        return mId;
    }

    public long getSiteId() {
        return mSiteId;
    }

    public long getPostId() {
        return mPostId;
    }

    public void setPressed(boolean isPressed) {
        if (!ListenerUtil.mutListener.listen(8535)) {
            this.mPressed = isPressed;
        }
    }

    public String getUrl() {
        return mUrl;
    }

    public void setCustomType(String type) {
        if (!ListenerUtil.mutListener.listen(8536)) {
            mRangeType = FormattableRangeType.Companion.fromString(type);
        }
    }
}
