package fr.free.nrw.commons.utils;

import androidx.fragment.app.Fragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentUtils {

    /**
     * Utility function to check whether the fragment UI is still active or not
     * @param fragment
     * @return
     */
    public static boolean isFragmentUIActive(Fragment fragment) {
        return (ListenerUtil.mutListener.listen(2075) ? ((ListenerUtil.mutListener.listen(2074) ? ((ListenerUtil.mutListener.listen(2073) ? ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) || fragment.isAdded()) : ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) && fragment.isAdded())) || !fragment.isDetached()) : ((ListenerUtil.mutListener.listen(2073) ? ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) || fragment.isAdded()) : ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) && fragment.isAdded())) && !fragment.isDetached())) || !fragment.isRemoving()) : ((ListenerUtil.mutListener.listen(2074) ? ((ListenerUtil.mutListener.listen(2073) ? ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) || fragment.isAdded()) : ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) && fragment.isAdded())) || !fragment.isDetached()) : ((ListenerUtil.mutListener.listen(2073) ? ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) || fragment.isAdded()) : ((ListenerUtil.mutListener.listen(2072) ? (fragment != null || fragment.getActivity() != null) : (fragment != null && fragment.getActivity() != null)) && fragment.isAdded())) && !fragment.isDetached())) && !fragment.isRemoving()));
    }
}
