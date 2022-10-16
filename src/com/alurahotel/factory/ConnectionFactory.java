package com.alurahotel.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionFactory {
	private DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotelalura?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("Hola2525.");
		pooledDataSource.setMaxPoolSize(10);
		
		this.dataSource = pooledDataSource;
	}
	
	public Connection RecuperaConexion() {
		try {
			return this.dataSource.getConnection();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
