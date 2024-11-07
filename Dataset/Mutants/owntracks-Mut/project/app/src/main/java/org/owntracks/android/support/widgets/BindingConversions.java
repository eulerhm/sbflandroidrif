package org.owntracks.android.support.widgets;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;
import androidx.databinding.InverseMethod;
import com.google.android.gms.location.Geofence;
import com.rengwuxian.materialedittext.MaterialEditText;
import org.owntracks.android.R;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.services.MessageProcessorEndpointMqtt;
import java.text.DateFormat;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BindingConversions {

    private static final String EMPTY_STRING = "";

    // XX to String
    @BindingConversion
    @InverseMethod("convertToInteger")
    public static String convertToString(@Nullable Integer d) {
        return d != null ? d.toString() : EMPTY_STRING;
    }

    @BindingConversion
    @InverseMethod("convertToIntegerZeroIsEmpty")
    public static String convertToStringZeroIsEmpty(@Nullable Integer d) {
        return (ListenerUtil.mutListener.listen(1032) ? (d != null || (ListenerUtil.mutListener.listen(1031) ? (d >= 0) : (ListenerUtil.mutListener.listen(1030) ? (d <= 0) : (ListenerUtil.mutListener.listen(1029) ? (d < 0) : (ListenerUtil.mutListener.listen(1028) ? (d != 0) : (ListenerUtil.mutListener.listen(1027) ? (d == 0) : (d > 0))))))) : (d != null && (ListenerUtil.mutListener.listen(1031) ? (d >= 0) : (ListenerUtil.mutListener.listen(1030) ? (d <= 0) : (ListenerUtil.mutListener.listen(1029) ? (d < 0) : (ListenerUtil.mutListener.listen(1028) ? (d != 0) : (ListenerUtil.mutListener.listen(1027) ? (d == 0) : (d > 0)))))))) ? d.toString() : EMPTY_STRING;
    }

    @BindingConversion
    public static String convertToString(@Nullable Long d) {
        return d != null ? d.toString() : EMPTY_STRING;
    }

    @BindingConversion
    public static String convertToString(boolean d) {
        return String.valueOf(d);
    }

    @BindingConversion
    public static String convertToString(String s) {
        return s != null ? s : EMPTY_STRING;
    }

    // XX to Integer
    @BindingConversion
    public static Integer convertToInteger(String d) {
        try {
            return Integer.parseInt(d);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @BindingConversion
    public static Integer convertToIntegerZeroIsEmpty(String d) {
        return convertToInteger(d);
    }

    // Misc
    @BindingAdapter({ "android:text" })
    public static void setText(TextView view, MessageProcessor.EndpointState state) {
        if (!ListenerUtil.mutListener.listen(1033)) {
            view.setText(state != null ? state.getLabel(view.getContext()) : view.getContext().getString(R.string.na));
        }
    }

    @BindingAdapter("met_helperText")
    public static void setVisibility(MaterialEditText view, String text) {
        if (!ListenerUtil.mutListener.listen(1034)) {
            view.setHelperText(text);
        }
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        if (!ListenerUtil.mutListener.listen(1035)) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    @BindingAdapter("relativeTimeSpanString")
    public static void setRelativeTimeSpanString(TextView view, long tstSeconds) {
        if (!ListenerUtil.mutListener.listen(1038)) {
            if (DateUtils.isToday(TimeUnit.SECONDS.toMillis(tstSeconds))) {
                if (!ListenerUtil.mutListener.listen(1037)) {
                    view.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(TimeUnit.SECONDS.toMillis(tstSeconds)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1036)) {
                    view.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(TimeUnit.SECONDS.toMillis(tstSeconds)));
                }
            }
        }
    }

    @BindingAdapter("lastTransition")
    public static void setLastTransition(TextView view, int transition) {
        if (!ListenerUtil.mutListener.listen(1042)) {
            switch(transition) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(1039)) {
                        view.setText(view.getResources().getString(R.string.region_unknown));
                    }
                    break;
                case Geofence.GEOFENCE_TRANSITION_ENTER:
                    if (!ListenerUtil.mutListener.listen(1040)) {
                        view.setText(view.getResources().getString(R.string.region_inside));
                    }
                    break;
                case Geofence.GEOFENCE_TRANSITION_EXIT:
                    if (!ListenerUtil.mutListener.listen(1041)) {
                        view.setText(view.getResources().getString(R.string.region_outside));
                    }
                    break;
            }
        }
    }

    public static int convertModeIdToLabelResId(int modeId) {
        switch(modeId) {
            case MessageProcessorEndpointHttp.MODE_ID:
                return R.string.mode_http_private_label;
            case MessageProcessorEndpointMqtt.MODE_ID:
            default:
                return R.string.mode_mqtt_private_label;
        }
    }
}
