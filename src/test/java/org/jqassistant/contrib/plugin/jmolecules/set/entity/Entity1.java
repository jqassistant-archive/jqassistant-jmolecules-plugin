package org.jqassistant.contrib.plugin.jmolecules.set.entity;

import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;
import org.jmolecules.ddd.types.Identifier;

@Entity
public class Entity1 {

    @Identity
    Entity1Identifier id;

    public static class Entity1Identifier implements Identifier {

    }
}
