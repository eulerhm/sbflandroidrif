package fr.free.nrw.commons.logging;

import static org.acra.ACRA.getErrorReporter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import org.acra.data.CrashReportData;
import org.acra.sender.ReportSender;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.SessionManager;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Abstract class that implements Acra's log sender
 */
public abstract class LogsSender implements ReportSender {

    String mailTo;

    String logFileName;

    String emailSubject;

    String emailBody;

    private final SessionManager sessionManager;

    LogsSender(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Overrides send method of ACRA's ReportSender to send logs
     *
     * @param context
     * @param report
     */
    @Override
    public void send(@NonNull final Context context, @Nullable CrashReportData report) {
        if (!ListenerUtil.mutListener.listen(6264)) {
            sendLogs(context, report);
        }
    }

    /**
     * Gets zipped log files and sends it via email. Can be modified to change the send log mechanism
     *
     * @param context
     * @param report
     */
    private void sendLogs(Context context, CrashReportData report) {
        final Uri logFileUri = getZippedLogFileUri(context, report);
        if (!ListenerUtil.mutListener.listen(6267)) {
            if (logFileUri != null) {
                if (!ListenerUtil.mutListener.listen(6266)) {
                    sendEmail(context, logFileUri);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6265)) {
                    getErrorReporter().handleSilentException(null);
                }
            }
        }
    }

    /**
     *  Provides any extra information that you want to send. The return value will be
     *  delivered inside the report verbatim
     *
     *  @return
     */
    protected abstract String getExtraInfo();

