package fr.free.nrw.commons.quiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.WelcomeActivity;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import fr.free.nrw.commons.utils.DialogUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * fetches the number of images uploaded and number of images reverted.
 * Then it calculates the percentage of the images reverted
 * if the percentage of images reverted after last quiz exceeds 50% and number of images uploaded is
 * greater than 50, then quiz is popped up
 */
@Singleton
public class QuizChecker {

    private int revertCount;

    private int totalUploadCount;

    private boolean isRevertCountFetched;

    private boolean isUploadCountFetched;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final SessionManager sessionManager;

    private final OkHttpJsonApiClient okHttpJsonApiClient;

    private final JsonKvStore revertKvStore;

    private static final int UPLOAD_COUNT_THRESHOLD = 5;

    private static final String REVERT_PERCENTAGE_FOR_MESSAGE = "50%";

    private final String REVERT_SHARED_PREFERENCE = "revertCount";

    private final String UPLOAD_SHARED_PREFERENCE = "uploadCount";

    /**
     * constructor to set the parameters for quiz
     * @param sessionManager
     * @param okHttpJsonApiClient
     */
    @Inject
    public QuizChecker(SessionManager sessionManager, OkHttpJsonApiClient okHttpJsonApiClient, @Named("default_preferences") JsonKvStore revertKvStore) {
        this.sessionManager = sessionManager;
        this.okHttpJsonApiClient = okHttpJsonApiClient;
        this.revertKvStore = revertKvStore;
    }

    public void initQuizCheck(Activity activity) {
        if (!ListenerUtil.mutListener.listen(1994)) {
            calculateRevertParameterAndShowQuiz(activity);
        }
    }

    public void cleanup() {
        if (!ListenerUtil.mutListener.listen(1995)) {
            compositeDisposable.clear();
        }
    }

    /**
     * to fet the total number of images uploaded
     */
    private void setUploadCount() {
        if (!ListenerUtil.mutListener.listen(1996)) {
            compositeDisposable.add(okHttpJsonApiClient.getUploadCount(sessionManager.getUserName()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::setTotalUploadCount, t -> Timber.e(t, "Fetching upload count failed")));
        }
    }

