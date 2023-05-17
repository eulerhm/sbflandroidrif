/**
 * *************************************************************************************
 *  Copyright (c) 2020 Mike Hardy <github@mikehardy.net>                                 *
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
package com.ichi2.anki.multimediacard.fields;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Collection;
import com.ichi2.ui.FixedTextView;
import java.io.File;
import java.io.InputStream;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BasicAudioClipFieldController extends FieldControllerBase implements IFieldController {

    private static final int ACTIVITY_SELECT_AUDIO_CLIP = 1;

    private File storingDirectory;

    private TextView mTvAudioClip;

    @Override
    public void createUI(Context context, LinearLayout layout) {
        Collection col = CollectionHelper.getInstance().getCol(context);
        if (!ListenerUtil.mutListener.listen(1514)) {
            storingDirectory = new File(col.getMedia().dir());
        }
        Button mBtnLibrary = new Button(mActivity);
        if (!ListenerUtil.mutListener.listen(1515)) {
            mBtnLibrary.setText(mActivity.getText(R.string.multimedia_editor_image_field_editing_library));
        }
        if (!ListenerUtil.mutListener.listen(1516)) {
            mBtnLibrary.setOnClickListener(v -> {
                Intent i = new Intent();
                i.setType("audio/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                // Only get openable files, to avoid virtual files issues with Android 7+
                i.addCategory(Intent.CATEGORY_OPENABLE);
                String chooserPrompt = mActivity.getResources().getString(R.string.multimedia_editor_popup_audio_clip);
                mActivity.startActivityForResultWithoutAnimation(Intent.createChooser(i, chooserPrompt), ACTIVITY_SELECT_AUDIO_CLIP);
            });
        }
        if (!ListenerUtil.mutListener.listen(1517)) {
            layout.addView(mBtnLibrary, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (!ListenerUtil.mutListener.listen(1518)) {
            mTvAudioClip = new FixedTextView(mActivity);
        }
        if (!ListenerUtil.mutListener.listen(1522)) {
            if (mField.getAudioPath() == null) {
                if (!ListenerUtil.mutListener.listen(1521)) {
                    mTvAudioClip.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1519)) {
                    mTvAudioClip.setText(mField.getAudioPath());
                }
                if (!ListenerUtil.mutListener.listen(1520)) {
                    mTvAudioClip.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1523)) {
            layout.addView(mTvAudioClip, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1533)) {
            if ((ListenerUtil.mutListener.listen(1529) ? ((resultCode != Activity.RESULT_CANCELED) || ((ListenerUtil.mutListener.listen(1528) ? (requestCode >= ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1527) ? (requestCode <= ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1526) ? (requestCode > ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1525) ? (requestCode < ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1524) ? (requestCode != ACTIVITY_SELECT_AUDIO_CLIP) : (requestCode == ACTIVITY_SELECT_AUDIO_CLIP)))))))) : ((resultCode != Activity.RESULT_CANCELED) && ((ListenerUtil.mutListener.listen(1528) ? (requestCode >= ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1527) ? (requestCode <= ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1526) ? (requestCode > ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1525) ? (requestCode < ACTIVITY_SELECT_AUDIO_CLIP) : (ListenerUtil.mutListener.listen(1524) ? (requestCode != ACTIVITY_SELECT_AUDIO_CLIP) : (requestCode == ACTIVITY_SELECT_AUDIO_CLIP)))))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(1532)) {
                        handleAudioSelection(data);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1530)) {
                        AnkiDroidApp.sendExceptionReport(e, "handleAudioSelection:unhandled");
                    }
                    if (!ListenerUtil.mutListener.listen(1531)) {
                        UIUtils.showThemedToast(AnkiDroidApp.getInstance().getApplicationContext(), AnkiDroidApp.getInstance().getString(R.string.multimedia_editor_something_wrong), true);
                    }
                }
            }
        }
    }

    private void handleAudioSelection(Intent data) {
        Uri selectedClip = data.getData();
        // Get information about the selected document
        String[] queryColumns = { MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.SIZE, MediaStore.MediaColumns.MIME_TYPE };
        String[] audioClipFullNameParts;
        try (Cursor cursor = mActivity.getContentResolver().query(selectedClip, queryColumns, null, null, null)) {
            if (!ListenerUtil.mutListener.listen(1535)) {
                if (cursor == null) {
                    if (!ListenerUtil.mutListener.listen(1534)) {
                        UIUtils.showThemedToast(AnkiDroidApp.getInstance().getApplicationContext(), AnkiDroidApp.getInstance().getString(R.string.multimedia_editor_something_wrong), true);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1536)) {
                cursor.moveToFirst();
            }
            String audioClipFullName = cursor.getString(0);
            audioClipFullNameParts = audioClipFullName.split("\\.");
            if ((ListenerUtil.mutListener.listen(1541) ? (audioClipFullNameParts.length >= 2) : (ListenerUtil.mutListener.listen(1540) ? (audioClipFullNameParts.length <= 2) : (ListenerUtil.mutListener.listen(1539) ? (audioClipFullNameParts.length > 2) : (ListenerUtil.mutListener.listen(1538) ? (audioClipFullNameParts.length != 2) : (ListenerUtil.mutListener.listen(1537) ? (audioClipFullNameParts.length == 2) : (audioClipFullNameParts.length < 2))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(1544)) {
                        Timber.i("Audio clip name does not have extension, using second half of mime type");
                    }
                    audioClipFullNameParts = new String[] { audioClipFullName, cursor.getString(2).split("/")[1] };
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(1542)) {
                        // about what people are experiencing in the real world and decide later, but without crashing at least
                        AnkiDroidApp.sendExceptionReport(e, "Audio Clip addition failed. Name " + audioClipFullName + " / cursor mime type column type " + cursor.getType(2));
                    }
                    if (!ListenerUtil.mutListener.listen(1543)) {
                        UIUtils.showThemedToast(AnkiDroidApp.getInstance().getApplicationContext(), AnkiDroidApp.getInstance().getString(R.string.multimedia_editor_something_wrong), true);
                    }
                    return;
                }
            }
        }
        // We may receive documents we can't access directly, we have to copy to a temp file
        File clipCopy;
        try {
            clipCopy = File.createTempFile("ankidroid_audioclip_" + audioClipFullNameParts[0], "." + audioClipFullNameParts[1], storingDirectory);
            if (!ListenerUtil.mutListener.listen(1548)) {
                Timber.d("audio clip picker file path is: %s", clipCopy.getAbsolutePath());
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1545)) {
                Timber.e(e, "Could not create temporary audio file. ");
            }
            if (!ListenerUtil.mutListener.listen(1546)) {
                AnkiDroidApp.sendExceptionReport(e, "handleAudioSelection:tempFile");
            }
            if (!ListenerUtil.mutListener.listen(1547)) {
                UIUtils.showThemedToast(AnkiDroidApp.getInstance().getApplicationContext(), AnkiDroidApp.getInstance().getString(R.string.multimedia_editor_something_wrong), true);
            }
            return;
        }
        // Copy file contents into new temp file. Possibly check file size first and warn if large?
        try (InputStream inputStream = mActivity.getContentResolver().openInputStream(selectedClip)) {
            if (!ListenerUtil.mutListener.listen(1552)) {
                CompatHelper.getCompat().copyFile(inputStream, clipCopy.getAbsolutePath());
            }
            if (!ListenerUtil.mutListener.listen(1553)) {
                // If everything worked, hand off the information
                mField.setHasTemporaryMedia(true);
            }
            if (!ListenerUtil.mutListener.listen(1554)) {
                mField.setAudioPath(clipCopy.getAbsolutePath());
            }
            if (!ListenerUtil.mutListener.listen(1555)) {
                mTvAudioClip.setText(mField.getFormattedValue());
            }
            if (!ListenerUtil.mutListener.listen(1556)) {
                mTvAudioClip.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1549)) {
                Timber.e(e, "Unable to copy audio file from ContentProvider");
            }
            if (!ListenerUtil.mutListener.listen(1550)) {
                AnkiDroidApp.sendExceptionReport(e, "handleAudioSelection:copyFromProvider");
            }
            if (!ListenerUtil.mutListener.listen(1551)) {
                UIUtils.showThemedToast(AnkiDroidApp.getInstance().getApplicationContext(), AnkiDroidApp.getInstance().getString(R.string.multimedia_editor_something_wrong), true);
            }
        }
    }

    @Override
    public void onDone() {
    }

    @Override
    public void onFocusLost() {
    }

    @Override
    public void onDestroy() {
    }
}
