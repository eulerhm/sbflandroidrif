package fr.free.nrw.commons.upload;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.filepicker.UploadableFile;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The adapter class for image thumbnails to be shown while uploading.
 */
class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailsAdapter.ViewHolder> {

    public static Context context;

    List<UploadableFile> uploadableFiles;

    private Callback callback;

    public ThumbnailsAdapter(Callback callback) {
        if (!ListenerUtil.mutListener.listen(7584)) {
            this.uploadableFiles = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(7585)) {
            this.callback = callback;
        }
    }

    /**
     * Sets the data, the media files
     * @param uploadableFiles
     */
    public void setUploadableFiles(List<UploadableFile> uploadableFiles) {
        if (!ListenerUtil.mutListener.listen(7586)) {
            this.uploadableFiles = uploadableFiles;
        }
        if (!ListenerUtil.mutListener.listen(7587)) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_upload_thumbnail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (!ListenerUtil.mutListener.listen(7588)) {
            viewHolder.bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return uploadableFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_container)
        RelativeLayout rlContainer;

        @BindView(R.id.iv_thumbnail)
        SimpleDraweeView background;

        @BindView(R.id.iv_error)
        ImageView ivError;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(7589)) {
                ButterKnife.bind(this, itemView);
            }
        }

        /**
         * Binds a row item to the ViewHolder
         * @param position
         */
        public void bind(int position) {
            UploadableFile uploadableFile = uploadableFiles.get(position);
            Uri uri = uploadableFile.getMediaUri();
            if (!ListenerUtil.mutListener.listen(7590)) {
                background.setImageURI(Uri.fromFile(new File(String.valueOf(uri))));
            }
            if (!ListenerUtil.mutListener.listen(7620)) {
                if ((ListenerUtil.mutListener.listen(7595) ? (position >= callback.getCurrentSelectedFilePosition()) : (ListenerUtil.mutListener.listen(7594) ? (position <= callback.getCurrentSelectedFilePosition()) : (ListenerUtil.mutListener.listen(7593) ? (position > callback.getCurrentSelectedFilePosition()) : (ListenerUtil.mutListener.listen(7592) ? (position < callback.getCurrentSelectedFilePosition()) : (ListenerUtil.mutListener.listen(7591) ? (position != callback.getCurrentSelectedFilePosition()) : (position == callback.getCurrentSelectedFilePosition()))))))) {
                    GradientDrawable border = new GradientDrawable();
                    if (!ListenerUtil.mutListener.listen(7607)) {
                        border.setShape(GradientDrawable.RECTANGLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7608)) {
                        border.setStroke(8, context.getResources().getColor(R.color.primaryColor));
                    }
                    if (!ListenerUtil.mutListener.listen(7609)) {
                        rlContainer.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7610)) {
                        rlContainer.setClickable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7611)) {
                        rlContainer.setAlpha(1.0f);
                    }
                    if (!ListenerUtil.mutListener.listen(7612)) {
                        rlContainer.setBackground(border);
                    }
                    if (!ListenerUtil.mutListener.listen(7619)) {
                        if ((ListenerUtil.mutListener.listen(7617) ? (VERSION.SDK_INT <= VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7616) ? (VERSION.SDK_INT > VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7615) ? (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7614) ? (VERSION.SDK_INT != VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7613) ? (VERSION.SDK_INT == VERSION_CODES.LOLLIPOP) : (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP))))))) {
                            if (!ListenerUtil.mutListener.listen(7618)) {
                                rlContainer.setElevation(10);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7596)) {
                        rlContainer.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7597)) {
                        rlContainer.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7598)) {
                        rlContainer.setAlpha(0.7f);
                    }
                    if (!ListenerUtil.mutListener.listen(7599)) {
                        rlContainer.setBackground(null);
                    }
                    if (!ListenerUtil.mutListener.listen(7606)) {
                        if ((ListenerUtil.mutListener.listen(7604) ? (VERSION.SDK_INT <= VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7603) ? (VERSION.SDK_INT > VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7602) ? (VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7601) ? (VERSION.SDK_INT != VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(7600) ? (VERSION.SDK_INT == VERSION_CODES.LOLLIPOP) : (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP))))))) {
                            if (!ListenerUtil.mutListener.listen(7605)) {
                                rlContainer.setElevation(0);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Callback used to get the current selected file position
     */
    interface Callback {

        int getCurrentSelectedFilePosition();
    }
}
