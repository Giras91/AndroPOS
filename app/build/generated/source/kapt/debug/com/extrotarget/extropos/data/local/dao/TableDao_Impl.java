package com.extrotarget.extropos.data.local.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.extrotarget.extropos.data.local.entity.TableEntity;
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
public final class TableDao_Impl implements TableDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<TableEntity> __insertAdapterOfTableEntity;

  public TableDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfTableEntity = new EntityInsertAdapter<TableEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tables` (`id`,`number`,`capacity`,`status`,`currentOrderId`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final TableEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindText(1, entity.getId());
        }
        if (entity.getNumber() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getNumber());
        }
        statement.bindLong(3, entity.getCapacity());
        if (entity.getStatus() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getStatus());
        }
        if (entity.getCurrentOrderId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getCurrentOrderId());
        }
      }
    };
  }

  @Override
  public Object upsert(final TableEntity[] tables, final Continuation<? super Unit> $completion) {
    if (tables == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfTableEntity.insert(_connection, tables);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<TableEntity>> $completion) {
    final String _sql = "SELECT * FROM tables";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "number");
        final int _columnIndexOfCapacity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "capacity");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfCurrentOrderId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentOrderId");
        final List<TableEntity> _result = new ArrayList<TableEntity>();
        while (_stmt.step()) {
          final TableEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpNumber;
          if (_stmt.isNull(_columnIndexOfNumber)) {
            _tmpNumber = null;
          } else {
            _tmpNumber = _stmt.getText(_columnIndexOfNumber);
          }
          final int _tmpCapacity;
          _tmpCapacity = (int) (_stmt.getLong(_columnIndexOfCapacity));
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpCurrentOrderId;
          if (_stmt.isNull(_columnIndexOfCurrentOrderId)) {
            _tmpCurrentOrderId = null;
          } else {
            _tmpCurrentOrderId = _stmt.getText(_columnIndexOfCurrentOrderId);
          }
          _item = new TableEntity(_tmpId,_tmpNumber,_tmpCapacity,_tmpStatus,_tmpCurrentOrderId);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getById(final String id, final Continuation<? super TableEntity> $completion) {
    final String _sql = "SELECT * FROM tables WHERE id = ?";
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
        final int _columnIndexOfNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "number");
        final int _columnIndexOfCapacity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "capacity");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfCurrentOrderId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentOrderId");
        final TableEntity _result;
        if (_stmt.step()) {
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpNumber;
          if (_stmt.isNull(_columnIndexOfNumber)) {
            _tmpNumber = null;
          } else {
            _tmpNumber = _stmt.getText(_columnIndexOfNumber);
          }
          final int _tmpCapacity;
          _tmpCapacity = (int) (_stmt.getLong(_columnIndexOfCapacity));
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpCurrentOrderId;
          if (_stmt.isNull(_columnIndexOfCurrentOrderId)) {
            _tmpCurrentOrderId = null;
          } else {
            _tmpCurrentOrderId = _stmt.getText(_columnIndexOfCurrentOrderId);
          }
          _result = new TableEntity(_tmpId,_tmpNumber,_tmpCapacity,_tmpStatus,_tmpCurrentOrderId);
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
  public Object getAvailable(final Continuation<? super List<TableEntity>> $completion) {
    final String _sql = "SELECT * FROM tables WHERE status = 'AVAILABLE'";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "number");
        final int _columnIndexOfCapacity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "capacity");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfCurrentOrderId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentOrderId");
        final List<TableEntity> _result = new ArrayList<TableEntity>();
        while (_stmt.step()) {
          final TableEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpNumber;
          if (_stmt.isNull(_columnIndexOfNumber)) {
            _tmpNumber = null;
          } else {
            _tmpNumber = _stmt.getText(_columnIndexOfNumber);
          }
          final int _tmpCapacity;
          _tmpCapacity = (int) (_stmt.getLong(_columnIndexOfCapacity));
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpCurrentOrderId;
          if (_stmt.isNull(_columnIndexOfCurrentOrderId)) {
            _tmpCurrentOrderId = null;
          } else {
            _tmpCurrentOrderId = _stmt.getText(_columnIndexOfCurrentOrderId);
          }
          _item = new TableEntity(_tmpId,_tmpNumber,_tmpCapacity,_tmpStatus,_tmpCurrentOrderId);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getOccupied(final Continuation<? super List<TableEntity>> $completion) {
    final String _sql = "SELECT * FROM tables WHERE status = 'OCCUPIED'";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNumber = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "number");
        final int _columnIndexOfCapacity = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "capacity");
        final int _columnIndexOfStatus = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "status");
        final int _columnIndexOfCurrentOrderId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "currentOrderId");
        final List<TableEntity> _result = new ArrayList<TableEntity>();
        while (_stmt.step()) {
          final TableEntity _item;
          final String _tmpId;
          if (_stmt.isNull(_columnIndexOfId)) {
            _tmpId = null;
          } else {
            _tmpId = _stmt.getText(_columnIndexOfId);
          }
          final String _tmpNumber;
          if (_stmt.isNull(_columnIndexOfNumber)) {
            _tmpNumber = null;
          } else {
            _tmpNumber = _stmt.getText(_columnIndexOfNumber);
          }
          final int _tmpCapacity;
          _tmpCapacity = (int) (_stmt.getLong(_columnIndexOfCapacity));
          final String _tmpStatus;
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmpStatus = null;
          } else {
            _tmpStatus = _stmt.getText(_columnIndexOfStatus);
          }
          final String _tmpCurrentOrderId;
          if (_stmt.isNull(_columnIndexOfCurrentOrderId)) {
            _tmpCurrentOrderId = null;
          } else {
            _tmpCurrentOrderId = _stmt.getText(_columnIndexOfCurrentOrderId);
          }
          _item = new TableEntity(_tmpId,_tmpNumber,_tmpCapacity,_tmpStatus,_tmpCurrentOrderId);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object updateStatus(final String id, final String status,
      final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE tables SET status = ? WHERE id = ?";
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

  @Override
  public Object assignOrder(final String tableId, final String orderId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE tables SET currentOrderId = ? WHERE id = ?";
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
        if (tableId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, tableId);
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
