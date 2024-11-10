package org.owntracks.android;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.owntracks.android.databinding.UiContactsBindingImpl;
import org.owntracks.android.databinding.UiMapBindingImpl;
import org.owntracks.android.databinding.UiPreferencesBindingImpl;
import org.owntracks.android.databinding.UiPreferencesConnectionBindingImpl;
import org.owntracks.android.databinding.UiPreferencesConnectionHostHttpBindingImpl;
import org.owntracks.android.databinding.UiPreferencesConnectionHostMqttBindingImpl;
import org.owntracks.android.databinding.UiPreferencesConnectionIdentificationBindingImpl;
import org.owntracks.android.databinding.UiPreferencesConnectionModeBindingImpl;
import org.owntracks.android.databinding.UiPreferencesConnectionParametersBindingImpl;
import org.owntracks.android.databinding.UiPreferencesConnectionSecurityBindingImpl;
import org.owntracks.android.databinding.UiPreferencesEditorBindingImpl;
import org.owntracks.android.databinding.UiPreferencesLoadBindingImpl;
import org.owntracks.android.databinding.UiPreferencesLogsBindingImpl;
import org.owntracks.android.databinding.UiRegionBindingImpl;
import org.owntracks.android.databinding.UiRegionsBindingImpl;
import org.owntracks.android.databinding.UiRowContactBindingImpl;
import org.owntracks.android.databinding.UiRowRegionBindingImpl;
import org.owntracks.android.databinding.UiStatusBindingImpl;
import org.owntracks.android.databinding.UiWelcomeBindingImpl;
import org.owntracks.android.databinding.UiWelcomeFinishBindingImpl;
import org.owntracks.android.databinding.UiWelcomeIntroBindingImpl;
import org.owntracks.android.databinding.UiWelcomePermissionsBindingImpl;
import org.owntracks.android.databinding.UiWelcomePlayBindingImpl;
import org.owntracks.android.databinding.UiWelcomeVersionBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_UICONTACTS = 1;

  private static final int LAYOUT_UIMAP = 2;

  private static final int LAYOUT_UIPREFERENCES = 3;

  private static final int LAYOUT_UIPREFERENCESCONNECTION = 4;

  private static final int LAYOUT_UIPREFERENCESCONNECTIONHOSTHTTP = 5;

  private static final int LAYOUT_UIPREFERENCESCONNECTIONHOSTMQTT = 6;

  private static final int LAYOUT_UIPREFERENCESCONNECTIONIDENTIFICATION = 7;

  private static final int LAYOUT_UIPREFERENCESCONNECTIONMODE = 8;

  private static final int LAYOUT_UIPREFERENCESCONNECTIONPARAMETERS = 9;

  private static final int LAYOUT_UIPREFERENCESCONNECTIONSECURITY = 10;

  private static final int LAYOUT_UIPREFERENCESEDITOR = 11;

  private static final int LAYOUT_UIPREFERENCESLOAD = 12;

  private static final int LAYOUT_UIPREFERENCESLOGS = 13;

  private static final int LAYOUT_UIREGION = 14;

  private static final int LAYOUT_UIREGIONS = 15;

  private static final int LAYOUT_UIROWCONTACT = 16;

  private static final int LAYOUT_UIROWREGION = 17;

  private static final int LAYOUT_UISTATUS = 18;

  private static final int LAYOUT_UIWELCOME = 19;

  private static final int LAYOUT_UIWELCOMEFINISH = 20;

  private static final int LAYOUT_UIWELCOMEINTRO = 21;

  private static final int LAYOUT_UIWELCOMEPERMISSIONS = 22;

  private static final int LAYOUT_UIWELCOMEPLAY = 23;

  private static final int LAYOUT_UIWELCOMEVERSION = 24;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(24);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_contacts, LAYOUT_UICONTACTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_map, LAYOUT_UIMAP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences, LAYOUT_UIPREFERENCES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_connection, LAYOUT_UIPREFERENCESCONNECTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_connection_host_http, LAYOUT_UIPREFERENCESCONNECTIONHOSTHTTP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_connection_host_mqtt, LAYOUT_UIPREFERENCESCONNECTIONHOSTMQTT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_connection_identification, LAYOUT_UIPREFERENCESCONNECTIONIDENTIFICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_connection_mode, LAYOUT_UIPREFERENCESCONNECTIONMODE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_connection_parameters, LAYOUT_UIPREFERENCESCONNECTIONPARAMETERS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_connection_security, LAYOUT_UIPREFERENCESCONNECTIONSECURITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_editor, LAYOUT_UIPREFERENCESEDITOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_load, LAYOUT_UIPREFERENCESLOAD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_preferences_logs, LAYOUT_UIPREFERENCESLOGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_region, LAYOUT_UIREGION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_regions, LAYOUT_UIREGIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_row_contact, LAYOUT_UIROWCONTACT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_row_region, LAYOUT_UIROWREGION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_status, LAYOUT_UISTATUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_welcome, LAYOUT_UIWELCOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_welcome_finish, LAYOUT_UIWELCOMEFINISH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_welcome_intro, LAYOUT_UIWELCOMEINTRO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_welcome_permissions, LAYOUT_UIWELCOMEPERMISSIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_welcome_play, LAYOUT_UIWELCOMEPLAY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(org.owntracks.android.R.layout.ui_welcome_version, LAYOUT_UIWELCOMEVERSION);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_UICONTACTS: {
          if ("layout/ui_contacts_0".equals(tag)) {
            return new UiContactsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_contacts is invalid. Received: " + tag);
        }
        case  LAYOUT_UIMAP: {
          if ("layout/ui_map_0".equals(tag)) {
            return new UiMapBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_map is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCES: {
          if ("layout/ui_preferences_0".equals(tag)) {
            return new UiPreferencesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESCONNECTION: {
          if ("layout/ui_preferences_connection_0".equals(tag)) {
            return new UiPreferencesConnectionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_connection is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESCONNECTIONHOSTHTTP: {
          if ("layout/ui_preferences_connection_host_http_0".equals(tag)) {
            return new UiPreferencesConnectionHostHttpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_connection_host_http is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESCONNECTIONHOSTMQTT: {
          if ("layout/ui_preferences_connection_host_mqtt_0".equals(tag)) {
            return new UiPreferencesConnectionHostMqttBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_connection_host_mqtt is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESCONNECTIONIDENTIFICATION: {
          if ("layout/ui_preferences_connection_identification_0".equals(tag)) {
            return new UiPreferencesConnectionIdentificationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_connection_identification is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESCONNECTIONMODE: {
          if ("layout/ui_preferences_connection_mode_0".equals(tag)) {
            return new UiPreferencesConnectionModeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_connection_mode is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESCONNECTIONPARAMETERS: {
          if ("layout/ui_preferences_connection_parameters_0".equals(tag)) {
            return new UiPreferencesConnectionParametersBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_connection_parameters is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESCONNECTIONSECURITY: {
          if ("layout/ui_preferences_connection_security_0".equals(tag)) {
            return new UiPreferencesConnectionSecurityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_connection_security is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESEDITOR: {
          if ("layout/ui_preferences_editor_0".equals(tag)) {
            return new UiPreferencesEditorBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_editor is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESLOAD: {
          if ("layout/ui_preferences_load_0".equals(tag)) {
            return new UiPreferencesLoadBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_load is invalid. Received: " + tag);
        }
        case  LAYOUT_UIPREFERENCESLOGS: {
          if ("layout/ui_preferences_logs_0".equals(tag)) {
            return new UiPreferencesLogsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_preferences_logs is invalid. Received: " + tag);
        }
        case  LAYOUT_UIREGION: {
          if ("layout/ui_region_0".equals(tag)) {
            return new UiRegionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_region is invalid. Received: " + tag);
        }
        case  LAYOUT_UIREGIONS: {
          if ("layout/ui_regions_0".equals(tag)) {
            return new UiRegionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_regions is invalid. Received: " + tag);
        }
        case  LAYOUT_UIROWCONTACT: {
          if ("layout/ui_row_contact_0".equals(tag)) {
            return new UiRowContactBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_row_contact is invalid. Received: " + tag);
        }
        case  LAYOUT_UIROWREGION: {
          if ("layout/ui_row_region_0".equals(tag)) {
            return new UiRowRegionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_row_region is invalid. Received: " + tag);
        }
        case  LAYOUT_UISTATUS: {
          if ("layout/ui_status_0".equals(tag)) {
            return new UiStatusBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_status is invalid. Received: " + tag);
        }
        case  LAYOUT_UIWELCOME: {
          if ("layout/ui_welcome_0".equals(tag)) {
            return new UiWelcomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_welcome is invalid. Received: " + tag);
        }
        case  LAYOUT_UIWELCOMEFINISH: {
          if ("layout/ui_welcome_finish_0".equals(tag)) {
            return new UiWelcomeFinishBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_welcome_finish is invalid. Received: " + tag);
        }
        case  LAYOUT_UIWELCOMEINTRO: {
          if ("layout/ui_welcome_intro_0".equals(tag)) {
            return new UiWelcomeIntroBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_welcome_intro is invalid. Received: " + tag);
        }
        case  LAYOUT_UIWELCOMEPERMISSIONS: {
          if ("layout/ui_welcome_permissions_0".equals(tag)) {
            return new UiWelcomePermissionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_welcome_permissions is invalid. Received: " + tag);
        }
        case  LAYOUT_UIWELCOMEPLAY: {
          if ("layout/ui_welcome_play_0".equals(tag)) {
            return new UiWelcomePlayBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_welcome_play is invalid. Received: " + tag);
        }
        case  LAYOUT_UIWELCOMEVERSION: {
          if ("layout/ui_welcome_version_0".equals(tag)) {
            return new UiWelcomeVersionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ui_welcome_version is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(28);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "activeContact");
      sKeys.put(2, "contact");
      sKeys.put(3, "effectiveConfiguration");
      sKeys.put(4, "endpointMessage");
      sKeys.put(5, "endpointQueue");
      sKeys.put(6, "endpointState");
      sKeys.put(7, "fixAvailable");
      sKeys.put(8, "fusedLocationAccuracy");
      sKeys.put(9, "fusedName");
      sKeys.put(10, "geocodedLocation");
      sKeys.put(11, "geofenceLatitude");
      sKeys.put(12, "geofenceLatitudeAsStr");
      sKeys.put(13, "geofenceLongitude");
      sKeys.put(14, "geofenceLongitudeAsStr");
      sKeys.put(15, "id");
      sKeys.put(16, "imageProvider");
      sKeys.put(17, "locationUpdated");
      sKeys.put(18, "message");
      sKeys.put(19, "messageCard");
      sKeys.put(20, "messageLocation");
      sKeys.put(21, "modeId");
      sKeys.put(22, "name");
      sKeys.put(23, "serviceStarted");
      sKeys.put(24, "trackerId");
      sKeys.put(25, "tst");
      sKeys.put(26, "vm");
      sKeys.put(27, "waypoint");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(24);

    static {
      sKeys.put("layout/ui_contacts_0", org.owntracks.android.R.layout.ui_contacts);
      sKeys.put("layout/ui_map_0", org.owntracks.android.R.layout.ui_map);
      sKeys.put("layout/ui_preferences_0", org.owntracks.android.R.layout.ui_preferences);
      sKeys.put("layout/ui_preferences_connection_0", org.owntracks.android.R.layout.ui_preferences_connection);
      sKeys.put("layout/ui_preferences_connection_host_http_0", org.owntracks.android.R.layout.ui_preferences_connection_host_http);
      sKeys.put("layout/ui_preferences_connection_host_mqtt_0", org.owntracks.android.R.layout.ui_preferences_connection_host_mqtt);
      sKeys.put("layout/ui_preferences_connection_identification_0", org.owntracks.android.R.layout.ui_preferences_connection_identification);
      sKeys.put("layout/ui_preferences_connection_mode_0", org.owntracks.android.R.layout.ui_preferences_connection_mode);
      sKeys.put("layout/ui_preferences_connection_parameters_0", org.owntracks.android.R.layout.ui_preferences_connection_parameters);
      sKeys.put("layout/ui_preferences_connection_security_0", org.owntracks.android.R.layout.ui_preferences_connection_security);
      sKeys.put("layout/ui_preferences_editor_0", org.owntracks.android.R.layout.ui_preferences_editor);
      sKeys.put("layout/ui_preferences_load_0", org.owntracks.android.R.layout.ui_preferences_load);
      sKeys.put("layout/ui_preferences_logs_0", org.owntracks.android.R.layout.ui_preferences_logs);
      sKeys.put("layout/ui_region_0", org.owntracks.android.R.layout.ui_region);
      sKeys.put("layout/ui_regions_0", org.owntracks.android.R.layout.ui_regions);
      sKeys.put("layout/ui_row_contact_0", org.owntracks.android.R.layout.ui_row_contact);
      sKeys.put("layout/ui_row_region_0", org.owntracks.android.R.layout.ui_row_region);
      sKeys.put("layout/ui_status_0", org.owntracks.android.R.layout.ui_status);
      sKeys.put("layout/ui_welcome_0", org.owntracks.android.R.layout.ui_welcome);
      sKeys.put("layout/ui_welcome_finish_0", org.owntracks.android.R.layout.ui_welcome_finish);
      sKeys.put("layout/ui_welcome_intro_0", org.owntracks.android.R.layout.ui_welcome_intro);
      sKeys.put("layout/ui_welcome_permissions_0", org.owntracks.android.R.layout.ui_welcome_permissions);
      sKeys.put("layout/ui_welcome_play_0", org.owntracks.android.R.layout.ui_welcome_play);
      sKeys.put("layout/ui_welcome_version_0", org.owntracks.android.R.layout.ui_welcome_version);
    }
  }
}
