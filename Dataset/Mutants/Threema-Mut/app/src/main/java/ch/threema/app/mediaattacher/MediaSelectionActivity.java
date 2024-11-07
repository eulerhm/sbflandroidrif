/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.mediaattacher;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import ch.threema.app.R;
import ch.threema.app.activities.SendMediaActivity;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LocaleUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaSelectionActivity extends MediaSelectionBaseActivity {

    private ControlPanelButton selectButton, cancelButton;

    private Button selectCounterButton;

    @Override
    protected void initActivity(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(29773)) {
            super.initActivity(null);
        }
        if (!ListenerUtil.mutListener.listen(29774)) {
            setControlPanelLayout();
        }
        if (!ListenerUtil.mutListener.listen(29775)) {
            setupControlPanelListeners();
        }
        if (!ListenerUtil.mutListener.listen(29776)) {
            // always open bottom sheet in expanded state right away
            expandBottomSheet();
        }
        if (!ListenerUtil.mutListener.listen(29777)) {
            setInitialMediaGrid();
        }
        if (!ListenerUtil.mutListener.listen(29778)) {
            handleSavedInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void setInitialMediaGrid() {
        if (!ListenerUtil.mutListener.listen(29779)) {
            super.setInitialMediaGrid();
        }
        if (!ListenerUtil.mutListener.listen(29780)) {
            // hide media items dependent views until we have data loaded and set to grid
            dateView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(29781)) {
            controlPanel.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(29782)) {
            controlPanel.animate().translationY(getResources().getDimensionPixelSize(R.dimen.control_panel_height));
        }
        if (!ListenerUtil.mutListener.listen(29787)) {
            mediaAttachViewModel.getCurrentMedia().observe(this, new Observer<List<MediaAttachItem>>() {

                @Override
                public void onChanged(List<MediaAttachItem> mediaAttachItems) {
                    if (!ListenerUtil.mutListener.listen(29786)) {
                        if (mediaAttachItems.size() != 0) {
                            if (!ListenerUtil.mutListener.listen(29783)) {
                                dateView.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(29784)) {
                                controlPanel.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(29785)) {
                                mediaAttachViewModel.getCurrentMedia().removeObserver(this);
                            }
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onItemChecked(int count) {
        if (!ListenerUtil.mutListener.listen(29802)) {
            if ((ListenerUtil.mutListener.listen(29792) ? (count >= 0) : (ListenerUtil.mutListener.listen(29791) ? (count <= 0) : (ListenerUtil.mutListener.listen(29790) ? (count < 0) : (ListenerUtil.mutListener.listen(29789) ? (count != 0) : (ListenerUtil.mutListener.listen(29788) ? (count == 0) : (count > 0))))))) {
                if (!ListenerUtil.mutListener.listen(29798)) {
                    selectCounterButton.setText(String.format(LocaleUtil.getCurrentLocale(this), "%d", count));
                }
                if (!ListenerUtil.mutListener.listen(29799)) {
                    selectCounterButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(29800)) {
                    controlPanel.animate().translationY(0);
                }
                if (!ListenerUtil.mutListener.listen(29801)) {
                    controlPanel.postDelayed(() -> bottomSheetLayout.setPadding(0, 0, 0, 0), 300);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(29793)) {
                    selectCounterButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(29794)) {
                    controlPanel.animate().translationY(controlPanel.getHeight());
                }
                ValueAnimator animator = ValueAnimator.ofInt(bottomSheetLayout.getPaddingBottom(), 0);
                if (!ListenerUtil.mutListener.listen(29795)) {
                    animator.addUpdateListener(valueAnimator -> bottomSheetLayout.setPadding(0, 0, 0, (Integer) valueAnimator.getAnimatedValue()));
                }
                if (!ListenerUtil.mutListener.listen(29796)) {
                    animator.setDuration(300);
                }
                if (!ListenerUtil.mutListener.listen(29797)) {
                    animator.start();
                }
            }
        }
    }

    public void setControlPanelLayout() {
        ViewStub stub = findViewById(R.id.stub);
        if (!ListenerUtil.mutListener.listen(29803)) {
            stub.setLayoutResource(R.layout.media_selection_control_panel);
        }
        if (!ListenerUtil.mutListener.listen(29804)) {
            stub.inflate();
        }
        if (!ListenerUtil.mutListener.listen(29805)) {
            this.controlPanel = findViewById(R.id.control_panel);
        }
        if (!ListenerUtil.mutListener.listen(29806)) {
            controlPanel.setTranslationY(controlPanel.getHeight());
        }
        ConstraintLayout selectPanel = findViewById(R.id.select_panel);
        if (!ListenerUtil.mutListener.listen(29807)) {
            this.cancelButton = selectPanel.findViewById(R.id.cancel);
        }
        if (!ListenerUtil.mutListener.listen(29808)) {
            this.selectButton = selectPanel.findViewById(R.id.select);
        }
        if (!ListenerUtil.mutListener.listen(29809)) {
            this.selectCounterButton = selectPanel.findViewById(R.id.select_counter_button);
        }
    }

    public void setupControlPanelListeners() {
        if (!ListenerUtil.mutListener.listen(29810)) {
            this.selectCounterButton.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(29812)) {
            this.cancelButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(29811)) {
                        MediaSelectionActivity.super.onClick(v);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(29815)) {
            this.selectButton.setOnClickListener(new DebouncedOnClickListener(1000) {

                @Override
                public void onDebouncedClick(View v) {
                    if (!ListenerUtil.mutListener.listen(29813)) {
                        v.setAlpha(0.3f);
                    }
                    if (!ListenerUtil.mutListener.listen(29814)) {
                        selectItemsAndClose(mediaAttachViewModel.getSelectedMediaUris());
                    }
                }
            });
        }
    }

    private void selectItemsAndClose(ArrayList<Uri> uris) {
        ArrayList<MediaItem> mediaItems = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(29818)) {
            {
                long _loopCounter198 = 0;
                for (Uri uri : uris) {
                    ListenerUtil.loopListener.listen("_loopCounter198", ++_loopCounter198);
                    MediaItem mediaItem = new MediaItem(uri, FileUtil.getMimeTypeFromUri(MediaSelectionActivity.this, uri), null);
                    if (!ListenerUtil.mutListener.listen(29816)) {
                        mediaItem.setFilename(FileUtil.getFilenameFromUri(getContentResolver(), mediaItem));
                    }
                    if (!ListenerUtil.mutListener.listen(29817)) {
                        mediaItems.add(mediaItem);
                    }
                }
            }
        }
        Intent resultIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(29819)) {
            resultIntent.putExtra(SendMediaActivity.EXTRA_MEDIA_ITEMS, mediaItems);
        }
        if (!ListenerUtil.mutListener.listen(29821)) {
            if (mediaAttachViewModel.getLastQuery() != null) {
                if (!ListenerUtil.mutListener.listen(29820)) {
                    resultIntent = IntentDataUtil.addLastMediaFilterToIntent(resultIntent, mediaAttachViewModel.getLastQuery(), mediaAttachViewModel.getLastQueryType());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29822)) {
            setResult(RESULT_OK, resultIntent);
        }
        if (!ListenerUtil.mutListener.listen(29823)) {
            finish();
        }
    }

    /**
     *  Check if the media attacher's selectable media grid can be shown
     *  @return true if option has been enabled by user and permissions are available
     */
    @Override
    protected boolean shouldShowMediaGrid() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(29824)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(29837)) {
            if ((ListenerUtil.mutListener.listen(29830) ? ((ListenerUtil.mutListener.listen(29829) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(29828) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(29827) ? (grantResults.length > 0) : (ListenerUtil.mutListener.listen(29826) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(29825) ? (grantResults.length != 0) : (grantResults.length == 0)))))) && grantResults[0] != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(29829) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(29828) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(29827) ? (grantResults.length > 0) : (ListenerUtil.mutListener.listen(29826) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(29825) ? (grantResults.length != 0) : (grantResults.length == 0)))))) || grantResults[0] != PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(29836)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_ATTACH_FILE:
                            if (!ListenerUtil.mutListener.listen(29831)) {
                                updateUI(BottomSheetBehavior.STATE_COLLAPSED);
                            }
                            if (!ListenerUtil.mutListener.listen(29832)) {
                                toolbar.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(29833)) {
                                selectButton.setAlpha(0.3f);
                            }
                            if (!ListenerUtil.mutListener.listen(29834)) {
                                selectButton.setOnClickListener(v -> {
                                    if (!ActivityCompat.shouldShowRequestPermissionRationale(MediaSelectionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                        showPermissionRationale(R.string.permission_storage_required);
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(29835)) {
                                cancelButton.setOnClickListener(v -> finish());
                            }
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(29838)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(29841)) {
            if (resultCode == Activity.RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(29840)) {
                    switch(requestCode) {
                        case REQUEST_CODE_ATTACH_FROM_GALLERY:
                            if (!ListenerUtil.mutListener.listen(29839)) {
                                selectItemsAndClose(FileUtil.getUrisFromResult(intent, getContentResolver()));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
