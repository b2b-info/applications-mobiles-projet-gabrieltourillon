package com.example.osrsdex.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LoadoutDAO_Impl implements LoadoutDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Loadout> __insertionAdapterOfLoadout;

  private final EntityDeletionOrUpdateAdapter<Loadout> __updateAdapterOfLoadout;

  public LoadoutDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoadout = new EntityInsertionAdapter<Loadout>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Loadout` (`loadoutName`,`playerName`,`description`,`atkStyle`,`lvlHp`,`lvlAtk`,`lvlStr`,`lvlDef`,`lvlMag`,`lvlRng`,`bnsStb`,`bnsSls`,`bnsCrs`,`bnsMag`,`bnsRng`,`bnsStrMel`,`bnsStrMag`,`bnsStrRng`,`defStb`,`defSls`,`defCrs`,`defMag`,`defRng`,`atkSpeed`,`bnsPry`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Loadout entity) {
        statement.bindString(1, entity.getLoadoutName());
        statement.bindString(2, entity.getPlayerName());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getAtkStyle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAtkStyle());
        }
        final CombatStats _tmpStats = entity.getStats();
        if (_tmpStats != null) {
          if (_tmpStats.getLvlHp() == null) {
            statement.bindNull(5);
          } else {
            statement.bindLong(5, _tmpStats.getLvlHp());
          }
          if (_tmpStats.getLvlAtk() == null) {
            statement.bindNull(6);
          } else {
            statement.bindLong(6, _tmpStats.getLvlAtk());
          }
          if (_tmpStats.getLvlStr() == null) {
            statement.bindNull(7);
          } else {
            statement.bindLong(7, _tmpStats.getLvlStr());
          }
          if (_tmpStats.getLvlDef() == null) {
            statement.bindNull(8);
          } else {
            statement.bindLong(8, _tmpStats.getLvlDef());
          }
          if (_tmpStats.getLvlMag() == null) {
            statement.bindNull(9);
          } else {
            statement.bindLong(9, _tmpStats.getLvlMag());
          }
          if (_tmpStats.getLvlRng() == null) {
            statement.bindNull(10);
          } else {
            statement.bindLong(10, _tmpStats.getLvlRng());
          }
          if (_tmpStats.getBnsStb() == null) {
            statement.bindNull(11);
          } else {
            statement.bindLong(11, _tmpStats.getBnsStb());
          }
          if (_tmpStats.getBnsSls() == null) {
            statement.bindNull(12);
          } else {
            statement.bindLong(12, _tmpStats.getBnsSls());
          }
          if (_tmpStats.getBnsCrs() == null) {
            statement.bindNull(13);
          } else {
            statement.bindLong(13, _tmpStats.getBnsCrs());
          }
          if (_tmpStats.getBnsMag() == null) {
            statement.bindNull(14);
          } else {
            statement.bindLong(14, _tmpStats.getBnsMag());
          }
          if (_tmpStats.getBnsRng() == null) {
            statement.bindNull(15);
          } else {
            statement.bindLong(15, _tmpStats.getBnsRng());
          }
          if (_tmpStats.getBnsStrMel() == null) {
            statement.bindNull(16);
          } else {
            statement.bindLong(16, _tmpStats.getBnsStrMel());
          }
          if (_tmpStats.getBnsStrMag() == null) {
            statement.bindNull(17);
          } else {
            statement.bindLong(17, _tmpStats.getBnsStrMag());
          }
          if (_tmpStats.getBnsStrRng() == null) {
            statement.bindNull(18);
          } else {
            statement.bindLong(18, _tmpStats.getBnsStrRng());
          }
          if (_tmpStats.getDefStb() == null) {
            statement.bindNull(19);
          } else {
            statement.bindLong(19, _tmpStats.getDefStb());
          }
          if (_tmpStats.getDefSls() == null) {
            statement.bindNull(20);
          } else {
            statement.bindLong(20, _tmpStats.getDefSls());
          }
          if (_tmpStats.getDefCrs() == null) {
            statement.bindNull(21);
          } else {
            statement.bindLong(21, _tmpStats.getDefCrs());
          }
          if (_tmpStats.getDefMag() == null) {
            statement.bindNull(22);
          } else {
            statement.bindLong(22, _tmpStats.getDefMag());
          }
          if (_tmpStats.getDefRng() == null) {
            statement.bindNull(23);
          } else {
            statement.bindLong(23, _tmpStats.getDefRng());
          }
          if (_tmpStats.getAtkSpeed() == null) {
            statement.bindNull(24);
          } else {
            statement.bindLong(24, _tmpStats.getAtkSpeed());
          }
          if (_tmpStats.getBnsPry() == null) {
            statement.bindNull(25);
          } else {
            statement.bindLong(25, _tmpStats.getBnsPry());
          }
        } else {
          statement.bindNull(5);
          statement.bindNull(6);
          statement.bindNull(7);
          statement.bindNull(8);
          statement.bindNull(9);
          statement.bindNull(10);
          statement.bindNull(11);
          statement.bindNull(12);
          statement.bindNull(13);
          statement.bindNull(14);
          statement.bindNull(15);
          statement.bindNull(16);
          statement.bindNull(17);
          statement.bindNull(18);
          statement.bindNull(19);
          statement.bindNull(20);
          statement.bindNull(21);
          statement.bindNull(22);
          statement.bindNull(23);
          statement.bindNull(24);
          statement.bindNull(25);
        }
      }
    };
    this.__updateAdapterOfLoadout = new EntityDeletionOrUpdateAdapter<Loadout>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Loadout` SET `loadoutName` = ?,`playerName` = ?,`description` = ?,`atkStyle` = ?,`lvlHp` = ?,`lvlAtk` = ?,`lvlStr` = ?,`lvlDef` = ?,`lvlMag` = ?,`lvlRng` = ?,`bnsStb` = ?,`bnsSls` = ?,`bnsCrs` = ?,`bnsMag` = ?,`bnsRng` = ?,`bnsStrMel` = ?,`bnsStrMag` = ?,`bnsStrRng` = ?,`defStb` = ?,`defSls` = ?,`defCrs` = ?,`defMag` = ?,`defRng` = ?,`atkSpeed` = ?,`bnsPry` = ? WHERE `loadoutName` = ? AND `playerName` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Loadout entity) {
        statement.bindString(1, entity.getLoadoutName());
        statement.bindString(2, entity.getPlayerName());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getAtkStyle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getAtkStyle());
        }
        final CombatStats _tmpStats = entity.getStats();
        if (_tmpStats != null) {
          if (_tmpStats.getLvlHp() == null) {
            statement.bindNull(5);
          } else {
            statement.bindLong(5, _tmpStats.getLvlHp());
          }
          if (_tmpStats.getLvlAtk() == null) {
            statement.bindNull(6);
          } else {
            statement.bindLong(6, _tmpStats.getLvlAtk());
          }
          if (_tmpStats.getLvlStr() == null) {
            statement.bindNull(7);
          } else {
            statement.bindLong(7, _tmpStats.getLvlStr());
          }
          if (_tmpStats.getLvlDef() == null) {
            statement.bindNull(8);
          } else {
            statement.bindLong(8, _tmpStats.getLvlDef());
          }
          if (_tmpStats.getLvlMag() == null) {
            statement.bindNull(9);
          } else {
            statement.bindLong(9, _tmpStats.getLvlMag());
          }
          if (_tmpStats.getLvlRng() == null) {
            statement.bindNull(10);
          } else {
            statement.bindLong(10, _tmpStats.getLvlRng());
          }
          if (_tmpStats.getBnsStb() == null) {
            statement.bindNull(11);
          } else {
            statement.bindLong(11, _tmpStats.getBnsStb());
          }
          if (_tmpStats.getBnsSls() == null) {
            statement.bindNull(12);
          } else {
            statement.bindLong(12, _tmpStats.getBnsSls());
          }
          if (_tmpStats.getBnsCrs() == null) {
            statement.bindNull(13);
          } else {
            statement.bindLong(13, _tmpStats.getBnsCrs());
          }
          if (_tmpStats.getBnsMag() == null) {
            statement.bindNull(14);
          } else {
            statement.bindLong(14, _tmpStats.getBnsMag());
          }
          if (_tmpStats.getBnsRng() == null) {
            statement.bindNull(15);
          } else {
            statement.bindLong(15, _tmpStats.getBnsRng());
          }
          if (_tmpStats.getBnsStrMel() == null) {
            statement.bindNull(16);
          } else {
            statement.bindLong(16, _tmpStats.getBnsStrMel());
          }
          if (_tmpStats.getBnsStrMag() == null) {
            statement.bindNull(17);
          } else {
            statement.bindLong(17, _tmpStats.getBnsStrMag());
          }
          if (_tmpStats.getBnsStrRng() == null) {
            statement.bindNull(18);
          } else {
            statement.bindLong(18, _tmpStats.getBnsStrRng());
          }
          if (_tmpStats.getDefStb() == null) {
            statement.bindNull(19);
          } else {
            statement.bindLong(19, _tmpStats.getDefStb());
          }
          if (_tmpStats.getDefSls() == null) {
            statement.bindNull(20);
          } else {
            statement.bindLong(20, _tmpStats.getDefSls());
          }
          if (_tmpStats.getDefCrs() == null) {
            statement.bindNull(21);
          } else {
            statement.bindLong(21, _tmpStats.getDefCrs());
          }
          if (_tmpStats.getDefMag() == null) {
            statement.bindNull(22);
          } else {
            statement.bindLong(22, _tmpStats.getDefMag());
          }
          if (_tmpStats.getDefRng() == null) {
            statement.bindNull(23);
          } else {
            statement.bindLong(23, _tmpStats.getDefRng());
          }
          if (_tmpStats.getAtkSpeed() == null) {
            statement.bindNull(24);
          } else {
            statement.bindLong(24, _tmpStats.getAtkSpeed());
          }
          if (_tmpStats.getBnsPry() == null) {
            statement.bindNull(25);
          } else {
            statement.bindLong(25, _tmpStats.getBnsPry());
          }
        } else {
          statement.bindNull(5);
          statement.bindNull(6);
          statement.bindNull(7);
          statement.bindNull(8);
          statement.bindNull(9);
          statement.bindNull(10);
          statement.bindNull(11);
          statement.bindNull(12);
          statement.bindNull(13);
          statement.bindNull(14);
          statement.bindNull(15);
          statement.bindNull(16);
          statement.bindNull(17);
          statement.bindNull(18);
          statement.bindNull(19);
          statement.bindNull(20);
          statement.bindNull(21);
          statement.bindNull(22);
          statement.bindNull(23);
          statement.bindNull(24);
          statement.bindNull(25);
        }
        statement.bindString(26, entity.getLoadoutName());
        statement.bindString(27, entity.getPlayerName());
      }
    };
  }

  @Override
  public Object insertLoadout(final Loadout loadout, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLoadout.insert(loadout);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLoadout(final Loadout loadout, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLoadout.handle(loadout);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<Loadout>> $completion) {
    final String _sql = "SELECT * FROM Loadout";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Loadout>>() {
      @Override
      @NonNull
      public List<Loadout> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLoadoutName = CursorUtil.getColumnIndexOrThrow(_cursor, "loadoutName");
          final int _cursorIndexOfPlayerName = CursorUtil.getColumnIndexOrThrow(_cursor, "playerName");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAtkStyle = CursorUtil.getColumnIndexOrThrow(_cursor, "atkStyle");
          final int _cursorIndexOfLvlHp = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlHp");
          final int _cursorIndexOfLvlAtk = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlAtk");
          final int _cursorIndexOfLvlStr = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlStr");
          final int _cursorIndexOfLvlDef = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlDef");
          final int _cursorIndexOfLvlMag = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlMag");
          final int _cursorIndexOfLvlRng = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlRng");
          final int _cursorIndexOfBnsStb = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStb");
          final int _cursorIndexOfBnsSls = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsSls");
          final int _cursorIndexOfBnsCrs = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsCrs");
          final int _cursorIndexOfBnsMag = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsMag");
          final int _cursorIndexOfBnsRng = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsRng");
          final int _cursorIndexOfBnsStrMel = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStrMel");
          final int _cursorIndexOfBnsStrMag = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStrMag");
          final int _cursorIndexOfBnsStrRng = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStrRng");
          final int _cursorIndexOfDefStb = CursorUtil.getColumnIndexOrThrow(_cursor, "defStb");
          final int _cursorIndexOfDefSls = CursorUtil.getColumnIndexOrThrow(_cursor, "defSls");
          final int _cursorIndexOfDefCrs = CursorUtil.getColumnIndexOrThrow(_cursor, "defCrs");
          final int _cursorIndexOfDefMag = CursorUtil.getColumnIndexOrThrow(_cursor, "defMag");
          final int _cursorIndexOfDefRng = CursorUtil.getColumnIndexOrThrow(_cursor, "defRng");
          final int _cursorIndexOfAtkSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "atkSpeed");
          final int _cursorIndexOfBnsPry = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsPry");
          final List<Loadout> _result = new ArrayList<Loadout>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Loadout _item;
            final String _tmpLoadoutName;
            _tmpLoadoutName = _cursor.getString(_cursorIndexOfLoadoutName);
            final String _tmpPlayerName;
            _tmpPlayerName = _cursor.getString(_cursorIndexOfPlayerName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpAtkStyle;
            if (_cursor.isNull(_cursorIndexOfAtkStyle)) {
              _tmpAtkStyle = null;
            } else {
              _tmpAtkStyle = _cursor.getString(_cursorIndexOfAtkStyle);
            }
            final CombatStats _tmpStats;
            if (!(_cursor.isNull(_cursorIndexOfLvlHp) && _cursor.isNull(_cursorIndexOfLvlAtk) && _cursor.isNull(_cursorIndexOfLvlStr) && _cursor.isNull(_cursorIndexOfLvlDef) && _cursor.isNull(_cursorIndexOfLvlMag) && _cursor.isNull(_cursorIndexOfLvlRng) && _cursor.isNull(_cursorIndexOfBnsStb) && _cursor.isNull(_cursorIndexOfBnsSls) && _cursor.isNull(_cursorIndexOfBnsCrs) && _cursor.isNull(_cursorIndexOfBnsMag) && _cursor.isNull(_cursorIndexOfBnsRng) && _cursor.isNull(_cursorIndexOfBnsStrMel) && _cursor.isNull(_cursorIndexOfBnsStrMag) && _cursor.isNull(_cursorIndexOfBnsStrRng) && _cursor.isNull(_cursorIndexOfDefStb) && _cursor.isNull(_cursorIndexOfDefSls) && _cursor.isNull(_cursorIndexOfDefCrs) && _cursor.isNull(_cursorIndexOfDefMag) && _cursor.isNull(_cursorIndexOfDefRng) && _cursor.isNull(_cursorIndexOfAtkSpeed) && _cursor.isNull(_cursorIndexOfBnsPry))) {
              final Integer _tmpLvlHp;
              if (_cursor.isNull(_cursorIndexOfLvlHp)) {
                _tmpLvlHp = null;
              } else {
                _tmpLvlHp = _cursor.getInt(_cursorIndexOfLvlHp);
              }
              final Integer _tmpLvlAtk;
              if (_cursor.isNull(_cursorIndexOfLvlAtk)) {
                _tmpLvlAtk = null;
              } else {
                _tmpLvlAtk = _cursor.getInt(_cursorIndexOfLvlAtk);
              }
              final Integer _tmpLvlStr;
              if (_cursor.isNull(_cursorIndexOfLvlStr)) {
                _tmpLvlStr = null;
              } else {
                _tmpLvlStr = _cursor.getInt(_cursorIndexOfLvlStr);
              }
              final Integer _tmpLvlDef;
              if (_cursor.isNull(_cursorIndexOfLvlDef)) {
                _tmpLvlDef = null;
              } else {
                _tmpLvlDef = _cursor.getInt(_cursorIndexOfLvlDef);
              }
              final Integer _tmpLvlMag;
              if (_cursor.isNull(_cursorIndexOfLvlMag)) {
                _tmpLvlMag = null;
              } else {
                _tmpLvlMag = _cursor.getInt(_cursorIndexOfLvlMag);
              }
              final Integer _tmpLvlRng;
              if (_cursor.isNull(_cursorIndexOfLvlRng)) {
                _tmpLvlRng = null;
              } else {
                _tmpLvlRng = _cursor.getInt(_cursorIndexOfLvlRng);
              }
              final Integer _tmpBnsStb;
              if (_cursor.isNull(_cursorIndexOfBnsStb)) {
                _tmpBnsStb = null;
              } else {
                _tmpBnsStb = _cursor.getInt(_cursorIndexOfBnsStb);
              }
              final Integer _tmpBnsSls;
              if (_cursor.isNull(_cursorIndexOfBnsSls)) {
                _tmpBnsSls = null;
              } else {
                _tmpBnsSls = _cursor.getInt(_cursorIndexOfBnsSls);
              }
              final Integer _tmpBnsCrs;
              if (_cursor.isNull(_cursorIndexOfBnsCrs)) {
                _tmpBnsCrs = null;
              } else {
                _tmpBnsCrs = _cursor.getInt(_cursorIndexOfBnsCrs);
              }
              final Integer _tmpBnsMag;
              if (_cursor.isNull(_cursorIndexOfBnsMag)) {
                _tmpBnsMag = null;
              } else {
                _tmpBnsMag = _cursor.getInt(_cursorIndexOfBnsMag);
              }
              final Integer _tmpBnsRng;
              if (_cursor.isNull(_cursorIndexOfBnsRng)) {
                _tmpBnsRng = null;
              } else {
                _tmpBnsRng = _cursor.getInt(_cursorIndexOfBnsRng);
              }
              final Integer _tmpBnsStrMel;
              if (_cursor.isNull(_cursorIndexOfBnsStrMel)) {
                _tmpBnsStrMel = null;
              } else {
                _tmpBnsStrMel = _cursor.getInt(_cursorIndexOfBnsStrMel);
              }
              final Integer _tmpBnsStrMag;
              if (_cursor.isNull(_cursorIndexOfBnsStrMag)) {
                _tmpBnsStrMag = null;
              } else {
                _tmpBnsStrMag = _cursor.getInt(_cursorIndexOfBnsStrMag);
              }
              final Integer _tmpBnsStrRng;
              if (_cursor.isNull(_cursorIndexOfBnsStrRng)) {
                _tmpBnsStrRng = null;
              } else {
                _tmpBnsStrRng = _cursor.getInt(_cursorIndexOfBnsStrRng);
              }
              final Integer _tmpDefStb;
              if (_cursor.isNull(_cursorIndexOfDefStb)) {
                _tmpDefStb = null;
              } else {
                _tmpDefStb = _cursor.getInt(_cursorIndexOfDefStb);
              }
              final Integer _tmpDefSls;
              if (_cursor.isNull(_cursorIndexOfDefSls)) {
                _tmpDefSls = null;
              } else {
                _tmpDefSls = _cursor.getInt(_cursorIndexOfDefSls);
              }
              final Integer _tmpDefCrs;
              if (_cursor.isNull(_cursorIndexOfDefCrs)) {
                _tmpDefCrs = null;
              } else {
                _tmpDefCrs = _cursor.getInt(_cursorIndexOfDefCrs);
              }
              final Integer _tmpDefMag;
              if (_cursor.isNull(_cursorIndexOfDefMag)) {
                _tmpDefMag = null;
              } else {
                _tmpDefMag = _cursor.getInt(_cursorIndexOfDefMag);
              }
              final Integer _tmpDefRng;
              if (_cursor.isNull(_cursorIndexOfDefRng)) {
                _tmpDefRng = null;
              } else {
                _tmpDefRng = _cursor.getInt(_cursorIndexOfDefRng);
              }
              final Integer _tmpAtkSpeed;
              if (_cursor.isNull(_cursorIndexOfAtkSpeed)) {
                _tmpAtkSpeed = null;
              } else {
                _tmpAtkSpeed = _cursor.getInt(_cursorIndexOfAtkSpeed);
              }
              final Integer _tmpBnsPry;
              if (_cursor.isNull(_cursorIndexOfBnsPry)) {
                _tmpBnsPry = null;
              } else {
                _tmpBnsPry = _cursor.getInt(_cursorIndexOfBnsPry);
              }
              _tmpStats = new CombatStats(_tmpLvlHp,_tmpLvlAtk,_tmpLvlStr,_tmpLvlDef,_tmpLvlMag,_tmpLvlRng,_tmpBnsStb,_tmpBnsSls,_tmpBnsCrs,_tmpBnsMag,_tmpBnsRng,_tmpBnsStrMel,_tmpBnsStrMag,_tmpBnsStrRng,_tmpDefStb,_tmpDefSls,_tmpDefCrs,_tmpDefMag,_tmpDefRng,_tmpAtkSpeed,_tmpBnsPry);
            } else {
              _tmpStats = null;
            }
            _item = new Loadout(_tmpLoadoutName,_tmpPlayerName,_tmpDescription,_tmpStats,_tmpAtkStyle);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllWherePlayerName(final String pn,
      final Continuation<? super List<Loadout>> $completion) {
    final String _sql = "SELECT * FROM Loadout WHERE playerName LIKE (?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, pn);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Loadout>>() {
      @Override
      @NonNull
      public List<Loadout> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLoadoutName = CursorUtil.getColumnIndexOrThrow(_cursor, "loadoutName");
          final int _cursorIndexOfPlayerName = CursorUtil.getColumnIndexOrThrow(_cursor, "playerName");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAtkStyle = CursorUtil.getColumnIndexOrThrow(_cursor, "atkStyle");
          final int _cursorIndexOfLvlHp = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlHp");
          final int _cursorIndexOfLvlAtk = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlAtk");
          final int _cursorIndexOfLvlStr = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlStr");
          final int _cursorIndexOfLvlDef = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlDef");
          final int _cursorIndexOfLvlMag = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlMag");
          final int _cursorIndexOfLvlRng = CursorUtil.getColumnIndexOrThrow(_cursor, "lvlRng");
          final int _cursorIndexOfBnsStb = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStb");
          final int _cursorIndexOfBnsSls = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsSls");
          final int _cursorIndexOfBnsCrs = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsCrs");
          final int _cursorIndexOfBnsMag = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsMag");
          final int _cursorIndexOfBnsRng = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsRng");
          final int _cursorIndexOfBnsStrMel = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStrMel");
          final int _cursorIndexOfBnsStrMag = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStrMag");
          final int _cursorIndexOfBnsStrRng = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsStrRng");
          final int _cursorIndexOfDefStb = CursorUtil.getColumnIndexOrThrow(_cursor, "defStb");
          final int _cursorIndexOfDefSls = CursorUtil.getColumnIndexOrThrow(_cursor, "defSls");
          final int _cursorIndexOfDefCrs = CursorUtil.getColumnIndexOrThrow(_cursor, "defCrs");
          final int _cursorIndexOfDefMag = CursorUtil.getColumnIndexOrThrow(_cursor, "defMag");
          final int _cursorIndexOfDefRng = CursorUtil.getColumnIndexOrThrow(_cursor, "defRng");
          final int _cursorIndexOfAtkSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "atkSpeed");
          final int _cursorIndexOfBnsPry = CursorUtil.getColumnIndexOrThrow(_cursor, "bnsPry");
          final List<Loadout> _result = new ArrayList<Loadout>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Loadout _item;
            final String _tmpLoadoutName;
            _tmpLoadoutName = _cursor.getString(_cursorIndexOfLoadoutName);
            final String _tmpPlayerName;
            _tmpPlayerName = _cursor.getString(_cursorIndexOfPlayerName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpAtkStyle;
            if (_cursor.isNull(_cursorIndexOfAtkStyle)) {
              _tmpAtkStyle = null;
            } else {
              _tmpAtkStyle = _cursor.getString(_cursorIndexOfAtkStyle);
            }
            final CombatStats _tmpStats;
            if (!(_cursor.isNull(_cursorIndexOfLvlHp) && _cursor.isNull(_cursorIndexOfLvlAtk) && _cursor.isNull(_cursorIndexOfLvlStr) && _cursor.isNull(_cursorIndexOfLvlDef) && _cursor.isNull(_cursorIndexOfLvlMag) && _cursor.isNull(_cursorIndexOfLvlRng) && _cursor.isNull(_cursorIndexOfBnsStb) && _cursor.isNull(_cursorIndexOfBnsSls) && _cursor.isNull(_cursorIndexOfBnsCrs) && _cursor.isNull(_cursorIndexOfBnsMag) && _cursor.isNull(_cursorIndexOfBnsRng) && _cursor.isNull(_cursorIndexOfBnsStrMel) && _cursor.isNull(_cursorIndexOfBnsStrMag) && _cursor.isNull(_cursorIndexOfBnsStrRng) && _cursor.isNull(_cursorIndexOfDefStb) && _cursor.isNull(_cursorIndexOfDefSls) && _cursor.isNull(_cursorIndexOfDefCrs) && _cursor.isNull(_cursorIndexOfDefMag) && _cursor.isNull(_cursorIndexOfDefRng) && _cursor.isNull(_cursorIndexOfAtkSpeed) && _cursor.isNull(_cursorIndexOfBnsPry))) {
              final Integer _tmpLvlHp;
              if (_cursor.isNull(_cursorIndexOfLvlHp)) {
                _tmpLvlHp = null;
              } else {
                _tmpLvlHp = _cursor.getInt(_cursorIndexOfLvlHp);
              }
              final Integer _tmpLvlAtk;
              if (_cursor.isNull(_cursorIndexOfLvlAtk)) {
                _tmpLvlAtk = null;
              } else {
                _tmpLvlAtk = _cursor.getInt(_cursorIndexOfLvlAtk);
              }
              final Integer _tmpLvlStr;
              if (_cursor.isNull(_cursorIndexOfLvlStr)) {
                _tmpLvlStr = null;
              } else {
                _tmpLvlStr = _cursor.getInt(_cursorIndexOfLvlStr);
              }
              final Integer _tmpLvlDef;
              if (_cursor.isNull(_cursorIndexOfLvlDef)) {
                _tmpLvlDef = null;
              } else {
                _tmpLvlDef = _cursor.getInt(_cursorIndexOfLvlDef);
              }
              final Integer _tmpLvlMag;
              if (_cursor.isNull(_cursorIndexOfLvlMag)) {
                _tmpLvlMag = null;
              } else {
                _tmpLvlMag = _cursor.getInt(_cursorIndexOfLvlMag);
              }
              final Integer _tmpLvlRng;
              if (_cursor.isNull(_cursorIndexOfLvlRng)) {
                _tmpLvlRng = null;
              } else {
                _tmpLvlRng = _cursor.getInt(_cursorIndexOfLvlRng);
              }
              final Integer _tmpBnsStb;
              if (_cursor.isNull(_cursorIndexOfBnsStb)) {
                _tmpBnsStb = null;
              } else {
                _tmpBnsStb = _cursor.getInt(_cursorIndexOfBnsStb);
              }
              final Integer _tmpBnsSls;
              if (_cursor.isNull(_cursorIndexOfBnsSls)) {
                _tmpBnsSls = null;
              } else {
                _tmpBnsSls = _cursor.getInt(_cursorIndexOfBnsSls);
              }
              final Integer _tmpBnsCrs;
              if (_cursor.isNull(_cursorIndexOfBnsCrs)) {
                _tmpBnsCrs = null;
              } else {
                _tmpBnsCrs = _cursor.getInt(_cursorIndexOfBnsCrs);
              }
              final Integer _tmpBnsMag;
              if (_cursor.isNull(_cursorIndexOfBnsMag)) {
                _tmpBnsMag = null;
              } else {
                _tmpBnsMag = _cursor.getInt(_cursorIndexOfBnsMag);
              }
              final Integer _tmpBnsRng;
              if (_cursor.isNull(_cursorIndexOfBnsRng)) {
                _tmpBnsRng = null;
              } else {
                _tmpBnsRng = _cursor.getInt(_cursorIndexOfBnsRng);
              }
              final Integer _tmpBnsStrMel;
              if (_cursor.isNull(_cursorIndexOfBnsStrMel)) {
                _tmpBnsStrMel = null;
              } else {
                _tmpBnsStrMel = _cursor.getInt(_cursorIndexOfBnsStrMel);
              }
              final Integer _tmpBnsStrMag;
              if (_cursor.isNull(_cursorIndexOfBnsStrMag)) {
                _tmpBnsStrMag = null;
              } else {
                _tmpBnsStrMag = _cursor.getInt(_cursorIndexOfBnsStrMag);
              }
              final Integer _tmpBnsStrRng;
              if (_cursor.isNull(_cursorIndexOfBnsStrRng)) {
                _tmpBnsStrRng = null;
              } else {
                _tmpBnsStrRng = _cursor.getInt(_cursorIndexOfBnsStrRng);
              }
              final Integer _tmpDefStb;
              if (_cursor.isNull(_cursorIndexOfDefStb)) {
                _tmpDefStb = null;
              } else {
                _tmpDefStb = _cursor.getInt(_cursorIndexOfDefStb);
              }
              final Integer _tmpDefSls;
              if (_cursor.isNull(_cursorIndexOfDefSls)) {
                _tmpDefSls = null;
              } else {
                _tmpDefSls = _cursor.getInt(_cursorIndexOfDefSls);
              }
              final Integer _tmpDefCrs;
              if (_cursor.isNull(_cursorIndexOfDefCrs)) {
                _tmpDefCrs = null;
              } else {
                _tmpDefCrs = _cursor.getInt(_cursorIndexOfDefCrs);
              }
              final Integer _tmpDefMag;
              if (_cursor.isNull(_cursorIndexOfDefMag)) {
                _tmpDefMag = null;
              } else {
                _tmpDefMag = _cursor.getInt(_cursorIndexOfDefMag);
              }
              final Integer _tmpDefRng;
              if (_cursor.isNull(_cursorIndexOfDefRng)) {
                _tmpDefRng = null;
              } else {
                _tmpDefRng = _cursor.getInt(_cursorIndexOfDefRng);
              }
              final Integer _tmpAtkSpeed;
              if (_cursor.isNull(_cursorIndexOfAtkSpeed)) {
                _tmpAtkSpeed = null;
              } else {
                _tmpAtkSpeed = _cursor.getInt(_cursorIndexOfAtkSpeed);
              }
              final Integer _tmpBnsPry;
              if (_cursor.isNull(_cursorIndexOfBnsPry)) {
                _tmpBnsPry = null;
              } else {
                _tmpBnsPry = _cursor.getInt(_cursorIndexOfBnsPry);
              }
              _tmpStats = new CombatStats(_tmpLvlHp,_tmpLvlAtk,_tmpLvlStr,_tmpLvlDef,_tmpLvlMag,_tmpLvlRng,_tmpBnsStb,_tmpBnsSls,_tmpBnsCrs,_tmpBnsMag,_tmpBnsRng,_tmpBnsStrMel,_tmpBnsStrMag,_tmpBnsStrRng,_tmpDefStb,_tmpDefSls,_tmpDefCrs,_tmpDefMag,_tmpDefRng,_tmpAtkSpeed,_tmpBnsPry);
            } else {
              _tmpStats = null;
            }
            _item = new Loadout(_tmpLoadoutName,_tmpPlayerName,_tmpDescription,_tmpStats,_tmpAtkStyle);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public boolean isLoadoutExists(final String pn, final String ln) {
    final String _sql = "SELECT EXISTS(SELECT * FROM Loadout WHERE loadoutName LIKE ? AND playerName LIKE ? LIMIT 1)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, ln);
    _argIndex = 2;
    _statement.bindString(_argIndex, pn);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if (_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
