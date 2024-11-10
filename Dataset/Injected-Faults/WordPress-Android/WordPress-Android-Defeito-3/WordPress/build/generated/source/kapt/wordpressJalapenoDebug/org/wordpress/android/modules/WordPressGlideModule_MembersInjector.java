package org.wordpress.android.modules;

import com.android.volley.RequestQueue;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Named;
import javax.inject.Provider;
import org.wordpress.android.networking.GlideRequestFactory;

@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class WordPressGlideModule_MembersInjector implements MembersInjector<WordPressGlideModule> {
  private final Provider<RequestQueue> requestQueueProvider;

  private final Provider<RequestQueue> noRedirectsRequestQueueProvider;

  private final Provider<GlideRequestFactory> glideRequestFactoryProvider;

  public WordPressGlideModule_MembersInjector(Provider<RequestQueue> requestQueueProvider,
      Provider<RequestQueue> noRedirectsRequestQueueProvider,
      Provider<GlideRequestFactory> glideRequestFactoryProvider) {
    this.requestQueueProvider = requestQueueProvider;
    this.noRedirectsRequestQueueProvider = noRedirectsRequestQueueProvider;
    this.glideRequestFactoryProvider = glideRequestFactoryProvider;
  }

  public static MembersInjector<WordPressGlideModule> create(
      Provider<RequestQueue> requestQueueProvider,
      Provider<RequestQueue> noRedirectsRequestQueueProvider,
      Provider<GlideRequestFactory> glideRequestFactoryProvider) {
    return new WordPressGlideModule_MembersInjector(requestQueueProvider, noRedirectsRequestQueueProvider, glideRequestFactoryProvider);
  }

  @Override
  public void injectMembers(WordPressGlideModule instance) {
    injectRequestQueue(instance, requestQueueProvider.get());
    injectNoRedirectsRequestQueue(instance, noRedirectsRequestQueueProvider.get());
    injectGlideRequestFactory(instance, glideRequestFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.modules.WordPressGlideModule.requestQueue")
  @Named("custom-ssl")
  public static void injectRequestQueue(WordPressGlideModule instance, RequestQueue requestQueue) {
    instance.requestQueue = requestQueue;
  }

  @InjectedFieldSignature("org.wordpress.android.modules.WordPressGlideModule.noRedirectsRequestQueue")
  @Named("no-redirects")
  public static void injectNoRedirectsRequestQueue(WordPressGlideModule instance,
      RequestQueue noRedirectsRequestQueue) {
    instance.noRedirectsRequestQueue = noRedirectsRequestQueue;
  }

  @InjectedFieldSignature("org.wordpress.android.modules.WordPressGlideModule.glideRequestFactory")
  public static void injectGlideRequestFactory(WordPressGlideModule instance,
      GlideRequestFactory glideRequestFactory) {
    instance.glideRequestFactory = glideRequestFactory;
  }
}
