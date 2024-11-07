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
package ch.threema.app.workers;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ContactUtil;
import ch.threema.client.APIConnector;
import ch.threema.client.IdentityState;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdentityStatesWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(IdentityStatesWorker.class);

    private ContactService contactService;

    private APIConnector apiConnector;

    private PreferenceService preferenceService;

    public IdentityStatesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        try {
            ServiceManager serviceManager = ThreemaApplication.getServiceManager();
            if (!ListenerUtil.mutListener.listen(65071)) {
                contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(65072)) {
                apiConnector = serviceManager.getAPIConnector();
            }
            if (!ListenerUtil.mutListener.listen(65073)) {
                preferenceService = serviceManager.getPreferenceService();
            }
        } catch (Exception e) {
        }
    }

    @NonNull
    @Override
    public Result doWork() {
        if (!ListenerUtil.mutListener.listen(65074)) {
            logger.info("@@@@ Starting IdentityStatesWorker");
        }
        if (!ListenerUtil.mutListener.listen(65076)) {
            if (this.contactService == null) {
                if (!ListenerUtil.mutListener.listen(65075)) {
                    logger.info("ContactService not available while updating IdentityStates");
                }
                return Result.failure();
            }
        }
        // get ALL, no filter set!
        final List<ContactModel> contactModelList = this.contactService.find(new ContactService.Filter() {

            @Override
            public ContactModel.State[] states() {
                // do not process invalid or deleted ids
                return new ContactModel.State[] { ContactModel.State.ACTIVE, ContactModel.State.INACTIVE };
            }

            @Override
            public Integer requiredFeature() {
                return null;
            }

            @Override
            public Boolean fetchMissingFeatureLevel() {
                return null;
            }

            @Override
            public Boolean includeMyself() {
                return true;
            }

            @Override
            public Boolean includeHidden() {
                return true;
            }
        });
        if (!ListenerUtil.mutListener.listen(65126)) {
            if ((ListenerUtil.mutListener.listen(65082) ? (contactModelList != null || (ListenerUtil.mutListener.listen(65081) ? (contactModelList.size() >= 0) : (ListenerUtil.mutListener.listen(65080) ? (contactModelList.size() <= 0) : (ListenerUtil.mutListener.listen(65079) ? (contactModelList.size() < 0) : (ListenerUtil.mutListener.listen(65078) ? (contactModelList.size() != 0) : (ListenerUtil.mutListener.listen(65077) ? (contactModelList.size() == 0) : (contactModelList.size() > 0))))))) : (contactModelList != null && (ListenerUtil.mutListener.listen(65081) ? (contactModelList.size() >= 0) : (ListenerUtil.mutListener.listen(65080) ? (contactModelList.size() <= 0) : (ListenerUtil.mutListener.listen(65079) ? (contactModelList.size() < 0) : (ListenerUtil.mutListener.listen(65078) ? (contactModelList.size() != 0) : (ListenerUtil.mutListener.listen(65077) ? (contactModelList.size() == 0) : (contactModelList.size() > 0))))))))) {
                // create identity array
                String[] identities = new String[contactModelList.size()];
                Map<String, Integer> contactMap = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(65090)) {
                    {
                        long _loopCounter790 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(65089) ? (n >= contactModelList.size()) : (ListenerUtil.mutListener.listen(65088) ? (n <= contactModelList.size()) : (ListenerUtil.mutListener.listen(65087) ? (n > contactModelList.size()) : (ListenerUtil.mutListener.listen(65086) ? (n != contactModelList.size()) : (ListenerUtil.mutListener.listen(65085) ? (n == contactModelList.size()) : (n < contactModelList.size())))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter790", ++_loopCounter790);
                            ContactModel contactModel = contactModelList.get(n);
                            if (!ListenerUtil.mutListener.listen(65083)) {
                                contactMap.put(contactModel.getIdentity(), n);
                            }
                            if (!ListenerUtil.mutListener.listen(65084)) {
                                identities[n] = contactModel.getIdentity();
                            }
                        }
                    }
                }
                try {
                    APIConnector.CheckIdentityStatesResult res = this.apiConnector.checkIdentityStates(identities);
                    if (!ListenerUtil.mutListener.listen(65092)) {
                        logger.trace("identityStates checkInterval = " + res.checkInterval);
                    }
                    if (!ListenerUtil.mutListener.listen(65118)) {
                        {
                            long _loopCounter791 = 0;
                            for (int n = 0; (ListenerUtil.mutListener.listen(65117) ? (n >= res.identities.length) : (ListenerUtil.mutListener.listen(65116) ? (n <= res.identities.length) : (ListenerUtil.mutListener.listen(65115) ? (n > res.identities.length) : (ListenerUtil.mutListener.listen(65114) ? (n != res.identities.length) : (ListenerUtil.mutListener.listen(65113) ? (n == res.identities.length) : (n < res.identities.length)))))); n++) {
                                ListenerUtil.loopListener.listen("_loopCounter791", ++_loopCounter791);
                                String identity = res.identities[n];
                                int state = res.states[n];
                                Integer featureMask = res.featureMasks[n];
                                if (!ListenerUtil.mutListener.listen(65112)) {
                                    if (contactMap.containsKey(identity)) {
                                        ContactModel contactModel = contactModelList.get(contactMap.get(identity));
                                        if (!ListenerUtil.mutListener.listen(65111)) {
                                            if (contactModel != null) {
                                                ContactModel.State contactModelState = null;
                                                if (!ListenerUtil.mutListener.listen(65096)) {
                                                    switch(state) {
                                                        case IdentityState.ACTIVE:
                                                            if (!ListenerUtil.mutListener.listen(65093)) {
                                                                contactModelState = ContactModel.State.ACTIVE;
                                                            }
                                                            break;
                                                        case IdentityState.INACTIVE:
                                                            if (!ListenerUtil.mutListener.listen(65094)) {
                                                                contactModelState = ContactModel.State.INACTIVE;
                                                            }
                                                            break;
                                                        case IdentityState.INVALID:
                                                            if (!ListenerUtil.mutListener.listen(65095)) {
                                                                contactModelState = ContactModel.State.INVALID;
                                                            }
                                                            break;
                                                    }
                                                }
                                                boolean save = false;
                                                if (!ListenerUtil.mutListener.listen(65099)) {
                                                    if (contactModel.getType() != res.types[n]) {
                                                        if (!ListenerUtil.mutListener.listen(65097)) {
                                                            // Set new type
                                                            contactModel.setType(res.types[n]);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(65098)) {
                                                            save = true;
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(65104)) {
                                                    if (featureMask != null) {
                                                        if (!ListenerUtil.mutListener.listen(65103)) {
                                                            if (contactModel.getFeatureMask() != featureMask) {
                                                                if (!ListenerUtil.mutListener.listen(65101)) {
                                                                    contactModel.setFeatureMask(featureMask);
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(65102)) {
                                                                    save = true;
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(65100)) {
                                                            logger.warn("Feature mask is null!");
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(65108)) {
                                                    if (ContactUtil.allowedChangeToState(contactModel, contactModelState)) {
                                                        if (!ListenerUtil.mutListener.listen(65105)) {
                                                            logger.debug("update {} with state {}", identity, contactModelState);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(65106)) {
                                                            contactModel.setState(contactModelState);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(65107)) {
                                                            save = true;
                                                        }
                                                    }
                                                }
                                                if (!ListenerUtil.mutListener.listen(65110)) {
                                                    if (save) {
                                                        if (!ListenerUtil.mutListener.listen(65109)) {
                                                            this.contactService.save(contactModel);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(65125)) {
                        if ((ListenerUtil.mutListener.listen(65123) ? (res.checkInterval >= 0) : (ListenerUtil.mutListener.listen(65122) ? (res.checkInterval <= 0) : (ListenerUtil.mutListener.listen(65121) ? (res.checkInterval < 0) : (ListenerUtil.mutListener.listen(65120) ? (res.checkInterval != 0) : (ListenerUtil.mutListener.listen(65119) ? (res.checkInterval == 0) : (res.checkInterval > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(65124)) {
                                // schedule next interval
                                this.preferenceService.setRoutineInterval(getApplicationContext().getString(R.string.preferences__identity_states_check_interval), res.checkInterval);
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(65091)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65127)) {
            logger.debug("finished");
        }
        return Result.success();
    }

    @Override
    public void onStopped() {
        if (!ListenerUtil.mutListener.listen(65128)) {
            logger.info("@@@@ Worker has been stopped.");
        }
        if (!ListenerUtil.mutListener.listen(65129)) {
            super.onStopped();
        }
    }
}
