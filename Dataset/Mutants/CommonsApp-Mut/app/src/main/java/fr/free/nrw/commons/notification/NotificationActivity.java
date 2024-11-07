package fr.free.nrw.commons.notification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.snackbar.Snackbar;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.notification.models.Notification;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.NetworkUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import kotlin.Unit;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationActivity extends BaseActivity {

    @BindView(R.id.listView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.container)
    RelativeLayout relativeLayout;

    @BindView(R.id.no_notification_background)
    ConstraintLayout no_notification;

    @BindView(R.id.no_notification_text)
    TextView noNotificationText;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    NotificationController controller;

    private static final String TAG_NOTIFICATION_WORKER_FRAGMENT = "NotificationWorkerFragment";

    private NotificationWorkerFragment mNotificationWorkerFragment;

    private NotificatinAdapter adapter;

    private List<Notification> notificationList;

    MenuItem notificationMenuItem;

    /**
     * Boolean isRead is true if this notification activity is for read section of notification.
     */
    private boolean isRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1485)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1486)) {
            isRead = getIntent().getStringExtra("title").equals("read");
        }
        if (!ListenerUtil.mutListener.listen(1487)) {
            setContentView(R.layout.activity_notification);
        }
        if (!ListenerUtil.mutListener.listen(1488)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(1489)) {
            mNotificationWorkerFragment = (NotificationWorkerFragment) getFragmentManager().findFragmentByTag(TAG_NOTIFICATION_WORKER_FRAGMENT);
        }
        if (!ListenerUtil.mutListener.listen(1490)) {
            initListView();
        }
        if (!ListenerUtil.mutListener.listen(1491)) {
            setPageTitle();
        }
        if (!ListenerUtil.mutListener.listen(1492)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(1493)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!ListenerUtil.mutListener.listen(1494)) {
            onBackPressed();
        }
        return true;
    }

    /**
     * If this is unread section of the notifications, removeNotification method
     *  Marks the notification as read,
     *  Removes the notification from unread,
     *  Displays the Snackbar.
     *
     * Otherwise returns (read section).
     *
     * @param notification
     */
    @SuppressLint("CheckResult")
    public void removeNotification(Notification notification) {
        if (!ListenerUtil.mutListener.listen(1495)) {
            if (isRead) {
                return;
            }
        }
        Disposable disposable = Observable.defer((Callable<ObservableSource<Boolean>>) () -> controller.markAsRead(notification)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(result -> {
            if (result) {
                notificationList.remove(notification);
                setItems(notificationList);
                adapter.notifyDataSetChanged();
                Snackbar snackbar = Snackbar.make(relativeLayout, getString(R.string.notification_mark_read), Snackbar.LENGTH_LONG);
                snackbar.show();
                if (notificationList.size() == 0) {
                    setEmptyView();
                    relativeLayout.setVisibility(View.GONE);
                    no_notification.setVisibility(View.VISIBLE);
                }
            } else {
                adapter.notifyDataSetChanged();
                setItems(notificationList);
                Toast.makeText(NotificationActivity.this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
            }
        }, throwable -> {
            Timber.e(throwable, "Error occurred while loading notifications");
            throwable.printStackTrace();
            ViewUtil.showShortSnackbar(relativeLayout, R.string.error_notifications);
            progressBar.setVisibility(View.GONE);
        });
        if (!ListenerUtil.mutListener.listen(1496)) {
            compositeDisposable.add(disposable);
        }
    }

    private void initListView() {
        if (!ListenerUtil.mutListener.listen(1497)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        DividerItemDecoration itemDecor = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        if (!ListenerUtil.mutListener.listen(1498)) {
            recyclerView.addItemDecoration(itemDecor);
        }
        if (!ListenerUtil.mutListener.listen(1501)) {
            if (isRead) {
                if (!ListenerUtil.mutListener.listen(1500)) {
                    refresh(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1499)) {
                    refresh(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1502)) {
            adapter = new NotificatinAdapter(item -> {
                Timber.d("Notification clicked %s", item.getLink());
                handleUrl(item.getLink());
                removeNotification(item);
                return Unit.INSTANCE;
            });
        }
        if (!ListenerUtil.mutListener.listen(1503)) {
            recyclerView.setAdapter(this.adapter);
        }
    }

    private void refresh(boolean archived) {
        if (!ListenerUtil.mutListener.listen(1507)) {
            if (!NetworkUtils.isInternetConnectionEstablished(this)) {
                if (!ListenerUtil.mutListener.listen(1505)) {
                    progressBar.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1506)) {
                    Snackbar.make(relativeLayout, R.string.no_internet, Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry, view -> refresh(archived)).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1504)) {
                    addNotifications(archived);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1508)) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1509)) {
            no_notification.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1510)) {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("CheckResult")
    private void addNotifications(boolean archived) {
        if (!ListenerUtil.mutListener.listen(1511)) {
            Timber.d("Add notifications");
        }
        if (!ListenerUtil.mutListener.listen(1516)) {
            if (mNotificationWorkerFragment == null) {
                if (!ListenerUtil.mutListener.listen(1514)) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1515)) {
                    compositeDisposable.add(controller.getNotifications(archived).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(notificationList -> {
                        Collections.reverse(notificationList);
                        Timber.d("Number of notifications is %d", notificationList.size());
                        this.notificationList = notificationList;
                        if (notificationList.size() == 0) {
                            setEmptyView();
                            relativeLayout.setVisibility(View.GONE);
                            no_notification.setVisibility(View.VISIBLE);
                        } else {
                            setItems(notificationList);
                        }
                        progressBar.setVisibility(View.GONE);
                    }, throwable -> {
                        Timber.e(throwable, "Error occurred while loading notifications");
                        ViewUtil.showShortSnackbar(relativeLayout, R.string.error_notifications);
                        progressBar.setVisibility(View.GONE);
                    }));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1512)) {
                    notificationList = mNotificationWorkerFragment.getNotificationList();
                }
                if (!ListenerUtil.mutListener.listen(1513)) {
                    setItems(notificationList);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(1517)) {
            inflater.inflate(R.menu.menu_notifications, menu);
        }
        if (!ListenerUtil.mutListener.listen(1518)) {
            notificationMenuItem = menu.findItem(R.id.archived);
        }
        if (!ListenerUtil.mutListener.listen(1519)) {
            setMenuItemTitle();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch(item.getItemId()) {
            case R.id.archived:
                if (!ListenerUtil.mutListener.listen(1522)) {
                    if (item.getTitle().equals(getString(R.string.menu_option_read))) {
                        if (!ListenerUtil.mutListener.listen(1521)) {
                            NotificationActivity.startYourself(NotificationActivity.this, "read");
                        }
                    } else if (item.getTitle().equals(getString(R.string.menu_option_unread))) {
                        if (!ListenerUtil.mutListener.listen(1520)) {
                            onBackPressed();
                        }
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleUrl(String url) {
        if (!ListenerUtil.mutListener.listen(1524)) {
            if ((ListenerUtil.mutListener.listen(1523) ? (url == null && url.equals("")) : (url == null || url.equals("")))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1525)) {
            Utils.handleWebUrl(this, Uri.parse(url));
        }
    }

    private void setItems(List<Notification> notificationList) {
        if (!ListenerUtil.mutListener.listen(1531)) {
            if ((ListenerUtil.mutListener.listen(1526) ? (notificationList == null && notificationList.isEmpty()) : (notificationList == null || notificationList.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(1527)) {
                    ViewUtil.showShortSnackbar(relativeLayout, R.string.no_notifications);
                }
                if (!ListenerUtil.mutListener.listen(1528)) {
                    /*progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);*/
                    relativeLayout.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1529)) {
                    setEmptyView();
                }
                if (!ListenerUtil.mutListener.listen(1530)) {
                    no_notification.setVisibility(View.VISIBLE);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1532)) {
            relativeLayout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1533)) {
            no_notification.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1534)) {
            adapter.setItems(notificationList);
        }
    }

    public static void startYourself(Context context, String title) {
        Intent intent = new Intent(context, NotificationActivity.class);
        if (!ListenerUtil.mutListener.listen(1535)) {
            intent.putExtra("title", title);
        }
        if (!ListenerUtil.mutListener.listen(1536)) {
            context.startActivity(intent);
        }
    }

    private void setPageTitle() {
        if (!ListenerUtil.mutListener.listen(1540)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(1539)) {
                    if (isRead) {
                        if (!ListenerUtil.mutListener.listen(1538)) {
                            getSupportActionBar().setTitle(R.string.read_notifications);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1537)) {
                            getSupportActionBar().setTitle(R.string.notifications);
                        }
                    }
                }
            }
        }
    }

    private void setEmptyView() {
        if (!ListenerUtil.mutListener.listen(1543)) {
            if (isRead) {
                if (!ListenerUtil.mutListener.listen(1542)) {
                    noNotificationText.setText(R.string.no_read_notification);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1541)) {
                    noNotificationText.setText(R.string.no_notification);
                }
            }
        }
    }

    private void setMenuItemTitle() {
        if (!ListenerUtil.mutListener.listen(1546)) {
            if (isRead) {
                if (!ListenerUtil.mutListener.listen(1545)) {
                    notificationMenuItem.setTitle(R.string.menu_option_unread);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1544)) {
                    notificationMenuItem.setTitle(R.string.menu_option_read);
                }
            }
        }
    }
}
