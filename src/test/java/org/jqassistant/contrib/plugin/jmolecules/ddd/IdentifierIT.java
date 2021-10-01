package org.jqassistant.contrib.plugin.jmolecules.ddd;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.jqassistant.contrib.plugin.jmolecules.set.aggregate.Aggregate1;
import org.jqassistant.contrib.plugin.jmolecules.set.aggregate.Aggregate2;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity1;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdentifierIT extends AbstractJavaPluginIT {

    @Test
    public void identifierExtends() throws RuleException {
        scanClasses(Aggregate2.Aggregate2Identifier.class, Entity2.Entity2Identifier.class);
        assertEquals(Result.Status.SUCCESS, applyConcept("jmolecules-ddd:IdentifierExtend").getStatus());
        store.beginTransaction();
        List<String> types = query("MATCH (t:JMolecules:DDD:Identifier) RETURN t.fqn AS t").getColumn("t");
        assertThat(types.size()).isEqualTo(2);
        assertThat(types).containsExactly(
                "org.jqassistant.contrib.plugin.jmolecules.set.aggregate.Aggregate2$Aggregate2Identifier",
                "org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity2$Entity2Identifier");
        store.commitTransaction();
    }

    @Test
    public void identifiedByFieldAnnotation() throws RuleException {
        scanClasses(Aggregate1.class, Aggregate1.Aggregate1Identifier.class, Entity1.class, Entity1.Entity1Identifier.class);
        assertEquals(Result.Status.SUCCESS, applyConcept("jmolecules-ddd:IdentifiedByFieldAnnotation").getStatus());
        store.beginTransaction();
        List<Map<String, Object>> types = query("MATCH (t:Type)-[:IDENTIFIED_BY]->(identifier:Type) RETURN t.fqn AS t, identifier.fqn AS id ORDER BY t").getRows();
        assertThat(types.size()).isEqualTo(2);
        assertThat(types.get(0).get("t")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.aggregate.Aggregate1");
        assertThat(types.get(0).get("id")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.aggregate.Aggregate1$Aggregate1Identifier");
        assertThat(types.get(1).get("t")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity1");
        assertThat(types.get(1).get("id")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity1$Entity1Identifier");
        store.commitTransaction();
    }

    @Test
    public void identifiedByMethod() throws RuleException {
        scanClasses(Aggregate2.class, Aggregate2.Aggregate2Identifier.class, Entity2.class, Entity2.Entity2Identifier.class);
        assertEquals(Result.Status.SUCCESS, applyConcept("jmolecules-ddd:IdentifiedByMethod").getStatus());
        store.beginTransaction();
        List<Map<String, Object>> types = query("MATCH (t:Type)-[:IDENTIFIED_BY]->(identifier:Type) RETURN t.fqn AS t, identifier.fqn AS id ORDER BY t").getRows();
        assertThat(types.size()).isEqualTo(2);
        assertThat(types.get(0).get("t")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.aggregate.Aggregate2");
        assertThat(types.get(0).get("id")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.aggregate.Aggregate2$Aggregate2Identifier");
        assertThat(types.get(1).get("t")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity2");
        assertThat(types.get(1).get("id")).isEqualTo("org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity2$Entity2Identifier");
        store.commitTransaction();
    }

}
