package fr.free.nrw.commons.wikidata;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class WikidataEditListener {

    protected WikidataP18EditListener wikidataP18EditListener;

    public abstract void onSuccessfulWikidataEdit();

    public void setAuthenticationStateListener(WikidataP18EditListener wikidataP18EditListener) {
        if (!ListenerUtil.mutListener.listen(6020)) {
            this.wikidataP18EditListener = wikidataP18EditListener;
        }
    }

    public interface WikidataP18EditListener {

        void onWikidataEditSuccessful();
    }
}
