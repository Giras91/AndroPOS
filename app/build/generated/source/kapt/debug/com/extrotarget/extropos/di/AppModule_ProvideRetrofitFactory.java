package com.extrotarget.extropos.di;

import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final Provider<Moshi> moshiProvider;

  public AppModule_ProvideRetrofitFactory(Provider<Moshi> moshiProvider) {
    this.moshiProvider = moshiProvider;
  }

  @Override
  public Retrofit get() {
    return provideRetrofit(moshiProvider.get());
  }

  public static AppModule_ProvideRetrofitFactory create(Provider<Moshi> moshiProvider) {
    return new AppModule_ProvideRetrofitFactory(moshiProvider);
  }

  public static Retrofit provideRetrofit(Moshi moshi) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRetrofit(moshi));
  }
}
