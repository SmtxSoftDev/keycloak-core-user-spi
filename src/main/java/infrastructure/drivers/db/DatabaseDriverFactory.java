package infrastructure.drivers.db;

import org.apache.commons.lang.NullArgumentException;

public class DatabaseDriverFactory {
    public static IDatabaseDriver getInstance(DatabaseTypes type){
        switch (type){
            case Mssql:
                return new MssqlDriver();
            case Mysql:
                return new MysqlDriver();
            case Postgresql:
                return new PostgresqlDriver();
            default:
                throw new NullArgumentException("Not found database driver");
        }
    }
}