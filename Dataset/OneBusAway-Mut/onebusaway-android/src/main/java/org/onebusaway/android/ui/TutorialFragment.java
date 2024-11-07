/*
* Copyright (C) 2014 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.onebusaway.android.R;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * TutorialFragment is a general-use fragment that shows a tutorial "wizard"
 * using the provided image and string resources.  See #335 for details and screenshots.
 *
 * Example usage:
 *
 * First, define a string and resource array in the `array.xml`:
 *     <!-- Resource array for tutorial strings -->
 *     <array name="report_types_icons_without_open311">
 *         <item>@drawable/stop_issue_tutorial_0</item>
 *         <item>@drawable/stop_issue_tutorial_2</item>
 *         <item>@drawable/stop_issue_tutorial_3</item>
 *     </array>
 *
 *     <!-- Resource array for tutorial images -->
 *     <string-array name="preferred_units_options">
 *         <item>@string/preferences_preferred_units_option_automatic</item>
 *         <item>@string/preferences_preferred_units_option_metric</item>
 *         <item>@string/preferences_preferred_units_option_imperial</item>
 *     </string-array>
 *
 * Then, pass in these arrays as a bundle into the TutorialFragment when instantiating it:
 *
 *     TutorialFragment tutorialFragment = new TutorialFragment();
 *     Bundle bundle = new Bundle();
 *     // Set string resources from arrays
 *     bundle.putInt(TutorialFragment.STRING_RESOURCE_ID, R.array.report_stop_issue_tutorial_desc);
 *     // Set image resources from arrays
 *     bundle.putInt(TutorialFragment.IMAGE_RESOURCE_ID, R.array.report_stop_issue_tutorial_images);
 *     tutorialFragment.setArguments(bundle);
 */
