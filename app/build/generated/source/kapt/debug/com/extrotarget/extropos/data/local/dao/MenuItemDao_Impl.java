package com.extrotarget.extropos.data.local.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.extrotarget.extropos.data.local.entity.MenuItemEntity;
import java.lang.Class;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class MenuItemDao_Impl implements MenuItemDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<MenuItemEntity> __insertAdapterOfMenuItemEntity;

  public MenuItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfMenuItemEntity = new EntityInsertAdapter<MenuItemEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `menu_items` (`id`,`name`,`description`,`priceCents`,`categoryId`,`imageUrl`,`isAvailable`,`preparationTimeMinutes`,`allergens`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MenuItemEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getDescription());
        }
        statement.bindLong(4, entity.getPriceCents());
        if (entity.getCategoryId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getCategoryId());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getImageUrl());
        }
        final int _tmp = entity.isAvailable() ? 1 : 0;
        statement.bindLong(7, _tmp);
        if (entity.getPreparationTimeMinutes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getPreparationTimeMinutes());
        }
        if (entity.getAllergens() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getAllergens());
        }
      }
    };
  }

  @Override
  public Object upsert(final MenuItemEntity[] items, final Continuation<? super Unit> $completion) {
    if (items == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfMenuItemEntity.insert(_connection, items);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAllAvailable(final Continuation<? super List<MenuItemEntity>> $completion) {
    final String _sql = "SELECT * FROM menu_items WHERE isAvailable = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfPriceCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "priceCents");
        final int _columnIndexOfCategoryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoryId");
        final int _columnIndexOfImageUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrl");
        final int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
        final int _columnIndexOfPreparationTimeMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preparationTimeMinutes");
        final int _columnIndexOfAllergens = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "allergens");
        final List<MenuItemEntity> _result = new ArrayList<MenuItemEntity>();
        while (_stmt.step()) {
          final MenuItemEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpPriceCents;
          _tmpPriceCents = _stmt.getLong(_columnIndexOfPriceCents);
          final String _tmpCategoryId;
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null;
          } else {
            _tmpCategoryId = _stmt.getText(_columnIndexOfCategoryId);
          }
          final String _tmpImageUrl;
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null;
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl);
          }
          final boolean _tmpIsAvailable;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsAvailable));
          _tmpIsAvailable = _tmp != 0;
          final Integer _tmpPreparationTimeMinutes;
          if (_stmt.isNull(_columnIndexOfPreparationTimeMinutes)) {
            _tmpPreparationTimeMinutes = null;
          } else {
            _tmpPreparationTimeMinutes = (int) (_stmt.getLong(_columnIndexOfPreparationTimeMinutes));
          }
          final String _tmpAllergens;
          if (_stmt.isNull(_columnIndexOfAllergens)) {
            _tmpAllergens = null;
          } else {
            _tmpAllergens = _stmt.getText(_columnIndexOfAllergens);
          }
          _item = new MenuItemEntity(_tmpId,_tmpName,_tmpDescription,_tmpPriceCents,_tmpCategoryId,_tmpImageUrl,_tmpIsAvailable,_tmpPreparationTimeMinutes,_tmpAllergens);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getByCategory(final String categoryId,
      final Continuation<? super List<MenuItemEntity>> $completion) {
    final String _sql = "SELECT * FROM menu_items WHERE categoryId = ? AND isAvailable = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (categoryId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, categoryId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfPriceCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "priceCents");
        final int _columnIndexOfCategoryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoryId");
        final int _columnIndexOfImageUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrl");
        final int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
        final int _columnIndexOfPreparationTimeMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preparationTimeMinutes");
        final int _columnIndexOfAllergens = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "allergens");
        final List<MenuItemEntity> _result = new ArrayList<MenuItemEntity>();
        while (_stmt.step()) {
          final MenuItemEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpPriceCents;
          _tmpPriceCents = _stmt.getLong(_columnIndexOfPriceCents);
          final String _tmpCategoryId;
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null;
          } else {
            _tmpCategoryId = _stmt.getText(_columnIndexOfCategoryId);
          }
          final String _tmpImageUrl;
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null;
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl);
          }
          final boolean _tmpIsAvailable;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsAvailable));
          _tmpIsAvailable = _tmp != 0;
          final Integer _tmpPreparationTimeMinutes;
          if (_stmt.isNull(_columnIndexOfPreparationTimeMinutes)) {
            _tmpPreparationTimeMinutes = null;
          } else {
            _tmpPreparationTimeMinutes = (int) (_stmt.getLong(_columnIndexOfPreparationTimeMinutes));
          }
          final String _tmpAllergens;
          if (_stmt.isNull(_columnIndexOfAllergens)) {
            _tmpAllergens = null;
          } else {
            _tmpAllergens = _stmt.getText(_columnIndexOfAllergens);
          }
          _item = new MenuItemEntity(_tmpId,_tmpName,_tmpDescription,_tmpPriceCents,_tmpCategoryId,_tmpImageUrl,_tmpIsAvailable,_tmpPreparationTimeMinutes,_tmpAllergens);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getById(final String id, final Continuation<? super MenuItemEntity> $completion) {
    final String _sql = "SELECT * FROM menu_items WHERE id = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfPriceCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "priceCents");
        final int _columnIndexOfCategoryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoryId");
        final int _columnIndexOfImageUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrl");
        final int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
        final int _columnIndexOfPreparationTimeMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preparationTimeMinutes");
        final int _columnIndexOfAllergens = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "allergens");
        final MenuItemEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpPriceCents;
          _tmpPriceCents = _stmt.getLong(_columnIndexOfPriceCents);
          final String _tmpCategoryId;
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null;
          } else {
            _tmpCategoryId = _stmt.getText(_columnIndexOfCategoryId);
          }
          final String _tmpImageUrl;
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null;
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl);
          }
          final boolean _tmpIsAvailable;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsAvailable));
          _tmpIsAvailable = _tmp != 0;
          final Integer _tmpPreparationTimeMinutes;
          if (_stmt.isNull(_columnIndexOfPreparationTimeMinutes)) {
            _tmpPreparationTimeMinutes = null;
          } else {
            _tmpPreparationTimeMinutes = (int) (_stmt.getLong(_columnIndexOfPreparationTimeMinutes));
          }
          final String _tmpAllergens;
          if (_stmt.isNull(_columnIndexOfAllergens)) {
            _tmpAllergens = null;
          } else {
            _tmpAllergens = _stmt.getText(_columnIndexOfAllergens);
          }
          _result = new MenuItemEntity(_tmpId,_tmpName,_tmpDescription,_tmpPriceCents,_tmpCategoryId,_tmpImageUrl,_tmpIsAvailable,_tmpPreparationTimeMinutes,_tmpAllergens);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object searchByName(final String query,
      final Continuation<? super List<MenuItemEntity>> $completion) {
    final String _sql = "SELECT * FROM menu_items WHERE name LIKE '%' || ? || '%' AND isAvailable = 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (query == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, query);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "name");
        final int _columnIndexOfDescription = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "description");
        final int _columnIndexOfPriceCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "priceCents");
        final int _columnIndexOfCategoryId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoryId");
        final int _columnIndexOfImageUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUrl");
        final int _columnIndexOfIsAvailable = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "isAvailable");
        final int _columnIndexOfPreparationTimeMinutes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "preparationTimeMinutes");
        final int _columnIndexOfAllergens = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "allergens");
        final List<MenuItemEntity> _result = new ArrayList<MenuItemEntity>();
        while (_stmt.step()) {
          final MenuItemEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpName;
          if (_stmt.isNull(_columnIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _stmt.getText(_columnIndexOfName);
          }
          final String _tmpDescription;
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null;
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription);
          }
          final long _tmpPriceCents;
          _tmpPriceCents = _stmt.getLong(_columnIndexOfPriceCents);
          final String _tmpCategoryId;
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null;
          } else {
            _tmpCategoryId = _stmt.getText(_columnIndexOfCategoryId);
          }
          final String _tmpImageUrl;
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null;
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl);
          }
          final boolean _tmpIsAvailable;
          final int _tmp;
          _tmp = (int) (_stmt.getLong(_columnIndexOfIsAvailable));
          _tmpIsAvailable = _tmp != 0;
          final Integer _tmpPreparationTimeMinutes;
          if (_stmt.isNull(_columnIndexOfPreparationTimeMinutes)) {
            _tmpPreparationTimeMinutes = null;
          } else {
            _tmpPreparationTimeMinutes = (int) (_stmt.getLong(_columnIndexOfPreparationTimeMinutes));
          }
          final String _tmpAllergens;
          if (_stmt.isNull(_columnIndexOfAllergens)) {
            _tmpAllergens = null;
          } else {
            _tmpAllergens = _stmt.getText(_columnIndexOfAllergens);
          }
          _item = new MenuItemEntity(_tmpId,_tmpName,_tmpDescription,_tmpPriceCents,_tmpCategoryId,_tmpImageUrl,_tmpIsAvailable,_tmpPreparationTimeMinutes,_tmpAllergens);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object updateAvailability(final String id, final boolean isAvailable,
      final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE menu_items SET isAvailable = ? WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        final int _tmp = isAvailable ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, id);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
