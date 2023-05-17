package com.ichi2.anki;

import com.ichi2.libanki.Sound;
import android.app.Activity;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.VideoView;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoPlayer extends Activity implements android.view.SurfaceHolder.Callback {

    VideoView mVideoView;

    String mPath;

    Sound mSoundPlayer;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12359)) {
            Timber.i("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(12360)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12361)) {
            setContentView(R.layout.video_player);
        }
        if (!ListenerUtil.mutListener.listen(12362)) {
            mPath = getIntent().getStringExtra("path");
        }
        if (!ListenerUtil.mutListener.listen(12363)) {
            Timber.i("Video Player intent had path: %s", mPath);
        }
        if (!ListenerUtil.mutListener.listen(12364)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (!ListenerUtil.mutListener.listen(12365)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        if (!ListenerUtil.mutListener.listen(12366)) {
            mVideoView = findViewById(R.id.video_surface);
        }
        if (!ListenerUtil.mutListener.listen(12367)) {
            mVideoView.getHolder().addCallback(this);
        }
        if (!ListenerUtil.mutListener.listen(12368)) {
            mSoundPlayer = new Sound();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!ListenerUtil.mutListener.listen(12369)) {
            Timber.i("surfaceCreated");
        }
        if (!ListenerUtil.mutListener.listen(12374)) {
            if (mPath == null) {
                if (!ListenerUtil.mutListener.listen(12370)) {
                    // #5911 - path shouldn't be null. I couldn't determine why this happens.
                    AnkiDroidApp.sendExceptionReport("Video: mPath was unexpectedly null", "VideoPlayer surfaceCreated");
                }
                if (!ListenerUtil.mutListener.listen(12371)) {
                    Timber.e("path was unexpectedly null");
                }
                if (!ListenerUtil.mutListener.listen(12372)) {
                    UIUtils.showThemedToast(this, getString(R.string.video_creation_error), true);
                }
                if (!ListenerUtil.mutListener.listen(12373)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12375)) {
            mSoundPlayer.playSound(mPath, mp -> {
                finish();
                MediaPlayer.OnCompletionListener originalListener = mSoundPlayer.getMediaCompletionListener();
                if (originalListener != null) {
                    originalListener.onCompletion(mp);
                }
            }, mVideoView, null);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (!ListenerUtil.mutListener.listen(12376)) {
            mSoundPlayer.stopSounds();
        }
        if (!ListenerUtil.mutListener.listen(12377)) {
            finish();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(12378)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(12379)) {
            mSoundPlayer.notifyConfigurationChanged(mVideoView);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(12380)) {
            super.onStop();
        }
    }
}
