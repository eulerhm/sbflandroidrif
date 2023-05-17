package net.programmierecke.radiodroid2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import net.programmierecke.radiodroid2.interfaces.IApplicationSelected;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ApplicationSelectorDialog extends DialogFragment {

    ArrayList<ActivityInfo> listInfos = new ArrayList<ActivityInfo>();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        PackageManager pm = getContext().getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_VIEW);
        if (!ListenerUtil.mutListener.listen(4183)) {
            mainIntent.setDataAndType(Uri.parse("http://example.com/test.mp3"), "audio/*");
        }
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (!ListenerUtil.mutListener.listen(4188)) {
            {
                long _loopCounter48 = 0;
                for (ResolveInfo info : resolveInfos) {
                    ListenerUtil.loopListener.listen("_loopCounter48", ++_loopCounter48);
                    ApplicationInfo applicationInfo = info.activityInfo.applicationInfo;
                    if (!ListenerUtil.mutListener.listen(4185)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(4184)) {
                                Log.d("UUU", "" + applicationInfo.packageName + " -- " + info.activityInfo.name + " -> ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4186)) {
                        arrayAdapter.add("" + pm.getApplicationLabel(applicationInfo));
                    }
                    if (!ListenerUtil.mutListener.listen(4187)) {
                        listInfos.add(info.activityInfo);
                    }
                }
            }
        }
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(4189)) {
            builder.setTitle(R.string.alert_select_external_alarm_app);
        }
        if (!ListenerUtil.mutListener.listen(4194)) {
            builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(4191)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(4190)) {
                                Log.d("AAA", "choose : " + which);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4193)) {
                        if (callback != null) {
                            ActivityInfo info = listInfos.get(which);
                            if (!ListenerUtil.mutListener.listen(4192)) {
                                callback.onAppSelected(info.packageName, info.name);
                            }
                        }
                    }
                }
            });
        }
        // Create the AlertDialog object and return it
        return builder.create();
    }

    IApplicationSelected callback;

    public void setCallback(IApplicationSelected callback) {
        if (!ListenerUtil.mutListener.listen(4195)) {
            this.callback = callback;
        }
    }
}
