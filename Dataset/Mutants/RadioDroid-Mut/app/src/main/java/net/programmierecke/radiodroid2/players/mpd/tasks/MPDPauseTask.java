package net.programmierecke.radiodroid2.players.mpd.tasks;

import androidx.annotation.Nullable;
import net.programmierecke.radiodroid2.players.mpd.MPDAsyncTask;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MPDPauseTask extends MPDAsyncTask {

    public MPDPauseTask(@Nullable FailureCallback failureCallback) {
        if (!ListenerUtil.mutListener.listen(1035)) {
            setStages(new MPDAsyncTask.ReadStage[] { okReadStage(), statusReadStage(false) }, new MPDAsyncTask.WriteStage[] { (task, bufferedWriter) -> {
                bufferedWriter.write("command_list_begin\npause 1\nstatus\ncommand_list_end\n");
                return true;
            } }, failureCallback);
        }
    }
}
