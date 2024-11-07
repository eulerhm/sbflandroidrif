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
package com.health.openscale.gui.measurement;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UserMeasurementView extends MeasurementView {

    // Don't change key value, it may be stored persistent in preferences
    public static final String KEY = "user";

    private OpenScale openScale = OpenScale.getInstance();

    private int userId;

    public UserMeasurementView(Context context) {
        super(context, R.string.label_user_name, R.drawable.ic_user);
        if (!ListenerUtil.mutListener.listen(7932)) {
            userId = -1;
        }
    }

    @Override
    public String getKey() {
        return KEY;
    }

    private void setValue(int newUserId, boolean callListener) {
        if (!ListenerUtil.mutListener.listen(7946)) {
            if ((ListenerUtil.mutListener.listen(7937) ? (newUserId >= -1) : (ListenerUtil.mutListener.listen(7936) ? (newUserId <= -1) : (ListenerUtil.mutListener.listen(7935) ? (newUserId > -1) : (ListenerUtil.mutListener.listen(7934) ? (newUserId < -1) : (ListenerUtil.mutListener.listen(7933) ? (newUserId != -1) : (newUserId == -1))))))) {
                if (!ListenerUtil.mutListener.listen(7945)) {
                    setValueView(openScale.getSelectedScaleUser().getUserName(), callListener);
                }
            } else if ((ListenerUtil.mutListener.listen(7942) ? (userId >= newUserId) : (ListenerUtil.mutListener.listen(7941) ? (userId <= newUserId) : (ListenerUtil.mutListener.listen(7940) ? (userId > newUserId) : (ListenerUtil.mutListener.listen(7939) ? (userId < newUserId) : (ListenerUtil.mutListener.listen(7938) ? (userId == newUserId) : (userId != newUserId))))))) {
                if (!ListenerUtil.mutListener.listen(7943)) {
                    userId = newUserId;
                }
                if (!ListenerUtil.mutListener.listen(7944)) {
                    setValueView(openScale.getScaleUser(userId).getUserName(), callListener);
                }
            }
        }
    }

    @Override
    public void loadFrom(ScaleMeasurement measurement, ScaleMeasurement previousMeasurement) {
        if (!ListenerUtil.mutListener.listen(7947)) {
            setValue(measurement.getUserId(), false);
        }
    }

    @Override
    public void saveTo(ScaleMeasurement measurement) {
        if (!ListenerUtil.mutListener.listen(7948)) {
            measurement.setUserId(userId);
        }
    }

    @Override
    public void clearIn(ScaleMeasurement measurement) {
    }

    @Override
    public void restoreState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(7949)) {
            setValue(state.getInt(getKey()), true);
        }
    }

    @Override
    public void saveState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(7950)) {
            state.putInt(getKey(), userId);
        }
    }

    @Override
    public String getValueAsString(boolean withUnit) {
        return openScale.getScaleUser(userId).getUserName();
    }

    @Override
    protected View getInputView() {
        Spinner spinScaleUer = new Spinner(getContext());
        ArrayAdapter<String> spinScaleUserAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        if (!ListenerUtil.mutListener.listen(7951)) {
            spinScaleUer.setAdapter(spinScaleUserAdapter);
        }
        int spinPos = 0;
        if (!ListenerUtil.mutListener.listen(7959)) {
            {
                long _loopCounter93 = 0;
                for (ScaleUser scaleUser : openScale.getScaleUserList()) {
                    ListenerUtil.loopListener.listen("_loopCounter93", ++_loopCounter93);
                    if (!ListenerUtil.mutListener.listen(7952)) {
                        spinScaleUserAdapter.add(scaleUser.getUserName());
                    }
                    if (!ListenerUtil.mutListener.listen(7958)) {
                        if (scaleUser.getId() == userId) {
                            if (!ListenerUtil.mutListener.listen(7957)) {
                                spinPos = (ListenerUtil.mutListener.listen(7956) ? (spinScaleUserAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(7955) ? (spinScaleUserAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(7954) ? (spinScaleUserAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(7953) ? (spinScaleUserAdapter.getCount() + 1) : (spinScaleUserAdapter.getCount() - 1)))));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7960)) {
            spinScaleUer.setSelection(spinPos);
        }
        return spinScaleUer;
    }

    @Override
    protected boolean validateAndSetInput(View view) {
        Spinner spinScaleUser = (Spinner) view;
        int pos = spinScaleUser.getSelectedItemPosition();
        if (!ListenerUtil.mutListener.listen(7961)) {
            setValue(openScale.getScaleUserList().get(pos).getId(), true);
        }
        return true;
    }
}
