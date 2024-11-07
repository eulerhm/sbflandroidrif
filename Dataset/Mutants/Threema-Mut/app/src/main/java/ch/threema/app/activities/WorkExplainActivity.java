/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WorkExplainActivity extends SimpleWebViewActivity {

    private static final String WORK_PACKAGE_NAME = "ch.threema.app.work";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7239)) {
            if (ConfigUtils.isAppInstalled(WORK_PACKAGE_NAME)) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(WORK_PACKAGE_NAME);
                if (!ListenerUtil.mutListener.listen(7237)) {
                    if (launchIntent != null) {
                        if (!ListenerUtil.mutListener.listen(7235)) {
                            startActivity(launchIntent);
                        }
                        if (!ListenerUtil.mutListener.listen(7236)) {
                            overridePendingTransition(0, 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7238)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7240)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected int getWebViewTitle() {
        return R.string.threema_work;
    }

    @Override
    protected String getWebViewUrl() {
        return ConfigUtils.getWorkExplainURL(this);
    }
}
