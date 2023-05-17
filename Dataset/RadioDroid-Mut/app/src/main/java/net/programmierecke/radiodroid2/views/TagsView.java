package net.programmierecke.radiodroid2.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import net.programmierecke.radiodroid2.R;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TagsView extends AppCompatTextView {

    public interface TagSelectionCallback {

        void onTagSelected(String tag);
    }

    // Credits to Nachos source
    private class RoundedBackgroundSpan extends ReplacementSpan {

        private int mHeight;

        private int mCornerRadius;

        private int mTextHorizontalPadding;

        private int mTextVerticalMargin;

        private int mBackgroundColor;

        private int mTextColor;

        RoundedBackgroundSpan(int mHeight, int mCornerRadius, int mTextHorizontalPadding, int mTextVerticalMargin, int mBackgroundColor, int mTextColor) {
            super();
            if (!ListenerUtil.mutListener.listen(3546)) {
                this.mHeight = mHeight;
            }
            if (!ListenerUtil.mutListener.listen(3547)) {
                this.mCornerRadius = mCornerRadius;
            }
            if (!ListenerUtil.mutListener.listen(3548)) {
                this.mTextHorizontalPadding = mTextHorizontalPadding;
            }
            if (!ListenerUtil.mutListener.listen(3549)) {
                this.mTextVerticalMargin = mTextVerticalMargin;
            }
            if (!ListenerUtil.mutListener.listen(3550)) {
                this.mBackgroundColor = mBackgroundColor;
            }
            if (!ListenerUtil.mutListener.listen(3551)) {
                this.mTextColor = mTextColor;
            }
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
            if (!ListenerUtil.mutListener.listen(3568)) {
                top += (ListenerUtil.mutListener.listen(3567) ? (((ListenerUtil.mutListener.listen(3559) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) % 2) : (ListenerUtil.mutListener.listen(3558) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) * 2) : (ListenerUtil.mutListener.listen(3557) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) - 2) : (ListenerUtil.mutListener.listen(3556) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) + 2) : (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) / 2)))))) % ((ListenerUtil.mutListener.listen(3563) ? (mHeight % 2) : (ListenerUtil.mutListener.listen(3562) ? (mHeight * 2) : (ListenerUtil.mutListener.listen(3561) ? (mHeight - 2) : (ListenerUtil.mutListener.listen(3560) ? (mHeight + 2) : (mHeight / 2))))))) : (ListenerUtil.mutListener.listen(3566) ? (((ListenerUtil.mutListener.listen(3559) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) % 2) : (ListenerUtil.mutListener.listen(3558) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) * 2) : (ListenerUtil.mutListener.listen(3557) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) - 2) : (ListenerUtil.mutListener.listen(3556) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) + 2) : (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) / 2)))))) / ((ListenerUtil.mutListener.listen(3563) ? (mHeight % 2) : (ListenerUtil.mutListener.listen(3562) ? (mHeight * 2) : (ListenerUtil.mutListener.listen(3561) ? (mHeight - 2) : (ListenerUtil.mutListener.listen(3560) ? (mHeight + 2) : (mHeight / 2))))))) : (ListenerUtil.mutListener.listen(3565) ? (((ListenerUtil.mutListener.listen(3559) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) % 2) : (ListenerUtil.mutListener.listen(3558) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) * 2) : (ListenerUtil.mutListener.listen(3557) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) - 2) : (ListenerUtil.mutListener.listen(3556) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) + 2) : (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) / 2)))))) * ((ListenerUtil.mutListener.listen(3563) ? (mHeight % 2) : (ListenerUtil.mutListener.listen(3562) ? (mHeight * 2) : (ListenerUtil.mutListener.listen(3561) ? (mHeight - 2) : (ListenerUtil.mutListener.listen(3560) ? (mHeight + 2) : (mHeight / 2))))))) : (ListenerUtil.mutListener.listen(3564) ? (((ListenerUtil.mutListener.listen(3559) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) % 2) : (ListenerUtil.mutListener.listen(3558) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) * 2) : (ListenerUtil.mutListener.listen(3557) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) - 2) : (ListenerUtil.mutListener.listen(3556) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) + 2) : (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) / 2)))))) + ((ListenerUtil.mutListener.listen(3563) ? (mHeight % 2) : (ListenerUtil.mutListener.listen(3562) ? (mHeight * 2) : (ListenerUtil.mutListener.listen(3561) ? (mHeight - 2) : (ListenerUtil.mutListener.listen(3560) ? (mHeight + 2) : (mHeight / 2))))))) : (((ListenerUtil.mutListener.listen(3559) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) % 2) : (ListenerUtil.mutListener.listen(3558) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) * 2) : (ListenerUtil.mutListener.listen(3557) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) - 2) : (ListenerUtil.mutListener.listen(3556) ? (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) + 2) : (((ListenerUtil.mutListener.listen(3555) ? (bottom % top) : (ListenerUtil.mutListener.listen(3554) ? (bottom / top) : (ListenerUtil.mutListener.listen(3553) ? (bottom * top) : (ListenerUtil.mutListener.listen(3552) ? (bottom + top) : (bottom - top)))))) / 2)))))) - ((ListenerUtil.mutListener.listen(3563) ? (mHeight % 2) : (ListenerUtil.mutListener.listen(3562) ? (mHeight * 2) : (ListenerUtil.mutListener.listen(3561) ? (mHeight - 2) : (ListenerUtil.mutListener.listen(3560) ? (mHeight + 2) : (mHeight / 2)))))))))));
            }
            if (!ListenerUtil.mutListener.listen(3573)) {
                bottom = (ListenerUtil.mutListener.listen(3572) ? (top % mHeight) : (ListenerUtil.mutListener.listen(3571) ? (top / mHeight) : (ListenerUtil.mutListener.listen(3570) ? (top * mHeight) : (ListenerUtil.mutListener.listen(3569) ? (top - mHeight) : (top + mHeight)))));
            }
            Paint.FontMetrics fm = paint.getFontMetrics();
            float adjustedY = top + (((ListenerUtil.mutListener.listen(3577) ? (mHeight % 2) : (ListenerUtil.mutListener.listen(3576) ? (mHeight * 2) : (ListenerUtil.mutListener.listen(3575) ? (mHeight - 2) : (ListenerUtil.mutListener.listen(3574) ? (mHeight + 2) : (mHeight / 2)))))) + ((ListenerUtil.mutListener.listen(3585) ? (((ListenerUtil.mutListener.listen(3581) ? (-fm.top % fm.bottom) : (ListenerUtil.mutListener.listen(3580) ? (-fm.top / fm.bottom) : (ListenerUtil.mutListener.listen(3579) ? (-fm.top * fm.bottom) : (ListenerUtil.mutListener.listen(3578) ? (-fm.top + fm.bottom) : (-fm.top - fm.bottom)))))) % 2) : (ListenerUtil.mutListener.listen(3584) ? (((ListenerUtil.mutListener.listen(3581) ? (-fm.top % fm.bottom) : (ListenerUtil.mutListener.listen(3580) ? (-fm.top / fm.bottom) : (ListenerUtil.mutListener.listen(3579) ? (-fm.top * fm.bottom) : (ListenerUtil.mutListener.listen(3578) ? (-fm.top + fm.bottom) : (-fm.top - fm.bottom)))))) * 2) : (ListenerUtil.mutListener.listen(3583) ? (((ListenerUtil.mutListener.listen(3581) ? (-fm.top % fm.bottom) : (ListenerUtil.mutListener.listen(3580) ? (-fm.top / fm.bottom) : (ListenerUtil.mutListener.listen(3579) ? (-fm.top * fm.bottom) : (ListenerUtil.mutListener.listen(3578) ? (-fm.top + fm.bottom) : (-fm.top - fm.bottom)))))) - 2) : (ListenerUtil.mutListener.listen(3582) ? (((ListenerUtil.mutListener.listen(3581) ? (-fm.top % fm.bottom) : (ListenerUtil.mutListener.listen(3580) ? (-fm.top / fm.bottom) : (ListenerUtil.mutListener.listen(3579) ? (-fm.top * fm.bottom) : (ListenerUtil.mutListener.listen(3578) ? (-fm.top + fm.bottom) : (-fm.top - fm.bottom)))))) + 2) : (((ListenerUtil.mutListener.listen(3581) ? (-fm.top % fm.bottom) : (ListenerUtil.mutListener.listen(3580) ? (-fm.top / fm.bottom) : (ListenerUtil.mutListener.listen(3579) ? (-fm.top * fm.bottom) : (ListenerUtil.mutListener.listen(3578) ? (-fm.top + fm.bottom) : (-fm.top - fm.bottom)))))) / 2)))))));
            RectF rect = new RectF(x, top, x + measureText(paint, text, start, end) + (ListenerUtil.mutListener.listen(3589) ? (2 % mTextHorizontalPadding) : (ListenerUtil.mutListener.listen(3588) ? (2 / mTextHorizontalPadding) : (ListenerUtil.mutListener.listen(3587) ? (2 - mTextHorizontalPadding) : (ListenerUtil.mutListener.listen(3586) ? (2 + mTextHorizontalPadding) : (2 * mTextHorizontalPadding))))), bottom);
            if (!ListenerUtil.mutListener.listen(3590)) {
                paint.setColor(mBackgroundColor);
            }
            if (!ListenerUtil.mutListener.listen(3591)) {
                canvas.drawRoundRect(rect, mCornerRadius, mCornerRadius, paint);
            }
            if (!ListenerUtil.mutListener.listen(3592)) {
                paint.setColor(mTextColor);
            }
            if (!ListenerUtil.mutListener.listen(3597)) {
                canvas.drawText(text, start, end, (ListenerUtil.mutListener.listen(3596) ? (x % mTextHorizontalPadding) : (ListenerUtil.mutListener.listen(3595) ? (x / mTextHorizontalPadding) : (ListenerUtil.mutListener.listen(3594) ? (x * mTextHorizontalPadding) : (ListenerUtil.mutListener.listen(3593) ? (x - mTextHorizontalPadding) : (x + mTextHorizontalPadding))))), adjustedY, paint);
            }
        }

        @Override
        public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            if (!ListenerUtil.mutListener.listen(3627)) {
                if (fm != null) {
                    if (!ListenerUtil.mutListener.listen(3598)) {
                        paint.getFontMetricsInt(fm);
                    }
                    int textHeight = (ListenerUtil.mutListener.listen(3602) ? (fm.descent % fm.ascent) : (ListenerUtil.mutListener.listen(3601) ? (fm.descent / fm.ascent) : (ListenerUtil.mutListener.listen(3600) ? (fm.descent * fm.ascent) : (ListenerUtil.mutListener.listen(3599) ? (fm.descent + fm.ascent) : (fm.descent - fm.ascent)))));
                    int spaceBetweenTopAndText = (ListenerUtil.mutListener.listen(3610) ? (((ListenerUtil.mutListener.listen(3606) ? (mHeight % textHeight) : (ListenerUtil.mutListener.listen(3605) ? (mHeight / textHeight) : (ListenerUtil.mutListener.listen(3604) ? (mHeight * textHeight) : (ListenerUtil.mutListener.listen(3603) ? (mHeight + textHeight) : (mHeight - textHeight)))))) % 2) : (ListenerUtil.mutListener.listen(3609) ? (((ListenerUtil.mutListener.listen(3606) ? (mHeight % textHeight) : (ListenerUtil.mutListener.listen(3605) ? (mHeight / textHeight) : (ListenerUtil.mutListener.listen(3604) ? (mHeight * textHeight) : (ListenerUtil.mutListener.listen(3603) ? (mHeight + textHeight) : (mHeight - textHeight)))))) * 2) : (ListenerUtil.mutListener.listen(3608) ? (((ListenerUtil.mutListener.listen(3606) ? (mHeight % textHeight) : (ListenerUtil.mutListener.listen(3605) ? (mHeight / textHeight) : (ListenerUtil.mutListener.listen(3604) ? (mHeight * textHeight) : (ListenerUtil.mutListener.listen(3603) ? (mHeight + textHeight) : (mHeight - textHeight)))))) - 2) : (ListenerUtil.mutListener.listen(3607) ? (((ListenerUtil.mutListener.listen(3606) ? (mHeight % textHeight) : (ListenerUtil.mutListener.listen(3605) ? (mHeight / textHeight) : (ListenerUtil.mutListener.listen(3604) ? (mHeight * textHeight) : (ListenerUtil.mutListener.listen(3603) ? (mHeight + textHeight) : (mHeight - textHeight)))))) + 2) : (((ListenerUtil.mutListener.listen(3606) ? (mHeight % textHeight) : (ListenerUtil.mutListener.listen(3605) ? (mHeight / textHeight) : (ListenerUtil.mutListener.listen(3604) ? (mHeight * textHeight) : (ListenerUtil.mutListener.listen(3603) ? (mHeight + textHeight) : (mHeight - textHeight)))))) / 2)))));
                    int textTop = fm.top;
                    int bkgTop = (ListenerUtil.mutListener.listen(3614) ? (fm.top % spaceBetweenTopAndText) : (ListenerUtil.mutListener.listen(3613) ? (fm.top / spaceBetweenTopAndText) : (ListenerUtil.mutListener.listen(3612) ? (fm.top * spaceBetweenTopAndText) : (ListenerUtil.mutListener.listen(3611) ? (fm.top + spaceBetweenTopAndText) : (fm.top - spaceBetweenTopAndText)))));
                    int textBottom = fm.bottom;
                    int bkgBottom = fm.bottom + spaceBetweenTopAndText;
                    // Text may be bigger than given height
                    int topOfContent = Math.min(textTop, bkgTop);
                    int bottomOfContent = Math.max(textBottom, bkgBottom);
                    int topOfContentWithPadding = (ListenerUtil.mutListener.listen(3618) ? (topOfContent % mTextVerticalMargin) : (ListenerUtil.mutListener.listen(3617) ? (topOfContent / mTextVerticalMargin) : (ListenerUtil.mutListener.listen(3616) ? (topOfContent * mTextVerticalMargin) : (ListenerUtil.mutListener.listen(3615) ? (topOfContent + mTextVerticalMargin) : (topOfContent - mTextVerticalMargin)))));
                    int bottomOfContentWithPadding = (ListenerUtil.mutListener.listen(3622) ? (bottomOfContent % mTextVerticalMargin) : (ListenerUtil.mutListener.listen(3621) ? (bottomOfContent / mTextVerticalMargin) : (ListenerUtil.mutListener.listen(3620) ? (bottomOfContent * mTextVerticalMargin) : (ListenerUtil.mutListener.listen(3619) ? (bottomOfContent - mTextVerticalMargin) : (bottomOfContent + mTextVerticalMargin)))));
                    if (!ListenerUtil.mutListener.listen(3623)) {
                        fm.ascent = topOfContentWithPadding;
                    }
                    if (!ListenerUtil.mutListener.listen(3624)) {
                        fm.descent = bottomOfContentWithPadding;
                    }
                    if (!ListenerUtil.mutListener.listen(3625)) {
                        fm.top = topOfContentWithPadding;
                    }
                    if (!ListenerUtil.mutListener.listen(3626)) {
                        fm.bottom = bottomOfContentWithPadding;
                    }
                }
            }
            return Math.round(paint.measureText(text, start, end)) + (ListenerUtil.mutListener.listen(3631) ? (mTextHorizontalPadding % 2) : (ListenerUtil.mutListener.listen(3630) ? (mTextHorizontalPadding / 2) : (ListenerUtil.mutListener.listen(3629) ? (mTextHorizontalPadding - 2) : (ListenerUtil.mutListener.listen(3628) ? (mTextHorizontalPadding + 2) : (mTextHorizontalPadding * 2)))));
        }

        private float measureText(Paint paint, CharSequence text, int start, int end) {
            return paint.measureText(text, start, end);
        }
    }

    private int mTagBackgroundColor = Color.RED;

    private int mCornerRadius = 16;

    private int mTagHeight = 20;

    private int mTextHorizontalPadding = 8;

    private int mTextVerticalMargin = 4;

    private TagSelectionCallback mTagSelectionCallback;

    public TagsView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(3632)) {
            init(null, 0);
        }
    }

    public TagsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(3633)) {
            init(attrs, 0);
        }
    }

    public TagsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(3634)) {
            init(attrs, defStyle);
        }
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TagsView, defStyle, 0);
        if (!ListenerUtil.mutListener.listen(3635)) {
            mTagBackgroundColor = a.getColor(R.styleable.TagsView_tagBackgroundColor, mTagBackgroundColor);
        }
        if (!ListenerUtil.mutListener.listen(3636)) {
            mCornerRadius = a.getDimensionPixelSize(R.styleable.TagsView_cornerRadius, mCornerRadius);
        }
        if (!ListenerUtil.mutListener.listen(3637)) {
            mTagHeight = a.getDimensionPixelSize(R.styleable.TagsView_tagHeight, mTagHeight);
        }
        if (!ListenerUtil.mutListener.listen(3638)) {
            mTextHorizontalPadding = a.getDimensionPixelSize(R.styleable.TagsView_textHorizontalPadding, mTextHorizontalPadding);
        }
        if (!ListenerUtil.mutListener.listen(3639)) {
            mTextVerticalMargin = a.getDimensionPixelSize(R.styleable.TagsView_textVerticalMargin, mTextVerticalMargin);
        }
        if (!ListenerUtil.mutListener.listen(3640)) {
            a.recycle();
        }
    }

    public void setTags(List<String> tags) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        final String spacing = "  ";
        if (!ListenerUtil.mutListener.listen(3654)) {
            {
                long _loopCounter46 = 0;
                for (final String tag : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter46", ++_loopCounter46);
                    String tagWithBufferSpace = tag + spacing;
                    if (!ListenerUtil.mutListener.listen(3641)) {
                        stringBuilder.append(tagWithBufferSpace);
                    }
                    RoundedBackgroundSpan span = new RoundedBackgroundSpan(mTagHeight, mCornerRadius, mTextHorizontalPadding, mTextVerticalMargin, mTagBackgroundColor, getCurrentTextColor());
                    final int start = (ListenerUtil.mutListener.listen(3645) ? (stringBuilder.length() % tagWithBufferSpace.length()) : (ListenerUtil.mutListener.listen(3644) ? (stringBuilder.length() / tagWithBufferSpace.length()) : (ListenerUtil.mutListener.listen(3643) ? (stringBuilder.length() * tagWithBufferSpace.length()) : (ListenerUtil.mutListener.listen(3642) ? (stringBuilder.length() + tagWithBufferSpace.length()) : (stringBuilder.length() - tagWithBufferSpace.length())))));
                    final int end = (ListenerUtil.mutListener.listen(3649) ? (stringBuilder.length() % spacing.length()) : (ListenerUtil.mutListener.listen(3648) ? (stringBuilder.length() / spacing.length()) : (ListenerUtil.mutListener.listen(3647) ? (stringBuilder.length() * spacing.length()) : (ListenerUtil.mutListener.listen(3646) ? (stringBuilder.length() + spacing.length()) : (stringBuilder.length() - spacing.length())))));
                    if (!ListenerUtil.mutListener.listen(3650)) {
                        stringBuilder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    ClickableSpan clickableSpan = new ClickableSpan() {

                        @Override
                        public void onClick(View view) {
                            if (!ListenerUtil.mutListener.listen(3652)) {
                                if (mTagSelectionCallback != null) {
                                    if (!ListenerUtil.mutListener.listen(3651)) {
                                        mTagSelectionCallback.onTagSelected(tag);
                                    }
                                }
                            }
                        }
                    };
                    if (!ListenerUtil.mutListener.listen(3653)) {
                        stringBuilder.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3655)) {
            setText(stringBuilder);
        }
        if (!ListenerUtil.mutListener.listen(3656)) {
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public void setTagSelectionCallback(TagSelectionCallback tagSelectionCallback) {
        if (!ListenerUtil.mutListener.listen(3657)) {
            mTagSelectionCallback = tagSelectionCallback;
        }
    }
}
