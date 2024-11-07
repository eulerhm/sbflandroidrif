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
package com.ichi2.anki.servicelayer;

import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.multimediacard.IMultimediaEditableNote;
import com.ichi2.anki.multimediacard.fields.AudioClipField;
import com.ichi2.anki.multimediacard.fields.AudioRecordingField;
import com.ichi2.anki.multimediacard.fields.IField;
import com.ichi2.anki.multimediacard.fields.ImageField;
import com.ichi2.anki.multimediacard.fields.TextField;
import com.ichi2.anki.multimediacard.impl.MultimediaEditableNote;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.exception.EmptyMediaException;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.io.File;
import java.io.IOException;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NoteService {

    /**
     * Creates an empty Note from given Model
     *
     * @param model the model in JSOBObject format
     * @return a new note instance
     */
    public static MultimediaEditableNote createEmptyNote(JSONObject model) {
        try {
            JSONArray fieldsArray = model.getJSONArray("flds");
            int numOfFields = fieldsArray.length();
            if (!ListenerUtil.mutListener.listen(3074)) {
                if ((ListenerUtil.mutListener.listen(3062) ? (numOfFields >= 0) : (ListenerUtil.mutListener.listen(3061) ? (numOfFields <= 0) : (ListenerUtil.mutListener.listen(3060) ? (numOfFields < 0) : (ListenerUtil.mutListener.listen(3059) ? (numOfFields != 0) : (ListenerUtil.mutListener.listen(3058) ? (numOfFields == 0) : (numOfFields > 0))))))) {
                    MultimediaEditableNote note = new MultimediaEditableNote();
                    if (!ListenerUtil.mutListener.listen(3063)) {
                        note.setNumFields(numOfFields);
                    }
                    if (!ListenerUtil.mutListener.listen(3072)) {
                        {
                            long _loopCounter78 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(3071) ? (i >= numOfFields) : (ListenerUtil.mutListener.listen(3070) ? (i <= numOfFields) : (ListenerUtil.mutListener.listen(3069) ? (i > numOfFields) : (ListenerUtil.mutListener.listen(3068) ? (i != numOfFields) : (ListenerUtil.mutListener.listen(3067) ? (i == numOfFields) : (i < numOfFields)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter78", ++_loopCounter78);
                                JSONObject fieldObject = fieldsArray.getJSONObject(i);
                                TextField uiTextField = new TextField();
                                if (!ListenerUtil.mutListener.listen(3064)) {
                                    uiTextField.setName(fieldObject.getString("name"));
                                }
                                if (!ListenerUtil.mutListener.listen(3065)) {
                                    uiTextField.setText(fieldObject.getString("name"));
                                }
                                if (!ListenerUtil.mutListener.listen(3066)) {
                                    note.setField(i, uiTextField);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3073)) {
                        note.setModelId(model.getLong("id"));
                    }
                    return note;
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(3057)) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void updateMultimediaNoteFromJsonNote(Collection col, final Note editorNoteSrc, final IMultimediaEditableNote noteDst) {
        if (!ListenerUtil.mutListener.listen(3076)) {
            if (noteDst instanceof MultimediaEditableNote) {
                if (!ListenerUtil.mutListener.listen(3075)) {
                    updateMultimediaNoteFromFields(col, editorNoteSrc.getFields(), editorNoteSrc.getMid(), (MultimediaEditableNote) noteDst);
                }
            }
        }
    }

    public static void updateMultimediaNoteFromFields(Collection col, String[] fields, long modelId, MultimediaEditableNote mmNote) {
        if (!ListenerUtil.mutListener.listen(3090)) {
            {
                long _loopCounter79 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3089) ? (i >= fields.length) : (ListenerUtil.mutListener.listen(3088) ? (i <= fields.length) : (ListenerUtil.mutListener.listen(3087) ? (i > fields.length) : (ListenerUtil.mutListener.listen(3086) ? (i != fields.length) : (ListenerUtil.mutListener.listen(3085) ? (i == fields.length) : (i < fields.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter79", ++_loopCounter79);
                    String value = fields[i];
                    IField field = null;
                    if (!ListenerUtil.mutListener.listen(3082)) {
                        if (value.startsWith("<img")) {
                            if (!ListenerUtil.mutListener.listen(3081)) {
                                field = new ImageField();
                            }
                        } else if ((ListenerUtil.mutListener.listen(3077) ? (value.startsWith("[sound:") || value.contains("rec")) : (value.startsWith("[sound:") && value.contains("rec")))) {
                            if (!ListenerUtil.mutListener.listen(3080)) {
                                field = new AudioRecordingField();
                            }
                        } else if (value.startsWith("[sound:")) {
                            if (!ListenerUtil.mutListener.listen(3079)) {
                                field = new AudioClipField();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3078)) {
                                field = new TextField();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3083)) {
                        field.setFormattedString(col, value);
                    }
                    if (!ListenerUtil.mutListener.listen(3084)) {
                        mmNote.setField(i, field);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3091)) {
            mmNote.setModelId(modelId);
        }
    }

    /**
     * Updates the JsonNote field values from MultimediaEditableNote When both notes are using the same Model, it updaes
     * the destination field values with source values. If models are different it throws an Exception
     *
     * @param noteSrc
     * @param editorNoteDst
     */
    public static void updateJsonNoteFromMultimediaNote(final IMultimediaEditableNote noteSrc, final Note editorNoteDst) {
        if (!ListenerUtil.mutListener.listen(3105)) {
            if (noteSrc instanceof MultimediaEditableNote) {
                MultimediaEditableNote mmNote = (MultimediaEditableNote) noteSrc;
                if (!ListenerUtil.mutListener.listen(3097)) {
                    if ((ListenerUtil.mutListener.listen(3096) ? (mmNote.getModelId() >= editorNoteDst.getMid()) : (ListenerUtil.mutListener.listen(3095) ? (mmNote.getModelId() <= editorNoteDst.getMid()) : (ListenerUtil.mutListener.listen(3094) ? (mmNote.getModelId() > editorNoteDst.getMid()) : (ListenerUtil.mutListener.listen(3093) ? (mmNote.getModelId() < editorNoteDst.getMid()) : (ListenerUtil.mutListener.listen(3092) ? (mmNote.getModelId() == editorNoteDst.getMid()) : (mmNote.getModelId() != editorNoteDst.getMid()))))))) {
                        throw new RuntimeException("Source and Destination Note ID do not match.");
                    }
                }
                int totalFields = mmNote.getNumberOfFields();
                if (!ListenerUtil.mutListener.listen(3104)) {
                    {
                        long _loopCounter80 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(3103) ? (i >= totalFields) : (ListenerUtil.mutListener.listen(3102) ? (i <= totalFields) : (ListenerUtil.mutListener.listen(3101) ? (i > totalFields) : (ListenerUtil.mutListener.listen(3100) ? (i != totalFields) : (ListenerUtil.mutListener.listen(3099) ? (i == totalFields) : (i < totalFields)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter80", ++_loopCounter80);
                            if (!ListenerUtil.mutListener.listen(3098)) {
                                editorNoteDst.values()[i] = mmNote.getField(i).getFormattedValue();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Saves the multimedia associated with this card to proper path inside anki folder. For each field associated with
     * the note it checks for the following condition a. The field content should have changed b. The field content does
     * not already point to a media inside anki media path If both condition satisfies then it copies the file inside
     * the media path and deletes the file referenced by the note
     *
     * @param noteNew
     */
    public static void saveMedia(Collection col, final MultimediaEditableNote noteNew) {
        // {
        int fieldCount = noteNew.getNumberOfFields();
        if (!ListenerUtil.mutListener.listen(3112)) {
            {
                long _loopCounter81 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3111) ? (i >= fieldCount) : (ListenerUtil.mutListener.listen(3110) ? (i <= fieldCount) : (ListenerUtil.mutListener.listen(3109) ? (i > fieldCount) : (ListenerUtil.mutListener.listen(3108) ? (i != fieldCount) : (ListenerUtil.mutListener.listen(3107) ? (i == fieldCount) : (i < fieldCount)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter81", ++_loopCounter81);
                    IField newField = noteNew.getField(i);
                    if (!ListenerUtil.mutListener.listen(3106)) {
                        importMediaToDirectory(col, newField);
                    }
                }
            }
        }
    }

    /**
     * Considering the field is new, if it has media handle it
     *
     * @param field
     */
    private static void importMediaToDirectory(Collection col, IField field) {
        String tmpMediaPath = null;
        if (!ListenerUtil.mutListener.listen(3115)) {
            switch(field.getType()) {
                case AUDIO_RECORDING:
                case AUDIO_CLIP:
                    if (!ListenerUtil.mutListener.listen(3113)) {
                        tmpMediaPath = field.getAudioPath();
                    }
                    break;
                case IMAGE:
                    if (!ListenerUtil.mutListener.listen(3114)) {
                        tmpMediaPath = field.getImagePath();
                    }
                    break;
                case TEXT:
                default:
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(3131)) {
            if (tmpMediaPath != null) {
                try {
                    File inFile = new File(tmpMediaPath);
                    if (!ListenerUtil.mutListener.listen(3130)) {
                        if ((ListenerUtil.mutListener.listen(3123) ? (inFile.exists() || (ListenerUtil.mutListener.listen(3122) ? (inFile.length() >= 0) : (ListenerUtil.mutListener.listen(3121) ? (inFile.length() <= 0) : (ListenerUtil.mutListener.listen(3120) ? (inFile.length() < 0) : (ListenerUtil.mutListener.listen(3119) ? (inFile.length() != 0) : (ListenerUtil.mutListener.listen(3118) ? (inFile.length() == 0) : (inFile.length() > 0))))))) : (inFile.exists() && (ListenerUtil.mutListener.listen(3122) ? (inFile.length() >= 0) : (ListenerUtil.mutListener.listen(3121) ? (inFile.length() <= 0) : (ListenerUtil.mutListener.listen(3120) ? (inFile.length() < 0) : (ListenerUtil.mutListener.listen(3119) ? (inFile.length() != 0) : (ListenerUtil.mutListener.listen(3118) ? (inFile.length() == 0) : (inFile.length() > 0))))))))) {
                            String fname = col.getMedia().addFile(inFile);
                            File outFile = new File(col.getMedia().dir(), fname);
                            if (!ListenerUtil.mutListener.listen(3126)) {
                                if ((ListenerUtil.mutListener.listen(3124) ? (field.hasTemporaryMedia() || !outFile.getAbsolutePath().equals(tmpMediaPath)) : (field.hasTemporaryMedia() && !outFile.getAbsolutePath().equals(tmpMediaPath)))) {
                                    if (!ListenerUtil.mutListener.listen(3125)) {
                                        // Delete original
                                        inFile.delete();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3129)) {
                                switch(field.getType()) {
                                    case AUDIO_RECORDING:
                                    case AUDIO_CLIP:
                                        if (!ListenerUtil.mutListener.listen(3127)) {
                                            field.setAudioPath(outFile.getAbsolutePath());
                                        }
                                        break;
                                    case IMAGE:
                                        if (!ListenerUtil.mutListener.listen(3128)) {
                                            field.setImagePath(outFile.getAbsolutePath());
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (EmptyMediaException mediaException) {
                    if (!ListenerUtil.mutListener.listen(3116)) {
                        // This shouldn't happen, but we're fine to ignore it if it does.
                        Timber.w(mediaException);
                    }
                    if (!ListenerUtil.mutListener.listen(3117)) {
                        AnkiDroidApp.sendExceptionReport(mediaException, "noteService::importMediaToDirectory");
                    }
                }
            }
        }
    }
}
