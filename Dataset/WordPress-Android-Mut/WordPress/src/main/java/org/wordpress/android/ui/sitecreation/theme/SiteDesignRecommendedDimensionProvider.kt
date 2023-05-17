package org.wordpress.android.ui.sitecreation.theme

import org.wordpress.android.R.dimen
import org.wordpress.android.ui.layoutpicker.ThumbDimensionProvider
import org.wordpress.android.viewmodel.ContextProvider
import javax.inject.Inject

class SiteDesignRecommendedDimensionProvider @Inject constructor(private val contextProvider: ContextProvider) :
        ThumbDimensionProvider {
    override val previewWidth: Int
        get() = contextProvider.getContext().resources.getDimensionPixelSize(dimen.hpp_recommended_card_width)

    override val previewHeight: Int
        get() = contextProvider.getContext().resources.getDimensionPixelSize(dimen.hpp_recommended_card_height)

    override val rowHeight: Int
        get() = contextProvider.getContext().resources.getDimensionPixelSize(dimen.hpp_recommended_row_height)
}
