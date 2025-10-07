package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.PaymentDao;
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
public final class AppModule_ProvidePaymentDaoFactory implements Factory<PaymentDao> {
  @Override
  public PaymentDao get() {
    return providePaymentDao();
  }

  public static AppModule_ProvidePaymentDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PaymentDao providePaymentDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePaymentDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvidePaymentDaoFactory INSTANCE = new AppModule_ProvidePaymentDaoFactory();
  }
}
