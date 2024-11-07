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
package com.google.android.gnd.ui

import android.content.Context
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import com.google.android.gnd.BaseHiltTest
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import org.junit.Before
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.gnd.R
import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
class MarkerIconFactoryTest : BaseHiltTest() {
    @Inject
    @ApplicationContext
    lateinit var context: Context

    @Inject
    lateinit var markerIconFactory: MarkerIconFactory
    private var markerUnscaledWidth = 0
    private var markerUnscaledHeight = 0

    @Before
    override fun setUp() {
        super.setUp()
        val outline = AppCompatResources.getDrawable(context, R.drawable.ic_marker_outline)
        markerUnscaledWidth = outline!!.intrinsicWidth
        markerUnscaledHeight = outline.intrinsicHeight
    }

    @Test
    fun markerBitmap_zoomedOut_scaleIsSetCorrectly() {
        val bitmap = markerIconFactory.getMarkerBitmap(
            Color.BLUE,
            MapContainerViewModel.ZOOM_LEVEL_THRESHOLD - 0.1f
        )

        val scale =
            ResourcesCompat.getFloat(context.resources, R.dimen.marker_bitmap_default_scale)
        verifyBitmapScale(bitmap, scale)
    }

    @Test
    fun markerBitmap_zoomedIn_scaleIsSetCorrectly() {
        val bitmap = markerIconFactory.getMarkerBitmap(
            Color.BLUE,
            MapContainerViewModel.ZOOM_LEVEL_THRESHOLD
        )

        val scale =
            ResourcesCompat.getFloat(context.resources, R.dimen.marker_bitmap_zoomed_scale)
        verifyBitmapScale(bitmap, scale)
    }

    private fun verifyBitmapScale(bitmap: Bitmap, scale: Float) {
        val expectedWidth = (markerUnscaledWidth * scale).toInt()
        val expectedHeight = (markerUnscaledHeight * scale).toInt()
        assertThat(bitmap.width).isEqualTo(expectedWidth)
        assertThat(bitmap.height).isEqualTo(expectedHeight)
    }
}
