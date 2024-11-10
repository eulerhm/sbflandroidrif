package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Suppress(names = {"LargeClass", "LongMethod", "LongParameterList", "TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00de\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 \u00d0\u00022\u00020\u0001:\u0014\u00d0\u0002\u00d1\u0002\u00d2\u0002\u00d3\u0002\u00d4\u0002\u00d5\u0002\u00d6\u0002\u00d7\u0002\u00d8\u0002\u00d9\u0002B\u00a3\u0002\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0006\u0010!\u001a\u00020\"\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020&\u0012\u0006\u0010\'\u001a\u00020(\u0012\u0006\u0010)\u001a\u00020*\u0012\u0006\u0010+\u001a\u00020,\u0012\u0006\u0010-\u001a\u00020.\u0012\u0006\u0010/\u001a\u000200\u0012\u0006\u00101\u001a\u000202\u0012\u0006\u00103\u001a\u000204\u0012\u0006\u00105\u001a\u000206\u0012\u0006\u00107\u001a\u000208\u0012\u0006\u00109\u001a\u00020:\u0012\u0006\u0010;\u001a\u00020<\u0012\u0006\u0010=\u001a\u00020>\u0012\u0006\u0010?\u001a\u00020@\u0012\u0006\u0010A\u001a\u00020B\u0012\u0006\u0010C\u001a\u00020D\u0012\u0006\u0010E\u001a\u00020F\u00a2\u0006\u0002\u0010GJ\u001e\u0010\u00a2\u0001\u001a\u0004\u0018\u00010Y2\b\u0010\u00a3\u0001\u001a\u00030\u00a4\u00012\u0007\u0010\u00a5\u0001\u001a\u00020PH\u0002J\n\u0010\u00a6\u0001\u001a\u00030\u00a7\u0001H\u0002J\u0093\u0001\u0010\u00a8\u0001\u001a\u0017\u0012\u0004\u0012\u00020g\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u00aa\u00010\u0094\u00010\u00a9\u00012\u0007\u0010\u00a5\u0001\u001a\u00020P2\t\u0010\u00ab\u0001\u001a\u0004\u0018\u00010K2\u0007\u0010\u00ac\u0001\u001a\u00020k2\u000f\u0010\u00ad\u0001\u001a\n\u0012\u0005\u0012\u00030\u00ae\u00010\u0094\u00012\n\u0010\u00af\u0001\u001a\u0005\u0018\u00010\u00b0\u00012\u000f\u0010\u00b1\u0001\u001a\n\u0012\u0005\u0012\u00030\u00b0\u00010\u0094\u00012\u0007\u0010\u00b2\u0001\u001a\u00020k2\u0007\u0010\u00b3\u0001\u001a\u00020k2\n\u0010\u00b4\u0001\u001a\u0005\u0018\u00010\u00b5\u00012\n\u0010\u00b6\u0001\u001a\u0005\u0018\u00010\u00b7\u0001H\u0002J\u0094\u0001\u0010\u00b8\u0001\u001a\u00030\u00b9\u00012\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u00012\u0007\u0010\u00a5\u0001\u001a\u00020P2\u0007\u0010\u00ba\u0001\u001a\u00020k2\t\u0010\u00ab\u0001\u001a\u0004\u0018\u00010K2\u0007\u0010\u00ac\u0001\u001a\u00020k2\u000f\u0010\u00ad\u0001\u001a\n\u0012\u0005\u0012\u00030\u00ae\u00010\u0094\u00012\n\u0010\u00af\u0001\u001a\u0005\u0018\u00010\u00b0\u00012\u000f\u0010\u00b1\u0001\u001a\n\u0012\u0005\u0012\u00030\u00b0\u00010\u0094\u00012\u0007\u0010\u00b2\u0001\u001a\u00020k2\u0007\u0010\u00b3\u0001\u001a\u00020k2\n\u0010\u00b4\u0001\u001a\u0005\u0018\u00010\u00b5\u00012\n\u0010\u00b6\u0001\u001a\u0005\u0018\u00010\u00b7\u0001H\u0002J\u0007\u0010\u00bb\u0001\u001a\u00020UJ\u0012\u0010\u00bc\u0001\u001a\u00020U2\u0007\u0010\u00bd\u0001\u001a\u00020kH\u0002J\"\u0010\u00be\u0001\u001a\u00020U2\u0007\u0010\u00bf\u0001\u001a\u00020L2\u0007\u0010\u00c0\u0001\u001a\u00020k2\u0007\u0010\u00bd\u0001\u001a\u00020kJ\u0007\u0010\u00c1\u0001\u001a\u00020UJ\n\u0010\u00c2\u0001\u001a\u00030\u009e\u0001H\u0002J\u0007\u0010\u00c3\u0001\u001a\u00020UJ\t\u0010\u00c4\u0001\u001a\u00020UH\u0002J\u0015\u0010\u00c5\u0001\u001a\u0005\u0018\u00010\u00c6\u00012\u0007\u0010\u00c7\u0001\u001a\u00020gH\u0002J\u001a\u0010\u00c8\u0001\u001a\n\u0012\u0005\u0012\u00030\u00c9\u00010\u0094\u00012\u0007\u0010\u00c7\u0001\u001a\u00020gH\u0002J0\u0010\u00ca\u0001\u001a\u00020L2\u001c\u0010\u00cb\u0001\u001a\u0017\u0012\u0004\u0012\u00020g\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u00aa\u00010\u0094\u00010\u00a9\u00012\u0007\u0010\u00ab\u0001\u001a\u00020KH\u0002J\n\u0010\u00cc\u0001\u001a\u00030\u00cd\u0001H\u0002J\u0012\u0010\u00ce\u0001\u001a\u00020[2\u0007\u0010\u00a5\u0001\u001a\u00020PH\u0002J\u001c\u0010\u00cf\u0001\u001a\u00020U2\n\u0010\u00d0\u0001\u001a\u0005\u0018\u00010\u00d1\u00012\u0007\u0010\u00d2\u0001\u001a\u00020kJ\u0011\u0010\u00d3\u0001\u001a\u00020U2\b\u0010\u00d4\u0001\u001a\u00030\u00d5\u0001J\u0011\u0010\u00d6\u0001\u001a\u00020U2\b\u0010\u00d7\u0001\u001a\u00030\u00d8\u0001J\u0012\u0010\u00d9\u0001\u001a\u00020U2\t\u0010\u00da\u0001\u001a\u0004\u0018\u00010]J\u0007\u0010\u00db\u0001\u001a\u00020UJ\u001e\u0010\u00dc\u0001\u001a\u00020U2\t\u0010\u00dd\u0001\u001a\u0004\u0018\u00010]2\n\u0010\u00de\u0001\u001a\u0005\u0018\u00010\u00df\u0001J\u0013\u0010\u00e0\u0001\u001a\u00020k2\b\u0010\u00e1\u0001\u001a\u00030\u00e2\u0001H\u0002J\t\u0010\u00e3\u0001\u001a\u00020UH\u0002J\u0007\u0010\u00e4\u0001\u001a\u00020UJ\u0007\u0010\u00e5\u0001\u001a\u00020kJ\u0012\u0010\u00e6\u0001\u001a\u00020k2\u0007\u0010\u00e7\u0001\u001a\u00020KH\u0002J\u001b\u0010\u00e8\u0001\u001a\u00020k2\u0007\u0010\u00e7\u0001\u001a\u00020K2\u0007\u0010\u00e9\u0001\u001a\u00020LH\u0002J\t\u0010\u00ea\u0001\u001a\u00020UH\u0002J\u0007\u0010\u00eb\u0001\u001a\u00020UJ\u0007\u0010\u00ec\u0001\u001a\u00020UJ\u0012\u0010\u00ed\u0001\u001a\u00020U2\u0007\u0010\u00ee\u0001\u001a\u00020LH\u0002J\u0012\u0010\u00ef\u0001\u001a\u00020U2\u0007\u0010\u00f0\u0001\u001a\u00020]H\u0002J\t\u0010\u00f1\u0001\u001a\u00020UH\u0002J\u0007\u0010\u00f2\u0001\u001a\u00020UJ\t\u0010\u00f3\u0001\u001a\u00020UH\u0014J\u0007\u0010\u00f4\u0001\u001a\u00020UJ\t\u0010\u00f5\u0001\u001a\u00020UH\u0002J\u0011\u0010\u00f6\u0001\u001a\u00020U2\b\u0010\u00f7\u0001\u001a\u00030\u00f8\u0001J\u0012\u0010\u00f9\u0001\u001a\u00020U2\u0007\u0010\u00fa\u0001\u001a\u00020WH\u0002J\t\u0010\u00fb\u0001\u001a\u00020UH\u0002J\u0013\u0010\u00fc\u0001\u001a\u00020U2\b\u0010\u00fd\u0001\u001a\u00030\u00fe\u0001H\u0002J\u0013\u0010\u00ff\u0001\u001a\u00020U2\b\u0010\u0080\u0002\u001a\u00030\u0081\u0002H\u0002J\u0013\u0010\u0082\u0002\u001a\u00020U2\b\u0010\u0083\u0002\u001a\u00030\u0084\u0002H\u0002J\u0013\u0010\u0085\u0002\u001a\u00020U2\b\u0010\u0086\u0002\u001a\u00030\u0087\u0002H\u0007J\t\u0010\u0088\u0002\u001a\u00020UH\u0002J\t\u0010\u0089\u0002\u001a\u00020UH\u0002J\t\u0010\u008a\u0002\u001a\u00020UH\u0002J\t\u0010\u008b\u0002\u001a\u00020UH\u0002J\t\u0010\u008c\u0002\u001a\u00020UH\u0002J\u0007\u0010\u008d\u0002\u001a\u00020UJ\u0011\u0010\u008e\u0002\u001a\u00020U2\b\u0010\u00f7\u0001\u001a\u00030\u008f\u0002J\u0010\u0010\u0090\u0002\u001a\u00020U2\u0007\u0010\u0091\u0002\u001a\u00020KJ\u0013\u0010\u0092\u0002\u001a\u00020U2\b\u0010\u0093\u0002\u001a\u00030\u0094\u0002H\u0002J\t\u0010\u0095\u0002\u001a\u00020UH\u0002J\t\u0010\u0096\u0002\u001a\u00020UH\u0002J\u0007\u0010\u0097\u0002\u001a\u00020UJ\u0007\u0010\u0098\u0002\u001a\u00020UJ\u0010\u0010\u0099\u0002\u001a\u00020U2\u0007\u0010\u009a\u0002\u001a\u00020]J\u0007\u0010\u009b\u0002\u001a\u00020UJ\u0010\u0010\u009c\u0002\u001a\u00020U2\u0007\u0010\u00e9\u0001\u001a\u00020LJ\t\u0010\u009d\u0002\u001a\u00020UH\u0002J\t\u0010\u009e\u0002\u001a\u00020UH\u0002JP\u0010\u009f\u0002\u001a\n\u0012\u0005\u0012\u00030\u00aa\u00010\u0094\u00012\n\u0010\u00a0\u0002\u001a\u0005\u0018\u00010\u00a1\u00022\u000f\u0010\u00a2\u0002\u001a\n\u0012\u0005\u0012\u00030\u00aa\u00010\u0094\u00012\u000f\u0010\u00a3\u0002\u001a\n\u0012\u0005\u0012\u00030\u00aa\u00010\u0094\u00012\u000f\u0010\u00cb\u0001\u001a\n\u0012\u0005\u0012\u00030\u00aa\u00010\u0094\u0001H\u0002J\"\u0010\u00a4\u0002\u001a\u00020U2\u0007\u0010\u00bf\u0001\u001a\u00020L2\u0007\u0010\u00c0\u0001\u001a\u00020k2\u0007\u0010\u00bd\u0001\u001a\u00020kJ\t\u0010\u00a5\u0002\u001a\u00020UH\u0002J\t\u0010\u00a6\u0002\u001a\u00020UH\u0002J\t\u0010\u00a7\u0002\u001a\u00020UH\u0002J\t\u0010\u00a8\u0002\u001a\u00020UH\u0002J\u0012\u0010\u00a9\u0002\u001a\u00020U2\t\b\u0002\u0010\u00aa\u0002\u001a\u00020kJ\u0012\u0010\u00ab\u0002\u001a\u00020U2\u0007\u0010\u00ac\u0002\u001a\u00020KH\u0002J\t\u0010\u00ad\u0002\u001a\u00020UH\u0002J\u001b\u0010\u00ae\u0002\u001a\u00020U2\u0007\u0010\u00e7\u0001\u001a\u00020K2\u0007\u0010\u00e9\u0001\u001a\u00020LH\u0002J\t\u0010\u00af\u0002\u001a\u00020UH\u0002J \u0010\u00b0\u0002\u001a\u00020U2\u0007\u0010\u00b1\u0002\u001a\u00020k2\u000e\u0010\u00b2\u0002\u001a\t\u0012\u0004\u0012\u00020U0\u00b3\u0002J \u0010\u00b4\u0002\u001a\u00020U2\u0007\u0010\u00b1\u0002\u001a\u00020k2\u000e\u0010\u00b5\u0002\u001a\t\u0012\u0004\u0012\u00020U0\u00b3\u0002J\t\u0010\u00b6\u0002\u001a\u00020kH\u0002J\t\u0010\u00b7\u0002\u001a\u00020kH\u0002J\u0012\u0010\u00b8\u0002\u001a\u00020k2\u0007\u0010\u00c7\u0001\u001a\u00020gH\u0002J\t\u0010\u00b9\u0002\u001a\u00020kH\u0002J\u0014\u0010\u00ba\u0002\u001a\u00020U2\t\u0010\u00bb\u0002\u001a\u0004\u0018\u00010PH\u0002J\u0007\u0010\u00bc\u0002\u001a\u00020UJ\u001b\u0010\u00bc\u0002\u001a\u00020U2\u0007\u0010\u00bf\u0001\u001a\u00020L2\u0007\u0010\u00c0\u0001\u001a\u00020kH\u0002J\u0012\u0010\u00bd\u0002\u001a\u00020U2\u0007\u0010\u00be\u0002\u001a\u00020]H\u0002J\t\u0010\u00bf\u0002\u001a\u00020UH\u0002J\t\u0010\u00c0\u0002\u001a\u00020UH\u0002J\u0013\u0010\u00c1\u0002\u001a\u00020U2\b\u0010\u00c2\u0002\u001a\u00030\u00b9\u0001H\u0002J\u0012\u0010\u00c3\u0002\u001a\u00020U2\u0007\u0010\u00c4\u0002\u001a\u00020kH\u0002J\u0010\u0010\u00c5\u0002\u001a\u00020U2\u0007\u0010\u0086\u0002\u001a\u00020cJ<\u0010\u00c6\u0002\u001a\u00020U2\b\u0010\u00c7\u0002\u001a\u00030\u00c8\u00022\'\b\u0002\u0010\u00c9\u0002\u001a \u0012\u0004\u0012\u00020]\u0012\u0002\b\u0003\u0018\u00010\u00ca\u0002j\u000f\u0012\u0004\u0012\u00020]\u0012\u0002\b\u0003\u0018\u0001`\u00cb\u0002H\u0002J\t\u0010\u00cc\u0002\u001a\u00020UH\u0002J\u000e\u0010\u00cd\u0002\u001a\u00020U*\u00030\u00b5\u0001H\u0002J\u001c\u0010\u00ce\u0002\u001a\n\u0012\u0005\u0012\u00030\u00c6\u00010\u0094\u0001*\t\u0012\u0004\u0012\u00020g0\u0094\u0001H\u0002J\r\u0010\u00cf\u0002\u001a\u00020k*\u00020KH\u0002R \u0010H\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020K\u0012\u0004\u0012\u00020L0J0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010M\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020P\u0012\b\u0012\u00060Lj\u0002`Q0J0O0NX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0O0NX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010X\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020[0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020]0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010`\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020a0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020e0O0IX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010f\u001a\u00020g8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bh\u0010iR\u000e\u0010E\u001a\u00020FX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020kX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020kX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020kX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010n\u001a\u00020k8F\u00a2\u0006\u0006\u001a\u0004\bn\u0010oR\u000e\u0010p\u001a\u00020kX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010q\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020L0J0O0r\u00a2\u0006\b\n\u0000\u001a\u0004\bs\u0010tR\u001d\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0O0r\u00a2\u0006\b\n\u0000\u001a\u0004\bv\u0010tR\u001d\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U0O0r\u00a2\u0006\b\n\u0000\u001a\u0004\bx\u0010tR\u001d\u0010y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0O0r\u00a2\u0006\b\n\u0000\u001a\u0004\bz\u0010tR\u001d\u0010{\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0O0r\u00a2\u0006\b\n\u0000\u001a\u0004\b|\u0010tR-\u0010}\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020[ \u007f*\n\u0012\u0004\u0012\u00020[\u0018\u00010O0O0~\u00a2\u0006\n\n\u0000\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R \u0010\u0082\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0083\u00010O0r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010tR\u001f\u0010\u0085\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0O0r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010tR \u0010\u0087\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020]0O0I\u00a2\u0006\n\n\u0000\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R.\u0010\u008a\u0001\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020_ \u007f*\n\u0012\u0004\u0012\u00020_\u0018\u00010O0O0~\u00a2\u0006\n\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u0081\u0001R\u001f\u0010\u008c\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020a0O0r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008d\u0001\u0010tR\u001f\u0010\u008e\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0O0r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008f\u0001\u0010tR \u0010\u0090\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0091\u00010O0r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010tR\u001b\u0010\u0093\u0001\u001a\t\u0012\u0004\u0012\u00020g0\u0094\u00018F\u00a2\u0006\b\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020DX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0097\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020e0O0r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000f\u0010\u0099\u0001\u001a\u00020kX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u009a\u0001\u001a\t\u0012\u0005\u0012\u00030\u009b\u00010r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u009c\u0001\u0010tR\u0016\u0010\u009d\u0001\u001a\t\u0012\u0005\u0012\u00030\u009e\u00010rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u009f\u0001\u001a\t\u0012\u0005\u0012\u00030\u00a0\u00010r\u00a2\u0006\t\n\u0000\u001a\u0005\b\u00a1\u0001\u0010tR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u00da\u0002"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "siteItemsBuilder", "Lorg/wordpress/android/ui/mysite/items/SiteItemsBuilder;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "wpMediaUtilsWrapper", "Lorg/wordpress/android/util/WPMediaUtilsWrapper;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "fluxCUtilsWrapper", "Lorg/wordpress/android/util/FluxCUtilsWrapper;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "siteIconUploadHandler", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler;", "siteStoriesHandler", "Lorg/wordpress/android/ui/mysite/SiteStoriesHandler;", "displayUtilsWrapper", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "quickStartRepository", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;", "quickStartCardBuilder", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;", "siteInfoHeaderCardBuilder", "Lorg/wordpress/android/ui/mysite/cards/siteinfo/SiteInfoHeaderCardBuilder;", "homePageDataLoader", "Lorg/wordpress/android/ui/mysite/HomePageDataLoader;", "quickStartDynamicCardsFeatureConfig", "Lorg/wordpress/android/util/config/QuickStartDynamicCardsFeatureConfig;", "quickStartUtilsWrapper", "Lorg/wordpress/android/util/QuickStartUtilsWrapper;", "snackbarSequencer", "Lorg/wordpress/android/util/SnackbarSequencer;", "cardsBuilder", "Lorg/wordpress/android/ui/mysite/cards/CardsBuilder;", "dynamicCardsBuilder", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardsBuilder;", "landOnTheEditorFeatureConfig", "Lorg/wordpress/android/util/config/LandOnTheEditorFeatureConfig;", "mySiteSourceManager", "Lorg/wordpress/android/ui/mysite/MySiteSourceManager;", "cardsTracker", "Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker;", "siteItemsTracker", "Lorg/wordpress/android/ui/mysite/items/SiteItemsTracker;", "domainRegistrationCardShownTracker", "Lorg/wordpress/android/ui/mysite/cards/DomainRegistrationCardShownTracker;", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "mySiteDashboardTabsFeatureConfig", "Lorg/wordpress/android/util/config/MySiteDashboardTabsFeatureConfig;", "bloggingPromptsFeatureConfig", "Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "bloggingPromptsCardAnalyticsTracker", "Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;", "quickStartTracker", "Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/mysite/items/SiteItemsBuilder;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/util/WPMediaUtilsWrapper;Lorg/wordpress/android/util/MediaUtilsWrapper;Lorg/wordpress/android/util/FluxCUtilsWrapper;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler;Lorg/wordpress/android/ui/mysite/SiteStoriesHandler;Lorg/wordpress/android/util/DisplayUtilsWrapper;Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;Lorg/wordpress/android/ui/mysite/cards/siteinfo/SiteInfoHeaderCardBuilder;Lorg/wordpress/android/ui/mysite/HomePageDataLoader;Lorg/wordpress/android/util/config/QuickStartDynamicCardsFeatureConfig;Lorg/wordpress/android/util/QuickStartUtilsWrapper;Lorg/wordpress/android/util/SnackbarSequencer;Lorg/wordpress/android/ui/mysite/cards/CardsBuilder;Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardsBuilder;Lorg/wordpress/android/util/config/LandOnTheEditorFeatureConfig;Lorg/wordpress/android/ui/mysite/MySiteSourceManager;Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker;Lorg/wordpress/android/ui/mysite/items/SiteItemsTracker;Lorg/wordpress/android/ui/mysite/cards/DomainRegistrationCardShownTracker;Lorg/wordpress/android/util/BuildConfigWrapper;Lorg/wordpress/android/util/config/MySiteDashboardTabsFeatureConfig;Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;Lorg/wordpress/android/ui/quickstart/QuickStartTracker;Lorg/wordpress/android/fluxc/Dispatcher;)V", "_activeTaskPosition", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Pair;", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "", "_onAnswerBloggingPrompt", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/fluxc/model/SiteModel;", "Lorg/wordpress/android/ui/mysite/PromptID;", "_onBasicDialogShown", "Lorg/wordpress/android/ui/mysite/SiteDialogModel;", "_onBloggingPromptsLearnMore", "", "_onDynamicCardMenuShown", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuFragment$DynamicCardMenuModel;", "_onMediaUpload", "Lorg/wordpress/android/fluxc/model/MediaModel;", "_onNavigation", "Lorg/wordpress/android/ui/mysite/SiteNavigationAction;", "_onShare", "", "_onSnackbarMessage", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_onTechInputDialogShown", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TextInputDialogModel;", "_onTrackWithTabSource", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$MySiteTrackWithTabSource;", "_selectTab", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabNavigation;", "defaultABExperimentTab", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "getDefaultABExperimentTab", "()Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "isBloggingPromptsFeatureConfigEnabled", "", "isDefaultABExperimentTabSet", "isMySiteDashboardTabsFeatureConfigEnabled", "isMySiteTabsEnabled", "()Z", "isSiteSelected", "onAnswerBloggingPrompt", "Landroidx/lifecycle/LiveData;", "getOnAnswerBloggingPrompt", "()Landroidx/lifecycle/LiveData;", "onBasicDialogShown", "getOnBasicDialogShown", "onBloggingPromptsLearnMore", "getOnBloggingPromptsLearnMore", "onDynamicCardMenuShown", "getOnDynamicCardMenuShown", "onMediaUpload", "getOnMediaUpload", "onNavigation", "Landroidx/lifecycle/MediatorLiveData;", "kotlin.jvm.PlatformType", "getOnNavigation", "()Landroidx/lifecycle/MediatorLiveData;", "onQuickStartMySitePrompts", "Lorg/wordpress/android/ui/quickstart/QuickStartMySitePrompts;", "getOnQuickStartMySitePrompts", "onScrollTo", "getOnScrollTo", "onShare", "getOnShare", "()Landroidx/lifecycle/MutableLiveData;", "onSnackbarMessage", "getOnSnackbarMessage", "onTextInputDialogShown", "getOnTextInputDialogShown", "onTrackWithTabSource", "getOnTrackWithTabSource", "onUploadedItem", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel;", "getOnUploadedItem", "orderedTabTypes", "", "getOrderedTabTypes", "()Ljava/util/List;", "selectTab", "getSelectTab", "shouldMarkUpdateSiteTitleTaskComplete", "state", "Lorg/wordpress/android/ui/mysite/MySiteUiState;", "getState", "tabsUiState", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "uiModel", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$UiModel;", "getUiModel", "buildMediaModel", "file", "Ljava/io/File;", "site", "buildNoSiteState", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State$NoSites;", "buildSiteSelectedState", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "activeTask", "isDomainCreditAvailable", "quickStartCategories", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartCategory;", "pinnedDynamicCard", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "visibleDynamicCards", "backupAvailable", "scanAvailable", "cardsUpdate", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$CardsUpdate;", "bloggingPromptUpdate", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$BloggingPromptUpdate;", "buildSiteSelectedStateAndScroll", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State$SiteSelected;", "showSiteIconProgressBar", "checkAndShowQuickStartNotice", "checkAndStartLandOnTheEditor", "isNewSite", "checkAndStartQuickStart", "siteLocalId", "isSiteTitleTaskCompleted", "clearActiveQuickStartTask", "createTabsUiState", "dismissQuickStartNotice", "domainRegistrationClick", "findUiStateForTab", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState$TabUiState;", "tabType", "getCardTypeExclusionFiltersForTab", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "getPositionOfQuickStartItem", "siteItems", "getSiteInfoToolbarViewParams", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "getStatsNavigationActionForSite", "handleCropResult", "croppedUri", "Landroid/net/Uri;", "success", "handleSelectedSiteIcon", "mediaId", "", "handleStoriesPhotoPickerResult", "data", "Landroid/content/Intent;", "handleSuccessfulDomainRegistrationResult", "email", "handleSuccessfulLoginResult", "handleTakenSiteIcon", "iconUrl", "source", "Lorg/wordpress/android/ui/photopicker/PhotoPickerActivity$PhotoPickerMediaSource;", "hasSiteHeaderUpdates", "nextSiteInfoHeaderCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard;", "iconClick", "ignoreQuickStart", "isRefreshing", "isSiteHeaderQuickStartTask", "quickStartTask", "isValidQuickStartFocusPosition", "position", "navigateToTodaysStats", "onAddSitePressed", "onAvatarPressed", "onBloggingPromptAnswerClick", "promptId", "onBloggingPromptShareClick", "message", "onBloggingPromptSkipClicked", "onBloggingPromptsLearnMoreClicked", "onCleared", "onCreateSiteResult", "onDashboardErrorRetry", "onDialogInteraction", "interaction", "Lorg/wordpress/android/ui/posts/BasicDialogViewModel$DialogInteraction;", "onDynamicCardMoreClick", "model", "onGetMoreViewsClick", "onItemClick", "action", "Lorg/wordpress/android/ui/mysite/items/listitem/ListItemAction;", "onPostCardFooterLinkClick", "postCardType", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "onPostItemClick", "params", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams$PostItemClickParams;", "onPostUploaded", "event", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostUploaded;", "onQuickLinkRibbonMediaClick", "onQuickLinkRibbonPagesClick", "onQuickLinkRibbonPostsClick", "onQuickLinkRibbonStatsClick", "onQuickStartBlockRemoveMenuItemClick", "onQuickStartFullScreenDialogDismiss", "onQuickStartMenuInteraction", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel$DynamicCardMenuInteraction;", "onQuickStartTaskCardClick", "task", "onQuickStartTaskTypeItemClick", "type", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "onRemoveNextStepsDialogNegativeButtonClicked", "onRemoveNextStepsDialogPositiveButtonClicked", "onResume", "onSiteNameChooserDismissed", "onSiteNameChosen", "input", "onSitePicked", "onTabChanged", "onTodaysStatsCardClick", "onTodaysStatsCardFooterLinkClick", "orderForDisplay", "infoItem", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$InfoItem;", "cards", "dynamicCards", "performFirstStepAfterSiteCreation", "quickActionMediaClick", "quickActionPagesClick", "quickActionPostsClick", "quickActionStatsClick", "refresh", "isPullToRefresh", "requestTabStepPendingTask", "pendingTask", "resetShownTrackers", "scrollToQuickStartTaskIfNecessary", "selectDefaultTabIfNeeded", "setActionableEmptyViewGone", "isVisible", "setGone", "Lkotlin/Function0;", "setActionableEmptyViewVisible", "setVisible", "shouldEnableQuickLinkRibbonFocusPoints", "shouldEnableSiteItemsFocusPoints", "shouldIncludeDynamicCards", "shouldUpdateViewPager", "showQuickStartDialog", "siteModel", "startQuickStart", "startSiteIconUpload", "filePath", "switchSiteClick", "titleClick", "trackCardsAndItemsShownIfNeeded", "siteSelected", "trackTabChanged", "isSiteMenu", "trackWithTabSource", "trackWithTabSourceIfNeeded", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "properties", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "urlClick", "checkAndShowSnackbarError", "mapToTabUiStates", "showInSiteMenu", "Companion", "MySiteTrackWithTabSource", "SiteIdToState", "SiteInfoHeaderState", "SiteInfoToolbarViewParams", "State", "TabNavigation", "TabsUiState", "TextInputDialogModel", "UiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.ui.mysite.items.SiteItemsBuilder siteItemsBuilder = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.ui.mysite.SiteIconUploadHandler siteIconUploadHandler = null;
    private final org.wordpress.android.ui.mysite.SiteStoriesHandler siteStoriesHandler = null;
    private final org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper = null;
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository = null;
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder quickStartCardBuilder = null;
    private final org.wordpress.android.ui.mysite.cards.siteinfo.SiteInfoHeaderCardBuilder siteInfoHeaderCardBuilder = null;
    private final org.wordpress.android.ui.mysite.HomePageDataLoader homePageDataLoader = null;
    private final org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig = null;
    private final org.wordpress.android.util.QuickStartUtilsWrapper quickStartUtilsWrapper = null;
    private final org.wordpress.android.util.SnackbarSequencer snackbarSequencer = null;
    private final org.wordpress.android.ui.mysite.cards.CardsBuilder cardsBuilder = null;
    private final org.wordpress.android.ui.mysite.dynamiccards.DynamicCardsBuilder dynamicCardsBuilder = null;
    private final org.wordpress.android.util.config.LandOnTheEditorFeatureConfig landOnTheEditorFeatureConfig = null;
    private final org.wordpress.android.ui.mysite.MySiteSourceManager mySiteSourceManager = null;
    private final org.wordpress.android.ui.mysite.cards.dashboard.CardsTracker cardsTracker = null;
    private final org.wordpress.android.ui.mysite.items.SiteItemsTracker siteItemsTracker = null;
    private final org.wordpress.android.ui.mysite.cards.DomainRegistrationCardShownTracker domainRegistrationCardShownTracker = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker = null;
    private final org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private boolean isDefaultABExperimentTabSet = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _onSnackbarMessage = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.TextInputDialogModel>> _onTechInputDialogShown = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteDialogModel>> _onBasicDialogShown = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuFragment.DynamicCardMenuModel>> _onDynamicCardMenuShown = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> _onNavigation = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.model.MediaModel>> _onMediaUpload = null;
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, java.lang.Integer>> _activeTaskPosition = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.String>> _onShare = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.MySiteTrackWithTabSource>> _onTrackWithTabSource = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.TabNavigation>> _selectTab = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.Event<kotlin.Pair<org.wordpress.android.fluxc.model.SiteModel, java.lang.Integer>>> _onAnswerBloggingPrompt = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _onBloggingPromptsLearnMore = null;
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState> tabsUiState = null;
    private boolean isSiteSelected = false;
    private final boolean isMySiteDashboardTabsFeatureConfigEnabled = false;
    private final boolean isBloggingPromptsFeatureConfigEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> onScrollTo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> onSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.quickstart.QuickStartMySitePrompts>> onQuickStartMySitePrompts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.TextInputDialogModel>> onTextInputDialogShown = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteDialogModel>> onBasicDialogShown = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuFragment.DynamicCardMenuModel>> onDynamicCardMenuShown = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.model.MediaModel>> onMediaUpload = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel>> onUploadedItem = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.String>> onShare = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Pair<org.wordpress.android.fluxc.model.SiteModel, java.lang.Integer>>> onAnswerBloggingPrompt = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> onBloggingPromptsLearnMore = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.MySiteTrackWithTabSource>> onTrackWithTabSource = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.TabNavigation>> selectTab = null;
    private boolean shouldMarkUpdateSiteTitleTaskComplete = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteViewModel.UiModel> uiModel = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.MySiteViewModel.Companion Companion = null;
    private static final int MIN_DISPLAY_PX_HEIGHT_NO_SITE_IMAGE = 600;
    private static final int LIST_INDEX_NO_ACTIVE_QUICK_START_ITEM = -1;
    private static final java.lang.String TYPE = "type";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG_ADD_SITE_ICON_DIALOG = "TAG_ADD_SITE_ICON_DIALOG";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG_CHANGE_SITE_ICON_DIALOG = "TAG_CHANGE_SITE_ICON_DIALOG";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG_REMOVE_NEXT_STEPS_DIALOG = "TAG_REMOVE_NEXT_STEPS_DIALOG";
    public static final int SITE_NAME_CHANGE_CALLBACK_ID = 1;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_QUICK_START_TASK = "ARG_QUICK_START_TASK";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HIDE_WP_ADMIN_GMT_TIME_ZONE = "GMT";
    public static final long LIST_SCROLL_DELAY_MS = 500L;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MY_SITE_TAB = "tab";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAB_SOURCE = "tab_source";
    
    @javax.inject.Inject()
    public MySiteViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.items.SiteItemsBuilder siteItemsBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.FluxCUtilsWrapper fluxCUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SiteIconUploadHandler siteIconUploadHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SiteStoriesHandler siteStoriesHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder quickStartCardBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.siteinfo.SiteInfoHeaderCardBuilder siteInfoHeaderCardBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.HomePageDataLoader homePageDataLoader, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.QuickStartUtilsWrapper quickStartUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarSequencer snackbarSequencer, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.CardsBuilder cardsBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.dynamiccards.DynamicCardsBuilder dynamicCardsBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.LandOnTheEditorFeatureConfig landOnTheEditorFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteSourceManager mySiteSourceManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.CardsTracker cardsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.items.SiteItemsTracker siteItemsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.DomainRegistrationCardShownTracker domainRegistrationCardShownTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig mySiteDashboardTabsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super(null);
    }
    
    public final boolean isMySiteTabsEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.mysite.tabs.MySiteTabType> getOrderedTabTypes() {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.tabs.MySiteTabType getDefaultABExperimentTab() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> getOnScrollTo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getOnSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.quickstart.QuickStartMySitePrompts>> getOnQuickStartMySitePrompts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.TextInputDialogModel>> getOnTextInputDialogShown() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteDialogModel>> getOnBasicDialogShown() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuFragment.DynamicCardMenuModel>> getOnDynamicCardMenuShown() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> getOnNavigation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.model.MediaModel>> getOnMediaUpload() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel>> getOnUploadedItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.String>> getOnShare() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Pair<org.wordpress.android.fluxc.model.SiteModel, java.lang.Integer>>> getOnAnswerBloggingPrompt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getOnBloggingPromptsLearnMore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.MySiteTrackWithTabSource>> getOnTrackWithTabSource() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.MySiteViewModel.TabNavigation>> getSelectTab() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteViewModel.UiModel> getUiModel() {
        return null;
    }
    
    private final void checkAndShowSnackbarError(org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate $this$checkAndShowSnackbarError) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    private final org.wordpress.android.ui.mysite.MySiteViewModel.State.SiteSelected buildSiteSelectedStateAndScroll(org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState tabsUiState, org.wordpress.android.fluxc.model.SiteModel site, boolean showSiteIconProgressBar, org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask, boolean isDomainCreditAvailable, java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> quickStartCategories, org.wordpress.android.fluxc.model.DynamicCardType pinnedDynamicCard, java.util.List<? extends org.wordpress.android.fluxc.model.DynamicCardType> visibleDynamicCards, boolean backupAvailable, boolean scanAvailable, org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate cardsUpdate, org.wordpress.android.ui.mysite.MySiteUiState.PartialState.BloggingPromptUpdate bloggingPromptUpdate) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams getSiteInfoToolbarViewParams() {
        return null;
    }
    
    private final int getPositionOfQuickStartItem(java.util.Map<org.wordpress.android.ui.mysite.tabs.MySiteTabType, ? extends java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem>> siteItems, org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask) {
        return 0;
    }
    
    private final boolean showInSiteMenu(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask $this$showInSiteMenu) {
        return false;
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    private final java.util.Map<org.wordpress.android.ui.mysite.tabs.MySiteTabType, java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem>> buildSiteSelectedState(org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask, boolean isDomainCreditAvailable, java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> quickStartCategories, org.wordpress.android.fluxc.model.DynamicCardType pinnedDynamicCard, java.util.List<? extends org.wordpress.android.fluxc.model.DynamicCardType> visibleDynamicCards, boolean backupAvailable, boolean scanAvailable, org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate cardsUpdate, org.wordpress.android.ui.mysite.MySiteUiState.PartialState.BloggingPromptUpdate bloggingPromptUpdate) {
        return null;
    }
    
    private final boolean shouldEnableQuickLinkRibbonFocusPoints() {
        return false;
    }
    
    private final boolean shouldEnableSiteItemsFocusPoints() {
        return false;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Type> getCardTypeExclusionFiltersForTab(org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType) {
        return null;
    }
    
    private final boolean shouldIncludeDynamicCards(org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType) {
        return false;
    }
    
    @kotlin.Suppress(names = {"EmptyFunctionBlock"})
    private final void onGetMoreViewsClick() {
    }
    
    private final void onTodaysStatsCardFooterLinkClick() {
    }
    
    private final void onTodaysStatsCardClick() {
    }
    
    private final void navigateToTodaysStats() {
    }
    
    private final org.wordpress.android.ui.mysite.MySiteViewModel.State.NoSites buildNoSiteState() {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> orderForDisplay(org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.InfoItem infoItem, java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> cards, java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> dynamicCards, java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> siteItems) {
        return null;
    }
    
    private final void scrollToQuickStartTaskIfNecessary(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask quickStartTask, int position) {
    }
    
    private final boolean isValidQuickStartFocusPosition(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask quickStartTask, int position) {
        return false;
    }
    
    private final boolean isSiteHeaderQuickStartTask(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask quickStartTask) {
        return false;
    }
    
    public final void onTabChanged(int position) {
    }
    
    private final void requestTabStepPendingTask(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask pendingTask) {
    }
    
    @kotlin.Suppress(names = {"ComplexMethod"})
    private final void onItemClick(org.wordpress.android.ui.mysite.items.listitem.ListItemAction action) {
    }
    
    private final void onDynamicCardMoreClick(org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuFragment.DynamicCardMenuModel model) {
    }
    
    private final void onQuickStartBlockRemoveMenuItemClick() {
    }
    
    private final void onQuickStartTaskTypeItemClick(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType type) {
    }
    
    public final void onQuickStartTaskCardClick(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    public final void onQuickStartFullScreenDialogDismiss() {
    }
    
    private final void titleClick() {
    }
    
    private final void iconClick() {
    }
    
    private final void urlClick() {
    }
    
    private final void switchSiteClick() {
    }
    
    private final void quickActionStatsClick() {
    }
    
    private final void quickActionPagesClick() {
    }
    
    private final void quickActionPostsClick() {
    }
    
    private final void quickActionMediaClick() {
    }
    
    private final void onQuickLinkRibbonStatsClick() {
    }
    
    private final void onQuickLinkRibbonPagesClick() {
    }
    
    private final void onQuickLinkRibbonPostsClick() {
    }
    
    private final void onQuickLinkRibbonMediaClick() {
    }
    
    private final void domainRegistrationClick() {
    }
    
    public final void refresh(boolean isPullToRefresh) {
    }
    
    public final void onResume() {
    }
    
    public final void clearActiveQuickStartTask() {
    }
    
    public final void checkAndShowQuickStartNotice() {
    }
    
    public final void dismissQuickStartNotice() {
    }
    
    public final void onSiteNameChosen(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
    }
    
    public final void onSiteNameChooserDismissed() {
    }
    
    public final void onDialogInteraction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.BasicDialogViewModel.DialogInteraction interaction) {
    }
    
    private final void onRemoveNextStepsDialogPositiveButtonClicked() {
    }
    
    private final void onRemoveNextStepsDialogNegativeButtonClicked() {
    }
    
    public final void handleTakenSiteIcon(@org.jetbrains.annotations.Nullable()
    java.lang.String iconUrl, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.photopicker.PhotoPickerActivity.PhotoPickerMediaSource source) {
    }
    
    public final void handleSelectedSiteIcon(long mediaId) {
    }
    
    public final void handleCropResult(@org.jetbrains.annotations.Nullable()
    android.net.Uri croppedUri, boolean success) {
    }
    
    public final void handleSuccessfulLoginResult() {
    }
    
    public final void handleSuccessfulDomainRegistrationResult(@org.jetbrains.annotations.Nullable()
    java.lang.String email) {
    }
    
    @kotlin.Suppress(names = {"ReturnCount"})
    private final void startSiteIconUpload(java.lang.String filePath) {
    }
    
    private final org.wordpress.android.fluxc.model.MediaModel buildMediaModel(java.io.File file, org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.SiteNavigationAction getStatsNavigationActionForSite(org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    public final void onAvatarPressed() {
    }
    
    public final void onAddSitePressed() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void handleStoriesPhotoPickerResult(@org.jetbrains.annotations.NotNull()
    android.content.Intent data) {
    }
    
    public final void onCreateSiteResult() {
    }
    
    public final void onSitePicked() {
    }
    
    public final void performFirstStepAfterSiteCreation(int siteLocalId, boolean isSiteTitleTaskCompleted, boolean isNewSite) {
    }
    
    private final void checkAndStartLandOnTheEditor(boolean isNewSite) {
    }
    
    public final void checkAndStartQuickStart(int siteLocalId, boolean isSiteTitleTaskCompleted, boolean isNewSite) {
    }
    
    private final void startQuickStart(int siteLocalId, boolean isSiteTitleTaskCompleted) {
    }
    
    public final void onQuickStartMenuInteraction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel.DynamicCardMenuInteraction interaction) {
    }
    
    private final void showQuickStartDialog(org.wordpress.android.fluxc.model.SiteModel siteModel) {
    }
    
    public final void startQuickStart() {
    }
    
    public final void ignoreQuickStart() {
    }
    
    private final void onPostItemClick(org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams params) {
    }
    
    private final void onDashboardErrorRetry() {
    }
    
    private final void onPostCardFooterLinkClick(org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType) {
    }
    
    private final void onBloggingPromptShareClick(java.lang.String message) {
    }
    
    private final void onBloggingPromptAnswerClick(int promptId) {
    }
    
    private final void onBloggingPromptSkipClicked() {
    }
    
    public final boolean isRefreshing() {
        return false;
    }
    
    public final void setActionableEmptyViewGone(boolean isVisible, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> setGone) {
    }
    
    public final void setActionableEmptyViewVisible(boolean isVisible, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> setVisible) {
    }
    
    public final void trackWithTabSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteViewModel.MySiteTrackWithTabSource event) {
    }
    
    public final void onBloggingPromptsLearnMoreClicked() {
    }
    
    private final void trackWithTabSourceIfNeeded(org.wordpress.android.analytics.AnalyticsTracker.Stat stat, java.util.HashMap<java.lang.String, ?> properties) {
    }
    
    @kotlin.Suppress(names = {"NestedBlockDepth"})
    private final void selectDefaultTabIfNeeded() {
    }
    
    private final void trackCardsAndItemsShownIfNeeded(org.wordpress.android.ui.mysite.MySiteViewModel.State.SiteSelected siteSelected) {
    }
    
    private final void resetShownTrackers() {
    }
    
    private final void trackTabChanged(boolean isSiteMenu) {
    }
    
    private final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState findUiStateForTab(org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState createTabsUiState() {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> mapToTabUiStates(java.util.List<? extends org.wordpress.android.ui.mysite.tabs.MySiteTabType> $this$mapToTabUiStates) {
        return null;
    }
    
    private final boolean shouldUpdateViewPager() {
        return false;
    }
    
    private final boolean hasSiteHeaderUpdates(org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard nextSiteInfoHeaderCard) {
        return false;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onPostUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostUploaded event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$UiModel;", "", "accountAvatarUrl", "", "state", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State;", "(Ljava/lang/String;Lorg/wordpress/android/ui/mysite/MySiteViewModel$State;)V", "getAccountAvatarUrl", "()Ljava/lang/String;", "getState", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$State;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiModel {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String accountAvatarUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteViewModel.State state = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.UiModel copy(@org.jetbrains.annotations.NotNull()
        java.lang.String accountAvatarUrl, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteViewModel.State state) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public UiModel(@org.jetbrains.annotations.NotNull()
        java.lang.String accountAvatarUrl, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteViewModel.State state) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAccountAvatarUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.State component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.State getState() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0001\u0002\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$State;", "", "()V", "siteInfoToolbarViewParams", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "getSiteInfoToolbarViewParams", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "tabsUiState", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "getTabsUiState", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "NoSites", "SiteSelected", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State$SiteSelected;", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State$NoSites;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class State {
        
        private State() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState getTabsUiState();
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams getSiteInfoToolbarViewParams();
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003JW\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$State$SiteSelected;", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State;", "tabsUiState", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "siteInfoToolbarViewParams", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "siteInfoHeaderState", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoHeaderState;", "cardAndItems", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "siteMenuCardsAndItems", "dashboardCardsAndItems", "(Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoHeaderState;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getCardAndItems", "()Ljava/util/List;", "getDashboardCardsAndItems", "getSiteInfoHeaderState", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoHeaderState;", "getSiteInfoToolbarViewParams", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "getSiteMenuCardsAndItems", "getTabsUiState", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SiteSelected extends org.wordpress.android.ui.mysite.MySiteViewModel.State {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState tabsUiState = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams siteInfoToolbarViewParams = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoHeaderState siteInfoHeaderState = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> cardAndItems = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> siteMenuCardsAndItems = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> dashboardCardsAndItems = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.State.SiteSelected copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState tabsUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams siteInfoToolbarViewParams, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoHeaderState siteInfoHeaderState, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> cardAndItems, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> siteMenuCardsAndItems, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> dashboardCardsAndItems) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public SiteSelected(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState tabsUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams siteInfoToolbarViewParams, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoHeaderState siteInfoHeaderState, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> cardAndItems, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> siteMenuCardsAndItems, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem> dashboardCardsAndItems) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState getTabsUiState() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams getSiteInfoToolbarViewParams() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoHeaderState component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoHeaderState getSiteInfoHeaderState() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> getCardAndItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> component5() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> getSiteMenuCardsAndItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> component6() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem> getDashboardCardsAndItems() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$State$NoSites;", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State;", "tabsUiState", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "siteInfoToolbarViewParams", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "shouldShowImage", "", "(Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;Z)V", "getShouldShowImage", "()Z", "getSiteInfoToolbarViewParams", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "getTabsUiState", "()Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NoSites extends org.wordpress.android.ui.mysite.MySiteViewModel.State {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState tabsUiState = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams siteInfoToolbarViewParams = null;
            private final boolean shouldShowImage = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.State.NoSites copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState tabsUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams siteInfoToolbarViewParams, boolean shouldShowImage) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public NoSites(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState tabsUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams siteInfoToolbarViewParams, boolean shouldShowImage) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState getTabsUiState() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams getSiteInfoToolbarViewParams() {
                return null;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getShouldShowImage() {
                return false;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoHeaderState;", "", "hasUpdates", "", "siteInfoHeader", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard;", "(ZLorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard;)V", "getHasUpdates", "()Z", "getSiteInfoHeader", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteInfoHeaderState {
        private final boolean hasUpdates = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard siteInfoHeader = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoHeaderState copy(boolean hasUpdates, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard siteInfoHeader) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SiteInfoHeaderState(boolean hasUpdates, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard siteInfoHeader) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getHasUpdates() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard getSiteInfoHeader() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001bB\'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState;", "", "showTabs", "", "tabUiStates", "", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState$TabUiState;", "shouldUpdateViewPager", "(ZLjava/util/List;Z)V", "getShouldUpdateViewPager", "()Z", "getShowTabs", "getTabUiStates", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "update", "quickStartTabStep", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartTabStep;", "TabUiState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TabsUiState {
        private final boolean showTabs = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> tabUiStates = null;
        private final boolean shouldUpdateViewPager = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState copy(boolean showTabs, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> tabUiStates, boolean shouldUpdateViewPager) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public TabsUiState(boolean showTabs, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> tabUiStates, boolean shouldUpdateViewPager) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getShowTabs() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> getTabUiStates() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getShouldUpdateViewPager() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> update(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartTabStep quickStartTabStep) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tH\u00c6\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState$TabUiState;", "", "label", "Lorg/wordpress/android/ui/utils/UiString;", "tabType", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "showQuickStartFocusPoint", "", "pendingTask", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;ZLorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;)V", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getPendingTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "getShowQuickStartFocusPoint", "()Z", "getTabType", "()Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class TabUiState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString label = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType = null;
            private final boolean showQuickStartFocusPoint = false;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask pendingTask = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType, boolean showQuickStartFocusPoint, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask pendingTask) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public TabUiState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType, boolean showQuickStartFocusPoint, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask pendingTask) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.tabs.MySiteTabType component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.tabs.MySiteTabType getTabType() {
                return null;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getShowQuickStartFocusPoint() {
                return false;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component4() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getPendingTask() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteInfoToolbarViewParams;", "", "appBarHeight", "", "toolbarBottomMargin", "headerVisible", "", "appBarLiftOnScroll", "(IIZZ)V", "getAppBarHeight", "()I", "getAppBarLiftOnScroll", "()Z", "getHeaderVisible", "getToolbarBottomMargin", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteInfoToolbarViewParams {
        private final int appBarHeight = 0;
        private final int toolbarBottomMargin = 0;
        private final boolean headerVisible = false;
        private final boolean appBarLiftOnScroll = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteInfoToolbarViewParams copy(@androidx.annotation.DimenRes()
        int appBarHeight, @androidx.annotation.DimenRes()
        int toolbarBottomMargin, boolean headerVisible, boolean appBarLiftOnScroll) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SiteInfoToolbarViewParams(@androidx.annotation.DimenRes()
        int appBarHeight, @androidx.annotation.DimenRes()
        int toolbarBottomMargin, boolean headerVisible, boolean appBarLiftOnScroll) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getAppBarHeight() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getToolbarBottomMargin() {
            return 0;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getHeaderVisible() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getAppBarLiftOnScroll() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabNavigation;", "", "position", "", "smoothAnimation", "", "(IZ)V", "getPosition", "()I", "getSmoothAnimation", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TabNavigation {
        private final int position = 0;
        private final boolean smoothAnimation = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.TabNavigation copy(int position, boolean smoothAnimation) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public TabNavigation(int position, boolean smoothAnimation) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPosition() {
            return 0;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getSmoothAnimation() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\tH\u00c6\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0011R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$TextInputDialogModel;", "", "callbackId", "", "title", "initialText", "", "hint", "isMultiline", "", "isInputEnabled", "(IILjava/lang/String;IZZ)V", "getCallbackId", "()I", "getHint", "getInitialText", "()Ljava/lang/String;", "()Z", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TextInputDialogModel {
        private final int callbackId = 0;
        private final int title = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String initialText = null;
        private final int hint = 0;
        private final boolean isMultiline = false;
        private final boolean isInputEnabled = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.TextInputDialogModel copy(int callbackId, @androidx.annotation.StringRes()
        int title, @org.jetbrains.annotations.NotNull()
        java.lang.String initialText, @androidx.annotation.StringRes()
        int hint, boolean isMultiline, boolean isInputEnabled) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public TextInputDialogModel(int callbackId, @androidx.annotation.StringRes()
        int title, @org.jetbrains.annotations.NotNull()
        java.lang.String initialText, @androidx.annotation.StringRes()
        int hint, boolean isMultiline, boolean isInputEnabled) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getCallbackId() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getTitle() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getInitialText() {
            return null;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getHint() {
            return 0;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean isMultiline() {
            return false;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isInputEnabled() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J$\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteIdToState;", "", "siteId", "", "state", "Lorg/wordpress/android/ui/mysite/MySiteUiState;", "(Ljava/lang/Integer;Lorg/wordpress/android/ui/mysite/MySiteUiState;)V", "getSiteId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getState", "()Lorg/wordpress/android/ui/mysite/MySiteUiState;", "component1", "component2", "copy", "(Ljava/lang/Integer;Lorg/wordpress/android/ui/mysite/MySiteUiState;)Lorg/wordpress/android/ui/mysite/MySiteViewModel$SiteIdToState;", "equals", "", "other", "hashCode", "toString", "", "update", "partialState", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState;", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class SiteIdToState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer siteId = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteUiState state = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteIdToState copy(@org.jetbrains.annotations.Nullable()
        java.lang.Integer siteId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteUiState state) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SiteIdToState(@org.jetbrains.annotations.Nullable()
        java.lang.Integer siteId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteUiState state) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSiteId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteUiState component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteUiState getState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.SiteIdToState update(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteUiState.PartialState partialState) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012$\b\u0002\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u0001`\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J%\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u0001`\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\nH\u00c6\u0003JM\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032$\b\u0002\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u0001`\u00072\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR-\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u0003\u0018\u0001`\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$MySiteTrackWithTabSource;", "", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "properties", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "key", "currentTab", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "(Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;Ljava/util/HashMap;Ljava/lang/String;Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;)V", "getCurrentTab", "()Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "getKey", "()Ljava/lang/String;", "getProperties", "()Ljava/util/HashMap;", "getStat", "()Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MySiteTrackWithTabSource {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.analytics.AnalyticsTracker.Stat stat = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.HashMap<java.lang.String, ?> properties = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String key = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.tabs.MySiteTabType currentTab = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteViewModel.MySiteTrackWithTabSource copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
        java.util.HashMap<java.lang.String, ?> properties, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.tabs.MySiteTabType currentTab) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public MySiteTrackWithTabSource(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
        java.util.HashMap<java.lang.String, ?> properties, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.tabs.MySiteTabType currentTab) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.analytics.AnalyticsTracker.Stat component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.analytics.AnalyticsTracker.Stat getStat() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.HashMap<java.lang.String, ?> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.HashMap<java.lang.String, ?> getProperties() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getKey() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.tabs.MySiteTabType component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.tabs.MySiteTabType getCurrentTab() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteViewModel$Companion;", "", "()V", "ARG_QUICK_START_TASK", "", "HIDE_WP_ADMIN_GMT_TIME_ZONE", "LIST_INDEX_NO_ACTIVE_QUICK_START_ITEM", "", "LIST_SCROLL_DELAY_MS", "", "MIN_DISPLAY_PX_HEIGHT_NO_SITE_IMAGE", "MY_SITE_TAB", "SITE_NAME_CHANGE_CALLBACK_ID", "TAB_SOURCE", "TAG_ADD_SITE_ICON_DIALOG", "TAG_CHANGE_SITE_ICON_DIALOG", "TAG_REMOVE_NEXT_STEPS_DIALOG", "TYPE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}