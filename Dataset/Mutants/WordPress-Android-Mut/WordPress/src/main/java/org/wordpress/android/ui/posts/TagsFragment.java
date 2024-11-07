package org.wordpress.android.ui.posts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.apache.commons.text.StringEscapeUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged;
import org.wordpress.android.util.ActivityUtils;
import javax.inject.Inject;
import static org.wordpress.android.ui.posts.PostSettingsTagsActivity.KEY_TAGS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class TagsFragment extends Fragment implements TextWatcher, View.OnKeyListener, TagSelectedListener {

    private SiteModel mSite;

    private EditText mTagsEditText;

    private TagsRecyclerViewAdapter mAdapter;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    TaxonomyStore mTaxonomyStore;

    private String mTags;

    TagsSelectedListener mTagsSelectedListener;

    public TagsFragment() {
    }

    @LayoutRes
    protected abstract int getContentLayout();

    protected abstract String getTagsFromEditPostRepositoryOrArguments();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13196)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13200)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(13197)) {
                    mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(13198)) {
                    mTags = getArguments().getString(KEY_TAGS);
                }
                if (!ListenerUtil.mutListener.listen(13199)) {
                    if (mSite == null) {
                        throw new IllegalStateException("Required argument mSite is missing.");
                    }
                }
            }
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(13201)) {
            super.onDetach();
        }
        if (!ListenerUtil.mutListener.listen(13202)) {
            mTagsSelectedListener = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(getContentLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13203)) {
            super.onViewCreated(view, savedInstanceState);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tags_suggestion_list);
        if (!ListenerUtil.mutListener.listen(13204)) {
            recyclerView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(13205)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        }
        if (!ListenerUtil.mutListener.listen(13206)) {
            mAdapter = new TagsRecyclerViewAdapter(requireActivity(), this);
        }
        if (!ListenerUtil.mutListener.listen(13207)) {
            mAdapter.setAllTags(mTaxonomyStore.getTagsForSite(mSite));
        }
        if (!ListenerUtil.mutListener.listen(13208)) {
            recyclerView.setAdapter(mAdapter);
        }
        if (!ListenerUtil.mutListener.listen(13209)) {
            mTagsEditText = (EditText) view.findViewById(R.id.tags_edit_text);
        }
        if (!ListenerUtil.mutListener.listen(13210)) {
            mTagsEditText.setOnKeyListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13211)) {
            mTagsEditText.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(13212)) {
            ActivityUtils.showKeyboard(mTagsEditText);
        }
        if (!ListenerUtil.mutListener.listen(13213)) {
            mTagsEditText.post(() -> mTagsEditText.addTextChangedListener(TagsFragment.this));
        }
        if (!ListenerUtil.mutListener.listen(13214)) {
            loadTags();
        }
        if (!ListenerUtil.mutListener.listen(13219)) {
            if (!TextUtils.isEmpty(mTags)) {
                if (!ListenerUtil.mutListener.listen(13215)) {
                    // add a , at the end so the user can start typing a new tag
                    mTags += ",";
                }
                if (!ListenerUtil.mutListener.listen(13216)) {
                    mTags = StringEscapeUtils.unescapeHtml4(mTags);
                }
                if (!ListenerUtil.mutListener.listen(13217)) {
                    mTagsEditText.setText(mTags);
                }
                if (!ListenerUtil.mutListener.listen(13218)) {
                    mTagsEditText.setSelection(mTagsEditText.length());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13220)) {
            filterListForCurrentText();
        }
    }

    private void loadTags() {
        if (!ListenerUtil.mutListener.listen(13221)) {
            mTags = getTagsFromEditPostRepositoryOrArguments();
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(13222)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(13223)) {
            mDispatcher.register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(13224)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(13225)) {
            super.onStop();
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (!ListenerUtil.mutListener.listen(13231)) {
            if ((ListenerUtil.mutListener.listen(13226) ? ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) || (keyCode == KeyEvent.KEYCODE_ENTER)) : ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)))) {
                // Since we don't allow new lines, we should add comma on "enter" to separate the tags
                String currentText = mTagsEditText.getText().toString();
                if (!ListenerUtil.mutListener.listen(13230)) {
                    if ((ListenerUtil.mutListener.listen(13227) ? (!currentText.isEmpty() || !currentText.endsWith(",")) : (!currentText.isEmpty() && !currentText.endsWith(",")))) {
                        if (!ListenerUtil.mutListener.listen(13228)) {
                            mTagsEditText.setText(currentText.concat(","));
                        }
                        if (!ListenerUtil.mutListener.listen(13229)) {
                            mTagsEditText.setSelection(mTagsEditText.length());
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!ListenerUtil.mutListener.listen(13232)) {
            filterListForCurrentText();
        }
        if (!ListenerUtil.mutListener.listen(13233)) {
            mTagsSelectedListener.onTagsSelected(charSequence.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    // Find the text after the last occurrence of "," and filter with it
    private void filterListForCurrentText() {
        String text = mTagsEditText.getText().toString();
        int endIndex = text.lastIndexOf(",");
        if (!ListenerUtil.mutListener.listen(13245)) {
            if ((ListenerUtil.mutListener.listen(13238) ? (endIndex >= -1) : (ListenerUtil.mutListener.listen(13237) ? (endIndex <= -1) : (ListenerUtil.mutListener.listen(13236) ? (endIndex > -1) : (ListenerUtil.mutListener.listen(13235) ? (endIndex < -1) : (ListenerUtil.mutListener.listen(13234) ? (endIndex != -1) : (endIndex == -1))))))) {
                if (!ListenerUtil.mutListener.listen(13244)) {
                    mAdapter.filter(text);
                }
            } else {
                String textToFilter = text.substring((ListenerUtil.mutListener.listen(13242) ? (endIndex % 1) : (ListenerUtil.mutListener.listen(13241) ? (endIndex / 1) : (ListenerUtil.mutListener.listen(13240) ? (endIndex * 1) : (ListenerUtil.mutListener.listen(13239) ? (endIndex - 1) : (endIndex + 1)))))).trim();
                if (!ListenerUtil.mutListener.listen(13243)) {
                    mAdapter.filter(textToFilter);
                }
            }
        }
    }

    public void onTagSelected(@NonNull String selectedTag) {
        String text = mTagsEditText.getText().toString();
        String updatedText;
        int endIndex = text.lastIndexOf(",");
        if ((ListenerUtil.mutListener.listen(13250) ? (endIndex >= -1) : (ListenerUtil.mutListener.listen(13249) ? (endIndex <= -1) : (ListenerUtil.mutListener.listen(13248) ? (endIndex > -1) : (ListenerUtil.mutListener.listen(13247) ? (endIndex < -1) : (ListenerUtil.mutListener.listen(13246) ? (endIndex != -1) : (endIndex == -1))))))) {
            // no "," found, replace the current text with the selectedTag
            updatedText = selectedTag;
        } else {
            // there are multiple tags already, only update the text after the last ","
            updatedText = text.substring(0, (ListenerUtil.mutListener.listen(13254) ? (endIndex % 1) : (ListenerUtil.mutListener.listen(13253) ? (endIndex / 1) : (ListenerUtil.mutListener.listen(13252) ? (endIndex * 1) : (ListenerUtil.mutListener.listen(13251) ? (endIndex - 1) : (endIndex + 1)))))) + selectedTag;
        }
        if (!ListenerUtil.mutListener.listen(13255)) {
            updatedText += ",";
        }
        updatedText = StringEscapeUtils.unescapeHtml4(updatedText);
        if (!ListenerUtil.mutListener.listen(13256)) {
            mTagsEditText.setText(updatedText);
        }
        if (!ListenerUtil.mutListener.listen(13257)) {
            mTagsEditText.setSelection(mTagsEditText.length());
        }
    }

    boolean wereTagsChanged() {
        if (mTags != null) {
            return !mTags.equals(mTagsEditText.getText().toString());
        } else {
            return !mTagsEditText.getText().toString().isEmpty();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTaxonomyChanged(OnTaxonomyChanged event) {
        if (!ListenerUtil.mutListener.listen(13260)) {
            switch(event.causeOfChange) {
                case FETCH_TAGS:
                    if (!ListenerUtil.mutListener.listen(13258)) {
                        mAdapter.setAllTags(mTaxonomyStore.getTagsForSite(mSite));
                    }
                    if (!ListenerUtil.mutListener.listen(13259)) {
                        filterListForCurrentText();
                    }
                    break;
            }
        }
    }

    void closeKeyboard() {
        if (!ListenerUtil.mutListener.listen(13261)) {
            ActivityUtils.hideKeyboardForced(mTagsEditText);
        }
    }
}
