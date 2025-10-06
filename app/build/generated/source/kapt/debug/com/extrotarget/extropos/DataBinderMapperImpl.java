package com.extrotarget.extropos;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.extrotarget.extropos.databinding.ActivityAddUserBindingImpl;
import com.extrotarget.extropos.databinding.ActivityEmailAuthBindingImpl;
import com.extrotarget.extropos.databinding.ActivityPinLoginBindingImpl;
import com.extrotarget.extropos.databinding.FragmentLoginBindingImpl;
import com.extrotarget.extropos.databinding.FragmentMainBindingImpl;
import com.extrotarget.extropos.databinding.FragmentMenuBindingImpl;
import com.extrotarget.extropos.databinding.FragmentProductsGridBindingImpl;
import com.extrotarget.extropos.databinding.FragmentSettingsBindingImpl;
import com.extrotarget.extropos.databinding.FragmentSignupBindingImpl;
import com.extrotarget.extropos.databinding.ItemCartBindingImpl;
import com.extrotarget.extropos.databinding.ItemCategoryBindingImpl;
import com.extrotarget.extropos.databinding.ItemMenuItemBindingImpl;
import com.extrotarget.extropos.databinding.ItemProductBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYADDUSER = 1;

  private static final int LAYOUT_ACTIVITYEMAILAUTH = 2;

  private static final int LAYOUT_ACTIVITYPINLOGIN = 3;

  private static final int LAYOUT_FRAGMENTLOGIN = 4;

  private static final int LAYOUT_FRAGMENTMAIN = 5;

  private static final int LAYOUT_FRAGMENTMENU = 6;

  private static final int LAYOUT_FRAGMENTPRODUCTSGRID = 7;

  private static final int LAYOUT_FRAGMENTSETTINGS = 8;

  private static final int LAYOUT_FRAGMENTSIGNUP = 9;

  private static final int LAYOUT_ITEMCART = 10;

  private static final int LAYOUT_ITEMCATEGORY = 11;

  private static final int LAYOUT_ITEMMENUITEM = 12;

  private static final int LAYOUT_ITEMPRODUCT = 13;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(13);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.activity_add_user, LAYOUT_ACTIVITYADDUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.activity_email_auth, LAYOUT_ACTIVITYEMAILAUTH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.activity_pin_login, LAYOUT_ACTIVITYPINLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.fragment_login, LAYOUT_FRAGMENTLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.fragment_main, LAYOUT_FRAGMENTMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.fragment_menu, LAYOUT_FRAGMENTMENU);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.fragment_products_grid, LAYOUT_FRAGMENTPRODUCTSGRID);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.fragment_settings, LAYOUT_FRAGMENTSETTINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.fragment_signup, LAYOUT_FRAGMENTSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.item_cart, LAYOUT_ITEMCART);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.item_category, LAYOUT_ITEMCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.item_menu_item, LAYOUT_ITEMMENUITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.extrotarget.extropos.R.layout.item_product, LAYOUT_ITEMPRODUCT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYADDUSER: {
          if ("layout/activity_add_user_0".equals(tag)) {
            return new ActivityAddUserBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_add_user is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYEMAILAUTH: {
          if ("layout/activity_email_auth_0".equals(tag)) {
            return new ActivityEmailAuthBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_email_auth is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPINLOGIN: {
          if ("layout/activity_pin_login_0".equals(tag)) {
            return new ActivityPinLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_pin_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGIN: {
          if ("layout/fragment_login_0".equals(tag)) {
            return new FragmentLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMAIN: {
          if ("layout/fragment_main_0".equals(tag)) {
            return new FragmentMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMENU: {
          if ("layout/fragment_menu_0".equals(tag)) {
            return new FragmentMenuBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_menu is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPRODUCTSGRID: {
          if ("layout/fragment_products_grid_0".equals(tag)) {
            return new FragmentProductsGridBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_products_grid is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSETTINGS: {
          if ("layout/fragment_settings_0".equals(tag)) {
            return new FragmentSettingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_settings is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSIGNUP: {
          if ("layout/fragment_signup_0".equals(tag)) {
            return new FragmentSignupBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_signup is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCART: {
          if ("layout/item_cart_0".equals(tag)) {
            return new ItemCartBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_cart is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCATEGORY: {
          if ("layout/item_category_0".equals(tag)) {
            return new ItemCategoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_category is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMENUITEM: {
          if ("layout/item_menu_item_0".equals(tag)) {
            return new ItemMenuItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_menu_item is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPRODUCT: {
          if ("layout/item_product_0".equals(tag)) {
            return new ItemProductBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_product is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(13);

    static {
      sKeys.put("layout/activity_add_user_0", com.extrotarget.extropos.R.layout.activity_add_user);
      sKeys.put("layout/activity_email_auth_0", com.extrotarget.extropos.R.layout.activity_email_auth);
      sKeys.put("layout/activity_pin_login_0", com.extrotarget.extropos.R.layout.activity_pin_login);
      sKeys.put("layout/fragment_login_0", com.extrotarget.extropos.R.layout.fragment_login);
      sKeys.put("layout/fragment_main_0", com.extrotarget.extropos.R.layout.fragment_main);
      sKeys.put("layout/fragment_menu_0", com.extrotarget.extropos.R.layout.fragment_menu);
      sKeys.put("layout/fragment_products_grid_0", com.extrotarget.extropos.R.layout.fragment_products_grid);
      sKeys.put("layout/fragment_settings_0", com.extrotarget.extropos.R.layout.fragment_settings);
      sKeys.put("layout/fragment_signup_0", com.extrotarget.extropos.R.layout.fragment_signup);
      sKeys.put("layout/item_cart_0", com.extrotarget.extropos.R.layout.item_cart);
      sKeys.put("layout/item_category_0", com.extrotarget.extropos.R.layout.item_category);
      sKeys.put("layout/item_menu_item_0", com.extrotarget.extropos.R.layout.item_menu_item);
      sKeys.put("layout/item_product_0", com.extrotarget.extropos.R.layout.item_product);
    }
  }
}
