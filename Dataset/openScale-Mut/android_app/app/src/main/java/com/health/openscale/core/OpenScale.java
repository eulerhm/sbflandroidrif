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
package com.health.openscale.core;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.OpenableColumns;
import android.text.format.DateFormat;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.health.openscale.R;
import com.health.openscale.core.alarm.AlarmHandler;
import com.health.openscale.core.bluetooth.BluetoothCommunication;
import com.health.openscale.core.bluetooth.BluetoothFactory;
import com.health.openscale.core.bodymetric.EstimatedFatMetric;
import com.health.openscale.core.bodymetric.EstimatedLBMMetric;
import com.health.openscale.core.bodymetric.EstimatedWaterMetric;
import com.health.openscale.core.database.AppDatabase;
import com.health.openscale.core.database.ScaleMeasurementDAO;
import com.health.openscale.core.database.ScaleUserDAO;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import com.health.openscale.core.utils.CsvHelper;
import com.health.openscale.gui.measurement.FatMeasurementView;
import com.health.openscale.gui.measurement.LBMMeasurementView;
import com.health.openscale.gui.measurement.MeasurementViewSettings;
import com.health.openscale.gui.measurement.WaterMeasurementView;
import com.health.openscale.gui.widget.WidgetProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class OpenScale {

    public static boolean DEBUG_MODE = false;

    public static final String DATABASE_NAME = "openScale.db";

    private static OpenScale instance;

    private AppDatabase appDB;

    private ScaleMeasurementDAO measurementDAO;

    private ScaleUserDAO userDAO;

    private ScaleUser selectedScaleUser;

    private BluetoothCommunication btDeviceDriver;

    private AlarmHandler alarmHandler;

    private Context context;

    private OpenScale(Context context) {
        if (!ListenerUtil.mutListener.listen(5977)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(5978)) {
            alarmHandler = new AlarmHandler();
        }
        if (!ListenerUtil.mutListener.listen(5979)) {
            btDeviceDriver = null;
        }
        if (!ListenerUtil.mutListener.listen(5980)) {
            reopenDatabase(false);
        }
    }

    public static void createInstance(Context context) {
        if (!ListenerUtil.mutListener.listen(5981)) {
            if (instance != null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5982)) {
            instance = new OpenScale(context);
        }
    }

    public static OpenScale getInstance() {
        if (!ListenerUtil.mutListener.listen(5983)) {
            if (instance == null) {
                throw new RuntimeException("No OpenScale instance created");
            }
        }
        return instance;
    }

    public void reopenDatabase(boolean truncate) throws SQLiteDatabaseCorruptException {
        if (!ListenerUtil.mutListener.listen(5985)) {
            if (appDB != null) {
                if (!ListenerUtil.mutListener.listen(5984)) {
                    appDB.close();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5988)) {
            appDB = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().setJournalMode(// in truncate mode no sql cache files (-shm, -wal) are generated
            truncate == true ? RoomDatabase.JournalMode.TRUNCATE : RoomDatabase.JournalMode.AUTOMATIC).addCallback(new RoomDatabase.Callback() {

                @Override
                public void onOpen(SupportSQLiteDatabase db) {
                    if (!ListenerUtil.mutListener.listen(5986)) {
                        super.onOpen(db);
                    }
                    if (!ListenerUtil.mutListener.listen(5987)) {
                        db.setForeignKeyConstraintsEnabled(true);
                    }
                }
            }).addMigrations(AppDatabase.MIGRATION_1_2, AppDatabase.MIGRATION_2_3, AppDatabase.MIGRATION_3_4, AppDatabase.MIGRATION_4_5).build();
        }
        if (!ListenerUtil.mutListener.listen(5989)) {
            measurementDAO = appDB.measurementDAO();
        }
        if (!ListenerUtil.mutListener.listen(5990)) {
            userDAO = appDB.userDAO();
        }
    }

    public void triggerWidgetUpdate() {
        int[] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
        if (!ListenerUtil.mutListener.listen(5999)) {
            if ((ListenerUtil.mutListener.listen(5995) ? (ids.length >= 0) : (ListenerUtil.mutListener.listen(5994) ? (ids.length <= 0) : (ListenerUtil.mutListener.listen(5993) ? (ids.length < 0) : (ListenerUtil.mutListener.listen(5992) ? (ids.length != 0) : (ListenerUtil.mutListener.listen(5991) ? (ids.length == 0) : (ids.length > 0))))))) {
                Intent intent = new Intent(context, WidgetProvider.class);
                if (!ListenerUtil.mutListener.listen(5996)) {
                    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                }
                if (!ListenerUtil.mutListener.listen(5997)) {
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                }
                if (!ListenerUtil.mutListener.listen(5998)) {
                    context.sendBroadcast(intent);
                }
            }
        }
    }

    public int addScaleUser(final ScaleUser user) {
        return (int) userDAO.insert(user);
    }

    public void selectScaleUser(int userId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(6000)) {
            prefs.edit().putInt("selectedUserId", userId).apply();
        }
        if (!ListenerUtil.mutListener.listen(6001)) {
            selectedScaleUser = getScaleUser(userId);
        }
    }

    public int getSelectedScaleUserId() {
        if (!ListenerUtil.mutListener.listen(6002)) {
            if (selectedScaleUser != null) {
                return selectedScaleUser.getId();
            }
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt("selectedUserId", -1);
    }

    public List<ScaleUser> getScaleUserList() {
        return userDAO.getAll();
    }

    public ScaleUser getScaleUser(int userId) {
        if (!ListenerUtil.mutListener.listen(6004)) {
            if ((ListenerUtil.mutListener.listen(6003) ? (selectedScaleUser != null || selectedScaleUser.getId() == userId) : (selectedScaleUser != null && selectedScaleUser.getId() == userId))) {
                return selectedScaleUser;
            }
        }
        return userDAO.get(userId);
    }

    public ScaleUser getSelectedScaleUser() {
        if (!ListenerUtil.mutListener.listen(6005)) {
            if (selectedScaleUser != null) {
                return selectedScaleUser;
            }
        }
        try {
            final int selectedUserId = getSelectedScaleUserId();
            if (!ListenerUtil.mutListener.listen(6016)) {
                if ((ListenerUtil.mutListener.listen(6012) ? (selectedUserId >= -1) : (ListenerUtil.mutListener.listen(6011) ? (selectedUserId <= -1) : (ListenerUtil.mutListener.listen(6010) ? (selectedUserId > -1) : (ListenerUtil.mutListener.listen(6009) ? (selectedUserId < -1) : (ListenerUtil.mutListener.listen(6008) ? (selectedUserId == -1) : (selectedUserId != -1))))))) {
                    if (!ListenerUtil.mutListener.listen(6013)) {
                        selectedScaleUser = userDAO.get(selectedUserId);
                    }
                    if (!ListenerUtil.mutListener.listen(6015)) {
                        if (selectedScaleUser == null) {
                            if (!ListenerUtil.mutListener.listen(6014)) {
                                selectScaleUser(-1);
                            }
                            throw new Exception("could not find the selected user");
                        }
                    }
                    return selectedScaleUser;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6006)) {
                Timber.e(e);
            }
            if (!ListenerUtil.mutListener.listen(6007)) {
                runUiToastMsg("Error: " + e.getMessage());
            }
        }
        return new ScaleUser();
    }

    public void deleteScaleUser(int id) {
        if (!ListenerUtil.mutListener.listen(6017)) {
            Timber.d("Delete user " + getScaleUser(id));
        }
        if (!ListenerUtil.mutListener.listen(6018)) {
            userDAO.delete(userDAO.get(id));
        }
        if (!ListenerUtil.mutListener.listen(6019)) {
            selectedScaleUser = null;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        // Remove user specific settings
        SharedPreferences.Editor editor = prefs.edit();
        final String prefix = ScaleUser.getPreferenceKey(id, "");
        if (!ListenerUtil.mutListener.listen(6022)) {
            {
                long _loopCounter62 = 0;
                for (String key : prefs.getAll().keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter62", ++_loopCounter62);
                    if (!ListenerUtil.mutListener.listen(6021)) {
                        if (key.startsWith(prefix)) {
                            if (!ListenerUtil.mutListener.listen(6020)) {
                                editor.remove(key);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6023)) {
            editor.apply();
        }
    }

    public void updateScaleUser(ScaleUser user) {
        if (!ListenerUtil.mutListener.listen(6024)) {
            userDAO.update(user);
        }
        if (!ListenerUtil.mutListener.listen(6025)) {
            selectedScaleUser = null;
        }
    }

    public boolean isScaleMeasurementListEmpty() {
        if (!ListenerUtil.mutListener.listen(6026)) {
            if (measurementDAO.getCount(getSelectedScaleUserId()) == 0) {
                return true;
            }
        }
        return false;
    }

    public ScaleMeasurement getLastScaleMeasurement() {
        return measurementDAO.getLatest(getSelectedScaleUserId());
    }

    public ScaleMeasurement getLastScaleMeasurement(int userId) {
        return measurementDAO.getLatest(userId);
    }

    public ScaleMeasurement getFirstScaleMeasurement() {
        return measurementDAO.getFirst(getSelectedScaleUserId());
    }

    public List<ScaleMeasurement> getScaleMeasurementList() {
        return measurementDAO.getAll(getSelectedScaleUserId());
    }

    public ScaleMeasurement[] getTupleOfScaleMeasurement(int id) {
        ScaleMeasurement[] tupleScaleMeasurement = new ScaleMeasurement[3];
        if (!ListenerUtil.mutListener.listen(6027)) {
            tupleScaleMeasurement[0] = null;
        }
        if (!ListenerUtil.mutListener.listen(6028)) {
            tupleScaleMeasurement[1] = measurementDAO.get(id);
        }
        if (!ListenerUtil.mutListener.listen(6029)) {
            tupleScaleMeasurement[2] = null;
        }
        if (!ListenerUtil.mutListener.listen(6032)) {
            if (tupleScaleMeasurement[1] != null) {
                if (!ListenerUtil.mutListener.listen(6030)) {
                    tupleScaleMeasurement[0] = measurementDAO.getPrevious(id, tupleScaleMeasurement[1].getUserId());
                }
                if (!ListenerUtil.mutListener.listen(6031)) {
                    tupleScaleMeasurement[2] = measurementDAO.getNext(id, tupleScaleMeasurement[1].getUserId());
                }
            }
        }
        return tupleScaleMeasurement;
    }

    public int addScaleMeasurement(final ScaleMeasurement scaleMeasurement) {
        return addScaleMeasurement(scaleMeasurement, false);
    }

    public int addScaleMeasurement(final ScaleMeasurement scaleMeasurement, boolean silent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(6038)) {
            // Check user id and do a smart user assign if option is enabled
            if (scaleMeasurement.getUserId() == -1) {
                if (!ListenerUtil.mutListener.listen(6035)) {
                    if (prefs.getBoolean("smartUserAssign", false)) {
                        if (!ListenerUtil.mutListener.listen(6034)) {
                            scaleMeasurement.setUserId(getSmartUserAssignment(scaleMeasurement.getWeight(), 15.0f));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6033)) {
                            scaleMeasurement.setUserId(getSelectedScaleUser().getId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6037)) {
                    // don't add scale data if no user is selected
                    if (scaleMeasurement.getUserId() == -1) {
                        if (!ListenerUtil.mutListener.listen(6036)) {
                            Timber.e("to be added measurement are thrown away because no user is selected");
                        }
                        return -1;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6052)) {
            // Assisted weighing
            if (getScaleUser(scaleMeasurement.getUserId()).isAssistedWeighing()) {
                int assistedWeighingRefUserId = prefs.getInt("assistedWeighingRefUserId", -1);
                if (!ListenerUtil.mutListener.listen(6051)) {
                    if ((ListenerUtil.mutListener.listen(6043) ? (assistedWeighingRefUserId >= -1) : (ListenerUtil.mutListener.listen(6042) ? (assistedWeighingRefUserId <= -1) : (ListenerUtil.mutListener.listen(6041) ? (assistedWeighingRefUserId > -1) : (ListenerUtil.mutListener.listen(6040) ? (assistedWeighingRefUserId < -1) : (ListenerUtil.mutListener.listen(6039) ? (assistedWeighingRefUserId == -1) : (assistedWeighingRefUserId != -1))))))) {
                        ScaleMeasurement lastRefScaleMeasurement = getLastScaleMeasurement(assistedWeighingRefUserId);
                        if (!ListenerUtil.mutListener.listen(6050)) {
                            if (lastRefScaleMeasurement != null) {
                                float refWeight = lastRefScaleMeasurement.getWeight();
                                float diffToRef = (ListenerUtil.mutListener.listen(6048) ? (scaleMeasurement.getWeight() % refWeight) : (ListenerUtil.mutListener.listen(6047) ? (scaleMeasurement.getWeight() / refWeight) : (ListenerUtil.mutListener.listen(6046) ? (scaleMeasurement.getWeight() * refWeight) : (ListenerUtil.mutListener.listen(6045) ? (scaleMeasurement.getWeight() + refWeight) : (scaleMeasurement.getWeight() - refWeight)))));
                                if (!ListenerUtil.mutListener.listen(6049)) {
                                    scaleMeasurement.setWeight(diffToRef);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6044)) {
                            Timber.e("assisted weighing reference user id is -1");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6061)) {
            // Calculate the amputation correction factor for the weight, if available
            scaleMeasurement.setWeight((ListenerUtil.mutListener.listen(6060) ? (((ListenerUtil.mutListener.listen(6056) ? (scaleMeasurement.getWeight() % 100.0f) : (ListenerUtil.mutListener.listen(6055) ? (scaleMeasurement.getWeight() / 100.0f) : (ListenerUtil.mutListener.listen(6054) ? (scaleMeasurement.getWeight() - 100.0f) : (ListenerUtil.mutListener.listen(6053) ? (scaleMeasurement.getWeight() + 100.0f) : (scaleMeasurement.getWeight() * 100.0f)))))) % getScaleUser(scaleMeasurement.getUserId()).getAmputationCorrectionFactor()) : (ListenerUtil.mutListener.listen(6059) ? (((ListenerUtil.mutListener.listen(6056) ? (scaleMeasurement.getWeight() % 100.0f) : (ListenerUtil.mutListener.listen(6055) ? (scaleMeasurement.getWeight() / 100.0f) : (ListenerUtil.mutListener.listen(6054) ? (scaleMeasurement.getWeight() - 100.0f) : (ListenerUtil.mutListener.listen(6053) ? (scaleMeasurement.getWeight() + 100.0f) : (scaleMeasurement.getWeight() * 100.0f)))))) * getScaleUser(scaleMeasurement.getUserId()).getAmputationCorrectionFactor()) : (ListenerUtil.mutListener.listen(6058) ? (((ListenerUtil.mutListener.listen(6056) ? (scaleMeasurement.getWeight() % 100.0f) : (ListenerUtil.mutListener.listen(6055) ? (scaleMeasurement.getWeight() / 100.0f) : (ListenerUtil.mutListener.listen(6054) ? (scaleMeasurement.getWeight() - 100.0f) : (ListenerUtil.mutListener.listen(6053) ? (scaleMeasurement.getWeight() + 100.0f) : (scaleMeasurement.getWeight() * 100.0f)))))) - getScaleUser(scaleMeasurement.getUserId()).getAmputationCorrectionFactor()) : (ListenerUtil.mutListener.listen(6057) ? (((ListenerUtil.mutListener.listen(6056) ? (scaleMeasurement.getWeight() % 100.0f) : (ListenerUtil.mutListener.listen(6055) ? (scaleMeasurement.getWeight() / 100.0f) : (ListenerUtil.mutListener.listen(6054) ? (scaleMeasurement.getWeight() - 100.0f) : (ListenerUtil.mutListener.listen(6053) ? (scaleMeasurement.getWeight() + 100.0f) : (scaleMeasurement.getWeight() * 100.0f)))))) + getScaleUser(scaleMeasurement.getUserId()).getAmputationCorrectionFactor()) : (((ListenerUtil.mutListener.listen(6056) ? (scaleMeasurement.getWeight() % 100.0f) : (ListenerUtil.mutListener.listen(6055) ? (scaleMeasurement.getWeight() / 100.0f) : (ListenerUtil.mutListener.listen(6054) ? (scaleMeasurement.getWeight() - 100.0f) : (ListenerUtil.mutListener.listen(6053) ? (scaleMeasurement.getWeight() + 100.0f) : (scaleMeasurement.getWeight() * 100.0f)))))) / getScaleUser(scaleMeasurement.getUserId()).getAmputationCorrectionFactor()))))));
        }
        // If option is enabled then calculate body measurements from generic formulas
        MeasurementViewSettings settings = new MeasurementViewSettings(prefs, WaterMeasurementView.KEY);
        if (!ListenerUtil.mutListener.listen(6064)) {
            if ((ListenerUtil.mutListener.listen(6062) ? (settings.isEnabled() || settings.isEstimationEnabled()) : (settings.isEnabled() && settings.isEstimationEnabled()))) {
                EstimatedWaterMetric waterMetric = EstimatedWaterMetric.getEstimatedMetric(EstimatedWaterMetric.FORMULA.valueOf(settings.getEstimationFormula()));
                if (!ListenerUtil.mutListener.listen(6063)) {
                    scaleMeasurement.setWater(waterMetric.getWater(getScaleUser(scaleMeasurement.getUserId()), scaleMeasurement));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6065)) {
            settings = new MeasurementViewSettings(prefs, FatMeasurementView.KEY);
        }
        if (!ListenerUtil.mutListener.listen(6068)) {
            if ((ListenerUtil.mutListener.listen(6066) ? (settings.isEnabled() || settings.isEstimationEnabled()) : (settings.isEnabled() && settings.isEstimationEnabled()))) {
                EstimatedFatMetric fatMetric = EstimatedFatMetric.getEstimatedMetric(EstimatedFatMetric.FORMULA.valueOf(settings.getEstimationFormula()));
                if (!ListenerUtil.mutListener.listen(6067)) {
                    scaleMeasurement.setFat(fatMetric.getFat(getScaleUser(scaleMeasurement.getUserId()), scaleMeasurement));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6069)) {
            // Must be after fat estimation as one formula is based on fat
            settings = new MeasurementViewSettings(prefs, LBMMeasurementView.KEY);
        }
        if (!ListenerUtil.mutListener.listen(6072)) {
            if ((ListenerUtil.mutListener.listen(6070) ? (settings.isEnabled() || settings.isEstimationEnabled()) : (settings.isEnabled() && settings.isEstimationEnabled()))) {
                EstimatedLBMMetric lbmMetric = EstimatedLBMMetric.getEstimatedMetric(EstimatedLBMMetric.FORMULA.valueOf(settings.getEstimationFormula()));
                if (!ListenerUtil.mutListener.listen(6071)) {
                    scaleMeasurement.setLbm(lbmMetric.getLBM(getScaleUser(scaleMeasurement.getUserId()), scaleMeasurement));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6082)) {
            // Insert measurement into the database, check return if it was successful inserted
            if (measurementDAO.insert(scaleMeasurement) != -1) {
                if (!ListenerUtil.mutListener.listen(6076)) {
                    Timber.d("Added measurement: %s", scaleMeasurement);
                }
                if (!ListenerUtil.mutListener.listen(6078)) {
                    if (!silent) {
                        ScaleUser scaleUser = getScaleUser(scaleMeasurement.getUserId());
                        final java.text.DateFormat dateFormat = DateFormat.getDateFormat(context);
                        final java.text.DateFormat timeFormat = DateFormat.getTimeFormat(context);
                        final Date dateTime = scaleMeasurement.getDateTime();
                        final Converters.WeightUnit unit = scaleUser.getScaleUnit();
                        String infoText = String.format(context.getString(R.string.info_new_data_added), Converters.fromKilogram(scaleMeasurement.getWeight(), unit), unit.toString(), dateFormat.format(dateTime) + " " + timeFormat.format(dateTime), scaleUser.getUserName());
                        if (!ListenerUtil.mutListener.listen(6077)) {
                            runUiToastMsg(infoText);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6079)) {
                    syncInsertMeasurement(scaleMeasurement);
                }
                if (!ListenerUtil.mutListener.listen(6080)) {
                    alarmHandler.entryChanged(context, scaleMeasurement);
                }
                if (!ListenerUtil.mutListener.listen(6081)) {
                    triggerWidgetUpdate();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6073)) {
                    Timber.d("to be added measurement is thrown away because measurement with the same date and time already exist");
                }
                if (!ListenerUtil.mutListener.listen(6075)) {
                    if (!silent) {
                        if (!ListenerUtil.mutListener.listen(6074)) {
                            runUiToastMsg(context.getString(R.string.info_new_data_duplicated));
                        }
                    }
                }
            }
        }
        return scaleMeasurement.getUserId();
    }

    private int getSmartUserAssignment(float weight, float range) {
        List<ScaleUser> scaleUsers = getScaleUserList();
        Map<Float, Integer> inRangeWeights = new TreeMap<>();
        if (!ListenerUtil.mutListener.listen(6118)) {
            {
                long _loopCounter63 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(6117) ? (i >= scaleUsers.size()) : (ListenerUtil.mutListener.listen(6116) ? (i <= scaleUsers.size()) : (ListenerUtil.mutListener.listen(6115) ? (i > scaleUsers.size()) : (ListenerUtil.mutListener.listen(6114) ? (i != scaleUsers.size()) : (ListenerUtil.mutListener.listen(6113) ? (i == scaleUsers.size()) : (i < scaleUsers.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter63", ++_loopCounter63);
                    List<ScaleMeasurement> scaleUserData = measurementDAO.getAll(scaleUsers.get(i).getId());
                    float lastWeight;
                    if ((ListenerUtil.mutListener.listen(6087) ? (scaleUserData.size() >= 0) : (ListenerUtil.mutListener.listen(6086) ? (scaleUserData.size() <= 0) : (ListenerUtil.mutListener.listen(6085) ? (scaleUserData.size() < 0) : (ListenerUtil.mutListener.listen(6084) ? (scaleUserData.size() != 0) : (ListenerUtil.mutListener.listen(6083) ? (scaleUserData.size() == 0) : (scaleUserData.size() > 0))))))) {
                        lastWeight = scaleUserData.get(0).getWeight();
                    } else {
                        lastWeight = scaleUsers.get(i).getInitialWeight();
                    }
                    if (!ListenerUtil.mutListener.listen(6112)) {
                        if ((ListenerUtil.mutListener.listen(6106) ? ((ListenerUtil.mutListener.listen(6096) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) >= weight) : (ListenerUtil.mutListener.listen(6095) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) > weight) : (ListenerUtil.mutListener.listen(6094) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) < weight) : (ListenerUtil.mutListener.listen(6093) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) != weight) : (ListenerUtil.mutListener.listen(6092) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) == weight) : (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) <= weight)))))) || (ListenerUtil.mutListener.listen(6105) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) <= weight) : (ListenerUtil.mutListener.listen(6104) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) > weight) : (ListenerUtil.mutListener.listen(6103) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) < weight) : (ListenerUtil.mutListener.listen(6102) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) != weight) : (ListenerUtil.mutListener.listen(6101) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) == weight) : (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) >= weight))))))) : ((ListenerUtil.mutListener.listen(6096) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) >= weight) : (ListenerUtil.mutListener.listen(6095) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) > weight) : (ListenerUtil.mutListener.listen(6094) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) < weight) : (ListenerUtil.mutListener.listen(6093) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) != weight) : (ListenerUtil.mutListener.listen(6092) ? (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) == weight) : (((ListenerUtil.mutListener.listen(6091) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6090) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6089) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6088) ? (lastWeight + range) : (lastWeight - range)))))) <= weight)))))) && (ListenerUtil.mutListener.listen(6105) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) <= weight) : (ListenerUtil.mutListener.listen(6104) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) > weight) : (ListenerUtil.mutListener.listen(6103) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) < weight) : (ListenerUtil.mutListener.listen(6102) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) != weight) : (ListenerUtil.mutListener.listen(6101) ? (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) == weight) : (((ListenerUtil.mutListener.listen(6100) ? (lastWeight % range) : (ListenerUtil.mutListener.listen(6099) ? (lastWeight / range) : (ListenerUtil.mutListener.listen(6098) ? (lastWeight * range) : (ListenerUtil.mutListener.listen(6097) ? (lastWeight - range) : (lastWeight + range)))))) >= weight))))))))) {
                            if (!ListenerUtil.mutListener.listen(6111)) {
                                inRangeWeights.put(Math.abs((ListenerUtil.mutListener.listen(6110) ? (lastWeight % weight) : (ListenerUtil.mutListener.listen(6109) ? (lastWeight / weight) : (ListenerUtil.mutListener.listen(6108) ? (lastWeight * weight) : (ListenerUtil.mutListener.listen(6107) ? (lastWeight + weight) : (lastWeight - weight)))))), scaleUsers.get(i).getId());
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6125)) {
            if ((ListenerUtil.mutListener.listen(6123) ? (inRangeWeights.size() >= 0) : (ListenerUtil.mutListener.listen(6122) ? (inRangeWeights.size() <= 0) : (ListenerUtil.mutListener.listen(6121) ? (inRangeWeights.size() < 0) : (ListenerUtil.mutListener.listen(6120) ? (inRangeWeights.size() != 0) : (ListenerUtil.mutListener.listen(6119) ? (inRangeWeights.size() == 0) : (inRangeWeights.size() > 0))))))) {
                // return the user id which is nearest to the weight (first element of the tree map)
                int userId = inRangeWeights.entrySet().iterator().next().getValue();
                if (!ListenerUtil.mutListener.listen(6124)) {
                    Timber.d("assign measurement to the nearest measurement with the user " + getScaleUser(userId).getUserName() + " (smartUserAssignment=on)");
                }
                return userId;
            }
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(6127)) {
            // if ignore out of range preference is true don't add this data
            if (prefs.getBoolean("ignoreOutOfRange", false)) {
                if (!ListenerUtil.mutListener.listen(6126)) {
                    Timber.d("to be added measurement is thrown away because measurement is out of range (smartUserAssignment=on;ignoreOutOfRange=on)");
                }
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(6128)) {
            // return selected scale user id if not out of range preference is checked and weight is out of range of any user
            Timber.d("assign measurement to the selected user (smartUserAssignment=on;ignoreOutOfRange=off)");
        }
        return getSelectedScaleUser().getId();
    }

    public void updateScaleMeasurement(ScaleMeasurement scaleMeasurement) {
        if (!ListenerUtil.mutListener.listen(6129)) {
            Timber.d("Update measurement: %s", scaleMeasurement);
        }
        if (!ListenerUtil.mutListener.listen(6130)) {
            measurementDAO.update(scaleMeasurement);
        }
        if (!ListenerUtil.mutListener.listen(6131)) {
            alarmHandler.entryChanged(context, scaleMeasurement);
        }
        if (!ListenerUtil.mutListener.listen(6132)) {
            syncUpdateMeasurement(scaleMeasurement);
        }
        if (!ListenerUtil.mutListener.listen(6133)) {
            triggerWidgetUpdate();
        }
    }

    public void deleteScaleMeasurement(int id) {
        if (!ListenerUtil.mutListener.listen(6134)) {
            syncDeleteMeasurement(measurementDAO.get(id).getDateTime());
        }
        if (!ListenerUtil.mutListener.listen(6135)) {
            measurementDAO.delete(id);
        }
    }

    public String getFilenameFromUriMayThrow(Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        try {
            if (!ListenerUtil.mutListener.listen(6138)) {
                cursor.moveToFirst();
            }
            return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        } finally {
            if (!ListenerUtil.mutListener.listen(6137)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(6136)) {
                        cursor.close();
                    }
                }
            }
        }
    }

    public String getFilenameFromUri(Uri uri) {
        try {
            return getFilenameFromUriMayThrow(uri);
        } catch (Exception e) {
            String name = uri.getLastPathSegment();
            if (name != null) {
                return name;
            }
            if (!ListenerUtil.mutListener.listen(6139)) {
                name = uri.getPath();
            }
            if (name != null) {
                return name;
            }
            return uri.toString();
        }
    }

    public void importDatabase(Uri importFile) throws IOException {
        File exportFile = context.getApplicationContext().getDatabasePath("openScale.db");
        File tmpExportFile = context.getApplicationContext().getDatabasePath("openScale_tmp.db");
        try {
            if (!ListenerUtil.mutListener.listen(6142)) {
                copyFile(Uri.fromFile(exportFile), Uri.fromFile(tmpExportFile));
            }
            if (!ListenerUtil.mutListener.listen(6143)) {
                copyFile(importFile, Uri.fromFile(exportFile));
            }
            if (!ListenerUtil.mutListener.listen(6144)) {
                reopenDatabase(false);
            }
            if (!ListenerUtil.mutListener.listen(6146)) {
                if (!getScaleUserList().isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(6145)) {
                        selectScaleUser(getScaleUserList().get(0).getId());
                    }
                }
            }
        } catch (SQLiteDatabaseCorruptException e) {
            if (!ListenerUtil.mutListener.listen(6140)) {
                copyFile(Uri.fromFile(tmpExportFile), Uri.fromFile(exportFile));
            }
            throw new IOException(e.getMessage());
        } finally {
            if (!ListenerUtil.mutListener.listen(6141)) {
                tmpExportFile.delete();
            }
        }
    }

    public void exportDatabase(Uri exportFile) throws IOException {
        File dbFile = context.getApplicationContext().getDatabasePath("openScale.db");
        if (!ListenerUtil.mutListener.listen(6147)) {
            // re-open database without caching sql -shm, -wal files
            reopenDatabase(true);
        }
        if (!ListenerUtil.mutListener.listen(6148)) {
            copyFile(Uri.fromFile(dbFile), exportFile);
        }
    }

    private void copyFile(Uri src, Uri dst) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(src);
        OutputStream output = context.getContentResolver().openOutputStream(dst);
        try {
            byte[] bytes = new byte[4096];
            int count;
            if (!ListenerUtil.mutListener.listen(6160)) {
                {
                    long _loopCounter64 = 0;
                    while ((ListenerUtil.mutListener.listen(6159) ? ((count = input.read(bytes)) >= -1) : (ListenerUtil.mutListener.listen(6158) ? ((count = input.read(bytes)) <= -1) : (ListenerUtil.mutListener.listen(6157) ? ((count = input.read(bytes)) > -1) : (ListenerUtil.mutListener.listen(6156) ? ((count = input.read(bytes)) < -1) : (ListenerUtil.mutListener.listen(6155) ? ((count = input.read(bytes)) == -1) : ((count = input.read(bytes)) != -1))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter64", ++_loopCounter64);
                        if (!ListenerUtil.mutListener.listen(6154)) {
                            output.write(bytes, 0, count);
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(6150)) {
                if (input != null) {
                    if (!ListenerUtil.mutListener.listen(6149)) {
                        input.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6153)) {
                if (output != null) {
                    if (!ListenerUtil.mutListener.listen(6151)) {
                        output.flush();
                    }
                    if (!ListenerUtil.mutListener.listen(6152)) {
                        output.close();
                    }
                }
            }
        }
    }

    public void importData(Uri uri) {
        try {
            final String filename = getFilenameFromUri(uri);
            InputStream input = context.getContentResolver().openInputStream(uri);
            List<ScaleMeasurement> csvScaleMeasurementList = CsvHelper.importFrom(new BufferedReader(new InputStreamReader(input)));
            final int userId = getSelectedScaleUser().getId();
            if (!ListenerUtil.mutListener.listen(6163)) {
                {
                    long _loopCounter65 = 0;
                    for (ScaleMeasurement measurement : csvScaleMeasurementList) {
                        ListenerUtil.loopListener.listen("_loopCounter65", ++_loopCounter65);
                        if (!ListenerUtil.mutListener.listen(6162)) {
                            measurement.setUserId(userId);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6164)) {
                measurementDAO.insertAll(csvScaleMeasurementList);
            }
            if (!ListenerUtil.mutListener.listen(6165)) {
                runUiToastMsg(context.getString(R.string.info_data_imported) + " " + filename);
            }
        } catch (IOException | ParseException e) {
            if (!ListenerUtil.mutListener.listen(6161)) {
                runUiToastMsg(context.getString(R.string.error_importing) + ": " + e.getMessage());
            }
        }
    }

    public boolean exportData(Uri uri) {
        try {
            List<ScaleMeasurement> scaleMeasurementList = getScaleMeasurementList();
            OutputStream output = context.getContentResolver().openOutputStream(uri);
            if (!ListenerUtil.mutListener.listen(6167)) {
                CsvHelper.exportTo(new OutputStreamWriter(output), scaleMeasurementList);
            }
            return true;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(6166)) {
                runUiToastMsg(context.getResources().getString(R.string.error_exporting) + " " + e.getMessage());
            }
        }
        return false;
    }

    public void clearScaleMeasurements(int userId) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(6168)) {
            prefs.edit().putInt("uniqueNumber", 0x00).apply();
        }
        if (!ListenerUtil.mutListener.listen(6169)) {
            syncClearMeasurements();
        }
        if (!ListenerUtil.mutListener.listen(6170)) {
            measurementDAO.deleteAll(userId);
        }
    }

    public int[] getCountsOfMonth(int year) {
        int selectedUserId = getSelectedScaleUserId();
        int[] numOfMonth = new int[12];
        Calendar startCalender = Calendar.getInstance();
        Calendar endCalender = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6180)) {
            {
                long _loopCounter66 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(6179) ? (i >= 12) : (ListenerUtil.mutListener.listen(6178) ? (i <= 12) : (ListenerUtil.mutListener.listen(6177) ? (i > 12) : (ListenerUtil.mutListener.listen(6176) ? (i != 12) : (ListenerUtil.mutListener.listen(6175) ? (i == 12) : (i < 12)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter66", ++_loopCounter66);
                    if (!ListenerUtil.mutListener.listen(6171)) {
                        startCalender.set(year, i, 1, 0, 0, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(6172)) {
                        endCalender.set(year, i, 1, 0, 0, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(6173)) {
                        endCalender.add(Calendar.MONTH, 1);
                    }
                    if (!ListenerUtil.mutListener.listen(6174)) {
                        numOfMonth[i] = measurementDAO.getAllInRange(startCalender.getTime(), endCalender.getTime(), selectedUserId).size();
                    }
                }
            }
        }
        return numOfMonth;
    }

    public List<ScaleMeasurement> getScaleMeasurementOfStartDate(int year, int month, int day) {
        int selectedUserId = getSelectedScaleUserId();
        Calendar startCalender = Calendar.getInstance();
        Calendar endCalender = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6181)) {
            startCalender.set(year, month, day, 0, 0, 0);
        }
        return measurementDAO.getAllInRange(startCalender.getTime(), endCalender.getTime(), selectedUserId);
    }

    public List<ScaleMeasurement> getScaleMeasurementOfDay(int year, int month, int day) {
        int selectedUserId = getSelectedScaleUserId();
        Calendar startCalender = Calendar.getInstance();
        Calendar endCalender = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6182)) {
            startCalender.set(year, month, day, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(6183)) {
            endCalender.set(year, month, day, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(6184)) {
            endCalender.add(Calendar.DAY_OF_MONTH, 1);
        }
        return measurementDAO.getAllInRange(startCalender.getTime(), endCalender.getTime(), selectedUserId);
    }

    public List<ScaleMeasurement> getScaleMeasurementOfMonth(int year, int month) {
        int selectedUserId = getSelectedScaleUserId();
        Calendar startCalender = Calendar.getInstance();
        Calendar endCalender = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6185)) {
            startCalender.set(year, month, 1, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(6186)) {
            endCalender.set(year, month, 1, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(6187)) {
            endCalender.add(Calendar.MONTH, 1);
        }
        return measurementDAO.getAllInRange(startCalender.getTime(), endCalender.getTime(), selectedUserId);
    }

    public List<ScaleMeasurement> getScaleMeasurementOfYear(int year) {
        int selectedUserId = getSelectedScaleUserId();
        Calendar startCalender = Calendar.getInstance();
        Calendar endCalender = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(6188)) {
            startCalender.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(6193)) {
            endCalender.set((ListenerUtil.mutListener.listen(6192) ? (year % 1) : (ListenerUtil.mutListener.listen(6191) ? (year / 1) : (ListenerUtil.mutListener.listen(6190) ? (year * 1) : (ListenerUtil.mutListener.listen(6189) ? (year - 1) : (year + 1))))), Calendar.JANUARY, 1, 0, 0, 0);
        }
        return measurementDAO.getAllInRange(startCalender.getTime(), endCalender.getTime(), selectedUserId);
    }

    public void connectToBluetoothDeviceDebugMode(String hwAddress, Handler callbackBtHandler) {
        if (!ListenerUtil.mutListener.listen(6194)) {
            Timber.d("Trying to connect to bluetooth device [%s] in debug mode", hwAddress);
        }
        if (!ListenerUtil.mutListener.listen(6195)) {
            disconnectFromBluetoothDevice();
        }
        if (!ListenerUtil.mutListener.listen(6196)) {
            btDeviceDriver = BluetoothFactory.createDebugDriver(context);
        }
        if (!ListenerUtil.mutListener.listen(6197)) {
            btDeviceDriver.registerCallbackHandler(callbackBtHandler);
        }
        if (!ListenerUtil.mutListener.listen(6198)) {
            btDeviceDriver.connect(hwAddress);
        }
    }

    public boolean connectToBluetoothDevice(String deviceName, String hwAddress, Handler callbackBtHandler) {
        if (!ListenerUtil.mutListener.listen(6199)) {
            Timber.d("Trying to connect to bluetooth device [%s] (%s)", hwAddress, deviceName);
        }
        if (!ListenerUtil.mutListener.listen(6200)) {
            disconnectFromBluetoothDevice();
        }
        if (!ListenerUtil.mutListener.listen(6201)) {
            btDeviceDriver = BluetoothFactory.createDeviceDriver(context, deviceName);
        }
        if (!ListenerUtil.mutListener.listen(6202)) {
            if (btDeviceDriver == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(6203)) {
            btDeviceDriver.registerCallbackHandler(callbackBtHandler);
        }
        if (!ListenerUtil.mutListener.listen(6204)) {
            btDeviceDriver.connect(hwAddress);
        }
        return true;
    }

    public boolean disconnectFromBluetoothDevice() {
        if (!ListenerUtil.mutListener.listen(6205)) {
            if (btDeviceDriver == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(6206)) {
            Timber.d("Disconnecting from bluetooth device");
        }
        if (!ListenerUtil.mutListener.listen(6207)) {
            btDeviceDriver.disconnect();
        }
        if (!ListenerUtil.mutListener.listen(6208)) {
            btDeviceDriver = null;
        }
        return true;
    }

    public LiveData<List<ScaleMeasurement>> getScaleMeasurementsLiveData() {
        int selectedUserId = getSelectedScaleUserId();
        return measurementDAO.getAllAsLiveData(selectedUserId);
    }

    // As getScaleUserList(), but as a Cursor for export via a Content Provider.
    public Cursor getScaleUserListCursor() {
        return userDAO.selectAll();
    }

    // As getScaleMeasurementList(), but as a Cursor for export via a Content Provider.
    public Cursor getScaleMeasurementListCursor(long userId) {
        return measurementDAO.selectAll(userId);
    }

    private void runUiToastMsg(String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (!ListenerUtil.mutListener.listen(6210)) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(6209)) {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void syncInsertMeasurement(ScaleMeasurement scaleMeasurement) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(6211)) {
            intent.setComponent(new ComponentName("com.health.openscale.sync", "com.health.openscale.sync.core.service.SyncService"));
        }
        if (!ListenerUtil.mutListener.listen(6212)) {
            intent.putExtra("mode", "insert");
        }
        if (!ListenerUtil.mutListener.listen(6213)) {
            intent.putExtra("userId", scaleMeasurement.getUserId());
        }
        if (!ListenerUtil.mutListener.listen(6214)) {
            intent.putExtra("weight", scaleMeasurement.getWeight());
        }
        if (!ListenerUtil.mutListener.listen(6215)) {
            intent.putExtra("date", scaleMeasurement.getDateTime().getTime());
        }
        if (!ListenerUtil.mutListener.listen(6216)) {
            ContextCompat.startForegroundService(context, intent);
        }
    }

    private void syncUpdateMeasurement(ScaleMeasurement scaleMeasurement) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(6217)) {
            intent.setComponent(new ComponentName("com.health.openscale.sync", "com.health.openscale.sync.core.service.SyncService"));
        }
        if (!ListenerUtil.mutListener.listen(6218)) {
            intent.putExtra("mode", "update");
        }
        if (!ListenerUtil.mutListener.listen(6219)) {
            intent.putExtra("userId", scaleMeasurement.getUserId());
        }
        if (!ListenerUtil.mutListener.listen(6220)) {
            intent.putExtra("weight", scaleMeasurement.getWeight());
        }
        if (!ListenerUtil.mutListener.listen(6221)) {
            intent.putExtra("date", scaleMeasurement.getDateTime().getTime());
        }
        if (!ListenerUtil.mutListener.listen(6222)) {
            ContextCompat.startForegroundService(context, intent);
        }
    }

    private void syncDeleteMeasurement(Date date) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(6223)) {
            intent.setComponent(new ComponentName("com.health.openscale.sync", "com.health.openscale.sync.core.service.SyncService"));
        }
        if (!ListenerUtil.mutListener.listen(6224)) {
            intent.putExtra("mode", "delete");
        }
        if (!ListenerUtil.mutListener.listen(6225)) {
            intent.putExtra("date", date.getTime());
        }
        if (!ListenerUtil.mutListener.listen(6226)) {
            ContextCompat.startForegroundService(context, intent);
        }
    }

    private void syncClearMeasurements() {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(6227)) {
            intent.setComponent(new ComponentName("com.health.openscale.sync", "com.health.openscale.sync.core.service.SyncService"));
        }
        if (!ListenerUtil.mutListener.listen(6228)) {
            intent.putExtra("mode", "clear");
        }
        if (!ListenerUtil.mutListener.listen(6229)) {
            ContextCompat.startForegroundService(context, intent);
        }
    }

    public ScaleMeasurementDAO getScaleMeasurementDAO() {
        return measurementDAO;
    }

    public ScaleUserDAO getScaleUserDAO() {
        return userDAO;
    }
}
