// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.wikidata;

import android.content.Context;
import com.google.gson.Gson;
import dagger.internal.Factory;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import javax.inject.Provider;

public final class WikidataEditService_Factory implements Factory<WikidataEditService> {
  private final Provider<Context> contextProvider;

  private final Provider<WikidataEditListener> wikidataEditListenerProvider;

  private final Provider<JsonKvStore> directKvStoreProvider;

  private final Provider<WikiBaseClient> wikiBaseClientProvider;

  private final Provider<WikidataClient> wikidataClientProvider;

  private final Provider<Gson> gsonProvider;

  public WikidataEditService_Factory(Provider<Context> contextProvider,
      Provider<WikidataEditListener> wikidataEditListenerProvider,
      Provider<JsonKvStore> directKvStoreProvider, Provider<WikiBaseClient> wikiBaseClientProvider,
      Provider<WikidataClient> wikidataClientProvider, Provider<Gson> gsonProvider) {
    this.contextProvider = contextProvider;
    this.wikidataEditListenerProvider = wikidataEditListenerProvider;
    this.directKvStoreProvider = directKvStoreProvider;
    this.wikiBaseClientProvider = wikiBaseClientProvider;
    this.wikidataClientProvider = wikidataClientProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public WikidataEditService get() {
    return new WikidataEditService(contextProvider.get(), wikidataEditListenerProvider.get(), directKvStoreProvider.get(), wikiBaseClientProvider.get(), wikidataClientProvider.get(), gsonProvider.get());
  }

  public static WikidataEditService_Factory create(Provider<Context> contextProvider,
      Provider<WikidataEditListener> wikidataEditListenerProvider,
      Provider<JsonKvStore> directKvStoreProvider, Provider<WikiBaseClient> wikiBaseClientProvider,
      Provider<WikidataClient> wikidataClientProvider, Provider<Gson> gsonProvider) {
    return new WikidataEditService_Factory(contextProvider, wikidataEditListenerProvider, directKvStoreProvider, wikiBaseClientProvider, wikidataClientProvider, gsonProvider);
  }

  public static WikidataEditService newInstance(Context context,
      WikidataEditListener wikidataEditListener, JsonKvStore directKvStore,
      WikiBaseClient wikiBaseClient, WikidataClient wikidataClient, Gson gson) {
    return new WikidataEditService(context, wikidataEditListener, directKvStore, wikiBaseClient, wikidataClient, gson);
  }
}
