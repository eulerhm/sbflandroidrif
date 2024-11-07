// Copyright 2015 Google Inc. All Rights Reserved.
// limitations under the License.
package com.ichi2.compat.customtabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A Fallback that opens a Webview when Custom Tabs is not available
 */
public class CustomTabsFallback implements CustomTabActivityHelper.CustomTabFallback {

    @Override
    public void openUri(Activity activity, Uri uri) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!ListenerUtil.mutListener.listen(13197)) {
                activity.startActivity(intent);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(13195)) {
                // Add an exception report to see if I'm wrong
                AnkiDroidApp.sendExceptionReport(e, "CustomTabsFallback::openUri");
            }
            if (!ListenerUtil.mutListener.listen(13196)) {
                UIUtils.showThemedToast(activity, activity.getString(R.string.web_page_error, uri), false);
            }
        }
    }
}
