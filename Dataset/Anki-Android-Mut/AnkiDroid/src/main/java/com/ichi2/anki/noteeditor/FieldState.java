/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ichi2.anki.noteeditor;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.util.SparseArray;
import android.view.AbsSavedState;
import android.view.View;
import com.ichi2.anki.FieldEditLine;
import com.ichi2.anki.NoteEditor;
import com.ichi2.anki.R;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Models;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import static com.ichi2.utils.MapUtil.getKeyByValue;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Responsible for recreating EditFieldLines after NoteEditor operations
 * This primarily exists so we can use saved instance state to repopulate the dynamically created FieldEditLine
 */
public class FieldState {

    private final NoteEditor mEditor;

    private List<View.BaseSavedState> mSavedFieldData;

    private FieldState(NoteEditor editor) {
        mEditor = editor;
    }

    private static boolean allowFieldRemapping(String[][] oldFields) {
        return (ListenerUtil.mutListener.listen(2222) ? (oldFields.length >= 2) : (ListenerUtil.mutListener.listen(2221) ? (oldFields.length <= 2) : (ListenerUtil.mutListener.listen(2220) ? (oldFields.length < 2) : (ListenerUtil.mutListener.listen(2219) ? (oldFields.length != 2) : (ListenerUtil.mutListener.listen(2218) ? (oldFields.length == 2) : (oldFields.length > 2))))));
    }

    public static FieldState fromEditor(NoteEditor editor) {
        return new FieldState(editor);
    }

