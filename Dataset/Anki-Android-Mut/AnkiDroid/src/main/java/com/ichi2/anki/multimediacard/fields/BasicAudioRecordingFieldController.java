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
package com.ichi2.anki.multimediacard.fields;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;
import com.ichi2.anki.R;
import com.ichi2.anki.multimediacard.AudioView;
import java.io.File;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BasicAudioRecordingFieldController extends FieldControllerBase implements IFieldController {

    /**
     * This controller always return a temporary path where it writes the audio
     */
    private String tempAudioPath;

    private AudioView mAudioView;

    @Override
    public void createUI(Context context, LinearLayout layout) {
        String origAudioPath = mField.getAudioPath();
        boolean bExist = false;
        if (!ListenerUtil.mutListener.listen(1560)) {
            if (origAudioPath != null) {
                File f = new File(origAudioPath);
                if (!ListenerUtil.mutListener.listen(1559)) {
                    if (f.exists()) {
                        if (!ListenerUtil.mutListener.listen(1557)) {
                            tempAudioPath = f.getAbsolutePath();
                        }
                        if (!ListenerUtil.mutListener.listen(1558)) {
                            bExist = true;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1562)) {
            if (!bExist) {
                if (!ListenerUtil.mutListener.listen(1561)) {
                    tempAudioPath = AudioView.generateTempAudioFile(mActivity);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1563)) {
            mAudioView = AudioView.createRecorderInstance(mActivity, R.drawable.av_play, R.drawable.av_pause, R.drawable.av_stop, R.drawable.av_rec, R.drawable.av_rec_stop, tempAudioPath);
        }
        if (!ListenerUtil.mutListener.listen(1564)) {
            mAudioView.setOnRecordingFinishEventListener(v -> {
                // FIXME is this okay if it is still null?
                mField.setAudioPath(tempAudioPath);
                mField.setHasTemporaryMedia(true);
            });
        }
        if (!ListenerUtil.mutListener.listen(1565)) {
            layout.addView(mAudioView, LinearLayout.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public void onDone() {
        if (!ListenerUtil.mutListener.listen(1566)) {
            mAudioView.notifyStopRecord();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onFocusLost() {
        if (!ListenerUtil.mutListener.listen(1567)) {
            mAudioView.notifyReleaseRecorder();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1568)) {
            mAudioView.notifyReleaseRecorder();
        }
    }
}
