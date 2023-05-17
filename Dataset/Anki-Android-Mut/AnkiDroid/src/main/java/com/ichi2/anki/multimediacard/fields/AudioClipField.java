package com.ichi2.anki.multimediacard.fields;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Implementation of Audio Clip field type
 */
public class AudioClipField extends AudioField {

    private static final long serialVersionUID = 2937641017832762987L;

    @Override
    public EFieldType getType() {
        return EFieldType.AUDIO_CLIP;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setHasTemporaryMedia(boolean hasTemporaryMedia) {
        if (!ListenerUtil.mutListener.listen(1503)) {
            mHasTemporaryMedia = hasTemporaryMedia;
        }
    }

    @Override
    public boolean hasTemporaryMedia() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
    }
}
