package org.jqassistant.contrib.plugin.jmolecules.ddd.constraints;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Constraint;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.FieldDescriptor;
import com.buschmais.jqassistant.plugin.java.api.model.MethodDescriptor;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.assertj.core.api.HamcrestCondition;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.AbstractEntity;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity1;
import org.jqassistant.contrib.plugin.jmolecules.set.entity.Entity3;
import org.jqassistant.contrib.plugin.jmolecules.set.valueobject.ValueObject1;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.buschmais.jqassistant.plugin.java.test.matcher.FieldDescriptorMatcher.fieldDescriptor;
import static com.buschmais.jqassistant.plugin.java.test.matcher.MethodDescriptorMatcher.methodDescriptor;
import static com.buschmais.jqassistant.plugin.java.test.matcher.TypeDescriptorMatcher.typeDescriptor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutableEntityIdIT extends AbstractJavaPluginIT {

    @Test
    public void testMutableIdInEntity() throws RuleException, NoSuchFieldException, NoSuchMethodException {
        scanClasses(Entity1.class);
        Result<Constraint> result = validateConstraint("jmolecules-ddd:MutableEntityId");
        store.beginTransaction();
        List<Map<String, Object>> rows = result.getRows();
        assertThat(rows.size()).isEqualTo(1);
        assertThat((TypeDescriptor) rows.get(0).get("Identifiable")).is(new HamcrestCondition<>(typeDescriptor(Entity1.class)));
        assertThat((FieldDescriptor) rows.get(0).get("MutableIdentity")).is(new HamcrestCondition<>(fieldDescriptor(Entity1.class, "id")));
        assertThat((MethodDescriptor) rows.get(0).get("Method")).is(new HamcrestCondition<>(methodDescriptor(Entity1.class, "setId", Entity1.Entity1Identifier.class)));
        store.commitTransaction();
    }

    @Test
    public void testMutableIdInAbstractEntity() throws RuleException, NoSuchFieldException, NoSuchMethodException {
        scanClasses(AbstractEntity.class, Entity3.class);
        Result<Constraint> result = validateConstraint("jmolecules-ddd:MutableEntityId");
        store.beginTransaction();
        List<Map<String, Object>> rows = result.getRows();
        assertThat(rows.size()).isEqualTo(1);
        assertThat((TypeDescriptor) rows.get(0).get("Identifiable")).is(new HamcrestCondition<>(typeDescriptor(Entity3.class)));
        assertThat((FieldDescriptor) rows.get(0).get("MutableIdentity")).is(new HamcrestCondition<>(fieldDescriptor(AbstractEntity.class, "mutableId")));
        assertThat((MethodDescriptor) rows.get(0).get("Method")).is(new HamcrestCondition<>(methodDescriptor(AbstractEntity.class, "setMutableId", String.class)));
        store.commitTransaction();
    }

}
