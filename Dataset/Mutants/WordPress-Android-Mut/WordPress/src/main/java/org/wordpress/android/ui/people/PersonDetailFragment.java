package org.wordpress.android.ui.people;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.PeopleTable;
import org.wordpress.android.fluxc.model.RoleModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.Person;
import org.wordpress.android.models.RoleUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PersonDetailFragment extends Fragment {

    private static final String ARG_CURRENT_USER_ID = "current_user_id";

    private static final String ARG_PERSON_ID = "person_id";

    private static final String ARG_LOCAL_TABLE_BLOG_ID = "local_table_blog_id";

    private static final String ARG_PERSON_TYPE = "person_type";

    private long mCurrentUserId;

    private long mPersonId;

    private int mLocalTableBlogId;

    private Person.PersonType mPersonType;

    private List<RoleModel> mUserRoles;

    private ImageView mAvatarImageView;

    private TextView mDisplayNameTextView;

    private TextView mUsernameTextView;

    private LinearLayout mRoleContainer;

    private TextView mRoleTextView;

    private LinearLayout mSubscribedDateContainer;

    private TextView mSubscribedDateTitleView;

    private TextView mSubscribedDateTextView;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ImageManager mImageManager;

    public static PersonDetailFragment newInstance(long currentUserId, long personId, int localTableBlogId, Person.PersonType personType) {
        PersonDetailFragment personDetailFragment = new PersonDetailFragment();
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(10115)) {
            bundle.putLong(ARG_CURRENT_USER_ID, currentUserId);
        }
        if (!ListenerUtil.mutListener.listen(10116)) {
            bundle.putLong(ARG_PERSON_ID, personId);
        }
        if (!ListenerUtil.mutListener.listen(10117)) {
            bundle.putInt(ARG_LOCAL_TABLE_BLOG_ID, localTableBlogId);
        }
        if (!ListenerUtil.mutListener.listen(10118)) {
            bundle.putSerializable(ARG_PERSON_TYPE, personType);
        }
        if (!ListenerUtil.mutListener.listen(10119)) {
            personDetailFragment.setArguments(bundle);
        }
        return personDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10120)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10121)) {
            ((WordPress) getActivity().getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(10130)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(10126)) {
                    mCurrentUserId = getArguments().getLong(ARG_CURRENT_USER_ID);
                }
                if (!ListenerUtil.mutListener.listen(10127)) {
                    mPersonId = getArguments().getLong(ARG_PERSON_ID);
                }
                if (!ListenerUtil.mutListener.listen(10128)) {
                    mLocalTableBlogId = getArguments().getInt(ARG_LOCAL_TABLE_BLOG_ID);
                }
                if (!ListenerUtil.mutListener.listen(10129)) {
                    mPersonType = (Person.PersonType) getArguments().getSerializable(ARG_PERSON_TYPE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10122)) {
                    mCurrentUserId = savedInstanceState.getLong(ARG_CURRENT_USER_ID);
                }
                if (!ListenerUtil.mutListener.listen(10123)) {
                    mPersonId = savedInstanceState.getLong(ARG_PERSON_ID);
                }
                if (!ListenerUtil.mutListener.listen(10124)) {
                    mLocalTableBlogId = savedInstanceState.getInt(ARG_LOCAL_TABLE_BLOG_ID);
                }
                if (!ListenerUtil.mutListener.listen(10125)) {
                    mPersonType = (Person.PersonType) savedInstanceState.getSerializable(ARG_PERSON_TYPE);
                }
            }
        }
        SiteModel siteModel = mSiteStore.getSiteByLocalId(mLocalTableBlogId);
        if (!ListenerUtil.mutListener.listen(10131)) {
            mUserRoles = mSiteStore.getUserRoles(siteModel);
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(10132)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(10133)) {
            outState.putLong(ARG_CURRENT_USER_ID, mCurrentUserId);
        }
        if (!ListenerUtil.mutListener.listen(10134)) {
            outState.putLong(ARG_PERSON_ID, mPersonId);
        }
        if (!ListenerUtil.mutListener.listen(10135)) {
            outState.putInt(ARG_LOCAL_TABLE_BLOG_ID, mLocalTableBlogId);
        }
        if (!ListenerUtil.mutListener.listen(10136)) {
            outState.putSerializable(ARG_PERSON_TYPE, mPersonType);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(10137)) {
            inflater.inflate(R.menu.person_detail, menu);
        }
        if (!ListenerUtil.mutListener.listen(10138)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.person_detail_fragment, container, false);
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(10139)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(10143)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(10140)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(10141)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(10142)) {
                    actionBar.setTitle(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10144)) {
            mAvatarImageView = rootView.findViewById(R.id.person_avatar);
        }
        if (!ListenerUtil.mutListener.listen(10145)) {
            mDisplayNameTextView = rootView.findViewById(R.id.person_display_name);
        }
        if (!ListenerUtil.mutListener.listen(10146)) {
            mUsernameTextView = rootView.findViewById(R.id.person_username);
        }
        if (!ListenerUtil.mutListener.listen(10147)) {
            mRoleContainer = rootView.findViewById(R.id.person_role_container);
        }
        if (!ListenerUtil.mutListener.listen(10148)) {
            mRoleTextView = rootView.findViewById(R.id.person_role);
        }
        if (!ListenerUtil.mutListener.listen(10149)) {
            mSubscribedDateContainer = rootView.findViewById(R.id.subscribed_date_container);
        }
        if (!ListenerUtil.mutListener.listen(10150)) {
            mSubscribedDateTitleView = rootView.findViewById(R.id.subscribed_date_title);
        }
        if (!ListenerUtil.mutListener.listen(10151)) {
            mSubscribedDateTextView = rootView.findViewById(R.id.subscribed_date_text);
        }
        boolean isCurrentUser = (ListenerUtil.mutListener.listen(10156) ? (mCurrentUserId >= mPersonId) : (ListenerUtil.mutListener.listen(10155) ? (mCurrentUserId <= mPersonId) : (ListenerUtil.mutListener.listen(10154) ? (mCurrentUserId > mPersonId) : (ListenerUtil.mutListener.listen(10153) ? (mCurrentUserId < mPersonId) : (ListenerUtil.mutListener.listen(10152) ? (mCurrentUserId != mPersonId) : (mCurrentUserId == mPersonId))))));
        SiteModel site = mSiteStore.getSiteByLocalId(mLocalTableBlogId);
        if (!ListenerUtil.mutListener.listen(10160)) {
            if ((ListenerUtil.mutListener.listen(10158) ? ((ListenerUtil.mutListener.listen(10157) ? (!isCurrentUser || site != null) : (!isCurrentUser && site != null)) || site.getHasCapabilityRemoveUsers()) : ((ListenerUtil.mutListener.listen(10157) ? (!isCurrentUser || site != null) : (!isCurrentUser && site != null)) && site.getHasCapabilityRemoveUsers()))) {
                if (!ListenerUtil.mutListener.listen(10159)) {
                    setHasOptionsMenu(true);
                }
            }
        }
        return rootView;
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(10161)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(10162)) {
            refreshPersonDetails();
        }
    }

    void refreshPersonDetails() {
        if (!ListenerUtil.mutListener.listen(10163)) {
            if (!isAdded()) {
                return;
            }
        }
        Person person = loadPerson();
        if (!ListenerUtil.mutListener.listen(10184)) {
            if (person != null) {
                int avatarSz = getResources().getDimensionPixelSize(R.dimen.people_avatar_sz);
                String avatarUrl = GravatarUtils.fixGravatarUrl(person.getAvatarUrl(), avatarSz);
                if (!ListenerUtil.mutListener.listen(10165)) {
                    mImageManager.loadIntoCircle(mAvatarImageView, ImageType.AVATAR_WITH_BACKGROUND, avatarUrl);
                }
                if (!ListenerUtil.mutListener.listen(10166)) {
                    mDisplayNameTextView.setText(StringEscapeUtils.unescapeHtml4(person.getDisplayName()));
                }
                if (!ListenerUtil.mutListener.listen(10168)) {
                    if (person.getRole() != null) {
                        if (!ListenerUtil.mutListener.listen(10167)) {
                            mRoleTextView.setText(RoleUtils.getDisplayName(person.getRole(), mUserRoles));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10170)) {
                    if (!TextUtils.isEmpty(person.getUsername())) {
                        if (!ListenerUtil.mutListener.listen(10169)) {
                            mUsernameTextView.setText(String.format("@%s", person.getUsername()));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10174)) {
                    if (mPersonType == Person.PersonType.USER) {
                        if (!ListenerUtil.mutListener.listen(10172)) {
                            mRoleContainer.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(10173)) {
                            setupRoleContainerForCapability();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10171)) {
                            mRoleContainer.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10182)) {
                    if ((ListenerUtil.mutListener.listen(10175) ? (mPersonType == Person.PersonType.USER && mPersonType == Person.PersonType.VIEWER) : (mPersonType == Person.PersonType.USER || mPersonType == Person.PersonType.VIEWER))) {
                        if (!ListenerUtil.mutListener.listen(10181)) {
                            mSubscribedDateContainer.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(10176)) {
                            mSubscribedDateContainer.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(10179)) {
                            if (mPersonType == Person.PersonType.FOLLOWER) {
                                if (!ListenerUtil.mutListener.listen(10178)) {
                                    mSubscribedDateTitleView.setText(R.string.title_follower);
                                }
                            } else if (mPersonType == Person.PersonType.EMAIL_FOLLOWER) {
                                if (!ListenerUtil.mutListener.listen(10177)) {
                                    mSubscribedDateTitleView.setText(R.string.title_email_follower);
                                }
                            }
                        }
                        String dateSubscribed = SimpleDateFormat.getDateInstance().format(person.getDateSubscribed());
                        String dateText = getString(R.string.follower_subscribed_since, dateSubscribed);
                        if (!ListenerUtil.mutListener.listen(10180)) {
                            mSubscribedDateTextView.setText(dateText);
                        }
                    }
                }
                // Adds extra padding to display name for email followers to make it vertically centered
                int padding = mPersonType == Person.PersonType.EMAIL_FOLLOWER ? (int) getResources().getDimension(R.dimen.margin_small) : 0;
                if (!ListenerUtil.mutListener.listen(10183)) {
                    changeDisplayNameTopPadding(padding);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10164)) {
                    AppLog.w(AppLog.T.PEOPLE, "Person returned null from DB for personID: " + mPersonId + " & localTableBlogID: " + mLocalTableBlogId);
                }
            }
        }
    }

    void setPersonDetails(long personID, int localTableBlogID) {
        if (!ListenerUtil.mutListener.listen(10185)) {
            mPersonId = personID;
        }
        if (!ListenerUtil.mutListener.listen(10186)) {
            mLocalTableBlogId = localTableBlogID;
        }
        if (!ListenerUtil.mutListener.listen(10187)) {
            refreshPersonDetails();
        }
    }

    // Checks current user's capabilities to decide whether she can change the role or not
    private void setupRoleContainerForCapability() {
        SiteModel site = mSiteStore.getSiteByLocalId(mLocalTableBlogId);
        boolean isCurrentUser = (ListenerUtil.mutListener.listen(10192) ? (mCurrentUserId >= mPersonId) : (ListenerUtil.mutListener.listen(10191) ? (mCurrentUserId <= mPersonId) : (ListenerUtil.mutListener.listen(10190) ? (mCurrentUserId > mPersonId) : (ListenerUtil.mutListener.listen(10189) ? (mCurrentUserId < mPersonId) : (ListenerUtil.mutListener.listen(10188) ? (mCurrentUserId != mPersonId) : (mCurrentUserId == mPersonId))))));
        boolean canChangeRole = (ListenerUtil.mutListener.listen(10194) ? ((ListenerUtil.mutListener.listen(10193) ? ((site != null) || !isCurrentUser) : ((site != null) && !isCurrentUser)) || site.getHasCapabilityPromoteUsers()) : ((ListenerUtil.mutListener.listen(10193) ? ((site != null) || !isCurrentUser) : ((site != null) && !isCurrentUser)) && site.getHasCapabilityPromoteUsers()));
        if (!ListenerUtil.mutListener.listen(10198)) {
            if (canChangeRole) {
                if (!ListenerUtil.mutListener.listen(10197)) {
                    mRoleContainer.setOnClickListener(v -> showRoleChangeDialog());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10195)) {
                    // Remove the selectableItemBackground if the user can't be edited
                    mRoleContainer.setBackground(null);
                }
                if (!ListenerUtil.mutListener.listen(10196)) {
                    // Change transparency to give a visual cue to the user that it's disabled
                    mRoleContainer.setAlpha(0.5f);
                }
            }
        }
    }

    private void showRoleChangeDialog() {
        Person person = loadPerson();
        if (!ListenerUtil.mutListener.listen(10200)) {
            if ((ListenerUtil.mutListener.listen(10199) ? (person == null && person.getRole() == null) : (person == null || person.getRole() == null))) {
                return;
            }
        }
        RoleChangeDialogFragment dialog = RoleChangeDialogFragment.newInstance(person.getPersonID(), mSiteStore.getSiteByLocalId(mLocalTableBlogId), person.getRole());
        if (!ListenerUtil.mutListener.listen(10201)) {
            dialog.show(getFragmentManager(), null);
        }
    }

    // used to optimistically update the role
    void changeRole(String newRole) {
        if (!ListenerUtil.mutListener.listen(10202)) {
            mRoleTextView.setText(RoleUtils.getDisplayName(newRole, mUserRoles));
        }
    }

    private void changeDisplayNameTopPadding(int newPadding) {
        if (!ListenerUtil.mutListener.listen(10203)) {
            if (mDisplayNameTextView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10204)) {
            mDisplayNameTextView.setPadding(0, newPadding, 0, 0);
        }
    }

    Person loadPerson() {
        return PeopleTable.getPerson(mPersonId, mLocalTableBlogId, mPersonType);
    }
}
