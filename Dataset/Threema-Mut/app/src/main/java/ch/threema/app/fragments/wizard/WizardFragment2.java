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
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import ch.threema.app.R;
import ch.threema.app.utils.EditTextUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WizardFragment2 extends WizardFragment {

    private EditText nicknameText;

    public static final int PAGE_ID = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
        if (!ListenerUtil.mutListener.listen(24031)) {
            // inflate content layout
            contentViewStub.setLayoutResource(R.layout.fragment_wizard2);
        }
        if (!ListenerUtil.mutListener.listen(24032)) {
            contentViewStub.inflate();
        }
        if (!ListenerUtil.mutListener.listen(24033)) {
            nicknameText = rootView.findViewById(R.id.wizard_edit1);
        }
        if (!ListenerUtil.mutListener.listen(24039)) {
            if (callback.isReadOnlyProfile()) {
                if (!ListenerUtil.mutListener.listen(24037)) {
                    nicknameText.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(24038)) {
                    rootView.findViewById(R.id.disabled_by_policy).setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24036)) {
                    nicknameText.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (!ListenerUtil.mutListener.listen(24035)) {
                                if (getActivity().getCurrentFocus() == nicknameText) {
                                    if (!ListenerUtil.mutListener.listen(24034)) {
                                        ((OnSettingsChangedListener) getActivity()).onNicknameSet(s.toString());
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        return rootView;
    }

    @Override
    protected int getAdditionalInfoText() {
        return R.string.new_wizard_info_nickname;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24040)) {
            super.onCreate(savedInstanceState);
        }
    }

    public interface OnSettingsChangedListener {

        void onNicknameSet(String nickname);
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(24041)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(24042)) {
            new Handler().postDelayed(() -> RuntimeUtil.runOnUiThread(this::initValues), 50);
        }
        if (!ListenerUtil.mutListener.listen(24045)) {
            if (this.nicknameText != null) {
                if (!ListenerUtil.mutListener.listen(24043)) {
                    this.nicknameText.requestFocus();
                }
                if (!ListenerUtil.mutListener.listen(24044)) {
                    EditTextUtil.showSoftKeyboard(this.nicknameText);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(24048)) {
            if (this.nicknameText != null) {
                if (!ListenerUtil.mutListener.listen(24046)) {
                    this.nicknameText.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(24047)) {
                    EditTextUtil.hideSoftKeyboard(this.nicknameText);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24049)) {
            super.onPause();
        }
    }

    private void initValues() {
        if (!ListenerUtil.mutListener.listen(24053)) {
            if (isResumed()) {
                WizardFragment5.SettingsInterface callback = (WizardFragment5.SettingsInterface) getActivity();
                String nickname = callback.getNickname();
                if (!ListenerUtil.mutListener.listen(24050)) {
                    nicknameText.setText(nickname);
                }
                if (!ListenerUtil.mutListener.listen(24052)) {
                    if (!TestUtil.empty(nickname)) {
                        if (!ListenerUtil.mutListener.listen(24051)) {
                            nicknameText.setSelection(nickname.length());
                        }
                    }
                }
            }
        }
    }
}
