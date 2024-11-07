package fr.free.nrw.commons.media;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import org.wikipedia.dataclient.mwapi.MwResponse;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MwParseResponse extends MwResponse {

    @Nullable
    private MwParseResult parse;

    @Nullable
    public MwParseResult parse() {
        return parse;
    }

    public boolean success() {
        return parse != null;
    }

    @VisibleForTesting
    protected void setParse(@Nullable MwParseResult parse) {
        if (!ListenerUtil.mutListener.listen(9307)) {
            this.parse = parse;
        }
    }
}
