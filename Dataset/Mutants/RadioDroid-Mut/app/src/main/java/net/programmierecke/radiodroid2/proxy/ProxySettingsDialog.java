package net.programmierecke.radiodroid2.proxy;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static net.programmierecke.radiodroid2.Utils.parseIntWithDefault;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ProxySettingsDialog extends DialogFragment {

    private static final String TEST_ADDRESS = "http://radio-browser.info";

    private EditText editProxyHost;

    private EditText editProxyPort;

    private AppCompatSpinner spinnerProxyType;

    private EditText editLogin;

    private EditText editProxyPassword;

    private TextView textProxyTestResult;

    private ArrayAdapter<Proxy.Type> proxyTypeAdapter;

    private AsyncTask<Void, Void, Void> proxyTestTask;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_proxy_settings, null);
        if (!ListenerUtil.mutListener.listen(1527)) {
            editProxyHost = layout.findViewById(R.id.edit_proxy_host);
        }
        if (!ListenerUtil.mutListener.listen(1528)) {
            editProxyPort = layout.findViewById(R.id.edit_proxy_port);
        }
        if (!ListenerUtil.mutListener.listen(1529)) {
            spinnerProxyType = layout.findViewById(R.id.spinner_proxy_type);
        }
        if (!ListenerUtil.mutListener.listen(1530)) {
            editLogin = layout.findViewById(R.id.edit_proxy_login);
        }
        if (!ListenerUtil.mutListener.listen(1531)) {
            editProxyPassword = layout.findViewById(R.id.edit_proxy_password);
        }
        if (!ListenerUtil.mutListener.listen(1532)) {
            textProxyTestResult = layout.findViewById(R.id.text_test_proxy_result);
        }
        if (!ListenerUtil.mutListener.listen(1533)) {
            proxyTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, new Proxy.Type[] { Proxy.Type.DIRECT, Proxy.Type.HTTP, Proxy.Type.SOCKS });
        }
        if (!ListenerUtil.mutListener.listen(1534)) {
            spinnerProxyType.setAdapter(proxyTypeAdapter);
        }
        {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
            ProxySettings proxySettings = ProxySettings.fromPreferences(sharedPref);
            if (!ListenerUtil.mutListener.listen(1540)) {
                if (proxySettings != null) {
                    if (!ListenerUtil.mutListener.listen(1535)) {
                        editProxyHost.setText(proxySettings.host);
                    }
                    if (!ListenerUtil.mutListener.listen(1536)) {
                        editProxyPort.setText(Integer.toString(proxySettings.port));
                    }
                    if (!ListenerUtil.mutListener.listen(1537)) {
                        editLogin.setText(proxySettings.login);
                    }
                    if (!ListenerUtil.mutListener.listen(1538)) {
                        editProxyPassword.setText(proxySettings.password);
                    }
                    if (!ListenerUtil.mutListener.listen(1539)) {
                        spinnerProxyType.setSelection(proxyTypeAdapter.getPosition(proxySettings.type));
                    }
                }
            }
        }
        final Dialog dialog = builder.setView(layout).setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                ProxySettings proxySettings = createProxySettings();
                if (!ListenerUtil.mutListener.listen(1542)) {
                    proxySettings.toPreferences(editor);
                }
                if (!ListenerUtil.mutListener.listen(1543)) {
                    editor.apply();
                }
                RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
                if (!ListenerUtil.mutListener.listen(1544)) {
                    radioDroidApp.rebuildHttpClient();
                }
            }
        }).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                if (!ListenerUtil.mutListener.listen(1541)) {
                    ProxySettingsDialog.this.getDialog().cancel();
                }
            }
        }).setNeutralButton(R.string.settings_proxy_action_test, null).create();
        if (!ListenerUtil.mutListener.listen(1547)) {
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEUTRAL);
                    if (!ListenerUtil.mutListener.listen(1546)) {
                        button.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                ProxySettings proxySettings = createProxySettings();
                                if (!ListenerUtil.mutListener.listen(1545)) {
                                    testProxy(proxySettings);
                                }
                            }
                        });
                    }
                }
            });
        }
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(1548)) {
            super.onDismiss(dialog);
        }
        if (!ListenerUtil.mutListener.listen(1550)) {
            if (proxyTestTask != null) {
                if (!ListenerUtil.mutListener.listen(1549)) {
                    proxyTestTask.cancel(true);
                }
            }
        }
    }

    private ProxySettings createProxySettings() {
        ProxySettings settings = new ProxySettings();
        if (!ListenerUtil.mutListener.listen(1551)) {
            settings.host = editProxyHost.getText().toString();
        }
        if (!ListenerUtil.mutListener.listen(1552)) {
            settings.port = parseIntWithDefault(editProxyPort.getText().toString(), 0);
        }
        if (!ListenerUtil.mutListener.listen(1553)) {
            settings.login = editLogin.getText().toString();
        }
        if (!ListenerUtil.mutListener.listen(1554)) {
            settings.password = editProxyPassword.getText().toString();
        }
        if (!ListenerUtil.mutListener.listen(1555)) {
            settings.type = proxyTypeAdapter.getItem(spinnerProxyType.getSelectedItemPosition());
        }
        return settings;
    }

    private static class ConnectionTesterTask extends AsyncTask<Void, Void, Void> {

        private WeakReference<TextView> textProxyTestResult;

        private OkHttpClient okHttpClient;

        private Call call;

        private String connectionSuccessStr;

        private String connectionFailedStr;

        private String connectionInvalidInputStr;

        private boolean requestSucceeded = false;

        private String errorStr;

        private ConnectionTesterTask(@NonNull RadioDroidApp radioDroidApp, @NonNull TextView textProxyTestResult, @NonNull ProxySettings proxySettings) {
            if (!ListenerUtil.mutListener.listen(1556)) {
                this.textProxyTestResult = new WeakReference<>(textProxyTestResult);
            }
            if (!ListenerUtil.mutListener.listen(1557)) {
                textProxyTestResult.setText("");
            }
            if (!ListenerUtil.mutListener.listen(1558)) {
                connectionSuccessStr = radioDroidApp.getString(R.string.settings_proxy_working, TEST_ADDRESS);
            }
            if (!ListenerUtil.mutListener.listen(1559)) {
                connectionFailedStr = radioDroidApp.getString(R.string.settings_proxy_not_working);
            }
            if (!ListenerUtil.mutListener.listen(1560)) {
                connectionInvalidInputStr = radioDroidApp.getString(R.string.settings_proxy_invalid);
            }
            OkHttpClient.Builder builder = radioDroidApp.newHttpClientWithoutProxy().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS);
            if (!ListenerUtil.mutListener.listen(1562)) {
                if (!Utils.setOkHttpProxy(builder, proxySettings)) {
                } else {
                    if (!ListenerUtil.mutListener.listen(1561)) {
                        okHttpClient = builder.build();
                    }
                }
            }
        }

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(1563)) {
                super.onPreExecute();
            }
            if (!ListenerUtil.mutListener.listen(1564)) {
                if (okHttpClient == null)
                    return;
            }
            Request.Builder builder = new Request.Builder().url(TEST_ADDRESS);
            if (!ListenerUtil.mutListener.listen(1565)) {
                call = okHttpClient.newCall(builder.build());
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (!ListenerUtil.mutListener.listen(1566)) {
                if (okHttpClient == null)
                    return null;
            }
            try {
                Response response = call.execute();
                if (!ListenerUtil.mutListener.listen(1569)) {
                    requestSucceeded = response.isSuccessful();
                }
                if (!ListenerUtil.mutListener.listen(1571)) {
                    if (!requestSucceeded) {
                        if (!ListenerUtil.mutListener.listen(1570)) {
                            errorStr = response.message();
                        }
                    }
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(1567)) {
                    requestSucceeded = false;
                }
                if (!ListenerUtil.mutListener.listen(1568)) {
                    errorStr = e.getMessage();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            if (!ListenerUtil.mutListener.listen(1572)) {
                super.onPostExecute(v);
            }
            TextView textResult = textProxyTestResult.get();
            if (!ListenerUtil.mutListener.listen(1573)) {
                if (textResult == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1578)) {
                if (okHttpClient == null) {
                    if (!ListenerUtil.mutListener.listen(1577)) {
                        textResult.setText(connectionInvalidInputStr);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1576)) {
                        if (requestSucceeded) {
                            if (!ListenerUtil.mutListener.listen(1575)) {
                                textResult.setText(connectionSuccessStr);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1574)) {
                                textResult.setText(String.format(connectionFailedStr, TEST_ADDRESS, errorStr));
                            }
                        }
                    }
                }
            }
        }
    }

    private void testProxy(@NonNull ProxySettings proxySettings) {
        if (!ListenerUtil.mutListener.listen(1580)) {
            if (proxyTestTask != null) {
                if (!ListenerUtil.mutListener.listen(1579)) {
                    proxyTestTask.cancel(true);
                }
            }
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(1581)) {
            proxyTestTask = new ConnectionTesterTask(radioDroidApp, textProxyTestResult, proxySettings);
        }
        if (!ListenerUtil.mutListener.listen(1582)) {
            proxyTestTask.execute();
        }
    }
}
