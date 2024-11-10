package org.wordpress.android.ui.sitecreation.domains;

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
public final class SiteCreationDomainSanitizer_Factory implements Factory<SiteCreationDomainSanitizer> {
  @Override
  public SiteCreationDomainSanitizer get() {
    return newInstance();
  }

  public static SiteCreationDomainSanitizer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SiteCreationDomainSanitizer newInstance() {
    return new SiteCreationDomainSanitizer();
  }

  private static final class InstanceHolder {
    private static final SiteCreationDomainSanitizer_Factory INSTANCE = new SiteCreationDomainSanitizer_Factory();
  }
}
