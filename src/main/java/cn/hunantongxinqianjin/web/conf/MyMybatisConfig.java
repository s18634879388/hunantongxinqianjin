package cn.hunantongxinqianjin.web.conf;

import cn.hunantongxinqianjin.web.mapper.ProductMapper;
import cn.hunantongxinqianjin.web.mapper.RecordMapper;
import cn.hunantongxinqianjin.web.mapper.UserInfoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2016/11/24.
 */
@Configuration
public class MyMybatisConfig {
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;
    @Value("${mybatis.configLocation}")
    private String configLocation;

    @Autowired
    @Qualifier("myDatasource")
    private DataSource dataSource;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        try{
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
            sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
            SqlSessionFactory sqlSessionFactory =null;
            try {
                sqlSessionFactory = sqlSessionFactoryBean.getObject();
            }catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        }catch (Exception e){
            return null;
        }
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(){
        sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }
    @Bean
    public ProductMapper productMapper(){return sqlSessionTemplate.getMapper(ProductMapper.class);}

    @Bean
    public RecordMapper recordMapper(){return sqlSessionTemplate.getMapper(RecordMapper.class);}

    @Bean
    public UserInfoMapper userInfoMapper(){return sqlSessionTemplate.getMapper(UserInfoMapper.class);}


}
