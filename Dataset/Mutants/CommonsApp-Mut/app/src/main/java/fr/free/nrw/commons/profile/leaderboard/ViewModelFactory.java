package fr.free.nrw.commons.profile.leaderboard;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class extends the ViewModelProvider.Factory and creates a ViewModelFactory class
 * for leaderboardListViewModel
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private OkHttpJsonApiClient okHttpJsonApiClient;

    private SessionManager sessionManager;

    @Inject
    public ViewModelFactory(OkHttpJsonApiClient okHttpJsonApiClient, SessionManager sessionManager) {
        if (!ListenerUtil.mutListener.listen(5519)) {
            this.okHttpJsonApiClient = okHttpJsonApiClient;
        }
        if (!ListenerUtil.mutListener.listen(5520)) {
            this.sessionManager = sessionManager;
        }
    }

    /**
     * Creats a new LeaderboardListViewModel
     * @param modelClass
     * @param <T>
     * @return
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LeaderboardListViewModel.class)) {
            return (T) new LeaderboardListViewModel(okHttpJsonApiClient, sessionManager);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
