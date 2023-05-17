package org.wordpress.android.models;

import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderBlogList extends ArrayList<ReaderBlog> {

    @Override
    public Object clone() {
        return super.clone();
    }

    public static ReaderBlogList fromJson(JSONObject json) {
        ReaderBlogList blogs = new ReaderBlogList();
        if (!ListenerUtil.mutListener.listen(2045)) {
            if (json == null) {
                return blogs;
            }
        }
        // read/following/mine response
        JSONArray jsonBlogs = json.optJSONArray("subscriptions");
        if (!ListenerUtil.mutListener.listen(2056)) {
            if (jsonBlogs != null) {
                if (!ListenerUtil.mutListener.listen(2055)) {
                    {
                        long _loopCounter76 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(2054) ? (i >= jsonBlogs.length()) : (ListenerUtil.mutListener.listen(2053) ? (i <= jsonBlogs.length()) : (ListenerUtil.mutListener.listen(2052) ? (i > jsonBlogs.length()) : (ListenerUtil.mutListener.listen(2051) ? (i != jsonBlogs.length()) : (ListenerUtil.mutListener.listen(2050) ? (i == jsonBlogs.length()) : (i < jsonBlogs.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter76", ++_loopCounter76);
                            ReaderBlog blog = ReaderBlog.fromJson(jsonBlogs.optJSONObject(i));
                            if (!ListenerUtil.mutListener.listen(2049)) {
                                // will let you follow any URL regardless if it's valid
                                if ((ListenerUtil.mutListener.listen(2047) ? ((ListenerUtil.mutListener.listen(2046) ? (blog.hasName() && blog.hasDescription()) : (blog.hasName() || blog.hasDescription())) && blog.hasUrl()) : ((ListenerUtil.mutListener.listen(2046) ? (blog.hasName() && blog.hasDescription()) : (blog.hasName() || blog.hasDescription())) || blog.hasUrl()))) {
                                    if (!ListenerUtil.mutListener.listen(2048)) {
                                        blogs.add(blog);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return blogs;
    }

    private int indexOfBlogId(long blogId) {
        if (!ListenerUtil.mutListener.listen(2063)) {
            {
                long _loopCounter77 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2062) ? (i >= size()) : (ListenerUtil.mutListener.listen(2061) ? (i <= size()) : (ListenerUtil.mutListener.listen(2060) ? (i > size()) : (ListenerUtil.mutListener.listen(2059) ? (i != size()) : (ListenerUtil.mutListener.listen(2058) ? (i == size()) : (i < size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter77", ++_loopCounter77);
                    if (!ListenerUtil.mutListener.listen(2057)) {
                        if (this.get(i).blogId == blogId) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public boolean isSameList(ReaderBlogList blogs) {
        if (!ListenerUtil.mutListener.listen(2070)) {
            if ((ListenerUtil.mutListener.listen(2069) ? (blogs == null && (ListenerUtil.mutListener.listen(2068) ? (blogs.size() >= this.size()) : (ListenerUtil.mutListener.listen(2067) ? (blogs.size() <= this.size()) : (ListenerUtil.mutListener.listen(2066) ? (blogs.size() > this.size()) : (ListenerUtil.mutListener.listen(2065) ? (blogs.size() < this.size()) : (ListenerUtil.mutListener.listen(2064) ? (blogs.size() == this.size()) : (blogs.size() != this.size()))))))) : (blogs == null || (ListenerUtil.mutListener.listen(2068) ? (blogs.size() >= this.size()) : (ListenerUtil.mutListener.listen(2067) ? (blogs.size() <= this.size()) : (ListenerUtil.mutListener.listen(2066) ? (blogs.size() > this.size()) : (ListenerUtil.mutListener.listen(2065) ? (blogs.size() < this.size()) : (ListenerUtil.mutListener.listen(2064) ? (blogs.size() == this.size()) : (blogs.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2078)) {
            {
                long _loopCounter78 = 0;
                for (ReaderBlog blogInfo : blogs) {
                    ListenerUtil.loopListener.listen("_loopCounter78", ++_loopCounter78);
                    int index = indexOfBlogId(blogInfo.blogId);
                    if (!ListenerUtil.mutListener.listen(2076)) {
                        if ((ListenerUtil.mutListener.listen(2075) ? (index >= -1) : (ListenerUtil.mutListener.listen(2074) ? (index <= -1) : (ListenerUtil.mutListener.listen(2073) ? (index > -1) : (ListenerUtil.mutListener.listen(2072) ? (index < -1) : (ListenerUtil.mutListener.listen(2071) ? (index != -1) : (index == -1))))))) {
                            return false;
                        }
                    }
                    ReaderBlog thisInfo = this.get(index);
                    if (!ListenerUtil.mutListener.listen(2077)) {
                        if (!thisInfo.isSameAs(blogInfo)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
     * returns true if the passed blog list has the same blogs that are in this list - differs
     * from isSameList() in that isSameList() checks for *any* changes (subscription count, etc.)
     * whereas this only checks if the passed list has any blogs that are not in this list, or
     * this list has any blogs that are not in the passed list
     */
    public boolean hasSameBlogs(@NonNull ReaderBlogList blogs) {
        if (!ListenerUtil.mutListener.listen(2084)) {
            if ((ListenerUtil.mutListener.listen(2083) ? (blogs.size() >= this.size()) : (ListenerUtil.mutListener.listen(2082) ? (blogs.size() <= this.size()) : (ListenerUtil.mutListener.listen(2081) ? (blogs.size() > this.size()) : (ListenerUtil.mutListener.listen(2080) ? (blogs.size() < this.size()) : (ListenerUtil.mutListener.listen(2079) ? (blogs.size() == this.size()) : (blogs.size() != this.size()))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2086)) {
            {
                long _loopCounter79 = 0;
                for (ReaderBlog blogInfo : blogs) {
                    ListenerUtil.loopListener.listen("_loopCounter79", ++_loopCounter79);
                    if (!ListenerUtil.mutListener.listen(2085)) {
                        if (indexOfBlogId(blogInfo.blogId) == -1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
