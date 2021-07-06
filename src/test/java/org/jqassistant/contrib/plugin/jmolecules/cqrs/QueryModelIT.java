package org.jqassistant.contrib.plugin.jmolecules.cqrs;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.jqassistant.contrib.plugin.jmolecules.set.cqrs.QueryModel1;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity1;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryModelIT extends AbstractJavaPluginIT {

    @Test
    public void queryModel() throws RuleException {
        scanClasses(QueryModel1.class);
        assertEquals(Result.Status.SUCCESS, applyConcept("jmolecules-cqrs:QueryModel").getStatus());
        store.beginTransaction();
        List<TypeDescriptor> types = query("MATCH (t:JMolecules:CQRS:QueryModel) RETURN t").getColumn("t");
        assertThat(types.size()).isEqualTo(1);
        assertThat(types.get(0).getName()).isEqualTo("QueryModel1");
        store.commitTransaction();
    }

}
