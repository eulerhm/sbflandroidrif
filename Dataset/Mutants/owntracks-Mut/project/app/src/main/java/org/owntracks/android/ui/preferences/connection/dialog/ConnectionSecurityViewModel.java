package org.owntracks.android.ui.preferences.connection.dialog;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.widget.PopupMenu;
import org.owntracks.android.R;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.navigator.Navigator;
import java.io.FileOutputStream;
import java.io.InputStream;
import timber.log.Timber;
import static android.app.Activity.RESULT_OK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectionSecurityViewModel extends BaseDialogViewModel {

    private static final int REQUEST_CODE_FILE_CA_CRT = 1;

    private static final int REQUEST_CODE_FILE_CLIENT_CRT = 2;

    private final Navigator navigator;

    private final Context context;

    private boolean tls;

    private String tlsCaCrtName;

    private String tlsClientCrtName;

    private String tlsClientCrtPassword;

    private boolean tlsClientCrtNameDirty;

    private boolean tlsCaCrtNameDirty;

    private boolean tlsClientCrtPasswortDirty;

    public ConnectionSecurityViewModel(Preferences preferences, Navigator navigator, Context context) {
        super(preferences);
        this.navigator = navigator;
        this.context = context;
    }

    @Override
    public void load() {
        if (!ListenerUtil.mutListener.listen(1895)) {
            this.tls = preferences.getTls();
        }
        if (!ListenerUtil.mutListener.listen(1896)) {
            this.tlsCaCrtName = preferences.getTlsCaCrt();
        }
        if (!ListenerUtil.mutListener.listen(1897)) {
            this.tlsClientCrtName = preferences.getTlsClientCrt();
        }
        if (!ListenerUtil.mutListener.listen(1898)) {
            this.tlsClientCrtPassword = preferences.getTlsClientCrtPassword();
        }
    }

    @Override
    public void save() {
        if (!ListenerUtil.mutListener.listen(1899)) {
            preferences.setTls(tls);
        }
        if (!ListenerUtil.mutListener.listen(1901)) {
            if (tlsCaCrtNameDirty)
                if (!ListenerUtil.mutListener.listen(1900)) {
                    preferences.setTlsCaCrt(tlsCaCrtName == null ? "" : tlsCaCrtName);
                }
        }
        if (!ListenerUtil.mutListener.listen(1903)) {
            if (tlsClientCrtNameDirty)
                if (!ListenerUtil.mutListener.listen(1902)) {
                    preferences.setTlsClientCrt(tlsClientCrtName == null ? "" : tlsClientCrtName);
                }
        }
        if (!ListenerUtil.mutListener.listen(1905)) {
            if (tlsClientCrtPasswortDirty)
                if (!ListenerUtil.mutListener.listen(1904)) {
                    preferences.setTlsClientCrtPassword(tlsClientCrtPassword == null ? "" : tlsClientCrtPassword);
                }
        }
    }

    public boolean isTls() {
        return tls;
    }

    private void setTls(boolean tls) {
        if (!ListenerUtil.mutListener.listen(1906)) {
            this.tls = tls;
        }
        if (!ListenerUtil.mutListener.listen(1907)) {
            notifyChange();
        }
    }

    public String getTlsCaCrtName() {
        return tlsCaCrtName;
    }

    private void setTlsCaCrtName(String tlsCaCrtName) {
        if (!ListenerUtil.mutListener.listen(1908)) {
            this.tlsCaCrtName = tlsCaCrtName;
        }
        if (!ListenerUtil.mutListener.listen(1909)) {
            this.tlsCaCrtNameDirty = true;
        }
        if (!ListenerUtil.mutListener.listen(1910)) {
            notifyChange();
        }
    }

    public String getTlsClientCrtName() {
        return tlsClientCrtName;
    }

    private void setTlsClientCrtName(String tlsClientCrtName) {
        if (!ListenerUtil.mutListener.listen(1911)) {
            this.tlsClientCrtName = tlsClientCrtName;
        }
        if (!ListenerUtil.mutListener.listen(1912)) {
            this.tlsClientCrtNameDirty = true;
        }
        if (!ListenerUtil.mutListener.listen(1913)) {
            notifyChange();
        }
    }

    public String getTlsClientCrtPassword() {
        return tlsClientCrtPassword;
    }

    public void setTlsClientCrtPassword(String tlsClientCrtPassword) {
        if (!ListenerUtil.mutListener.listen(1914)) {
            this.tlsClientCrtPassword = tlsClientCrtPassword;
        }
        if (!ListenerUtil.mutListener.listen(1915)) {
            this.tlsClientCrtPasswortDirty = true;
        }
    }

    public void onTlsCheckedChanged(final CompoundButton ignored, boolean isChecked) {
        if (!ListenerUtil.mutListener.listen(1916)) {
            setTls(isChecked);
        }
    }

    public void onTlsCaCrtNameClick(final View v) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        if (!ListenerUtil.mutListener.listen(1917)) {
            popup.getMenuInflater().inflate(R.menu.picker, popup.getMenu());
        }
        if (!ListenerUtil.mutListener.listen(1918)) {
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.clear) {
                    setTlsCaCrtName(null);
                } else if (item.getItemId() == R.id.select) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("*/*");
                    try {
                        navigator.startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE_FILE_CA_CRT);
                    } catch (android.content.ActivityNotFoundException ex) {
                    }
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(1919)) {
            popup.show();
        }
    }

    public void onTlsClientCrtNameClick(final View v) {
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        if (!ListenerUtil.mutListener.listen(1920)) {
            popup.getMenuInflater().inflate(R.menu.picker, popup.getMenu());
        }
        if (!ListenerUtil.mutListener.listen(1921)) {
            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.clear) {
                    setTlsClientCrtName(null);
                } else if (item.getItemId() == R.id.select) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("*/*");
                    try {
                        navigator.startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE_FILE_CLIENT_CRT);
                    } catch (android.content.ActivityNotFoundException ex) {
                    }
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(1922)) {
            popup.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1944)) {
            if ((ListenerUtil.mutListener.listen(1934) ? (resultCode == RESULT_OK || ((ListenerUtil.mutListener.listen(1933) ? ((ListenerUtil.mutListener.listen(1927) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1926) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1925) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1924) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1923) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT)))))) && (ListenerUtil.mutListener.listen(1932) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1931) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1930) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1929) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1928) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT))))))) : ((ListenerUtil.mutListener.listen(1927) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1926) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1925) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1924) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1923) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT)))))) || (ListenerUtil.mutListener.listen(1932) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1931) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1930) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1929) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1928) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT)))))))))) : (resultCode == RESULT_OK && ((ListenerUtil.mutListener.listen(1933) ? ((ListenerUtil.mutListener.listen(1927) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1926) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1925) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1924) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1923) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT)))))) && (ListenerUtil.mutListener.listen(1932) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1931) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1930) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1929) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1928) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT))))))) : ((ListenerUtil.mutListener.listen(1927) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1926) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1925) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1924) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1923) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT)))))) || (ListenerUtil.mutListener.listen(1932) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1931) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1930) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1929) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (ListenerUtil.mutListener.listen(1928) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CLIENT_CRT)))))))))))) {
                Uri uri = data.getData();
                if (!ListenerUtil.mutListener.listen(1935)) {
                    Timber.v("uri:  %s,", uri.toString());
                }
                if (!ListenerUtil.mutListener.listen(1943)) {
                    if ((ListenerUtil.mutListener.listen(1940) ? (requestCode >= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1939) ? (requestCode <= ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1938) ? (requestCode > ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1937) ? (requestCode < ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (ListenerUtil.mutListener.listen(1936) ? (requestCode != ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT) : (requestCode == ConnectionSecurityViewModel.REQUEST_CODE_FILE_CA_CRT))))))) {
                        if (!ListenerUtil.mutListener.listen(1942)) {
                            new CaCrtCopyTask(context).execute(uri);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1941)) {
                            new ClientCrtCopyTask(context).execute(uri);
                        }
                    }
                }
            }
        }
    }

    private abstract class CopyTask extends AsyncTask<Uri, String, String> {

        protected final Context context;

        protected CopyTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Uri... params) {
            try {
                if (!ListenerUtil.mutListener.listen(1947)) {
                    Timber.v("CopyTask with URI: %s", params[0]);
                }
                String filename = uriToFilename(params[0]);
                if (!ListenerUtil.mutListener.listen(1948)) {
                    Timber.v("filename for save is: %s", filename);
                }
                InputStream inputStream = context.getApplicationContext().getContentResolver().openInputStream(params[0]);
                FileOutputStream outputStream = context.getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
                byte[] buffer = new byte[256];
                int bytesRead;
                if (!ListenerUtil.mutListener.listen(1955)) {
                    {
                        long _loopCounter18 = 0;
                        while ((ListenerUtil.mutListener.listen(1954) ? ((bytesRead = inputStream.read(buffer)) >= -1) : (ListenerUtil.mutListener.listen(1953) ? ((bytesRead = inputStream.read(buffer)) <= -1) : (ListenerUtil.mutListener.listen(1952) ? ((bytesRead = inputStream.read(buffer)) > -1) : (ListenerUtil.mutListener.listen(1951) ? ((bytesRead = inputStream.read(buffer)) < -1) : (ListenerUtil.mutListener.listen(1950) ? ((bytesRead = inputStream.read(buffer)) == -1) : ((bytesRead = inputStream.read(buffer)) != -1))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                            if (!ListenerUtil.mutListener.listen(1949)) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1956)) {
                    inputStream.close();
                }
                if (!ListenerUtil.mutListener.listen(1957)) {
                    outputStream.close();
                }
                if (!ListenerUtil.mutListener.listen(1958)) {
                    Timber.v("copied file to private storage: %s", filename);
                }
                return filename;
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(1945)) {
                    Timber.e(e);
                }
                if (!ListenerUtil.mutListener.listen(1946)) {
                    this.cancel(true);
                }
                return null;
            }
        }
    }

    private class CaCrtCopyTask extends CopyTask {

        protected CaCrtCopyTask(Context context) {
            super(context);
        }

        @Override
        protected void onPostExecute(String s) {
            if (!ListenerUtil.mutListener.listen(1959)) {
                Timber.v("crt copied %s", s);
            }
            if (!ListenerUtil.mutListener.listen(1960)) {
                setTlsCaCrtName(s);
            }
        }

        @Override
        protected void onCancelled(String s) {
            if (!ListenerUtil.mutListener.listen(1961)) {
                setTlsCaCrtName(null);
            }
            if (!ListenerUtil.mutListener.listen(1962)) {
                Toast.makeText(context.getApplicationContext(), context.getString(R.string.unableToCopyCertificate), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ClientCrtCopyTask extends CopyTask {

        protected ClientCrtCopyTask(Context context) {
            super(context);
        }

        @Override
        protected void onPostExecute(String s) {
            if (!ListenerUtil.mutListener.listen(1963)) {
                setTlsClientCrtName(s);
            }
        }

        @Override
        protected void onCancelled(String s) {
            if (!ListenerUtil.mutListener.listen(1964)) {
                setTlsClientCrtName(null);
            }
            if (!ListenerUtil.mutListener.listen(1965)) {
                Toast.makeText(context.getApplicationContext(), context.getString(R.string.unableToCopyCertificate), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String uriToFilename(Uri uri) {
        String result = null;
        if (!ListenerUtil.mutListener.listen(1969)) {
            if (uri.getScheme().equals("content")) {
                try (Cursor cursor = context.getApplicationContext().getContentResolver().query(uri, null, null, null, null)) {
                    if (!ListenerUtil.mutListener.listen(1968)) {
                        if ((ListenerUtil.mutListener.listen(1966) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                            if (!ListenerUtil.mutListener.listen(1967)) {
                                result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1982)) {
            if (result == null) {
                if (!ListenerUtil.mutListener.listen(1970)) {
                    result = uri.getPath();
                }
                int cut = result.lastIndexOf('/');
                if (!ListenerUtil.mutListener.listen(1981)) {
                    if ((ListenerUtil.mutListener.listen(1975) ? (cut >= -1) : (ListenerUtil.mutListener.listen(1974) ? (cut <= -1) : (ListenerUtil.mutListener.listen(1973) ? (cut > -1) : (ListenerUtil.mutListener.listen(1972) ? (cut < -1) : (ListenerUtil.mutListener.listen(1971) ? (cut == -1) : (cut != -1))))))) {
                        if (!ListenerUtil.mutListener.listen(1980)) {
                            result = result.substring((ListenerUtil.mutListener.listen(1979) ? (cut % 1) : (ListenerUtil.mutListener.listen(1978) ? (cut / 1) : (ListenerUtil.mutListener.listen(1977) ? (cut * 1) : (ListenerUtil.mutListener.listen(1976) ? (cut - 1) : (cut + 1))))));
                        }
                    }
                }
            }
        }
        return result;
    }
}
