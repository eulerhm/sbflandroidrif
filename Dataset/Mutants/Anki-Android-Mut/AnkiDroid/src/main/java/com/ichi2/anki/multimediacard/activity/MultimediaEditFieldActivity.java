/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.multimediacard.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import com.ichi2.anki.AnkiActivity;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.anki.multimediacard.IMultimediaEditableNote;
import com.ichi2.anki.multimediacard.fields.AudioClipField;
import com.ichi2.anki.multimediacard.fields.AudioRecordingField;
import com.ichi2.anki.multimediacard.fields.BasicControllerFactory;
import com.ichi2.anki.multimediacard.fields.BasicImageFieldController;
import com.ichi2.anki.multimediacard.fields.EFieldType;
import com.ichi2.anki.multimediacard.fields.IControllerFactory;
import com.ichi2.anki.multimediacard.fields.IField;
import com.ichi2.anki.multimediacard.fields.IFieldController;
import com.ichi2.anki.multimediacard.fields.ImageField;
import com.ichi2.anki.multimediacard.fields.TextField;
import com.ichi2.utils.Permissions;
import java.io.File;
import java.text.DecimalFormat;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MultimediaEditFieldActivity extends AnkiActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public static final String EXTRA_RESULT_FIELD = "edit.field.result.field";

    public static final String EXTRA_RESULT_FIELD_INDEX = "edit.field.result.field.index";

    public static final String EXTRA_FIELD_INDEX = "multim.card.ed.extra.field.index";

    public static final String EXTRA_FIELD = "multim.card.ed.extra.field";

    public static final String EXTRA_WHOLE_NOTE = "multim.card.ed.extra.whole.note";

    private static final String BUNDLE_KEY_SHUT_OFF = "key.edit.field.shut.off";

    private static final int REQUEST_AUDIO_PERMISSION = 0;

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    // 1MB in bytes
    public static final int sImageLimit = 1024 * 1024;

    private IField mField;

    private IMultimediaEditableNote mNote;

    private int mFieldIndex;

    private IFieldController mFieldController;

    /**
     * Cached copy of the current request to change a field
     * Used to access past state from OnRequestPermissionsResultCallback
     */
    private ChangeUIRequest mCurrentChangeRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1195)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1196)) {
            super.onCreate(savedInstanceState);
        }
        Bundle controllerBundle = null;
        if (!ListenerUtil.mutListener.listen(1203)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(1197)) {
                    Timber.i("onCreate - saved bundle exists");
                }
                boolean b = savedInstanceState.getBoolean(BUNDLE_KEY_SHUT_OFF, false);
                if (!ListenerUtil.mutListener.listen(1198)) {
                    controllerBundle = savedInstanceState.getBundle("controllerBundle");
                }
                if (!ListenerUtil.mutListener.listen(1202)) {
                    if ((ListenerUtil.mutListener.listen(1199) ? (controllerBundle == null || b) : (controllerBundle == null && b))) {
                        if (!ListenerUtil.mutListener.listen(1200)) {
                            Timber.i("onCreate - saved bundle has BUNDLE_KEY_SHUT_OFF and no controller bundle, terminating");
                        }
                        if (!ListenerUtil.mutListener.listen(1201)) {
                            finishCancel();
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1204)) {
            setContentView(R.layout.multimedia_edit_field_activity);
        }
        View mainView = findViewById(android.R.id.content);
        if (!ListenerUtil.mutListener.listen(1205)) {
            enableToolbar(mainView);
        }
        Intent intent = this.getIntent();
        if (!ListenerUtil.mutListener.listen(1206)) {
            mField = getFieldFromIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(1207)) {
            mNote = (IMultimediaEditableNote) intent.getSerializableExtra(EXTRA_WHOLE_NOTE);
        }
        if (!ListenerUtil.mutListener.listen(1208)) {
            mFieldIndex = intent.getIntExtra(EXTRA_FIELD_INDEX, 0);
        }
        if (!ListenerUtil.mutListener.listen(1211)) {
            if (mField == null) {
                if (!ListenerUtil.mutListener.listen(1209)) {
                    UIUtils.showThemedToast(this, getString(R.string.multimedia_editor_failed), false);
                }
                if (!ListenerUtil.mutListener.listen(1210)) {
                    finishCancel();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1212)) {
            recreateEditingUi(ChangeUIRequest.init(mField), controllerBundle);
        }
    }

    @VisibleForTesting
    public static IField getFieldFromIntent(Intent intent) {
        return (IField) intent.getExtras().getSerializable(EXTRA_FIELD);
    }

    private void finishCancel() {
        if (!ListenerUtil.mutListener.listen(1213)) {
            Timber.d("Completing activity via finishCancel()");
        }
        Intent resultData = new Intent();
        if (!ListenerUtil.mutListener.listen(1214)) {
            setResult(RESULT_CANCELED, resultData);
        }
        if (!ListenerUtil.mutListener.listen(1215)) {
            finishWithoutAnimation();
        }
    }

    private boolean performPermissionRequest(IField field) {
        if (!ListenerUtil.mutListener.listen(1219)) {
            // Request permission to record if audio field
            if ((ListenerUtil.mutListener.listen(1216) ? (field instanceof AudioRecordingField || !Permissions.canRecordAudio(this)) : (field instanceof AudioRecordingField && !Permissions.canRecordAudio(this)))) {
                if (!ListenerUtil.mutListener.listen(1217)) {
                    Timber.d("Requesting Audio Permissions");
                }
                if (!ListenerUtil.mutListener.listen(1218)) {
                    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO }, REQUEST_AUDIO_PERMISSION);
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(1223)) {
            // Request permission to use the camera if image field
            if ((ListenerUtil.mutListener.listen(1220) ? (field instanceof ImageField || !Permissions.canUseCamera(this)) : (field instanceof ImageField && !Permissions.canUseCamera(this)))) {
                if (!ListenerUtil.mutListener.listen(1221)) {
                    Timber.d("Requesting Camera Permissions");
                }
                if (!ListenerUtil.mutListener.listen(1222)) {
                    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, REQUEST_CAMERA_PERMISSION);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Sets various properties required for IFieldController to be in a valid state
     */
    private void setupUIController(IFieldController fieldController, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1224)) {
            fieldController.setField(mField);
        }
        if (!ListenerUtil.mutListener.listen(1225)) {
            fieldController.setFieldIndex(mFieldIndex);
        }
        if (!ListenerUtil.mutListener.listen(1226)) {
            fieldController.setNote(mNote);
        }
        if (!ListenerUtil.mutListener.listen(1227)) {
            fieldController.setEditingActivity(this);
        }
        if (!ListenerUtil.mutListener.listen(1228)) {
            fieldController.loadInstanceState(savedInstanceState);
        }
    }

    private void recreateEditingUi(ChangeUIRequest newUI) {
        if (!ListenerUtil.mutListener.listen(1229)) {
            this.recreateEditingUi(newUI, null);
        }
    }

    private void recreateEditingUi(ChangeUIRequest newUI, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1230)) {
            Timber.d("recreateEditingUi()");
        }
        if (!ListenerUtil.mutListener.listen(1231)) {
            // Permissions are checked async, save our current state to allow continuation
            mCurrentChangeRequest = newUI;
        }
        if (!ListenerUtil.mutListener.listen(1234)) {
            // As we only get here a second time if we have the required permissions
            if ((ListenerUtil.mutListener.listen(1232) ? (newUI.getRequiresPermissionCheck() || performPermissionRequest(newUI.getField())) : (newUI.getRequiresPermissionCheck() && performPermissionRequest(newUI.getField())))) {
                if (!ListenerUtil.mutListener.listen(1233)) {
                    newUI.markAsPermissionRequested();
                }
                return;
            }
        }
        IControllerFactory controllerFactory = BasicControllerFactory.getInstance();
        IFieldController fieldController = controllerFactory.createControllerForField(newUI.getField());
        if (!ListenerUtil.mutListener.listen(1237)) {
            if (fieldController == null) {
                if (!ListenerUtil.mutListener.listen(1235)) {
                    Timber.w("Field controller creation failed");
                }
                if (!ListenerUtil.mutListener.listen(1236)) {
                    UIRecreationHandler.onControllerCreationFailed(newUI, this);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1238)) {
            UIRecreationHandler.onPreFieldControllerReplacement(mFieldController);
        }
        if (!ListenerUtil.mutListener.listen(1239)) {
            mFieldController = fieldController;
        }
        if (!ListenerUtil.mutListener.listen(1240)) {
            mField = newUI.getField();
        }
        if (!ListenerUtil.mutListener.listen(1241)) {
            setupUIController(mFieldController, savedInstanceState);
        }
        LinearLayout linearLayout = findViewById(R.id.LinearLayoutInScrollViewFieldEdit);
        if (!ListenerUtil.mutListener.listen(1242)) {
            linearLayout.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(1243)) {
            mFieldController.createUI(this, linearLayout);
        }
        if (!ListenerUtil.mutListener.listen(1244)) {
            UIRecreationHandler.onPostUICreation(newUI, this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(1245)) {
            Timber.d("onCreateOptionsMenu() - mField.getType() = %s", mField.getType());
        }
        if (!ListenerUtil.mutListener.listen(1246)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_edit_text, menu);
        }
        if (!ListenerUtil.mutListener.listen(1247)) {
            menu.findItem(R.id.multimedia_edit_field_to_text).setVisible(mField.getType() != EFieldType.TEXT);
        }
        if (!ListenerUtil.mutListener.listen(1248)) {
            menu.findItem(R.id.multimedia_edit_field_to_audio).setVisible(mField.getType() != EFieldType.AUDIO_RECORDING);
        }
        if (!ListenerUtil.mutListener.listen(1249)) {
            menu.findItem(R.id.multimedia_edit_field_to_audio_clip).setVisible(mField.getType() != EFieldType.AUDIO_CLIP);
        }
        if (!ListenerUtil.mutListener.listen(1250)) {
            menu.findItem(R.id.multimedia_edit_field_to_image).setVisible(mField.getType() != EFieldType.IMAGE);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(1261)) {
            if (itemId == R.id.multimedia_edit_field_to_text) {
                if (!ListenerUtil.mutListener.listen(1259)) {
                    Timber.i("To text field button pressed");
                }
                if (!ListenerUtil.mutListener.listen(1260)) {
                    toTextField();
                }
                return true;
            } else if (itemId == R.id.multimedia_edit_field_to_image) {
                if (!ListenerUtil.mutListener.listen(1257)) {
                    Timber.i("To image button pressed");
                }
                if (!ListenerUtil.mutListener.listen(1258)) {
                    toImageField();
                }
                return true;
            } else if (itemId == R.id.multimedia_edit_field_to_audio) {
                if (!ListenerUtil.mutListener.listen(1255)) {
                    Timber.i("To audio recording button pressed");
                }
                if (!ListenerUtil.mutListener.listen(1256)) {
                    toAudioRecordingField();
                }
                return true;
            } else if (itemId == R.id.multimedia_edit_field_to_audio_clip) {
                if (!ListenerUtil.mutListener.listen(1253)) {
                    Timber.i("To audio clip button pressed");
                }
                if (!ListenerUtil.mutListener.listen(1254)) {
                    toAudioClipField();
                }
                return true;
            } else if (itemId == R.id.multimedia_edit_field_done) {
                if (!ListenerUtil.mutListener.listen(1251)) {
                    Timber.i("Save button pressed");
                }
                if (!ListenerUtil.mutListener.listen(1252)) {
                    done();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    protected void done() {
        if (!ListenerUtil.mutListener.listen(1262)) {
            mFieldController.onDone();
        }
        boolean bChangeToText = false;
        if (!ListenerUtil.mutListener.listen(1288)) {
            if (mField.getType() == EFieldType.IMAGE) {
                if (!ListenerUtil.mutListener.listen(1269)) {
                    if (mField.getImagePath() == null) {
                        if (!ListenerUtil.mutListener.listen(1268)) {
                            bChangeToText = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1287)) {
                    if (!bChangeToText) {
                        File f = new File(mField.getImagePath());
                        if (!ListenerUtil.mutListener.listen(1286)) {
                            if (!f.exists()) {
                                if (!ListenerUtil.mutListener.listen(1285)) {
                                    bChangeToText = true;
                                }
                            } else {
                                long length = f.length();
                                if (!ListenerUtil.mutListener.listen(1284)) {
                                    if ((ListenerUtil.mutListener.listen(1274) ? (length >= sImageLimit) : (ListenerUtil.mutListener.listen(1273) ? (length <= sImageLimit) : (ListenerUtil.mutListener.listen(1272) ? (length < sImageLimit) : (ListenerUtil.mutListener.listen(1271) ? (length != sImageLimit) : (ListenerUtil.mutListener.listen(1270) ? (length == sImageLimit) : (length > sImageLimit))))))) {
                                        if (!ListenerUtil.mutListener.listen(1283)) {
                                            showLargeFileCropDialog((float) ((ListenerUtil.mutListener.listen(1282) ? ((ListenerUtil.mutListener.listen(1278) ? (1.0 % length) : (ListenerUtil.mutListener.listen(1277) ? (1.0 / length) : (ListenerUtil.mutListener.listen(1276) ? (1.0 - length) : (ListenerUtil.mutListener.listen(1275) ? (1.0 + length) : (1.0 * length))))) % sImageLimit) : (ListenerUtil.mutListener.listen(1281) ? ((ListenerUtil.mutListener.listen(1278) ? (1.0 % length) : (ListenerUtil.mutListener.listen(1277) ? (1.0 / length) : (ListenerUtil.mutListener.listen(1276) ? (1.0 - length) : (ListenerUtil.mutListener.listen(1275) ? (1.0 + length) : (1.0 * length))))) * sImageLimit) : (ListenerUtil.mutListener.listen(1280) ? ((ListenerUtil.mutListener.listen(1278) ? (1.0 % length) : (ListenerUtil.mutListener.listen(1277) ? (1.0 / length) : (ListenerUtil.mutListener.listen(1276) ? (1.0 - length) : (ListenerUtil.mutListener.listen(1275) ? (1.0 + length) : (1.0 * length))))) - sImageLimit) : (ListenerUtil.mutListener.listen(1279) ? ((ListenerUtil.mutListener.listen(1278) ? (1.0 % length) : (ListenerUtil.mutListener.listen(1277) ? (1.0 / length) : (ListenerUtil.mutListener.listen(1276) ? (1.0 - length) : (ListenerUtil.mutListener.listen(1275) ? (1.0 + length) : (1.0 * length))))) + sImageLimit) : ((ListenerUtil.mutListener.listen(1278) ? (1.0 % length) : (ListenerUtil.mutListener.listen(1277) ? (1.0 / length) : (ListenerUtil.mutListener.listen(1276) ? (1.0 - length) : (ListenerUtil.mutListener.listen(1275) ? (1.0 + length) : (1.0 * length))))) / sImageLimit)))))));
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (mField.getType() == EFieldType.AUDIO_RECORDING) {
                if (!ListenerUtil.mutListener.listen(1264)) {
                    if (mField.getAudioPath() == null) {
                        if (!ListenerUtil.mutListener.listen(1263)) {
                            bChangeToText = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1267)) {
                    if (!bChangeToText) {
                        File f = new File(mField.getAudioPath());
                        if (!ListenerUtil.mutListener.listen(1266)) {
                            if (!f.exists()) {
                                if (!ListenerUtil.mutListener.listen(1265)) {
                                    bChangeToText = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1290)) {
            if (bChangeToText) {
                if (!ListenerUtil.mutListener.listen(1289)) {
                    mField = new TextField();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1291)) {
            saveAndExit();
        }
    }

    protected void toAudioRecordingField() {
        if (!ListenerUtil.mutListener.listen(1293)) {
            if (mField.getType() != EFieldType.AUDIO_RECORDING) {
                ChangeUIRequest request = ChangeUIRequest.uiChange(new AudioRecordingField());
                if (!ListenerUtil.mutListener.listen(1292)) {
                    recreateEditingUi(request);
                }
            }
        }
    }

    protected void toAudioClipField() {
        if (!ListenerUtil.mutListener.listen(1295)) {
            if (mField.getType() != EFieldType.AUDIO_CLIP) {
                ChangeUIRequest request = ChangeUIRequest.uiChange(new AudioClipField());
                if (!ListenerUtil.mutListener.listen(1294)) {
                    recreateEditingUi(request);
                }
            }
        }
    }

    protected void toImageField() {
        if (!ListenerUtil.mutListener.listen(1297)) {
            if (mField.getType() != EFieldType.IMAGE) {
                ChangeUIRequest request = ChangeUIRequest.uiChange(new ImageField());
                if (!ListenerUtil.mutListener.listen(1296)) {
                    recreateEditingUi(request);
                }
            }
        }
    }

    protected void toTextField() {
        if (!ListenerUtil.mutListener.listen(1299)) {
            if (mField.getType() != EFieldType.TEXT) {
                ChangeUIRequest request = ChangeUIRequest.uiChange(new TextField());
                if (!ListenerUtil.mutListener.listen(1298)) {
                    recreateEditingUi(request);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1300)) {
            Timber.d("onActivityResult()");
        }
        if (!ListenerUtil.mutListener.listen(1302)) {
            if (mFieldController != null) {
                if (!ListenerUtil.mutListener.listen(1301)) {
                    mFieldController.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1303)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(1304)) {
            supportInvalidateOptionsMenu();
        }
    }

    private void recreateEditingUIUsingCachedRequest() {
        if (!ListenerUtil.mutListener.listen(1305)) {
            Timber.d("recreateEditingUIUsingCachedRequest()");
        }
        if (!ListenerUtil.mutListener.listen(1307)) {
            if (mCurrentChangeRequest == null) {
                if (!ListenerUtil.mutListener.listen(1306)) {
                    cancelActivityWithAssertionFailure("mCurrentChangeRequest should be set before using cached request");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1308)) {
            recreateEditingUi(mCurrentChangeRequest);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(1310)) {
            if (mCurrentChangeRequest == null) {
                if (!ListenerUtil.mutListener.listen(1309)) {
                    cancelActivityWithAssertionFailure("mCurrentChangeRequest should be set before requesting permissions");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1311)) {
            Timber.d("onRequestPermissionsResult. Code: %d", requestCode);
        }
        if (!ListenerUtil.mutListener.listen(1327)) {
            if ((ListenerUtil.mutListener.listen(1322) ? ((ListenerUtil.mutListener.listen(1316) ? (requestCode >= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1315) ? (requestCode <= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1314) ? (requestCode > REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1313) ? (requestCode < REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1312) ? (requestCode != REQUEST_AUDIO_PERMISSION) : (requestCode == REQUEST_AUDIO_PERMISSION)))))) || (ListenerUtil.mutListener.listen(1321) ? (permissions.length >= 1) : (ListenerUtil.mutListener.listen(1320) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(1319) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(1318) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(1317) ? (permissions.length != 1) : (permissions.length == 1))))))) : ((ListenerUtil.mutListener.listen(1316) ? (requestCode >= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1315) ? (requestCode <= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1314) ? (requestCode > REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1313) ? (requestCode < REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(1312) ? (requestCode != REQUEST_AUDIO_PERMISSION) : (requestCode == REQUEST_AUDIO_PERMISSION)))))) && (ListenerUtil.mutListener.listen(1321) ? (permissions.length >= 1) : (ListenerUtil.mutListener.listen(1320) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(1319) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(1318) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(1317) ? (permissions.length != 1) : (permissions.length == 1))))))))) {
                if (!ListenerUtil.mutListener.listen(1324)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (!ListenerUtil.mutListener.listen(1323)) {
                            recreateEditingUIUsingCachedRequest();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(1325)) {
                    UIUtils.showThemedToast(this, getResources().getString(R.string.multimedia_editor_audio_permission_refused), true);
                }
                if (!ListenerUtil.mutListener.listen(1326)) {
                    UIRecreationHandler.onRequiredPermissionDenied(mCurrentChangeRequest, this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1342)) {
            if ((ListenerUtil.mutListener.listen(1338) ? ((ListenerUtil.mutListener.listen(1332) ? (requestCode >= REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1331) ? (requestCode <= REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1330) ? (requestCode > REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1329) ? (requestCode < REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1328) ? (requestCode != REQUEST_CAMERA_PERMISSION) : (requestCode == REQUEST_CAMERA_PERMISSION)))))) || (ListenerUtil.mutListener.listen(1337) ? (permissions.length >= 1) : (ListenerUtil.mutListener.listen(1336) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(1335) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(1334) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(1333) ? (permissions.length != 1) : (permissions.length == 1))))))) : ((ListenerUtil.mutListener.listen(1332) ? (requestCode >= REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1331) ? (requestCode <= REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1330) ? (requestCode > REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1329) ? (requestCode < REQUEST_CAMERA_PERMISSION) : (ListenerUtil.mutListener.listen(1328) ? (requestCode != REQUEST_CAMERA_PERMISSION) : (requestCode == REQUEST_CAMERA_PERMISSION)))))) && (ListenerUtil.mutListener.listen(1337) ? (permissions.length >= 1) : (ListenerUtil.mutListener.listen(1336) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(1335) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(1334) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(1333) ? (permissions.length != 1) : (permissions.length == 1))))))))) {
                if (!ListenerUtil.mutListener.listen(1340)) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        if (!ListenerUtil.mutListener.listen(1339)) {
                            UIUtils.showThemedToast(this, getResources().getString(R.string.multimedia_editor_camera_permission_refused), true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1341)) {
                    // We check permissions to set visibility on the camera button, just recreate
                    recreateEditingUIUsingCachedRequest();
                }
            }
        }
    }

    private void cancelActivityWithAssertionFailure(String logMessage) {
        if (!ListenerUtil.mutListener.listen(1343)) {
            Timber.e(logMessage);
        }
        if (!ListenerUtil.mutListener.listen(1344)) {
            UIUtils.showThemedToast(this, getString(R.string.mutimedia_editor_assertion_failed), false);
        }
        if (!ListenerUtil.mutListener.listen(1345)) {
            finishCancel();
        }
    }

    public void handleFieldChanged(IField newField) {
        if (!ListenerUtil.mutListener.listen(1346)) {
            recreateEditingUi(ChangeUIRequest.fieldChange(newField));
        }
    }

    public void showLargeFileCropDialog(float length) {
        BasicImageFieldController imageFieldController = (BasicImageFieldController) mFieldController;
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String size = decimalFormat.format(length);
        String content = getString(R.string.save_dialog_content, size);
        if (!ListenerUtil.mutListener.listen(1347)) {
            imageFieldController.showCropDialog(content, (dialog, which) -> saveAndExit());
        }
    }

    private void saveAndExit() {
        Intent resultData = new Intent();
        if (!ListenerUtil.mutListener.listen(1348)) {
            resultData.putExtra(EXTRA_RESULT_FIELD, mField);
        }
        if (!ListenerUtil.mutListener.listen(1349)) {
            resultData.putExtra(EXTRA_RESULT_FIELD_INDEX, mFieldIndex);
        }
        if (!ListenerUtil.mutListener.listen(1350)) {
            setResult(RESULT_OK, resultData);
        }
        if (!ListenerUtil.mutListener.listen(1351)) {
            finishWithoutAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1353)) {
            if (mFieldController != null) {
                if (!ListenerUtil.mutListener.listen(1352)) {
                    mFieldController.onDestroy();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1354)) {
            super.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1355)) {
            Timber.d("onSaveInstanceState - saving state");
        }
        if (!ListenerUtil.mutListener.listen(1356)) {
            // Why? I am not really sure. Perhaps to avoid terrible bugs due to not implementing things correctly?
            outState.putBoolean(BUNDLE_KEY_SHUT_OFF, true);
        }
        if (!ListenerUtil.mutListener.listen(1359)) {
            // If this bundle is not null, on restore, we should continue across Activity restart.
            if (mFieldController != null) {
                Bundle controllerBundle = mFieldController.saveInstanceState();
                if (!ListenerUtil.mutListener.listen(1358)) {
                    if (controllerBundle != null) {
                        if (!ListenerUtil.mutListener.listen(1357)) {
                            outState.putBundle("controllerBundle", mFieldController.saveInstanceState());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1360)) {
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Intermediate class to hold state for the onRequestPermissionsResult callback
     */
    private static final class ChangeUIRequest {

        private final IField newField;

        private final int state;

        private boolean mRequiresPermissionCheck = true;

        /**
         * Initial request when activity is created
         */
        public static final int ACTIVITY_LOAD = 0;

        /**
         * A change in UI via the menu options. Cancellable
         */
        public static final int UI_CHANGE = 1;

        /**
         * A change in UI via access to the activity. Not (yet) cancellable
         */
        public static final int EXTERNAL_FIELD_CHANGE = 2;

        private ChangeUIRequest(IField field, int state) {
            this.newField = field;
            this.state = state;
        }

        private IField getField() {
            return newField;
        }

        private static ChangeUIRequest init(@NonNull IField field) {
            return new ChangeUIRequest(field, ACTIVITY_LOAD);
        }

        private static ChangeUIRequest uiChange(IField field) {
            return new ChangeUIRequest(field, UI_CHANGE);
        }

        private static ChangeUIRequest fieldChange(IField field) {
            return new ChangeUIRequest(field, EXTERNAL_FIELD_CHANGE);
        }

        private boolean getRequiresPermissionCheck() {
            return mRequiresPermissionCheck;
        }

        private void markAsPermissionRequested() {
            if (!ListenerUtil.mutListener.listen(1361)) {
                mRequiresPermissionCheck = false;
            }
        }

        private int getState() {
            return state;
        }
    }

    /**
     * Class to contain logic relating to decisions made when recreating a UI.
     * Can later be converted to a non-static class to allow testing of the logic.
     */
    private static final class UIRecreationHandler {

        /**
         * Raised just before the field controller is replaced
         */
        private static void onPreFieldControllerReplacement(IFieldController previousFieldController) {
            if (!ListenerUtil.mutListener.listen(1362)) {
                Timber.d("onPreFieldControllerReplacement");
            }
            if (!ListenerUtil.mutListener.listen(1363)) {
                // on init, we don't need to do anything
                if (previousFieldController == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1364)) {
                // Otherwise, clean up the previous screen.
                previousFieldController.onFocusLost();
            }
        }

        /**
         * Raised when we were supplied with a field that could not generate a UI controller
         * Currently: We used a field for which we didn't know how to generate the UI
         */
        private static void onControllerCreationFailed(ChangeUIRequest request, MultimediaEditFieldActivity activity) {
            if (!ListenerUtil.mutListener.listen(1365)) {
                Timber.d("onControllerCreationFailed. State: %d", request.getState());
            }
            if (!ListenerUtil.mutListener.listen(1368)) {
                switch(request.getState()) {
                    case ChangeUIRequest.ACTIVITY_LOAD:
                    case ChangeUIRequest.EXTERNAL_FIELD_CHANGE:
                        if (!ListenerUtil.mutListener.listen(1366)) {
                            // TODO: (Optional) change in functionality. Previously we'd be left with a menu, but no UI.
                            activity.finishCancel();
                        }
                        break;
                    case ChangeUIRequest.UI_CHANGE:
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(1367)) {
                            Timber.e("onControllerCreationFailed: Unhandled state: %s", request.getState());
                        }
                        break;
                }
            }
        }

        private static void onPostUICreation(ChangeUIRequest request, MultimediaEditFieldActivity activity) {
            if (!ListenerUtil.mutListener.listen(1369)) {
                Timber.d("onPostUICreation. State: %d", request.getState());
            }
            if (!ListenerUtil.mutListener.listen(1372)) {
                switch(request.getState()) {
                    case ChangeUIRequest.UI_CHANGE:
                    case ChangeUIRequest.EXTERNAL_FIELD_CHANGE:
                        if (!ListenerUtil.mutListener.listen(1370)) {
                            activity.supportInvalidateOptionsMenu();
                        }
                        break;
                    case ChangeUIRequest.ACTIVITY_LOAD:
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(1371)) {
                            Timber.e("onPostUICreation: Unhandled state: %s", request.getState());
                        }
                        break;
                }
            }
        }

        private static void onRequiredPermissionDenied(ChangeUIRequest request, MultimediaEditFieldActivity activity) {
            if (!ListenerUtil.mutListener.listen(1373)) {
                Timber.d("onRequiredPermissionDenied. State: %d", request.getState());
            }
            if (!ListenerUtil.mutListener.listen(1378)) {
                switch(request.state) {
                    case ChangeUIRequest.ACTIVITY_LOAD:
                        if (!ListenerUtil.mutListener.listen(1374)) {
                            activity.finishCancel();
                        }
                        break;
                    case ChangeUIRequest.UI_CHANGE:
                        return;
                    case ChangeUIRequest.EXTERNAL_FIELD_CHANGE:
                        if (!ListenerUtil.mutListener.listen(1375)) {
                            activity.recreateEditingUIUsingCachedRequest();
                        }
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(1376)) {
                            Timber.e("onRequiredPermissionDenied: Unhandled state: %s", request.getState());
                        }
                        if (!ListenerUtil.mutListener.listen(1377)) {
                            activity.finishCancel();
                        }
                        break;
                }
            }
        }
    }

    @VisibleForTesting
    IFieldController getFieldController() {
        return mFieldController;
    }
}
