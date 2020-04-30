package br.com.flourish.pedemeia.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Profile("!test")
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "datasource.pedemeia.postgresql")
@EnableJpaRepositories(//
		basePackages = "br.com.flourish.pedemeia.db.sql.pedemeia.repository", //
		entityManagerFactoryRef = "pedemeiaEntityManager", //
		transactionManagerRef = "pedemeiaTransactionManager")
public class DbPeDeMeiaConfig extends HikariConfig {

	@Bean(name = "pedemeiaDataSource")
	public DataSource propostapjDataSourceFactory() {
		return new HikariDataSource(this);
	}

	@PersistenceContext(unitName = "Teste")
	@Bean(name = "pedemeiaEntityManager")
	public LocalContainerEntityManagerFactoryBean propostaPJManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(propostapjDataSourceFactory()).persistenceUnit("Teste").properties(jpaProperties())
				.packages("br.com.flourish.pedemeia.db.sql.propostapj.entity").build();
	}

	@Bean(name = "pedemeiaTransactionManager")
	public PlatformTransactionManager propostaPJTransactionManagerFactory(
			@Qualifier("pedemeiaEntityManager") EntityManagerFactory em) {
		return new JpaTransactionManager(em);
	}

	private Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2005Dialect");
		return props;
	}
}
