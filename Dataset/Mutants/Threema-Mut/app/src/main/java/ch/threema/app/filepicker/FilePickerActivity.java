/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.filepicker;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import ch.threema.app.R;
import ch.threema.app.activities.ThreemaToolbarActivity;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.StorageUtil;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FilePickerActivity extends ThreemaToolbarActivity implements ListView.OnItemClickListener {

    private static final Logger logger = LoggerFactory.getLogger(FilePickerActivity.class);

    private static final int PERMISSION_STORAGE = 1;

    public static final String INTENT_DATA_DEFAULT_PATH = "defpath";

    public static final String INTENT_DATA_SELECT_DIRECTORY = "directory";

    public static final String EXTRA_DIRECTORY = "dir";

    private String currentFolder;

    private FilePickerAdapter fileArrayListAdapter;

    private FileFilter fileFilter;

    private File fileSelected;

    private ListView listView;

    private ArrayList<String> extensions;

    private ArrayList<String> rootPaths = new ArrayList<>(2);

    private ActionBar actionBar;

    private DrawerLayout drawerLayout;

    private Comparator<FileInfo> comparator;

    private ExtendedFloatingActionButton floatingActionButton;

    private int currentRoot = 0;

    private boolean isDirectoriesOnly = false, isExternal = false;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_filepicker;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23258)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        boolean result = super.initActivity(savedInstanceState);
        if (!ListenerUtil.mutListener.listen(23260)) {
            if (getConnectionIndicator() != null) {
                if (!ListenerUtil.mutListener.listen(23259)) {
                    getConnectionIndicator().setVisibility(View.INVISIBLE);
                }
            }
        }
        String defaultPath = null;
        if (!ListenerUtil.mutListener.listen(23261)) {
            actionBar = getSupportActionBar();
        }
        if (!ListenerUtil.mutListener.listen(23262)) {
            floatingActionButton = findViewById(R.id.floating);
        }
        Bundle extras = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(23280)) {
            if (extras != null) {
                if (!ListenerUtil.mutListener.listen(23267)) {
                    if (extras.getStringArrayList(Constants.KEY_FILTER_FILES_EXTENSIONS) != null) {
                        if (!ListenerUtil.mutListener.listen(23263)) {
                            extensions = extras.getStringArrayList(Constants.KEY_FILTER_FILES_EXTENSIONS);
                        }
                        if (!ListenerUtil.mutListener.listen(23266)) {
                            fileFilter = new FileFilter() {

                                @Override
                                public boolean accept(File pathname) {
                                    return ((ListenerUtil.mutListener.listen(23265) ? ((pathname.isDirectory()) && ((ListenerUtil.mutListener.listen(23264) ? (pathname.getName().contains(".") || extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf(".")))) : (pathname.getName().contains(".") && extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf("."))))))) : ((pathname.isDirectory()) || ((ListenerUtil.mutListener.listen(23264) ? (pathname.getName().contains(".") || extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf(".")))) : (pathname.getName().contains(".") && extensions.contains(pathname.getName().substring(pathname.getName().lastIndexOf(".")))))))));
                                }
                            };
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23268)) {
                    defaultPath = extras.getString(INTENT_DATA_DEFAULT_PATH, null);
                }
                if (!ListenerUtil.mutListener.listen(23271)) {
                    if ((ListenerUtil.mutListener.listen(23269) ? (defaultPath != null || !(new File(defaultPath)).exists()) : (defaultPath != null && !(new File(defaultPath)).exists()))) {
                        if (!ListenerUtil.mutListener.listen(23270)) {
                            defaultPath = null;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23279)) {
                    if (extras.getBoolean(INTENT_DATA_SELECT_DIRECTORY, false)) {
                        if (!ListenerUtil.mutListener.listen(23272)) {
                            floatingActionButton.setText(R.string.select_directory_for_backup);
                        }
                        if (!ListenerUtil.mutListener.listen(23273)) {
                            floatingActionButton.setIconResource(R.drawable.ic_check);
                        }
                        if (!ListenerUtil.mutListener.listen(23277)) {
                            floatingActionButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    if (!ListenerUtil.mutListener.listen(23274)) {
                                        intent.putExtra(EXTRA_DIRECTORY, currentFolder);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23275)) {
                                        setResult(Activity.RESULT_OK, intent);
                                    }
                                    if (!ListenerUtil.mutListener.listen(23276)) {
                                        finish();
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(23278)) {
                            isDirectoriesOnly = true;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23281)) {
            listView = findViewById(android.R.id.list);
        }
        if (!ListenerUtil.mutListener.listen(23284)) {
            if (listView == null) {
                if (!ListenerUtil.mutListener.listen(23282)) {
                    Toast.makeText(this, "Unable to inflate layout", Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(23283)) {
                    finish();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(23285)) {
            listView.setOnItemClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(23288)) {
            if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(23287)) {
                    listView.setDivider(getResources().getDrawable(R.drawable.divider_listview_dark));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23286)) {
                    listView.setDivider(getResources().getDrawable(R.drawable.divider_listview));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23299)) {
            if (isDirectoriesOnly) {
                if (!ListenerUtil.mutListener.listen(23298)) {
                    listView.setOnScrollListener(new AbsListView.OnScrollListener() {

                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            if (!ListenerUtil.mutListener.listen(23297)) {
                                if (floatingActionButton != null) {
                                    if (!ListenerUtil.mutListener.listen(23296)) {
                                        if ((ListenerUtil.mutListener.listen(23293) ? (firstVisibleItem >= 0) : (ListenerUtil.mutListener.listen(23292) ? (firstVisibleItem <= 0) : (ListenerUtil.mutListener.listen(23291) ? (firstVisibleItem > 0) : (ListenerUtil.mutListener.listen(23290) ? (firstVisibleItem < 0) : (ListenerUtil.mutListener.listen(23289) ? (firstVisibleItem != 0) : (firstVisibleItem == 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(23295)) {
                                                floatingActionButton.extend();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(23294)) {
                                                floatingActionButton.shrink();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23300)) {
            listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.list_divider_height));
        }
        if (!ListenerUtil.mutListener.listen(23308)) {
            if ((ListenerUtil.mutListener.listen(23305) ? (getRootPaths() >= 0) : (ListenerUtil.mutListener.listen(23304) ? (getRootPaths() <= 0) : (ListenerUtil.mutListener.listen(23303) ? (getRootPaths() > 0) : (ListenerUtil.mutListener.listen(23302) ? (getRootPaths() < 0) : (ListenerUtil.mutListener.listen(23301) ? (getRootPaths() != 0) : (getRootPaths() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(23306)) {
                    Toast.makeText(this, "No storage found", Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(23307)) {
                    finish();
                }
                return false;
            }
        }
        ;
        if (!ListenerUtil.mutListener.listen(23309)) {
            drawerLayout = findViewById(R.id.drawer_layout);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(), R.string.open_navdrawer, R.string.close);
        if (!ListenerUtil.mutListener.listen(23310)) {
            toggle.setDrawerIndicatorEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(23311)) {
            toggle.setDrawerSlideAnimationEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(23312)) {
            toggle.syncState();
        }
        if (!ListenerUtil.mutListener.listen(23313)) {
            drawerLayout.addDrawerListener(toggle);
        }
        if (!ListenerUtil.mutListener.listen(23334)) {
            if (defaultPath != null) {
                if (!ListenerUtil.mutListener.listen(23317)) {
                    currentRoot = 0;
                }
                if (!ListenerUtil.mutListener.listen(23318)) {
                    currentFolder = defaultPath;
                }
                if (!ListenerUtil.mutListener.listen(23327)) {
                    if (currentFolder != null) {
                        if (!ListenerUtil.mutListener.listen(23326)) {
                            {
                                long _loopCounter155 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(23325) ? (i >= rootPaths.size()) : (ListenerUtil.mutListener.listen(23324) ? (i <= rootPaths.size()) : (ListenerUtil.mutListener.listen(23323) ? (i > rootPaths.size()) : (ListenerUtil.mutListener.listen(23322) ? (i != rootPaths.size()) : (ListenerUtil.mutListener.listen(23321) ? (i == rootPaths.size()) : (i < rootPaths.size())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter155", ++_loopCounter155);
                                    if (!ListenerUtil.mutListener.listen(23320)) {
                                        if (currentFolder.startsWith(rootPaths.get(i))) {
                                            if (!ListenerUtil.mutListener.listen(23319)) {
                                                currentRoot = i;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23333)) {
                    // sort by date (most recent first)
                    comparator = new Comparator<FileInfo>() {

                        @Override
                        public int compare(FileInfo f1, FileInfo f2) {
                            return f1.getLastModified() == f2.getLastModified() ? 0 : (ListenerUtil.mutListener.listen(23332) ? (f1.getLastModified() >= f2.getLastModified()) : (ListenerUtil.mutListener.listen(23331) ? (f1.getLastModified() <= f2.getLastModified()) : (ListenerUtil.mutListener.listen(23330) ? (f1.getLastModified() > f2.getLastModified()) : (ListenerUtil.mutListener.listen(23329) ? (f1.getLastModified() != f2.getLastModified()) : (ListenerUtil.mutListener.listen(23328) ? (f1.getLastModified() == f2.getLastModified()) : (f1.getLastModified() < f2.getLastModified())))))) ? 1 : -1;
                        }
                    };
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23314)) {
                    currentFolder = rootPaths.get(0);
                }
                if (!ListenerUtil.mutListener.listen(23315)) {
                    currentRoot = 0;
                }
                if (!ListenerUtil.mutListener.listen(23316)) {
                    // sort by filename
                    comparator = new Comparator<FileInfo>() {

                        @Override
                        public int compare(FileInfo f1, FileInfo f2) {
                            return f1.getName().compareTo(f2.getName());
                        }
                    };
                }
            }
        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (!ListenerUtil.mutListener.listen(23336)) {
            if (navigationView != null) {
                if (!ListenerUtil.mutListener.listen(23335)) {
                    setupDrawerContent(navigationView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23337)) {
            setResult(RESULT_CANCELED);
        }
        if (!ListenerUtil.mutListener.listen(23339)) {
            if (ConfigUtils.requestStoragePermissions(this, null, PERMISSION_STORAGE)) {
                if (!ListenerUtil.mutListener.listen(23338)) {
                    scanFiles(currentFolder);
                }
            }
        }
        return result;
    }

    private int getRootPaths() {
        if (!ListenerUtil.mutListener.listen(23340)) {
            // Internal storage - should always be around
            rootPaths.addAll(Arrays.asList(StorageUtil.getStorageDirectories(this)));
        }
        return rootPaths.size();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(23343)) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!ListenerUtil.mutListener.listen(23341)) {
                    setResult(Activity.RESULT_CANCELED);
                }
                if (!ListenerUtil.mutListener.listen(23342)) {
                    finish();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void scanFiles(String path) {
        File f = new File(path);
        File[] folders;
        if (fileFilter != null)
            folders = f.listFiles(fileFilter);
        else
            folders = f.listFiles();
        if (!ListenerUtil.mutListener.listen(23346)) {
            if (f.getName().equalsIgnoreCase(Environment.getExternalStorageDirectory().getName())) {
                if (!ListenerUtil.mutListener.listen(23345)) {
                    actionBar.setTitle(R.string.internal_storage);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23344)) {
                    actionBar.setTitle(f.getName());
                }
            }
        }
        List<FileInfo> dirs = new ArrayList<FileInfo>();
        List<FileInfo> files = new ArrayList<FileInfo>();
        try {
            if (!ListenerUtil.mutListener.listen(23353)) {
                {
                    long _loopCounter156 = 0;
                    for (File file : folders) {
                        ListenerUtil.loopListener.listen("_loopCounter156", ++_loopCounter156);
                        if (!ListenerUtil.mutListener.listen(23352)) {
                            if ((ListenerUtil.mutListener.listen(23348) ? (file.isDirectory() || !file.isHidden()) : (file.isDirectory() && !file.isHidden()))) {
                                if (!ListenerUtil.mutListener.listen(23351)) {
                                    dirs.add(new FileInfo(file.getName(), Constants.FOLDER, file.getAbsolutePath(), file.lastModified(), true, false));
                                }
                            } else // else if (!isDirectoriesOnly) {
                            {
                                if (!ListenerUtil.mutListener.listen(23350)) {
                                    if (!file.isHidden())
                                        if (!ListenerUtil.mutListener.listen(23349)) {
                                            files.add(new FileInfo(file.getName(), Formatter.formatFileSize(this, file.length()), file.getAbsolutePath(), file.lastModified(), false, false));
                                        }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(23347)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(23354)) {
            Collections.sort(dirs);
        }
        if (!ListenerUtil.mutListener.listen(23355)) {
            Collections.sort(files, comparator);
        }
        if (!ListenerUtil.mutListener.listen(23356)) {
            dirs.addAll(files);
        }
        String canonicalFilePath = null;
        try {
            if (!ListenerUtil.mutListener.listen(23358)) {
                canonicalFilePath = f.getCanonicalPath();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(23357)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(23362)) {
            if ((ListenerUtil.mutListener.listen(23359) ? (!TestUtil.empty(canonicalFilePath) || !isTop(canonicalFilePath)) : (!TestUtil.empty(canonicalFilePath) && !isTop(canonicalFilePath)))) {
                if (!ListenerUtil.mutListener.listen(23361)) {
                    if (f.getParentFile() != null)
                        if (!ListenerUtil.mutListener.listen(23360)) {
                            dirs.add(0, new FileInfo("..", Constants.PARENT_FOLDER, f.getParent(), 0, false, true));
                        }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23363)) {
            fileArrayListAdapter = new FilePickerAdapter(FilePickerActivity.this, R.layout.item_filepicker, dirs, isDirectoriesOnly);
        }
        if (!ListenerUtil.mutListener.listen(23364)) {
            listView.setAdapter(fileArrayListAdapter);
        }
        if (!ListenerUtil.mutListener.listen(23366)) {
            if (isDirectoriesOnly) {
                if (!ListenerUtil.mutListener.listen(23365)) {
                    floatingActionButton.setVisibility(f.canWrite() ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    private boolean isTop(String path) {
        if (!ListenerUtil.mutListener.listen(23369)) {
            {
                long _loopCounter157 = 0;
                for (String rootPath : rootPaths) {
                    ListenerUtil.loopListener.listen("_loopCounter157", ++_loopCounter157);
                    File file = new File(rootPath);
                    try {
                        if (!ListenerUtil.mutListener.listen(23368)) {
                            if (file.getCanonicalPath().equalsIgnoreCase(path)) {
                                return true;
                            }
                        }
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(23367)) {
                            logger.error("Exception", e);
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FileInfo fileDescriptor = fileArrayListAdapter.getItem(position);
        if (!ListenerUtil.mutListener.listen(23377)) {
            if ((ListenerUtil.mutListener.listen(23370) ? (fileDescriptor.isFolder() && fileDescriptor.isParent()) : (fileDescriptor.isFolder() || fileDescriptor.isParent()))) {
                if (!ListenerUtil.mutListener.listen(23375)) {
                    currentFolder = fileDescriptor.getPath();
                }
                if (!ListenerUtil.mutListener.listen(23376)) {
                    scanFiles(currentFolder);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23371)) {
                    fileSelected = new File(fileDescriptor.getPath());
                }
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(23372)) {
                    intent.setData(Uri.fromFile(fileSelected));
                }
                if (!ListenerUtil.mutListener.listen(23373)) {
                    setResult(Activity.RESULT_OK, intent);
                }
                if (!ListenerUtil.mutListener.listen(23374)) {
                    finish();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(23380)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(23379)) {
                        if (drawerLayout != null) {
                            if (!ListenerUtil.mutListener.listen(23378)) {
                                drawerLayout.openDrawer(GravityCompat.START);
                            }
                        }
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        Menu menu = navigationView.getMenu();
        if (!ListenerUtil.mutListener.listen(23408)) {
            if (menu != null) {
                if (!ListenerUtil.mutListener.listen(23399)) {
                    if ((ListenerUtil.mutListener.listen(23385) ? (rootPaths.size() >= 1) : (ListenerUtil.mutListener.listen(23384) ? (rootPaths.size() <= 1) : (ListenerUtil.mutListener.listen(23383) ? (rootPaths.size() < 1) : (ListenerUtil.mutListener.listen(23382) ? (rootPaths.size() != 1) : (ListenerUtil.mutListener.listen(23381) ? (rootPaths.size() == 1) : (rootPaths.size() > 1))))))) {
                        if (!ListenerUtil.mutListener.listen(23398)) {
                            {
                                long _loopCounter158 = 0;
                                for (int i = 1; (ListenerUtil.mutListener.listen(23397) ? (i >= rootPaths.size()) : (ListenerUtil.mutListener.listen(23396) ? (i <= rootPaths.size()) : (ListenerUtil.mutListener.listen(23395) ? (i > rootPaths.size()) : (ListenerUtil.mutListener.listen(23394) ? (i != rootPaths.size()) : (ListenerUtil.mutListener.listen(23393) ? (i == rootPaths.size()) : (i < rootPaths.size())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter158", ++_loopCounter158);
                                    File file = new File(rootPaths.get(i));
                                    MenuItem item = menu.add(R.id.main_group, Menu.NONE, i, file.getName()).setIcon(R.drawable.ic_sd_card_black_24dp);
                                    if (!ListenerUtil.mutListener.listen(23392)) {
                                        if ((ListenerUtil.mutListener.listen(23390) ? (i >= currentRoot) : (ListenerUtil.mutListener.listen(23389) ? (i <= currentRoot) : (ListenerUtil.mutListener.listen(23388) ? (i > currentRoot) : (ListenerUtil.mutListener.listen(23387) ? (i < currentRoot) : (ListenerUtil.mutListener.listen(23386) ? (i != currentRoot) : (i == currentRoot))))))) {
                                            if (!ListenerUtil.mutListener.listen(23391)) {
                                                item.setChecked(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23400)) {
                    menu.setGroupCheckable(R.id.main_group, true, true);
                }
                if (!ListenerUtil.mutListener.listen(23407)) {
                    if ((ListenerUtil.mutListener.listen(23405) ? (currentRoot >= 0) : (ListenerUtil.mutListener.listen(23404) ? (currentRoot <= 0) : (ListenerUtil.mutListener.listen(23403) ? (currentRoot > 0) : (ListenerUtil.mutListener.listen(23402) ? (currentRoot < 0) : (ListenerUtil.mutListener.listen(23401) ? (currentRoot != 0) : (currentRoot == 0))))))) {
                        MenuItem menuItem = menu.findItem(R.id.internal_storage);
                        if (!ListenerUtil.mutListener.listen(23406)) {
                            menuItem.setChecked(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23414)) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    if (!ListenerUtil.mutListener.listen(23409)) {
                        currentFolder = rootPaths.get(menuItem.getOrder());
                    }
                    if (!ListenerUtil.mutListener.listen(23410)) {
                        currentRoot = menuItem.getOrder();
                    }
                    if (!ListenerUtil.mutListener.listen(23411)) {
                        scanFiles(currentFolder);
                    }
                    if (!ListenerUtil.mutListener.listen(23412)) {
                        drawerLayout.closeDrawers();
                    }
                    if (!ListenerUtil.mutListener.listen(23413)) {
                        menuItem.setChecked(true);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(23424)) {
            switch(requestCode) {
                case PERMISSION_STORAGE:
                    if (!ListenerUtil.mutListener.listen(23423)) {
                        /* From the docs: It is possible that the permissions request interaction with the user is
				 * interrupted. In this case you will receive empty permissions and results arrays which
				 * should be treated as a cancellation.
				 */
                        if ((ListenerUtil.mutListener.listen(23420) ? ((ListenerUtil.mutListener.listen(23419) ? (grantResults.length <= 1) : (ListenerUtil.mutListener.listen(23418) ? (grantResults.length > 1) : (ListenerUtil.mutListener.listen(23417) ? (grantResults.length < 1) : (ListenerUtil.mutListener.listen(23416) ? (grantResults.length != 1) : (ListenerUtil.mutListener.listen(23415) ? (grantResults.length == 1) : (grantResults.length >= 1)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(23419) ? (grantResults.length <= 1) : (ListenerUtil.mutListener.listen(23418) ? (grantResults.length > 1) : (ListenerUtil.mutListener.listen(23417) ? (grantResults.length < 1) : (ListenerUtil.mutListener.listen(23416) ? (grantResults.length != 1) : (ListenerUtil.mutListener.listen(23415) ? (grantResults.length == 1) : (grantResults.length >= 1)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(23422)) {
                                scanFiles(currentFolder);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23421)) {
                                finish();
                            }
                        }
                    }
            }
        }
    }
}
