/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MainActivity extends ThreemaAppCompatActivity {

    // to avoid "class has no zero argument constructor" on some devices
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4308)) {
            super.onCreate(savedInstanceState);
        }
        Intent intent = new Intent(this, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(4309)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(4310)) {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
        if (!ListenerUtil.mutListener.listen(4311)) {
            finish();
        }
    }
}
