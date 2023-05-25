package mycode;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.stream.Stream;

public class PostgresBigintGenerator implements IdentifierGenerator {
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		var query = "select coalesce(max(%s), 0) as oldMax from %s".formatted(
				session.getEntityPersister(obj.getClass().getName(), obj).getIdentifierPropertyName(),
				obj.getClass().getSimpleName()
		);
		Stream<Long> stream = session.createQuery(query, Long.class).stream();
		return stream.findFirst().orElse(0L) + 1;
	}

	@Override
	public boolean supportsJdbcBatchInserts() {
		return false;
	}
}
