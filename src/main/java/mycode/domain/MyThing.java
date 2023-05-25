package mycode.domain;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static lombok.AccessLevel.PRIVATE;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = PRIVATE)
public class MyThing {
    @AggregateIdentifier
    private String id;
    private String theAttribute;

    @CommandHandler
    public MyThing(CreateMyThing command) {
        apply(new MyThingCreated(command.id(), command.someAttribute()));
    }

    @EventSourcingHandler
    public void on(MyThingCreated event) {
        id = event.id();
        theAttribute = event.someAttribute();
    }

    @CommandHandler
    public void on(UpdateMyThing command) {
        apply(new MyThingUpdated(command.id(), command.someAttribute()));
    }

    @EventSourcingHandler
    public void on(MyThingUpdated event) {
        theAttribute = event.someAttribute();
    }
}
