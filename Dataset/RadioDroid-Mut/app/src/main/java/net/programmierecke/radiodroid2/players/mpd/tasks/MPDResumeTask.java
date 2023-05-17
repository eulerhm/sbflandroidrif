package net.programmierecke.radiodroid2.players.mpd.tasks;

import androidx.annotation.Nullable;
import net.programmierecke.radiodroid2.players.mpd.MPDAsyncTask;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MPDResumeTask extends MPDAsyncTask {

    public MPDResumeTask(@Nullable FailureCallback failureCallback) {
        if (!ListenerUtil.mutListener.listen(1037)) {
            setStages(new ReadStage[] { okReadStage(), statusReadStage(false) }, new WriteStage[] { (task, bufferedWriter) -> {
                bufferedWriter.write("command_list_begin\npause 0\nstatus\ncommand_list_end\n");
                return true;
            } }, failureCallback);
        }
    }
}
