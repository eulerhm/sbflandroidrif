package com.ichi2.libanki.sched;

import java.util.Arrays;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Represents the three counts shown in deck picker and reviewer. Semantically more meaningful than int[]
 */
public class Counts {

    public enum Queue {

        NEW, LRN, REV
    }

    private int mNew;

    private int mLrn;

    private int mRev;

    public Counts() {
        this(0, 0, 0);
    }

    public Counts(int new_, int lrn, int rev) {
        if (!ListenerUtil.mutListener.listen(14513)) {
            mNew = new_;
        }
        if (!ListenerUtil.mutListener.listen(14514)) {
            mLrn = lrn;
        }
        if (!ListenerUtil.mutListener.listen(14515)) {
            mRev = rev;
        }
    }

    public int getLrn() {
        return mLrn;
    }

    public int getNew() {
        return mNew;
    }

    public int getRev() {
        return mRev;
    }

    /**
     * @param index Queue in which it elements are added
     * @param number How much to add.
     */
    public void changeCount(@NonNull Queue index, int number) {
        if (!ListenerUtil.mutListener.listen(14519)) {
            switch(index) {
                case NEW:
                    if (!ListenerUtil.mutListener.listen(14516)) {
                        mNew += number;
                    }
                    break;
                case LRN:
                    if (!ListenerUtil.mutListener.listen(14517)) {
                        mLrn += number;
                    }
                    break;
                case REV:
                    if (!ListenerUtil.mutListener.listen(14518)) {
                        mRev += number;
                    }
                    break;
                default:
                    throw new RuntimeException("Index " + index + " does not exists.");
            }
        }
    }

    public void addNew(int new_) {
        if (!ListenerUtil.mutListener.listen(14520)) {
            mNew += new_;
        }
    }

    public void addLrn(int lrn) {
        if (!ListenerUtil.mutListener.listen(14521)) {
            mLrn += lrn;
        }
    }

    public void addRev(int rev) {
        if (!ListenerUtil.mutListener.listen(14522)) {
            mRev += rev;
        }
    }

