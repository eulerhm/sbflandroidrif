package fr.free.nrw.commons;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0007J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\b\u0010 \u001a\u00020\u0016H\u0007J\b\u0010!\u001a\u00020\u0016H\u0007J\b\u0010\"\u001a\u00020\u0016H\u0007J\b\u0010#\u001a\u00020\u0016H\u0007R*\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u00048GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u00020\f8GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006$"}, d2 = {"Lfr/free/nrw/commons/UploadTest;", "", "()V", "activityRule", "Landroidx/test/rule/ActivityTestRule;", "Lfr/free/nrw/commons/auth/LoginActivity;", "kotlin.jvm.PlatformType", "getActivityRule", "()Landroidx/test/rule/ActivityTestRule;", "setActivityRule", "(Landroidx/test/rule/ActivityTestRule;)V", "permissionRule", "Landroidx/test/rule/GrantPermissionRule;", "getPermissionRule", "()Landroidx/test/rule/GrantPermissionRule;", "setPermissionRule", "(Landroidx/test/rule/GrantPermissionRule;)V", "randomBitmap", "Landroid/graphics/Bitmap;", "getRandomBitmap", "()Landroid/graphics/Bitmap;", "dismissWarning", "", "warningText", "", "dismissWarningDialog", "openGallery", "saveToInternalStorage", "imageName", "setup", "setupSingleUpload", "singleImageIntent", "teardown", "testUploadWithDescription", "testUploadWithMultilingualDescription", "testUploadWithoutDescription", "app-commons-v4.2.1-master_prodDebug"})
@org.junit.runner.RunWith(value = androidx.test.ext.junit.runners.AndroidJUnit4.class)
@androidx.test.filters.LargeTest
public final class UploadTest {
    @org.jetbrains.annotations.NotNull
    private androidx.test.rule.GrantPermissionRule permissionRule;
    @org.jetbrains.annotations.NotNull
    private androidx.test.rule.ActivityTestRule<fr.free.nrw.commons.auth.LoginActivity> activityRule;
    
    public UploadTest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @org.junit.Rule
    public final androidx.test.rule.GrantPermissionRule getPermissionRule() {
        return null;
    }
    
    public final void setPermissionRule(@org.jetbrains.annotations.NotNull
    androidx.test.rule.GrantPermissionRule p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    @org.junit.Rule
    public final androidx.test.rule.ActivityTestRule<fr.free.nrw.commons.auth.LoginActivity> getActivityRule() {
        return null;
    }
    
    public final void setActivityRule(@org.jetbrains.annotations.NotNull
    androidx.test.rule.ActivityTestRule<fr.free.nrw.commons.auth.LoginActivity> p0) {
    }
    
    private final android.graphics.Bitmap getRandomBitmap() {
        return null;
    }
    
    @org.junit.Before
    public final void setup() {
    }
    
    @org.junit.After
    public final void teardown() {
    }
    
    @org.junit.Ignore(value = "Fix Failing Test")
    @org.junit.Test
    public final void testUploadWithDescription() {
    }
    
    private final void dismissWarning(java.lang.String warningText) {
    }
    
    @org.junit.Ignore(value = "Fix Failing Test")
    @org.junit.Test
    public final void testUploadWithoutDescription() {
    }
    
    @org.junit.Ignore(value = "Fix Failing Test")
    @org.junit.Test
    public final void testUploadWithMultilingualDescription() {
    }
    
    private final void setupSingleUpload(java.lang.String imageName) {
    }
    
    private final void saveToInternalStorage(java.lang.String imageName) {
    }
    
    private final void singleImageIntent(java.lang.String imageName) {
    }
    
    private final void dismissWarningDialog() {
    }
    
    private final void openGallery() {
    }
}