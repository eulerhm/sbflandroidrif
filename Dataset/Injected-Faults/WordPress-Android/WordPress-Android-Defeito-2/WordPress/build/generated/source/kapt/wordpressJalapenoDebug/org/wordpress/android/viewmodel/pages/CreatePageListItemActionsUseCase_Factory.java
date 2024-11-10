package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CreatePageListItemActionsUseCase_Factory implements Factory<CreatePageListItemActionsUseCase> {
  @Override
  public CreatePageListItemActionsUseCase get() {
    return newInstance();
  }

  public static CreatePageListItemActionsUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CreatePageListItemActionsUseCase newInstance() {
    return new CreatePageListItemActionsUseCase();
  }

  private static final class InstanceHolder {
    private static final CreatePageListItemActionsUseCase_Factory INSTANCE = new CreatePageListItemActionsUseCase_Factory();
  }
}
