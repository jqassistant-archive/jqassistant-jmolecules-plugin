package org.jqassistant.contrib.plugin.jmolecules.events;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.jqassistant.contrib.plugin.jmolecules.set.domainevent.DomainEvent1;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainEventIT extends AbstractJavaPluginIT {

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

}
