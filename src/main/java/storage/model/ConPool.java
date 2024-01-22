package storage.model;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConPool {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException{
        if(dataSource == null){
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/persistenzaMC?serverTimexone="+ TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("root");
<<<<<<< HEAD
            p.setPassword("1107");
=======
            p.setPassword("@Paci24!");
>>>>>>> e95687fd124265f5fbced7d6b9f52005c13065f2
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            dataSource = new DataSource();
            dataSource.setPoolProperties(p);
        }
        return  dataSource.getConnection();
    }
}
