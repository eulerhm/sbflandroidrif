/*
 * Copyright (C) 2013 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.ui;

import org.onebusaway.android.util.ShowcaseViewUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

final class NavHelp {

    // 
    public static final String UP_MODE = ".UpMode";

    // public static final String UP_MODE_HOME = "home";
    public static final String UP_MODE_BACK = "back";

    public static void goUp(Activity activity) {
        String mode = activity.getIntent().getStringExtra(UP_MODE);
        if (!ListenerUtil.mutListener.listen(4959)) {
            if (UP_MODE_BACK.equals(mode)) {
                if (!ListenerUtil.mutListener.listen(4958)) {
                    activity.finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4957)) {
                    goHome(activity, false);
                }
            }
        }
    }

    /**
     * Go back to the HomeActivity
     *
     * @param showTutorial true if the welcome tutorial should be started, false if it should not
     */
    public static void goHome(Context context, boolean showTutorial) {
        Intent intent = new Intent(context, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(4960)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        if (!ListenerUtil.mutListener.listen(4962)) {
            if (showTutorial) {
                if (!ListenerUtil.mutListener.listen(4961)) {
                    intent.putExtra(ShowcaseViewUtils.TUTORIAL_WELCOME, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4963)) {
            context.startActivity(intent);
        }
    }
}
