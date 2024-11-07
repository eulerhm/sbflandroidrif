/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.colorpicker.ColorPickerDialog;
import com.android.colorpicker.ColorPickerSwatch;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.GenericProgressDialog;
import ch.threema.app.motionviews.FaceItem;
import ch.threema.app.motionviews.viewmodel.Font;
import ch.threema.app.motionviews.viewmodel.Layer;
import ch.threema.app.motionviews.viewmodel.TextLayer;
import ch.threema.app.motionviews.widget.FaceBlurEntity;
import ch.threema.app.motionviews.widget.FaceEmojiEntity;
import ch.threema.app.motionviews.widget.FaceEntity;
import ch.threema.app.motionviews.widget.ImageEntity;
import ch.threema.app.motionviews.widget.MotionEntity;
import ch.threema.app.motionviews.widget.MotionView;
import ch.threema.app.motionviews.widget.PathEntity;
import ch.threema.app.motionviews.widget.TextEntity;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.ui.PaintSelectionPopup;
import ch.threema.app.ui.PaintView;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.BitmapWorkerTask;
import ch.threema.app.utils.BitmapWorkerTaskParams;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DialogUtil;
import ch.threema.app.utils.TestUtil;
import static ch.threema.app.utils.BitmapUtil.FLIP_NONE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImagePaintActivity extends ThreemaToolbarActivity implements GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(ImagePaintActivity.class);

    private static final String DIALOG_TAG_COLOR_PICKER = "colp";

    private static final String KEY_PEN_COLOR = "pc";

    private static final int REQUEST_CODE_STICKER_SELECTOR = 44;

    private static final int REQUEST_CODE_ENTER_TEXT = 45;

    private static final String DIALOG_TAG_QUIT_CONFIRM = "qq";

    private static final String DIALOG_TAG_SAVING_IMAGE = "se";

    private static final String DIALOG_TAG_BLUR_FACES = "bf";

    private static final String SMILEY_PATH = "emojione/3_Emoji_classic/1f600.png";

    private static final int STROKE_MODE_BRUSH = 0;

    private static final int STROKE_MODE_PENCIL = 1;

    private static final int MAX_FACES = 16;

    private ImageView imageView;

    private PaintView paintView;

    private MotionView motionView;

    private FrameLayout imageFrame;

    private int orientation, exifOrientation, flip, exifFlip, clipWidth, clipHeight;

    private Uri imageUri, outputUri;

    private ProgressBar progressBar;

    @ColorInt
    private int penColor;

    private MenuItem undoItem, paletteItem, paintItem, pencilItem, blurFacesItem;

    private PaintSelectionPopup paintSelectionPopup;

    private ArrayList<MotionEntity> undoHistory = new ArrayList<>();

    private boolean saveSemaphore = false;

    private int strokeMode = STROKE_MODE_BRUSH;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_image_paint;
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(3836)) {
            if (hasChanges()) {
                GenericAlertDialog dialogFragment = GenericAlertDialog.newInstance(R.string.draw, R.string.discard_changes, R.string.discard, R.string.cancel);
                if (!ListenerUtil.mutListener.listen(3835)) {
                    dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_QUIT_CONFIRM);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3834)) {
                    finish();
                }
            }
        }
    }

    private boolean hasChanges() {
        return (ListenerUtil.mutListener.listen(3841) ? (undoHistory.size() >= 0) : (ListenerUtil.mutListener.listen(3840) ? (undoHistory.size() <= 0) : (ListenerUtil.mutListener.listen(3839) ? (undoHistory.size() < 0) : (ListenerUtil.mutListener.listen(3838) ? (undoHistory.size() != 0) : (ListenerUtil.mutListener.listen(3837) ? (undoHistory.size() == 0) : (undoHistory.size() > 0))))));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(3842)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(3849)) {
            if ((ListenerUtil.mutListener.listen(3843) ? (resultCode == RESULT_OK || data != null) : (resultCode == RESULT_OK && data != null))) {
                if (!ListenerUtil.mutListener.listen(3848)) {
                    switch(requestCode) {
                        case REQUEST_CODE_STICKER_SELECTOR:
                            final String stickerPath = data.getStringExtra(StickerSelectorActivity.EXTRA_STICKER_PATH);
                            if (!ListenerUtil.mutListener.listen(3845)) {
                                if (!TestUtil.empty(stickerPath)) {
                                    if (!ListenerUtil.mutListener.listen(3844)) {
                                        addSticker(stickerPath);
                                    }
                                }
                            }
                            break;
                        case REQUEST_CODE_ENTER_TEXT:
                            final String text = data.getStringExtra(ImagePaintKeyboardActivity.INTENT_EXTRA_TEXT);
                            if (!ListenerUtil.mutListener.listen(3847)) {
                                if (!TestUtil.empty(text)) {
                                    if (!ListenerUtil.mutListener.listen(3846)) {
                                        addText(text);
                                    }
                                }
                            }
                    }
                }
            }
        }
    }

    private void addSticker(final String stickerPath) {
        if (!ListenerUtil.mutListener.listen(3850)) {
            paintView.setActive(false);
        }
        if (!ListenerUtil.mutListener.listen(3855)) {
            new AsyncTask<Void, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        return BitmapFactory.decodeStream(getAssets().open(stickerPath));
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(3851)) {
                            logger.error("Exception", e);
                        }
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(final Bitmap bitmap) {
                    if (!ListenerUtil.mutListener.listen(3854)) {
                        if (bitmap != null) {
                            if (!ListenerUtil.mutListener.listen(3853)) {
                                motionView.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        Layer layer = new Layer();
                                        ImageEntity entity = new ImageEntity(layer, bitmap, motionView.getWidth(), motionView.getHeight());
                                        if (!ListenerUtil.mutListener.listen(3852)) {
                                            motionView.addEntityAndPosition(entity);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    private void addText(final String text) {
        if (!ListenerUtil.mutListener.listen(3856)) {
            paintView.setActive(false);
        }
        TextLayer textLayer = new TextLayer();
        Font font = new Font();
        if (!ListenerUtil.mutListener.listen(3857)) {
            font.setColor(penColor);
        }
        if (!ListenerUtil.mutListener.listen(3858)) {
            font.setSize(getResources().getDimensionPixelSize(R.dimen.imagepaint_default_font_size));
        }
        if (!ListenerUtil.mutListener.listen(3859)) {
            textLayer.setFont(font);
        }
        if (!ListenerUtil.mutListener.listen(3860)) {
            textLayer.setText(text);
        }
        TextEntity textEntity = new TextEntity(textLayer, motionView.getWidth(), motionView.getHeight());
        if (!ListenerUtil.mutListener.listen(3861)) {
            motionView.addEntityAndPosition(textEntity);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3862)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3869)) {
            if ((ListenerUtil.mutListener.listen(3867) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3866) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3865) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3864) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3863) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(3868)) {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                }
            }
        }
        Intent intent = getIntent();
        MediaItem mediaItem = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (!ListenerUtil.mutListener.listen(3871)) {
            if (mediaItem == null) {
                if (!ListenerUtil.mutListener.listen(3870)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3872)) {
            this.imageUri = mediaItem.getUri();
        }
        if (!ListenerUtil.mutListener.listen(3874)) {
            if (this.imageUri == null) {
                if (!ListenerUtil.mutListener.listen(3873)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3875)) {
            this.orientation = intent.getIntExtra(ThreemaApplication.EXTRA_ORIENTATION, 0);
        }
        if (!ListenerUtil.mutListener.listen(3876)) {
            this.flip = intent.getIntExtra(ThreemaApplication.EXTRA_FLIP, BitmapUtil.FLIP_NONE);
        }
        if (!ListenerUtil.mutListener.listen(3877)) {
            this.exifOrientation = intent.getIntExtra(ThreemaApplication.EXTRA_EXIF_ORIENTATION, 0);
        }
        if (!ListenerUtil.mutListener.listen(3878)) {
            this.exifFlip = intent.getIntExtra(ThreemaApplication.EXTRA_EXIF_FLIP, BitmapUtil.FLIP_NONE);
        }
        if (!ListenerUtil.mutListener.listen(3879)) {
            this.outputUri = intent.getParcelableExtra(ThreemaApplication.EXTRA_OUTPUT_FILE);
        }
        if (!ListenerUtil.mutListener.listen(3880)) {
            setSupportActionBar(getToolbar());
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(3882)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(3881)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3883)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(3884)) {
            actionBar.setTitle("");
        }
        if (!ListenerUtil.mutListener.listen(3885)) {
            this.paintView = findViewById(R.id.paint_view);
        }
        if (!ListenerUtil.mutListener.listen(3886)) {
            this.progressBar = findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(3887)) {
            this.imageView = findViewById(R.id.preview_image);
        }
        if (!ListenerUtil.mutListener.listen(3888)) {
            this.motionView = findViewById(R.id.motion_view);
        }
        if (!ListenerUtil.mutListener.listen(3889)) {
            this.penColor = getResources().getColor(R.color.material_red);
        }
        if (!ListenerUtil.mutListener.listen(3891)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(3890)) {
                    this.penColor = savedInstanceState.getInt(KEY_PEN_COLOR, penColor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3892)) {
            this.paintView.setColor(penColor);
        }
        if (!ListenerUtil.mutListener.listen(3893)) {
            this.paintView.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.imagepaint_brush_stroke_width));
        }
        if (!ListenerUtil.mutListener.listen(3907)) {
            this.paintView.setTouchListener(new PaintView.TouchListener() {

                @Override
                public void onTouchUp() {
                    if (!ListenerUtil.mutListener.listen(3894)) {
                        invalidateOptionsMenu();
                    }
                }

                @Override
                public void onTouchDown() {
                }

                @Override
                public void onAdded() {
                    if (!ListenerUtil.mutListener.listen(3895)) {
                        undoHistory.add(new PathEntity());
                    }
                }

                @Override
                public void onDeleted() {
                    if (!ListenerUtil.mutListener.listen(3906)) {
                        if ((ListenerUtil.mutListener.listen(3900) ? (undoHistory.size() >= 0) : (ListenerUtil.mutListener.listen(3899) ? (undoHistory.size() <= 0) : (ListenerUtil.mutListener.listen(3898) ? (undoHistory.size() < 0) : (ListenerUtil.mutListener.listen(3897) ? (undoHistory.size() != 0) : (ListenerUtil.mutListener.listen(3896) ? (undoHistory.size() == 0) : (undoHistory.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(3905)) {
                                undoHistory.remove((ListenerUtil.mutListener.listen(3904) ? (undoHistory.size() % 1) : (ListenerUtil.mutListener.listen(3903) ? (undoHistory.size() / 1) : (ListenerUtil.mutListener.listen(3902) ? (undoHistory.size() * 1) : (ListenerUtil.mutListener.listen(3901) ? (undoHistory.size() + 1) : (undoHistory.size() - 1))))));
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3922)) {
            this.motionView.setTouchListener(new MotionView.TouchListener() {

                @Override
                public void onSelected(boolean isSelected) {
                    if (!ListenerUtil.mutListener.listen(3908)) {
                        invalidateOptionsMenu();
                    }
                }

                @Override
                public void onLongClick(MotionEntity entity, int x, int y) {
                    if (!ListenerUtil.mutListener.listen(3917)) {
                        paintSelectionPopup.show((ListenerUtil.mutListener.listen(3912) ? ((int) motionView.getX() % x) : (ListenerUtil.mutListener.listen(3911) ? ((int) motionView.getX() / x) : (ListenerUtil.mutListener.listen(3910) ? ((int) motionView.getX() * x) : (ListenerUtil.mutListener.listen(3909) ? ((int) motionView.getX() - x) : ((int) motionView.getX() + x))))), (ListenerUtil.mutListener.listen(3916) ? ((int) motionView.getY() % y) : (ListenerUtil.mutListener.listen(3915) ? ((int) motionView.getY() / y) : (ListenerUtil.mutListener.listen(3914) ? ((int) motionView.getY() * y) : (ListenerUtil.mutListener.listen(3913) ? ((int) motionView.getY() - y) : ((int) motionView.getY() + y))))), !entity.hasFixedPositionAndSize());
                    }
                }

                @Override
                public void onAdded(MotionEntity entity) {
                    if (!ListenerUtil.mutListener.listen(3918)) {
                        undoHistory.add(entity);
                    }
                }

                @SuppressLint("UseValueOf")
                @Override
                public void onDeleted(MotionEntity entity) {
                    if (!ListenerUtil.mutListener.listen(3919)) {
                        undoHistory.remove(entity);
                    }
                }

                @Override
                public void onTouchUp() {
                    if (!ListenerUtil.mutListener.listen(3921)) {
                        if (!paintView.getActive()) {
                            if (!ListenerUtil.mutListener.listen(3920)) {
                                invalidateOptionsMenu();
                            }
                        }
                    }
                }

                @Override
                public void onTouchDown() {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3923)) {
            this.paintSelectionPopup = new PaintSelectionPopup(this, this.motionView);
        }
        if (!ListenerUtil.mutListener.listen(3932)) {
            this.paintSelectionPopup.setListener(new PaintSelectionPopup.PaintSelectPopupListener() {

                @Override
                public void onItemSelected(int tag) {
                    if (!ListenerUtil.mutListener.listen(3927)) {
                        switch(tag) {
                            case PaintSelectionPopup.TAG_REMOVE:
                                if (!ListenerUtil.mutListener.listen(3924)) {
                                    deleteEntity();
                                }
                                break;
                            case PaintSelectionPopup.TAG_FLIP:
                                if (!ListenerUtil.mutListener.listen(3925)) {
                                    flipEntity();
                                }
                                break;
                            case PaintSelectionPopup.TAG_TO_FRONT:
                                if (!ListenerUtil.mutListener.listen(3926)) {
                                    bringToFrontEntity();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                @Override
                public void onOpen() {
                    if (!ListenerUtil.mutListener.listen(3928)) {
                        motionView.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(3929)) {
                        paintView.setClickable(false);
                    }
                }

                @Override
                public void onClose() {
                    if (!ListenerUtil.mutListener.listen(3930)) {
                        motionView.setClickable(true);
                    }
                    if (!ListenerUtil.mutListener.listen(3931)) {
                        paintView.setClickable(true);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3933)) {
            this.imageFrame = findViewById(R.id.content_frame);
        }
        if (!ListenerUtil.mutListener.listen(3934)) {
            this.imageFrame.post(() -> loadImage());
        }
        if (!ListenerUtil.mutListener.listen(3935)) {
            showTooltip();
        }
    }

    private void loadImage() {
        BitmapWorkerTaskParams bitmapParams = new BitmapWorkerTaskParams();
        if (!ListenerUtil.mutListener.listen(3936)) {
            bitmapParams.imageUri = this.imageUri;
        }
        if (!ListenerUtil.mutListener.listen(3937)) {
            bitmapParams.width = this.imageFrame.getWidth();
        }
        if (!ListenerUtil.mutListener.listen(3938)) {
            bitmapParams.height = this.imageFrame.getHeight();
        }
        if (!ListenerUtil.mutListener.listen(3939)) {
            bitmapParams.contentResolver = getContentResolver();
        }
        if (!ListenerUtil.mutListener.listen(3940)) {
            bitmapParams.orientation = this.orientation;
        }
        if (!ListenerUtil.mutListener.listen(3941)) {
            bitmapParams.flip = this.flip;
        }
        if (!ListenerUtil.mutListener.listen(3942)) {
            bitmapParams.exifOrientation = this.exifOrientation;
        }
        if (!ListenerUtil.mutListener.listen(3943)) {
            bitmapParams.exifFlip = this.exifFlip;
        }
        if (!ListenerUtil.mutListener.listen(3944)) {
            logger.debug("screen height: " + bitmapParams.height);
        }
        if (!ListenerUtil.mutListener.listen(3961)) {
            // load main image
            new BitmapWorkerTask(this.imageView) {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(3945)) {
                        super.onPreExecute();
                    }
                    if (!ListenerUtil.mutListener.listen(3946)) {
                        imageView.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3947)) {
                        paintView.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3948)) {
                        motionView.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3949)) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (!ListenerUtil.mutListener.listen(3950)) {
                        super.onPostExecute(bitmap);
                    }
                    if (!ListenerUtil.mutListener.listen(3951)) {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(3952)) {
                        imageView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3953)) {
                        paintView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3954)) {
                        motionView.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3960)) {
                        // clip other views to image size
                        if (bitmap != null) {
                            if (!ListenerUtil.mutListener.listen(3955)) {
                                clipWidth = bitmap.getWidth();
                            }
                            if (!ListenerUtil.mutListener.listen(3956)) {
                                clipHeight = bitmap.getHeight();
                            }
                            if (!ListenerUtil.mutListener.listen(3957)) {
                                paintView.recalculate(clipWidth, clipHeight);
                            }
                            if (!ListenerUtil.mutListener.listen(3958)) {
                                resizeView(paintView, clipWidth, clipHeight);
                            }
                            if (!ListenerUtil.mutListener.listen(3959)) {
                                resizeView(motionView, clipWidth, clipHeight);
                            }
                        }
                    }
                }
            }.execute(bitmapParams);
        }
    }

    private void resizeView(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(3962)) {
            params.width = width;
        }
        if (!ListenerUtil.mutListener.listen(3963)) {
            params.height = height;
        }
        if (!ListenerUtil.mutListener.listen(3964)) {
            view.requestLayout();
        }
    }

    private void selectSticker() {
        if (!ListenerUtil.mutListener.listen(3965)) {
            startActivityForResult(new Intent(ImagePaintActivity.this, StickerSelectorActivity.class), REQUEST_CODE_STICKER_SELECTOR);
        }
        if (!ListenerUtil.mutListener.listen(3966)) {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    private void enterText() {
        Intent intent = new Intent(ImagePaintActivity.this, ImagePaintKeyboardActivity.class);
        if (!ListenerUtil.mutListener.listen(3967)) {
            intent.putExtra(ImagePaintKeyboardActivity.INTENT_EXTRA_COLOR, penColor);
        }
        if (!ListenerUtil.mutListener.listen(3968)) {
            startActivityForResult(intent, REQUEST_CODE_ENTER_TEXT);
        }
        if (!ListenerUtil.mutListener.listen(3969)) {
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void blurFaces(final boolean useEmoji) {
        if (!ListenerUtil.mutListener.listen(3970)) {
            this.paintView.setActive(false);
        }
        if (!ListenerUtil.mutListener.listen(4063)) {
            new AsyncTask<Void, Void, List<FaceItem>>() {

                int numFaces = -1;

                int originalImageWidth, originalImageHeight;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(3971)) {
                        GenericProgressDialog.newInstance(-1, R.string.please_wait).show(getSupportFragmentManager(), DIALOG_TAG_BLUR_FACES);
                    }
                }

                @Override
                protected List<FaceItem> doInBackground(Void... voids) {
                    BitmapFactory.Options options;
                    Bitmap bitmap, orgBitmap;
                    List<FaceItem> faceItemList = new ArrayList<>();
                    try (InputStream measure = getContentResolver().openInputStream(imageUri)) {
                        options = BitmapUtil.getImageDimensions(measure);
                    } catch (IOException | SecurityException | IllegalStateException | OutOfMemoryError e) {
                        if (!ListenerUtil.mutListener.listen(3972)) {
                            logger.error("Exception", e);
                        }
                        return null;
                    }
                    if ((ListenerUtil.mutListener.listen(3983) ? ((ListenerUtil.mutListener.listen(3977) ? (options.outWidth >= 16) : (ListenerUtil.mutListener.listen(3976) ? (options.outWidth <= 16) : (ListenerUtil.mutListener.listen(3975) ? (options.outWidth > 16) : (ListenerUtil.mutListener.listen(3974) ? (options.outWidth != 16) : (ListenerUtil.mutListener.listen(3973) ? (options.outWidth == 16) : (options.outWidth < 16)))))) && (ListenerUtil.mutListener.listen(3982) ? (options.outHeight >= 16) : (ListenerUtil.mutListener.listen(3981) ? (options.outHeight <= 16) : (ListenerUtil.mutListener.listen(3980) ? (options.outHeight > 16) : (ListenerUtil.mutListener.listen(3979) ? (options.outHeight != 16) : (ListenerUtil.mutListener.listen(3978) ? (options.outHeight == 16) : (options.outHeight < 16))))))) : ((ListenerUtil.mutListener.listen(3977) ? (options.outWidth >= 16) : (ListenerUtil.mutListener.listen(3976) ? (options.outWidth <= 16) : (ListenerUtil.mutListener.listen(3975) ? (options.outWidth > 16) : (ListenerUtil.mutListener.listen(3974) ? (options.outWidth != 16) : (ListenerUtil.mutListener.listen(3973) ? (options.outWidth == 16) : (options.outWidth < 16)))))) || (ListenerUtil.mutListener.listen(3982) ? (options.outHeight >= 16) : (ListenerUtil.mutListener.listen(3981) ? (options.outHeight <= 16) : (ListenerUtil.mutListener.listen(3980) ? (options.outHeight > 16) : (ListenerUtil.mutListener.listen(3979) ? (options.outHeight != 16) : (ListenerUtil.mutListener.listen(3978) ? (options.outHeight == 16) : (options.outHeight < 16))))))))) {
                        return null;
                    }
                    if (!ListenerUtil.mutListener.listen(3984)) {
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    }
                    if (!ListenerUtil.mutListener.listen(3985)) {
                        options.inJustDecodeBounds = false;
                    }
                    try (InputStream data = getContentResolver().openInputStream(imageUri)) {
                        if (data != null) {
                            orgBitmap = BitmapFactory.decodeStream(new BufferedInputStream(data), null, options);
                            if (orgBitmap != null) {
                                if ((ListenerUtil.mutListener.listen(3989) ? (exifOrientation != 0 && exifFlip != FLIP_NONE) : (exifOrientation != 0 || exifFlip != FLIP_NONE))) {
                                    orgBitmap = BitmapUtil.rotateBitmap(orgBitmap, exifOrientation, exifFlip);
                                }
                                if ((ListenerUtil.mutListener.listen(3990) ? (orientation != 0 && flip != FLIP_NONE) : (orientation != 0 || flip != FLIP_NONE))) {
                                    orgBitmap = BitmapUtil.rotateBitmap(orgBitmap, orientation, flip);
                                }
                                bitmap = Bitmap.createBitmap(orgBitmap.getWidth() & ~0x1, orgBitmap.getHeight(), Bitmap.Config.RGB_565);
                                if (!ListenerUtil.mutListener.listen(3991)) {
                                    new Canvas(bitmap).drawBitmap(orgBitmap, 0, 0, null);
                                }
                                if (!ListenerUtil.mutListener.listen(3992)) {
                                    originalImageWidth = orgBitmap.getWidth();
                                }
                                if (!ListenerUtil.mutListener.listen(3993)) {
                                    originalImageHeight = orgBitmap.getHeight();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(3988)) {
                                    logger.info("could not open image");
                                }
                                return null;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3987)) {
                                logger.info("could not open input stream");
                            }
                            return null;
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(3986)) {
                            logger.error("Exception", e);
                        }
                        return null;
                    }
                    try {
                        FaceDetector faceDetector = new FaceDetector(bitmap.getWidth(), bitmap.getHeight(), MAX_FACES);
                        FaceDetector.Face[] faces = new FaceDetector.Face[MAX_FACES];
                        if (!ListenerUtil.mutListener.listen(3996)) {
                            numFaces = faceDetector.findFaces(bitmap, faces);
                        }
                        if ((ListenerUtil.mutListener.listen(4001) ? (numFaces >= 1) : (ListenerUtil.mutListener.listen(4000) ? (numFaces <= 1) : (ListenerUtil.mutListener.listen(3999) ? (numFaces > 1) : (ListenerUtil.mutListener.listen(3998) ? (numFaces != 1) : (ListenerUtil.mutListener.listen(3997) ? (numFaces == 1) : (numFaces < 1))))))) {
                            return null;
                        }
                        if (!ListenerUtil.mutListener.listen(4002)) {
                            logger.debug("{} faces found.", numFaces);
                        }
                        Bitmap emoji = null;
                        if (!ListenerUtil.mutListener.listen(4004)) {
                            if (useEmoji) {
                                if (!ListenerUtil.mutListener.listen(4003)) {
                                    emoji = BitmapFactory.decodeStream(getAssets().open(SMILEY_PATH));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(4052)) {
                            {
                                long _loopCounter24 = 0;
                                for (FaceDetector.Face face : faces) {
                                    ListenerUtil.loopListener.listen("_loopCounter24", ++_loopCounter24);
                                    if (!ListenerUtil.mutListener.listen(4051)) {
                                        if (face != null) {
                                            if (!ListenerUtil.mutListener.listen(4050)) {
                                                if (useEmoji) {
                                                    if (!ListenerUtil.mutListener.listen(4049)) {
                                                        faceItemList.add(new FaceItem(face, emoji, 1));
                                                    }
                                                } else {
                                                    float offsetY = (ListenerUtil.mutListener.listen(4008) ? (face.eyesDistance() % FaceEntity.BLUR_RADIUS) : (ListenerUtil.mutListener.listen(4007) ? (face.eyesDistance() / FaceEntity.BLUR_RADIUS) : (ListenerUtil.mutListener.listen(4006) ? (face.eyesDistance() - FaceEntity.BLUR_RADIUS) : (ListenerUtil.mutListener.listen(4005) ? (face.eyesDistance() + FaceEntity.BLUR_RADIUS) : (face.eyesDistance() * FaceEntity.BLUR_RADIUS)))));
                                                    PointF midPoint = new PointF();
                                                    if (!ListenerUtil.mutListener.listen(4009)) {
                                                        face.getMidPoint(midPoint);
                                                    }
                                                    int croppedBitmapSize = (int) ((ListenerUtil.mutListener.listen(4013) ? (offsetY % 2) : (ListenerUtil.mutListener.listen(4012) ? (offsetY / 2) : (ListenerUtil.mutListener.listen(4011) ? (offsetY - 2) : (ListenerUtil.mutListener.listen(4010) ? (offsetY + 2) : (offsetY * 2))))));
                                                    float scale = 1f;
                                                    if (!ListenerUtil.mutListener.listen(4024)) {
                                                        // pixelize large bitmaps
                                                        if ((ListenerUtil.mutListener.listen(4018) ? (croppedBitmapSize >= 64) : (ListenerUtil.mutListener.listen(4017) ? (croppedBitmapSize <= 64) : (ListenerUtil.mutListener.listen(4016) ? (croppedBitmapSize < 64) : (ListenerUtil.mutListener.listen(4015) ? (croppedBitmapSize != 64) : (ListenerUtil.mutListener.listen(4014) ? (croppedBitmapSize == 64) : (croppedBitmapSize > 64))))))) {
                                                            if (!ListenerUtil.mutListener.listen(4023)) {
                                                                scale = (ListenerUtil.mutListener.listen(4022) ? ((float) croppedBitmapSize % 64f) : (ListenerUtil.mutListener.listen(4021) ? ((float) croppedBitmapSize * 64f) : (ListenerUtil.mutListener.listen(4020) ? ((float) croppedBitmapSize - 64f) : (ListenerUtil.mutListener.listen(4019) ? ((float) croppedBitmapSize + 64f) : ((float) croppedBitmapSize / 64f)))));
                                                            }
                                                        }
                                                    }
                                                    float scaleFactor = (ListenerUtil.mutListener.listen(4028) ? (1f % scale) : (ListenerUtil.mutListener.listen(4027) ? (1f * scale) : (ListenerUtil.mutListener.listen(4026) ? (1f - scale) : (ListenerUtil.mutListener.listen(4025) ? (1f + scale) : (1f / scale)))));
                                                    Matrix matrix = new Matrix();
                                                    if (!ListenerUtil.mutListener.listen(4029)) {
                                                        matrix.setScale(scaleFactor, scaleFactor);
                                                    }
                                                    Bitmap croppedBitmap = Bitmap.createBitmap(orgBitmap, (ListenerUtil.mutListener.listen(4034) ? (offsetY >= midPoint.x) : (ListenerUtil.mutListener.listen(4033) ? (offsetY <= midPoint.x) : (ListenerUtil.mutListener.listen(4032) ? (offsetY < midPoint.x) : (ListenerUtil.mutListener.listen(4031) ? (offsetY != midPoint.x) : (ListenerUtil.mutListener.listen(4030) ? (offsetY == midPoint.x) : (offsetY > midPoint.x)))))) ? 0 : (int) ((ListenerUtil.mutListener.listen(4038) ? (midPoint.x % offsetY) : (ListenerUtil.mutListener.listen(4037) ? (midPoint.x / offsetY) : (ListenerUtil.mutListener.listen(4036) ? (midPoint.x * offsetY) : (ListenerUtil.mutListener.listen(4035) ? (midPoint.x + offsetY) : (midPoint.x - offsetY)))))), (ListenerUtil.mutListener.listen(4043) ? (offsetY >= midPoint.y) : (ListenerUtil.mutListener.listen(4042) ? (offsetY <= midPoint.y) : (ListenerUtil.mutListener.listen(4041) ? (offsetY < midPoint.y) : (ListenerUtil.mutListener.listen(4040) ? (offsetY != midPoint.y) : (ListenerUtil.mutListener.listen(4039) ? (offsetY == midPoint.y) : (offsetY > midPoint.y)))))) ? 0 : (int) ((ListenerUtil.mutListener.listen(4047) ? (midPoint.y % offsetY) : (ListenerUtil.mutListener.listen(4046) ? (midPoint.y / offsetY) : (ListenerUtil.mutListener.listen(4045) ? (midPoint.y * offsetY) : (ListenerUtil.mutListener.listen(4044) ? (midPoint.y + offsetY) : (midPoint.y - offsetY)))))), croppedBitmapSize, croppedBitmapSize, matrix, false);
                                                    if (!ListenerUtil.mutListener.listen(4048)) {
                                                        faceItemList.add(new FaceItem(face, croppedBitmap, scale));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return faceItemList;
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(3994)) {
                            logger.error("Face detection failed", e);
                        }
                        return null;
                    } finally {
                        if (!ListenerUtil.mutListener.listen(3995)) {
                            bitmap.recycle();
                        }
                    }
                }

                @Override
                protected void onPostExecute(List<FaceItem> faceItemList) {
                    if (!ListenerUtil.mutListener.listen(4061)) {
                        if ((ListenerUtil.mutListener.listen(4058) ? (faceItemList != null || (ListenerUtil.mutListener.listen(4057) ? (faceItemList.size() >= 0) : (ListenerUtil.mutListener.listen(4056) ? (faceItemList.size() <= 0) : (ListenerUtil.mutListener.listen(4055) ? (faceItemList.size() < 0) : (ListenerUtil.mutListener.listen(4054) ? (faceItemList.size() != 0) : (ListenerUtil.mutListener.listen(4053) ? (faceItemList.size() == 0) : (faceItemList.size() > 0))))))) : (faceItemList != null && (ListenerUtil.mutListener.listen(4057) ? (faceItemList.size() >= 0) : (ListenerUtil.mutListener.listen(4056) ? (faceItemList.size() <= 0) : (ListenerUtil.mutListener.listen(4055) ? (faceItemList.size() < 0) : (ListenerUtil.mutListener.listen(4054) ? (faceItemList.size() != 0) : (ListenerUtil.mutListener.listen(4053) ? (faceItemList.size() == 0) : (faceItemList.size() > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(4060)) {
                                motionView.post(() -> {
                                    {
                                        long _loopCounter25 = 0;
                                        for (FaceItem faceItem : faceItemList) {
                                            ListenerUtil.loopListener.listen("_loopCounter25", ++_loopCounter25);
                                            Layer layer = new Layer();
                                            if (useEmoji) {
                                                FaceEmojiEntity entity = new FaceEmojiEntity(layer, faceItem, originalImageWidth, originalImageHeight, motionView.getWidth(), motionView.getHeight());
                                                motionView.addEntity(entity);
                                            } else {
                                                FaceBlurEntity entity = new FaceBlurEntity(layer, faceItem, originalImageWidth, originalImageHeight, motionView.getWidth(), motionView.getHeight());
                                                motionView.addEntity(entity);
                                            }
                                        }
                                    }
                                });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4059)) {
                                Toast.makeText(ImagePaintActivity.this, R.string.no_faces_detected, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4062)) {
                        DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_BLUR_FACES, true);
                    }
                }
            }.execute();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4064)) {
            super.onPrepareOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(4065)) {
            ConfigUtils.themeMenuItem(paletteItem, Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(4066)) {
            ConfigUtils.themeMenuItem(paintItem, Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(4067)) {
            ConfigUtils.themeMenuItem(pencilItem, Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(4077)) {
            if (motionView.getSelectedEntity() == null) {
                if (!ListenerUtil.mutListener.listen(4076)) {
                    // no selected entities => draw mode or neutral mode
                    if (paintView.getActive()) {
                        if (!ListenerUtil.mutListener.listen(4075)) {
                            if ((ListenerUtil.mutListener.listen(4072) ? (this.strokeMode >= STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4071) ? (this.strokeMode <= STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4070) ? (this.strokeMode > STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4069) ? (this.strokeMode < STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4068) ? (this.strokeMode != STROKE_MODE_PENCIL) : (this.strokeMode == STROKE_MODE_PENCIL))))))) {
                                if (!ListenerUtil.mutListener.listen(4074)) {
                                    ConfigUtils.themeMenuItem(pencilItem, this.penColor);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(4073)) {
                                    ConfigUtils.themeMenuItem(paintItem, this.penColor);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4083)) {
            undoItem.setVisible((ListenerUtil.mutListener.listen(4082) ? (undoHistory.size() >= 0) : (ListenerUtil.mutListener.listen(4081) ? (undoHistory.size() <= 0) : (ListenerUtil.mutListener.listen(4080) ? (undoHistory.size() < 0) : (ListenerUtil.mutListener.listen(4079) ? (undoHistory.size() != 0) : (ListenerUtil.mutListener.listen(4078) ? (undoHistory.size() == 0) : (undoHistory.size() > 0)))))));
        }
        if (!ListenerUtil.mutListener.listen(4084)) {
            blurFacesItem.setVisible(motionView.getEntitiesCount() == 0);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4085)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(4086)) {
            getMenuInflater().inflate(R.menu.activity_image_paint, menu);
        }
        if (!ListenerUtil.mutListener.listen(4087)) {
            undoItem = menu.findItem(R.id.item_undo);
        }
        if (!ListenerUtil.mutListener.listen(4088)) {
            paletteItem = menu.findItem(R.id.item_palette);
        }
        if (!ListenerUtil.mutListener.listen(4089)) {
            paintItem = menu.findItem(R.id.item_draw);
        }
        if (!ListenerUtil.mutListener.listen(4090)) {
            pencilItem = menu.findItem(R.id.item_pencil);
        }
        if (!ListenerUtil.mutListener.listen(4091)) {
            blurFacesItem = menu.findItem(R.id.item_face);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4092)) {
            super.onOptionsItemSelected(item);
        }
        if (!ListenerUtil.mutListener.listen(4118)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(4101)) {
                        if ((ListenerUtil.mutListener.listen(4097) ? (undoHistory.size() >= 0) : (ListenerUtil.mutListener.listen(4096) ? (undoHistory.size() <= 0) : (ListenerUtil.mutListener.listen(4095) ? (undoHistory.size() < 0) : (ListenerUtil.mutListener.listen(4094) ? (undoHistory.size() != 0) : (ListenerUtil.mutListener.listen(4093) ? (undoHistory.size() == 0) : (undoHistory.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(4099)) {
                                item.setEnabled(false);
                            }
                            if (!ListenerUtil.mutListener.listen(4100)) {
                                renderImage();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4098)) {
                                finish();
                            }
                        }
                    }
                    return true;
                case R.id.item_undo:
                    if (!ListenerUtil.mutListener.listen(4102)) {
                        undo();
                    }
                    break;
                case R.id.item_stickers:
                    if (!ListenerUtil.mutListener.listen(4103)) {
                        selectSticker();
                    }
                    break;
                case R.id.item_palette:
                    if (!ListenerUtil.mutListener.listen(4104)) {
                        chooseColor();
                    }
                    break;
                case R.id.item_text:
                    if (!ListenerUtil.mutListener.listen(4105)) {
                        enterText();
                    }
                    break;
                case R.id.item_draw:
                    if (!ListenerUtil.mutListener.listen(4110)) {
                        if ((ListenerUtil.mutListener.listen(4106) ? (strokeMode == STROKE_MODE_BRUSH || this.paintView.getActive()) : (strokeMode == STROKE_MODE_BRUSH && this.paintView.getActive()))) {
                            if (!ListenerUtil.mutListener.listen(4109)) {
                                // switch to selection mode
                                setDrawMode(false);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4107)) {
                                setStrokeMode(STROKE_MODE_BRUSH);
                            }
                            if (!ListenerUtil.mutListener.listen(4108)) {
                                setDrawMode(true);
                            }
                        }
                    }
                    break;
                case R.id.item_pencil:
                    if (!ListenerUtil.mutListener.listen(4115)) {
                        if ((ListenerUtil.mutListener.listen(4111) ? (strokeMode == STROKE_MODE_PENCIL || this.paintView.getActive()) : (strokeMode == STROKE_MODE_PENCIL && this.paintView.getActive()))) {
                            if (!ListenerUtil.mutListener.listen(4114)) {
                                // switch to selection mode
                                setDrawMode(false);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4112)) {
                                setStrokeMode(STROKE_MODE_PENCIL);
                            }
                            if (!ListenerUtil.mutListener.listen(4113)) {
                                setDrawMode(true);
                            }
                        }
                    }
                    break;
                case R.id.item_face_blur:
                    if (!ListenerUtil.mutListener.listen(4116)) {
                        blurFaces(false);
                    }
                    break;
                case R.id.item_face_emoji:
                    if (!ListenerUtil.mutListener.listen(4117)) {
                        blurFaces(true);
                    }
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    @UiThread
    public void showTooltip() {
        if (!ListenerUtil.mutListener.listen(4121)) {
            if (!preferenceService.getIsFaceBlurTooltipShown()) {
                if (!ListenerUtil.mutListener.listen(4120)) {
                    if (getToolbar() != null) {
                        if (!ListenerUtil.mutListener.listen(4119)) {
                            getToolbar().postDelayed(() -> {
                                final View v = findViewById(R.id.item_face);
                                try {
                                    TapTargetView.showFor(this, TapTarget.forView(v, getString(R.string.face_blur_tooltip_title), getString(R.string.face_blur_tooltip_text)).outerCircleColor(// Specify a color for the outer circle
                                    R.color.accent_dark).outerCircleAlpha(// Specify the alpha amount for the outer circle
                                    0.96f).targetCircleColor(// Specify a color for the target circle
                                    android.R.color.white).titleTextSize(// Specify the size (in sp) of the title text
                                    24).titleTextColor(// Specify the color of the title text
                                    android.R.color.white).descriptionTextSize(// Specify the size (in sp) of the description text
                                    18).descriptionTextColor(// Specify the color of the description text
                                    android.R.color.white).textColor(// Specify a color for both the title and description text
                                    android.R.color.white).textTypeface(// Specify a typeface for the text
                                    Typeface.SANS_SERIF).dimColor(// If set, will dim behind the view with 30% opacity of the given color
                                    android.R.color.black).drawShadow(// Whether to draw a drop shadow or not
                                    true).cancelable(// Whether tapping outside the outer circle dismisses the view
                                    true).tintTarget(// Whether to tint the target view's color
                                    true).transparentTarget(// Specify whether the target is transparent (displays the content underneath)
                                    false).targetRadius(// Specify the target radius (in dp)
                                    50));
                                    preferenceService.setFaceBlurTooltipShown(true);
                                } catch (Exception ignore) {
                                }
                            }, 2000);
                        }
                    }
                }
            }
        }
    }

    private void setStrokeMode(int strokeMode) {
        if (!ListenerUtil.mutListener.listen(4122)) {
            this.strokeMode = strokeMode;
        }
        if (!ListenerUtil.mutListener.listen(4128)) {
            this.paintView.setStrokeWidth(getResources().getDimensionPixelSize((ListenerUtil.mutListener.listen(4127) ? (strokeMode >= STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4126) ? (strokeMode <= STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4125) ? (strokeMode > STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4124) ? (strokeMode < STROKE_MODE_PENCIL) : (ListenerUtil.mutListener.listen(4123) ? (strokeMode != STROKE_MODE_PENCIL) : (strokeMode == STROKE_MODE_PENCIL)))))) ? R.dimen.imagepaint_pencil_stroke_width : R.dimen.imagepaint_brush_stroke_width));
        }
    }

    private void deleteEntity() {
        if (!ListenerUtil.mutListener.listen(4129)) {
            motionView.deletedSelectedEntity();
        }
        if (!ListenerUtil.mutListener.listen(4130)) {
            invalidateOptionsMenu();
        }
    }

    private void flipEntity() {
        if (!ListenerUtil.mutListener.listen(4131)) {
            motionView.flipSelectedEntity();
        }
        if (!ListenerUtil.mutListener.listen(4132)) {
            invalidateOptionsMenu();
        }
    }

    private void bringToFrontEntity() {
        if (!ListenerUtil.mutListener.listen(4133)) {
            motionView.moveSelectedEntityToFront();
        }
        if (!ListenerUtil.mutListener.listen(4134)) {
            invalidateOptionsMenu();
        }
    }

    private void undo() {
        if (!ListenerUtil.mutListener.listen(4149)) {
            if ((ListenerUtil.mutListener.listen(4139) ? (undoHistory.size() >= 0) : (ListenerUtil.mutListener.listen(4138) ? (undoHistory.size() <= 0) : (ListenerUtil.mutListener.listen(4137) ? (undoHistory.size() < 0) : (ListenerUtil.mutListener.listen(4136) ? (undoHistory.size() != 0) : (ListenerUtil.mutListener.listen(4135) ? (undoHistory.size() == 0) : (undoHistory.size() > 0))))))) {
                MotionEntity entity = undoHistory.get((ListenerUtil.mutListener.listen(4143) ? (undoHistory.size() % 1) : (ListenerUtil.mutListener.listen(4142) ? (undoHistory.size() / 1) : (ListenerUtil.mutListener.listen(4141) ? (undoHistory.size() * 1) : (ListenerUtil.mutListener.listen(4140) ? (undoHistory.size() + 1) : (undoHistory.size() - 1))))));
                if (!ListenerUtil.mutListener.listen(4144)) {
                    motionView.unselectEntity();
                }
                if (!ListenerUtil.mutListener.listen(4147)) {
                    if (entity instanceof PathEntity) {
                        if (!ListenerUtil.mutListener.listen(4146)) {
                            paintView.undo();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4145)) {
                            motionView.deleteEntity(entity);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4148)) {
                    invalidateOptionsMenu();
                }
            }
        }
    }

    private void setDrawMode(boolean enable) {
        if (!ListenerUtil.mutListener.listen(4153)) {
            if (enable) {
                if (!ListenerUtil.mutListener.listen(4151)) {
                    motionView.unselectEntity();
                }
                if (!ListenerUtil.mutListener.listen(4152)) {
                    paintView.setActive(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4150)) {
                    paintView.setActive(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4154)) {
            invalidateOptionsMenu();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(4155)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(4156)) {
            // hack to adjust toolbar height after rotate
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
        if (!ListenerUtil.mutListener.listen(4157)) {
            this.imageFrame = findViewById(R.id.content_frame);
        }
        if (!ListenerUtil.mutListener.listen(4160)) {
            if (this.imageFrame != null) {
                if (!ListenerUtil.mutListener.listen(4159)) {
                    this.imageFrame.post(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(4158)) {
                                loadImage();
                            }
                        }
                    });
                }
            }
        }
    }

    private void chooseColor() {
        int[] colors = { getResources().getColor(R.color.material_cyan), getResources().getColor(R.color.material_blue), getResources().getColor(R.color.material_indigo), getResources().getColor(R.color.material_deep_purple), getResources().getColor(R.color.material_purple), getResources().getColor(R.color.material_pink), getResources().getColor(R.color.material_red), getResources().getColor(R.color.material_orange), getResources().getColor(R.color.material_amber), getResources().getColor(R.color.material_yellow), getResources().getColor(R.color.material_lime), getResources().getColor(R.color.material_green), getResources().getColor(R.color.material_green_700), getResources().getColor(R.color.material_teal), getResources().getColor(R.color.material_brown), getResources().getColor(R.color.material_grey_600), getResources().getColor(R.color.material_grey_500), getResources().getColor(R.color.material_grey_300), Color.WHITE, Color.BLACK };
        ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        if (!ListenerUtil.mutListener.listen(4161)) {
            colorPickerDialog.initialize(R.string.color_picker_default_title, colors, 0, 4, colors.length);
        }
        if (!ListenerUtil.mutListener.listen(4162)) {
            colorPickerDialog.setSelectedColor(penColor);
        }
        if (!ListenerUtil.mutListener.listen(4172)) {
            colorPickerDialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {

                @Override
                public void onColorSelected(int color) {
                    if (!ListenerUtil.mutListener.listen(4163)) {
                        paintView.setColor(color);
                    }
                    if (!ListenerUtil.mutListener.listen(4164)) {
                        penColor = color;
                    }
                    if (!ListenerUtil.mutListener.listen(4165)) {
                        ConfigUtils.themeMenuItem(paletteItem, penColor);
                    }
                    if (!ListenerUtil.mutListener.listen(4171)) {
                        if (motionView.getSelectedEntity() != null) {
                            if (!ListenerUtil.mutListener.listen(4170)) {
                                if (motionView.getSelectedEntity() instanceof TextEntity) {
                                    TextEntity textEntity = (TextEntity) motionView.getSelectedEntity();
                                    if (!ListenerUtil.mutListener.listen(4167)) {
                                        textEntity.getLayer().getFont().setColor(penColor);
                                    }
                                    if (!ListenerUtil.mutListener.listen(4168)) {
                                        textEntity.updateEntity();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4169)) {
                                        motionView.invalidate();
                                    }
                                } else {
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4166)) {
                                setDrawMode(true);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4173)) {
            colorPickerDialog.show(getSupportFragmentManager(), DIALOG_TAG_COLOR_PICKER);
        }
    }

    private void renderImage() {
        if (!ListenerUtil.mutListener.listen(4174)) {
            logger.debug("renderImage");
        }
        if (!ListenerUtil.mutListener.listen(4175)) {
            if (saveSemaphore) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4176)) {
            saveSemaphore = true;
        }
        BitmapWorkerTaskParams bitmapParams = new BitmapWorkerTaskParams();
        if (!ListenerUtil.mutListener.listen(4177)) {
            bitmapParams.imageUri = this.imageUri;
        }
        if (!ListenerUtil.mutListener.listen(4178)) {
            bitmapParams.contentResolver = getContentResolver();
        }
        if (!ListenerUtil.mutListener.listen(4179)) {
            bitmapParams.orientation = this.orientation;
        }
        if (!ListenerUtil.mutListener.listen(4180)) {
            bitmapParams.flip = this.flip;
        }
        if (!ListenerUtil.mutListener.listen(4181)) {
            bitmapParams.exifOrientation = this.exifOrientation;
        }
        if (!ListenerUtil.mutListener.listen(4182)) {
            bitmapParams.exifFlip = this.exifFlip;
        }
        if (!ListenerUtil.mutListener.listen(4183)) {
            bitmapParams.mutable = true;
        }
        if (!ListenerUtil.mutListener.listen(4197)) {
            new BitmapWorkerTask(null) {

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(4184)) {
                        super.onPreExecute();
                    }
                    if (!ListenerUtil.mutListener.listen(4185)) {
                        GenericProgressDialog.newInstance(R.string.draw, R.string.saving_media).show(getSupportFragmentManager(), DIALOG_TAG_SAVING_IMAGE);
                    }
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    Canvas canvas = new Canvas(bitmap);
                    if (!ListenerUtil.mutListener.listen(4186)) {
                        motionView.renderOverlay(canvas);
                    }
                    if (!ListenerUtil.mutListener.listen(4187)) {
                        paintView.renderOverlay(canvas, clipWidth, clipHeight);
                    }
                    if (!ListenerUtil.mutListener.listen(4196)) {
                        new AsyncTask<Bitmap, Void, Boolean>() {

                            @Override
                            protected Boolean doInBackground(Bitmap... params) {
                                try {
                                    File output = new File(outputUri.getPath());
                                    FileOutputStream outputStream = new FileOutputStream(output);
                                    if (!ListenerUtil.mutListener.listen(4188)) {
                                        params[0].compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                    }
                                    if (!ListenerUtil.mutListener.listen(4189)) {
                                        outputStream.flush();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4190)) {
                                        outputStream.close();
                                    }
                                } catch (Exception e) {
                                    return false;
                                }
                                return true;
                            }

                            @Override
                            protected void onPostExecute(Boolean success) {
                                if (!ListenerUtil.mutListener.listen(4191)) {
                                    DialogUtil.dismissDialog(getSupportFragmentManager(), DIALOG_TAG_SAVING_IMAGE, true);
                                }
                                if (!ListenerUtil.mutListener.listen(4195)) {
                                    if (success) {
                                        if (!ListenerUtil.mutListener.listen(4193)) {
                                            setResult(RESULT_OK);
                                        }
                                        if (!ListenerUtil.mutListener.listen(4194)) {
                                            finish();
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(4192)) {
                                            Toast.makeText(ImagePaintActivity.this, R.string.error_saving_file, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }.execute(bitmap);
                    }
                }
            }.execute(bitmapParams);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(4198)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(4199)) {
            outState.putInt(KEY_PEN_COLOR, penColor);
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(4200)) {
            finish();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }
}
