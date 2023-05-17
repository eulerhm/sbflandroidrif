package net.programmierecke.radiodroid2.players.exoplayer;

import androidx.annotation.NonNull;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RadioDataSourceFactory implements DataSource.Factory {

    private OkHttpClient httpClient;

    private final TransferListener transferListener;

    private IcyDataSource.IcyDataSourceListener dataSourceListener;

    private long retryTimeout;

    private long retryDelay;

    public RadioDataSourceFactory(@NonNull OkHttpClient httpClient, @NonNull TransferListener transferListener, @NonNull IcyDataSource.IcyDataSourceListener dataSourceListener, long retryTimeout, long retryDelay) {
        if (!ListenerUtil.mutListener.listen(752)) {
            this.httpClient = httpClient;
        }
        this.transferListener = transferListener;
        if (!ListenerUtil.mutListener.listen(753)) {
            this.dataSourceListener = dataSourceListener;
        }
        if (!ListenerUtil.mutListener.listen(754)) {
            this.retryTimeout = retryTimeout;
        }
        if (!ListenerUtil.mutListener.listen(755)) {
            this.retryDelay = retryDelay;
        }
    }

    @Override
    public DataSource createDataSource() {
        return new IcyDataSource(httpClient, transferListener, dataSourceListener);
    }
}
