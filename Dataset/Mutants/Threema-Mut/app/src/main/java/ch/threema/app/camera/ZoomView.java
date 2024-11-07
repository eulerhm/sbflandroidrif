/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
public class ZoomView extends FrameLayout {

    private Paint linePaint, circlePaint, semiPaint, labelPaint;

    private int strokeWidth, barPadding, labelStrokeWidth;

    private float zoomFactor;

    public ZoomView(@NonNull Context context) {
        this(context, null);
    }

    public ZoomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(12824)) {
            init(context);
        }
    }

    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(12825)) {
            setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(12826)) {
            this.strokeWidth = context.getResources().getDimensionPixelSize(R.dimen.zoom_view_stroke_width);
        }
        if (!ListenerUtil.mutListener.listen(12827)) {
            this.labelStrokeWidth = context.getResources().getDimensionPixelSize(R.dimen.zoom_view_label_stroke_width);
        }
        if (!ListenerUtil.mutListener.listen(12828)) {
            this.barPadding = context.getResources().getDimensionPixelSize(R.dimen.zoom_view_bar_padding);
        }
        if (!ListenerUtil.mutListener.listen(12829)) {
            this.zoomFactor = 0;
        }
        if (!ListenerUtil.mutListener.listen(12830)) {
            this.linePaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12831)) {
            this.linePaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12832)) {
            this.linePaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(12833)) {
            this.linePaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12834)) {
            this.linePaint.setStrokeWidth(this.strokeWidth);
        }
        if (!ListenerUtil.mutListener.listen(12835)) {
            this.semiPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12836)) {
            this.semiPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12837)) {
            this.semiPaint.setColor(getResources().getColor(R.color.background_dim_light));
        }
        if (!ListenerUtil.mutListener.listen(12838)) {
            this.semiPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12839)) {
            this.semiPaint.setStrokeWidth(this.strokeWidth);
        }
        if (!ListenerUtil.mutListener.listen(12840)) {
            this.circlePaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12841)) {
            this.circlePaint.setStyle(Paint.Style.FILL);
        }
        if (!ListenerUtil.mutListener.listen(12842)) {
            this.circlePaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(12843)) {
            this.circlePaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12844)) {
            this.circlePaint.setStrokeWidth(0);
        }
        if (!ListenerUtil.mutListener.listen(12845)) {
            this.labelPaint = new Paint();
        }
        if (!ListenerUtil.mutListener.listen(12846)) {
            this.labelPaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(12847)) {
            this.labelPaint.setColor(Color.WHITE);
        }
        if (!ListenerUtil.mutListener.listen(12848)) {
            this.labelPaint.setAntiAlias(true);
        }
        if (!ListenerUtil.mutListener.listen(12849)) {
            this.labelPaint.setStrokeWidth(this.labelStrokeWidth);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(12850)) {
            super.dispatchDraw(canvas);
        }
        if (!ListenerUtil.mutListener.listen(13036)) {
            if ((ListenerUtil.mutListener.listen(12855) ? (getWidth() >= getHeight()) : (ListenerUtil.mutListener.listen(12854) ? (getWidth() <= getHeight()) : (ListenerUtil.mutListener.listen(12853) ? (getWidth() < getHeight()) : (ListenerUtil.mutListener.listen(12852) ? (getWidth() != getHeight()) : (ListenerUtil.mutListener.listen(12851) ? (getWidth() == getHeight()) : (getWidth() > getHeight()))))))) {
                int left = getLeft() + this.barPadding;
                int right = (ListenerUtil.mutListener.listen(12949) ? (getRight() % this.barPadding) : (ListenerUtil.mutListener.listen(12948) ? (getRight() / this.barPadding) : (ListenerUtil.mutListener.listen(12947) ? (getRight() * this.barPadding) : (ListenerUtil.mutListener.listen(12946) ? (getRight() + this.barPadding) : (getRight() - this.barPadding)))));
                int width = (ListenerUtil.mutListener.listen(12953) ? (right % left) : (ListenerUtil.mutListener.listen(12952) ? (right / left) : (ListenerUtil.mutListener.listen(12951) ? (right * left) : (ListenerUtil.mutListener.listen(12950) ? (right + left) : (right - left)))));
                int top = 0;
                // height
                int bottom = (ListenerUtil.mutListener.listen(12957) ? (getBottom() % getTop()) : (ListenerUtil.mutListener.listen(12956) ? (getBottom() / getTop()) : (ListenerUtil.mutListener.listen(12955) ? (getBottom() * getTop()) : (ListenerUtil.mutListener.listen(12954) ? (getBottom() + getTop()) : (getBottom() - getTop())))));
                int center = (ListenerUtil.mutListener.listen(12965) ? (((ListenerUtil.mutListener.listen(12961) ? (bottom % top) : (ListenerUtil.mutListener.listen(12960) ? (bottom / top) : (ListenerUtil.mutListener.listen(12959) ? (bottom * top) : (ListenerUtil.mutListener.listen(12958) ? (bottom - top) : (bottom + top)))))) % 2) : (ListenerUtil.mutListener.listen(12964) ? (((ListenerUtil.mutListener.listen(12961) ? (bottom % top) : (ListenerUtil.mutListener.listen(12960) ? (bottom / top) : (ListenerUtil.mutListener.listen(12959) ? (bottom * top) : (ListenerUtil.mutListener.listen(12958) ? (bottom - top) : (bottom + top)))))) * 2) : (ListenerUtil.mutListener.listen(12963) ? (((ListenerUtil.mutListener.listen(12961) ? (bottom % top) : (ListenerUtil.mutListener.listen(12960) ? (bottom / top) : (ListenerUtil.mutListener.listen(12959) ? (bottom * top) : (ListenerUtil.mutListener.listen(12958) ? (bottom - top) : (bottom + top)))))) - 2) : (ListenerUtil.mutListener.listen(12962) ? (((ListenerUtil.mutListener.listen(12961) ? (bottom % top) : (ListenerUtil.mutListener.listen(12960) ? (bottom / top) : (ListenerUtil.mutListener.listen(12959) ? (bottom * top) : (ListenerUtil.mutListener.listen(12958) ? (bottom - top) : (bottom + top)))))) + 2) : (((ListenerUtil.mutListener.listen(12961) ? (bottom % top) : (ListenerUtil.mutListener.listen(12960) ? (bottom / top) : (ListenerUtil.mutListener.listen(12959) ? (bottom * top) : (ListenerUtil.mutListener.listen(12958) ? (bottom - top) : (bottom + top)))))) / 2)))));
                int circlePosition = (ListenerUtil.mutListener.listen(12973) ? (left % (int) ((ListenerUtil.mutListener.listen(12969) ? (this.zoomFactor % (float) width) : (ListenerUtil.mutListener.listen(12968) ? (this.zoomFactor / (float) width) : (ListenerUtil.mutListener.listen(12967) ? (this.zoomFactor - (float) width) : (ListenerUtil.mutListener.listen(12966) ? (this.zoomFactor + (float) width) : (this.zoomFactor * (float) width))))))) : (ListenerUtil.mutListener.listen(12972) ? (left / (int) ((ListenerUtil.mutListener.listen(12969) ? (this.zoomFactor % (float) width) : (ListenerUtil.mutListener.listen(12968) ? (this.zoomFactor / (float) width) : (ListenerUtil.mutListener.listen(12967) ? (this.zoomFactor - (float) width) : (ListenerUtil.mutListener.listen(12966) ? (this.zoomFactor + (float) width) : (this.zoomFactor * (float) width))))))) : (ListenerUtil.mutListener.listen(12971) ? (left * (int) ((ListenerUtil.mutListener.listen(12969) ? (this.zoomFactor % (float) width) : (ListenerUtil.mutListener.listen(12968) ? (this.zoomFactor / (float) width) : (ListenerUtil.mutListener.listen(12967) ? (this.zoomFactor - (float) width) : (ListenerUtil.mutListener.listen(12966) ? (this.zoomFactor + (float) width) : (this.zoomFactor * (float) width))))))) : (ListenerUtil.mutListener.listen(12970) ? (left - (int) ((ListenerUtil.mutListener.listen(12969) ? (this.zoomFactor % (float) width) : (ListenerUtil.mutListener.listen(12968) ? (this.zoomFactor / (float) width) : (ListenerUtil.mutListener.listen(12967) ? (this.zoomFactor - (float) width) : (ListenerUtil.mutListener.listen(12966) ? (this.zoomFactor + (float) width) : (this.zoomFactor * (float) width))))))) : (left + (int) ((ListenerUtil.mutListener.listen(12969) ? (this.zoomFactor % (float) width) : (ListenerUtil.mutListener.listen(12968) ? (this.zoomFactor / (float) width) : (ListenerUtil.mutListener.listen(12967) ? (this.zoomFactor - (float) width) : (ListenerUtil.mutListener.listen(12966) ? (this.zoomFactor + (float) width) : (this.zoomFactor * (float) width)))))))))));
                if (!ListenerUtil.mutListener.listen(12974)) {
                    // draw lines
                    canvas.drawLine(left, center, circlePosition, center, this.linePaint);
                }
                if (!ListenerUtil.mutListener.listen(12975)) {
                    canvas.drawLine(circlePosition, center, right, center, this.semiPaint);
                }
                if (!ListenerUtil.mutListener.listen(12992)) {
                    // draw circle
                    canvas.drawArc((ListenerUtil.mutListener.listen(12983) ? (circlePosition % ((ListenerUtil.mutListener.listen(12979) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12978) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12977) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12976) ? (bottom + 2) : (bottom / 2))))))) : (ListenerUtil.mutListener.listen(12982) ? (circlePosition / ((ListenerUtil.mutListener.listen(12979) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12978) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12977) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12976) ? (bottom + 2) : (bottom / 2))))))) : (ListenerUtil.mutListener.listen(12981) ? (circlePosition * ((ListenerUtil.mutListener.listen(12979) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12978) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12977) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12976) ? (bottom + 2) : (bottom / 2))))))) : (ListenerUtil.mutListener.listen(12980) ? (circlePosition + ((ListenerUtil.mutListener.listen(12979) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12978) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12977) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12976) ? (bottom + 2) : (bottom / 2))))))) : (circlePosition - ((ListenerUtil.mutListener.listen(12979) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12978) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12977) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12976) ? (bottom + 2) : (bottom / 2))))))))))), top, (ListenerUtil.mutListener.listen(12991) ? (circlePosition % ((ListenerUtil.mutListener.listen(12987) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12986) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12985) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12984) ? (bottom + 2) : (bottom / 2))))))) : (ListenerUtil.mutListener.listen(12990) ? (circlePosition / ((ListenerUtil.mutListener.listen(12987) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12986) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12985) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12984) ? (bottom + 2) : (bottom / 2))))))) : (ListenerUtil.mutListener.listen(12989) ? (circlePosition * ((ListenerUtil.mutListener.listen(12987) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12986) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12985) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12984) ? (bottom + 2) : (bottom / 2))))))) : (ListenerUtil.mutListener.listen(12988) ? (circlePosition - ((ListenerUtil.mutListener.listen(12987) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12986) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12985) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12984) ? (bottom + 2) : (bottom / 2))))))) : (circlePosition + ((ListenerUtil.mutListener.listen(12987) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12986) ? (bottom * 2) : (ListenerUtil.mutListener.listen(12985) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12984) ? (bottom + 2) : (bottom / 2))))))))))), bottom, 0, 360, false, this.circlePaint);
                }
                if (!ListenerUtil.mutListener.listen(13005)) {
                    // draw plus/minus
                    canvas.drawLine((ListenerUtil.mutListener.listen(13000) ? (left % ((ListenerUtil.mutListener.listen(12996) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12995) ? (bottom / 2) : (ListenerUtil.mutListener.listen(12994) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12993) ? (bottom + 2) : (bottom * 2))))))) : (ListenerUtil.mutListener.listen(12999) ? (left / ((ListenerUtil.mutListener.listen(12996) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12995) ? (bottom / 2) : (ListenerUtil.mutListener.listen(12994) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12993) ? (bottom + 2) : (bottom * 2))))))) : (ListenerUtil.mutListener.listen(12998) ? (left * ((ListenerUtil.mutListener.listen(12996) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12995) ? (bottom / 2) : (ListenerUtil.mutListener.listen(12994) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12993) ? (bottom + 2) : (bottom * 2))))))) : (ListenerUtil.mutListener.listen(12997) ? (left + ((ListenerUtil.mutListener.listen(12996) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12995) ? (bottom / 2) : (ListenerUtil.mutListener.listen(12994) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12993) ? (bottom + 2) : (bottom * 2))))))) : (left - ((ListenerUtil.mutListener.listen(12996) ? (bottom % 2) : (ListenerUtil.mutListener.listen(12995) ? (bottom / 2) : (ListenerUtil.mutListener.listen(12994) ? (bottom - 2) : (ListenerUtil.mutListener.listen(12993) ? (bottom + 2) : (bottom * 2))))))))))), center, (ListenerUtil.mutListener.listen(13004) ? (left % bottom) : (ListenerUtil.mutListener.listen(13003) ? (left / bottom) : (ListenerUtil.mutListener.listen(13002) ? (left * bottom) : (ListenerUtil.mutListener.listen(13001) ? (left + bottom) : (left - bottom))))), center, this.labelPaint);
                }
                if (!ListenerUtil.mutListener.listen(13018)) {
                    canvas.drawLine((ListenerUtil.mutListener.listen(13013) ? (right % ((ListenerUtil.mutListener.listen(13009) ? (bottom % 2) : (ListenerUtil.mutListener.listen(13008) ? (bottom / 2) : (ListenerUtil.mutListener.listen(13007) ? (bottom - 2) : (ListenerUtil.mutListener.listen(13006) ? (bottom + 2) : (bottom * 2))))))) : (ListenerUtil.mutListener.listen(13012) ? (right / ((ListenerUtil.mutListener.listen(13009) ? (bottom % 2) : (ListenerUtil.mutListener.listen(13008) ? (bottom / 2) : (ListenerUtil.mutListener.listen(13007) ? (bottom - 2) : (ListenerUtil.mutListener.listen(13006) ? (bottom + 2) : (bottom * 2))))))) : (ListenerUtil.mutListener.listen(13011) ? (right * ((ListenerUtil.mutListener.listen(13009) ? (bottom % 2) : (ListenerUtil.mutListener.listen(13008) ? (bottom / 2) : (ListenerUtil.mutListener.listen(13007) ? (bottom - 2) : (ListenerUtil.mutListener.listen(13006) ? (bottom + 2) : (bottom * 2))))))) : (ListenerUtil.mutListener.listen(13010) ? (right - ((ListenerUtil.mutListener.listen(13009) ? (bottom % 2) : (ListenerUtil.mutListener.listen(13008) ? (bottom / 2) : (ListenerUtil.mutListener.listen(13007) ? (bottom - 2) : (ListenerUtil.mutListener.listen(13006) ? (bottom + 2) : (bottom * 2))))))) : (right + ((ListenerUtil.mutListener.listen(13009) ? (bottom % 2) : (ListenerUtil.mutListener.listen(13008) ? (bottom / 2) : (ListenerUtil.mutListener.listen(13007) ? (bottom - 2) : (ListenerUtil.mutListener.listen(13006) ? (bottom + 2) : (bottom * 2))))))))))), center, (ListenerUtil.mutListener.listen(13017) ? (right % bottom) : (ListenerUtil.mutListener.listen(13016) ? (right / bottom) : (ListenerUtil.mutListener.listen(13015) ? (right * bottom) : (ListenerUtil.mutListener.listen(13014) ? (right - bottom) : (right + bottom))))), center, this.labelPaint);
                }
                if (!ListenerUtil.mutListener.listen(13035)) {
                    canvas.drawLine((ListenerUtil.mutListener.listen(13026) ? ((ListenerUtil.mutListener.listen(13022) ? (right % bottom) : (ListenerUtil.mutListener.listen(13021) ? (right / bottom) : (ListenerUtil.mutListener.listen(13020) ? (right * bottom) : (ListenerUtil.mutListener.listen(13019) ? (right - bottom) : (right + bottom))))) % center) : (ListenerUtil.mutListener.listen(13025) ? ((ListenerUtil.mutListener.listen(13022) ? (right % bottom) : (ListenerUtil.mutListener.listen(13021) ? (right / bottom) : (ListenerUtil.mutListener.listen(13020) ? (right * bottom) : (ListenerUtil.mutListener.listen(13019) ? (right - bottom) : (right + bottom))))) / center) : (ListenerUtil.mutListener.listen(13024) ? ((ListenerUtil.mutListener.listen(13022) ? (right % bottom) : (ListenerUtil.mutListener.listen(13021) ? (right / bottom) : (ListenerUtil.mutListener.listen(13020) ? (right * bottom) : (ListenerUtil.mutListener.listen(13019) ? (right - bottom) : (right + bottom))))) * center) : (ListenerUtil.mutListener.listen(13023) ? ((ListenerUtil.mutListener.listen(13022) ? (right % bottom) : (ListenerUtil.mutListener.listen(13021) ? (right / bottom) : (ListenerUtil.mutListener.listen(13020) ? (right * bottom) : (ListenerUtil.mutListener.listen(13019) ? (right - bottom) : (right + bottom))))) - center) : ((ListenerUtil.mutListener.listen(13022) ? (right % bottom) : (ListenerUtil.mutListener.listen(13021) ? (right / bottom) : (ListenerUtil.mutListener.listen(13020) ? (right * bottom) : (ListenerUtil.mutListener.listen(13019) ? (right - bottom) : (right + bottom))))) + center))))), top, (ListenerUtil.mutListener.listen(13034) ? ((ListenerUtil.mutListener.listen(13030) ? (right % bottom) : (ListenerUtil.mutListener.listen(13029) ? (right / bottom) : (ListenerUtil.mutListener.listen(13028) ? (right * bottom) : (ListenerUtil.mutListener.listen(13027) ? (right - bottom) : (right + bottom))))) % center) : (ListenerUtil.mutListener.listen(13033) ? ((ListenerUtil.mutListener.listen(13030) ? (right % bottom) : (ListenerUtil.mutListener.listen(13029) ? (right / bottom) : (ListenerUtil.mutListener.listen(13028) ? (right * bottom) : (ListenerUtil.mutListener.listen(13027) ? (right - bottom) : (right + bottom))))) / center) : (ListenerUtil.mutListener.listen(13032) ? ((ListenerUtil.mutListener.listen(13030) ? (right % bottom) : (ListenerUtil.mutListener.listen(13029) ? (right / bottom) : (ListenerUtil.mutListener.listen(13028) ? (right * bottom) : (ListenerUtil.mutListener.listen(13027) ? (right - bottom) : (right + bottom))))) * center) : (ListenerUtil.mutListener.listen(13031) ? ((ListenerUtil.mutListener.listen(13030) ? (right % bottom) : (ListenerUtil.mutListener.listen(13029) ? (right / bottom) : (ListenerUtil.mutListener.listen(13028) ? (right * bottom) : (ListenerUtil.mutListener.listen(13027) ? (right - bottom) : (right + bottom))))) - center) : ((ListenerUtil.mutListener.listen(13030) ? (right % bottom) : (ListenerUtil.mutListener.listen(13029) ? (right / bottom) : (ListenerUtil.mutListener.listen(13028) ? (right * bottom) : (ListenerUtil.mutListener.listen(13027) ? (right - bottom) : (right + bottom))))) + center))))), bottom, this.labelPaint);
                }
            } else {
                int top = getTop() + this.barPadding;
                int bottom = (ListenerUtil.mutListener.listen(12859) ? (getBottom() % this.barPadding) : (ListenerUtil.mutListener.listen(12858) ? (getBottom() / this.barPadding) : (ListenerUtil.mutListener.listen(12857) ? (getBottom() * this.barPadding) : (ListenerUtil.mutListener.listen(12856) ? (getBottom() + this.barPadding) : (getBottom() - this.barPadding)))));
                int height = (ListenerUtil.mutListener.listen(12863) ? (bottom % top) : (ListenerUtil.mutListener.listen(12862) ? (bottom / top) : (ListenerUtil.mutListener.listen(12861) ? (bottom * top) : (ListenerUtil.mutListener.listen(12860) ? (bottom + top) : (bottom - top)))));
                int left = 0;
                // width
                int right = (ListenerUtil.mutListener.listen(12867) ? (getRight() % getLeft()) : (ListenerUtil.mutListener.listen(12866) ? (getRight() / getLeft()) : (ListenerUtil.mutListener.listen(12865) ? (getRight() * getLeft()) : (ListenerUtil.mutListener.listen(12864) ? (getRight() + getLeft()) : (getRight() - getLeft())))));
                int center = (ListenerUtil.mutListener.listen(12875) ? (((ListenerUtil.mutListener.listen(12871) ? (right % left) : (ListenerUtil.mutListener.listen(12870) ? (right / left) : (ListenerUtil.mutListener.listen(12869) ? (right * left) : (ListenerUtil.mutListener.listen(12868) ? (right - left) : (right + left)))))) % 2) : (ListenerUtil.mutListener.listen(12874) ? (((ListenerUtil.mutListener.listen(12871) ? (right % left) : (ListenerUtil.mutListener.listen(12870) ? (right / left) : (ListenerUtil.mutListener.listen(12869) ? (right * left) : (ListenerUtil.mutListener.listen(12868) ? (right - left) : (right + left)))))) * 2) : (ListenerUtil.mutListener.listen(12873) ? (((ListenerUtil.mutListener.listen(12871) ? (right % left) : (ListenerUtil.mutListener.listen(12870) ? (right / left) : (ListenerUtil.mutListener.listen(12869) ? (right * left) : (ListenerUtil.mutListener.listen(12868) ? (right - left) : (right + left)))))) - 2) : (ListenerUtil.mutListener.listen(12872) ? (((ListenerUtil.mutListener.listen(12871) ? (right % left) : (ListenerUtil.mutListener.listen(12870) ? (right / left) : (ListenerUtil.mutListener.listen(12869) ? (right * left) : (ListenerUtil.mutListener.listen(12868) ? (right - left) : (right + left)))))) + 2) : (((ListenerUtil.mutListener.listen(12871) ? (right % left) : (ListenerUtil.mutListener.listen(12870) ? (right / left) : (ListenerUtil.mutListener.listen(12869) ? (right * left) : (ListenerUtil.mutListener.listen(12868) ? (right - left) : (right + left)))))) / 2)))));
                int circlePosition = (ListenerUtil.mutListener.listen(12883) ? (bottom % (int) ((ListenerUtil.mutListener.listen(12879) ? (this.zoomFactor % (float) height) : (ListenerUtil.mutListener.listen(12878) ? (this.zoomFactor / (float) height) : (ListenerUtil.mutListener.listen(12877) ? (this.zoomFactor - (float) height) : (ListenerUtil.mutListener.listen(12876) ? (this.zoomFactor + (float) height) : (this.zoomFactor * (float) height))))))) : (ListenerUtil.mutListener.listen(12882) ? (bottom / (int) ((ListenerUtil.mutListener.listen(12879) ? (this.zoomFactor % (float) height) : (ListenerUtil.mutListener.listen(12878) ? (this.zoomFactor / (float) height) : (ListenerUtil.mutListener.listen(12877) ? (this.zoomFactor - (float) height) : (ListenerUtil.mutListener.listen(12876) ? (this.zoomFactor + (float) height) : (this.zoomFactor * (float) height))))))) : (ListenerUtil.mutListener.listen(12881) ? (bottom * (int) ((ListenerUtil.mutListener.listen(12879) ? (this.zoomFactor % (float) height) : (ListenerUtil.mutListener.listen(12878) ? (this.zoomFactor / (float) height) : (ListenerUtil.mutListener.listen(12877) ? (this.zoomFactor - (float) height) : (ListenerUtil.mutListener.listen(12876) ? (this.zoomFactor + (float) height) : (this.zoomFactor * (float) height))))))) : (ListenerUtil.mutListener.listen(12880) ? (bottom + (int) ((ListenerUtil.mutListener.listen(12879) ? (this.zoomFactor % (float) height) : (ListenerUtil.mutListener.listen(12878) ? (this.zoomFactor / (float) height) : (ListenerUtil.mutListener.listen(12877) ? (this.zoomFactor - (float) height) : (ListenerUtil.mutListener.listen(12876) ? (this.zoomFactor + (float) height) : (this.zoomFactor * (float) height))))))) : (bottom - (int) ((ListenerUtil.mutListener.listen(12879) ? (this.zoomFactor % (float) height) : (ListenerUtil.mutListener.listen(12878) ? (this.zoomFactor / (float) height) : (ListenerUtil.mutListener.listen(12877) ? (this.zoomFactor - (float) height) : (ListenerUtil.mutListener.listen(12876) ? (this.zoomFactor + (float) height) : (this.zoomFactor * (float) height)))))))))));
                if (!ListenerUtil.mutListener.listen(12884)) {
                    // draw lines
                    canvas.drawLine(center, top, center, circlePosition, this.semiPaint);
                }
                if (!ListenerUtil.mutListener.listen(12885)) {
                    canvas.drawLine(center, circlePosition, center, bottom, this.linePaint);
                }
                if (!ListenerUtil.mutListener.listen(12902)) {
                    // draw circle
                    canvas.drawArc(left, (ListenerUtil.mutListener.listen(12893) ? (circlePosition % ((ListenerUtil.mutListener.listen(12889) ? (right % 2) : (ListenerUtil.mutListener.listen(12888) ? (right * 2) : (ListenerUtil.mutListener.listen(12887) ? (right - 2) : (ListenerUtil.mutListener.listen(12886) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12892) ? (circlePosition / ((ListenerUtil.mutListener.listen(12889) ? (right % 2) : (ListenerUtil.mutListener.listen(12888) ? (right * 2) : (ListenerUtil.mutListener.listen(12887) ? (right - 2) : (ListenerUtil.mutListener.listen(12886) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12891) ? (circlePosition * ((ListenerUtil.mutListener.listen(12889) ? (right % 2) : (ListenerUtil.mutListener.listen(12888) ? (right * 2) : (ListenerUtil.mutListener.listen(12887) ? (right - 2) : (ListenerUtil.mutListener.listen(12886) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12890) ? (circlePosition + ((ListenerUtil.mutListener.listen(12889) ? (right % 2) : (ListenerUtil.mutListener.listen(12888) ? (right * 2) : (ListenerUtil.mutListener.listen(12887) ? (right - 2) : (ListenerUtil.mutListener.listen(12886) ? (right + 2) : (right / 2))))))) : (circlePosition - ((ListenerUtil.mutListener.listen(12889) ? (right % 2) : (ListenerUtil.mutListener.listen(12888) ? (right * 2) : (ListenerUtil.mutListener.listen(12887) ? (right - 2) : (ListenerUtil.mutListener.listen(12886) ? (right + 2) : (right / 2))))))))))), right, (ListenerUtil.mutListener.listen(12901) ? (circlePosition % ((ListenerUtil.mutListener.listen(12897) ? (right % 2) : (ListenerUtil.mutListener.listen(12896) ? (right * 2) : (ListenerUtil.mutListener.listen(12895) ? (right - 2) : (ListenerUtil.mutListener.listen(12894) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12900) ? (circlePosition / ((ListenerUtil.mutListener.listen(12897) ? (right % 2) : (ListenerUtil.mutListener.listen(12896) ? (right * 2) : (ListenerUtil.mutListener.listen(12895) ? (right - 2) : (ListenerUtil.mutListener.listen(12894) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12899) ? (circlePosition * ((ListenerUtil.mutListener.listen(12897) ? (right % 2) : (ListenerUtil.mutListener.listen(12896) ? (right * 2) : (ListenerUtil.mutListener.listen(12895) ? (right - 2) : (ListenerUtil.mutListener.listen(12894) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12898) ? (circlePosition - ((ListenerUtil.mutListener.listen(12897) ? (right % 2) : (ListenerUtil.mutListener.listen(12896) ? (right * 2) : (ListenerUtil.mutListener.listen(12895) ? (right - 2) : (ListenerUtil.mutListener.listen(12894) ? (right + 2) : (right / 2))))))) : (circlePosition + ((ListenerUtil.mutListener.listen(12897) ? (right % 2) : (ListenerUtil.mutListener.listen(12896) ? (right * 2) : (ListenerUtil.mutListener.listen(12895) ? (right - 2) : (ListenerUtil.mutListener.listen(12894) ? (right + 2) : (right / 2))))))))))), 0, 360, false, this.circlePaint);
                }
                // draw plus/minus
                int plusLineY = (ListenerUtil.mutListener.listen(12910) ? (top % ((ListenerUtil.mutListener.listen(12906) ? (right % 2) : (ListenerUtil.mutListener.listen(12905) ? (right / 2) : (ListenerUtil.mutListener.listen(12904) ? (right - 2) : (ListenerUtil.mutListener.listen(12903) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12909) ? (top / ((ListenerUtil.mutListener.listen(12906) ? (right % 2) : (ListenerUtil.mutListener.listen(12905) ? (right / 2) : (ListenerUtil.mutListener.listen(12904) ? (right - 2) : (ListenerUtil.mutListener.listen(12903) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12908) ? (top * ((ListenerUtil.mutListener.listen(12906) ? (right % 2) : (ListenerUtil.mutListener.listen(12905) ? (right / 2) : (ListenerUtil.mutListener.listen(12904) ? (right - 2) : (ListenerUtil.mutListener.listen(12903) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12907) ? (top + ((ListenerUtil.mutListener.listen(12906) ? (right % 2) : (ListenerUtil.mutListener.listen(12905) ? (right / 2) : (ListenerUtil.mutListener.listen(12904) ? (right - 2) : (ListenerUtil.mutListener.listen(12903) ? (right + 2) : (right * 2))))))) : (top - ((ListenerUtil.mutListener.listen(12906) ? (right % 2) : (ListenerUtil.mutListener.listen(12905) ? (right / 2) : (ListenerUtil.mutListener.listen(12904) ? (right - 2) : (ListenerUtil.mutListener.listen(12903) ? (right + 2) : (right * 2)))))))))));
                if (!ListenerUtil.mutListener.listen(12927)) {
                    canvas.drawLine(left, (ListenerUtil.mutListener.listen(12918) ? (bottom % ((ListenerUtil.mutListener.listen(12914) ? (right % 2) : (ListenerUtil.mutListener.listen(12913) ? (right / 2) : (ListenerUtil.mutListener.listen(12912) ? (right - 2) : (ListenerUtil.mutListener.listen(12911) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12917) ? (bottom / ((ListenerUtil.mutListener.listen(12914) ? (right % 2) : (ListenerUtil.mutListener.listen(12913) ? (right / 2) : (ListenerUtil.mutListener.listen(12912) ? (right - 2) : (ListenerUtil.mutListener.listen(12911) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12916) ? (bottom * ((ListenerUtil.mutListener.listen(12914) ? (right % 2) : (ListenerUtil.mutListener.listen(12913) ? (right / 2) : (ListenerUtil.mutListener.listen(12912) ? (right - 2) : (ListenerUtil.mutListener.listen(12911) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12915) ? (bottom - ((ListenerUtil.mutListener.listen(12914) ? (right % 2) : (ListenerUtil.mutListener.listen(12913) ? (right / 2) : (ListenerUtil.mutListener.listen(12912) ? (right - 2) : (ListenerUtil.mutListener.listen(12911) ? (right + 2) : (right * 2))))))) : (bottom + ((ListenerUtil.mutListener.listen(12914) ? (right % 2) : (ListenerUtil.mutListener.listen(12913) ? (right / 2) : (ListenerUtil.mutListener.listen(12912) ? (right - 2) : (ListenerUtil.mutListener.listen(12911) ? (right + 2) : (right * 2))))))))))), right, (ListenerUtil.mutListener.listen(12926) ? (bottom % ((ListenerUtil.mutListener.listen(12922) ? (right % 2) : (ListenerUtil.mutListener.listen(12921) ? (right / 2) : (ListenerUtil.mutListener.listen(12920) ? (right - 2) : (ListenerUtil.mutListener.listen(12919) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12925) ? (bottom / ((ListenerUtil.mutListener.listen(12922) ? (right % 2) : (ListenerUtil.mutListener.listen(12921) ? (right / 2) : (ListenerUtil.mutListener.listen(12920) ? (right - 2) : (ListenerUtil.mutListener.listen(12919) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12924) ? (bottom * ((ListenerUtil.mutListener.listen(12922) ? (right % 2) : (ListenerUtil.mutListener.listen(12921) ? (right / 2) : (ListenerUtil.mutListener.listen(12920) ? (right - 2) : (ListenerUtil.mutListener.listen(12919) ? (right + 2) : (right * 2))))))) : (ListenerUtil.mutListener.listen(12923) ? (bottom - ((ListenerUtil.mutListener.listen(12922) ? (right % 2) : (ListenerUtil.mutListener.listen(12921) ? (right / 2) : (ListenerUtil.mutListener.listen(12920) ? (right - 2) : (ListenerUtil.mutListener.listen(12919) ? (right + 2) : (right * 2))))))) : (bottom + ((ListenerUtil.mutListener.listen(12922) ? (right % 2) : (ListenerUtil.mutListener.listen(12921) ? (right / 2) : (ListenerUtil.mutListener.listen(12920) ? (right - 2) : (ListenerUtil.mutListener.listen(12919) ? (right + 2) : (right * 2))))))))))), this.labelPaint);
                }
                if (!ListenerUtil.mutListener.listen(12928)) {
                    canvas.drawLine(left, plusLineY, right, plusLineY, this.labelPaint);
                }
                if (!ListenerUtil.mutListener.listen(12945)) {
                    canvas.drawLine(center, (ListenerUtil.mutListener.listen(12936) ? (plusLineY % ((ListenerUtil.mutListener.listen(12932) ? (right % 2) : (ListenerUtil.mutListener.listen(12931) ? (right * 2) : (ListenerUtil.mutListener.listen(12930) ? (right - 2) : (ListenerUtil.mutListener.listen(12929) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12935) ? (plusLineY / ((ListenerUtil.mutListener.listen(12932) ? (right % 2) : (ListenerUtil.mutListener.listen(12931) ? (right * 2) : (ListenerUtil.mutListener.listen(12930) ? (right - 2) : (ListenerUtil.mutListener.listen(12929) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12934) ? (plusLineY * ((ListenerUtil.mutListener.listen(12932) ? (right % 2) : (ListenerUtil.mutListener.listen(12931) ? (right * 2) : (ListenerUtil.mutListener.listen(12930) ? (right - 2) : (ListenerUtil.mutListener.listen(12929) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12933) ? (plusLineY + ((ListenerUtil.mutListener.listen(12932) ? (right % 2) : (ListenerUtil.mutListener.listen(12931) ? (right * 2) : (ListenerUtil.mutListener.listen(12930) ? (right - 2) : (ListenerUtil.mutListener.listen(12929) ? (right + 2) : (right / 2))))))) : (plusLineY - ((ListenerUtil.mutListener.listen(12932) ? (right % 2) : (ListenerUtil.mutListener.listen(12931) ? (right * 2) : (ListenerUtil.mutListener.listen(12930) ? (right - 2) : (ListenerUtil.mutListener.listen(12929) ? (right + 2) : (right / 2))))))))))), center, (ListenerUtil.mutListener.listen(12944) ? (plusLineY % ((ListenerUtil.mutListener.listen(12940) ? (right % 2) : (ListenerUtil.mutListener.listen(12939) ? (right * 2) : (ListenerUtil.mutListener.listen(12938) ? (right - 2) : (ListenerUtil.mutListener.listen(12937) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12943) ? (plusLineY / ((ListenerUtil.mutListener.listen(12940) ? (right % 2) : (ListenerUtil.mutListener.listen(12939) ? (right * 2) : (ListenerUtil.mutListener.listen(12938) ? (right - 2) : (ListenerUtil.mutListener.listen(12937) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12942) ? (plusLineY * ((ListenerUtil.mutListener.listen(12940) ? (right % 2) : (ListenerUtil.mutListener.listen(12939) ? (right * 2) : (ListenerUtil.mutListener.listen(12938) ? (right - 2) : (ListenerUtil.mutListener.listen(12937) ? (right + 2) : (right / 2))))))) : (ListenerUtil.mutListener.listen(12941) ? (plusLineY - ((ListenerUtil.mutListener.listen(12940) ? (right % 2) : (ListenerUtil.mutListener.listen(12939) ? (right * 2) : (ListenerUtil.mutListener.listen(12938) ? (right - 2) : (ListenerUtil.mutListener.listen(12937) ? (right + 2) : (right / 2))))))) : (plusLineY + ((ListenerUtil.mutListener.listen(12940) ? (right % 2) : (ListenerUtil.mutListener.listen(12939) ? (right * 2) : (ListenerUtil.mutListener.listen(12938) ? (right - 2) : (ListenerUtil.mutListener.listen(12937) ? (right + 2) : (right / 2))))))))))), this.labelPaint);
                }
            }
        }
    }

    public void setZoomFactor(float zoomFactor) {
        if (!ListenerUtil.mutListener.listen(13037)) {
            this.zoomFactor = zoomFactor;
        }
        if (!ListenerUtil.mutListener.listen(13045)) {
            if ((ListenerUtil.mutListener.listen(13042) ? (this.zoomFactor >= 0) : (ListenerUtil.mutListener.listen(13041) ? (this.zoomFactor <= 0) : (ListenerUtil.mutListener.listen(13040) ? (this.zoomFactor < 0) : (ListenerUtil.mutListener.listen(13039) ? (this.zoomFactor != 0) : (ListenerUtil.mutListener.listen(13038) ? (this.zoomFactor == 0) : (this.zoomFactor > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13044)) {
                    setVisibility(VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13043)) {
                    setVisibility(GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13046)) {
            invalidate();
        }
    }
}
