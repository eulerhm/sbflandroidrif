/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gnd.ui.tos;

import androidx.lifecycle.MutableLiveData;
import com.google.android.gnd.repository.TermsOfServiceRepository;
import com.google.android.gnd.rx.annotations.Hot;
import com.google.android.gnd.ui.common.AbstractViewModel;
import com.google.android.gnd.ui.common.Navigator;
import com.google.android.gnd.ui.home.HomeScreenFragmentDirections;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TermsOfServiceViewModel extends AbstractViewModel {

    private final Navigator navigator;

    private String termsOfServiceText = "";

    @Hot(replays = true)
    public final MutableLiveData<Boolean> agreeCheckboxChecked = new MutableLiveData<>();

    private final TermsOfServiceRepository termsOfServiceRepository;

    @Inject
    public TermsOfServiceViewModel(Navigator navigator, TermsOfServiceRepository termsOfServiceRepository) {
        this.navigator = navigator;
        this.termsOfServiceRepository = termsOfServiceRepository;
    }

    public String getTermsOfServiceText() {
        return termsOfServiceText;
    }

    public void setTermsOfServiceText(String termsOfServiceText) {
        if (!ListenerUtil.mutListener.listen(646)) {
            this.termsOfServiceText = termsOfServiceText;
        }
    }

    public void onButtonClicked() {
        if (!ListenerUtil.mutListener.listen(647)) {
            termsOfServiceRepository.setTermsOfServiceAccepted(true);
        }
        if (!ListenerUtil.mutListener.listen(648)) {
            navigator.navigate(HomeScreenFragmentDirections.showHomeScreen());
        }
    }
}
