package org.jqassistant.contrib.plugin.jmolecules.ddd.constraints;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Constraint;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.FieldDescriptor;
import com.buschmais.jqassistant.plugin.java.api.model.MethodDescriptor;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.assertj.core.api.HamcrestCondition;
import org.jqassistant.contrib.plugin.jmolecules.set.valueobject.AbstractValueObject;
import org.jqassistant.contrib.plugin.jmolecules.set.valueobject.ValueObject1;
import org.jqassistant.contrib.plugin.jmolecules.set.valueobject.ValueObject3;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.buschmais.jqassistant.plugin.java.test.matcher.FieldDescriptorMatcher.fieldDescriptor;
import static com.buschmais.jqassistant.plugin.java.test.matcher.MethodDescriptorMatcher.methodDescriptor;
import static com.buschmais.jqassistant.plugin.java.test.matcher.TypeDescriptorMatcher.typeDescriptor;
import static org.assertj.core.api.Assertions.assertThat;

public class NonFinalFieldInValueObjectIT extends AbstractJavaPluginIT {

    @Test
    public void testNonFinalFieldsInValueObjects() throws RuleException, NoSuchFieldException {
        scanClasses(ValueObject1.class);
        Result<Constraint> result = validateConstraint("jmolecules-ddd:NonFinalFieldInValueObject");
        store.beginTransaction();
        List<Map<String, Object>> rows = result.getRows();
        assertThat(rows.size()).isEqualTo(2);
        assertThat((TypeDescriptor) rows.get(0).get("ValueObject")).is(new HamcrestCondition<>(typeDescriptor(ValueObject1.class)));
        assertThat((FieldDescriptor) rows.get(0).get("MutableField")).is(new HamcrestCondition<>(fieldDescriptor(ValueObject1.class, "effectivelImmutableValue")));
        assertThat((TypeDescriptor) rows.get(1).get("ValueObject")).is(new HamcrestCondition<>(typeDescriptor(ValueObject1.class)));
        assertThat((FieldDescriptor) rows.get(1).get("MutableField")).is(new HamcrestCondition<>(fieldDescriptor(ValueObject1.class, "mutableValue")));
        store.commitTransaction();
    }

    @Test
    public void testNonFinalFieldsInAbstractValueObjects() throws RuleException, NoSuchFieldException {
        scanClasses(AbstractValueObject.class, ValueObject3.class);
        Result<Constraint> result = validateConstraint("jmolecules-ddd:NonFinalFieldInValueObject");
        store.beginTransaction();
        List<Map<String, Object>> rows = result.getRows();
        assertThat(rows.size()).isEqualTo(2);
        assertThat((TypeDescriptor) rows.get(0).get("ValueObject")).is(new HamcrestCondition<>(typeDescriptor(ValueObject3.class)));
        assertThat((FieldDescriptor) rows.get(0).get("MutableField")).is(new HamcrestCondition<>(fieldDescriptor(AbstractValueObject.class, "field")));
        assertThat((TypeDescriptor) rows.get(1).get("ValueObject")).is(new HamcrestCondition<>(typeDescriptor(ValueObject3.class)));
        assertThat((FieldDescriptor) rows.get(1).get("MutableField")).is(new HamcrestCondition<>(fieldDescriptor(AbstractValueObject.class, "effectivelyFinalField")));
        store.commitTransaction();
    }

}
