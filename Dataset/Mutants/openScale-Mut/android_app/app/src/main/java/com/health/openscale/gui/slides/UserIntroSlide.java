/* Copyright (C) 2019  olie.xdev <olie.xdev@googlemail.com>
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.health.openscale.gui.slides;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleUser;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UserIntroSlide extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";

    private int layoutResId;

    private Button btnAddUser;

    private TableLayout tblUsers;

    public static UserIntroSlide newInstance(int layoutResId) {
        UserIntroSlide sampleSlide = new UserIntroSlide();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(8875)) {
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        }
        if (!ListenerUtil.mutListener.listen(8876)) {
            sampleSlide.setArguments(args);
        }
        return sampleSlide;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8877)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(8880)) {
            if ((ListenerUtil.mutListener.listen(8878) ? (getArguments() != null || getArguments().containsKey(ARG_LAYOUT_RES_ID)) : (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)))) {
                if (!ListenerUtil.mutListener.listen(8879)) {
                    layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResId, container, false);
        if (!ListenerUtil.mutListener.listen(8881)) {
            btnAddUser = view.findViewById(R.id.btnAddUser);
        }
        if (!ListenerUtil.mutListener.listen(8882)) {
            tblUsers = view.findViewById(R.id.tblUsers);
        }
        if (!ListenerUtil.mutListener.listen(8883)) {
            btnAddUser.setOnClickListener(new onBtnAddUserClickListener());
        }
        if (!ListenerUtil.mutListener.listen(8884)) {
            updateTableUsers();
        }
        return view;
    }

    private class onBtnAddUserClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), SlideToNavigationAdapter.class);
            if (!ListenerUtil.mutListener.listen(8885)) {
                intent.putExtra(SlideToNavigationAdapter.EXTRA_MODE, SlideToNavigationAdapter.EXTRA_USER_SETTING_MODE);
            }
            if (!ListenerUtil.mutListener.listen(8886)) {
                startActivityForResult(intent, 100);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(8887)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(8888)) {
            updateTableUsers();
        }
    }

    private void updateTableUsers() {
        if (!ListenerUtil.mutListener.listen(8889)) {
            tblUsers.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(8890)) {
            tblUsers.setStretchAllColumns(true);
        }
        List<ScaleUser> scaleUserList = OpenScale.getInstance().getScaleUserList();
        TableRow header = new TableRow(getContext());
        TextView headerUsername = new TextView(getContext());
        if (!ListenerUtil.mutListener.listen(8891)) {
            headerUsername.setText(R.string.label_user_name);
        }
        if (!ListenerUtil.mutListener.listen(8892)) {
            headerUsername.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(8893)) {
            headerUsername.setTypeface(null, Typeface.BOLD);
        }
        if (!ListenerUtil.mutListener.listen(8894)) {
            header.addView(headerUsername);
        }
        TextView headAge = new TextView(getContext());
        if (!ListenerUtil.mutListener.listen(8895)) {
            headAge.setText(R.string.label_age);
        }
        if (!ListenerUtil.mutListener.listen(8896)) {
            headAge.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(8897)) {
            headAge.setTypeface(null, Typeface.BOLD);
        }
        if (!ListenerUtil.mutListener.listen(8898)) {
            header.addView(headAge);
        }
        TextView headerGender = new TextView(getContext());
        if (!ListenerUtil.mutListener.listen(8899)) {
            headerGender.setText(R.string.label_gender);
        }
        if (!ListenerUtil.mutListener.listen(8900)) {
            headerGender.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(8901)) {
            headerGender.setTypeface(null, Typeface.BOLD);
        }
        if (!ListenerUtil.mutListener.listen(8902)) {
            header.addView(headerGender);
        }
        if (!ListenerUtil.mutListener.listen(8903)) {
            tblUsers.addView(header);
        }
        if (!ListenerUtil.mutListener.listen(8922)) {
            if (!scaleUserList.isEmpty()) {
                TableRow row = new TableRow(getContext());
                if (!ListenerUtil.mutListener.listen(8921)) {
                    {
                        long _loopCounter107 = 0;
                        for (ScaleUser scaleUser : scaleUserList) {
                            ListenerUtil.loopListener.listen("_loopCounter107", ++_loopCounter107);
                            if (!ListenerUtil.mutListener.listen(8909)) {
                                row = new TableRow(getContext());
                            }
                            TextView txtUsername = new TextView(getContext());
                            if (!ListenerUtil.mutListener.listen(8910)) {
                                txtUsername.setText(scaleUser.getUserName());
                            }
                            if (!ListenerUtil.mutListener.listen(8911)) {
                                txtUsername.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            if (!ListenerUtil.mutListener.listen(8912)) {
                                row.addView(txtUsername);
                            }
                            TextView txtAge = new TextView(getContext());
                            if (!ListenerUtil.mutListener.listen(8913)) {
                                txtAge.setText(Integer.toString(scaleUser.getAge()));
                            }
                            if (!ListenerUtil.mutListener.listen(8914)) {
                                txtAge.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            if (!ListenerUtil.mutListener.listen(8915)) {
                                row.addView(txtAge);
                            }
                            TextView txtGender = new TextView(getContext());
                            if (!ListenerUtil.mutListener.listen(8916)) {
                                txtGender.setText((scaleUser.getGender().isMale()) ? getString(R.string.label_male) : getString(R.string.label_female));
                            }
                            if (!ListenerUtil.mutListener.listen(8917)) {
                                txtGender.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            if (!ListenerUtil.mutListener.listen(8918)) {
                                row.addView(txtGender);
                            }
                            if (!ListenerUtil.mutListener.listen(8919)) {
                                row.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            if (!ListenerUtil.mutListener.listen(8920)) {
                                tblUsers.addView(row);
                            }
                        }
                    }
                }
            } else {
                TableRow row = new TableRow(getContext());
                TextView txtEmpty = new TextView(getContext());
                if (!ListenerUtil.mutListener.listen(8904)) {
                    txtEmpty.setText("[" + getContext().getString(R.string.label_empty) + "]");
                }
                if (!ListenerUtil.mutListener.listen(8905)) {
                    txtEmpty.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                if (!ListenerUtil.mutListener.listen(8906)) {
                    row.addView(txtEmpty);
                }
                if (!ListenerUtil.mutListener.listen(8907)) {
                    row.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                if (!ListenerUtil.mutListener.listen(8908)) {
                    tblUsers.addView(row);
                }
            }
        }
    }
}
