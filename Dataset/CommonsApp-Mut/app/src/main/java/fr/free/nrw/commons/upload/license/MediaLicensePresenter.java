package fr.free.nrw.commons.upload.license;

import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.repository.UploadRepository;
import fr.free.nrw.commons.settings.Prefs;
import fr.free.nrw.commons.upload.license.MediaLicenseContract.View;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Added JavaDocs for MediaLicensePresenter
 */
public class MediaLicensePresenter implements MediaLicenseContract.UserActionListener {

    private static final MediaLicenseContract.View DUMMY = (MediaLicenseContract.View) Proxy.newProxyInstance(MediaLicenseContract.View.class.getClassLoader(), new Class[] { MediaLicenseContract.View.class }, (proxy, method, methodArgs) -> null);

    private final UploadRepository repository;

    private final JsonKvStore defaultKVStore;

    private MediaLicenseContract.View view = DUMMY;

    @Inject
    public MediaLicensePresenter(UploadRepository uploadRepository, @Named("default_preferences") JsonKvStore defaultKVStore) {
        this.repository = uploadRepository;
        this.defaultKVStore = defaultKVStore;
    }

    @Override
    public void onAttachView(View view) {
        if (!ListenerUtil.mutListener.listen(6421)) {
            this.view = view;
        }
    }

    @Override
    public void onDetachView() {
        if (!ListenerUtil.mutListener.listen(6422)) {
            this.view = DUMMY;
        }
    }

    /**
     * asks the repository for the available licenses, and informs the view on the same
     */
    @Override
    public void getLicenses() {
        List<String> licenses = repository.getLicenses();
        if (!ListenerUtil.mutListener.listen(6423)) {
            view.setLicenses(licenses);
        }
        String selectedLicense = defaultKVStore.getString(Prefs.DEFAULT_LICENSE, // CC_BY_SA_4 is the default one used by the commons web app
        Prefs.Licenses.CC_BY_SA_4);
        try {
            if (!ListenerUtil.mutListener.listen(6427)) {
                // I have to make sure that the stored default license was not one of the deprecated one's
                Utils.licenseNameFor(selectedLicense);
            }
        } catch (IllegalStateException exception) {
            if (!ListenerUtil.mutListener.listen(6424)) {
                Timber.e(exception.getMessage());
            }
            if (!ListenerUtil.mutListener.listen(6425)) {
                selectedLicense = Prefs.Licenses.CC_BY_SA_4;
            }
            if (!ListenerUtil.mutListener.listen(6426)) {
                defaultKVStore.putString(Prefs.DEFAULT_LICENSE, Prefs.Licenses.CC_BY_SA_4);
            }
        }
        if (!ListenerUtil.mutListener.listen(6428)) {
            view.setSelectedLicense(selectedLicense);
        }
    }

    /**
     * ask the repository to select a license for the current upload
     *
     * @param licenseName
     */
    @Override
    public void selectLicense(String licenseName) {
        if (!ListenerUtil.mutListener.listen(6429)) {
            repository.setSelectedLicense(licenseName);
        }
        if (!ListenerUtil.mutListener.listen(6430)) {
            view.updateLicenseSummary(repository.getSelectedLicense(), repository.getCount());
        }
    }

    @Override
    public boolean isWLMSupportedForThisPlace() {
        return repository.isWMLSupportedForThisPlace();
    }
}
