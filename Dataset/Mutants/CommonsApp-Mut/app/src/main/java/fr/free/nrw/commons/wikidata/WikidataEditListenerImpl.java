package fr.free.nrw.commons.wikidata;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Listener for wikidata edits
 */
public class WikidataEditListenerImpl extends WikidataEditListener {

    public WikidataEditListenerImpl() {
    }

    /**
     * Fired when wikidata P18 edit is successful. If there's an active listener, then it is fired
     */
    @Override
    public void onSuccessfulWikidataEdit() {
        if (!ListenerUtil.mutListener.listen(6019)) {
            if (wikidataP18EditListener != null) {
                if (!ListenerUtil.mutListener.listen(6018)) {
                    wikidataP18EditListener.onWikidataEditSuccessful();
                }
            }
        }
    }
}
