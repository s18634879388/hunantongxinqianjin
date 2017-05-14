package cn.hunantongxinqianjin.web.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Administrator on 2016/11/24.
 */
@Configuration
public class MyDatasouce {
    @Value("${jdbc_test.JdbcUrl}")
    private String url;
    @Value("${jdbc_test.driverClass}")
    private String driverClass;
    @Value("${jdbc_test.User}")
    private String username;
    @Value("${jdbc_test.Password}")
    private String password;

    @Bean(name = "myDatasource")
    public DataSource dataSource() throws PropertyVetoException {
        System.out.println(url+"------"+driverClass+"------"+username+"--------"+password);
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setDriverClass(driverClass);
        comboPooledDataSource.setUser(username);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setInitialPoolSize(5);
        comboPooledDataSource.setMinPoolSize(2);
        comboPooledDataSource.setMaxPoolSize(10);
        comboPooledDataSource.setIdleConnectionTestPeriod(3000);
        return comboPooledDataSource;
    }

}
