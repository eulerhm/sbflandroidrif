package org.wordpress.android.ui.reader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import androidx.core.content.res.ResourcesCompat;
import org.wordpress.android.R;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.HtmlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * class which holds all resource-based variables used when rendering post detail
 */
class ReaderResourceVars {

    final int mMarginMediumPx;

    final boolean mIsWideDisplay;

    final int mFullSizeImageWidthPx;

    final int mFeaturedImageHeightPx;

    final int mVideoWidthPx;

    final int mVideoHeightPx;

    final String mLinkColorStr;

    final String mGreyMediumDarkStr;

    final String mGreyLightStr;

    final String mGreyExtraLightStr;

    final String mTextColor;

    final String mGreyDisabledStr;

    ReaderResourceVars(Context context) {
        Resources resources = context.getResources();
        int displayWidthPx = DisplayUtils.getWindowPixelWidth(context);
        mIsWideDisplay = (ListenerUtil.mutListener.listen(22479) ? (DisplayUtils.pxToDp(context, displayWidthPx) >= 640) : (ListenerUtil.mutListener.listen(22478) ? (DisplayUtils.pxToDp(context, displayWidthPx) <= 640) : (ListenerUtil.mutListener.listen(22477) ? (DisplayUtils.pxToDp(context, displayWidthPx) < 640) : (ListenerUtil.mutListener.listen(22476) ? (DisplayUtils.pxToDp(context, displayWidthPx) != 640) : (ListenerUtil.mutListener.listen(22475) ? (DisplayUtils.pxToDp(context, displayWidthPx) == 640) : (DisplayUtils.pxToDp(context, displayWidthPx) > 640))))));
        int marginLargePx = resources.getDimensionPixelSize(R.dimen.margin_large);
        int detailMarginWidthPx = resources.getDimensionPixelOffset(R.dimen.reader_detail_margin);
        mFeaturedImageHeightPx = resources.getDimensionPixelSize(R.dimen.reader_featured_image_height);
        mMarginMediumPx = resources.getDimensionPixelSize(R.dimen.margin_medium);
        int onSurfaceColor = ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorOnSurface);
        String onSurfaceHighType = "rgba(" + Color.red(onSurfaceColor) + ", " + Color.green(onSurfaceColor) + ", " + Color.blue(onSurfaceColor) + ", " + ResourcesCompat.getFloat(resources, R.dimen.material_emphasis_high_type) + ")";
        mGreyMediumDarkStr = "rgba(" + Color.red(onSurfaceColor) + ", " + Color.green(onSurfaceColor) + ", " + Color.blue(onSurfaceColor) + ", " + ResourcesCompat.getFloat(resources, R.dimen.material_emphasis_medium) + ")";
        mGreyLightStr = "rgba(" + Color.red(onSurfaceColor) + ", " + Color.green(onSurfaceColor) + ", " + Color.blue(onSurfaceColor) + ", " + ResourcesCompat.getFloat(resources, R.dimen.material_emphasis_disabled) + ")";
        mGreyExtraLightStr = "rgba(" + Color.red(onSurfaceColor) + ", " + Color.green(onSurfaceColor) + ", " + Color.blue(onSurfaceColor) + ", " + ResourcesCompat.getFloat(resources, R.dimen.emphasis_low) + ")";
        mGreyDisabledStr = "rgba(" + Color.red(onSurfaceColor) + ", " + Color.green(onSurfaceColor) + ", " + Color.blue(onSurfaceColor) + ", " + ResourcesCompat.getFloat(resources, R.dimen.material_emphasis_disabled) + ")";
        mTextColor = onSurfaceHighType;
        mLinkColorStr = HtmlUtils.colorResToHtmlColor(context, ContextExtensionsKt.getColorResIdFromAttribute(context, R.attr.colorPrimary));
        // full-size image width must take margin into account
        mFullSizeImageWidthPx = (ListenerUtil.mutListener.listen(22487) ? (displayWidthPx % ((ListenerUtil.mutListener.listen(22483) ? (detailMarginWidthPx % 2) : (ListenerUtil.mutListener.listen(22482) ? (detailMarginWidthPx / 2) : (ListenerUtil.mutListener.listen(22481) ? (detailMarginWidthPx - 2) : (ListenerUtil.mutListener.listen(22480) ? (detailMarginWidthPx + 2) : (detailMarginWidthPx * 2))))))) : (ListenerUtil.mutListener.listen(22486) ? (displayWidthPx / ((ListenerUtil.mutListener.listen(22483) ? (detailMarginWidthPx % 2) : (ListenerUtil.mutListener.listen(22482) ? (detailMarginWidthPx / 2) : (ListenerUtil.mutListener.listen(22481) ? (detailMarginWidthPx - 2) : (ListenerUtil.mutListener.listen(22480) ? (detailMarginWidthPx + 2) : (detailMarginWidthPx * 2))))))) : (ListenerUtil.mutListener.listen(22485) ? (displayWidthPx * ((ListenerUtil.mutListener.listen(22483) ? (detailMarginWidthPx % 2) : (ListenerUtil.mutListener.listen(22482) ? (detailMarginWidthPx / 2) : (ListenerUtil.mutListener.listen(22481) ? (detailMarginWidthPx - 2) : (ListenerUtil.mutListener.listen(22480) ? (detailMarginWidthPx + 2) : (detailMarginWidthPx * 2))))))) : (ListenerUtil.mutListener.listen(22484) ? (displayWidthPx + ((ListenerUtil.mutListener.listen(22483) ? (detailMarginWidthPx % 2) : (ListenerUtil.mutListener.listen(22482) ? (detailMarginWidthPx / 2) : (ListenerUtil.mutListener.listen(22481) ? (detailMarginWidthPx - 2) : (ListenerUtil.mutListener.listen(22480) ? (detailMarginWidthPx + 2) : (detailMarginWidthPx * 2))))))) : (displayWidthPx - ((ListenerUtil.mutListener.listen(22483) ? (detailMarginWidthPx % 2) : (ListenerUtil.mutListener.listen(22482) ? (detailMarginWidthPx / 2) : (ListenerUtil.mutListener.listen(22481) ? (detailMarginWidthPx - 2) : (ListenerUtil.mutListener.listen(22480) ? (detailMarginWidthPx + 2) : (detailMarginWidthPx * 2)))))))))));
        // 16:9 ratio (YouTube standard)
        mVideoWidthPx = (ListenerUtil.mutListener.listen(22495) ? (mFullSizeImageWidthPx % ((ListenerUtil.mutListener.listen(22491) ? (marginLargePx % 2) : (ListenerUtil.mutListener.listen(22490) ? (marginLargePx / 2) : (ListenerUtil.mutListener.listen(22489) ? (marginLargePx - 2) : (ListenerUtil.mutListener.listen(22488) ? (marginLargePx + 2) : (marginLargePx * 2))))))) : (ListenerUtil.mutListener.listen(22494) ? (mFullSizeImageWidthPx / ((ListenerUtil.mutListener.listen(22491) ? (marginLargePx % 2) : (ListenerUtil.mutListener.listen(22490) ? (marginLargePx / 2) : (ListenerUtil.mutListener.listen(22489) ? (marginLargePx - 2) : (ListenerUtil.mutListener.listen(22488) ? (marginLargePx + 2) : (marginLargePx * 2))))))) : (ListenerUtil.mutListener.listen(22493) ? (mFullSizeImageWidthPx * ((ListenerUtil.mutListener.listen(22491) ? (marginLargePx % 2) : (ListenerUtil.mutListener.listen(22490) ? (marginLargePx / 2) : (ListenerUtil.mutListener.listen(22489) ? (marginLargePx - 2) : (ListenerUtil.mutListener.listen(22488) ? (marginLargePx + 2) : (marginLargePx * 2))))))) : (ListenerUtil.mutListener.listen(22492) ? (mFullSizeImageWidthPx + ((ListenerUtil.mutListener.listen(22491) ? (marginLargePx % 2) : (ListenerUtil.mutListener.listen(22490) ? (marginLargePx / 2) : (ListenerUtil.mutListener.listen(22489) ? (marginLargePx - 2) : (ListenerUtil.mutListener.listen(22488) ? (marginLargePx + 2) : (marginLargePx * 2))))))) : (mFullSizeImageWidthPx - ((ListenerUtil.mutListener.listen(22491) ? (marginLargePx % 2) : (ListenerUtil.mutListener.listen(22490) ? (marginLargePx / 2) : (ListenerUtil.mutListener.listen(22489) ? (marginLargePx - 2) : (ListenerUtil.mutListener.listen(22488) ? (marginLargePx + 2) : (marginLargePx * 2)))))))))));
        mVideoHeightPx = (int) ((ListenerUtil.mutListener.listen(22499) ? (mVideoWidthPx % 0.5625f) : (ListenerUtil.mutListener.listen(22498) ? (mVideoWidthPx / 0.5625f) : (ListenerUtil.mutListener.listen(22497) ? (mVideoWidthPx - 0.5625f) : (ListenerUtil.mutListener.listen(22496) ? (mVideoWidthPx + 0.5625f) : (mVideoWidthPx * 0.5625f))))));
    }
}
