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
package ch.threema.app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import androidx.appcompat.content.res.AppCompatResources;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import static ch.threema.app.utils.BitmapUtil.FLIP_NONE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BitmapWorkerTask extends AsyncTask<BitmapWorkerTaskParams, Void, Bitmap> {

    private static final Logger logger = LoggerFactory.getLogger(BitmapWorkerTask.class);

    private final WeakReference<ImageView> imageViewReference;

    public BitmapWorkerTask(ImageView imageView) {
        this.imageViewReference = new WeakReference<ImageView>(imageView);
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(BitmapWorkerTaskParams... params) {
        BitmapWorkerTaskParams bitmapParams = params[0];
        Bitmap bitmap = null;
        try {
            if (!ListenerUtil.mutListener.listen(49953)) {
                bitmap = decodeSampledBitmapFromUri(bitmapParams);
            }
        } catch (FileNotFoundException | IllegalStateException | SecurityException e) {
            if (!ListenerUtil.mutListener.listen(49952)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(49955)) {
            if (bitmap == null) {
                if (!ListenerUtil.mutListener.listen(49954)) {
                    bitmap = BitmapUtil.getBitmapFromVectorDrawable(AppCompatResources.getDrawable(ThreemaApplication.getAppContext(), R.drawable.ic_baseline_broken_image_24), Color.WHITE);
                }
            }
        }
        return bitmap;
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(49958)) {
            if (imageViewReference != null) {
                final ImageView imageView = imageViewReference.get();
                if (!ListenerUtil.mutListener.listen(49957)) {
                    if (imageView != null) {
                        if (!ListenerUtil.mutListener.listen(49956)) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                }
            }
        }
    }

    private Bitmap decodeSampledBitmapFromUri(BitmapWorkerTaskParams bitmapParams) throws FileNotFoundException, IllegalStateException, SecurityException {
        InputStream is = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        if ((ListenerUtil.mutListener.listen(49959) ? (bitmapParams.width != 0 || bitmapParams.height != 0) : (bitmapParams.width != 0 && bitmapParams.height != 0))) {
            if (!ListenerUtil.mutListener.listen(49960)) {
                // First decode with inJustDecodeBounds=true to check dimensions
                options.inJustDecodeBounds = true;
            }
            try {
                if (!ListenerUtil.mutListener.listen(49963)) {
                    is = bitmapParams.contentResolver.openInputStream(bitmapParams.imageUri);
                }
                if (!ListenerUtil.mutListener.listen(49964)) {
                    BitmapFactory.decodeStream(is, null, options);
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(49962)) {
                    if (is != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(49961)) {
                                is.close();
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            }
            boolean isRotated = (ListenerUtil.mutListener.listen(49968) ? ((bitmapParams.exifOrientation + bitmapParams.orientation) / 180) : (ListenerUtil.mutListener.listen(49967) ? ((bitmapParams.exifOrientation + bitmapParams.orientation) * 180) : (ListenerUtil.mutListener.listen(49966) ? ((bitmapParams.exifOrientation + bitmapParams.orientation) - 180) : (ListenerUtil.mutListener.listen(49965) ? ((bitmapParams.exifOrientation + bitmapParams.orientation) + 180) : ((bitmapParams.exifOrientation + bitmapParams.orientation) % 180))))) != 0;
            // width of container in which the resulting bitmap should be fitted
            int containerWidth = bitmapParams.width;
            int containerHeight = bitmapParams.height;
            int bitmapWidth = isRotated ? options.outHeight : options.outWidth;
            int bitmapHeight = isRotated ? options.outWidth : options.outHeight;
            if (!ListenerUtil.mutListener.listen(50038)) {
                if ((ListenerUtil.mutListener.listen(49979) ? ((ListenerUtil.mutListener.listen(49973) ? (bitmapWidth >= containerWidth) : (ListenerUtil.mutListener.listen(49972) ? (bitmapWidth <= containerWidth) : (ListenerUtil.mutListener.listen(49971) ? (bitmapWidth > containerWidth) : (ListenerUtil.mutListener.listen(49970) ? (bitmapWidth != containerWidth) : (ListenerUtil.mutListener.listen(49969) ? (bitmapWidth == containerWidth) : (bitmapWidth < containerWidth)))))) || (ListenerUtil.mutListener.listen(49978) ? (bitmapHeight >= containerHeight) : (ListenerUtil.mutListener.listen(49977) ? (bitmapHeight <= containerHeight) : (ListenerUtil.mutListener.listen(49976) ? (bitmapHeight > containerHeight) : (ListenerUtil.mutListener.listen(49975) ? (bitmapHeight != containerHeight) : (ListenerUtil.mutListener.listen(49974) ? (bitmapHeight == containerHeight) : (bitmapHeight < containerHeight))))))) : ((ListenerUtil.mutListener.listen(49973) ? (bitmapWidth >= containerWidth) : (ListenerUtil.mutListener.listen(49972) ? (bitmapWidth <= containerWidth) : (ListenerUtil.mutListener.listen(49971) ? (bitmapWidth > containerWidth) : (ListenerUtil.mutListener.listen(49970) ? (bitmapWidth != containerWidth) : (ListenerUtil.mutListener.listen(49969) ? (bitmapWidth == containerWidth) : (bitmapWidth < containerWidth)))))) && (ListenerUtil.mutListener.listen(49978) ? (bitmapHeight >= containerHeight) : (ListenerUtil.mutListener.listen(49977) ? (bitmapHeight <= containerHeight) : (ListenerUtil.mutListener.listen(49976) ? (bitmapHeight > containerHeight) : (ListenerUtil.mutListener.listen(49975) ? (bitmapHeight != containerHeight) : (ListenerUtil.mutListener.listen(49974) ? (bitmapHeight == containerHeight) : (bitmapHeight < containerHeight))))))))) {
                    // blow up
                    float ratioX = (ListenerUtil.mutListener.listen(50015) ? ((float) containerWidth % bitmapWidth) : (ListenerUtil.mutListener.listen(50014) ? ((float) containerWidth * bitmapWidth) : (ListenerUtil.mutListener.listen(50013) ? ((float) containerWidth - bitmapWidth) : (ListenerUtil.mutListener.listen(50012) ? ((float) containerWidth + bitmapWidth) : ((float) containerWidth / bitmapWidth)))));
                    float ratioY = (ListenerUtil.mutListener.listen(50019) ? ((float) containerHeight % bitmapHeight) : (ListenerUtil.mutListener.listen(50018) ? ((float) containerHeight * bitmapHeight) : (ListenerUtil.mutListener.listen(50017) ? ((float) containerHeight - bitmapHeight) : (ListenerUtil.mutListener.listen(50016) ? ((float) containerHeight + bitmapHeight) : ((float) containerHeight / bitmapHeight)))));
                    if (!ListenerUtil.mutListener.listen(50037)) {
                        if ((ListenerUtil.mutListener.listen(50024) ? (ratioX >= ratioY) : (ListenerUtil.mutListener.listen(50023) ? (ratioX <= ratioY) : (ListenerUtil.mutListener.listen(50022) ? (ratioX > ratioY) : (ListenerUtil.mutListener.listen(50021) ? (ratioX != ratioY) : (ListenerUtil.mutListener.listen(50020) ? (ratioX == ratioY) : (ratioX < ratioY))))))) {
                            if (!ListenerUtil.mutListener.listen(50031)) {
                                bitmapWidth = containerWidth;
                            }
                            if (!ListenerUtil.mutListener.listen(50036)) {
                                bitmapHeight = (int) ((ListenerUtil.mutListener.listen(50035) ? ((float) bitmapHeight % ratioX) : (ListenerUtil.mutListener.listen(50034) ? ((float) bitmapHeight / ratioX) : (ListenerUtil.mutListener.listen(50033) ? ((float) bitmapHeight - ratioX) : (ListenerUtil.mutListener.listen(50032) ? ((float) bitmapHeight + ratioX) : ((float) bitmapHeight * ratioX))))));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(50025)) {
                                bitmapHeight = containerHeight;
                            }
                            if (!ListenerUtil.mutListener.listen(50030)) {
                                bitmapWidth = (int) ((ListenerUtil.mutListener.listen(50029) ? ((float) bitmapWidth % ratioY) : (ListenerUtil.mutListener.listen(50028) ? ((float) bitmapWidth / ratioY) : (ListenerUtil.mutListener.listen(50027) ? ((float) bitmapWidth - ratioY) : (ListenerUtil.mutListener.listen(50026) ? ((float) bitmapWidth + ratioY) : ((float) bitmapWidth * ratioY))))));
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(49995)) {
                        if ((ListenerUtil.mutListener.listen(49984) ? (bitmapWidth >= containerWidth) : (ListenerUtil.mutListener.listen(49983) ? (bitmapWidth <= containerWidth) : (ListenerUtil.mutListener.listen(49982) ? (bitmapWidth < containerWidth) : (ListenerUtil.mutListener.listen(49981) ? (bitmapWidth != containerWidth) : (ListenerUtil.mutListener.listen(49980) ? (bitmapWidth == containerWidth) : (bitmapWidth > containerWidth))))))) {
                            if (!ListenerUtil.mutListener.listen(49993)) {
                                // scale down
                                bitmapHeight = (ListenerUtil.mutListener.listen(49992) ? (((ListenerUtil.mutListener.listen(49988) ? (bitmapHeight % containerWidth) : (ListenerUtil.mutListener.listen(49987) ? (bitmapHeight / containerWidth) : (ListenerUtil.mutListener.listen(49986) ? (bitmapHeight - containerWidth) : (ListenerUtil.mutListener.listen(49985) ? (bitmapHeight + containerWidth) : (bitmapHeight * containerWidth)))))) % bitmapWidth) : (ListenerUtil.mutListener.listen(49991) ? (((ListenerUtil.mutListener.listen(49988) ? (bitmapHeight % containerWidth) : (ListenerUtil.mutListener.listen(49987) ? (bitmapHeight / containerWidth) : (ListenerUtil.mutListener.listen(49986) ? (bitmapHeight - containerWidth) : (ListenerUtil.mutListener.listen(49985) ? (bitmapHeight + containerWidth) : (bitmapHeight * containerWidth)))))) * bitmapWidth) : (ListenerUtil.mutListener.listen(49990) ? (((ListenerUtil.mutListener.listen(49988) ? (bitmapHeight % containerWidth) : (ListenerUtil.mutListener.listen(49987) ? (bitmapHeight / containerWidth) : (ListenerUtil.mutListener.listen(49986) ? (bitmapHeight - containerWidth) : (ListenerUtil.mutListener.listen(49985) ? (bitmapHeight + containerWidth) : (bitmapHeight * containerWidth)))))) - bitmapWidth) : (ListenerUtil.mutListener.listen(49989) ? (((ListenerUtil.mutListener.listen(49988) ? (bitmapHeight % containerWidth) : (ListenerUtil.mutListener.listen(49987) ? (bitmapHeight / containerWidth) : (ListenerUtil.mutListener.listen(49986) ? (bitmapHeight - containerWidth) : (ListenerUtil.mutListener.listen(49985) ? (bitmapHeight + containerWidth) : (bitmapHeight * containerWidth)))))) + bitmapWidth) : (((ListenerUtil.mutListener.listen(49988) ? (bitmapHeight % containerWidth) : (ListenerUtil.mutListener.listen(49987) ? (bitmapHeight / containerWidth) : (ListenerUtil.mutListener.listen(49986) ? (bitmapHeight - containerWidth) : (ListenerUtil.mutListener.listen(49985) ? (bitmapHeight + containerWidth) : (bitmapHeight * containerWidth)))))) / bitmapWidth)))));
                            }
                            if (!ListenerUtil.mutListener.listen(49994)) {
                                bitmapWidth = containerWidth;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(50011)) {
                        if ((ListenerUtil.mutListener.listen(50000) ? (bitmapHeight >= containerHeight) : (ListenerUtil.mutListener.listen(49999) ? (bitmapHeight <= containerHeight) : (ListenerUtil.mutListener.listen(49998) ? (bitmapHeight < containerHeight) : (ListenerUtil.mutListener.listen(49997) ? (bitmapHeight != containerHeight) : (ListenerUtil.mutListener.listen(49996) ? (bitmapHeight == containerHeight) : (bitmapHeight > containerHeight))))))) {
                            if (!ListenerUtil.mutListener.listen(50009)) {
                                bitmapWidth = (ListenerUtil.mutListener.listen(50008) ? (((ListenerUtil.mutListener.listen(50004) ? (bitmapWidth % containerHeight) : (ListenerUtil.mutListener.listen(50003) ? (bitmapWidth / containerHeight) : (ListenerUtil.mutListener.listen(50002) ? (bitmapWidth - containerHeight) : (ListenerUtil.mutListener.listen(50001) ? (bitmapWidth + containerHeight) : (bitmapWidth * containerHeight)))))) % bitmapHeight) : (ListenerUtil.mutListener.listen(50007) ? (((ListenerUtil.mutListener.listen(50004) ? (bitmapWidth % containerHeight) : (ListenerUtil.mutListener.listen(50003) ? (bitmapWidth / containerHeight) : (ListenerUtil.mutListener.listen(50002) ? (bitmapWidth - containerHeight) : (ListenerUtil.mutListener.listen(50001) ? (bitmapWidth + containerHeight) : (bitmapWidth * containerHeight)))))) * bitmapHeight) : (ListenerUtil.mutListener.listen(50006) ? (((ListenerUtil.mutListener.listen(50004) ? (bitmapWidth % containerHeight) : (ListenerUtil.mutListener.listen(50003) ? (bitmapWidth / containerHeight) : (ListenerUtil.mutListener.listen(50002) ? (bitmapWidth - containerHeight) : (ListenerUtil.mutListener.listen(50001) ? (bitmapWidth + containerHeight) : (bitmapWidth * containerHeight)))))) - bitmapHeight) : (ListenerUtil.mutListener.listen(50005) ? (((ListenerUtil.mutListener.listen(50004) ? (bitmapWidth % containerHeight) : (ListenerUtil.mutListener.listen(50003) ? (bitmapWidth / containerHeight) : (ListenerUtil.mutListener.listen(50002) ? (bitmapWidth - containerHeight) : (ListenerUtil.mutListener.listen(50001) ? (bitmapWidth + containerHeight) : (bitmapWidth * containerHeight)))))) + bitmapHeight) : (((ListenerUtil.mutListener.listen(50004) ? (bitmapWidth % containerHeight) : (ListenerUtil.mutListener.listen(50003) ? (bitmapWidth / containerHeight) : (ListenerUtil.mutListener.listen(50002) ? (bitmapWidth - containerHeight) : (ListenerUtil.mutListener.listen(50001) ? (bitmapWidth + containerHeight) : (bitmapWidth * containerHeight)))))) / bitmapHeight)))));
                            }
                            if (!ListenerUtil.mutListener.listen(50010)) {
                                bitmapHeight = containerHeight;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(50039)) {
                options.inSampleSize = calculateInSampleSize(options, bitmapWidth, bitmapHeight);
            }
            if (!ListenerUtil.mutListener.listen(50040)) {
                // Decode bitmap with inSampleSize set
                options.inJustDecodeBounds = false;
            }
            if (!ListenerUtil.mutListener.listen(50041)) {
                options.inMutable = bitmapParams.mutable;
            }
            if (!ListenerUtil.mutListener.listen(50042)) {
                is = null;
            }
            Bitmap roughBitmap;
            try {
                if (!ListenerUtil.mutListener.listen(50045)) {
                    is = bitmapParams.contentResolver.openInputStream(bitmapParams.imageUri);
                }
                roughBitmap = BitmapFactory.decodeStream(is, null, options);
            } finally {
                if (!ListenerUtil.mutListener.listen(50044)) {
                    if (is != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(50043)) {
                                is.close();
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            }
            int outWidth = bitmapWidth;
            int outHeight = bitmapHeight;
            // resize bitmap to exact size
            if (roughBitmap != null) {
                if ((ListenerUtil.mutListener.listen(50046) ? (bitmapParams.exifOrientation != 0 && bitmapParams.exifFlip != FLIP_NONE) : (bitmapParams.exifOrientation != 0 || bitmapParams.exifFlip != FLIP_NONE))) {
                    roughBitmap = BitmapUtil.rotateBitmap(roughBitmap, bitmapParams.exifOrientation, bitmapParams.exifFlip);
                }
                if ((ListenerUtil.mutListener.listen(50047) ? (bitmapParams.orientation != 0 && bitmapParams.flip != FLIP_NONE) : (bitmapParams.orientation != 0 || bitmapParams.flip != FLIP_NONE))) {
                    roughBitmap = BitmapUtil.rotateBitmap(roughBitmap, bitmapParams.orientation, bitmapParams.flip);
                }
                try {
                    return Bitmap.createScaledBitmap(roughBitmap, outWidth, outHeight, true);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }
        if (!ListenerUtil.mutListener.listen(50048)) {
            options.inMutable = bitmapParams.mutable;
        }
        if (!ListenerUtil.mutListener.listen(50049)) {
            is = null;
        }
        try {
            if (!ListenerUtil.mutListener.listen(50052)) {
                is = bitmapParams.contentResolver.openInputStream(bitmapParams.imageUri);
            }
            Bitmap roughBitmap = BitmapFactory.decodeStream(is, null, options);
            if (!ListenerUtil.mutListener.listen(50059)) {
                if (roughBitmap != null) {
                    if (!ListenerUtil.mutListener.listen(50055)) {
                        if ((ListenerUtil.mutListener.listen(50053) ? (bitmapParams.exifOrientation != 0 && bitmapParams.exifFlip != FLIP_NONE) : (bitmapParams.exifOrientation != 0 || bitmapParams.exifFlip != FLIP_NONE))) {
                            if (!ListenerUtil.mutListener.listen(50054)) {
                                roughBitmap = BitmapUtil.rotateBitmap(roughBitmap, bitmapParams.exifOrientation, bitmapParams.exifFlip);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(50058)) {
                        if ((ListenerUtil.mutListener.listen(50056) ? (bitmapParams.orientation != 0 && bitmapParams.flip != FLIP_NONE) : (bitmapParams.orientation != 0 || bitmapParams.flip != FLIP_NONE))) {
                            if (!ListenerUtil.mutListener.listen(50057)) {
                                roughBitmap = BitmapUtil.rotateBitmap(roughBitmap, bitmapParams.orientation, bitmapParams.flip);
                            }
                        }
                    }
                }
            }
            return roughBitmap;
        } finally {
            if (!ListenerUtil.mutListener.listen(50051)) {
                if (is != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(50050)) {
                            is.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (!ListenerUtil.mutListener.listen(50080)) {
            if ((ListenerUtil.mutListener.listen(50070) ? ((ListenerUtil.mutListener.listen(50064) ? (height >= reqHeight) : (ListenerUtil.mutListener.listen(50063) ? (height <= reqHeight) : (ListenerUtil.mutListener.listen(50062) ? (height < reqHeight) : (ListenerUtil.mutListener.listen(50061) ? (height != reqHeight) : (ListenerUtil.mutListener.listen(50060) ? (height == reqHeight) : (height > reqHeight)))))) && (ListenerUtil.mutListener.listen(50069) ? (width >= reqWidth) : (ListenerUtil.mutListener.listen(50068) ? (width <= reqWidth) : (ListenerUtil.mutListener.listen(50067) ? (width < reqWidth) : (ListenerUtil.mutListener.listen(50066) ? (width != reqWidth) : (ListenerUtil.mutListener.listen(50065) ? (width == reqWidth) : (width > reqWidth))))))) : ((ListenerUtil.mutListener.listen(50064) ? (height >= reqHeight) : (ListenerUtil.mutListener.listen(50063) ? (height <= reqHeight) : (ListenerUtil.mutListener.listen(50062) ? (height < reqHeight) : (ListenerUtil.mutListener.listen(50061) ? (height != reqHeight) : (ListenerUtil.mutListener.listen(50060) ? (height == reqHeight) : (height > reqHeight)))))) || (ListenerUtil.mutListener.listen(50069) ? (width >= reqWidth) : (ListenerUtil.mutListener.listen(50068) ? (width <= reqWidth) : (ListenerUtil.mutListener.listen(50067) ? (width < reqWidth) : (ListenerUtil.mutListener.listen(50066) ? (width != reqWidth) : (ListenerUtil.mutListener.listen(50065) ? (width == reqWidth) : (width > reqWidth))))))))) {
                // Calculate ratios of height and width to requested height and width
                final int heightRatio = Math.round((ListenerUtil.mutListener.listen(50074) ? ((float) height % (float) reqHeight) : (ListenerUtil.mutListener.listen(50073) ? ((float) height * (float) reqHeight) : (ListenerUtil.mutListener.listen(50072) ? ((float) height - (float) reqHeight) : (ListenerUtil.mutListener.listen(50071) ? ((float) height + (float) reqHeight) : ((float) height / (float) reqHeight))))));
                final int widthRatio = Math.round((ListenerUtil.mutListener.listen(50078) ? ((float) width % (float) reqWidth) : (ListenerUtil.mutListener.listen(50077) ? ((float) width * (float) reqWidth) : (ListenerUtil.mutListener.listen(50076) ? ((float) width - (float) reqWidth) : (ListenerUtil.mutListener.listen(50075) ? ((float) width + (float) reqWidth) : ((float) width / (float) reqWidth))))));
                if (!ListenerUtil.mutListener.listen(50079)) {
                    // requested height and width.
                    inSampleSize = Math.min(heightRatio, widthRatio);
                }
            }
        }
        return inSampleSize;
    }
}
