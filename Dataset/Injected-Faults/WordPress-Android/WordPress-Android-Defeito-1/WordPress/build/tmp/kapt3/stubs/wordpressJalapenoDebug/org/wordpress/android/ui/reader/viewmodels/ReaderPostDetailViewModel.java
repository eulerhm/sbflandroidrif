package org.wordpress.android.ui.reader.viewmodels;

import java.lang.System;

@kotlin.Suppress(names = {"LargeClass"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00fe\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00ce\u00012\u00020\u0001:\u000e\u00cc\u0001\u00cd\u0001\u00ce\u0001\u00cf\u0001\u00d0\u0001\u00d1\u0001\u00d2\u0001B\u00dd\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\b\b\u0001\u0010\u001e\u001a\u00020\u001f\u0012\b\b\u0001\u0010 \u001a\u00020\u001f\u0012\b\b\u0001\u0010!\u001a\u00020\u001f\u0012\u0006\u0010\"\u001a\u00020#\u0012\u0006\u0010$\u001a\u00020%\u0012\u0006\u0010&\u001a\u00020\'\u0012\u0006\u0010(\u001a\u00020)\u0012\u0006\u0010*\u001a\u00020+\u0012\u0006\u0010,\u001a\u00020-\u0012\u0006\u0010.\u001a\u00020/\u0012\u0006\u00100\u001a\u000201\u0012\u0006\u00102\u001a\u000203\u00a2\u0006\u0002\u00104J\u0012\u0010r\u001a\u00020_2\b\u0010s\u001a\u0004\u0018\u00010CH\u0002J\u0010\u0010t\u001a\u00020=2\u0006\u0010u\u001a\u00020LH\u0002J\u0010\u0010v\u001a\u00020w2\u0006\u0010e\u001a\u00020dH\u0002J \u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020d2\u0006\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020LH\u0002J\u001d\u0010~\u001a\u0004\u0018\u00010d2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001H\u0002J$\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020L2\u000f\u0010\u0085\u0001\u001a\n\u0012\u0005\u0012\u00030\u0087\u00010\u0086\u0001H\u0002J/\u0010\u0088\u0001\u001a\u001e\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u0087\u00010\u0086\u0001\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020L0\u0089\u00012\b\u0010s\u001a\u0004\u0018\u00010CH\u0002J-\u0010\u008b\u0001\u001a\n\u0012\u0005\u0012\u00030\u0087\u00010\u0086\u00012\u0007\u0010\u008c\u0001\u001a\u00020L2\b\u0010\u008d\u0001\u001a\u00030\u008a\u00012\u0007\u0010\u008e\u0001\u001a\u00020LH\u0002J\n\u0010\u008f\u0001\u001a\u00030\u008a\u0001H\u0002J&\u0010\u0090\u0001\u001a\u00020=2\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0091\u0001J&\u0010\u0092\u0001\u001a\u00020=2\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0091\u0001J\u001f\u0010\u0093\u0001\u001a\u0002072\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0002J\t\u0010\u0098\u0001\u001a\u00020=H\u0002J\u001a\u0010\u0099\u0001\u001a\u00020L2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010LH\u0002\u00a2\u0006\u0003\u0010\u009a\u0001J/\u0010\u009b\u0001\u001a\u00020=2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\b\u0010\u009c\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020PH\u0002J\u001c\u0010\u009f\u0001\u001a\u00020=2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001H\u0002J$\u0010\u00a0\u0001\u001a\u00020=2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001J\t\u0010\u00a1\u0001\u001a\u00020=H\u0014J\u001a\u0010\u00a2\u0001\u001a\u00020=2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001J\u0015\u0010\u00a3\u0001\u001a\u00020=2\n\u0010\u00a4\u0001\u001a\u0005\u0018\u00010\u00a5\u0001H\u0007J\u0015\u0010\u00a3\u0001\u001a\u00020=2\n\u0010\u00a4\u0001\u001a\u0005\u0018\u00010\u00a6\u0001H\u0007J\u001a\u0010\u00a7\u0001\u001a\u00020=2\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u00a8\u0001\u001a\u00020PJ\u0007\u0010\u00a9\u0001\u001a\u00020=J\u0007\u0010\u00aa\u0001\u001a\u00020=J\u0007\u0010\u00ab\u0001\u001a\u00020=J\u0011\u0010\u00ac\u0001\u001a\u00020=2\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001J\u0007\u0010\u00ad\u0001\u001a\u00020=J\u0011\u0010\u00ae\u0001\u001a\u00020=2\b\u0010\u00af\u0001\u001a\u00030\u008a\u0001J\u001a\u0010\u00b0\u0001\u001a\u00020=2\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u007f\u001a\u00030\u0080\u0001J\u001a\u0010\u00b1\u0001\u001a\u00020=2\u0006\u0010e\u001a\u00020d2\t\b\u0002\u0010\u00b2\u0001\u001a\u00020LJ$\u0010\u00b3\u0001\u001a\u00020=2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0006\u0010}\u001a\u00020LH\u0002J\u000f\u0010\u00b4\u0001\u001a\u00020=2\u0006\u0010z\u001a\u00020dJ\u001a\u0010\u00b5\u0001\u001a\u00020=2\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u007f\u001a\u00030\u0080\u0001J\u0012\u0010\u00b6\u0001\u001a\u00020=2\u0007\u0010\u00b7\u0001\u001a\u00020PH\u0002J\u000f\u0010\u00b8\u0001\u001a\u00020=2\u0006\u0010e\u001a\u00020dJ\u0007\u0010\u00b9\u0001\u001a\u00020=J\u0010\u0010\u00ba\u0001\u001a\u00020=2\u0007\u0010\u00bb\u0001\u001a\u00020PJ!\u0010\u00bc\u0001\u001a\u00020=2\u0006\u0010X\u001a\u00020L2\u0006\u0010U\u001a\u00020L2\b\u0010O\u001a\u0004\u0018\u00010PJ\t\u0010\u00bd\u0001\u001a\u00020=H\u0002J\t\u0010\u00be\u0001\u001a\u00020=H\u0002J$\u0010\u00bf\u0001\u001a\u00020=2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\u0006\u0010}\u001a\u00020LH\u0002J\u001b\u0010\u00c0\u0001\u001a\u00020=2\u0007\u0010\u00c1\u0001\u001a\u00020w2\u0007\u0010\u00c2\u0001\u001a\u00020LH\u0002J\u0011\u0010\u00c3\u0001\u001a\u00020=2\u0006\u0010e\u001a\u00020dH\u0002J\t\u0010\u00c4\u0001\u001a\u00020=H\u0002J\u001b\u0010\u00c5\u0001\u001a\u00020=2\u0006\u0010z\u001a\u00020d2\b\u0010\u00c6\u0001\u001a\u00030\u00c7\u0001H\u0002J&\u0010\u00c8\u0001\u001a\n\u0012\u0005\u0012\u00030\u00c9\u00010\u0086\u0001*\n\u0012\u0005\u0012\u00030\u00c9\u00010\u0086\u00012\u0007\u0010\u008e\u0001\u001a\u00020LH\u0002J\u0016\u0010\u00ca\u0001\u001a\n\u0012\u0005\u0012\u00030\u00cb\u00010\u0086\u0001*\u00030\u00cb\u0001H\u0002R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0:09X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0:09X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0:09X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020A09X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020C09X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010D\u001a\b\u0012\u0004\u0012\u00020F0E\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u000e\u0010.\u001a\u00020/X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010K\u001a\u00020L8F\u00a2\u0006\u0006\u001a\u0004\bM\u0010NR\u000e\u0010(\u001a\u00020)X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010O\u001a\u0004\u0018\u00010PX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u000e\u0010 \u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010U\u001a\u00020LX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bU\u0010N\"\u0004\bV\u0010WR\u000e\u0010X\u001a\u00020LX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020LX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010Z\u001a\u0004\u0018\u00010[X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\\\u001a\u0004\u0018\u00010]X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010^\u001a\b\u0012\u0004\u0012\u00020_0E\u00a2\u0006\b\n\u0000\u001a\u0004\b`\u0010HR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0:0E\u00a2\u0006\b\n\u0000\u001a\u0004\bb\u0010HR\u000e\u0010,\u001a\u00020-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010c\u001a\u0004\u0018\u00010dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010e\u001a\u0004\u0018\u00010dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010j\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0:0E\u00a2\u0006\b\n\u0000\u001a\u0004\bk\u0010HR\u0014\u0010l\u001a\u00020L8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bm\u0010NR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0:0E\u00a2\u0006\b\n\u0000\u001a\u0004\bo\u0010HR\u0017\u0010p\u001a\b\u0012\u0004\u0012\u00020A0E\u00a2\u0006\b\n\u0000\u001a\u0004\bq\u0010HR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u00d3\u0001"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "readerPostCardActionsHandler", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionsHandler;", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "readerPostMoreButtonUiStateBuilder", "Lorg/wordpress/android/ui/reader/discover/ReaderPostMoreButtonUiStateBuilder;", "postDetailUiStateBuilder", "Lorg/wordpress/android/ui/reader/ReaderPostDetailUiStateBuilder;", "reblogUseCase", "Lorg/wordpress/android/ui/reader/reblog/ReblogUseCase;", "readerFetchRelatedPostsUseCase", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase;", "readerGetPostUseCase", "Lorg/wordpress/android/ui/reader/usecases/ReaderGetPostUseCase;", "readerFetchPostUseCase", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "wpUrlUtilsWrapper", "Lorg/wordpress/android/util/WpUrlUtilsWrapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "ioDispatcher", "bgDispatcher", "getLikesHandler", "Lorg/wordpress/android/ui/engagement/GetLikesHandler;", "likesEnhancementsFeatureConfig", "Lorg/wordpress/android/util/config/LikesEnhancementsFeatureConfig;", "engagementUtils", "Lorg/wordpress/android/ui/engagement/EngagementUtils;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "commentsSnippetFeatureConfig", "Lorg/wordpress/android/util/config/CommentsSnippetFeatureConfig;", "readerCommentTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderCommentTableWrapper;", "readerCommentServiceStarterWrapper", "Lorg/wordpress/android/ui/reader/services/comment/wrapper/ReaderCommentServiceStarterWrapper;", "(Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionsHandler;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;Lorg/wordpress/android/ui/reader/discover/ReaderPostMoreButtonUiStateBuilder;Lorg/wordpress/android/ui/reader/ReaderPostDetailUiStateBuilder;Lorg/wordpress/android/ui/reader/reblog/ReblogUseCase;Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase;Lorg/wordpress/android/ui/reader/usecases/ReaderGetPostUseCase;Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/util/WpUrlUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/engagement/GetLikesHandler;Lorg/wordpress/android/util/config/LikesEnhancementsFeatureConfig;Lorg/wordpress/android/ui/engagement/EngagementUtils;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/config/CommentsSnippetFeatureConfig;Lorg/wordpress/android/datasets/wrappers/ReaderCommentTableWrapper;Lorg/wordpress/android/ui/reader/services/comment/wrapper/ReaderCommentServiceStarterWrapper;)V", "_commentSnippetState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState;", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "_refreshPost", "", "_snackbarEvents", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_uiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState;", "_updateLikesState", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "commentSnippetState", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetUiState;", "getCommentSnippetState", "()Landroidx/lifecycle/LiveData;", "getLikesJob", "Lkotlinx/coroutines/Job;", "hasPost", "", "getHasPost", "()Z", "interceptedUri", "", "getInterceptedUri", "()Ljava/lang/String;", "setInterceptedUri", "(Ljava/lang/String;)V", "isFeed", "setFeed", "(Z)V", "isRelatedPost", "isStarted", "lastRenderedLikesData", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$RenderedLikesData;", "lastRenderedRepliesData", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$RenderedRepliesData;", "likesUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$TrainOfFacesUiState;", "getLikesUiState", "navigationEvents", "getNavigationEvents", "pendingReblogPost", "Lorg/wordpress/android/models/ReaderPost;", "post", "getPost", "()Lorg/wordpress/android/models/ReaderPost;", "setPost", "(Lorg/wordpress/android/models/ReaderPost;)V", "refreshPost", "getRefreshPost", "shouldOfferSignIn", "getShouldOfferSignIn", "snackbarEvents", "getSnackbarEvents", "uiState", "getUiState", "buildLikersUiState", "updateLikesState", "changeMoreMenuVisibility", "show", "convertPostToUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState;", "convertRelatedPostsToUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;", "sourcePost", "relatedPosts", "Lorg/wordpress/android/ui/reader/models/ReaderSimplePostList;", "isGlobal", "findPost", "postId", "", "blogId", "getContentDescription", "Lorg/wordpress/android/ui/utils/UiString;", "goingToShowFaces", "items", "", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem;", "getLikersEssentials", "Lkotlin/Triple;", "", "getLikersFacesText", "showEmptyState", "numLikes", "iLiked", "getNotAuthorisedErrorMessageRes", "getOrFetchReaderPost", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReaderPostFromDb", "getUpdatedSnippetState", "comments", "Lorg/wordpress/android/models/ReaderCommentList;", "result", "Lorg/wordpress/android/ui/reader/actions/ReaderActions$UpdateResult;", "init", "isLikedByCurrentUser", "(Ljava/lang/Boolean;)Z", "onActionClicked", "type", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "source", "onBlogSectionClicked", "onButtonClicked", "onCleared", "onCommentSnippetClicked", "onEventMainThread", "event", "Lorg/wordpress/android/ui/reader/ReaderEvents$UpdateCommentsEnded;", "Lorg/wordpress/android/ui/reader/ReaderEvents$UpdateCommentsStarted;", "onFeaturedImageClicked", "featuredImageUrl", "onLikeFacesClicked", "onMoreButtonClicked", "onMoreMenuDismissed", "onMoreMenuItemClicked", "onNotAuthorisedRequestFailure", "onReblogSiteSelected", "siteLocalId", "onRefreshCommentsData", "onRefreshLikersData", "isLikingAction", "onRelatedPostItemClicked", "onRelatedPostsRequested", "onShowPost", "onTagItemClicked", "tagSlug", "onUpdatePost", "onUserNavigateFromComments", "onVisitPostExcerptFooterClicked", "postLink", "start", "trackAndUpdateNotAuthorisedErrorState", "trackNotAuthorisedState", "trackRelatedPostClickAction", "updateFollowButtonUiState", "currentUiState", "isFollowed", "updatePostActions", "updatePostDetailsUi", "updateRelatedPostsUiState", "state", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Success;", "manageSelfLike", "Lorg/wordpress/android/fluxc/model/LikeModel;", "toList", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem$TrailingLabelTextItem;", "CommentSnippetState", "CommentSnippetUiState", "Companion", "RenderedLikesData", "RenderedRepliesData", "TrainOfFacesUiState", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderPostDetailViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardActionsHandler readerPostCardActionsHandler = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    private final org.wordpress.android.ui.reader.discover.ReaderPostMoreButtonUiStateBuilder readerPostMoreButtonUiStateBuilder = null;
    private final org.wordpress.android.ui.reader.ReaderPostDetailUiStateBuilder postDetailUiStateBuilder = null;
    private final org.wordpress.android.ui.reader.reblog.ReblogUseCase reblogUseCase = null;
    private final org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase readerFetchRelatedPostsUseCase = null;
    private final org.wordpress.android.ui.reader.usecases.ReaderGetPostUseCase readerGetPostUseCase = null;
    private final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase readerFetchPostUseCase = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.util.WpUrlUtilsWrapper wpUrlUtilsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.engagement.GetLikesHandler getLikesHandler = null;
    private final org.wordpress.android.util.config.LikesEnhancementsFeatureConfig likesEnhancementsFeatureConfig = null;
    private final org.wordpress.android.ui.engagement.EngagementUtils engagementUtils = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.util.config.CommentsSnippetFeatureConfig commentsSnippetFeatureConfig = null;
    private final org.wordpress.android.datasets.wrappers.ReaderCommentTableWrapper readerCommentTableWrapper = null;
    private final org.wordpress.android.ui.reader.services.comment.wrapper.ReaderCommentServiceStarterWrapper readerCommentServiceStarterWrapper = null;
    private kotlinx.coroutines.Job getLikesJob;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState> uiState = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _refreshPost = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> refreshPost = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.discover.ReaderNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.discover.ReaderNavigationEvents>> navigationEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState> _updateLikesState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.TrainOfFacesUiState> likesUiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState> _commentSnippetState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetUiState> commentSnippetState = null;
    
    /**
     * Post which is about to be reblogged after the user selects a target site.
     */
    private org.wordpress.android.models.ReaderPost pendingReblogPost;
    private boolean isStarted = false;
    private boolean isRelatedPost = false;
    private boolean isFeed = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String interceptedUri;
    @org.jetbrains.annotations.Nullable()
    private org.wordpress.android.models.ReaderPost post;
    private org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.RenderedLikesData lastRenderedLikesData;
    private org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.RenderedRepliesData lastRenderedRepliesData;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.Companion Companion = null;
    public static final int MAX_NUM_LIKES_FACES_WITH_SELF = 6;
    public static final int MAX_NUM_LIKES_FACES_WITHOUT_SELF = 5;
    
    @javax.inject.Inject()
    public ReaderPostDetailViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostCardActionsHandler readerPostCardActionsHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostMoreButtonUiStateBuilder readerPostMoreButtonUiStateBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderPostDetailUiStateBuilder postDetailUiStateBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.reblog.ReblogUseCase reblogUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase readerFetchRelatedPostsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderGetPostUseCase readerGetPostUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase readerFetchPostUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.WpUrlUtilsWrapper wpUrlUtilsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesHandler getLikesHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.LikesEnhancementsFeatureConfig likesEnhancementsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.EngagementUtils engagementUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.CommentsSnippetFeatureConfig commentsSnippetFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderCommentTableWrapper readerCommentTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.comment.wrapper.ReaderCommentServiceStarterWrapper readerCommentServiceStarterWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getRefreshPost() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.discover.ReaderNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.TrainOfFacesUiState> getLikesUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetUiState> getCommentSnippetState() {
        return null;
    }
    
    public final boolean isFeed() {
        return false;
    }
    
    public final void setFeed(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getInterceptedUri() {
        return null;
    }
    
    public final void setInterceptedUri(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderPost getPost() {
        return null;
    }
    
    public final void setPost(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost p0) {
    }
    
    public final boolean getHasPost() {
        return false;
    }
    
    private final boolean getShouldOfferSignIn() {
        return false;
    }
    
    public final void start(boolean isRelatedPost, boolean isFeed, @org.jetbrains.annotations.Nullable()
    java.lang.String interceptedUri) {
    }
    
    private final void init() {
    }
    
    public final void onRefreshCommentsData(long blogId, long postId) {
    }
    
    public final void onRefreshLikersData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean isLikingAction) {
    }
    
    public final void onShowPost(long blogId, long postId) {
    }
    
    private final java.lang.Object getOrFetchReaderPost(long blogId, long postId, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object getReaderPostFromDb(long blogId, long postId, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void onNotAuthorisedRequestFailure() {
    }
    
    public final void onMoreButtonClicked() {
    }
    
    public final void onMoreMenuDismissed() {
    }
    
    public final void onMoreMenuItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type) {
    }
    
    private final void changeMoreMenuVisibility(boolean show) {
    }
    
    public final void onFeaturedImageClicked(long blogId, @org.jetbrains.annotations.NotNull()
    java.lang.String featuredImageUrl) {
    }
    
    public final void onCommentSnippetClicked(long postId, long blogId) {
    }
    
    public final void onButtonClicked(long postId, long blogId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type) {
    }
    
    private final void onActionClicked(long postId, long blogId, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type, java.lang.String source) {
    }
    
    public final void onReblogSiteSelected(int siteLocalId) {
    }
    
    public final void onUpdatePost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
    }
    
    private final void onTagItemClicked(java.lang.String tagSlug) {
    }
    
    private final void onBlogSectionClicked(long postId, long blogId) {
    }
    
    public final void onVisitPostExcerptFooterClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String postLink) {
    }
    
    private final void onRelatedPostItemClicked(long postId, long blogId, boolean isGlobal) {
    }
    
    public final void onRelatedPostsRequested(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost sourcePost) {
    }
    
    private final void trackRelatedPostClickAction(long postId, long blogId, boolean isGlobal) {
    }
    
    private final org.wordpress.android.models.ReaderPost findPost(long postId, long blogId) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState convertPostToUiState(org.wordpress.android.models.ReaderPost post) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState convertRelatedPostsToUiState(org.wordpress.android.models.ReaderPost sourcePost, org.wordpress.android.ui.reader.models.ReaderSimplePostList relatedPosts, boolean isGlobal) {
        return null;
    }
    
    private final void updatePostDetailsUi() {
    }
    
    private final void updateFollowButtonUiState(org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState currentUiState, boolean isFollowed) {
    }
    
    private final void updatePostActions(org.wordpress.android.models.ReaderPost post) {
    }
    
    private final void updateRelatedPostsUiState(org.wordpress.android.models.ReaderPost sourcePost, org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState.Success state) {
    }
    
    private final void trackAndUpdateNotAuthorisedErrorState() {
    }
    
    private final void trackNotAuthorisedState() {
    }
    
    private final int getNotAuthorisedErrorMessageRes() {
        return 0;
    }
    
    private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.TrainOfFacesUiState buildLikersUiState(org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState updateLikesState) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString getContentDescription(boolean goingToShowFaces, java.util.List<? extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem> items) {
        return null;
    }
    
    @kotlin.Suppress(names = {"LongMethod"})
    private final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> getLikersFacesText(boolean showEmptyState, int numLikes, boolean iLiked) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem.TrailingLabelTextItem> toList(org.wordpress.android.ui.avatars.TrainOfAvatarsItem.TrailingLabelTextItem $this$toList) {
        return null;
    }
    
    private final kotlin.Triple<java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem>, java.lang.Integer, java.lang.Boolean> getLikersEssentials(org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState updateLikesState) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.LikeModel> manageSelfLike(java.util.List<org.wordpress.android.fluxc.model.LikeModel> $this$manageSelfLike, boolean iLiked) {
        return null;
    }
    
    private final boolean isLikedByCurrentUser(java.lang.Boolean iLiked) {
        return false;
    }
    
    public final void onLikeFacesClicked() {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.ReaderEvents.UpdateCommentsStarted event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.ReaderEvents.UpdateCommentsEnded event) {
    }
    
    public final void onUserNavigateFromComments() {
    }
    
    private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState getUpdatedSnippetState(org.wordpress.android.models.ReaderCommentList comments, org.wordpress.android.ui.reader.actions.ReaderActions.UpdateResult result) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00d6\u0001J\u000e\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bJ\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$RenderedLikesData;", "", "blogId", "", "postId", "numLikes", "", "iLike", "", "(JJIZ)V", "getBlogId", "()J", "getILike", "()Z", "getNumLikes", "()I", "getPostId", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "isMatchingPostLikeStatus", "post", "Lorg/wordpress/android/models/ReaderPost;", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class RenderedLikesData {
        private final long blogId = 0L;
        private final long postId = 0L;
        private final int numLikes = 0;
        private final boolean iLike = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.RenderedLikesData copy(long blogId, long postId, int numLikes, boolean iLike) {
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
        
        public RenderedLikesData(long blogId, long postId, int numLikes, boolean iLike) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getBlogId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getNumLikes() {
            return 0;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getILike() {
            return false;
        }
        
        public final boolean isMatchingPostLikeStatus(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderPost post) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ2\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00d6\u0001J\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000e\u0010\t\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$RenderedRepliesData;", "", "blogId", "", "postId", "numReplies", "", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;)V", "getBlogId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getNumReplies", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPostId", "component1", "component2", "component3", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;)Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$RenderedRepliesData;", "equals", "", "other", "hashCode", "isMatchingPostCommentsStatus", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class RenderedRepliesData {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long blogId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long postId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer numReplies = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.RenderedRepliesData copy(@org.jetbrains.annotations.Nullable()
        java.lang.Long blogId, @org.jetbrains.annotations.Nullable()
        java.lang.Long postId, @org.jetbrains.annotations.Nullable()
        java.lang.Integer numReplies) {
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
        
        public RenderedRepliesData(@org.jetbrains.annotations.Nullable()
        java.lang.Long blogId, @org.jetbrains.annotations.Nullable()
        java.lang.Long postId, @org.jetbrains.annotations.Nullable()
        java.lang.Integer numReplies) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getBlogId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getPostId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getNumReplies() {
            return null;
        }
        
        public final boolean isMatchingPostCommentsStatus(long blogId, long postId, int numReplies) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003JW\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020%H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$TrainOfFacesUiState;", "", "showLikeFacesTrainContainer", "", "showLoading", "engageItemsList", "", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem;", "showEmptyState", "emptyStateTitle", "Lorg/wordpress/android/ui/utils/UiString;", "contentDescription", "goingToShowFaces", "(ZZLjava/util/List;ZLorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Z)V", "getContentDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getEmptyStateTitle", "getEngageItemsList", "()Ljava/util/List;", "getGoingToShowFaces", "()Z", "getShowEmptyState", "getShowLikeFacesTrainContainer", "getShowLoading", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TrainOfFacesUiState {
        private final boolean showLikeFacesTrainContainer = false;
        private final boolean showLoading = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> engageItemsList = null;
        private final boolean showEmptyState = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString emptyStateTitle = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString contentDescription = null;
        private final boolean goingToShowFaces = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.TrainOfFacesUiState copy(boolean showLikeFacesTrainContainer, boolean showLoading, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem> engageItemsList, boolean showEmptyState, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString emptyStateTitle, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString contentDescription, boolean goingToShowFaces) {
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
        
        public TrainOfFacesUiState(boolean showLikeFacesTrainContainer, boolean showLoading, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem> engageItemsList, boolean showEmptyState, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString emptyStateTitle, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString contentDescription, boolean goingToShowFaces) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getShowLikeFacesTrainContainer() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getShowLoading() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> getEngageItemsList() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowEmptyState() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getEmptyStateTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getContentDescription() {
            return null;
        }
        
        public final boolean component7() {
            return false;
        }
        
        public final boolean getGoingToShowFaces() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState;", "", "()V", "CommentSnippetData", "Empty", "Failure", "Loading", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$Loading;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$Empty;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$Failure;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$CommentSnippetData;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class CommentSnippetState {
        
        private CommentSnippetState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$Loading;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Loading extends org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState.Loading INSTANCE = null;
            
            private Loading() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$Empty;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState;", "message", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Empty extends org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState.Empty copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString message) {
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
            
            public Empty(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString message) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getMessage() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$Failure;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState;", "message", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString message = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState.Failure copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString message) {
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
            
            public Failure(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString message) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getMessage() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState$CommentSnippetData;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState;", "comments", "Lorg/wordpress/android/models/ReaderCommentList;", "(Lorg/wordpress/android/models/ReaderCommentList;)V", "getComments", "()Lorg/wordpress/android/models/ReaderCommentList;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class CommentSnippetData extends org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.ReaderCommentList comments = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState.CommentSnippetData copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.ReaderCommentList comments) {
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
            
            public CommentSnippetData(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.ReaderCommentList comments) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.ReaderCommentList component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.ReaderCommentList getComments() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetUiState;", "", "commentsNumber", "", "showFollowConversation", "", "snippetItems", "", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "(IZLjava/util/List;)V", "getCommentsNumber", "()I", "getShowFollowConversation", "()Z", "getSnippetItems", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CommentSnippetUiState {
        private final int commentsNumber = 0;
        private final boolean showFollowConversation = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> snippetItems = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetUiState copy(int commentsNumber, boolean showFollowConversation, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> snippetItems) {
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
        
        public CommentSnippetUiState(int commentsNumber, boolean showFollowConversation, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> snippetItems) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getCommentsNumber() {
            return 0;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getShowFollowConversation() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> getSnippetItems() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u001b\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0003\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState;", "", "loadingVisible", "", "errorVisible", "(ZZ)V", "getErrorVisible", "()Z", "getLoadingVisible", "ErrorUiState", "LoadingUiState", "ReaderPostDetailsUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$LoadingUiState;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ErrorUiState;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UiState {
        private final boolean loadingVisible = false;
        private final boolean errorVisible = false;
        
        private UiState(boolean loadingVisible, boolean errorVisible) {
            super();
        }
        
        public final boolean getLoadingVisible() {
            return false;
        }
        
        public final boolean getErrorVisible() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$LoadingUiState;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class LoadingUiState extends org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.LoadingUiState INSTANCE = null;
            
            private LoadingUiState() {
                super(false, false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ErrorUiState;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState;", "message", "Lorg/wordpress/android/ui/utils/UiString;", "signInButtonVisibility", "", "(Lorg/wordpress/android/ui/utils/UiString;Z)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "getSignInButtonVisibility", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ErrorUiState extends org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState {
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString message = null;
            private final boolean signInButtonVisibility = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ErrorUiState copy(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString message, boolean signInButtonVisibility) {
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
            
            public ErrorUiState(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString message, boolean signInButtonVisibility) {
                super(false, false);
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getMessage() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getSignInButtonVisibility() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0003678Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\'\u001a\u00020\bH\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u00c6\u0003J\t\u0010*\u001a\u00020\u000fH\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003Js\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u00c6\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101H\u00d6\u0003J\t\u00102\u001a\u000203H\u00d6\u0001J\t\u00104\u001a\u000205H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017\u00a8\u00069"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState;", "postId", "", "blogId", "featuredImageUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ReaderPostFeaturedImageUiState;", "headerUiState", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderPostDetailsHeaderViewUiState$ReaderPostDetailsHeaderUiState;", "excerptFooterUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ExcerptFooterUiState;", "moreMenuItems", "", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "actions", "Lorg/wordpress/android/ui/reader/discover/ReaderPostActions;", "localRelatedPosts", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;", "globalRelatedPosts", "(JJLorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ReaderPostFeaturedImageUiState;Lorg/wordpress/android/ui/reader/views/uistates/ReaderPostDetailsHeaderViewUiState$ReaderPostDetailsHeaderUiState;Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ExcerptFooterUiState;Ljava/util/List;Lorg/wordpress/android/ui/reader/discover/ReaderPostActions;Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;)V", "getActions", "()Lorg/wordpress/android/ui/reader/discover/ReaderPostActions;", "getBlogId", "()J", "getExcerptFooterUiState", "()Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ExcerptFooterUiState;", "getFeaturedImageUiState", "()Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ReaderPostFeaturedImageUiState;", "getGlobalRelatedPosts", "()Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;", "getHeaderUiState", "()Lorg/wordpress/android/ui/reader/views/uistates/ReaderPostDetailsHeaderViewUiState$ReaderPostDetailsHeaderUiState;", "getLocalRelatedPosts", "getMoreMenuItems", "()Ljava/util/List;", "getPostId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ExcerptFooterUiState", "ReaderPostFeaturedImageUiState", "RelatedPostsUiState", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ReaderPostDetailsUiState extends org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState {
            private final long postId = 0L;
            private final long blogId = 0L;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ReaderPostFeaturedImageUiState featuredImageUiState = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState headerUiState = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ExcerptFooterUiState excerptFooterUiState = null;
            @org.jetbrains.annotations.Nullable()
            private final java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> moreMenuItems = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.discover.ReaderPostActions actions = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState localRelatedPosts = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState globalRelatedPosts = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState copy(long postId, long blogId, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ReaderPostFeaturedImageUiState featuredImageUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState headerUiState, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ExcerptFooterUiState excerptFooterUiState, @org.jetbrains.annotations.Nullable()
            java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction> moreMenuItems, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.discover.ReaderPostActions actions, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState localRelatedPosts, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState globalRelatedPosts) {
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
            
            public ReaderPostDetailsUiState(long postId, long blogId, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ReaderPostFeaturedImageUiState featuredImageUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState headerUiState, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ExcerptFooterUiState excerptFooterUiState, @org.jetbrains.annotations.Nullable()
            java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction> moreMenuItems, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.discover.ReaderPostActions actions, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState localRelatedPosts, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState globalRelatedPosts) {
                super(false, false);
            }
            
            public final long component1() {
                return 0L;
            }
            
            public final long getPostId() {
                return 0L;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getBlogId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ReaderPostFeaturedImageUiState component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ReaderPostFeaturedImageUiState getFeaturedImageUiState() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState getHeaderUiState() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ExcerptFooterUiState component5() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ExcerptFooterUiState getExcerptFooterUiState() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> component6() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> getMoreMenuItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderPostActions component7() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.discover.ReaderPostActions getActions() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState component8() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState getLocalRelatedPosts() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState component9() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState getGlobalRelatedPosts() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ReaderPostFeaturedImageUiState;", "", "blogId", "", "url", "", "height", "", "(JLjava/lang/String;I)V", "getBlogId", "()J", "getHeight", "()I", "getUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ReaderPostFeaturedImageUiState {
                private final long blogId = 0L;
                @org.jetbrains.annotations.Nullable()
                private final java.lang.String url = null;
                private final int height = 0;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ReaderPostFeaturedImageUiState copy(long blogId, @org.jetbrains.annotations.Nullable()
                java.lang.String url, int height) {
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
                
                public ReaderPostFeaturedImageUiState(long blogId, @org.jetbrains.annotations.Nullable()
                java.lang.String url, int height) {
                    super();
                }
                
                public final long component1() {
                    return 0L;
                }
                
                public final long getBlogId() {
                    return 0L;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.String component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.String getUrl() {
                    return null;
                }
                
                public final int component3() {
                    return 0;
                }
                
                public final int getHeight() {
                    return 0;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ExcerptFooterUiState;", "", "visitPostExcerptFooterLinkText", "Lorg/wordpress/android/ui/utils/UiString;", "postLink", "", "(Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/String;)V", "getPostLink", "()Ljava/lang/String;", "getVisitPostExcerptFooterLinkText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class ExcerptFooterUiState {
                @org.jetbrains.annotations.Nullable()
                private final org.wordpress.android.ui.utils.UiString visitPostExcerptFooterLinkText = null;
                @org.jetbrains.annotations.Nullable()
                private final java.lang.String postLink = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ExcerptFooterUiState copy(@org.jetbrains.annotations.Nullable()
                org.wordpress.android.ui.utils.UiString visitPostExcerptFooterLinkText, @org.jetbrains.annotations.Nullable()
                java.lang.String postLink) {
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
                
                public ExcerptFooterUiState(@org.jetbrains.annotations.Nullable()
                org.wordpress.android.ui.utils.UiString visitPostExcerptFooterLinkText, @org.jetbrains.annotations.Nullable()
                java.lang.String postLink) {
                    super();
                }
                
                @org.jetbrains.annotations.Nullable()
                public final org.wordpress.android.ui.utils.UiString component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final org.wordpress.android.ui.utils.UiString getVisitPostExcerptFooterLinkText() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.String component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.String getPostLink() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB7\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0003\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0003H\u00c6\u0003JC\u0010\u0016\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0003H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\nH\u00d6\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;", "", "cards", "", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState$ReaderRelatedPostUiState;", "isGlobal", "", "headerLabel", "Lorg/wordpress/android/ui/utils/UiString;", "railcarJsonStrings", "", "(Ljava/util/List;ZLorg/wordpress/android/ui/utils/UiString;Ljava/util/List;)V", "getCards", "()Ljava/util/List;", "getHeaderLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "()Z", "getRailcarJsonStrings", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "ReaderRelatedPostUiState", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RelatedPostsUiState {
                @org.jetbrains.annotations.Nullable()
                private final java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState.ReaderRelatedPostUiState> cards = null;
                private final boolean isGlobal = false;
                @org.jetbrains.annotations.Nullable()
                private final org.wordpress.android.ui.utils.UiString headerLabel = null;
                @org.jetbrains.annotations.NotNull()
                private final java.util.List<java.lang.String> railcarJsonStrings = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState copy(@org.jetbrains.annotations.Nullable()
                java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState.ReaderRelatedPostUiState> cards, boolean isGlobal, @org.jetbrains.annotations.Nullable()
                org.wordpress.android.ui.utils.UiString headerLabel, @org.jetbrains.annotations.NotNull()
                java.util.List<java.lang.String> railcarJsonStrings) {
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
                
                public RelatedPostsUiState(@org.jetbrains.annotations.Nullable()
                java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState.ReaderRelatedPostUiState> cards, boolean isGlobal, @org.jetbrains.annotations.Nullable()
                org.wordpress.android.ui.utils.UiString headerLabel, @org.jetbrains.annotations.NotNull()
                java.util.List<java.lang.String> railcarJsonStrings) {
                    super();
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState.ReaderRelatedPostUiState> component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState.ReaderRelatedPostUiState> getCards() {
                    return null;
                }
                
                public final boolean component2() {
                    return false;
                }
                
                public final boolean isGlobal() {
                    return false;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final org.wordpress.android.ui.utils.UiString component3() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final org.wordpress.android.ui.utils.UiString getHeaderLabel() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final java.util.List<java.lang.String> component4() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final java.util.List<java.lang.String> getRailcarJsonStrings() {
                    return null;
                }
                
                @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\u000eH\u00c6\u0003J!\u0010)\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003J\u0081\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2 \b\u0002\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0001J\u0013\u0010+\u001a\u00020\u00062\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u001cR)\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState$ReaderRelatedPostUiState;", "", "postId", "", "blogId", "isGlobal", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "excerpt", "featuredImageUrl", "", "featuredImageVisibility", "featuredImageCornerRadius", "Lorg/wordpress/android/ui/utils/UiDimen;", "onItemClicked", "Lkotlin/Function3;", "", "(JJZLorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/String;ZLorg/wordpress/android/ui/utils/UiDimen;Lkotlin/jvm/functions/Function3;)V", "getBlogId", "()J", "getExcerpt", "()Lorg/wordpress/android/ui/utils/UiString;", "getFeaturedImageCornerRadius", "()Lorg/wordpress/android/ui/utils/UiDimen;", "getFeaturedImageUrl", "()Ljava/lang/String;", "getFeaturedImageVisibility", "()Z", "getOnItemClicked", "()Lkotlin/jvm/functions/Function3;", "getPostId", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
                public static final class ReaderRelatedPostUiState {
                    private final long postId = 0L;
                    private final long blogId = 0L;
                    private final boolean isGlobal = false;
                    @org.jetbrains.annotations.Nullable()
                    private final org.wordpress.android.ui.utils.UiString title = null;
                    @org.jetbrains.annotations.Nullable()
                    private final org.wordpress.android.ui.utils.UiString excerpt = null;
                    @org.jetbrains.annotations.Nullable()
                    private final java.lang.String featuredImageUrl = null;
                    private final boolean featuredImageVisibility = false;
                    @org.jetbrains.annotations.NotNull()
                    private final org.wordpress.android.ui.utils.UiDimen featuredImageCornerRadius = null;
                    @org.jetbrains.annotations.NotNull()
                    private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, java.lang.Boolean, kotlin.Unit> onItemClicked = null;
                    
                    @org.jetbrains.annotations.NotNull()
                    public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState.ReaderRelatedPostUiState copy(long postId, long blogId, boolean isGlobal, @org.jetbrains.annotations.Nullable()
                    org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
                    org.wordpress.android.ui.utils.UiString excerpt, @org.jetbrains.annotations.Nullable()
                    java.lang.String featuredImageUrl, boolean featuredImageVisibility, @org.jetbrains.annotations.NotNull()
                    org.wordpress.android.ui.utils.UiDimen featuredImageCornerRadius, @org.jetbrains.annotations.NotNull()
                    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> onItemClicked) {
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
                    
                    public ReaderRelatedPostUiState(long postId, long blogId, boolean isGlobal, @org.jetbrains.annotations.Nullable()
                    org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
                    org.wordpress.android.ui.utils.UiString excerpt, @org.jetbrains.annotations.Nullable()
                    java.lang.String featuredImageUrl, boolean featuredImageVisibility, @org.jetbrains.annotations.NotNull()
                    org.wordpress.android.ui.utils.UiDimen featuredImageCornerRadius, @org.jetbrains.annotations.NotNull()
                    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> onItemClicked) {
                        super();
                    }
                    
                    public final long component1() {
                        return 0L;
                    }
                    
                    public final long getPostId() {
                        return 0L;
                    }
                    
                    public final long component2() {
                        return 0L;
                    }
                    
                    public final long getBlogId() {
                        return 0L;
                    }
                    
                    public final boolean component3() {
                        return false;
                    }
                    
                    public final boolean isGlobal() {
                        return false;
                    }
                    
                    @org.jetbrains.annotations.Nullable()
                    public final org.wordpress.android.ui.utils.UiString component4() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.Nullable()
                    public final org.wordpress.android.ui.utils.UiString getTitle() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.Nullable()
                    public final org.wordpress.android.ui.utils.UiString component5() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.Nullable()
                    public final org.wordpress.android.ui.utils.UiString getExcerpt() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.Nullable()
                    public final java.lang.String component6() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.Nullable()
                    public final java.lang.String getFeaturedImageUrl() {
                        return null;
                    }
                    
                    public final boolean component7() {
                        return false;
                    }
                    
                    public final boolean getFeaturedImageVisibility() {
                        return false;
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    public final org.wordpress.android.ui.utils.UiDimen component8() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    public final org.wordpress.android.ui.utils.UiDimen getFeaturedImageCornerRadius() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, java.lang.Boolean, kotlin.Unit> component9() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, java.lang.Boolean, kotlin.Unit> getOnItemClicked() {
                        return null;
                    }
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087T\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$Companion;", "", "()V", "MAX_NUM_LIKES_FACES_WITHOUT_SELF", "", "getMAX_NUM_LIKES_FACES_WITHOUT_SELF$annotations", "MAX_NUM_LIKES_FACES_WITH_SELF", "getMAX_NUM_LIKES_FACES_WITH_SELF$annotations", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @androidx.annotation.VisibleForTesting(otherwise = androidx.annotation.VisibleForTesting.PRIVATE)
        @java.lang.Deprecated()
        public static void getMAX_NUM_LIKES_FACES_WITH_SELF$annotations() {
        }
        
        @androidx.annotation.VisibleForTesting(otherwise = androidx.annotation.VisibleForTesting.PRIVATE)
        @java.lang.Deprecated()
        public static void getMAX_NUM_LIKES_FACES_WITHOUT_SELF$annotations() {
        }
    }
}