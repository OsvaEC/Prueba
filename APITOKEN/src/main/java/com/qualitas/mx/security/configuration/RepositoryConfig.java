package com.qualitas.mx.security.configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;

@Configuration
@PropertySource("classpath:config/app.properties")
public class RepositoryConfig {
	
	private static final String PROPERTY_NAME_JNDI = "jndi.name";
		
	@Autowired
	private Environment env;

	@Bean(name="sqlDS")
	@Primary
    public DataSource otro() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("base.driver"));
        dataSource.setUrl(env.getProperty("base.url"));
        dataSource.setUsername(env.getProperty("base.username"));
        dataSource.setPassword(env.getProperty("base.password"));
        return dataSource;
    }
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    Resource resource = resolver.getResource("classpath:config/mybatis-config.xml");
	    sqlSessionFactoryBean.setConfigLocation(resource);
	    sqlSessionFactoryBean.setDataSource(otro());
	    return sqlSessionFactoryBean;
	}
	
//	@Bean(name="sqlDS")
//	@Primary
//    public DataSource dataSource() throws NamingException {
//        return (DataSource) new JndiTemplate().lookup(env.getRequiredProperty(PROPERTY_NAME_JNDI));
//    }
	
//	@Bean
//    JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);        
//    }
}
