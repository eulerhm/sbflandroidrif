package fr.free.nrw.commons.upload;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.textfield.TextInputLayout;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.recentlanguages.Language;
import fr.free.nrw.commons.recentlanguages.RecentLanguagesAdapter;
import fr.free.nrw.commons.recentlanguages.RecentLanguagesDao;
import fr.free.nrw.commons.ui.PasteSensitiveTextInputEditText;
import fr.free.nrw.commons.utils.AbstractTextWatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadMediaDetailAdapter extends RecyclerView.Adapter<UploadMediaDetailAdapter.ViewHolder> {

    RecentLanguagesDao recentLanguagesDao;

    private List<UploadMediaDetail> uploadMediaDetails;

    private Callback callback;

    private EventListener eventListener;

    private HashMap<Integer, String> selectedLanguages;

    private final String savedLanguageValue;

    private TextView recentLanguagesTextView;

    private View separator;

    private ListView languageHistoryListView;

    private int currentPosition;

    private Fragment fragment;

    private Activity activity;

    private SelectedVoiceIcon selectedVoiceIcon;

    private static final int REQUEST_CODE_FOR_VOICE_INPUT = 1213;

    public UploadMediaDetailAdapter(Fragment fragment, String savedLanguageValue, RecentLanguagesDao recentLanguagesDao) {
        if (!ListenerUtil.mutListener.listen(7319)) {
            uploadMediaDetails = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(7320)) {
            selectedLanguages = new HashMap<>();
        }
        this.savedLanguageValue = savedLanguageValue;
        if (!ListenerUtil.mutListener.listen(7321)) {
            this.recentLanguagesDao = recentLanguagesDao;
        }
        if (!ListenerUtil.mutListener.listen(7322)) {
            this.fragment = fragment;
        }
    }

    public UploadMediaDetailAdapter(Activity activity, final String savedLanguageValue, List<UploadMediaDetail> uploadMediaDetails, RecentLanguagesDao recentLanguagesDao) {
        if (!ListenerUtil.mutListener.listen(7323)) {
            this.uploadMediaDetails = uploadMediaDetails;
        }
        if (!ListenerUtil.mutListener.listen(7324)) {
            selectedLanguages = new HashMap<>();
        }
        this.savedLanguageValue = savedLanguageValue;
        if (!ListenerUtil.mutListener.listen(7325)) {
            this.recentLanguagesDao = recentLanguagesDao;
        }
        if (!ListenerUtil.mutListener.listen(7326)) {
            this.activity = activity;
        }
    }

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(7327)) {
            this.callback = callback;
        }
    }

    public void setEventListener(EventListener eventListener) {
        if (!ListenerUtil.mutListener.listen(7328)) {
            this.eventListener = eventListener;
        }
    }

    public void setItems(List<UploadMediaDetail> uploadMediaDetails) {
        if (!ListenerUtil.mutListener.listen(7329)) {
            this.uploadMediaDetails = uploadMediaDetails;
        }
        if (!ListenerUtil.mutListener.listen(7330)) {
            selectedLanguages = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(7331)) {
            notifyDataSetChanged();
        }
    }

    public List<UploadMediaDetail> getItems() {
        return uploadMediaDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_description, parent, false));
    }

    /**
     * This is a workaround for a known bug by android here
     * https://issuetracker.google.com/issues/37095917 makes the edit text on second and subsequent
     * fragments inside an adapter receptive to long click for copy/paste options
     *
     * @param holder the view holder
     */
    @Override
    public void onViewAttachedToWindow(@NonNull final ViewHolder holder) {
        if (!ListenerUtil.mutListener.listen(7332)) {
            super.onViewAttachedToWindow(holder);
        }
        if (!ListenerUtil.mutListener.listen(7333)) {
            holder.captionItemEditText.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(7334)) {
            holder.captionItemEditText.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(7335)) {
            holder.descItemEditText.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(7336)) {
            holder.descItemEditText.setEnabled(true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(7337)) {
            holder.bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return uploadMediaDetails.size();
    }

    public void addDescription(UploadMediaDetail uploadMediaDetail) {
        if (!ListenerUtil.mutListener.listen(7338)) {
            selectedLanguages.put(uploadMediaDetails.size(), "en");
        }
        if (!ListenerUtil.mutListener.listen(7339)) {
            this.uploadMediaDetails.add(uploadMediaDetail);
        }
        if (!ListenerUtil.mutListener.listen(7340)) {
            notifyItemInserted(uploadMediaDetails.size());
        }
    }

    private void startSpeechInput(String locale) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        if (!ListenerUtil.mutListener.listen(7341)) {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        }
        if (!ListenerUtil.mutListener.listen(7342)) {
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale);
        }
        try {
            if (!ListenerUtil.mutListener.listen(7346)) {
                if (activity == null) {
                    if (!ListenerUtil.mutListener.listen(7345)) {
                        fragment.startActivityForResult(intent, REQUEST_CODE_FOR_VOICE_INPUT);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7344)) {
                        activity.startActivityForResult(intent, REQUEST_CODE_FOR_VOICE_INPUT);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7343)) {
                Timber.e(e.getMessage());
            }
        }
    }

    public void handleSpeechResult(String spokenText) {
        if (!ListenerUtil.mutListener.listen(7357)) {
            if (!spokenText.isEmpty()) {
                String spokenTextCapitalized = spokenText.substring(0, 1).toUpperCase() + spokenText.substring(1);
                if (!ListenerUtil.mutListener.listen(7356)) {
                    if ((ListenerUtil.mutListener.listen(7351) ? (currentPosition >= uploadMediaDetails.size()) : (ListenerUtil.mutListener.listen(7350) ? (currentPosition <= uploadMediaDetails.size()) : (ListenerUtil.mutListener.listen(7349) ? (currentPosition > uploadMediaDetails.size()) : (ListenerUtil.mutListener.listen(7348) ? (currentPosition != uploadMediaDetails.size()) : (ListenerUtil.mutListener.listen(7347) ? (currentPosition == uploadMediaDetails.size()) : (currentPosition < uploadMediaDetails.size()))))))) {
                        UploadMediaDetail uploadMediaDetail = uploadMediaDetails.get(currentPosition);
                        if (!ListenerUtil.mutListener.listen(7354)) {
                            if (selectedVoiceIcon == SelectedVoiceIcon.CAPTION) {
                                if (!ListenerUtil.mutListener.listen(7353)) {
                                    uploadMediaDetail.setCaptionText(spokenTextCapitalized);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(7352)) {
                                    uploadMediaDetail.setDescriptionText(spokenTextCapitalized);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7355)) {
                            notifyItemChanged(currentPosition);
                        }
                    }
                }
            }
        }
    }

    /**
     * Remove description based on position from the list and notifies the RecyclerView Adapter that
     * data in adapter has been removed at that particular position.
     *
     * @param uploadMediaDetail
     * @param position
     */
    public void removeDescription(final UploadMediaDetail uploadMediaDetail, final int position) {
        if (!ListenerUtil.mutListener.listen(7358)) {
            selectedLanguages.remove(position);
        }
        final int ListPosition = (int) selectedLanguages.keySet().stream().filter(e -> e < position).count();
        if (!ListenerUtil.mutListener.listen(7359)) {
            this.uploadMediaDetails.remove(uploadMediaDetails.get(ListPosition));
        }
        int i = (ListenerUtil.mutListener.listen(7363) ? (position % 1) : (ListenerUtil.mutListener.listen(7362) ? (position / 1) : (ListenerUtil.mutListener.listen(7361) ? (position * 1) : (ListenerUtil.mutListener.listen(7360) ? (position - 1) : (position + 1)))));
        if (!ListenerUtil.mutListener.listen(7366)) {
            {
                long _loopCounter114 = 0;
                while (selectedLanguages.containsKey(i)) {
                    ListenerUtil.loopListener.listen("_loopCounter114", ++_loopCounter114);
                    if (!ListenerUtil.mutListener.listen(7364)) {
                        selectedLanguages.remove(i);
                    }
                    if (!ListenerUtil.mutListener.listen(7365)) {
                        i++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7367)) {
            notifyItemRemoved(position);
        }
        if (!ListenerUtil.mutListener.listen(7372)) {
            notifyItemRangeChanged(position, (ListenerUtil.mutListener.listen(7371) ? (uploadMediaDetails.size() % position) : (ListenerUtil.mutListener.listen(7370) ? (uploadMediaDetails.size() / position) : (ListenerUtil.mutListener.listen(7369) ? (uploadMediaDetails.size() * position) : (ListenerUtil.mutListener.listen(7368) ? (uploadMediaDetails.size() + position) : (uploadMediaDetails.size() - position))))));
        }
        if (!ListenerUtil.mutListener.listen(7373)) {
            updateAddButtonVisibility();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.description_languages)
        TextView descriptionLanguages;

        @BindView(R.id.description_item_edit_text)
        PasteSensitiveTextInputEditText descItemEditText;

        @BindView(R.id.description_item_edit_text_input_layout)
        TextInputLayout descInputLayout;

        @BindView(R.id.caption_item_edit_text)
        PasteSensitiveTextInputEditText captionItemEditText;

        @BindView(R.id.caption_item_edit_text_input_layout)
        TextInputLayout captionInputLayout;

        @BindView(R.id.btn_remove)
        ImageView removeButton;

        @BindView(R.id.btn_add)
        ImageView addButton;

        @BindView(R.id.cl_parent)
        ConstraintLayout clParent;

        @BindView(R.id.ll_write_better_caption)
        LinearLayout betterCaptionLinearLayout;

        @BindView(R.id.ll_write_better_description)
        LinearLayout betterDescriptionLinearLayout;

        AbstractTextWatcher captionListener;

        AbstractTextWatcher descriptionListener;

        public ViewHolder(View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(7374)) {
                ButterKnife.bind(this, itemView);
            }
            if (!ListenerUtil.mutListener.listen(7375)) {
                Timber.i("descItemEditText:" + descItemEditText);
            }
        }

        public void bind(int position) {
            UploadMediaDetail uploadMediaDetail = uploadMediaDetails.get(position);
            if (!ListenerUtil.mutListener.listen(7376)) {
                Timber.d("UploadMediaDetail is " + uploadMediaDetail);
            }
            if (!ListenerUtil.mutListener.listen(7377)) {
                descriptionLanguages.setFocusable(false);
            }
            if (!ListenerUtil.mutListener.listen(7378)) {
                captionItemEditText.addTextChangedListener(new AbstractTextWatcher(value -> {
                    if (position == 0) {
                        eventListener.onPrimaryCaptionTextChange(value.length() != 0);
                    }
                }));
            }
            if (!ListenerUtil.mutListener.listen(7379)) {
                captionItemEditText.removeTextChangedListener(captionListener);
            }
            if (!ListenerUtil.mutListener.listen(7380)) {
                descItemEditText.removeTextChangedListener(descriptionListener);
            }
            if (!ListenerUtil.mutListener.listen(7381)) {
                captionItemEditText.setText(uploadMediaDetail.getCaptionText());
            }
            if (!ListenerUtil.mutListener.listen(7382)) {
                descItemEditText.setText(uploadMediaDetail.getDescriptionText());
            }
            if (!ListenerUtil.mutListener.listen(7383)) {
                captionInputLayout.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
            }
            if (!ListenerUtil.mutListener.listen(7384)) {
                captionInputLayout.setEndIconDrawable(R.drawable.baseline_keyboard_voice);
            }
            if (!ListenerUtil.mutListener.listen(7385)) {
                captionInputLayout.setEndIconOnClickListener(v -> {
                    currentPosition = position;
                    selectedVoiceIcon = SelectedVoiceIcon.CAPTION;
                    startSpeechInput(descriptionLanguages.getText().toString());
                });
            }
            if (!ListenerUtil.mutListener.listen(7386)) {
                descInputLayout.setEndIconMode(TextInputLayout.END_ICON_CUSTOM);
            }
            if (!ListenerUtil.mutListener.listen(7387)) {
                descInputLayout.setEndIconDrawable(R.drawable.baseline_keyboard_voice);
            }
            if (!ListenerUtil.mutListener.listen(7388)) {
                descInputLayout.setEndIconOnClickListener(v -> {
                    currentPosition = position;
                    selectedVoiceIcon = SelectedVoiceIcon.DESCRIPTION;
                    startSpeechInput(descriptionLanguages.getText().toString());
                });
            }
            if (!ListenerUtil.mutListener.listen(7403)) {
                if ((ListenerUtil.mutListener.listen(7393) ? (position >= 0) : (ListenerUtil.mutListener.listen(7392) ? (position <= 0) : (ListenerUtil.mutListener.listen(7391) ? (position > 0) : (ListenerUtil.mutListener.listen(7390) ? (position < 0) : (ListenerUtil.mutListener.listen(7389) ? (position != 0) : (position == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(7397)) {
                        removeButton.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7398)) {
                        betterCaptionLinearLayout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7399)) {
                        betterCaptionLinearLayout.setOnClickListener(v -> callback.showAlert(R.string.media_detail_caption, R.string.caption_info));
                    }
                    if (!ListenerUtil.mutListener.listen(7400)) {
                        betterDescriptionLinearLayout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7401)) {
                        betterDescriptionLinearLayout.setOnClickListener(v -> callback.showAlert(R.string.media_detail_description, R.string.description_info));
                    }
                    if (!ListenerUtil.mutListener.listen(7402)) {
                        Objects.requireNonNull(captionInputLayout.getEditText()).setFilters(new InputFilter[] { new UploadMediaDetailInputFilter() });
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7394)) {
                        removeButton.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7395)) {
                        betterCaptionLinearLayout.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7396)) {
                        betterDescriptionLinearLayout.setVisibility(View.GONE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7404)) {
                removeButton.setOnClickListener(v -> removeDescription(uploadMediaDetail, position));
            }
            if (!ListenerUtil.mutListener.listen(7405)) {
                captionListener = new AbstractTextWatcher(captionText -> uploadMediaDetails.get(position).setCaptionText(convertIdeographicSpaceToLatinSpace(removeLeadingAndTrailingWhitespace(captionText))));
            }
            if (!ListenerUtil.mutListener.listen(7406)) {
                descriptionListener = new AbstractTextWatcher(descriptionText -> uploadMediaDetails.get(position).setDescriptionText(descriptionText));
            }
            if (!ListenerUtil.mutListener.listen(7407)) {
                captionItemEditText.addTextChangedListener(captionListener);
            }
            if (!ListenerUtil.mutListener.listen(7408)) {
                initLanguage(position, uploadMediaDetail);
            }
            if (!ListenerUtil.mutListener.listen(7409)) {
                descItemEditText.addTextChangedListener(descriptionListener);
            }
            if (!ListenerUtil.mutListener.listen(7410)) {
                initLanguage(position, uploadMediaDetail);
            }
            if (!ListenerUtil.mutListener.listen(7416)) {
                if (fragment != null) {
                    FrameLayout.LayoutParams newLayoutParams = (FrameLayout.LayoutParams) clParent.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(7411)) {
                        newLayoutParams.topMargin = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(7412)) {
                        newLayoutParams.leftMargin = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(7413)) {
                        newLayoutParams.rightMargin = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(7414)) {
                        newLayoutParams.bottomMargin = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(7415)) {
                        clParent.setLayoutParams(newLayoutParams);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7417)) {
                updateAddButtonVisibility();
            }
            if (!ListenerUtil.mutListener.listen(7418)) {
                addButton.setOnClickListener(v -> eventListener.addLanguage());
            }
            if (!ListenerUtil.mutListener.listen(7421)) {
                // If the description was manually added by the user, it deserves focus, if not, let the user decide
                if (uploadMediaDetail.isManuallyAdded()) {
                    if (!ListenerUtil.mutListener.listen(7420)) {
                        captionItemEditText.requestFocus();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7419)) {
                        captionItemEditText.clearFocus();
                    }
                }
            }
        }

        private void initLanguage(int position, UploadMediaDetail description) {
            final List<Language> recentLanguages = recentLanguagesDao.getRecentLanguages();
            LanguagesAdapter languagesAdapter = new LanguagesAdapter(descriptionLanguages.getContext(), selectedLanguages);
            if (!ListenerUtil.mutListener.listen(7456)) {
                descriptionLanguages.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(view.getContext());
                        if (!ListenerUtil.mutListener.listen(7422)) {
                            dialog.setContentView(R.layout.dialog_select_language);
                        }
                        if (!ListenerUtil.mutListener.listen(7423)) {
                            dialog.setCanceledOnTouchOutside(true);
                        }
                        if (!ListenerUtil.mutListener.listen(7432)) {
                            dialog.getWindow().setLayout((int) ((ListenerUtil.mutListener.listen(7427) ? (view.getContext().getResources().getDisplayMetrics().widthPixels % 0.90) : (ListenerUtil.mutListener.listen(7426) ? (view.getContext().getResources().getDisplayMetrics().widthPixels / 0.90) : (ListenerUtil.mutListener.listen(7425) ? (view.getContext().getResources().getDisplayMetrics().widthPixels - 0.90) : (ListenerUtil.mutListener.listen(7424) ? (view.getContext().getResources().getDisplayMetrics().widthPixels + 0.90) : (view.getContext().getResources().getDisplayMetrics().widthPixels * 0.90)))))), (int) ((ListenerUtil.mutListener.listen(7431) ? (view.getContext().getResources().getDisplayMetrics().heightPixels % 0.90) : (ListenerUtil.mutListener.listen(7430) ? (view.getContext().getResources().getDisplayMetrics().heightPixels / 0.90) : (ListenerUtil.mutListener.listen(7429) ? (view.getContext().getResources().getDisplayMetrics().heightPixels - 0.90) : (ListenerUtil.mutListener.listen(7428) ? (view.getContext().getResources().getDisplayMetrics().heightPixels + 0.90) : (view.getContext().getResources().getDisplayMetrics().heightPixels * 0.90)))))));
                        }
                        if (!ListenerUtil.mutListener.listen(7433)) {
                            dialog.show();
                        }
                        EditText editText = dialog.findViewById(R.id.search_language);
                        ListView listView = dialog.findViewById(R.id.language_list);
                        if (!ListenerUtil.mutListener.listen(7434)) {
                            languageHistoryListView = dialog.findViewById(R.id.language_history_list);
                        }
                        if (!ListenerUtil.mutListener.listen(7435)) {
                            recentLanguagesTextView = dialog.findViewById(R.id.recent_searches);
                        }
                        if (!ListenerUtil.mutListener.listen(7436)) {
                            separator = dialog.findViewById(R.id.separator);
                        }
                        if (!ListenerUtil.mutListener.listen(7437)) {
                            setUpRecentLanguagesSection(recentLanguages);
                        }
                        if (!ListenerUtil.mutListener.listen(7438)) {
                            listView.setAdapter(languagesAdapter);
                        }
                        if (!ListenerUtil.mutListener.listen(7441)) {
                            editText.addTextChangedListener(new TextWatcher() {

                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                    if (!ListenerUtil.mutListener.listen(7439)) {
                                        hideRecentLanguagesSection();
                                    }
                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                    if (!ListenerUtil.mutListener.listen(7440)) {
                                        languagesAdapter.getFilter().filter(charSequence);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable editable) {
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(7442)) {
                            languageHistoryListView.setOnItemClickListener((adapterView, view1, position, id) -> {
                                onRecentLanguageClicked(dialog, adapterView, position, description);
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(7454)) {
                            listView.setOnItemClickListener(new OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    if (!ListenerUtil.mutListener.listen(7443)) {
                                        description.setSelectedLanguageIndex(i);
                                    }
                                    String languageCode = ((LanguagesAdapter) adapterView.getAdapter()).getLanguageCode(i);
                                    if (!ListenerUtil.mutListener.listen(7444)) {
                                        description.setLanguageCode(languageCode);
                                    }
                                    final String languageName = ((LanguagesAdapter) adapterView.getAdapter()).getLanguageName(i);
                                    final boolean isExists = recentLanguagesDao.findRecentLanguage(languageCode);
                                    if (!ListenerUtil.mutListener.listen(7446)) {
                                        if (isExists) {
                                            if (!ListenerUtil.mutListener.listen(7445)) {
                                                recentLanguagesDao.deleteRecentLanguage(languageCode);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(7447)) {
                                        recentLanguagesDao.addRecentLanguage(new Language(languageName, languageCode));
                                    }
                                    if (!ListenerUtil.mutListener.listen(7448)) {
                                        selectedLanguages.remove(position);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7449)) {
                                        selectedLanguages.put(position, languageCode);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7450)) {
                                        ((LanguagesAdapter) adapterView.getAdapter()).setSelectedLangCode(languageCode);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7451)) {
                                        Timber.d("Description language code is: " + languageCode);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7452)) {
                                        descriptionLanguages.setText(languageCode);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7453)) {
                                        dialog.dismiss();
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(7455)) {
                            dialog.setOnDismissListener(dialogInterface -> languagesAdapter.getFilter().filter(""));
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(7490)) {
                if (description.getSelectedLanguageIndex() == -1) {
                    if (!ListenerUtil.mutListener.listen(7489)) {
                        if (!TextUtils.isEmpty(savedLanguageValue)) {
                            if (!ListenerUtil.mutListener.listen(7488)) {
                                // savedLanguageValue is not null
                                if (!TextUtils.isEmpty(description.getLanguageCode())) {
                                    if (!ListenerUtil.mutListener.listen(7485)) {
                                        descriptionLanguages.setText(description.getLanguageCode());
                                    }
                                    if (!ListenerUtil.mutListener.listen(7486)) {
                                        selectedLanguages.remove(position);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7487)) {
                                        selectedLanguages.put(position, description.getLanguageCode());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7481)) {
                                        description.setLanguageCode(savedLanguageValue);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7482)) {
                                        descriptionLanguages.setText(savedLanguageValue);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7483)) {
                                        selectedLanguages.remove(position);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7484)) {
                                        selectedLanguages.put(position, savedLanguageValue);
                                    }
                                }
                            }
                        } else if (!TextUtils.isEmpty(description.getLanguageCode())) {
                            if (!ListenerUtil.mutListener.listen(7478)) {
                                descriptionLanguages.setText(description.getLanguageCode());
                            }
                            if (!ListenerUtil.mutListener.listen(7479)) {
                                selectedLanguages.remove(position);
                            }
                            if (!ListenerUtil.mutListener.listen(7480)) {
                                selectedLanguages.put(position, description.getLanguageCode());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7477)) {
                                // Checking whether Language Code attribute is null or not.
                                if (uploadMediaDetails.get(position).getLanguageCode() != null) {
                                    if (!ListenerUtil.mutListener.listen(7474)) {
                                        // hence providing same language code for the current upload.
                                        descriptionLanguages.setText(uploadMediaDetails.get(position).getLanguageCode());
                                    }
                                    if (!ListenerUtil.mutListener.listen(7475)) {
                                        selectedLanguages.remove(position);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7476)) {
                                        selectedLanguages.put(position, uploadMediaDetails.get(position).getLanguageCode());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7473)) {
                                        if ((ListenerUtil.mutListener.listen(7464) ? (position >= 0) : (ListenerUtil.mutListener.listen(7463) ? (position <= 0) : (ListenerUtil.mutListener.listen(7462) ? (position > 0) : (ListenerUtil.mutListener.listen(7461) ? (position < 0) : (ListenerUtil.mutListener.listen(7460) ? (position != 0) : (position == 0))))))) {
                                            final int defaultLocaleIndex = languagesAdapter.getIndexOfUserDefaultLocale(descriptionLanguages.getContext());
                                            if (!ListenerUtil.mutListener.listen(7469)) {
                                                descriptionLanguages.setText(languagesAdapter.getLanguageCode(defaultLocaleIndex));
                                            }
                                            if (!ListenerUtil.mutListener.listen(7470)) {
                                                description.setLanguageCode(languagesAdapter.getLanguageCode(defaultLocaleIndex));
                                            }
                                            if (!ListenerUtil.mutListener.listen(7471)) {
                                                selectedLanguages.remove(position);
                                            }
                                            if (!ListenerUtil.mutListener.listen(7472)) {
                                                selectedLanguages.put(position, languagesAdapter.getLanguageCode(defaultLocaleIndex));
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(7465)) {
                                                description.setLanguageCode(languagesAdapter.getLanguageCode(0));
                                            }
                                            if (!ListenerUtil.mutListener.listen(7466)) {
                                                descriptionLanguages.setText(languagesAdapter.getLanguageCode(0));
                                            }
                                            if (!ListenerUtil.mutListener.listen(7467)) {
                                                selectedLanguages.remove(position);
                                            }
                                            if (!ListenerUtil.mutListener.listen(7468)) {
                                                selectedLanguages.put(position, languagesAdapter.getLanguageCode(0));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7457)) {
                        descriptionLanguages.setText(description.getLanguageCode());
                    }
                    if (!ListenerUtil.mutListener.listen(7458)) {
                        selectedLanguages.remove(position);
                    }
                    if (!ListenerUtil.mutListener.listen(7459)) {
                        selectedLanguages.put(position, description.getLanguageCode());
                    }
                }
            }
        }

        /**
         * Handles click event for recent language section
         */
        private void onRecentLanguageClicked(final Dialog dialog, final AdapterView<?> adapterView, final int position, final UploadMediaDetail description) {
            if (!ListenerUtil.mutListener.listen(7491)) {
                description.setSelectedLanguageIndex(position);
            }
            final String languageCode = ((RecentLanguagesAdapter) adapterView.getAdapter()).getLanguageCode(position);
            if (!ListenerUtil.mutListener.listen(7492)) {
                description.setLanguageCode(languageCode);
            }
            final String languageName = ((RecentLanguagesAdapter) adapterView.getAdapter()).getLanguageName(position);
            final boolean isExists = recentLanguagesDao.findRecentLanguage(languageCode);
            if (!ListenerUtil.mutListener.listen(7494)) {
                if (isExists) {
                    if (!ListenerUtil.mutListener.listen(7493)) {
                        recentLanguagesDao.deleteRecentLanguage(languageCode);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7495)) {
                recentLanguagesDao.addRecentLanguage(new Language(languageName, languageCode));
            }
            if (!ListenerUtil.mutListener.listen(7496)) {
                selectedLanguages.remove(position);
            }
            if (!ListenerUtil.mutListener.listen(7497)) {
                selectedLanguages.put(position, languageCode);
            }
            if (!ListenerUtil.mutListener.listen(7498)) {
                ((RecentLanguagesAdapter) adapterView.getAdapter()).setSelectedLangCode(languageCode);
            }
            if (!ListenerUtil.mutListener.listen(7499)) {
                Timber.d("Description language code is: %s", languageCode);
            }
            if (!ListenerUtil.mutListener.listen(7500)) {
                descriptionLanguages.setText(languageCode);
            }
            if (!ListenerUtil.mutListener.listen(7501)) {
                dialog.dismiss();
            }
        }

        /**
         * Hides recent languages section
         */
        private void hideRecentLanguagesSection() {
            if (!ListenerUtil.mutListener.listen(7502)) {
                languageHistoryListView.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(7503)) {
                recentLanguagesTextView.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(7504)) {
                separator.setVisibility(View.GONE);
            }
        }

        /**
         * Set up recent languages section
         *
         * @param recentLanguages recently used languages
         */
        private void setUpRecentLanguagesSection(final List<Language> recentLanguages) {
            if (!ListenerUtil.mutListener.listen(7529)) {
                if (recentLanguages.isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(7526)) {
                        languageHistoryListView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7527)) {
                        recentLanguagesTextView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(7528)) {
                        separator.setVisibility(View.GONE);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(7521)) {
                        if ((ListenerUtil.mutListener.listen(7509) ? (recentLanguages.size() >= 5) : (ListenerUtil.mutListener.listen(7508) ? (recentLanguages.size() <= 5) : (ListenerUtil.mutListener.listen(7507) ? (recentLanguages.size() < 5) : (ListenerUtil.mutListener.listen(7506) ? (recentLanguages.size() != 5) : (ListenerUtil.mutListener.listen(7505) ? (recentLanguages.size() == 5) : (recentLanguages.size() > 5))))))) {
                            if (!ListenerUtil.mutListener.listen(7520)) {
                                {
                                    long _loopCounter115 = 0;
                                    for (int i = (ListenerUtil.mutListener.listen(7519) ? (recentLanguages.size() % 1) : (ListenerUtil.mutListener.listen(7518) ? (recentLanguages.size() / 1) : (ListenerUtil.mutListener.listen(7517) ? (recentLanguages.size() * 1) : (ListenerUtil.mutListener.listen(7516) ? (recentLanguages.size() + 1) : (recentLanguages.size() - 1))))); (ListenerUtil.mutListener.listen(7515) ? (i <= 5) : (ListenerUtil.mutListener.listen(7514) ? (i > 5) : (ListenerUtil.mutListener.listen(7513) ? (i < 5) : (ListenerUtil.mutListener.listen(7512) ? (i != 5) : (ListenerUtil.mutListener.listen(7511) ? (i == 5) : (i >= 5)))))); i--) {
                                        ListenerUtil.loopListener.listen("_loopCounter115", ++_loopCounter115);
                                        if (!ListenerUtil.mutListener.listen(7510)) {
                                            recentLanguagesDao.deleteRecentLanguage(recentLanguages.get(i).getLanguageCode());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7522)) {
                        languageHistoryListView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7523)) {
                        recentLanguagesTextView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7524)) {
                        separator.setVisibility(View.VISIBLE);
                    }
                    final RecentLanguagesAdapter recentLanguagesAdapter = new RecentLanguagesAdapter(descriptionLanguages.getContext(), recentLanguagesDao.getRecentLanguages(), selectedLanguages);
                    if (!ListenerUtil.mutListener.listen(7525)) {
                        languageHistoryListView.setAdapter(recentLanguagesAdapter);
                    }
                }
            }
        }

        /**
         * Removes any leading and trailing whitespace from the source text.
         *
         * @param source input string
         * @return a string without leading and trailing whitespace
         */
        public String removeLeadingAndTrailingWhitespace(String source) {
            // Note that String::trim does not adequately remove all whitespace chars.
            int firstNonWhitespaceIndex = 0;
            if (!ListenerUtil.mutListener.listen(7537)) {
                {
                    long _loopCounter116 = 0;
                    while ((ListenerUtil.mutListener.listen(7536) ? (firstNonWhitespaceIndex >= source.length()) : (ListenerUtil.mutListener.listen(7535) ? (firstNonWhitespaceIndex <= source.length()) : (ListenerUtil.mutListener.listen(7534) ? (firstNonWhitespaceIndex > source.length()) : (ListenerUtil.mutListener.listen(7533) ? (firstNonWhitespaceIndex != source.length()) : (ListenerUtil.mutListener.listen(7532) ? (firstNonWhitespaceIndex == source.length()) : (firstNonWhitespaceIndex < source.length()))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter116", ++_loopCounter116);
                        if (!ListenerUtil.mutListener.listen(7531)) {
                            if (Character.isWhitespace(source.charAt(firstNonWhitespaceIndex))) {
                                if (!ListenerUtil.mutListener.listen(7530)) {
                                    firstNonWhitespaceIndex++;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7543)) {
                if ((ListenerUtil.mutListener.listen(7542) ? (firstNonWhitespaceIndex >= source.length()) : (ListenerUtil.mutListener.listen(7541) ? (firstNonWhitespaceIndex <= source.length()) : (ListenerUtil.mutListener.listen(7540) ? (firstNonWhitespaceIndex > source.length()) : (ListenerUtil.mutListener.listen(7539) ? (firstNonWhitespaceIndex < source.length()) : (ListenerUtil.mutListener.listen(7538) ? (firstNonWhitespaceIndex != source.length()) : (firstNonWhitespaceIndex == source.length()))))))) {
                    return "";
                }
            }
            int lastNonWhitespaceIndex = (ListenerUtil.mutListener.listen(7547) ? (source.length() % 1) : (ListenerUtil.mutListener.listen(7546) ? (source.length() / 1) : (ListenerUtil.mutListener.listen(7545) ? (source.length() * 1) : (ListenerUtil.mutListener.listen(7544) ? (source.length() + 1) : (source.length() - 1)))));
            if (!ListenerUtil.mutListener.listen(7555)) {
                {
                    long _loopCounter117 = 0;
                    while ((ListenerUtil.mutListener.listen(7554) ? (lastNonWhitespaceIndex >= firstNonWhitespaceIndex) : (ListenerUtil.mutListener.listen(7553) ? (lastNonWhitespaceIndex <= firstNonWhitespaceIndex) : (ListenerUtil.mutListener.listen(7552) ? (lastNonWhitespaceIndex < firstNonWhitespaceIndex) : (ListenerUtil.mutListener.listen(7551) ? (lastNonWhitespaceIndex != firstNonWhitespaceIndex) : (ListenerUtil.mutListener.listen(7550) ? (lastNonWhitespaceIndex == firstNonWhitespaceIndex) : (lastNonWhitespaceIndex > firstNonWhitespaceIndex))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter117", ++_loopCounter117);
                        if (!ListenerUtil.mutListener.listen(7549)) {
                            if (Character.isWhitespace(source.charAt(lastNonWhitespaceIndex))) {
                                if (!ListenerUtil.mutListener.listen(7548)) {
                                    lastNonWhitespaceIndex--;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            return source.substring(firstNonWhitespaceIndex, (ListenerUtil.mutListener.listen(7559) ? (lastNonWhitespaceIndex % 1) : (ListenerUtil.mutListener.listen(7558) ? (lastNonWhitespaceIndex / 1) : (ListenerUtil.mutListener.listen(7557) ? (lastNonWhitespaceIndex * 1) : (ListenerUtil.mutListener.listen(7556) ? (lastNonWhitespaceIndex - 1) : (lastNonWhitespaceIndex + 1))))));
        }

        /**
         * Convert Ideographic space to Latin space
         *
         * @param source the source text
         * @return a string with Latin spaces instead of Ideographic spaces
         */
        public String convertIdeographicSpaceToLatinSpace(String source) {
            Pattern ideographicSpacePattern = Pattern.compile("\\x{3000}");
            return ideographicSpacePattern.matcher(source).replaceAll(" ");
        }
    }

    /**
     * Hides the visibility of the "Add" button for all items in the RecyclerView except
     * the last item in RecyclerView
     */
    private void updateAddButtonVisibility() {
        int lastItemPosition = (ListenerUtil.mutListener.listen(7563) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(7562) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(7561) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(7560) ? (getItemCount() + 1) : (getItemCount() - 1)))));
        if (!ListenerUtil.mutListener.listen(7576)) {
            {
                long _loopCounter118 = 0;
                // Hide Add Button for all items
                for (int i = 0; (ListenerUtil.mutListener.listen(7575) ? (i >= getItemCount()) : (ListenerUtil.mutListener.listen(7574) ? (i <= getItemCount()) : (ListenerUtil.mutListener.listen(7573) ? (i > getItemCount()) : (ListenerUtil.mutListener.listen(7572) ? (i != getItemCount()) : (ListenerUtil.mutListener.listen(7571) ? (i == getItemCount()) : (i < getItemCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter118", ++_loopCounter118);
                    if (!ListenerUtil.mutListener.listen(7570)) {
                        if (fragment != null) {
                            if (!ListenerUtil.mutListener.listen(7569)) {
                                if (fragment.getView() != null) {
                                    ViewHolder holder = (ViewHolder) ((RecyclerView) fragment.getView().findViewById(R.id.rv_descriptions)).findViewHolderForAdapterPosition(i);
                                    if (!ListenerUtil.mutListener.listen(7568)) {
                                        if (holder != null) {
                                            if (!ListenerUtil.mutListener.listen(7567)) {
                                                holder.addButton.setVisibility(View.GONE);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7566)) {
                                if (this.activity != null) {
                                    ViewHolder holder = (ViewHolder) ((RecyclerView) activity.findViewById(R.id.rv_descriptions_captions)).findViewHolderForAdapterPosition(i);
                                    if (!ListenerUtil.mutListener.listen(7565)) {
                                        if (holder != null) {
                                            if (!ListenerUtil.mutListener.listen(7564)) {
                                                holder.addButton.setVisibility(View.GONE);
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
        if (!ListenerUtil.mutListener.listen(7583)) {
            // Show Add Button for the last item
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(7582)) {
                    if (fragment.getView() != null) {
                        ViewHolder lastItemHolder = (ViewHolder) ((RecyclerView) fragment.getView().findViewById(R.id.rv_descriptions)).findViewHolderForAdapterPosition(lastItemPosition);
                        if (!ListenerUtil.mutListener.listen(7581)) {
                            if (lastItemHolder != null) {
                                if (!ListenerUtil.mutListener.listen(7580)) {
                                    lastItemHolder.addButton.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7579)) {
                    if (this.activity != null) {
                        ViewHolder lastItemHolder = (ViewHolder) ((RecyclerView) activity.findViewById(R.id.rv_descriptions_captions)).findViewHolderForAdapterPosition(lastItemPosition);
                        if (!ListenerUtil.mutListener.listen(7578)) {
                            if (lastItemHolder != null) {
                                if (!ListenerUtil.mutListener.listen(7577)) {
                                    lastItemHolder.addButton.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public interface Callback {

        void showAlert(int mediaDetailDescription, int descriptionInfo);
    }

    public interface EventListener {

        void onPrimaryCaptionTextChange(boolean isNotEmpty);

        void addLanguage();
    }

    enum SelectedVoiceIcon {

        CAPTION, DESCRIPTION
    }
}
