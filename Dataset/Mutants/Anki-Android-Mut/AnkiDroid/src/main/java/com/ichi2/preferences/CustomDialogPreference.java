/**
 * ************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
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
package com.ichi2.preferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences.Editor;
import android.util.AttributeSet;
import android.widget.Toast;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.MetaDB;
import com.ichi2.anki.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
public class CustomDialogPreference extends android.preference.DialogPreference implements DialogInterface.OnClickListener {

    private final Context mContext;

    public CustomDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void onClick(DialogInterface dialog, int which) {
        if (!ListenerUtil.mutListener.listen(24710)) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (!ListenerUtil.mutListener.listen(24709)) {
                    if (this.getTitle().equals(mContext.getResources().getString(R.string.deck_conf_reset))) {
                        // Deck Options :: Restore Defaults for Options Group
                        Editor editor = AnkiDroidApp.getSharedPrefs(mContext).edit();
                        if (!ListenerUtil.mutListener.listen(24707)) {
                            editor.putBoolean("confReset", true);
                        }
                        if (!ListenerUtil.mutListener.listen(24708)) {
                            editor.commit();
                        }
                    } else if (this.getTitle().equals(mContext.getResources().getString(R.string.dialog_positive_remove))) {
                        // Deck Options :: Remove Options Group
                        Editor editor = AnkiDroidApp.getSharedPrefs(mContext).edit();
                        if (!ListenerUtil.mutListener.listen(24705)) {
                            editor.putBoolean("confRemove", true);
                        }
                        if (!ListenerUtil.mutListener.listen(24706)) {
                            editor.commit();
                        }
                    } else if (this.getTitle().equals(mContext.getResources().getString(R.string.deck_conf_set_subdecks))) {
                        // Deck Options :: Set Options Group for all Sub-decks
                        Editor editor = AnkiDroidApp.getSharedPrefs(mContext).edit();
                        if (!ListenerUtil.mutListener.listen(24703)) {
                            editor.putBoolean("confSetSubdecks", true);
                        }
                        if (!ListenerUtil.mutListener.listen(24704)) {
                            editor.commit();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24702)) {
                            // Main Preferences :: Reset Languages
                            if (MetaDB.resetLanguages(mContext)) {
                                Toast successReport = Toast.makeText(this.getContext(), AnkiDroidApp.getAppResources().getString(R.string.reset_confirmation), Toast.LENGTH_SHORT);
                                if (!ListenerUtil.mutListener.listen(24701)) {
                                    successReport.show();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
