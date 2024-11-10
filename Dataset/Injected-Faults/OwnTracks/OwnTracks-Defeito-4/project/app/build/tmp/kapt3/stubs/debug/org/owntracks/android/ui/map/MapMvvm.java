package org.owntracks.android.ui.map;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u00a8\u0006\u0004"}, d2 = {"Lorg/owntracks/android/ui/map/MapMvvm;", "", "View", "ViewModel", "app_debug"})
public abstract interface MapMvvm {
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\r\u001a\u00020\u0003H&\u00a8\u0006\u000e"}, d2 = {"Lorg/owntracks/android/ui/map/MapMvvm$View;", "Lorg/owntracks/android/ui/base/view/MvvmView;", "clearMarkers", "", "enableLocationMenus", "removeMarker", "c", "Lorg/owntracks/android/model/FusedContact;", "setBottomSheetCollapsed", "setBottomSheetExpanded", "setBottomSheetHidden", "updateMarker", "contact", "updateMonitoringModeMenu", "app_debug"})
    public static abstract interface View extends org.owntracks.android.ui.base.view.MvvmView {
        
        public abstract void setBottomSheetExpanded();
        
        public abstract void setBottomSheetCollapsed();
        
        public abstract void setBottomSheetHidden();
        
        public abstract void updateMarker(@org.jetbrains.annotations.Nullable()
        org.owntracks.android.model.FusedContact contact);
        
        public abstract void removeMarker(@org.jetbrains.annotations.Nullable()
        org.owntracks.android.model.FusedContact c);
        
        public abstract void clearMarkers();
        
        public abstract void enableLocationMenus();
        
        public abstract void updateMonitoringModeMenu();
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\b\u0010%\u001a\u00020\nH&J\b\u0010&\u001a\u00020\'H&J\b\u0010(\u001a\u00020\'H&J\b\u0010)\u001a\u00020\'H&J\b\u0010*\u001a\u00020\'H&J\b\u0010+\u001a\u00020\'H&J\u0012\u0010,\u001a\u00020\'2\b\u0010-\u001a\u0004\u0018\u00010.H&J\b\u0010/\u001a\u00020\'H&R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\fR\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u000eX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u0004\u0018\u00010\"X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010$\u00a8\u00060"}, d2 = {"Lorg/owntracks/android/ui/map/MapMvvm$ViewModel;", "V", "Lorg/owntracks/android/ui/base/view/MvvmView;", "Lorg/owntracks/android/ui/base/viewmodel/MvvmViewModel;", "activeContact", "Lorg/owntracks/android/model/FusedContact;", "getActiveContact", "()Lorg/owntracks/android/model/FusedContact;", "bottomSheetHidden", "Landroidx/lifecycle/LiveData;", "", "getBottomSheetHidden", "()Landroidx/lifecycle/LiveData;", "center", "Lcom/google/android/gms/maps/model/LatLng;", "getCenter", "contact", "getContact", "currentLocation", "getCurrentLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "mapLocationSource", "Lcom/google/android/gms/maps/LocationSource;", "getMapLocationSource", "()Lcom/google/android/gms/maps/LocationSource;", "onMapCameraMoveStartedListener", "Lcom/google/android/gms/maps/GoogleMap$OnCameraMoveStartedListener;", "getOnMapCameraMoveStartedListener", "()Lcom/google/android/gms/maps/GoogleMap$OnCameraMoveStartedListener;", "onMapClickListener", "Lcom/google/android/gms/maps/GoogleMap$OnMapClickListener;", "getOnMapClickListener", "()Lcom/google/android/gms/maps/GoogleMap$OnMapClickListener;", "onMarkerClickListener", "Lcom/google/android/gms/maps/GoogleMap$OnMarkerClickListener;", "getOnMarkerClickListener", "()Lcom/google/android/gms/maps/GoogleMap$OnMarkerClickListener;", "hasLocation", "onBottomSheetClick", "", "onBottomSheetLongClick", "onClearContactClicked", "onMapReady", "onMenuCenterDeviceClicked", "restore", "contactId", "", "sendLocation", "app_debug"})
    public static abstract interface ViewModel<V extends org.owntracks.android.ui.base.view.MvvmView> extends org.owntracks.android.ui.base.viewmodel.MvvmViewModel<V> {
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.model.LatLng getCurrentLocation();
        
        @org.jetbrains.annotations.Nullable()
        @androidx.databinding.Bindable()
        public abstract org.owntracks.android.model.FusedContact getActiveContact();
        
        public abstract void onBottomSheetLongClick();
        
        public abstract void onBottomSheetClick();
        
        public abstract void onMenuCenterDeviceClicked();
        
        public abstract void onClearContactClicked();
        
        public abstract void restore(@org.jetbrains.annotations.Nullable()
        java.lang.String contactId);
        
        public abstract boolean hasLocation();
        
        public abstract void onMapReady();
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.LocationSource getMapLocationSource();
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.GoogleMap.OnMapClickListener getOnMapClickListener();
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.GoogleMap.OnMarkerClickListener getOnMarkerClickListener();
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener getOnMapCameraMoveStartedListener();
        
        @org.jetbrains.annotations.Nullable()
        public abstract androidx.lifecycle.LiveData<org.owntracks.android.model.FusedContact> getContact();
        
        @org.jetbrains.annotations.Nullable()
        public abstract androidx.lifecycle.LiveData<java.lang.Boolean> getBottomSheetHidden();
        
        @org.jetbrains.annotations.Nullable()
        public abstract androidx.lifecycle.LiveData<com.google.android.gms.maps.model.LatLng> getCenter();
        
        public abstract void sendLocation();
    }
}