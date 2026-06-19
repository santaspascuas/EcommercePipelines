package com.AplicatioEcommerce.EcoomerceAplication.shared.security;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;


@Component
public class SchemaMultiTenantConnectionProvider  implements MultiTenantConnectionProvider<String>{
	
	private final DataSource dataSource;
	
	
	public SchemaMultiTenantConnectionProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
	@Override
	public Connection getAnyConnection() throws SQLException {
		// TODO Auto-generated method stub
		return dataSource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		//Conexion por esquema de la database.
		Connection connection = getAnyConnection();
		
		try {
			
			//
			if(tenantIdentifier == null || tenantIdentifier.isBlank()) {
				tenantIdentifier = TenantContextHolder.SYSTEM_TENANT_ID;
			}
				connection.setSchema(tenantIdentifier);
			
			
		}catch(SQLException e) {
			connection.close();
			throw new SQLException("No se pudo alterar la conexión al esquema: " + tenantIdentifier, e);
		}
		
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUnwrappableAs(Class<?> unwrapType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T>  T unwrap( Class< T> unwrapType) {
		// TODO Auto-generated method stub
		return null;
	}

}
