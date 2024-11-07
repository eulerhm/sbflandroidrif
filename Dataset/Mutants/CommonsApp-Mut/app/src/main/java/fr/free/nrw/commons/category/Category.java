package fr.free.nrw.commons.category;

import android.net.Uri;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Represents a category
 */
public class Category {

    private Uri contentUri;

    private String name;

    private String description;

    private String thumbnail;

    private Date lastUsed;

    private int timesUsed;

    public Category() {
    }

    public Category(Uri contentUri, String name, String description, String thumbnail, Date lastUsed, int timesUsed) {
        if (!ListenerUtil.mutListener.listen(380)) {
            this.contentUri = contentUri;
        }
        if (!ListenerUtil.mutListener.listen(381)) {
            this.name = name;
        }
        if (!ListenerUtil.mutListener.listen(382)) {
            this.description = description;
        }
        if (!ListenerUtil.mutListener.listen(383)) {
            this.thumbnail = thumbnail;
        }
        if (!ListenerUtil.mutListener.listen(384)) {
            this.lastUsed = lastUsed;
        }
        if (!ListenerUtil.mutListener.listen(385)) {
            this.timesUsed = timesUsed;
        }
    }

    /**
     * Gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies name
     *
     * @param name Category name
     */
    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(386)) {
            this.name = name;
        }
    }

    /**
     * Gets last used date
     *
     * @return Last used date
     */
    public Date getLastUsed() {
        // warning: Date objects are mutable.
        return (Date) lastUsed.clone();
    }

    /**
     * Generates new last used date
     */
    private void touch() {
        if (!ListenerUtil.mutListener.listen(387)) {
            lastUsed = new Date();
        }
    }

    /**
     * Gets no. of times the category is used
     *
     * @return no. of times used
     */
    public int getTimesUsed() {
        return timesUsed;
    }

    /**
     * Increments timesUsed by 1 and sets last used date as now.
     */
    public void incTimesUsed() {
        if (!ListenerUtil.mutListener.listen(388)) {
            timesUsed++;
        }
        if (!ListenerUtil.mutListener.listen(389)) {
            touch();
        }
    }

    /**
     * Gets the content URI for this category
     *
     * @return content URI
     */
    public Uri getContentUri() {
        return contentUri;
    }

    /**
     * Modifies the content URI - marking this category as already saved in the database
     *
     * @param contentUri the content URI
     */
    public void setContentUri(Uri contentUri) {
        if (!ListenerUtil.mutListener.listen(390)) {
            this.contentUri = contentUri;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setDescription(final String description) {
        if (!ListenerUtil.mutListener.listen(391)) {
            this.description = description;
        }
    }

    public void setThumbnail(final String thumbnail) {
        if (!ListenerUtil.mutListener.listen(392)) {
            this.thumbnail = thumbnail;
        }
    }
}
