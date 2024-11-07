package fr.free.nrw.commons.feedback;

import android.content.Context;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.AccountUtil;
import fr.free.nrw.commons.feedback.model.Feedback;
import fr.free.nrw.commons.utils.LangCodeUtils;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Creates a wikimedia recognizable format
 * from feedback information
 */
public class FeedbackContentCreator {

    private StringBuilder stringBuilder;

    private Feedback feedback;

    private Context context;

    public FeedbackContentCreator(Context context, Feedback feedback) {
        if (!ListenerUtil.mutListener.listen(3788)) {
            this.feedback = feedback;
        }
        if (!ListenerUtil.mutListener.listen(3789)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(3790)) {
            init();
        }
    }

    /**
     * Initializes the string buffer object to append content from feedback object
     */
    public void init() {
        if (!ListenerUtil.mutListener.listen(3791)) {
            stringBuilder = new StringBuilder();
        }
        if (!ListenerUtil.mutListener.listen(3792)) {
            stringBuilder.append("== ");
        }
        if (!ListenerUtil.mutListener.listen(3793)) {
            stringBuilder.append("Feedback from  ");
        }
        if (!ListenerUtil.mutListener.listen(3794)) {
            stringBuilder.append(AccountUtil.getUserName(context));
        }
        if (!ListenerUtil.mutListener.listen(3795)) {
            stringBuilder.append(" for version ");
        }
        if (!ListenerUtil.mutListener.listen(3796)) {
            stringBuilder.append(feedback.getVersion());
        }
        if (!ListenerUtil.mutListener.listen(3797)) {
            stringBuilder.append(" ==");
        }
        if (!ListenerUtil.mutListener.listen(3798)) {
            stringBuilder.append("\n");
        }
        if (!ListenerUtil.mutListener.listen(3799)) {
            stringBuilder.append(feedback.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(3800)) {
            stringBuilder.append("\n");
        }
        if (!ListenerUtil.mutListener.listen(3801)) {
            stringBuilder.append("\n");
        }
        if (!ListenerUtil.mutListener.listen(3807)) {
            if (feedback.getApiLevel() != null) {
                if (!ListenerUtil.mutListener.listen(3802)) {
                    stringBuilder.append("* ");
                }
                if (!ListenerUtil.mutListener.listen(3803)) {
                    stringBuilder.append(LangCodeUtils.getLocalizedResources(context, Locale.ENGLISH).getString(R.string.api_level));
                }
                if (!ListenerUtil.mutListener.listen(3804)) {
                    stringBuilder.append(": ");
                }
                if (!ListenerUtil.mutListener.listen(3805)) {
                    stringBuilder.append(feedback.getApiLevel());
                }
                if (!ListenerUtil.mutListener.listen(3806)) {
                    stringBuilder.append("\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3813)) {
            if (feedback.getAndroidVersion() != null) {
                if (!ListenerUtil.mutListener.listen(3808)) {
                    stringBuilder.append("* ");
                }
                if (!ListenerUtil.mutListener.listen(3809)) {
                    stringBuilder.append(LangCodeUtils.getLocalizedResources(context, Locale.ENGLISH).getString(R.string.android_version));
                }
                if (!ListenerUtil.mutListener.listen(3810)) {
                    stringBuilder.append(": ");
                }
                if (!ListenerUtil.mutListener.listen(3811)) {
                    stringBuilder.append(feedback.getAndroidVersion());
                }
                if (!ListenerUtil.mutListener.listen(3812)) {
                    stringBuilder.append("\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3819)) {
            if (feedback.getDeviceManufacturer() != null) {
                if (!ListenerUtil.mutListener.listen(3814)) {
                    stringBuilder.append("* ");
                }
                if (!ListenerUtil.mutListener.listen(3815)) {
                    stringBuilder.append(LangCodeUtils.getLocalizedResources(context, Locale.ENGLISH).getString(R.string.device_manufacturer));
                }
                if (!ListenerUtil.mutListener.listen(3816)) {
                    stringBuilder.append(": ");
                }
                if (!ListenerUtil.mutListener.listen(3817)) {
                    stringBuilder.append(feedback.getDeviceManufacturer());
                }
                if (!ListenerUtil.mutListener.listen(3818)) {
                    stringBuilder.append("\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3825)) {
            if (feedback.getDeviceModel() != null) {
                if (!ListenerUtil.mutListener.listen(3820)) {
                    stringBuilder.append("* ");
                }
                if (!ListenerUtil.mutListener.listen(3821)) {
                    stringBuilder.append(LangCodeUtils.getLocalizedResources(context, Locale.ENGLISH).getString(R.string.device_model));
                }
                if (!ListenerUtil.mutListener.listen(3822)) {
                    stringBuilder.append(": ");
                }
                if (!ListenerUtil.mutListener.listen(3823)) {
                    stringBuilder.append(feedback.getDeviceModel());
                }
                if (!ListenerUtil.mutListener.listen(3824)) {
                    stringBuilder.append("\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3831)) {
            if (feedback.getDevice() != null) {
                if (!ListenerUtil.mutListener.listen(3826)) {
                    stringBuilder.append("* ");
                }
                if (!ListenerUtil.mutListener.listen(3827)) {
                    stringBuilder.append(LangCodeUtils.getLocalizedResources(context, Locale.ENGLISH).getString(R.string.device_name));
                }
                if (!ListenerUtil.mutListener.listen(3828)) {
                    stringBuilder.append(": ");
                }
                if (!ListenerUtil.mutListener.listen(3829)) {
                    stringBuilder.append(feedback.getDevice());
                }
                if (!ListenerUtil.mutListener.listen(3830)) {
                    stringBuilder.append("\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3837)) {
            if (feedback.getNetworkType() != null) {
                if (!ListenerUtil.mutListener.listen(3832)) {
                    stringBuilder.append("* ");
                }
                if (!ListenerUtil.mutListener.listen(3833)) {
                    stringBuilder.append(LangCodeUtils.getLocalizedResources(context, Locale.ENGLISH).getString(R.string.network_type));
                }
                if (!ListenerUtil.mutListener.listen(3834)) {
                    stringBuilder.append(": ");
                }
                if (!ListenerUtil.mutListener.listen(3835)) {
                    stringBuilder.append(feedback.getNetworkType());
                }
                if (!ListenerUtil.mutListener.listen(3836)) {
                    stringBuilder.append("\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3838)) {
            stringBuilder.append("~~~~");
        }
        if (!ListenerUtil.mutListener.listen(3839)) {
            stringBuilder.append("\n");
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
