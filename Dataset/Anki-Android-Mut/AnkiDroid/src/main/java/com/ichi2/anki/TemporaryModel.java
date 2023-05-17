/**
 * ************************************************************************************
 *                                                                                       *
 *  Copyright (c) 2020 Mike Hardy <mike@mikehardy.net>                                   *
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
package com.ichi2.anki;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import timber.log.Timber;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListener;
import com.ichi2.async.TaskManager;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Model;
import com.ichi2.utils.JSONObject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TemporaryModel {

    public enum ChangeType {

        ADD, DELETE
    }

    public static final String INTENT_MODEL_FILENAME = "editedModelFilename";

    private ArrayList<Object[]> mTemplateChanges = new ArrayList<>();

    private String mEditedModelFileName = null;

    @NonNull
    private final Model mEditedModel;

    public TemporaryModel(@NonNull Model model) {
        if (!ListenerUtil.mutListener.listen(12081)) {
            Timber.d("Constructor called with model");
        }
        mEditedModel = model;
    }

    /**
     * Load the TemporaryModel from the filename included in a Bundle
     *
     * @param bundle a Bundle that should contain persisted JSON under INTENT_MODEL_FILENAME key
     * @return re-hydrated TemporaryModel or null if there was a problem, null means should reload from database
     */
    @Nullable
    public static TemporaryModel fromBundle(Bundle bundle) {
        String mEditedModelFileName = bundle.getString(INTENT_MODEL_FILENAME);
        if (!ListenerUtil.mutListener.listen(12083)) {
            // Bundle.getString is @Nullable, so we have to check.
            if (mEditedModelFileName == null) {
                if (!ListenerUtil.mutListener.listen(12082)) {
                    Timber.d("fromBundle() - model file name under key %s", INTENT_MODEL_FILENAME);
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(12084)) {
            Timber.d("onCreate() loading saved model file %s", mEditedModelFileName);
        }
        Model tempModelJSON;
        try {
            tempModelJSON = getTempModel((mEditedModelFileName));
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(12085)) {
                Timber.w(e, "Unable to load saved model file");
            }
            return null;
        }
        TemporaryModel model = new TemporaryModel(tempModelJSON);
        if (!ListenerUtil.mutListener.listen(12086)) {
            model.loadTemplateChanges(bundle);
        }
        return model;
    }

    public Bundle toBundle() {
        Bundle outState = new Bundle();
        if (!ListenerUtil.mutListener.listen(12087)) {
            outState.putString(INTENT_MODEL_FILENAME, saveTempModel(AnkiDroidApp.getInstance().getApplicationContext(), mEditedModel));
        }
        if (!ListenerUtil.mutListener.listen(12088)) {
            outState.putSerializable("mTemplateChanges", mTemplateChanges);
        }
        return outState;
    }

    @SuppressWarnings("unchecked")
    private void loadTemplateChanges(Bundle bundle) {
        try {
            if (!ListenerUtil.mutListener.listen(12090)) {
                mTemplateChanges = (ArrayList<Object[]>) bundle.getSerializable("mTemplateChanges");
            }
        } catch (ClassCastException e) {
            if (!ListenerUtil.mutListener.listen(12089)) {
                Timber.e(e, "Unexpected cast failure");
            }
        }
    }

    public JSONObject getTemplate(int ord) {
        if (!ListenerUtil.mutListener.listen(12091)) {
            Timber.d("getTemplate() on ordinal %s", ord);
        }
        return mEditedModel.getJSONArray("tmpls").getJSONObject(ord);
    }

    public int getTemplateCount() {
        return mEditedModel.getJSONArray("tmpls").length();
    }

    public long getModelId() {
        return mEditedModel.getLong("id");
    }

    public void updateCss(String css) {
        if (!ListenerUtil.mutListener.listen(12092)) {
            mEditedModel.put("css", css);
        }
    }

    public String getCss() {
        return mEditedModel.getString("css");
    }

    public void updateTemplate(int ordinal, JSONObject template) {
        if (!ListenerUtil.mutListener.listen(12093)) {
            mEditedModel.getJSONArray("tmpls").put(ordinal, template);
        }
    }

    public void addNewTemplate(JSONObject newTemplate) {
        if (!ListenerUtil.mutListener.listen(12094)) {
            Timber.d("addNewTemplate()");
        }
        if (!ListenerUtil.mutListener.listen(12095)) {
            addTemplateChange(ChangeType.ADD, newTemplate.getInt("ord"));
        }
    }

    public void removeTemplate(int ord) {
        if (!ListenerUtil.mutListener.listen(12096)) {
            Timber.d("removeTemplate() on ordinal %s", ord);
        }
        if (!ListenerUtil.mutListener.listen(12097)) {
            addTemplateChange(ChangeType.DELETE, ord);
        }
    }

    public void saveToDatabase(CardTemplateEditor.CardTemplateFragment.SaveModelAndExitHandler listener) {
        if (!ListenerUtil.mutListener.listen(12098)) {
            Timber.d("saveToDatabase() called");
        }
        if (!ListenerUtil.mutListener.listen(12099)) {
            dumpChanges();
        }
        if (!ListenerUtil.mutListener.listen(12100)) {
            TemporaryModel.clearTempModelFiles();
        }
        if (!ListenerUtil.mutListener.listen(12101)) {
            TaskManager.launchCollectionTask(new CollectionTask.SaveModel(mEditedModel, getAdjustedTemplateChanges()), listener);
        }
    }

    public Model getModel() {
        return mEditedModel;
    }

    public void setEditedModelFileName(String fileName) {
        if (!ListenerUtil.mutListener.listen(12102)) {
            mEditedModelFileName = fileName;
        }
    }

    public String getEditedModelFileName() {
        return mEditedModelFileName;
    }

    /**
     * Save the current model to a temp file in the application internal cache directory
     * @return String representing the absolute path of the saved file, or null if there was a problem
     */
    @Nullable
    public static String saveTempModel(@NonNull Context context, @NonNull JSONObject tempModel) {
        if (!ListenerUtil.mutListener.listen(12103)) {
            Timber.d("saveTempModel() saving tempModel");
        }
        File tempModelFile;
        try (ByteArrayInputStream source = new ByteArrayInputStream(tempModel.toString().getBytes())) {
            tempModelFile = File.createTempFile("editedTemplate", ".json", context.getCacheDir());
            if (!ListenerUtil.mutListener.listen(12105)) {
                CompatHelper.getCompat().copyFile(source, tempModelFile.getAbsolutePath());
            }
        } catch (IOException ioe) {
            if (!ListenerUtil.mutListener.listen(12104)) {
                Timber.e(ioe, "Unable to create+write temp file for model");
            }
            return null;
        }
        return tempModelFile.getAbsolutePath();
    }

    /**
     * Get the model temporarily saved into the file represented by the given path
     * @return JSONObject holding the model, or null if there was a problem
     */
    public static Model getTempModel(@NonNull String tempModelFileName) throws IOException {
        if (!ListenerUtil.mutListener.listen(12106)) {
            Timber.d("getTempModel() fetching tempModel %s", tempModelFileName);
        }
        try (ByteArrayOutputStream target = new ByteArrayOutputStream()) {
            if (!ListenerUtil.mutListener.listen(12108)) {
                CompatHelper.getCompat().copyFile(tempModelFileName, target);
            }
            return new Model(target.toString());
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(12107)) {
                Timber.e(e, "Unable to read+parse tempModel from file %s", tempModelFileName);
            }
            throw e;
        }
    }

    /**
     * Clear any temp model files saved into internal cache directory
     */
    public static int clearTempModelFiles() {
        int deleteCount = 0;
        if (!ListenerUtil.mutListener.listen(12115)) {
            {
                long _loopCounter191 = 0;
                for (File c : AnkiDroidApp.getInstance().getCacheDir().listFiles()) {
                    ListenerUtil.loopListener.listen("_loopCounter191", ++_loopCounter191);
                    String absolutePath = c.getAbsolutePath();
                    if (!ListenerUtil.mutListener.listen(12114)) {
                        if ((ListenerUtil.mutListener.listen(12109) ? (absolutePath.contains("editedTemplate") || absolutePath.endsWith("json")) : (absolutePath.contains("editedTemplate") && absolutePath.endsWith("json")))) {
                            if (!ListenerUtil.mutListener.listen(12113)) {
                                if (!c.delete()) {
                                    if (!ListenerUtil.mutListener.listen(12112)) {
                                        Timber.w("Unable to delete temp file %s", c.getAbsolutePath());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(12110)) {
                                        deleteCount++;
                                    }
                                    if (!ListenerUtil.mutListener.listen(12111)) {
                                        Timber.d("Deleted temp model file %s", c.getAbsolutePath());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return deleteCount;
    }

    /**
     * Template deletes shift card ordinals in the database. To operate without saving, we must keep track to apply in order.
     * In addition, we don't want to persist a template add just to delete it later, so we combine those if they happen
     */
    public void addTemplateChange(ChangeType type, int ordinal) {
        if (!ListenerUtil.mutListener.listen(12116)) {
            Timber.d("addTemplateChange() type %s for ordinal %s", type, ordinal);
        }
        ArrayList<Object[]> templateChanges = getTemplateChanges();
        Object[] change = new Object[] { ordinal, type };
        if (!ListenerUtil.mutListener.listen(12150)) {
            // If we are deleting something we added but have not saved, edit it out of the change list
            if (type == ChangeType.DELETE) {
                int ordinalAdjustment = 0;
                if (!ListenerUtil.mutListener.listen(12149)) {
                    {
                        long _loopCounter192 = 0;
                        for (int i = (ListenerUtil.mutListener.listen(12148) ? (templateChanges.size() % 1) : (ListenerUtil.mutListener.listen(12147) ? (templateChanges.size() / 1) : (ListenerUtil.mutListener.listen(12146) ? (templateChanges.size() * 1) : (ListenerUtil.mutListener.listen(12145) ? (templateChanges.size() + 1) : (templateChanges.size() - 1))))); (ListenerUtil.mutListener.listen(12144) ? (i <= 0) : (ListenerUtil.mutListener.listen(12143) ? (i > 0) : (ListenerUtil.mutListener.listen(12142) ? (i < 0) : (ListenerUtil.mutListener.listen(12141) ? (i != 0) : (ListenerUtil.mutListener.listen(12140) ? (i == 0) : (i >= 0)))))); i--) {
                            ListenerUtil.loopListener.listen("_loopCounter192", ++_loopCounter192);
                            Object[] oldChange = templateChanges.get(i);
                            if (!ListenerUtil.mutListener.listen(12139)) {
                                switch((ChangeType) oldChange[1]) {
                                    case DELETE:
                                        {
                                            if (!ListenerUtil.mutListener.listen(12127)) {
                                                // Deleting an ordinal at or below us? Adjust our comparison basis...
                                                if ((ListenerUtil.mutListener.listen(12125) ? ((ListenerUtil.mutListener.listen(12120) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12119) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12118) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12117) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment))))) >= ordinal) : (ListenerUtil.mutListener.listen(12124) ? ((ListenerUtil.mutListener.listen(12120) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12119) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12118) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12117) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment))))) > ordinal) : (ListenerUtil.mutListener.listen(12123) ? ((ListenerUtil.mutListener.listen(12120) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12119) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12118) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12117) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment))))) < ordinal) : (ListenerUtil.mutListener.listen(12122) ? ((ListenerUtil.mutListener.listen(12120) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12119) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12118) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12117) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment))))) != ordinal) : (ListenerUtil.mutListener.listen(12121) ? ((ListenerUtil.mutListener.listen(12120) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12119) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12118) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12117) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment))))) == ordinal) : ((ListenerUtil.mutListener.listen(12120) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12119) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12118) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12117) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment))))) <= ordinal))))))) {
                                                    if (!ListenerUtil.mutListener.listen(12126)) {
                                                        ordinalAdjustment++;
                                                    }
                                                    continue;
                                                }
                                            }
                                            break;
                                        }
                                    case ADD:
                                        if (!ListenerUtil.mutListener.listen(12138)) {
                                            if ((ListenerUtil.mutListener.listen(12136) ? (ordinal >= (ListenerUtil.mutListener.listen(12131) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12130) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12129) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12128) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment)))))) : (ListenerUtil.mutListener.listen(12135) ? (ordinal <= (ListenerUtil.mutListener.listen(12131) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12130) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12129) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12128) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment)))))) : (ListenerUtil.mutListener.listen(12134) ? (ordinal > (ListenerUtil.mutListener.listen(12131) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12130) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12129) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12128) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment)))))) : (ListenerUtil.mutListener.listen(12133) ? (ordinal < (ListenerUtil.mutListener.listen(12131) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12130) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12129) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12128) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment)))))) : (ListenerUtil.mutListener.listen(12132) ? (ordinal != (ListenerUtil.mutListener.listen(12131) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12130) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12129) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12128) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment)))))) : (ordinal == (ListenerUtil.mutListener.listen(12131) ? ((Integer) oldChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12130) ? ((Integer) oldChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12129) ? ((Integer) oldChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12128) ? ((Integer) oldChange[0] + ordinalAdjustment) : ((Integer) oldChange[0] - ordinalAdjustment)))))))))))) {
                                                if (!ListenerUtil.mutListener.listen(12137)) {
                                                    // Deleting something we added this session? Edit it out via compaction
                                                    compactTemplateChanges((Integer) oldChange[0]);
                                                }
                                                return;
                                            }
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12151)) {
            Timber.d("addTemplateChange() added ord/type: %s/%s", change[0], change[1]);
        }
        if (!ListenerUtil.mutListener.listen(12152)) {
            templateChanges.add(change);
        }
        if (!ListenerUtil.mutListener.listen(12153)) {
            dumpChanges();
        }
    }

    /**
     * Check if the given ordinal from the current UI state (which includes all pending changes) is a pending add
     *
     * @param ord int representing an ordinal in the model, that might be an unsaved addition
     * @return boolean true if it is a pending addition from this editing session
     */
    public static boolean isOrdinalPendingAdd(TemporaryModel model, int ord) {
        if (!ListenerUtil.mutListener.listen(12166)) {
            {
                long _loopCounter193 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12165) ? (i >= model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12164) ? (i <= model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12163) ? (i > model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12162) ? (i != model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12161) ? (i == model.getTemplateChanges().size()) : (i < model.getTemplateChanges().size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter193", ++_loopCounter193);
                    Object[] change = model.getTemplateChanges().get(i);
                    int adjustedOrdinal = getAdjustedAddOrdinalAtChangeIndex(model, i);
                    if (!ListenerUtil.mutListener.listen(12160)) {
                        if ((ListenerUtil.mutListener.listen(12158) ? (adjustedOrdinal >= ord) : (ListenerUtil.mutListener.listen(12157) ? (adjustedOrdinal <= ord) : (ListenerUtil.mutListener.listen(12156) ? (adjustedOrdinal > ord) : (ListenerUtil.mutListener.listen(12155) ? (adjustedOrdinal < ord) : (ListenerUtil.mutListener.listen(12154) ? (adjustedOrdinal != ord) : (adjustedOrdinal == ord))))))) {
                            if (!ListenerUtil.mutListener.listen(12159)) {
                                Timber.d("isOrdinalPendingAdd() found ord %s was pending add (would adjust to %s)", ord, adjustedOrdinal);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12167)) {
            Timber.d("isOrdinalPendingAdd() ord %s is not a pending add", ord);
        }
        return false;
    }

    /**
     * Check if the change at the given index in the changes array is an addition from this editing session
     * (and thus is not in the database yet, and possibly needing ordinal adjustment from subsequent deletes)
     * @param changesIndex the index of the template in the changes array
     * @return either ordinal adjusted by any pending deletes if it is a pending add, or -1 if the ordinal is not an add
     */
    public static int getAdjustedAddOrdinalAtChangeIndex(TemporaryModel model, int changesIndex) {
        if (!ListenerUtil.mutListener.listen(12173)) {
            if ((ListenerUtil.mutListener.listen(12172) ? (changesIndex <= model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12171) ? (changesIndex > model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12170) ? (changesIndex < model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12169) ? (changesIndex != model.getTemplateChanges().size()) : (ListenerUtil.mutListener.listen(12168) ? (changesIndex == model.getTemplateChanges().size()) : (changesIndex >= model.getTemplateChanges().size()))))))) {
                return -1;
            }
        }
        int ordinalAdjustment = 0;
        Object[] change = model.getTemplateChanges().get(changesIndex);
        int ordinalToInspect = (Integer) change[0];
        if (!ListenerUtil.mutListener.listen(12211)) {
            {
                long _loopCounter194 = 0;
                for (int i = (ListenerUtil.mutListener.listen(12210) ? (model.getTemplateChanges().size() % 1) : (ListenerUtil.mutListener.listen(12209) ? (model.getTemplateChanges().size() / 1) : (ListenerUtil.mutListener.listen(12208) ? (model.getTemplateChanges().size() * 1) : (ListenerUtil.mutListener.listen(12207) ? (model.getTemplateChanges().size() + 1) : (model.getTemplateChanges().size() - 1))))); (ListenerUtil.mutListener.listen(12206) ? (i <= changesIndex) : (ListenerUtil.mutListener.listen(12205) ? (i > changesIndex) : (ListenerUtil.mutListener.listen(12204) ? (i < changesIndex) : (ListenerUtil.mutListener.listen(12203) ? (i != changesIndex) : (ListenerUtil.mutListener.listen(12202) ? (i == changesIndex) : (i >= changesIndex)))))); i--) {
                    ListenerUtil.loopListener.listen("_loopCounter194", ++_loopCounter194);
                    Object[] oldChange = model.getTemplateChanges().get(i);
                    int currentOrdinal = (Integer) change[0];
                    if (!ListenerUtil.mutListener.listen(12201)) {
                        switch((ChangeType) oldChange[1]) {
                            case DELETE:
                                {
                                    if (!ListenerUtil.mutListener.listen(12184)) {
                                        // Deleting an ordinal at or below us? Adjust our comparison basis...
                                        if ((ListenerUtil.mutListener.listen(12182) ? ((ListenerUtil.mutListener.listen(12177) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12176) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12175) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12174) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment))))) >= ordinalToInspect) : (ListenerUtil.mutListener.listen(12181) ? ((ListenerUtil.mutListener.listen(12177) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12176) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12175) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12174) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment))))) > ordinalToInspect) : (ListenerUtil.mutListener.listen(12180) ? ((ListenerUtil.mutListener.listen(12177) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12176) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12175) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12174) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment))))) < ordinalToInspect) : (ListenerUtil.mutListener.listen(12179) ? ((ListenerUtil.mutListener.listen(12177) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12176) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12175) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12174) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment))))) != ordinalToInspect) : (ListenerUtil.mutListener.listen(12178) ? ((ListenerUtil.mutListener.listen(12177) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12176) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12175) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12174) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment))))) == ordinalToInspect) : ((ListenerUtil.mutListener.listen(12177) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12176) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12175) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12174) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment))))) <= ordinalToInspect))))))) {
                                            if (!ListenerUtil.mutListener.listen(12183)) {
                                                ordinalAdjustment++;
                                            }
                                            continue;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(12185)) {
                                        Timber.d("getAdjustedAddOrdinalAtChangeIndex() contemplating delete at index %s, current ord adj %s", i, ordinalAdjustment);
                                    }
                                    break;
                                }
                            case ADD:
                                if (!ListenerUtil.mutListener.listen(12200)) {
                                    if ((ListenerUtil.mutListener.listen(12190) ? (changesIndex >= i) : (ListenerUtil.mutListener.listen(12189) ? (changesIndex <= i) : (ListenerUtil.mutListener.listen(12188) ? (changesIndex > i) : (ListenerUtil.mutListener.listen(12187) ? (changesIndex < i) : (ListenerUtil.mutListener.listen(12186) ? (changesIndex != i) : (changesIndex == i))))))) {
                                        if (!ListenerUtil.mutListener.listen(12195)) {
                                            // something we added this session
                                            Timber.d("getAdjustedAddOrdinalAtChangeIndex() pending add found at at index %s, old ord/adjusted ord %s/%s", i, currentOrdinal, ((ListenerUtil.mutListener.listen(12194) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12193) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12192) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12191) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment)))))));
                                        }
                                        return ((ListenerUtil.mutListener.listen(12199) ? (currentOrdinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12198) ? (currentOrdinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12197) ? (currentOrdinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12196) ? (currentOrdinal + ordinalAdjustment) : (currentOrdinal - ordinalAdjustment))))));
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12212)) {
            Timber.d("getAdjustedAddOrdinalAtChangeIndex() determined changesIndex %s was not a pending add", changesIndex);
        }
        return -1;
    }

    /**
     * Return an int[] containing the collection-relative ordinals of all the currently pending deletes,
     * including the ordinal passed in, as opposed to the changelist-relative ordinals
     *
     * @param ord int UI-relative ordinal to check database for delete safety along with existing deletes
     * @return int[] of all ordinals currently in the database, pending delete
     */
    public int[] getDeleteDbOrds(int ord) {
        if (!ListenerUtil.mutListener.listen(12213)) {
            dumpChanges();
        }
        if (!ListenerUtil.mutListener.listen(12214)) {
            Timber.d("getDeleteDbOrds()");
        }
        // array containing the original / db-relative ordinals for all pending deletes plus the proposed one
        ArrayList<Integer> deletedDbOrds = new ArrayList<>(mTemplateChanges.size());
        if (!ListenerUtil.mutListener.listen(12247)) {
            {
                long _loopCounter196 = 0;
                // For each entry in the changes list - and the proposed delete - scan for deletes to get original ordinal
                for (int i = 0; (ListenerUtil.mutListener.listen(12246) ? (i >= mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12245) ? (i > mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12244) ? (i < mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12243) ? (i != mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12242) ? (i == mTemplateChanges.size()) : (i <= mTemplateChanges.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter196", ++_loopCounter196);
                    int ordinalAdjustment = 0;
                    // We need an initializer. Though proposed change is checked last, it's a reasonable default initializer.
                    Object[] currentChange = { ord, ChangeType.DELETE };
                    if (!ListenerUtil.mutListener.listen(12221)) {
                        if ((ListenerUtil.mutListener.listen(12219) ? (i >= mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12218) ? (i <= mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12217) ? (i > mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12216) ? (i != mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12215) ? (i == mTemplateChanges.size()) : (i < mTemplateChanges.size()))))))) {
                            if (!ListenerUtil.mutListener.listen(12220)) {
                                // Until we exhaust the pending change list we will use them
                                currentChange = mTemplateChanges.get(i);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12222)) {
                        // If the current pending change isn't a delete, it is unimportant here
                        if (currentChange[1] != ChangeType.DELETE) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12236)) {
                        {
                            long _loopCounter195 = 0;
                            // If it is a delete, scan previous deletes and shift as necessary for original ord
                            for (int j = 0; (ListenerUtil.mutListener.listen(12235) ? (j >= i) : (ListenerUtil.mutListener.listen(12234) ? (j <= i) : (ListenerUtil.mutListener.listen(12233) ? (j > i) : (ListenerUtil.mutListener.listen(12232) ? (j != i) : (ListenerUtil.mutListener.listen(12231) ? (j == i) : (j < i)))))); j++) {
                                ListenerUtil.loopListener.listen("_loopCounter195", ++_loopCounter195);
                                Object[] previousChange = mTemplateChanges.get(j);
                                if (!ListenerUtil.mutListener.listen(12230)) {
                                    // Is previous change a delete? Lower ordinal than current change?
                                    if ((ListenerUtil.mutListener.listen(12228) ? ((previousChange[1] == ChangeType.DELETE) || ((ListenerUtil.mutListener.listen(12227) ? ((int) previousChange[0] >= (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12226) ? ((int) previousChange[0] > (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12225) ? ((int) previousChange[0] < (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12224) ? ((int) previousChange[0] != (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12223) ? ((int) previousChange[0] == (int) currentChange[0]) : ((int) previousChange[0] <= (int) currentChange[0])))))))) : ((previousChange[1] == ChangeType.DELETE) && ((ListenerUtil.mutListener.listen(12227) ? ((int) previousChange[0] >= (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12226) ? ((int) previousChange[0] > (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12225) ? ((int) previousChange[0] < (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12224) ? ((int) previousChange[0] != (int) currentChange[0]) : (ListenerUtil.mutListener.listen(12223) ? ((int) previousChange[0] == (int) currentChange[0]) : ((int) previousChange[0] <= (int) currentChange[0])))))))))) {
                                        if (!ListenerUtil.mutListener.listen(12229)) {
                                            // If so, that is the case where things shift. It means our ordinals moved and original ord is higher
                                            ordinalAdjustment++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12241)) {
                        // Save this pending delete at it's original / db-relative position
                        deletedDbOrds.add((ListenerUtil.mutListener.listen(12240) ? ((int) currentChange[0] % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12239) ? ((int) currentChange[0] / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12238) ? ((int) currentChange[0] * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12237) ? ((int) currentChange[0] - ordinalAdjustment) : ((int) currentChange[0] + ordinalAdjustment))))));
                    }
                }
            }
        }
        int[] deletedDbOrdInts = new int[deletedDbOrds.size()];
        if (!ListenerUtil.mutListener.listen(12254)) {
            {
                long _loopCounter197 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12253) ? (i >= deletedDbOrdInts.length) : (ListenerUtil.mutListener.listen(12252) ? (i <= deletedDbOrdInts.length) : (ListenerUtil.mutListener.listen(12251) ? (i > deletedDbOrdInts.length) : (ListenerUtil.mutListener.listen(12250) ? (i != deletedDbOrdInts.length) : (ListenerUtil.mutListener.listen(12249) ? (i == deletedDbOrdInts.length) : (i < deletedDbOrdInts.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter197", ++_loopCounter197);
                    if (!ListenerUtil.mutListener.listen(12248)) {
                        deletedDbOrdInts[i] = (deletedDbOrds.get(i));
                    }
                }
            }
        }
        return deletedDbOrdInts;
    }

    private void dumpChanges() {
        if (!ListenerUtil.mutListener.listen(12255)) {
            if (!BuildConfig.DEBUG) {
                return;
            }
        }
        ArrayList<Object[]> adjustedChanges = getAdjustedTemplateChanges();
        if (!ListenerUtil.mutListener.listen(12263)) {
            {
                long _loopCounter198 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12262) ? (i >= mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12261) ? (i <= mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12260) ? (i > mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12259) ? (i != mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12258) ? (i == mTemplateChanges.size()) : (i < mTemplateChanges.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter198", ++_loopCounter198);
                    Object[] change = mTemplateChanges.get(i);
                    Object[] adjustedChange = adjustedChanges.get(i);
                    if (!ListenerUtil.mutListener.listen(12256)) {
                        Timber.d("dumpChanges() Change %s is ord/type %s/%s", i, change[0], change[1]);
                    }
                    if (!ListenerUtil.mutListener.listen(12257)) {
                        Timber.d("dumpChanges() During save change %s will be ord/type %s/%s", i, adjustedChange[0], adjustedChange[1]);
                    }
                }
            }
        }
    }

    @NonNull
    public ArrayList<Object[]> getTemplateChanges() {
        if (!ListenerUtil.mutListener.listen(12265)) {
            if (mTemplateChanges == null) {
                if (!ListenerUtil.mutListener.listen(12264)) {
                    mTemplateChanges = new ArrayList<>();
                }
            }
        }
        return mTemplateChanges;
    }

    /**
     * Adjust the ordinals in our accrued change list so that any pending adds have the correct
     * ordinal after taking into account any pending deletes
     *
     * @return ArrayList<Object[2]> of [ordinal][ChangeType] entries
     */
    @NonNull
    public ArrayList<Object[]> getAdjustedTemplateChanges() {
        ArrayList<Object[]> changes = getTemplateChanges();
        ArrayList<Object[]> adjustedChanges = new ArrayList<>(changes.size());
        if (!ListenerUtil.mutListener.listen(12275)) {
            {
                long _loopCounter199 = 0;
                // change list as-is until the save time comes, then the adjustment is made all at once
                for (int i = 0; (ListenerUtil.mutListener.listen(12274) ? (i >= changes.size()) : (ListenerUtil.mutListener.listen(12273) ? (i <= changes.size()) : (ListenerUtil.mutListener.listen(12272) ? (i > changes.size()) : (ListenerUtil.mutListener.listen(12271) ? (i != changes.size()) : (ListenerUtil.mutListener.listen(12270) ? (i == changes.size()) : (i < changes.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter199", ++_loopCounter199);
                    Object[] change = changes.get(i);
                    Object[] adjustedChange = { change[0], change[1] };
                    if (!ListenerUtil.mutListener.listen(12268)) {
                        switch((ChangeType) adjustedChange[1]) {
                            case ADD:
                                if (!ListenerUtil.mutListener.listen(12266)) {
                                    adjustedChange[0] = TemporaryModel.getAdjustedAddOrdinalAtChangeIndex(this, i);
                                }
                                if (!ListenerUtil.mutListener.listen(12267)) {
                                    Timber.d("getAdjustedTemplateChanges() change %s ordinal adjusted from %s to %s", i, change[0], adjustedChange[0]);
                                }
                                break;
                            case DELETE:
                            default:
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12269)) {
                        adjustedChanges.add(adjustedChange);
                    }
                }
            }
        }
        return adjustedChanges;
    }

    /**
     * Scan the sequence of template add/deletes, looking for the given ordinal.
     * When found, purge that ordinal and shift future changes down if they had ordinals higher than the one purged
     */
    private void compactTemplateChanges(int addedOrdinalToDelete) {
        if (!ListenerUtil.mutListener.listen(12276)) {
            Timber.d("compactTemplateChanges() merge/purge add/delete ordinal added as %s", addedOrdinalToDelete);
        }
        boolean postChange = false;
        int ordinalAdjustment = 0;
        if (!ListenerUtil.mutListener.listen(12310)) {
            {
                long _loopCounter200 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(12309) ? (i >= mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12308) ? (i <= mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12307) ? (i > mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12306) ? (i != mTemplateChanges.size()) : (ListenerUtil.mutListener.listen(12305) ? (i == mTemplateChanges.size()) : (i < mTemplateChanges.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter200", ++_loopCounter200);
                    Object[] change = mTemplateChanges.get(i);
                    int ordinal = (Integer) change[0];
                    ChangeType changeType = (ChangeType) change[1];
                    if (!ListenerUtil.mutListener.listen(12277)) {
                        Timber.d("compactTemplateChanges() examining change entry %s / %s", ordinal, changeType);
                    }
                    if (!ListenerUtil.mutListener.listen(12289)) {
                        // Only make adjustments after the ordinal we want to delete was added
                        if (!postChange) {
                            if (!ListenerUtil.mutListener.listen(12288)) {
                                if ((ListenerUtil.mutListener.listen(12283) ? ((ListenerUtil.mutListener.listen(12282) ? (ordinal >= addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12281) ? (ordinal <= addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12280) ? (ordinal > addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12279) ? (ordinal < addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12278) ? (ordinal != addedOrdinalToDelete) : (ordinal == addedOrdinalToDelete)))))) || changeType == ChangeType.ADD) : ((ListenerUtil.mutListener.listen(12282) ? (ordinal >= addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12281) ? (ordinal <= addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12280) ? (ordinal > addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12279) ? (ordinal < addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12278) ? (ordinal != addedOrdinalToDelete) : (ordinal == addedOrdinalToDelete)))))) && changeType == ChangeType.ADD))) {
                                    if (!ListenerUtil.mutListener.listen(12284)) {
                                        Timber.d("compactTemplateChanges() found our entry at index %s", i);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12285)) {
                                        // Remove this entry to start compaction, then fix up the loop counter since we altered size
                                        postChange = true;
                                    }
                                    if (!ListenerUtil.mutListener.listen(12286)) {
                                        mTemplateChanges.remove(i);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12287)) {
                                        i--;
                                    }
                                }
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12292)) {
                        // We compact all deletes with higher ordinals, so any delete is below us: shift our comparison basis
                        if (changeType == ChangeType.DELETE) {
                            if (!ListenerUtil.mutListener.listen(12290)) {
                                ordinalAdjustment++;
                            }
                            if (!ListenerUtil.mutListener.listen(12291)) {
                                Timber.d("compactTemplateChanges() delete affecting purged template, shifting basis, adj: %s", ordinalAdjustment);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12304)) {
                        // If following ordinals were higher, we move them as part of compaction
                        if ((ListenerUtil.mutListener.listen(12301) ? (((ListenerUtil.mutListener.listen(12296) ? (ordinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12295) ? (ordinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12294) ? (ordinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12293) ? (ordinal - ordinalAdjustment) : (ordinal + ordinalAdjustment)))))) >= addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12300) ? (((ListenerUtil.mutListener.listen(12296) ? (ordinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12295) ? (ordinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12294) ? (ordinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12293) ? (ordinal - ordinalAdjustment) : (ordinal + ordinalAdjustment)))))) <= addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12299) ? (((ListenerUtil.mutListener.listen(12296) ? (ordinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12295) ? (ordinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12294) ? (ordinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12293) ? (ordinal - ordinalAdjustment) : (ordinal + ordinalAdjustment)))))) < addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12298) ? (((ListenerUtil.mutListener.listen(12296) ? (ordinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12295) ? (ordinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12294) ? (ordinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12293) ? (ordinal - ordinalAdjustment) : (ordinal + ordinalAdjustment)))))) != addedOrdinalToDelete) : (ListenerUtil.mutListener.listen(12297) ? (((ListenerUtil.mutListener.listen(12296) ? (ordinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12295) ? (ordinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12294) ? (ordinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12293) ? (ordinal - ordinalAdjustment) : (ordinal + ordinalAdjustment)))))) == addedOrdinalToDelete) : (((ListenerUtil.mutListener.listen(12296) ? (ordinal % ordinalAdjustment) : (ListenerUtil.mutListener.listen(12295) ? (ordinal / ordinalAdjustment) : (ListenerUtil.mutListener.listen(12294) ? (ordinal * ordinalAdjustment) : (ListenerUtil.mutListener.listen(12293) ? (ordinal - ordinalAdjustment) : (ordinal + ordinalAdjustment)))))) > addedOrdinalToDelete))))))) {
                            if (!ListenerUtil.mutListener.listen(12302)) {
                                Timber.d("compactTemplateChanges() shifting later/higher ordinal down");
                            }
                            if (!ListenerUtil.mutListener.listen(12303)) {
                                change[0] = --ordinal;
                            }
                        }
                    }
                }
            }
        }
    }
}
