package vdm.appinstall.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.core.env.Environment;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages="vdm.repository",
        entityManagerFactoryRef="emf")
@PropertySource("classpath:postgresql.properties")
public class JpaConfig {

    private Environment env;

    @Autowired
    JpaConfig(Environment env){
        this.env = env;
    }

    @Bean
    public DataSource databaseDataSources(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("driver"));
        dataSource.setUrl(env.getRequiredProperty("url"));
        dataSource.setUsername(env.getRequiredProperty("user"));
        dataSource.setPassword(env.getRequiredProperty("password"));
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter vendorAdapter(){
        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        vendor.setDatabase(Database.POSTGRESQL);
        vendor.setDatabasePlatform(env.getRequiredProperty("dialect"));
        vendor.setShowSql(true);
        vendor.setGenerateDdl(true);
        return vendor;
    }

    @Bean
    public EntityManagerFactory emf(){

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource((databaseDataSources()));
        factoryBean.setJpaVendorAdapter(vendorAdapter());
        factoryBean.setPackagesToScan("vdm.entity");
        factoryBean.afterPropertiesSet(); //set properties from JpaVendorAdapter

        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(emf());
    }
}
