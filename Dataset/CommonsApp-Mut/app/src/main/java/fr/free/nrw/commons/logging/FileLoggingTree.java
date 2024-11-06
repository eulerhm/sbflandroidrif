package fr.free.nrw.commons.logging;

import android.util.Log;
import androidx.annotation.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;
import java.util.concurrent.Executor;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Extends Timber's debug tree to write logs to a file
 */
public class FileLoggingTree extends Timber.DebugTree implements LogLevelSettableTree {

    private final Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    private int logLevel;

    private final String logFileName;

    private int fileSize;

    private FixedWindowRollingPolicy rollingPolicy;

    private final Executor executor;

    public FileLoggingTree(int logLevel, String logFileName, String logDirectory, int fileSizeInKb, Executor executor) {
        if (!ListenerUtil.mutListener.listen(6311)) {
            this.logLevel = logLevel;
        }
        this.logFileName = logFileName;
        if (!ListenerUtil.mutListener.listen(6312)) {
            this.fileSize = fileSizeInKb;
        }
        if (!ListenerUtil.mutListener.listen(6313)) {
            configureLogger(logDirectory);
        }
        this.executor = executor;
    }

    /**
     * Can be overridden to change file's log level
     * @param logLevel
     */
    @Override
    public void setLogLevel(int logLevel) {
        if (!ListenerUtil.mutListener.listen(6314)) {
            this.logLevel = logLevel;
        }
    }

    /**
     * Check and log any message
     * @param priority
     * @param tag
     * @param message
     * @param t
     */
    @Override
    protected void log(final int priority, final String tag, @NonNull final String message, Throwable t) {
        if (!ListenerUtil.mutListener.listen(6315)) {
            executor.execute(() -> logMessage(priority, tag, message));
        }
    }

    /**
     * Log any message based on the priority
     * @param priority
     * @param tag
     * @param message
     */
    private void logMessage(int priority, String tag, String message) {
        String messageWithTag = String.format("[%s] : %s", tag, message);
        if (!ListenerUtil.mutListener.listen(6322)) {
            switch(priority) {
                case Log.VERBOSE:
                    if (!ListenerUtil.mutListener.listen(6316)) {
                        logger.trace(messageWithTag);
                    }
                    break;
                case Log.DEBUG:
                    if (!ListenerUtil.mutListener.listen(6317)) {
                        logger.debug(messageWithTag);
                    }
                    break;
                case Log.INFO:
                    if (!ListenerUtil.mutListener.listen(6318)) {
                        logger.info(messageWithTag);
                    }
                    break;
                case Log.WARN:
                    if (!ListenerUtil.mutListener.listen(6319)) {
                        logger.warn(messageWithTag);
                    }
                    break;
                case Log.ERROR:
                    if (!ListenerUtil.mutListener.listen(6320)) {
                        logger.error(messageWithTag);
                    }
                    break;
                case Log.ASSERT:
                    if (!ListenerUtil.mutListener.listen(6321)) {
                        logger.error(messageWithTag);
                    }
                    break;
            }
        }
    }

    /**
     * Checks if a particular log line should be logged in the file or not
     * @param priority
     * @return
     */
    @Override
    protected boolean isLoggable(int priority) {
        return (ListenerUtil.mutListener.listen(6327) ? (priority <= logLevel) : (ListenerUtil.mutListener.listen(6326) ? (priority > logLevel) : (ListenerUtil.mutListener.listen(6325) ? (priority < logLevel) : (ListenerUtil.mutListener.listen(6324) ? (priority != logLevel) : (ListenerUtil.mutListener.listen(6323) ? (priority == logLevel) : (priority >= logLevel))))));
    }

    /**
     * Configures the logger with a file size rolling policy (SizeBasedTriggeringPolicy)
     * https://github.com/tony19/logback-android/wiki
     * @param logDir
     */
    private void configureLogger(String logDir) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        if (!ListenerUtil.mutListener.listen(6328)) {
            loggerContext.reset();
        }
        RollingFileAppender<ILoggingEvent> rollingFileAppender = new RollingFileAppender<>();
        if (!ListenerUtil.mutListener.listen(6329)) {
            rollingFileAppender.setContext(loggerContext);
        }
        if (!ListenerUtil.mutListener.listen(6330)) {
            rollingFileAppender.setFile(logDir + "/" + logFileName + ".0.log");
        }
        if (!ListenerUtil.mutListener.listen(6331)) {
            rollingPolicy = new FixedWindowRollingPolicy();
        }
        if (!ListenerUtil.mutListener.listen(6332)) {
            rollingPolicy.setContext(loggerContext);
        }
        if (!ListenerUtil.mutListener.listen(6333)) {
            rollingPolicy.setMinIndex(1);
        }
        if (!ListenerUtil.mutListener.listen(6334)) {
            rollingPolicy.setMaxIndex(4);
        }
        if (!ListenerUtil.mutListener.listen(6335)) {
            rollingPolicy.setParent(rollingFileAppender);
        }
        if (!ListenerUtil.mutListener.listen(6336)) {
            rollingPolicy.setFileNamePattern(logDir + "/" + logFileName + ".%i.log");
        }
        if (!ListenerUtil.mutListener.listen(6337)) {
            rollingPolicy.start();
        }
        SizeBasedTriggeringPolicy<ILoggingEvent> triggeringPolicy = new SizeBasedTriggeringPolicy<>();
        if (!ListenerUtil.mutListener.listen(6338)) {
            triggeringPolicy.setContext(loggerContext);
        }
        if (!ListenerUtil.mutListener.listen(6339)) {
            triggeringPolicy.setMaxFileSize(String.format(Locale.ENGLISH, "%dKB", fileSize));
        }
        if (!ListenerUtil.mutListener.listen(6340)) {
            triggeringPolicy.start();
        }
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        if (!ListenerUtil.mutListener.listen(6341)) {
            encoder.setContext(loggerContext);
        }
        if (!ListenerUtil.mutListener.listen(6342)) {
            encoder.setPattern("%-27(%date{ISO8601}) [%-5level] [%thread] %msg%n");
        }
        if (!ListenerUtil.mutListener.listen(6343)) {
            encoder.start();
        }
        if (!ListenerUtil.mutListener.listen(6344)) {
            rollingFileAppender.setEncoder(encoder);
        }
        if (!ListenerUtil.mutListener.listen(6345)) {
            rollingFileAppender.setRollingPolicy(rollingPolicy);
        }
        if (!ListenerUtil.mutListener.listen(6346)) {
            rollingFileAppender.setTriggeringPolicy(triggeringPolicy);
        }
        if (!ListenerUtil.mutListener.listen(6347)) {
            rollingFileAppender.start();
        }
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        if (!ListenerUtil.mutListener.listen(6348)) {
            logger.addAppender(rollingFileAppender);
        }
    }
}
