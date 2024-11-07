package com.ichi2.libanki.sched;

import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Decks;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Holds the data for a single node (row) in the deck due tree (the user-visible list
 * of decks and their counts). A node also contains a list of nodes that refer to the
 * next level of sub-decks for that particular deck (which can be an empty list).
 *
 * The names field is an array of names that build a deck name from a hierarchy (i.e., a nested
 * deck will have an entry for every level of nesting). While the python version interchanges
 * between a string and a list of strings throughout processing, we always use an array for
 * this field and use getNamePart(0) for those cases.
 *
 * T represents the type of children. Required for typing purpose only.
 */
public abstract class AbstractDeckTreeNode<T extends AbstractDeckTreeNode<T>> implements Comparable<AbstractDeckTreeNode<T>> {

    private final String mName;

    private final String[] mNameComponents;

    private final Collection mCol;

    private final long mDid;

    @Nullable
    private List<T> mChildren = null;

    public AbstractDeckTreeNode(Collection col, String mName, long mDid) {
        this.mCol = col;
        this.mName = mName;
        this.mDid = mDid;
        this.mNameComponents = Decks.path(mName);
    }

    /**
     * Sort on the head of the node.
     */
    @Override
    public int compareTo(AbstractDeckTreeNode<T> rhs) {
        int minDepth = (ListenerUtil.mutListener.listen(14460) ? (Math.min(getDepth(), rhs.getDepth()) % 1) : (ListenerUtil.mutListener.listen(14459) ? (Math.min(getDepth(), rhs.getDepth()) / 1) : (ListenerUtil.mutListener.listen(14458) ? (Math.min(getDepth(), rhs.getDepth()) * 1) : (ListenerUtil.mutListener.listen(14457) ? (Math.min(getDepth(), rhs.getDepth()) - 1) : (Math.min(getDepth(), rhs.getDepth()) + 1)))));
        if (!ListenerUtil.mutListener.listen(14472)) {
            {
                long _loopCounter288 = 0;
                // Consider each subdeck name in the ordering
                for (int i = 0; (ListenerUtil.mutListener.listen(14471) ? (i >= minDepth) : (ListenerUtil.mutListener.listen(14470) ? (i <= minDepth) : (ListenerUtil.mutListener.listen(14469) ? (i > minDepth) : (ListenerUtil.mutListener.listen(14468) ? (i != minDepth) : (ListenerUtil.mutListener.listen(14467) ? (i == minDepth) : (i < minDepth)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter288", ++_loopCounter288);
                    int cmp = mNameComponents[i].compareTo(rhs.mNameComponents[i]);
                    if (!ListenerUtil.mutListener.listen(14466)) {
                        if ((ListenerUtil.mutListener.listen(14465) ? (cmp >= 0) : (ListenerUtil.mutListener.listen(14464) ? (cmp <= 0) : (ListenerUtil.mutListener.listen(14463) ? (cmp > 0) : (ListenerUtil.mutListener.listen(14462) ? (cmp < 0) : (ListenerUtil.mutListener.listen(14461) ? (cmp != 0) : (cmp == 0))))))) {
                            continue;
                        }
                    }
                    return cmp;
                }
            }
        }
        // (i.e., the short one is an ancestor of the longer one).
        return Integer.compare(getDepth(), rhs.getDepth());
    }

    /**
     * Line representing this string without its children. Used in timbers only.
     */
    protected String toStringLine() {
        return String.format(Locale.US, "%s, %d, %s", mName, mDid, mChildren);
    }

    @Override
    @NonNull
    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (!ListenerUtil.mutListener.listen(14473)) {
            toString(buf);
        }
        return buf.toString();
    }

    protected void toString(StringBuffer buf) {
        if (!ListenerUtil.mutListener.listen(14480)) {
            {
                long _loopCounter289 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(14479) ? (i >= getDepth()) : (ListenerUtil.mutListener.listen(14478) ? (i <= getDepth()) : (ListenerUtil.mutListener.listen(14477) ? (i > getDepth()) : (ListenerUtil.mutListener.listen(14476) ? (i != getDepth()) : (ListenerUtil.mutListener.listen(14475) ? (i == getDepth()) : (i < getDepth())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter289", ++_loopCounter289);
                    if (!ListenerUtil.mutListener.listen(14474)) {
                        buf.append("  ");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14481)) {
            buf.append(toStringLine());
        }
        if (!ListenerUtil.mutListener.listen(14482)) {
            if (mChildren == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14484)) {
            {
                long _loopCounter290 = 0;
                for (T children : mChildren) {
                    ListenerUtil.loopListener.listen("_loopCounter290", ++_loopCounter290);
                    if (!ListenerUtil.mutListener.listen(14483)) {
                        children.toString(buf);
                    }
                }
            }
        }
    }

    /**
     * @return The full deck name, e.g. "A::B::C"
     */
    public String getFullDeckName() {
        return mName;
    }

    /**
     * For deck "A::B::C", `getDeckNameComponent(0)` returns "A",
     * `getDeckNameComponent(1)` returns "B", etc...
     */
    public String getDeckNameComponent(int part) {
        return mNameComponents[part];
    }

    /**
     * The part of the name displayed in deck picker, i.e. the
     * part that does not belong to its parents. E.g.  for deck
     * "A::B::C", returns "C".
     */
    public String getLastDeckNameComponent() {
        return getDeckNameComponent(getDepth());
    }

    public long getDid() {
        return mDid;
    }

    /**
     * @return The depth of a deck. Top level decks have depth 0,
     * their children have depth 1, etc... So "A::B::C" would have
     * depth 2.
     */
    public int getDepth() {
        return (ListenerUtil.mutListener.listen(14488) ? (mNameComponents.length % 1) : (ListenerUtil.mutListener.listen(14487) ? (mNameComponents.length / 1) : (ListenerUtil.mutListener.listen(14486) ? (mNameComponents.length * 1) : (ListenerUtil.mutListener.listen(14485) ? (mNameComponents.length + 1) : (mNameComponents.length - 1)))));
    }

    /**
     * @return The children of this deck. Note that they are set
     * in the data structure returned by DeckDueTree but are
     * always empty when the data structure is returned by
     * deckDueList.
     */
    public List<T> getChildren() {
        return mChildren;
    }

    /**
     * @return whether this node as any children.
     */
    public boolean hasChildren() {
        return (ListenerUtil.mutListener.listen(14489) ? (mChildren != null || !mChildren.isEmpty()) : (mChildren != null && !mChildren.isEmpty()));
    }

    public void setChildren(@NonNull List<T> children, boolean addRev) {
        if (!ListenerUtil.mutListener.listen(14490)) {
            // addRev present here because it needs to be overriden
            mChildren = children;
        }
    }

    @Override
    public int hashCode() {
        int childrenHash = mChildren == null ? 0 : mChildren.hashCode();
        return (ListenerUtil.mutListener.listen(14494) ? (getFullDeckName().hashCode() % childrenHash) : (ListenerUtil.mutListener.listen(14493) ? (getFullDeckName().hashCode() / childrenHash) : (ListenerUtil.mutListener.listen(14492) ? (getFullDeckName().hashCode() * childrenHash) : (ListenerUtil.mutListener.listen(14491) ? (getFullDeckName().hashCode() - childrenHash) : (getFullDeckName().hashCode() + childrenHash)))));
    }

    /**
     * Whether both elements have the same structure and numbers.
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!ListenerUtil.mutListener.listen(14495)) {
            if (!(object instanceof AbstractDeckTreeNode)) {
                return false;
            }
        }
        AbstractDeckTreeNode<?> tree = (AbstractDeckTreeNode) object;
        return (ListenerUtil.mutListener.listen(14499) ? ((ListenerUtil.mutListener.listen(14497) ? (Decks.equalName(getFullDeckName(), tree.getFullDeckName()) || // Would be the case if both are null, or the same pointer
        ((ListenerUtil.mutListener.listen(14496) ? (mChildren == null || tree.mChildren == null) : (mChildren == null && tree.mChildren == null)))) : (Decks.equalName(getFullDeckName(), tree.getFullDeckName()) && // Would be the case if both are null, or the same pointer
        ((ListenerUtil.mutListener.listen(14496) ? (mChildren == null || tree.mChildren == null) : (mChildren == null && tree.mChildren == null))))) && ((ListenerUtil.mutListener.listen(14498) ? (mChildren != null || mChildren.equals(tree.mChildren)) : (mChildren != null && mChildren.equals(tree.mChildren))))) : ((ListenerUtil.mutListener.listen(14497) ? (Decks.equalName(getFullDeckName(), tree.getFullDeckName()) || // Would be the case if both are null, or the same pointer
        ((ListenerUtil.mutListener.listen(14496) ? (mChildren == null || tree.mChildren == null) : (mChildren == null && tree.mChildren == null)))) : (Decks.equalName(getFullDeckName(), tree.getFullDeckName()) && // Would be the case if both are null, or the same pointer
        ((ListenerUtil.mutListener.listen(14496) ? (mChildren == null || tree.mChildren == null) : (mChildren == null && tree.mChildren == null))))) || ((ListenerUtil.mutListener.listen(14498) ? (mChildren != null || mChildren.equals(tree.mChildren)) : (mChildren != null && mChildren.equals(tree.mChildren))))));
    }

    public Collection getCol() {
        return mCol;
    }

    public boolean shouldDisplayCounts() {
        return false;
    }

    /* Number of new cards to see today known to be in this deck and its descendants. The number to show to user*/
    public int getNewCount() {
        throw new UnsupportedOperationException();
    }

    /* Number of lrn cards (or repetition) to see today known to be in this deck and its descendants. The number to show to user*/
    public int getLrnCount() {
        throw new UnsupportedOperationException();
    }

    /* Number of rev cards to see today known to be in this deck and its descendants. The number to show to user*/
    public int getRevCount() {
        throw new UnsupportedOperationException();
    }

    public boolean knownToHaveRep() {
        return false;
    }

    public abstract T withChildren(List<T> children);
}
