package org.jqassistant.contrib.plugin.jmolecules.ddd;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.jqassistant.contrib.plugin.jmolecules.set.valueobject.ValueObject1;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueObjectIT extends AbstractJavaPluginIT {

    @Test
    public void valueObjectType() throws RuleException {
        scanClasses(ValueObject1.class);
        assertThat(applyConcept("jmolecules-ddd:ValueObjectType").getStatus()).isEqualTo(Result.Status.SUCCESS);
        store.beginTransaction();
        List<TypeDescriptor> types = query("MATCH (t:JMolecules:DDD:ValueObject) RETURN t").getColumn("t");
        assertThat(types.size()).isEqualTo(1);
        assertThat(types.get(0).getName()).isEqualTo("ValueObject1");
        store.commitTransaction();
    }

}
