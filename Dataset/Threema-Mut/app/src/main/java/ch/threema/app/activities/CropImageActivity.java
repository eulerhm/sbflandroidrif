/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import com.theartofdev.edmodo.cropper.CropImageView;
import androidx.appcompat.widget.Toolbar;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CropImageActivity extends ThreemaToolbarActivity {

    public static final String EXTRA_ASPECT_X = "ax";

    public static final String EXTRA_ASPECT_Y = "ay";

    public static final String EXTRA_MAX_X = "mx";

    public static final String EXTRA_MAX_Y = "my";

    public static final String EXTRA_OVAL = "oval";

    public static final String FORCE_DARK_THEME = "darkTheme";

    public static final int REQUEST_CROP = 7732;

    private int aspectX;

    private int aspectY;

    private int orientation, exifOrientation, flip, exifFlip;

    // Output image size
    private int maxX;

    private int maxY;

    private boolean oval = false;

    private Uri sourceUri;

    private Uri saveUri;

    private boolean isSaving;

    private CropImageView imageView;

    @Override
    public void onCreate(Bundle icicle) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (!ListenerUtil.mutListener.listen(2329)) {
            if ((ListenerUtil.mutListener.listen(2326) ? (extras != null || extras.getBoolean(FORCE_DARK_THEME, false)) : (extras != null && extras.getBoolean(FORCE_DARK_THEME, false)))) {
                if (!ListenerUtil.mutListener.listen(2328)) {
                    ConfigUtils.configureActivityTheme(this, ConfigUtils.THEME_DARK);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2327)) {
                    ConfigUtils.configureActivityTheme(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2330)) {
            super.onCreate(icicle);
        }
        Toolbar toolbar = findViewById(R.id.crop_toolbar);
        if (!ListenerUtil.mutListener.listen(2331)) {
            setSupportActionBar(toolbar);
        }
        View cancelActionView = findViewById(R.id.action_cancel);
        if (!ListenerUtil.mutListener.listen(2334)) {
            cancelActionView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2332)) {
                        setResult(RESULT_CANCELED);
                    }
                    if (!ListenerUtil.mutListener.listen(2333)) {
                        finish();
                    }
                }
            });
        }
        View doneActionView = findViewById(R.id.action_done);
        if (!ListenerUtil.mutListener.listen(2336)) {
            doneActionView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2335)) {
                        onSaveClicked();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2337)) {
            setupFromIntent();
        }
        if (!ListenerUtil.mutListener.listen(2338)) {
            imageView = findViewById(R.id.crop_image);
        }
        if (!ListenerUtil.mutListener.listen(2352)) {
            if ((ListenerUtil.mutListener.listen(2349) ? ((ListenerUtil.mutListener.listen(2343) ? (aspectX >= 0) : (ListenerUtil.mutListener.listen(2342) ? (aspectX <= 0) : (ListenerUtil.mutListener.listen(2341) ? (aspectX > 0) : (ListenerUtil.mutListener.listen(2340) ? (aspectX < 0) : (ListenerUtil.mutListener.listen(2339) ? (aspectX == 0) : (aspectX != 0)))))) || (ListenerUtil.mutListener.listen(2348) ? (aspectY >= 0) : (ListenerUtil.mutListener.listen(2347) ? (aspectY <= 0) : (ListenerUtil.mutListener.listen(2346) ? (aspectY > 0) : (ListenerUtil.mutListener.listen(2345) ? (aspectY < 0) : (ListenerUtil.mutListener.listen(2344) ? (aspectY == 0) : (aspectY != 0))))))) : ((ListenerUtil.mutListener.listen(2343) ? (aspectX >= 0) : (ListenerUtil.mutListener.listen(2342) ? (aspectX <= 0) : (ListenerUtil.mutListener.listen(2341) ? (aspectX > 0) : (ListenerUtil.mutListener.listen(2340) ? (aspectX < 0) : (ListenerUtil.mutListener.listen(2339) ? (aspectX == 0) : (aspectX != 0)))))) && (ListenerUtil.mutListener.listen(2348) ? (aspectY >= 0) : (ListenerUtil.mutListener.listen(2347) ? (aspectY <= 0) : (ListenerUtil.mutListener.listen(2346) ? (aspectY > 0) : (ListenerUtil.mutListener.listen(2345) ? (aspectY < 0) : (ListenerUtil.mutListener.listen(2344) ? (aspectY == 0) : (aspectY != 0))))))))) {
                if (!ListenerUtil.mutListener.listen(2350)) {
                    imageView.setAspectRatio(aspectX, aspectY);
                }
                if (!ListenerUtil.mutListener.listen(2351)) {
                    imageView.setFixedAspectRatio(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2379)) {
            if ((ListenerUtil.mutListener.listen(2365) ? ((ListenerUtil.mutListener.listen(2364) ? ((ListenerUtil.mutListener.listen(2358) ? ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) && flip != BitmapUtil.FLIP_NONE) : ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) || flip != BitmapUtil.FLIP_NONE)) && (ListenerUtil.mutListener.listen(2363) ? (exifOrientation >= 0) : (ListenerUtil.mutListener.listen(2362) ? (exifOrientation <= 0) : (ListenerUtil.mutListener.listen(2361) ? (exifOrientation > 0) : (ListenerUtil.mutListener.listen(2360) ? (exifOrientation < 0) : (ListenerUtil.mutListener.listen(2359) ? (exifOrientation == 0) : (exifOrientation != 0))))))) : ((ListenerUtil.mutListener.listen(2358) ? ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) && flip != BitmapUtil.FLIP_NONE) : ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) || flip != BitmapUtil.FLIP_NONE)) || (ListenerUtil.mutListener.listen(2363) ? (exifOrientation >= 0) : (ListenerUtil.mutListener.listen(2362) ? (exifOrientation <= 0) : (ListenerUtil.mutListener.listen(2361) ? (exifOrientation > 0) : (ListenerUtil.mutListener.listen(2360) ? (exifOrientation < 0) : (ListenerUtil.mutListener.listen(2359) ? (exifOrientation == 0) : (exifOrientation != 0)))))))) && exifFlip != BitmapUtil.FLIP_NONE) : ((ListenerUtil.mutListener.listen(2364) ? ((ListenerUtil.mutListener.listen(2358) ? ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) && flip != BitmapUtil.FLIP_NONE) : ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) || flip != BitmapUtil.FLIP_NONE)) && (ListenerUtil.mutListener.listen(2363) ? (exifOrientation >= 0) : (ListenerUtil.mutListener.listen(2362) ? (exifOrientation <= 0) : (ListenerUtil.mutListener.listen(2361) ? (exifOrientation > 0) : (ListenerUtil.mutListener.listen(2360) ? (exifOrientation < 0) : (ListenerUtil.mutListener.listen(2359) ? (exifOrientation == 0) : (exifOrientation != 0))))))) : ((ListenerUtil.mutListener.listen(2358) ? ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) && flip != BitmapUtil.FLIP_NONE) : ((ListenerUtil.mutListener.listen(2357) ? (orientation >= 0) : (ListenerUtil.mutListener.listen(2356) ? (orientation <= 0) : (ListenerUtil.mutListener.listen(2355) ? (orientation > 0) : (ListenerUtil.mutListener.listen(2354) ? (orientation < 0) : (ListenerUtil.mutListener.listen(2353) ? (orientation == 0) : (orientation != 0)))))) || flip != BitmapUtil.FLIP_NONE)) || (ListenerUtil.mutListener.listen(2363) ? (exifOrientation >= 0) : (ListenerUtil.mutListener.listen(2362) ? (exifOrientation <= 0) : (ListenerUtil.mutListener.listen(2361) ? (exifOrientation > 0) : (ListenerUtil.mutListener.listen(2360) ? (exifOrientation < 0) : (ListenerUtil.mutListener.listen(2359) ? (exifOrientation == 0) : (exifOrientation != 0)))))))) || exifFlip != BitmapUtil.FLIP_NONE))) {
                if (!ListenerUtil.mutListener.listen(2378)) {
                    imageView.setOnSetImageUriCompleteListener(new CropImageView.OnSetImageUriCompleteListener() {

                        @Override
                        public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
                            if (!ListenerUtil.mutListener.listen(2367)) {
                                if ((exifFlip & BitmapUtil.FLIP_HORIZONTAL) == BitmapUtil.FLIP_HORIZONTAL) {
                                    if (!ListenerUtil.mutListener.listen(2366)) {
                                        imageView.flipImageHorizontally();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2369)) {
                                if ((exifFlip & BitmapUtil.FLIP_VERTICAL) == BitmapUtil.FLIP_VERTICAL) {
                                    if (!ListenerUtil.mutListener.listen(2368)) {
                                        imageView.flipImageVertically();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2371)) {
                                // Bug Workaround: CropImageView accounts for exif rotation but NOT if there's also a flip
                                if (exifFlip != BitmapUtil.FLIP_NONE) {
                                    if (!ListenerUtil.mutListener.listen(2370)) {
                                        imageView.rotateImage(exifOrientation);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2373)) {
                                if ((flip & BitmapUtil.FLIP_HORIZONTAL) == BitmapUtil.FLIP_HORIZONTAL) {
                                    if (!ListenerUtil.mutListener.listen(2372)) {
                                        imageView.flipImageHorizontally();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2375)) {
                                if ((flip & BitmapUtil.FLIP_VERTICAL) == BitmapUtil.FLIP_VERTICAL) {
                                    if (!ListenerUtil.mutListener.listen(2374)) {
                                        imageView.flipImageVertically();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2377)) {
                                if (orientation != 0) {
                                    if (!ListenerUtil.mutListener.listen(2376)) {
                                        imageView.rotateImage(orientation);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2380)) {
            imageView.setImageUriAsync(sourceUri);
        }
        if (!ListenerUtil.mutListener.listen(2381)) {
            imageView.setCropShape(oval ? CropImageView.CropShape.OVAL : CropImageView.CropShape.RECTANGLE);
        }
        if (!ListenerUtil.mutListener.listen(2383)) {
            imageView.setOnCropImageCompleteListener(new CropImageView.OnCropImageCompleteListener() {

                @Override
                public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
                    if (!ListenerUtil.mutListener.listen(2382)) {
                        cropCompleted();
                    }
                }
            });
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_crop;
    }

    private void cropCompleted() {
        if (!ListenerUtil.mutListener.listen(2384)) {
            setResult(RESULT_OK, new Intent().putExtra(MediaStore.EXTRA_OUTPUT, saveUri));
        }
        if (!ListenerUtil.mutListener.listen(2385)) {
            finish();
        }
    }

    private void setupFromIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (!ListenerUtil.mutListener.listen(2396)) {
            if (extras != null) {
                if (!ListenerUtil.mutListener.listen(2386)) {
                    aspectX = extras.getInt(EXTRA_ASPECT_X);
                }
                if (!ListenerUtil.mutListener.listen(2387)) {
                    aspectY = extras.getInt(EXTRA_ASPECT_Y);
                }
                if (!ListenerUtil.mutListener.listen(2388)) {
                    maxX = extras.getInt(EXTRA_MAX_X);
                }
                if (!ListenerUtil.mutListener.listen(2389)) {
                    maxY = extras.getInt(EXTRA_MAX_Y);
                }
                if (!ListenerUtil.mutListener.listen(2390)) {
                    oval = extras.getBoolean(EXTRA_OVAL, false);
                }
                if (!ListenerUtil.mutListener.listen(2391)) {
                    saveUri = extras.getParcelable(MediaStore.EXTRA_OUTPUT);
                }
                if (!ListenerUtil.mutListener.listen(2392)) {
                    orientation = extras.getInt(ThreemaApplication.EXTRA_ORIENTATION, 0);
                }
                if (!ListenerUtil.mutListener.listen(2393)) {
                    flip = extras.getInt(ThreemaApplication.EXTRA_FLIP, BitmapUtil.FLIP_NONE);
                }
                if (!ListenerUtil.mutListener.listen(2394)) {
                    exifOrientation = extras.getInt(ThreemaApplication.EXTRA_EXIF_ORIENTATION, 0);
                }
                if (!ListenerUtil.mutListener.listen(2395)) {
                    exifFlip = extras.getInt(ThreemaApplication.EXTRA_EXIF_FLIP, BitmapUtil.FLIP_NONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2397)) {
            sourceUri = intent.getData();
        }
    }

    private void onSaveClicked() {
        if (!ListenerUtil.mutListener.listen(2399)) {
            if ((ListenerUtil.mutListener.listen(2398) ? (imageView == null && isSaving) : (imageView == null || isSaving))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2400)) {
            isSaving = true;
        }
        if (!ListenerUtil.mutListener.listen(2414)) {
            if ((ListenerUtil.mutListener.listen(2411) ? ((ListenerUtil.mutListener.listen(2405) ? (maxX >= 0) : (ListenerUtil.mutListener.listen(2404) ? (maxX <= 0) : (ListenerUtil.mutListener.listen(2403) ? (maxX > 0) : (ListenerUtil.mutListener.listen(2402) ? (maxX < 0) : (ListenerUtil.mutListener.listen(2401) ? (maxX == 0) : (maxX != 0)))))) || (ListenerUtil.mutListener.listen(2410) ? (maxY >= 0) : (ListenerUtil.mutListener.listen(2409) ? (maxY <= 0) : (ListenerUtil.mutListener.listen(2408) ? (maxY > 0) : (ListenerUtil.mutListener.listen(2407) ? (maxY < 0) : (ListenerUtil.mutListener.listen(2406) ? (maxY == 0) : (maxY != 0))))))) : ((ListenerUtil.mutListener.listen(2405) ? (maxX >= 0) : (ListenerUtil.mutListener.listen(2404) ? (maxX <= 0) : (ListenerUtil.mutListener.listen(2403) ? (maxX > 0) : (ListenerUtil.mutListener.listen(2402) ? (maxX < 0) : (ListenerUtil.mutListener.listen(2401) ? (maxX == 0) : (maxX != 0)))))) && (ListenerUtil.mutListener.listen(2410) ? (maxY >= 0) : (ListenerUtil.mutListener.listen(2409) ? (maxY <= 0) : (ListenerUtil.mutListener.listen(2408) ? (maxY > 0) : (ListenerUtil.mutListener.listen(2407) ? (maxY < 0) : (ListenerUtil.mutListener.listen(2406) ? (maxY == 0) : (maxY != 0))))))))) {
                if (!ListenerUtil.mutListener.listen(2413)) {
                    imageView.saveCroppedImageAsync(saveUri, Bitmap.CompressFormat.PNG, 100, maxX, maxY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2412)) {
                    imageView.saveCroppedImageAsync(saveUri, Bitmap.CompressFormat.PNG, 100);
                }
            }
        }
    }
}
