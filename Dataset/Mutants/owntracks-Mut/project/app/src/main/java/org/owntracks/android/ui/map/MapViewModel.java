package org.owntracks.android.ui.map;

import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.data.repos.ContactsRepo;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.model.messages.MessageClear;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.services.LocationProcessor;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.SimpleIdlingResource;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class MapViewModel extends BaseViewModel<MapMvvm.View> implements MapMvvm.ViewModel<MapMvvm.View>, LocationSource, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnCameraMoveStartedListener {

    private final ContactsRepo contactsRepo;

    private final LocationProcessor locationProcessor;

    private FusedContact activeContact;

    private LocationSource.OnLocationChangedListener onLocationChangedListener;

    private MessageProcessor messageProcessor;

    private Location location;

    private static final int VIEW_FREE = 0;

    private static final int VIEW_CONTACT = 1;

    private static final int VIEW_DEVICE = 2;

    private static int mode = VIEW_DEVICE;

    private MutableLiveData<FusedContact> liveContact = new MutableLiveData<>();

    private MutableLiveData<Boolean> liveBottomSheetHidden = new MutableLiveData<>();

    private MutableLiveData<LatLng> liveCamera = new MutableLiveData<>();

    private final SimpleIdlingResource locationIdlingResource = new SimpleIdlingResource("locationIdlingResource", false);

    @Inject
    public MapViewModel(ContactsRepo contactsRepo, LocationProcessor locationRepo, MessageProcessor messageProcessor) {
        if (!ListenerUtil.mutListener.listen(1747)) {
            Timber.v("onCreate");
        }
        this.contactsRepo = contactsRepo;
        if (!ListenerUtil.mutListener.listen(1748)) {
            this.messageProcessor = messageProcessor;
        }
        this.locationProcessor = locationRepo;
    }

    @Override
    public void saveInstanceState(@NonNull Bundle outState) {
    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {
    }

    @Override
    public LocationSource getMapLocationSource() {
        return this;
    }

    @Override
    public GoogleMap.OnMapClickListener getOnMapClickListener() {
        return this;
    }

    @Override
    public GoogleMap.OnMarkerClickListener getOnMarkerClickListener() {
        return this;
    }

    @Override
    public void onMapReady() {
        if (!ListenerUtil.mutListener.listen(1750)) {
            {
                long _loopCounter17 = 0;
                for (Object c : contactsRepo.getAll().getValue().values()) {
                    ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                    if (!ListenerUtil.mutListener.listen(1749)) {
                        getView().updateMarker((FusedContact) c);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1765)) {
            if ((ListenerUtil.mutListener.listen(1756) ? ((ListenerUtil.mutListener.listen(1755) ? (mode >= VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1754) ? (mode <= VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1753) ? (mode > VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1752) ? (mode < VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1751) ? (mode != VIEW_CONTACT) : (mode == VIEW_CONTACT)))))) || activeContact != null) : ((ListenerUtil.mutListener.listen(1755) ? (mode >= VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1754) ? (mode <= VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1753) ? (mode > VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1752) ? (mode < VIEW_CONTACT) : (ListenerUtil.mutListener.listen(1751) ? (mode != VIEW_CONTACT) : (mode == VIEW_CONTACT)))))) && activeContact != null))) {
                if (!ListenerUtil.mutListener.listen(1764)) {
                    setViewModeContact(activeContact, true);
                }
            } else if ((ListenerUtil.mutListener.listen(1761) ? (mode >= VIEW_FREE) : (ListenerUtil.mutListener.listen(1760) ? (mode <= VIEW_FREE) : (ListenerUtil.mutListener.listen(1759) ? (mode > VIEW_FREE) : (ListenerUtil.mutListener.listen(1758) ? (mode < VIEW_FREE) : (ListenerUtil.mutListener.listen(1757) ? (mode != VIEW_FREE) : (mode == VIEW_FREE))))))) {
                if (!ListenerUtil.mutListener.listen(1763)) {
                    setViewModeFree();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1762)) {
                    setViewModeDevice();
                }
            }
        }
    }

    @Override
    public LiveData<FusedContact> getContact() {
        return liveContact;
    }

    @Override
    public LiveData<Boolean> getBottomSheetHidden() {
        return liveBottomSheetHidden;
    }

    @Override
    public LiveData<LatLng> getCenter() {
        return liveCamera;
    }

    @Override
    public void sendLocation() {
        if (!ListenerUtil.mutListener.listen(1766)) {
            locationProcessor.publishLocationMessage(MessageLocation.REPORT_TYPE_USER);
        }
    }

    private void setViewModeContact(@NonNull String contactId, boolean center) {
        FusedContact c = contactsRepo.getById(contactId);
        if (!ListenerUtil.mutListener.listen(1769)) {
            if (c != null) {
                if (!ListenerUtil.mutListener.listen(1768)) {
                    setViewModeContact(c, center);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1767)) {
                    Timber.e("contact not found %s, ", contactId);
                }
            }
        }
    }

    private void setViewModeContact(@NonNull FusedContact c, boolean center) {
        if (!ListenerUtil.mutListener.listen(1770)) {
            mode = VIEW_CONTACT;
        }
        if (!ListenerUtil.mutListener.listen(1771)) {
            Timber.v("contactId:%s, obj:%s ", c.getId(), activeContact);
        }
        if (!ListenerUtil.mutListener.listen(1772)) {
            activeContact = c;
        }
        if (!ListenerUtil.mutListener.listen(1773)) {
            liveContact.postValue(c);
        }
        if (!ListenerUtil.mutListener.listen(1774)) {
            liveBottomSheetHidden.postValue(false);
        }
        if (!ListenerUtil.mutListener.listen(1776)) {
            if (center)
                if (!ListenerUtil.mutListener.listen(1775)) {
                    liveCamera.postValue(c.getLatLng());
                }
        }
    }

    private void setViewModeFree() {
        if (!ListenerUtil.mutListener.listen(1777)) {
            Timber.v("setting view mode: VIEW_FREE");
        }
        if (!ListenerUtil.mutListener.listen(1778)) {
            mode = VIEW_FREE;
        }
        if (!ListenerUtil.mutListener.listen(1779)) {
            clearActiveContact();
        }
    }

    private void setViewModeDevice() {
        if (!ListenerUtil.mutListener.listen(1780)) {
            Timber.v("setting view mode: VIEW_DEVICE");
        }
        if (!ListenerUtil.mutListener.listen(1781)) {
            mode = VIEW_DEVICE;
        }
        if (!ListenerUtil.mutListener.listen(1782)) {
            clearActiveContact();
        }
        if (!ListenerUtil.mutListener.listen(1785)) {
            if (hasLocation()) {
                if (!ListenerUtil.mutListener.listen(1784)) {
                    liveCamera.postValue(getCurrentLocation());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1783)) {
                    Timber.e("no location available");
                }
            }
        }
    }

    @Override
    @Nullable
    public LatLng getCurrentLocation() {
        return location != null ? new LatLng(location.getLatitude(), location.getLongitude()) : null;
    }

    @Override
    @Bindable
    public FusedContact getActiveContact() {
        return activeContact;
    }

    @Override
    public void restore(@NonNull String contactId) {
        if (!ListenerUtil.mutListener.listen(1786)) {
            Timber.v("restoring contact id:%s", contactId);
        }
        if (!ListenerUtil.mutListener.listen(1787)) {
            setViewModeContact(contactId, true);
        }
    }

    @Override
    public boolean hasLocation() {
        return location != null;
    }

    private void clearActiveContact() {
        if (!ListenerUtil.mutListener.listen(1788)) {
            activeContact = null;
        }
        if (!ListenerUtil.mutListener.listen(1789)) {
            liveContact.postValue(null);
        }
        if (!ListenerUtil.mutListener.listen(1790)) {
            liveBottomSheetHidden.postValue(true);
        }
    }

    @Override
    public void onBottomSheetClick() {
        if (!ListenerUtil.mutListener.listen(1791)) {
            getView().setBottomSheetExpanded();
        }
    }

    @Override
    public void onMenuCenterDeviceClicked() {
        if (!ListenerUtil.mutListener.listen(1792)) {
            setViewModeDevice();
        }
    }

    @Override
    public void onClearContactClicked() {
        MessageClear m = new MessageClear();
        if (!ListenerUtil.mutListener.listen(1795)) {
            if (activeContact != null) {
                if (!ListenerUtil.mutListener.listen(1793)) {
                    m.setTopic(activeContact.getId());
                }
                if (!ListenerUtil.mutListener.listen(1794)) {
                    messageProcessor.queueMessageForSending(m);
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.FusedContactAdded e) {
        if (!ListenerUtil.mutListener.listen(1796)) {
            onEvent(e.getContact());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.FusedContactRemoved c) {
        if (!ListenerUtil.mutListener.listen(1799)) {
            if (c.getContact() == activeContact) {
                if (!ListenerUtil.mutListener.listen(1797)) {
                    clearActiveContact();
                }
                if (!ListenerUtil.mutListener.listen(1798)) {
                    setViewModeFree();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1800)) {
            getView().removeMarker(c.getContact());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FusedContact c) {
        if (!ListenerUtil.mutListener.listen(1801)) {
            getView().updateMarker(c);
        }
        if (!ListenerUtil.mutListener.listen(1804)) {
            if (c == activeContact) {
                if (!ListenerUtil.mutListener.listen(1802)) {
                    liveContact.postValue(c);
                }
                if (!ListenerUtil.mutListener.listen(1803)) {
                    liveCamera.postValue(c.getLatLng());
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.ModeChanged e) {
        if (!ListenerUtil.mutListener.listen(1805)) {
            getView().clearMarkers();
        }
        if (!ListenerUtil.mutListener.listen(1806)) {
            clearActiveContact();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.MonitoringChanged e) {
        if (!ListenerUtil.mutListener.listen(1807)) {
            getView().updateMonitoringModeMenu();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1, sticky = true)
    public void onEvent(@NonNull Location location) {
        if (!ListenerUtil.mutListener.listen(1808)) {
            Timber.v("location source updated");
        }
        if (!ListenerUtil.mutListener.listen(1809)) {
            this.location = location;
        }
        if (!ListenerUtil.mutListener.listen(1810)) {
            getView().enableLocationMenus();
        }
        if (!ListenerUtil.mutListener.listen(1811)) {
            locationIdlingResource.setIdleState(true);
        }
        if (!ListenerUtil.mutListener.listen(1818)) {
            if ((ListenerUtil.mutListener.listen(1816) ? (mode >= VIEW_DEVICE) : (ListenerUtil.mutListener.listen(1815) ? (mode <= VIEW_DEVICE) : (ListenerUtil.mutListener.listen(1814) ? (mode > VIEW_DEVICE) : (ListenerUtil.mutListener.listen(1813) ? (mode < VIEW_DEVICE) : (ListenerUtil.mutListener.listen(1812) ? (mode != VIEW_DEVICE) : (mode == VIEW_DEVICE))))))) {
                if (!ListenerUtil.mutListener.listen(1817)) {
                    liveCamera.postValue(getCurrentLocation());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1820)) {
            if (onLocationChangedListener != null) {
                if (!ListenerUtil.mutListener.listen(1819)) {
                    this.onLocationChangedListener.onLocationChanged(this.location);
                }
            }
        }
    }

    // Map Callback
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        if (!ListenerUtil.mutListener.listen(1821)) {
            Timber.v("location source activated");
        }
        if (!ListenerUtil.mutListener.listen(1822)) {
            this.onLocationChangedListener = onLocationChangedListener;
        }
        if (!ListenerUtil.mutListener.listen(1824)) {
            if (location != null)
                if (!ListenerUtil.mutListener.listen(1823)) {
                    this.onLocationChangedListener.onLocationChanged(location);
                }
        }
    }

    // Map Callback
    @Override
    public void deactivate() {
        if (!ListenerUtil.mutListener.listen(1825)) {
            onLocationChangedListener = null;
        }
    }

    // Map Callback
    @Override
    public void onMapClick(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(1826)) {
            setViewModeFree();
        }
    }

    // Map Callback
    @Override
    public boolean onMarkerClick(Marker marker) {
        if (!ListenerUtil.mutListener.listen(1828)) {
            if (marker.getTag() != null) {
                if (!ListenerUtil.mutListener.listen(1827)) {
                    setViewModeContact((String) marker.getTag(), false);
                }
            }
        }
        return true;
    }

    @Override
    public void onBottomSheetLongClick() {
        if (!ListenerUtil.mutListener.listen(1829)) {
            setViewModeContact(activeContact.getId(), true);
        }
    }

    @Override
    public GoogleMap.OnCameraMoveStartedListener getOnMapCameraMoveStartedListener() {
        return this;
    }

    @Override
    public void onCameraMoveStarted(int reason) {
        if (!ListenerUtil.mutListener.listen(1831)) {
            if (reason == REASON_GESTURE) {
                if (!ListenerUtil.mutListener.listen(1830)) {
                    setViewModeFree();
                }
            }
        }
    }

    public SimpleIdlingResource getLocationIdlingResource() {
        return locationIdlingResource;
    }
}
