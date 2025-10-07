package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.CustomerDao;
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
public final class AppModule_ProvideCustomerDaoFactory implements Factory<CustomerDao> {
  @Override
  public CustomerDao get() {
    return provideCustomerDao();
  }

  public static AppModule_ProvideCustomerDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CustomerDao provideCustomerDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCustomerDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideCustomerDaoFactory INSTANCE = new AppModule_ProvideCustomerDaoFactory();
  }
}
