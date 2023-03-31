//package task45;
//
//import java.util.Properties;
//import jakarta.sql.DataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class HibernateConfig {
//
//    @Value("${hibernate.dialect}")
//    private String hibernateDialect;
//
//    @Value("${hibernate.show_sql}")
//    private boolean hibernateShowSql;
//
//    @Value("${hibernate.hbm2ddl.auto}")
//    private String hibernateHbm2ddlAuto;
//
//    @Value("${hibernate.format_sql}")
//    private boolean hibernateFormatSql;
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("task45");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/myapp");
//        dataSource.setUsername("root");
//        dataSource.setPassword("password");
//        return dataSource;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//        return txManager;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", hibernateDialect);
//        properties.setProperty("hibernate.show_sql", String.valueOf(hibernateShowSql));
//        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
//        properties.setProperty("hibernate.format_sql", String.valueOf(hibernateFormatSql));
//        return properties;
//    }
//}