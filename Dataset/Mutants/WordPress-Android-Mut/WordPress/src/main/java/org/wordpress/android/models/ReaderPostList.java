package org.wordpress.android.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostId;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPostList extends ArrayList<ReaderPost> {

    public static ReaderPostList fromJson(JSONObject json) {
        if (!ListenerUtil.mutListener.listen(2415)) {
            if (json == null) {
                throw new IllegalArgumentException("null json post list");
            }
        }
        ReaderPostList posts = new ReaderPostList();
        JSONArray jsonPosts = json.optJSONArray("posts");
        if (!ListenerUtil.mutListener.listen(2423)) {
            if (jsonPosts != null) {
                if (!ListenerUtil.mutListener.listen(2422)) {
                    {
                        long _loopCounter86 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(2421) ? (i >= jsonPosts.length()) : (ListenerUtil.mutListener.listen(2420) ? (i <= jsonPosts.length()) : (ListenerUtil.mutListener.listen(2419) ? (i > jsonPosts.length()) : (ListenerUtil.mutListener.listen(2418) ? (i != jsonPosts.length()) : (ListenerUtil.mutListener.listen(2417) ? (i == jsonPosts.length()) : (i < jsonPosts.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter86", ++_loopCounter86);
                            if (!ListenerUtil.mutListener.listen(2416)) {
                                posts.add(ReaderPost.fromJson(jsonPosts.optJSONObject(i)));
                            }
                        }
                    }
                }
            }
        }
        return posts;
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    public int indexOfPost(ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(2424)) {
            if (post == null) {
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(2434)) {
            {
                long _loopCounter87 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2433) ? (i >= size()) : (ListenerUtil.mutListener.listen(2432) ? (i <= size()) : (ListenerUtil.mutListener.listen(2431) ? (i > size()) : (ListenerUtil.mutListener.listen(2430) ? (i != size()) : (ListenerUtil.mutListener.listen(2429) ? (i == size()) : (i < size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter87", ++_loopCounter87);
                    if (!ListenerUtil.mutListener.listen(2428)) {
                        if (this.get(i).postId == post.postId) {
                            if (!ListenerUtil.mutListener.listen(2427)) {
                                if ((ListenerUtil.mutListener.listen(2425) ? (post.isExternal || post.feedId == this.get(i).feedId) : (post.isExternal && post.feedId == this.get(i).feedId))) {
                                    return i;
                                } else if ((ListenerUtil.mutListener.listen(2426) ? (!post.isExternal || post.blogId == this.get(i).blogId) : (!post.isExternal && post.blogId == this.get(i).blogId))) {
                                    return i;
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int indexOfIds(ReaderBlogIdPostId ids) {
        if (!ListenerUtil.mutListener.listen(2435)) {
            if (ids == null) {
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(2442)) {
            {
                long _loopCounter88 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2441) ? (i >= size()) : (ListenerUtil.mutListener.listen(2440) ? (i <= size()) : (ListenerUtil.mutListener.listen(2439) ? (i > size()) : (ListenerUtil.mutListener.listen(2438) ? (i != size()) : (ListenerUtil.mutListener.listen(2437) ? (i == size()) : (i < size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter88", ++_loopCounter88);
                    if (!ListenerUtil.mutListener.listen(2436)) {
                        if (this.get(i).hasIds(ids)) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /*
     * does passed list contain the same posts as this list?
     */
    public boolean isSameList(ReaderPostList posts) {
        if (!ListenerUtil.mutListener.listen(2449)) {
            if ((ListenerUtil.mutListener.listen(2448) ? (posts == null && (ListenerUtil.mutListener.listen(2447) ? (posts.size() >= this.size()) : (ListenerUtil.mutListener.listen(2446) ? (posts.size() <= this.size()) : (ListenerUtil.mutListener.listen(2445) ? (posts.size() > this.size()) : (ListenerUtil.mutListener.listen(2444) ? (posts.size() < this.size()) : (ListenerUtil.mutListener.listen(2443) ? (posts.size() == this.size()) : (posts.size() != this.size()))))))) : (posts == null || (ListenerUtil.mutListener.listen(2447) ? (posts.size() >= this.size()) : (ListenerUtil.mutListener.listen(2446) ? (posts.size() <= this.size()) : (ListenerUtil.mutListener.listen(2445) ? (posts.size() > this.size()) : (ListenerUtil.mutListener.listen(2444) ? (posts.size() < this.size()) : (ListenerUtil.mutListener.listen(2443) ? (posts.size() == this.size()) : (posts.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2457)) {
            {
                long _loopCounter89 = 0;
                for (ReaderPost post : posts) {
                    ListenerUtil.loopListener.listen("_loopCounter89", ++_loopCounter89);
                    int index = indexOfPost(post);
                    if (!ListenerUtil.mutListener.listen(2456)) {
                        if ((ListenerUtil.mutListener.listen(2455) ? ((ListenerUtil.mutListener.listen(2454) ? (index >= -1) : (ListenerUtil.mutListener.listen(2453) ? (index <= -1) : (ListenerUtil.mutListener.listen(2452) ? (index > -1) : (ListenerUtil.mutListener.listen(2451) ? (index < -1) : (ListenerUtil.mutListener.listen(2450) ? (index != -1) : (index == -1)))))) && !post.isSamePost(this.get(index))) : ((ListenerUtil.mutListener.listen(2454) ? (index >= -1) : (ListenerUtil.mutListener.listen(2453) ? (index <= -1) : (ListenerUtil.mutListener.listen(2452) ? (index > -1) : (ListenerUtil.mutListener.listen(2451) ? (index < -1) : (ListenerUtil.mutListener.listen(2450) ? (index != -1) : (index == -1)))))) || !post.isSamePost(this.get(index))))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
     * Does passed list contain the same posts as this list?
     * Also compares the bookmark flag that is not yet implemented on server
     * We might want to use original isSameList when bookmarked flag will be implemented on server side and Post model
     * updated.
     */
    public boolean isSameListWithBookmark(ReaderPostList posts) {
        if (!ListenerUtil.mutListener.listen(2464)) {
            if ((ListenerUtil.mutListener.listen(2463) ? (posts == null && (ListenerUtil.mutListener.listen(2462) ? (posts.size() >= this.size()) : (ListenerUtil.mutListener.listen(2461) ? (posts.size() <= this.size()) : (ListenerUtil.mutListener.listen(2460) ? (posts.size() > this.size()) : (ListenerUtil.mutListener.listen(2459) ? (posts.size() < this.size()) : (ListenerUtil.mutListener.listen(2458) ? (posts.size() == this.size()) : (posts.size() != this.size()))))))) : (posts == null || (ListenerUtil.mutListener.listen(2462) ? (posts.size() >= this.size()) : (ListenerUtil.mutListener.listen(2461) ? (posts.size() <= this.size()) : (ListenerUtil.mutListener.listen(2460) ? (posts.size() > this.size()) : (ListenerUtil.mutListener.listen(2459) ? (posts.size() < this.size()) : (ListenerUtil.mutListener.listen(2458) ? (posts.size() == this.size()) : (posts.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2473)) {
            {
                long _loopCounter90 = 0;
                for (ReaderPost post : posts) {
                    ListenerUtil.loopListener.listen("_loopCounter90", ++_loopCounter90);
                    int index = indexOfPost(post);
                    if (!ListenerUtil.mutListener.listen(2470)) {
                        if ((ListenerUtil.mutListener.listen(2469) ? (index >= -1) : (ListenerUtil.mutListener.listen(2468) ? (index <= -1) : (ListenerUtil.mutListener.listen(2467) ? (index > -1) : (ListenerUtil.mutListener.listen(2466) ? (index < -1) : (ListenerUtil.mutListener.listen(2465) ? (index != -1) : (index == -1))))))) {
                            return false;
                        }
                    }
                    ReaderPost postInsideList = this.get(index);
                    if (!ListenerUtil.mutListener.listen(2472)) {
                        if ((ListenerUtil.mutListener.listen(2471) ? (!post.isSamePost(postInsideList) && post.isBookmarked != postInsideList.isBookmarked) : (!post.isSamePost(postInsideList) || post.isBookmarked != postInsideList.isBookmarked))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
     * returns posts in this list which are in the passed blog
     */
    public ReaderPostList getPostsInBlog(long blogId) {
        ReaderPostList postsInBlog = new ReaderPostList();
        if (!ListenerUtil.mutListener.listen(2476)) {
            {
                long _loopCounter91 = 0;
                for (ReaderPost post : this) {
                    ListenerUtil.loopListener.listen("_loopCounter91", ++_loopCounter91);
                    if (!ListenerUtil.mutListener.listen(2475)) {
                        if (post.blogId == blogId) {
                            if (!ListenerUtil.mutListener.listen(2474)) {
                                postsInBlog.add(post);
                            }
                        }
                    }
                }
            }
        }
        return postsInBlog;
    }
}
