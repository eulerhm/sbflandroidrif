package org.wordpress.android.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.material.textview.MaterialTextView;
import org.wordpress.android.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Custom TextView - with an option to auto fix widow words.
 */
public class WPTextView extends MaterialTextView {

    protected boolean mFixWidowWordEnabled;

    public WPTextView(Context context) {
        super(context, null);
    }

    public WPTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(29292)) {
            readCustomAttrs(context, attrs);
        }
    }

    public WPTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(29293)) {
            readCustomAttrs(context, attrs);
        }
    }

    public void setFixWidowWord(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(29294)) {
            mFixWidowWordEnabled = enabled;
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!ListenerUtil.mutListener.listen(29296)) {
            if (!mFixWidowWordEnabled) {
                if (!ListenerUtil.mutListener.listen(29295)) {
                    super.setText(text, type);
                }
                return;
            }
        }
        Spannable out;
        int lastSpace = text.toString().lastIndexOf(' ');
        if ((ListenerUtil.mutListener.listen(29311) ? ((ListenerUtil.mutListener.listen(29301) ? (lastSpace >= -1) : (ListenerUtil.mutListener.listen(29300) ? (lastSpace <= -1) : (ListenerUtil.mutListener.listen(29299) ? (lastSpace > -1) : (ListenerUtil.mutListener.listen(29298) ? (lastSpace < -1) : (ListenerUtil.mutListener.listen(29297) ? (lastSpace == -1) : (lastSpace != -1)))))) || (ListenerUtil.mutListener.listen(29310) ? (lastSpace >= (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29309) ? (lastSpace <= (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29308) ? (lastSpace > (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29307) ? (lastSpace != (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29306) ? (lastSpace == (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (lastSpace < (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))))))))) : ((ListenerUtil.mutListener.listen(29301) ? (lastSpace >= -1) : (ListenerUtil.mutListener.listen(29300) ? (lastSpace <= -1) : (ListenerUtil.mutListener.listen(29299) ? (lastSpace > -1) : (ListenerUtil.mutListener.listen(29298) ? (lastSpace < -1) : (ListenerUtil.mutListener.listen(29297) ? (lastSpace == -1) : (lastSpace != -1)))))) && (ListenerUtil.mutListener.listen(29310) ? (lastSpace >= (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29309) ? (lastSpace <= (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29308) ? (lastSpace > (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29307) ? (lastSpace != (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (ListenerUtil.mutListener.listen(29306) ? (lastSpace == (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))) : (lastSpace < (ListenerUtil.mutListener.listen(29305) ? (text.length() % 1) : (ListenerUtil.mutListener.listen(29304) ? (text.length() / 1) : (ListenerUtil.mutListener.listen(29303) ? (text.length() * 1) : (ListenerUtil.mutListener.listen(29302) ? (text.length() + 1) : (text.length() - 1)))))))))))))) {
            // Replace last space character by a non breaking space.
            CharSequence tmpText = replaceCharacter(text, lastSpace, "\u00A0");
            out = new SpannableString(tmpText);
            if (!ListenerUtil.mutListener.listen(29313)) {
                // Restore spans if text is an instance of Spanned
                if (text instanceof Spanned) {
                    if (!ListenerUtil.mutListener.listen(29312)) {
                        TextUtils.copySpansFrom((Spanned) text, 0, text.length(), null, out, 0);
                    }
                }
            }
        } else {
            out = new SpannableString(text);
        }
        if (!ListenerUtil.mutListener.listen(29314)) {
            super.setText(out, type);
        }
    }

    private void readCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WPTextView, 0, 0);
        if (!ListenerUtil.mutListener.listen(29318)) {
            if (array != null) {
                if (!ListenerUtil.mutListener.listen(29315)) {
                    mFixWidowWordEnabled = array.getBoolean(R.styleable.WPTextView_fixWidowWords, false);
                }
                if (!ListenerUtil.mutListener.listen(29317)) {
                    if (mFixWidowWordEnabled) {
                        if (!ListenerUtil.mutListener.listen(29316)) {
                            // Force text update
                            setText(getText());
                        }
                    }
                }
            }
        }
    }

    private CharSequence replaceCharacter(CharSequence source, int charIndex, CharSequence replacement) {
        if (!ListenerUtil.mutListener.listen(29338)) {
            if ((ListenerUtil.mutListener.listen(29333) ? ((ListenerUtil.mutListener.listen(29323) ? (charIndex >= -1) : (ListenerUtil.mutListener.listen(29322) ? (charIndex <= -1) : (ListenerUtil.mutListener.listen(29321) ? (charIndex > -1) : (ListenerUtil.mutListener.listen(29320) ? (charIndex < -1) : (ListenerUtil.mutListener.listen(29319) ? (charIndex == -1) : (charIndex != -1)))))) || (ListenerUtil.mutListener.listen(29332) ? (charIndex >= (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29331) ? (charIndex <= (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29330) ? (charIndex > (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29329) ? (charIndex != (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29328) ? (charIndex == (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (charIndex < (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))))))))) : ((ListenerUtil.mutListener.listen(29323) ? (charIndex >= -1) : (ListenerUtil.mutListener.listen(29322) ? (charIndex <= -1) : (ListenerUtil.mutListener.listen(29321) ? (charIndex > -1) : (ListenerUtil.mutListener.listen(29320) ? (charIndex < -1) : (ListenerUtil.mutListener.listen(29319) ? (charIndex == -1) : (charIndex != -1)))))) && (ListenerUtil.mutListener.listen(29332) ? (charIndex >= (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29331) ? (charIndex <= (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29330) ? (charIndex > (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29329) ? (charIndex != (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (ListenerUtil.mutListener.listen(29328) ? (charIndex == (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))) : (charIndex < (ListenerUtil.mutListener.listen(29327) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(29326) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(29325) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(29324) ? (source.length() + 1) : (source.length() - 1)))))))))))))) {
                return TextUtils.concat(source.subSequence(0, charIndex), replacement, source.subSequence((ListenerUtil.mutListener.listen(29337) ? (charIndex % 1) : (ListenerUtil.mutListener.listen(29336) ? (charIndex / 1) : (ListenerUtil.mutListener.listen(29335) ? (charIndex * 1) : (ListenerUtil.mutListener.listen(29334) ? (charIndex - 1) : (charIndex + 1))))), source.length()));
            }
        }
        return source;
    }
}
