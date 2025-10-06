package com.extrotarget.extropos;

import com.extrotarget.extropos.auth.EmailAuthActivity_GeneratedInjector;
import com.extrotarget.extropos.auth.EmailAuthViewModel_HiltModules;
import com.extrotarget.extropos.di.AppModule;
import com.extrotarget.extropos.di.RepositoryModule;
import com.extrotarget.extropos.pdf.test.PdfTestActivity_GeneratedInjector;
import com.extrotarget.extropos.printer.di.PrinterModule;
import com.extrotarget.extropos.printer.ui.PrinterViewModel_HiltModules;
import com.extrotarget.extropos.ui.auth.AppLockFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.auth.AuthViewModel_HiltModules;
import com.extrotarget.extropos.ui.auth.LoginFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.auth.SignupFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.cart.CartFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.cart.CartViewModel_HiltModules;
import com.extrotarget.extropos.ui.cart.TicketViewModel_HiltModules;
import com.extrotarget.extropos.ui.dashboard.DashboardActivity_GeneratedInjector;
import com.extrotarget.extropos.ui.login.LoginViewModel_HiltModules;
import com.extrotarget.extropos.ui.main.MainFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.menu.MenuFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.menu.MenuViewModel_HiltModules;
import com.extrotarget.extropos.ui.order.OrderViewModel_HiltModules;
import com.extrotarget.extropos.ui.pin.PinLoginActivity_GeneratedInjector;
import com.extrotarget.extropos.ui.pin.PinLoginViewModel_HiltModules;
import com.extrotarget.extropos.ui.pos.PosFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.product.ProductViewModel_HiltModules;
import com.extrotarget.extropos.ui.product.ProductsGridFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.settings.SettingsFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.settings.employee.EmployeeManagementFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.settings.printer.PrinterSetupFragment_GeneratedInjector;
import com.extrotarget.extropos.ui.table.TableViewModel_HiltModules;
import com.extrotarget.extropos.ui.user.AddUserActivity_GeneratedInjector;
import com.extrotarget.extropos.ui.user.AddUserViewModel_HiltModules;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_SavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.processing.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class App_HiltComponents {
  private App_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          AppModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          ApplicationContextModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          PrinterModule.class,
          RepositoryModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements App_GeneratedInjector,
      FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AddUserViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          AuthViewModel_HiltModules.KeyModule.class,
          CartViewModel_HiltModules.KeyModule.class,
          EmailAuthViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HiltWrapper_SavedStateHandleModule.class,
          LoginViewModel_HiltModules.KeyModule.class,
          MenuViewModel_HiltModules.KeyModule.class,
          OrderViewModel_HiltModules.KeyModule.class,
          PinLoginViewModel_HiltModules.KeyModule.class,
          PrinterViewModel_HiltModules.KeyModule.class,
          ProductViewModel_HiltModules.KeyModule.class,
          TableViewModel_HiltModules.KeyModule.class,
          TicketViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements MainActivity_GeneratedInjector,
      EmailAuthActivity_GeneratedInjector,
      PdfTestActivity_GeneratedInjector,
      DashboardActivity_GeneratedInjector,
      PinLoginActivity_GeneratedInjector,
      AddUserActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          AddUserViewModel_HiltModules.BindsModule.class,
          AuthViewModel_HiltModules.BindsModule.class,
          CartViewModel_HiltModules.BindsModule.class,
          EmailAuthViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          LoginViewModel_HiltModules.BindsModule.class,
          MenuViewModel_HiltModules.BindsModule.class,
          OrderViewModel_HiltModules.BindsModule.class,
          PinLoginViewModel_HiltModules.BindsModule.class,
          PrinterViewModel_HiltModules.BindsModule.class,
          ProductViewModel_HiltModules.BindsModule.class,
          TableViewModel_HiltModules.BindsModule.class,
          TicketViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements AppLockFragment_GeneratedInjector,
      LoginFragment_GeneratedInjector,
      SignupFragment_GeneratedInjector,
      CartFragment_GeneratedInjector,
      com.extrotarget.extropos.ui.login.LoginFragment_GeneratedInjector,
      MainFragment_GeneratedInjector,
      MenuFragment_GeneratedInjector,
      PosFragment_GeneratedInjector,
      ProductsGridFragment_GeneratedInjector,
      SettingsFragment_GeneratedInjector,
      EmployeeManagementFragment_GeneratedInjector,
      PrinterSetupFragment_GeneratedInjector,
      FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
