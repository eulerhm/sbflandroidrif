package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002Jd\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042<\b\u0002\u0010\b\u001a6\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagementUtils;", "", "()V", "likesToEngagedPeople", "", "Lorg/wordpress/android/ui/engagement/EngageItem;", "likes", "Lorg/wordpress/android/fluxc/model/LikeModel;", "onClick", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;", "Lkotlin/ParameterName;", "name", "userProfile", "Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "source", "", "likesToTrainOfFaces", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem$AvatarItem;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class EngagementUtils {
    
    @javax.inject.Inject()
    public EngagementUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.engagement.EngageItem> likesToEngagedPeople(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.LikeModel> likes, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile, ? super org.wordpress.android.ui.engagement.EngagementNavigationSource, kotlin.Unit> onClick, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.engagement.EngagementNavigationSource source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem.AvatarItem> likesToTrainOfFaces(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.LikeModel> likes) {
        return null;
    }
}