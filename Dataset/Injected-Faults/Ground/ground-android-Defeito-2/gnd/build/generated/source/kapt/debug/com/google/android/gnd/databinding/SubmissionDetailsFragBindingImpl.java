package com.google.android.gnd.databinding;
import com.google.android.gnd.R;
import com.google.android.gnd.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SubmissionDetailsFragBindingImpl extends SubmissionDetailsFragBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.submission_details_header, 3);
        sViewsWithIds.put(R.id.submission_details_layout, 4);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SubmissionDetailsFragBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private SubmissionDetailsFragBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (androidx.cardview.widget.CardView) bindings[3]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.ProgressBar) bindings[2]
            , (com.google.android.gnd.ui.common.TwoLineToolbar) bindings[1]
            );
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.submissionDetailsProgressBar.setTag(null);
        this.submissionDetailsToolbar.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.google.android.gnd.ui.submissiondetails.SubmissionDetailsViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.google.android.gnd.ui.submissiondetails.SubmissionDetailsViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelTitle((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelProgressBarVisibility((androidx.lifecycle.LiveData<java.lang.Integer>) object, fieldId);
            case 2 :
                return onChangeViewModelSubtitle((androidx.lifecycle.LiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelTitle(androidx.lifecycle.LiveData<java.lang.String> ViewModelTitle, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelProgressBarVisibility(androidx.lifecycle.LiveData<java.lang.Integer> ViewModelProgressBarVisibility, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelSubtitle(androidx.lifecycle.LiveData<java.lang.String> ViewModelSubtitle, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        int androidxDatabindingViewDataBindingSafeUnboxViewModelProgressBarVisibilityGetValue = 0;
        java.lang.Integer viewModelProgressBarVisibilityGetValue = null;
        java.lang.String viewModelTitleGetValue = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelTitle = null;
        androidx.lifecycle.LiveData<java.lang.Integer> viewModelProgressBarVisibility = null;
        androidx.lifecycle.LiveData<java.lang.String> viewModelSubtitle = null;
        com.google.android.gnd.ui.submissiondetails.SubmissionDetailsViewModel viewModel = mViewModel;
        java.lang.String viewModelSubtitleGetValue = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.title
                        viewModelTitle = viewModel.title;
                    }
                    updateLiveDataRegistration(0, viewModelTitle);


                    if (viewModelTitle != null) {
                        // read viewModel.title.getValue()
                        viewModelTitleGetValue = viewModelTitle.getValue();
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.progressBarVisibility
                        viewModelProgressBarVisibility = viewModel.progressBarVisibility;
                    }
                    updateLiveDataRegistration(1, viewModelProgressBarVisibility);


                    if (viewModelProgressBarVisibility != null) {
                        // read viewModel.progressBarVisibility.getValue()
                        viewModelProgressBarVisibilityGetValue = viewModelProgressBarVisibility.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.progressBarVisibility.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelProgressBarVisibilityGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelProgressBarVisibilityGetValue);
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.subtitle
                        viewModelSubtitle = viewModel.subtitle;
                    }
                    updateLiveDataRegistration(2, viewModelSubtitle);


                    if (viewModelSubtitle != null) {
                        // read viewModel.subtitle.getValue()
                        viewModelSubtitleGetValue = viewModelSubtitle.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            this.submissionDetailsProgressBar.setVisibility(androidxDatabindingViewDataBindingSafeUnboxViewModelProgressBarVisibilityGetValue);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            this.submissionDetailsToolbar.setSubtitle(viewModelSubtitleGetValue);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.submissionDetailsToolbar.setTitle(viewModelTitleGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.title
        flag 1 (0x2L): viewModel.progressBarVisibility
        flag 2 (0x3L): viewModel.subtitle
        flag 3 (0x4L): viewModel
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}