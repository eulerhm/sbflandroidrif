package fr.free.nrw.commons.bookmarks.items;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.upload.structure.depictions.DepictedItem;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Tab fragment to show list of bookmarked Wikidata Items
 */
public class BookmarkItemsFragment extends DaggerFragment {

    @BindView(R.id.status_message)
    TextView statusTextView;

    @BindView(R.id.loading_images_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.list_view)
    RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    RelativeLayout parentLayout;

    @Inject
    BookmarkItemsController controller;

    public static BookmarkItemsFragment newInstance() {
        return new BookmarkItemsFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_bookmarks_items, container, false);
        if (!ListenerUtil.mutListener.listen(5069)) {
            ButterKnife.bind(this, v);
        }
        return v;
    }

    @Override
    public void onViewCreated(@NotNull final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5070)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5071)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(5072)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(5073)) {
            initList(requireContext());
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(5074)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(5075)) {
            initList(requireContext());
        }
    }

    /**
     * Get list of DepictedItem and sets to the adapter
     * @param context context
     */
    private void initList(final Context context) {
        final List<DepictedItem> depictItems = controller.loadFavoritesItems();
        final BookmarkItemsAdapter adapter = new BookmarkItemsAdapter(depictItems, context);
        if (!ListenerUtil.mutListener.listen(5076)) {
            recyclerView.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(5077)) {
            progressBar.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(5081)) {
            if (depictItems.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(5079)) {
                    statusTextView.setText(R.string.bookmark_empty);
                }
                if (!ListenerUtil.mutListener.listen(5080)) {
                    statusTextView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5078)) {
                    statusTextView.setVisibility(View.GONE);
                }
            }
        }
    }
}
