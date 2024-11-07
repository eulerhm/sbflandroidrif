package net.programmierecke.radiodroid2.station;

import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import net.programmierecke.radiodroid2.ActivityMain;
import net.programmierecke.radiodroid2.FavouriteManager;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioBrowserServerManager;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.alarm.TimePickerFragment;
import net.programmierecke.radiodroid2.players.selector.PlayerType;
import net.programmierecke.radiodroid2.views.ItemListDialog;
import java.lang.ref.WeakReference;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StationActions {

    private static final String TAG = "StationActions";

    public static void setAsAlarm(@NonNull final FragmentActivity activity, @NonNull final DataRadioStation station) {
        final RadioDroidApp radioDroidApp = (RadioDroidApp) activity.getApplicationContext();
        final TimePickerFragment newFragment = new TimePickerFragment();
        if (!ListenerUtil.mutListener.listen(3138)) {
            newFragment.setCallback((timePicker, hourOfDay, minute) -> {
                Log.i(TAG, String.format("Alarm time picked %d:%d", hourOfDay, minute));
                radioDroidApp.getAlarmManager().add(station, hourOfDay, minute);
            });
        }
        if (!ListenerUtil.mutListener.listen(3139)) {
            newFragment.show(activity.getSupportFragmentManager(), "timePicker");
        }
    }

    public static void showWebLinks(@NonNull final FragmentActivity activity, @NonNull final DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(3140)) {
            ItemListDialog.create(activity, new int[] { R.string.action_station_visit_website, R.string.action_station_copy_stream_url, R.string.action_station_share }, resourceId -> {
                switch(resourceId) {
                    case R.string.action_station_visit_website:
                        {
                            openStationHomeUrl(activity, station);
                            break;
                        }
                    case R.string.action_station_copy_stream_url:
                        {
                            retrieveAndCopyStreamUrlToClipboard(activity, station);
                            break;
                        }
                    case R.string.action_station_share:
                        {
                            share(activity, station);
                            break;
                        }
                }
            }).show();
        }
    }

    static void openStationHomeUrl(@NonNull final FragmentActivity activity, @NonNull final DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(3143)) {
            if (!TextUtils.isEmpty(station.HomePageUrl)) {
                Uri stationUrl = Uri.parse(station.HomePageUrl);
                if (!ListenerUtil.mutListener.listen(3142)) {
                    if (stationUrl != null) {
                        Intent newIntent = new Intent(Intent.ACTION_VIEW, stationUrl);
                        if (!ListenerUtil.mutListener.listen(3141)) {
                            activity.startActivity(newIntent);
                        }
                    }
                }
            }
        }
    }

    private static void retrieveAndCopyStreamUrlToClipboard(@NonNull final Context context, @NonNull final DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(3144)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ActivityMain.ACTION_SHOW_LOADING));
        }
        final WeakReference<Context> contextRef = new WeakReference<>(context);
        if (!ListenerUtil.mutListener.listen(3156)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    Context ctx = contextRef.get();
                    if (!ListenerUtil.mutListener.listen(3145)) {
                        if (ctx == null) {
                            return null;
                        }
                    }
                    final RadioDroidApp radioDroidApp = (RadioDroidApp) ctx.getApplicationContext();
                    final OkHttpClient httpClient = radioDroidApp.getHttpClient();
                    return Utils.getRealStationLink(httpClient, radioDroidApp, station.StationUuid);
                }

                @Override
                protected void onPostExecute(String result) {
                    Context ctx = contextRef.get();
                    if (!ListenerUtil.mutListener.listen(3147)) {
                        if (ctx == null) {
                            if (!ListenerUtil.mutListener.listen(3146)) {
                                super.onPostExecute(result);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3148)) {
                        LocalBroadcastManager.getInstance(ctx).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                    }
                    if (!ListenerUtil.mutListener.listen(3154)) {
                        if (result != null) {
                            ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
                            if (!ListenerUtil.mutListener.listen(3153)) {
                                if (clipboard != null) {
                                    ClipData clip = ClipData.newPlainText("Stream Url", result);
                                    if (!ListenerUtil.mutListener.listen(3151)) {
                                        clipboard.setPrimaryClip(clip);
                                    }
                                    CharSequence toastText = ctx.getResources().getText(R.string.notify_stream_url_copied);
                                    if (!ListenerUtil.mutListener.listen(3152)) {
                                        Toast.makeText(ctx.getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3150)) {
                                        Log.e(TAG, "Clipboard is NULL!");
                                    }
                                }
                            }
                        } else {
                            CharSequence toastText = ctx.getResources().getText(R.string.error_station_load);
                            if (!ListenerUtil.mutListener.listen(3149)) {
                                Toast.makeText(ctx.getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3155)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    public static void markAsFavourite(@NonNull final Context context, @NonNull final DataRadioStation station) {
        final RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        if (!ListenerUtil.mutListener.listen(3157)) {
            radioDroidApp.getFavouriteManager().add(station);
        }
        Toast toast = Toast.makeText(context, context.getString(R.string.notify_starred), Toast.LENGTH_SHORT);
        if (!ListenerUtil.mutListener.listen(3158)) {
            toast.show();
        }
        if (!ListenerUtil.mutListener.listen(3159)) {
            vote(context, station);
        }
    }

    public static void removeFromFavourites(@NonNull final Context context, @Nullable final View view, @NonNull final DataRadioStation station) {
        final RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        final FavouriteManager favouriteManager = radioDroidApp.getFavouriteManager();
        final int removedIdx = favouriteManager.remove(station.StationUuid);
        if (!ListenerUtil.mutListener.listen(3163)) {
            if (view != null) {
                final View viewAttachTo = view.getRootView().findViewById(R.id.fragment_player_small);
                Snackbar snackbar = Snackbar.make(viewAttachTo, R.string.notify_station_removed_from_list, 6000);
                if (!ListenerUtil.mutListener.listen(3160)) {
                    snackbar.setAnchorView(viewAttachTo);
                }
                if (!ListenerUtil.mutListener.listen(3161)) {
                    snackbar.setAction(R.string.action_station_removed_from_list_undo, view1 -> favouriteManager.restore(station, removedIdx));
                }
                if (!ListenerUtil.mutListener.listen(3162)) {
                    snackbar.show();
                }
            }
        }
    }

    public static void share(@NonNull final Context context, @NonNull final DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(3164)) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ActivityMain.ACTION_SHOW_LOADING));
        }
        final WeakReference<Context> contextRef = new WeakReference<>(context);
        if (!ListenerUtil.mutListener.listen(3175)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    Context ctx = contextRef.get();
                    if (!ListenerUtil.mutListener.listen(3165)) {
                        if (ctx == null) {
                            return null;
                        }
                    }
                    final RadioDroidApp radioDroidApp = (RadioDroidApp) ctx.getApplicationContext();
                    final OkHttpClient httpClient = radioDroidApp.getHttpClient();
                    return Utils.getRealStationLink(httpClient, radioDroidApp, station.StationUuid);
                }

                @Override
                protected void onPostExecute(String result) {
                    Context ctx = contextRef.get();
                    if (!ListenerUtil.mutListener.listen(3166)) {
                        if (ctx == null) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3167)) {
                        LocalBroadcastManager.getInstance(ctx).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                    }
                    if (!ListenerUtil.mutListener.listen(3173)) {
                        if (result != null) {
                            Intent share = new Intent(Intent.ACTION_SEND);
                            if (!ListenerUtil.mutListener.listen(3169)) {
                                share.setType("text/plain");
                            }
                            if (!ListenerUtil.mutListener.listen(3170)) {
                                share.putExtra(Intent.EXTRA_SUBJECT, station.Name);
                            }
                            if (!ListenerUtil.mutListener.listen(3171)) {
                                share.putExtra(Intent.EXTRA_TEXT, result);
                            }
                            String title = ctx.getResources().getString(R.string.share_action);
                            Intent chooser = Intent.createChooser(share, title);
                            if (!ListenerUtil.mutListener.listen(3172)) {
                                ctx.startActivity(chooser);
                            }
                        } else {
                            Toast toast = Toast.makeText(ctx.getApplicationContext(), ctx.getResources().getText(R.string.error_station_load), Toast.LENGTH_SHORT);
                            if (!ListenerUtil.mutListener.listen(3168)) {
                                toast.show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3174)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    public static void playInRadioDroid(@NonNull final Context context, @NonNull final DataRadioStation station) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        if (!ListenerUtil.mutListener.listen(3176)) {
            Utils.playAndWarnIfMetered(radioDroidApp, station, PlayerType.RADIODROID, () -> Utils.play(radioDroidApp, station));
        }
    }

    private static void vote(@NonNull final Context context, @NonNull final DataRadioStation station) {
        final WeakReference<Context> contextRef = new WeakReference<>(context);
        if (!ListenerUtil.mutListener.listen(3179)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    Context ctx = contextRef.get();
                    if (!ListenerUtil.mutListener.listen(3177)) {
                        if (ctx == null) {
                            return null;
                        }
                    }
                    final RadioDroidApp radioDroidApp = (RadioDroidApp) ctx.getApplicationContext();
                    final OkHttpClient httpClient = radioDroidApp.getHttpClient();
                    return Utils.downloadFeedRelative(httpClient, ctx, "json/vote/" + station.StationUuid, true, null);
                }

                @Override
                protected void onPostExecute(String result) {
                    if (!ListenerUtil.mutListener.listen(3178)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }
}
