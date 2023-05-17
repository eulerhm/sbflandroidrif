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
package com.google.android.gnd.ui.home.mapcontainer;

import com.google.android.gnd.model.feature.Point;
import com.google.android.gnd.rx.Nil;
import com.google.android.gnd.rx.annotations.Hot;
import com.google.android.gnd.ui.common.AbstractViewModel;
import com.google.android.gnd.ui.common.SharedViewModel;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javax.annotation.Nullable;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SharedViewModel
public class FeatureRepositionViewModel extends AbstractViewModel {

    @Hot
    private final Subject<Point> confirmButtonClicks = PublishSubject.create();

    @Hot
    private final Subject<Nil> cancelButtonClicks = PublishSubject.create();

    @Nullable
    private Point cameraTarget;

    @Inject
    FeatureRepositionViewModel() {
    }

    // TODO: Disable the confirm button until the map has not been moved
    public void onConfirmButtonClick() {
        if (!ListenerUtil.mutListener.listen(801)) {
            if (cameraTarget != null) {
                if (!ListenerUtil.mutListener.listen(800)) {
                    confirmButtonClicks.onNext(cameraTarget);
                }
            }
        }
    }

    public void onCancelButtonClick() {
        if (!ListenerUtil.mutListener.listen(802)) {
            cancelButtonClicks.onNext(Nil.NIL);
        }
    }

    @Hot
    public Observable<Point> getConfirmButtonClicks() {
        return confirmButtonClicks;
    }

    @Hot
    public Observable<Nil> getCancelButtonClicks() {
        return cancelButtonClicks;
    }

    public void onCameraMoved(Point newTarget) {
        if (!ListenerUtil.mutListener.listen(803)) {
            cameraTarget = newTarget;
        }
    }
}
