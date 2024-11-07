package fr.free.nrw.commons.upload;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.contributions.Contribution;
import fr.free.nrw.commons.contributions.ContributionDao;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.settings.Prefs;
import fr.free.nrw.commons.upload.worker.UploadWorker;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class UploadController {

    private final SessionManager sessionManager;

    private final Context context;

    private final JsonKvStore store;

    @Inject
    public UploadController(final SessionManager sessionManager, final Context context, final JsonKvStore store) {
        this.sessionManager = sessionManager;
        this.context = context;
        this.store = store;
    }

    /**
     * Starts a new upload task.
     *
     * @param contribution the contribution object
     */
    @SuppressLint("StaticFieldLeak")
    public void prepareMedia(final Contribution contribution) {
        // If author name is enabled and set, use it
        final Media media = contribution.getMedia();
        if (!ListenerUtil.mutListener.listen(7715)) {
            if (store.getBoolean("useAuthorName", false)) {
                final String authorName = store.getString("authorName", "");
                if (!ListenerUtil.mutListener.listen(7714)) {
                    media.setAuthor(authorName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7721)) {
            if (TextUtils.isEmpty(media.getAuthor())) {
                final Account currentAccount = sessionManager.getCurrentAccount();
                if (!ListenerUtil.mutListener.listen(7719)) {
                    if (currentAccount == null) {
                        if (!ListenerUtil.mutListener.listen(7716)) {
                            Timber.d("Current account is null");
                        }
                        if (!ListenerUtil.mutListener.listen(7717)) {
                            ViewUtil.showLongToast(context, context.getString(R.string.user_not_logged_in));
                        }
                        if (!ListenerUtil.mutListener.listen(7718)) {
                            sessionManager.forceLogin(context);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(7720)) {
                    media.setAuthor(sessionManager.getUserName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7723)) {
            if (media.getFallbackDescription() == null) {
                if (!ListenerUtil.mutListener.listen(7722)) {
                    media.setFallbackDescription("");
                }
            }
        }
        final String license = store.getString(Prefs.DEFAULT_LICENSE, Prefs.Licenses.CC_BY_SA_3);
        if (!ListenerUtil.mutListener.listen(7724)) {
            media.setLicense(license);
        }
        if (!ListenerUtil.mutListener.listen(7725)) {
            buildUpload(contribution);
        }
    }

    /**
     * Make the Contribution object ready to be uploaded
     * @param contribution
     * @return
     */
    private void buildUpload(final Contribution contribution) {
        final ContentResolver contentResolver = context.getContentResolver();
        if (!ListenerUtil.mutListener.listen(7726)) {
            contribution.setDataLength(resolveDataLength(contentResolver, contribution));
        }
        final String mimeType = resolveMimeType(contentResolver, contribution);
        if (!ListenerUtil.mutListener.listen(7732)) {
            if (mimeType != null) {
                if (!ListenerUtil.mutListener.listen(7727)) {
                    Timber.d("MimeType is: %s", mimeType);
                }
                if (!ListenerUtil.mutListener.listen(7728)) {
                    contribution.setMimeType(mimeType);
                }
                if (!ListenerUtil.mutListener.listen(7731)) {
                    if ((ListenerUtil.mutListener.listen(7729) ? (mimeType.startsWith("image/") || contribution.getDateCreated() == null) : (mimeType.startsWith("image/") && contribution.getDateCreated() == null))) {
                        if (!ListenerUtil.mutListener.listen(7730)) {
                            contribution.setDateCreated(resolveDateTakenOrNow(contentResolver, contribution));
                        }
                    }
                }
            }
        }
    }

    private String resolveMimeType(final ContentResolver contentResolver, final Contribution contribution) {
        final String mimeType = contribution.getMimeType();
        if (!ListenerUtil.mutListener.listen(7735)) {
            if ((ListenerUtil.mutListener.listen(7734) ? ((ListenerUtil.mutListener.listen(7733) ? (mimeType == null && TextUtils.isEmpty(mimeType)) : (mimeType == null || TextUtils.isEmpty(mimeType))) && mimeType.endsWith("*")) : ((ListenerUtil.mutListener.listen(7733) ? (mimeType == null && TextUtils.isEmpty(mimeType)) : (mimeType == null || TextUtils.isEmpty(mimeType))) || mimeType.endsWith("*")))) {
                return contentResolver.getType(contribution.getLocalUri());
            }
        }
        return mimeType;
    }

    private long resolveDataLength(final ContentResolver contentResolver, final Contribution contribution) {
        try {
            if (!ListenerUtil.mutListener.listen(7749)) {
                if ((ListenerUtil.mutListener.listen(7741) ? (contribution.getDataLength() >= 0) : (ListenerUtil.mutListener.listen(7740) ? (contribution.getDataLength() > 0) : (ListenerUtil.mutListener.listen(7739) ? (contribution.getDataLength() < 0) : (ListenerUtil.mutListener.listen(7738) ? (contribution.getDataLength() != 0) : (ListenerUtil.mutListener.listen(7737) ? (contribution.getDataLength() == 0) : (contribution.getDataLength() <= 0))))))) {
                    if (!ListenerUtil.mutListener.listen(7742)) {
                        Timber.d("UploadController/doInBackground, contribution.getLocalUri():%s", contribution.getLocalUri());
                    }
                    final AssetFileDescriptor assetFileDescriptor = contentResolver.openAssetFileDescriptor(Uri.fromFile(new File(contribution.getLocalUri().getPath())), "r");
                    if (!ListenerUtil.mutListener.listen(7748)) {
                        if (assetFileDescriptor != null) {
                            final long length = assetFileDescriptor.getLength();
                            return (ListenerUtil.mutListener.listen(7747) ? (length >= -1) : (ListenerUtil.mutListener.listen(7746) ? (length <= -1) : (ListenerUtil.mutListener.listen(7745) ? (length > -1) : (ListenerUtil.mutListener.listen(7744) ? (length < -1) : (ListenerUtil.mutListener.listen(7743) ? (length == -1) : (length != -1)))))) ? length : countBytes(contentResolver.openInputStream(contribution.getLocalUri()));
                        }
                    }
                }
            }
        } catch (final IOException | NullPointerException | SecurityException e) {
            if (!ListenerUtil.mutListener.listen(7736)) {
                Timber.e(e, "Exception occurred while uploading image");
            }
        }
        return contribution.getDataLength();
    }

    private Date resolveDateTakenOrNow(final ContentResolver contentResolver, final Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(7750)) {
            Timber.d("local uri   %s", contribution.getLocalUri());
        }
        try (final Cursor cursor = dateTakenCursor(contentResolver, contribution)) {
            if ((ListenerUtil.mutListener.listen(7752) ? ((ListenerUtil.mutListener.listen(7751) ? (cursor != null || cursor.getCount() != 0) : (cursor != null && cursor.getCount() != 0)) || cursor.getColumnCount() != 0) : ((ListenerUtil.mutListener.listen(7751) ? (cursor != null || cursor.getCount() != 0) : (cursor != null && cursor.getCount() != 0)) && cursor.getColumnCount() != 0))) {
                if (!ListenerUtil.mutListener.listen(7753)) {
                    cursor.moveToFirst();
                }
                final Date dateCreated = new Date(cursor.getLong(0));
                if (dateCreated.after(new Date(0))) {
                    return dateCreated;
                }
            }
            return new Date();
        }
    }

    private Cursor dateTakenCursor(final ContentResolver contentResolver, final Contribution contribution) {
        return contentResolver.query(contribution.getLocalUri(), new String[] { MediaStore.Images.ImageColumns.DATE_TAKEN }, null, null, null);
    }

    /**
     * Counts the number of bytes in {@code stream}.
     *
     * @param stream the stream
     * @return the number of bytes in {@code stream}
     * @throws IOException if an I/O error occurs
     */
    private long countBytes(final InputStream stream) throws IOException {
        long count = 0;
        final BufferedInputStream bis = new BufferedInputStream(stream);
        if (!ListenerUtil.mutListener.listen(7760)) {
            {
                long _loopCounter122 = 0;
                while ((ListenerUtil.mutListener.listen(7759) ? (bis.read() >= -1) : (ListenerUtil.mutListener.listen(7758) ? (bis.read() <= -1) : (ListenerUtil.mutListener.listen(7757) ? (bis.read() > -1) : (ListenerUtil.mutListener.listen(7756) ? (bis.read() < -1) : (ListenerUtil.mutListener.listen(7755) ? (bis.read() == -1) : (bis.read() != -1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter122", ++_loopCounter122);
                    if (!ListenerUtil.mutListener.listen(7754)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
