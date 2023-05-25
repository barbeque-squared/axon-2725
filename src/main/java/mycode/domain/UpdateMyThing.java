package mycode.domain;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateMyThing(@TargetAggregateIdentifier String id, String someAttribute) {}
