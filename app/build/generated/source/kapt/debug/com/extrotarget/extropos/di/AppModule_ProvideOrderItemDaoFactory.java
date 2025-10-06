package com.extrotarget.extropos.di;

import com.extrotarget.extropos.data.local.dao.OrderItemDao;
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
public final class AppModule_ProvideOrderItemDaoFactory implements Factory<OrderItemDao> {
  @Override
  public OrderItemDao get() {
    return provideOrderItemDao();
  }

  public static AppModule_ProvideOrderItemDaoFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OrderItemDao provideOrderItemDao() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideOrderItemDao());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideOrderItemDaoFactory INSTANCE = new AppModule_ProvideOrderItemDaoFactory();
  }
}
