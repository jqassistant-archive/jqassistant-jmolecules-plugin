package org.jqassistant.contrib.plugin.jmolecules.ddd.constraints;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Constraint;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.assertj.core.api.HamcrestCondition;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity1;
import org.jqassistant.contrib.plugin.jmolecules.set.valueobject.ValueObject1;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.buschmais.jqassistant.plugin.java.test.matcher.TypeDescriptorMatcher.typeDescriptor;
import static org.assertj.core.api.Assertions.assertThat;

public class ValueObjectReferencingEntityOrAggregateRootIT extends AbstractJavaPluginIT {

    @Test
    public void testValueObjectReferencingEntity() throws RuleException {
        scanClasses(ValueObject1.class, Entity1.class);
        Result<Constraint> result = validateConstraint("jmolecules-ddd:ValueObjectReferencingEntityOrAggregateRoot");
        store.beginTransaction();
        List<Map<String, Object>> rows = result.getRows();
        assertThat(rows.size()).isEqualTo(1);
        assertThat((TypeDescriptor) rows.get(0).get("ValueObject")).is(new HamcrestCondition<>(typeDescriptor(ValueObject1.class)));
        assertThat((TypeDescriptor) rows.get(0).get("Identifiable")).is(new HamcrestCondition<>(typeDescriptor(Entity1.class)));
        store.commitTransaction();
    }

}
