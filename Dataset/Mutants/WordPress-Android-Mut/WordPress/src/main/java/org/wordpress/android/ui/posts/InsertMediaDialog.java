package org.wordpress.android.ui.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.util.AniUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Displayed after user selects multiple items from the WP media library to insert into
 * a post - provides a choice between inserting them individually or as a gallery
 */
public class InsertMediaDialog extends AppCompatDialogFragment {

    public enum InsertType {

        INDIVIDUALLY, GALLERY
    }

    public enum GalleryType {

        DEFAULT, TILED, SQUARES, CIRCLES, SLIDESHOW;

        // overridden to return the actual name used in the gallery shortcode
        @Override
        public String toString() {
            switch(this) {
                case CIRCLES:
                    return "circle";
                case SLIDESHOW:
                    return "slideshow";
                case SQUARES:
                    return "square";
                case TILED:
                    return "rectangular";
                default:
                    return "";
            }
        }
    }

    public interface InsertMediaCallback {

        void onCompleted(@NonNull InsertMediaDialog dialog);
    }

    private static final int DEFAULT_COLUMN_COUNT = 3;

    private static final int MAX_COLUMN_COUNT = 9;

    private static final String STATE_INSERT_TYPE = "STATE_INSERT_TYPE";

    private static final String STATE_NUM_COLUMNS = "STATE_NUM_COLUMNS";

    private static final String STATE_GALLERY_TYPE_ORD = "GALLERY_TYPE_ORD";

    private RadioGroup mInsertRadioGroup;

    private RadioGroup mGalleryRadioGroup;

    private ViewGroup mNumColumnsContainer;

    private SeekBar mNumColumnsSeekBar;

    private InsertMediaCallback mCallback;

    private SiteModel mSite;

    private GalleryType mGalleryType;

    private InsertType mInsertType;

    private int mNumColumns;

    public static InsertMediaDialog newInstance(@NonNull InsertMediaCallback callback, @NonNull SiteModel site) {
        InsertMediaDialog dialog = new InsertMediaDialog();
        if (!ListenerUtil.mutListener.listen(12588)) {
            dialog.setCallback(callback);
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(12589)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(12590)) {
            dialog.setArguments(args);
        }
        return dialog;
    }

