package fr.free.nrw.commons.contributions;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.contributions.ContributionsListAdapter.Callback;
import fr.free.nrw.commons.media.MediaClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContributionViewHolder extends RecyclerView.ViewHolder {

    private final Callback callback;

    @BindView(R.id.contributionImage)
    SimpleDraweeView imageView;

    @BindView(R.id.contributionTitle)
    TextView titleView;

    @BindView(R.id.authorView)
    TextView authorView;

    @BindView(R.id.contributionState)
    TextView stateView;

    @BindView(R.id.contributionSequenceNumber)
    TextView seqNumView;

    @BindView(R.id.contributionProgress)
    ProgressBar progressView;

    @BindView(R.id.image_options)
    RelativeLayout imageOptions;

    @BindView(R.id.wikipediaButton)
    ImageButton addToWikipediaButton;

    @BindView(R.id.retryButton)
    ImageButton retryButton;

    @BindView(R.id.cancelButton)
    ImageButton cancelButton;

    @BindView(R.id.pauseResumeButton)
    ImageButton pauseResumeButton;

    private int position;

    private Contribution contribution;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MediaClient mediaClient;

    private boolean isWikipediaButtonDisplayed;

    private AlertDialog pausingPopUp;

    private View parent;

    private ImageRequest imageRequest;

    ContributionViewHolder(final View parent, final Callback callback, final MediaClient mediaClient) {
        super(parent);
        if (!ListenerUtil.mutListener.listen(1160)) {
            this.parent = parent;
        }
        this.mediaClient = mediaClient;
        if (!ListenerUtil.mutListener.listen(1161)) {
            ButterKnife.bind(this, parent);
        }
        this.callback = callback;
        /* Set a dialog indicating that the upload is being paused. This is needed because pausing
        an upload might take a dozen seconds. */
        AlertDialog.Builder builder = new Builder(parent.getContext());
        if (!ListenerUtil.mutListener.listen(1162)) {
            builder.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(1163)) {
            builder.setView(R.layout.progress_dialog);
        }
        if (!ListenerUtil.mutListener.listen(1164)) {
            pausingPopUp = builder.create();
        }
    }

    public void init(final int position, final Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(1165)) {
            // handling crashes when the contribution is null.
            if (null == contribution) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1166)) {
            this.contribution = contribution;
        }
        if (!ListenerUtil.mutListener.listen(1167)) {
            this.position = position;
        }
        if (!ListenerUtil.mutListener.listen(1168)) {
            titleView.setText(contribution.getMedia().getMostRelevantCaption());
        }
        if (!ListenerUtil.mutListener.listen(1169)) {
            authorView.setText(contribution.getMedia().getAuthor());
        }
        if (!ListenerUtil.mutListener.listen(1170)) {
            // Removes flicker of loading image.
            imageView.getHierarchy().setFadeDuration(0);
        }
        if (!ListenerUtil.mutListener.listen(1171)) {
            imageView.getHierarchy().setPlaceholderImage(R.drawable.image_placeholder);
        }
        if (!ListenerUtil.mutListener.listen(1172)) {
            imageView.getHierarchy().setFailureImage(R.drawable.image_placeholder);
        }
        final String imageSource = chooseImageSource(contribution.getMedia().getThumbUrl(), contribution.getLocalUri());
        if (!ListenerUtil.mutListener.listen(1178)) {
            if (!TextUtils.isEmpty(imageSource)) {
                if (!ListenerUtil.mutListener.listen(1175)) {
                    if (URLUtil.isHttpsUrl(imageSource)) {
                        if (!ListenerUtil.mutListener.listen(1174)) {
                            imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageSource)).setProgressiveRenderingEnabled(true).build();
                        }
                    } else if (imageSource != null) {
                        final File file = new File(imageSource);
                        if (!ListenerUtil.mutListener.listen(1173)) {
                            imageRequest = ImageRequest.fromFile(file);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1177)) {
                    if (imageRequest != null) {
                        if (!ListenerUtil.mutListener.listen(1176)) {
                            imageView.setImageRequest(imageRequest);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1183)) {
            seqNumView.setText(String.valueOf((ListenerUtil.mutListener.listen(1182) ? (position % 1) : (ListenerUtil.mutListener.listen(1181) ? (position / 1) : (ListenerUtil.mutListener.listen(1180) ? (position * 1) : (ListenerUtil.mutListener.listen(1179) ? (position - 1) : (position + 1)))))));
        }
        if (!ListenerUtil.mutListener.listen(1184)) {
            seqNumView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1185)) {
            addToWikipediaButton.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1242)) {
            switch(contribution.getState()) {
                case Contribution.STATE_COMPLETED:
                    if (!ListenerUtil.mutListener.listen(1186)) {
                        stateView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1187)) {
                        progressView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1188)) {
                        imageOptions.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1189)) {
                        stateView.setText("");
                    }
                    if (!ListenerUtil.mutListener.listen(1190)) {
                        checkIfMediaExistsOnWikipediaPage(contribution);
                    }
                    break;
                case Contribution.STATE_QUEUED:
                case Contribution.STATE_QUEUED_LIMITED_CONNECTION_MODE:
                    if (!ListenerUtil.mutListener.listen(1191)) {
                        progressView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1192)) {
                        stateView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1193)) {
                        stateView.setText(R.string.contribution_state_queued);
                    }
                    if (!ListenerUtil.mutListener.listen(1194)) {
                        imageOptions.setVisibility(View.GONE);
                    }
                    break;
                case Contribution.STATE_IN_PROGRESS:
                    if (!ListenerUtil.mutListener.listen(1195)) {
                        stateView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1196)) {
                        progressView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1197)) {
                        addToWikipediaButton.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1198)) {
                        pauseResumeButton.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1199)) {
                        cancelButton.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1200)) {
                        retryButton.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1201)) {
                        imageOptions.setVisibility(View.VISIBLE);
                    }
                    final long total = contribution.getDataLength();
                    final long transferred = contribution.getTransferred();
                    if (!ListenerUtil.mutListener.listen(1224)) {
                        if ((ListenerUtil.mutListener.listen(1212) ? ((ListenerUtil.mutListener.listen(1206) ? (transferred >= 0) : (ListenerUtil.mutListener.listen(1205) ? (transferred <= 0) : (ListenerUtil.mutListener.listen(1204) ? (transferred > 0) : (ListenerUtil.mutListener.listen(1203) ? (transferred < 0) : (ListenerUtil.mutListener.listen(1202) ? (transferred != 0) : (transferred == 0)))))) && (ListenerUtil.mutListener.listen(1211) ? (transferred <= total) : (ListenerUtil.mutListener.listen(1210) ? (transferred > total) : (ListenerUtil.mutListener.listen(1209) ? (transferred < total) : (ListenerUtil.mutListener.listen(1208) ? (transferred != total) : (ListenerUtil.mutListener.listen(1207) ? (transferred == total) : (transferred >= total))))))) : ((ListenerUtil.mutListener.listen(1206) ? (transferred >= 0) : (ListenerUtil.mutListener.listen(1205) ? (transferred <= 0) : (ListenerUtil.mutListener.listen(1204) ? (transferred > 0) : (ListenerUtil.mutListener.listen(1203) ? (transferred < 0) : (ListenerUtil.mutListener.listen(1202) ? (transferred != 0) : (transferred == 0)))))) || (ListenerUtil.mutListener.listen(1211) ? (transferred <= total) : (ListenerUtil.mutListener.listen(1210) ? (transferred > total) : (ListenerUtil.mutListener.listen(1209) ? (transferred < total) : (ListenerUtil.mutListener.listen(1208) ? (transferred != total) : (ListenerUtil.mutListener.listen(1207) ? (transferred == total) : (transferred >= total))))))))) {
                            if (!ListenerUtil.mutListener.listen(1223)) {
                                progressView.setIndeterminate(true);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1213)) {
                                progressView.setIndeterminate(false);
                            }
                            if (!ListenerUtil.mutListener.listen(1222)) {
                                progressView.setProgress((int) ((ListenerUtil.mutListener.listen(1221) ? (((ListenerUtil.mutListener.listen(1217) ? ((double) transferred % (double) total) : (ListenerUtil.mutListener.listen(1216) ? ((double) transferred * (double) total) : (ListenerUtil.mutListener.listen(1215) ? ((double) transferred - (double) total) : (ListenerUtil.mutListener.listen(1214) ? ((double) transferred + (double) total) : ((double) transferred / (double) total)))))) % 100) : (ListenerUtil.mutListener.listen(1220) ? (((ListenerUtil.mutListener.listen(1217) ? ((double) transferred % (double) total) : (ListenerUtil.mutListener.listen(1216) ? ((double) transferred * (double) total) : (ListenerUtil.mutListener.listen(1215) ? ((double) transferred - (double) total) : (ListenerUtil.mutListener.listen(1214) ? ((double) transferred + (double) total) : ((double) transferred / (double) total)))))) / 100) : (ListenerUtil.mutListener.listen(1219) ? (((ListenerUtil.mutListener.listen(1217) ? ((double) transferred % (double) total) : (ListenerUtil.mutListener.listen(1216) ? ((double) transferred * (double) total) : (ListenerUtil.mutListener.listen(1215) ? ((double) transferred - (double) total) : (ListenerUtil.mutListener.listen(1214) ? ((double) transferred + (double) total) : ((double) transferred / (double) total)))))) - 100) : (ListenerUtil.mutListener.listen(1218) ? (((ListenerUtil.mutListener.listen(1217) ? ((double) transferred % (double) total) : (ListenerUtil.mutListener.listen(1216) ? ((double) transferred * (double) total) : (ListenerUtil.mutListener.listen(1215) ? ((double) transferred - (double) total) : (ListenerUtil.mutListener.listen(1214) ? ((double) transferred + (double) total) : ((double) transferred / (double) total)))))) + 100) : (((ListenerUtil.mutListener.listen(1217) ? ((double) transferred % (double) total) : (ListenerUtil.mutListener.listen(1216) ? ((double) transferred * (double) total) : (ListenerUtil.mutListener.listen(1215) ? ((double) transferred - (double) total) : (ListenerUtil.mutListener.listen(1214) ? ((double) transferred + (double) total) : ((double) transferred / (double) total)))))) * 100)))))));
                            }
                        }
                    }
                    break;
                case Contribution.STATE_PAUSED:
                    if (!ListenerUtil.mutListener.listen(1225)) {
                        progressView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1226)) {
                        stateView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1227)) {
                        stateView.setText(R.string.paused);
                    }
                    if (!ListenerUtil.mutListener.listen(1228)) {
                        cancelButton.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1229)) {
                        retryButton.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1230)) {
                        pauseResumeButton.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1231)) {
                        imageOptions.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1232)) {
                        setResume();
                    }
                    if (!ListenerUtil.mutListener.listen(1234)) {
                        if (pausingPopUp.isShowing()) {
                            if (!ListenerUtil.mutListener.listen(1233)) {
                                pausingPopUp.hide();
                            }
                        }
                    }
                    break;
                case Contribution.STATE_FAILED:
                    if (!ListenerUtil.mutListener.listen(1235)) {
                        stateView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1236)) {
                        stateView.setText(R.string.contribution_state_failed);
                    }
                    if (!ListenerUtil.mutListener.listen(1237)) {
                        progressView.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1238)) {
                        cancelButton.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1239)) {
                        retryButton.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(1240)) {
                        pauseResumeButton.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(1241)) {
                        imageOptions.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    }

    /**
     * Checks if a media exists on the corresponding Wikipedia article Currently the check is made
     * for the device's current language Wikipedia
     *
     * @param contribution
     */
    private void checkIfMediaExistsOnWikipediaPage(final Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(1244)) {
            if ((ListenerUtil.mutListener.listen(1243) ? (contribution.getWikidataPlace() == null && contribution.getWikidataPlace().getWikipediaArticle() == null) : (contribution.getWikidataPlace() == null || contribution.getWikidataPlace().getWikipediaArticle() == null))) {
                return;
            }
        }
        final String wikipediaArticle = contribution.getWikidataPlace().getWikipediaPageTitle();
        if (!ListenerUtil.mutListener.listen(1245)) {
            compositeDisposable.add(mediaClient.doesPageContainMedia(wikipediaArticle).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mediaExists -> {
                displayWikipediaButton(mediaExists);
            }));
        }
    }

    /**
     * Handle action buttons visibility if the corresponding wikipedia page doesn't contain any
     * media. This method needs to control the state of just the scenario where media does not
     * exists as other scenarios are already handled in the init method.
     *
     * @param mediaExists
     */
    private void displayWikipediaButton(Boolean mediaExists) {
        if (!ListenerUtil.mutListener.listen(1251)) {
            if (!mediaExists) {
                if (!ListenerUtil.mutListener.listen(1246)) {
                    addToWikipediaButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1247)) {
                    isWikipediaButtonDisplayed = true;
                }
                if (!ListenerUtil.mutListener.listen(1248)) {
                    cancelButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1249)) {
                    retryButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1250)) {
                    imageOptions.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Returns the image source for the image view, first preference is given to thumbUrl if that is
     * null, moves to local uri and if both are null return null
     *
     * @param thumbUrl
     * @param localUri
     * @return
     */
    @Nullable
    private String chooseImageSource(final String thumbUrl, final Uri localUri) {
        return !TextUtils.isEmpty(thumbUrl) ? thumbUrl : localUri != null ? localUri.toString() : null;
    }

    /**
     * Retry upload when it is failed
     */
    @OnClick(R.id.retryButton)
    public void retryUpload() {
        if (!ListenerUtil.mutListener.listen(1252)) {
            callback.retryUpload(contribution);
        }
    }

    /**
     * Delete a failed upload attempt
     */
    @OnClick(R.id.cancelButton)
    public void deleteUpload() {
        if (!ListenerUtil.mutListener.listen(1253)) {
            callback.deleteUpload(contribution);
        }
    }

    @OnClick(R.id.contributionImage)
    public void imageClicked() {
        if (!ListenerUtil.mutListener.listen(1254)) {
            callback.openMediaDetail(position, isWikipediaButtonDisplayed);
        }
    }

    @OnClick(R.id.wikipediaButton)
    public void wikipediaButtonClicked() {
        if (!ListenerUtil.mutListener.listen(1255)) {
            callback.addImageToWikipedia(contribution);
        }
    }

    /**
     * Triggers a callback for pause/resume
     */
    @OnClick(R.id.pauseResumeButton)
    public void onPauseResumeButtonClicked() {
        if (!ListenerUtil.mutListener.listen(1258)) {
            if (pauseResumeButton.getTag().toString().equals("pause")) {
                if (!ListenerUtil.mutListener.listen(1257)) {
                    pause();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1256)) {
                    resume();
                }
            }
        }
    }

    private void resume() {
        if (!ListenerUtil.mutListener.listen(1259)) {
            callback.resumeUpload(contribution);
        }
        if (!ListenerUtil.mutListener.listen(1260)) {
            setPaused();
        }
    }

    private void pause() {
        if (!ListenerUtil.mutListener.listen(1261)) {
            pausingPopUp.show();
        }
        if (!ListenerUtil.mutListener.listen(1262)) {
            callback.pauseUpload(contribution);
        }
        if (!ListenerUtil.mutListener.listen(1263)) {
            setResume();
        }
    }

    /**
     * Update pause/resume button to show pause state
     */
    private void setPaused() {
        if (!ListenerUtil.mutListener.listen(1264)) {
            pauseResumeButton.setImageResource(R.drawable.pause_icon);
        }
        if (!ListenerUtil.mutListener.listen(1265)) {
            pauseResumeButton.setTag(parent.getContext().getString(R.string.pause));
        }
    }

    /**
     * Update pause/resume button to show resume state
     */
    private void setResume() {
        if (!ListenerUtil.mutListener.listen(1266)) {
            pauseResumeButton.setImageResource(R.drawable.play_icon);
        }
        if (!ListenerUtil.mutListener.listen(1267)) {
            pauseResumeButton.setTag(parent.getContext().getString(R.string.resume));
        }
    }

    public ImageRequest getImageRequest() {
        return imageRequest;
    }
}