public class TutorialFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    public static final String STRING_RESOURCE_ID = "stringResource";

    public static final String IMAGE_RESOURCE_ID = "imageResource";

    int[] images;

    private Button pagerDone;

    private Button pagerPrev;

    private Button pagerNext;

    private ViewPager viewPager;

    int index = 0;

    /**
     * Array of resources
     */
    private int stringArrayResourceId;

    private int imageArrayResourceId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1184)) {
            stringArrayResourceId = getArguments().getInt(STRING_RESOURCE_ID, -1);
        }
        if (!ListenerUtil.mutListener.listen(1185)) {
            imageArrayResourceId = getArguments().getInt(IMAGE_RESOURCE_ID, -1);
        }
        return inflater.inflate(R.layout.tutorial, null, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1186)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1187)) {
            setupViews();
        }
    }

    private void setupViews() {
        TypedArray typedArray = getResources().obtainTypedArray(imageArrayResourceId);
        String[] texts = getResources().getStringArray(stringArrayResourceId);
        if (!ListenerUtil.mutListener.listen(1188)) {
            images = new int[typedArray.length()];
        }
        if (!ListenerUtil.mutListener.listen(1195)) {
            {
                long _loopCounter13 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1194) ? (i >= typedArray.length()) : (ListenerUtil.mutListener.listen(1193) ? (i <= typedArray.length()) : (ListenerUtil.mutListener.listen(1192) ? (i > typedArray.length()) : (ListenerUtil.mutListener.listen(1191) ? (i != typedArray.length()) : (ListenerUtil.mutListener.listen(1190) ? (i == typedArray.length()) : (i < typedArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                    if (!ListenerUtil.mutListener.listen(1189)) {
                        images[i] = typedArray.getResourceId(i, -1);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1196)) {
            typedArray.recycle();
        }
        if (!ListenerUtil.mutListener.listen(1197)) {
            updatePagerIndicator(0, images.length);
        }
        if (!ListenerUtil.mutListener.listen(1198)) {
            viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
        }
        PagerAdapter adapter = new ImageAdapter(getActivity(), images, texts);
        if (!ListenerUtil.mutListener.listen(1199)) {
            viewPager.setOffscreenPageLimit(3);
        }
        if (!ListenerUtil.mutListener.listen(1200)) {
            viewPager.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(1201)) {
            viewPager.addOnPageChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1202)) {
            pagerDone = ((Button) getActivity().findViewById(R.id.pager_button_done));
        }
        if (!ListenerUtil.mutListener.listen(1203)) {
            pagerNext = ((Button) getActivity().findViewById(R.id.pager_button_next));
        }
        if (!ListenerUtil.mutListener.listen(1204)) {
            pagerPrev = ((Button) getActivity().findViewById(R.id.pager_button_prev));
        }
        if (!ListenerUtil.mutListener.listen(1205)) {
            pagerDone.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1206)) {
            pagerPrev.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1207)) {
            pagerNext.setOnClickListener(this);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
    }

    @Override
    public void onPageSelected(int i) {
        if (!ListenerUtil.mutListener.listen(1208)) {
            index = i;
        }
        if (!ListenerUtil.mutListener.listen(1209)) {
            updatePagerIndicator(i, images.length);
        }
        if (!ListenerUtil.mutListener.listen(1210)) {
            updateNavigationButtons(i, images.length);
        }
        if (!ListenerUtil.mutListener.listen(1221)) {
            if ((ListenerUtil.mutListener.listen(1219) ? (i >= (ListenerUtil.mutListener.listen(1214) ? (images.length % 1) : (ListenerUtil.mutListener.listen(1213) ? (images.length / 1) : (ListenerUtil.mutListener.listen(1212) ? (images.length * 1) : (ListenerUtil.mutListener.listen(1211) ? (images.length + 1) : (images.length - 1)))))) : (ListenerUtil.mutListener.listen(1218) ? (i <= (ListenerUtil.mutListener.listen(1214) ? (images.length % 1) : (ListenerUtil.mutListener.listen(1213) ? (images.length / 1) : (ListenerUtil.mutListener.listen(1212) ? (images.length * 1) : (ListenerUtil.mutListener.listen(1211) ? (images.length + 1) : (images.length - 1)))))) : (ListenerUtil.mutListener.listen(1217) ? (i > (ListenerUtil.mutListener.listen(1214) ? (images.length % 1) : (ListenerUtil.mutListener.listen(1213) ? (images.length / 1) : (ListenerUtil.mutListener.listen(1212) ? (images.length * 1) : (ListenerUtil.mutListener.listen(1211) ? (images.length + 1) : (images.length - 1)))))) : (ListenerUtil.mutListener.listen(1216) ? (i < (ListenerUtil.mutListener.listen(1214) ? (images.length % 1) : (ListenerUtil.mutListener.listen(1213) ? (images.length / 1) : (ListenerUtil.mutListener.listen(1212) ? (images.length * 1) : (ListenerUtil.mutListener.listen(1211) ? (images.length + 1) : (images.length - 1)))))) : (ListenerUtil.mutListener.listen(1215) ? (i != (ListenerUtil.mutListener.listen(1214) ? (images.length % 1) : (ListenerUtil.mutListener.listen(1213) ? (images.length / 1) : (ListenerUtil.mutListener.listen(1212) ? (images.length * 1) : (ListenerUtil.mutListener.listen(1211) ? (images.length + 1) : (images.length - 1)))))) : (i == (ListenerUtil.mutListener.listen(1214) ? (images.length % 1) : (ListenerUtil.mutListener.listen(1213) ? (images.length / 1) : (ListenerUtil.mutListener.listen(1212) ? (images.length * 1) : (ListenerUtil.mutListener.listen(1211) ? (images.length + 1) : (images.length - 1))))))))))))
                if (!ListenerUtil.mutListener.listen(1220)) {
                    pagerDone.setEnabled(true);
                }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void onClick(View view) {
        if (!ListenerUtil.mutListener.listen(1225)) {
            if (view == pagerDone) {
                if (!ListenerUtil.mutListener.listen(1224)) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                }
            } else if (view == pagerNext) {
                if (!ListenerUtil.mutListener.listen(1223)) {
                    viewPager.setCurrentItem(++index, true);
                }
            } else if (view == pagerPrev) {
                if (!ListenerUtil.mutListener.listen(1222)) {
                    viewPager.setCurrentItem(--index, true);
                }
            }
        }
    }

    private void updatePagerIndicator(int position, int size) {
        LinearLayout linear = (LinearLayout) getActivity().findViewById(R.id.pager_indicator);
        if (!ListenerUtil.mutListener.listen(1226)) {
            linear.removeAllViewsInLayout();
        }
        if (!ListenerUtil.mutListener.listen(1244)) {
            {
                long _loopCounter14 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1243) ? (i >= size) : (ListenerUtil.mutListener.listen(1242) ? (i <= size) : (ListenerUtil.mutListener.listen(1241) ? (i > size) : (ListenerUtil.mutListener.listen(1240) ? (i != size) : (ListenerUtil.mutListener.listen(1239) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                    ImageView iw = new ImageView(getActivity());
                    if (!ListenerUtil.mutListener.listen(1234)) {
                        if ((ListenerUtil.mutListener.listen(1231) ? (position >= i) : (ListenerUtil.mutListener.listen(1230) ? (position <= i) : (ListenerUtil.mutListener.listen(1229) ? (position > i) : (ListenerUtil.mutListener.listen(1228) ? (position < i) : (ListenerUtil.mutListener.listen(1227) ? (position != i) : (position == i))))))) {
                            if (!ListenerUtil.mutListener.listen(1233)) {
                                iw.setImageResource(R.drawable.pager_dot_hover);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1232)) {
                                iw.setImageResource(R.drawable.pager_dot);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1235)) {
                        iw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    }
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iw.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(1236)) {
                        params.setMargins(5, 0, 0, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(1237)) {
                        iw.setLayoutParams(params);
                    }
                    if (!ListenerUtil.mutListener.listen(1238)) {
                        linear.addView(iw);
                    }
                }
            }
        }
    }

    private void updateNavigationButtons(int i, int size) {
        if (!ListenerUtil.mutListener.listen(1278)) {
            if ((ListenerUtil.mutListener.listen(1259) ? ((ListenerUtil.mutListener.listen(1253) ? (i >= (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1252) ? (i <= (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1251) ? (i > (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1250) ? (i < (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1249) ? (i == (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (i != (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1))))))))))) || (ListenerUtil.mutListener.listen(1258) ? (i >= 0) : (ListenerUtil.mutListener.listen(1257) ? (i <= 0) : (ListenerUtil.mutListener.listen(1256) ? (i > 0) : (ListenerUtil.mutListener.listen(1255) ? (i < 0) : (ListenerUtil.mutListener.listen(1254) ? (i != 0) : (i == 0))))))) : ((ListenerUtil.mutListener.listen(1253) ? (i >= (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1252) ? (i <= (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1251) ? (i > (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1250) ? (i < (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1249) ? (i == (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1)))))) : (i != (ListenerUtil.mutListener.listen(1248) ? (size % 1) : (ListenerUtil.mutListener.listen(1247) ? (size / 1) : (ListenerUtil.mutListener.listen(1246) ? (size * 1) : (ListenerUtil.mutListener.listen(1245) ? (size + 1) : (size - 1))))))))))) && (ListenerUtil.mutListener.listen(1258) ? (i >= 0) : (ListenerUtil.mutListener.listen(1257) ? (i <= 0) : (ListenerUtil.mutListener.listen(1256) ? (i > 0) : (ListenerUtil.mutListener.listen(1255) ? (i < 0) : (ListenerUtil.mutListener.listen(1254) ? (i != 0) : (i == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(1275)) {
                    pagerPrev.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1276)) {
                    pagerDone.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1277)) {
                    pagerNext.setVisibility(View.VISIBLE);
                }
            } else if ((ListenerUtil.mutListener.listen(1268) ? (i >= (ListenerUtil.mutListener.listen(1263) ? (size % 1) : (ListenerUtil.mutListener.listen(1262) ? (size / 1) : (ListenerUtil.mutListener.listen(1261) ? (size * 1) : (ListenerUtil.mutListener.listen(1260) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1267) ? (i <= (ListenerUtil.mutListener.listen(1263) ? (size % 1) : (ListenerUtil.mutListener.listen(1262) ? (size / 1) : (ListenerUtil.mutListener.listen(1261) ? (size * 1) : (ListenerUtil.mutListener.listen(1260) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1266) ? (i > (ListenerUtil.mutListener.listen(1263) ? (size % 1) : (ListenerUtil.mutListener.listen(1262) ? (size / 1) : (ListenerUtil.mutListener.listen(1261) ? (size * 1) : (ListenerUtil.mutListener.listen(1260) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1265) ? (i < (ListenerUtil.mutListener.listen(1263) ? (size % 1) : (ListenerUtil.mutListener.listen(1262) ? (size / 1) : (ListenerUtil.mutListener.listen(1261) ? (size * 1) : (ListenerUtil.mutListener.listen(1260) ? (size + 1) : (size - 1)))))) : (ListenerUtil.mutListener.listen(1264) ? (i != (ListenerUtil.mutListener.listen(1263) ? (size % 1) : (ListenerUtil.mutListener.listen(1262) ? (size / 1) : (ListenerUtil.mutListener.listen(1261) ? (size * 1) : (ListenerUtil.mutListener.listen(1260) ? (size + 1) : (size - 1)))))) : (i == (ListenerUtil.mutListener.listen(1263) ? (size % 1) : (ListenerUtil.mutListener.listen(1262) ? (size / 1) : (ListenerUtil.mutListener.listen(1261) ? (size * 1) : (ListenerUtil.mutListener.listen(1260) ? (size + 1) : (size - 1)))))))))))) {
                if (!ListenerUtil.mutListener.listen(1272)) {
                    pagerPrev.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1273)) {
                    pagerDone.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1274)) {
                    pagerNext.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1269)) {
                    pagerPrev.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1270)) {
                    pagerDone.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1271)) {
                    pagerNext.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Image adapter for tutorial images
     */
    public class ImageAdapter extends PagerAdapter {

        Context context;

        int[] images;

        String[] texts;

        LayoutInflater inflater;

        public ImageAdapter(Context context, int[] images, String[] texts) {
            if (!ListenerUtil.mutListener.listen(1279)) {
                this.context = context;
            }
            if (!ListenerUtil.mutListener.listen(1280)) {
                this.images = images;
            }
            if (!ListenerUtil.mutListener.listen(1281)) {
                this.texts = texts;
            }
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView;
            if (!ListenerUtil.mutListener.listen(1282)) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            View itemView = inflater.inflate(R.layout.tutorial_item, container, false);
            imageView = (ImageView) itemView.findViewById(R.id.ti_imageView);
            Spanned html = convertStringToHtml(texts[position]);
            if (!ListenerUtil.mutListener.listen(1283)) {
                ((TextView) itemView.findViewById(R.id.ti_textView)).setText(html);
            }
            Bitmap btm = BitmapFactory.decodeResource(context.getResources(), images[position]);
            if (!ListenerUtil.mutListener.listen(1284)) {
                imageView.setImageBitmap(btm);
            }
            if (!ListenerUtil.mutListener.listen(1285)) {
                container.addView(itemView);
            }
            return itemView;
        }

        private Spanned convertStringToHtml(String text) {
            return Html.fromHtml("<body style=\"text-align:center; \">" + text + "</body>");
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            try {
                if (!ListenerUtil.mutListener.listen(1288)) {
                    if (object instanceof View)
                        if (!ListenerUtil.mutListener.listen(1287)) {
                            container.removeView((View) object);
                        }
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(1286)) {
                    e.printStackTrace();
                }
            }
        }
    }
}
