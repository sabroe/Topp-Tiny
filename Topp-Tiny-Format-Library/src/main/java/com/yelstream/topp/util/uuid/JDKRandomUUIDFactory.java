package com.yelstream.topp.util.uuid;

import java.util.UUID;

/**
 * .
 *
 * @author Morten Sabroe Mortensen
 * @version 1.0
 * @since 2013-10-21
 */
public class JDKRandomUUIDFactory implements UUIDFactory {
    @Override
    public UUID createUUID() {
        return UUID.randomUUID();
    }
}
