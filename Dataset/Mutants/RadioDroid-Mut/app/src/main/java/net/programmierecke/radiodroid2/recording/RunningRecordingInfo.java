package net.programmierecke.radiodroid2.recording;

import java.io.FileOutputStream;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RunningRecordingInfo {

    private Recordable recordable;

    private String title;

    private String fileName;

    private FileOutputStream outputStream;

    private long bytesWritten;

    public Recordable getRecordable() {
        return recordable;
    }

    protected void setRecordable(Recordable recordable) {
        if (!ListenerUtil.mutListener.listen(1666)) {
            this.recordable = recordable;
        }
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        if (!ListenerUtil.mutListener.listen(1667)) {
            this.title = title;
        }
    }

    public String getFileName() {
        return fileName;
    }

    protected void setFileName(String fileName) {
        if (!ListenerUtil.mutListener.listen(1668)) {
            this.fileName = fileName;
        }
    }

    public FileOutputStream getOutputStream() {
        return outputStream;
    }

    protected void setOutputStream(FileOutputStream outputStream) {
        if (!ListenerUtil.mutListener.listen(1669)) {
            this.outputStream = outputStream;
        }
    }

    public long getBytesWritten() {
        return bytesWritten;
    }

    protected void setBytesWritten(long bytesWritten) {
        if (!ListenerUtil.mutListener.listen(1670)) {
            this.bytesWritten = bytesWritten;
        }
    }
}
