/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.globalsearch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.chip.Chip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.ui.ThreemaSearchView;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_DRAGGING;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_SETTLING;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GlobalSearchActivity extends ThreemaToolbarActivity implements ThreemaSearchView.OnQueryTextListener {

    private static final Logger logger = LoggerFactory.getLogger(GlobalSearchActivity.class);

    private static final int QUERY_MIN_LENGTH = 2;

    private static final long QUERY_TIMEOUT_MS = 500;

    public static final int FILTER_CHATS = 0x1;

    public static final int FILTER_GROUPS = 0x2;

    public static final int FILTER_INCLUDE_ARCHIVED = 0x4;

    private GlobalSearchAdapter chatsAdapter;

    private GlobalSearchViewModel chatsViewModel;

    private TextView emptyTextView;

    private ProgressBar progressBar;

    private DeadlineListService hiddenChatsListService;

    private ContactService contactService;

    private GroupService groupService;

    private int filterFlags = FILTER_CHATS | FILTER_GROUPS | FILTER_INCLUDE_ARCHIVED;

    private String queryText;

    private final Handler queryHandler = new Handler();

    private final Runnable queryTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(28195)) {
                chatsViewModel.onQueryChanged(queryText, filterFlags);
            }
            if (!ListenerUtil.mutListener.listen(28196)) {
                chatsAdapter.onQueryChanged(queryText);
            }
        }
    };

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Do something
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onQueryTextChange(String newText) {
        if (!ListenerUtil.mutListener.listen(28197)) {
            queryText = newText;
        }
        if (!ListenerUtil.mutListener.listen(28210)) {
            if ((ListenerUtil.mutListener.listen(28198) ? (chatsViewModel != null || chatsAdapter != null) : (chatsViewModel != null && chatsAdapter != null))) {
                if (!ListenerUtil.mutListener.listen(28199)) {
                    queryHandler.removeCallbacksAndMessages(null);
                }
                if (!ListenerUtil.mutListener.listen(28209)) {
                    if ((ListenerUtil.mutListener.listen(28205) ? (queryText != null || (ListenerUtil.mutListener.listen(28204) ? (queryText.length() <= QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28203) ? (queryText.length() > QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28202) ? (queryText.length() < QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28201) ? (queryText.length() != QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28200) ? (queryText.length() == QUERY_MIN_LENGTH) : (queryText.length() >= QUERY_MIN_LENGTH))))))) : (queryText != null && (ListenerUtil.mutListener.listen(28204) ? (queryText.length() <= QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28203) ? (queryText.length() > QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28202) ? (queryText.length() < QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28201) ? (queryText.length() != QUERY_MIN_LENGTH) : (ListenerUtil.mutListener.listen(28200) ? (queryText.length() == QUERY_MIN_LENGTH) : (queryText.length() >= QUERY_MIN_LENGTH))))))))) {
                        if (!ListenerUtil.mutListener.listen(28208)) {
                            queryHandler.postDelayed(queryTask, QUERY_TIMEOUT_MS);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(28206)) {
                            chatsViewModel.onQueryChanged(null, filterFlags);
                        }
                        if (!ListenerUtil.mutListener.listen(28207)) {
                            chatsAdapter.onQueryChanged(null);
                        }
                    }
                }
            }
        }
        return true;
    }

    public int getLayoutResource() {
        return R.layout.activity_global_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28211)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(28214)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(28215)) {
                groupService = serviceManager.getGroupService();
            }
            if (!ListenerUtil.mutListener.listen(28216)) {
                hiddenChatsListService = serviceManager.getHiddenChatsListService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(28212)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(28213)) {
                finish();
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28217)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        ;
        ConstraintLayout bottomSheetLayout = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        if (!ListenerUtil.mutListener.listen(28236)) {
            bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (!ListenerUtil.mutListener.listen(28235)) {
                        switch(newState) {
                            case STATE_HIDDEN:
                                if (!ListenerUtil.mutListener.listen(28218)) {
                                    finish();
                                }
                                break;
                            case STATE_EXPANDED:
                                if (!ListenerUtil.mutListener.listen(28219)) {
                                    findViewById(R.id.drag_handle).setVisibility(View.INVISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(28226)) {
                                    if ((ListenerUtil.mutListener.listen(28224) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28223) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28222) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28221) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28220) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                                        if (!ListenerUtil.mutListener.listen(28225)) {
                                            getWindow().setStatusBarColor(ConfigUtils.getColorFromAttribute(GlobalSearchActivity.this, R.attr.attach_status_bar_color_expanded));
                                        }
                                    }
                                }
                                break;
                            case STATE_SETTLING:
                                if (!ListenerUtil.mutListener.listen(28227)) {
                                    findViewById(R.id.drag_handle).setVisibility(View.VISIBLE);
                                }
                                break;
                            case STATE_DRAGGING:
                                if (!ListenerUtil.mutListener.listen(28234)) {
                                    if ((ListenerUtil.mutListener.listen(28232) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28231) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28230) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28229) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28228) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                                        if (!ListenerUtil.mutListener.listen(28233)) {
                                            getWindow().setStatusBarColor(ConfigUtils.getColorFromAttribute(GlobalSearchActivity.this, R.attr.attach_status_bar_color_collapsed));
                                        }
                                    }
                                }
                            default:
                                break;
                        }
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28238)) {
            findViewById(R.id.parent_layout).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(28237)) {
                        bottomSheetBehavior.setState(STATE_HIDDEN);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28245)) {
            if ((ListenerUtil.mutListener.listen(28243) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28242) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28241) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28240) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(28239) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(28244)) {
                    getWindow().setStatusBarColor(ConfigUtils.getColorFromAttribute(GlobalSearchActivity.this, R.attr.attach_status_bar_color_collapsed));
                }
            }
        }
        ThreemaSearchView searchView = findViewById(R.id.search);
        if (!ListenerUtil.mutListener.listen(28246)) {
            searchView.setOnQueryTextListener(this);
        }
        if (!ListenerUtil.mutListener.listen(28247)) {
            emptyTextView = findViewById(R.id.empty_text);
        }
        if (!ListenerUtil.mutListener.listen(28248)) {
            progressBar = findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(28249)) {
            chatsAdapter = new GlobalSearchAdapter(this);
        }
        if (!ListenerUtil.mutListener.listen(28250)) {
            chatsAdapter.setOnClickItemListener(this::showMessage);
        }
        if (!ListenerUtil.mutListener.listen(28251)) {
            setupChip(R.id.chats, FILTER_CHATS);
        }
        if (!ListenerUtil.mutListener.listen(28252)) {
            setupChip(R.id.groups, FILTER_GROUPS);
        }
        if (!ListenerUtil.mutListener.listen(28253)) {
            setupChip(R.id.archived, FILTER_INCLUDE_ARCHIVED);
        }
        RecyclerView chatsRecyclerView = this.findViewById(R.id.recycler_chats);
        if (!ListenerUtil.mutListener.listen(28254)) {
            chatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(28255)) {
            chatsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        if (!ListenerUtil.mutListener.listen(28256)) {
            chatsRecyclerView.setAdapter(chatsAdapter);
        }
        if (!ListenerUtil.mutListener.listen(28257)) {
            chatsViewModel = new ViewModelProvider(this).get(GlobalSearchViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(28258)) {
            chatsViewModel.getMessageModels().observe(this, messageModels -> {
                if (messageModels.size() > 0) {
                    messageModels = Functional.filter(messageModels, (IPredicateNonNull<AbstractMessageModel>) messageModel -> {
                        if (messageModel instanceof GroupMessageModel) {
                            if (((GroupMessageModel) messageModel).getGroupId() > 0) {
                                return !hiddenChatsListService.has(groupService.getUniqueIdString(((GroupMessageModel) messageModel).getGroupId()));
                            }
                        } else {
                            if (messageModel.getIdentity() != null) {
                                return !hiddenChatsListService.has(contactService.getUniqueIdString(messageModel.getIdentity()));
                            }
                        }
                        return true;
                    });
                }
                if (messageModels.size() == 0) {
                    if (queryText != null && queryText.length() >= QUERY_MIN_LENGTH) {
                        emptyTextView.setText(R.string.search_no_matches);
                    } else {
                        emptyTextView.setText(R.string.global_search_empty_view_text);
                    }
                    emptyTextView.setVisibility(View.VISIBLE);
                } else {
                    emptyTextView.setVisibility(View.GONE);
                }
                chatsAdapter.setMessageModels(messageModels);
            });
        }
        if (!ListenerUtil.mutListener.listen(28259)) {
            chatsViewModel.getIsLoading().observe(this, isLoading -> {
                if (isLoading != null) {
                    showProgressBar(isLoading);
                }
            });
        }
        return true;
    }

    private void setupChip(@IdRes int id, int flag) {
        // https://github.com/material-components/material-components-android/issues/1419
        Chip chip = findViewById(id);
        if (!ListenerUtil.mutListener.listen(28260)) {
            chip.setChecked(true);
        }
        if (!ListenerUtil.mutListener.listen(28261)) {
            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    filterFlags |= flag;
                } else {
                    filterFlags &= ~flag;
                }
                chatsViewModel.onQueryChanged(queryText, filterFlags);
            });
        }
    }

    private void showMessage(AbstractMessageModel messageModel, View view) {
        if (!ListenerUtil.mutListener.listen(28262)) {
            if (messageModel == null) {
                return;
            }
        }
        Intent intent = new Intent(this, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(28266)) {
            if (messageModel instanceof GroupMessageModel) {
                if (!ListenerUtil.mutListener.listen(28265)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, ((GroupMessageModel) messageModel).getGroupId());
                }
            } else if (messageModel instanceof DistributionListMessageModel) {
                if (!ListenerUtil.mutListener.listen(28264)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, ((DistributionListMessageModel) messageModel).getDistributionListId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28263)) {
                    intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, messageModel.getIdentity());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28267)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_API_MESSAGE_ID, messageModel.getApiMessageId());
        }
        if (!ListenerUtil.mutListener.listen(28268)) {
            intent.putExtra(ComposeMessageFragment.EXTRA_SEARCH_QUERY, queryText);
        }
        if (!ListenerUtil.mutListener.listen(28269)) {
            AnimationUtil.startActivityForResult(this, view, intent, ThreemaActivity.ACTIVITY_ID_COMPOSE_MESSAGE);
        }
        if (!ListenerUtil.mutListener.listen(28270)) {
            finish();
        }
    }

    @UiThread
    private synchronized void showProgressBar(boolean show) {
        if (!ListenerUtil.mutListener.listen(28275)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(28273)) {
                    logger.debug("show progress");
                }
                if (!ListenerUtil.mutListener.listen(28274)) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28271)) {
                    logger.debug("hide progress");
                }
                if (!ListenerUtil.mutListener.listen(28272)) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }
}
