/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.lifecycle.LifecycleOwner;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.ui.ImagePopup;
import ch.threema.app.ui.TooltipPopup;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExportIDResultActivity extends ThreemaToolbarActivity implements GenericAlertDialog.DialogClickListener, LifecycleOwner {

    private static final String DIALOG_TAG_QUIT_CONFIRM = "qconf";

    private static final int QRCODE_SMALL_DIMENSION_PIXEL = 200;

    private Bitmap qrcodeBitmap;

    private WebView printWebView;

    private Toolbar toolbar;

    private TooltipPopup tooltipPopup;

    private String identity, backupData;

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2736)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2737)) {
            this.toolbar = findViewById(R.id.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(2738)) {
            setSupportActionBar(this.toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(2740)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(2739)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2741)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(2742)) {
            actionBar.setTitle("");
        }
        if (!ListenerUtil.mutListener.listen(2744)) {
            if (ConfigUtils.getAppTheme(this) != ConfigUtils.THEME_DARK) {
                if (!ListenerUtil.mutListener.listen(2743)) {
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_check);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2745)) {
            this.backupData = this.getIntent().getStringExtra(ThreemaApplication.INTENT_DATA_ID_BACKUP);
        }
        if (!ListenerUtil.mutListener.listen(2746)) {
            this.identity = this.getIntent().getStringExtra(ThreemaApplication.INTENT_DATA_CONTACT);
        }
        if (!ListenerUtil.mutListener.listen(2748)) {
            if (TestUtil.empty(this.backupData)) {
                if (!ListenerUtil.mutListener.listen(2747)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2749)) {
            displayIDBackup();
        }
        if (!ListenerUtil.mutListener.listen(2751)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(2750)) {
                    showTooltip();
                }
            }
        }
    }

    private void displayIDBackup() {
        ScrollView layoutContainer = findViewById(R.id.qr_container_backup);
        if (!ListenerUtil.mutListener.listen(2752)) {
            layoutContainer.setVisibility(View.VISIBLE);
        }
        TextView textView = findViewById(R.id.threemaid);
        if (!ListenerUtil.mutListener.listen(2753)) {
            textView.setText(backupData);
        }
        final ImageView imageView = findViewById(R.id.qrcode_backup);
        if (!ListenerUtil.mutListener.listen(2754)) {
            this.qrcodeBitmap = serviceManager.getQRCodeService().getRawQR(backupData, false);
        }
        final int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, QRCODE_SMALL_DIMENSION_PIXEL, getResources().getDisplayMetrics());
        Bitmap bmpScaled = Bitmap.createScaledBitmap(qrcodeBitmap, px, px, false);
        if (!ListenerUtil.mutListener.listen(2755)) {
            bmpScaled.setDensity(Bitmap.DENSITY_NONE);
        }
        if (!ListenerUtil.mutListener.listen(2756)) {
            imageView.setImageBitmap(bmpScaled);
        }
        if (!ListenerUtil.mutListener.listen(2760)) {
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2759)) {
                        if (qrcodeBitmap != null) {
                            // using a BitmapDrawable disables anti-aliasing in ImageView's scaling
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), qrcodeBitmap);
                            if (!ListenerUtil.mutListener.listen(2757)) {
                                bitmapDrawable.setFilterBitmap(false);
                            }
                            View rootView = findViewById(R.id.main_content);
                            ImagePopup detailPopup = new ImagePopup(ExportIDResultActivity.this, rootView, rootView.getWidth(), rootView.getHeight(), getResources().getDimensionPixelSize(R.dimen.image_popup_screen_border_width));
                            if (!ListenerUtil.mutListener.listen(2758)) {
                                detailPopup.show(v, bitmapDrawable, getString(R.string.backup_share_subject) + " " + identity, true);
                            }
                        }
                    }
                }
            });
        }
    }

    private void showTooltip() {
        if (!ListenerUtil.mutListener.listen(2762)) {
            if (!preferenceService.getIsExportIdTooltipShown()) {
                if (!ListenerUtil.mutListener.listen(2761)) {
                    getToolbar().postDelayed(() -> {
                        tooltipPopup = new TooltipPopup(this, R.string.preferences__tooltip_export_id_shown, R.layout.popup_tooltip_top_right, this);
                        tooltipPopup.show(this, getToolbar(), getString(R.string.tooltip_export_id), TooltipPopup.ALIGN_BELOW_ANCHOR_ARROW_RIGHT, 5000);
                    }, 1000);
                }
            }
        }
    }

    private void done() {
        GenericAlertDialog dialogFragment = GenericAlertDialog.newInstance(R.string.backup_id, R.string.really_leave_id_export, R.string.ok, R.string.back);
        if (!ListenerUtil.mutListener.listen(2763)) {
            dialogFragment.show(getSupportFragmentManager(), DIALOG_TAG_QUIT_CONFIRM);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_export_id;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2764)) {
            getMenuInflater().inflate(R.menu.activity_export_id, menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem printMenu = menu.findItem(R.id.menu_print);
        if (!ListenerUtil.mutListener.listen(2765)) {
            printMenu.setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void createWebPrintJob(WebView webView) {
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter;
        if ((ListenerUtil.mutListener.listen(2770) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(2769) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(2768) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(2767) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(2766) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
            printAdapter = webView.createPrintDocumentAdapter("Threema_ID_" + identity);
        } else {
            printAdapter = webView.createPrintDocumentAdapter();
        }
        String jobName = getString(R.string.app_name) + " " + getString(R.string.backup_id_title);
        if (!ListenerUtil.mutListener.listen(2771)) {
            printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
        }
    }

    private void printBitmap(Bitmap bitmap) {
        String html = "<html><body><center><h1>" + getString(R.string.backup_share_subject) + "</h1><h2>" + identity + "</h2><br><br><img src='{IMAGE_URL}' width='350px' height='350px'/>" + "<font face='monospace' size='8pt'><br><br>" + backupData + "</font></center></body></html>";
        WebView webView = new WebView(this);
        if (!ListenerUtil.mutListener.listen(2774)) {
            webView.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    if (!ListenerUtil.mutListener.listen(2772)) {
                        createWebPrintJob(view);
                    }
                    if (!ListenerUtil.mutListener.listen(2773)) {
                        printWebView = null;
                    }
                }
            });
        }
        // Convert bitmap to Base64 encoded image
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (!ListenerUtil.mutListener.listen(2775)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imgageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        String image = "data:image/png;base64," + imgageBase64;
        if (!ListenerUtil.mutListener.listen(2776)) {
            html = html.replace("{IMAGE_URL}", image);
        }
        if (!ListenerUtil.mutListener.listen(2777)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
        if (!ListenerUtil.mutListener.listen(2778)) {
            // to the PrintManager
            printWebView = webView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(2782)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(2779)) {
                        done();
                    }
                    return true;
                case R.id.menu_print:
                    if (!ListenerUtil.mutListener.listen(2780)) {
                        printBitmap(qrcodeBitmap);
                    }
                    break;
                case R.id.menu_backup_share:
                    if (!ListenerUtil.mutListener.listen(2781)) {
                        shareId();
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareId() {
        String shareText = getString(R.string.backup_share_content) + "\n\n" + backupData;
        String shareSubject = getString(R.string.backup_share_subject) + " " + this.identity;
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(2783)) {
            shareIntent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(2784)) {
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        }
        if (!ListenerUtil.mutListener.listen(2785)) {
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        }
        if (!ListenerUtil.mutListener.listen(2786)) {
            startActivity(shareIntent);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(2787)) {
            done();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(2788)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(2789)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        Intent upIntent = new Intent(ExportIDResultActivity.this, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(2790)) {
            NavUtils.navigateUpTo(ExportIDResultActivity.this, upIntent);
        }
        if (!ListenerUtil.mutListener.listen(2791)) {
            finish();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }
}
