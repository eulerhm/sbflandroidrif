package org.owntracks.android.ui.preferences.connection;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiPreferencesConnectionBinding;
import org.owntracks.android.databinding.UiPreferencesConnectionHostHttpBinding;
import org.owntracks.android.databinding.UiPreferencesConnectionHostMqttBinding;
import org.owntracks.android.databinding.UiPreferencesConnectionIdentificationBinding;
import org.owntracks.android.databinding.UiPreferencesConnectionModeBinding;
import org.owntracks.android.databinding.UiPreferencesConnectionParametersBinding;
import org.owntracks.android.databinding.UiPreferencesConnectionSecurityBinding;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.support.RunThingsOnOtherThreads;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.preferences.connection.dialog.BaseDialogViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel;
import org.owntracks.android.ui.status.StatusActivity;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectionActivity extends BaseActivity<UiPreferencesConnectionBinding, ConnectionMvvm.ViewModel<ConnectionMvvm.View>> implements ConnectionMvvm.View {

    private BaseDialogViewModel activeDialogViewModel;

    @Inject
    RunThingsOnOtherThreads runThingsOnOtherThreads;

    @Inject
    MessageProcessor messageProcessor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1983)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1984)) {
            disablesAnimation();
        }
        if (!ListenerUtil.mutListener.listen(1985)) {
            bindAndAttachContentView(R.layout.ui_preferences_connection, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1986)) {
            setSupportToolbar(binding.toolbar, true, true);
        }
        if (!ListenerUtil.mutListener.listen(1987)) {
            setHasEventBus(true);
        }
    }

    @Override
    public void showModeDialog() {
        UiPreferencesConnectionModeBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.ui_preferences_connection_mode, null, false);
        if (!ListenerUtil.mutListener.listen(1988)) {
            dialogBinding.setVm(viewModel.getModeDialogViewModel());
        }
        if (!ListenerUtil.mutListener.listen(1989)) {
            activeDialogViewModel = dialogBinding.getVm();
        }
        if (!ListenerUtil.mutListener.listen(1990)) {
            new AlertDialog.Builder(this).setView(dialogBinding.getRoot()).setTitle(R.string.mode_heading).setPositiveButton(R.string.accept, dialogBinding.getVm()).setNegativeButton(R.string.cancel, dialogBinding.getVm()).show();
        }
    }

    @Override
    public void showHostDialog() {
        if (!ListenerUtil.mutListener.listen(1997)) {
            if (viewModel.getModeId() == MessageProcessorEndpointHttp.MODE_ID) {
                UiPreferencesConnectionHostHttpBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.ui_preferences_connection_host_http, null, false);
                if (!ListenerUtil.mutListener.listen(1994)) {
                    dialogBinding.setVm(viewModel.getHostDialogViewModelHttp());
                }
                if (!ListenerUtil.mutListener.listen(1995)) {
                    activeDialogViewModel = dialogBinding.getVm();
                }
                if (!ListenerUtil.mutListener.listen(1996)) {
                    new AlertDialog.Builder(this).setView(dialogBinding.getRoot()).setTitle(R.string.preferencesHost).setPositiveButton(R.string.accept, dialogBinding.getVm()).setNegativeButton(R.string.cancel, dialogBinding.getVm()).show();
                }
            } else {
                UiPreferencesConnectionHostMqttBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.ui_preferences_connection_host_mqtt, null, false);
                if (!ListenerUtil.mutListener.listen(1991)) {
                    dialogBinding.setVm(viewModel.getHostDialogViewModelMqtt());
                }
                if (!ListenerUtil.mutListener.listen(1992)) {
                    activeDialogViewModel = dialogBinding.getVm();
                }
                if (!ListenerUtil.mutListener.listen(1993)) {
                    new AlertDialog.Builder(this).setView(dialogBinding.getRoot()).setTitle(R.string.preferencesHost).setPositiveButton(R.string.accept, dialogBinding.getVm()).setNegativeButton(R.string.cancel, dialogBinding.getVm()).show();
                }
            }
        }
    }

    @Override
    public void showIdentificationDialog() {
        UiPreferencesConnectionIdentificationBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.ui_preferences_connection_identification, null, false);
        if (!ListenerUtil.mutListener.listen(1998)) {
            dialogBinding.setVm(viewModel.getIdentificationDialogViewModel());
        }
        if (!ListenerUtil.mutListener.listen(1999)) {
            activeDialogViewModel = dialogBinding.getVm();
        }
        if (!ListenerUtil.mutListener.listen(2000)) {
            new AlertDialog.Builder(this).setView(dialogBinding.getRoot()).setTitle(R.string.preferencesIdentification).setPositiveButton(R.string.accept, dialogBinding.getVm()).setNegativeButton(R.string.cancel, dialogBinding.getVm()).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(2001)) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void showSecurityDialog() {
        UiPreferencesConnectionSecurityBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.ui_preferences_connection_security, null, false);
        if (!ListenerUtil.mutListener.listen(2002)) {
            dialogBinding.setVm(viewModel.getConnectionSecurityViewModel());
        }
        if (!ListenerUtil.mutListener.listen(2003)) {
            activeDialogViewModel = dialogBinding.getVm();
        }
        if (!ListenerUtil.mutListener.listen(2004)) {
            new AlertDialog.Builder(this).setView(dialogBinding.getRoot()).setTitle(R.string.preferencesSecurity).setPositiveButton(R.string.accept, dialogBinding.getVm()).setNegativeButton(R.string.cancel, dialogBinding.getVm()).show();
        }
    }

    ConnectionParametersViewModel connectionParametersViewModel;

    @Override
    public void showParametersDialog() {
        UiPreferencesConnectionParametersBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.ui_preferences_connection_parameters, null, false);
        if (!ListenerUtil.mutListener.listen(2005)) {
            connectionParametersViewModel = viewModel.getConnectionParametersViewModel();
        }
        if (!ListenerUtil.mutListener.listen(2006)) {
            dialogBinding.setVm(connectionParametersViewModel);
        }
        if (!ListenerUtil.mutListener.listen(2007)) {
            activeDialogViewModel = dialogBinding.getVm();
        }
        AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogBinding.getRoot()).setTitle(R.string.preferencesParameters).setPositiveButton(R.string.accept, dialogBinding.getVm()).setNegativeButton(R.string.cancel, dialogBinding.getVm()).create();
        MaterialEditText keepAliveEditText = dialogBinding.getRoot().findViewById(R.id.keepalive);
        if (!ListenerUtil.mutListener.listen(2016)) {
            keepAliveEditText.addValidator(new METValidator(getString(R.string.preferencesKeepaliveValidationError, preferences.isExperimentalFeatureEnabled(Preferences.EXPERIMENTAL_FEATURE_ALLOW_SMALL_KEEPALIVE) ? 1 : preferences.getMinimumKeepalive())) {

                @Override
                public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                    try {
                        int intValue = Integer.parseInt(text.toString());
                        return (ListenerUtil.mutListener.listen(2015) ? ((ListenerUtil.mutListener.listen(2008) ? (isEmpty && preferences.keepAliveInRange(intValue)) : (isEmpty || preferences.keepAliveInRange(intValue))) && ((ListenerUtil.mutListener.listen(2014) ? (preferences.isExperimentalFeatureEnabled(Preferences.EXPERIMENTAL_FEATURE_ALLOW_SMALL_KEEPALIVE) || (ListenerUtil.mutListener.listen(2013) ? (intValue <= 1) : (ListenerUtil.mutListener.listen(2012) ? (intValue > 1) : (ListenerUtil.mutListener.listen(2011) ? (intValue < 1) : (ListenerUtil.mutListener.listen(2010) ? (intValue != 1) : (ListenerUtil.mutListener.listen(2009) ? (intValue == 1) : (intValue >= 1))))))) : (preferences.isExperimentalFeatureEnabled(Preferences.EXPERIMENTAL_FEATURE_ALLOW_SMALL_KEEPALIVE) && (ListenerUtil.mutListener.listen(2013) ? (intValue <= 1) : (ListenerUtil.mutListener.listen(2012) ? (intValue > 1) : (ListenerUtil.mutListener.listen(2011) ? (intValue < 1) : (ListenerUtil.mutListener.listen(2010) ? (intValue != 1) : (ListenerUtil.mutListener.listen(2009) ? (intValue == 1) : (intValue >= 1)))))))))) : ((ListenerUtil.mutListener.listen(2008) ? (isEmpty && preferences.keepAliveInRange(intValue)) : (isEmpty || preferences.keepAliveInRange(intValue))) || ((ListenerUtil.mutListener.listen(2014) ? (preferences.isExperimentalFeatureEnabled(Preferences.EXPERIMENTAL_FEATURE_ALLOW_SMALL_KEEPALIVE) || (ListenerUtil.mutListener.listen(2013) ? (intValue <= 1) : (ListenerUtil.mutListener.listen(2012) ? (intValue > 1) : (ListenerUtil.mutListener.listen(2011) ? (intValue < 1) : (ListenerUtil.mutListener.listen(2010) ? (intValue != 1) : (ListenerUtil.mutListener.listen(2009) ? (intValue == 1) : (intValue >= 1))))))) : (preferences.isExperimentalFeatureEnabled(Preferences.EXPERIMENTAL_FEATURE_ALLOW_SMALL_KEEPALIVE) && (ListenerUtil.mutListener.listen(2013) ? (intValue <= 1) : (ListenerUtil.mutListener.listen(2012) ? (intValue > 1) : (ListenerUtil.mutListener.listen(2011) ? (intValue < 1) : (ListenerUtil.mutListener.listen(2010) ? (intValue != 1) : (ListenerUtil.mutListener.listen(2009) ? (intValue == 1) : (intValue >= 1)))))))))));
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2017)) {
            keepAliveEditText.setAutoValidate(true);
        }
        if (!ListenerUtil.mutListener.listen(2018)) {
            dialog.show();
        }
        if (!ListenerUtil.mutListener.listen(2019)) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                if (keepAliveEditText.validate()) {
                    connectionParametersViewModel.save();
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(2020)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(2021)) {
            activeDialogViewModel.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void recreateOptionsMenu() {
        if (!ListenerUtil.mutListener.listen(2022)) {
            invalidateOptionsMenu();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2024)) {
            if (menu != null) {
                if (!ListenerUtil.mutListener.listen(2023)) {
                    menu.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2027)) {
            if (viewModel.getModeId() == MessageProcessorEndpointHttp.MODE_ID) {
                if (!ListenerUtil.mutListener.listen(2026)) {
                    getMenuInflater().inflate(R.menu.preferences_connection_http, menu);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2025)) {
                    getMenuInflater().inflate(R.menu.preferences_connection_mqtt, menu);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.connect:
                if (!ListenerUtil.mutListener.listen(2030)) {
                    if (messageProcessor.isEndpointConfigurationComplete()) {
                        if (!ListenerUtil.mutListener.listen(2029)) {
                            messageProcessor.reconnect();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2028)) {
                            Toast.makeText(this, R.string.ERROR_CONFIGURATION, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return true;
            case R.id.status:
                if (!ListenerUtil.mutListener.listen(2031)) {
                    startActivity(new Intent(this, StatusActivity.class));
                }
            default:
                return false;
        }
    }
}
