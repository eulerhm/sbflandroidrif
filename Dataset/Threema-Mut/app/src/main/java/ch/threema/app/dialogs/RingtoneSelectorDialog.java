/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.msgpack.core.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import ch.threema.app.R;
import ch.threema.app.utils.RingtoneUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RingtoneSelectorDialog extends ThreemaDialogFragment {

    private static final Logger logger = LoggerFactory.getLogger(RingtoneSelectorDialog.class);

    private RingtoneSelectorDialogClickListener callback;

    private Activity activity;

    private AlertDialog alertDialog;

    private Uri selectedRingtoneUri, defaultUri;

    private static final String CURSOR_DEFAULT_ID = "-2";

    private static final String CURSOR_NONE_ID = "-1";

    private int selectedIndex = -1;

    private Cursor cursor;

    private RingtoneManager ringtoneManager;

    private Ringtone selectedRingtone;

    /**
     *  Creates a ringtone selector dialog similar to Android's RingtonePreference
     *  @param title Title shown on top of the dialog
     *  @param ringtoneType Type of ringtone as defined in {@link RingtoneManager}
     *  @param existingUri Uri pointing to the currently selected ringtone.
     *  @param defaultUri Uri pointing to a ringtone that will be marked as "default". If null, the system's default ringtone for {@param ringtoneType} will be used
     *  @param showDefault Show a selection for the default ringtone
     *  @param showSilent Show a selection for a silent ringtone
     *  @return RingtoneSelectorDialog
     */
    public static RingtoneSelectorDialog newInstance(String title, int ringtoneType, Uri existingUri, Uri defaultUri, boolean showDefault, boolean showSilent) {
        RingtoneSelectorDialog dialog = new RingtoneSelectorDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(14030)) {
            args.putString("title", title);
        }
        if (!ListenerUtil.mutListener.listen(14031)) {
            args.putInt("type", ringtoneType);
        }
        if (!ListenerUtil.mutListener.listen(14032)) {
            args.putParcelable("existingUri", existingUri);
        }
        if (!ListenerUtil.mutListener.listen(14033)) {
            args.putParcelable("defaultUri", defaultUri);
        }
        if (!ListenerUtil.mutListener.listen(14034)) {
            args.putBoolean("showDefault", showDefault);
        }
        if (!ListenerUtil.mutListener.listen(14035)) {
            args.putBoolean("showSilent", showSilent);
        }
        if (!ListenerUtil.mutListener.listen(14036)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    public interface RingtoneSelectorDialogClickListener {

        void onRingtoneSelected(String tag, Uri ringtone);

        void onCancel(String tag);
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(14037)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(14038)) {
            this.activity = activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14039)) {
            super.onCreate(savedInstanceState);
        }
        try {
            if (!ListenerUtil.mutListener.listen(14040)) {
                callback = (RingtoneSelectorDialog.RingtoneSelectorDialogClickListener) getTargetFragment();
            }
        } catch (ClassCastException e) {
        }
        if (!ListenerUtil.mutListener.listen(14043)) {
            // called from an activity rather than a fragment
            if (callback == null) {
                if (!ListenerUtil.mutListener.listen(14041)) {
                    if (!(activity instanceof RingtoneSelectorDialog.RingtoneSelectorDialogClickListener)) {
                        throw new ClassCastException("Calling fragment must implement RingtoneSelectorDialogClickListener interface");
                    }
                }
                if (!ListenerUtil.mutListener.listen(14042)) {
                    callback = (RingtoneSelectorDialog.RingtoneSelectorDialogClickListener) activity;
                }
            }
        }
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        if (!ListenerUtil.mutListener.listen(14044)) {
            super.onCancel(dialogInterface);
        }
        if (!ListenerUtil.mutListener.listen(14045)) {
            callback.onCancel(this.getTag());
        }
    }

    @NonNull
    @Override
    public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        final int ringtoneType = getArguments().getInt("type");
        if (!ListenerUtil.mutListener.listen(14046)) {
            defaultUri = getArguments().getParcelable("defaultUri");
        }
        final Uri existingUri = getArguments().getParcelable("existingUri");
        final boolean showDefault = getArguments().getBoolean("showDefault");
        final boolean showSilent = getArguments().getBoolean("showSilent");
        if (!ListenerUtil.mutListener.listen(14049)) {
            if ((ListenerUtil.mutListener.listen(14047) ? (showDefault || defaultUri == null) : (showDefault && defaultUri == null))) {
                if (!ListenerUtil.mutListener.listen(14048)) {
                    // get default URI from system if none is provided by caller
                    defaultUri = RingtoneManager.getDefaultUri(ringtoneType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14050)) {
            selectedRingtoneUri = existingUri;
        }
        if (!ListenerUtil.mutListener.listen(14051)) {
            cursor = createCursor(existingUri, ringtoneType, defaultUri, showDefault, showSilent);
        }
        final String tag = this.getTag();
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), getTheme());
        if (!ListenerUtil.mutListener.listen(14053)) {
            if (title != null) {
                if (!ListenerUtil.mutListener.listen(14052)) {
                    builder.setTitle(title);
                }
            }
        }
        String[] labels = new String[cursor.getCount()];
        if (!ListenerUtil.mutListener.listen(14056)) {
            if (cursor.moveToFirst()) {
                if (!ListenerUtil.mutListener.listen(14055)) {
                    {
                        long _loopCounter133 = 0;
                        do {
                            ListenerUtil.loopListener.listen("_loopCounter133", ++_loopCounter133);
                            if (!ListenerUtil.mutListener.listen(14054)) {
                                labels[cursor.getPosition()] = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
                            }
                        } while (cursor.moveToNext());
                    }
                }
            }
        }
        final TypedArray a = getContext().obtainStyledAttributes(null, androidx.appcompat.R.styleable.AlertDialog, androidx.appcompat.R.attr.alertDialogStyle, 0);
        int itemLayout = a.getResourceId(com.google.android.material.R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        RingtoneListItemAdapter adapter = new RingtoneListItemAdapter(getContext(), itemLayout, android.R.id.text1, labels);
        if (!ListenerUtil.mutListener.listen(14081)) {
            builder.setSingleChoiceItems(adapter, selectedIndex, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!ListenerUtil.mutListener.listen(14080)) {
                        if ((ListenerUtil.mutListener.listen(14067) ? (i >= adapter.getCount()) : (ListenerUtil.mutListener.listen(14066) ? (i <= adapter.getCount()) : (ListenerUtil.mutListener.listen(14065) ? (i > adapter.getCount()) : (ListenerUtil.mutListener.listen(14064) ? (i != adapter.getCount()) : (ListenerUtil.mutListener.listen(14063) ? (i == adapter.getCount()) : (i < adapter.getCount()))))))) {
                            if (!ListenerUtil.mutListener.listen(14068)) {
                                selectedIndex = i;
                            }
                            if (!ListenerUtil.mutListener.listen(14069)) {
                                stopPlaying();
                            }
                            if (!ListenerUtil.mutListener.listen(14070)) {
                                selectedRingtoneUri = getUriFromPosition(selectedIndex, showSilent, showDefault);
                            }
                            if (!ListenerUtil.mutListener.listen(14079)) {
                                if (selectedRingtoneUri == null) {
                                    if (!ListenerUtil.mutListener.listen(14078)) {
                                        // "playing" silence
                                        ringtoneManager.stopPreviousRingtone();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(14071)) {
                                        selectedRingtone = RingtoneManager.getRingtone(getContext(), selectedRingtoneUri);
                                    }
                                    if (!ListenerUtil.mutListener.listen(14077)) {
                                        if (selectedRingtone != null) {
                                            try {
                                                if (!ListenerUtil.mutListener.listen(14076)) {
                                                    selectedRingtone.play();
                                                }
                                            } catch (Exception e) {
                                                if (!ListenerUtil.mutListener.listen(14072)) {
                                                    // on some HTC devices
                                                    Toast.makeText(getContext(), "Unable to play ringtone " + selectedRingtoneUri.toString(), Toast.LENGTH_LONG).show();
                                                }
                                                if (!ListenerUtil.mutListener.listen(14073)) {
                                                    logger.debug("Unable to play ringtone " + selectedRingtoneUri.toString());
                                                }
                                                if (!ListenerUtil.mutListener.listen(14074)) {
                                                    logger.error("Exception", e);
                                                }
                                                if (!ListenerUtil.mutListener.listen(14075)) {
                                                    ringtoneManager.stopPreviousRingtone();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }).setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (!ListenerUtil.mutListener.listen(14061)) {
                        stopPlaying();
                    }
                    if (!ListenerUtil.mutListener.listen(14062)) {
                        callback.onCancel(tag);
                    }
                }
            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(14059)) {
                        stopPlaying();
                    }
                    if (!ListenerUtil.mutListener.listen(14060)) {
                        callback.onCancel(tag);
                    }
                }
            }).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(14057)) {
                        stopPlaying();
                    }
                    if (!ListenerUtil.mutListener.listen(14058)) {
                        callback.onRingtoneSelected(tag, selectedRingtoneUri);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(14082)) {
            alertDialog = builder.create();
        }
        return alertDialog;
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(14083)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(14084)) {
            stopPlaying();
        }
    }

    private void stopPlaying() {
        if (!ListenerUtil.mutListener.listen(14087)) {
            if ((ListenerUtil.mutListener.listen(14085) ? (selectedRingtone != null || selectedRingtone.isPlaying()) : (selectedRingtone != null && selectedRingtone.isPlaying()))) {
                if (!ListenerUtil.mutListener.listen(14086)) {
                    selectedRingtone.stop();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14089)) {
            if (ringtoneManager != null) {
                if (!ListenerUtil.mutListener.listen(14088)) {
                    ringtoneManager.stopPreviousRingtone();
                }
            }
        }
    }

    @NonNull
    private Cursor createCursor(Uri existingUri, int ringtoneType, Uri defaultUri, boolean showDefault, boolean showSilent) {
        if (!ListenerUtil.mutListener.listen(14090)) {
            ringtoneManager = new RingtoneManager(getContext());
        }
        if (!ListenerUtil.mutListener.listen(14091)) {
            ringtoneManager.setType(ringtoneType);
        }
        if (!ListenerUtil.mutListener.listen(14092)) {
            ringtoneManager.setStopPreviousRingtone(true);
        }
        Cursor ringtoneCursor = ringtoneManager.getCursor();
        String colId = ringtoneCursor.getColumnName(RingtoneManager.ID_COLUMN_INDEX);
        String colTitle = ringtoneCursor.getColumnName(RingtoneManager.TITLE_COLUMN_INDEX);
        try (MatrixCursor extras = new MatrixCursor(new String[] { colId, colTitle })) {
            if (!ListenerUtil.mutListener.listen(14094)) {
                if (showSilent) {
                    if (!ListenerUtil.mutListener.listen(14093)) {
                        extras.addRow(new String[] { CURSOR_NONE_ID, getString(R.string.ringtone_none) });
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14099)) {
                if (showDefault) {
                    String defaultUriString = RingtoneUtil.getRingtoneNameFromUri(getContext(), defaultUri);
                    if (!ListenerUtil.mutListener.listen(14097)) {
                        // hack to prevent showing label for default uri twice
                        if (!((ListenerUtil.mutListener.listen(14095) ? (defaultUriString.contains("(") || defaultUriString.contains(")")) : (defaultUriString.contains("(") && defaultUriString.contains(")"))))) {
                            if (!ListenerUtil.mutListener.listen(14096)) {
                                defaultUriString = String.format(getString(R.string.ringtone_selection_default), defaultUriString);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14098)) {
                        extras.addRow(new String[] { CURSOR_DEFAULT_ID, defaultUriString });
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14133)) {
                if ((ListenerUtil.mutListener.listen(14101) ? ((ListenerUtil.mutListener.listen(14100) ? (showSilent || existingUri != null) : (showSilent && existingUri != null)) || existingUri.toString().equals("")) : ((ListenerUtil.mutListener.listen(14100) ? (showSilent || existingUri != null) : (showSilent && existingUri != null)) && existingUri.toString().equals("")))) {
                    if (!ListenerUtil.mutListener.listen(14132)) {
                        // silent default
                        selectedIndex = 0;
                    }
                } else {
                    try {
                        if (!ListenerUtil.mutListener.listen(14104)) {
                            selectedIndex = ringtoneManager.getRingtonePosition(existingUri);
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(14102)) {
                            logger.error("Exception", e);
                        }
                        if (!ListenerUtil.mutListener.listen(14103)) {
                            selectedIndex = 0;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14115)) {
                        if ((ListenerUtil.mutListener.listen(14109) ? (selectedIndex <= 0) : (ListenerUtil.mutListener.listen(14108) ? (selectedIndex > 0) : (ListenerUtil.mutListener.listen(14107) ? (selectedIndex < 0) : (ListenerUtil.mutListener.listen(14106) ? (selectedIndex != 0) : (ListenerUtil.mutListener.listen(14105) ? (selectedIndex == 0) : (selectedIndex >= 0))))))) {
                            if (!ListenerUtil.mutListener.listen(14114)) {
                                selectedIndex += (ListenerUtil.mutListener.listen(14113) ? ((showDefault ? 1 : 0) % (showSilent ? 1 : 0)) : (ListenerUtil.mutListener.listen(14112) ? ((showDefault ? 1 : 0) / (showSilent ? 1 : 0)) : (ListenerUtil.mutListener.listen(14111) ? ((showDefault ? 1 : 0) * (showSilent ? 1 : 0)) : (ListenerUtil.mutListener.listen(14110) ? ((showDefault ? 1 : 0) - (showSilent ? 1 : 0)) : ((showDefault ? 1 : 0) + (showSilent ? 1 : 0))))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14123)) {
                        if ((ListenerUtil.mutListener.listen(14121) ? ((ListenerUtil.mutListener.listen(14120) ? (selectedIndex >= 0) : (ListenerUtil.mutListener.listen(14119) ? (selectedIndex <= 0) : (ListenerUtil.mutListener.listen(14118) ? (selectedIndex > 0) : (ListenerUtil.mutListener.listen(14117) ? (selectedIndex != 0) : (ListenerUtil.mutListener.listen(14116) ? (selectedIndex == 0) : (selectedIndex < 0)))))) || showDefault) : ((ListenerUtil.mutListener.listen(14120) ? (selectedIndex >= 0) : (ListenerUtil.mutListener.listen(14119) ? (selectedIndex <= 0) : (ListenerUtil.mutListener.listen(14118) ? (selectedIndex > 0) : (ListenerUtil.mutListener.listen(14117) ? (selectedIndex != 0) : (ListenerUtil.mutListener.listen(14116) ? (selectedIndex == 0) : (selectedIndex < 0)))))) && showDefault))) {
                            if (!ListenerUtil.mutListener.listen(14122)) {
                                selectedIndex = showSilent ? 1 : 0;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14131)) {
                        if ((ListenerUtil.mutListener.listen(14129) ? ((ListenerUtil.mutListener.listen(14128) ? (selectedIndex >= 0) : (ListenerUtil.mutListener.listen(14127) ? (selectedIndex <= 0) : (ListenerUtil.mutListener.listen(14126) ? (selectedIndex > 0) : (ListenerUtil.mutListener.listen(14125) ? (selectedIndex != 0) : (ListenerUtil.mutListener.listen(14124) ? (selectedIndex == 0) : (selectedIndex < 0)))))) || showSilent) : ((ListenerUtil.mutListener.listen(14128) ? (selectedIndex >= 0) : (ListenerUtil.mutListener.listen(14127) ? (selectedIndex <= 0) : (ListenerUtil.mutListener.listen(14126) ? (selectedIndex > 0) : (ListenerUtil.mutListener.listen(14125) ? (selectedIndex != 0) : (ListenerUtil.mutListener.listen(14124) ? (selectedIndex == 0) : (selectedIndex < 0)))))) && showSilent))) {
                            if (!ListenerUtil.mutListener.listen(14130)) {
                                selectedIndex = 0;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14134)) {
                // get uri for initial selection
                selectedRingtoneUri = getUriFromPosition(selectedIndex, showSilent, showDefault);
            }
            Cursor[] cursors = { extras, ringtoneCursor };
            return this.cursor = new MergeCursor(cursors);
        }
    }

    private static class RingtoneListItemAdapter extends ArrayAdapter<CharSequence> {

        RingtoneListItemAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull CharSequence[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    @Nullable
    private Uri getUriFromPosition(int index, boolean showSilent, boolean showDefault) {
        int positionFix = 0;
        if (!ListenerUtil.mutListener.listen(14142)) {
            if (showSilent) {
                if (!ListenerUtil.mutListener.listen(14140)) {
                    if ((ListenerUtil.mutListener.listen(14139) ? (index >= 0) : (ListenerUtil.mutListener.listen(14138) ? (index <= 0) : (ListenerUtil.mutListener.listen(14137) ? (index > 0) : (ListenerUtil.mutListener.listen(14136) ? (index < 0) : (ListenerUtil.mutListener.listen(14135) ? (index != 0) : (index == 0))))))) {
                        // silent
                        return null;
                    }
                }
                if (!ListenerUtil.mutListener.listen(14141)) {
                    positionFix += 1;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14158)) {
            if (showDefault) {
                if (!ListenerUtil.mutListener.listen(14156)) {
                    if ((ListenerUtil.mutListener.listen(14154) ? (((ListenerUtil.mutListener.listen(14148) ? (showSilent || (ListenerUtil.mutListener.listen(14147) ? (index >= 1) : (ListenerUtil.mutListener.listen(14146) ? (index <= 1) : (ListenerUtil.mutListener.listen(14145) ? (index > 1) : (ListenerUtil.mutListener.listen(14144) ? (index < 1) : (ListenerUtil.mutListener.listen(14143) ? (index != 1) : (index == 1))))))) : (showSilent && (ListenerUtil.mutListener.listen(14147) ? (index >= 1) : (ListenerUtil.mutListener.listen(14146) ? (index <= 1) : (ListenerUtil.mutListener.listen(14145) ? (index > 1) : (ListenerUtil.mutListener.listen(14144) ? (index < 1) : (ListenerUtil.mutListener.listen(14143) ? (index != 1) : (index == 1))))))))) && (ListenerUtil.mutListener.listen(14153) ? (index >= 0) : (ListenerUtil.mutListener.listen(14152) ? (index <= 0) : (ListenerUtil.mutListener.listen(14151) ? (index > 0) : (ListenerUtil.mutListener.listen(14150) ? (index < 0) : (ListenerUtil.mutListener.listen(14149) ? (index != 0) : (index == 0))))))) : (((ListenerUtil.mutListener.listen(14148) ? (showSilent || (ListenerUtil.mutListener.listen(14147) ? (index >= 1) : (ListenerUtil.mutListener.listen(14146) ? (index <= 1) : (ListenerUtil.mutListener.listen(14145) ? (index > 1) : (ListenerUtil.mutListener.listen(14144) ? (index < 1) : (ListenerUtil.mutListener.listen(14143) ? (index != 1) : (index == 1))))))) : (showSilent && (ListenerUtil.mutListener.listen(14147) ? (index >= 1) : (ListenerUtil.mutListener.listen(14146) ? (index <= 1) : (ListenerUtil.mutListener.listen(14145) ? (index > 1) : (ListenerUtil.mutListener.listen(14144) ? (index < 1) : (ListenerUtil.mutListener.listen(14143) ? (index != 1) : (index == 1))))))))) || (ListenerUtil.mutListener.listen(14153) ? (index >= 0) : (ListenerUtil.mutListener.listen(14152) ? (index <= 0) : (ListenerUtil.mutListener.listen(14151) ? (index > 0) : (ListenerUtil.mutListener.listen(14150) ? (index < 0) : (ListenerUtil.mutListener.listen(14149) ? (index != 0) : (index == 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(14155)) {
                            // "default" ringtone
                            selectedRingtone = RingtoneManager.getRingtone(getContext(), defaultUri);
                        }
                        return defaultUri;
                    }
                }
                if (!ListenerUtil.mutListener.listen(14157)) {
                    positionFix += 1;
                }
            }
        }
        Uri uri = null;
        try {
            if (!ListenerUtil.mutListener.listen(14164)) {
                uri = ringtoneManager.getRingtoneUri((ListenerUtil.mutListener.listen(14163) ? (index % positionFix) : (ListenerUtil.mutListener.listen(14162) ? (index / positionFix) : (ListenerUtil.mutListener.listen(14161) ? (index * positionFix) : (ListenerUtil.mutListener.listen(14160) ? (index + positionFix) : (index - positionFix))))));
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(14159)) {
                logger.error("Buggy Ringtone Manager", e);
            }
        }
        return uri;
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(14167)) {
            if ((ListenerUtil.mutListener.listen(14165) ? (this.cursor != null || !this.cursor.isClosed()) : (this.cursor != null && !this.cursor.isClosed()))) {
                if (!ListenerUtil.mutListener.listen(14166)) {
                    this.cursor.close();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14168)) {
            super.onDestroy();
        }
    }
}
