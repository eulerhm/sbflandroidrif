package org.wordpress.android.ui.reader.utils;

import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import androidx.annotation.NonNull;
import org.wordpress.android.models.ReaderPost;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderXPostUtils {

    // note that these strings don't need to be localized due to the intended audience
    private static final String UNKNOWN_SITE = "(unknown)";

    private static final String FMT_SITE_XPOST = "%1$s cross-posted from %2$s to %3$s";

    private static final String FMT_COMMENT_XPOST = "%1$s commented on %2$s, cross-posted to %3$s";

    /*
     * returns the title to display for this xpost, which is simply the post's title
     * without the "X-post: " prefix
     */
    public static String getXPostTitle(@NonNull ReaderPost post) {
        if (post.getTitle().startsWith("X-post: ")) {
            return post.getTitle().substring(8);
        } else {
            return post.getTitle();
        }
    }

    /*
     * returns the html subtitle to display for this xpost
     * ex: "Nick cross-posted from +blog1 to +blog2"
     * ex: "Nick commented on +blog1, cross-posted to +blog2"
     */
    public static Spanned getXPostSubtitleHtml(@NonNull ReaderPost post) {
        boolean isCommentXPost = post.getExcerpt().startsWith("X-comment");
        String name = post.hasAuthorFirstName() ? post.getAuthorFirstName() : post.getAuthorName();
        String subtitle = String.format(isCommentXPost ? FMT_COMMENT_XPOST : FMT_SITE_XPOST, "<strong>" + name + "</strong>", "<strong>" + getFromSiteName(post) + "</strong>", "<strong>" + getToSiteName(post) + "</strong>");
        return Html.fromHtml(subtitle);
    }

    // example excerpt: "<p>X-post from +blog2: I have a request..."
    private static String getFromSiteName(@NonNull ReaderPost post) {
        String excerpt = post.getExcerpt();
        int plusPos = excerpt.indexOf("+");
        int colonPos = excerpt.indexOf(":", plusPos);
        if ((ListenerUtil.mutListener.listen(19926) ? ((ListenerUtil.mutListener.listen(19920) ? (plusPos >= 0) : (ListenerUtil.mutListener.listen(19919) ? (plusPos <= 0) : (ListenerUtil.mutListener.listen(19918) ? (plusPos < 0) : (ListenerUtil.mutListener.listen(19917) ? (plusPos != 0) : (ListenerUtil.mutListener.listen(19916) ? (plusPos == 0) : (plusPos > 0)))))) || (ListenerUtil.mutListener.listen(19925) ? (colonPos >= 0) : (ListenerUtil.mutListener.listen(19924) ? (colonPos <= 0) : (ListenerUtil.mutListener.listen(19923) ? (colonPos < 0) : (ListenerUtil.mutListener.listen(19922) ? (colonPos != 0) : (ListenerUtil.mutListener.listen(19921) ? (colonPos == 0) : (colonPos > 0))))))) : ((ListenerUtil.mutListener.listen(19920) ? (plusPos >= 0) : (ListenerUtil.mutListener.listen(19919) ? (plusPos <= 0) : (ListenerUtil.mutListener.listen(19918) ? (plusPos < 0) : (ListenerUtil.mutListener.listen(19917) ? (plusPos != 0) : (ListenerUtil.mutListener.listen(19916) ? (plusPos == 0) : (plusPos > 0)))))) && (ListenerUtil.mutListener.listen(19925) ? (colonPos >= 0) : (ListenerUtil.mutListener.listen(19924) ? (colonPos <= 0) : (ListenerUtil.mutListener.listen(19923) ? (colonPos < 0) : (ListenerUtil.mutListener.listen(19922) ? (colonPos != 0) : (ListenerUtil.mutListener.listen(19921) ? (colonPos == 0) : (colonPos > 0))))))))) {
            return excerpt.substring(plusPos, colonPos);
        } else {
            return UNKNOWN_SITE;
        }
    }

    // destination site name is the subdomain of the blog url
    private static String getToSiteName(@NonNull ReaderPost post) {
        Uri uri = Uri.parse(post.getBlogUrl());
        String domain = uri.getHost();
        if (!ListenerUtil.mutListener.listen(19928)) {
            if ((ListenerUtil.mutListener.listen(19927) ? (domain == null && !domain.contains(".")) : (domain == null || !domain.contains(".")))) {
                return "+" + UNKNOWN_SITE;
            }
        }
        return "+" + domain.substring(0, domain.indexOf("."));
    }
}