    private void setCallback(@NonNull InsertMediaCallback callback) {
        if (!ListenerUtil.mutListener.listen(12591)) {
            mCallback = callback;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12592)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12594)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(12593)) {
                    mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12595)) {
            getDialog().setTitle(R.string.media_insert_title);
        }
        View view = inflater.inflate(R.layout.insert_media_dialog, container, false);
        if (!ListenerUtil.mutListener.listen(12596)) {
            mInsertRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group_insert_type);
        }
        if (!ListenerUtil.mutListener.listen(12597)) {
            mGalleryRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group_gallery_type);
        }
        if (!ListenerUtil.mutListener.listen(12598)) {
            mNumColumnsContainer = (ViewGroup) view.findViewById(R.id.num_columns_container);
        }
        if (!ListenerUtil.mutListener.listen(12599)) {
            mNumColumnsSeekBar = (SeekBar) mNumColumnsContainer.findViewById(R.id.seekbar_num_columns);
        }
        if (!ListenerUtil.mutListener.listen(12603)) {
            mInsertRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (!ListenerUtil.mutListener.listen(12602)) {
                        if (checkedId == R.id.radio_insert_as_gallery) {
                            if (!ListenerUtil.mutListener.listen(12601)) {
                                setInsertType(InsertType.GALLERY);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(12600)) {
                                setInsertType(InsertType.INDIVIDUALLY);
                            }
                        }
                    }
                }
            });
        }
        // self-hosted sites don't support gallery types
        boolean enableGalleryType = (ListenerUtil.mutListener.listen(12604) ? (mSite != null || mSite.isUsingWpComRestApi()) : (mSite != null && mSite.isUsingWpComRestApi()));
        if (!ListenerUtil.mutListener.listen(12610)) {
            if (enableGalleryType) {
                if (!ListenerUtil.mutListener.listen(12609)) {
                    mGalleryRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                            GalleryType galleryType;
                            switch(checkedId) {
                                case R.id.radio_circles:
                                    galleryType = GalleryType.CIRCLES;
                                    break;
                                case R.id.radio_slideshow:
                                    galleryType = GalleryType.SLIDESHOW;
                                    break;
                                case R.id.radio_squares:
                                    galleryType = GalleryType.SQUARES;
                                    break;
                                case R.id.radio_tiled:
                                    galleryType = GalleryType.TILED;
                                    break;
                                default:
                                    galleryType = GalleryType.DEFAULT;
                                    break;
                            }
                            if (!ListenerUtil.mutListener.listen(12608)) {
                                setGalleryType(galleryType);
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12605)) {
                    mGalleryRadioGroup.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(12606)) {
                    mNumColumnsContainer.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(12607)) {
                    mGalleryType = GalleryType.DEFAULT;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12615)) {
            mNumColumnsSeekBar.setMax((ListenerUtil.mutListener.listen(12614) ? (MAX_COLUMN_COUNT % 1) : (ListenerUtil.mutListener.listen(12613) ? (MAX_COLUMN_COUNT / 1) : (ListenerUtil.mutListener.listen(12612) ? (MAX_COLUMN_COUNT * 1) : (ListenerUtil.mutListener.listen(12611) ? (MAX_COLUMN_COUNT + 1) : (MAX_COLUMN_COUNT - 1))))));
        }
        if (!ListenerUtil.mutListener.listen(12618)) {
            mNumColumnsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (!ListenerUtil.mutListener.listen(12617)) {
                        if (fromUser) {
                            if (!ListenerUtil.mutListener.listen(12616)) {
                                setNumColumns(progress, true);
                            }
                        }
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
        Button btnCancel = (Button) view.findViewById(R.id.button_cancel);
        if (!ListenerUtil.mutListener.listen(12620)) {
            btnCancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(12619)) {
                        getDialog().cancel();
                    }
                }
            });
        }
        Button btnOk = (Button) view.findViewById(R.id.button_ok);
        if (!ListenerUtil.mutListener.listen(12623)) {
            btnOk.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(12621)) {
                        mCallback.onCompleted(InsertMediaDialog.this);
                    }
                    if (!ListenerUtil.mutListener.listen(12622)) {
                        getDialog().dismiss();
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(12624)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(12625)) {
            outState.putInt(STATE_INSERT_TYPE, mInsertType.ordinal());
        }
        if (!ListenerUtil.mutListener.listen(12626)) {
            outState.putInt(STATE_GALLERY_TYPE_ORD, mGalleryType.ordinal());
        }
        if (!ListenerUtil.mutListener.listen(12627)) {
            outState.putInt(STATE_NUM_COLUMNS, mNumColumns);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12628)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12635)) {
            if (savedInstanceState != null) {
                int insertTypeOrdinal = savedInstanceState.getInt(STATE_INSERT_TYPE);
                if (!ListenerUtil.mutListener.listen(12632)) {
                    setInsertType(InsertType.values()[insertTypeOrdinal]);
                }
                int galleryTypeOrdinal = savedInstanceState.getInt(STATE_GALLERY_TYPE_ORD);
                if (!ListenerUtil.mutListener.listen(12633)) {
                    setGalleryType(GalleryType.values()[galleryTypeOrdinal]);
                }
                if (!ListenerUtil.mutListener.listen(12634)) {
                    setNumColumns(savedInstanceState.getInt(STATE_NUM_COLUMNS), false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12629)) {
                    setInsertType(InsertType.GALLERY);
                }
                if (!ListenerUtil.mutListener.listen(12630)) {
                    setGalleryType(GalleryType.DEFAULT);
                }
                if (!ListenerUtil.mutListener.listen(12631)) {
                    setNumColumns(DEFAULT_COLUMN_COUNT, false);
                }
            }
        }
    }

    public InsertType getInsertType() {
        return mInsertType;
    }

    private void setInsertType(@NonNull InsertType insertType) {
        if (!ListenerUtil.mutListener.listen(12636)) {
            if (insertType == mInsertType) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12637)) {
            mInsertType = insertType;
        }
        @IdRes
        int radioId = insertType == InsertType.GALLERY ? R.id.radio_insert_as_gallery : R.id.radio_insert_individually;
        RadioButton radio = (RadioButton) getView().findViewById(radioId);
        if (!ListenerUtil.mutListener.listen(12639)) {
            if (!radio.isChecked()) {
                if (!ListenerUtil.mutListener.listen(12638)) {
                    radio.setChecked(true);
                }
            }
        }
        ViewGroup container = (ViewGroup) getView().findViewById(R.id.container_gallery_settings);
        boolean enableGalleryTypes = insertType == InsertType.GALLERY;
        if (!ListenerUtil.mutListener.listen(12644)) {
            if ((ListenerUtil.mutListener.listen(12640) ? (enableGalleryTypes || container.getVisibility() != View.VISIBLE) : (enableGalleryTypes && container.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(12643)) {
                    AniUtils.fadeIn(container, AniUtils.Duration.SHORT);
                }
            } else if ((ListenerUtil.mutListener.listen(12641) ? (!enableGalleryTypes || container.getVisibility() == View.VISIBLE) : (!enableGalleryTypes && container.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(12642)) {
                    AniUtils.fadeOut(container, AniUtils.Duration.SHORT, View.INVISIBLE);
                }
            }
        }
    }

    public GalleryType getGalleryType() {
        return mGalleryType;
    }

    private void setGalleryType(@NonNull GalleryType galleryType) {
        if (!ListenerUtil.mutListener.listen(12645)) {
            if (galleryType == mGalleryType) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12646)) {
            mGalleryType = galleryType;
        }
        // column count applies only to thumbnail grid
        boolean showNumColumns = (galleryType == GalleryType.DEFAULT);
        if (!ListenerUtil.mutListener.listen(12651)) {
            if ((ListenerUtil.mutListener.listen(12647) ? (showNumColumns || mNumColumnsContainer.getVisibility() != View.VISIBLE) : (showNumColumns && mNumColumnsContainer.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(12650)) {
                    AniUtils.fadeIn(mNumColumnsContainer, AniUtils.Duration.SHORT);
                }
            } else if ((ListenerUtil.mutListener.listen(12648) ? (!showNumColumns || mNumColumnsContainer.getVisibility() == View.VISIBLE) : (!showNumColumns && mNumColumnsContainer.getVisibility() == View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(12649)) {
                    AniUtils.fadeOut(mNumColumnsContainer, AniUtils.Duration.SHORT, View.INVISIBLE);
                }
            }
        }
        @IdRes
        final int resId;
        @DrawableRes
        final int drawableId;
        switch(galleryType) {
            case CIRCLES:
                resId = R.id.radio_circles;
                drawableId = R.drawable.gallery_icon_circles;
                break;
            case SLIDESHOW:
                resId = R.id.radio_slideshow;
                drawableId = R.drawable.gallery_icon_slideshow;
                break;
            case SQUARES:
                resId = R.id.radio_squares;
                drawableId = R.drawable.gallery_icon_squares;
                break;
            case TILED:
                resId = R.id.radio_tiled;
                drawableId = R.drawable.gallery_icon_tiled;
                break;
            default:
                resId = R.id.radio_thumbnail_grid;
                drawableId = R.drawable.gallery_icon_thumbnailgrid;
                break;
        }
        RadioButton radio = (RadioButton) mGalleryRadioGroup.findViewById(resId);
        if (!ListenerUtil.mutListener.listen(12653)) {
            if (!radio.isChecked()) {
                if (!ListenerUtil.mutListener.listen(12652)) {
                    radio.setChecked(true);
                }
            }
        }
        // scale out the gallery type image, then set the new image and scale it back in
        final ImageView imageView = (ImageView) getView().findViewById(R.id.image_gallery_type);
        if (!ListenerUtil.mutListener.listen(12656)) {
            AniUtils.scaleOut(imageView, View.VISIBLE, AniUtils.Duration.SHORT, new AniUtils.AnimationEndListener() {

                @Override
                public void onAnimationEnd() {
                    if (!ListenerUtil.mutListener.listen(12654)) {
                        imageView.setImageResource(drawableId);
                    }
                    if (!ListenerUtil.mutListener.listen(12655)) {
                        AniUtils.scaleIn(imageView, AniUtils.Duration.SHORT);
                    }
                }
            });
        }
    }

    public int getNumColumns() {
        return mNumColumns;
    }

    private void setNumColumns(int numColumns, boolean fromSeekBar) {
        if (!ListenerUtil.mutListener.listen(12664)) {
            // seekbar is zero-based, so increment the column count if this was called from it
            if (fromSeekBar) {
                if (!ListenerUtil.mutListener.listen(12663)) {
                    mNumColumns = (ListenerUtil.mutListener.listen(12662) ? (numColumns % 1) : (ListenerUtil.mutListener.listen(12661) ? (numColumns / 1) : (ListenerUtil.mutListener.listen(12660) ? (numColumns * 1) : (ListenerUtil.mutListener.listen(12659) ? (numColumns - 1) : (numColumns + 1)))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12657)) {
                    mNumColumns = numColumns;
                }
                if (!ListenerUtil.mutListener.listen(12658)) {
                    mNumColumnsSeekBar.setProgress(numColumns);
                }
            }
        }
        TextView textValue = (TextView) getView().findViewById(R.id.text_num_columns_label);
        if (!ListenerUtil.mutListener.listen(12672)) {
            if ((ListenerUtil.mutListener.listen(12669) ? (mNumColumns >= 1) : (ListenerUtil.mutListener.listen(12668) ? (mNumColumns <= 1) : (ListenerUtil.mutListener.listen(12667) ? (mNumColumns > 1) : (ListenerUtil.mutListener.listen(12666) ? (mNumColumns < 1) : (ListenerUtil.mutListener.listen(12665) ? (mNumColumns != 1) : (mNumColumns == 1))))))) {
                if (!ListenerUtil.mutListener.listen(12671)) {
                    textValue.setText(getString(R.string.media_gallery_column_count_single));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12670)) {
                    textValue.setText(String.format(getString(R.string.media_gallery_column_count_multi), mNumColumns));
                }
            }
        }
    }
}
