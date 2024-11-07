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
package ch.threema.app.preference;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
@SuppressLint("Registered")
public class PreferenceActivityCompat extends AppCompatActivity implements PreferenceActivityCompatDelegate.Connector, PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private static final Logger logger = LoggerFactory.getLogger(PreferenceActivityCompat.class);

    private PreferenceActivityCompatDelegate mDelegate;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(31848)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(31849)) {
            mDelegate = new PreferenceActivityCompatDelegate(this, this);
        }
        if (!ListenerUtil.mutListener.listen(31850)) {
            mDelegate.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(31851)) {
            mDelegate.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(31852)) {
            super.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        try {
            if (!ListenerUtil.mutListener.listen(31854)) {
                super.onSaveInstanceState(outState);
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(31853)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(31855)) {
            mDelegate.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull final Bundle state) {
        if (!ListenerUtil.mutListener.listen(31856)) {
            super.onRestoreInstanceState(state);
        }
        if (!ListenerUtil.mutListener.listen(31857)) {
            mDelegate.onRestoreInstanceState(state);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(31858)) {
            if (mDelegate.onBackPressed()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(31859)) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onIsMultiPane() {
        return getResources().getBoolean(R.bool.tablet_layout);
    }

    @Override
    public void onBuildHeaders(@NonNull final List<Header> target) {
    }

    @Override
    public boolean isValidFragment(@Nullable final String fragmentName) {
        throw new RuntimeException("Subclasses of PreferenceActivity must override isValidFragment(String)" + " to verify that the Fragment class is valid! " + getClass().getName() + " has not checked if fragment " + fragmentName + " is valid.");
    }

    public int getSelectedItemPosition() {
        return mDelegate.getSelectedItemPosition();
    }

    public boolean hasHeaders() {
        return mDelegate.hasHeaders();
    }

    @NonNull
    public List<Header> getHeaders() {
        return mDelegate.getHeaders();
    }

    public boolean isMultiPane() {
        return mDelegate.isMultiPane();
    }

    public void invalidateHeaders() {
        if (!ListenerUtil.mutListener.listen(31860)) {
            mDelegate.invalidateHeaders();
        }
    }

    public void loadHeadersFromResource(@XmlRes final int resId, @NonNull final List<Header> target) {
        if (!ListenerUtil.mutListener.listen(31861)) {
            mDelegate.loadHeadersFromResource(resId, target);
        }
    }

    public void setListFooter(@NonNull final View view) {
        if (!ListenerUtil.mutListener.listen(31862)) {
            mDelegate.setListFooter(view);
        }
    }

    public void switchToHeader(@NonNull final Header header) {
        if (!ListenerUtil.mutListener.listen(31863)) {
            mDelegate.switchToHeader(header);
        }
    }

    @Override
    public boolean onPreferenceStartFragment(@NonNull final PreferenceFragmentCompat caller, @NonNull final Preference pref) {
        if (!ListenerUtil.mutListener.listen(31864)) {
            mDelegate.startPreferenceFragment(pref);
        }
        return true;
    }
}
