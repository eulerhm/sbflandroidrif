package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

/**
 * This class is based on the {@link com.github.mikephil.charting.formatter.LargeValueFormatter} and fixes the issue
 * with Locale other than US (in some languages is the DecimalFormat different).
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J(\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/LargeValueFormatter;", "Lcom/github/mikephil/charting/formatter/ValueFormatter;", "()V", "mFormat", "Ljava/text/DecimalFormat;", "mMaxLength", "", "mSuffix", "", "", "[Ljava/lang/String;", "getFormattedValue", "value", "", "entry", "Lcom/github/mikephil/charting/data/Entry;", "dataSetIndex", "viewPortHandler", "Lcom/github/mikephil/charting/utils/ViewPortHandler;", "makePretty", "number", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LargeValueFormatter extends com.github.mikephil.charting.formatter.ValueFormatter {
    private java.lang.String[] mSuffix = {"", "k", "m", "b", "t"};
    private int mMaxLength = 5;
    private final java.text.DecimalFormat mFormat = null;
    
    public LargeValueFormatter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getFormattedValue(float value, @org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.data.Entry entry, int dataSetIndex, @org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.utils.ViewPortHandler viewPortHandler) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getFormattedValue(float value) {
        return null;
    }
    
    private final java.lang.String makePretty(double number) {
        return null;
    }
}