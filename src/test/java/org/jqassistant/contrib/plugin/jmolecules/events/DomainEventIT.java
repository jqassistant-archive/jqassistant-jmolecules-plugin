package org.jqassistant.contrib.plugin.jmolecules.events;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import org.jqassistant.contrib.plugin.jmolecules.AbstractJMoleculesPluginIT;
import org.jqassistant.contrib.plugin.jmolecules.set.domainevent.DomainEvent1;
import org.jqassistant.contrib.plugin.jmolecules.set.domainevent.DomainEvent2;
import org.jqassistant.contrib.plugin.jmolecules.set.domainevent.DomainEvent3;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainEventIT extends AbstractJMoleculesPluginIT {

    @Test
    public void domainEventType() throws RuleException {
        scanClasses(DomainEvent1.class);
        assertEquals(Result.Status.SUCCESS, applyConcept("jmolecules-event:DomainEventType").getStatus());
        store.beginTransaction();
        List<TypeDescriptor> types = query("MATCH (t:JMolecules:Event:DomainEvent) RETURN t").getColumn("t");
        assertThat(types.size()).isEqualTo(1);
        assertThat(types.get(0).getName()).isEqualTo("DomainEvent1");
        store.commitTransaction();
    }

    @Test
    public void domainEventExtend() throws RuleException {
        scanClasses(DomainEvent2.class);
        assertEquals(Result.Status.SUCCESS, applyConcept("jmolecules-event:DomainEventExtend").getStatus());
        store.beginTransaction();
        List<TypeDescriptor> types = query("MATCH (t:JMolecules:Event:DomainEvent) RETURN t").getColumn("t");
        assertThat(types.size()).isEqualTo(1);
        assertThat(types.get(0).getName()).isEqualTo("DomainEvent2");
        store.commitTransaction();
    }

    @Test
    public void domainEventPackage() throws RuleException {
        scanClassesAndPackages(DomainEvent3.class);
        assertEquals(Result.Status.SUCCESS, applyConcept("jmolecules-event:DomainEventPackage").getStatus());
        store.beginTransaction();
        List<TypeDescriptor> types = query("MATCH (t:JMolecules:Event:DomainEvent) RETURN t ORDER BY t.name").getColumn("t");
        assertThat(types.size()).isEqualTo(4);
        assertThat(types.get(2).getName()).isEqualTo("DomainEvent3");
        store.commitTransaction();
    }

}
