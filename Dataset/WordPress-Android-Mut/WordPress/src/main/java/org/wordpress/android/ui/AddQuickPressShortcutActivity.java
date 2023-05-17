package org.wordpress.android.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import org.apache.commons.text.StringEscapeUtils;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.tools.FluxCImageLoader;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AddQuickPressShortcutActivity extends LocaleAwareActivity {

    public String[] blogNames;

    public int[] siteIds;

    public String[] blogUrls;

    public String[] blavatars;

    public List<String> accountNames = new ArrayList<>();

    @Inject
    SiteStore mSiteStore;

    @Inject
    FluxCImageLoader mImageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(25841)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(25842)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(25843)) {
            setContentView(R.layout.quickpress_widget_configure_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(25844)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(25847)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(25845)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(25846)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25848)) {
            displayAccounts();
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(25850)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(25849)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayAccounts() {
        List<SiteModel> sites = mSiteStore.getVisibleSites();
        ListView listView = (ListView) findViewById(android.R.id.list);
        if (!ListenerUtil.mutListener.listen(25851)) {
            listView.setVerticalFadingEdgeEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(25852)) {
            listView.setVerticalScrollBarEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(25879)) {
            if ((ListenerUtil.mutListener.listen(25857) ? (sites.size() >= 0) : (ListenerUtil.mutListener.listen(25856) ? (sites.size() <= 0) : (ListenerUtil.mutListener.listen(25855) ? (sites.size() < 0) : (ListenerUtil.mutListener.listen(25854) ? (sites.size() != 0) : (ListenerUtil.mutListener.listen(25853) ? (sites.size() == 0) : (sites.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(25859)) {
                    blogNames = new String[sites.size()];
                }
                if (!ListenerUtil.mutListener.listen(25860)) {
                    siteIds = new int[sites.size()];
                }
                if (!ListenerUtil.mutListener.listen(25861)) {
                    blogUrls = new String[sites.size()];
                }
                if (!ListenerUtil.mutListener.listen(25862)) {
                    blavatars = new String[sites.size()];
                }
                if (!ListenerUtil.mutListener.listen(25873)) {
                    {
                        long _loopCounter395 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(25872) ? (i >= sites.size()) : (ListenerUtil.mutListener.listen(25871) ? (i <= sites.size()) : (ListenerUtil.mutListener.listen(25870) ? (i > sites.size()) : (ListenerUtil.mutListener.listen(25869) ? (i != sites.size()) : (ListenerUtil.mutListener.listen(25868) ? (i == sites.size()) : (i < sites.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter395", ++_loopCounter395);
                            SiteModel site = sites.get(i);
                            if (!ListenerUtil.mutListener.listen(25863)) {
                                blogNames[i] = SiteUtils.getSiteNameOrHomeURL(site);
                            }
                            if (!ListenerUtil.mutListener.listen(25864)) {
                                blogUrls[i] = site.getUrl();
                            }
                            if (!ListenerUtil.mutListener.listen(25865)) {
                                siteIds[i] = site.getId();
                            }
                            if (!ListenerUtil.mutListener.listen(25866)) {
                                blavatars[i] = SiteUtils.getSiteIconUrl(site, 60);
                            }
                            if (!ListenerUtil.mutListener.listen(25867)) {
                                accountNames.add(i, blogNames[i]);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25874)) {
                    listView.setAdapter(new HomeListAdapter());
                }
                if (!ListenerUtil.mutListener.listen(25876)) {
                    listView.setOnItemClickListener(new OnItemClickListener() {

                        public void onItemClick(AdapterView<?> arg0, View row, int position, long id) {
                            if (!ListenerUtil.mutListener.listen(25875)) {
                                AddQuickPressShortcutActivity.this.buildDialog(position);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(25878)) {
                    if (sites.size() == 1) {
                        if (!ListenerUtil.mutListener.listen(25877)) {
                            AddQuickPressShortcutActivity.this.buildDialog(0);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25858)) {
                    // no account, load new account view
                    ActivityLauncher.showSignInForResult(AddQuickPressShortcutActivity.this);
                }
            }
        }
    }

    private void buildDialog(final int position) {
        AlertDialog.Builder dialogBuilder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(25880)) {
            dialogBuilder.setTitle(R.string.quickpress_add_alert_title);
        }
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        // noinspection InflateParams
        View dialogView = layoutInflater.inflate(R.layout.quick_press_input_dialog, null);
        TextInputEditText quickPressShortcutName = dialogView.findViewById(R.id.quick_press_input_dialog_edit_text);
        if (!ListenerUtil.mutListener.listen(25881)) {
            quickPressShortcutName.setText(getString(R.string.quickpress_shortcut_with_account_param, StringEscapeUtils.unescapeHtml4(accountNames.get(position))));
        }
        if (!ListenerUtil.mutListener.listen(25882)) {
            dialogBuilder.setView(dialogView);
        }
        if (!ListenerUtil.mutListener.listen(25893)) {
            dialogBuilder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(25892)) {
                        if (TextUtils.isEmpty(quickPressShortcutName.getText())) {
                            if (!ListenerUtil.mutListener.listen(25891)) {
                                ToastUtils.showToast(AddQuickPressShortcutActivity.this, R.string.quickpress_add_error, ToastUtils.Duration.LONG);
                            }
                        } else {
                            Intent shortcutIntent = new Intent(getApplicationContext(), EditPostActivity.class);
                            if (!ListenerUtil.mutListener.listen(25883)) {
                                shortcutIntent.setAction(Intent.ACTION_MAIN);
                            }
                            if (!ListenerUtil.mutListener.listen(25884)) {
                                shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            if (!ListenerUtil.mutListener.listen(25885)) {
                                shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            }
                            if (!ListenerUtil.mutListener.listen(25886)) {
                                shortcutIntent.putExtra(EditPostActivity.EXTRA_QUICKPRESS_BLOG_ID, siteIds[position]);
                            }
                            if (!ListenerUtil.mutListener.listen(25887)) {
                                shortcutIntent.putExtra(EditPostActivity.EXTRA_IS_QUICKPRESS, true);
                            }
                            String shortcutName = quickPressShortcutName.getText().toString();
                            if (!ListenerUtil.mutListener.listen(25888)) {
                                WordPress.wpDB.addQuickPressShortcut(siteIds[position], shortcutName);
                            }
                            ShortcutInfoCompat pinShortcutInfo = new ShortcutInfoCompat.Builder(getApplicationContext(), shortcutName).setIcon(IconCompat.createWithResource(getApplicationContext(), R.mipmap.app_icon)).setShortLabel(shortcutName).setIntent(shortcutIntent).build();
                            if (!ListenerUtil.mutListener.listen(25889)) {
                                ShortcutManagerCompat.requestPinShortcut(getApplicationContext(), pinShortcutInfo, null);
                            }
                            if (!ListenerUtil.mutListener.listen(25890)) {
                                AddQuickPressShortcutActivity.this.finish();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(25894)) {
            dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                // just let the dialog close
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(25895)) {
            dialogBuilder.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(25896)) {
            dialogBuilder.create().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(25897)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(25907)) {
            switch(requestCode) {
                case RequestCodes.ADD_ACCOUNT:
                    if (!ListenerUtil.mutListener.listen(25905)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(25904)) {
                                if ((ListenerUtil.mutListener.listen(25902) ? (mSiteStore.getVisibleSitesCount() >= 0) : (ListenerUtil.mutListener.listen(25901) ? (mSiteStore.getVisibleSitesCount() <= 0) : (ListenerUtil.mutListener.listen(25900) ? (mSiteStore.getVisibleSitesCount() < 0) : (ListenerUtil.mutListener.listen(25899) ? (mSiteStore.getVisibleSitesCount() != 0) : (ListenerUtil.mutListener.listen(25898) ? (mSiteStore.getVisibleSitesCount() == 0) : (mSiteStore.getVisibleSitesCount() > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(25903)) {
                                        displayAccounts();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(25906)) {
                        finish();
                    }
                    break;
            }
        }
    }

    protected class HomeListAdapter extends BaseAdapter {

        public HomeListAdapter() {
        }

        public int getCount() {
            return mSiteStore.getVisibleSitesCount();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            RelativeLayout view = (RelativeLayout) convertView;
            if (!ListenerUtil.mutListener.listen(25909)) {
                if (view == null) {
                    LayoutInflater inflater = getLayoutInflater();
                    if (!ListenerUtil.mutListener.listen(25908)) {
                        view = (RelativeLayout) inflater.inflate(R.layout.quick_press_widget_configure_list_row, parent, false);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(25910)) {
                view.setId(siteIds[position]);
            }
            TextView blogName = (TextView) view.findViewById(R.id.blogName);
            TextView blogUrl = (TextView) view.findViewById(R.id.blogUrl);
            NetworkImageView blavatar = (NetworkImageView) view.findViewById(R.id.blavatar);
            if (!ListenerUtil.mutListener.listen(25911)) {
                blavatar.setDefaultImageResId(R.drawable.ic_placeholder_blavatar_grey_lighten_20_40dp);
            }
            if (!ListenerUtil.mutListener.listen(25912)) {
                blogName.setText(StringEscapeUtils.unescapeHtml4(blogNames[position]));
            }
            if (!ListenerUtil.mutListener.listen(25913)) {
                blogUrl.setText(StringEscapeUtils.unescapeHtml4(blogUrls[position]));
            }
            if (!ListenerUtil.mutListener.listen(25914)) {
                blavatar.setErrorImageResId(R.drawable.bg_rectangle_placeholder_globe_32dp);
            }
            if (!ListenerUtil.mutListener.listen(25915)) {
                blavatar.setImageUrl(blavatars[position], mImageLoader);
            }
            return view;
        }
    }
}
