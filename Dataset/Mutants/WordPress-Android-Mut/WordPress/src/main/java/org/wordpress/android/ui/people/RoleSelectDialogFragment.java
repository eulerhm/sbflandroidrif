package org.wordpress.android.ui.people;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.RoleModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.RoleUtils;
import org.wordpress.android.viewmodel.ContextProvider;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RoleSelectDialogFragment extends DialogFragment {

    @Inject
    SiteStore mSiteStore;

    @Inject
    ContextProvider mContextProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10235)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10236)) {
            ((WordPress) getActivity().getApplicationContext()).component().inject(this);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        SiteModel site = (SiteModel) getArguments().getSerializable(WordPress.SITE);
        final List<RoleModel> inviteRoles = RoleUtils.getInviteRoles(mSiteStore, site, mContextProvider.getContext());
        final String[] stringRoles = new String[inviteRoles.size()];
        if (!ListenerUtil.mutListener.listen(10243)) {
            {
                long _loopCounter194 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(10242) ? (i >= inviteRoles.size()) : (ListenerUtil.mutListener.listen(10241) ? (i <= inviteRoles.size()) : (ListenerUtil.mutListener.listen(10240) ? (i > inviteRoles.size()) : (ListenerUtil.mutListener.listen(10239) ? (i != inviteRoles.size()) : (ListenerUtil.mutListener.listen(10238) ? (i == inviteRoles.size()) : (i < inviteRoles.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter194", ++_loopCounter194);
                    if (!ListenerUtil.mutListener.listen(10237)) {
                        stringRoles[i] = inviteRoles.get(i).getDisplayName();
                    }
                }
            }
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(10244)) {
            builder.setTitle(R.string.role);
        }
        if (!ListenerUtil.mutListener.listen(10245)) {
            builder.setItems(stringRoles, (dialog, which) -> {
                if (!isAdded()) {
                    return;
                }
                if (getTargetFragment() instanceof OnRoleSelectListener) {
                    ((OnRoleSelectListener) getTargetFragment()).onRoleSelected(inviteRoles.get(which));
                } else if (getActivity() instanceof OnRoleSelectListener) {
                    ((OnRoleSelectListener) getActivity()).onRoleSelected(inviteRoles.get(which));
                }
            });
        }
        return builder.create();
    }

    public static <T extends Fragment & OnRoleSelectListener> void show(T parentFragment, int requestCode, @NonNull SiteModel site) {
        RoleSelectDialogFragment roleChangeDialogFragment = new RoleSelectDialogFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(10246)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(10247)) {
            roleChangeDialogFragment.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(10248)) {
            roleChangeDialogFragment.setTargetFragment(parentFragment, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(10249)) {
            roleChangeDialogFragment.show(parentFragment.getFragmentManager(), null);
        }
    }

    public static <T extends AppCompatActivity & OnRoleSelectListener> void show(T parentActivity) {
        RoleSelectDialogFragment roleChangeDialogFragment = new RoleSelectDialogFragment();
        if (!ListenerUtil.mutListener.listen(10250)) {
            roleChangeDialogFragment.show(parentActivity.getSupportFragmentManager(), null);
        }
    }

    // Container Activity must implement this interface
    interface OnRoleSelectListener {

        void onRoleSelected(RoleModel newRole);
    }
}
