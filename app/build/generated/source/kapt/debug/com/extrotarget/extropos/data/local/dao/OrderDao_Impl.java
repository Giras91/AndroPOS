package com.extrotarget.extropos.data.local.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.extrotarget.extropos.data.local.entity.OrderEntity;
import java.lang.Class;
import java.lang.Long;
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
public final class OrderDao_Impl implements OrderDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<OrderEntity> __insertAdapterOfOrderEntity;

  private final EntityDeleteOrUpdateAdapter<OrderEntity> __updateAdapterOfOrderEntity;

  public OrderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfOrderEntity = new EntityInsertAdapter<OrderEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `orders` (`id`,`tableId`,`orderNumber`,`status`,`orderType`,`subtotalCents`,`taxCents`,`discountCents`,`totalCents`,`createdAt`,`updatedAt`,`notes`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OrderEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getTableId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTableId());
        }
        if (entity.getOrderNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getOrderNumber());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getStatus());
        }
        if (entity.getOrderType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getOrderType());
        }
        statement.bindLong(6, entity.getSubtotalCents());
        statement.bindLong(7, entity.getTaxCents());
        statement.bindLong(8, entity.getDiscountCents());
        statement.bindLong(9, entity.getTotalCents());
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getUpdatedAt());
        if (entity.getNotes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getNotes());
        }
      }
    };
    this.__updateAdapterOfOrderEntity = new EntityDeleteOrUpdateAdapter<OrderEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `orders` SET `id` = ?,`tableId` = ?,`orderNumber` = ?,`status` = ?,`orderType` = ?,`subtotalCents` = ?,`taxCents` = ?,`discountCents` = ?,`totalCents` = ?,`createdAt` = ?,`updatedAt` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final OrderEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getTableId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTableId());
        }
        if (entity.getOrderNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getOrderNumber());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getStatus());
        }
        if (entity.getOrderType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getOrderType());
        }
        statement.bindLong(6, entity.getSubtotalCents());
        statement.bindLong(7, entity.getTaxCents());
        statement.bindLong(8, entity.getDiscountCents());
        statement.bindLong(9, entity.getTotalCents());
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getUpdatedAt());
        if (entity.getNotes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindText(12, entity.getNotes());
        }
        if (entity.getId() == null) {
          statement.bindNull(13);
        } else {
          statement.bindText(13, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insert(final OrderEntity order, final Continuation<? super Long> $completion) {
    if (order == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfOrderEntity.insertAndReturnId(_connection, order);
    }, $completion);
  }

  @Override
  public Object update(final OrderEntity order, final Continuation<? super Unit> $completion) {
    if (order == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfOrderEntity.handle(_connection, order);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getActiveOrders(final Continuation<? super List<OrderEntity>> $completion) {
    final String _sql = "SELECT * FROM orders WHERE status IN ('PENDING', 'CONFIRMED', 'PREPARING', 'READY') ORDER BY createdAt DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTableId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tableId");
        final int _columnIndexOfOrderNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderNumber");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfOrderType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderType");
        final int _columnIndexOfSubtotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subtotalCents");
        final int _columnIndexOfTaxCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taxCents");
        final int _columnIndexOfDiscountCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "discountCents");
        final int _columnIndexOfTotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalCents");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final List<OrderEntity> _result = new ArrayList<OrderEntity>();
        while (_stmt.step()) {
          final OrderEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTableId;
          if (_stmt.isNull(_columnIndexOfTableId)) {
            _tmpTableId = null;
          } else {
            _tmpTableId = _stmt.getText(_columnIndexOfTableId);
          }
          final String _tmpOrderNumber;
          if (_stmt.isNull(_columnIndexOfOrderNumber)) {
            _tmpOrderNumber = null;
          } else {
            _tmpOrderNumber = _stmt.getText(_columnIndexOfOrderNumber);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpOrderType;
          if (_stmt.isNull(_columnIndexOfOrderType)) {
            _tmpOrderType = null;
          } else {
            _tmpOrderType = _stmt.getText(_columnIndexOfOrderType);
          }
          final long _tmpSubtotalCents;
          _tmpSubtotalCents = _stmt.getLong(_columnIndexOfSubtotalCents);
          final long _tmpTaxCents;
          _tmpTaxCents = _stmt.getLong(_columnIndexOfTaxCents);
          final long _tmpDiscountCents;
          _tmpDiscountCents = _stmt.getLong(_columnIndexOfDiscountCents);
          final long _tmpTotalCents;
          _tmpTotalCents = _stmt.getLong(_columnIndexOfTotalCents);
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          _item = new OrderEntity(_tmpId,_tmpTableId,_tmpOrderNumber,_tmpStatus,_tmpOrderType,_tmpSubtotalCents,_tmpTaxCents,_tmpDiscountCents,_tmpTotalCents,_tmpCreatedAt,_tmpUpdatedAt,_tmpNotes);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getByStatus(final String status,
      final Continuation<? super List<OrderEntity>> $completion) {
    final String _sql = "SELECT * FROM orders WHERE status = ? ORDER BY createdAt DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, status);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTableId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tableId");
        final int _columnIndexOfOrderNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderNumber");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfOrderType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderType");
        final int _columnIndexOfSubtotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subtotalCents");
        final int _columnIndexOfTaxCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taxCents");
        final int _columnIndexOfDiscountCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "discountCents");
        final int _columnIndexOfTotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalCents");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final List<OrderEntity> _result = new ArrayList<OrderEntity>();
        while (_stmt.step()) {
          final OrderEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTableId;
          if (_stmt.isNull(_columnIndexOfTableId)) {
            _tmpTableId = null;
          } else {
            _tmpTableId = _stmt.getText(_columnIndexOfTableId);
          }
          final String _tmpOrderNumber;
          if (_stmt.isNull(_columnIndexOfOrderNumber)) {
            _tmpOrderNumber = null;
          } else {
            _tmpOrderNumber = _stmt.getText(_columnIndexOfOrderNumber);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpOrderType;
          if (_stmt.isNull(_columnIndexOfOrderType)) {
            _tmpOrderType = null;
          } else {
            _tmpOrderType = _stmt.getText(_columnIndexOfOrderType);
          }
          final long _tmpSubtotalCents;
          _tmpSubtotalCents = _stmt.getLong(_columnIndexOfSubtotalCents);
          final long _tmpTaxCents;
          _tmpTaxCents = _stmt.getLong(_columnIndexOfTaxCents);
          final long _tmpDiscountCents;
          _tmpDiscountCents = _stmt.getLong(_columnIndexOfDiscountCents);
          final long _tmpTotalCents;
          _tmpTotalCents = _stmt.getLong(_columnIndexOfTotalCents);
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          _item = new OrderEntity(_tmpId,_tmpTableId,_tmpOrderNumber,_tmpStatus,_tmpOrderType,_tmpSubtotalCents,_tmpTaxCents,_tmpDiscountCents,_tmpTotalCents,_tmpCreatedAt,_tmpUpdatedAt,_tmpNotes);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getByTable(final String tableId,
      final Continuation<? super List<OrderEntity>> $completion) {
    final String _sql = "SELECT * FROM orders WHERE tableId = ? ORDER BY createdAt DESC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (tableId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, tableId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTableId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tableId");
        final int _columnIndexOfOrderNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderNumber");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfOrderType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderType");
        final int _columnIndexOfSubtotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subtotalCents");
        final int _columnIndexOfTaxCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taxCents");
        final int _columnIndexOfDiscountCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "discountCents");
        final int _columnIndexOfTotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalCents");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final List<OrderEntity> _result = new ArrayList<OrderEntity>();
        while (_stmt.step()) {
          final OrderEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTableId;
          if (_stmt.isNull(_columnIndexOfTableId)) {
            _tmpTableId = null;
          } else {
            _tmpTableId = _stmt.getText(_columnIndexOfTableId);
          }
          final String _tmpOrderNumber;
          if (_stmt.isNull(_columnIndexOfOrderNumber)) {
            _tmpOrderNumber = null;
          } else {
            _tmpOrderNumber = _stmt.getText(_columnIndexOfOrderNumber);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpOrderType;
          if (_stmt.isNull(_columnIndexOfOrderType)) {
            _tmpOrderType = null;
          } else {
            _tmpOrderType = _stmt.getText(_columnIndexOfOrderType);
          }
          final long _tmpSubtotalCents;
          _tmpSubtotalCents = _stmt.getLong(_columnIndexOfSubtotalCents);
          final long _tmpTaxCents;
          _tmpTaxCents = _stmt.getLong(_columnIndexOfTaxCents);
          final long _tmpDiscountCents;
          _tmpDiscountCents = _stmt.getLong(_columnIndexOfDiscountCents);
          final long _tmpTotalCents;
          _tmpTotalCents = _stmt.getLong(_columnIndexOfTotalCents);
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          _item = new OrderEntity(_tmpId,_tmpTableId,_tmpOrderNumber,_tmpStatus,_tmpOrderType,_tmpSubtotalCents,_tmpTaxCents,_tmpDiscountCents,_tmpTotalCents,_tmpCreatedAt,_tmpUpdatedAt,_tmpNotes);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getById(final String id, final Continuation<? super OrderEntity> $completion) {
    final String _sql = "SELECT * FROM orders WHERE id = ?";
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
        final int _columnIndexOfTableId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tableId");
        final int _columnIndexOfOrderNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderNumber");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfOrderType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "orderType");
        final int _columnIndexOfSubtotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "subtotalCents");
        final int _columnIndexOfTaxCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "taxCents");
        final int _columnIndexOfDiscountCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "discountCents");
        final int _columnIndexOfTotalCents = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "totalCents");
        final int _columnIndexOfCreatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "createdAt");
        final int _columnIndexOfUpdatedAt = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "updatedAt");
        final int _columnIndexOfNotes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notes");
        final OrderEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpTableId;
          if (_stmt.isNull(_columnIndexOfTableId)) {
            _tmpTableId = null;
          } else {
            _tmpTableId = _stmt.getText(_columnIndexOfTableId);
          }
          final String _tmpOrderNumber;
          if (_stmt.isNull(_columnIndexOfOrderNumber)) {
            _tmpOrderNumber = null;
          } else {
            _tmpOrderNumber = _stmt.getText(_columnIndexOfOrderNumber);
          }
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpOrderType;
          if (_stmt.isNull(_columnIndexOfOrderType)) {
            _tmpOrderType = null;
          } else {
            _tmpOrderType = _stmt.getText(_columnIndexOfOrderType);
          }
          final long _tmpSubtotalCents;
          _tmpSubtotalCents = _stmt.getLong(_columnIndexOfSubtotalCents);
          final long _tmpTaxCents;
          _tmpTaxCents = _stmt.getLong(_columnIndexOfTaxCents);
          final long _tmpDiscountCents;
          _tmpDiscountCents = _stmt.getLong(_columnIndexOfDiscountCents);
          final long _tmpTotalCents;
          _tmpTotalCents = _stmt.getLong(_columnIndexOfTotalCents);
          final long _tmpCreatedAt;
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt);
          final long _tmpUpdatedAt;
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt);
          final String _tmpNotes;
          if (_stmt.isNull(_columnIndexOfNotes)) {
            _tmpNotes = null;
          } else {
            _tmpNotes = _stmt.getText(_columnIndexOfNotes);
          }
          _result = new OrderEntity(_tmpId,_tmpTableId,_tmpOrderNumber,_tmpStatus,_tmpOrderType,_tmpSubtotalCents,_tmpTaxCents,_tmpDiscountCents,_tmpTotalCents,_tmpCreatedAt,_tmpUpdatedAt,_tmpNotes);
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
  public Object updateStatus(final String id, final String status, final long updatedAt,
      final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE orders SET status = ?, updatedAt = ? WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, status);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
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
