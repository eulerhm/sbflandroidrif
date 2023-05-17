/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.RejectedExecutionException;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.AvatarService;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.listitemholder.AvatarListItemHolder;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.ReceiverModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AvatarListItemUtil {

    private static final Logger logger = LoggerFactory.getLogger(AvatarListItemUtil.class);

    public static void loadAvatar(final int position, final ConversationModel conversationModel, final Bitmap defaultImage, final Bitmap defaultGroupImage, final Bitmap defaultDistributionListImage, final ContactService contactService, final GroupService groupService, final DistributionListService distributionListService, AvatarListItemHolder holder) {
        final boolean isWork;
        if (!ListenerUtil.mutListener.listen(44653)) {
            // do nothing
            if (holder.avatarLoadingAsyncTask != null) {
                if (!ListenerUtil.mutListener.listen(44651)) {
                    // cancel async task
                    holder.avatarLoadingAsyncTask.cancel(false);
                }
                if (!ListenerUtil.mutListener.listen(44652)) {
                    holder.avatarLoadingAsyncTask = null;
                }
            }
        }
        final AvatarService avatarService;
        final ReceiverModel avatarObject;
        if (conversationModel.isContactConversation()) {
            avatarService = contactService;
            avatarObject = conversationModel.getContact();
            isWork = contactService.showBadge(conversationModel.getContact());
            if (!ListenerUtil.mutListener.listen(44656)) {
                holder.avatarView.setContentDescription(ThreemaApplication.getAppContext().getString(R.string.edit_type_content_description, ThreemaApplication.getAppContext().getString(R.string.mime_contact), NameUtil.getDisplayNameOrNickname(conversationModel.getContact(), true)));
            }
        } else if (conversationModel.isGroupConversation()) {
            avatarService = groupService;
            avatarObject = conversationModel.getGroup();
            isWork = false;
            if (!ListenerUtil.mutListener.listen(44655)) {
                holder.avatarView.setContentDescription(ThreemaApplication.getAppContext().getString(R.string.edit_type_content_description, ThreemaApplication.getAppContext().getString(R.string.group), NameUtil.getDisplayName(conversationModel.getGroup(), groupService)));
            }
        } else if (conversationModel.isDistributionListConversation()) {
            avatarService = distributionListService;
            avatarObject = conversationModel.getDistributionList();
            isWork = false;
            if (!ListenerUtil.mutListener.listen(44654)) {
                holder.avatarView.setContentDescription(ThreemaApplication.getAppContext().getString(R.string.edit_type_content_description, ThreemaApplication.getAppContext().getString(R.string.distribution_list), NameUtil.getDisplayName(conversationModel.getDistributionList(), distributionListService)));
            }
        } else {
            return;
        }
        if (!ListenerUtil.mutListener.listen(44657)) {
            if (!TestUtil.required(avatarService, avatarObject, holder)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(44659)) {
            // check the cache for existing avatar to avoid async task call
            if (show(holder, avatarService.getCachedAvatar(avatarObject))) {
                if (!ListenerUtil.mutListener.listen(44658)) {
                    holder.avatarView.setBadgeVisible(isWork);
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(44676)) {
                holder.avatarLoadingAsyncTask = new AsyncTask<AvatarListItemHolder, Void, Bitmap>() {

                    private AvatarListItemHolder holder;

                    @Override
                    protected void onCancelled(Bitmap bitmap) {
                        if (!ListenerUtil.mutListener.listen(44663)) {
                            super.onCancelled(bitmap);
                        }
                    }

                    @Override
                    protected Bitmap doInBackground(AvatarListItemHolder... params) {
                        if (!ListenerUtil.mutListener.listen(44664)) {
                            this.holder = params[0];
                        }
                        if (!ListenerUtil.mutListener.listen(44665)) {
                            if (!isCancelled()) {
                                return avatarService.getAvatar(avatarObject, false);
                            }
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Bitmap avatar) {
                        if (!ListenerUtil.mutListener.listen(44675)) {
                            // fix flickering
                            if (!isCancelled()) {
                                if (!ListenerUtil.mutListener.listen(44674)) {
                                    if (position == holder.position) {
                                        if (!ListenerUtil.mutListener.listen(44670)) {
                                            if (avatar == null) {
                                                if (!ListenerUtil.mutListener.listen(44669)) {
                                                    if (conversationModel.isGroupConversation()) {
                                                        if (!ListenerUtil.mutListener.listen(44668)) {
                                                            avatar = defaultGroupImage;
                                                        }
                                                    } else if (conversationModel.isDistributionListConversation()) {
                                                        if (!ListenerUtil.mutListener.listen(44667)) {
                                                            avatar = defaultDistributionListImage;
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(44666)) {
                                                            avatar = defaultImage;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(44671)) {
                                            show(this.holder, avatar);
                                        }
                                        if (!ListenerUtil.mutListener.listen(44672)) {
                                            holder.avatarView.setBadgeVisible(isWork);
                                        }
                                        if (!ListenerUtil.mutListener.listen(44673)) {
                                            holder.avatarLoadingAsyncTask = null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, prepare(position, holder, defaultImage));
            }
        } catch (RejectedExecutionException e) {
            // thread pool is full - load by non thread
            Bitmap avatar = avatarService.getAvatar(avatarObject, false);
            if (!ListenerUtil.mutListener.listen(44662)) {
                if (avatar != null) {
                    if (!ListenerUtil.mutListener.listen(44660)) {
                        show(holder, avatar);
                    }
                    if (!ListenerUtil.mutListener.listen(44661)) {
                        holder.avatarView.setBadgeVisible(isWork);
                    }
                }
            }
        }
    }

    private static <M extends ReceiverModel> void loadAvatarAbstract(final int position, final M model, final Bitmap defaultImage, final AvatarService avatarService, AvatarListItemHolder holder) {
        if (!ListenerUtil.mutListener.listen(44678)) {
            // do nothing
            if ((ListenerUtil.mutListener.listen(44677) ? (!TestUtil.required(model, avatarService, holder) && holder.avatarView == null) : (!TestUtil.required(model, avatarService, holder) || holder.avatarView == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(44680)) {
            if (holder.avatarLoadingAsyncTask != null) {
                if (!ListenerUtil.mutListener.listen(44679)) {
                    holder.avatarLoadingAsyncTask.cancel(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(44683)) {
            if (model instanceof ContactModel) {
                if (!ListenerUtil.mutListener.listen(44682)) {
                    holder.avatarView.setBadgeVisible(((ContactService) avatarService).showBadge((ContactModel) model));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(44681)) {
                    holder.avatarView.setBadgeVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(44684)) {
            // check the cache for existing avatar to avoid async task call
            if (show(holder, avatarService.getCachedAvatar(model))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(44690)) {
            holder.avatarLoadingAsyncTask = new AsyncTask<AvatarListItemHolder, Void, Bitmap>() {

                private AvatarListItemHolder holder;

                @Override
                protected Bitmap doInBackground(AvatarListItemHolder... params) {
                    if (!ListenerUtil.mutListener.listen(44685)) {
                        this.holder = params[0];
                    }
                    return avatarService.getAvatar(model, false);
                }

                @Override
                protected void onPostExecute(Bitmap avatar) {
                    if (!ListenerUtil.mutListener.listen(44689)) {
                        if (position == holder.position) {
                            if (!ListenerUtil.mutListener.listen(44687)) {
                                if (avatar == null) {
                                    if (!ListenerUtil.mutListener.listen(44686)) {
                                        avatar = defaultImage;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(44688)) {
                                show(this.holder, avatar);
                            }
                        }
                    }
                }
            }.execute(prepare(position, holder, defaultImage));
        }
    }

    public static void loadAvatar(final int position, final ContactModel contactModel, final Bitmap defaultImage, final ContactService contactService, AvatarListItemHolder holder) {
        if (!ListenerUtil.mutListener.listen(44691)) {
            loadAvatarAbstract(position, contactModel, defaultImage, contactService, holder);
        }
    }

    public static void loadAvatar(final int position, final GroupModel groupModel, final Bitmap defaultImage, final GroupService groupService, AvatarListItemHolder holder) {
        if (!ListenerUtil.mutListener.listen(44692)) {
            loadAvatarAbstract(position, groupModel, defaultImage, groupService, holder);
        }
    }

    public static void loadAvatar(final int position, final DistributionListModel distributionListModel, final Bitmap defaultImage, final DistributionListService distributionListService, AvatarListItemHolder holder) {
        if (!ListenerUtil.mutListener.listen(44693)) {
            loadAvatarAbstract(position, distributionListModel, defaultImage, distributionListService, holder);
        }
    }

    private static boolean show(final AvatarListItemHolder holder, final Bitmap avatar) {
        if (!ListenerUtil.mutListener.listen(44695)) {
            if ((ListenerUtil.mutListener.listen(44694) ? (avatar == null && avatar.isRecycled()) : (avatar == null || avatar.isRecycled()))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(44696)) {
            holder.avatarView.setImageBitmap(avatar);
        }
        if (!ListenerUtil.mutListener.listen(44697)) {
            holder.avatarView.setVisibility(View.VISIBLE);
        }
        return true;
    }

    private static AvatarListItemHolder prepare(int position, AvatarListItemHolder holder, Bitmap defaultImage) {
        if (!ListenerUtil.mutListener.listen(44698)) {
            holder.position = position;
        }
        if (!ListenerUtil.mutListener.listen(44699)) {
            show(holder, defaultImage);
        }
        return holder;
    }
}
