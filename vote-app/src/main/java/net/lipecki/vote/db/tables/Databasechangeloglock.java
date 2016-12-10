/**
 * This class is generated by jOOQ
 */
package net.lipecki.vote.db.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import net.lipecki.vote.db.Keys;
import net.lipecki.vote.db.Public;
import net.lipecki.vote.db.tables.records.DatabasechangeloglockRecord;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Databasechangeloglock extends TableImpl<DatabasechangeloglockRecord> {

    private static final long serialVersionUID = -649651587;

    /**
     * The reference instance of <code>PUBLIC.DATABASECHANGELOGLOCK</code>
     */
    public static final Databasechangeloglock DATABASECHANGELOGLOCK = new Databasechangeloglock();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DatabasechangeloglockRecord> getRecordType() {
        return DatabasechangeloglockRecord.class;
    }

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.ID</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Boolean> LOCKED = createField("LOCKED", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Timestamp> LOCKGRANTED = createField("LOCKGRANTED", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>PUBLIC.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    public final TableField<DatabasechangeloglockRecord, String> LOCKEDBY = createField("LOCKEDBY", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * Create a <code>PUBLIC.DATABASECHANGELOGLOCK</code> table reference
     */
    public Databasechangeloglock() {
        this("DATABASECHANGELOGLOCK", null);
    }

    /**
     * Create an aliased <code>PUBLIC.DATABASECHANGELOGLOCK</code> table reference
     */
    public Databasechangeloglock(String alias) {
        this(alias, DATABASECHANGELOGLOCK);
    }

    private Databasechangeloglock(String alias, Table<DatabasechangeloglockRecord> aliased) {
        this(alias, aliased, null);
    }

    private Databasechangeloglock(String alias, Table<DatabasechangeloglockRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DatabasechangeloglockRecord> getPrimaryKey() {
        return Keys.PK_DATABASECHANGELOGLOCK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DatabasechangeloglockRecord>> getKeys() {
        return Arrays.<UniqueKey<DatabasechangeloglockRecord>>asList(Keys.PK_DATABASECHANGELOGLOCK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Databasechangeloglock as(String alias) {
        return new Databasechangeloglock(alias, this);
    }

    /**
     * Rename this table
     */
    public Databasechangeloglock rename(String name) {
        return new Databasechangeloglock(name, null);
    }
}
