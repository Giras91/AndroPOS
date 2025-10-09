package com.extrotarget.extropos.di

import com.extrotarget.extropos.domain.usecase.AddCategoryUseCase
import com.extrotarget.extropos.domain.usecase.AddMenuItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    // Use-cases have @Inject constructors; no explicit provider needed.
}
