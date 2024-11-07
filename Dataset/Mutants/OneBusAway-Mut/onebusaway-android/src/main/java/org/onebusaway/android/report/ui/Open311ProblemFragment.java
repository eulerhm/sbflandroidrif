/*
* Copyright (C) 2014-2015 University of South Florida (sjbarbeau@gmail.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.onebusaway.android.report.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.elements.ObaTripStatus;
import org.onebusaway.android.report.connection.ServiceDescriptionTask;
import org.onebusaway.android.report.connection.ServiceRequestTask;
import org.onebusaway.android.report.constants.ReportConstants;
import org.onebusaway.android.report.ui.model.AttributeValue;
import org.onebusaway.android.report.ui.util.IssueLocationHelper;
import org.onebusaway.android.report.ui.util.ServiceUtils;
import org.onebusaway.android.util.MyTextUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.UIUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import edu.usf.cutr.open311client.Open311;
import edu.usf.cutr.open311client.constants.Open311DataType;
import edu.usf.cutr.open311client.models.Open311Attribute;
import edu.usf.cutr.open311client.models.Open311AttributePair;
import edu.usf.cutr.open311client.models.Open311User;
import edu.usf.cutr.open311client.models.Service;
import edu.usf.cutr.open311client.models.ServiceDescription;
import edu.usf.cutr.open311client.models.ServiceDescriptionRequest;
import edu.usf.cutr.open311client.models.ServiceRequest;
import edu.usf.cutr.open311client.models.ServiceRequestResponse;
import edu.usf.cutr.open311client.utils.Open311Validator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Open311ProblemFragment extends BaseReportFragment implements ServiceDescriptionTask.Callback, ServiceRequestTask.Callback {

    private ImageView mIssueImageView;

    private String mImagePath;

    private Open311 mOpen311;

    private Service mService;

    private String mAgencyName;

    // Block ID for the mArrivalInfo
    private String mBlockId;

    // Arrival information for trip problem
    private ObaArrivalInfo mArrivalInfo;

    // Captured image url
    private Uri mCapturedImageURI;

    // Open311 service description result for selected service code
    private ServiceDescription mServiceDescription;

    // Store ServiceDescription Task Result if host activity haven't been created
    private ServiceDescription mServiceDescriptionTaskResult;

    // Load dynamic open311 fields into info layout
    private LinearLayout mInfoLayout;

    private CheckBox mAnonymousReportingCheckBox;

    private EditText mContactNameView;

    private EditText mContactLastNameView;

    private EditText mContactEmailView;

    private EditText mContactPhoneView;

    private ProgressDialog mProgressDialog;

    private boolean mIsProgressDialogShowing = false;

    private Map<Integer, AttributeValue> mAttributeValueHashMap = new HashMap<>();

    private Map<Integer, View> mDynamicAttributeUIMap = new HashMap<>();

    // Maps attribute name + id with its key
    private Map<String, String> mOpen311AttributeKeyNameMap = new HashMap<>();

    private ServiceRequestTask mRequestTask;

    private ReportProblemFragmentCallback mCallback;

    public static final String TAG = "Open311ProblemFragment";

    private static final String ATTRIBUTES = ".attributes";

    private static final String IMAGE_PATH = ".image";

    private static final String IMAGE_URI = ".imageUri";

    private static final String IMAGE_THUMBNAIL = ".imageThumbnail";

    private static final String TRIP_INFO = ".tripInfo";

    private static final String SHOW_PROGRESS_DIALOG = ".showProgressDialog";

    private static final String AGENCY_NAME = ".agencyName";

    private static final String BLOCK_ID = ".blockId";

    private FirebaseAnalytics mFirebaseAnalytics;

    public static void show(AppCompatActivity activity, Integer containerViewId, Open311 open311, Service service, ObaArrivalInfo obaArrivalInfo, String agencyName, String blockId) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Open311ProblemFragment fragment = new Open311ProblemFragment();
        if (!ListenerUtil.mutListener.listen(11233)) {
            fragment.setOpen311(open311);
        }
        if (!ListenerUtil.mutListener.listen(11234)) {
            fragment.setService(service);
        }
        if (!ListenerUtil.mutListener.listen(11235)) {
            fragment.setArrivalInfo(obaArrivalInfo);
        }
        if (!ListenerUtil.mutListener.listen(11236)) {
            fragment.setAgencyName(agencyName);
        }
        if (!ListenerUtil.mutListener.listen(11237)) {
            fragment.setBlockId(blockId);
        }
        try {
            FragmentTransaction ft = fm.beginTransaction();
            if (!ListenerUtil.mutListener.listen(11239)) {
                ft.replace(containerViewId, fragment, TAG);
            }
            if (!ListenerUtil.mutListener.listen(11240)) {
                ft.addToBackStack(null);
            }
            if (!ListenerUtil.mutListener.listen(11241)) {
                ft.commit();
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(11238)) {
                Log.e(TAG, "Cannot show Open311ProblemFragment after onSaveInstanceState has been called");
            }
        }
    }

    public static void show(AppCompatActivity activity, Integer containerViewId, Open311 open311, Service service) {
        if (!ListenerUtil.mutListener.listen(11242)) {
            Open311ProblemFragment.show(activity, containerViewId, open311, service, null, null, null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.open311_issue, container, false);
        if (!ListenerUtil.mutListener.listen(11243)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(11244)) {
            setHasOptionsMenu(Boolean.TRUE);
        }
        if (!ListenerUtil.mutListener.listen(11245)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11246)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11247)) {
            setupViews(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11248)) {
            setupIconColors();
        }
        if (!ListenerUtil.mutListener.listen(11249)) {
            setUpContactInfoViews();
        }
        if (!ListenerUtil.mutListener.listen(11250)) {
            callServiceDescription();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(11251)) {
            super.onSaveInstanceState(outState);
        }
        List<AttributeValue> attributeValues = createAttributeValues(mServiceDescription);
        if (!ListenerUtil.mutListener.listen(11258)) {
            if ((ListenerUtil.mutListener.listen(11256) ? (attributeValues.size() >= 0) : (ListenerUtil.mutListener.listen(11255) ? (attributeValues.size() <= 0) : (ListenerUtil.mutListener.listen(11254) ? (attributeValues.size() < 0) : (ListenerUtil.mutListener.listen(11253) ? (attributeValues.size() != 0) : (ListenerUtil.mutListener.listen(11252) ? (attributeValues.size() == 0) : (attributeValues.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(11257)) {
                    outState.putParcelableArrayList(ATTRIBUTES, (ArrayList<? extends Parcelable>) attributeValues);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11262)) {
            if (mImagePath != null) {
                if (!ListenerUtil.mutListener.listen(11259)) {
                    outState.putParcelable(IMAGE_URI, mCapturedImageURI);
                }
                if (!ListenerUtil.mutListener.listen(11260)) {
                    outState.putString(IMAGE_PATH, mImagePath);
                }
                Bitmap bitmap = ((BitmapDrawable) mIssueImageView.getDrawable()).getBitmap();
                if (!ListenerUtil.mutListener.listen(11261)) {
                    outState.putParcelable(IMAGE_THUMBNAIL, bitmap);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11264)) {
            if (mArrivalInfo != null) {
                if (!ListenerUtil.mutListener.listen(11263)) {
                    outState.putSerializable(TRIP_INFO, mArrivalInfo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11266)) {
            if (mIsProgressDialogShowing) {
                if (!ListenerUtil.mutListener.listen(11265)) {
                    // Dismiss the progress dialog when orientation change to prevent leaked window
                    mProgressDialog.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11267)) {
            outState.putBoolean(SHOW_PROGRESS_DIALOG, mIsProgressDialogShowing);
        }
        if (!ListenerUtil.mutListener.listen(11268)) {
            outState.putString(AGENCY_NAME, mAgencyName);
        }
        if (!ListenerUtil.mutListener.listen(11269)) {
            outState.putString(BLOCK_ID, mBlockId);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11270)) {
            super.onViewStateRestored(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11283)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(11271)) {
                    mCapturedImageURI = savedInstanceState.getParcelable(IMAGE_URI);
                }
                if (!ListenerUtil.mutListener.listen(11272)) {
                    mImagePath = savedInstanceState.getString(IMAGE_PATH);
                }
                if (!ListenerUtil.mutListener.listen(11273)) {
                    mArrivalInfo = (ObaArrivalInfo) savedInstanceState.getSerializable(TRIP_INFO);
                }
                if (!ListenerUtil.mutListener.listen(11274)) {
                    mAgencyName = savedInstanceState.getString(AGENCY_NAME);
                }
                if (!ListenerUtil.mutListener.listen(11275)) {
                    mBlockId = savedInstanceState.getString(BLOCK_ID);
                }
                List<AttributeValue> values = savedInstanceState.getParcelableArrayList(ATTRIBUTES);
                if (!ListenerUtil.mutListener.listen(11276)) {
                    mAttributeValueHashMap.clear();
                }
                if (!ListenerUtil.mutListener.listen(11279)) {
                    if (values != null) {
                        if (!ListenerUtil.mutListener.listen(11278)) {
                            {
                                long _loopCounter150 = 0;
                                for (AttributeValue v : values) {
                                    ListenerUtil.loopListener.listen("_loopCounter150", ++_loopCounter150);
                                    if (!ListenerUtil.mutListener.listen(11277)) {
                                        mAttributeValueHashMap.put(v.getCode(), v);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11280)) {
                    mIsProgressDialogShowing = savedInstanceState.getBoolean(SHOW_PROGRESS_DIALOG);
                }
                if (!ListenerUtil.mutListener.listen(11282)) {
                    if (mIsProgressDialogShowing) {
                        if (!ListenerUtil.mutListener.listen(11281)) {
                            showProgressDialog(true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(11284)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(11287)) {
            if (mServiceDescriptionTaskResult != null) {
                if (!ListenerUtil.mutListener.listen(11285)) {
                    // Reload service description task if the activity restored
                    this.onServiceDescriptionTaskCompleted(mServiceDescriptionTaskResult);
                }
                if (!ListenerUtil.mutListener.listen(11286)) {
                    mServiceDescriptionTaskResult = null;
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(11288)) {
                mCallback = (ReportProblemFragmentCallback) context;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("ReportProblemFragmentCallback should be implemented" + " in parent activity");
        }
    }

    /**
     * Initialize UI components
     */
    private void setupViews(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(11289)) {
            mIssueImageView = (ImageView) findViewById(R.id.ri_imageView);
        }
        if (!ListenerUtil.mutListener.listen(11292)) {
            if ((ListenerUtil.mutListener.listen(11290) ? (bundle != null || bundle.getParcelable(IMAGE_THUMBNAIL) != null) : (bundle != null && bundle.getParcelable(IMAGE_THUMBNAIL) != null))) {
                if (!ListenerUtil.mutListener.listen(11291)) {
                    mIssueImageView.setImageBitmap((Bitmap) bundle.getParcelable(IMAGE_THUMBNAIL));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11293)) {
            mInfoLayout = (LinearLayout) findViewById(R.id.ri_info_layout);
        }
        Button addImageButton = (Button) findViewById(R.id.ri_attach_image);
        final PopupMenu popupMenu = new PopupMenu(getActivity(), addImageButton);
        if (!ListenerUtil.mutListener.listen(11294)) {
            popupMenu.inflate(R.menu.report_issue_add_image);
        }
        if (!ListenerUtil.mutListener.listen(11296)) {
            addImageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(11295)) {
                        popupMenu.show();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11300)) {
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(11299)) {
                        switch(item.getItemId()) {
                            case R.id.ri_button_camera:
                                if (!ListenerUtil.mutListener.listen(11297)) {
                                    openCamera();
                                }
                                break;
                            case R.id.ri_button_gallery:
                                if (!ListenerUtil.mutListener.listen(11298)) {
                                    openGallery();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11301)) {
            mAnonymousReportingCheckBox = (CheckBox) findViewById(R.id.rici_anonymous_checkbox);
        }
        if (!ListenerUtil.mutListener.listen(11303)) {
            mAnonymousReportingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (!ListenerUtil.mutListener.listen(11302)) {
                        disableEnableContactInfoViews(isChecked);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11304)) {
            // Setup contact info views
            mContactNameView = ((EditText) findViewById(R.id.rici_name_editText));
        }
        if (!ListenerUtil.mutListener.listen(11305)) {
            mContactLastNameView = ((EditText) findViewById(R.id.rici_lastname_editText));
        }
        if (!ListenerUtil.mutListener.listen(11306)) {
            mContactEmailView = ((EditText) findViewById(R.id.rici_email_editText));
        }
        if (!ListenerUtil.mutListener.listen(11307)) {
            mContactPhoneView = ((EditText) findViewById(R.id.rici_phone_editText));
        }
    }

    private void setupIconColors() {
        if (!ListenerUtil.mutListener.listen(11308)) {
            ((ImageView) findViewById(R.id.ri_ic_app_feedback)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11309)) {
            ((ImageView) findViewById(R.id.ri_ic_image_picker)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11310)) {
            ((ImageView) findViewById(R.id.ri_ic_username)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11311)) {
            ((ImageView) findViewById(R.id.ri_ic_customer_service_email)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11312)) {
            ((ImageView) findViewById(R.id.ri_ic_customer_service_phone)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11313)) {
            ((ImageView) findViewById(R.id.ri_ic_anonymous)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
    }

    private void callServiceDescription() {
        if (!ListenerUtil.mutListener.listen(11314)) {
            if (mOpen311 == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11315)) {
            showProgress(Boolean.TRUE);
        }
        Location location = getIssueLocationHelper().getIssueLocation();
        ServiceDescriptionRequest sdr = new ServiceDescriptionRequest(location.getLatitude(), location.getLongitude(), mOpen311.getJurisdiction(), mService.getService_code());
        ServiceDescriptionTask sdt = new ServiceDescriptionTask(sdr, mOpen311, Open311ProblemFragment.this);
        if (!ListenerUtil.mutListener.listen(11316)) {
            sdt.execute();
        }
    }

    /**
     * Update ui if service description request is success
     */
    @Override
    public void onServiceDescriptionTaskCompleted(ServiceDescription serviceDescription) {
        if (!ListenerUtil.mutListener.listen(11323)) {
            if (isActivityAttached()) {
                if (!ListenerUtil.mutListener.listen(11318)) {
                    showProgress(Boolean.FALSE);
                }
                if (!ListenerUtil.mutListener.listen(11322)) {
                    if (serviceDescription.isSuccess()) {
                        if (!ListenerUtil.mutListener.listen(11321)) {
                            createServiceDescriptionUI(serviceDescription);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11319)) {
                            createToastMessage(getString(R.string.ri_service_description_problem));
                        }
                        if (!ListenerUtil.mutListener.listen(11320)) {
                            // Close open311 fragment
                            ((InfrastructureIssueActivity) getActivity()).removeOpen311ProblemFragment();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11317)) {
                    mServiceDescriptionTaskResult = serviceDescription;
                }
            }
        }
    }

    /**
     * Show the result of the open311 issue submission
     */
    @Override
    public void onServiceRequestTaskCompleted(ServiceRequestResponse response) {
        if (!ListenerUtil.mutListener.listen(11324)) {
            showProgressDialog(false);
        }
        if (!ListenerUtil.mutListener.listen(11329)) {
            if (response.isSuccess()) {
                if (!ListenerUtil.mutListener.listen(11328)) {
                    mCallback.onReportSent();
                }
            } else {
                String message = response.getErrorMessage();
                if (!ListenerUtil.mutListener.listen(11326)) {
                    if (TextUtils.isEmpty(message)) {
                        if (!ListenerUtil.mutListener.listen(11325)) {
                            message = getString(R.string.ri_unsuccessful_submit);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11327)) {
                    createToastMessage(message);
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(11330)) {
            inflater.inflate(R.menu.report_issue_action, menu);
        }
        if (!ListenerUtil.mutListener.listen(11331)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(11333)) {
            if (item.getItemId() == R.id.report_problem_send) {
                if (!ListenerUtil.mutListener.listen(11332)) {
                    submitReport();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(11334)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(11342)) {
            if ((ListenerUtil.mutListener.listen(11336) ? ((ListenerUtil.mutListener.listen(11335) ? (requestCode == ReportConstants.GALLERY_INTENT || resultCode == Activity.RESULT_OK) : (requestCode == ReportConstants.GALLERY_INTENT && resultCode == Activity.RESULT_OK)) || data != null) : ((ListenerUtil.mutListener.listen(11335) ? (requestCode == ReportConstants.GALLERY_INTENT || resultCode == Activity.RESULT_OK) : (requestCode == ReportConstants.GALLERY_INTENT && resultCode == Activity.RESULT_OK)) && data != null))) {
                if (!ListenerUtil.mutListener.listen(11337)) {
                    // Get the gallery image info
                    mCapturedImageURI = data.getData();
                }
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getActivity().getContentResolver().query(mCapturedImageURI, filePathColumn, null, null, null);
                if (!ListenerUtil.mutListener.listen(11338)) {
                    if (cursor == null)
                        return;
                }
                if (!ListenerUtil.mutListener.listen(11339)) {
                    cursor.moveToFirst();
                }
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                if (!ListenerUtil.mutListener.listen(11340)) {
                    mImagePath = cursor.getString(columnIndex);
                }
                if (!ListenerUtil.mutListener.listen(11341)) {
                    cursor.close();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11348)) {
            // Whether image was from gallery or captured via camera, we need to downscale it for ImageView
            if ((ListenerUtil.mutListener.listen(11344) ? (((ListenerUtil.mutListener.listen(11343) ? (requestCode == ReportConstants.GALLERY_INTENT && requestCode == ReportConstants.CAPTURE_PICTURE_INTENT) : (requestCode == ReportConstants.GALLERY_INTENT || requestCode == ReportConstants.CAPTURE_PICTURE_INTENT))) || resultCode == Activity.RESULT_OK) : (((ListenerUtil.mutListener.listen(11343) ? (requestCode == ReportConstants.GALLERY_INTENT && requestCode == ReportConstants.CAPTURE_PICTURE_INTENT) : (requestCode == ReportConstants.GALLERY_INTENT || requestCode == ReportConstants.CAPTURE_PICTURE_INTENT))) && resultCode == Activity.RESULT_OK))) {
                // Load thumbnail to avoid OutOfMemory issue on Galaxy S5 - see #730
                Bitmap thumbnail = null;
                try {
                    if (!ListenerUtil.mutListener.listen(11346)) {
                        thumbnail = UIUtils.decodeSampledBitmapFromFile(mImagePath, mIssueImageView.getWidth(), mIssueImageView.getHeight());
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(11345)) {
                        e.printStackTrace();
                    }
                }
                if (!ListenerUtil.mutListener.listen(11347)) {
                    mIssueImageView.setImageBitmap(thumbnail);
                }
            }
        }
    }

    /**
     * Prepare submit forms and submit report
     */
    private void submitReport() {
        if (!ListenerUtil.mutListener.listen(11349)) {
            // Save the open311 user
            saveOpen311User();
        }
        // Prepare issue description
        String description = ((EditText) getActivity().findViewById(R.id.ri_editTextDesc)).getText().toString();
        Open311User open311User;
        if (!mAnonymousReportingCheckBox.isChecked()) {
            open311User = getOpen311UserFromUI();
        } else {
            open311User = getOpen311UserFromStrings();
        }
        String appUid = PreferenceUtils.getString(Application.APP_UID);
        IssueLocationHelper issueLocationHelper = getIssueLocationHelper();
        ServiceRequest.Builder builder = new ServiceRequest.Builder();
        if (!ListenerUtil.mutListener.listen(11350)) {
            builder.setJurisdiction_id(mOpen311.getJurisdiction()).setService_code(mService.getService_code()).setService_name(mService.getService_name()).setLatitude(issueLocationHelper.getIssueLocation().getLatitude()).setLongitude(issueLocationHelper.getIssueLocation().getLongitude()).setSummary(null).setDescription(description).setEmail(open311User.getEmail()).setFirst_name(open311User.getName()).setLast_name(open311User.getLastName()).setPhone(open311User.getPhone()).setAddress_string(getCurrentAddress()).setDevice_id(appUid);
        }
        if (!ListenerUtil.mutListener.listen(11352)) {
            if (mImagePath != null) {
                if (!ListenerUtil.mutListener.listen(11351)) {
                    attachImage(builder);
                }
            }
        }
        ServiceRequest serviceRequest = builder.createServiceRequest();
        List<Open311AttributePair> attributes = createOpen311Attributes(mServiceDescription);
        if (!ListenerUtil.mutListener.listen(11353)) {
            serviceRequest.setAttributes(attributes);
        }
        int errorCode = Open311Validator.validateServiceRequest(serviceRequest, mOpen311.getOpen311Option().getOpen311Type(), mServiceDescription);
        if (!ListenerUtil.mutListener.listen(11362)) {
            if (Open311Validator.isValid(errorCode)) {
                if (!ListenerUtil.mutListener.listen(11357)) {
                    // Append transit service parameters to issue description
                    if (ServiceUtils.isTransitServiceByType(mService.getType())) {
                        if (!ListenerUtil.mutListener.listen(11355)) {
                            description += getTransitIssueParameters(mService);
                        }
                        if (!ListenerUtil.mutListener.listen(11356)) {
                            serviceRequest.setDescription(description);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11358)) {
                    // Start progress
                    showProgressDialog(true);
                }
                if (!ListenerUtil.mutListener.listen(11359)) {
                    mRequestTask = new ServiceRequestTask(mOpen311, serviceRequest, this);
                }
                if (!ListenerUtil.mutListener.listen(11360)) {
                    mRequestTask.execute();
                }
                if (!ListenerUtil.mutListener.listen(11361)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), mService.getService_name());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11354)) {
                    createToastMessage(Open311Validator.getErrorMessageForServiceRequestByErrorCode(errorCode));
                }
            }
        }
    }

    /**
     * Attaches the captured image saved in this fragment to the provided builder
     *
     * @param builder the builder to attach the image to
     */
    private void attachImage(ServiceRequest.Builder builder) {
        // Downsample the image file to avoid uploading huge images
        File smallImageFile = null;
        try {
            if (!ListenerUtil.mutListener.listen(11365)) {
                smallImageFile = UIUtils.createImageFile(getContext(), "-small");
            }
        } catch (IOException ex) {
            if (!ListenerUtil.mutListener.listen(11363)) {
                // Error occurred while creating the File
                createToastMessage(getString(R.string.ri_resize_image_problem));
            }
            if (!ListenerUtil.mutListener.listen(11364)) {
                Log.e(TAG, "Couldn't resize image - " + ex);
            }
        }
        if (!ListenerUtil.mutListener.listen(11375)) {
            // Continue only if the File was successfully created
            if (smallImageFile != null) {
                // Max SeeClickFix resolution image is "800x600 image center cropped"
                final int WIDTH = 800, HEIGHT = 800;
                Bitmap smallImage;
                FileOutputStream out = null;
                try {
                    smallImage = UIUtils.decodeSampledBitmapFromFile(mImagePath, WIDTH, HEIGHT);
                    if (!ListenerUtil.mutListener.listen(11372)) {
                        out = new FileOutputStream(smallImageFile);
                    }
                    if (!ListenerUtil.mutListener.listen(11373)) {
                        smallImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    }
                    if (!ListenerUtil.mutListener.listen(11374)) {
                        out.flush();
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(11367)) {
                        e.printStackTrace();
                    }
                    if (!ListenerUtil.mutListener.listen(11368)) {
                        // Just use the full size image
                        smallImageFile = new File(mImagePath);
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(11371)) {
                        if (out != null) {
                            try {
                                if (!ListenerUtil.mutListener.listen(11370)) {
                                    out.close();
                                }
                            } catch (IOException e) {
                                if (!ListenerUtil.mutListener.listen(11369)) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11366)) {
                    // Just use the full size image
                    smallImageFile = new File(mImagePath);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11376)) {
            builder.setMedia(smallImageFile);
        }
    }

    /**
     * Generates stop and trip problem parameters for given open311 service
     *
     * @param service open311 service
     * @return a string containing parameters
     */
    private String getTransitIssueParameters(Service service) {
        StringBuilder sb = new StringBuilder();
        ObaStop obaStop = getIssueLocationHelper().getObaStop();
        if (!ListenerUtil.mutListener.listen(11377)) {
            if (obaStop == null) {
                return sb.toString();
            }
        }
        if (!ListenerUtil.mutListener.listen(11378)) {
            sb.append(getString(R.string.ri_append_start));
        }
        if (!ListenerUtil.mutListener.listen(11433)) {
            if (ServiceUtils.isTransitStopServiceByType(service.getType())) {
                if (!ListenerUtil.mutListener.listen(11431)) {
                    // Append stop service params
                    sb.append(getResources().getString(R.string.ri_append_gtfs_stop_id, obaStop.getId()));
                }
                if (!ListenerUtil.mutListener.listen(11432)) {
                    sb.append(getResources().getString(R.string.ri_append_stop_name, obaStop.getName()));
                }
            } else if (ServiceUtils.isTransitTripServiceByType(service.getType())) {
                if (!ListenerUtil.mutListener.listen(11430)) {
                    if (mArrivalInfo != null) {
                        // Append trip service params
                        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        if (!ListenerUtil.mutListener.listen(11379)) {
                            sb.append(getResources().getString(R.string.ri_append_service_date, dateFormat.format(new Date(mArrivalInfo.getServiceDate()))));
                        }
                        if (!ListenerUtil.mutListener.listen(11381)) {
                            if (mAgencyName != null) {
                                if (!ListenerUtil.mutListener.listen(11380)) {
                                    sb.append(getResources().getString(R.string.ri_append_agency_name, mAgencyName));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11382)) {
                            sb.append(getResources().getString(R.string.ri_append_gtfs_stop_id, obaStop.getId()));
                        }
                        if (!ListenerUtil.mutListener.listen(11383)) {
                            sb.append(getResources().getString(R.string.ri_append_stop_name, obaStop.getName()));
                        }
                        if (!ListenerUtil.mutListener.listen(11384)) {
                            sb.append(getResources().getString(R.string.ri_append_route_id, mArrivalInfo.getRouteId()));
                        }
                        String routeDisplayName = UIUtils.getRouteDisplayName(mArrivalInfo);
                        if (!ListenerUtil.mutListener.listen(11386)) {
                            if (!TextUtils.isEmpty(routeDisplayName)) {
                                if (!ListenerUtil.mutListener.listen(11385)) {
                                    sb.append(getResources().getString(R.string.ri_append_route_display_name, routeDisplayName));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11388)) {
                            if (mBlockId != null) {
                                if (!ListenerUtil.mutListener.listen(11387)) {
                                    sb.append(getResources().getString(R.string.ri_append_block_id, mBlockId));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11389)) {
                            sb.append(getResources().getString(R.string.ri_append_trip_id, mArrivalInfo.getTripId()));
                        }
                        if (!ListenerUtil.mutListener.listen(11390)) {
                            sb.append(getResources().getString(R.string.ri_append_trip_name, mArrivalInfo.getHeadsign()));
                        }
                        if (!ListenerUtil.mutListener.listen(11391)) {
                            sb.append(getResources().getString(R.string.ri_append_predicted, Boolean.valueOf(mArrivalInfo.getPredicted())));
                        }
                        ObaTripStatus tripStatus = mArrivalInfo.getTripStatus();
                        if (!ListenerUtil.mutListener.listen(11423)) {
                            if ((ListenerUtil.mutListener.listen(11392) ? (tripStatus != null || mArrivalInfo.getPredicted()) : (tripStatus != null && mArrivalInfo.getPredicted()))) {
                                if (!ListenerUtil.mutListener.listen(11393)) {
                                    sb.append(getResources().getString(R.string.ri_append_vehicle_id, mArrivalInfo.getVehicleId()));
                                }
                                Location lastKnownLocation = tripStatus.getLastKnownLocation();
                                if (!ListenerUtil.mutListener.listen(11395)) {
                                    if (lastKnownLocation != null) {
                                        String locationString = lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude();
                                        if (!ListenerUtil.mutListener.listen(11394)) {
                                            sb.append(getResources().getString(R.string.ri_append_vehicle_location, locationString));
                                        }
                                    }
                                }
                                DecimalFormat numberFormat = new DecimalFormat("#.000");
                                double scheduleDeviation = (ListenerUtil.mutListener.listen(11399) ? (tripStatus.getScheduleDeviation() % 60.0) : (ListenerUtil.mutListener.listen(11398) ? (tripStatus.getScheduleDeviation() * 60.0) : (ListenerUtil.mutListener.listen(11397) ? (tripStatus.getScheduleDeviation() - 60.0) : (ListenerUtil.mutListener.listen(11396) ? (tripStatus.getScheduleDeviation() + 60.0) : (tripStatus.getScheduleDeviation() / 60.0)))));
                                if (!ListenerUtil.mutListener.listen(11422)) {
                                    if ((ListenerUtil.mutListener.listen(11404) ? (scheduleDeviation >= 0.0) : (ListenerUtil.mutListener.listen(11403) ? (scheduleDeviation <= 0.0) : (ListenerUtil.mutListener.listen(11402) ? (scheduleDeviation > 0.0) : (ListenerUtil.mutListener.listen(11401) ? (scheduleDeviation < 0.0) : (ListenerUtil.mutListener.listen(11400) ? (scheduleDeviation != 0.0) : (scheduleDeviation == 0.0))))))) {
                                        if (!ListenerUtil.mutListener.listen(11421)) {
                                            sb.append(getResources().getString(R.string.ri_append_schedule_deviation, "0"));
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(11409) ? (scheduleDeviation >= 0) : (ListenerUtil.mutListener.listen(11408) ? (scheduleDeviation <= 0) : (ListenerUtil.mutListener.listen(11407) ? (scheduleDeviation > 0) : (ListenerUtil.mutListener.listen(11406) ? (scheduleDeviation != 0) : (ListenerUtil.mutListener.listen(11405) ? (scheduleDeviation == 0) : (scheduleDeviation < 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(11420)) {
                                            sb.append(getResources().getString(R.string.ri_append_schedule_deviation_early, numberFormat.format((ListenerUtil.mutListener.listen(11419) ? (scheduleDeviation % -1.0) : (ListenerUtil.mutListener.listen(11418) ? (scheduleDeviation / -1.0) : (ListenerUtil.mutListener.listen(11417) ? (scheduleDeviation - -1.0) : (ListenerUtil.mutListener.listen(11416) ? (scheduleDeviation + -1.0) : (scheduleDeviation * -1.0))))))));
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(11414) ? (scheduleDeviation >= 0) : (ListenerUtil.mutListener.listen(11413) ? (scheduleDeviation <= 0) : (ListenerUtil.mutListener.listen(11412) ? (scheduleDeviation < 0) : (ListenerUtil.mutListener.listen(11411) ? (scheduleDeviation != 0) : (ListenerUtil.mutListener.listen(11410) ? (scheduleDeviation == 0) : (scheduleDeviation > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(11415)) {
                                            sb.append(getResources().getString(R.string.ri_append_schedule_deviation_late, numberFormat.format(scheduleDeviation)));
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11424)) {
                            dateFormat = new SimpleDateFormat("hh:mm a");
                        }
                        if (!ListenerUtil.mutListener.listen(11429)) {
                            if (mArrivalInfo.getPredicted()) {
                                if (!ListenerUtil.mutListener.listen(11427)) {
                                    sb.append(getResources().getString(R.string.ri_append_arrival_time, dateFormat.format(new Date(mArrivalInfo.getPredictedArrivalTime()))));
                                }
                                if (!ListenerUtil.mutListener.listen(11428)) {
                                    sb.append(getResources().getString(R.string.ri_append_departure_time, dateFormat.format(new Date(mArrivalInfo.getPredictedDepartureTime()))));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11425)) {
                                    sb.append(getResources().getString(R.string.ri_append_arrival_time, dateFormat.format(new Date(mArrivalInfo.getScheduledArrivalTime()))));
                                }
                                if (!ListenerUtil.mutListener.listen(11426)) {
                                    sb.append(getResources().getString(R.string.ri_append_departure_time, dateFormat.format(new Date(mArrivalInfo.getScheduledDepartureTime()))));
                                }
                            }
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Creates open311 question and answer attributes to submit a report
     * Reads from dynamically created UI
     *
     * @param serviceDescription contains attribute types
     * @return List of code value pair of attributes
     */
    private List<Open311AttributePair> createOpen311Attributes(ServiceDescription serviceDescription) {
        List<Open311AttributePair> attributes = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(11459)) {
            {
                long _loopCounter153 = 0;
                for (Open311Attribute open311Attribute : serviceDescription.getAttributes()) {
                    ListenerUtil.loopListener.listen("_loopCounter153", ++_loopCounter153);
                    if (!ListenerUtil.mutListener.listen(11458)) {
                        if (Boolean.valueOf(open311Attribute.getVariable())) {
                            if (!ListenerUtil.mutListener.listen(11457)) {
                                if ((ListenerUtil.mutListener.listen(11436) ? ((ListenerUtil.mutListener.listen(11435) ? ((ListenerUtil.mutListener.listen(11434) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) && Open311DataType.NUMBER.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11434) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) || Open311DataType.NUMBER.equals(open311Attribute.getDatatype()))) && Open311DataType.DATETIME.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11435) ? ((ListenerUtil.mutListener.listen(11434) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) && Open311DataType.NUMBER.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11434) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) || Open311DataType.NUMBER.equals(open311Attribute.getDatatype()))) || Open311DataType.DATETIME.equals(open311Attribute.getDatatype())))) {
                                    EditText et = (EditText) mDynamicAttributeUIMap.get(open311Attribute.getCode());
                                    if (!ListenerUtil.mutListener.listen(11456)) {
                                        if (et != null) {
                                            if (!ListenerUtil.mutListener.listen(11455)) {
                                                attributes.add(new Open311AttributePair(open311Attribute.getCode(), et.getText().toString(), open311Attribute.getDatatype()));
                                            }
                                        }
                                    }
                                } else if (Open311DataType.SINGLEVALUELIST.equals(open311Attribute.getDatatype())) {
                                    RadioGroup rg = (RadioGroup) mDynamicAttributeUIMap.get(open311Attribute.getCode());
                                    if (!ListenerUtil.mutListener.listen(11454)) {
                                        if (rg != null) {
                                            int count = rg.getChildCount();
                                            if (!ListenerUtil.mutListener.listen(11453)) {
                                                {
                                                    long _loopCounter152 = 0;
                                                    for (int i = 0; (ListenerUtil.mutListener.listen(11452) ? (i >= count) : (ListenerUtil.mutListener.listen(11451) ? (i <= count) : (ListenerUtil.mutListener.listen(11450) ? (i > count) : (ListenerUtil.mutListener.listen(11449) ? (i != count) : (ListenerUtil.mutListener.listen(11448) ? (i == count) : (i < count)))))); i++) {
                                                        ListenerUtil.loopListener.listen("_loopCounter152", ++_loopCounter152);
                                                        RadioButton rb = (RadioButton) rg.getChildAt(i);
                                                        if (!ListenerUtil.mutListener.listen(11447)) {
                                                            if (rb.isChecked()) {
                                                                String attributeKey = mOpen311AttributeKeyNameMap.get(open311Attribute.getCode() + rb.getText().toString());
                                                                if (!ListenerUtil.mutListener.listen(11446)) {
                                                                    attributes.add(new Open311AttributePair(open311Attribute.getCode(), attributeKey, open311Attribute.getDatatype()));
                                                                }
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if (Open311DataType.MULTIVALUELIST.equals(open311Attribute.getDatatype())) {
                                    LinearLayout ll = (LinearLayout) mDynamicAttributeUIMap.get(open311Attribute.getCode());
                                    if (!ListenerUtil.mutListener.listen(11445)) {
                                        if (ll != null) {
                                            int count = ll.getChildCount();
                                            if (!ListenerUtil.mutListener.listen(11444)) {
                                                {
                                                    long _loopCounter151 = 0;
                                                    for (int i = 0; (ListenerUtil.mutListener.listen(11443) ? (i >= count) : (ListenerUtil.mutListener.listen(11442) ? (i <= count) : (ListenerUtil.mutListener.listen(11441) ? (i > count) : (ListenerUtil.mutListener.listen(11440) ? (i != count) : (ListenerUtil.mutListener.listen(11439) ? (i == count) : (i < count)))))); i++) {
                                                        ListenerUtil.loopListener.listen("_loopCounter151", ++_loopCounter151);
                                                        CheckBox cb = (CheckBox) ll.getChildAt(i);
                                                        if (!ListenerUtil.mutListener.listen(11438)) {
                                                            if (cb.isChecked()) {
                                                                String attributeKey = mOpen311AttributeKeyNameMap.get(open311Attribute.getCode() + cb.getText().toString());
                                                                if (!ListenerUtil.mutListener.listen(11437)) {
                                                                    attributes.add(new Open311AttributePair(open311Attribute.getCode(), attributeKey, open311Attribute.getDatatype()));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return attributes;
    }

    /**
     * This method dynamically reads all user inputted the values from the screen and puts into a
     * list
     *
     * @param serviceDescription displayed service description
     * @return List of attribute values
     */
    private List<AttributeValue> createAttributeValues(ServiceDescription serviceDescription) {
        List<AttributeValue> values = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(11460)) {
            if (serviceDescription == null) {
                return values;
            }
        }
        if (!ListenerUtil.mutListener.listen(11495)) {
            {
                long _loopCounter156 = 0;
                for (Open311Attribute open311Attribute : serviceDescription.getAttributes()) {
                    ListenerUtil.loopListener.listen("_loopCounter156", ++_loopCounter156);
                    if (!ListenerUtil.mutListener.listen(11494)) {
                        if (Boolean.valueOf(open311Attribute.getVariable())) {
                            if (!ListenerUtil.mutListener.listen(11493)) {
                                if ((ListenerUtil.mutListener.listen(11463) ? ((ListenerUtil.mutListener.listen(11462) ? ((ListenerUtil.mutListener.listen(11461) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) && Open311DataType.NUMBER.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11461) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) || Open311DataType.NUMBER.equals(open311Attribute.getDatatype()))) && Open311DataType.DATETIME.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11462) ? ((ListenerUtil.mutListener.listen(11461) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) && Open311DataType.NUMBER.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11461) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) || Open311DataType.NUMBER.equals(open311Attribute.getDatatype()))) || Open311DataType.DATETIME.equals(open311Attribute.getDatatype())))) {
                                    EditText et = (EditText) mDynamicAttributeUIMap.get(open311Attribute.getCode());
                                    if (!ListenerUtil.mutListener.listen(11492)) {
                                        if (et != null) {
                                            AttributeValue value = new AttributeValue(open311Attribute.getCode());
                                            if (!ListenerUtil.mutListener.listen(11490)) {
                                                value.addValue(et.getText().toString());
                                            }
                                            if (!ListenerUtil.mutListener.listen(11491)) {
                                                values.add(value);
                                            }
                                        }
                                    }
                                } else if (Open311DataType.SINGLEVALUELIST.equals(open311Attribute.getDatatype())) {
                                    RadioGroup rg = (RadioGroup) mDynamicAttributeUIMap.get(open311Attribute.getCode());
                                    if (!ListenerUtil.mutListener.listen(11489)) {
                                        if (rg != null) {
                                            int count = rg.getChildCount();
                                            if (!ListenerUtil.mutListener.listen(11488)) {
                                                {
                                                    long _loopCounter155 = 0;
                                                    for (int i = 0; (ListenerUtil.mutListener.listen(11487) ? (i >= count) : (ListenerUtil.mutListener.listen(11486) ? (i <= count) : (ListenerUtil.mutListener.listen(11485) ? (i > count) : (ListenerUtil.mutListener.listen(11484) ? (i != count) : (ListenerUtil.mutListener.listen(11483) ? (i == count) : (i < count)))))); i++) {
                                                        ListenerUtil.loopListener.listen("_loopCounter155", ++_loopCounter155);
                                                        RadioButton rb = (RadioButton) rg.getChildAt(i);
                                                        if (!ListenerUtil.mutListener.listen(11482)) {
                                                            if (rb.isChecked()) {
                                                                AttributeValue value = new AttributeValue(open311Attribute.getCode());
                                                                if (!ListenerUtil.mutListener.listen(11480)) {
                                                                    value.addValue(rb.getText().toString());
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(11481)) {
                                                                    values.add(value);
                                                                }
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if (Open311DataType.MULTIVALUELIST.equals(open311Attribute.getDatatype())) {
                                    LinearLayout ll = (LinearLayout) mDynamicAttributeUIMap.get(open311Attribute.getCode());
                                    if (!ListenerUtil.mutListener.listen(11479)) {
                                        if (ll != null) {
                                            int count = ll.getChildCount();
                                            AttributeValue value = new AttributeValue(open311Attribute.getCode());
                                            if (!ListenerUtil.mutListener.listen(11471)) {
                                                {
                                                    long _loopCounter154 = 0;
                                                    for (int i = 0; (ListenerUtil.mutListener.listen(11470) ? (i >= count) : (ListenerUtil.mutListener.listen(11469) ? (i <= count) : (ListenerUtil.mutListener.listen(11468) ? (i > count) : (ListenerUtil.mutListener.listen(11467) ? (i != count) : (ListenerUtil.mutListener.listen(11466) ? (i == count) : (i < count)))))); i++) {
                                                        ListenerUtil.loopListener.listen("_loopCounter154", ++_loopCounter154);
                                                        CheckBox cb = (CheckBox) ll.getChildAt(i);
                                                        if (!ListenerUtil.mutListener.listen(11465)) {
                                                            if (cb.isChecked()) {
                                                                if (!ListenerUtil.mutListener.listen(11464)) {
                                                                    value.addValue(cb.getText().toString());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(11478)) {
                                                if ((ListenerUtil.mutListener.listen(11476) ? (value.getValues().size() >= 0) : (ListenerUtil.mutListener.listen(11475) ? (value.getValues().size() <= 0) : (ListenerUtil.mutListener.listen(11474) ? (value.getValues().size() < 0) : (ListenerUtil.mutListener.listen(11473) ? (value.getValues().size() != 0) : (ListenerUtil.mutListener.listen(11472) ? (value.getValues().size() == 0) : (value.getValues().size() > 0)))))))
                                                    if (!ListenerUtil.mutListener.listen(11477)) {
                                                        values.add(value);
                                                    }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return values;
    }

    /**
     * From https://developer.android.com/training/camera/photobasics.html#TaskPath
     */
    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!ListenerUtil.mutListener.listen(11504)) {
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    if (!ListenerUtil.mutListener.listen(11498)) {
                        photoFile = UIUtils.createImageFile(getContext(), null);
                    }
                } catch (IOException ex) {
                    if (!ListenerUtil.mutListener.listen(11496)) {
                        // Error occurred while creating the File
                        createToastMessage(getString(R.string.ri_open_camera_problem));
                    }
                    if (!ListenerUtil.mutListener.listen(11497)) {
                        Log.e(TAG, "Couldn't open camera - " + ex);
                    }
                }
                if (!ListenerUtil.mutListener.listen(11503)) {
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        if (!ListenerUtil.mutListener.listen(11499)) {
                            // Save a file: path for use with ACTION_VIEW intents
                            mImagePath = photoFile.getAbsolutePath();
                        }
                        if (!ListenerUtil.mutListener.listen(11500)) {
                            mCapturedImageURI = Uri.fromFile(photoFile);
                        }
                        if (!ListenerUtil.mutListener.listen(11501)) {
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
                        }
                        if (!ListenerUtil.mutListener.listen(11502)) {
                            startActivityForResult(takePictureIntent, ReportConstants.CAPTURE_PICTURE_INTENT);
                        }
                    }
                }
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (!ListenerUtil.mutListener.listen(11505)) {
            startActivityForResult(intent, ReportConstants.GALLERY_INTENT);
        }
    }

    /**
     * Dynamically creates Open311 questions from service description
     *
     * @param serviceDescription contains Open311 questions
     */
    public void createServiceDescriptionUI(ServiceDescription serviceDescription) {
        if (!ListenerUtil.mutListener.listen(11506)) {
            clearInfoField();
        }
        if (!ListenerUtil.mutListener.listen(11507)) {
            this.mServiceDescription = serviceDescription;
        }
        if (!ListenerUtil.mutListener.listen(11510)) {
            if ((ListenerUtil.mutListener.listen(11508) ? (!"".equals(mService.getDescription()) || mService.getDescription() != null) : (!"".equals(mService.getDescription()) && mService.getDescription() != null))) {
                if (!ListenerUtil.mutListener.listen(11509)) {
                    addDescriptionText(mService.getDescription());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11513)) {
            if ((ListenerUtil.mutListener.listen(11511) ? (mArrivalInfo != null || ServiceUtils.isTransitTripServiceByType(mService.getType())) : (mArrivalInfo != null && ServiceUtils.isTransitTripServiceByType(mService.getType())))) {
                if (!ListenerUtil.mutListener.listen(11512)) {
                    createTripHeadsign(mArrivalInfo.getHeadsign());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11523)) {
            {
                long _loopCounter157 = 0;
                for (Open311Attribute open311Attribute : serviceDescription.getAttributes()) {
                    ListenerUtil.loopListener.listen("_loopCounter157", ++_loopCounter157);
                    if (!ListenerUtil.mutListener.listen(11522)) {
                        if (!Boolean.valueOf(open311Attribute.getVariable())) {
                            if (!ListenerUtil.mutListener.listen(11521)) {
                                addDescriptionText(open311Attribute.getDescription());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11520)) {
                                if ((ListenerUtil.mutListener.listen(11516) ? ((ListenerUtil.mutListener.listen(11515) ? ((ListenerUtil.mutListener.listen(11514) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) && Open311DataType.NUMBER.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11514) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) || Open311DataType.NUMBER.equals(open311Attribute.getDatatype()))) && Open311DataType.DATETIME.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11515) ? ((ListenerUtil.mutListener.listen(11514) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) && Open311DataType.NUMBER.equals(open311Attribute.getDatatype())) : ((ListenerUtil.mutListener.listen(11514) ? (Open311DataType.STRING.equals(open311Attribute.getDatatype()) && Open311DataType.TEXT.equals(open311Attribute.getDatatype())) : (Open311DataType.STRING.equals(open311Attribute.getDatatype()) || Open311DataType.TEXT.equals(open311Attribute.getDatatype()))) || Open311DataType.NUMBER.equals(open311Attribute.getDatatype()))) || Open311DataType.DATETIME.equals(open311Attribute.getDatatype())))) {
                                    if (!ListenerUtil.mutListener.listen(11519)) {
                                        createEditText(open311Attribute);
                                    }
                                } else if (Open311DataType.SINGLEVALUELIST.equals(open311Attribute.getDatatype())) {
                                    if (!ListenerUtil.mutListener.listen(11518)) {
                                        createSingleValueList(open311Attribute);
                                    }
                                } else if (Open311DataType.MULTIVALUELIST.equals(open311Attribute.getDatatype())) {
                                    if (!ListenerUtil.mutListener.listen(11517)) {
                                        createMultiValueList(open311Attribute);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Dynamically creates an edit text
     *
     * @param open311Attribute contains the open311 attributes
     */
    private void createEditText(Open311Attribute open311Attribute) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.report_issue_text_item, null, false);
        ImageView icon = ((ImageView) layout.findViewById(R.id.ri_ic_question_answer));
        if (!ListenerUtil.mutListener.listen(11524)) {
            icon.setColorFilter(getResources().getColor(R.color.material_gray));
        }
        Spannable desc = new SpannableString(MyTextUtils.toSentenceCase(open311Attribute.getDescription()));
        EditText editText = ((EditText) layout.findViewById(R.id.riti_editText));
        if (!ListenerUtil.mutListener.listen(11528)) {
            if (open311Attribute.getRequired()) {
                Spannable req = new SpannableString("(required)");
                if (!ListenerUtil.mutListener.listen(11526)) {
                    req.setSpan(new ForegroundColorSpan(Color.RED), 0, req.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(11527)) {
                    editText.setHint(TextUtils.concat(desc, " ", req));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11525)) {
                    editText.setHint(desc);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11531)) {
            if (Open311DataType.NUMBER.equals(open311Attribute.getDatatype())) {
                if (!ListenerUtil.mutListener.listen(11530)) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            } else if (Open311DataType.DATETIME.equals(open311Attribute.getDatatype())) {
                if (!ListenerUtil.mutListener.listen(11529)) {
                    editText.setInputType(InputType.TYPE_CLASS_DATETIME);
                }
            }
        }
        // Restore view state from attribute result hash map
        AttributeValue av = mAttributeValueHashMap.get(open311Attribute.getCode());
        if (!ListenerUtil.mutListener.listen(11533)) {
            if (av != null) {
                if (!ListenerUtil.mutListener.listen(11532)) {
                    editText.setText(av.getSingleValue());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11538)) {
            // And if this is a bus stop field
            if ((ListenerUtil.mutListener.listen(11534) ? (ServiceUtils.isTransitServiceByType(mService.getType()) || ServiceUtils.isStopIdField(open311Attribute.getDescription())) : (ServiceUtils.isTransitServiceByType(mService.getType()) && ServiceUtils.isStopIdField(open311Attribute.getDescription())))) {
                if (!ListenerUtil.mutListener.listen(11535)) {
                    icon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_stop_flag_triangle));
                }
                ObaStop obaStop = getIssueLocationHelper().getObaStop();
                if (!ListenerUtil.mutListener.listen(11537)) {
                    if (obaStop != null) {
                        if (!ListenerUtil.mutListener.listen(11536)) {
                            editText.setText(obaStop.getStopCode());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11539)) {
            mInfoLayout.addView(layout);
        }
        if (!ListenerUtil.mutListener.listen(11540)) {
            mDynamicAttributeUIMap.put(open311Attribute.getCode(), editText);
        }
    }

    /**
     * Dynamically creates radio buttons
     *
     * @param open311Attribute contains the open311 attributes
     */
    private void createSingleValueList(Open311Attribute open311Attribute) {
        ArrayList<Object> values = (ArrayList<Object>) open311Attribute.getValues();
        if (!ListenerUtil.mutListener.listen(11574)) {
            if ((ListenerUtil.mutListener.listen(11546) ? (values != null || (ListenerUtil.mutListener.listen(11545) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(11544) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(11543) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(11542) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(11541) ? (values.size() == 0) : (values.size() > 0))))))) : (values != null && (ListenerUtil.mutListener.listen(11545) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(11544) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(11543) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(11542) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(11541) ? (values.size() == 0) : (values.size() > 0))))))))) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.report_issue_single_value_list_item, null, false);
                if (!ListenerUtil.mutListener.listen(11547)) {
                    layout.setSaveEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(11548)) {
                    ((ImageView) layout.findViewById(R.id.ri_ic_radio)).setColorFilter(getResources().getColor(R.color.material_gray));
                }
                Spannable word = new SpannableString(open311Attribute.getDescription());
                if (!ListenerUtil.mutListener.listen(11549)) {
                    ((TextView) layout.findViewById(R.id.risvli_textView)).setText(word);
                }
                if (!ListenerUtil.mutListener.listen(11552)) {
                    if (open311Attribute.getRequired()) {
                        Spannable wordTwo = new SpannableString(" *Required");
                        if (!ListenerUtil.mutListener.listen(11550)) {
                            wordTwo.setSpan(new ForegroundColorSpan(Color.RED), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        if (!ListenerUtil.mutListener.listen(11551)) {
                            ((TextView) layout.findViewById(R.id.risvli_textView)).append(wordTwo);
                        }
                    }
                }
                RadioGroup rg = (RadioGroup) layout.findViewById(R.id.risvli_radioGroup);
                if (!ListenerUtil.mutListener.listen(11553)) {
                    rg.setOrientation(RadioGroup.VERTICAL);
                }
                // Restore view state from attribute result hash map
                AttributeValue av = mAttributeValueHashMap.get(open311Attribute.getCode());
                String entryValue = null;
                if (!ListenerUtil.mutListener.listen(11555)) {
                    if (av != null) {
                        if (!ListenerUtil.mutListener.listen(11554)) {
                            entryValue = av.getSingleValue();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11571)) {
                    {
                        long _loopCounter159 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(11570) ? (i >= values.size()) : (ListenerUtil.mutListener.listen(11569) ? (i <= values.size()) : (ListenerUtil.mutListener.listen(11568) ? (i > values.size()) : (ListenerUtil.mutListener.listen(11567) ? (i != values.size()) : (ListenerUtil.mutListener.listen(11566) ? (i == values.size()) : (i < values.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter159", ++_loopCounter159);
                            LinkedHashMap<String, String> value = (LinkedHashMap<String, String>) values.get(i);
                            RadioButton rb = new RadioButton(getActivity());
                            if (!ListenerUtil.mutListener.listen(11556)) {
                                // the RadioButtons are added to the radioGroup instead of the layout
                                rg.addView(rb);
                            }
                            String attributeKey = "";
                            String attributeValue = "";
                            if (!ListenerUtil.mutListener.listen(11564)) {
                                {
                                    long _loopCounter158 = 0;
                                    for (LinkedHashMap.Entry<String, String> entry : value.entrySet()) {
                                        ListenerUtil.loopListener.listen("_loopCounter158", ++_loopCounter158);
                                        if (!ListenerUtil.mutListener.listen(11563)) {
                                            if (Open311Attribute.NAME.equals(entry.getKey())) {
                                                if (!ListenerUtil.mutListener.listen(11558)) {
                                                    rb.setText(entry.getValue());
                                                }
                                                if (!ListenerUtil.mutListener.listen(11561)) {
                                                    if ((ListenerUtil.mutListener.listen(11559) ? (entryValue != null || entryValue.equalsIgnoreCase(entry.getValue())) : (entryValue != null && entryValue.equalsIgnoreCase(entry.getValue())))) {
                                                        if (!ListenerUtil.mutListener.listen(11560)) {
                                                            rb.setChecked(true);
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(11562)) {
                                                    attributeKey = open311Attribute.getCode() + entry.getValue();
                                                }
                                            } else if (Open311Attribute.KEY.equals(entry.getKey())) {
                                                if (!ListenerUtil.mutListener.listen(11557)) {
                                                    attributeValue = entry.getValue();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11565)) {
                                mOpen311AttributeKeyNameMap.put(attributeKey, attributeValue);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11572)) {
                    mInfoLayout.addView(layout);
                }
                if (!ListenerUtil.mutListener.listen(11573)) {
                    mDynamicAttributeUIMap.put(open311Attribute.getCode(), rg);
                }
            }
        }
    }

    /**
     * Dynamically creates checkboxes
     *
     * @param open311Attribute contains the open311 attributes
     */
    private void createMultiValueList(Open311Attribute open311Attribute) {
        ArrayList<Object> values = (ArrayList<Object>) open311Attribute.getValues();
        if (!ListenerUtil.mutListener.listen(11604)) {
            if ((ListenerUtil.mutListener.listen(11580) ? (values != null || (ListenerUtil.mutListener.listen(11579) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(11578) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(11577) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(11576) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(11575) ? (values.size() == 0) : (values.size() > 0))))))) : (values != null && (ListenerUtil.mutListener.listen(11579) ? (values.size() >= 0) : (ListenerUtil.mutListener.listen(11578) ? (values.size() <= 0) : (ListenerUtil.mutListener.listen(11577) ? (values.size() < 0) : (ListenerUtil.mutListener.listen(11576) ? (values.size() != 0) : (ListenerUtil.mutListener.listen(11575) ? (values.size() == 0) : (values.size() > 0))))))))) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.report_issue_multi_value_list_item, null, false);
                if (!ListenerUtil.mutListener.listen(11581)) {
                    ((ImageView) layout.findViewById(R.id.ri_ic_checkbox)).setColorFilter(getResources().getColor(R.color.material_gray));
                }
                Spannable word = new SpannableString(open311Attribute.getDescription());
                if (!ListenerUtil.mutListener.listen(11582)) {
                    ((TextView) layout.findViewById(R.id.rimvli_textView)).setText(word);
                }
                if (!ListenerUtil.mutListener.listen(11585)) {
                    if (open311Attribute.getRequired()) {
                        Spannable wordTwo = new SpannableString(" *Required");
                        if (!ListenerUtil.mutListener.listen(11583)) {
                            wordTwo.setSpan(new ForegroundColorSpan(Color.RED), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        if (!ListenerUtil.mutListener.listen(11584)) {
                            ((TextView) layout.findViewById(R.id.rimvli_textView)).append(wordTwo);
                        }
                    }
                }
                // Restore view state from attribute result hash map
                AttributeValue av = mAttributeValueHashMap.get(open311Attribute.getCode());
                LinearLayout cg = (LinearLayout) layout.findViewById(R.id.rimvli_checkBoxGroup);
                if (!ListenerUtil.mutListener.listen(11601)) {
                    {
                        long _loopCounter161 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(11600) ? (i >= values.size()) : (ListenerUtil.mutListener.listen(11599) ? (i <= values.size()) : (ListenerUtil.mutListener.listen(11598) ? (i > values.size()) : (ListenerUtil.mutListener.listen(11597) ? (i != values.size()) : (ListenerUtil.mutListener.listen(11596) ? (i == values.size()) : (i < values.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter161", ++_loopCounter161);
                            LinkedHashMap<String, String> value = (LinkedHashMap<String, String>) values.get(i);
                            CheckBox cb = new CheckBox(getActivity());
                            if (!ListenerUtil.mutListener.listen(11586)) {
                                cg.addView(cb);
                            }
                            String attributeKey = "";
                            String attributeValue = "";
                            if (!ListenerUtil.mutListener.listen(11594)) {
                                {
                                    long _loopCounter160 = 0;
                                    for (LinkedHashMap.Entry<String, String> entry : value.entrySet()) {
                                        ListenerUtil.loopListener.listen("_loopCounter160", ++_loopCounter160);
                                        if (!ListenerUtil.mutListener.listen(11593)) {
                                            if (Open311Attribute.NAME.equals(entry.getKey())) {
                                                if (!ListenerUtil.mutListener.listen(11588)) {
                                                    cb.setText(entry.getValue());
                                                }
                                                if (!ListenerUtil.mutListener.listen(11591)) {
                                                    if ((ListenerUtil.mutListener.listen(11589) ? (av != null || av.getValues().contains(entry.getValue())) : (av != null && av.getValues().contains(entry.getValue())))) {
                                                        if (!ListenerUtil.mutListener.listen(11590)) {
                                                            cb.setChecked(true);
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(11592)) {
                                                    attributeKey = open311Attribute.getCode() + entry.getValue();
                                                }
                                            } else if (Open311Attribute.KEY.equals(entry.getKey())) {
                                                if (!ListenerUtil.mutListener.listen(11587)) {
                                                    attributeValue = entry.getValue();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11595)) {
                                mOpen311AttributeKeyNameMap.put(attributeKey, attributeValue);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11602)) {
                    mInfoLayout.addView(layout);
                }
                if (!ListenerUtil.mutListener.listen(11603)) {
                    mDynamicAttributeUIMap.put(open311Attribute.getCode(), cg);
                }
            }
        }
    }

    private void createTripHeadsign(String text) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.report_issue_description_item, null, false);
        LinearLayout linear = (LinearLayout) findViewById(R.id.ri_report_stop_problem);
        TextView tv = ((TextView) layout.findViewById(R.id.riii_textView));
        if (!ListenerUtil.mutListener.listen(11605)) {
            tv.setText(UIUtils.formatDisplayText(text));
        }
        if (!ListenerUtil.mutListener.listen(11606)) {
            tv.setTypeface(null, Typeface.NORMAL);
        }
        if (!ListenerUtil.mutListener.listen(11607)) {
            linear.addView(layout, 0);
        }
        ImageView imageView = (ImageView) layout.findViewById(R.id.ic_action_info);
        if (!ListenerUtil.mutListener.listen(11608)) {
            imageView.setImageResource(R.drawable.ic_trip_details);
        }
        if (!ListenerUtil.mutListener.listen(11609)) {
            imageView.setColorFilter(getResources().getColor(R.color.material_gray));
        }
    }

    private void clearInfoField() {
        if (!ListenerUtil.mutListener.listen(11610)) {
            mInfoLayout.removeAllViewsInLayout();
        }
    }

    private void addDescriptionText(String text) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.report_issue_description_item, null, false);
        if (!ListenerUtil.mutListener.listen(11611)) {
            ((TextView) layout.findViewById(R.id.riii_textView)).setText(text);
        }
        if (!ListenerUtil.mutListener.listen(11612)) {
            mInfoLayout.addView(layout);
        }
        if (!ListenerUtil.mutListener.listen(11613)) {
            ((ImageView) layout.findViewById(R.id.ic_action_info)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
    }

    /**
     * Set up USer contact information view
     */
    private void setUpContactInfoViews() {
        Open311User open311User = getOpen311UserFromSharedPref();
        if (!ListenerUtil.mutListener.listen(11615)) {
            if (open311User.getName() != null)
                if (!ListenerUtil.mutListener.listen(11614)) {
                    mContactNameView.setText(open311User.getName());
                }
        }
        if (!ListenerUtil.mutListener.listen(11617)) {
            if (open311User.getLastName() != null)
                if (!ListenerUtil.mutListener.listen(11616)) {
                    mContactLastNameView.setText(open311User.getLastName());
                }
        }
        if (!ListenerUtil.mutListener.listen(11619)) {
            if (open311User.getEmail() != null)
                if (!ListenerUtil.mutListener.listen(11618)) {
                    mContactEmailView.setText(open311User.getEmail());
                }
        }
        if (!ListenerUtil.mutListener.listen(11621)) {
            if (open311User.getPhone() != null)
                if (!ListenerUtil.mutListener.listen(11620)) {
                    mContactPhoneView.setText(open311User.getPhone());
                }
        }
    }

    /**
     * This method disables or enables editing for contact info fields
     *
     * @param isDisabled true if you want to disable contact info fields
     */
    private void disableEnableContactInfoViews(boolean isDisabled) {
        if (!ListenerUtil.mutListener.listen(11622)) {
            mContactNameView.setEnabled(!isDisabled);
        }
        if (!ListenerUtil.mutListener.listen(11623)) {
            mContactLastNameView.setEnabled(!isDisabled);
        }
        if (!ListenerUtil.mutListener.listen(11624)) {
            mContactEmailView.setEnabled(!isDisabled);
        }
        if (!ListenerUtil.mutListener.listen(11625)) {
            mContactPhoneView.setEnabled(!isDisabled);
        }
    }

    /**
     * Get open311 user from shared preferences
     *
     * @return Open311User
     */
    private Open311User getOpen311UserFromSharedPref() {
        return new Open311User(PreferenceUtils.getString(ReportConstants.PREF_NAME), PreferenceUtils.getString(ReportConstants.PREF_LAST_NAME), PreferenceUtils.getString(ReportConstants.PREF_EMAIL), PreferenceUtils.getString(ReportConstants.PREF_PHONE));
    }

    /**
     * Get open311 user from fields on the screen
     *
     * @return Open311User
     */
    private Open311User getOpen311UserFromUI() {
        String name = ((EditText) findViewById(R.id.rici_name_editText)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.rici_lastname_editText)).getText().toString();
        String email = ((EditText) findViewById(R.id.rici_email_editText)).getText().toString();
        String phone = ((EditText) findViewById(R.id.rici_phone_editText)).getText().toString();
        return new Open311User(name, lastName, email, phone);
    }

    /**
     * Get open311 user from static strings for anonymous reporting
     *
     * @return Open311User
     */
    public Open311User getOpen311UserFromStrings() {
        String name = getString(R.string.ri_static_user_name);
        String lastName = getString(R.string.ri_static_user_last_name);
        String email = getString(R.string.ri_static_user_email);
        String phone = getString(R.string.ri_static_user_phone);
        return new Open311User(name, lastName, email, phone);
    }

    /**
     * Save open311 user to shared prefs
     */
    private void saveOpen311User() {
        String name = ((EditText) findViewById(R.id.rici_name_editText)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.rici_lastname_editText)).getText().toString();
        String email = ((EditText) findViewById(R.id.rici_email_editText)).getText().toString();
        String phone = ((EditText) findViewById(R.id.rici_phone_editText)).getText().toString();
        if (!ListenerUtil.mutListener.listen(11626)) {
            PreferenceUtils.saveString(ReportConstants.PREF_NAME, name);
        }
        if (!ListenerUtil.mutListener.listen(11627)) {
            PreferenceUtils.saveString(ReportConstants.PREF_LAST_NAME, lastName);
        }
        if (!ListenerUtil.mutListener.listen(11628)) {
            PreferenceUtils.saveString(ReportConstants.PREF_EMAIL, email);
        }
        if (!ListenerUtil.mutListener.listen(11629)) {
            PreferenceUtils.saveString(ReportConstants.PREF_PHONE, phone);
        }
    }

    private IssueLocationHelper getIssueLocationHelper() {
        return ((InfrastructureIssueActivity) getActivity()).getIssueLocationHelper();
    }

    private String getCurrentAddress() {
        String address = ((InfrastructureIssueActivity) getActivity()).getCurrentAddress();
        if (TextUtils.isEmpty(address)) {
            return null;
        } else {
            return address;
        }
    }

    /**
     * Show a progress icon on the action bar
     *
     * @param visible show or hide the progress icon based
     */
    private void showProgress(Boolean visible) {
        InfrastructureIssueActivity activity = ((InfrastructureIssueActivity) getActivity());
        if (!ListenerUtil.mutListener.listen(11631)) {
            if (activity != null) {
                if (!ListenerUtil.mutListener.listen(11630)) {
                    activity.showProgress(visible);
                }
            }
        }
    }

    /**
     * Show a progress dialog on the screen
     *
     * @param visible show or hide the progress icon based
     */
    private void showProgressDialog(boolean visible) {
        if (!ListenerUtil.mutListener.listen(11648)) {
            if (visible) {
                if (!ListenerUtil.mutListener.listen(11636)) {
                    mIsProgressDialogShowing = true;
                }
                if (!ListenerUtil.mutListener.listen(11637)) {
                    mProgressDialog = new ProgressDialog(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(11638)) {
                    mProgressDialog.setMessage(getActivity().getString(R.string.ri_submitting_message));
                }
                if (!ListenerUtil.mutListener.listen(11639)) {
                    mProgressDialog.setIndeterminate(true);
                }
                if (!ListenerUtil.mutListener.listen(11640)) {
                    mProgressDialog.setCancelable(false);
                }
                if (!ListenerUtil.mutListener.listen(11641)) {
                    mProgressDialog.setCanceledOnTouchOutside(false);
                }
                if (!ListenerUtil.mutListener.listen(11646)) {
                    mProgressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!ListenerUtil.mutListener.listen(11643)) {
                                if (mRequestTask != null) {
                                    if (!ListenerUtil.mutListener.listen(11642)) {
                                        mRequestTask.cancel(true);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11644)) {
                                mIsProgressDialogShowing = false;
                            }
                            if (!ListenerUtil.mutListener.listen(11645)) {
                                mProgressDialog.dismiss();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(11647)) {
                    mProgressDialog.show();
                }
            } else if ((ListenerUtil.mutListener.listen(11633) ? ((ListenerUtil.mutListener.listen(11632) ? (mProgressDialog != null || !visible) : (mProgressDialog != null && !visible)) || mProgressDialog.isShowing()) : ((ListenerUtil.mutListener.listen(11632) ? (mProgressDialog != null || !visible) : (mProgressDialog != null && !visible)) && mProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(11634)) {
                    mIsProgressDialogShowing = false;
                }
                if (!ListenerUtil.mutListener.listen(11635)) {
                    mProgressDialog.dismiss();
                }
            }
        }
    }

    private boolean isActivityAttached() {
        return getActivity() != null;
    }

    public void setOpen311(Open311 open311) {
        if (!ListenerUtil.mutListener.listen(11649)) {
            mOpen311 = open311;
        }
    }

    public void setService(Service service) {
        if (!ListenerUtil.mutListener.listen(11650)) {
            mService = service;
        }
    }

    public void setArrivalInfo(ObaArrivalInfo arrivalInfo) {
        if (!ListenerUtil.mutListener.listen(11651)) {
            mArrivalInfo = arrivalInfo;
        }
    }

    public void setAgencyName(String agencyName) {
        if (!ListenerUtil.mutListener.listen(11652)) {
            this.mAgencyName = agencyName;
        }
    }

    public void setBlockId(String blockId) {
        if (!ListenerUtil.mutListener.listen(11653)) {
            mBlockId = blockId;
        }
    }
}
