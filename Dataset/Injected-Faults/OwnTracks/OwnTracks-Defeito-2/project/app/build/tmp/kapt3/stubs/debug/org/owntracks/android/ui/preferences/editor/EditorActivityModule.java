package org.owntracks.android.ui.preferences.editor;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;
import org.owntracks.android.injection.scopes.PerActivity;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lorg/owntracks/android/ui/preferences/editor/EditorActivityModule;", "", "()V", "bindActivity", "Landroidx/appcompat/app/AppCompatActivity;", "a", "Lorg/owntracks/android/ui/preferences/editor/EditorActivity;", "bindViewModel", "Lorg/owntracks/android/ui/preferences/editor/EditorMvvm$ViewModel;", "Lorg/owntracks/android/ui/preferences/editor/EditorMvvm$View;", "viewModel", "Lorg/owntracks/android/ui/preferences/editor/EditorViewModel;", "app_debug"})
@dagger.Module()
public abstract class EditorActivityModule {
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerActivity()
    @dagger.Binds()
    public abstract androidx.appcompat.app.AppCompatActivity bindActivity(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.ui.preferences.editor.EditorActivity a);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract org.owntracks.android.ui.preferences.editor.EditorMvvm.ViewModel<org.owntracks.android.ui.preferences.editor.EditorMvvm.View> bindViewModel(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.ui.preferences.editor.EditorViewModel viewModel);
    
    public EditorActivityModule() {
        super();
    }
}