package com.yelstream.topp.util.uuid;

import java.util.UUID;

/**
 * .
 * 
 * @author Morten Sabroe Mortensen
 */
public class JDKRandomUUIDFactory extends AbstractRandomUUIDFactory {
    @Override
    public UUID createUUID() {
        return UUID.randomUUID();
    }
}
