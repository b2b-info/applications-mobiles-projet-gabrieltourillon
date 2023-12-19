package com.example.osrsdex.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile LoadoutDAO _loadoutDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `Loadout` (`loadoutName` TEXT NOT NULL, `playerName` TEXT NOT NULL, `description` TEXT, `atkStyle` TEXT, `lvlHp` INTEGER, `lvlAtk` INTEGER, `lvlStr` INTEGER, `lvlDef` INTEGER, `lvlMag` INTEGER, `lvlRng` INTEGER, `bnsStb` INTEGER, `bnsSls` INTEGER, `bnsCrs` INTEGER, `bnsMag` INTEGER, `bnsRng` INTEGER, `bnsStrMel` INTEGER, `bnsStrMag` INTEGER, `bnsStrRng` INTEGER, `defStb` INTEGER, `defSls` INTEGER, `defCrs` INTEGER, `defMag` INTEGER, `defRng` INTEGER, `atkSpeed` INTEGER, `bnsPry` INTEGER, PRIMARY KEY(`loadoutName`, `playerName`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '92607870e608e8242e99dba5a5740dc9')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `Loadout`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsLoadout = new HashMap<String, TableInfo.Column>(25);
        _columnsLoadout.put("loadoutName", new TableInfo.Column("loadoutName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("playerName", new TableInfo.Column("playerName", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("atkStyle", new TableInfo.Column("atkStyle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("lvlHp", new TableInfo.Column("lvlHp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("lvlAtk", new TableInfo.Column("lvlAtk", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("lvlStr", new TableInfo.Column("lvlStr", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("lvlDef", new TableInfo.Column("lvlDef", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("lvlMag", new TableInfo.Column("lvlMag", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("lvlRng", new TableInfo.Column("lvlRng", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsStb", new TableInfo.Column("bnsStb", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsSls", new TableInfo.Column("bnsSls", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsCrs", new TableInfo.Column("bnsCrs", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsMag", new TableInfo.Column("bnsMag", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsRng", new TableInfo.Column("bnsRng", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsStrMel", new TableInfo.Column("bnsStrMel", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsStrMag", new TableInfo.Column("bnsStrMag", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsStrRng", new TableInfo.Column("bnsStrRng", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("defStb", new TableInfo.Column("defStb", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("defSls", new TableInfo.Column("defSls", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("defCrs", new TableInfo.Column("defCrs", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("defMag", new TableInfo.Column("defMag", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("defRng", new TableInfo.Column("defRng", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("atkSpeed", new TableInfo.Column("atkSpeed", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadout.put("bnsPry", new TableInfo.Column("bnsPry", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoadout = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLoadout = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLoadout = new TableInfo("Loadout", _columnsLoadout, _foreignKeysLoadout, _indicesLoadout);
        final TableInfo _existingLoadout = TableInfo.read(db, "Loadout");
        if (!_infoLoadout.equals(_existingLoadout)) {
          return new RoomOpenHelper.ValidationResult(false, "Loadout(com.example.osrsdex.db.Loadout).\n"
                  + " Expected:\n" + _infoLoadout + "\n"
                  + " Found:\n" + _existingLoadout);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "92607870e608e8242e99dba5a5740dc9", "c28f4efe708c626b5fff01feaaafd685");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Loadout");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Loadout`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LoadoutDAO.class, LoadoutDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LoadoutDAO loadoutDAO() {
    if (_loadoutDAO != null) {
      return _loadoutDAO;
    } else {
      synchronized(this) {
        if(_loadoutDAO == null) {
          _loadoutDAO = new LoadoutDAO_Impl(this);
        }
        return _loadoutDAO;
      }
    }
  }
}
