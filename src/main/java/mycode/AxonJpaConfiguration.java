package mycode;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import mycode.axonframework.eventsourcing.eventstore.jpa.JpaTextEventStorageEngine;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.CompactDriver;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AxonJpaConfiguration {
	@Bean
	public JpaEventStorageEngine eventStorageEngine(Serializer eventSerializer,
													Serializer snapShotSerializer,
													DataSource dataSource,
													EntityManagerProvider entityManagerProvider,
													TransactionManager transactionManager) throws SQLException {

		return new JpaTextEventStorageEngine(JpaEventStorageEngine.builder()
				.eventSerializer(eventSerializer)
				.snapshotSerializer(snapShotSerializer)
				.dataSource(dataSource)
				.entityManagerProvider(entityManagerProvider)
				.transactionManager(transactionManager));
	}

	@Bean
	XStream xStream() {
		XStream xStream = new XStream(new CompactDriver());
		xStream.addPermission(NoTypePermission.NONE);
		xStream.setClassLoader(getClass().getClassLoader());
		xStream.allowTypesByWildcard(new String[] {
				"mycode.**",
				"org.axonframework.**",
				"java.**",
				"com.thoughtworks.xstream.**"
		});
		return xStream;
	}

	@Bean
	@Primary
	Serializer serializer(XStream xStream) {
		return XStreamSerializer.builder().xStream(xStream).build();
	}

}
