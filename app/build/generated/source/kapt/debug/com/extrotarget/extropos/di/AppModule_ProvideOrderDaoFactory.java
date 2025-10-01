package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.OrderDao;
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
    "KotlinInternalInJava"
})
public final class AppModule_ProvideOrderDaoFactory implements Factory<OrderDao> {
  @Override
  public OrderDao get() {
    return provideOrderDao();
  }

  public static AppModule_ProvideOrderDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OrderDao provideOrderDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideOrderDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideOrderDaoFactory INSTANCE = new AppModule_ProvideOrderDaoFactory();
  }
}
