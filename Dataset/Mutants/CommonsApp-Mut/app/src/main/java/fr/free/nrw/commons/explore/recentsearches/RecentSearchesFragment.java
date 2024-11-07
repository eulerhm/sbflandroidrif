package fr.free.nrw.commons.explore.recentsearches;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.explore.SearchActivity;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Displays the recent searches screen.
 */
public class RecentSearchesFragment extends CommonsDaggerSupportFragment {

    @Inject
    RecentSearchesDao recentSearchesDao;

    @BindView(R.id.recent_searches_list)
    ListView recentSearchesList;

    List<String> recentSearches;

    ArrayAdapter adapter;

    @BindView(R.id.recent_searches_delete_button)
    ImageView recent_searches_delete_button;

    @BindView(R.id.recent_searches_text_view)
    TextView recent_searches_text_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_history, container, false);
        if (!ListenerUtil.mutListener.listen(4435)) {
            ButterKnife.bind(this, rootView);
        }
        if (!ListenerUtil.mutListener.listen(4436)) {
            recentSearches = recentSearchesDao.recentSearches(10);
        }
        if (!ListenerUtil.mutListener.listen(4439)) {
            if (recentSearches.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(4437)) {
                    recent_searches_delete_button.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(4438)) {
                    recent_searches_text_view.setText(R.string.no_recent_searches);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4440)) {
            recent_searches_delete_button.setOnClickListener(v -> {
                showDeleteRecentAlertDialog(requireContext());
            });
        }
        if (!ListenerUtil.mutListener.listen(4441)) {
            adapter = new ArrayAdapter<>(requireContext(), R.layout.item_recent_searches, recentSearches);
        }
        if (!ListenerUtil.mutListener.listen(4442)) {
            recentSearchesList.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(4443)) {
            recentSearchesList.setOnItemClickListener((parent, view, position, id) -> ((SearchActivity) getContext()).updateText(recentSearches.get(position)));
        }
        if (!ListenerUtil.mutListener.listen(4444)) {
            recentSearchesList.setOnItemLongClickListener((parent, view, position, id) -> {
                showDeleteAlertDialog(requireContext(), position);
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(4445)) {
            updateRecentSearches();
        }
        return rootView;
    }

    private void showDeleteRecentAlertDialog(@NonNull final Context context) {
        if (!ListenerUtil.mutListener.listen(4446)) {
            new AlertDialog.Builder(context).setMessage(getString(R.string.delete_recent_searches_dialog)).setPositiveButton(android.R.string.yes, (dialog, which) -> setDeleteRecentPositiveButton(context, dialog)).setNegativeButton(android.R.string.no, null).create().show();
        }
    }

    private void setDeleteRecentPositiveButton(@NonNull final Context context, final DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(4447)) {
            recentSearchesDao.deleteAll();
        }
        if (!ListenerUtil.mutListener.listen(4448)) {
            recent_searches_delete_button.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4449)) {
            recent_searches_text_view.setText(R.string.no_recent_searches);
        }
        if (!ListenerUtil.mutListener.listen(4450)) {
            Toast.makeText(getContext(), getString(R.string.search_history_deleted), Toast.LENGTH_SHORT).show();
        }
        if (!ListenerUtil.mutListener.listen(4451)) {
            recentSearches = recentSearchesDao.recentSearches(10);
        }
        if (!ListenerUtil.mutListener.listen(4452)) {
            adapter = new ArrayAdapter<>(context, R.layout.item_recent_searches, recentSearches);
        }
        if (!ListenerUtil.mutListener.listen(4453)) {
            recentSearchesList.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(4454)) {
            adapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(4455)) {
            dialog.dismiss();
        }
    }

    private void showDeleteAlertDialog(@NonNull final Context context, final int position) {
        if (!ListenerUtil.mutListener.listen(4456)) {
            new AlertDialog.Builder(context).setMessage(R.string.delete_search_dialog).setPositiveButton(getString(R.string.delete).toUpperCase(), ((dialog, which) -> setDeletePositiveButton(context, dialog, position))).setNegativeButton(android.R.string.cancel, null).create().show();
        }
    }

    private void setDeletePositiveButton(@NonNull final Context context, final DialogInterface dialog, final int position) {
        if (!ListenerUtil.mutListener.listen(4457)) {
            recentSearchesDao.delete(recentSearchesDao.find(recentSearches.get(position)));
        }
        if (!ListenerUtil.mutListener.listen(4458)) {
            recentSearches = recentSearchesDao.recentSearches(10);
        }
        if (!ListenerUtil.mutListener.listen(4459)) {
            adapter = new ArrayAdapter<>(context, R.layout.item_recent_searches, recentSearches);
        }
        if (!ListenerUtil.mutListener.listen(4460)) {
            recentSearchesList.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(4461)) {
            adapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(4462)) {
            dialog.dismiss();
        }
    }

    /**
     * This method is called on back press of activity so we are updating the list from database to
     * refresh the recent searches list.
     */
    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4463)) {
            updateRecentSearches();
        }
        if (!ListenerUtil.mutListener.listen(4464)) {
            super.onResume();
        }
    }

    /**
     * This method is called when search query is null to update Recent Searches
     */
    public void updateRecentSearches() {
        if (!ListenerUtil.mutListener.listen(4465)) {
            recentSearches = recentSearchesDao.recentSearches(10);
        }
        if (!ListenerUtil.mutListener.listen(4466)) {
            adapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(4469)) {
            if (!recentSearches.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(4467)) {
                    recent_searches_delete_button.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(4468)) {
                    recent_searches_text_view.setText(R.string.search_recent_header);
                }
            }
        }
    }
}
