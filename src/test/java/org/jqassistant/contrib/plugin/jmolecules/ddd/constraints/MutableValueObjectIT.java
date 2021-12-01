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

public class MutableValueObjectIT extends AbstractJavaPluginIT {

    @Test
    public void testMutableFieldsInValueObjects() throws RuleException, NoSuchFieldException, NoSuchMethodException {
        scanClasses(ValueObject1.class);
        Result<Constraint> result = validateConstraint("jmolecules-ddd:MutableValueObject");
        store.beginTransaction();
        List<Map<String, Object>> rows = result.getRows();
        assertThat(rows.size()).isEqualTo(1);
        assertThat((TypeDescriptor) rows.get(0).get("ValueObject")).is(new HamcrestCondition<>(typeDescriptor(ValueObject1.class)));
        assertThat((FieldDescriptor) rows.get(0).get("MutableField")).is(new HamcrestCondition<>(fieldDescriptor(ValueObject1.class, "mutableValue")));
        assertThat((MethodDescriptor) rows.get(0).get("Method")).is(new HamcrestCondition<>(methodDescriptor(ValueObject1.class, "setMutableValue", String.class)));
        store.commitTransaction();
    }

    @Test
    public void testMutableFieldsInAbstractValueObjects() throws RuleException, NoSuchFieldException, NoSuchMethodException {
        scanClasses(AbstractValueObject.class, ValueObject3.class);
        Result<Constraint> result = validateConstraint("jmolecules-ddd:MutableValueObject");
        store.beginTransaction();
        List<Map<String, Object>> rows = result.getRows();
        assertThat(rows.size()).isEqualTo(1);
        assertThat((TypeDescriptor) rows.get(0).get("ValueObject")).is(new HamcrestCondition<>(typeDescriptor(ValueObject3.class)));
        assertThat((FieldDescriptor) rows.get(0).get("MutableField")).is(new HamcrestCondition<>(fieldDescriptor(AbstractValueObject.class, "field")));
        assertThat((MethodDescriptor) rows.get(0).get("Method")).is(new HamcrestCondition<>(methodDescriptor(AbstractValueObject.class, "setField", String.class)));
        store.commitTransaction();
    }

}
