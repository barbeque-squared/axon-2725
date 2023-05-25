package mycode.axonframework.eventsourcing.eventstore.jpa;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;

public class JpaTextEventStorageEngine extends JpaEventStorageEngine {
    public JpaTextEventStorageEngine(Builder builder) {
        super(builder);
    }

    @Override
    protected Object createEventEntity(EventMessage<?> eventMessage, Serializer serializer) {
        return new DomainEventEntry(asDomainEventMessage(eventMessage), serializer);
    }

    @Override
    protected String domainEventEntryEntityName() {
        return DomainEventEntry.class.getSimpleName();
    }

}
