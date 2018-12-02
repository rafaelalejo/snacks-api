package com.applaudo.snacks.api.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
// Activacion de repositorios para Spring Data
@EnableJpaRepositories(basePackages = "com.applaudo.snacks.api.repository")
public class JpaConfiguration {

	// Constantes de la conexion a base de datos
	public static String POSTGRES_DBNAME = "snacks_applaudo";
	public static String POSTGRES_ADDR = "jdbc:postgresql://192.168.70.130/" + POSTGRES_DBNAME;
	public static String POSTGRES_USER = "applaudo";
	public static String POSTGRES_PASS = "applaudo";

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