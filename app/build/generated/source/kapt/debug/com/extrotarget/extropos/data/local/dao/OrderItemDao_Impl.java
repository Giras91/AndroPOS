package com.extrotarget.extropos.data.local.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.extrotarget.extropos.data.local.entity.OrderItemEntity;
import java.lang.Class;
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
public final class OrderItemDao_Impl implements OrderItemDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OrderItemEntity> __insertAdapterOfOrderItemEntity;

  private final EntityDeleteOrUpdateAdapter<OrderItemEntity> __deleteAdapterOfOrderItemEntity;

  private final EntityDeleteOrUpdateAdapter<OrderItemEntity> __updateAdapterOfOrderItemEntity;

  public OrderItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOrderItemEntity = new EntityInsertAdapter<OrderItemEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `order_items` (`id`,`orderId`,`menuItemId`,`menuItemName`,`quantity`,`unitPriceCents`,`totalPriceCents`,`notes`,`status`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OrderItemEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getOrderId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getOrderId());
        }
        if (entity.getMenuItemId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getMenuItemId());
        }
        if (entity.getMenuItemName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getMenuItemName());
        }
        statement.bindLong(5, entity.getQuantity());
        statement.bindLong(6, entity.getUnitPriceCents());
        statement.bindLong(7, entity.getTotalPriceCents());
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getNotes());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getStatus());
        }
      }
    };
    this.__deleteAdapterOfOrderItemEntity = new EntityDeleteOrUpdateAdapter<OrderItemEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `order_items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OrderItemEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfOrderItemEntity = new EntityDeleteOrUpdateAdapter<OrderItemEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `order_items` SET `id` = ?,`orderId` = ?,`menuItemId` = ?,`menuItemName` = ?,`quantity` = ?,`unitPriceCents` = ?,`totalPriceCents` = ?,`notes` = ?,`status` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OrderItemEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getOrderId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getOrderId());
        }
        if (entity.getMenuItemId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getMenuItemId());
        }
        if (entity.getMenuItemName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getMenuItemName());
        }
        statement.bindLong(5, entity.getQuantity());
        statement.bindLong(6, entity.getUnitPriceCents());
        statement.bindLong(7, entity.getTotalPriceCents());
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getNotes());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getStatus());
        }
        if (entity.getId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindText(10, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insert(final OrderItemEntity[] items,
      final Continuation<? super Unit> $completion) {
    if (items == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfOrderItemEntity.insert(_connection, items);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final OrderItemEntity item, final Continuation<? super Unit> $completion) {
    if (item == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfOrderItemEntity.handle(_connection, item);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final OrderItemEntity item, final Continuation<? super Unit> $completion) {
    if (item == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfOrderItemEntity.handle(_connection, item);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getByOrderId(final String orderId,
      final Continuation<? super List<OrderItemEntity>> $completion) {
    final String _sql = "SELECT * FROM order_items WHERE orderId = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (orderId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, orderId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfOrderId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderId");
        final int _columnIndexOfMenuItemId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "menuItemId");
        final int _columnIndexOfMenuItemName = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "menuItemName");
        final int _columnIndexOfQuantity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "quantity");
        final int _columnIndexOfUnitPriceCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "unitPriceCents");
        final int _columnIndexOfTotalPriceCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalPriceCents");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final List<OrderItemEntity> _result = new ArrayList<OrderItemEntity>();
        while (_stmt.step()) {
          final OrderItemEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpOrderId;
          if (_stmt.isNull(_columnIndexOfOrderId)) {
            _tmpOrderId = null;
          } else {
            _tmpOrderId = _stmt.getText(_columnIndexOfOrderId);
          }
          final String _tmpMenuItemId;
          if (_stmt.isNull(_columnIndexOfMenuItemId)) {
            _tmpMenuItemId = null;
          } else {
            _tmpMenuItemId = _stmt.getText(_columnIndexOfMenuItemId);
          }
          final String _tmpMenuItemName;
          if (_stmt.isNull(_columnIndexOfMenuItemName)) {
            _tmpMenuItemName = null;
          } else {
            _tmpMenuItemName = _stmt.getText(_columnIndexOfMenuItemName);
          }
          final int _tmpQuantity;
          _tmpQuantity = (int) (_stmt.getLong(_columnIndexOfQuantity));
          final long _tmpUnitPriceCents;
          _tmpUnitPriceCents = _stmt.getLong(_columnIndexOfUnitPriceCents);
          final long _tmpTotalPriceCents;
          _tmpTotalPriceCents = _stmt.getLong(_columnIndexOfTotalPriceCents);
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          _item = new OrderItemEntity(_tmpId,_tmpOrderId,_tmpMenuItemId,_tmpMenuItemName,_tmpQuantity,_tmpUnitPriceCents,_tmpTotalPriceCents,_tmpNotes,_tmpStatus);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String orderId, final String itemId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM order_items WHERE orderId = ? AND id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (orderId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, orderId);
        }
        _argIndex = 2;
        if (itemId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, itemId);
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
