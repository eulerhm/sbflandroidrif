/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.fragments.mediaviews;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.MediaViewerActivity;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.MessageType;
import static ch.threema.storage.models.data.MessageContentsType.VOICE_MESSAGE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class MediaViewFragment extends Fragment {

    private static final Logger logger = LoggerFactory.getLogger(MediaViewFragment.class);

    // enums are evil
    private final int ImageState_NONE = 0;

    private final int ImageState_THUMBNAIL = 1;

    private final int ImageState_DECRYPTED = 2;

    public interface OnMediaLoadListener {

        void decrypting();

        void decrypted(boolean success);

        void loaded(File file);

        void thumbnailLoaded(Bitmap bitmap);
    }

    public interface OnMediaOpenListener {

        void closed();

        void open();
    }

    private AbstractMessageModel messageModel;

    private Future threadFullDecrypt;

    private ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();

    protected FileService fileService;

    protected MessageService messageService;

    private File[] decryptedFileCache;

    private OnMediaLoadListener onMediaLoadListener;

    private File decryptedFile;

    private int imageState = ImageState_NONE;

    private WeakReference<TextView> emptyTextViewReference;

    WeakReference<ViewGroup> rootViewReference;

    private Activity activity;

    private int position;

    private static final int KEEP_ALIVE_DELAY = 20000;

    private static final Handler keepAliveHandler = new Handler();

    private final Runnable keepAliveTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(23748)) {
                if (getActivity() != null) {
                    if (!ListenerUtil.mutListener.listen(23746)) {
                        ThreemaApplication.activityUserInteract(getActivity());
                    }
                    if (!ListenerUtil.mutListener.listen(23747)) {
                        keepAliveHandler.postDelayed(keepAliveTask, KEEP_ALIVE_DELAY);
                    }
                }
            }
        }
    };

    public MediaViewFragment() {
        super();
    }

    private void processBundle(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(23752)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(23749)) {
                    this.position = bundle.getInt("position", 0);
                }
                if (!ListenerUtil.mutListener.listen(23750)) {
                    this.messageModel = ((MediaViewerActivity) this.activity).getMessageModel(this.position);
                }
                if (!ListenerUtil.mutListener.listen(23751)) {
                    this.decryptedFileCache = ((MediaViewerActivity) this.activity).getDecryptedFileCache();
                }
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(23753)) {
            super.onAttach(activity);
        }
        if (!ListenerUtil.mutListener.listen(23754)) {
            this.activity = activity;
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
    }

    public void setOnMediaOpenListener(OnMediaOpenListener onMediaOpenListener) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(23755)) {
            if (serviceManager == null) {
                return null;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(23757)) {
                this.fileService = serviceManager.getFileService();
            }
            if (!ListenerUtil.mutListener.listen(23758)) {
                this.messageService = serviceManager.getMessageService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(23756)) {
                logger.error("Exception", e);
            }
            return null;
        }
        ViewGroup rootView = (ViewGroup) inflater.inflate(this.getFragmentResourceId(), container, false);
        if (!ListenerUtil.mutListener.listen(23761)) {
            if (rootView != null) {
                if (!ListenerUtil.mutListener.listen(23759)) {
                    // keep a reference to the textview
                    this.rootViewReference = new WeakReference<>(rootView);
                }
                if (!ListenerUtil.mutListener.listen(23760)) {
                    this.emptyTextViewReference = new WeakReference<>(rootView.findViewById(R.id.empty_text));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23762)) {
            processBundle(getArguments());
        }
        if (!ListenerUtil.mutListener.listen(23763)) {
            this.created(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(23764)) {
            this.decryptThumbnail();
        }
        return rootView;
    }

    protected AbstractMessageModel getMessageModel() {
        return this.messageModel;
    }

    public void setOnImageLoaded(OnMediaLoadListener onMediaLoadListener) {
        if (!ListenerUtil.mutListener.listen(23765)) {
            this.onMediaLoadListener = onMediaLoadListener;
        }
        if (!ListenerUtil.mutListener.listen(23766)) {
            // if image already loaded!
            this.fireLoadedFile();
        }
    }

    public void killDecryptThread() {
        if (!ListenerUtil.mutListener.listen(23769)) {
            if (this.threadFullDecrypt != null) {
                if (!ListenerUtil.mutListener.listen(23767)) {
                    this.threadFullDecrypt.cancel(true);
                }
                if (!ListenerUtil.mutListener.listen(23768)) {
                    this.threadFullDecrypt = null;
                }
            }
        }
    }

    private void fireLoadedFile() {
        if (!ListenerUtil.mutListener.listen(23771)) {
            if (TestUtil.required(this.onMediaLoadListener, this.decryptedFile)) {
                if (!ListenerUtil.mutListener.listen(23770)) {
                    this.onMediaLoadListener.loaded(this.decryptedFile);
                }
            }
        }
    }

    private void decryptThumbnail() {
        if (!ListenerUtil.mutListener.listen(23787)) {
            if (TestUtil.required(this.messageModel, this.fileService)) {
                boolean isGeneric = false;
                if (!ListenerUtil.mutListener.listen(23772)) {
                    logger.debug("show thumbnail of " + this.position);
                }
                Bitmap thumbnail = null;
                try {
                    if (!ListenerUtil.mutListener.listen(23773)) {
                        thumbnail = this.fileService.getMessageThumbnailBitmap(messageModel, null);
                    }
                } catch (Exception e) {
                }
                String filename = null;
                if (!ListenerUtil.mutListener.listen(23779)) {
                    if (thumbnail == null) {
                        if (!ListenerUtil.mutListener.listen(23778)) {
                            if (messageModel.getMessageContentsType() == VOICE_MESSAGE) {
                                if (!ListenerUtil.mutListener.listen(23777)) {
                                    thumbnail = BitmapUtil.getBitmapFromVectorDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.ic_keyboard_voice_outline), getResources().getColor(R.color.material_dark_grey));
                                }
                            } else if (messageModel.getType() == MessageType.FILE) {
                                if (!ListenerUtil.mutListener.listen(23774)) {
                                    thumbnail = BitmapUtil.tintImage(fileService.getDefaultMessageThumbnailBitmap(getContext(), messageModel, null, messageModel.getFileData().getMimeType()), getResources().getColor(R.color.material_dark_grey));
                                }
                                if (!ListenerUtil.mutListener.listen(23775)) {
                                    filename = messageModel.getFileData().getFileName();
                                }
                                if (!ListenerUtil.mutListener.listen(23776)) {
                                    isGeneric = true;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23786)) {
                    if ((ListenerUtil.mutListener.listen(23780) ? (thumbnail != null || !thumbnail.isRecycled()) : (thumbnail != null && !thumbnail.isRecycled()))) {
                        if (!ListenerUtil.mutListener.listen(23782)) {
                            this.showThumbnail(thumbnail, isGeneric, filename);
                        }
                        if (!ListenerUtil.mutListener.listen(23783)) {
                            this.imageState = ImageState_THUMBNAIL;
                        }
                        if (!ListenerUtil.mutListener.listen(23785)) {
                            if (this.onMediaLoadListener != null) {
                                if (!ListenerUtil.mutListener.listen(23784)) {
                                    this.onMediaLoadListener.thumbnailLoaded(thumbnail);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23781)) {
                            this.showBrokenImage();
                        }
                    }
                }
            }
        }
    }

    public void destroy() {
        if (!ListenerUtil.mutListener.listen(23789)) {
            if (TestUtil.required(this.messageModel)) {
                if (!ListenerUtil.mutListener.listen(23788)) {
                    logger.debug("destroy decrypted image in fragment " + this.position);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23790)) {
            this.killDecryptThread();
        }
    }

    public void hide() {
        if (!ListenerUtil.mutListener.listen(23792)) {
            if (TestUtil.required(this.messageModel)) {
                if (!ListenerUtil.mutListener.listen(23791)) {
                    logger.debug("hide fragment " + this.position);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23793)) {
            this.killDecryptThread();
        }
        if (!ListenerUtil.mutListener.listen(23794)) {
            this.decryptThumbnail();
        }
    }

    public void showDecrypted() {
        if (!ListenerUtil.mutListener.listen(23795)) {
            this.killDecryptThread();
        }
        if (!ListenerUtil.mutListener.listen(23796)) {
            logger.debug("showDecrypted " + position + " imageState = " + this.imageState);
        }
        if (!ListenerUtil.mutListener.listen(23803)) {
            // already decrypted
            if ((ListenerUtil.mutListener.listen(23801) ? (this.imageState >= ImageState_DECRYPTED) : (ListenerUtil.mutListener.listen(23800) ? (this.imageState <= ImageState_DECRYPTED) : (ListenerUtil.mutListener.listen(23799) ? (this.imageState > ImageState_DECRYPTED) : (ListenerUtil.mutListener.listen(23798) ? (this.imageState < ImageState_DECRYPTED) : (ListenerUtil.mutListener.listen(23797) ? (this.imageState != ImageState_DECRYPTED) : (this.imageState == ImageState_DECRYPTED))))))) {
                if (!ListenerUtil.mutListener.listen(23802)) {
                    this.fireLoadedFile();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23804)) {
            this.handleDecryptingFile();
        }
        if (!ListenerUtil.mutListener.listen(23807)) {
            // use cached files!
            if ((ListenerUtil.mutListener.listen(23805) ? (this.decryptedFileCache[this.position] != null || this.decryptedFileCache[this.position].exists()) : (this.decryptedFileCache[this.position] != null && this.decryptedFileCache[this.position].exists()))) {
                if (!ListenerUtil.mutListener.listen(23806)) {
                    this.fileDecrypted(this.decryptedFileCache[this.position]);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23810)) {
            // load decrypted image
            if (TestUtil.required(this.messageModel, this.fileService)) {
                if (!ListenerUtil.mutListener.listen(23808)) {
                    this.killDecryptThread();
                }
                if (!ListenerUtil.mutListener.listen(23809)) {
                    this.threadFullDecrypt = threadPoolExecutor.submit(() -> {
                        try {
                            logger.debug("show decrypted of " + position);
                            final File decrypted = fileService.getDecryptedMessageFile(messageModel);
                            if (!TestUtil.required(decrypted) || !decrypted.exists()) {
                                throw new Exception("Decrypted file not found");
                            }
                            // TODO: If the fragment has been destroyed in the meantime, stop calling any callbacks!
                            RuntimeUtil.runOnUiThread(() -> {
                                fileDecrypted(decrypted);
                                if (TestUtil.required(onMediaLoadListener)) {
                                    onMediaLoadListener.decrypted(true);
                                }
                            });
                        } catch (Exception x) {
                            logger.error("Exception", x);
                            RuntimeUtil.runOnUiThread(() -> {
                                if (TestUtil.required(onMediaLoadListener)) {
                                    onMediaLoadListener.decrypted(false);
                                }
                                // reload thumbnail, if failed, show broken image!
                                decryptThumbnail();
                                handleDecryptFailure();
                            });
                        }
                    });
                }
            }
        }
    }

    protected void showBrokenImage() {
        if (!ListenerUtil.mutListener.listen(23811)) {
            // TODO
            logger.debug("show broken image on position " + this.position);
        }
        if (!ListenerUtil.mutListener.listen(23815)) {
            if ((ListenerUtil.mutListener.listen(23812) ? (this.emptyTextViewReference != null || this.emptyTextViewReference.get() != null) : (this.emptyTextViewReference != null && this.emptyTextViewReference.get() != null))) {
                if (!ListenerUtil.mutListener.listen(23813)) {
                    this.emptyTextViewReference.get().setText(R.string.media_file_not_found);
                }
                if (!ListenerUtil.mutListener.listen(23814)) {
                    this.emptyTextViewReference.get().setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23816)) {
            this.imageState = ImageState_NONE;
        }
    }

    private void fileDecrypted(File file) {
        if (!ListenerUtil.mutListener.listen(23818)) {
            if ((ListenerUtil.mutListener.listen(23817) ? (!TestUtil.required(file) && !file.exists()) : (!TestUtil.required(file) || !file.exists()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23819)) {
            logger.debug("file decrypted " + this.position);
        }
        if (!ListenerUtil.mutListener.listen(23820)) {
            this.decryptedFile = file;
        }
        if (!ListenerUtil.mutListener.listen(23821)) {
            this.decryptedFileCache[this.position] = this.decryptedFile;
        }
        if (!ListenerUtil.mutListener.listen(23824)) {
            if ((ListenerUtil.mutListener.listen(23822) ? (this.emptyTextViewReference != null || this.emptyTextViewReference.get() != null) : (this.emptyTextViewReference != null && this.emptyTextViewReference.get() != null))) {
                if (!ListenerUtil.mutListener.listen(23823)) {
                    this.emptyTextViewReference.get().setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23825)) {
            this.handleDecryptedFile(file);
        }
        if (!ListenerUtil.mutListener.listen(23826)) {
            this.imageState = ImageState_DECRYPTED;
        }
        if (!ListenerUtil.mutListener.listen(23827)) {
            this.fireLoadedFile();
        }
    }

    protected void keepScreenOn(boolean value) {
        if (!ListenerUtil.mutListener.listen(23832)) {
            if (value) {
                if (!ListenerUtil.mutListener.listen(23830)) {
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
                if (!ListenerUtil.mutListener.listen(23831)) {
                    keepAliveHandler.postDelayed(keepAliveTask, KEEP_ALIVE_DELAY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23828)) {
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
                if (!ListenerUtil.mutListener.listen(23829)) {
                    keepAliveHandler.removeCallbacks(keepAliveTask);
                }
            }
        }
    }

    protected void showUi(boolean show) {
        if (!ListenerUtil.mutListener.listen(23837)) {
            if ((ListenerUtil.mutListener.listen(23833) ? (isAdded() || getActivity() != null) : (isAdded() && getActivity() != null))) {
                if (!ListenerUtil.mutListener.listen(23836)) {
                    if (show) {
                        if (!ListenerUtil.mutListener.listen(23835)) {
                            ((MediaViewerActivity) getActivity()).showUi();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23834)) {
                            ((MediaViewerActivity) getActivity()).hideUi();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(23838)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(23839)) {
            keepAliveHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(23840)) {
            super.onSaveInstanceState(outState);
        }
    }

    protected abstract void created(Bundle savedInstanceState);

    protected abstract int getFragmentResourceId();

    public abstract boolean inquireClose();

    protected abstract void showThumbnail(Bitmap thumbnail, boolean isGeneric, String filename);

    protected abstract void hideThumbnail();

    protected abstract void handleDecryptingFile();

    protected abstract void handleDecryptFailure();

    protected abstract void handleDecryptedFile(File file);
}
