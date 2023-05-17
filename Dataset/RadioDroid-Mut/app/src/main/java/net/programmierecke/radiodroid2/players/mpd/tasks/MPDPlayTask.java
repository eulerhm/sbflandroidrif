package net.programmierecke.radiodroid2.players.mpd.tasks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import net.programmierecke.radiodroid2.players.mpd.MPDAsyncTask;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MPDPlayTask extends MPDAsyncTask {

    private int songId = -1;

    public MPDPlayTask(@NonNull final String url, @Nullable FailureCallback failureCallback) {
        if (!ListenerUtil.mutListener.listen(1036)) {
            setStages(new MPDAsyncTask.ReadStage[] { okReadStage(), (task, result) -> {
                if (result.startsWith("Id:")) {
                    MPDPlayTask.this.songId = Integer.parseInt(result.substring(3, result.indexOf("\n")).trim());
                    return true;
                }
                return true;
            }, statusReadStage(false) }, new MPDAsyncTask.WriteStage[] { (task, bufferedWriter) -> {
                bufferedWriter.write(String.format("addid %s\n", url));
                return true;
            }, (task, bufferedWriter) -> {
                bufferedWriter.write(String.format("command_list_begin\nplayid %s\nstatus\ncommand_list_end\n", MPDPlayTask.this.songId));
                return true;
            } }, failureCallback);
        }
    }
}
