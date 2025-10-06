package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.TicketDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
  @Override
  public TicketDao get() {
    return provideTicketDao();
  }

  public static AppModule_ProvideTicketDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TicketDao provideTicketDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTicketDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideTicketDaoFactory INSTANCE = new AppModule_ProvideTicketDaoFactory();
  }
}
