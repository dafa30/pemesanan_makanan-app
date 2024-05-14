package com.dafa_adi_raharjo_uts.catering.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.dafa_adi_raharjo_uts.catering.database.DatabaseModel;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DatabaseDao_Impl implements DatabaseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DatabaseModel> __insertionAdapterOfDatabaseModel;

  private final SharedSQLiteStatement __preparedStmtOfUpdateData;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSingleData;

  public DatabaseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDatabaseModel = new EntityInsertionAdapter<DatabaseModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tbl_catering` (`uid`,`nama_menu`,`jml_items`,`harga`,`email`,`username`,`password`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DatabaseModel value) {
        stmt.bindLong(1, value.uid);
        if (value.nama_menu == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.nama_menu);
        }
        stmt.bindLong(3, value.items);
        stmt.bindLong(4, value.harga);
        if (value.email == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.email);
        }
        if (value.username == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.username);
        }
        if (value.password == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.password);
        }
      }
    };
    this.__preparedStmtOfUpdateData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE tbl_catering SET nama_menu= ?, jml_items= ?, harga= ? WHERE uid = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSingleData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tbl_catering WHERE uid= ?";
        return _query;
      }
    };
  }

  @Override
  public void insertData(final DatabaseModel... modelDatabases) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDatabaseModel.insert(modelDatabases);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateData(final String nama_menu, final int jml_items, final int harga,
      final int uid) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateData.acquire();
    int _argIndex = 1;
    if (nama_menu == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, nama_menu);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, jml_items);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, harga);
    _argIndex = 4;
    _stmt.bindLong(_argIndex, uid);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateData.release(_stmt);
    }
  }

  @Override
  public void deleteSingleData(final int uid) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSingleData.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, uid);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteSingleData.release(_stmt);
    }
  }

  @Override
  public LiveData<List<DatabaseModel>> getAllOrder() {
    final String _sql = "SELECT * FROM tbl_catering where uid != 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tbl_catering"}, false, new Callable<List<DatabaseModel>>() {
      @Override
      public List<DatabaseModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfNamaMenu = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_menu");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "jml_items");
          final int _cursorIndexOfHarga = CursorUtil.getColumnIndexOrThrow(_cursor, "harga");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final List<DatabaseModel> _result = new ArrayList<DatabaseModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseModel _item;
            _item = new DatabaseModel();
            _item.uid = _cursor.getInt(_cursorIndexOfUid);
            if (_cursor.isNull(_cursorIndexOfNamaMenu)) {
              _item.nama_menu = null;
            } else {
              _item.nama_menu = _cursor.getString(_cursorIndexOfNamaMenu);
            }
            _item.items = _cursor.getInt(_cursorIndexOfItems);
            _item.harga = _cursor.getInt(_cursorIndexOfHarga);
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _item.email = null;
            } else {
              _item.email = _cursor.getString(_cursorIndexOfEmail);
            }
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _item.username = null;
            } else {
              _item.username = _cursor.getString(_cursorIndexOfUsername);
            }
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _item.password = null;
            } else {
              _item.password = _cursor.getString(_cursorIndexOfPassword);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DatabaseModel>> getUserByName(final String username, final String password) {
    final String _sql = "SELECT * FROM tbl_catering where username= ? AND password= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"tbl_catering"}, false, new Callable<List<DatabaseModel>>() {
      @Override
      public List<DatabaseModel> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfNamaMenu = CursorUtil.getColumnIndexOrThrow(_cursor, "nama_menu");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "jml_items");
          final int _cursorIndexOfHarga = CursorUtil.getColumnIndexOrThrow(_cursor, "harga");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final List<DatabaseModel> _result = new ArrayList<DatabaseModel>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseModel _item;
            _item = new DatabaseModel();
            _item.uid = _cursor.getInt(_cursorIndexOfUid);
            if (_cursor.isNull(_cursorIndexOfNamaMenu)) {
              _item.nama_menu = null;
            } else {
              _item.nama_menu = _cursor.getString(_cursorIndexOfNamaMenu);
            }
            _item.items = _cursor.getInt(_cursorIndexOfItems);
            _item.harga = _cursor.getInt(_cursorIndexOfHarga);
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _item.email = null;
            } else {
              _item.email = _cursor.getString(_cursorIndexOfEmail);
            }
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _item.username = null;
            } else {
              _item.username = _cursor.getString(_cursorIndexOfUsername);
            }
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _item.password = null;
            } else {
              _item.password = _cursor.getString(_cursorIndexOfPassword);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
