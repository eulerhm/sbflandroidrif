package org.owntracks.android.ui.preferences.load;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.Observable;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiPreferencesLoadBinding;
import org.owntracks.android.support.Events;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.navigator.Navigator;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressLint("GoogleAppIndexingApiWarning")
public class LoadActivity extends BaseActivity<UiPreferencesLoadBinding, LoadMvvm.ViewModel<LoadMvvm.View>> implements LoadMvvm.View {

    private static final int REQUEST_CODE = 1;

    public static final String FLAG_IN_APP = "INAPP";

    @Inject
    Navigator navigator;

    @Inject
    EventBus eventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2088)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2089)) {
            bindAndAttachContentView(R.layout.ui_preferences_load, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2090)) {
            setHasEventBus(false);
        }
        if (!ListenerUtil.mutListener.listen(2091)) {
            setSupportToolbar(binding.toolbar, true, false);
        }
        if (!ListenerUtil.mutListener.listen(2093)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(2092)) {
                    getSupportActionBar().setTitle(R.string.title_activity_load);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2094)) {
            binding.getVm().addOnPropertyChangedCallback(propertyChangedCallback);
        }
        if (!ListenerUtil.mutListener.listen(2095)) {
            handleIntent(getIntent());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(2096)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(2097)) {
            setHasBack(false);
        }
        if (!ListenerUtil.mutListener.listen(2098)) {
            handleIntent(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save:
                if (!ListenerUtil.mutListener.listen(2099)) {
                    viewModel.saveConfiguration();
                }
                return true;
            case R.id.close:
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(2100)) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setHasBack(boolean hasBackArrow) {
        if (!ListenerUtil.mutListener.listen(2102)) {
            if (getSupportActionBar() != null)
                if (!ListenerUtil.mutListener.listen(2101)) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackArrow);
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2103)) {
            getMenuInflater().inflate(R.menu.activity_load, menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2104)) {
            menu.findItem(R.id.close).setVisible(viewModel.getConfigurationImportStatus() != ImportStatus.LOADING);
        }
        if (!ListenerUtil.mutListener.listen(2105)) {
            menu.findItem(R.id.save).setVisible(viewModel.getConfigurationImportStatus() == ImportStatus.SUCCESS);
        }
        return true;
    }

    private final Observable.OnPropertyChangedCallback propertyChangedCallback = new Observable.OnPropertyChangedCallback() {

        @Override
        public void onPropertyChanged(Observable observable, int i) {
            if (!ListenerUtil.mutListener.listen(2106)) {
                invalidateOptionsMenu();
            }
        }
    };

    private void handleIntent(@Nullable Intent intent) {
        if (!ListenerUtil.mutListener.listen(2108)) {
            if (intent == null) {
                if (!ListenerUtil.mutListener.listen(2107)) {
                    Timber.e("no intent provided");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2109)) {
            setHasBack(navigator.getExtrasBundle(getIntent()).getBoolean(FLAG_IN_APP, false));
        }
        if (!ListenerUtil.mutListener.listen(2110)) {
            Timber.v("inApp %s", intent.getBooleanExtra(FLAG_IN_APP, false));
        }
        final String action = intent.getAction();
        if (!ListenerUtil.mutListener.listen(2127)) {
            if (Intent.ACTION_VIEW.equals(action)) {
                Uri uri = intent.getData();
                if (!ListenerUtil.mutListener.listen(2126)) {
                    if (uri != null) {
                        if (!ListenerUtil.mutListener.listen(2118)) {
                            Timber.v("uri: %s", uri);
                        }
                        if (!ListenerUtil.mutListener.listen(2125)) {
                            if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(2124)) {
                                        viewModel.extractPreferences(getContentFromURI(uri));
                                    }
                                } catch (IOException e) {
                                    String msg = String.format("Could not extract content from %s", uri);
                                    if (!ListenerUtil.mutListener.listen(2122)) {
                                        viewModel.setError(new Exception(msg, e));
                                    }
                                    if (!ListenerUtil.mutListener.listen(2123)) {
                                        Timber.e(e, msg);
                                    }
                                }
                            } else {
                                try {
                                    if (!ListenerUtil.mutListener.listen(2121)) {
                                        viewModel.extractPreferences(new URI(uri.toString()));
                                    }
                                } catch (URISyntaxException e) {
                                    String msg = "Error parsing intent URI";
                                    if (!ListenerUtil.mutListener.listen(2119)) {
                                        viewModel.setError(new Exception(msg, e));
                                    }
                                    if (!ListenerUtil.mutListener.listen(2120)) {
                                        Timber.e(e, msg);
                                    }
                                }
                            }
                        }
                    } else {
                        String msg = "No URI given for importing configuration";
                        if (!ListenerUtil.mutListener.listen(2116)) {
                            viewModel.setError(new Exception(msg));
                        }
                        if (!ListenerUtil.mutListener.listen(2117)) {
                            Timber.e(msg);
                        }
                    }
                }
            } else {
                Intent pickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                if (!ListenerUtil.mutListener.listen(2111)) {
                    pickerIntent.addCategory(Intent.CATEGORY_OPENABLE);
                }
                if (!ListenerUtil.mutListener.listen(2112)) {
                    pickerIntent.setType("*/*");
                }
                try {
                    if (!ListenerUtil.mutListener.listen(2114)) {
                        Timber.v("loading picker");
                    }
                    if (!ListenerUtil.mutListener.listen(2115)) {
                        startActivityForResult(Intent.createChooser(pickerIntent, "Select a file"), LoadActivity.REQUEST_CODE);
                    }
                } catch (android.content.ActivityNotFoundException ex) {
                    if (!ListenerUtil.mutListener.listen(2113)) {
                        Toast.makeText(this, "No file explorer app found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    // Return path from file picker
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        if (!ListenerUtil.mutListener.listen(2128)) {
            super.onActivityResult(requestCode, resultCode, resultIntent);
        }
        if (!ListenerUtil.mutListener.listen(2129)) {
            Timber.v("RequestCode: %s resultCode: %s", requestCode, resultCode);
        }
        if (!ListenerUtil.mutListener.listen(2140)) {
            if ((ListenerUtil.mutListener.listen(2134) ? (requestCode >= LoadActivity.REQUEST_CODE) : (ListenerUtil.mutListener.listen(2133) ? (requestCode <= LoadActivity.REQUEST_CODE) : (ListenerUtil.mutListener.listen(2132) ? (requestCode > LoadActivity.REQUEST_CODE) : (ListenerUtil.mutListener.listen(2131) ? (requestCode < LoadActivity.REQUEST_CODE) : (ListenerUtil.mutListener.listen(2130) ? (requestCode != LoadActivity.REQUEST_CODE) : (requestCode == LoadActivity.REQUEST_CODE))))))) {
                if (!ListenerUtil.mutListener.listen(2139)) {
                    if (resultCode == RESULT_OK) {
                        byte[] content = new byte[0];
                        try {
                            if (!ListenerUtil.mutListener.listen(2137)) {
                                content = getContentFromURI(resultIntent.getData());
                            }
                        } catch (IOException e) {
                            if (!ListenerUtil.mutListener.listen(2136)) {
                                Timber.e(e, "Could not extract content from %s", resultIntent);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(2138)) {
                            viewModel.extractPreferences(content);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2135)) {
                            finish();
                        }
                    }
                }
            }
        }
    }

    private byte[] getContentFromURI(Uri uri) throws IOException {
        InputStream stream = getContentResolver().openInputStream(uri);
        byte[] output = new byte[stream.available()];
        int bytesRead = stream.read(output);
        if (!ListenerUtil.mutListener.listen(2141)) {
            Timber.d("Read %d bytes from content URI", bytesRead);
        }
        return output;
    }

    @Override
    public void showFinishDialog() {
        if (!ListenerUtil.mutListener.listen(2142)) {
            (new AlertDialog.Builder(this).setTitle("Import successful").setMessage("It is recommended to restart the app to apply all imported values").setPositiveButton("Restart", (dialog, which) -> eventBus.post(new Events.RestartApp())).setNegativeButton("Cancel", (dialog, which) -> finish())).show();
        }
    }
}
