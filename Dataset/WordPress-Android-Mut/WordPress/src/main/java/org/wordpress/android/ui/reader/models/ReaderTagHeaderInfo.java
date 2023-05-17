package org.wordpress.android.ui.reader.models;

import android.text.TextUtils;
import org.wordpress.android.util.StringUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderTagHeaderInfo {

    private String mImageUrl;

    private long mSourceBlogId;

    private long mSourcePostId;

    private String mAuthorName;

    private String mBlogName;

    public String getImageUrl() {
        return StringUtils.notNullStr(mImageUrl);
    }

    public void setImageUrl(String imageUrl) {
        if (!ListenerUtil.mutListener.listen(19104)) {
            mImageUrl = StringUtils.notNullStr(imageUrl);
        }
    }

    public String getAuthorName() {
        return StringUtils.notNullStr(mAuthorName);
    }

    public void setAuthorName(String authorName) {
        if (!ListenerUtil.mutListener.listen(19105)) {
            mAuthorName = StringUtils.notNullStr(authorName);
        }
    }

    public String getBlogName() {
        return StringUtils.notNullStr(mBlogName);
    }

    public void setBlogName(String blogName) {
        if (!ListenerUtil.mutListener.listen(19106)) {
            mBlogName = StringUtils.notNullStr(blogName);
        }
    }

    public long getSourceBlogId() {
        return mSourceBlogId;
    }

    public void setSourceBlogId(long blogId) {
        if (!ListenerUtil.mutListener.listen(19107)) {
            mSourceBlogId = blogId;
        }
    }

    public long getSourcePostId() {
        return mSourcePostId;
    }

    public void setSourcePostId(long postId) {
        if (!ListenerUtil.mutListener.listen(19108)) {
            mSourcePostId = postId;
        }
    }

    public boolean hasAuthorName() {
        return !TextUtils.isEmpty(mAuthorName);
    }

    public boolean hasBlogName() {
        return !TextUtils.isEmpty(mBlogName);
    }

    public boolean hasSourcePost() {
        return (ListenerUtil.mutListener.listen(19119) ? ((ListenerUtil.mutListener.listen(19113) ? (mSourceBlogId >= 0) : (ListenerUtil.mutListener.listen(19112) ? (mSourceBlogId <= 0) : (ListenerUtil.mutListener.listen(19111) ? (mSourceBlogId > 0) : (ListenerUtil.mutListener.listen(19110) ? (mSourceBlogId < 0) : (ListenerUtil.mutListener.listen(19109) ? (mSourceBlogId == 0) : (mSourceBlogId != 0)))))) || (ListenerUtil.mutListener.listen(19118) ? (mSourcePostId >= 0) : (ListenerUtil.mutListener.listen(19117) ? (mSourcePostId <= 0) : (ListenerUtil.mutListener.listen(19116) ? (mSourcePostId > 0) : (ListenerUtil.mutListener.listen(19115) ? (mSourcePostId < 0) : (ListenerUtil.mutListener.listen(19114) ? (mSourcePostId == 0) : (mSourcePostId != 0))))))) : ((ListenerUtil.mutListener.listen(19113) ? (mSourceBlogId >= 0) : (ListenerUtil.mutListener.listen(19112) ? (mSourceBlogId <= 0) : (ListenerUtil.mutListener.listen(19111) ? (mSourceBlogId > 0) : (ListenerUtil.mutListener.listen(19110) ? (mSourceBlogId < 0) : (ListenerUtil.mutListener.listen(19109) ? (mSourceBlogId == 0) : (mSourceBlogId != 0)))))) && (ListenerUtil.mutListener.listen(19118) ? (mSourcePostId >= 0) : (ListenerUtil.mutListener.listen(19117) ? (mSourcePostId <= 0) : (ListenerUtil.mutListener.listen(19116) ? (mSourcePostId > 0) : (ListenerUtil.mutListener.listen(19115) ? (mSourcePostId < 0) : (ListenerUtil.mutListener.listen(19114) ? (mSourcePostId == 0) : (mSourcePostId != 0))))))));
    }
}
