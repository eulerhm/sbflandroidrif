package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.ReaderPost;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * topmost view in reader comment adapter - show info about the post
 */
public class ReaderCommentsPostHeaderView extends LinearLayout {

    public ReaderCommentsPostHeaderView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(19929)) {
            initView(context);
        }
    }

    public ReaderCommentsPostHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(19930)) {
            initView(context);
        }
    }

    public ReaderCommentsPostHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(19931)) {
            initView(context);
        }
    }

    private void initView(Context context) {
        if (!ListenerUtil.mutListener.listen(19932)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(19933)) {
            inflate(context, R.layout.reader_comments_post_header_view, this);
        }
    }

    public void setPost(final ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(19934)) {
            if (post == null) {
                return;
            }
        }
        TextView replyToAuthor = findViewById(R.id.reply_to_author);
        TextView postTitle = findViewById(R.id.post_title);
        if (!ListenerUtil.mutListener.listen(19935)) {
            replyToAuthor.setText(replyToAuthor.getContext().getString(R.string.comment_reply_to_user, post.getAuthorName()));
        }
        if (!ListenerUtil.mutListener.listen(19936)) {
            postTitle.setText(post.getTitle());
        }
    }
}
