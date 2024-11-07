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
package ch.threema.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.List;
import androidx.annotation.MainThread;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.TextEntryDialog;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.utils.LogUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListMemberModel;
import ch.threema.storage.models.DistributionListModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListAddActivity extends MemberChooseActivity implements TextEntryDialog.TextEntryDialogClickListener {

    private static final String DIALOG_TAG_ENTER_NAME = "enterName";

    private DistributionListService distributionListService;

    private DistributionListModel distributionListModel;

    private List<ContactModel> selectedContacts;

    private boolean isEdit = false;

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2659)) {
            if (!super.initActivity(savedInstanceState)) {
                return false;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(2661)) {
                this.distributionListService = serviceManager.getDistributionListService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2660)) {
                LogUtil.exception(e, this);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(2662)) {
            initData(savedInstanceState);
        }
        return true;
    }

    @Override
    @MainThread
    protected void initData(final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2665)) {
            if (this.getIntent().hasExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST)) {
                if (!ListenerUtil.mutListener.listen(2663)) {
                    this.distributionListModel = this.distributionListService.getById(this.getIntent().getIntExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, 0));
                }
                if (!ListenerUtil.mutListener.listen(2664)) {
                    this.isEdit = this.distributionListModel != null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2669)) {
            if ((ListenerUtil.mutListener.listen(2666) ? (isEdit || savedInstanceState == null) : (isEdit && savedInstanceState == null))) {
                if (!ListenerUtil.mutListener.listen(2668)) {
                    {
                        long _loopCounter17 = 0;
                        for (final DistributionListMemberModel model : distributionListService.getDistributionListMembers(distributionListModel)) {
                            ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                            if (!ListenerUtil.mutListener.listen(2667)) {
                                preselectedIdentities.add(model.getIdentity());
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2672)) {
            if (isEdit) {
                if (!ListenerUtil.mutListener.listen(2671)) {
                    updateToolbarTitle(R.string.title_edit_distribution_list, R.string.title_select_contacts);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2670)) {
                    updateToolbarTitle(R.string.title_add_distribution_list, R.string.title_select_contacts);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2673)) {
            initList();
        }
    }

    @Override
    protected int getNotice() {
        return 0;
    }

    @Override
    protected boolean getAddNextButton() {
        return true;
    }

    @Override
    protected void menuNext(final List<ContactModel> contacts) {
        if (!ListenerUtil.mutListener.listen(2674)) {
            selectedContacts = contacts;
        }
        if (!ListenerUtil.mutListener.listen(2685)) {
            if ((ListenerUtil.mutListener.listen(2679) ? (selectedContacts.size() >= 0) : (ListenerUtil.mutListener.listen(2678) ? (selectedContacts.size() <= 0) : (ListenerUtil.mutListener.listen(2677) ? (selectedContacts.size() < 0) : (ListenerUtil.mutListener.listen(2676) ? (selectedContacts.size() != 0) : (ListenerUtil.mutListener.listen(2675) ? (selectedContacts.size() == 0) : (selectedContacts.size() > 0))))))) {
                String defaultString = null;
                if (!ListenerUtil.mutListener.listen(2683)) {
                    if ((ListenerUtil.mutListener.listen(2681) ? (this.isEdit || this.distributionListModel != null) : (this.isEdit && this.distributionListModel != null))) {
                        if (!ListenerUtil.mutListener.listen(2682)) {
                            defaultString = this.distributionListModel.getName();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2684)) {
                    TextEntryDialog.newInstance(isEdit ? R.string.title_edit_distribution_list : R.string.title_add_distribution_list, R.string.enter_distribution_list_name, R.string.ok, 0, R.string.cancel, defaultString, 0, TextEntryDialog.INPUT_FILTER_TYPE_NONE, DistributionListModel.DISTRIBUTIONLIST_NAME_MAX_LENGTH_BYTES).show(getSupportFragmentManager(), DIALOG_TAG_ENTER_NAME);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2680)) {
                    Toast.makeText(this, getString(R.string.group_select_at_least_two), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void launchComposeActivity() {
        Intent intent = new Intent(DistributionListAddActivity.this, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(2686)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(2687)) {
            distributionListService.createReceiver(distributionListModel).prepareIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(2688)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(2689)) {
            finish();
        }
    }

    @Override
    public void onYes(String tag, String text) {
        try {
            String[] identities = new String[selectedContacts.size()];
            int pos = 0;
            if (!ListenerUtil.mutListener.listen(2693)) {
                {
                    long _loopCounter18 = 0;
                    for (ContactModel cm : selectedContacts) {
                        ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                        if (!ListenerUtil.mutListener.listen(2692)) {
                            if (cm != null) {
                                if (!ListenerUtil.mutListener.listen(2691)) {
                                    identities[pos++] = cm.getIdentity();
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2704)) {
                if (isEdit) {
                    if (!ListenerUtil.mutListener.listen(2702)) {
                        if ((ListenerUtil.mutListener.listen(2700) ? (pos >= 0) : (ListenerUtil.mutListener.listen(2699) ? (pos <= 0) : (ListenerUtil.mutListener.listen(2698) ? (pos < 0) : (ListenerUtil.mutListener.listen(2697) ? (pos != 0) : (ListenerUtil.mutListener.listen(2696) ? (pos == 0) : (pos > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2701)) {
                                distributionListService.updateDistributionList(distributionListModel, text, identities);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2703)) {
                        RuntimeUtil.runOnUiThread(this::launchComposeActivity);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2694)) {
                        distributionListModel = distributionListService.createDistributionList(text, identities);
                    }
                    if (!ListenerUtil.mutListener.listen(2695)) {
                        RuntimeUtil.runOnUiThread(this::launchComposeActivity);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2690)) {
                LogUtil.exception(e, DistributionListAddActivity.this);
            }
        }
    }

    @Override
    public void onNo(String tag) {
    }

    @Override
    public void onNeutral(String tag) {
    }
}
