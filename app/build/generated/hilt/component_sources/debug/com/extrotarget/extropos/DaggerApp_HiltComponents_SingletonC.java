package com.extrotarget.extropos;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.extrotarget.extropos.auth.EmailAuthActivity;
import com.extrotarget.extropos.auth.EmailAuthViewModel;
import com.extrotarget.extropos.auth.EmailAuthViewModel_HiltModules;
import com.extrotarget.extropos.data.local.AppDatabase;
import com.extrotarget.extropos.data.local.auth.LocalAuthManager;
import com.extrotarget.extropos.data.local.dao.CategoryDao;
import com.extrotarget.extropos.data.local.dao.MenuItemDao;
import com.extrotarget.extropos.data.local.dao.OrderDao;
import com.extrotarget.extropos.data.local.dao.OrderItemDao;
import com.extrotarget.extropos.data.local.dao.ProductDao;
import com.extrotarget.extropos.data.local.dao.TableDao;
import com.extrotarget.extropos.data.local.dao.TicketDao;
import com.extrotarget.extropos.data.remote.AppwriteService;
import com.extrotarget.extropos.data.repository.AuthRepository;
import com.extrotarget.extropos.data.repository.MenuRepository;
import com.extrotarget.extropos.data.repository.OrderRepository;
import com.extrotarget.extropos.data.repository.ProductRepositoryImpl;
import com.extrotarget.extropos.data.repository.TableRepository;
import com.extrotarget.extropos.data.repository.TicketRepository;
import com.extrotarget.extropos.di.AppModule_ProvideAppDatabaseFactory;
import com.extrotarget.extropos.di.AppModule_ProvideCategoryDaoFactory;
import com.extrotarget.extropos.di.AppModule_ProvideMenuItemDaoFactory;
import com.extrotarget.extropos.di.AppModule_ProvideMoshiFactory;
import com.extrotarget.extropos.di.AppModule_ProvideOrderDaoFactory;
import com.extrotarget.extropos.di.AppModule_ProvideOrderItemDaoFactory;
import com.extrotarget.extropos.di.AppModule_ProvideProductDaoFactory;
import com.extrotarget.extropos.di.AppModule_ProvideTableDaoFactory;
import com.extrotarget.extropos.di.AppModule_ProvideTicketDaoFactory;
import com.extrotarget.extropos.domain.usecase.AddCategoryUseCase;
import com.extrotarget.extropos.domain.usecase.AddItemToOrderUseCase;
import com.extrotarget.extropos.domain.usecase.CheckAppActivationUseCase;
import com.extrotarget.extropos.domain.usecase.CheckEmailVerificationUseCase;
import com.extrotarget.extropos.domain.usecase.CreateOrderUseCase;
import com.extrotarget.extropos.domain.usecase.GetActiveOrdersUseCase;
import com.extrotarget.extropos.domain.usecase.GetAvailableTablesUseCase;
import com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase;
import com.extrotarget.extropos.domain.usecase.GetMenuItemsUseCase;
import com.extrotarget.extropos.domain.usecase.GetOccupiedTablesUseCase;
import com.extrotarget.extropos.domain.usecase.GetOrderUseCase;
import com.extrotarget.extropos.domain.usecase.GetTableUseCase;
import com.extrotarget.extropos.domain.usecase.GetTablesUseCase;
import com.extrotarget.extropos.domain.usecase.IsUserLoggedInUseCase;
import com.extrotarget.extropos.domain.usecase.LoginUseCase;
import com.extrotarget.extropos.domain.usecase.LogoutUseCase;
import com.extrotarget.extropos.domain.usecase.RemoveOrderItemUseCase;
import com.extrotarget.extropos.domain.usecase.ResendVerificationEmailUseCase;
import com.extrotarget.extropos.domain.usecase.SearchMenuItemsUseCase;
import com.extrotarget.extropos.domain.usecase.SignupUseCase;
import com.extrotarget.extropos.domain.usecase.UpdateOrderItemUseCase;
import com.extrotarget.extropos.domain.usecase.UpdateOrderStatusUseCase;
import com.extrotarget.extropos.domain.usecase.UpdateTableStatusUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.AddItemToTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.ClearTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.CompleteTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.CreateTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.GetCurrentTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.RemoveItemFromTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.SuspendTicketUseCase;
import com.extrotarget.extropos.domain.usecase.ticket.UpdateItemQuantityUseCase;
import com.extrotarget.extropos.pdf.PdfGenerationService;
import com.extrotarget.extropos.pdf.test.PdfTestActivity;
import com.extrotarget.extropos.pdf.test.PdfTestActivity_MembersInjector;
import com.extrotarget.extropos.printer.data.IPrinterRepository;
import com.extrotarget.extropos.printer.data.PrinterRepository;
import com.extrotarget.extropos.printer.domain.usecase.PrintReceiptUseCase;
import com.extrotarget.extropos.printer.ui.PrinterViewModel;
import com.extrotarget.extropos.printer.ui.PrinterViewModel_HiltModules;
import com.extrotarget.extropos.ui.auth.AppLockFragment;
import com.extrotarget.extropos.ui.auth.AuthViewModel;
import com.extrotarget.extropos.ui.auth.AuthViewModel_HiltModules;
import com.extrotarget.extropos.ui.auth.LoginFragment;
import com.extrotarget.extropos.ui.auth.SignupFragment;
import com.extrotarget.extropos.ui.cart.CartFragment;
import com.extrotarget.extropos.ui.cart.CartViewModel;
import com.extrotarget.extropos.ui.cart.CartViewModel_HiltModules;
import com.extrotarget.extropos.ui.cart.TicketViewModel;
import com.extrotarget.extropos.ui.cart.TicketViewModel_HiltModules;
import com.extrotarget.extropos.ui.dashboard.DashboardActivity;
import com.extrotarget.extropos.ui.debug.DebugCategoryActivity;
import com.extrotarget.extropos.ui.debug.DebugCategoryActivity_MembersInjector;
import com.extrotarget.extropos.ui.debug.DebugProductActivity;
import com.extrotarget.extropos.ui.debug.DebugProductActivity_MembersInjector;
import com.extrotarget.extropos.ui.login.LoginViewModel;
import com.extrotarget.extropos.ui.login.LoginViewModel_HiltModules;
import com.extrotarget.extropos.ui.main.MainFragment;
import com.extrotarget.extropos.ui.menu.MenuFragment;
import com.extrotarget.extropos.ui.menu.MenuViewModel;
import com.extrotarget.extropos.ui.menu.MenuViewModel_HiltModules;
import com.extrotarget.extropos.ui.order.OrderViewModel;
import com.extrotarget.extropos.ui.order.OrderViewModel_HiltModules;
import com.extrotarget.extropos.ui.pin.PinLoginActivity;
import com.extrotarget.extropos.ui.pin.PinLoginViewModel;
import com.extrotarget.extropos.ui.pin.PinLoginViewModel_HiltModules;
import com.extrotarget.extropos.ui.pos.PosFragment;
import com.extrotarget.extropos.ui.product.ProductDetailFragment;
import com.extrotarget.extropos.ui.product.ProductViewModel;
import com.extrotarget.extropos.ui.product.ProductViewModel_HiltModules;
import com.extrotarget.extropos.ui.product.ProductsGridFragment;
import com.extrotarget.extropos.ui.settings.SettingsFragment;
import com.extrotarget.extropos.ui.settings.employee.EmployeeManagementFragment;
import com.extrotarget.extropos.ui.settings.printer.PrinterSetupFragment;
import com.extrotarget.extropos.ui.table.TableViewModel;
import com.extrotarget.extropos.ui.table.TableViewModel_HiltModules;
import com.extrotarget.extropos.ui.user.AddUserActivity;
import com.extrotarget.extropos.ui.user.AddUserViewModel;
import com.extrotarget.extropos.ui.user.AddUserViewModel_HiltModules;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.squareup.moshi.Moshi;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerApp_HiltComponents_SingletonC {
  private DaggerApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public App_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements App_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public App_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements App_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public App_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements App_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public App_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements App_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public App_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements App_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public App_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements App_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public App_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements App_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public App_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends App_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends App_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectAppLockFragment(AppLockFragment appLockFragment) {
    }

    @Override
    public void injectLoginFragment(LoginFragment loginFragment) {
    }

    @Override
    public void injectLoginFragment(com.extrotarget.extropos.ui.login.LoginFragment loginFragment) {
    }

    @Override
    public void injectSignupFragment(SignupFragment signupFragment) {
    }

    @Override
    public void injectCartFragment(CartFragment cartFragment) {
    }

    @Override
    public void injectMainFragment(MainFragment mainFragment) {
    }

    @Override
    public void injectMenuFragment(MenuFragment menuFragment) {
    }

    @Override
    public void injectPosFragment(PosFragment posFragment) {
    }

    @Override
    public void injectProductDetailFragment(ProductDetailFragment productDetailFragment) {
    }

    @Override
    public void injectProductsGridFragment(ProductsGridFragment productsGridFragment) {
    }

    @Override
    public void injectSettingsFragment(SettingsFragment settingsFragment) {
    }

    @Override
    public void injectEmployeeManagementFragment(
        EmployeeManagementFragment employeeManagementFragment) {
    }

    @Override
    public void injectPrinterSetupFragment(PrinterSetupFragment printerSetupFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends App_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends App_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public void injectEmailAuthActivity(EmailAuthActivity emailAuthActivity) {
    }

    @Override
    public void injectPdfTestActivity(PdfTestActivity pdfTestActivity) {
      injectPdfTestActivity2(pdfTestActivity);
    }

    @Override
    public void injectDashboardActivity(DashboardActivity dashboardActivity) {
    }

    @Override
    public void injectDebugCategoryActivity(DebugCategoryActivity debugCategoryActivity) {
      injectDebugCategoryActivity2(debugCategoryActivity);
    }

    @Override
    public void injectDebugProductActivity(DebugProductActivity debugProductActivity) {
      injectDebugProductActivity2(debugProductActivity);
    }

    @Override
    public void injectPinLoginActivity(PinLoginActivity pinLoginActivity) {
    }

    @Override
    public void injectAddUserActivity(AddUserActivity addUserActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(12).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_user_AddUserViewModel, AddUserViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_auth_AuthViewModel, AuthViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_cart_CartViewModel, CartViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_auth_EmailAuthViewModel, EmailAuthViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_login_LoginViewModel, LoginViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_menu_MenuViewModel, MenuViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_order_OrderViewModel, OrderViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_pin_PinLoginViewModel, PinLoginViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_printer_ui_PrinterViewModel, PrinterViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_product_ProductViewModel, ProductViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_table_TableViewModel, TableViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_cart_TicketViewModel, TicketViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private PdfTestActivity injectPdfTestActivity2(PdfTestActivity instance) {
      PdfTestActivity_MembersInjector.injectPdfService(instance, singletonCImpl.pdfGenerationServiceProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private DebugCategoryActivity injectDebugCategoryActivity2(DebugCategoryActivity instance2) {
      DebugCategoryActivity_MembersInjector.injectMenuRepository(instance2, singletonCImpl.menuRepositoryProvider.get());
      return instance2;
    }

    @CanIgnoreReturnValue
    private DebugProductActivity injectDebugProductActivity2(DebugProductActivity instance3) {
      DebugProductActivity_MembersInjector.injectProductRepository(instance3, singletonCImpl.productRepositoryImplProvider.get());
      return instance3;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_extrotarget_extropos_ui_table_TableViewModel = "com.extrotarget.extropos.ui.table.TableViewModel";

      static String com_extrotarget_extropos_ui_pin_PinLoginViewModel = "com.extrotarget.extropos.ui.pin.PinLoginViewModel";

      static String com_extrotarget_extropos_ui_login_LoginViewModel = "com.extrotarget.extropos.ui.login.LoginViewModel";

      static String com_extrotarget_extropos_ui_product_ProductViewModel = "com.extrotarget.extropos.ui.product.ProductViewModel";

      static String com_extrotarget_extropos_printer_ui_PrinterViewModel = "com.extrotarget.extropos.printer.ui.PrinterViewModel";

      static String com_extrotarget_extropos_ui_cart_TicketViewModel = "com.extrotarget.extropos.ui.cart.TicketViewModel";

      static String com_extrotarget_extropos_ui_order_OrderViewModel = "com.extrotarget.extropos.ui.order.OrderViewModel";

      static String com_extrotarget_extropos_ui_auth_AuthViewModel = "com.extrotarget.extropos.ui.auth.AuthViewModel";

      static String com_extrotarget_extropos_ui_menu_MenuViewModel = "com.extrotarget.extropos.ui.menu.MenuViewModel";

      static String com_extrotarget_extropos_auth_EmailAuthViewModel = "com.extrotarget.extropos.auth.EmailAuthViewModel";

      static String com_extrotarget_extropos_ui_cart_CartViewModel = "com.extrotarget.extropos.ui.cart.CartViewModel";

      static String com_extrotarget_extropos_ui_user_AddUserViewModel = "com.extrotarget.extropos.ui.user.AddUserViewModel";

      @KeepFieldType
      TableViewModel com_extrotarget_extropos_ui_table_TableViewModel2;

      @KeepFieldType
      PinLoginViewModel com_extrotarget_extropos_ui_pin_PinLoginViewModel2;

      @KeepFieldType
      LoginViewModel com_extrotarget_extropos_ui_login_LoginViewModel2;

      @KeepFieldType
      ProductViewModel com_extrotarget_extropos_ui_product_ProductViewModel2;

      @KeepFieldType
      PrinterViewModel com_extrotarget_extropos_printer_ui_PrinterViewModel2;

      @KeepFieldType
      TicketViewModel com_extrotarget_extropos_ui_cart_TicketViewModel2;

      @KeepFieldType
      OrderViewModel com_extrotarget_extropos_ui_order_OrderViewModel2;

      @KeepFieldType
      AuthViewModel com_extrotarget_extropos_ui_auth_AuthViewModel2;

      @KeepFieldType
      MenuViewModel com_extrotarget_extropos_ui_menu_MenuViewModel2;

      @KeepFieldType
      EmailAuthViewModel com_extrotarget_extropos_auth_EmailAuthViewModel2;

      @KeepFieldType
      CartViewModel com_extrotarget_extropos_ui_cart_CartViewModel2;

      @KeepFieldType
      AddUserViewModel com_extrotarget_extropos_ui_user_AddUserViewModel2;
    }
  }

  private static final class ViewModelCImpl extends App_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddUserViewModel> addUserViewModelProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<CartViewModel> cartViewModelProvider;

    private Provider<EmailAuthViewModel> emailAuthViewModelProvider;

    private Provider<LoginViewModel> loginViewModelProvider;

    private Provider<MenuViewModel> menuViewModelProvider;

    private Provider<OrderViewModel> orderViewModelProvider;

    private Provider<PinLoginViewModel> pinLoginViewModelProvider;

    private Provider<PrinterViewModel> printerViewModelProvider;

    private Provider<ProductViewModel> productViewModelProvider;

    private Provider<TableViewModel> tableViewModelProvider;

    private Provider<TicketViewModel> ticketViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private LoginUseCase loginUseCase() {
      return new LoginUseCase(singletonCImpl.authRepositoryProvider.get());
    }

    private SignupUseCase signupUseCase() {
      return new SignupUseCase(singletonCImpl.authRepositoryProvider.get());
    }

    private LogoutUseCase logoutUseCase() {
      return new LogoutUseCase(singletonCImpl.authRepositoryProvider.get());
    }

    private CheckAppActivationUseCase checkAppActivationUseCase() {
      return new CheckAppActivationUseCase(singletonCImpl.authRepositoryProvider.get());
    }

    private ResendVerificationEmailUseCase resendVerificationEmailUseCase() {
      return new ResendVerificationEmailUseCase(singletonCImpl.authRepositoryProvider.get());
    }

    private GetCurrentTicketUseCase getCurrentTicketUseCase() {
      return new GetCurrentTicketUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private CreateTicketUseCase createTicketUseCase() {
      return new CreateTicketUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private AddItemToTicketUseCase addItemToTicketUseCase() {
      return new AddItemToTicketUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private UpdateItemQuantityUseCase updateItemQuantityUseCase() {
      return new UpdateItemQuantityUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private RemoveItemFromTicketUseCase removeItemFromTicketUseCase() {
      return new RemoveItemFromTicketUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private ClearTicketUseCase clearTicketUseCase() {
      return new ClearTicketUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private SuspendTicketUseCase suspendTicketUseCase() {
      return new SuspendTicketUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private CompleteTicketUseCase completeTicketUseCase() {
      return new CompleteTicketUseCase(singletonCImpl.ticketRepositoryProvider.get());
    }

    private IsUserLoggedInUseCase isUserLoggedInUseCase() {
      return new IsUserLoggedInUseCase(singletonCImpl.authRepositoryProvider.get());
    }

    private CheckEmailVerificationUseCase checkEmailVerificationUseCase() {
      return new CheckEmailVerificationUseCase(singletonCImpl.authRepositoryProvider.get());
    }

    private GetCategoriesUseCase getCategoriesUseCase() {
      return new GetCategoriesUseCase(singletonCImpl.menuRepositoryProvider.get());
    }

    private GetMenuItemsUseCase getMenuItemsUseCase() {
      return new GetMenuItemsUseCase(singletonCImpl.menuRepositoryProvider.get());
    }

    private SearchMenuItemsUseCase searchMenuItemsUseCase() {
      return new SearchMenuItemsUseCase(singletonCImpl.menuRepositoryProvider.get());
    }

    private CreateOrderUseCase createOrderUseCase() {
      return new CreateOrderUseCase(singletonCImpl.orderRepositoryProvider.get());
    }

    private GetOrderUseCase getOrderUseCase() {
      return new GetOrderUseCase(singletonCImpl.orderRepositoryProvider.get());
    }

    private UpdateOrderStatusUseCase updateOrderStatusUseCase() {
      return new UpdateOrderStatusUseCase(singletonCImpl.orderRepositoryProvider.get());
    }

    private AddItemToOrderUseCase addItemToOrderUseCase() {
      return new AddItemToOrderUseCase(singletonCImpl.orderRepositoryProvider.get());
    }

    private UpdateOrderItemUseCase updateOrderItemUseCase() {
      return new UpdateOrderItemUseCase(singletonCImpl.orderRepositoryProvider.get());
    }

    private RemoveOrderItemUseCase removeOrderItemUseCase() {
      return new RemoveOrderItemUseCase(singletonCImpl.orderRepositoryProvider.get());
    }

    private GetActiveOrdersUseCase getActiveOrdersUseCase() {
      return new GetActiveOrdersUseCase(singletonCImpl.orderRepositoryProvider.get());
    }

    private PrintReceiptUseCase printReceiptUseCase() {
      return new PrintReceiptUseCase(singletonCImpl.bindPrinterRepositoryProvider.get());
    }

    private AddCategoryUseCase addCategoryUseCase() {
      return new AddCategoryUseCase(singletonCImpl.menuRepositoryProvider.get());
    }

    private GetTablesUseCase getTablesUseCase() {
      return new GetTablesUseCase(singletonCImpl.tableRepositoryProvider.get());
    }

    private GetTableUseCase getTableUseCase() {
      return new GetTableUseCase(singletonCImpl.tableRepositoryProvider.get());
    }

    private GetAvailableTablesUseCase getAvailableTablesUseCase() {
      return new GetAvailableTablesUseCase(singletonCImpl.tableRepositoryProvider.get());
    }

    private GetOccupiedTablesUseCase getOccupiedTablesUseCase() {
      return new GetOccupiedTablesUseCase(singletonCImpl.tableRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addUserViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.cartViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.emailAuthViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.menuViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.orderViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.pinLoginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.printerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.productViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.tableViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.ticketViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(12).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_user_AddUserViewModel, ((Provider) addUserViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_auth_AuthViewModel, ((Provider) authViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_cart_CartViewModel, ((Provider) cartViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_auth_EmailAuthViewModel, ((Provider) emailAuthViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_login_LoginViewModel, ((Provider) loginViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_menu_MenuViewModel, ((Provider) menuViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_order_OrderViewModel, ((Provider) orderViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_pin_PinLoginViewModel, ((Provider) pinLoginViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_printer_ui_PrinterViewModel, ((Provider) printerViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_product_ProductViewModel, ((Provider) productViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_table_TableViewModel, ((Provider) tableViewModelProvider)).put(LazyClassKeyProvider.com_extrotarget_extropos_ui_cart_TicketViewModel, ((Provider) ticketViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_extrotarget_extropos_ui_user_AddUserViewModel = "com.extrotarget.extropos.ui.user.AddUserViewModel";

      static String com_extrotarget_extropos_ui_table_TableViewModel = "com.extrotarget.extropos.ui.table.TableViewModel";

      static String com_extrotarget_extropos_ui_cart_TicketViewModel = "com.extrotarget.extropos.ui.cart.TicketViewModel";

      static String com_extrotarget_extropos_ui_cart_CartViewModel = "com.extrotarget.extropos.ui.cart.CartViewModel";

      static String com_extrotarget_extropos_ui_order_OrderViewModel = "com.extrotarget.extropos.ui.order.OrderViewModel";

      static String com_extrotarget_extropos_ui_pin_PinLoginViewModel = "com.extrotarget.extropos.ui.pin.PinLoginViewModel";

      static String com_extrotarget_extropos_auth_EmailAuthViewModel = "com.extrotarget.extropos.auth.EmailAuthViewModel";

      static String com_extrotarget_extropos_ui_login_LoginViewModel = "com.extrotarget.extropos.ui.login.LoginViewModel";

      static String com_extrotarget_extropos_ui_auth_AuthViewModel = "com.extrotarget.extropos.ui.auth.AuthViewModel";

      static String com_extrotarget_extropos_ui_menu_MenuViewModel = "com.extrotarget.extropos.ui.menu.MenuViewModel";

      static String com_extrotarget_extropos_printer_ui_PrinterViewModel = "com.extrotarget.extropos.printer.ui.PrinterViewModel";

      static String com_extrotarget_extropos_ui_product_ProductViewModel = "com.extrotarget.extropos.ui.product.ProductViewModel";

      @KeepFieldType
      AddUserViewModel com_extrotarget_extropos_ui_user_AddUserViewModel2;

      @KeepFieldType
      TableViewModel com_extrotarget_extropos_ui_table_TableViewModel2;

      @KeepFieldType
      TicketViewModel com_extrotarget_extropos_ui_cart_TicketViewModel2;

      @KeepFieldType
      CartViewModel com_extrotarget_extropos_ui_cart_CartViewModel2;

      @KeepFieldType
      OrderViewModel com_extrotarget_extropos_ui_order_OrderViewModel2;

      @KeepFieldType
      PinLoginViewModel com_extrotarget_extropos_ui_pin_PinLoginViewModel2;

      @KeepFieldType
      EmailAuthViewModel com_extrotarget_extropos_auth_EmailAuthViewModel2;

      @KeepFieldType
      LoginViewModel com_extrotarget_extropos_ui_login_LoginViewModel2;

      @KeepFieldType
      AuthViewModel com_extrotarget_extropos_ui_auth_AuthViewModel2;

      @KeepFieldType
      MenuViewModel com_extrotarget_extropos_ui_menu_MenuViewModel2;

      @KeepFieldType
      PrinterViewModel com_extrotarget_extropos_printer_ui_PrinterViewModel2;

      @KeepFieldType
      ProductViewModel com_extrotarget_extropos_ui_product_ProductViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.extrotarget.extropos.ui.user.AddUserViewModel 
          return (T) new AddUserViewModel(singletonCImpl.localAuthManagerProvider.get());

          case 1: // com.extrotarget.extropos.ui.auth.AuthViewModel 
          return (T) new AuthViewModel(viewModelCImpl.loginUseCase(), viewModelCImpl.signupUseCase(), viewModelCImpl.logoutUseCase(), viewModelCImpl.checkAppActivationUseCase(), viewModelCImpl.resendVerificationEmailUseCase());

          case 2: // com.extrotarget.extropos.ui.cart.CartViewModel 
          return (T) new CartViewModel(viewModelCImpl.getCurrentTicketUseCase(), viewModelCImpl.createTicketUseCase(), viewModelCImpl.addItemToTicketUseCase(), viewModelCImpl.updateItemQuantityUseCase(), viewModelCImpl.removeItemFromTicketUseCase(), viewModelCImpl.clearTicketUseCase(), viewModelCImpl.suspendTicketUseCase(), viewModelCImpl.completeTicketUseCase());

          case 3: // com.extrotarget.extropos.auth.EmailAuthViewModel 
          return (T) new EmailAuthViewModel(viewModelCImpl.loginUseCase(), viewModelCImpl.isUserLoggedInUseCase(), viewModelCImpl.checkEmailVerificationUseCase());

          case 4: // com.extrotarget.extropos.ui.login.LoginViewModel 
          return (T) new LoginViewModel(viewModelCImpl.loginUseCase());

          case 5: // com.extrotarget.extropos.ui.menu.MenuViewModel 
          return (T) new MenuViewModel(viewModelCImpl.getCategoriesUseCase(), viewModelCImpl.getMenuItemsUseCase(), viewModelCImpl.searchMenuItemsUseCase());

          case 6: // com.extrotarget.extropos.ui.order.OrderViewModel 
          return (T) new OrderViewModel(viewModelCImpl.createOrderUseCase(), viewModelCImpl.getOrderUseCase(), viewModelCImpl.updateOrderStatusUseCase(), viewModelCImpl.addItemToOrderUseCase(), viewModelCImpl.updateOrderItemUseCase(), viewModelCImpl.removeOrderItemUseCase(), viewModelCImpl.getActiveOrdersUseCase());

          case 7: // com.extrotarget.extropos.ui.pin.PinLoginViewModel 
          return (T) new PinLoginViewModel(singletonCImpl.localAuthManagerProvider.get());

          case 8: // com.extrotarget.extropos.printer.ui.PrinterViewModel 
          return (T) new PrinterViewModel(viewModelCImpl.printReceiptUseCase());

          case 9: // com.extrotarget.extropos.ui.product.ProductViewModel 
          return (T) new ProductViewModel(singletonCImpl.productRepositoryImplProvider.get(), viewModelCImpl.addCategoryUseCase());

          case 10: // com.extrotarget.extropos.ui.table.TableViewModel 
          return (T) new TableViewModel(viewModelCImpl.getTablesUseCase(), viewModelCImpl.getTableUseCase(), new UpdateTableStatusUseCase(), viewModelCImpl.getAvailableTablesUseCase(), viewModelCImpl.getOccupiedTablesUseCase());

          case 11: // com.extrotarget.extropos.ui.cart.TicketViewModel 
          return (T) new TicketViewModel(viewModelCImpl.getCurrentTicketUseCase(), viewModelCImpl.createTicketUseCase(), viewModelCImpl.addItemToTicketUseCase(), viewModelCImpl.updateItemQuantityUseCase(), viewModelCImpl.removeItemFromTicketUseCase(), viewModelCImpl.clearTicketUseCase(), viewModelCImpl.suspendTicketUseCase(), viewModelCImpl.completeTicketUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends App_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends App_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends App_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<PdfGenerationService> pdfGenerationServiceProvider;

    private Provider<AppDatabase> provideAppDatabaseProvider;

    private Provider<CategoryDao> provideCategoryDaoProvider;

    private Provider<MenuItemDao> provideMenuItemDaoProvider;

    private Provider<Moshi> provideMoshiProvider;

    private Provider<MenuRepository> menuRepositoryProvider;

    private Provider<ProductDao> provideProductDaoProvider;

    private Provider<ProductRepositoryImpl> productRepositoryImplProvider;

    private Provider<LocalAuthManager> localAuthManagerProvider;

    private Provider<AppwriteService> appwriteServiceProvider;

    private Provider<AuthRepository> authRepositoryProvider;

    private Provider<TicketDao> provideTicketDaoProvider;

    private Provider<TicketRepository> ticketRepositoryProvider;

    private Provider<OrderDao> provideOrderDaoProvider;

    private Provider<OrderItemDao> provideOrderItemDaoProvider;

    private Provider<OrderRepository> orderRepositoryProvider;

    private Provider<PrinterRepository> printerRepositoryProvider;

    private Provider<IPrinterRepository> bindPrinterRepositoryProvider;

    private Provider<TableDao> provideTableDaoProvider;

    private Provider<TableRepository> tableRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.pdfGenerationServiceProvider = DoubleCheck.provider(new SwitchingProvider<PdfGenerationService>(singletonCImpl, 0));
      this.provideAppDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 3));
      this.provideCategoryDaoProvider = DoubleCheck.provider(new SwitchingProvider<CategoryDao>(singletonCImpl, 2));
      this.provideMenuItemDaoProvider = DoubleCheck.provider(new SwitchingProvider<MenuItemDao>(singletonCImpl, 4));
      this.provideMoshiProvider = DoubleCheck.provider(new SwitchingProvider<Moshi>(singletonCImpl, 5));
      this.menuRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<MenuRepository>(singletonCImpl, 1));
      this.provideProductDaoProvider = DoubleCheck.provider(new SwitchingProvider<ProductDao>(singletonCImpl, 7));
      this.productRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ProductRepositoryImpl>(singletonCImpl, 6));
      this.localAuthManagerProvider = DoubleCheck.provider(new SwitchingProvider<LocalAuthManager>(singletonCImpl, 8));
      this.appwriteServiceProvider = DoubleCheck.provider(new SwitchingProvider<AppwriteService>(singletonCImpl, 10));
      this.authRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepository>(singletonCImpl, 9));
      this.provideTicketDaoProvider = DoubleCheck.provider(new SwitchingProvider<TicketDao>(singletonCImpl, 12));
      this.ticketRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TicketRepository>(singletonCImpl, 11));
      this.provideOrderDaoProvider = DoubleCheck.provider(new SwitchingProvider<OrderDao>(singletonCImpl, 14));
      this.provideOrderItemDaoProvider = DoubleCheck.provider(new SwitchingProvider<OrderItemDao>(singletonCImpl, 15));
      this.orderRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<OrderRepository>(singletonCImpl, 13));
      this.printerRepositoryProvider = new SwitchingProvider<>(singletonCImpl, 16);
      this.bindPrinterRepositoryProvider = DoubleCheck.provider((Provider) printerRepositoryProvider);
      this.provideTableDaoProvider = DoubleCheck.provider(new SwitchingProvider<TableDao>(singletonCImpl, 18));
      this.tableRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TableRepository>(singletonCImpl, 17));
    }

    @Override
    public void injectApp(App app) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.extrotarget.extropos.pdf.PdfGenerationService 
          return (T) new PdfGenerationService(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 1: // com.extrotarget.extropos.data.repository.MenuRepository 
          return (T) new MenuRepository(singletonCImpl.provideCategoryDaoProvider.get(), singletonCImpl.provideMenuItemDaoProvider.get(), singletonCImpl.provideMoshiProvider.get());

          case 2: // com.extrotarget.extropos.data.local.dao.CategoryDao 
          return (T) AppModule_ProvideCategoryDaoFactory.provideCategoryDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 3: // com.extrotarget.extropos.data.local.AppDatabase 
          return (T) AppModule_ProvideAppDatabaseFactory.provideAppDatabase(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 4: // com.extrotarget.extropos.data.local.dao.MenuItemDao 
          return (T) AppModule_ProvideMenuItemDaoFactory.provideMenuItemDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 5: // com.squareup.moshi.Moshi 
          return (T) AppModule_ProvideMoshiFactory.provideMoshi();

          case 6: // com.extrotarget.extropos.data.repository.ProductRepositoryImpl 
          return (T) new ProductRepositoryImpl(singletonCImpl.provideProductDaoProvider.get());

          case 7: // com.extrotarget.extropos.data.local.dao.ProductDao 
          return (T) AppModule_ProvideProductDaoFactory.provideProductDao(singletonCImpl.provideAppDatabaseProvider.get());

          case 8: // com.extrotarget.extropos.data.local.auth.LocalAuthManager 
          return (T) new LocalAuthManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // com.extrotarget.extropos.data.repository.AuthRepository 
          return (T) new AuthRepository(singletonCImpl.appwriteServiceProvider.get());

          case 10: // com.extrotarget.extropos.data.remote.AppwriteService 
          return (T) new AppwriteService(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 11: // com.extrotarget.extropos.data.repository.TicketRepository 
          return (T) new TicketRepository(singletonCImpl.provideTicketDaoProvider.get());

          case 12: // com.extrotarget.extropos.data.local.dao.TicketDao 
          return (T) AppModule_ProvideTicketDaoFactory.provideTicketDao();

          case 13: // com.extrotarget.extropos.data.repository.OrderRepository 
          return (T) new OrderRepository(singletonCImpl.provideOrderDaoProvider.get(), singletonCImpl.provideOrderItemDaoProvider.get());

          case 14: // com.extrotarget.extropos.data.local.dao.OrderDao 
          return (T) AppModule_ProvideOrderDaoFactory.provideOrderDao();

          case 15: // com.extrotarget.extropos.data.local.dao.OrderItemDao 
          return (T) AppModule_ProvideOrderItemDaoFactory.provideOrderItemDao();

          case 16: // com.extrotarget.extropos.printer.data.PrinterRepository 
          return (T) new PrinterRepository();

          case 17: // com.extrotarget.extropos.data.repository.TableRepository 
          return (T) new TableRepository(singletonCImpl.provideTableDaoProvider.get());

          case 18: // com.extrotarget.extropos.data.local.dao.TableDao 
          return (T) AppModule_ProvideTableDaoFactory.provideTableDao();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
