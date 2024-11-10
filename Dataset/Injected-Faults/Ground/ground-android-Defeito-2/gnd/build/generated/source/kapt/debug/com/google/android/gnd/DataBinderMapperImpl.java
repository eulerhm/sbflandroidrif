package com.google.android.gnd;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.gnd.databinding.DataCollectionFragBindingImpl;
import com.google.android.gnd.databinding.DateInputFieldBindingImpl;
import com.google.android.gnd.databinding.EditSubmissionBottomSheetBindingImpl;
import com.google.android.gnd.databinding.EditSubmissionFragBindingImpl;
import com.google.android.gnd.databinding.FeatureDetailsChromeBindingImpl;
import com.google.android.gnd.databinding.FeatureDetailsFragBindingImpl;
import com.google.android.gnd.databinding.FeatureDetailsHeaderBindingImpl;
import com.google.android.gnd.databinding.FragmentTermsServiceBindingImpl;
import com.google.android.gnd.databinding.HomeScreenFragBindingImpl;
import com.google.android.gnd.databinding.MapContainerFragBindingImpl;
import com.google.android.gnd.databinding.MapHomeLayoutBindingImpl;
import com.google.android.gnd.databinding.MapMoveFeatureLayoutBindingImpl;
import com.google.android.gnd.databinding.MultipleChoiceInputFieldBindingImpl;
import com.google.android.gnd.databinding.NavDrawerHeaderBindingImpl;
import com.google.android.gnd.databinding.NumberInputFieldBindingImpl;
import com.google.android.gnd.databinding.OfflineBaseMapSelectorFragBindingImpl;
import com.google.android.gnd.databinding.OfflineBaseMapViewerFragBindingImpl;
import com.google.android.gnd.databinding.OfflineBaseMapsFragBindingImpl;
import com.google.android.gnd.databinding.PhotoFieldBindingImpl;
import com.google.android.gnd.databinding.PhotoInputFieldBindingImpl;
import com.google.android.gnd.databinding.PolygonDrawingControlsBindingImpl;
import com.google.android.gnd.databinding.SettingsActivityBindingImpl;
import com.google.android.gnd.databinding.SignInFragBindingImpl;
import com.google.android.gnd.databinding.SubmissionDetailsFieldBindingImpl;
import com.google.android.gnd.databinding.SubmissionDetailsFragBindingImpl;
import com.google.android.gnd.databinding.SubmissionListFragBindingImpl;
import com.google.android.gnd.databinding.SubmissionListItemBindingImpl;
import com.google.android.gnd.databinding.SyncStatusFragBindingImpl;
import com.google.android.gnd.databinding.TextInputFieldBindingImpl;
import com.google.android.gnd.databinding.TimeInputFieldBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_DATACOLLECTIONFRAG = 1;

  private static final int LAYOUT_DATEINPUTFIELD = 2;

  private static final int LAYOUT_EDITSUBMISSIONBOTTOMSHEET = 3;

  private static final int LAYOUT_EDITSUBMISSIONFRAG = 4;

  private static final int LAYOUT_FEATUREDETAILSCHROME = 5;

  private static final int LAYOUT_FEATUREDETAILSFRAG = 6;

  private static final int LAYOUT_FEATUREDETAILSHEADER = 7;

  private static final int LAYOUT_FRAGMENTTERMSSERVICE = 8;

  private static final int LAYOUT_HOMESCREENFRAG = 9;

  private static final int LAYOUT_MAPCONTAINERFRAG = 10;

  private static final int LAYOUT_MAPHOMELAYOUT = 11;

  private static final int LAYOUT_MAPMOVEFEATURELAYOUT = 12;

  private static final int LAYOUT_MULTIPLECHOICEINPUTFIELD = 13;

  private static final int LAYOUT_NAVDRAWERHEADER = 14;

  private static final int LAYOUT_NUMBERINPUTFIELD = 15;

  private static final int LAYOUT_OFFLINEBASEMAPSELECTORFRAG = 16;

  private static final int LAYOUT_OFFLINEBASEMAPVIEWERFRAG = 17;

  private static final int LAYOUT_OFFLINEBASEMAPSFRAG = 18;

  private static final int LAYOUT_PHOTOFIELD = 19;

  private static final int LAYOUT_PHOTOINPUTFIELD = 20;

  private static final int LAYOUT_POLYGONDRAWINGCONTROLS = 21;

  private static final int LAYOUT_SETTINGSACTIVITY = 22;

  private static final int LAYOUT_SIGNINFRAG = 23;

  private static final int LAYOUT_SUBMISSIONDETAILSFIELD = 24;

  private static final int LAYOUT_SUBMISSIONDETAILSFRAG = 25;

  private static final int LAYOUT_SUBMISSIONLISTFRAG = 26;

  private static final int LAYOUT_SUBMISSIONLISTITEM = 27;

  private static final int LAYOUT_SYNCSTATUSFRAG = 28;

  private static final int LAYOUT_TEXTINPUTFIELD = 29;

  private static final int LAYOUT_TIMEINPUTFIELD = 30;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(30);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.data_collection_frag, LAYOUT_DATACOLLECTIONFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.date_input_field, LAYOUT_DATEINPUTFIELD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.edit_submission_bottom_sheet, LAYOUT_EDITSUBMISSIONBOTTOMSHEET);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.edit_submission_frag, LAYOUT_EDITSUBMISSIONFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.feature_details_chrome, LAYOUT_FEATUREDETAILSCHROME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.feature_details_frag, LAYOUT_FEATUREDETAILSFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.feature_details_header, LAYOUT_FEATUREDETAILSHEADER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.fragment_terms_service, LAYOUT_FRAGMENTTERMSSERVICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.home_screen_frag, LAYOUT_HOMESCREENFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.map_container_frag, LAYOUT_MAPCONTAINERFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.map_home_layout, LAYOUT_MAPHOMELAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.map_move_feature_layout, LAYOUT_MAPMOVEFEATURELAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.multiple_choice_input_field, LAYOUT_MULTIPLECHOICEINPUTFIELD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.nav_drawer_header, LAYOUT_NAVDRAWERHEADER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.number_input_field, LAYOUT_NUMBERINPUTFIELD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.offline_base_map_selector_frag, LAYOUT_OFFLINEBASEMAPSELECTORFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.offline_base_map_viewer_frag, LAYOUT_OFFLINEBASEMAPVIEWERFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.offline_base_maps_frag, LAYOUT_OFFLINEBASEMAPSFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.photo_field, LAYOUT_PHOTOFIELD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.photo_input_field, LAYOUT_PHOTOINPUTFIELD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.polygon_drawing_controls, LAYOUT_POLYGONDRAWINGCONTROLS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.settings_activity, LAYOUT_SETTINGSACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.sign_in_frag, LAYOUT_SIGNINFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.submission_details_field, LAYOUT_SUBMISSIONDETAILSFIELD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.submission_details_frag, LAYOUT_SUBMISSIONDETAILSFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.submission_list_frag, LAYOUT_SUBMISSIONLISTFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.submission_list_item, LAYOUT_SUBMISSIONLISTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.sync_status_frag, LAYOUT_SYNCSTATUSFRAG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.text_input_field, LAYOUT_TEXTINPUTFIELD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.google.android.gnd.R.layout.time_input_field, LAYOUT_TIMEINPUTFIELD);
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
        case  LAYOUT_DATACOLLECTIONFRAG: {
          if ("layout/data_collection_frag_0".equals(tag)) {
            return new DataCollectionFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for data_collection_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_DATEINPUTFIELD: {
          if ("layout/date_input_field_0".equals(tag)) {
            return new DateInputFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for date_input_field is invalid. Received: " + tag);
        }
        case  LAYOUT_EDITSUBMISSIONBOTTOMSHEET: {
          if ("layout/edit_submission_bottom_sheet_0".equals(tag)) {
            return new EditSubmissionBottomSheetBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for edit_submission_bottom_sheet is invalid. Received: " + tag);
        }
        case  LAYOUT_EDITSUBMISSIONFRAG: {
          if ("layout/edit_submission_frag_0".equals(tag)) {
            return new EditSubmissionFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for edit_submission_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_FEATUREDETAILSCHROME: {
          if ("layout/feature_details_chrome_0".equals(tag)) {
            return new FeatureDetailsChromeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for feature_details_chrome is invalid. Received: " + tag);
        }
        case  LAYOUT_FEATUREDETAILSFRAG: {
          if ("layout/feature_details_frag_0".equals(tag)) {
            return new FeatureDetailsFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for feature_details_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_FEATUREDETAILSHEADER: {
          if ("layout/feature_details_header_0".equals(tag)) {
            return new FeatureDetailsHeaderBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for feature_details_header is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTERMSSERVICE: {
          if ("layout/fragment_terms_service_0".equals(tag)) {
            return new FragmentTermsServiceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_terms_service is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMESCREENFRAG: {
          if ("layout/home_screen_frag_0".equals(tag)) {
            return new HomeScreenFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_screen_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_MAPCONTAINERFRAG: {
          if ("layout/map_container_frag_0".equals(tag)) {
            return new MapContainerFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for map_container_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_MAPHOMELAYOUT: {
          if ("layout/map_home_layout_0".equals(tag)) {
            return new MapHomeLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for map_home_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_MAPMOVEFEATURELAYOUT: {
          if ("layout/map_move_feature_layout_0".equals(tag)) {
            return new MapMoveFeatureLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for map_move_feature_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_MULTIPLECHOICEINPUTFIELD: {
          if ("layout/multiple_choice_input_field_0".equals(tag)) {
            return new MultipleChoiceInputFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for multiple_choice_input_field is invalid. Received: " + tag);
        }
        case  LAYOUT_NAVDRAWERHEADER: {
          if ("layout/nav_drawer_header_0".equals(tag)) {
            return new NavDrawerHeaderBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for nav_drawer_header is invalid. Received: " + tag);
        }
        case  LAYOUT_NUMBERINPUTFIELD: {
          if ("layout/number_input_field_0".equals(tag)) {
            return new NumberInputFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for number_input_field is invalid. Received: " + tag);
        }
        case  LAYOUT_OFFLINEBASEMAPSELECTORFRAG: {
          if ("layout/offline_base_map_selector_frag_0".equals(tag)) {
            return new OfflineBaseMapSelectorFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for offline_base_map_selector_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_OFFLINEBASEMAPVIEWERFRAG: {
          if ("layout/offline_base_map_viewer_frag_0".equals(tag)) {
            return new OfflineBaseMapViewerFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for offline_base_map_viewer_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_OFFLINEBASEMAPSFRAG: {
          if ("layout/offline_base_maps_frag_0".equals(tag)) {
            return new OfflineBaseMapsFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for offline_base_maps_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_PHOTOFIELD: {
          if ("layout/photo_field_0".equals(tag)) {
            return new PhotoFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for photo_field is invalid. Received: " + tag);
        }
        case  LAYOUT_PHOTOINPUTFIELD: {
          if ("layout/photo_input_field_0".equals(tag)) {
            return new PhotoInputFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for photo_input_field is invalid. Received: " + tag);
        }
        case  LAYOUT_POLYGONDRAWINGCONTROLS: {
          if ("layout/polygon_drawing_controls_0".equals(tag)) {
            return new PolygonDrawingControlsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for polygon_drawing_controls is invalid. Received: " + tag);
        }
        case  LAYOUT_SETTINGSACTIVITY: {
          if ("layout/settings_activity_0".equals(tag)) {
            return new SettingsActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for settings_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_SIGNINFRAG: {
          if ("layout/sign_in_frag_0".equals(tag)) {
            return new SignInFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for sign_in_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_SUBMISSIONDETAILSFIELD: {
          if ("layout/submission_details_field_0".equals(tag)) {
            return new SubmissionDetailsFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for submission_details_field is invalid. Received: " + tag);
        }
        case  LAYOUT_SUBMISSIONDETAILSFRAG: {
          if ("layout/submission_details_frag_0".equals(tag)) {
            return new SubmissionDetailsFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for submission_details_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_SUBMISSIONLISTFRAG: {
          if ("layout/submission_list_frag_0".equals(tag)) {
            return new SubmissionListFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for submission_list_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_SUBMISSIONLISTITEM: {
          if ("layout/submission_list_item_0".equals(tag)) {
            return new SubmissionListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for submission_list_item is invalid. Received: " + tag);
        }
        case  LAYOUT_SYNCSTATUSFRAG: {
          if ("layout/sync_status_frag_0".equals(tag)) {
            return new SyncStatusFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for sync_status_frag is invalid. Received: " + tag);
        }
        case  LAYOUT_TEXTINPUTFIELD: {
          if ("layout/text_input_field_0".equals(tag)) {
            return new TextInputFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for text_input_field is invalid. Received: " + tag);
        }
        case  LAYOUT_TIMEINPUTFIELD: {
          if ("layout/time_input_field_0".equals(tag)) {
            return new TimeInputFieldBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for time_input_field is invalid. Received: " + tag);
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
    static final SparseArray<String> sKeys = new SparseArray<String>(8);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "editSubmissionViewModel");
      sKeys.put(2, "field");
      sKeys.put(3, "fragment");
      sKeys.put(4, "homeScreenViewModel");
      sKeys.put(5, "polygonDrawingViewModel");
      sKeys.put(6, "user");
      sKeys.put(7, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(30);

    static {
      sKeys.put("layout/data_collection_frag_0", com.google.android.gnd.R.layout.data_collection_frag);
      sKeys.put("layout/date_input_field_0", com.google.android.gnd.R.layout.date_input_field);
      sKeys.put("layout/edit_submission_bottom_sheet_0", com.google.android.gnd.R.layout.edit_submission_bottom_sheet);
      sKeys.put("layout/edit_submission_frag_0", com.google.android.gnd.R.layout.edit_submission_frag);
      sKeys.put("layout/feature_details_chrome_0", com.google.android.gnd.R.layout.feature_details_chrome);
      sKeys.put("layout/feature_details_frag_0", com.google.android.gnd.R.layout.feature_details_frag);
      sKeys.put("layout/feature_details_header_0", com.google.android.gnd.R.layout.feature_details_header);
      sKeys.put("layout/fragment_terms_service_0", com.google.android.gnd.R.layout.fragment_terms_service);
      sKeys.put("layout/home_screen_frag_0", com.google.android.gnd.R.layout.home_screen_frag);
      sKeys.put("layout/map_container_frag_0", com.google.android.gnd.R.layout.map_container_frag);
      sKeys.put("layout/map_home_layout_0", com.google.android.gnd.R.layout.map_home_layout);
      sKeys.put("layout/map_move_feature_layout_0", com.google.android.gnd.R.layout.map_move_feature_layout);
      sKeys.put("layout/multiple_choice_input_field_0", com.google.android.gnd.R.layout.multiple_choice_input_field);
      sKeys.put("layout/nav_drawer_header_0", com.google.android.gnd.R.layout.nav_drawer_header);
      sKeys.put("layout/number_input_field_0", com.google.android.gnd.R.layout.number_input_field);
      sKeys.put("layout/offline_base_map_selector_frag_0", com.google.android.gnd.R.layout.offline_base_map_selector_frag);
      sKeys.put("layout/offline_base_map_viewer_frag_0", com.google.android.gnd.R.layout.offline_base_map_viewer_frag);
      sKeys.put("layout/offline_base_maps_frag_0", com.google.android.gnd.R.layout.offline_base_maps_frag);
      sKeys.put("layout/photo_field_0", com.google.android.gnd.R.layout.photo_field);
      sKeys.put("layout/photo_input_field_0", com.google.android.gnd.R.layout.photo_input_field);
      sKeys.put("layout/polygon_drawing_controls_0", com.google.android.gnd.R.layout.polygon_drawing_controls);
      sKeys.put("layout/settings_activity_0", com.google.android.gnd.R.layout.settings_activity);
      sKeys.put("layout/sign_in_frag_0", com.google.android.gnd.R.layout.sign_in_frag);
      sKeys.put("layout/submission_details_field_0", com.google.android.gnd.R.layout.submission_details_field);
      sKeys.put("layout/submission_details_frag_0", com.google.android.gnd.R.layout.submission_details_frag);
      sKeys.put("layout/submission_list_frag_0", com.google.android.gnd.R.layout.submission_list_frag);
      sKeys.put("layout/submission_list_item_0", com.google.android.gnd.R.layout.submission_list_item);
      sKeys.put("layout/sync_status_frag_0", com.google.android.gnd.R.layout.sync_status_frag);
      sKeys.put("layout/text_input_field_0", com.google.android.gnd.R.layout.text_input_field);
      sKeys.put("layout/time_input_field_0", com.google.android.gnd.R.layout.time_input_field);
    }
  }
}
