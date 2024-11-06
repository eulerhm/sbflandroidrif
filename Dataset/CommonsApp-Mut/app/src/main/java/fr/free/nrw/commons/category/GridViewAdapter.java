package fr.free.nrw.commons.category;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GridViewAdapter extends ArrayAdapter {

    private List<Media> data;

    public GridViewAdapter(Context context, int layoutResourceId, List<Media> data) {
        super(context, layoutResourceId, data);
        if (!ListenerUtil.mutListener.listen(393)) {
            this.data = data;
        }
    }

    /**
     * Adds more item to the list
     * Its triggered on scrolling down in the list
     * @param images
     */
    public void addItems(List<Media> images) {
        if (!ListenerUtil.mutListener.listen(395)) {
            if (data == null) {
                if (!ListenerUtil.mutListener.listen(394)) {
                    data = new ArrayList<>();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(396)) {
            data.addAll(images);
        }
        if (!ListenerUtil.mutListener.listen(397)) {
            notifyDataSetChanged();
        }
    }

    /**
     * Check the first item in the new list with old list and returns true if they are same
     * Its triggered on successful response of the fetch images API.
     * @param images
     */
    public boolean containsAll(List<Media> images) {
        if (!ListenerUtil.mutListener.listen(399)) {
            if ((ListenerUtil.mutListener.listen(398) ? (images == null && images.isEmpty()) : (images == null || images.isEmpty()))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(401)) {
            if (data == null) {
                if (!ListenerUtil.mutListener.listen(400)) {
                    data = new ArrayList<>();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(402)) {
            if (data.isEmpty()) {
                return false;
            }
        }
        String fileName = data.get(0).getFilename();
        String imageName = images.get(0).getFilename();
        return imageName.equals(fileName);
    }

    @Override
    public boolean isEmpty() {
        return (ListenerUtil.mutListener.listen(403) ? (data == null && data.isEmpty()) : (data == null || data.isEmpty()));
    }

    /**
     * Sets up the UI for the category image item
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (!ListenerUtil.mutListener.listen(405)) {
            if (convertView == null) {
                if (!ListenerUtil.mutListener.listen(404)) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_category_images, null);
                }
            }
        }
        Media item = data.get(position);
        SimpleDraweeView imageView = convertView.findViewById(R.id.categoryImageView);
        TextView fileName = convertView.findViewById(R.id.categoryImageTitle);
        TextView uploader = convertView.findViewById(R.id.categoryImageAuthor);
        if (!ListenerUtil.mutListener.listen(406)) {
            fileName.setText(item.getMostRelevantCaption());
        }
        if (!ListenerUtil.mutListener.listen(407)) {
            setUploaderView(item, uploader);
        }
        if (!ListenerUtil.mutListener.listen(408)) {
            imageView.setImageURI(item.getThumbUrl());
        }
        return convertView;
    }

    /**
     * @return the Media item at the given position
     */
    @Nullable
    @Override
    public Media getItem(int position) {
        return data.get(position);
    }

    /**
     * Shows author information if its present
     * @param item
     * @param uploader
     */
    private void setUploaderView(Media item, TextView uploader) {
        if (!ListenerUtil.mutListener.listen(412)) {
            if (!TextUtils.isEmpty(item.getAuthor())) {
                if (!ListenerUtil.mutListener.listen(410)) {
                    uploader.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(411)) {
                    uploader.setText(getContext().getString(R.string.image_uploaded_by, item.getUser()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(409)) {
                    uploader.setVisibility(View.GONE);
                }
            }
        }
    }
}
