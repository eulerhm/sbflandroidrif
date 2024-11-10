package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00fa\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002\u0097\u0002B\u00a3\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020#\u0012\b\b\u0001\u0010$\u001a\u00020%\u0012\b\b\u0001\u0010&\u001a\u00020%\u00a2\u0006\u0002\u0010\'J\u0012\u0010\u00a0\u0001\u001a\u0002032\u0007\u0010\u00a1\u0001\u001a\u00020=H\u0002J\u001c\u0010\u00a2\u0001\u001a\u0002032\u0007\u0010\u00a3\u0001\u001a\u00020~2\b\u0010\u00a4\u0001\u001a\u00030\u00a5\u0001H\u0002J\u0012\u0010\u00a6\u0001\u001a\u0002032\u0007\u0010\u00a7\u0001\u001a\u00020EH\u0002J\u0007\u0010\u00a8\u0001\u001a\u000203J\t\u0010\u00a9\u0001\u001a\u000203H\u0002J\u001d\u0010\u00aa\u0001\u001a\u0002032\u0007\u0010\u00a1\u0001\u001a\u00020~2\t\b\u0002\u0010\u00ab\u0001\u001a\u00020)H\u0002J\u001d\u0010\u00ac\u0001\u001a\u0002032\b\u0010\u00a7\u0001\u001a\u00030\u00ad\u00012\b\u0010\u00ae\u0001\u001a\u00030\u00af\u0001H\u0002J\u0012\u0010\u00b0\u0001\u001a\u0002032\u0007\u0010\u00a7\u0001\u001a\u00020EH\u0002J\u0013\u0010\u00b0\u0001\u001a\u0002032\b\u0010\u00a7\u0001\u001a\u00030\u00ad\u0001H\u0002J!\u0010f\u001a\u0002032\b\u0010\u00a1\u0001\u001a\u00030\u00b1\u00012\r\b\u0002\u0010\u00b2\u0001\u001a\u00060)j\u0002`:H\u0002J7\u0010\u00b3\u0001\u001a\u0014\u0012\u0004\u0012\u00020O\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0<0N2\u0007\u0010\u0099\u0001\u001a\u0002082\u0007\u0010\u00b4\u0001\u001a\u00020AH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00b5\u0001J\u0012\u0010\u00b6\u0001\u001a\u0002032\u0007\u0010\u00b7\u0001\u001a\u000208H\u0002J\u0018\u0010\u00b8\u0001\u001a\u0002032\r\u0010\u00b9\u0001\u001a\b\u0012\u0004\u0012\u00020=0<H\u0002J\u001b\u0010\u00ba\u0001\u001a\u0002032\u0007\u0010\u00bb\u0001\u001a\u0002092\u0007\u0010\u00bc\u0001\u001a\u00020)H\u0002J\u001d\u0010\u00bd\u0001\u001a\u0002032\b\u0010\u00be\u0001\u001a\u00030\u00bf\u00012\b\u0010\u00c0\u0001\u001a\u00030\u00c1\u0001H\u0002J\u001b\u0010\u00c2\u0001\u001a\u0002032\u0007\u0010\u00a1\u0001\u001a\u00020=2\u0007\u0010\u00bc\u0001\u001a\u00020)H\u0002J\t\u0010\u00c3\u0001\u001a\u00020)H\u0002J\t\u0010\u00c4\u0001\u001a\u00020)H\u0002J\n\u0010\u00c5\u0001\u001a\u00030\u0092\u0001H\u0002J\t\u0010\u00c6\u0001\u001a\u000203H\u0014J\u0013\u0010\u00c7\u0001\u001a\u0002032\b\u0010\u00a7\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0013\u0010\u00c8\u0001\u001a\u0002032\b\u0010\u00a1\u0001\u001a\u00030\u00b1\u0001H\u0002J\u0013\u0010\u00c9\u0001\u001a\u0002032\b\u0010\u00a1\u0001\u001a\u00030\u00b1\u0001H\u0002J\u0013\u0010\u00ca\u0001\u001a\u0002032\b\u0010\u00a1\u0001\u001a\u00030\u00b1\u0001H\u0002J\u0007\u0010\u00cb\u0001\u001a\u000203J\u0011\u0010\u00cc\u0001\u001a\u0002032\b\u0010\u00cd\u0001\u001a\u00030\u00ad\u0001J)\u0010\u00ce\u0001\u001a\u00020)2\b\u0010\u00cf\u0001\u001a\u00030\u00d0\u00012\b\u0010\u00a7\u0001\u001a\u00030\u00ad\u00012\f\b\u0002\u0010\u00ae\u0001\u001a\u0005\u0018\u00010\u00af\u0001J\u0010\u0010\u00d1\u0001\u001a\u0002032\u0007\u0010\u00d2\u0001\u001a\u00020AJ\u0007\u0010\u00d3\u0001\u001a\u000203J\u001a\u0010\u00d4\u0001\u001a\u0002032\b\u0010\u00d5\u0001\u001a\u00030\u00bf\u00012\u0007\u0010\u00d6\u0001\u001a\u00020GJ\u0019\u0010\u00d7\u0001\u001a\u0002032\u0007\u0010\u00a1\u0001\u001a\u00020~2\u0007\u0010\u00d8\u0001\u001a\u00020~J\u0010\u0010\u00d9\u0001\u001a\u0002032\u0007\u0010\u00da\u0001\u001a\u00020OJ\u0010\u0010\u00db\u0001\u001a\u0002032\u0007\u0010\u00d2\u0001\u001a\u00020AJ\u0007\u0010\u00dc\u0001\u001a\u000203J\u001b\u0010\u00dd\u0001\u001a\u0002032\u0007\u0010\u00b4\u0001\u001a\u00020A2\t\b\u0002\u0010\u00de\u0001\u001a\u00020~J\u0007\u0010\u00df\u0001\u001a\u000203J\u0010\u0010\u00e0\u0001\u001a\u0002032\u0007\u0010\u00e1\u0001\u001a\u00020)J\u0012\u0010\u00e2\u0001\u001a\u0002032\u0007\u0010\u00e3\u0001\u001a\u00020~H\u0007J\u0019\u0010\u00e4\u0001\u001a\u00020)2\u000e\u0010\u00e5\u0001\u001a\t\u0012\u0004\u0012\u0002030\u00e6\u0001H\u0002J5\u0010\u00e7\u0001\u001a\u00020)2 \u0010\u00e5\u0001\u001a\u001b\b\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u0002030\u00e9\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u00ea\u00010\u00e8\u0001H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00eb\u0001J%\u0010\u00ec\u0001\u001a\u0002032\b\u0010\u00a3\u0001\u001a\u00030\u00b1\u00012\u0007\u0010\u00bc\u0001\u001a\u00020)2\u0007\u0010\u00ed\u0001\u001a\u00020)H\u0002J\'\u0010\u00ee\u0001\u001a\u00020R2\b\u0010\u00ef\u0001\u001a\u00030\u00a5\u00012\u0012\b\u0002\u0010\u00f0\u0001\u001a\u000b\u0012\u0004\u0012\u000203\u0018\u00010\u00e6\u0001H\u0002J\u0013\u0010\u0086\u0001\u001a\u0002032\b\u0010\u00a7\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0012\u0010\u00f1\u0001\u001a\u0002032\u0007\u0010\u00a3\u0001\u001a\u00020~H\u0002J\u0013\u0010\u00f2\u0001\u001a\u000203H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00f3\u0001J)\u0010\u00f4\u0001\u001a\u0002032\t\b\u0002\u0010\u00f5\u0001\u001a\u00020C2\t\b\u0002\u0010\u00f6\u0001\u001a\u00020)H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00f7\u0001J\u0012\u0010\u00f8\u0001\u001a\u0002032\u0007\u0010\u00f9\u0001\u001a\u00020~H\u0002J\u001b\u0010\u00fa\u0001\u001a\u0002032\u0007\u0010\u00a7\u0001\u001a\u00020E2\u0007\u0010\u00d8\u0001\u001a\u00020~H\u0002J\u0013\u0010\u00fa\u0001\u001a\u0002032\b\u0010\u00a7\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0012\u0010\u00fb\u0001\u001a\u0002032\u0007\u0010\u00a3\u0001\u001a\u00020~H\u0002J\u0007\u0010\u00fc\u0001\u001a\u00020)J\u001c\u0010\u00fd\u0001\u001a\u0002032\u0007\u0010\u00fe\u0001\u001a\u00020RH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u00ff\u0001J\u0010\u0010\u0080\u0002\u001a\u0002032\u0007\u0010\u0099\u0001\u001a\u000208J\u0013\u0010\u0081\u0002\u001a\u0002032\b\u0010\u00cf\u0001\u001a\u00030\u00d0\u0001H\u0002J\u0012\u0010\u0082\u0002\u001a\u0002032\u0007\u0010\u00da\u0001\u001a\u00020OH\u0002J\u0010\u0010\u0083\u0002\u001a\u0002032\u0007\u0010\u0084\u0002\u001a\u00020~J\u001c\u0010\u0085\u0002\u001a\u00020E2\u0007\u0010\u00a7\u0001\u001a\u00020E2\b\u0010\u0086\u0002\u001a\u00030\u00a5\u0001H\u0002J\u001b\u0010\u0087\u0002\u001a\u00020E2\u0007\u0010\u00a7\u0001\u001a\u00020E2\u0007\u0010\u00d8\u0001\u001a\u00020~H\u0002J\u001a\u0010\u0088\u0002\u001a\u0002032\u0007\u0010\u0089\u0002\u001a\u00020J2\b\u0010\u008a\u0002\u001a\u00030\u008b\u0002J=\u0010\u008c\u0002\u001a\u0002032\u000b\b\u0002\u0010\u008d\u0002\u001a\u0004\u0018\u00010)2\u000b\b\u0002\u0010\u008e\u0002\u001a\u0004\u0018\u00010,2\u0012\b\u0002\u0010\u008f\u0002\u001a\u000b\u0012\u0005\u0012\u00030\u0090\u0002\u0018\u00010<H\u0003\u00a2\u0006\u0003\u0010\u0091\u0002J*\u0010\u0092\u0002\u001a\u000203\"\u0005\b\u0000\u0010\u0093\u0002*\t\u0012\u0005\u0012\u0003H\u0093\u00020+2\u0007\u0010|\u001a\u0003H\u0093\u0002H\u0003\u00a2\u0006\u0003\u0010\u0094\u0002J.\u0010\u0095\u0002\u001a\u000203\"\u0005\b\u0000\u0010\u0093\u0002*\t\u0012\u0005\u0012\u0003H\u0093\u00020+2\u0007\u0010|\u001a\u0003H\u0093\u0002H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0096\u0002R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020.0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010100X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020300X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020500X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u00106\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u000208\u0012\u0006\u0012\u0004\u0018\u000109\u0012\b\u0012\u00060)j\u0002`:0700X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0<0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020)0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020)0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020C0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0<0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010F\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020G0700X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010900X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010I\u001a\b\u0012\u0004\u0012\u00020J00X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010K\u001a\b\u0012\u0004\u0012\u00020E00X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020E00X\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010M\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020O\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0<\u0018\u00010N0+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E00X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010Q\u001a\b\u0012\u0004\u0012\u00020R00X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010S\u001a\u0004\u0018\u000108X\u0082\u000e\u00a2\u0006\u0002\n\u0000R&\u0010T\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0700X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010U\u001a\u00020)8F\u00a2\u0006\u0006\u001a\u0004\bV\u0010WR\u0017\u0010X\u001a\b\u0012\u0004\u0012\u00020,0+\u00a2\u0006\b\n\u0000\u001a\u0004\bY\u0010ZR\u0017\u0010[\u001a\b\u0012\u0004\u0012\u00020.0\\\u00a2\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001010\\\u00a2\u0006\b\n\u0000\u001a\u0004\b`\u0010^R\u0017\u0010a\u001a\b\u0012\u0004\u0012\u0002030\\\u00a2\u0006\b\n\u0000\u001a\u0004\bb\u0010^R\u000e\u0010c\u001a\u00020OX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010d\u001a\b\u0012\u0004\u0012\u0002050\\\u00a2\u0006\b\n\u0000\u001a\u0004\be\u0010^R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010f\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u000208\u0012\u0006\u0012\u0004\u0018\u000109\u0012\b\u0012\u00060)j\u0002`:070\\\u00a2\u0006\b\n\u0000\u001a\u0004\bg\u0010^R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010h\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0<0\\\u00a2\u0006\b\n\u0000\u001a\u0004\bi\u0010^R\u001b\u0010j\u001a\u00020)8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bk\u0010l\u001a\u0004\bj\u0010WR\u000e\u0010m\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010n\u001a\b\u0012\u0004\u0012\u00020)0\\\u00a2\u0006\b\n\u0000\u001a\u0004\bn\u0010^R\u0017\u0010o\u001a\b\u0012\u0004\u0012\u00020)0\\\u00a2\u0006\b\n\u0000\u001a\u0004\bo\u0010^R\u0011\u0010p\u001a\u00020A8F\u00a2\u0006\u0006\u001a\u0004\bq\u0010rR\u0017\u0010s\u001a\b\u0012\u0004\u0012\u00020C0\\\u00a2\u0006\b\n\u0000\u001a\u0004\bt\u0010^R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010u\u001a\u00020v8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\by\u0010l\u001a\u0004\bw\u0010xR\u000e\u0010z\u001a\u00020{X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R8\u0010\u007f\u001a\u000e\u0012\u0004\u0012\u00020~\u0012\u0004\u0012\u00020E0}2\u0012\u0010|\u001a\u000e\u0012\u0004\u0012\u00020~\u0012\u0004\u0012\u00020E0}@BX\u0082\u000e\u00a2\u0006\n\n\u0000\"\u0006\b\u0080\u0001\u0010\u0081\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0082\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0<0\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0083\u0001\u0010^R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0084\u0001\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020G070\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0085\u0001\u0010^R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0086\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0087\u0001\u0010^R\u0019\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020J0\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u0010^R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020E00\u00a2\u0006\n\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u001b\u0010\u008d\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010^R\u0014\u0010\u008f\u0001\u001a\u0004\u0018\u00010~X\u0082\u000e\u00a2\u0006\u0005\n\u0003\u0010\u0090\u0001R\u0012\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0092\u0001X\u0082\u000e\u00a2\u0006\u0002\n\u0000R-\u0010\u0093\u0001\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020O\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0<\u0018\u00010N0\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0094\u0001\u0010^R\u001b\u0010\u0095\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010E0\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010^R\u0019\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020R0\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010^R\u0014\u0010\u0099\u0001\u001a\u0002088F\u00a2\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u009c\u0001\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)070\\\u00a2\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u0010^R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\n\n\u0000\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0098\u0002"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PagesViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "pageStore", "Lorg/wordpress/android/fluxc/store/PageStore;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "actionPerformer", "Lorg/wordpress/android/viewmodel/pages/ActionPerformer;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "previewStateHelper", "Lorg/wordpress/android/ui/posts/PreviewStateHelper;", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "autoSaveConflictResolver", "Lorg/wordpress/android/viewmodel/pages/AutoSaveConflictResolver;", "uploadStatusTracker", "Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "pageListEventListenerFactory", "Lorg/wordpress/android/viewmodel/pages/PageListEventListener$Factory;", "siteOptionsStore", "Lorg/wordpress/android/fluxc/store/SiteOptionsStore;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "prefs", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "defaultDispatcher", "(Lorg/wordpress/android/fluxc/store/PageStore;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/viewmodel/pages/ActionPerformer;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/posts/PreviewStateHelper;Lorg/wordpress/android/ui/uploads/UploadStarter;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/viewmodel/pages/AutoSaveConflictResolver;Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;Lorg/wordpress/android/viewmodel/pages/PageListEventListener$Factory;Lorg/wordpress/android/fluxc/store/SiteOptionsStore;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_arePageActionsEnabled", "", "_authorSelectionUpdated", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "_authorUIState", "Lorg/wordpress/android/ui/pages/PagesAuthorFilterUIState;", "_browsePreview", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/pages/PagesViewModel$BrowsePreview;", "_createNewPage", "", "_dialogAction", "Lorg/wordpress/android/viewmodel/helpers/DialogHolder;", "_editPage", "Lkotlin/Triple;", "Lorg/wordpress/android/fluxc/model/SiteModel;", "Lorg/wordpress/android/fluxc/model/PostModel;", "Lorg/wordpress/android/viewmodel/pages/LoadAutoSaveRevision;", "_invalidateUploadStatus", "", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "_isNewPageButtonVisible", "_isSearchExpanded", "_lastSearchQuery", "", "_listState", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListState;", "_pages", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "_postUploadAction", "Landroid/content/Intent;", "_previewPage", "_previewState", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "_publishAction", "_scrollToPage", "_searchPages", "Ljava/util/SortedMap;", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "_setPageParent", "_showSnackbarMessage", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_site", "_uploadFinishedAction", "arePageActionsEnabled", "getArePageActionsEnabled", "()Z", "authorSelectionUpdated", "getAuthorSelectionUpdated", "()Landroidx/lifecycle/MutableLiveData;", "authorUIState", "Landroidx/lifecycle/LiveData;", "getAuthorUIState", "()Landroidx/lifecycle/LiveData;", "browsePreview", "getBrowsePreview", "createNewPage", "getCreateNewPage", "currentPageType", "dialogAction", "getDialogAction", "editPage", "getEditPage", "invalidateUploadStatus", "getInvalidateUploadStatus", "isFilteringByAuthorSupported", "isFilteringByAuthorSupported$delegate", "Lkotlin/Lazy;", "isInitialized", "isNewPageButtonVisible", "isSearchExpanded", "lastSearchQuery", "getLastSearchQuery", "()Ljava/lang/String;", "listState", "getListState", "pageListDialogHelper", "Lorg/wordpress/android/viewmodel/pages/PageListDialogHelper;", "getPageListDialogHelper", "()Lorg/wordpress/android/viewmodel/pages/PageListDialogHelper;", "pageListDialogHelper$delegate", "pageListEventListener", "Lorg/wordpress/android/viewmodel/pages/PageListEventListener;", "value", "", "", "pageMap", "setPageMap", "(Ljava/util/Map;)V", "pages", "getPages", "postUploadAction", "getPostUploadAction", "previewPage", "getPreviewPage", "previewState", "getPreviewState", "publishAction", "getPublishAction", "()Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "scrollToPage", "getScrollToPage", "scrollToPageId", "Ljava/lang/Long;", "searchJob", "Lkotlinx/coroutines/Job;", "searchPages", "getSearchPages", "setPageParent", "getSetPageParent", "showSnackbarMessage", "getShowSnackbarMessage", "site", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "uploadFinishedAction", "getUploadFinishedAction", "getUploadStatusTracker", "()Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "cancelPendingAutoUpload", "pageId", "changePageStatus", "remoteId", "status", "Lorg/wordpress/android/fluxc/model/page/PageStatus;", "checkAndEdit", "page", "checkIfNewPageButtonShouldBeVisible", "clearSearch", "copyPage", "performChecks", "copyPageLink", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "context", "Landroid/content/Context;", "deletePage", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "loadAutoSaveRevision", "groupedSearch", "searchQuery", "(Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleHomepageSettingsChange", "siteModel", "handleInvalidateUploadStatus", "ids", "handleRemoteAutoSave", "post", "isError", "handleRemotePreview", "localPostId", "", "remotePreviewType", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "handleRemoveAutoSaveEvent", "hasRemoteAutoSavePreviewError", "isRemotePreviewingFromPostsList", "loadPagesAsync", "onCleared", "onCopyPage", "onCopyPageLocal", "onDeleteConfirmed", "onEditPageFirst", "onImagesChanged", "onItemTapped", "pageItem", "onMenuAction", "action", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "onNegativeClickedForBasicDialog", "instanceTag", "onNewPageButtonTapped", "onPageEditFinished", "localPageId", "data", "onPageParentSet", "parentId", "onPageTypeChanged", "type", "onPositiveClickedForBasicDialog", "onPullToRefresh", "onSearch", "delay", "onSearchCollapsed", "onSearchExpanded", "restorePreviousSearch", "onSpecificPageRequested", "remotePageId", "performIfNetworkAvailable", "performAction", "Lkotlin/Function0;", "performIfNetworkAvailableAsync", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postUploadedFinished", "isFirstTimePublish", "prepareStatusChangeSnackbar", "newStatus", "undo", "publishPageNow", "refreshPages", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reloadPages", "state", "forced", "(Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListState;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setHomepage", "homepageId", "setParent", "setPostsPage", "shouldFilterByAuthor", "showSnackbar", "message", "(Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "trackMenuSelectionEvent", "trackTabChangeEvent", "updateAuthorFilterSelection", "selectionId", "updatePageStatus", "oldStatus", "updateParent", "updatePreviewAndDialogState", "newState", "postInfo", "Lorg/wordpress/android/ui/posts/PostInfoType;", "updateViewStateTriggerPagerChange", "isAuthorFilterVisible", "authorFilterSelection", "authorFilterItems", "Lorg/wordpress/android/ui/posts/AuthorFilterListItemUIState;", "(Ljava/lang/Boolean;Lorg/wordpress/android/ui/posts/AuthorFilterSelection;Ljava/util/List;)V", "postOnUi", "T", "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;)V", "setOnUi", "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "BrowsePreview", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PagesViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.store.PageStore pageStore = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.viewmodel.pages.ActionPerformer actionPerformer = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.posts.PreviewStateHelper previewStateHelper = null;
    private final org.wordpress.android.ui.uploads.UploadStarter uploadStarter = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.viewmodel.pages.AutoSaveConflictResolver autoSaveConflictResolver = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.posts.PostModelUploadStatusTracker uploadStatusTracker = null;
    private final org.wordpress.android.viewmodel.pages.PageListEventListener.Factory pageListEventListenerFactory = null;
    private final org.wordpress.android.fluxc.store.SiteOptionsStore siteOptionsStore = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper prefs = null;
    private final kotlinx.coroutines.CoroutineDispatcher uiDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher defaultDispatcher = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isSearchExpanded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isSearchExpanded = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListState> _listState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListState> listState = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isNewPageButtonVisible = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isNewPageButtonVisible = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.fluxc.model.page.PageModel>> _pages = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.fluxc.model.page.PageModel>> pages = null;
    private final androidx.lifecycle.MutableLiveData<java.util.SortedMap<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType, java.util.List<org.wordpress.android.fluxc.model.page.PageModel>>> _searchPages = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.SortedMap<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType, java.util.List<org.wordpress.android.fluxc.model.page.PageModel>>> searchPages = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _createNewPage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> createNewPage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Triple<org.wordpress.android.fluxc.model.SiteModel, org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean>> _editPage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Triple<org.wordpress.android.fluxc.model.SiteModel, org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean>> editPage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.model.PostModel> _previewPage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.PostModel> previewPage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.pages.PagesViewModel.BrowsePreview> _browsePreview = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.pages.PagesViewModel.BrowsePreview> browsePreview = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.posts.PostListRemotePreviewState> _previewState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListRemotePreviewState> previewState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.model.page.PageModel> _setPageParent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.page.PageModel> setPageParent = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.model.page.PageModel> _scrollToPage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.page.PageModel> scrollToPage = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId>> _invalidateUploadStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId>> invalidateUploadStatus = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Triple<org.wordpress.android.fluxc.model.PostModel, org.wordpress.android.fluxc.model.SiteModel, android.content.Intent>> _postUploadAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Triple<org.wordpress.android.fluxc.model.PostModel, org.wordpress.android.fluxc.model.SiteModel, android.content.Intent>> postUploadAction = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Triple<org.wordpress.android.fluxc.model.page.PageModel, java.lang.Boolean, java.lang.Boolean>> _uploadFinishedAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Triple<org.wordpress.android.fluxc.model.page.PageModel, java.lang.Boolean, java.lang.Boolean>> uploadFinishedAction = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.model.page.PageModel> _publishAction = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.model.page.PageModel> publishAction = null;
    private boolean isInitialized = false;
    private java.lang.Long scrollToPageId;
    private java.util.Map<java.lang.Long, org.wordpress.android.fluxc.model.page.PageModel> pageMap;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.pages.SnackbarMessageHolder> _showSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> showSnackbarMessage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.helpers.DialogHolder> _dialogAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.DialogHolder> dialogAction = null;
    private org.wordpress.android.fluxc.model.SiteModel _site;
    private boolean _arePageActionsEnabled = true;
    private java.lang.String _lastSearchQuery = "";
    private kotlinx.coroutines.Job searchJob;
    private org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType currentPageType = org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType.PUBLISHED;
    private org.wordpress.android.viewmodel.pages.PageListEventListener pageListEventListener;
    private final kotlin.Lazy pageListDialogHelper$delegate = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.AuthorFilterSelection> _authorSelectionUpdated = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.AuthorFilterSelection> authorSelectionUpdated = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.pages.PagesAuthorFilterUIState> _authorUIState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.PagesAuthorFilterUIState> authorUIState = null;
    
    /**
     * Filtering by author is disable on:
     * 1) Self-hosted sites - The XMLRPC api doesn't support filtering by author.
     * 2) Jetpack sites - we need to pass in the self-hosted user id to be able to filter for authors
     * which we currently can't
     * 3) Sites on which the user doesn't have permissions to edit posts of other users.
     *
     * This behavior is consistent with Calypso and Posts as of 11/4/2019.
     */
    private final kotlin.Lazy isFilteringByAuthorSupported$delegate = null;
    
    @javax.inject.Inject()
    public PagesViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PageStore pageStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.ActionPerformer actionPerformer, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PreviewStateHelper previewStateHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadStarter uploadStarter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.AutoSaveConflictResolver autoSaveConflictResolver, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostModelUploadStatusTracker uploadStatusTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PageListEventListener.Factory pageListEventListenerFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteOptionsStore siteOptionsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper prefs, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PostModelUploadStatusTracker getUploadStatusTracker() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isSearchExpanded() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListState> getListState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isNewPageButtonVisible() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.fluxc.model.page.PageModel>> getPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.SortedMap<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType, java.util.List<org.wordpress.android.fluxc.model.page.PageModel>>> getSearchPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getCreateNewPage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Triple<org.wordpress.android.fluxc.model.SiteModel, org.wordpress.android.fluxc.model.PostModel, java.lang.Boolean>> getEditPage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.PostModel> getPreviewPage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.pages.PagesViewModel.BrowsePreview> getBrowsePreview() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PostListRemotePreviewState> getPreviewState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.page.PageModel> getSetPageParent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.page.PageModel> getScrollToPage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId>> getInvalidateUploadStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Triple<org.wordpress.android.fluxc.model.PostModel, org.wordpress.android.fluxc.model.SiteModel, android.content.Intent>> getPostUploadAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Triple<org.wordpress.android.fluxc.model.page.PageModel, java.lang.Boolean, java.lang.Boolean>> getUploadFinishedAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.model.page.PageModel> getPublishAction() {
        return null;
    }
    
    private final void setPageMap(java.util.Map<java.lang.Long, org.wordpress.android.fluxc.model.page.PageModel> value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> getShowSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.DialogHolder> getDialogAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    public final boolean getArePageActionsEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastSearchQuery() {
        return null;
    }
    
    private final org.wordpress.android.viewmodel.pages.PageListDialogHelper getPageListDialogHelper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.AuthorFilterSelection> getAuthorSelectionUpdated() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.PagesAuthorFilterUIState> getAuthorUIState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final kotlinx.coroutines.Job loadPagesAsync() {
        return null;
    }
    
    private final java.lang.Object reloadPages(org.wordpress.android.viewmodel.pages.PageListViewModel.PageListState state, boolean forced, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object refreshPages(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void onPageEditFinished(int localPageId, @org.jetbrains.annotations.NotNull()
    android.content.Intent data) {
    }
    
    public final void onPageParentSet(long pageId, long parentId) {
    }
    
    public final void onPageTypeChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType type) {
    }
    
    private final void trackTabChangeEvent(org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType type) {
    }
    
    public final void checkIfNewPageButtonShouldBeVisible() {
    }
    
    public final void onSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, long delay) {
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    public final void onSpecificPageRequested(long remotePageId) {
    }
    
    public final void updatePreviewAndDialogState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListRemotePreviewState newState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostInfoType postInfo) {
    }
    
    private final java.lang.Object groupedSearch(org.wordpress.android.fluxc.model.SiteModel site, java.lang.String searchQuery, kotlin.coroutines.Continuation<? super java.util.SortedMap<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType, java.util.List<org.wordpress.android.fluxc.model.page.PageModel>>> continuation) {
        return null;
    }
    
    public final void onSearchExpanded(boolean restorePreviousSearch) {
    }
    
    public final void onSearchCollapsed() {
    }
    
    public final boolean onMenuAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Action action, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Page page, @org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return false;
    }
    
    private final void deletePage(org.wordpress.android.ui.pages.PageItem.Page page) {
    }
    
    private final void cancelPendingAutoUpload(org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId pageId) {
    }
    
    private final void setParent(org.wordpress.android.ui.pages.PageItem.Page page) {
    }
    
    private final void setHomepage(long homepageId) {
    }
    
    private final void setPostsPage(long remoteId) {
    }
    
    @kotlin.Suppress(names = {"TooGenericExceptionCaught"})
    private final void copyPageLink(org.wordpress.android.ui.pages.PageItem.Page page, android.content.Context context) {
    }
    
    private final void previewPage(org.wordpress.android.ui.pages.PageItem.Page page) {
    }
    
    private final void onCopyPage(org.wordpress.android.ui.pages.PageItem.Page page) {
    }
    
    private final void copyPage(long pageId, boolean performChecks) {
    }
    
    private final void onCopyPageLocal(org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageId) {
    }
    
    private final void onEditPageFirst(org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageId) {
    }
    
    private final boolean performIfNetworkAvailable(kotlin.jvm.functions.Function0<kotlin.Unit> performAction) {
        return false;
    }
    
    private final java.lang.Object performIfNetworkAvailableAsync(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> performAction, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final void trackMenuSelectionEvent(org.wordpress.android.ui.pages.PageItem.Action action) {
    }
    
    private final void publishPageNow(long remoteId) {
    }
    
    public final void onImagesChanged() {
    }
    
    public final void onItemTapped(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItem.Page pageItem) {
    }
    
    private final void checkAndEdit(org.wordpress.android.fluxc.model.page.PageModel page) {
    }
    
    public final void onNewPageButtonTapped() {
    }
    
    public final void onPullToRefresh() {
    }
    
    private final void setParent(org.wordpress.android.fluxc.model.page.PageModel page, long parentId) {
    }
    
    private final java.lang.Object showSnackbar(org.wordpress.android.ui.pages.SnackbarMessageHolder message, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.page.PageModel updateParent(org.wordpress.android.fluxc.model.page.PageModel page, long parentId) {
        return null;
    }
    
    private final void deletePage(org.wordpress.android.fluxc.model.page.PageModel page) {
    }
    
    private final void changePageStatus(long remoteId, org.wordpress.android.fluxc.model.page.PageStatus status) {
    }
    
    private final org.wordpress.android.fluxc.model.page.PageModel updatePageStatus(org.wordpress.android.fluxc.model.page.PageModel page, org.wordpress.android.fluxc.model.page.PageStatus oldStatus) {
        return null;
    }
    
    private final org.wordpress.android.ui.pages.SnackbarMessageHolder prepareStatusChangeSnackbar(org.wordpress.android.fluxc.model.page.PageStatus newStatus, kotlin.jvm.functions.Function0<kotlin.Unit> undo) {
        return null;
    }
    
    private final void clearSearch() {
    }
    
    private final void handleRemotePreview(int localPostId, org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType remotePreviewType) {
    }
    
    private final void handleRemoteAutoSave(org.wordpress.android.fluxc.model.PostModel post, boolean isError) {
    }
    
    public final void updateAuthorFilterSelection(long selectionId) {
    }
    
    /**
     * Filtering by author is disable on:
     * 1) Self-hosted sites - The XMLRPC api doesn't support filtering by author.
     * 2) Jetpack sites - we need to pass in the self-hosted user id to be able to filter for authors
     * which we currently can't
     * 3) Sites on which the user doesn't have permissions to edit posts of other users.
     *
     * This behavior is consistent with Calypso and Posts as of 11/4/2019.
     */
    private final boolean isFilteringByAuthorSupported() {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    private final void updateViewStateTriggerPagerChange(java.lang.Boolean isAuthorFilterVisible, org.wordpress.android.ui.posts.AuthorFilterSelection authorFilterSelection, java.util.List<? extends org.wordpress.android.ui.posts.AuthorFilterListItemUIState> authorFilterItems) {
    }
    
    public final void onPositiveClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    public final void onNegativeClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    private final void onDeleteConfirmed(org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageId) {
    }
    
    private final void editPage(org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageId, boolean loadAutoSaveRevision) {
    }
    
    private final boolean isRemotePreviewingFromPostsList() {
        return false;
    }
    
    private final boolean hasRemoteAutoSavePreviewError() {
        return false;
    }
    
    private final void handleRemoveAutoSaveEvent(org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId pageId, boolean isError) {
    }
    
    private final void handleInvalidateUploadStatus(java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId> ids) {
    }
    
    private final void postUploadedFinished(org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId remoteId, boolean isError, boolean isFirstTimePublish) {
    }
    
    private final void handleHomepageSettingsChange(org.wordpress.android.fluxc.model.SiteModel siteModel) {
    }
    
    private final <T extends java.lang.Object>java.lang.Object setOnUi(androidx.lifecycle.MutableLiveData<T> $this$setOnUi, T value, kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    private final <T extends java.lang.Object>void postOnUi(androidx.lifecycle.MutableLiveData<T> $this$postOnUi, T value) {
    }
    
    public final boolean shouldFilterByAuthor() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PagesViewModel$BrowsePreview;", "", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "previewType", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "(Lorg/wordpress/android/fluxc/model/PostModel;Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;)V", "getPost", "()Lorg/wordpress/android/fluxc/model/PostModel;", "getPreviewType", "()Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BrowsePreview {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.PostModel post = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType previewType = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.pages.PagesViewModel.BrowsePreview copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType previewType) {
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
        
        public BrowsePreview(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType previewType) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.PostModel getPost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType getPreviewType() {
            return null;
        }
    }
}