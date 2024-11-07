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
package ch.threema.app.fragments.wizard;

import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import ch.threema.app.R;
import ch.threema.app.activities.wizard.WizardBaseActivity;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.SynchronizeContactsUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardFragment4 extends WizardFragment {

    private static final boolean defaultSwitchValue = WizardBaseActivity.DEFAULT_SYNC_CONTACTS;

    private SwitchCompat syncContactsSwitch;

    public static final int PAGE_ID = 4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
        if (!ListenerUtil.mutListener.listen(24214)) {
            // inflate content layout
            contentViewStub.setLayoutResource(R.layout.fragment_wizard4);
        }
        if (!ListenerUtil.mutListener.listen(24215)) {
            contentViewStub.inflate();
        }
        if (!ListenerUtil.mutListener.listen(24216)) {
            syncContactsSwitch = rootView.findViewById(R.id.wizard_switch_sync_contacts);
        }
        if (!ListenerUtil.mutListener.listen(24228)) {
            if ((ListenerUtil.mutListener.listen(24217) ? (SynchronizeContactsUtil.isRestrictedProfile(getActivity()) || !ConfigUtils.isWorkRestricted()) : (SynchronizeContactsUtil.isRestrictedProfile(getActivity()) && !ConfigUtils.isWorkRestricted()))) {
                if (!ListenerUtil.mutListener.listen(24225)) {
                    // restricted user profiles cannot add accounts
                    syncContactsSwitch.setChecked(false);
                }
                if (!ListenerUtil.mutListener.listen(24226)) {
                    syncContactsSwitch.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(24227)) {
                    ((OnSettingsChangedListener) getActivity()).onSyncContactsSet(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24224)) {
                    if (callback.isReadOnlyProfile()) {
                        if (!ListenerUtil.mutListener.listen(24222)) {
                            syncContactsSwitch.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(24223)) {
                            rootView.findViewById(R.id.disabled_by_policy).setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24219)) {
                            syncContactsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (!ListenerUtil.mutListener.listen(24218)) {
                                        ((OnSettingsChangedListener) getActivity()).onSyncContactsSet(isChecked);
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(24220)) {
                            syncContactsSwitch.setChecked(defaultSwitchValue);
                        }
                        if (!ListenerUtil.mutListener.listen(24221)) {
                            ((OnSettingsChangedListener) getActivity()).onSyncContactsSet(defaultSwitchValue);
                        }
                    }
                }
            }
        }
        return rootView;
    }

    @Override
    protected int getAdditionalInfoText() {
        return R.string.new_wizard_info_sync_contacts;
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(24229)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(24230)) {
            initValues();
        }
    }

    void initValues() {
        if (!ListenerUtil.mutListener.listen(24233)) {
            if ((ListenerUtil.mutListener.listen(24231) ? (isResumed() || ConfigUtils.isWorkRestricted()) : (isResumed() && ConfigUtils.isWorkRestricted()))) {
                WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
                if (!ListenerUtil.mutListener.listen(24232)) {
                    syncContactsSwitch.setChecked(callback.getSyncContacts());
                }
            }
        }
    }

    public interface OnSettingsChangedListener {

        void onSyncContactsSet(boolean enabled);
    }
}
