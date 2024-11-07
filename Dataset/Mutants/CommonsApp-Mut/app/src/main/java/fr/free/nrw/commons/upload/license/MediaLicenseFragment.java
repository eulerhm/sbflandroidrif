package fr.free.nrw.commons.upload.license;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.databinding.FragmentMediaLicenseBinding;
import fr.free.nrw.commons.upload.UploadActivity;
import fr.free.nrw.commons.utils.DialogUtil;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.upload.UploadBaseFragment;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaLicenseFragment extends UploadBaseFragment implements MediaLicenseContract.View {

    @Inject
    MediaLicenseContract.UserActionListener presenter;

    private FragmentMediaLicenseBinding binding;

    private ArrayAdapter<String> adapter;

    private List<String> licenses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6365)) {
            binding = FragmentMediaLicenseBinding.inflate(inflater, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6366)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6367)) {
            binding.tvTitle.setText(getString(R.string.step_count, callback.getIndexInViewFlipper(this) + 1, callback.getTotalNumberOfSteps(), getString(R.string.license_step_title)));
        }
        if (!ListenerUtil.mutListener.listen(6368)) {
            setTvSubTitle();
        }
        if (!ListenerUtil.mutListener.listen(6369)) {
            binding.btnPrevious.setOnClickListener(v -> callback.onPreviousButtonClicked(callback.getIndexInViewFlipper(this)));
        }
        if (!ListenerUtil.mutListener.listen(6370)) {
            binding.btnSubmit.setOnClickListener(v -> callback.onNextButtonClicked(callback.getIndexInViewFlipper(this)));
        }
        if (!ListenerUtil.mutListener.listen(6371)) {
            binding.tooltip.setOnClickListener(v -> DialogUtil.showAlertDialog(requireActivity(), getString(R.string.license_step_title), getString(R.string.license_tooltip), getString(android.R.string.ok), null, true));
        }
        if (!ListenerUtil.mutListener.listen(6372)) {
            initPresenter();
        }
        if (!ListenerUtil.mutListener.listen(6373)) {
            initLicenseSpinner();
        }
        if (!ListenerUtil.mutListener.listen(6374)) {
            presenter.getLicenses();
        }
    }

    /**
     * Removes the tv Subtitle If the activity is the instance of [UploadActivity] and
     * if multiple files aren't selected.
     */
    private void setTvSubTitle() {
        final Activity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(6377)) {
            if (activity instanceof UploadActivity) {
                final boolean isMultipleFileSelected = ((UploadActivity) activity).getIsMultipleFilesSelected();
                if (!ListenerUtil.mutListener.listen(6376)) {
                    if (!isMultipleFileSelected) {
                        if (!ListenerUtil.mutListener.listen(6375)) {
                            binding.tvSubtitle.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    private void initPresenter() {
        if (!ListenerUtil.mutListener.listen(6378)) {
            presenter.onAttachView(this);
        }
    }

    /**
     * Initialise the license spinner
     */
    private void initLicenseSpinner() {
        if (!ListenerUtil.mutListener.listen(6379)) {
            if (getActivity() == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6380)) {
            adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(6381)) {
            binding.spinnerLicenseList.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(6384)) {
            binding.spinnerLicenseList.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    String licenseName = adapterView.getItemAtPosition(position).toString();
                    if (!ListenerUtil.mutListener.listen(6382)) {
                        presenter.selectLicense(licenseName);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    if (!ListenerUtil.mutListener.listen(6383)) {
                        presenter.selectLicense(null);
                    }
                }
            });
        }
    }

    @Override
    public void setLicenses(List<String> licenses) {
        if (!ListenerUtil.mutListener.listen(6385)) {
            adapter.clear();
        }
        if (!ListenerUtil.mutListener.listen(6386)) {
            this.licenses = licenses;
        }
        if (!ListenerUtil.mutListener.listen(6387)) {
            adapter.addAll(this.licenses);
        }
        if (!ListenerUtil.mutListener.listen(6388)) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setSelectedLicense(String license) {
        int position = licenses.indexOf(getString(Utils.licenseNameFor(license)));
        if (!ListenerUtil.mutListener.listen(6401)) {
            // Check if position is valid
            if ((ListenerUtil.mutListener.listen(6393) ? (position >= 0) : (ListenerUtil.mutListener.listen(6392) ? (position <= 0) : (ListenerUtil.mutListener.listen(6391) ? (position > 0) : (ListenerUtil.mutListener.listen(6390) ? (position != 0) : (ListenerUtil.mutListener.listen(6389) ? (position == 0) : (position < 0))))))) {
                if (!ListenerUtil.mutListener.listen(6395)) {
                    Timber.d("Invalid position: %d. Using default licenses", position);
                }
                if (!ListenerUtil.mutListener.listen(6400)) {
                    position = (ListenerUtil.mutListener.listen(6399) ? (licenses.size() % 1) : (ListenerUtil.mutListener.listen(6398) ? (licenses.size() / 1) : (ListenerUtil.mutListener.listen(6397) ? (licenses.size() * 1) : (ListenerUtil.mutListener.listen(6396) ? (licenses.size() + 1) : (licenses.size() - 1)))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6394)) {
                    Timber.d("Position: %d %s", position, getString(Utils.licenseNameFor(license)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6402)) {
            binding.spinnerLicenseList.setSelection(position);
        }
    }

    @Override
    public void updateLicenseSummary(String licenseSummary, int numberOfItems) {
        String licenseHyperLink = "<a href='" + Utils.licenseUrlFor(licenseSummary) + "'>" + getString(Utils.licenseNameFor(licenseSummary)) + "</a><br>";
        if (!ListenerUtil.mutListener.listen(6403)) {
            setTextViewHTML(binding.tvShareLicenseSummary, getResources().getQuantityString(R.plurals.share_license_summary, numberOfItems, licenseHyperLink));
        }
    }

    private void setTextViewHTML(TextView textView, String text) {
        CharSequence sequence = Html.fromHtml(text);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        if (!ListenerUtil.mutListener.listen(6405)) {
            {
                long _loopCounter100 = 0;
                for (URLSpan span : urls) {
                    ListenerUtil.loopListener.listen("_loopCounter100", ++_loopCounter100);
                    if (!ListenerUtil.mutListener.listen(6404)) {
                        makeLinkClickable(strBuilder, span);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6406)) {
            textView.setText(strBuilder);
        }
        if (!ListenerUtil.mutListener.listen(6407)) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {

            @Override
            public void onClick(View view) {
                // Handle hyperlink click
                String hyperLink = span.getURL();
                if (!ListenerUtil.mutListener.listen(6408)) {
                    launchBrowser(hyperLink);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(6409)) {
            strBuilder.setSpan(clickable, start, end, flags);
        }
        if (!ListenerUtil.mutListener.listen(6410)) {
            strBuilder.removeSpan(span);
        }
    }

    private void launchBrowser(String hyperLink) {
        if (!ListenerUtil.mutListener.listen(6411)) {
            Utils.handleWebUrl(getContext(), Uri.parse(hyperLink));
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(6412)) {
            presenter.onDetachView();
        }
        if (!ListenerUtil.mutListener.listen(6413)) {
            // Free the adapter to avoid memory leaks
            adapter = null;
        }
        if (!ListenerUtil.mutListener.listen(6414)) {
            binding = null;
        }
        if (!ListenerUtil.mutListener.listen(6415)) {
            super.onDestroyView();
        }
    }

    @Override
    protected void onBecameVisible() {
        if (!ListenerUtil.mutListener.listen(6416)) {
            super.onBecameVisible();
        }
        if (!ListenerUtil.mutListener.listen(6420)) {
            /**
             * Show the wlm info message if the upload is a WLM upload
             */
            if ((ListenerUtil.mutListener.listen(6417) ? (callback.isWLMUpload() || presenter.isWLMSupportedForThisPlace()) : (callback.isWLMUpload() && presenter.isWLMSupportedForThisPlace()))) {
                if (!ListenerUtil.mutListener.listen(6419)) {
                    binding.llInfoMonumentUpload.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6418)) {
                    binding.llInfoMonumentUpload.setVisibility(View.GONE);
                }
            }
        }
    }
}
