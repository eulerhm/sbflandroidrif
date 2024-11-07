package org.wordpress.android.ui.comments;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;
import android.text.util.Linkify;
import android.widget.TextView;
import org.wordpress.android.util.EmoticonsUtils;
import org.wordpress.android.util.HtmlUtils;
import org.wordpress.android.util.image.getters.WPCustomImageGetter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CommentUtils {

    /*
     * displays comment text as html, including retrieving images
     */
    public static void displayHtmlComment(TextView textView, String content, int maxImageSize, int maxEmojiSize, String errorParseMsg) {
        if (!ListenerUtil.mutListener.listen(4760)) {
            if (textView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4762)) {
            if (content == null) {
                if (!ListenerUtil.mutListener.listen(4761)) {
                    textView.setText(null);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4768)) {
            // skip performance hit of html conversion if content doesn't contain html
            if ((ListenerUtil.mutListener.listen(4763) ? (!content.contains("<") || !content.contains("&")) : (!content.contains("<") && !content.contains("&")))) {
                if (!ListenerUtil.mutListener.listen(4764)) {
                    content = content.trim();
                }
                if (!ListenerUtil.mutListener.listen(4765)) {
                    textView.setText(content);
                }
                if (!ListenerUtil.mutListener.listen(4767)) {
                    // make sure unnamed links are clickable
                    if (content.contains("://")) {
                        if (!ListenerUtil.mutListener.listen(4766)) {
                            Linkify.addLinks(textView, Linkify.WEB_URLS);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4769)) {
            // convert emoticons first (otherwise they'll be downloaded)
            content = EmoticonsUtils.replaceEmoticonsWithEmoji(content);
        }
        // now convert to HTML with an image getter that enforces a max image size
        final Spanned html;
        if ((ListenerUtil.mutListener.listen(4775) ? ((ListenerUtil.mutListener.listen(4774) ? (maxImageSize >= 0) : (ListenerUtil.mutListener.listen(4773) ? (maxImageSize <= 0) : (ListenerUtil.mutListener.listen(4772) ? (maxImageSize < 0) : (ListenerUtil.mutListener.listen(4771) ? (maxImageSize != 0) : (ListenerUtil.mutListener.listen(4770) ? (maxImageSize == 0) : (maxImageSize > 0)))))) || content.contains("<img")) : ((ListenerUtil.mutListener.listen(4774) ? (maxImageSize >= 0) : (ListenerUtil.mutListener.listen(4773) ? (maxImageSize <= 0) : (ListenerUtil.mutListener.listen(4772) ? (maxImageSize < 0) : (ListenerUtil.mutListener.listen(4771) ? (maxImageSize != 0) : (ListenerUtil.mutListener.listen(4770) ? (maxImageSize == 0) : (maxImageSize > 0)))))) && content.contains("<img")))) {
            html = HtmlUtils.fromHtml(content, new WPCustomImageGetter(textView, maxImageSize, maxEmojiSize));
        } else {
            html = HtmlUtils.fromHtml(content);
        }
        // remove extra \n\n added by Html.convert()
        int start = 0;
        int end = html.length();
        if (!ListenerUtil.mutListener.listen(4783)) {
            {
                long _loopCounter122 = 0;
                while ((ListenerUtil.mutListener.listen(4782) ? ((ListenerUtil.mutListener.listen(4781) ? (start >= end) : (ListenerUtil.mutListener.listen(4780) ? (start <= end) : (ListenerUtil.mutListener.listen(4779) ? (start > end) : (ListenerUtil.mutListener.listen(4778) ? (start != end) : (ListenerUtil.mutListener.listen(4777) ? (start == end) : (start < end)))))) || Character.isWhitespace(html.charAt(start))) : ((ListenerUtil.mutListener.listen(4781) ? (start >= end) : (ListenerUtil.mutListener.listen(4780) ? (start <= end) : (ListenerUtil.mutListener.listen(4779) ? (start > end) : (ListenerUtil.mutListener.listen(4778) ? (start != end) : (ListenerUtil.mutListener.listen(4777) ? (start == end) : (start < end)))))) && Character.isWhitespace(html.charAt(start))))) {
                    ListenerUtil.loopListener.listen("_loopCounter122", ++_loopCounter122);
                    if (!ListenerUtil.mutListener.listen(4776)) {
                        start++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4795)) {
            {
                long _loopCounter123 = 0;
                while ((ListenerUtil.mutListener.listen(4794) ? ((ListenerUtil.mutListener.listen(4789) ? (end >= start) : (ListenerUtil.mutListener.listen(4788) ? (end <= start) : (ListenerUtil.mutListener.listen(4787) ? (end < start) : (ListenerUtil.mutListener.listen(4786) ? (end != start) : (ListenerUtil.mutListener.listen(4785) ? (end == start) : (end > start)))))) || Character.isWhitespace(html.charAt((ListenerUtil.mutListener.listen(4793) ? (end % 1) : (ListenerUtil.mutListener.listen(4792) ? (end / 1) : (ListenerUtil.mutListener.listen(4791) ? (end * 1) : (ListenerUtil.mutListener.listen(4790) ? (end + 1) : (end - 1)))))))) : ((ListenerUtil.mutListener.listen(4789) ? (end >= start) : (ListenerUtil.mutListener.listen(4788) ? (end <= start) : (ListenerUtil.mutListener.listen(4787) ? (end < start) : (ListenerUtil.mutListener.listen(4786) ? (end != start) : (ListenerUtil.mutListener.listen(4785) ? (end == start) : (end > start)))))) && Character.isWhitespace(html.charAt((ListenerUtil.mutListener.listen(4793) ? (end % 1) : (ListenerUtil.mutListener.listen(4792) ? (end / 1) : (ListenerUtil.mutListener.listen(4791) ? (end * 1) : (ListenerUtil.mutListener.listen(4790) ? (end + 1) : (end - 1)))))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter123", ++_loopCounter123);
                    if (!ListenerUtil.mutListener.listen(4784)) {
                        end--;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4798)) {
            if (html.length() == 0) {
                if (!ListenerUtil.mutListener.listen(4797)) {
                    textView.setText(errorParseMsg);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4796)) {
                    textView.setText(html.subSequence(start, end));
                }
            }
        }
    }

    // Assumes all lines after first line will not be indented
    public static void indentTextViewFirstLine(TextView textView, int textOffsetX) {
        if (!ListenerUtil.mutListener.listen(4805)) {
            if ((ListenerUtil.mutListener.listen(4804) ? (textView == null && (ListenerUtil.mutListener.listen(4803) ? (textOffsetX >= 0) : (ListenerUtil.mutListener.listen(4802) ? (textOffsetX <= 0) : (ListenerUtil.mutListener.listen(4801) ? (textOffsetX > 0) : (ListenerUtil.mutListener.listen(4800) ? (textOffsetX != 0) : (ListenerUtil.mutListener.listen(4799) ? (textOffsetX == 0) : (textOffsetX < 0))))))) : (textView == null || (ListenerUtil.mutListener.listen(4803) ? (textOffsetX >= 0) : (ListenerUtil.mutListener.listen(4802) ? (textOffsetX <= 0) : (ListenerUtil.mutListener.listen(4801) ? (textOffsetX > 0) : (ListenerUtil.mutListener.listen(4800) ? (textOffsetX != 0) : (ListenerUtil.mutListener.listen(4799) ? (textOffsetX == 0) : (textOffsetX < 0))))))))) {
                return;
            }
        }
        SpannableString text = new SpannableString(textView.getText());
        if (!ListenerUtil.mutListener.listen(4806)) {
            text.setSpan(new TextWrappingLeadingMarginSpan(textOffsetX), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!ListenerUtil.mutListener.listen(4807)) {
            textView.setText(text);
        }
    }

    private static class TextWrappingLeadingMarginSpan implements LeadingMarginSpan.LeadingMarginSpan2 {

        private final int mMargin;

        private final int mLines;

        TextWrappingLeadingMarginSpan(int margin) {
            this.mMargin = margin;
            this.mLines = 1;
        }

        @Override
        public int getLeadingMargin(boolean first) {
            return first ? mMargin : 0;
        }

        @Override
        public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        }

        @Override
        public int getLeadingMarginLineCount() {
            return mLines;
        }
    }
}
