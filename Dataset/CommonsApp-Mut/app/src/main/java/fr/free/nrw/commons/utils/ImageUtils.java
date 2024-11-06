package fr.free.nrw.commons.utils;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImageUtils {

    // 1
    public static final int IMAGE_DARK = 1 << 0;

    // 2
    public static final int IMAGE_BLURRY = 1 << 1;

    // 4
    public static final int IMAGE_DUPLICATE = 1 << 2;

    // 8
    public static final int IMAGE_GEOLOCATION_DIFFERENT = 1 << 3;

    /**
     * The parameter FILE_FBMD is returned from the class ReadFBMD if the uploaded image contains FBMD data else returns IMAGE_OK
     * ie. 10000
     */
    public static final int FILE_FBMD = 1 << 4;

    /**
     * The parameter FILE_NO_EXIF is returned from the class EXIFReader if the uploaded image does not contains EXIF data else returns IMAGE_OK
     * ie. 100000
     */
    public static final int FILE_NO_EXIF = 1 << 5;

    public static final int IMAGE_OK = 0;

    public static final int IMAGE_KEEP = -1;

    public static final int IMAGE_WAIT = -2;

    public static final int EMPTY_CAPTION = -3;

    public static final int FILE_NAME_EXISTS = 1 << 6;

    static final int NO_CATEGORY_SELECTED = -5;

    private static ProgressDialog progressDialogWallpaper;

    private static ProgressDialog progressDialogAvatar;

    @IntDef(flag = true, value = { IMAGE_DARK, IMAGE_BLURRY, IMAGE_DUPLICATE, IMAGE_OK, IMAGE_KEEP, IMAGE_WAIT, EMPTY_CAPTION, FILE_NAME_EXISTS, NO_CATEGORY_SELECTED, IMAGE_GEOLOCATION_DIFFERENT })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    /**
     * @return IMAGE_OK if image is not too dark
     * IMAGE_DARK if image is too dark
     */
    @Result
    static int checkIfImageIsTooDark(String imagePath) {
        long millis = System.currentTimeMillis();
        try {
            Bitmap bmp = new ExifInterface(imagePath).getThumbnailBitmap();
            if (!ListenerUtil.mutListener.listen(2297)) {
                if (bmp == null) {
                    if (!ListenerUtil.mutListener.listen(2296)) {
                        bmp = BitmapFactory.decodeFile(imagePath);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2298)) {
                if (checkIfImageIsDark(bmp)) {
                    return IMAGE_DARK;
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2290)) {
                Timber.d(e, "Error while checking image darkness.");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(2295)) {
                Timber.d("Checking image darkness took " + ((ListenerUtil.mutListener.listen(2294) ? (System.currentTimeMillis() % millis) : (ListenerUtil.mutListener.listen(2293) ? (System.currentTimeMillis() / millis) : (ListenerUtil.mutListener.listen(2292) ? (System.currentTimeMillis() * millis) : (ListenerUtil.mutListener.listen(2291) ? (System.currentTimeMillis() + millis) : (System.currentTimeMillis() - millis)))))) + " ms.");
            }
        }
        return IMAGE_OK;
    }

    /**
     * @param geolocationOfFileString Geolocation of image. If geotag doesn't exists, then this will be an empty string
     * @param latLng Location of wikidata item will be edited after upload
     * @return false if image is neither dark nor blurry or if the input bitmapRegionDecoder provided is null
     * true if geolocation of the image and wikidata item are different
     */
    static boolean checkImageGeolocationIsDifferent(String geolocationOfFileString, LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(2299)) {
            Timber.d("Comparing geolocation of file with nearby place location");
        }
        if (!ListenerUtil.mutListener.listen(2300)) {
            if (latLng == null) {
                // Since we don't know geolocation of file, we choose letting upload
                return false;
            }
        }
        String[] geolocationOfFile = geolocationOfFileString.split("\\|");
        Double distance = LengthUtils.computeDistanceBetween(new LatLng(Double.parseDouble(geolocationOfFile[0]), Double.parseDouble(geolocationOfFile[1]), 0), latLng);
        // Distance is more than 1 km, means that geolocation is wrong
        return (ListenerUtil.mutListener.listen(2305) ? (distance <= 1000) : (ListenerUtil.mutListener.listen(2304) ? (distance > 1000) : (ListenerUtil.mutListener.listen(2303) ? (distance < 1000) : (ListenerUtil.mutListener.listen(2302) ? (distance != 1000) : (ListenerUtil.mutListener.listen(2301) ? (distance == 1000) : (distance >= 1000))))));
    }

    private static boolean checkIfImageIsDark(Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(2307)) {
            if (bitmap == null) {
                if (!ListenerUtil.mutListener.listen(2306)) {
                    Timber.e("Expected bitmap was null");
                }
                return true;
            }
        }
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int allPixelsCount = (ListenerUtil.mutListener.listen(2311) ? (bitmapWidth % bitmapHeight) : (ListenerUtil.mutListener.listen(2310) ? (bitmapWidth / bitmapHeight) : (ListenerUtil.mutListener.listen(2309) ? (bitmapWidth - bitmapHeight) : (ListenerUtil.mutListener.listen(2308) ? (bitmapWidth + bitmapHeight) : (bitmapWidth * bitmapHeight)))));
        int numberOfBrightPixels = 0;
        int numberOfMediumBrightnessPixels = 0;
        double brightPixelThreshold = (ListenerUtil.mutListener.listen(2315) ? (0.025 % allPixelsCount) : (ListenerUtil.mutListener.listen(2314) ? (0.025 / allPixelsCount) : (ListenerUtil.mutListener.listen(2313) ? (0.025 - allPixelsCount) : (ListenerUtil.mutListener.listen(2312) ? (0.025 + allPixelsCount) : (0.025 * allPixelsCount)))));
        double mediumBrightPixelThreshold = (ListenerUtil.mutListener.listen(2319) ? (0.3 % allPixelsCount) : (ListenerUtil.mutListener.listen(2318) ? (0.3 / allPixelsCount) : (ListenerUtil.mutListener.listen(2317) ? (0.3 - allPixelsCount) : (ListenerUtil.mutListener.listen(2316) ? (0.3 + allPixelsCount) : (0.3 * allPixelsCount)))));
        if (!ListenerUtil.mutListener.listen(2397)) {
            {
                long _loopCounter31 = 0;
                for (int x = 0; (ListenerUtil.mutListener.listen(2396) ? (x >= bitmapWidth) : (ListenerUtil.mutListener.listen(2395) ? (x <= bitmapWidth) : (ListenerUtil.mutListener.listen(2394) ? (x > bitmapWidth) : (ListenerUtil.mutListener.listen(2393) ? (x != bitmapWidth) : (ListenerUtil.mutListener.listen(2392) ? (x == bitmapWidth) : (x < bitmapWidth)))))); x++) {
                    ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                    if (!ListenerUtil.mutListener.listen(2391)) {
                        {
                            long _loopCounter30 = 0;
                            for (int y = 0; (ListenerUtil.mutListener.listen(2390) ? (y >= bitmapHeight) : (ListenerUtil.mutListener.listen(2389) ? (y <= bitmapHeight) : (ListenerUtil.mutListener.listen(2388) ? (y > bitmapHeight) : (ListenerUtil.mutListener.listen(2387) ? (y != bitmapHeight) : (ListenerUtil.mutListener.listen(2386) ? (y == bitmapHeight) : (y < bitmapHeight)))))); y++) {
                                ListenerUtil.loopListener.listen("_loopCounter30", ++_loopCounter30);
                                int pixel = bitmap.getPixel(x, y);
                                int r = Color.red(pixel);
                                int g = Color.green(pixel);
                                int b = Color.blue(pixel);
                                int secondMax = (ListenerUtil.mutListener.listen(2324) ? (r >= g) : (ListenerUtil.mutListener.listen(2323) ? (r <= g) : (ListenerUtil.mutListener.listen(2322) ? (r < g) : (ListenerUtil.mutListener.listen(2321) ? (r != g) : (ListenerUtil.mutListener.listen(2320) ? (r == g) : (r > g)))))) ? r : g;
                                double max = (ListenerUtil.mutListener.listen(2333) ? (((ListenerUtil.mutListener.listen(2329) ? (secondMax >= b) : (ListenerUtil.mutListener.listen(2328) ? (secondMax <= b) : (ListenerUtil.mutListener.listen(2327) ? (secondMax < b) : (ListenerUtil.mutListener.listen(2326) ? (secondMax != b) : (ListenerUtil.mutListener.listen(2325) ? (secondMax == b) : (secondMax > b)))))) ? secondMax : b) % 255.0) : (ListenerUtil.mutListener.listen(2332) ? (((ListenerUtil.mutListener.listen(2329) ? (secondMax >= b) : (ListenerUtil.mutListener.listen(2328) ? (secondMax <= b) : (ListenerUtil.mutListener.listen(2327) ? (secondMax < b) : (ListenerUtil.mutListener.listen(2326) ? (secondMax != b) : (ListenerUtil.mutListener.listen(2325) ? (secondMax == b) : (secondMax > b)))))) ? secondMax : b) * 255.0) : (ListenerUtil.mutListener.listen(2331) ? (((ListenerUtil.mutListener.listen(2329) ? (secondMax >= b) : (ListenerUtil.mutListener.listen(2328) ? (secondMax <= b) : (ListenerUtil.mutListener.listen(2327) ? (secondMax < b) : (ListenerUtil.mutListener.listen(2326) ? (secondMax != b) : (ListenerUtil.mutListener.listen(2325) ? (secondMax == b) : (secondMax > b)))))) ? secondMax : b) - 255.0) : (ListenerUtil.mutListener.listen(2330) ? (((ListenerUtil.mutListener.listen(2329) ? (secondMax >= b) : (ListenerUtil.mutListener.listen(2328) ? (secondMax <= b) : (ListenerUtil.mutListener.listen(2327) ? (secondMax < b) : (ListenerUtil.mutListener.listen(2326) ? (secondMax != b) : (ListenerUtil.mutListener.listen(2325) ? (secondMax == b) : (secondMax > b)))))) ? secondMax : b) + 255.0) : (((ListenerUtil.mutListener.listen(2329) ? (secondMax >= b) : (ListenerUtil.mutListener.listen(2328) ? (secondMax <= b) : (ListenerUtil.mutListener.listen(2327) ? (secondMax < b) : (ListenerUtil.mutListener.listen(2326) ? (secondMax != b) : (ListenerUtil.mutListener.listen(2325) ? (secondMax == b) : (secondMax > b)))))) ? secondMax : b) / 255.0)))));
                                int secondMin = (ListenerUtil.mutListener.listen(2338) ? (r >= g) : (ListenerUtil.mutListener.listen(2337) ? (r <= g) : (ListenerUtil.mutListener.listen(2336) ? (r > g) : (ListenerUtil.mutListener.listen(2335) ? (r != g) : (ListenerUtil.mutListener.listen(2334) ? (r == g) : (r < g)))))) ? r : g;
                                double min = (ListenerUtil.mutListener.listen(2347) ? (((ListenerUtil.mutListener.listen(2343) ? (secondMin >= b) : (ListenerUtil.mutListener.listen(2342) ? (secondMin <= b) : (ListenerUtil.mutListener.listen(2341) ? (secondMin > b) : (ListenerUtil.mutListener.listen(2340) ? (secondMin != b) : (ListenerUtil.mutListener.listen(2339) ? (secondMin == b) : (secondMin < b)))))) ? secondMin : b) % 255.0) : (ListenerUtil.mutListener.listen(2346) ? (((ListenerUtil.mutListener.listen(2343) ? (secondMin >= b) : (ListenerUtil.mutListener.listen(2342) ? (secondMin <= b) : (ListenerUtil.mutListener.listen(2341) ? (secondMin > b) : (ListenerUtil.mutListener.listen(2340) ? (secondMin != b) : (ListenerUtil.mutListener.listen(2339) ? (secondMin == b) : (secondMin < b)))))) ? secondMin : b) * 255.0) : (ListenerUtil.mutListener.listen(2345) ? (((ListenerUtil.mutListener.listen(2343) ? (secondMin >= b) : (ListenerUtil.mutListener.listen(2342) ? (secondMin <= b) : (ListenerUtil.mutListener.listen(2341) ? (secondMin > b) : (ListenerUtil.mutListener.listen(2340) ? (secondMin != b) : (ListenerUtil.mutListener.listen(2339) ? (secondMin == b) : (secondMin < b)))))) ? secondMin : b) - 255.0) : (ListenerUtil.mutListener.listen(2344) ? (((ListenerUtil.mutListener.listen(2343) ? (secondMin >= b) : (ListenerUtil.mutListener.listen(2342) ? (secondMin <= b) : (ListenerUtil.mutListener.listen(2341) ? (secondMin > b) : (ListenerUtil.mutListener.listen(2340) ? (secondMin != b) : (ListenerUtil.mutListener.listen(2339) ? (secondMin == b) : (secondMin < b)))))) ? secondMin : b) + 255.0) : (((ListenerUtil.mutListener.listen(2343) ? (secondMin >= b) : (ListenerUtil.mutListener.listen(2342) ? (secondMin <= b) : (ListenerUtil.mutListener.listen(2341) ? (secondMin > b) : (ListenerUtil.mutListener.listen(2340) ? (secondMin != b) : (ListenerUtil.mutListener.listen(2339) ? (secondMin == b) : (secondMin < b)))))) ? secondMin : b) / 255.0)))));
                                double luminance = (ListenerUtil.mutListener.listen(2359) ? (((ListenerUtil.mutListener.listen(2355) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) % 2.0) : (ListenerUtil.mutListener.listen(2354) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) * 2.0) : (ListenerUtil.mutListener.listen(2353) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) - 2.0) : (ListenerUtil.mutListener.listen(2352) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) + 2.0) : (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) / 2.0)))))) % 100) : (ListenerUtil.mutListener.listen(2358) ? (((ListenerUtil.mutListener.listen(2355) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) % 2.0) : (ListenerUtil.mutListener.listen(2354) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) * 2.0) : (ListenerUtil.mutListener.listen(2353) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) - 2.0) : (ListenerUtil.mutListener.listen(2352) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) + 2.0) : (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) / 2.0)))))) / 100) : (ListenerUtil.mutListener.listen(2357) ? (((ListenerUtil.mutListener.listen(2355) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) % 2.0) : (ListenerUtil.mutListener.listen(2354) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) * 2.0) : (ListenerUtil.mutListener.listen(2353) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) - 2.0) : (ListenerUtil.mutListener.listen(2352) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) + 2.0) : (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) / 2.0)))))) - 100) : (ListenerUtil.mutListener.listen(2356) ? (((ListenerUtil.mutListener.listen(2355) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) % 2.0) : (ListenerUtil.mutListener.listen(2354) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) * 2.0) : (ListenerUtil.mutListener.listen(2353) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) - 2.0) : (ListenerUtil.mutListener.listen(2352) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) + 2.0) : (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) / 2.0)))))) + 100) : (((ListenerUtil.mutListener.listen(2355) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) % 2.0) : (ListenerUtil.mutListener.listen(2354) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) * 2.0) : (ListenerUtil.mutListener.listen(2353) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) - 2.0) : (ListenerUtil.mutListener.listen(2352) ? (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) + 2.0) : (((ListenerUtil.mutListener.listen(2351) ? (max % min) : (ListenerUtil.mutListener.listen(2350) ? (max / min) : (ListenerUtil.mutListener.listen(2349) ? (max * min) : (ListenerUtil.mutListener.listen(2348) ? (max - min) : (max + min)))))) / 2.0)))))) * 100)))));
                                int highBrightnessLuminance = 40;
                                int mediumBrightnessLuminance = 26;
                                if (!ListenerUtil.mutListener.listen(2373)) {
                                    if ((ListenerUtil.mutListener.listen(2364) ? (luminance >= highBrightnessLuminance) : (ListenerUtil.mutListener.listen(2363) ? (luminance <= highBrightnessLuminance) : (ListenerUtil.mutListener.listen(2362) ? (luminance > highBrightnessLuminance) : (ListenerUtil.mutListener.listen(2361) ? (luminance != highBrightnessLuminance) : (ListenerUtil.mutListener.listen(2360) ? (luminance == highBrightnessLuminance) : (luminance < highBrightnessLuminance))))))) {
                                        if (!ListenerUtil.mutListener.listen(2372)) {
                                            if ((ListenerUtil.mutListener.listen(2370) ? (luminance >= mediumBrightnessLuminance) : (ListenerUtil.mutListener.listen(2369) ? (luminance <= mediumBrightnessLuminance) : (ListenerUtil.mutListener.listen(2368) ? (luminance < mediumBrightnessLuminance) : (ListenerUtil.mutListener.listen(2367) ? (luminance != mediumBrightnessLuminance) : (ListenerUtil.mutListener.listen(2366) ? (luminance == mediumBrightnessLuminance) : (luminance > mediumBrightnessLuminance))))))) {
                                                if (!ListenerUtil.mutListener.listen(2371)) {
                                                    numberOfMediumBrightnessPixels++;
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(2365)) {
                                            numberOfBrightPixels++;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2385)) {
                                    if ((ListenerUtil.mutListener.listen(2384) ? ((ListenerUtil.mutListener.listen(2378) ? (numberOfBrightPixels <= brightPixelThreshold) : (ListenerUtil.mutListener.listen(2377) ? (numberOfBrightPixels > brightPixelThreshold) : (ListenerUtil.mutListener.listen(2376) ? (numberOfBrightPixels < brightPixelThreshold) : (ListenerUtil.mutListener.listen(2375) ? (numberOfBrightPixels != brightPixelThreshold) : (ListenerUtil.mutListener.listen(2374) ? (numberOfBrightPixels == brightPixelThreshold) : (numberOfBrightPixels >= brightPixelThreshold)))))) && (ListenerUtil.mutListener.listen(2383) ? (numberOfMediumBrightnessPixels <= mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2382) ? (numberOfMediumBrightnessPixels > mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2381) ? (numberOfMediumBrightnessPixels < mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2380) ? (numberOfMediumBrightnessPixels != mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2379) ? (numberOfMediumBrightnessPixels == mediumBrightPixelThreshold) : (numberOfMediumBrightnessPixels >= mediumBrightPixelThreshold))))))) : ((ListenerUtil.mutListener.listen(2378) ? (numberOfBrightPixels <= brightPixelThreshold) : (ListenerUtil.mutListener.listen(2377) ? (numberOfBrightPixels > brightPixelThreshold) : (ListenerUtil.mutListener.listen(2376) ? (numberOfBrightPixels < brightPixelThreshold) : (ListenerUtil.mutListener.listen(2375) ? (numberOfBrightPixels != brightPixelThreshold) : (ListenerUtil.mutListener.listen(2374) ? (numberOfBrightPixels == brightPixelThreshold) : (numberOfBrightPixels >= brightPixelThreshold)))))) || (ListenerUtil.mutListener.listen(2383) ? (numberOfMediumBrightnessPixels <= mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2382) ? (numberOfMediumBrightnessPixels > mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2381) ? (numberOfMediumBrightnessPixels < mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2380) ? (numberOfMediumBrightnessPixels != mediumBrightPixelThreshold) : (ListenerUtil.mutListener.listen(2379) ? (numberOfMediumBrightnessPixels == mediumBrightPixelThreshold) : (numberOfMediumBrightnessPixels >= mediumBrightPixelThreshold))))))))) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Downloads the image from the URL and sets it as the phone's wallpaper
     * Fails silently if download or setting wallpaper fails.
     *
     * @param context context
     * @param imageUrl Url of the image
     */
    public static void setWallpaperFromImageUrl(Context context, Uri imageUrl) {
        if (!ListenerUtil.mutListener.listen(2398)) {
            showSettingWallpaperProgressBar(context);
        }
        if (!ListenerUtil.mutListener.listen(2399)) {
            Timber.d("Trying to set wallpaper from url %s", imageUrl.toString());
        }
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(imageUrl).setAutoRotateEnabled(true).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        if (!ListenerUtil.mutListener.listen(2408)) {
            dataSource.subscribe(new BaseBitmapDataSubscriber() {

                @Override
                public void onNewResultImpl(@Nullable Bitmap bitmap) {
                    if (!ListenerUtil.mutListener.listen(2404)) {
                        if ((ListenerUtil.mutListener.listen(2400) ? (dataSource.isFinished() || bitmap != null) : (dataSource.isFinished() && bitmap != null))) {
                            if (!ListenerUtil.mutListener.listen(2401)) {
                                Timber.d("Bitmap loaded from url %s", imageUrl.toString());
                            }
                            if (!ListenerUtil.mutListener.listen(2402)) {
                                setWallpaper(context, Bitmap.createBitmap(bitmap));
                            }
                            if (!ListenerUtil.mutListener.listen(2403)) {
                                dataSource.close();
                            }
                        }
                    }
                }

                @Override
                public void onFailureImpl(DataSource dataSource) {
                    if (!ListenerUtil.mutListener.listen(2405)) {
                        Timber.d("Error getting bitmap from image url %s", imageUrl.toString());
                    }
                    if (!ListenerUtil.mutListener.listen(2407)) {
                        if (dataSource != null) {
                            if (!ListenerUtil.mutListener.listen(2406)) {
                                dataSource.close();
                            }
                        }
                    }
                }
            }, CallerThreadExecutor.getInstance());
        }
    }

    /**
     * Calls the set avatar api to set the image url as user's avatar
     * @param context
     * @param url
     * @param username
     * @param okHttpJsonApiClient
     * @param compositeDisposable
     */
    public static void setAvatarFromImageUrl(Context context, String url, String username, OkHttpJsonApiClient okHttpJsonApiClient, CompositeDisposable compositeDisposable) {
        if (!ListenerUtil.mutListener.listen(2409)) {
            showSettingAvatarProgressBar(context);
        }
        try {
            if (!ListenerUtil.mutListener.listen(2414)) {
                compositeDisposable.add(okHttpJsonApiClient.setAvatar(username, url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
                    if (response != null && response.getStatus().equals("200")) {
                        ViewUtil.showLongToast(context, context.getString(R.string.avatar_set_successfully));
                        if (progressDialogAvatar != null && progressDialogAvatar.isShowing()) {
                            progressDialogAvatar.dismiss();
                        }
                    }
                }, t -> {
                    Timber.e(t, "Setting Avatar Failed");
                    ViewUtil.showLongToast(context, context.getString(R.string.avatar_set_unsuccessfully));
                    if (progressDialogAvatar != null) {
                        progressDialogAvatar.cancel();
                    }
                }));
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2410)) {
                Timber.d(e + "success");
            }
            if (!ListenerUtil.mutListener.listen(2411)) {
                ViewUtil.showLongToast(context, context.getString(R.string.avatar_set_unsuccessfully));
            }
            if (!ListenerUtil.mutListener.listen(2413)) {
                if (progressDialogAvatar != null) {
                    if (!ListenerUtil.mutListener.listen(2412)) {
                        progressDialogAvatar.cancel();
                    }
                }
            }
        }
    }

    private static void setWallpaper(Context context, Bitmap bitmap) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            if (!ListenerUtil.mutListener.listen(2419)) {
                wallpaperManager.setBitmap(bitmap);
            }
            if (!ListenerUtil.mutListener.listen(2420)) {
                ViewUtil.showLongToast(context, context.getString(R.string.wallpaper_set_successfully));
            }
            if (!ListenerUtil.mutListener.listen(2423)) {
                if ((ListenerUtil.mutListener.listen(2421) ? (progressDialogWallpaper != null || progressDialogWallpaper.isShowing()) : (progressDialogWallpaper != null && progressDialogWallpaper.isShowing()))) {
                    if (!ListenerUtil.mutListener.listen(2422)) {
                        progressDialogWallpaper.dismiss();
                    }
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(2415)) {
                Timber.e(e, "Error setting wallpaper");
            }
            if (!ListenerUtil.mutListener.listen(2416)) {
                ViewUtil.showLongToast(context, context.getString(R.string.wallpaper_set_unsuccessfully));
            }
            if (!ListenerUtil.mutListener.listen(2418)) {
                if (progressDialogWallpaper != null) {
                    if (!ListenerUtil.mutListener.listen(2417)) {
                        progressDialogWallpaper.cancel();
                    }
                }
            }
        }
    }

    private static void showSettingWallpaperProgressBar(Context context) {
        if (!ListenerUtil.mutListener.listen(2424)) {
            progressDialogWallpaper = ProgressDialog.show(context, context.getString(R.string.setting_wallpaper_dialog_title), context.getString(R.string.setting_wallpaper_dialog_message), true);
        }
    }

    private static void showSettingAvatarProgressBar(Context context) {
        if (!ListenerUtil.mutListener.listen(2425)) {
            progressDialogAvatar = ProgressDialog.show(context, context.getString(R.string.setting_avatar_dialog_title), context.getString(R.string.setting_avatar_dialog_message), true);
        }
    }

    /**
     * Result variable is a result of an or operation of all possible problems. Ie. if result
     * is 0001 means IMAGE_DARK
     * if result is 1100 IMAGE_DUPLICATE and IMAGE_GEOLOCATION_DIFFERENT
     */
    public static String getErrorMessageForResult(Context context, @Result int result) {
        StringBuilder errorMessage = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(2477)) {
            if ((ListenerUtil.mutListener.listen(2430) ? (result >= 0) : (ListenerUtil.mutListener.listen(2429) ? (result > 0) : (ListenerUtil.mutListener.listen(2428) ? (result < 0) : (ListenerUtil.mutListener.listen(2427) ? (result != 0) : (ListenerUtil.mutListener.listen(2426) ? (result == 0) : (result <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(2476)) {
                    Timber.d("No issues to warn user is found");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2431)) {
                    Timber.d("Issues found to warn user");
                }
                if (!ListenerUtil.mutListener.listen(2432)) {
                    errorMessage.append(context.getResources().getString(R.string.upload_problem_exist));
                }
                if (!ListenerUtil.mutListener.listen(2439)) {
                    if ((ListenerUtil.mutListener.listen(2437) ? ((IMAGE_DARK & result) >= 0) : (ListenerUtil.mutListener.listen(2436) ? ((IMAGE_DARK & result) <= 0) : (ListenerUtil.mutListener.listen(2435) ? ((IMAGE_DARK & result) > 0) : (ListenerUtil.mutListener.listen(2434) ? ((IMAGE_DARK & result) < 0) : (ListenerUtil.mutListener.listen(2433) ? ((IMAGE_DARK & result) == 0) : ((IMAGE_DARK & result) != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2438)) {
                            // We are checking image dark bit to see if that bit is set or not
                            errorMessage.append("\n - ").append(context.getResources().getString(R.string.upload_problem_image_dark));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2446)) {
                    if ((ListenerUtil.mutListener.listen(2444) ? ((IMAGE_BLURRY & result) >= 0) : (ListenerUtil.mutListener.listen(2443) ? ((IMAGE_BLURRY & result) <= 0) : (ListenerUtil.mutListener.listen(2442) ? ((IMAGE_BLURRY & result) > 0) : (ListenerUtil.mutListener.listen(2441) ? ((IMAGE_BLURRY & result) < 0) : (ListenerUtil.mutListener.listen(2440) ? ((IMAGE_BLURRY & result) == 0) : ((IMAGE_BLURRY & result) != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2445)) {
                            errorMessage.append("\n - ").append(context.getResources().getString(R.string.upload_problem_image_blurry));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2453)) {
                    if ((ListenerUtil.mutListener.listen(2451) ? ((IMAGE_DUPLICATE & result) >= 0) : (ListenerUtil.mutListener.listen(2450) ? ((IMAGE_DUPLICATE & result) <= 0) : (ListenerUtil.mutListener.listen(2449) ? ((IMAGE_DUPLICATE & result) > 0) : (ListenerUtil.mutListener.listen(2448) ? ((IMAGE_DUPLICATE & result) < 0) : (ListenerUtil.mutListener.listen(2447) ? ((IMAGE_DUPLICATE & result) == 0) : ((IMAGE_DUPLICATE & result) != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2452)) {
                            errorMessage.append("\n - ").append(context.getResources().getString(R.string.upload_problem_image_duplicate));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2460)) {
                    if ((ListenerUtil.mutListener.listen(2458) ? ((IMAGE_GEOLOCATION_DIFFERENT & result) >= 0) : (ListenerUtil.mutListener.listen(2457) ? ((IMAGE_GEOLOCATION_DIFFERENT & result) <= 0) : (ListenerUtil.mutListener.listen(2456) ? ((IMAGE_GEOLOCATION_DIFFERENT & result) > 0) : (ListenerUtil.mutListener.listen(2455) ? ((IMAGE_GEOLOCATION_DIFFERENT & result) < 0) : (ListenerUtil.mutListener.listen(2454) ? ((IMAGE_GEOLOCATION_DIFFERENT & result) == 0) : ((IMAGE_GEOLOCATION_DIFFERENT & result) != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2459)) {
                            errorMessage.append("\n - ").append(context.getResources().getString(R.string.upload_problem_different_geolocation));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2467)) {
                    if ((ListenerUtil.mutListener.listen(2465) ? ((FILE_FBMD & result) >= 0) : (ListenerUtil.mutListener.listen(2464) ? ((FILE_FBMD & result) <= 0) : (ListenerUtil.mutListener.listen(2463) ? ((FILE_FBMD & result) > 0) : (ListenerUtil.mutListener.listen(2462) ? ((FILE_FBMD & result) < 0) : (ListenerUtil.mutListener.listen(2461) ? ((FILE_FBMD & result) == 0) : ((FILE_FBMD & result) != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2466)) {
                            errorMessage.append("\n - ").append(context.getResources().getString(R.string.upload_problem_fbmd));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2474)) {
                    if ((ListenerUtil.mutListener.listen(2472) ? ((FILE_NO_EXIF & result) >= 0) : (ListenerUtil.mutListener.listen(2471) ? ((FILE_NO_EXIF & result) <= 0) : (ListenerUtil.mutListener.listen(2470) ? ((FILE_NO_EXIF & result) > 0) : (ListenerUtil.mutListener.listen(2469) ? ((FILE_NO_EXIF & result) < 0) : (ListenerUtil.mutListener.listen(2468) ? ((FILE_NO_EXIF & result) == 0) : ((FILE_NO_EXIF & result) != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2473)) {
                            errorMessage.append("\n - ").append(context.getResources().getString(R.string.internet_downloaded));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2475)) {
                    errorMessage.append("\n\n").append(context.getResources().getString(R.string.upload_problem_do_you_continue));
                }
            }
        }
        return errorMessage.toString();
    }

    /**
     * Adds red border to a bitmap
     * @param bitmap
     * @param borderSize
     * @param context
     * @return
     */
    public static Bitmap addRedBorder(Bitmap bitmap, int borderSize, Context context) {
        Bitmap bmpWithBorder = Bitmap.createBitmap(bitmap.getWidth() + (ListenerUtil.mutListener.listen(2481) ? (borderSize % 2) : (ListenerUtil.mutListener.listen(2480) ? (borderSize / 2) : (ListenerUtil.mutListener.listen(2479) ? (borderSize - 2) : (ListenerUtil.mutListener.listen(2478) ? (borderSize + 2) : (borderSize * 2))))), bitmap.getHeight() + (ListenerUtil.mutListener.listen(2485) ? (borderSize % 2) : (ListenerUtil.mutListener.listen(2484) ? (borderSize / 2) : (ListenerUtil.mutListener.listen(2483) ? (borderSize - 2) : (ListenerUtil.mutListener.listen(2482) ? (borderSize + 2) : (borderSize * 2))))), bitmap.getConfig());
        Canvas canvas = new Canvas(bmpWithBorder);
        if (!ListenerUtil.mutListener.listen(2486)) {
            canvas.drawColor(ContextCompat.getColor(context, R.color.deleteRed));
        }
        if (!ListenerUtil.mutListener.listen(2487)) {
            canvas.drawBitmap(bitmap, borderSize, borderSize, null);
        }
        return bmpWithBorder;
    }
}
