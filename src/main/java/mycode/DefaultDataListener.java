package mycode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mycode.domain.CreateMyThing;
import mycode.domain.UpdateMyThing;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("development")
public class DefaultDataListener implements ApplicationListener<ApplicationStartedEvent>  {
    private final CommandGateway commandGateway;

    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        log.info("it reaches this");
        var id = UUID.randomUUID().toString();
        commandGateway.sendAndWait(new CreateMyThing(id, "initialValue"));
        // the next line will trigger the error
        commandGateway.sendAndWait(new UpdateMyThing(id, "updatedValue"));
    }
}
