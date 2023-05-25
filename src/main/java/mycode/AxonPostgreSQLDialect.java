package mycode;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;
import org.springframework.context.annotation.Configuration;

import java.sql.Types;

// This is needed for Axon to store some payloads (tokens, sagas, snapshots) as BYTEA rather than BLOB
// It is configured from application.yml
@Configuration
public class AxonPostgreSQLDialect extends PostgreSQLDialect {
    public AxonPostgreSQLDialect() {
        super(DatabaseVersion.make(10));
    }

    @Override
    protected String columnType(int sqlTypeCode) {
        if (sqlTypeCode == SqlTypes.BLOB) {
            return "bytea";
        }
        return super.columnType(sqlTypeCode);
    }

    @Override
    protected String castType(int sqlTypeCode) {
        if (sqlTypeCode == SqlTypes.BLOB) {
            return "bytea";
        }
        return super.castType(sqlTypeCode);
    }

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.contributeTypes(typeContributions, serviceRegistry);
        var typeRegistry = typeContributions.getTypeConfiguration().getJdbcTypeRegistry();
        typeRegistry.addDescriptor(Types.BLOB, BinaryJdbcType.INSTANCE);
    }
}
