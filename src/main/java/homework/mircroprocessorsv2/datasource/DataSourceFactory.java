package homework.mircroprocessorsv2.datasource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DataSourceFactory {
    private static final String PLUG_DATA_SOURCE_VALUE = "plug";
    private static final String DB_DATA_SOURCE_VALUE = "db";
    private final String dataSourceConf;

    public DataSourceFactory() throws NamingException {
        Context ctx = new InitialContext();
        Context env = (Context) ctx.lookup("java:comp/env");
        dataSourceConf = (String)env.lookup("data-source");
    }

    public MicroprocessorDataSource getDataSource() {
        return switch(dataSourceConf) {
            case DB_DATA_SOURCE_VALUE -> new DBMicroprocessorDataSource();
            default -> null;
        };
    }
}
