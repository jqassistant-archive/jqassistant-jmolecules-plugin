package org.jqassistant.contrib.plugin.jmolecules.set.aggregate;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

public class Aggregate2 implements AggregateRoot<Aggregate2, Aggregate2.Aggregate2Identifier> {
    @Override
    public Aggregate2Identifier getId() {
        return null;
    }

    class Aggregate2Identifier implements Identifier {

    }
}
