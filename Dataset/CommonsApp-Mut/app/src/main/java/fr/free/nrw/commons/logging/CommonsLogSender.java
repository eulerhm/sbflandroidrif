package fr.free.nrw.commons.logging;

import android.content.Context;
import android.os.Bundle;
import javax.inject.Inject;
import javax.inject.Singleton;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.utils.ConfigUtils;
import fr.free.nrw.commons.utils.DeviceInfoUtil;
import org.acra.data.CrashReportData;
import org.acra.sender.ReportSenderException;
import org.jetbrains.annotations.NotNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Class responsible for sending logs to developers
 */
@Singleton
public class CommonsLogSender extends LogsSender {

    private static final String LOGS_PRIVATE_EMAIL = "commons-app-android-private@googlegroups.com";

    private static final String LOGS_PRIVATE_EMAIL_SUBJECT = "Commons Android App (%s) Logs";

    private static final String BETA_LOGS_PRIVATE_EMAIL_SUBJECT = "Commons Beta Android App (%s) Logs";

    private SessionManager sessionManager;

    private Context context;

    @Inject
    public CommonsLogSender(SessionManager sessionManager, Context context) {
        super(sessionManager);
        if (!ListenerUtil.mutListener.listen(6349)) {
            this.sessionManager = sessionManager;
        }
        if (!ListenerUtil.mutListener.listen(6350)) {
            this.context = context;
        }
        boolean isBeta = ConfigUtils.isBetaFlavour();
        if (!ListenerUtil.mutListener.listen(6351)) {
            this.logFileName = isBeta ? "CommonsBetaAppLogs.zip" : "CommonsAppLogs.zip";
        }
        String emailSubjectFormat = isBeta ? BETA_LOGS_PRIVATE_EMAIL_SUBJECT : LOGS_PRIVATE_EMAIL_SUBJECT;
        if (!ListenerUtil.mutListener.listen(6352)) {
            this.emailSubject = String.format(emailSubjectFormat, sessionManager.getUserName());
        }
        if (!ListenerUtil.mutListener.listen(6353)) {
            this.emailBody = getExtraInfo();
        }
        if (!ListenerUtil.mutListener.listen(6354)) {
            this.mailTo = LOGS_PRIVATE_EMAIL;
        }
    }

    /**
     * Attach any extra meta information about user or device that might help in debugging
     * @return String with extra meta information useful for debugging
     */
    @Override
    public String getExtraInfo() {
        StringBuilder builder = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(6355)) {
            // Getting API Level
            builder.append("API level: ").append(DeviceInfoUtil.getAPILevel()).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(6356)) {
            // Getting Android Version
            builder.append("Android version: ").append(DeviceInfoUtil.getAndroidVersion()).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(6357)) {
            // Getting Device Manufacturer
            builder.append("Device manufacturer: ").append(DeviceInfoUtil.getDeviceManufacturer()).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(6358)) {
            // Getting Device Model
            builder.append("Device model: ").append(DeviceInfoUtil.getDeviceModel()).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(6359)) {
            // Getting Device Name
            builder.append("Device: ").append(DeviceInfoUtil.getDevice()).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(6360)) {
            // Getting Network Type
            builder.append("Network type: ").append(DeviceInfoUtil.getConnectionType(context)).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(6361)) {
            // Getting App Version
            builder.append("App version name: ").append(ConfigUtils.getVersionNameWithSha(context)).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(6362)) {
            // Getting Username
            builder.append("User name: ").append(sessionManager.getUserName()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean requiresForeground() {
        return false;
    }

    @Override
    public void send(@NotNull Context context, @NotNull CrashReportData crashReportData, @NotNull Bundle bundle) throws ReportSenderException {
    }
}
