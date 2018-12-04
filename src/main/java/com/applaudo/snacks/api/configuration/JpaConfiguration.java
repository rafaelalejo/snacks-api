package com.applaudo.snacks.api.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.applaudo.snacks.api.demo.YAMLConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@EnableTransactionManagement
// Activacion de repositorios para Spring Data
@EnableJpaRepositories(basePackages = "com.applaudo.snacks.api.repository")
public class JpaConfiguration {

	@Autowired
	private YAMLConfig config;

	// Constantes de la conexion a base de datos
	public String POSTGRES_DBNAME;
	public String POSTGRES_ADDR;
	public String POSTGRES_USER;
	public String POSTGRES_PASS;

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	// Definicion de las entidades del proyecto
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(dataSource());
		em.setPersistenceUnitName("applaudo");
		em.setPackagesToScan("com.applaudo.snacks.api.domain");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());

		return em;
	}

	// Activar la funcionalidad de transacciones.
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	// Aplicacion de los ajustes de conexion a la base de datos.
	@Bean
	public DataSource dataSource() {
		POSTGRES_DBNAME = config.getDbName();
		POSTGRES_ADDR = config.getServerAddress() + POSTGRES_DBNAME;
		POSTGRES_USER = config.getDbUser();
		POSTGRES_PASS = config.getDbPassword();

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(POSTGRES_ADDR);
		dataSource.setUsername(POSTGRES_USER);
		dataSource.setPassword(POSTGRES_PASS);

		return dataSource;
	}

	// Ajustes de hibernate para postgreSQL
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

		return properties;
	}
}