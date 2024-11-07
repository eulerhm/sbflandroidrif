/*
 * Copyright (C) 2014 The Android Open Source Project
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
package com.ichi2.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ichi2.anki.AnkiDroidApp;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A {@link android.preference.PreferenceActivity} which implements and proxies the necessary calls
 * to be used with AppCompat.
 *
 * This technique can be used with an {@link android.app.Activity} class, not just
 * {@link android.preference.PreferenceActivity}.
 */
// TODO Tracked in https://github.com/ankidroid/Anki-Android/issues/5019
@SuppressWarnings("deprecation")
public abstract class AppCompatPreferenceActivity extends android.preference.PreferenceActivity {

    private AppCompatDelegate mDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24941)) {
            getDelegate().installViewFactory();
        }
        if (!ListenerUtil.mutListener.listen(24942)) {
            getDelegate().onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(24943)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        if (!ListenerUtil.mutListener.listen(24944)) {
            super.attachBaseContext(AnkiDroidApp.updateContextWithLanguage(base));
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(24945)) {
            super.onPostCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(24946)) {
            getDelegate().onPostCreate(savedInstanceState);
        }
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        if (!ListenerUtil.mutListener.listen(24947)) {
            getDelegate().setSupportActionBar(toolbar);
        }
    }

    @Override
    @NonNull
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (!ListenerUtil.mutListener.listen(24948)) {
            getDelegate().setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if (!ListenerUtil.mutListener.listen(24949)) {
            getDelegate().setContentView(view);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (!ListenerUtil.mutListener.listen(24950)) {
            getDelegate().setContentView(view, params);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if (!ListenerUtil.mutListener.listen(24951)) {
            getDelegate().addContentView(view, params);
        }
    }

    @Override
    protected void onPostResume() {
        if (!ListenerUtil.mutListener.listen(24952)) {
            super.onPostResume();
        }
        if (!ListenerUtil.mutListener.listen(24953)) {
            getDelegate().onPostResume();
        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        if (!ListenerUtil.mutListener.listen(24954)) {
            super.onTitleChanged(title, color);
        }
        if (!ListenerUtil.mutListener.listen(24955)) {
            getDelegate().setTitle(title);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(24956)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(24957)) {
            getDelegate().onConfigurationChanged(newConfig);
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(24958)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(24959)) {
            getDelegate().onStop();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(24960)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(24961)) {
            getDelegate().onDestroy();
        }
    }

    public void invalidateOptionsMenu() {
        if (!ListenerUtil.mutListener.listen(24962)) {
            getDelegate().invalidateOptionsMenu();
        }
    }

    private AppCompatDelegate getDelegate() {
        if (!ListenerUtil.mutListener.listen(24964)) {
            if (mDelegate == null) {
                if (!ListenerUtil.mutListener.listen(24963)) {
                    mDelegate = AppCompatDelegate.create(this, null);
                }
            }
        }
        return mDelegate;
    }
}
