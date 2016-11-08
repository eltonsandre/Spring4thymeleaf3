package br.com.modulo.core.config;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.modulo.core.model.AbstractModel;
import br.com.modulo.core.repository.AbstractRepository;

@Configuration
@ComponentScan(basePackageClasses = AbstractRepository.class)
@EnableJpaRepositories(basePackageClasses = AbstractRepository.class, enableDefaultTransactions = false)
@EnableTransactionManagement
public class JPAConfig {

	@Bean
	public DataSource dataSource() {
		// org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/sanDB");
		// return dataSourceH2();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		// adapter.setDatabase(Database.MYSQL);
		// adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		// adapter.setShowSql(false); adapter.setGenerateDdl(false);
		// return adapter;
		adapter.setDatabase(Database.HSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);

		return adapter;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(AbstractModel.class.getPackage().getName());
		factory.setMappingResources("sql/consultas-nativas.xml");
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	/**
	 * H2 dataSource do H2
	 */
	// private DataSource dataSourceH2() {// 01
	// try {
	// // aqui inicio um novo servidor tcp e permito que ele seja acessivel
	// // desde qualquer outro pc na rede
	// Server.createTcpServer(new String[] { "-tcpAllowOthers" }).start();
	// System.out.println("H2: Server createTcpServer().start()");
	// // aqui eu inicio o servidor web inbutido para ser acessivel desde
	// // http://localhost:8082, e acesivel desde qualquer outro pc na rede
	// Server.createWebServer().start();
	// System.out.println("H2: Server createWebServer().start()");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// DriverManagerDataSource dataSource = new DriverManagerDataSource();
	// dataSource.setDriverClassName("org.h2.Driver");
	// dataSource.setUrl("jdbc:h2:~/test;DB_CLOSE_DELAY=-1");
	// // dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
	// dataSource.setUsername("sa");
	// dataSource.setPassword("");
	// return dataSource;
	// }

}
