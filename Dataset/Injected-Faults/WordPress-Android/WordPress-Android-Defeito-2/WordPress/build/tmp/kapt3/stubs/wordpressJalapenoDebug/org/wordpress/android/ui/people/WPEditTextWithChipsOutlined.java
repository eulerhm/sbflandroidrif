package org.wordpress.android.ui.people;

import java.lang.System;

/**
 * As of our understanding, currently the TextInputLayout (that is a LinearLayout) works in OutlinedBox mode having
 * a single TextInputEditText as a direct child, inflating needed views into an internal not accessible FrameLayout;
 * so not easily possible to add additional views over the main TextInputLayout keeping the usual
 * behaviours of the TextInputEditText.
 *
 * This component was mainly created to address the move of PeopleInviteFragment into material design.
 * Specifically tries to cover the usage of chips in combination with an EditText while mimic an
 * OutlinedBox with TextInputLayout material design behaviour.
 *
 * The implementation is pretty basic and do not cover all possible use cases. We should leave this component usage
 * once we find a more out of the box solution with material design lib (keep an eye on future releases) that
 * covers our use cases.
 *
 * Supports hint/label animation (with RTL support).
 *
 * Based and inspired by https://stackoverflow.com/a/61748466
 *
 * Note: other possible implementation is using ImageSpan in the EditText but we found cursor/keyboard management
 * to be more complex and less clean and preferred to use the FlexBox approach for now.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 ]2\u00020\u0001:\u0007[\\]^_`aB%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0002J\u0016\u0010(\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020\u0011H\u0002J\b\u0010-\u001a\u00020\nH\u0002J\u0018\u0010.\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\u0006\u0010)\u001a\u00020*H\u0002J\u000e\u0010/\u001a\u00020\n2\u0006\u0010&\u001a\u00020\'J\u0010\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\'H\u0002J\u0014\u00102\u001a\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020403H\u0002J\f\u00105\u001a\b\u0012\u0004\u0012\u00020\'06J\b\u00107\u001a\u0004\u0018\u00010\'J\u0006\u00108\u001a\u00020\nJ\b\u00109\u001a\u00020\nH\u0002J\b\u0010:\u001a\u00020\nH\u0002J\b\u0010;\u001a\u00020\nH\u0002J\b\u0010<\u001a\u00020\nH\u0002J\b\u0010=\u001a\u00020\nH\u0002J\b\u0010>\u001a\u00020%H\u0002J\b\u0010?\u001a\u00020%H\u0002J\b\u0010@\u001a\u00020%H\u0002J\u0012\u0010A\u001a\u00020%2\b\u0010)\u001a\u0004\u0018\u00010BH\u0014J\b\u0010C\u001a\u00020BH\u0014J\u000e\u0010D\u001a\u00020%2\u0006\u0010&\u001a\u00020\'J\u0014\u0010E\u001a\u0004\u0018\u00010\'2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0002J\b\u0010F\u001a\u00020\nH\u0002J\b\u0010G\u001a\u00020%H\u0002J\u0010\u0010H\u001a\u00020%2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J \u0010I\u001a\u00020%2\u0006\u0010J\u001a\u00020\u00132\u0006\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\u0015H\u0002J\b\u0010M\u001a\u00020%H\u0002J \u0010N\u001a\u00020%2\u0006\u0010O\u001a\u00020\u00072\u0006\u0010K\u001a\u00020\u00072\u0006\u0010L\u001a\u00020\u0015H\u0002J \u0010P\u001a\u00020%2\u0006\u0010Q\u001a\u00020\n2\u0006\u0010<\u001a\u00020\n2\u0006\u0010R\u001a\u00020\nH\u0002J\b\u0010S\u001a\u00020%H\u0002J,\u0010T\u001a\u0002HU\"\u0004\b\u0000\u0010U2\u0017\u0010V\u001a\u0013\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u0002HU0W\u00a2\u0006\u0002\bYH\u0002\u00a2\u0006\u0002\u0010ZR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006b"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "chipifyEnabled", "", "colorSurface", "editor", "Lcom/google/android/material/textfield/TextInputEditText;", "flexBox", "Lcom/google/android/flexbox/FlexboxLayout;", "helperTextState", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$HelperTextState;", "hint", "Lcom/google/android/material/textview/MaterialTextView;", "hintLabelColorAlphaDefault", "", "hintResourceId", "isRightToLeft", "itemsManager", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ItemsManagerInterface;", "label", "maxChips", "outlineColorAlphaDefault", "outlineColorAlphaFocused", "outlineColorDefault", "outlineColorFocused", "outlineDrawable", "Lcom/google/android/material/shape/MaterialShapeDrawable;", "outlinePixelWidthDefault", "outlinePixelWidthFocused", "addItem", "", "item", "", "addOrUpdateChip", "state", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ItemValidationState;", "animateViewTo", "targetState", "canAddMoreChips", "chipify", "containsChip", "endsWithDelimiter", "string", "getChipsMap", "", "Lcom/google/android/material/chip/Chip;", "getChipsStrings", "", "getTextIfAvailableOrNull", "hasChips", "hasHint", "hasItems", "hasItemsOrText", "hasText", "isEditorFocused", "loadColors", "loadDimensions", "loadOutlineDrawable", "onRestoreInstanceState", "Landroid/os/Parcelable;", "onSaveInstanceState", "removeChip", "removeDelimiterFromItemIfPresent", "removeLastEnteredItem", "resetText", "setItemsManager", "setLabelColor", "textView", "color", "alpha", "setListeners", "setOutlineStroke", "width", "styleView", "hasFocus", "animateOnChange", "throwExceptionIfChipifyNotEnabled", "withBinding", "T", "block", "Lkotlin/Function1;", "Lorg/wordpress/android/databinding/WpEditTextWithChipsOutlinedBinding;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ChipData", "ChipReplaceData", "Companion", "HelperTextState", "ItemValidationState", "ItemsManagerInterface", "SavedState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPEditTextWithChipsOutlined extends androidx.constraintlayout.widget.ConstraintLayout {
    private org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.HelperTextState helperTextState = org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.HelperTextState.HINT_VISIBLE;
    private com.google.android.flexbox.FlexboxLayout flexBox;
    private com.google.android.material.textfield.TextInputEditText editor;
    private com.google.android.material.textview.MaterialTextView label;
    private com.google.android.material.textview.MaterialTextView hint;
    private com.google.android.material.shape.MaterialShapeDrawable outlineDrawable;
    private int outlineColorDefault = 0;
    private int outlineColorFocused = 0;
    private int colorSurface = 0;
    private float outlineColorAlphaFocused = 0.0F;
    private float outlineColorAlphaDefault = 0.0F;
    private float hintLabelColorAlphaDefault = 0.0F;
    private int outlinePixelWidthDefault = 0;
    private int outlinePixelWidthFocused = 0;
    private int hintResourceId = 0;
    private boolean chipifyEnabled = false;
    private int maxChips = 0;
    private boolean isRightToLeft = false;
    private org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemsManagerInterface itemsManager;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.Companion Companion = null;
    private static final long LABEL_ANIMATION_DURATION = 167L;
    private static final java.lang.String[] ITEM_DELIMITERS = {" ", ","};
    
    @kotlin.jvm.JvmOverloads()
    public WPEditTextWithChipsOutlined(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public WPEditTextWithChipsOutlined(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public WPEditTextWithChipsOutlined(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    public final void setItemsManager(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemsManagerInterface itemsManager) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTextIfAvailableOrNull() {
        return null;
    }
    
    public final void addOrUpdateChip(@org.jetbrains.annotations.NotNull()
    java.lang.String item, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemValidationState state) {
    }
    
    public final void removeChip(@org.jetbrains.annotations.NotNull()
    java.lang.String item) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getChipsStrings() {
        return null;
    }
    
    public final boolean hasChips() {
        return false;
    }
    
    public final boolean containsChip(@org.jetbrains.annotations.NotNull()
    java.lang.String item) {
        return false;
    }
    
    private final <T extends java.lang.Object>T withBinding(kotlin.jvm.functions.Function1<? super org.wordpress.android.databinding.WpEditTextWithChipsOutlinedBinding, ? extends T> block) {
        return null;
    }
    
    private final void loadOutlineDrawable() {
    }
    
    private final void loadDimensions() {
    }
    
    private final void loadColors() {
    }
    
    private final void setListeners() {
    }
    
    private final boolean canAddMoreChips() {
        return false;
    }
    
    private final void addItem(java.lang.String item) {
    }
    
    private final void chipify(java.lang.String item, org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemValidationState state) {
    }
    
    private final void resetText() {
    }
    
    private final java.lang.String removeDelimiterFromItemIfPresent(java.lang.String item) {
        return null;
    }
    
    private final void throwExceptionIfChipifyNotEnabled() {
    }
    
    private final java.util.Map<java.lang.String, com.google.android.material.chip.Chip> getChipsMap() {
        return null;
    }
    
    private final boolean removeLastEnteredItem() {
        return false;
    }
    
    private final boolean endsWithDelimiter(java.lang.String string) {
        return false;
    }
    
    private final void styleView(boolean hasFocus, boolean hasText, boolean animateOnChange) {
    }
    
    private final void animateViewTo(org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.HelperTextState targetState) {
    }
    
    private final void setOutlineStroke(int width, int color, float alpha) {
    }
    
    private final void setLabelColor(com.google.android.material.textview.MaterialTextView textView, int color, float alpha) {
    }
    
    private final boolean hasItemsOrText() {
        return false;
    }
    
    private final boolean hasItems() {
        return false;
    }
    
    private final boolean hasText() {
        return false;
    }
    
    private final boolean isEditorFocused() {
        return false;
    }
    
    private final boolean hasHint() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.os.Parcelable onSaveInstanceState() {
        return null;
    }
    
    @java.lang.Override()
    protected void onRestoreInstanceState(@org.jetbrains.annotations.Nullable()
    android.os.Parcelable state) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$HelperTextState;", "", "(Ljava/lang/String;I)V", "HINT_VISIBLE", "LABEL_VISIBLE", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum HelperTextState {
        /*public static final*/ HINT_VISIBLE /* = new HINT_VISIBLE() */,
        /*public static final*/ LABEL_VISIBLE /* = new LABEL_VISIBLE() */;
        
        HelperTextState() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ItemsManagerInterface;", "", "onAddItem", "", "item", "", "onRemoveItem", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface ItemsManagerInterface {
        
        public abstract void onRemoveItem(@org.jetbrains.annotations.NotNull()
        java.lang.String item);
        
        public abstract void onAddItem(@org.jetbrains.annotations.NotNull()
        java.lang.String item);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ItemValidationState;", "", "colorAttr", "", "(Ljava/lang/String;II)V", "getColorAttr", "()I", "colorFromState", "context", "Landroid/content/Context;", "NEUTRAL", "VALIDATED", "VALIDATED_WITH_ERRORS", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ItemValidationState {
        /*public static final*/ NEUTRAL /* = new NEUTRAL(0) */,
        /*public static final*/ VALIDATED /* = new VALIDATED(0) */,
        /*public static final*/ VALIDATED_WITH_ERRORS /* = new VALIDATED_WITH_ERRORS(0) */;
        private final int colorAttr = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemValidationState.Companion Companion = null;
        
        ItemValidationState(@androidx.annotation.AttrRes()
        int colorAttr) {
        }
        
        public final int getColorAttr() {
            return 0;
        }
        
        @androidx.annotation.ColorInt()
        public final int colorFromState(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ItemValidationState$Companion;", "", "()V", "stateFromColor", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ItemValidationState;", "context", "Landroid/content/Context;", "color", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemValidationState stateFromColor(@org.jetbrains.annotations.NotNull()
            android.content.Context context, int color) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ChipReplaceData;", "", "index", "", "chip", "Lcom/google/android/material/chip/Chip;", "isAlreadyInGroup", "", "(ILcom/google/android/material/chip/Chip;Z)V", "getChip", "()Lcom/google/android/material/chip/Chip;", "getIndex", "()I", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class ChipReplaceData {
        private final int index = 0;
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.material.chip.Chip chip = null;
        private final boolean isAlreadyInGroup = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipReplaceData copy(int index, @org.jetbrains.annotations.NotNull()
        com.google.android.material.chip.Chip chip, boolean isAlreadyInGroup) {
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
        
        public ChipReplaceData(int index, @org.jetbrains.annotations.NotNull()
        com.google.android.material.chip.Chip chip, boolean isAlreadyInGroup) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getIndex() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.material.chip.Chip component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.material.chip.Chip getChip() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isAlreadyInGroup() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0082\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\bH\u00c6\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\b\u0010\u0011\u001a\u00020\bH\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0006H\u00d6\u0001J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\bH\u0016R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ChipData;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "text", "", "color", "", "(Ljava/lang/String;I)V", "getColor", "()I", "getText", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class ChipData implements android.os.Parcelable {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        private final int color = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData.CREATOR CREATOR = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData copy(@org.jetbrains.annotations.NotNull()
        java.lang.String text, int color) {
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
        
        public ChipData(@org.jetbrains.annotations.NotNull()
        java.lang.String text, int color) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getColor() {
            return 0;
        }
        
        public ChipData(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel) {
            super();
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ChipData$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ChipData;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ChipData;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class CREATOR implements android.os.Parcelable.Creator<org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData> {
            
            private CREATOR() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel parcel) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData[] newArray(int size) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004B\u000f\b\u0012\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!H\u0016R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$SavedState;", "Landroid/view/View$BaseSavedState;", "superState", "Landroid/os/Parcelable;", "(Landroid/os/Parcelable;)V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "chipsData", "", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$ChipData;", "getChipsData", "()Ljava/util/List;", "setChipsData", "(Ljava/util/List;)V", "hasText", "", "getHasText", "()Z", "setHasText", "(Z)V", "isFocused", "setFocused", "labelState", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$HelperTextState;", "getLabelState", "()Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$HelperTextState;", "setLabelState", "(Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$HelperTextState;)V", "writeToParcel", "", "out", "flags", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class SavedState extends android.view.View.BaseSavedState {
        @org.jetbrains.annotations.NotNull()
        private org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.HelperTextState labelState = org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.HelperTextState.HINT_VISIBLE;
        private boolean isFocused = false;
        private boolean hasText = false;
        @org.jetbrains.annotations.NotNull()
        private java.util.List<org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData> chipsData;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.SavedState.Companion Companion = null;
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmField()
        public static final android.os.Parcelable.Creator<org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.SavedState> CREATOR = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.HelperTextState getLabelState() {
            return null;
        }
        
        public final void setLabelState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.HelperTextState p0) {
        }
        
        public final boolean isFocused() {
            return false;
        }
        
        public final void setFocused(boolean p0) {
        }
        
        public final boolean getHasText() {
            return false;
        }
        
        public final void setHasText(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData> getChipsData() {
            return null;
        }
        
        public final void setChipsData(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ChipData> p0) {
        }
        
        public SavedState(@org.jetbrains.annotations.Nullable()
        android.os.Parcelable superState) {
            super(null);
        }
        
        private SavedState(android.os.Parcel in) {
            super(null);
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel out, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$SavedState$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$SavedState;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/people/WPEditTextWithChipsOutlined$Companion;", "", "()V", "ITEM_DELIMITERS", "", "", "[Ljava/lang/String;", "LABEL_ANIMATION_DURATION", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}