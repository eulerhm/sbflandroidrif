package com.google.android.gnd;

import android.util.Log;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

public class MyUIWatcher {

  private static final String anrText = "isn't responding";
  private static UiDevice uiDevice = UiDevice.getInstance(
      InstrumentationRegistry.getInstrumentation());

  private static final String UI_TEST_TAG = "ui_tag";

  public static boolean closeAnrWithWait() {
    final UiObject anrDialog = uiDevice.findObject(new UiSelector()
        .textContains(anrText));

    while (!anrDialog.exists()) {
      Log.i(UI_TEST_TAG, "ANR dialog detected!");

      try {
        uiDevice.findObject(new UiSelector().text("Close app")).click();
        final String anrDialogText = anrDialog.getText();
        final String appName = anrDialogText.substring(0,
            anrDialogText.length() - anrText.length());
        Log.i(UI_TEST_TAG, String.format("Application \"%s\" is not responding!", appName));
      } catch (final UiObjectNotFoundException e) {
        Log.i(UI_TEST_TAG, "Detected ANR, but window disappeared!");
      }

      Log.i(UI_TEST_TAG, "ANR dialog closed: pressed on wait!");

      return false;
    }

    return true;
  }

}
