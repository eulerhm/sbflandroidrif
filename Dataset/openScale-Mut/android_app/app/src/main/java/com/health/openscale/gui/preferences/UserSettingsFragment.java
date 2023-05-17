/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
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
package com.health.openscale.gui.preferences;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UserSettingsFragment extends Fragment {

    public enum USER_SETTING_MODE {

        ADD, EDIT
    }

    private USER_SETTING_MODE mode = USER_SETTING_MODE.ADD;

    private Date birthday = new Date();

    private Date goal_date = new Date();

    private EditText txtUserName;

    private EditText txtBodyHeight;

    private EditText txtBirthday;

    private EditText txtInitialWeight;

    private EditText txtGoalWeight;

    private EditText txtGoalDate;

    private RadioGroup radioScaleUnit;

    private RadioGroup radioGender;

    private CheckBox assistedWeighing;

    private RadioGroup radioMeasurementUnit;

    private Spinner spinnerActivityLevel;

    private Spinner spinnerLeftAmputationLevel;

    private Spinner spinnerRightAmputationLevel;

    private final DateFormat dateFormat = DateFormat.getDateInstance();

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_usersettings, container, false);
        if (!ListenerUtil.mutListener.listen(8567)) {
            context = getContext();
        }
        if (!ListenerUtil.mutListener.listen(8568)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(8571)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(8570)) {
                    mode = UserSettingsFragmentArgs.fromBundle(getArguments()).getMode();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8569)) {
                    mode = USER_SETTING_MODE.ADD;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8572)) {
            txtUserName = root.findViewById(R.id.txtUserName);
        }
        if (!ListenerUtil.mutListener.listen(8573)) {
            txtBodyHeight = root.findViewById(R.id.txtBodyHeight);
        }
        if (!ListenerUtil.mutListener.listen(8574)) {
            radioScaleUnit = root.findViewById(R.id.groupScaleUnit);
        }
        if (!ListenerUtil.mutListener.listen(8575)) {
            radioGender = root.findViewById(R.id.groupGender);
        }
        if (!ListenerUtil.mutListener.listen(8576)) {
            assistedWeighing = root.findViewById(R.id.asisstedWeighing);
        }
        if (!ListenerUtil.mutListener.listen(8577)) {
            radioMeasurementUnit = root.findViewById(R.id.groupMeasureUnit);
        }
        if (!ListenerUtil.mutListener.listen(8578)) {
            spinnerActivityLevel = root.findViewById(R.id.spinnerActivityLevel);
        }
        if (!ListenerUtil.mutListener.listen(8579)) {
            spinnerLeftAmputationLevel = root.findViewById(R.id.spinnerLeftAmputationLevel);
        }
        if (!ListenerUtil.mutListener.listen(8580)) {
            spinnerRightAmputationLevel = root.findViewById(R.id.spinnerRightAmputationLevel);
        }
        if (!ListenerUtil.mutListener.listen(8581)) {
            txtInitialWeight = root.findViewById(R.id.txtInitialWeight);
        }
        if (!ListenerUtil.mutListener.listen(8582)) {
            txtGoalWeight = root.findViewById(R.id.txtGoalWeight);
        }
        if (!ListenerUtil.mutListener.listen(8583)) {
            txtBirthday = root.findViewById(R.id.txtBirthday);
        }
        if (!ListenerUtil.mutListener.listen(8584)) {
            txtGoalDate = root.findViewById(R.id.txtGoalDate);
        }
        if (!ListenerUtil.mutListener.listen(8585)) {
            txtBodyHeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + Converters.MeasureUnit.CM.toString());
        }
        if (!ListenerUtil.mutListener.listen(8586)) {
            txtInitialWeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + Converters.WeightUnit.KG.toString());
        }
        if (!ListenerUtil.mutListener.listen(8587)) {
            txtGoalWeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + Converters.WeightUnit.KG.toString());
        }
        Calendar birthdayCal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(8588)) {
            birthdayCal.setTime(birthday);
        }
        if (!ListenerUtil.mutListener.listen(8589)) {
            birthdayCal.add(Calendar.YEAR, -20);
        }
        if (!ListenerUtil.mutListener.listen(8590)) {
            birthday = birthdayCal.getTime();
        }
        Calendar goalCal = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(8591)) {
            goalCal.setTime(goal_date);
        }
        if (!ListenerUtil.mutListener.listen(8592)) {
            goalCal.add(Calendar.MONTH, 6);
        }
        if (!ListenerUtil.mutListener.listen(8593)) {
            goal_date = goalCal.getTime();
        }
        if (!ListenerUtil.mutListener.listen(8594)) {
            txtBirthday.setText(dateFormat.format(birthday));
        }
        if (!ListenerUtil.mutListener.listen(8595)) {
            txtGoalDate.setText(dateFormat.format(goal_date));
        }
        if (!ListenerUtil.mutListener.listen(8598)) {
            txtBirthday.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    if (!ListenerUtil.mutListener.listen(8596)) {
                        cal.setTime(birthday);
                    }
                    DatePickerDialog datePicker = new DatePickerDialog(context, birthdayPickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                    if (!ListenerUtil.mutListener.listen(8597)) {
                        datePicker.show();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8601)) {
            txtGoalDate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    if (!ListenerUtil.mutListener.listen(8599)) {
                        cal.setTime(goal_date);
                    }
                    DatePickerDialog datePicker = new DatePickerDialog(context, goalDatePickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                    if (!ListenerUtil.mutListener.listen(8600)) {
                        datePicker.show();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8608)) {
            radioScaleUnit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Converters.WeightUnit scale_unit = Converters.WeightUnit.KG;
                    if (!ListenerUtil.mutListener.listen(8605)) {
                        switch(checkedId) {
                            case R.id.btnRadioKG:
                                if (!ListenerUtil.mutListener.listen(8602)) {
                                    scale_unit = Converters.WeightUnit.KG;
                                }
                                break;
                            case R.id.btnRadioLB:
                                if (!ListenerUtil.mutListener.listen(8603)) {
                                    scale_unit = Converters.WeightUnit.LB;
                                }
                                break;
                            case R.id.btnRadioST:
                                if (!ListenerUtil.mutListener.listen(8604)) {
                                    scale_unit = Converters.WeightUnit.ST;
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8606)) {
                        txtInitialWeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + scale_unit.toString());
                    }
                    if (!ListenerUtil.mutListener.listen(8607)) {
                        txtGoalWeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + scale_unit.toString());
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8613)) {
            radioMeasurementUnit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Converters.MeasureUnit measure_unit = Converters.MeasureUnit.CM;
                    if (!ListenerUtil.mutListener.listen(8611)) {
                        switch(radioMeasurementUnit.getCheckedRadioButtonId()) {
                            case R.id.btnRadioCM:
                                if (!ListenerUtil.mutListener.listen(8609)) {
                                    measure_unit = Converters.MeasureUnit.CM;
                                }
                                break;
                            case R.id.btnRadioINCH:
                                if (!ListenerUtil.mutListener.listen(8610)) {
                                    measure_unit = Converters.MeasureUnit.INCH;
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8612)) {
                        txtBodyHeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + measure_unit.toString());
                    }
                }
            });
        }
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8614)) {
            menu.clear();
        }
        if (!ListenerUtil.mutListener.listen(8615)) {
            inflater.inflate(R.menu.userentry_menu, menu);
        }
        if (!ListenerUtil.mutListener.listen(8626)) {
            {
                long _loopCounter104 = 0;
                // Apply a tint to all icons in the toolbar
                for (int i = 0; (ListenerUtil.mutListener.listen(8625) ? (i >= menu.size()) : (ListenerUtil.mutListener.listen(8624) ? (i <= menu.size()) : (ListenerUtil.mutListener.listen(8623) ? (i > menu.size()) : (ListenerUtil.mutListener.listen(8622) ? (i != menu.size()) : (ListenerUtil.mutListener.listen(8621) ? (i == menu.size()) : (i < menu.size())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter104", ++_loopCounter104);
                    MenuItem item = menu.getItem(i);
                    final Drawable drawable = item.getIcon();
                    if (!ListenerUtil.mutListener.listen(8616)) {
                        if (drawable == null) {
                            continue;
                        }
                    }
                    final Drawable wrapped = DrawableCompat.wrap(drawable.mutate());
                    if (!ListenerUtil.mutListener.listen(8619)) {
                        if (item.getItemId() == R.id.saveButton) {
                            if (!ListenerUtil.mutListener.listen(8618)) {
                                DrawableCompat.setTint(wrapped, Color.parseColor("#FFFFFF"));
                            }
                        } else if (item.getItemId() == R.id.deleteButton) {
                            if (!ListenerUtil.mutListener.listen(8617)) {
                                DrawableCompat.setTint(wrapped, Color.parseColor("#FF4444"));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8620)) {
                        item.setIcon(wrapped);
                    }
                }
            }
        }
        MenuItem deleteButton = menu.findItem(R.id.deleteButton);
        if (!ListenerUtil.mutListener.listen(8630)) {
            switch(mode) {
                case ADD:
                    if (!ListenerUtil.mutListener.listen(8627)) {
                        deleteButton.setVisible(false);
                    }
                    break;
                case EDIT:
                    if (!ListenerUtil.mutListener.listen(8628)) {
                        editMode();
                    }
                    if (!ListenerUtil.mutListener.listen(8629)) {
                        deleteButton.setVisible(true);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(8631)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(8638)) {
            switch(item.getItemId()) {
                case R.id.saveButton:
                    if (!ListenerUtil.mutListener.listen(8636)) {
                        if (saveUserData()) {
                            if (!ListenerUtil.mutListener.listen(8635)) {
                                if (getActivity().findViewById(R.id.nav_host_fragment) != null) {
                                    if (!ListenerUtil.mutListener.listen(8633)) {
                                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).getPreviousBackStackEntry().getSavedStateHandle().set("update", true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(8634)) {
                                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigateUp();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(8632)) {
                                        getActivity().finish();
                                    }
                                }
                            }
                        }
                    }
                    return true;
                case R.id.deleteButton:
                    if (!ListenerUtil.mutListener.listen(8637)) {
                        deleteUser();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void editMode() {
        int id = UserSettingsFragmentArgs.fromBundle(getArguments()).getUserId();
        OpenScale openScale = OpenScale.getInstance();
        ScaleUser scaleUser = openScale.getScaleUser(id);
        if (!ListenerUtil.mutListener.listen(8639)) {
            birthday = scaleUser.getBirthday();
        }
        if (!ListenerUtil.mutListener.listen(8640)) {
            goal_date = scaleUser.getGoalDate();
        }
        if (!ListenerUtil.mutListener.listen(8641)) {
            txtUserName.setText(scaleUser.getUserName());
        }
        if (!ListenerUtil.mutListener.listen(8650)) {
            txtBodyHeight.setText(Float.toString((ListenerUtil.mutListener.listen(8649) ? (Math.round((ListenerUtil.mutListener.listen(8645) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8644) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8643) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8642) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) + 100.0f) : (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) * 100.0f)))))) % 100.0f) : (ListenerUtil.mutListener.listen(8648) ? (Math.round((ListenerUtil.mutListener.listen(8645) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8644) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8643) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8642) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) + 100.0f) : (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) * 100.0f)))))) * 100.0f) : (ListenerUtil.mutListener.listen(8647) ? (Math.round((ListenerUtil.mutListener.listen(8645) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8644) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8643) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8642) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) + 100.0f) : (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) * 100.0f)))))) - 100.0f) : (ListenerUtil.mutListener.listen(8646) ? (Math.round((ListenerUtil.mutListener.listen(8645) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8644) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8643) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8642) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) + 100.0f) : (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) * 100.0f)))))) + 100.0f) : (Math.round((ListenerUtil.mutListener.listen(8645) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8644) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8643) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8642) ? (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) + 100.0f) : (Converters.fromCentimeter(scaleUser.getBodyHeight(), scaleUser.getMeasureUnit()) * 100.0f)))))) / 100.0f)))))));
        }
        if (!ListenerUtil.mutListener.listen(8651)) {
            txtBodyHeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + scaleUser.getMeasureUnit().toString());
        }
        if (!ListenerUtil.mutListener.listen(8652)) {
            txtBirthday.setText(dateFormat.format(birthday));
        }
        if (!ListenerUtil.mutListener.listen(8653)) {
            txtGoalDate.setText(dateFormat.format(goal_date));
        }
        if (!ListenerUtil.mutListener.listen(8662)) {
            txtInitialWeight.setText(Float.toString((ListenerUtil.mutListener.listen(8661) ? (Math.round((ListenerUtil.mutListener.listen(8657) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8656) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8655) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8654) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) % 100.0f) : (ListenerUtil.mutListener.listen(8660) ? (Math.round((ListenerUtil.mutListener.listen(8657) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8656) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8655) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8654) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) * 100.0f) : (ListenerUtil.mutListener.listen(8659) ? (Math.round((ListenerUtil.mutListener.listen(8657) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8656) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8655) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8654) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) - 100.0f) : (ListenerUtil.mutListener.listen(8658) ? (Math.round((ListenerUtil.mutListener.listen(8657) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8656) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8655) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8654) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) + 100.0f) : (Math.round((ListenerUtil.mutListener.listen(8657) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8656) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8655) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8654) ? (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getInitialWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) / 100.0f)))))));
        }
        if (!ListenerUtil.mutListener.listen(8671)) {
            txtGoalWeight.setText(Float.toString((ListenerUtil.mutListener.listen(8670) ? (Math.round((ListenerUtil.mutListener.listen(8666) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8665) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8664) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8663) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) % 100.0f) : (ListenerUtil.mutListener.listen(8669) ? (Math.round((ListenerUtil.mutListener.listen(8666) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8665) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8664) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8663) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) * 100.0f) : (ListenerUtil.mutListener.listen(8668) ? (Math.round((ListenerUtil.mutListener.listen(8666) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8665) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8664) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8663) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) - 100.0f) : (ListenerUtil.mutListener.listen(8667) ? (Math.round((ListenerUtil.mutListener.listen(8666) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8665) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8664) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8663) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) + 100.0f) : (Math.round((ListenerUtil.mutListener.listen(8666) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) % 100.0f) : (ListenerUtil.mutListener.listen(8665) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) / 100.0f) : (ListenerUtil.mutListener.listen(8664) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) - 100.0f) : (ListenerUtil.mutListener.listen(8663) ? (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) + 100.0f) : (Converters.fromKilogram(scaleUser.getGoalWeight(), scaleUser.getScaleUnit()) * 100.0f)))))) / 100.0f)))))));
        }
        if (!ListenerUtil.mutListener.listen(8672)) {
            txtInitialWeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + scaleUser.getScaleUnit().toString());
        }
        if (!ListenerUtil.mutListener.listen(8673)) {
            txtGoalWeight.setHint(getResources().getString(R.string.info_enter_value_in) + " " + scaleUser.getScaleUnit().toString());
        }
        if (!ListenerUtil.mutListener.listen(8676)) {
            switch(scaleUser.getMeasureUnit()) {
                case CM:
                    if (!ListenerUtil.mutListener.listen(8674)) {
                        radioMeasurementUnit.check(R.id.btnRadioCM);
                    }
                    break;
                case INCH:
                    if (!ListenerUtil.mutListener.listen(8675)) {
                        radioMeasurementUnit.check(R.id.btnRadioINCH);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(8680)) {
            switch(scaleUser.getScaleUnit()) {
                case KG:
                    if (!ListenerUtil.mutListener.listen(8677)) {
                        radioScaleUnit.check(R.id.btnRadioKG);
                    }
                    break;
                case LB:
                    if (!ListenerUtil.mutListener.listen(8678)) {
                        radioScaleUnit.check(R.id.btnRadioLB);
                    }
                    break;
                case ST:
                    if (!ListenerUtil.mutListener.listen(8679)) {
                        radioScaleUnit.check(R.id.btnRadioST);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(8683)) {
            switch(scaleUser.getGender()) {
                case MALE:
                    if (!ListenerUtil.mutListener.listen(8681)) {
                        radioGender.check(R.id.btnRadioMale);
                    }
                    break;
                case FEMALE:
                    if (!ListenerUtil.mutListener.listen(8682)) {
                        radioGender.check(R.id.btnRadioWoman);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(8684)) {
            assistedWeighing.setChecked(scaleUser.isAssistedWeighing());
        }
        if (!ListenerUtil.mutListener.listen(8685)) {
            spinnerActivityLevel.setSelection(scaleUser.getActivityLevel().toInt());
        }
        if (!ListenerUtil.mutListener.listen(8686)) {
            spinnerLeftAmputationLevel.setSelection(scaleUser.getLeftAmputationLevel().toInt());
        }
        if (!ListenerUtil.mutListener.listen(8687)) {
            spinnerRightAmputationLevel.setSelection(scaleUser.getRightAmputationLevel().toInt());
        }
    }

    private boolean validateInput() {
        boolean validate = true;
        if (!ListenerUtil.mutListener.listen(8690)) {
            if (txtUserName.getText().toString().length() == 0) {
                if (!ListenerUtil.mutListener.listen(8688)) {
                    txtUserName.setError(getResources().getString(R.string.error_user_name_required));
                }
                if (!ListenerUtil.mutListener.listen(8689)) {
                    validate = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8693)) {
            if (txtBodyHeight.getText().toString().length() == 0) {
                if (!ListenerUtil.mutListener.listen(8691)) {
                    txtBodyHeight.setError(getResources().getString(R.string.error_height_required));
                }
                if (!ListenerUtil.mutListener.listen(8692)) {
                    validate = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8696)) {
            if (txtInitialWeight.getText().toString().length() == 0) {
                if (!ListenerUtil.mutListener.listen(8694)) {
                    txtInitialWeight.setError(getResources().getString(R.string.error_initial_weight_required));
                }
                if (!ListenerUtil.mutListener.listen(8695)) {
                    validate = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8699)) {
            if (txtGoalWeight.getText().toString().length() == 0) {
                if (!ListenerUtil.mutListener.listen(8697)) {
                    txtGoalWeight.setError(getResources().getString(R.string.error_goal_weight_required));
                }
                if (!ListenerUtil.mutListener.listen(8698)) {
                    validate = false;
                }
            }
        }
        return validate;
    }

    private final DatePickerDialog.OnDateSetListener birthdayPickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            Calendar cal = Calendar.getInstance();
            if (!ListenerUtil.mutListener.listen(8700)) {
                cal.set(selectedYear, selectedMonth, selectedDay, 0, 0, 0);
            }
            if (!ListenerUtil.mutListener.listen(8701)) {
                cal.set(Calendar.MILLISECOND, 0);
            }
            if (!ListenerUtil.mutListener.listen(8702)) {
                birthday = cal.getTime();
            }
            if (!ListenerUtil.mutListener.listen(8703)) {
                txtBirthday.setText(dateFormat.format(birthday));
            }
        }
    };

    private final DatePickerDialog.OnDateSetListener goalDatePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            Calendar cal = Calendar.getInstance();
            if (!ListenerUtil.mutListener.listen(8704)) {
                cal.set(selectedYear, selectedMonth, selectedDay, 0, 0, 0);
            }
            if (!ListenerUtil.mutListener.listen(8705)) {
                cal.set(Calendar.MILLISECOND, 0);
            }
            if (!ListenerUtil.mutListener.listen(8706)) {
                goal_date = cal.getTime();
            }
            if (!ListenerUtil.mutListener.listen(8707)) {
                txtGoalDate.setText(dateFormat.format(goal_date));
            }
        }
    };

    private void deleteUser() {
        AlertDialog.Builder deleteAllDialog = new AlertDialog.Builder(context);
        if (!ListenerUtil.mutListener.listen(8708)) {
            deleteAllDialog.setMessage(getResources().getString(R.string.question_really_delete_user));
        }
        if (!ListenerUtil.mutListener.listen(8717)) {
            deleteAllDialog.setPositiveButton(getResources().getString(R.string.label_yes), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    int userId = UserSettingsFragmentArgs.fromBundle(getArguments()).getUserId();
                    OpenScale openScale = OpenScale.getInstance();
                    boolean isSelected = openScale.getSelectedScaleUserId() == userId;
                    if (!ListenerUtil.mutListener.listen(8709)) {
                        openScale.clearScaleMeasurements(userId);
                    }
                    if (!ListenerUtil.mutListener.listen(8710)) {
                        openScale.deleteScaleUser(userId);
                    }
                    if (!ListenerUtil.mutListener.listen(8714)) {
                        if (isSelected) {
                            List<ScaleUser> scaleUser = openScale.getScaleUserList();
                            int lastUserId = -1;
                            if (!ListenerUtil.mutListener.listen(8712)) {
                                if (!scaleUser.isEmpty()) {
                                    if (!ListenerUtil.mutListener.listen(8711)) {
                                        lastUserId = scaleUser.get(0).getId();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8713)) {
                                openScale.selectScaleUser(lastUserId);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8715)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).getPreviousBackStackEntry().getSavedStateHandle().set("update", true);
                    }
                    if (!ListenerUtil.mutListener.listen(8716)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigateUp();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8719)) {
            deleteAllDialog.setNegativeButton(getResources().getString(R.string.label_no), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    if (!ListenerUtil.mutListener.listen(8718)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8720)) {
            deleteAllDialog.show();
        }
    }

    private boolean saveUserData() {
        try {
            if (!ListenerUtil.mutListener.listen(8750)) {
                if (validateInput()) {
                    OpenScale openScale = OpenScale.getInstance();
                    String name = txtUserName.getText().toString();
                    float body_height = Float.valueOf(txtBodyHeight.getText().toString());
                    float initial_weight = Float.valueOf(txtInitialWeight.getText().toString());
                    float goal_weight = Float.valueOf(txtGoalWeight.getText().toString());
                    Converters.MeasureUnit measure_unit = Converters.MeasureUnit.CM;
                    if (!ListenerUtil.mutListener.listen(8724)) {
                        switch(radioMeasurementUnit.getCheckedRadioButtonId()) {
                            case R.id.btnRadioCM:
                                if (!ListenerUtil.mutListener.listen(8722)) {
                                    measure_unit = Converters.MeasureUnit.CM;
                                }
                                break;
                            case R.id.btnRadioINCH:
                                if (!ListenerUtil.mutListener.listen(8723)) {
                                    measure_unit = Converters.MeasureUnit.INCH;
                                }
                                break;
                        }
                    }
                    Converters.WeightUnit scale_unit = Converters.WeightUnit.KG;
                    if (!ListenerUtil.mutListener.listen(8728)) {
                        switch(radioScaleUnit.getCheckedRadioButtonId()) {
                            case R.id.btnRadioKG:
                                if (!ListenerUtil.mutListener.listen(8725)) {
                                    scale_unit = Converters.WeightUnit.KG;
                                }
                                break;
                            case R.id.btnRadioLB:
                                if (!ListenerUtil.mutListener.listen(8726)) {
                                    scale_unit = Converters.WeightUnit.LB;
                                }
                                break;
                            case R.id.btnRadioST:
                                if (!ListenerUtil.mutListener.listen(8727)) {
                                    scale_unit = Converters.WeightUnit.ST;
                                }
                                break;
                        }
                    }
                    Converters.Gender gender = Converters.Gender.MALE;
                    if (!ListenerUtil.mutListener.listen(8731)) {
                        switch(radioGender.getCheckedRadioButtonId()) {
                            case R.id.btnRadioMale:
                                if (!ListenerUtil.mutListener.listen(8729)) {
                                    gender = Converters.Gender.MALE;
                                }
                                break;
                            case R.id.btnRadioWoman:
                                if (!ListenerUtil.mutListener.listen(8730)) {
                                    gender = Converters.Gender.FEMALE;
                                }
                                break;
                        }
                    }
                    final ScaleUser scaleUser = new ScaleUser();
                    if (!ListenerUtil.mutListener.listen(8732)) {
                        scaleUser.setUserName(name);
                    }
                    if (!ListenerUtil.mutListener.listen(8733)) {
                        scaleUser.setBirthday(birthday);
                    }
                    if (!ListenerUtil.mutListener.listen(8734)) {
                        scaleUser.setBodyHeight(Converters.toCentimeter(body_height, measure_unit));
                    }
                    if (!ListenerUtil.mutListener.listen(8735)) {
                        scaleUser.setScaleUnit(scale_unit);
                    }
                    if (!ListenerUtil.mutListener.listen(8736)) {
                        scaleUser.setMeasureUnit(measure_unit);
                    }
                    if (!ListenerUtil.mutListener.listen(8737)) {
                        scaleUser.setActivityLevel(Converters.fromActivityLevelInt(spinnerActivityLevel.getSelectedItemPosition()));
                    }
                    if (!ListenerUtil.mutListener.listen(8738)) {
                        scaleUser.setLeftAmputationLevel(Converters.fromAmputationLevelInt(spinnerLeftAmputationLevel.getSelectedItemPosition()));
                    }
                    if (!ListenerUtil.mutListener.listen(8739)) {
                        scaleUser.setRightAmputationLevel(Converters.fromAmputationLevelInt(spinnerRightAmputationLevel.getSelectedItemPosition()));
                    }
                    if (!ListenerUtil.mutListener.listen(8740)) {
                        scaleUser.setGender(gender);
                    }
                    if (!ListenerUtil.mutListener.listen(8741)) {
                        scaleUser.setAssistedWeighing(assistedWeighing.isChecked());
                    }
                    if (!ListenerUtil.mutListener.listen(8742)) {
                        scaleUser.setInitialWeight(Converters.toKilogram(initial_weight, scale_unit));
                    }
                    if (!ListenerUtil.mutListener.listen(8743)) {
                        scaleUser.setGoalWeight(Converters.toKilogram(goal_weight, scale_unit));
                    }
                    if (!ListenerUtil.mutListener.listen(8744)) {
                        scaleUser.setGoalDate(goal_date);
                    }
                    if (!ListenerUtil.mutListener.listen(8748)) {
                        switch(mode) {
                            case ADD:
                                int id = openScale.addScaleUser(scaleUser);
                                if (!ListenerUtil.mutListener.listen(8745)) {
                                    scaleUser.setId(id);
                                }
                                break;
                            case EDIT:
                                if (!ListenerUtil.mutListener.listen(8746)) {
                                    scaleUser.setId(UserSettingsFragmentArgs.fromBundle(getArguments()).getUserId());
                                }
                                if (!ListenerUtil.mutListener.listen(8747)) {
                                    openScale.updateScaleUser(scaleUser);
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8749)) {
                        openScale.selectScaleUser(scaleUser.getId());
                    }
                    return true;
                }
            }
        } catch (NumberFormatException ex) {
            if (!ListenerUtil.mutListener.listen(8721)) {
                Toast.makeText(context, getResources().getString(R.string.error_value_range) + "(" + ex.getMessage() + ")", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}
