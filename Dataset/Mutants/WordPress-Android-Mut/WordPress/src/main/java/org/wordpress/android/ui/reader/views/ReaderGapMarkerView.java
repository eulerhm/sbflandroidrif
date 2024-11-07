package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.wordpress.android.R;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter;
import org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.UpdateAction;
import org.wordpress.android.util.NetworkUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * marker view between posts indicating a gap in time between them that can be filled in - designed
 * for use inside ReaderPostAdapter
 */
public class ReaderGapMarkerView extends RelativeLayout {

    private TextView mText;

    private ProgressBar mProgress;

    private ReaderTag mCurrentTag;

    public ReaderGapMarkerView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(19937)) {
            initView(context);
        }
    }

    public ReaderGapMarkerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(19938)) {
            initView(context);
        }
    }

    public ReaderGapMarkerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(19939)) {
            initView(context);
        }
    }

    private void initView(Context context) {
        View view = inflate(context, R.layout.reader_gap_marker_view, this);
        if (!ListenerUtil.mutListener.listen(19940)) {
            mText = (TextView) view.findViewById(R.id.text_gap_marker);
        }
        if (!ListenerUtil.mutListener.listen(19941)) {
            mProgress = (ProgressBar) view.findViewById(R.id.progress_gap_marker);
        }
        if (!ListenerUtil.mutListener.listen(19943)) {
            mText.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(19942)) {
                        fillTheGap();
                    }
                }
            });
        }
    }

    public void setCurrentTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(19944)) {
            mCurrentTag = tag;
        }
        if (!ListenerUtil.mutListener.listen(19945)) {
            hideProgress();
        }
    }

    private void fillTheGap() {
        if (!ListenerUtil.mutListener.listen(19947)) {
            if ((ListenerUtil.mutListener.listen(19946) ? (mCurrentTag == null && !NetworkUtils.checkConnection(getContext())) : (mCurrentTag == null || !NetworkUtils.checkConnection(getContext())))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(19948)) {
            // and will take care of hiding this view
            ReaderPostServiceStarter.startServiceForTag(getContext(), mCurrentTag, UpdateAction.REQUEST_OLDER_THAN_GAP);
        }
        if (!ListenerUtil.mutListener.listen(19949)) {
            showProgress();
        }
    }

    private void showProgress() {
        if (!ListenerUtil.mutListener.listen(19950)) {
            mText.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(19951)) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgress() {
        if (!ListenerUtil.mutListener.listen(19952)) {
            mText.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(19953)) {
            mProgress.setVisibility(View.GONE);
        }
    }
}
