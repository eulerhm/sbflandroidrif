package fr.free.nrw.commons.nearby;

import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NearbyFilterState {

    private boolean existsSelected;

    private boolean needPhotoSelected;

    private boolean wlmSelected;

    private int checkBoxTriState;

    private ArrayList<Label> selectedLabels;

    private static NearbyFilterState nearbyFılterStateInstance;

    /**
     * Define initial filter values here
     */
    private NearbyFilterState() {
        if (!ListenerUtil.mutListener.listen(3761)) {
            existsSelected = true;
        }
        if (!ListenerUtil.mutListener.listen(3762)) {
            needPhotoSelected = true;
        }
        if (!ListenerUtil.mutListener.listen(3763)) {
            wlmSelected = true;
        }
        if (!ListenerUtil.mutListener.listen(3764)) {
            // Unknown
            checkBoxTriState = -1;
        }
        if (!ListenerUtil.mutListener.listen(3765)) {
            // Initially empty
            selectedLabels = new ArrayList<>();
        }
    }

    public static NearbyFilterState getInstance() {
        if (!ListenerUtil.mutListener.listen(3767)) {
            if (nearbyFılterStateInstance == null) {
                if (!ListenerUtil.mutListener.listen(3766)) {
                    nearbyFılterStateInstance = new NearbyFilterState();
                }
            }
        }
        return nearbyFılterStateInstance;
    }

    public static void setSelectedLabels(ArrayList<Label> selectedLabels) {
        if (!ListenerUtil.mutListener.listen(3768)) {
            getInstance().selectedLabels = selectedLabels;
        }
    }

    public static void setExistsSelected(boolean existsSelected) {
        if (!ListenerUtil.mutListener.listen(3769)) {
            getInstance().existsSelected = existsSelected;
        }
    }

    public static void setNeedPhotoSelected(boolean needPhotoSelected) {
        if (!ListenerUtil.mutListener.listen(3770)) {
            getInstance().needPhotoSelected = needPhotoSelected;
        }
    }

    public static void setWlmSelected(final boolean wlmSelected) {
        if (!ListenerUtil.mutListener.listen(3771)) {
            getInstance().wlmSelected = wlmSelected;
        }
    }

    public boolean isWlmSelected() {
        return wlmSelected;
    }

    public boolean isExistsSelected() {
        return existsSelected;
    }

    public boolean isNeedPhotoSelected() {
        return needPhotoSelected;
    }

    public int getCheckBoxTriState() {
        return checkBoxTriState;
    }

    public ArrayList<Label> getSelectedLabels() {
        return selectedLabels;
    }
}