    /**
     * @return the sum of the three counts
     */
    public int count() {
        return (ListenerUtil.mutListener.listen(14530) ? ((ListenerUtil.mutListener.listen(14526) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(14525) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(14524) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(14523) ? (mNew - mLrn) : (mNew + mLrn))))) % mRev) : (ListenerUtil.mutListener.listen(14529) ? ((ListenerUtil.mutListener.listen(14526) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(14525) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(14524) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(14523) ? (mNew - mLrn) : (mNew + mLrn))))) / mRev) : (ListenerUtil.mutListener.listen(14528) ? ((ListenerUtil.mutListener.listen(14526) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(14525) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(14524) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(14523) ? (mNew - mLrn) : (mNew + mLrn))))) * mRev) : (ListenerUtil.mutListener.listen(14527) ? ((ListenerUtil.mutListener.listen(14526) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(14525) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(14524) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(14523) ? (mNew - mLrn) : (mNew + mLrn))))) - mRev) : ((ListenerUtil.mutListener.listen(14526) ? (mNew % mLrn) : (ListenerUtil.mutListener.listen(14525) ? (mNew / mLrn) : (ListenerUtil.mutListener.listen(14524) ? (mNew * mLrn) : (ListenerUtil.mutListener.listen(14523) ? (mNew - mLrn) : (mNew + mLrn))))) + mRev)))));
    }

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(14531)) {
            if (this == o) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(14533)) {
            if ((ListenerUtil.mutListener.listen(14532) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass()))) {
                return false;
            }
        }
        Counts counts = (Counts) o;
        return (ListenerUtil.mutListener.listen(14550) ? ((ListenerUtil.mutListener.listen(14544) ? ((ListenerUtil.mutListener.listen(14538) ? (mNew >= counts.mNew) : (ListenerUtil.mutListener.listen(14537) ? (mNew <= counts.mNew) : (ListenerUtil.mutListener.listen(14536) ? (mNew > counts.mNew) : (ListenerUtil.mutListener.listen(14535) ? (mNew < counts.mNew) : (ListenerUtil.mutListener.listen(14534) ? (mNew != counts.mNew) : (mNew == counts.mNew)))))) || (ListenerUtil.mutListener.listen(14543) ? (mRev >= counts.mRev) : (ListenerUtil.mutListener.listen(14542) ? (mRev <= counts.mRev) : (ListenerUtil.mutListener.listen(14541) ? (mRev > counts.mRev) : (ListenerUtil.mutListener.listen(14540) ? (mRev < counts.mRev) : (ListenerUtil.mutListener.listen(14539) ? (mRev != counts.mRev) : (mRev == counts.mRev))))))) : ((ListenerUtil.mutListener.listen(14538) ? (mNew >= counts.mNew) : (ListenerUtil.mutListener.listen(14537) ? (mNew <= counts.mNew) : (ListenerUtil.mutListener.listen(14536) ? (mNew > counts.mNew) : (ListenerUtil.mutListener.listen(14535) ? (mNew < counts.mNew) : (ListenerUtil.mutListener.listen(14534) ? (mNew != counts.mNew) : (mNew == counts.mNew)))))) && (ListenerUtil.mutListener.listen(14543) ? (mRev >= counts.mRev) : (ListenerUtil.mutListener.listen(14542) ? (mRev <= counts.mRev) : (ListenerUtil.mutListener.listen(14541) ? (mRev > counts.mRev) : (ListenerUtil.mutListener.listen(14540) ? (mRev < counts.mRev) : (ListenerUtil.mutListener.listen(14539) ? (mRev != counts.mRev) : (mRev == counts.mRev)))))))) || (ListenerUtil.mutListener.listen(14549) ? (mLrn >= counts.mLrn) : (ListenerUtil.mutListener.listen(14548) ? (mLrn <= counts.mLrn) : (ListenerUtil.mutListener.listen(14547) ? (mLrn > counts.mLrn) : (ListenerUtil.mutListener.listen(14546) ? (mLrn < counts.mLrn) : (ListenerUtil.mutListener.listen(14545) ? (mLrn != counts.mLrn) : (mLrn == counts.mLrn))))))) : ((ListenerUtil.mutListener.listen(14544) ? ((ListenerUtil.mutListener.listen(14538) ? (mNew >= counts.mNew) : (ListenerUtil.mutListener.listen(14537) ? (mNew <= counts.mNew) : (ListenerUtil.mutListener.listen(14536) ? (mNew > counts.mNew) : (ListenerUtil.mutListener.listen(14535) ? (mNew < counts.mNew) : (ListenerUtil.mutListener.listen(14534) ? (mNew != counts.mNew) : (mNew == counts.mNew)))))) || (ListenerUtil.mutListener.listen(14543) ? (mRev >= counts.mRev) : (ListenerUtil.mutListener.listen(14542) ? (mRev <= counts.mRev) : (ListenerUtil.mutListener.listen(14541) ? (mRev > counts.mRev) : (ListenerUtil.mutListener.listen(14540) ? (mRev < counts.mRev) : (ListenerUtil.mutListener.listen(14539) ? (mRev != counts.mRev) : (mRev == counts.mRev))))))) : ((ListenerUtil.mutListener.listen(14538) ? (mNew >= counts.mNew) : (ListenerUtil.mutListener.listen(14537) ? (mNew <= counts.mNew) : (ListenerUtil.mutListener.listen(14536) ? (mNew > counts.mNew) : (ListenerUtil.mutListener.listen(14535) ? (mNew < counts.mNew) : (ListenerUtil.mutListener.listen(14534) ? (mNew != counts.mNew) : (mNew == counts.mNew)))))) && (ListenerUtil.mutListener.listen(14543) ? (mRev >= counts.mRev) : (ListenerUtil.mutListener.listen(14542) ? (mRev <= counts.mRev) : (ListenerUtil.mutListener.listen(14541) ? (mRev > counts.mRev) : (ListenerUtil.mutListener.listen(14540) ? (mRev < counts.mRev) : (ListenerUtil.mutListener.listen(14539) ? (mRev != counts.mRev) : (mRev == counts.mRev)))))))) && (ListenerUtil.mutListener.listen(14549) ? (mLrn >= counts.mLrn) : (ListenerUtil.mutListener.listen(14548) ? (mLrn <= counts.mLrn) : (ListenerUtil.mutListener.listen(14547) ? (mLrn > counts.mLrn) : (ListenerUtil.mutListener.listen(14546) ? (mLrn < counts.mLrn) : (ListenerUtil.mutListener.listen(14545) ? (mLrn != counts.mLrn) : (mLrn == counts.mLrn))))))));
    }

    @Override
    public int hashCode() {
        return Arrays.asList(mNew, mRev, mLrn).hashCode();
    }
}
