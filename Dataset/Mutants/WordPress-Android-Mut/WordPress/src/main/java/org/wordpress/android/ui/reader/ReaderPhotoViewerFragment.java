package org.wordpress.android.ui.reader;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import org.wordpress.android.R;
import org.wordpress.android.ui.reader.views.ReaderPhotoView;
import org.wordpress.android.ui.reader.views.ReaderPhotoView.PhotoViewListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DisplayUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPhotoViewerFragment extends Fragment {

    private String mImageUrl;

    private boolean mIsPrivate;

    private ReaderPhotoView mPhotoView;

    private PhotoViewListener mPhotoViewListener;

    /**
     * @param imageUrl the url of the image to load
     * @param isPrivate whether image is from a private blog
     */
    static ReaderPhotoViewerFragment newInstance(String imageUrl, boolean isPrivate) {
        if (!ListenerUtil.mutListener.listen(20931)) {
            AppLog.d(AppLog.T.READER, "reader photo fragment > newInstance");
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(20932)) {
            args.putString(ReaderConstants.ARG_IMAGE_URL, imageUrl);
        }
        if (!ListenerUtil.mutListener.listen(20933)) {
            args.putBoolean(ReaderConstants.ARG_IS_PRIVATE, isPrivate);
        }
        ReaderPhotoViewerFragment fragment = new ReaderPhotoViewerFragment();
        if (!ListenerUtil.mutListener.listen(20934)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        if (!ListenerUtil.mutListener.listen(20935)) {
            super.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(20938)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(20936)) {
                    mImageUrl = args.getString(ReaderConstants.ARG_IMAGE_URL);
                }
                if (!ListenerUtil.mutListener.listen(20937)) {
                    mIsPrivate = args.getBoolean(ReaderConstants.ARG_IS_PRIVATE);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reader_fragment_photo_viewer, container, false);
        if (!ListenerUtil.mutListener.listen(20939)) {
            mPhotoView = (ReaderPhotoView) view.findViewById(R.id.photo_view);
        }
        if (!ListenerUtil.mutListener.listen(20942)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(20940)) {
                    mImageUrl = savedInstanceState.getString(ReaderConstants.ARG_IMAGE_URL);
                }
                if (!ListenerUtil.mutListener.listen(20941)) {
                    mIsPrivate = savedInstanceState.getBoolean(ReaderConstants.ARG_IS_PRIVATE);
                }
            }
        }
        return view;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(20943)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(20945)) {
            if (activity instanceof PhotoViewListener) {
                if (!ListenerUtil.mutListener.listen(20944)) {
                    mPhotoViewListener = (PhotoViewListener) activity;
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(20946)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(20947)) {
            showImage();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(20948)) {
            outState.putString(ReaderConstants.ARG_IMAGE_URL, mImageUrl);
        }
        if (!ListenerUtil.mutListener.listen(20949)) {
            outState.putBoolean(ReaderConstants.ARG_IS_PRIVATE, mIsPrivate);
        }
        if (!ListenerUtil.mutListener.listen(20950)) {
            super.onSaveInstanceState(outState);
        }
    }

    private void showImage() {
        if (!ListenerUtil.mutListener.listen(20953)) {
            if ((ListenerUtil.mutListener.listen(20951) ? (isAdded() || !TextUtils.isEmpty(mImageUrl)) : (isAdded() && !TextUtils.isEmpty(mImageUrl)))) {
                // use max of width/height so image is cached the same regardless of orientation
                Rect pt = DisplayUtils.getWindowSize(requireActivity());
                int hiResWidth = Math.max(pt.height(), pt.width());
                if (!ListenerUtil.mutListener.listen(20952)) {
                    // don't use AT media proxy here
                    mPhotoView.setImageUrl(mImageUrl, hiResWidth, mIsPrivate, false, mPhotoViewListener);
                }
            }
        }
    }
}
