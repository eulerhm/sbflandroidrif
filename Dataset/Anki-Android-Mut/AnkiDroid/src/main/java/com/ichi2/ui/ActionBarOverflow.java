package com.ichi2.ui;

import android.util.Pair;
import android.view.MenuItem;
import java.lang.reflect.Method;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Detection of whether an item is in the ActionBar overflow
 * WARN: When making changes to this code, also test with Proguard
 */
public class ActionBarOverflow {

    protected static final String NATIVE_CLASS = "com.android.internal.view.menu.MenuItemImpl";

    protected static final String ANDROIDX_CLASS = "androidx.appcompat.view.menu.MenuItemImpl";

    @Nullable
    protected static Class<?> sNativeClassRef;

    @Nullable
    protected static Class<?> sAndroidXClassRef;

    @Nullable
    protected static Method sNativeIsActionButton;

    @Nullable
    protected static Method sAndroidXIsActionButton;

    static {
        if (!ListenerUtil.mutListener.listen(24898)) {
            setupMethods(ActionBarOverflow::getPrivateMethodHandleSystemErrors);
        }
    }

    @VisibleForTesting
    static void setupMethods(PrivateMethodAccessor accessor) {
        // Note: Multiple of these can succeed.
        Pair<Class<?>, Method> nativeImpl = accessor.getPrivateMethod(NATIVE_CLASS, "isActionButton");
        if (!ListenerUtil.mutListener.listen(24899)) {
            sNativeClassRef = nativeImpl.first;
        }
        if (!ListenerUtil.mutListener.listen(24900)) {
            sNativeIsActionButton = nativeImpl.second;
        }
        Pair<Class<?>, Method> androidXImpl = accessor.getPrivateMethod(ANDROIDX_CLASS, "isActionButton");
        if (!ListenerUtil.mutListener.listen(24901)) {
            sAndroidXClassRef = androidXImpl.first;
        }
        if (!ListenerUtil.mutListener.listen(24902)) {
            sAndroidXIsActionButton = androidXImpl.second;
        }
    }

    @CheckResult
    private static Pair<Class<?>, Method> getPrivateMethodHandleSystemErrors(String className, String methodName) {
        Method action = null;
        Class<?> menuItemImpl = null;
        try {
            if (!ListenerUtil.mutListener.listen(24904)) {
                // We know this won't always work, we'll log if this isn't the case.
                menuItemImpl = Class.forName(className);
            }
            if (!ListenerUtil.mutListener.listen(24905)) {
                action = menuItemImpl.getDeclaredMethod(methodName);
            }
            if (!ListenerUtil.mutListener.listen(24906)) {
                action.setAccessible(true);
            }
            if (!ListenerUtil.mutListener.listen(24907)) {
                Timber.d("Setup ActionBarOverflow: %s", className);
            }
        } catch (Exception | NoSuchFieldError | NoSuchMethodError ignoreAndLogEx) {
            if (!ListenerUtil.mutListener.listen(24903)) {
                // https://developer.android.com/distribute/best-practices/develop/restrictions-non-sdk-interfaces#results-of-keeping-non-sdk
                Timber.d(ignoreAndLogEx, "Failed to handle: %s", className);
            }
        }
        return new Pair<>(menuItemImpl, action);
    }

    /**
     * Check if an item is showing (not in the overflow menu).
     *
     * @param item
     *            the MenuItem.
     * @return {@code true} if the MenuItem is visible on the ActionBar. {@code false} if not. {@code null if unknown}
     */
    @Nullable
    public static Boolean isActionButton(MenuItem item) {
        if ((ListenerUtil.mutListener.listen(24908) ? (sNativeClassRef != null || sNativeClassRef.isInstance(item)) : (sNativeClassRef != null && sNativeClassRef.isInstance(item)))) {
            return tryInvokeMethod(item, sNativeIsActionButton);
        } else if ((ListenerUtil.mutListener.listen(24909) ? (sAndroidXClassRef != null || sAndroidXClassRef.isInstance(item)) : (sAndroidXClassRef != null && sAndroidXClassRef.isInstance(item)))) {
            return tryInvokeMethod(item, sAndroidXIsActionButton);
        } else {
            if (!ListenerUtil.mutListener.listen(24910)) {
                Timber.w("Unhandled ActionBar class: %s", item.getClass().getName());
            }
            return null;
        }
    }

    private static Boolean tryInvokeMethod(MenuItem item, Method method) {
        try {
            return (boolean) method.invoke(item, (Object[]) null);
        } catch (Exception | NoSuchFieldError | NoSuchMethodError ex) {
            if (!ListenerUtil.mutListener.listen(24911)) {
                Timber.w(ex, "Error handling ActionBar class: %s", item.getClass().getName());
            }
            return null;
        }
    }

    @VisibleForTesting
    @FunctionalInterface
    interface PrivateMethodAccessor {

        Pair<Class<?>, Method> getPrivateMethod(String className, String methodName);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    static boolean hasUsableMethod() {
        return (ListenerUtil.mutListener.listen(24912) ? (sNativeIsActionButton != null && sAndroidXIsActionButton != null) : (sNativeIsActionButton != null || sAndroidXIsActionButton != null));
    }

    @CheckResult
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    static Pair<Class<?>, Method> getPrivateMethodOnlyHandleExceptions(String className, String methodName) {
        Method action = null;
        Class<?> menuItemImpl = null;
        try {
            if (!ListenerUtil.mutListener.listen(24914)) {
                menuItemImpl = Class.forName(className);
            }
            if (!ListenerUtil.mutListener.listen(24915)) {
                action = menuItemImpl.getDeclaredMethod(methodName);
            }
            if (!ListenerUtil.mutListener.listen(24916)) {
                action.setAccessible(true);
            }
            if (!ListenerUtil.mutListener.listen(24917)) {
                Timber.d("Setup ActionBarOverflow: %s", className);
            }
        } catch (Exception ignoreAndLogEx) {
            if (!ListenerUtil.mutListener.listen(24913)) {
                Timber.d(ignoreAndLogEx, "Failed to handle: %s", className);
            }
        }
        return new Pair<>(menuItemImpl, action);
    }
}
