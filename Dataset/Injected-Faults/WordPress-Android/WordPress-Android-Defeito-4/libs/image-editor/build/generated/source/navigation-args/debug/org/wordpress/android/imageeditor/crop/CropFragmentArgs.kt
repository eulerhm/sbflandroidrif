package org.wordpress.android.imageeditor.crop

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Boolean
import kotlin.String
import kotlin.jvm.JvmStatic

public data class CropFragmentArgs(
  public val inputFilePath: String = "\"\"",
  public val outputFileExtension: String? = null,
  public val shouldReturnToPreviewScreen: Boolean = false
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("inputFilePath", this.inputFilePath)
    result.putString("outputFileExtension", this.outputFileExtension)
    result.putBoolean("shouldReturnToPreviewScreen", this.shouldReturnToPreviewScreen)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("inputFilePath", this.inputFilePath)
    result.set("outputFileExtension", this.outputFileExtension)
    result.set("shouldReturnToPreviewScreen", this.shouldReturnToPreviewScreen)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CropFragmentArgs {
      bundle.setClassLoader(CropFragmentArgs::class.java.classLoader)
      val __inputFilePath : String?
      if (bundle.containsKey("inputFilePath")) {
        __inputFilePath = bundle.getString("inputFilePath")
        if (__inputFilePath == null) {
          throw IllegalArgumentException("Argument \"inputFilePath\" is marked as non-null but was passed a null value.")
        }
      } else {
        __inputFilePath = "\"\""
      }
      val __outputFileExtension : String?
      if (bundle.containsKey("outputFileExtension")) {
        __outputFileExtension = bundle.getString("outputFileExtension")
      } else {
        __outputFileExtension = null
      }
      val __shouldReturnToPreviewScreen : Boolean
      if (bundle.containsKey("shouldReturnToPreviewScreen")) {
        __shouldReturnToPreviewScreen = bundle.getBoolean("shouldReturnToPreviewScreen")
      } else {
        __shouldReturnToPreviewScreen = false
      }
      return CropFragmentArgs(__inputFilePath, __outputFileExtension, __shouldReturnToPreviewScreen)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): CropFragmentArgs {
      val __inputFilePath : String?
      if (savedStateHandle.contains("inputFilePath")) {
        __inputFilePath = savedStateHandle["inputFilePath"]
        if (__inputFilePath == null) {
          throw IllegalArgumentException("Argument \"inputFilePath\" is marked as non-null but was passed a null value")
        }
      } else {
        __inputFilePath = "\"\""
      }
      val __outputFileExtension : String?
      if (savedStateHandle.contains("outputFileExtension")) {
        __outputFileExtension = savedStateHandle["outputFileExtension"]
      } else {
        __outputFileExtension = null
      }
      val __shouldReturnToPreviewScreen : Boolean?
      if (savedStateHandle.contains("shouldReturnToPreviewScreen")) {
        __shouldReturnToPreviewScreen = savedStateHandle["shouldReturnToPreviewScreen"]
        if (__shouldReturnToPreviewScreen == null) {
          throw IllegalArgumentException("Argument \"shouldReturnToPreviewScreen\" of type boolean does not support null values")
        }
      } else {
        __shouldReturnToPreviewScreen = false
      }
      return CropFragmentArgs(__inputFilePath, __outputFileExtension, __shouldReturnToPreviewScreen)
    }
  }
}
