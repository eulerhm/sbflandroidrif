package fr.free.nrw.commons.review;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Entity to store reviewed/skipped images identifier
 */
@Entity(tableName = "reviewed-images")
public class ReviewEntity {

    @PrimaryKey
    @NonNull
    String imageId;

    public ReviewEntity(String imageId) {
        if (!ListenerUtil.mutListener.listen(5909)) {
            this.imageId = imageId;
        }
    }
}