    @NonNull
    public List<FieldEditLine> loadFieldEditLines(FieldChangeType type) {
        List<FieldEditLine> fieldEditLines;
        if ((ListenerUtil.mutListener.listen(2223) ? (type.mType == Type.INIT || mSavedFieldData != null) : (type.mType == Type.INIT && mSavedFieldData != null))) {
            fieldEditLines = recreateFieldsFromState();
            if (!ListenerUtil.mutListener.listen(2224)) {
                mSavedFieldData = null;
            }
        } else {
            fieldEditLines = createFields(type);
        }
        if (!ListenerUtil.mutListener.listen(2226)) {
            {
                long _loopCounter33 = 0;
                for (FieldEditLine l : fieldEditLines) {
                    ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                    if (!ListenerUtil.mutListener.listen(2225)) {
                        l.setId(View.generateViewId());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2235)) {
            if (type.mType == Type.CLEAR_KEEP_STICKY) {
                // we use the UI values here as the model will post-processing steps (newline -> br).
                String[] currentFieldStrings = mEditor.getCurrentFieldStrings();
                JSONArray flds = mEditor.getCurrentFields();
                if (!ListenerUtil.mutListener.listen(2234)) {
                    {
                        long _loopCounter34 = 0;
                        for (int fldIdx = 0; (ListenerUtil.mutListener.listen(2233) ? (fldIdx >= flds.length()) : (ListenerUtil.mutListener.listen(2232) ? (fldIdx <= flds.length()) : (ListenerUtil.mutListener.listen(2231) ? (fldIdx > flds.length()) : (ListenerUtil.mutListener.listen(2230) ? (fldIdx != flds.length()) : (ListenerUtil.mutListener.listen(2229) ? (fldIdx == flds.length()) : (fldIdx < flds.length())))))); fldIdx++) {
                            ListenerUtil.loopListener.listen("_loopCounter34", ++_loopCounter34);
                            if (!ListenerUtil.mutListener.listen(2228)) {
                                if (flds.getJSONObject(fldIdx).getBoolean("sticky")) {
                                    if (!ListenerUtil.mutListener.listen(2227)) {
                                        fieldEditLines.get(fldIdx).setContent(currentFieldStrings[fldIdx], type.replaceNewlines);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2243)) {
            if (type.mType == Type.CHANGE_FIELD_COUNT) {
                String[] currentFieldStrings = mEditor.getCurrentFieldStrings();
                if (!ListenerUtil.mutListener.listen(2242)) {
                    {
                        long _loopCounter35 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(2241) ? (i >= Math.min(currentFieldStrings.length, fieldEditLines.size())) : (ListenerUtil.mutListener.listen(2240) ? (i <= Math.min(currentFieldStrings.length, fieldEditLines.size())) : (ListenerUtil.mutListener.listen(2239) ? (i > Math.min(currentFieldStrings.length, fieldEditLines.size())) : (ListenerUtil.mutListener.listen(2238) ? (i != Math.min(currentFieldStrings.length, fieldEditLines.size())) : (ListenerUtil.mutListener.listen(2237) ? (i == Math.min(currentFieldStrings.length, fieldEditLines.size())) : (i < Math.min(currentFieldStrings.length, fieldEditLines.size()))))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                            if (!ListenerUtil.mutListener.listen(2236)) {
                                fieldEditLines.get(i).setContent(currentFieldStrings[i], type.replaceNewlines);
                            }
                        }
                    }
                }
            }
        }
        return fieldEditLines;
    }

    private List<FieldEditLine> recreateFieldsFromState() {
        List<FieldEditLine> editLines = new ArrayList<>(mSavedFieldData.size());
        if (!ListenerUtil.mutListener.listen(2248)) {
            {
                long _loopCounter36 = 0;
                for (AbsSavedState state : mSavedFieldData) {
                    ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                    FieldEditLine edit_line_view = new FieldEditLine(mEditor);
                    if (!ListenerUtil.mutListener.listen(2245)) {
                        if (edit_line_view.getId() == 0) {
                            if (!ListenerUtil.mutListener.listen(2244)) {
                                edit_line_view.setId(View.generateViewId());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2246)) {
                        edit_line_view.loadState(state);
                    }
                    if (!ListenerUtil.mutListener.listen(2247)) {
                        editLines.add(edit_line_view);
                    }
                }
            }
        }
        return editLines;
    }

    @NonNull
    protected List<FieldEditLine> createFields(FieldChangeType type) {
        String[][] fields = getFields(type);
        List<FieldEditLine> editLines = new ArrayList<>(fields.length);
        if (!ListenerUtil.mutListener.listen(2258)) {
            {
                long _loopCounter37 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2257) ? (i >= fields.length) : (ListenerUtil.mutListener.listen(2256) ? (i <= fields.length) : (ListenerUtil.mutListener.listen(2255) ? (i > fields.length) : (ListenerUtil.mutListener.listen(2254) ? (i != fields.length) : (ListenerUtil.mutListener.listen(2253) ? (i == fields.length) : (i < fields.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                    FieldEditLine edit_line_view = new FieldEditLine(mEditor);
                    if (!ListenerUtil.mutListener.listen(2249)) {
                        editLines.add(edit_line_view);
                    }
                    if (!ListenerUtil.mutListener.listen(2250)) {
                        edit_line_view.setName(fields[i][0]);
                    }
                    if (!ListenerUtil.mutListener.listen(2251)) {
                        edit_line_view.setContent(fields[i][1], type.replaceNewlines);
                    }
                    if (!ListenerUtil.mutListener.listen(2252)) {
                        edit_line_view.setOrd(i);
                    }
                }
            }
        }
        return editLines;
    }

    private String[][] getFields(FieldChangeType type) {
        if (!ListenerUtil.mutListener.listen(2259)) {
            if (type.mType == Type.REFRESH_WITH_MAP) {
                String[][] items = mEditor.getFieldsFromSelectedNote();
                Map<String, Pair<Integer, JSONObject>> fMapNew = Models.fieldMap(type.newModel);
                return FieldState.fromFieldMap(mEditor, items, fMapNew, type.modelChangeFieldMap);
            }
        }
        return mEditor.getFieldsFromSelectedNote();
    }

    private static String[][] fromFieldMap(Context context, String[][] oldFields, Map<String, Pair<Integer, JSONObject>> fMapNew, Map<Integer, Integer> mModelChangeFieldMap) {
        // Build array of label/values to provide to field EditText views
        String[][] fields = new String[fMapNew.size()][2];
        if (!ListenerUtil.mutListener.listen(2269)) {
            {
                long _loopCounter38 = 0;
                for (String fname : fMapNew.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                    Pair<Integer, JSONObject> fieldPair = fMapNew.get(fname);
                    if (!ListenerUtil.mutListener.listen(2260)) {
                        if (fieldPair == null) {
                            continue;
                        }
                    }
                    // Field index of new note type
                    Integer i = fieldPair.first;
                    if (!ListenerUtil.mutListener.listen(2268)) {
                        // Add values from old note type if they exist in map, otherwise make the new field empty
                        if (mModelChangeFieldMap.containsValue(i)) {
                            // Get index of field from old note type given the field index of new note type
                            Integer j = getKeyByValue(mModelChangeFieldMap, i);
                            if (!ListenerUtil.mutListener.listen(2263)) {
                                if (j == null) {
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2266)) {
                                // Set the new field label text
                                if (allowFieldRemapping(oldFields)) {
                                    if (!ListenerUtil.mutListener.listen(2265)) {
                                        // Show the content of old field if remapping is enabled
                                        fields[i][0] = String.format(context.getResources().getString(R.string.field_remapping), fname, oldFields[j][0]);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2264)) {
                                        fields[i][0] = fname;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2267)) {
                                // Set the new field label value
                                fields[i][1] = oldFields[j][1];
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2261)) {
                                // No values from old note type exist in the mapping
                                fields[i][0] = fname;
                            }
                            if (!ListenerUtil.mutListener.listen(2262)) {
                                fields[i][1] = "";
                            }
                        }
                    }
                }
            }
        }
        return fields;
    }

    public void setInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2270)) {
            if (savedInstanceState == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2272)) {
            if ((ListenerUtil.mutListener.listen(2271) ? (!savedInstanceState.containsKey("customViewIds") && !savedInstanceState.containsKey("android:viewHierarchyState")) : (!savedInstanceState.containsKey("customViewIds") || !savedInstanceState.containsKey("android:viewHierarchyState")))) {
                return;
            }
        }
        ArrayList<Integer> customViewIds = savedInstanceState.getIntegerArrayList("customViewIds");
        Bundle viewHierarchyState = savedInstanceState.getBundle("android:viewHierarchyState");
        if (!ListenerUtil.mutListener.listen(2274)) {
            if ((ListenerUtil.mutListener.listen(2273) ? (customViewIds == null && viewHierarchyState == null) : (customViewIds == null || viewHierarchyState == null))) {
                return;
            }
        }
        SparseArray<?> views = (SparseArray<?>) viewHierarchyState.get("android:views");
        if (!ListenerUtil.mutListener.listen(2275)) {
            if (views == null) {
                return;
            }
        }
        List<View.BaseSavedState> important = new ArrayList<>(customViewIds.size());
        if (!ListenerUtil.mutListener.listen(2277)) {
            {
                long _loopCounter39 = 0;
                for (Integer i : customViewIds) {
                    ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                    if (!ListenerUtil.mutListener.listen(2276)) {
                        important.add((View.BaseSavedState) views.get(i));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2278)) {
            mSavedFieldData = important;
        }
    }

    /**
     * How fields should be changed when the UI is rebuilt
     */
    public static class FieldChangeType {

        private final Type mType;

        private Map<Integer, Integer> modelChangeFieldMap;

        private Model newModel;

        private final boolean replaceNewlines;

        public FieldChangeType(Type type, boolean replaceNewlines) {
            this.mType = type;
            this.replaceNewlines = replaceNewlines;
        }

        public static FieldChangeType refreshWithMap(Model newModel, Map<Integer, Integer> modelChangeFieldMap, boolean replaceNewlines) {
            FieldChangeType typeClass = new FieldChangeType(Type.REFRESH_WITH_MAP, replaceNewlines);
            if (!ListenerUtil.mutListener.listen(2279)) {
                typeClass.newModel = newModel;
            }
            if (!ListenerUtil.mutListener.listen(2280)) {
                typeClass.modelChangeFieldMap = modelChangeFieldMap;
            }
            return typeClass;
        }

        public static FieldChangeType refresh(boolean replaceNewlines) {
            return fromType(FieldState.Type.REFRESH, replaceNewlines);
        }

        public static FieldChangeType refreshWithStickyFields(boolean replaceNewlines) {
            return fromType(Type.CLEAR_KEEP_STICKY, replaceNewlines);
        }

        public static FieldChangeType changeFieldCount(boolean replaceNewlines) {
            return fromType(Type.CHANGE_FIELD_COUNT, replaceNewlines);
        }

        public static FieldChangeType onActivityCreation(boolean replaceNewlines) {
            return fromType(Type.INIT, replaceNewlines);
        }

        private static FieldChangeType fromType(Type type, boolean replaceNewlines) {
            return new FieldChangeType(type, replaceNewlines);
        }
    }

    public enum Type {

        INIT, CLEAR_KEEP_STICKY, CHANGE_FIELD_COUNT, REFRESH, REFRESH_WITH_MAP
    }
}
