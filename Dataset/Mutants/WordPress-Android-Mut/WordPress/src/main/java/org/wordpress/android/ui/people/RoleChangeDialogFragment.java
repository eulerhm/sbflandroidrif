package org.wordpress.android.ui.people;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.RoleModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RoleChangeDialogFragment extends DialogFragment {

    private static final String PERSON_ID_TAG = "person_id";

    private static final String ROLE_TAG = "role";

    @Inject
    SiteStore mSiteStore;

    private RoleListAdapter mRoleListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10205)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10206)) {
            ((WordPress) getActivity().getApplicationContext()).component().inject(this);
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(10207)) {
            super.onSaveInstanceState(outState);
        }
        String role = mRoleListAdapter.getSelectedRole();
        if (!ListenerUtil.mutListener.listen(10208)) {
            outState.putSerializable(ROLE_TAG, role);
        }
    }

    public static RoleChangeDialogFragment newInstance(long personID, SiteModel site, String role) {
        RoleChangeDialogFragment roleChangeDialogFragment = new RoleChangeDialogFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(10209)) {
            args.putLong(PERSON_ID_TAG, personID);
        }
        if (!ListenerUtil.mutListener.listen(10210)) {
            args.putString(ROLE_TAG, role);
        }
        if (!ListenerUtil.mutListener.listen(10211)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(10212)) {
            roleChangeDialogFragment.setArguments(args);
        }
        return roleChangeDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final SiteModel site = (SiteModel) getArguments().getSerializable(WordPress.SITE);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(10213)) {
            builder.setTitle(R.string.role);
        }
        if (!ListenerUtil.mutListener.listen(10214)) {
            builder.setNegativeButton(R.string.cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(10215)) {
            builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                String role = mRoleListAdapter.getSelectedRole();
                Bundle args = getArguments();
                if (args != null) {
                    long personID = args.getLong(PERSON_ID_TAG);
                    if (site != null) {
                        EventBus.getDefault().post(new RoleChangeEvent(personID, site.getId(), role));
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(10218)) {
            if ((ListenerUtil.mutListener.listen(10216) ? (mRoleListAdapter == null || site != null) : (mRoleListAdapter == null && site != null))) {
                List<RoleModel> roleList = mSiteStore.getUserRoles(site);
                RoleModel[] userRoles = roleList.toArray(new RoleModel[roleList.size()]);
                if (!ListenerUtil.mutListener.listen(10217)) {
                    mRoleListAdapter = new RoleListAdapter(getActivity(), R.layout.role_list_row, userRoles);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10222)) {
            if (savedInstanceState != null) {
                String savedRole = savedInstanceState.getString(ROLE_TAG);
                if (!ListenerUtil.mutListener.listen(10221)) {
                    mRoleListAdapter.setSelectedRole(savedRole);
                }
            } else {
                Bundle args = getArguments();
                if (!ListenerUtil.mutListener.listen(10220)) {
                    if (args != null) {
                        String role = args.getString(ROLE_TAG);
                        if (!ListenerUtil.mutListener.listen(10219)) {
                            mRoleListAdapter.setSelectedRole(role);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10223)) {
            builder.setAdapter(mRoleListAdapter, null);
        }
        return builder.create();
    }

    private class RoleListAdapter extends ArrayAdapter<RoleModel> {

        private String mSelectedRole;

        RoleListAdapter(Context context, int resource, RoleModel[] userRoles) {
            super(context, resource, userRoles);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            if (!ListenerUtil.mutListener.listen(10225)) {
                if (convertView == null) {
                    if (!ListenerUtil.mutListener.listen(10224)) {
                        convertView = View.inflate(getContext(), R.layout.role_list_row, null);
                    }
                }
            }
            TextView mainText = convertView.findViewById(R.id.role_label);
            final RadioButton radioButton = convertView.findViewById(R.id.radio);
            if (!ListenerUtil.mutListener.listen(10226)) {
                radioButton.setOnClickListener(v -> changeSelection(position));
            }
            if (!ListenerUtil.mutListener.listen(10227)) {
                convertView.setOnClickListener(v -> changeSelection(position));
            }
            RoleModel role = getItem(position);
            if (!ListenerUtil.mutListener.listen(10230)) {
                if (role != null) {
                    if (!ListenerUtil.mutListener.listen(10228)) {
                        radioButton.setChecked(role.getName().equals(mSelectedRole));
                    }
                    if (!ListenerUtil.mutListener.listen(10229)) {
                        mainText.setText(role.getDisplayName());
                    }
                }
            }
            return convertView;
        }

        private void changeSelection(int position) {
            RoleModel roleModel = getItem(position);
            if (!ListenerUtil.mutListener.listen(10233)) {
                if (roleModel != null) {
                    if (!ListenerUtil.mutListener.listen(10231)) {
                        mSelectedRole = roleModel.getName();
                    }
                    if (!ListenerUtil.mutListener.listen(10232)) {
                        notifyDataSetChanged();
                    }
                }
            }
        }

        String getSelectedRole() {
            return mSelectedRole;
        }

        void setSelectedRole(String role) {
            if (!ListenerUtil.mutListener.listen(10234)) {
                mSelectedRole = role;
            }
        }
    }

    static class RoleChangeEvent {

        private final long mPersonID;

        private final int mLocalTableBlogId;

        private final String mNewRole;

        RoleChangeEvent(long personID, int localTableBlogId, String newRole) {
            mPersonID = personID;
            mLocalTableBlogId = localTableBlogId;
            mNewRole = newRole;
        }

        long getPersonID() {
            return mPersonID;
        }

        int getLocalTableBlogId() {
            return mLocalTableBlogId;
        }

        String getNewRole() {
            return mNewRole;
        }
    }
}
