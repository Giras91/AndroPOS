package com.example.pos.data.repository;

import com.example.pos.data.local.dao.TicketDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
    "KotlinInternalInJava"
})
public final class TicketRepository_Factory implements Factory<TicketRepository> {
  private final Provider<TicketDao> ticketDaoProvider;

  public TicketRepository_Factory(Provider<TicketDao> ticketDaoProvider) {
    this.ticketDaoProvider = ticketDaoProvider;
  }

  @Override
  public TicketRepository get() {
    return newInstance(ticketDaoProvider.get());
  }

  public static TicketRepository_Factory create(Provider<TicketDao> ticketDaoProvider) {
    return new TicketRepository_Factory(ticketDaoProvider);
  }

  public static TicketRepository newInstance(TicketDao ticketDao) {
    return new TicketRepository(ticketDao);
  }
}
