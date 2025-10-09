package com.extrotarget.extropos.ui.debug;

import com.extrotarget.extropos.domain.repository.IMenuRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DebugCategoryActivity_MembersInjector implements MembersInjector<DebugCategoryActivity> {
  private final Provider<IMenuRepository> menuRepositoryProvider;

  public DebugCategoryActivity_MembersInjector(Provider<IMenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  public static MembersInjector<DebugCategoryActivity> create(
      Provider<IMenuRepository> menuRepositoryProvider) {
    return new DebugCategoryActivity_MembersInjector(menuRepositoryProvider);
  }

  @Override
  public void injectMembers(DebugCategoryActivity instance) {
    injectMenuRepository(instance, menuRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.extrotarget.extropos.ui.debug.DebugCategoryActivity.menuRepository")
  public static void injectMenuRepository(DebugCategoryActivity instance,
      IMenuRepository menuRepository) {
    instance.menuRepository = menuRepository;
  }
}
