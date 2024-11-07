package org.wordpress.android.ui.posts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.TermModel;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.models.CategoryNode;
import org.wordpress.android.util.ToastUtils;
import java.util.ArrayList;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AddCategoryFragment extends AppCompatDialogFragment {

    private SiteModel mSite;

    private EditText mCategoryEditText;

    private Spinner mParentSpinner;

    @Inject
    TaxonomyStore mTaxonomyStore;

    public static AddCategoryFragment newInstance(SiteModel site) {
        AddCategoryFragment fragment = new AddCategoryFragment();
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(11252)) {
            bundle.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(11253)) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11254)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(11255)) {
            initSite(savedInstanceState);
        }
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // noinspection InflateParams
        View view = inflater.inflate(R.layout.add_category, null);
        if (!ListenerUtil.mutListener.listen(11256)) {
            mCategoryEditText = (EditText) view.findViewById(R.id.category_name);
        }
        if (!ListenerUtil.mutListener.listen(11257)) {
            mParentSpinner = (Spinner) view.findViewById(R.id.parent_category);
        }
        if (!ListenerUtil.mutListener.listen(11258)) {
            loadCategories();
        }
        if (!ListenerUtil.mutListener.listen(11259)) {
            builder.setView(view).setPositiveButton(android.R.string.ok, null).setNegativeButton(android.R.string.cancel, null);
        }
        return builder.create();
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(11260)) {
            super.onStart();
        }
        AlertDialog dialog = (AlertDialog) getDialog();
        if (!ListenerUtil.mutListener.listen(11263)) {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(11262)) {
                        if (addCategory()) {
                            if (!ListenerUtil.mutListener.listen(11261)) {
                                dismiss();
                            }
                        }
                    }
                }
            });
        }
    }

    private void initSite(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11268)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(11267)) {
                    if (getArguments() != null) {
                        if (!ListenerUtil.mutListener.listen(11266)) {
                            mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11265)) {
                            mSite = (SiteModel) getActivity().getIntent().getSerializableExtra(WordPress.SITE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11264)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11271)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(11269)) {
                    ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(11270)) {
                    getFragmentManager().popBackStack();
                }
            }
        }
    }

    private boolean addCategory() {
        String categoryName = mCategoryEditText.getText().toString();
        CategoryNode selectedCategory = (CategoryNode) mParentSpinner.getSelectedItem();
        long parentId = (selectedCategory != null) ? selectedCategory.getCategoryId() : 0;
        if (!ListenerUtil.mutListener.listen(11273)) {
            if (categoryName.replaceAll(" ", "").equals("")) {
                if (!ListenerUtil.mutListener.listen(11272)) {
                    mCategoryEditText.setError(getText(R.string.cat_name_required));
                }
                return false;
            }
        }
        TermModel newCategory = new TermModel();
        if (!ListenerUtil.mutListener.listen(11274)) {
            newCategory.setTaxonomy(TaxonomyStore.DEFAULT_TAXONOMY_CATEGORY);
        }
        if (!ListenerUtil.mutListener.listen(11275)) {
            newCategory.setName(categoryName);
        }
        if (!ListenerUtil.mutListener.listen(11276)) {
            newCategory.setParentRemoteId(parentId);
        }
        if (!ListenerUtil.mutListener.listen(11277)) {
            ((SelectCategoriesActivity) getActivity()).categoryAdded(newCategory);
        }
        return true;
    }

    private void loadCategories() {
        CategoryNode rootCategory = CategoryNode.createCategoryTreeFromList(mTaxonomyStore.getCategoriesForSite(mSite));
        ArrayList<CategoryNode> categoryLevels = CategoryNode.getSortedListOfCategoriesFromRoot(rootCategory);
        if (!ListenerUtil.mutListener.listen(11278)) {
            categoryLevels.add(0, new CategoryNode(0, 0, getString(R.string.top_level_category_name)));
        }
        if (!ListenerUtil.mutListener.listen(11285)) {
            if ((ListenerUtil.mutListener.listen(11283) ? (categoryLevels.size() >= 0) : (ListenerUtil.mutListener.listen(11282) ? (categoryLevels.size() <= 0) : (ListenerUtil.mutListener.listen(11281) ? (categoryLevels.size() < 0) : (ListenerUtil.mutListener.listen(11280) ? (categoryLevels.size() != 0) : (ListenerUtil.mutListener.listen(11279) ? (categoryLevels.size() == 0) : (categoryLevels.size() > 0))))))) {
                ParentCategorySpinnerAdapter categoryAdapter = new ParentCategorySpinnerAdapter(getActivity(), R.layout.categories_row_parent, categoryLevels);
                if (!ListenerUtil.mutListener.listen(11284)) {
                    mParentSpinner.setAdapter(categoryAdapter);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(11286)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(11287)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
    }
}
