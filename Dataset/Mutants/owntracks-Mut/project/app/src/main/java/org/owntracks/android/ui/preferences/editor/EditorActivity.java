package org.owntracks.android.ui.preferences.editor;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.R;
import org.owntracks.android.data.repos.WaypointsRepo;
import org.owntracks.android.databinding.UiPreferencesEditorBinding;
import org.owntracks.android.model.messages.MessageConfiguration;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.Parser;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.navigator.Navigator;
import org.owntracks.android.ui.preferences.load.LoadActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EditorActivity extends BaseActivity<UiPreferencesEditorBinding, EditorMvvm.ViewModel<EditorMvvm.View>> implements EditorMvvm.View {

    @Inject
    Preferences preferences;

    @Inject
    WaypointsRepo waypointsRepo;

    @Inject
    Parser parser;

    @Inject
    EventBus eventBus;

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2044)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2045)) {
            disablesAnimation();
        }
        if (!ListenerUtil.mutListener.listen(2046)) {
            bindAndAttachContentView(R.layout.ui_preferences_editor, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2047)) {
            setHasEventBus(false);
        }
        if (!ListenerUtil.mutListener.listen(2048)) {
            setSupportToolbar(binding.toolbar, true, true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(2049)) {
            inflater.inflate(R.menu.activity_configuration, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.exportConfigurationFile:
                if (!ListenerUtil.mutListener.listen(2050)) {
                    new ExportTask(this).execute();
                }
                return true;
            case R.id.importConfigurationFile:
                if (!ListenerUtil.mutListener.listen(2051)) {
                    showImportConfigurationFilePickerView();
                }
                return true;
            case R.id.importConfigurationSingleValue:
                if (!ListenerUtil.mutListener.listen(2052)) {
                    showEditorView();
                }
                return true;
            case R.id.restart:
                if (!ListenerUtil.mutListener.listen(2053)) {
                    eventBus.post(new Events.RestartApp());
                }
            default:
                return false;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(2054)) {
            onBackPressed();
        }
        return true;
    }

    private void showImportConfigurationFilePickerView() {
        Bundle b = new Bundle();
        if (!ListenerUtil.mutListener.listen(2055)) {
            b.putBoolean(LoadActivity.FLAG_IN_APP, true);
        }
        if (!ListenerUtil.mutListener.listen(2056)) {
            navigator.startActivity(LoadActivity.class, b);
        }
    }

    private void showEditorView() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.ui_preferences_editor_dialog, null);
        // Set autocomplete items
        MaterialAutoCompleteTextView inputKeyView = layout.findViewById(R.id.inputKey);
        if (!ListenerUtil.mutListener.listen(2057)) {
            inputKeyView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, preferences.getImportKeys()));
        }
        if (!ListenerUtil.mutListener.listen(2058)) {
            builder.setTitle(R.string.preferencesEditor).setPositiveButton(R.string.accept, (dialog, which) -> {
                final MaterialEditText inputValue = layout.findViewById(R.id.inputValue);
                String key = inputKeyView.getText().toString();
                String value = inputValue.getText().toString();
                try {
                    preferences.importKeyValue(key, value);
                    viewModel.onPreferencesValueForKeySetSuccessful();
                    dialog.dismiss();
                } catch (IllegalAccessException e) {
                    Timber.w(e);
                    displayPreferencesValueForKeySetFailedKey();
                } catch (IllegalArgumentException e) {
                    Timber.w(e);
                    displayPreferencesValueForKeySetFailedValue();
                }
            }).setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).setView(layout);
        }
        if (!ListenerUtil.mutListener.listen(2059)) {
            builder.show();
        }
    }

    @Override
    public boolean exportConfigurationToFile(String exportStr) {
        File cDir = getBaseContext().getCacheDir();
        File tempFile = new File(cDir.getPath() + "/config.otrc");
        try {
            FileWriter writer = new FileWriter(tempFile);
            if (!ListenerUtil.mutListener.listen(2061)) {
                writer.write(exportStr);
            }
            if (!ListenerUtil.mutListener.listen(2062)) {
                writer.close();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(2060)) {
                displayExportToFileFailed();
            }
            return false;
        }
        Uri configUri = FileProvider.getUriForFile(this, "org.owntracks.android.fileprovider", tempFile);
        Intent sendIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(2063)) {
            sendIntent.setAction(Intent.ACTION_SEND);
        }
        if (!ListenerUtil.mutListener.listen(2064)) {
            sendIntent.putExtra(Intent.EXTRA_STREAM, configUri);
        }
        if (!ListenerUtil.mutListener.listen(2065)) {
            sendIntent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(2066)) {
            startActivity(Intent.createChooser(sendIntent, getString(R.string.exportConfiguration)));
        }
        return true;
    }

    @Override
    public void displayLoadFailed() {
        if (!ListenerUtil.mutListener.listen(2067)) {
            Toast.makeText(this, R.string.preferencesLoadFailed, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayExportToFileFailed() {
        if (!ListenerUtil.mutListener.listen(2068)) {
            Toast.makeText(this, R.string.preferencesExportFailed, Toast.LENGTH_SHORT).show();
        }
    }

    public void displayExportToFileSuccessful() {
        if (!ListenerUtil.mutListener.listen(2069)) {
            Toast.makeText(this, R.string.preferencesExportSuccess, Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPreferencesValueForKeySetFailedKey() {
        if (!ListenerUtil.mutListener.listen(2070)) {
            Toast.makeText(this, R.string.preferencesEditorKeyError, Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPreferencesValueForKeySetFailedValue() {
        if (!ListenerUtil.mutListener.listen(2071)) {
            Toast.makeText(this, R.string.preferencesEditorValueError, Toast.LENGTH_SHORT).show();
        }
    }

    private String getExportString() throws IOException {
        MessageConfiguration message = preferences.exportToMessage();
        if (!ListenerUtil.mutListener.listen(2072)) {
            message.setWaypoints(waypointsRepo.exportToMessage());
        }
        return parser.toJsonPlain(message);
    }

    static class ExportTask extends AsyncTask<Void, Void, Boolean> {

        WeakReference<EditorActivity> ref;

        ExportTask(EditorActivity activity) {
            if (!ListenerUtil.mutListener.listen(2073)) {
                ref = new WeakReference<>(activity);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            String exportStr;
            try {
                exportStr = ref.get().getExportString();
            } catch (IOException e) {
                return false;
            }
            if (!ListenerUtil.mutListener.listen(2075)) {
                if (ref.get() != null)
                    if (!ListenerUtil.mutListener.listen(2074)) {
                        ref.get().exportConfigurationToFile(exportStr);
                    }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!ListenerUtil.mutListener.listen(2077)) {
                if (ref.get() != null) {
                    if (!ListenerUtil.mutListener.listen(2076)) {
                        ref.get().displayExportToFileSuccessful();
                    }
                }
            }
        }
    }
}
