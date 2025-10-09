package com.extrotarget.extropos.di

import com.extrotarget.extropos.data.repository.*
import com.extrotarget.extropos.domain.repository.*
import com.extrotarget.extropos.data.repository.ProductRepositoryImpl
import com.extrotarget.extropos.domain.repository.IProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepository): IAuthRepository

    @Binds
    @Singleton
    abstract fun bindMenuRepository(impl: MenuRepository): IMenuRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): IProductRepository

    @Binds
    @Singleton
    abstract fun bindOrderRepository(impl: OrderRepository): IOrderRepository

    @Binds
    @Singleton
    abstract fun bindTableRepository(impl: TableRepository): ITableRepository

    @Binds
    @Singleton
    abstract fun bindTicketRepository(impl: TicketRepository): ITicketRepository
}
