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
package ch.threema.app.activities.ballot;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import ch.threema.app.R;
import ch.threema.app.utils.ViewUtil;
import ch.threema.storage.models.ballot.BallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotWizardFragment0 extends BallotWizardFragment implements BallotWizardActivity.BallotWizardCallback {

    private EditText editText;

    private TextInputLayout textInputLayout;

    private CheckBox secretCheckbox;

    private CheckBox typeCheckbox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ballot_wizard0, container, false);
        if (!ListenerUtil.mutListener.listen(500)) {
            this.editText = rootView.findViewById(R.id.wizard_edittext);
        }
        if (!ListenerUtil.mutListener.listen(505)) {
            this.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!ListenerUtil.mutListener.listen(504)) {
                        if ((ListenerUtil.mutListener.listen(501) ? (actionId == getResources().getInteger(R.integer.ime_wizard_next) && actionId == EditorInfo.IME_ACTION_DONE) : (actionId == getResources().getInteger(R.integer.ime_wizard_next) || actionId == EditorInfo.IME_ACTION_DONE))) {
                            if (!ListenerUtil.mutListener.listen(503)) {
                                if (getBallotActivity() != null) {
                                    if (!ListenerUtil.mutListener.listen(502)) {
                                        getBallotActivity().nextPage();
                                    }
                                }
                            }
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(516)) {
            this.editText.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(507)) {
                        if (getBallotActivity() != null) {
                            if (!ListenerUtil.mutListener.listen(506)) {
                                getBallotActivity().setBallotTitle(editText.getText().toString());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(515)) {
                        if ((ListenerUtil.mutListener.listen(513) ? (s != null || (ListenerUtil.mutListener.listen(512) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(511) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(510) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(509) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(508) ? (s.length() == 0) : (s.length() > 0))))))) : (s != null && (ListenerUtil.mutListener.listen(512) ? (s.length() >= 0) : (ListenerUtil.mutListener.listen(511) ? (s.length() <= 0) : (ListenerUtil.mutListener.listen(510) ? (s.length() < 0) : (ListenerUtil.mutListener.listen(509) ? (s.length() != 0) : (ListenerUtil.mutListener.listen(508) ? (s.length() == 0) : (s.length() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(514)) {
                                textInputLayout.setError(null);
                            }
                        }
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(517)) {
            this.textInputLayout = rootView.findViewById(R.id.wizard_edittext_layout);
        }
        if (!ListenerUtil.mutListener.listen(518)) {
            this.typeCheckbox = rootView.findViewById(R.id.type);
        }
        if (!ListenerUtil.mutListener.listen(521)) {
            this.typeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!ListenerUtil.mutListener.listen(520)) {
                        if (getBallotActivity() != null) {
                            if (!ListenerUtil.mutListener.listen(519)) {
                                getBallotActivity().setBallotAssessment(isChecked ? BallotModel.Assessment.MULTIPLE_CHOICE : BallotModel.Assessment.SINGLE_CHOICE);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(522)) {
            this.secretCheckbox = rootView.findViewById(R.id.visibility);
        }
        if (!ListenerUtil.mutListener.listen(525)) {
            this.secretCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!ListenerUtil.mutListener.listen(524)) {
                        if (getBallotActivity() != null) {
                            if (!ListenerUtil.mutListener.listen(523)) {
                                getBallotActivity().setBallotType(isChecked ? BallotModel.Type.INTERMEDIATE : BallotModel.Type.RESULT_ON_CLOSE);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(526)) {
            this.updateView();
        }
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(527)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void updateView() {
        if (!ListenerUtil.mutListener.listen(529)) {
            if (getBallotActivity() != null) {
                if (!ListenerUtil.mutListener.listen(528)) {
                    ViewUtil.showAndSet(this.editText, this.getBallotActivity().getBallotTitle());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(532)) {
            if (this.getBallotActivity() != null) {
                if (!ListenerUtil.mutListener.listen(530)) {
                    ViewUtil.showAndSet(this.typeCheckbox, this.getBallotActivity().getBallotAssessment() == BallotModel.Assessment.MULTIPLE_CHOICE);
                }
                if (!ListenerUtil.mutListener.listen(531)) {
                    ViewUtil.showAndSet(this.secretCheckbox, this.getBallotActivity().getBallotType() == BallotModel.Type.INTERMEDIATE);
                }
            }
        }
    }

    @Override
    public void onMissingTitle() {
        if (!ListenerUtil.mutListener.listen(533)) {
            this.textInputLayout.setError(getString(R.string.title_cannot_be_empty));
        }
        if (!ListenerUtil.mutListener.listen(534)) {
            this.editText.setFocusableInTouchMode(true);
        }
        if (!ListenerUtil.mutListener.listen(535)) {
            this.editText.setFocusable(true);
        }
        if (!ListenerUtil.mutListener.listen(536)) {
            this.editText.requestFocus();
        }
    }

    @Override
    public void onPageSelected(int page) {
        if (!ListenerUtil.mutListener.listen(547)) {
            if ((ListenerUtil.mutListener.listen(541) ? (page >= 1) : (ListenerUtil.mutListener.listen(540) ? (page <= 1) : (ListenerUtil.mutListener.listen(539) ? (page > 1) : (ListenerUtil.mutListener.listen(538) ? (page < 1) : (ListenerUtil.mutListener.listen(537) ? (page != 1) : (page == 1))))))) {
                if (!ListenerUtil.mutListener.listen(544)) {
                    this.editText.clearFocus();
                }
                if (!ListenerUtil.mutListener.listen(545)) {
                    this.editText.setFocusableInTouchMode(false);
                }
                if (!ListenerUtil.mutListener.listen(546)) {
                    this.editText.setFocusable(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(542)) {
                    this.editText.setFocusableInTouchMode(true);
                }
                if (!ListenerUtil.mutListener.listen(543)) {
                    this.editText.setFocusable(true);
                }
            }
        }
    }
}
