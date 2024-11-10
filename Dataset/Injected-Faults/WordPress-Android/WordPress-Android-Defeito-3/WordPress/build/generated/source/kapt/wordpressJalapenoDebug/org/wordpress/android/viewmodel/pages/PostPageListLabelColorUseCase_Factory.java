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
public final class PostPageListLabelColorUseCase_Factory implements Factory<PostPageListLabelColorUseCase> {
  @Override
  public PostPageListLabelColorUseCase get() {
    return newInstance();
  }

  public static PostPageListLabelColorUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PostPageListLabelColorUseCase newInstance() {
    return new PostPageListLabelColorUseCase();
  }

  private static final class InstanceHolder {
    private static final PostPageListLabelColorUseCase_Factory INSTANCE = new PostPageListLabelColorUseCase_Factory();
  }
}
