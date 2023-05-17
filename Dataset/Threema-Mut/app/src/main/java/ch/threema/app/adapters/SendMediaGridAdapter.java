/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import ch.threema.app.R;
import ch.threema.app.activities.SendMediaActivity;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.ui.draggablegrid.BaseDynamicGridAdapter;
import ch.threema.app.ui.listitemholder.AbstractListItemHolder;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.StringConversionUtil;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SendMediaGridAdapter extends BaseDynamicGridAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SendMediaGridAdapter.class);

    private final List<MediaItem> items;

    private final Context context;

    private final LayoutInflater layoutInflater;

    private final int itemWidth;

    private final ClickListener clickListener;

    public static final int VIEW_TYPE_NORMAL = 0;

    public static final int VIEW_TYPE_ADD = 1;

    public SendMediaGridAdapter(Context context, List<MediaItem> items, int itemWidth, ClickListener clickListener) {
        super(context, items, context.getResources().getInteger(R.integer.gridview_num_columns));
        this.context = context;
        this.items = items;
        this.itemWidth = itemWidth;
        this.layoutInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
    }

    public static class SendMediaHolder extends AbstractListItemHolder {

        public ImageView imageView, deleteView, brokenView;

        public LinearLayout qualifierView;

        public int itemType;
    }

    @Override
    public int getItemViewType(int position) {
        return position == items.size() ? VIEW_TYPE_ADD : VIEW_TYPE_NORMAL;
    }

    @Override
    public int getCount() {
        return Math.min(items.size() + 1, SendMediaActivity.MAX_SELECTABLE_IMAGES);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView;
        int itemType = getItemViewType(position);
        SendMediaHolder holder = new SendMediaHolder();
        if ((ListenerUtil.mutListener.listen(9560) ? (itemType >= VIEW_TYPE_ADD) : (ListenerUtil.mutListener.listen(9559) ? (itemType <= VIEW_TYPE_ADD) : (ListenerUtil.mutListener.listen(9558) ? (itemType > VIEW_TYPE_ADD) : (ListenerUtil.mutListener.listen(9557) ? (itemType < VIEW_TYPE_ADD) : (ListenerUtil.mutListener.listen(9556) ? (itemType != VIEW_TYPE_ADD) : (itemType == VIEW_TYPE_ADD))))))) {
            itemView = layoutInflater.inflate(R.layout.item_send_media_add, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.item_send_media, parent, false);
        }
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(this.itemWidth, this.itemWidth);
        if (!ListenerUtil.mutListener.listen(9561)) {
            itemView.setLayoutParams(params);
        }
        if (!ListenerUtil.mutListener.listen(9562)) {
            holder.imageView = itemView.findViewById(R.id.image_view);
        }
        if (!ListenerUtil.mutListener.listen(9563)) {
            holder.qualifierView = itemView.findViewById(R.id.qualifier_view);
        }
        if (!ListenerUtil.mutListener.listen(9564)) {
            holder.deleteView = itemView.findViewById(R.id.delete_view);
        }
        if (!ListenerUtil.mutListener.listen(9565)) {
            holder.brokenView = itemView.findViewById(R.id.broken_view);
        }
        if (!ListenerUtil.mutListener.listen(9566)) {
            holder.position = position;
        }
        if (!ListenerUtil.mutListener.listen(9567)) {
            holder.itemType = itemType;
        }
        if (!ListenerUtil.mutListener.listen(9568)) {
            itemView.setTag(holder);
        }
        if (!ListenerUtil.mutListener.listen(9596)) {
            if ((ListenerUtil.mutListener.listen(9573) ? (itemType >= VIEW_TYPE_NORMAL) : (ListenerUtil.mutListener.listen(9572) ? (itemType <= VIEW_TYPE_NORMAL) : (ListenerUtil.mutListener.listen(9571) ? (itemType > VIEW_TYPE_NORMAL) : (ListenerUtil.mutListener.listen(9570) ? (itemType < VIEW_TYPE_NORMAL) : (ListenerUtil.mutListener.listen(9569) ? (itemType != VIEW_TYPE_NORMAL) : (itemType == VIEW_TYPE_NORMAL))))))) {
                final MediaItem item = items.get(position);
                if (!ListenerUtil.mutListener.listen(9574)) {
                    holder.deleteView.setOnClickListener(v -> clickListener.onDeleteKeyClicked(item));
                }
                if (!ListenerUtil.mutListener.listen(9575)) {
                    holder.brokenView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(9594)) {
                    Glide.with(context).load(item.getUri()).transition(withCrossFade()).addListener(new RequestListener<Drawable>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            if (!ListenerUtil.mutListener.listen(9576)) {
                                holder.brokenView.setVisibility(View.VISIBLE);
                            }
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            if (!ListenerUtil.mutListener.listen(9593)) {
                                if ((ListenerUtil.mutListener.listen(9577) ? (item.getType() == MediaItem.TYPE_VIDEO_CAM && item.getType() == MediaItem.TYPE_VIDEO) : (item.getType() == MediaItem.TYPE_VIDEO_CAM || item.getType() == MediaItem.TYPE_VIDEO))) {
                                    if (!ListenerUtil.mutListener.listen(9582)) {
                                        holder.qualifierView.setVisibility(View.VISIBLE);
                                    }
                                    AppCompatImageView imageView = holder.qualifierView.findViewById(R.id.video_icon);
                                    if (!ListenerUtil.mutListener.listen(9583)) {
                                        imageView.setImageResource(R.drawable.ic_videocam_black_24dp);
                                    }
                                    TextView durationView = holder.qualifierView.findViewById(R.id.video_duration_text);
                                    if (!ListenerUtil.mutListener.listen(9592)) {
                                        if ((ListenerUtil.mutListener.listen(9588) ? (item.getDurationMs() >= 0) : (ListenerUtil.mutListener.listen(9587) ? (item.getDurationMs() <= 0) : (ListenerUtil.mutListener.listen(9586) ? (item.getDurationMs() < 0) : (ListenerUtil.mutListener.listen(9585) ? (item.getDurationMs() != 0) : (ListenerUtil.mutListener.listen(9584) ? (item.getDurationMs() == 0) : (item.getDurationMs() > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(9590)) {
                                                durationView.setText(StringConversionUtil.getDurationString(item.getDurationMs()));
                                            }
                                            if (!ListenerUtil.mutListener.listen(9591)) {
                                                durationView.setVisibility(View.VISIBLE);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(9589)) {
                                                durationView.setVisibility(View.GONE);
                                            }
                                        }
                                    }
                                } else if (item.getType() == MediaItem.TYPE_GIF) {
                                    if (!ListenerUtil.mutListener.listen(9579)) {
                                        holder.qualifierView.setVisibility(View.VISIBLE);
                                    }
                                    AppCompatImageView imageView = holder.qualifierView.findViewById(R.id.video_icon);
                                    if (!ListenerUtil.mutListener.listen(9580)) {
                                        imageView.setImageResource(R.drawable.ic_gif_24dp);
                                    }
                                    if (!ListenerUtil.mutListener.listen(9581)) {
                                        holder.qualifierView.findViewById(R.id.video_duration_text).setVisibility(View.GONE);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9578)) {
                                        holder.qualifierView.setVisibility(View.GONE);
                                    }
                                }
                            }
                            return false;
                        }
                    }).into(holder.imageView);
                }
                if (!ListenerUtil.mutListener.listen(9595)) {
                    rotateAndFlipImageView(holder.imageView, item);
                }
            }
        }
        return itemView;
    }

    private void rotateAndFlipImageView(ImageView imageView, MediaItem item) {
        if (!ListenerUtil.mutListener.listen(9597)) {
            imageView.setRotation(item.getRotation());
        }
        if (!ListenerUtil.mutListener.listen(9600)) {
            if (item.getFlip() == BitmapUtil.FLIP_NONE) {
                if (!ListenerUtil.mutListener.listen(9598)) {
                    imageView.setScaleY(1);
                }
                if (!ListenerUtil.mutListener.listen(9599)) {
                    imageView.setScaleX(1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9602)) {
            if ((item.getFlip() & BitmapUtil.FLIP_HORIZONTAL) == BitmapUtil.FLIP_HORIZONTAL) {
                if (!ListenerUtil.mutListener.listen(9601)) {
                    imageView.setScaleX(-1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9604)) {
            if ((item.getFlip() & BitmapUtil.FLIP_VERTICAL) == BitmapUtil.FLIP_VERTICAL) {
                if (!ListenerUtil.mutListener.listen(9603)) {
                    imageView.setScaleY(-1);
                }
            }
        }
    }

    @Override
    public void reorderItems(int originalPosition, int newPosition) {
        if (!ListenerUtil.mutListener.listen(9611)) {
            if ((ListenerUtil.mutListener.listen(9609) ? (newPosition >= items.size()) : (ListenerUtil.mutListener.listen(9608) ? (newPosition <= items.size()) : (ListenerUtil.mutListener.listen(9607) ? (newPosition > items.size()) : (ListenerUtil.mutListener.listen(9606) ? (newPosition != items.size()) : (ListenerUtil.mutListener.listen(9605) ? (newPosition == items.size()) : (newPosition < items.size()))))))) {
                if (!ListenerUtil.mutListener.listen(9610)) {
                    super.reorderItems(originalPosition, newPosition);
                }
            }
        }
    }

    public interface ClickListener {

        void onDeleteKeyClicked(MediaItem item);
    }
}
