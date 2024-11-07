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

import org.onebusaway.android.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Created by Cagri Cetin
 */
public class BaseReportActivity extends AppCompatActivity {

    public static final String CLOSE_REQUEST = "BaseReportActivityClose";

    public static final String LOCATION_STRING = "locationString";

    protected RelativeLayout mInfoHeader;

    protected FrameLayout mInLineInstructions;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(10986)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(10990)) {
            if ((ListenerUtil.mutListener.listen(10988) ? ((ListenerUtil.mutListener.listen(10987) ? (resultCode == RESULT_OK || data != null) : (resultCode == RESULT_OK && data != null)) || data.getBooleanExtra(CLOSE_REQUEST, false)) : ((ListenerUtil.mutListener.listen(10987) ? (resultCode == RESULT_OK || data != null) : (resultCode == RESULT_OK && data != null)) && data.getBooleanExtra(CLOSE_REQUEST, false)))) {
                if (!ListenerUtil.mutListener.listen(10989)) {
                    finish();
                }
            }
        }
    }

    @SuppressLint("CommitTransaction")
    protected FragmentTransaction setFragment(Fragment fragment, int containerViewId) {
        FragmentManager fm = getSupportFragmentManager();
        return fm.beginTransaction().replace(containerViewId, fragment);
    }

    protected void removeFragmentByTag(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(tag);
        if (!ListenerUtil.mutListener.listen(10994)) {
            if (fragment != null) {
                FragmentTransaction trans = manager.beginTransaction();
                if (!ListenerUtil.mutListener.listen(10991)) {
                    trans.remove(fragment);
                }
                if (!ListenerUtil.mutListener.listen(10992)) {
                    trans.commit();
                }
                if (!ListenerUtil.mutListener.listen(10993)) {
                    manager.popBackStack();
                }
            }
        }
    }

    protected void setUpProgressBar() {
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        if (!ListenerUtil.mutListener.listen(10995)) {
            params.gravity = Gravity.END;
        }
        ProgressBar progressBar = new ProgressBar(this);
        if (!ListenerUtil.mutListener.listen(10996)) {
            progressBar.setIndeterminate(true);
        }
        if (!ListenerUtil.mutListener.listen(10997)) {
            progressBar.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(10998)) {
            progressBar.setIndeterminate(true);
        }
        if (!ListenerUtil.mutListener.listen(10999)) {
            progressBar.setLayoutParams(params);
        }
        if (!ListenerUtil.mutListener.listen(11000)) {
            progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        ActionBar ab = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(11003)) {
            if (ab != null) {
                if (!ListenerUtil.mutListener.listen(11001)) {
                    ab.setDisplayShowCustomEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(11002)) {
                    ab.setCustomView(progressBar);
                }
            }
        }
    }

    public void showProgress(Boolean visible) {
        if (!ListenerUtil.mutListener.listen(11004)) {
            if (getSupportActionBar() == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(11007)) {
            if (visible) {
                if (!ListenerUtil.mutListener.listen(11006)) {
                    getSupportActionBar().getCustomView().setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11005)) {
                    getSupportActionBar().getCustomView().setVisibility(View.GONE);
                }
            }
        }
    }

    protected void createToastMessage(String message) {
        if (!ListenerUtil.mutListener.listen(11008)) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    protected void addInfoText(String text) {
        if (!ListenerUtil.mutListener.listen(11010)) {
            // Instructions in header of report
            if (mInfoHeader == null) {
                if (!ListenerUtil.mutListener.listen(11009)) {
                    mInfoHeader = (RelativeLayout) findViewById(R.id.ri_info_header);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11011)) {
            ((TextView) mInfoHeader.findViewById(R.id.ri_info_text)).setText(text);
        }
        if (!ListenerUtil.mutListener.listen(11013)) {
            if (mInfoHeader.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(11012)) {
                    mInfoHeader.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11015)) {
            // Instructions in body of report
            if (mInLineInstructions == null) {
                if (!ListenerUtil.mutListener.listen(11014)) {
                    mInLineInstructions = (FrameLayout) findViewById(R.id.in_line_instructions_container);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11016)) {
            ((ImageView) findViewById(R.id.in_line_instructions_image)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11017)) {
            ((TextView) mInLineInstructions.findViewById(R.id.in_line_instructions_text)).setText(text);
        }
        if (!ListenerUtil.mutListener.listen(11019)) {
            if (mInLineInstructions.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(11018)) {
                    mInLineInstructions.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected boolean isInfoVisible() {
        if (!ListenerUtil.mutListener.listen(11021)) {
            if (mInfoHeader == null) {
                if (!ListenerUtil.mutListener.listen(11020)) {
                    mInfoHeader = (RelativeLayout) findViewById(R.id.ri_info_header);
                }
            }
        }
        return mInfoHeader.getVisibility() == View.VISIBLE;
    }

    protected void removeInfoText() {
        if (!ListenerUtil.mutListener.listen(11023)) {
            if (mInfoHeader == null) {
                if (!ListenerUtil.mutListener.listen(11022)) {
                    mInfoHeader = (RelativeLayout) findViewById(R.id.ri_info_header);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11024)) {
            ((TextView) mInfoHeader.findViewById(R.id.ri_info_text)).setText("");
        }
        if (!ListenerUtil.mutListener.listen(11025)) {
            mInfoHeader.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(11027)) {
            if (mInLineInstructions == null) {
                if (!ListenerUtil.mutListener.listen(11026)) {
                    mInLineInstructions = (FrameLayout) findViewById(R.id.in_line_instructions_container);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11028)) {
            ((TextView) mInLineInstructions.findViewById(R.id.in_line_instructions_text)).setText("");
        }
        if (!ListenerUtil.mutListener.listen(11029)) {
            mInLineInstructions.setVisibility(View.GONE);
        }
    }
}
