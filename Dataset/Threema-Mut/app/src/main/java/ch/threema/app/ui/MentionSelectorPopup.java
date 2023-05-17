/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import java.util.Collections;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.adapters.MentionSelectorAdapter;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MentionSelectorPopup extends PopupWindow implements MentionSelectorAdapter.OnClickListener {

    private Context context;

    private LinearLayout popupLayout;

    private MentionSelectorAdapter mentionAdapter;

    private GroupService groupService;

    private ContactService contactService;

    private UserService userService;

    private PreferenceService preferenceService;

    private String filterText;

    private int filterStart;

    private GroupModel groupModel;

    private RecyclerView recyclerView;

    private ContactModel allContactModel;

    private MentionSelectorListener mentionSelectorListener;

    private ComposeEditText editText;

    private int dividersHeight, viewableSpaceHeight;

    private int popupY, popupX;

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!ListenerUtil.mutListener.listen(45981)) {
                if (TextUtils.isEmpty(s)) {
                    if (!ListenerUtil.mutListener.listen(45980)) {
                        editText.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(45979)) {
                                    dismiss();
                                }
                            }
                        });
                    }
                } else if (!s.toString().equals(filterText)) {
                    String filterTextAfterAtChar = null;
                    int spacePosition = -1;
                    try {
                        if (!ListenerUtil.mutListener.listen(45965)) {
                            filterTextAfterAtChar = s.toString().substring(filterStart);
                        }
                        if (!ListenerUtil.mutListener.listen(45967)) {
                            if (!TestUtil.empty(filterTextAfterAtChar)) {
                                if (!ListenerUtil.mutListener.listen(45966)) {
                                    spacePosition = filterTextAfterAtChar.indexOf(" ");
                                }
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                    }
                    if (!ListenerUtil.mutListener.listen(45976)) {
                        if ((ListenerUtil.mutListener.listen(45972) ? (spacePosition >= -1) : (ListenerUtil.mutListener.listen(45971) ? (spacePosition <= -1) : (ListenerUtil.mutListener.listen(45970) ? (spacePosition > -1) : (ListenerUtil.mutListener.listen(45969) ? (spacePosition < -1) : (ListenerUtil.mutListener.listen(45968) ? (spacePosition == -1) : (spacePosition != -1))))))) {
                            if (!ListenerUtil.mutListener.listen(45974)) {
                                filterText = s.toString().substring(0, filterStart + spacePosition);
                            }
                            if (!ListenerUtil.mutListener.listen(45975)) {
                                editText.setSelection(filterStart + spacePosition);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(45973)) {
                                filterText = s.toString();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(45977)) {
                        updateList(false);
                    }
                    if (!ListenerUtil.mutListener.listen(45978)) {
                        updateRecyclerViewDimensions();
                    }
                }
            }
        }
    };

    public MentionSelectorPopup(final Context context, MentionSelectorListener mentionSelectorListener, GroupService groupService, ContactService contactService, UserService userService, PreferenceService preferenceService, GroupModel groupModel) {
        super(context);
        if (!ListenerUtil.mutListener.listen(45982)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(45983)) {
            this.groupService = groupService;
        }
        if (!ListenerUtil.mutListener.listen(45984)) {
            this.contactService = contactService;
        }
        if (!ListenerUtil.mutListener.listen(45985)) {
            this.userService = userService;
        }
        if (!ListenerUtil.mutListener.listen(45986)) {
            this.preferenceService = preferenceService;
        }
        if (!ListenerUtil.mutListener.listen(45987)) {
            this.groupModel = groupModel;
        }
        if (!ListenerUtil.mutListener.listen(45988)) {
            this.mentionSelectorListener = mentionSelectorListener;
        }
        if (!ListenerUtil.mutListener.listen(45989)) {
            this.allContactModel = new ContactModel(ContactService.ALL_USERS_PLACEHOLDER_ID, new byte[] {});
        }
        if (!ListenerUtil.mutListener.listen(45990)) {
            this.allContactModel.setName(context.getString(R.string.all), "");
        }
        if (!ListenerUtil.mutListener.listen(45991)) {
            this.allContactModel.setState(ContactModel.State.ACTIVE);
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (!ListenerUtil.mutListener.listen(45992)) {
            this.popupLayout = (LinearLayout) layoutInflater.inflate(R.layout.popup_mention_selector, null, false);
        }
        if (!ListenerUtil.mutListener.listen(45993)) {
            setContentView(popupLayout);
        }
        if (!ListenerUtil.mutListener.listen(45994)) {
            setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        }
        if (!ListenerUtil.mutListener.listen(45995)) {
            setAnimationStyle(0);
        }
        if (!ListenerUtil.mutListener.listen(45996)) {
            setFocusable(false);
        }
        if (!ListenerUtil.mutListener.listen(45997)) {
            setTouchable(true);
        }
        if (!ListenerUtil.mutListener.listen(45998)) {
            setOutsideTouchable(false);
        }
        if (!ListenerUtil.mutListener.listen(45999)) {
            setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        if (!ListenerUtil.mutListener.listen(46000)) {
            this.recyclerView = this.popupLayout.findViewById(R.id.group_members_list);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        if (!ListenerUtil.mutListener.listen(46001)) {
            linearLayoutManager.setStackFromEnd(true);
        }
        if (!ListenerUtil.mutListener.listen(46002)) {
            this.recyclerView.setLayoutManager(linearLayoutManager);
        }
        if (!ListenerUtil.mutListener.listen(46003)) {
            this.recyclerView.setItemAnimator(null);
        }
        if (!ListenerUtil.mutListener.listen(46004)) {
            this.filterText = "";
        }
        if (!ListenerUtil.mutListener.listen(46005)) {
            this.filterStart = 0;
        }
        MentionSelectorAdapter adapter = updateList(true);
        if (!ListenerUtil.mutListener.listen(46007)) {
            if (adapter != null) {
                if (!ListenerUtil.mutListener.listen(46006)) {
                    this.recyclerView.setAdapter(adapter);
                }
            }
        }
    }

    public void show(Activity activity, final ComposeEditText editText, final int originXOffset) {
        if (!ListenerUtil.mutListener.listen(46009)) {
            if (this.mentionAdapter == null) {
                if (!ListenerUtil.mutListener.listen(46008)) {
                    dismiss();
                }
                return;
            }
        }
        int[] originLocation = { 0, 0 };
        if (!ListenerUtil.mutListener.listen(46010)) {
            this.editText = editText;
        }
        if (!ListenerUtil.mutListener.listen(46011)) {
            editText.setLocked(true);
        }
        if (!ListenerUtil.mutListener.listen(46012)) {
            editText.getLocationInWindow(originLocation);
        }
        if (!ListenerUtil.mutListener.listen(46013)) {
            editText.addTextChangedListener(textWatcher);
        }
        if (!ListenerUtil.mutListener.listen(46014)) {
            this.filterStart = editText.getSelectionStart();
        }
        int screenHeight = activity.getWindowManager().getDefaultDisplay().getHeight();
        if (!ListenerUtil.mutListener.listen(46015)) {
            this.popupX = originLocation[0];
        }
        if (!ListenerUtil.mutListener.listen(46020)) {
            this.popupY = (ListenerUtil.mutListener.listen(46019) ? (screenHeight % originLocation[1]) : (ListenerUtil.mutListener.listen(46018) ? (screenHeight / originLocation[1]) : (ListenerUtil.mutListener.listen(46017) ? (screenHeight * originLocation[1]) : (ListenerUtil.mutListener.listen(46016) ? (screenHeight + originLocation[1]) : (screenHeight - originLocation[1]))))) + ConfigUtils.getNavigationBarHeight(activity);
        }
        if (!ListenerUtil.mutListener.listen(46029)) {
            this.viewableSpaceHeight = (ListenerUtil.mutListener.listen(46028) ? ((ListenerUtil.mutListener.listen(46024) ? (originLocation[1] % ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46023) ? (originLocation[1] / ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46022) ? (originLocation[1] * ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46021) ? (originLocation[1] + ConfigUtils.getStatusBarHeight(context)) : (originLocation[1] - ConfigUtils.getStatusBarHeight(context)))))) % ConfigUtils.getActionBarSize(context)) : (ListenerUtil.mutListener.listen(46027) ? ((ListenerUtil.mutListener.listen(46024) ? (originLocation[1] % ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46023) ? (originLocation[1] / ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46022) ? (originLocation[1] * ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46021) ? (originLocation[1] + ConfigUtils.getStatusBarHeight(context)) : (originLocation[1] - ConfigUtils.getStatusBarHeight(context)))))) / ConfigUtils.getActionBarSize(context)) : (ListenerUtil.mutListener.listen(46026) ? ((ListenerUtil.mutListener.listen(46024) ? (originLocation[1] % ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46023) ? (originLocation[1] / ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46022) ? (originLocation[1] * ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46021) ? (originLocation[1] + ConfigUtils.getStatusBarHeight(context)) : (originLocation[1] - ConfigUtils.getStatusBarHeight(context)))))) * ConfigUtils.getActionBarSize(context)) : (ListenerUtil.mutListener.listen(46025) ? ((ListenerUtil.mutListener.listen(46024) ? (originLocation[1] % ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46023) ? (originLocation[1] / ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46022) ? (originLocation[1] * ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46021) ? (originLocation[1] + ConfigUtils.getStatusBarHeight(context)) : (originLocation[1] - ConfigUtils.getStatusBarHeight(context)))))) + ConfigUtils.getActionBarSize(context)) : ((ListenerUtil.mutListener.listen(46024) ? (originLocation[1] % ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46023) ? (originLocation[1] / ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46022) ? (originLocation[1] * ConfigUtils.getStatusBarHeight(context)) : (ListenerUtil.mutListener.listen(46021) ? (originLocation[1] + ConfigUtils.getStatusBarHeight(context)) : (originLocation[1] - ConfigUtils.getStatusBarHeight(context)))))) - ConfigUtils.getActionBarSize(context))))));
        }
        if (!ListenerUtil.mutListener.listen(46034)) {
            this.dividersHeight = (ListenerUtil.mutListener.listen(46033) ? (2 % context.getResources().getDimensionPixelSize(R.dimen.list_divider_height)) : (ListenerUtil.mutListener.listen(46032) ? (2 / context.getResources().getDimensionPixelSize(R.dimen.list_divider_height)) : (ListenerUtil.mutListener.listen(46031) ? (2 - context.getResources().getDimensionPixelSize(R.dimen.list_divider_height)) : (ListenerUtil.mutListener.listen(46030) ? (2 + context.getResources().getDimensionPixelSize(R.dimen.list_divider_height)) : (2 * context.getResources().getDimensionPixelSize(R.dimen.list_divider_height))))));
        }
        if (!ListenerUtil.mutListener.listen(46047)) {
            if ((ListenerUtil.mutListener.listen(46039) ? (this.popupX >= originXOffset) : (ListenerUtil.mutListener.listen(46038) ? (this.popupX <= originXOffset) : (ListenerUtil.mutListener.listen(46037) ? (this.popupX < originXOffset) : (ListenerUtil.mutListener.listen(46036) ? (this.popupX != originXOffset) : (ListenerUtil.mutListener.listen(46035) ? (this.popupX == originXOffset) : (this.popupX > originXOffset))))))) {
                if (!ListenerUtil.mutListener.listen(46045)) {
                    this.setWidth((ListenerUtil.mutListener.listen(46044) ? (activity.getWindowManager().getDefaultDisplay().getWidth() % this.popupX) : (ListenerUtil.mutListener.listen(46043) ? (activity.getWindowManager().getDefaultDisplay().getWidth() / this.popupX) : (ListenerUtil.mutListener.listen(46042) ? (activity.getWindowManager().getDefaultDisplay().getWidth() * this.popupX) : (ListenerUtil.mutListener.listen(46041) ? (activity.getWindowManager().getDefaultDisplay().getWidth() + this.popupX) : (activity.getWindowManager().getDefaultDisplay().getWidth() - this.popupX))))) + originXOffset);
                }
                if (!ListenerUtil.mutListener.listen(46046)) {
                    this.popupX -= originXOffset;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(46040)) {
                    this.setWidth(activity.getWindowManager().getDefaultDisplay().getWidth());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46048)) {
            this.setHeight(this.viewableSpaceHeight);
        }
        try {
            if (!ListenerUtil.mutListener.listen(46049)) {
                showAtLocation(editText, Gravity.LEFT | Gravity.BOTTOM, this.popupX, this.popupY);
            }
            if (!ListenerUtil.mutListener.listen(46052)) {
                getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (!ListenerUtil.mutListener.listen(46050)) {
                            getContentView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        if (!ListenerUtil.mutListener.listen(46051)) {
                            AnimationUtil.slideInAnimation(getContentView(), true, 150);
                        }
                    }
                });
            }
        } catch (WindowManager.BadTokenException e) {
        }
    }

    private void updateRecyclerViewDimensions() {
        int maxHeight = (ListenerUtil.mutListener.listen(46056) ? (context.getResources().getDimensionPixelSize(R.dimen.group_detail_list_item_size) % this.mentionAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(46055) ? (context.getResources().getDimensionPixelSize(R.dimen.group_detail_list_item_size) / this.mentionAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(46054) ? (context.getResources().getDimensionPixelSize(R.dimen.group_detail_list_item_size) - this.mentionAdapter.getItemCount()) : (ListenerUtil.mutListener.listen(46053) ? (context.getResources().getDimensionPixelSize(R.dimen.group_detail_list_item_size) + this.mentionAdapter.getItemCount()) : (context.getResources().getDimensionPixelSize(R.dimen.group_detail_list_item_size) * this.mentionAdapter.getItemCount())))));
        if (!ListenerUtil.mutListener.listen(46061)) {
            this.recyclerView.getLayoutParams().height = Math.min(maxHeight, (ListenerUtil.mutListener.listen(46060) ? (viewableSpaceHeight % dividersHeight) : (ListenerUtil.mutListener.listen(46059) ? (viewableSpaceHeight / dividersHeight) : (ListenerUtil.mutListener.listen(46058) ? (viewableSpaceHeight * dividersHeight) : (ListenerUtil.mutListener.listen(46057) ? (viewableSpaceHeight + dividersHeight) : (viewableSpaceHeight - dividersHeight))))));
        }
        if (!ListenerUtil.mutListener.listen(46062)) {
            this.recyclerView.requestLayout();
        }
    }

    private MentionSelectorAdapter updateList(boolean init) {
        List<ContactModel> groupContacts = contactService.getByIdentities(groupService.getGroupIdentities(groupModel));
        if (!ListenerUtil.mutListener.listen(46063)) {
            Collections.sort(groupContacts, (model1, model2) -> ContactUtil.getSafeNameString(model1, preferenceService).compareTo(ContactUtil.getSafeNameString(model2, preferenceService)));
        }
        if (!ListenerUtil.mutListener.listen(46064)) {
            groupContacts.add(allContactModel);
        }
        if (!ListenerUtil.mutListener.listen(46076)) {
            if ((ListenerUtil.mutListener.listen(46074) ? (!init || (ListenerUtil.mutListener.listen(46073) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) >= 0) : (ListenerUtil.mutListener.listen(46072) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) <= 0) : (ListenerUtil.mutListener.listen(46071) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) < 0) : (ListenerUtil.mutListener.listen(46070) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) != 0) : (ListenerUtil.mutListener.listen(46069) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) == 0) : ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) > 0))))))) : (!init && (ListenerUtil.mutListener.listen(46073) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) >= 0) : (ListenerUtil.mutListener.listen(46072) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) <= 0) : (ListenerUtil.mutListener.listen(46071) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) < 0) : (ListenerUtil.mutListener.listen(46070) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) != 0) : (ListenerUtil.mutListener.listen(46069) ? ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) == 0) : ((ListenerUtil.mutListener.listen(46068) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46067) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46066) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46065) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(46075)) {
                    groupContacts = Functional.filter(groupContacts, (IPredicateNonNull<ContactModel>) contactModel -> ContactUtil.getSafeNameString(contactModel, preferenceService).toLowerCase().contains(filterText.substring(filterStart).toLowerCase()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46083)) {
            if ((ListenerUtil.mutListener.listen(46081) ? (groupContacts.size() >= 1) : (ListenerUtil.mutListener.listen(46080) ? (groupContacts.size() <= 1) : (ListenerUtil.mutListener.listen(46079) ? (groupContacts.size() > 1) : (ListenerUtil.mutListener.listen(46078) ? (groupContacts.size() != 1) : (ListenerUtil.mutListener.listen(46077) ? (groupContacts.size() == 1) : (groupContacts.size() < 1))))))) {
                if (!ListenerUtil.mutListener.listen(46082)) {
                    dismiss();
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(46086)) {
            if (this.mentionAdapter == null) {
                if (!ListenerUtil.mutListener.listen(46084)) {
                    this.mentionAdapter = new MentionSelectorAdapter(context, this.userService, this.contactService, this.groupService, this.groupModel);
                }
                if (!ListenerUtil.mutListener.listen(46085)) {
                    this.mentionAdapter.setOnClickListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46088)) {
            if (mentionAdapter != null) {
                if (!ListenerUtil.mutListener.listen(46087)) {
                    this.mentionAdapter.setData(groupContacts);
                }
            }
        }
        return this.mentionAdapter;
    }

    @Override
    public void onItemClick(View v, ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(46109)) {
            if (contactModel != null) {
                String identity = contactModel.getIdentity();
                if (!ListenerUtil.mutListener.listen(46108)) {
                    if (this.mentionSelectorListener != null) {
                        if (!ListenerUtil.mutListener.listen(46089)) {
                            dismiss();
                        }
                        if (!ListenerUtil.mutListener.listen(46107)) {
                            this.mentionSelectorListener.onContactSelected(identity, filterText != null ? (ListenerUtil.mutListener.listen(46097) ? ((ListenerUtil.mutListener.listen(46093) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46092) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46091) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46090) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) % 1) : (ListenerUtil.mutListener.listen(46096) ? ((ListenerUtil.mutListener.listen(46093) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46092) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46091) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46090) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) / 1) : (ListenerUtil.mutListener.listen(46095) ? ((ListenerUtil.mutListener.listen(46093) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46092) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46091) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46090) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) * 1) : (ListenerUtil.mutListener.listen(46094) ? ((ListenerUtil.mutListener.listen(46093) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46092) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46091) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46090) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) - 1) : ((ListenerUtil.mutListener.listen(46093) ? (filterText.length() % filterStart) : (ListenerUtil.mutListener.listen(46092) ? (filterText.length() / filterStart) : (ListenerUtil.mutListener.listen(46091) ? (filterText.length() * filterStart) : (ListenerUtil.mutListener.listen(46090) ? (filterText.length() + filterStart) : (filterText.length() - filterStart))))) + 1))))) : 0, (ListenerUtil.mutListener.listen(46102) ? (filterStart >= 0) : (ListenerUtil.mutListener.listen(46101) ? (filterStart <= 0) : (ListenerUtil.mutListener.listen(46100) ? (filterStart < 0) : (ListenerUtil.mutListener.listen(46099) ? (filterStart != 0) : (ListenerUtil.mutListener.listen(46098) ? (filterStart == 0) : (filterStart > 0)))))) ? (ListenerUtil.mutListener.listen(46106) ? (filterStart % 1) : (ListenerUtil.mutListener.listen(46105) ? (filterStart / 1) : (ListenerUtil.mutListener.listen(46104) ? (filterStart * 1) : (ListenerUtil.mutListener.listen(46103) ? (filterStart + 1) : (filterStart - 1))))) : 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void dismiss() {
        if (!ListenerUtil.mutListener.listen(46112)) {
            if (this.editText != null) {
                if (!ListenerUtil.mutListener.listen(46110)) {
                    this.editText.removeTextChangedListener(textWatcher);
                }
                if (!ListenerUtil.mutListener.listen(46111)) {
                    this.editText.setLocked(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(46113)) {
            super.dismiss();
        }
    }

    public interface MentionSelectorListener {

        void onContactSelected(String identity, int length, int insertPosition);
    }
}
