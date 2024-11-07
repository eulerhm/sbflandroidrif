package net.programmierecke.radiodroid2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentBase extends Fragment {

    private static final String TAG = "FragmentBase";

    private String relativeUrl;

    private String urlResult;

    private boolean isCreated = false;

    private AsyncTask task = null;

    public FragmentBase() {
    }

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(4244)) {
            super.onAttach(context);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4245)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4246)) {
            isCreated = true;
        }
        if (!ListenerUtil.mutListener.listen(4248)) {
            if (relativeUrl == null) {
                Bundle bundle = this.getArguments();
                if (!ListenerUtil.mutListener.listen(4247)) {
                    relativeUrl = bundle.getString("url");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4249)) {
            DownloadUrl(false);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4251)) {
            if (task != null) {
                if (!ListenerUtil.mutListener.listen(4250)) {
                    task.cancel(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4252)) {
            super.onDestroy();
        }
    }

    protected String getUrlResult() {
        return urlResult;
    }

    protected boolean hasUrl() {
        return !TextUtils.isEmpty(relativeUrl);
    }

    public void DownloadUrl(final boolean forceUpdate) {
        if (!ListenerUtil.mutListener.listen(4253)) {
            DownloadUrl(forceUpdate, true);
        }
    }

    public void DownloadUrl(final boolean forceUpdate, final boolean displayProgress) {
        if (!ListenerUtil.mutListener.listen(4254)) {
            if (!isCreated) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4257)) {
            if (task != null) {
                if (!ListenerUtil.mutListener.listen(4255)) {
                    task.cancel(true);
                }
                if (!ListenerUtil.mutListener.listen(4256)) {
                    task = null;
                }
            }
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        final boolean show_broken = sharedPref.getBoolean("show_broken", false);
        if (!ListenerUtil.mutListener.listen(4259)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(4258)) {
                    Log.d(TAG, "Download relativeUrl:" + relativeUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4284)) {
            if (TextUtils.isGraphic(relativeUrl)) {
                String cache = Utils.getCacheFile(getActivity(), relativeUrl);
                if (!ListenerUtil.mutListener.listen(4283)) {
                    if ((ListenerUtil.mutListener.listen(4261) ? (cache == null && forceUpdate) : (cache == null || forceUpdate))) {
                        if (!ListenerUtil.mutListener.listen(4267)) {
                            if ((ListenerUtil.mutListener.listen(4265) ? (getContext() != null || displayProgress) : (getContext() != null && displayProgress))) {
                                if (!ListenerUtil.mutListener.listen(4266)) {
                                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_SHOW_LOADING));
                                }
                            }
                        }
                        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
                        final OkHttpClient httpClient = radioDroidApp.getHttpClient();
                        if (!ListenerUtil.mutListener.listen(4282)) {
                            task = new AsyncTask<Void, Void, String>() {

                                @Override
                                protected String doInBackground(Void... params) {
                                    HashMap<String, String> p = new HashMap<String, String>();
                                    if (!ListenerUtil.mutListener.listen(4268)) {
                                        p.put("hidebroken", "" + (!show_broken));
                                    }
                                    return Utils.downloadFeedRelative(httpClient, getActivity(), relativeUrl, forceUpdate, p);
                                }

                                @Override
                                protected void onPostExecute(String result) {
                                    if (!ListenerUtil.mutListener.listen(4269)) {
                                        DownloadFinished();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4271)) {
                                        if (getContext() != null)
                                            if (!ListenerUtil.mutListener.listen(4270)) {
                                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                                            }
                                    }
                                    if (!ListenerUtil.mutListener.listen(4273)) {
                                        if (BuildConfig.DEBUG) {
                                            if (!ListenerUtil.mutListener.listen(4272)) {
                                                Log.d(TAG, "Download relativeUrl finished:" + relativeUrl);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(4280)) {
                                        if (result != null) {
                                            if (!ListenerUtil.mutListener.listen(4277)) {
                                                if (BuildConfig.DEBUG) {
                                                    if (!ListenerUtil.mutListener.listen(4276)) {
                                                        Log.d(TAG, "Download relativeUrl OK:" + relativeUrl);
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(4278)) {
                                                urlResult = result;
                                            }
                                            if (!ListenerUtil.mutListener.listen(4279)) {
                                                RefreshListGui();
                                            }
                                        } else {
                                            try {
                                                Toast toast = Toast.makeText(getContext(), getResources().getText(R.string.error_list_update), Toast.LENGTH_SHORT);
                                                if (!ListenerUtil.mutListener.listen(4275)) {
                                                    toast.show();
                                                }
                                            } catch (Exception e) {
                                                if (!ListenerUtil.mutListener.listen(4274)) {
                                                    Log.e("ERR", e.toString());
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(4281)) {
                                        super.onPostExecute(result);
                                    }
                                }
                            }.execute();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4262)) {
                            urlResult = cache;
                        }
                        if (!ListenerUtil.mutListener.listen(4263)) {
                            DownloadFinished();
                        }
                        if (!ListenerUtil.mutListener.listen(4264)) {
                            RefreshListGui();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4260)) {
                    RefreshListGui();
                }
            }
        }
    }

    protected void RefreshListGui() {
    }

    protected void DownloadFinished() {
    }
}
