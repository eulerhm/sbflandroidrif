package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00f8\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0083\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0001\u0010\u001b\u001a\u00020\u001c\u0012\b\b\u0001\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u00a2\u0006\u0002\u0010 J\t\u0010\u0099\u0001\u001a\u00020(H\u0002J\t\u0010\u009a\u0001\u001a\u00020+H\u0002J\u0012\u0010\u009b\u0001\u001a\u00020+2\u0007\u0010\u009c\u0001\u001a\u00020:H\u0002J&\u0010\u009d\u0001\u001a\u00020+2\b\u0010\u008b\u0001\u001a\u00030\u008c\u00012\b\u0010\u009e\u0001\u001a\u00030\u009f\u00012\t\b\u0002\u0010\u00a0\u0001\u001a\u00020(J\u0012\u0010\u00a1\u0001\u001a\u00020+2\u0007\u0010\u009c\u0001\u001a\u00020:H\u0002J\u0012\u0010\u00a2\u0001\u001a\u00020+2\u0007\u0010\u009c\u0001\u001a\u00020:H\u0002J\u0007\u0010\u00a3\u0001\u001a\u00020+J\u0012\u0010\u00a4\u0001\u001a\u00030\u00a5\u00012\b\u0010\u00a6\u0001\u001a\u00030\u00a7\u0001J\u0013\u0010\u00a8\u0001\u001a\u00020+2\n\u0010\u00a9\u0001\u001a\u0005\u0018\u00010\u00aa\u0001J\u0007\u0010\u00ab\u0001\u001a\u00020+J\t\u0010\u00ac\u0001\u001a\u00020(H\u0002J\t\u0010\u00ad\u0001\u001a\u00020+H\u0002J\t\u0010\u00ae\u0001\u001a\u00020(H\u0002J\u0007\u0010\u00af\u0001\u001a\u00020+J\u0007\u0010\u00b0\u0001\u001a\u00020+J\u0007\u0010\u00b1\u0001\u001a\u00020+J\t\u0010\u00b2\u0001\u001a\u00020+H\u0014J\u0010\u0010\u00b3\u0001\u001a\u00020+2\u0007\u0010\u00b4\u0001\u001a\u000208J\u0007\u0010\u00b5\u0001\u001a\u00020+J\u0010\u0010\u00b6\u0001\u001a\u00020+2\u0007\u0010\u00b4\u0001\u001a\u000208J\u0010\u0010\u00b7\u0001\u001a\u00020+2\u0007\u0010\u00b4\u0001\u001a\u000208J\u0010\u0010\u00b8\u0001\u001a\u00020+2\u0007\u0010\u0087\u0001\u001a\u000208J\u0013\u0010\u00b9\u0001\u001a\u00020+2\n\b\u0002\u0010\u00ba\u0001\u001a\u00030\u00bb\u0001J\u0010\u0010\u00bc\u0001\u001a\u00020+2\u0007\u0010\u00bd\u0001\u001a\u00020(J\u0010\u0010\u00be\u0001\u001a\u00020+2\u0007\u0010\u00bf\u0001\u001a\u00020:J\t\u0010\u00c0\u0001\u001a\u00020+H\u0002J\t\u0010\u00c1\u0001\u001a\u00020+H\u0002J\u001d\u0010\u00c2\u0001\u001a\u00020+2\u0007\u0010\u00c3\u0001\u001a\u00020@2\t\b\u0002\u0010\u00c4\u0001\u001a\u00020(H\u0002J\u0013\u0010\u00c5\u0001\u001a\u00020+2\b\u0010\u00c6\u0001\u001a\u00030\u009f\u0001H\u0002J\u0010\u0010\u00c7\u0001\u001a\u00020+2\u0007\u0010\u00c8\u0001\u001a\u00020:J4\u0010\u00c9\u0001\u001a\u00020+2\b\u0010\u008b\u0001\u001a\u00030\u008c\u00012\u0007\u0010\u00ca\u0001\u001a\u0002042\u0006\u0010L\u001a\u00020M2\u0006\u0010V\u001a\u00020W2\b\u0010\u00cb\u0001\u001a\u00030\u00cc\u0001J\u0007\u0010\u00cd\u0001\u001a\u00020+J\u0011\u0010\u00ce\u0001\u001a\u00020+2\b\u0010\u00cf\u0001\u001a\u00030\u00bb\u0001J\u001c\u0010\u00d0\u0001\u001a\u00020+2\u0007\u0010\u00d1\u0001\u001a\u0002042\b\u0010\u00d2\u0001\u001a\u00030\u00d3\u0001H\u0002JK\u0010\u00d4\u0001\u001a\u00020+2\u000b\b\u0002\u0010\u00d5\u0001\u001a\u0004\u0018\u00010(2\u000b\b\u0002\u0010\u00d6\u0001\u001a\u0004\u0018\u00010(2\u000b\b\u0002\u0010\u00d7\u0001\u001a\u0004\u0018\u00010#2\u0013\b\u0002\u0010\u00d8\u0001\u001a\f\u0012\u0005\u0012\u00030\u00da\u0001\u0018\u00010\u00d9\u0001H\u0003\u00a2\u0006\u0003\u0010\u00db\u0001R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u0002000%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00103\u001a\b\u0012\u0004\u0012\u0002040%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u0002060%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001080\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00109\u001a\b\u0012\u0004\u0012\u00020:0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020<0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020>0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020#0\"\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010H\u001a\u00020I8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bJ\u0010KR\u001c\u0010L\u001a\u0004\u0018\u00010MX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0017\u0010R\u001a\b\u0012\u0004\u0012\u00020&0S\u00a2\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020WX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020YX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010Z\u001a\u00020(8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\\\u0010]\u001a\u0004\bZ\u0010[R\u0017\u0010^\u001a\b\u0012\u0004\u0012\u00020(0S\u00a2\u0006\b\n\u0000\u001a\u0004\b^\u0010UR\u000e\u0010_\u001a\u00020`X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0S\u00a2\u0006\b\n\u0000\u001a\u0004\bb\u0010UR\u001d\u0010c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0S\u00a2\u0006\b\n\u0000\u001a\u0004\bd\u0010UR\u001d\u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0S\u00a2\u0006\b\n\u0000\u001a\u0004\bf\u0010UR\u001d\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0S\u00a2\u0006\b\n\u0000\u001a\u0004\bh\u0010UR\u001b\u0010i\u001a\u00020j8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bm\u0010]\u001a\u0004\bk\u0010lR\u001b\u0010n\u001a\u00020o8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\br\u0010]\u001a\u0004\bp\u0010qR\u001b\u0010s\u001a\u00020t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bw\u0010]\u001a\u0004\bu\u0010vR\u0017\u0010x\u001a\b\u0012\u0004\u0012\u0002000S\u00a2\u0006\b\n\u0000\u001a\u0004\by\u0010UR\u001b\u0010z\u001a\u00020{8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b~\u0010]\u001a\u0004\b|\u0010}R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u007f\u001a\b\u0012\u0004\u0012\u0002020S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010UR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u0002040S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010UR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u0002060S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010UR\u0010\u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0087\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001080S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u0010UR\u0019\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020:0S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010UR\u0010\u0010\u008b\u0001\u001a\u00030\u008c\u0001X\u0082.\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020<0S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010UR\u0019\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020>0S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0090\u0001\u0010UR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0093\u0001\u001a\b\u0012\u0004\u0012\u00020@0S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0094\u0001\u0010UR\u0019\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020B0S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010UR\u0019\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020D0S\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010U\u00a8\u0006\u00dc\u0001"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListMainViewModel;", "Landroidx/lifecycle/ViewModel;", "Lkotlinx/coroutines/CoroutineScope;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "uploadActionUseCase", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "uploadStore", "Lorg/wordpress/android/fluxc/store/UploadStore;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "prefs", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "postListEventListenerFactory", "Lorg/wordpress/android/ui/posts/PostListEventListener$Factory;", "previewStateHelper", "Lorg/wordpress/android/ui/posts/PreviewStateHelper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "savePostToDbUseCase", "Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/uploads/UploadActionUseCase;Lorg/wordpress/android/fluxc/store/UploadStore;Lorg/wordpress/android/fluxc/store/MediaStore;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/posts/PostListEventListener$Factory;Lorg/wordpress/android/ui/posts/PreviewStateHelper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/uploads/UploadStarter;)V", "_authorSelectionUpdated", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "_dialogAction", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/helpers/DialogHolder;", "_isSearchExpanded", "", "_onFabClicked", "Lorg/wordpress/android/viewmodel/Event;", "", "_onFabLongPressedForCreateMenu", "_onFabLongPressedForPostList", "_openPrepublishingBottomSheet", "_postListAction", "Lorg/wordpress/android/ui/posts/PostListAction;", "_postUploadAction", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "_previewState", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "_scrollToLocalPostId", "Lorg/wordpress/android/viewmodel/posts/PostListItemIdentifier$LocalPostId;", "_searchQuery", "", "_selectTab", "", "_snackBarMessage", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_toastMessage", "Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;", "_viewLayoutType", "Lorg/wordpress/android/ui/posts/PostListViewLayoutType;", "_viewLayoutTypeMenuUiState", "Lorg/wordpress/android/ui/posts/PostListViewLayoutTypeMenuUiState;", "_viewState", "Lorg/wordpress/android/ui/posts/PostListMainViewState;", "authorSelectionUpdated", "getAuthorSelectionUpdated", "()Landroidx/lifecycle/MutableLiveData;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "currentBottomSheetPostId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "getCurrentBottomSheetPostId", "()Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "setCurrentBottomSheetPostId", "(Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;)V", "dialogAction", "Landroidx/lifecycle/LiveData;", "getDialogAction", "()Landroidx/lifecycle/LiveData;", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "featuredImageTracker", "Lorg/wordpress/android/ui/posts/PostListFeaturedImageTracker;", "isFilteringByAuthorSupported", "()Z", "isFilteringByAuthorSupported$delegate", "Lkotlin/Lazy;", "isSearchExpanded", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "onFabClicked", "getOnFabClicked", "onFabLongPressedForCreateMenu", "getOnFabLongPressedForCreateMenu", "onFabLongPressedForPostList", "getOnFabLongPressedForPostList", "openPrepublishingBottomSheet", "getOpenPrepublishingBottomSheet", "postActionHandler", "Lorg/wordpress/android/ui/posts/PostActionHandler;", "getPostActionHandler", "()Lorg/wordpress/android/ui/posts/PostActionHandler;", "postActionHandler$delegate", "postConflictResolver", "Lorg/wordpress/android/ui/posts/PostConflictResolver;", "getPostConflictResolver", "()Lorg/wordpress/android/ui/posts/PostConflictResolver;", "postConflictResolver$delegate", "postFetcher", "Lorg/wordpress/android/viewmodel/posts/PostFetcher;", "getPostFetcher", "()Lorg/wordpress/android/viewmodel/posts/PostFetcher;", "postFetcher$delegate", "postListAction", "getPostListAction", "postListDialogHelper", "Lorg/wordpress/android/ui/posts/PostListDialogHelper;", "getPostListDialogHelper", "()Lorg/wordpress/android/ui/posts/PostListDialogHelper;", "postListDialogHelper$delegate", "postUploadAction", "getPostUploadAction", "previewState", "getPreviewState", "scrollToLocalPostId", "getScrollToLocalPostId", "scrollToTargetPostJob", "Lkotlinx/coroutines/Job;", "searchQuery", "getSearchQuery", "selectTab", "getSelectTab", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "snackBarMessage", "getSnackBarMessage", "toastMessage", "getToastMessage", "uploadStatusTracker", "Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "viewLayoutType", "getViewLayoutType", "viewLayoutTypeMenuUiState", "getViewLayoutTypeMenuUiState", "viewState", "getViewState", "checkNetworkConnection", "clearSearch", "copyLocalPost", "localPostId", "copyPost", "postToCopy", "Lorg/wordpress/android/fluxc/model/PostModel;", "performChecks", "editLocalPost", "editRestoredAutoSavePost", "fabClicked", "getPostListViewModelConnector", "Lorg/wordpress/android/viewmodel/posts/PostListViewModelConnector;", "postListType", "Lorg/wordpress/android/ui/posts/PostListType;", "handleEditPostResult", "data", "Landroid/content/Intent;", "handleRemotePreviewClosing", "hasRemoteAutoSavePreviewError", "invalidateAllLists", "isRemotePreviewingFromPostsList", "newPost", "newStoryPost", "onBottomSheetPublishButtonClicked", "onCleared", "onDismissByOutsideTouchForBasicDialog", "instanceTag", "onFabLongPressed", "onNegativeClickedForBasicDialog", "onPositiveClickedForBasicDialog", "onSearch", "onSearchCollapsed", "delay", "", "onSearchExpanded", "restorePreviousSearch", "onTabChanged", "position", "refreshAllLists", "setUserPreferredViewLayoutType", "setViewLayoutAndIcon", "layout", "storeIntoPreferences", "showPrepublishingBottomSheet", "post", "showTargetPost", "targetPostId", "start", "initPreviewState", "context", "Landroid/content/Context;", "toggleViewLayout", "updateAuthorFilterSelection", "selectionId", "updatePreviewAndDialogState", "newState", "postInfo", "Lorg/wordpress/android/ui/posts/PostInfoType;", "updateViewStateTriggerPagerChange", "isFabVisible", "isAuthorFilterVisible", "authorFilterSelection", "authorFilterItems", "", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Lorg/wordpress/android/ui/posts/AuthorFilterSelection;Ljava/util/List;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListMainViewModel extends androidx.lifecycle.ViewModel implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper prefs = null;
    private final org.wordpress.android.ui.posts.PostListEventListener.Factory postListEventListenerFactory = null;
    private final org.wordpress.android.ui.posts.PreviewStateHelper previewStateHelper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.posts.SavePostToDbUseCase savePostToDbUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.uploads.UploadStarter uploadStarter = null;
    private final androidx.lifecycle.LifecycleOwner lifecycleOwner = null;
    private final kotlinx.coroutines.Job scrollToTargetPostJob = null;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private org.wordpress.android.ui.posts.EditPostRepository editPostRepository;
    @org.jetbrains.annotations.Nullable()
    private org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId currentBottomSheetPostId;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.PostListMainViewState> _viewState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListMainViewState> viewState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.posts.PostListAction> _postListAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListAction> postListAction = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.AuthorFilterSelection> _authorSelectionUpdated = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.AuthorFilterSelection> authorSelectionUpdated = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.Integer> _selectTab = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> selectTab = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId> _scrollToLocalPostId = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId> scrollToLocalPostId = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _openPrepublishingBottomSheet = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> openPrepublishingBottomSheet = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.pages.SnackbarMessageHolder> _snackBarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> snackBarMessage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.posts.PostListRemotePreviewState> _previewState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListRemotePreviewState> previewState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.helpers.ToastMessageHolder> _toastMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ToastMessageHolder> toastMessage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.helpers.DialogHolder> _dialogAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.DialogHolder> dialogAction = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.posts.PostUploadAction> _postUploadAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostUploadAction> postUploadAction = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.PostListViewLayoutType> _viewLayoutType = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListViewLayoutType> viewLayoutType = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.PostListViewLayoutTypeMenuUiState> _viewLayoutTypeMenuUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListViewLayoutTypeMenuUiState> viewLayoutTypeMenuUiState = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isSearchExpanded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isSearchExpanded = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> searchQuery = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _onFabClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> onFabClicked = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _onFabLongPressedForCreateMenu = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> onFabLongPressedForCreateMenu = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _onFabLongPressedForPostList = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> onFabLongPressedForPostList = null;
    private final org.wordpress.android.ui.posts.PostModelUploadStatusTracker uploadStatusTracker = null;
    private final org.wordpress.android.ui.posts.PostListFeaturedImageTracker featuredImageTracker = null;
    private final kotlin.Lazy postFetcher$delegate = null;
    private final kotlin.Lazy postListDialogHelper$delegate = null;
    private final kotlin.Lazy postConflictResolver$delegate = null;
    private final kotlin.Lazy postActionHandler$delegate = null;
    
    /**
     * Filtering by author is disable on:
     * 1) Self-hosted sites - The XMLRPC api doesn't support filtering by author.
     * 2) Jetpack sites - we need to pass in the self-hosted user id to be able to filter for authors
     * which we currently can't
     * 3) Sites on which the user doesn't have permissions to edit posts of other users.
     *
     * This behavior is consistent with Calypso as of 11/4/2019.
     */
    private final kotlin.Lazy isFilteringByAuthorSupported$delegate = null;
    
    @javax.inject.Inject()
    public PostListMainViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.UploadStore uploadStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper prefs, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListEventListener.Factory postListEventListenerFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PreviewStateHelper previewStateHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.SavePostToDbUseCase savePostToDbUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadStarter uploadStarter) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId getCurrentBottomSheetPostId() {
        return null;
    }
    
    public final void setCurrentBottomSheetPostId(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListMainViewState> getViewState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListAction> getPostListAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.AuthorFilterSelection> getAuthorSelectionUpdated() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getSelectTab() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.posts.PostListItemIdentifier.LocalPostId> getScrollToLocalPostId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getOpenPrepublishingBottomSheet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> getSnackBarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListRemotePreviewState> getPreviewState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ToastMessageHolder> getToastMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.DialogHolder> getDialogAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostUploadAction> getPostUploadAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListViewLayoutType> getViewLayoutType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListViewLayoutTypeMenuUiState> getViewLayoutTypeMenuUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isSearchExpanded() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getOnFabClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getOnFabLongPressedForCreateMenu() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getOnFabLongPressedForPostList() {
        return null;
    }
    
    private final org.wordpress.android.viewmodel.posts.PostFetcher getPostFetcher() {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.PostListDialogHelper getPostListDialogHelper() {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.PostConflictResolver getPostConflictResolver() {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.PostActionHandler getPostActionHandler() {
        return null;
    }
    
    public final void copyPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel postToCopy, boolean performChecks) {
    }
    
    /**
     * Filtering by author is disable on:
     * 1) Self-hosted sites - The XMLRPC api doesn't support filtering by author.
     * 2) Jetpack sites - we need to pass in the self-hosted user id to be able to filter for authors
     * which we currently can't
     * 3) Sites on which the user doesn't have permissions to edit posts of other users.
     *
     * This behavior is consistent with Calypso as of 11/4/2019.
     */
    private final boolean isFilteringByAuthorSupported() {
        return false;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListRemotePreviewState initPreviewState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId currentBottomSheetPostId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListViewModelConnector getPostListViewModelConnector(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListType postListType) {
        return null;
    }
    
    public final void onSearchExpanded(boolean restorePreviousSearch) {
    }
    
    public final void onSearchCollapsed(long delay) {
    }
    
    public final void onSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery) {
    }
    
    private final void clearSearch() {
    }
    
    public final void fabClicked() {
    }
    
    public final void newPost() {
    }
    
    public final void newStoryPost() {
    }
    
    public final void updateAuthorFilterSelection(long selectionId) {
    }
    
    public final void onTabChanged(int position) {
    }
    
    public final void showTargetPost(int targetPostId) {
    }
    
    public final void handleEditPostResult(@org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void editRestoredAutoSavePost(int localPostId) {
    }
    
    private final void editLocalPost(int localPostId) {
    }
    
    private final void copyLocalPost(int localPostId) {
    }
    
    public final void onPositiveClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    public final void onNegativeClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    public final void onDismissByOutsideTouchForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    private final void showPrepublishingBottomSheet(org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    /**
     * Only the non-null variables will be changed in the current state
     */
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    private final void updateViewStateTriggerPagerChange(java.lang.Boolean isFabVisible, java.lang.Boolean isAuthorFilterVisible, org.wordpress.android.ui.posts.AuthorFilterSelection authorFilterSelection, java.util.List<? extends org.wordpress.android.ui.posts.AuthorFilterListItemUIState> authorFilterItems) {
    }
    
    private final void invalidateAllLists() {
    }
    
    private final void refreshAllLists() {
    }
    
    private final boolean isRemotePreviewingFromPostsList() {
        return false;
    }
    
    private final boolean hasRemoteAutoSavePreviewError() {
        return false;
    }
    
    private final boolean checkNetworkConnection() {
        return false;
    }
    
    public final void handleRemotePreviewClosing() {
    }
    
    private final void updatePreviewAndDialogState(org.wordpress.android.ui.posts.PostListRemotePreviewState newState, org.wordpress.android.ui.posts.PostInfoType postInfo) {
    }
    
    public final void toggleViewLayout() {
    }
    
    private final void setViewLayoutAndIcon(org.wordpress.android.ui.posts.PostListViewLayoutType layout, boolean storeIntoPreferences) {
    }
    
    private final void setUserPreferredViewLayoutType() {
    }
    
    public final void onBottomSheetPublishButtonClicked() {
    }
    
    public final void onFabLongPressed() {
    }
}