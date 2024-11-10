package org.wordpress.android.imageeditor.preview

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import org.wordpress.android.imageeditor.R

public class PreviewImageFragmentDirections private constructor() {
  private data class ActionPreviewFragmentToCropFragment(
    public val inputFilePath: String = "\"\"",
    public val outputFileExtension: String? = null,
    public val shouldReturnToPreviewScreen: Boolean = false
  ) : NavDirections {
    public override val actionId: Int = R.id.action_previewFragment_to_cropFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("inputFilePath", this.inputFilePath)
        result.putString("outputFileExtension", this.outputFileExtension)
        result.putBoolean("shouldReturnToPreviewScreen", this.shouldReturnToPreviewScreen)
        return result
      }
  }

  public companion object {
    public fun actionPreviewFragmentToCropFragment(
      inputFilePath: String = "\"\"",
      outputFileExtension: String? = null,
      shouldReturnToPreviewScreen: Boolean = false
    ): NavDirections = ActionPreviewFragmentToCropFragment(inputFilePath, outputFileExtension,
        shouldReturnToPreviewScreen)
  }
}
