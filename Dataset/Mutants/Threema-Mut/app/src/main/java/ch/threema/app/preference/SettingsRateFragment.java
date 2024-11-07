/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.preference;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.BuildConfig;
import ch.threema.app.BuildFlavor;
import ch.threema.app.R;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.RateDialog;
import static ch.threema.app.ThreemaApplication.getAppContext;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SettingsRateFragment extends ThreemaPreferenceFragment implements RateDialog.RateDialogClickListener, GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(SettingsRateFragment.class);

    private static final String DIALOG_TAG_RATE = "rate";

    private static final String DIALOG_TAG_RATE_ON_GOOGLE_PLAY = "ratep";

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(32707)) {
            addPreferencesFromResource(R.xml.preference_rate);
        }
        RateDialog rateDialog = RateDialog.newInstance(getString(R.string.rate_title));
        if (!ListenerUtil.mutListener.listen(32708)) {
            rateDialog.setTargetFragment(SettingsRateFragment.this, 0);
        }
        if (!ListenerUtil.mutListener.listen(32709)) {
            rateDialog.show(getFragmentManager(), DIALOG_TAG_RATE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(32710)) {
            preferenceFragmentCallbackInterface.setToolbarTitle(R.string.rate_title);
        }
        if (!ListenerUtil.mutListener.listen(32711)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    private boolean startRating(Uri uri) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (!ListenerUtil.mutListener.listen(32712)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        }
        try {
            if (!ListenerUtil.mutListener.listen(32713)) {
                startActivity(intent);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void onYes(String tag, final int rating, final String text) {
        if (!ListenerUtil.mutListener.listen(32725)) {
            if ((ListenerUtil.mutListener.listen(32720) ? ((ListenerUtil.mutListener.listen(32718) ? (rating <= 4) : (ListenerUtil.mutListener.listen(32717) ? (rating > 4) : (ListenerUtil.mutListener.listen(32716) ? (rating < 4) : (ListenerUtil.mutListener.listen(32715) ? (rating != 4) : (ListenerUtil.mutListener.listen(32714) ? (rating == 4) : (rating >= 4)))))) || ((ListenerUtil.mutListener.listen(32719) ? (BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.GOOGLE && BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.NONE) : (BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.GOOGLE || BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.NONE)))) : ((ListenerUtil.mutListener.listen(32718) ? (rating <= 4) : (ListenerUtil.mutListener.listen(32717) ? (rating > 4) : (ListenerUtil.mutListener.listen(32716) ? (rating < 4) : (ListenerUtil.mutListener.listen(32715) ? (rating != 4) : (ListenerUtil.mutListener.listen(32714) ? (rating == 4) : (rating >= 4)))))) && ((ListenerUtil.mutListener.listen(32719) ? (BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.GOOGLE && BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.NONE) : (BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.GOOGLE || BuildFlavor.getLicenseType() == BuildFlavor.LicenseType.NONE)))))) {
                GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.rate_title, getString(R.string.rate_thank_you) + " " + getString(R.string.rate_forward_to_play_store), R.string.yes, R.string.no);
                if (!ListenerUtil.mutListener.listen(32723)) {
                    dialog.setTargetFragment(this);
                }
                if (!ListenerUtil.mutListener.listen(32724)) {
                    dialog.show(getFragmentManager(), DIALOG_TAG_RATE_ON_GOOGLE_PLAY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(32721)) {
                    Toast.makeText(getAppContext(), getString(R.string.rate_thank_you), Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(32722)) {
                    getActivity().onBackPressed();
                }
            }
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(32729)) {
            switch(tag) {
                case DIALOG_TAG_RATE_ON_GOOGLE_PLAY:
                    if (!ListenerUtil.mutListener.listen(32727)) {
                        if (!startRating(Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID))) {
                            if (!ListenerUtil.mutListener.listen(32726)) {
                                startRating(Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(32728)) {
                        getActivity().onBackPressed();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(32730)) {
            getActivity().onBackPressed();
        }
    }

    @Override
    public void onCancel(String tag) {
        if (!ListenerUtil.mutListener.listen(32731)) {
            getActivity().onBackPressed();
        }
    }
}
