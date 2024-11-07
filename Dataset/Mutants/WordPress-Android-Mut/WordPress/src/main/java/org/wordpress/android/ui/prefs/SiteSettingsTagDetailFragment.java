package org.wordpress.android.ui.prefs;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.TermModel;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.EditTextUtils;
import org.wordpress.android.util.StringUtils;
import static org.wordpress.android.ui.reader.utils.ReaderUtils.sanitizeWithDashes;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// See https://developer.android.com/reference/android/app/Fragment
public class SiteSettingsTagDetailFragment extends android.app.Fragment {

    private static final String ARGS_TERM = "term";

    private static final String ARGS_IS_NEW_TERM = "is_new";

    static final String TAG = "TagDetailFragment";

    public interface OnTagDetailListener {

        void onRequestDeleteTag(@NonNull TermModel tag);
    }

    private EditText mNameView;

    private EditText mDescriptionView;

    private TermModel mTerm;

    private boolean mIsNewTerm;

    private OnTagDetailListener mListener;

    /*
     * pass an existing term to edit it, or pass null to create a new one
     */
    public static SiteSettingsTagDetailFragment newInstance(@Nullable TermModel term) {
        SiteSettingsTagDetailFragment fragment = new SiteSettingsTagDetailFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(16215)) {
            if (term == null) {
                if (!ListenerUtil.mutListener.listen(16212)) {
                    args.putBoolean(ARGS_IS_NEW_TERM, true);
                }
                if (!ListenerUtil.mutListener.listen(16213)) {
                    term = new TermModel();
                }
                if (!ListenerUtil.mutListener.listen(16214)) {
                    term.setTaxonomy(TaxonomyStore.DEFAULT_TAXONOMY_TAG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16216)) {
            args.putSerializable(ARGS_TERM, term);
        }
        if (!ListenerUtil.mutListener.listen(16217)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(16218)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(16219)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(16220)) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.site_settings_tag_detail_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(16221)) {
            mNameView = view.findViewById(R.id.edit_name);
        }
        if (!ListenerUtil.mutListener.listen(16222)) {
            mDescriptionView = view.findViewById(R.id.edit_description);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(16223)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(16224)) {
            mTerm = (TermModel) getArguments().getSerializable(ARGS_TERM);
        }
        if (!ListenerUtil.mutListener.listen(16225)) {
            mIsNewTerm = getArguments().getBoolean(ARGS_IS_NEW_TERM);
        }
        if (!ListenerUtil.mutListener.listen(16228)) {
            if ((ListenerUtil.mutListener.listen(16226) ? (savedInstanceState == null || !DisplayUtils.isLandscape(getActivity())) : (savedInstanceState == null && !DisplayUtils.isLandscape(getActivity())))) {
                if (!ListenerUtil.mutListener.listen(16227)) {
                    EditTextUtils.showSoftInput(mNameView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16229)) {
            loadTagDetail();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(16230)) {
            menu.clear();
        }
        if (!ListenerUtil.mutListener.listen(16231)) {
            inflater.inflate(R.menu.tag_detail, menu);
        }
        if (!ListenerUtil.mutListener.listen(16232)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(16233)) {
            super.onPrepareOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(16234)) {
            menu.findItem(R.id.menu_trash).setVisible(!mIsNewTerm);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(16237)) {
            if ((ListenerUtil.mutListener.listen(16235) ? (item.getItemId() == R.id.menu_trash || mListener != null) : (item.getItemId() == R.id.menu_trash && mListener != null))) {
                if (!ListenerUtil.mutListener.listen(16236)) {
                    mListener.onRequestDeleteTag(mTerm);
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    void setOnTagDetailListener(@NonNull OnTagDetailListener listener) {
        if (!ListenerUtil.mutListener.listen(16238)) {
            mListener = listener;
        }
    }

    private void loadTagDetail() {
        if (!ListenerUtil.mutListener.listen(16239)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16242)) {
            if (mIsNewTerm) {
                if (!ListenerUtil.mutListener.listen(16241)) {
                    getActivity().setTitle(R.string.add_new_tag);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16240)) {
                    getActivity().setTitle(mTerm.getName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16243)) {
            mNameView.setText(mTerm.getName());
        }
        if (!ListenerUtil.mutListener.listen(16244)) {
            mDescriptionView.setText(mTerm.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(16245)) {
            mNameView.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(16246)) {
            mNameView.setSelection(mNameView.getText().length());
        }
    }

    boolean hasChanges() {
        String thisName = EditTextUtils.getText(mNameView);
        String thisDescription = EditTextUtils.getText(mDescriptionView);
        if (!ListenerUtil.mutListener.listen(16247)) {
            if (mIsNewTerm) {
                return !TextUtils.isEmpty(thisName);
            }
        }
        return (ListenerUtil.mutListener.listen(16249) ? (!TextUtils.isEmpty(thisName) || ((ListenerUtil.mutListener.listen(16248) ? (!StringUtils.equals(mTerm.getName(), thisName) && !StringUtils.equals(mTerm.getDescription(), thisDescription)) : (!StringUtils.equals(mTerm.getName(), thisName) || !StringUtils.equals(mTerm.getDescription(), thisDescription))))) : (!TextUtils.isEmpty(thisName) && ((ListenerUtil.mutListener.listen(16248) ? (!StringUtils.equals(mTerm.getName(), thisName) && !StringUtils.equals(mTerm.getDescription(), thisDescription)) : (!StringUtils.equals(mTerm.getName(), thisName) || !StringUtils.equals(mTerm.getDescription(), thisDescription))))));
    }

    @NonNull
    TermModel getTerm() {
        String thisName = EditTextUtils.getText(mNameView);
        String thisDescription = EditTextUtils.getText(mDescriptionView);
        if (!ListenerUtil.mutListener.listen(16250)) {
            mTerm.setName(thisName);
        }
        if (!ListenerUtil.mutListener.listen(16251)) {
            mTerm.setDescription(thisDescription);
        }
        if (!ListenerUtil.mutListener.listen(16253)) {
            if (mIsNewTerm) {
                if (!ListenerUtil.mutListener.listen(16252)) {
                    mTerm.setSlug(sanitizeWithDashes(thisName));
                }
            }
        }
        return mTerm;
    }

    boolean isNewTerm() {
        return mIsNewTerm;
    }
}
