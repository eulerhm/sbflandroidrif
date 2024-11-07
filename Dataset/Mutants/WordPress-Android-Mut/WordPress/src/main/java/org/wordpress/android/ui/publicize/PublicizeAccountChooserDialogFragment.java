package org.wordpress.android.ui.publicize;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.models.PublicizeConnection;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.util.ToastUtils;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeAccountChooserDialogFragment extends DialogFragment implements PublicizeAccountChooserListAdapter.OnPublicizeAccountChooserListener {

    public static final String TAG = "publicize-account-chooser-dialog-fragment";

    private RecyclerView mNotConnectedRecyclerView;

    private ArrayList<PublicizeConnection> mNotConnectedAccounts;

    private ArrayList<PublicizeConnection> mConnectedAccounts;

    private String mConnectionName = "";

    private String mServiceId = "";

    private int mSelectedIndex = 0;

    private SiteModel mSite;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17220)) {
            retrieveCurrentSiteFromArgs();
        }
        if (!ListenerUtil.mutListener.listen(17221)) {
            configureConnectionName();
        }
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // noinspection InflateParams
        View view = inflater.inflate(R.layout.publicize_account_chooser_dialog, null);
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(17222)) {
            configureAlertDialog(view, builder);
        }
        if (!ListenerUtil.mutListener.listen(17223)) {
            configureRecyclerViews(view);
        }
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(17224)) {
            super.onDismiss(dialog);
        }
        Activity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(17227)) {
            if ((ListenerUtil.mutListener.listen(17225) ? (activity != null || activity instanceof DialogInterface.OnDismissListener) : (activity != null && activity instanceof DialogInterface.OnDismissListener))) {
                if (!ListenerUtil.mutListener.listen(17226)) {
                    ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
                }
            }
        }
    }

    private void configureRecyclerViews(View view) {
        PublicizeAccountChooserListAdapter notConnectedAdapter = new PublicizeAccountChooserListAdapter(getActivity(), mNotConnectedAccounts, this, false);
        if (!ListenerUtil.mutListener.listen(17228)) {
            notConnectedAdapter.setHasStableIds(true);
        }
        if (!ListenerUtil.mutListener.listen(17229)) {
            mNotConnectedRecyclerView = view.findViewById(R.id.not_connected_recyclerview);
        }
        if (!ListenerUtil.mutListener.listen(17230)) {
            mNotConnectedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        if (!ListenerUtil.mutListener.listen(17231)) {
            mNotConnectedRecyclerView.setAdapter(notConnectedAdapter);
        }
        if (!ListenerUtil.mutListener.listen(17234)) {
            if (mConnectedAccounts.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(17233)) {
                    hideConnectedView(view);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17232)) {
                    populateConnectedListView(view);
                }
            }
        }
    }

    private void hideConnectedView(View view) {
        LinearLayout connectedHeader = view.findViewById(R.id.connected_header);
        if (!ListenerUtil.mutListener.listen(17235)) {
            connectedHeader.setVisibility(View.GONE);
        }
    }

    private void populateConnectedListView(View view) {
        RecyclerView listViewConnected = view.findViewById(R.id.connected_recyclerview);
        PublicizeAccountChooserListAdapter connectedAdapter = new PublicizeAccountChooserListAdapter(getActivity(), mConnectedAccounts, null, true);
        if (!ListenerUtil.mutListener.listen(17236)) {
            listViewConnected.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        if (!ListenerUtil.mutListener.listen(17237)) {
            listViewConnected.setAdapter(connectedAdapter);
        }
    }

    private void configureAlertDialog(View view, AlertDialog.Builder builder) {
        if (!ListenerUtil.mutListener.listen(17238)) {
            builder.setView(view);
        }
        if (!ListenerUtil.mutListener.listen(17239)) {
            builder.setTitle(getString(R.string.connecting_social_network, mConnectionName));
        }
        if (!ListenerUtil.mutListener.listen(17240)) {
            builder.setMessage(getString(R.string.connection_chooser_message));
        }
        if (!ListenerUtil.mutListener.listen(17241)) {
            builder.setPositiveButton(R.string.share_btn_connect, (dialogInterface, i) -> {
                dialogInterface.dismiss();
                int keychainId = mNotConnectedAccounts.get(mSelectedIndex).connectionId;
                String service = mNotConnectedAccounts.get(mSelectedIndex).getService();
                String externalUserId = mNotConnectedAccounts.get(mSelectedIndex).getExternalId();
                EventBus.getDefault().post(new PublicizeEvents.ActionAccountChosen(mSite.getSiteId(), keychainId, service, externalUserId));
            });
        }
        if (!ListenerUtil.mutListener.listen(17242)) {
            builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                dialogInterface.cancel();
                ToastUtils.showToast(getActivity(), getActivity().getString(R.string.cannot_connect_account_error, mConnectionName));
            });
        }
    }

    private void retrieveCurrentSiteFromArgs() {
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(17246)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(17243)) {
                    mSite = (SiteModel) args.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(17244)) {
                    mServiceId = args.getString(PublicizeConstants.ARG_SERVICE_ID);
                }
                String jsonString = args.getString(PublicizeConstants.ARG_CONNECTION_ARRAY_JSON);
                if (!ListenerUtil.mutListener.listen(17245)) {
                    addConnectionsToLists(jsonString);
                }
            }
        }
    }

    private void addConnectionsToLists(String jsonString) {
        if (!ListenerUtil.mutListener.listen(17247)) {
            mNotConnectedAccounts = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(17248)) {
            mConnectedAccounts = new ArrayList<>();
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("connections");
            if (!ListenerUtil.mutListener.listen(17271)) {
                {
                    long _loopCounter284 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17270) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(17269) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(17268) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(17267) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(17266) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter284", ++_loopCounter284);
                        JSONObject currentConnectionJson = jsonArray.getJSONObject(i);
                        PublicizeConnection connection = PublicizeConnection.fromJson(currentConnectionJson);
                        if (!ListenerUtil.mutListener.listen(17265)) {
                            if (connection.getService().equals(mServiceId)) {
                                PublicizeService service = PublicizeTable.getService(mServiceId);
                                if (!ListenerUtil.mutListener.listen(17254)) {
                                    if ((ListenerUtil.mutListener.listen(17250) ? (service != null || !service.isExternalUsersOnly()) : (service != null && !service.isExternalUsersOnly()))) {
                                        if (!ListenerUtil.mutListener.listen(17253)) {
                                            if (connection.isInSite(mSite.getSiteId())) {
                                                if (!ListenerUtil.mutListener.listen(17252)) {
                                                    mConnectedAccounts.add(connection);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(17251)) {
                                                    mNotConnectedAccounts.add(connection);
                                                }
                                            }
                                        }
                                    }
                                }
                                JSONArray externalJsonArray = currentConnectionJson.getJSONArray("additional_external_users");
                                if (!ListenerUtil.mutListener.listen(17264)) {
                                    {
                                        long _loopCounter283 = 0;
                                        for (int j = 0; (ListenerUtil.mutListener.listen(17263) ? (j >= externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17262) ? (j <= externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17261) ? (j > externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17260) ? (j != externalJsonArray.length()) : (ListenerUtil.mutListener.listen(17259) ? (j == externalJsonArray.length()) : (j < externalJsonArray.length())))))); j++) {
                                            ListenerUtil.loopListener.listen("_loopCounter283", ++_loopCounter283);
                                            JSONObject currentExternalConnectionJson = externalJsonArray.getJSONObject(j);
                                            if (!ListenerUtil.mutListener.listen(17255)) {
                                                PublicizeConnection.updateConnectionfromExternalJson(connection, currentExternalConnectionJson);
                                            }
                                            if (!ListenerUtil.mutListener.listen(17258)) {
                                                if (connection.isInSite(mSite.getSiteId())) {
                                                    if (!ListenerUtil.mutListener.listen(17257)) {
                                                        mConnectedAccounts.add(connection);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(17256)) {
                                                        mNotConnectedAccounts.add(connection);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(17249)) {
                e.printStackTrace();
            }
        }
    }

    private void configureConnectionName() {
        if (!ListenerUtil.mutListener.listen(17272)) {
            if (mNotConnectedAccounts.isEmpty()) {
                return;
            }
        }
        PublicizeConnection connection = mNotConnectedAccounts.get(0);
        if (!ListenerUtil.mutListener.listen(17274)) {
            if (connection != null) {
                if (!ListenerUtil.mutListener.listen(17273)) {
                    mConnectionName = connection.getLabel();
                }
            }
        }
    }

    @Override
    public void onAccountSelected(int selectedIndex) {
        if (!ListenerUtil.mutListener.listen(17275)) {
            mSelectedIndex = selectedIndex;
        }
        if (!ListenerUtil.mutListener.listen(17276)) {
            mNotConnectedRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
