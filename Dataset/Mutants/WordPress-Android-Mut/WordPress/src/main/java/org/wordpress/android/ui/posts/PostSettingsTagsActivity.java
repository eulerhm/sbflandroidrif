package org.wordpress.android.ui.posts;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.util.ToastUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PostSettingsTagsActivity extends LocaleAwareActivity implements TagsSelectedListener {

    public static final String KEY_TAGS = "KEY_TAGS";

    public static final String KEY_SELECTED_TAGS = "KEY_SELECTED_TAGS";

    private SiteModel mSite;

    private String mTags;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12864)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12868)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(12866)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(12867)) {
                    mTags = getIntent().getStringExtra(KEY_TAGS);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12865)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12871)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(12869)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(12870)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12872)) {
            setContentView(R.layout.post_settings_tags_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(12873)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(12876)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(12874)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(12875)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12877)) {
            postponeEnterTransition();
        }
        if (!ListenerUtil.mutListener.listen(12878)) {
            showPostSettingsTagsFragment();
        }
    }

    private void showPostSettingsTagsFragment() {
        PostSettingsTagsFragment postSettingsTagsFragment = PostSettingsTagsFragment.newInstance(mSite, mTags);
        if (!ListenerUtil.mutListener.listen(12879)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, postSettingsTagsFragment, PostSettingsTagsFragment.TAG).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(12880)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(12881)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(12883)) {
            if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(12882)) {
                    saveAndFinish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(12884)) {
            saveAndFinish();
        }
        if (!ListenerUtil.mutListener.listen(12885)) {
            super.onBackPressed();
        }
    }

    private void saveAndFinish() {
        if (!ListenerUtil.mutListener.listen(12886)) {
            closeKeyboard();
        }
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(12887)) {
            bundle.putString(KEY_SELECTED_TAGS, mTags);
        }
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(12888)) {
            intent.putExtras(bundle);
        }
        if (!ListenerUtil.mutListener.listen(12889)) {
            setResult(RESULT_OK, intent);
        }
        if (!ListenerUtil.mutListener.listen(12890)) {
            finish();
        }
    }

    private void closeKeyboard() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(PostSettingsTagsFragment.TAG);
        if (!ListenerUtil.mutListener.listen(12892)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(12891)) {
                    ((PostSettingsTagsFragment) fragment).closeKeyboard();
                }
            }
        }
    }

    @Override
    public void onTagsSelected(@NonNull String selectedTags) {
        if (!ListenerUtil.mutListener.listen(12893)) {
            mTags = selectedTags;
        }
    }
}
