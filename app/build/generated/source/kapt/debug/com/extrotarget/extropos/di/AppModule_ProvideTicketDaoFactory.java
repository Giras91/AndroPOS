package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.AppDatabase;
import com.extrotarget.extropos.data.local.dao.TicketDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class AppModule_ProvideTicketDaoFactory implements Factory<TicketDao> {
  private final Provider<AppDatabase> dbProvider;

  public AppModule_ProvideTicketDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TicketDao get() {
    return provideTicketDao(dbProvider.get());
  }

  public static AppModule_ProvideTicketDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new AppModule_ProvideTicketDaoFactory(dbProvider);
  }

  public static TicketDao provideTicketDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTicketDao(db));
  }
}
