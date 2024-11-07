package org.wordpress.android.util;

import android.text.TextUtils;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.post.PostStatus;
import org.wordpress.android.util.AppLog.T;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPMeShortlinks {

    /**
     * Converts a base-10 number to base-62
     *
     * @param num base-10 number
     * @return String base-62 number
     */
    public static String wpme_dec2sixtwo(double num) {
        if (!ListenerUtil.mutListener.listen(28039)) {
            if ((ListenerUtil.mutListener.listen(28038) ? (num >= 0) : (ListenerUtil.mutListener.listen(28037) ? (num <= 0) : (ListenerUtil.mutListener.listen(28036) ? (num > 0) : (ListenerUtil.mutListener.listen(28035) ? (num < 0) : (ListenerUtil.mutListener.listen(28034) ? (num != 0) : (num == 0))))))) {
                return "0";
            }
        }
        StringBuilder out;
        try {
            String index = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            out = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(28048)) {
                if ((ListenerUtil.mutListener.listen(28045) ? (num >= 0) : (ListenerUtil.mutListener.listen(28044) ? (num <= 0) : (ListenerUtil.mutListener.listen(28043) ? (num > 0) : (ListenerUtil.mutListener.listen(28042) ? (num != 0) : (ListenerUtil.mutListener.listen(28041) ? (num == 0) : (num < 0))))))) {
                    if (!ListenerUtil.mutListener.listen(28046)) {
                        out.append('-');
                    }
                    if (!ListenerUtil.mutListener.listen(28047)) {
                        num = Math.abs(num);
                    }
                }
            }
            double t = Math.floor((ListenerUtil.mutListener.listen(28052) ? (Math.log10(num) % Math.log10(62)) : (ListenerUtil.mutListener.listen(28051) ? (Math.log10(num) * Math.log10(62)) : (ListenerUtil.mutListener.listen(28050) ? (Math.log10(num) - Math.log10(62)) : (ListenerUtil.mutListener.listen(28049) ? (Math.log10(num) + Math.log10(62)) : (Math.log10(num) / Math.log10(62)))))));
            if (!ListenerUtil.mutListener.listen(28076)) {
                {
                    long _loopCounter421 = 0;
                    for (; (ListenerUtil.mutListener.listen(28075) ? (t <= 0) : (ListenerUtil.mutListener.listen(28074) ? (t > 0) : (ListenerUtil.mutListener.listen(28073) ? (t < 0) : (ListenerUtil.mutListener.listen(28072) ? (t != 0) : (ListenerUtil.mutListener.listen(28071) ? (t == 0) : (t >= 0)))))); t--) {
                        ListenerUtil.loopListener.listen("_loopCounter421", ++_loopCounter421);
                        int a = (int) Math.floor((ListenerUtil.mutListener.listen(28056) ? (num % Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28055) ? (num * Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28054) ? (num - Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28053) ? (num + Math.pow(62, t)) : (num / Math.pow(62, t)))))));
                        if (!ListenerUtil.mutListener.listen(28061)) {
                            out.append(index.substring(a, (ListenerUtil.mutListener.listen(28060) ? (a % 1) : (ListenerUtil.mutListener.listen(28059) ? (a / 1) : (ListenerUtil.mutListener.listen(28058) ? (a * 1) : (ListenerUtil.mutListener.listen(28057) ? (a - 1) : (a + 1)))))));
                        }
                        if (!ListenerUtil.mutListener.listen(28070)) {
                            num = (ListenerUtil.mutListener.listen(28069) ? (num % ((ListenerUtil.mutListener.listen(28065) ? (a % Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28064) ? (a / Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28063) ? (a - Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28062) ? (a + Math.pow(62, t)) : (a * Math.pow(62, t)))))))) : (ListenerUtil.mutListener.listen(28068) ? (num / ((ListenerUtil.mutListener.listen(28065) ? (a % Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28064) ? (a / Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28063) ? (a - Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28062) ? (a + Math.pow(62, t)) : (a * Math.pow(62, t)))))))) : (ListenerUtil.mutListener.listen(28067) ? (num * ((ListenerUtil.mutListener.listen(28065) ? (a % Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28064) ? (a / Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28063) ? (a - Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28062) ? (a + Math.pow(62, t)) : (a * Math.pow(62, t)))))))) : (ListenerUtil.mutListener.listen(28066) ? (num + ((ListenerUtil.mutListener.listen(28065) ? (a % Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28064) ? (a / Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28063) ? (a - Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28062) ? (a + Math.pow(62, t)) : (a * Math.pow(62, t)))))))) : (num - ((ListenerUtil.mutListener.listen(28065) ? (a % Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28064) ? (a / Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28063) ? (a - Math.pow(62, t)) : (ListenerUtil.mutListener.listen(28062) ? (a + Math.pow(62, t)) : (a * Math.pow(62, t))))))))))));
                        }
                    }
                }
            }
            return out.toString();
        } catch (IndexOutOfBoundsException e) {
            if (!ListenerUtil.mutListener.listen(28040)) {
                AppLog.e(T.UTILS, "Cannot convert number " + num + " to base 62", e);
            }
        }
        return null;
    }

    /**
     * Returns The post shortlink
     *
     * @param site Blog that contains the post or the page
     * @param post Post or page we want calculate the shortlink
     * @return String The blog shortlink or null (null is returned if the blog object is empty, or it's not a
     * wpcom/jetpack blog, or in case of errors).
     */
    public static String getPostShortlink(SiteModel site, PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(28078)) {
            if ((ListenerUtil.mutListener.listen(28077) ? (post == null && site == null) : (post == null || site == null))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(28079)) {
            if (!SiteUtils.isAccessedViaWPComRest(site)) {
                return null;
            }
        }
        long postId = post.getRemotePostId();
        if (!ListenerUtil.mutListener.listen(28085)) {
            if ((ListenerUtil.mutListener.listen(28084) ? (postId >= 0) : (ListenerUtil.mutListener.listen(28083) ? (postId <= 0) : (ListenerUtil.mutListener.listen(28082) ? (postId > 0) : (ListenerUtil.mutListener.listen(28081) ? (postId < 0) : (ListenerUtil.mutListener.listen(28080) ? (postId != 0) : (postId == 0))))))) {
                return null;
            }
        }
        String id;
        String type;
        String postName = StringUtils.notNullStr(post.getSlug());
        if ((ListenerUtil.mutListener.listen(28099) ? ((ListenerUtil.mutListener.listen(28098) ? ((ListenerUtil.mutListener.listen(28097) ? ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) || (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8))))))) : ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) && (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8)))))))) || !postName.contains("%")) : ((ListenerUtil.mutListener.listen(28097) ? ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) || (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8))))))) : ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) && (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8)))))))) && !postName.contains("%"))) || !postName.contains("-")) : ((ListenerUtil.mutListener.listen(28098) ? ((ListenerUtil.mutListener.listen(28097) ? ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) || (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8))))))) : ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) && (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8)))))))) || !postName.contains("%")) : ((ListenerUtil.mutListener.listen(28097) ? ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) || (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8))))))) : ((ListenerUtil.mutListener.listen(28091) ? (PostStatus.fromPost(post) == PostStatus.PUBLISHED || (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0))))))) : (PostStatus.fromPost(post) == PostStatus.PUBLISHED && (ListenerUtil.mutListener.listen(28090) ? (postName.length() >= 0) : (ListenerUtil.mutListener.listen(28089) ? (postName.length() <= 0) : (ListenerUtil.mutListener.listen(28088) ? (postName.length() < 0) : (ListenerUtil.mutListener.listen(28087) ? (postName.length() != 0) : (ListenerUtil.mutListener.listen(28086) ? (postName.length() == 0) : (postName.length() > 0)))))))) && (ListenerUtil.mutListener.listen(28096) ? (postName.length() >= 8) : (ListenerUtil.mutListener.listen(28095) ? (postName.length() > 8) : (ListenerUtil.mutListener.listen(28094) ? (postName.length() < 8) : (ListenerUtil.mutListener.listen(28093) ? (postName.length() != 8) : (ListenerUtil.mutListener.listen(28092) ? (postName.length() == 8) : (postName.length() <= 8)))))))) && !postName.contains("%"))) && !postName.contains("-")))) {
            id = postName;
            type = "s";
        } else {
            id = wpme_dec2sixtwo(postId);
            if (post.isPage()) {
                type = "P";
            } else {
                type = "p";
            }
        }
        // Calculate the blog shortlink
        String blogShortlink;
        try {
            blogShortlink = wpme_dec2sixtwo(site.getSiteId());
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(28100)) {
                AppLog.e(T.UTILS, "Remote Blog ID cannot be converted to double", e);
            }
            return null;
        }
        if (!ListenerUtil.mutListener.listen(28103)) {
            if ((ListenerUtil.mutListener.listen(28102) ? ((ListenerUtil.mutListener.listen(28101) ? (TextUtils.isEmpty(type) && TextUtils.isEmpty(id)) : (TextUtils.isEmpty(type) || TextUtils.isEmpty(id))) && TextUtils.isEmpty(blogShortlink)) : ((ListenerUtil.mutListener.listen(28101) ? (TextUtils.isEmpty(type) && TextUtils.isEmpty(id)) : (TextUtils.isEmpty(type) || TextUtils.isEmpty(id))) || TextUtils.isEmpty(blogShortlink)))) {
                return null;
            }
        }
        return "http://wp.me/" + type + blogShortlink + "-" + id;
    }
}