    /**
     * set the sub Title of Contibutions Activity and
     * call function to check for quiz
     * @param uploadCount user's upload count
     */
    private void setTotalUploadCount(int uploadCount) {
        if (!ListenerUtil.mutListener.listen(2001)) {
            totalUploadCount = (ListenerUtil.mutListener.listen(2000) ? (uploadCount % revertKvStore.getInt(UPLOAD_SHARED_PREFERENCE, 0)) : (ListenerUtil.mutListener.listen(1999) ? (uploadCount / revertKvStore.getInt(UPLOAD_SHARED_PREFERENCE, 0)) : (ListenerUtil.mutListener.listen(1998) ? (uploadCount * revertKvStore.getInt(UPLOAD_SHARED_PREFERENCE, 0)) : (ListenerUtil.mutListener.listen(1997) ? (uploadCount + revertKvStore.getInt(UPLOAD_SHARED_PREFERENCE, 0)) : (uploadCount - revertKvStore.getInt(UPLOAD_SHARED_PREFERENCE, 0))))));
        }
        if (!ListenerUtil.mutListener.listen(2009)) {
            if ((ListenerUtil.mutListener.listen(2006) ? (totalUploadCount >= 0) : (ListenerUtil.mutListener.listen(2005) ? (totalUploadCount <= 0) : (ListenerUtil.mutListener.listen(2004) ? (totalUploadCount > 0) : (ListenerUtil.mutListener.listen(2003) ? (totalUploadCount != 0) : (ListenerUtil.mutListener.listen(2002) ? (totalUploadCount == 0) : (totalUploadCount < 0))))))) {
                if (!ListenerUtil.mutListener.listen(2007)) {
                    totalUploadCount = 0;
                }
                if (!ListenerUtil.mutListener.listen(2008)) {
                    revertKvStore.putInt(UPLOAD_SHARED_PREFERENCE, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2010)) {
            isUploadCountFetched = true;
        }
    }

    /**
     * To call the API to get reverts count in form of JSONObject
     */
    private void setRevertCount() {
        if (!ListenerUtil.mutListener.listen(2011)) {
            compositeDisposable.add(okHttpJsonApiClient.getAchievements(sessionManager.getUserName()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(response -> {
                if (response != null) {
                    setRevertParameter(response.getDeletedUploads());
                }
            }, throwable -> Timber.e(throwable, "Fetching feedback failed")));
        }
    }

    /**
     * to calculate the number of images reverted after previous quiz
     * @param revertCountFetched count of deleted uploads
     */
    private void setRevertParameter(int revertCountFetched) {
        if (!ListenerUtil.mutListener.listen(2016)) {
            revertCount = (ListenerUtil.mutListener.listen(2015) ? (revertCountFetched % revertKvStore.getInt(REVERT_SHARED_PREFERENCE, 0)) : (ListenerUtil.mutListener.listen(2014) ? (revertCountFetched / revertKvStore.getInt(REVERT_SHARED_PREFERENCE, 0)) : (ListenerUtil.mutListener.listen(2013) ? (revertCountFetched * revertKvStore.getInt(REVERT_SHARED_PREFERENCE, 0)) : (ListenerUtil.mutListener.listen(2012) ? (revertCountFetched + revertKvStore.getInt(REVERT_SHARED_PREFERENCE, 0)) : (revertCountFetched - revertKvStore.getInt(REVERT_SHARED_PREFERENCE, 0))))));
        }
        if (!ListenerUtil.mutListener.listen(2024)) {
            if ((ListenerUtil.mutListener.listen(2021) ? (revertCount >= 0) : (ListenerUtil.mutListener.listen(2020) ? (revertCount <= 0) : (ListenerUtil.mutListener.listen(2019) ? (revertCount > 0) : (ListenerUtil.mutListener.listen(2018) ? (revertCount != 0) : (ListenerUtil.mutListener.listen(2017) ? (revertCount == 0) : (revertCount < 0))))))) {
                if (!ListenerUtil.mutListener.listen(2022)) {
                    revertCount = 0;
                }
                if (!ListenerUtil.mutListener.listen(2023)) {
                    revertKvStore.putInt(REVERT_SHARED_PREFERENCE, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2025)) {
            isRevertCountFetched = true;
        }
    }

    /**
     * to check whether the criterion to call quiz is satisfied
     */
    private void calculateRevertParameterAndShowQuiz(Activity activity) {
        if (!ListenerUtil.mutListener.listen(2026)) {
            setUploadCount();
        }
        if (!ListenerUtil.mutListener.listen(2027)) {
            setRevertCount();
        }
        if (!ListenerUtil.mutListener.listen(2041)) {
            if ((ListenerUtil.mutListener.listen(2038) ? ((ListenerUtil.mutListener.listen(2032) ? (revertCount >= 0) : (ListenerUtil.mutListener.listen(2031) ? (revertCount <= 0) : (ListenerUtil.mutListener.listen(2030) ? (revertCount > 0) : (ListenerUtil.mutListener.listen(2029) ? (revertCount != 0) : (ListenerUtil.mutListener.listen(2028) ? (revertCount == 0) : (revertCount < 0)))))) && (ListenerUtil.mutListener.listen(2037) ? (totalUploadCount >= 0) : (ListenerUtil.mutListener.listen(2036) ? (totalUploadCount <= 0) : (ListenerUtil.mutListener.listen(2035) ? (totalUploadCount > 0) : (ListenerUtil.mutListener.listen(2034) ? (totalUploadCount != 0) : (ListenerUtil.mutListener.listen(2033) ? (totalUploadCount == 0) : (totalUploadCount < 0))))))) : ((ListenerUtil.mutListener.listen(2032) ? (revertCount >= 0) : (ListenerUtil.mutListener.listen(2031) ? (revertCount <= 0) : (ListenerUtil.mutListener.listen(2030) ? (revertCount > 0) : (ListenerUtil.mutListener.listen(2029) ? (revertCount != 0) : (ListenerUtil.mutListener.listen(2028) ? (revertCount == 0) : (revertCount < 0)))))) || (ListenerUtil.mutListener.listen(2037) ? (totalUploadCount >= 0) : (ListenerUtil.mutListener.listen(2036) ? (totalUploadCount <= 0) : (ListenerUtil.mutListener.listen(2035) ? (totalUploadCount > 0) : (ListenerUtil.mutListener.listen(2034) ? (totalUploadCount != 0) : (ListenerUtil.mutListener.listen(2033) ? (totalUploadCount == 0) : (totalUploadCount < 0))))))))) {
                if (!ListenerUtil.mutListener.listen(2039)) {
                    revertKvStore.putInt(REVERT_SHARED_PREFERENCE, 0);
                }
                if (!ListenerUtil.mutListener.listen(2040)) {
                    revertKvStore.putInt(UPLOAD_SHARED_PREFERENCE, 0);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2064)) {
            if ((ListenerUtil.mutListener.listen(2062) ? ((ListenerUtil.mutListener.listen(2048) ? ((ListenerUtil.mutListener.listen(2042) ? (isRevertCountFetched || isUploadCountFetched) : (isRevertCountFetched && isUploadCountFetched)) || (ListenerUtil.mutListener.listen(2047) ? (totalUploadCount <= UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2046) ? (totalUploadCount > UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2045) ? (totalUploadCount < UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2044) ? (totalUploadCount != UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2043) ? (totalUploadCount == UPLOAD_COUNT_THRESHOLD) : (totalUploadCount >= UPLOAD_COUNT_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(2042) ? (isRevertCountFetched || isUploadCountFetched) : (isRevertCountFetched && isUploadCountFetched)) && (ListenerUtil.mutListener.listen(2047) ? (totalUploadCount <= UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2046) ? (totalUploadCount > UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2045) ? (totalUploadCount < UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2044) ? (totalUploadCount != UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2043) ? (totalUploadCount == UPLOAD_COUNT_THRESHOLD) : (totalUploadCount >= UPLOAD_COUNT_THRESHOLD)))))))) || (ListenerUtil.mutListener.listen(2061) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) <= 50) : (ListenerUtil.mutListener.listen(2060) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) > 50) : (ListenerUtil.mutListener.listen(2059) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) < 50) : (ListenerUtil.mutListener.listen(2058) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) != 50) : (ListenerUtil.mutListener.listen(2057) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) == 50) : ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) >= 50))))))) : ((ListenerUtil.mutListener.listen(2048) ? ((ListenerUtil.mutListener.listen(2042) ? (isRevertCountFetched || isUploadCountFetched) : (isRevertCountFetched && isUploadCountFetched)) || (ListenerUtil.mutListener.listen(2047) ? (totalUploadCount <= UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2046) ? (totalUploadCount > UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2045) ? (totalUploadCount < UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2044) ? (totalUploadCount != UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2043) ? (totalUploadCount == UPLOAD_COUNT_THRESHOLD) : (totalUploadCount >= UPLOAD_COUNT_THRESHOLD))))))) : ((ListenerUtil.mutListener.listen(2042) ? (isRevertCountFetched || isUploadCountFetched) : (isRevertCountFetched && isUploadCountFetched)) && (ListenerUtil.mutListener.listen(2047) ? (totalUploadCount <= UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2046) ? (totalUploadCount > UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2045) ? (totalUploadCount < UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2044) ? (totalUploadCount != UPLOAD_COUNT_THRESHOLD) : (ListenerUtil.mutListener.listen(2043) ? (totalUploadCount == UPLOAD_COUNT_THRESHOLD) : (totalUploadCount >= UPLOAD_COUNT_THRESHOLD)))))))) && (ListenerUtil.mutListener.listen(2061) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) <= 50) : (ListenerUtil.mutListener.listen(2060) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) > 50) : (ListenerUtil.mutListener.listen(2059) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) < 50) : (ListenerUtil.mutListener.listen(2058) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) != 50) : (ListenerUtil.mutListener.listen(2057) ? ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) == 50) : ((ListenerUtil.mutListener.listen(2056) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) % totalUploadCount) : (ListenerUtil.mutListener.listen(2055) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) * totalUploadCount) : (ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) - totalUploadCount) : (ListenerUtil.mutListener.listen(2053) ? (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) + totalUploadCount) : (((ListenerUtil.mutListener.listen(2052) ? (revertCount % 100) : (ListenerUtil.mutListener.listen(2051) ? (revertCount / 100) : (ListenerUtil.mutListener.listen(2050) ? (revertCount - 100) : (ListenerUtil.mutListener.listen(2049) ? (revertCount + 100) : (revertCount * 100)))))) / totalUploadCount))))) >= 50))))))))) {
                if (!ListenerUtil.mutListener.listen(2063)) {
                    callQuiz(activity);
                }
            }
        }
    }

    /**
     * Alert which prompts to quiz
     */
    @SuppressLint("StringFormatInvalid")
    private void callQuiz(Activity activity) {
        if (!ListenerUtil.mutListener.listen(2065)) {
            DialogUtil.showAlertDialog(activity, activity.getString(R.string.quiz), activity.getString(R.string.quiz_alert_message, REVERT_PERCENTAGE_FOR_MESSAGE), activity.getString(R.string.about_translate_proceed), activity.getString(android.R.string.cancel), () -> startQuizActivity(activity), null);
        }
    }

    private void startQuizActivity(Activity activity) {
        int newRevetSharedPrefs = revertCount + revertKvStore.getInt(REVERT_SHARED_PREFERENCE, 0);
        if (!ListenerUtil.mutListener.listen(2066)) {
            revertKvStore.putInt(REVERT_SHARED_PREFERENCE, newRevetSharedPrefs);
        }
        int newUploadCount = totalUploadCount + revertKvStore.getInt(UPLOAD_SHARED_PREFERENCE, 0);
        if (!ListenerUtil.mutListener.listen(2067)) {
            revertKvStore.putInt(UPLOAD_SHARED_PREFERENCE, newUploadCount);
        }
        Intent i = new Intent(activity, WelcomeActivity.class);
        if (!ListenerUtil.mutListener.listen(2068)) {
            i.putExtra("isQuiz", true);
        }
        if (!ListenerUtil.mutListener.listen(2069)) {
            activity.startActivity(i);
        }
    }
}
