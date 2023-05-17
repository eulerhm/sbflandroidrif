/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import java.util.EnumMap;
import java.util.Map;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.MessageState;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class caches bitmaps and resources used for the message states (e.g. sent, read, acked...)
 */
public class StateBitmapUtil {

    // Singleton stuff
    private static StateBitmapUtil instance;

    @Nullable
    public static StateBitmapUtil getInstance() {
        return instance;
    }

    public static synchronized void init(Context context) {
        if (!ListenerUtil.mutListener.listen(55451)) {
            StateBitmapUtil.instance = new StateBitmapUtil(context.getApplicationContext());
        }
    }

    private final Context context;

    private Map<MessageState, Integer> messageStateBitmapResourceIds = new EnumMap<>(MessageState.class);

    private Map<MessageState, Integer> messageStateDescriptionMap = new EnumMap<MessageState, Integer>(MessageState.class);

    private int regularColor;

    private int warningColor;

    private int ackColor;

    private int decColor;

    private StateBitmapUtil(Context context) {
        this.context = context;
        if (!ListenerUtil.mutListener.listen(55452)) {
            buildState();
        }
    }

    private void buildState() {
        if (!ListenerUtil.mutListener.listen(55453)) {
            this.messageStateBitmapResourceIds.put(MessageState.READ, R.drawable.ic_visibility_filled);
        }
        if (!ListenerUtil.mutListener.listen(55454)) {
            this.messageStateBitmapResourceIds.put(MessageState.DELIVERED, R.drawable.ic_inbox_filled);
        }
        if (!ListenerUtil.mutListener.listen(55455)) {
            this.messageStateBitmapResourceIds.put(MessageState.SENT, R.drawable.ic_mail_filled);
        }
        if (!ListenerUtil.mutListener.listen(55456)) {
            this.messageStateBitmapResourceIds.put(MessageState.SENDFAILED, R.drawable.ic_report_problem_filled);
        }
        if (!ListenerUtil.mutListener.listen(55457)) {
            this.messageStateBitmapResourceIds.put(MessageState.USERACK, R.drawable.ic_thumb_up_filled);
        }
        if (!ListenerUtil.mutListener.listen(55458)) {
            this.messageStateBitmapResourceIds.put(MessageState.USERDEC, R.drawable.ic_thumb_down_filled);
        }
        if (!ListenerUtil.mutListener.listen(55459)) {
            this.messageStateBitmapResourceIds.put(MessageState.SENDING, R.drawable.ic_upload_filled);
        }
        if (!ListenerUtil.mutListener.listen(55460)) {
            this.messageStateBitmapResourceIds.put(MessageState.PENDING, R.drawable.ic_upload_filled);
        }
        if (!ListenerUtil.mutListener.listen(55461)) {
            this.messageStateBitmapResourceIds.put(MessageState.TRANSCODING, R.drawable.ic_outline_hourglass_top_24);
        }
        if (!ListenerUtil.mutListener.listen(55462)) {
            this.messageStateBitmapResourceIds.put(MessageState.CONSUMED, R.drawable.ic_baseline_hearing_24);
        }
        if (!ListenerUtil.mutListener.listen(55463)) {
            this.messageStateDescriptionMap.put(MessageState.READ, R.string.state_read);
        }
        if (!ListenerUtil.mutListener.listen(55464)) {
            this.messageStateDescriptionMap.put(MessageState.DELIVERED, R.string.state_delivered);
        }
        if (!ListenerUtil.mutListener.listen(55465)) {
            this.messageStateDescriptionMap.put(MessageState.SENT, R.string.state_sent);
        }
        if (!ListenerUtil.mutListener.listen(55466)) {
            this.messageStateDescriptionMap.put(MessageState.SENDFAILED, R.string.state_failed);
        }
        if (!ListenerUtil.mutListener.listen(55467)) {
            this.messageStateDescriptionMap.put(MessageState.USERACK, R.string.state_ack);
        }
        if (!ListenerUtil.mutListener.listen(55468)) {
            this.messageStateDescriptionMap.put(MessageState.USERDEC, R.string.state_dec);
        }
        if (!ListenerUtil.mutListener.listen(55469)) {
            this.messageStateDescriptionMap.put(MessageState.SENDING, R.string.state_sending);
        }
        if (!ListenerUtil.mutListener.listen(55470)) {
            this.messageStateDescriptionMap.put(MessageState.PENDING, R.string.state_pending);
        }
        if (!ListenerUtil.mutListener.listen(55471)) {
            this.messageStateDescriptionMap.put(MessageState.TRANSCODING, R.string.state_processing);
        }
        if (!ListenerUtil.mutListener.listen(55472)) {
            this.messageStateDescriptionMap.put(MessageState.CONSUMED, R.string.listened_to);
        }
        if (!ListenerUtil.mutListener.listen(55473)) {
            this.ackColor = context.getResources().getColor(R.color.material_green);
        }
        if (!ListenerUtil.mutListener.listen(55474)) {
            this.decColor = context.getResources().getColor(R.color.material_orange);
        }
        if (!ListenerUtil.mutListener.listen(55475)) {
            this.warningColor = context.getResources().getColor(R.color.material_red);
        }
        if (!ListenerUtil.mutListener.listen(55476)) {
            this.refresh();
        }
    }

    public void refresh() {
        if (!ListenerUtil.mutListener.listen(55479)) {
            if (ConfigUtils.getAppTheme(context) != ConfigUtils.THEME_LIGHT) {
                if (!ListenerUtil.mutListener.listen(55478)) {
                    this.regularColor = context.getResources().getColor(R.color.dark_text_color_secondary);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55477)) {
                    this.regularColor = context.getResources().getColor(R.color.text_color_secondary);
                }
            }
        }
    }

    public void setStateDrawable(AbstractMessageModel messageModel, @Nullable ImageView imageView, boolean useInverseColors) {
        if (!ListenerUtil.mutListener.listen(55480)) {
            if (imageView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(55481)) {
            // set to invisible
            imageView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(55491)) {
            if (MessageUtil.showStatusIcon(messageModel)) {
                MessageState state = messageModel.getState();
                Integer resId = this.messageStateBitmapResourceIds.get(state);
                if (!ListenerUtil.mutListener.listen(55490)) {
                    if ((ListenerUtil.mutListener.listen(55482) ? (resId != null || ViewUtil.showAndSet(imageView, resId)) : (resId != null && ViewUtil.showAndSet(imageView, resId)))) {
                        if (!ListenerUtil.mutListener.listen(55483)) {
                            imageView.setContentDescription(context.getString(this.messageStateDescriptionMap.get(state)));
                        }
                        if (!ListenerUtil.mutListener.listen(55489)) {
                            if (state == MessageState.SENDFAILED) {
                                if (!ListenerUtil.mutListener.listen(55488)) {
                                    imageView.setColorFilter(this.warningColor);
                                }
                            } else if (state == MessageState.USERACK) {
                                if (!ListenerUtil.mutListener.listen(55487)) {
                                    imageView.setColorFilter(this.ackColor);
                                }
                            } else if (state == MessageState.USERDEC) {
                                if (!ListenerUtil.mutListener.listen(55486)) {
                                    imageView.setColorFilter(this.decColor);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(55485)) {
                                    if (useInverseColors) {
                                        if (!ListenerUtil.mutListener.listen(55484)) {
                                            imageView.setColorFilter(this.regularColor);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
