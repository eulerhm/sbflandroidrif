package org.wordpress.android.ui.themes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ImageViewCompat;
import com.google.android.material.elevation.ElevationOverlayProvider;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.model.ThemeModel;
import org.wordpress.android.ui.plans.PlansConstants;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.themes.ThemeBrowserFragment.ThemeBrowserFragmentCallback;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import org.wordpress.android.widgets.HeaderGridView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class ThemeBrowserAdapter extends BaseAdapter implements Filterable {

    private static final String THEME_IMAGE_PARAMETER = "w=";

    private final Context mContext;

    private final long mSitePlanId;

    private final LayoutInflater mInflater;

    private final ThemeBrowserFragmentCallback mCallback;

    private final ImageManager mImageManager;

    private int mViewWidth;

    private String mQuery;

    private int mElevatedSurfaceColor;

    private final List<ThemeModel> mAllThemes = new ArrayList<>();

    private final List<ThemeModel> mFilteredThemes = new ArrayList<>();

    ThemeBrowserAdapter(Context context, long sitePlanId, ThemeBrowserFragmentCallback callback, ImageManager imageManager) {
        mContext = context;
        mSitePlanId = sitePlanId;
        mInflater = LayoutInflater.from(context);
        mCallback = callback;
        if (!ListenerUtil.mutListener.listen(23397)) {
            mViewWidth = AppPrefs.getThemeImageSizeWidth();
        }
        mImageManager = imageManager;
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(mContext);
        float cardElevation = mContext.getResources().getDimension(R.dimen.card_elevation);
        if (!ListenerUtil.mutListener.listen(23398)) {
            mElevatedSurfaceColor = elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(cardElevation);
        }
    }

    private static class ThemeViewHolder {

        private final CardView mCardView;

        private final ImageView mImageView;

        private final TextView mNameView;

        private final TextView mActiveView;

        private final TextView mPriceView;

        private final ImageButton mImageButton;

        private final FrameLayout mFrameLayout;

        private final RelativeLayout mDetailsView;

        ThemeViewHolder(View view) {
            mCardView = view.findViewById(R.id.theme_grid_card);
            mImageView = view.findViewById(R.id.theme_grid_item_image);
            mNameView = view.findViewById(R.id.theme_grid_item_name);
            mPriceView = view.findViewById(R.id.theme_grid_item_price);
            mActiveView = view.findViewById(R.id.theme_grid_item_active);
            mImageButton = view.findViewById(R.id.theme_grid_item_image_button);
            mFrameLayout = view.findViewById(R.id.theme_grid_item_image_layout);
            mDetailsView = view.findViewById(R.id.theme_grid_item_details);
        }
    }

    @Override
    public int getCount() {
        return mFilteredThemes.size();
    }

    public int getUnfilteredCount() {
        return mAllThemes.size();
    }

    @Override
    public Object getItem(int position) {
        return mFilteredThemes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void setThemeList(@NonNull List<ThemeModel> themes) {
        if (!ListenerUtil.mutListener.listen(23399)) {
            mAllThemes.clear();
        }
        if (!ListenerUtil.mutListener.listen(23400)) {
            mAllThemes.addAll(themes);
        }
        if (!ListenerUtil.mutListener.listen(23401)) {
            mFilteredThemes.clear();
        }
        if (!ListenerUtil.mutListener.listen(23402)) {
            mFilteredThemes.addAll(themes);
        }
        if (!ListenerUtil.mutListener.listen(23405)) {
            if (!TextUtils.isEmpty(mQuery)) {
                if (!ListenerUtil.mutListener.listen(23404)) {
                    getFilter().filter(mQuery);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23403)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThemeViewHolder holder;
        if ((ListenerUtil.mutListener.listen(23406) ? (convertView == null && convertView.getTag() == null) : (convertView == null || convertView.getTag() == null))) {
            if (!ListenerUtil.mutListener.listen(23407)) {
                convertView = mInflater.inflate(R.layout.theme_grid_item, parent, false);
            }
            holder = new ThemeViewHolder(convertView);
            if (!ListenerUtil.mutListener.listen(23408)) {
                convertView.setTag(holder);
            }
        } else {
            holder = (ThemeViewHolder) convertView.getTag();
        }
        if (!ListenerUtil.mutListener.listen(23409)) {
            configureThemeImageSize(parent);
        }
        ThemeModel theme = mFilteredThemes.get(position);
        String screenshotURL = theme.getScreenshotUrl();
        String themeId = theme.getThemeId();
        boolean isPremium = !theme.isFree();
        boolean isCurrent = theme.getActive();
        if (!ListenerUtil.mutListener.listen(23410)) {
            holder.mNameView.setText(theme.getName());
        }
        if (!ListenerUtil.mutListener.listen(23414)) {
            if (isPremium) {
                if (!ListenerUtil.mutListener.listen(23412)) {
                    holder.mPriceView.setText(theme.getPriceText());
                }
                if (!ListenerUtil.mutListener.listen(23413)) {
                    holder.mPriceView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23411)) {
                    holder.mPriceView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23418)) {
            // catch the case where a URL has no protocol
            if (!screenshotURL.startsWith(ThemeWebActivity.THEME_HTTP_PREFIX)) {
                if (!ListenerUtil.mutListener.listen(23416)) {
                    // strip // before adding the protocol
                    if (screenshotURL.startsWith("//")) {
                        if (!ListenerUtil.mutListener.listen(23415)) {
                            screenshotURL = screenshotURL.substring(2);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23417)) {
                    screenshotURL = ThemeWebActivity.THEME_HTTPS_PROTOCOL + screenshotURL;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23419)) {
            configureImageView(holder, screenshotURL, themeId, isCurrent);
        }
        if (!ListenerUtil.mutListener.listen(23420)) {
            configureImageButton(holder, themeId, isPremium, isCurrent);
        }
        if (!ListenerUtil.mutListener.listen(23421)) {
            configureCardView(holder, isCurrent);
        }
        return convertView;
    }

    @SuppressWarnings("deprecation")
    private void configureCardView(ThemeViewHolder themeViewHolder, boolean isCurrent) {
        if (!ListenerUtil.mutListener.listen(23432)) {
            if (isCurrent) {
                ColorStateList color = ContextExtensionsKt.getColorStateListFromAttribute(mContext, R.attr.colorOnPrimarySurface);
                if (!ListenerUtil.mutListener.listen(23427)) {
                    themeViewHolder.mDetailsView.setBackgroundColor(ContextExtensionsKt.getColorFromAttribute(mContext, R.attr.colorPrimary));
                }
                if (!ListenerUtil.mutListener.listen(23428)) {
                    themeViewHolder.mNameView.setTextColor(color);
                }
                if (!ListenerUtil.mutListener.listen(23429)) {
                    themeViewHolder.mActiveView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(23430)) {
                    themeViewHolder.mCardView.setCardBackgroundColor(ContextExtensionsKt.getColorFromAttribute(mContext, R.attr.colorPrimary));
                }
                if (!ListenerUtil.mutListener.listen(23431)) {
                    ImageViewCompat.setImageTintList(themeViewHolder.mImageButton, color);
                }
            } else {
                ColorStateList color = ContextExtensionsKt.getColorStateListFromAttribute(mContext, R.attr.colorOnSurface);
                if (!ListenerUtil.mutListener.listen(23422)) {
                    themeViewHolder.mDetailsView.setBackgroundColor(mElevatedSurfaceColor);
                }
                if (!ListenerUtil.mutListener.listen(23423)) {
                    themeViewHolder.mNameView.setTextColor(color);
                }
                if (!ListenerUtil.mutListener.listen(23424)) {
                    themeViewHolder.mActiveView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23425)) {
                    themeViewHolder.mCardView.setCardBackgroundColor(mElevatedSurfaceColor);
                }
                if (!ListenerUtil.mutListener.listen(23426)) {
                    ImageViewCompat.setImageTintList(themeViewHolder.mImageButton, color);
                }
            }
        }
    }

    private void configureImageView(ThemeViewHolder themeViewHolder, String screenshotURL, final String themeId, final boolean isCurrent) {
        if (!ListenerUtil.mutListener.listen(23433)) {
            mImageManager.load(themeViewHolder.mImageView, ImageType.THEME, getUrlWithWidth(screenshotURL), ScaleType.FIT_CENTER);
        }
        if (!ListenerUtil.mutListener.listen(23437)) {
            themeViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(23436)) {
                        if (isCurrent) {
                            if (!ListenerUtil.mutListener.listen(23435)) {
                                mCallback.onTryAndCustomizeSelected(themeId);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23434)) {
                                mCallback.onViewSelected(themeId);
                            }
                        }
                    }
                }
            });
        }
    }

    private String getUrlWithWidth(String screenshotURL) {
        if (screenshotURL.contains("?")) {
            return screenshotURL + "&" + THEME_IMAGE_PARAMETER + mViewWidth;
        } else {
            return screenshotURL + "?" + THEME_IMAGE_PARAMETER + mViewWidth;
        }
    }

    private void configureImageButton(ThemeViewHolder themeViewHolder, final String themeId, final boolean isPremium, boolean isCurrent) {
        final PopupMenu popupMenu = new PopupMenu(mContext, themeViewHolder.mImageButton);
        if (!ListenerUtil.mutListener.listen(23438)) {
            popupMenu.getMenuInflater().inflate(R.menu.theme_more, popupMenu.getMenu());
        }
        if (!ListenerUtil.mutListener.listen(23439)) {
            configureMenuForTheme(popupMenu.getMenu(), isCurrent);
        }
        if (!ListenerUtil.mutListener.listen(23448)) {
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int i = item.getItemId();
                    if (!ListenerUtil.mutListener.listen(23447)) {
                        if (i == R.id.menu_activate) {
                            if (!ListenerUtil.mutListener.listen(23446)) {
                                if (canActivateThemeDirectly(isPremium, mSitePlanId)) {
                                    if (!ListenerUtil.mutListener.listen(23445)) {
                                        mCallback.onActivateSelected(themeId);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(23444)) {
                                        // forward the user online to complete the activation
                                        mCallback.onDetailsSelected(themeId);
                                    }
                                }
                            }
                        } else if (i == R.id.menu_try_and_customize) {
                            if (!ListenerUtil.mutListener.listen(23443)) {
                                mCallback.onTryAndCustomizeSelected(themeId);
                            }
                        } else if (i == R.id.menu_view) {
                            if (!ListenerUtil.mutListener.listen(23442)) {
                                mCallback.onViewSelected(themeId);
                            }
                        } else if (i == R.id.menu_details) {
                            if (!ListenerUtil.mutListener.listen(23441)) {
                                mCallback.onDetailsSelected(themeId);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23440)) {
                                mCallback.onSupportSelected(themeId);
                            }
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(23450)) {
            themeViewHolder.mImageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(23449)) {
                        popupMenu.show();
                    }
                }
            });
        }
    }

    private boolean canActivateThemeDirectly(final boolean isPremiumTheme, final long sitePlanId) {
        if (!ListenerUtil.mutListener.listen(23451)) {
            if (!isPremiumTheme) {
                // It's a free theme so, can always activate directly
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(23453)) {
            if ((ListenerUtil.mutListener.listen(23452) ? (sitePlanId == PlansConstants.PREMIUM_PLAN_ID && mSitePlanId == PlansConstants.BUSINESS_PLAN_ID) : (sitePlanId == PlansConstants.PREMIUM_PLAN_ID || mSitePlanId == PlansConstants.BUSINESS_PLAN_ID))) {
                // Can activate any theme on a Premium and Business site plan
                return true;
            }
        }
        // Theme cannot be activated directly and needs to be purchased
        return false;
    }

    private void configureMenuForTheme(Menu menu, boolean isCurrent) {
        MenuItem activate = menu.findItem(R.id.menu_activate);
        MenuItem customize = menu.findItem(R.id.menu_try_and_customize);
        MenuItem view = menu.findItem(R.id.menu_view);
        if (!ListenerUtil.mutListener.listen(23455)) {
            if (activate != null) {
                if (!ListenerUtil.mutListener.listen(23454)) {
                    activate.setVisible(!isCurrent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23459)) {
            if (customize != null) {
                if (!ListenerUtil.mutListener.listen(23458)) {
                    if (isCurrent) {
                        if (!ListenerUtil.mutListener.listen(23457)) {
                            customize.setTitle(R.string.customize);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23456)) {
                            customize.setTitle(R.string.theme_try_and_customize);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23461)) {
            if (view != null) {
                if (!ListenerUtil.mutListener.listen(23460)) {
                    view.setVisible(!isCurrent);
                }
            }
        }
    }

    private void configureThemeImageSize(ViewGroup parent) {
        HeaderGridView gridView = parent.findViewById(R.id.theme_listview);
        int numColumns = gridView.getNumColumns();
        int screenWidth = gridView.getWidth();
        int imageWidth = (ListenerUtil.mutListener.listen(23465) ? (screenWidth % numColumns) : (ListenerUtil.mutListener.listen(23464) ? (screenWidth * numColumns) : (ListenerUtil.mutListener.listen(23463) ? (screenWidth - numColumns) : (ListenerUtil.mutListener.listen(23462) ? (screenWidth + numColumns) : (screenWidth / numColumns)))));
        if (!ListenerUtil.mutListener.listen(23473)) {
            if ((ListenerUtil.mutListener.listen(23470) ? (imageWidth >= mViewWidth) : (ListenerUtil.mutListener.listen(23469) ? (imageWidth <= mViewWidth) : (ListenerUtil.mutListener.listen(23468) ? (imageWidth < mViewWidth) : (ListenerUtil.mutListener.listen(23467) ? (imageWidth != mViewWidth) : (ListenerUtil.mutListener.listen(23466) ? (imageWidth == mViewWidth) : (imageWidth > mViewWidth))))))) {
                if (!ListenerUtil.mutListener.listen(23471)) {
                    mViewWidth = imageWidth;
                }
                if (!ListenerUtil.mutListener.listen(23472)) {
                    AppPrefs.setThemeImageSizeWidth(mViewWidth);
                }
            }
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (!ListenerUtil.mutListener.listen(23474)) {
                    mFilteredThemes.clear();
                }
                if (!ListenerUtil.mutListener.listen(23475)) {
                    mFilteredThemes.addAll((List<ThemeModel>) results.values);
                }
                if (!ListenerUtil.mutListener.listen(23476)) {
                    ThemeBrowserAdapter.this.notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ThemeModel> filtered = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(23483)) {
                    if (TextUtils.isEmpty(constraint)) {
                        if (!ListenerUtil.mutListener.listen(23481)) {
                            mQuery = null;
                        }
                        if (!ListenerUtil.mutListener.listen(23482)) {
                            filtered.addAll(mAllThemes);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23477)) {
                            mQuery = constraint.toString();
                        }
                        // Locale.ROOT is used on user input for convenience as all the theme names are in english
                        String lcConstraint = constraint.toString().toLowerCase(Locale.ROOT);
                        if (!ListenerUtil.mutListener.listen(23480)) {
                            {
                                long _loopCounter349 = 0;
                                for (ThemeModel theme : mAllThemes) {
                                    ListenerUtil.loopListener.listen("_loopCounter349", ++_loopCounter349);
                                    if (!ListenerUtil.mutListener.listen(23479)) {
                                        if (theme.getName().toLowerCase(Locale.ROOT).contains(lcConstraint)) {
                                            if (!ListenerUtil.mutListener.listen(23478)) {
                                                filtered.add(theme);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                FilterResults results = new FilterResults();
                if (!ListenerUtil.mutListener.listen(23484)) {
                    results.values = filtered;
                }
                return results;
            }
        };
    }
}
