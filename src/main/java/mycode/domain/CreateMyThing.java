package mycode.domain;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateMyThing(@TargetAggregateIdentifier String id, String someAttribute) {}