    /**
     * Fires an intent to send email with logs
     *
     * @param context
     * @param logFileUri
     */
    private void sendEmail(Context context, Uri logFileUri) {
        String subject = emailSubject;
        String body = emailBody;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(6268)) {
            emailIntent.setType("message/rfc822");
        }
        if (!ListenerUtil.mutListener.listen(6269)) {
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { mailTo });
        }
        if (!ListenerUtil.mutListener.listen(6270)) {
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (!ListenerUtil.mutListener.listen(6271)) {
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        }
        if (!ListenerUtil.mutListener.listen(6272)) {
            emailIntent.putExtra(Intent.EXTRA_STREAM, logFileUri);
        }
        if (!ListenerUtil.mutListener.listen(6273)) {
            emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(6274)) {
            emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        if (!ListenerUtil.mutListener.listen(6275)) {
            context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.share_logs_using)));
        }
    }

    /**
     * Returns the URI for the zipped log file
     *
     * @param report
     * @return
     */
    private Uri getZippedLogFileUri(Context context, CrashReportData report) {
        try {
            StringBuilder builder = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(6278)) {
                if (report != null) {
                    if (!ListenerUtil.mutListener.listen(6277)) {
                        attachCrashInfo(report, builder);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6279)) {
                attachUserInfo(builder);
            }
            if (!ListenerUtil.mutListener.listen(6280)) {
                attachExtraInfo(builder);
            }
            byte[] metaData = builder.toString().getBytes(Charset.forName("UTF-8"));
            File zipFile = new File(LogUtils.getLogZipDirectory(), logFileName);
            if (!ListenerUtil.mutListener.listen(6281)) {
                writeLogToZipFile(metaData, zipFile);
            }
            return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", zipFile);
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(6276)) {
                Timber.w(e, "Error in generating log file");
            }
        }
        return null;
    }

    /**
     * Checks if there are any pending crash reports and attaches them to the logs
     *
     * @param report
     * @param builder
     */
    private void attachCrashInfo(CrashReportData report, StringBuilder builder) {
        if (!ListenerUtil.mutListener.listen(6282)) {
            if (report == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6283)) {
            builder.append(report);
        }
    }

    /**
     * Attaches username to the the meta_data file
     *
     * @param builder
     */
    private void attachUserInfo(StringBuilder builder) {
        if (!ListenerUtil.mutListener.listen(6284)) {
            builder.append("MediaWiki Username = ").append(sessionManager.getUserName()).append("\n");
        }
    }

    /**
     * Gets any extra meta information to be attached with the log files
     *
     * @param builder
     */
    private void attachExtraInfo(StringBuilder builder) {
        String infoToBeAttached = getExtraInfo();
        if (!ListenerUtil.mutListener.listen(6285)) {
            builder.append(infoToBeAttached);
        }
        if (!ListenerUtil.mutListener.listen(6286)) {
            builder.append("\n");
        }
    }

    /**
     * Zips the logs and meta information
     *
     * @param metaData
     * @param zipFile
     * @throws IOException
     */
    private void writeLogToZipFile(byte[] metaData, File zipFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ZipOutputStream zos = new ZipOutputStream(bos);
        File logDir = new File(LogUtils.getLogDirectory());
        if (!ListenerUtil.mutListener.listen(6293)) {
            if ((ListenerUtil.mutListener.listen(6292) ? (!logDir.exists() && (ListenerUtil.mutListener.listen(6291) ? (logDir.listFiles().length >= 0) : (ListenerUtil.mutListener.listen(6290) ? (logDir.listFiles().length <= 0) : (ListenerUtil.mutListener.listen(6289) ? (logDir.listFiles().length > 0) : (ListenerUtil.mutListener.listen(6288) ? (logDir.listFiles().length < 0) : (ListenerUtil.mutListener.listen(6287) ? (logDir.listFiles().length != 0) : (logDir.listFiles().length == 0))))))) : (!logDir.exists() || (ListenerUtil.mutListener.listen(6291) ? (logDir.listFiles().length >= 0) : (ListenerUtil.mutListener.listen(6290) ? (logDir.listFiles().length <= 0) : (ListenerUtil.mutListener.listen(6289) ? (logDir.listFiles().length > 0) : (ListenerUtil.mutListener.listen(6288) ? (logDir.listFiles().length < 0) : (ListenerUtil.mutListener.listen(6287) ? (logDir.listFiles().length != 0) : (logDir.listFiles().length == 0))))))))) {
                return;
            }
        }
        byte[] buffer = new byte[1024];
        if (!ListenerUtil.mutListener.listen(6305)) {
            {
                long _loopCounter99 = 0;
                for (File file : logDir.listFiles()) {
                    ListenerUtil.loopListener.listen("_loopCounter99", ++_loopCounter99);
                    if (!ListenerUtil.mutListener.listen(6294)) {
                        if (file.isDirectory()) {
                            continue;
                        }
                    }
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    if (!ListenerUtil.mutListener.listen(6295)) {
                        zos.putNextEntry(new ZipEntry(file.getName()));
                    }
                    int length;
                    if (!ListenerUtil.mutListener.listen(6302)) {
                        {
                            long _loopCounter98 = 0;
                            while ((ListenerUtil.mutListener.listen(6301) ? ((length = bis.read(buffer)) >= 0) : (ListenerUtil.mutListener.listen(6300) ? ((length = bis.read(buffer)) <= 0) : (ListenerUtil.mutListener.listen(6299) ? ((length = bis.read(buffer)) < 0) : (ListenerUtil.mutListener.listen(6298) ? ((length = bis.read(buffer)) != 0) : (ListenerUtil.mutListener.listen(6297) ? ((length = bis.read(buffer)) == 0) : ((length = bis.read(buffer)) > 0))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter98", ++_loopCounter98);
                                if (!ListenerUtil.mutListener.listen(6296)) {
                                    zos.write(buffer, 0, length);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6303)) {
                        zos.closeEntry();
                    }
                    if (!ListenerUtil.mutListener.listen(6304)) {
                        bis.close();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6306)) {
            // attach metadata as a separate file
            zos.putNextEntry(new ZipEntry("meta_data.txt"));
        }
        if (!ListenerUtil.mutListener.listen(6307)) {
            zos.write(metaData);
        }
        if (!ListenerUtil.mutListener.listen(6308)) {
            zos.closeEntry();
        }
        if (!ListenerUtil.mutListener.listen(6309)) {
            zos.flush();
        }
        if (!ListenerUtil.mutListener.listen(6310)) {
            zos.close();
        }
    }
}
