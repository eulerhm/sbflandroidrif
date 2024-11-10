package fr.free.nrw.commons;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\b\u0010\u0015\u001a\u00020\u0014H\u0007J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0007J\b\u0010\u0018\u001a\u00020\u0014H\u0007J\b\u0010\u0019\u001a\u00020\u0014H\u0007J\b\u0010\u001a\u001a\u00020\u0014H\u0007J\b\u0010\u001b\u001a\u00020\u0014H\u0007J\b\u0010\u001c\u001a\u00020\u0014H\u0007R \u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00048GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u00020\u000e8GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001d"}, d2 = {"Lfr/free/nrw/commons/MainActivityTest;", "", "()V", "activityRule", "Landroidx/test/rule/ActivityTestRule;", "getActivityRule", "()Landroidx/test/rule/ActivityTestRule;", "setActivityRule", "(Landroidx/test/rule/ActivityTestRule;)V", "defaultKvStore", "Lfr/free/nrw/commons/kvstore/JsonKvStore;", "device", "Landroidx/test/uiautomator/UiDevice;", "mGrantPermissionRule", "Landroidx/test/rule/GrantPermissionRule;", "getMGrantPermissionRule", "()Landroidx/test/rule/GrantPermissionRule;", "setMGrantPermissionRule", "(Landroidx/test/rule/GrantPermissionRule;)V", "cleanUp", "", "setup", "swipeTillLast", "testBookmarks", "testContributions", "testExplore", "testLimitedConnectionModeToggle", "testNearby", "testNotifications", "app-commons-v4.2.1-master_prodDebug"})
@org.junit.runner.RunWith(value = androidx.test.ext.junit.runners.AndroidJUnit4.class)
@androidx.test.filters.LargeTest
public final class MainActivityTest {
    @org.jetbrains.annotations.NotNull
    private androidx.test.rule.ActivityTestRule<?> activityRule;
    @org.jetbrains.annotations.NotNull
    private androidx.test.rule.GrantPermissionRule mGrantPermissionRule;
    private final androidx.test.uiautomator.UiDevice device = null;
    private fr.free.nrw.commons.kvstore.JsonKvStore defaultKvStore;
    
    public MainActivityTest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @org.junit.Rule
    public final androidx.test.rule.ActivityTestRule<?> getActivityRule() {
        return null;
    }
    
    public final void setActivityRule(@org.jetbrains.annotations.NotNull
    androidx.test.rule.ActivityTestRule<?> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @org.junit.Rule
    public final androidx.test.rule.GrantPermissionRule getMGrantPermissionRule() {
        return null;
    }
    
    public final void setMGrantPermissionRule(@org.jetbrains.annotations.NotNull
    androidx.test.rule.GrantPermissionRule p0) {
    }
    
    private final void swipeTillLast() {
    }
    
    @org.junit.Before
    public final void setup() {
    }
    
    @org.junit.After
    public final void cleanUp() {
    }
    
    @org.junit.Test
    public final void testNearby() {
    }
    
    @org.junit.Test
    public final void testExplore() {
    }
    
    @org.junit.Ignore
    public final void testContributions() {
    }
    
    @org.junit.Test
    public final void testBookmarks() {
    }
    
    @org.junit.Test
    public final void testNotifications() {
    }
    
    @org.junit.Ignore
    public final void testLimitedConnectionModeToggle() {
    }
}