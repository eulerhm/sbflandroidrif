/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.routines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.services.ContactService;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.APIConnector;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UpdateFeatureLevelRoutine implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(UpdateFeatureLevelRoutine.class);

    private static final Map<String, Long> checkedIdentities = new HashMap<>();

    public static void removeTimeCache(String identity) {
        synchronized (checkedIdentities) {
            if (!ListenerUtil.mutListener.listen(34879)) {
                checkedIdentities.remove(identity);
            }
        }
    }

    public static void removeTimeCache(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(34881)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(34880)) {
                    removeTimeCache(contactModel.getIdentity());
                }
            }
        }
    }

    public interface StatusResult {

        void onFinished(List<ContactModel> handledContacts);

        void onAbort();

        void onError(Exception x);
    }

    public interface Request {

        boolean requestToServer(int featureLevel);
    }

    private final ContactService contactService;

    private final APIConnector apiConnector;

    private String[] identities = null;

    private List<ContactModel> contactModels = null;

    private Request request = null;

    private final List<StatusResult> statusResults = new ArrayList<StatusResult>();

    private boolean abortOnCheckIdentitiesFailed = true;

    public UpdateFeatureLevelRoutine(@NonNull ContactService contactService, @NonNull APIConnector apiConnector, String[] identities, Request request) {
        this.contactService = contactService;
        this.apiConnector = apiConnector;
        if (!ListenerUtil.mutListener.listen(34882)) {
            this.identities = identities;
        }
        if (!ListenerUtil.mutListener.listen(34883)) {
            this.request = request;
        }
    }

    public UpdateFeatureLevelRoutine(@NonNull ContactService contactService, @NonNull APIConnector apiConnector, @Nullable List<ContactModel> contactModels) {
        this.contactService = contactService;
        this.apiConnector = apiConnector;
        if (!ListenerUtil.mutListener.listen(34884)) {
            this.contactModels = contactModels;
        }
    }

    public UpdateFeatureLevelRoutine abortOnCheckIdentitiesFailed(boolean abort) {
        if (!ListenerUtil.mutListener.listen(34885)) {
            this.abortOnCheckIdentitiesFailed = abort;
        }
        return this;
    }

    public UpdateFeatureLevelRoutine addStatusResult(StatusResult result) {
        if (!ListenerUtil.mutListener.listen(34886)) {
            this.statusResults.add(result);
        }
        return this;
    }

    @Override
    @WorkerThread
    public void run() {
        if (!ListenerUtil.mutListener.listen(34887)) {
            logger.info("Running...");
        }
        try {
            if (!ListenerUtil.mutListener.listen(34894)) {
                // get all identities
                if (this.contactModels == null) {
                    if (!ListenerUtil.mutListener.listen(34893)) {
                        if (this.request != null) {
                            if (!ListenerUtil.mutListener.listen(34892)) {
                                this.contactModels = Functional.filter(this.contactService.getByIdentities(this.identities), new IPredicateNonNull<ContactModel>() {

                                    @Override
                                    public boolean apply(@NonNull ContactModel type) {
                                        return request.requestToServer(type.getFeatureMask());
                                    }
                                });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(34891)) {
                                this.contactModels = this.contactService.getByIdentities(identities);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(34895)) {
                // remove "me" from list
                this.contactModels = Functional.filter(this.contactModels, new IPredicateNonNull<ContactModel>() {

                    @Override
                    public boolean apply(@NonNull ContactModel c) {
                        return !TestUtil.compare(c, contactService.getMe());
                    }
                });
            }
            // remove already checked identities
            final Calendar calendar = Calendar.getInstance();
            if (!ListenerUtil.mutListener.listen(34896)) {
                calendar.setTime(new Date());
            }
            final long nowTimestamp = calendar.getTimeInMillis();
            if (!ListenerUtil.mutListener.listen(34897)) {
                // leave the identity 1 hour in the cache!
                calendar.add(Calendar.HOUR, -1);
            }
            final long validTimestamp = calendar.getTimeInMillis();
            synchronized (checkedIdentities) {
                List<ContactModel> filteredList = Functional.filter(this.contactModels, new IPredicateNonNull<ContactModel>() {

                    @Override
                    public boolean apply(@NonNull ContactModel contactModel) {
                        if (!ListenerUtil.mutListener.listen(34904)) {
                            if ((ListenerUtil.mutListener.listen(34903) ? (checkedIdentities.containsKey(contactModel.getIdentity()) || (ListenerUtil.mutListener.listen(34902) ? (checkedIdentities.get(contactModel.getIdentity()) <= validTimestamp) : (ListenerUtil.mutListener.listen(34901) ? (checkedIdentities.get(contactModel.getIdentity()) > validTimestamp) : (ListenerUtil.mutListener.listen(34900) ? (checkedIdentities.get(contactModel.getIdentity()) < validTimestamp) : (ListenerUtil.mutListener.listen(34899) ? (checkedIdentities.get(contactModel.getIdentity()) != validTimestamp) : (ListenerUtil.mutListener.listen(34898) ? (checkedIdentities.get(contactModel.getIdentity()) == validTimestamp) : (checkedIdentities.get(contactModel.getIdentity()) >= validTimestamp))))))) : (checkedIdentities.containsKey(contactModel.getIdentity()) && (ListenerUtil.mutListener.listen(34902) ? (checkedIdentities.get(contactModel.getIdentity()) <= validTimestamp) : (ListenerUtil.mutListener.listen(34901) ? (checkedIdentities.get(contactModel.getIdentity()) > validTimestamp) : (ListenerUtil.mutListener.listen(34900) ? (checkedIdentities.get(contactModel.getIdentity()) < validTimestamp) : (ListenerUtil.mutListener.listen(34899) ? (checkedIdentities.get(contactModel.getIdentity()) != validTimestamp) : (ListenerUtil.mutListener.listen(34898) ? (checkedIdentities.get(contactModel.getIdentity()) == validTimestamp) : (checkedIdentities.get(contactModel.getIdentity()) >= validTimestamp))))))))) {
                                return false;
                            }
                        }
                        return true;
                    }
                });
                if (!ListenerUtil.mutListener.listen(34905)) {
                    logger.info("Running for {} entries", filteredList.size());
                }
                if (!ListenerUtil.mutListener.listen(34939)) {
                    if ((ListenerUtil.mutListener.listen(34910) ? (filteredList.size() >= 0) : (ListenerUtil.mutListener.listen(34909) ? (filteredList.size() <= 0) : (ListenerUtil.mutListener.listen(34908) ? (filteredList.size() < 0) : (ListenerUtil.mutListener.listen(34907) ? (filteredList.size() != 0) : (ListenerUtil.mutListener.listen(34906) ? (filteredList.size() == 0) : (filteredList.size() > 0))))))) {
                        String[] identities = new String[filteredList.size()];
                        if (!ListenerUtil.mutListener.listen(34919)) {
                            {
                                long _loopCounter260 = 0;
                                for (int n = 0; (ListenerUtil.mutListener.listen(34918) ? (n >= filteredList.size()) : (ListenerUtil.mutListener.listen(34917) ? (n <= filteredList.size()) : (ListenerUtil.mutListener.listen(34916) ? (n > filteredList.size()) : (ListenerUtil.mutListener.listen(34915) ? (n != filteredList.size()) : (ListenerUtil.mutListener.listen(34914) ? (n == filteredList.size()) : (n < filteredList.size())))))); n++) {
                                    ListenerUtil.loopListener.listen("_loopCounter260", ++_loopCounter260);
                                    if (!ListenerUtil.mutListener.listen(34913)) {
                                        identities[n] = filteredList.get(n).getIdentity();
                                    }
                                }
                            }
                        }
                        try {
                            Integer[] featureMasks = this.apiConnector.checkFeatureMask(identities);
                            if (!ListenerUtil.mutListener.listen(34936)) {
                                {
                                    long _loopCounter262 = 0;
                                    for (int n = 0; (ListenerUtil.mutListener.listen(34935) ? (n >= featureMasks.length) : (ListenerUtil.mutListener.listen(34934) ? (n <= featureMasks.length) : (ListenerUtil.mutListener.listen(34933) ? (n > featureMasks.length) : (ListenerUtil.mutListener.listen(34932) ? (n != featureMasks.length) : (ListenerUtil.mutListener.listen(34931) ? (n == featureMasks.length) : (n < featureMasks.length)))))); n++) {
                                        ListenerUtil.loopListener.listen("_loopCounter262", ++_loopCounter262);
                                        final Integer featureMask = featureMasks[n];
                                        if (!ListenerUtil.mutListener.listen(34925)) {
                                            if (featureMask == null) {
                                                if (!ListenerUtil.mutListener.listen(34924)) {
                                                    // Skip NULL values
                                                    logger.warn("Feature mask is null!");
                                                }
                                                continue;
                                            }
                                        }
                                        ContactModel model = filteredList.get(n);
                                        if (!ListenerUtil.mutListener.listen(34930)) {
                                            if ((ListenerUtil.mutListener.listen(34926) ? (model != null || model.getFeatureMask() != featureMask) : (model != null && model.getFeatureMask() != featureMask))) {
                                                final String identity = model.getIdentity();
                                                if (!ListenerUtil.mutListener.listen(34927)) {
                                                    model.setFeatureMask(featureMask);
                                                }
                                                if (!ListenerUtil.mutListener.listen(34928)) {
                                                    this.contactService.save(model);
                                                }
                                                if (!ListenerUtil.mutListener.listen(34929)) {
                                                    // update checked identities cache
                                                    checkedIdentities.put(identity, nowTimestamp);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception x) {
                            if (!ListenerUtil.mutListener.listen(34922)) {
                                // connection error
                                if (this.abortOnCheckIdentitiesFailed) {
                                    if (!ListenerUtil.mutListener.listen(34921)) {
                                        {
                                            long _loopCounter261 = 0;
                                            for (StatusResult result : statusResults) {
                                                ListenerUtil.loopListener.listen("_loopCounter261", ++_loopCounter261);
                                                if (!ListenerUtil.mutListener.listen(34920)) {
                                                    result.onAbort();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(34923)) {
                                logger.error("Error while setting feature mask", x);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34938)) {
                            {
                                long _loopCounter263 = 0;
                                for (StatusResult result : statusResults) {
                                    ListenerUtil.loopListener.listen("_loopCounter263", ++_loopCounter263);
                                    if (!ListenerUtil.mutListener.listen(34937)) {
                                        result.onFinished(this.contactModels);
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(34912)) {
                            {
                                long _loopCounter259 = 0;
                                for (StatusResult result : statusResults) {
                                    ListenerUtil.loopListener.listen("_loopCounter259", ++_loopCounter259);
                                    if (!ListenerUtil.mutListener.listen(34911)) {
                                        result.onFinished(this.contactModels);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(34888)) {
                logger.error("Error in run()", e);
            }
            if (!ListenerUtil.mutListener.listen(34890)) {
                {
                    long _loopCounter258 = 0;
                    for (StatusResult result : statusResults) {
                        ListenerUtil.loopListener.listen("_loopCounter258", ++_loopCounter258);
                        if (!ListenerUtil.mutListener.listen(34889)) {
                            result.onError(e);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(34940)) {
            logger.info("Done");
        }
    }
}
