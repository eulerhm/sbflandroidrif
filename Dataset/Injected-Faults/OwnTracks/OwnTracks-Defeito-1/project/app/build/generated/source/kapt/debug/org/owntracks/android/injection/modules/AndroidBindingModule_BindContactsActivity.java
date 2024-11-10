package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.contacts.ContactsActivity;
import org.owntracks.android.ui.contacts.ContactsActivityModule;

@Module(
  subcomponents = AndroidBindingModule_BindContactsActivity.ContactsActivitySubcomponent.class
)
public abstract class AndroidBindingModule_BindContactsActivity {
  private AndroidBindingModule_BindContactsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ContactsActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ContactsActivitySubcomponent.Factory builder);

  @Subcomponent(modules = ContactsActivityModule.class)
  @PerActivity
  public interface ContactsActivitySubcomponent extends AndroidInjector<ContactsActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ContactsActivity> {}
  }
}
