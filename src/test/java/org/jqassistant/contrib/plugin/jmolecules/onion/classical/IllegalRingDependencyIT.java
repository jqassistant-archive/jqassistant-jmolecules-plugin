package org.jqassistant.contrib.plugin.jmolecules.onion.classical;

import com.buschmais.jqassistant.core.report.api.model.Result;
import com.buschmais.jqassistant.core.rule.api.model.Constraint;
import com.buschmais.jqassistant.core.rule.api.model.RuleException;
import com.buschmais.jqassistant.plugin.java.api.model.TypeDescriptor;
import com.buschmais.jqassistant.plugin.java.test.AbstractJavaPluginIT;
import org.assertj.core.api.HamcrestCondition;
import org.jqassistant.contrib.plugin.jmolecules.set.ring.classical.ClassicalRingApp;
import org.jqassistant.contrib.plugin.jmolecules.set.ring.classical.applicationservice.ApplicationService1;
import org.jqassistant.contrib.plugin.jmolecules.set.ring.classical.domainservice.DomainService1;
import org.jqassistant.contrib.plugin.jmolecules.set.ring.classical.domainsmodel.DomainModel1;
import org.jqassistant.contrib.plugin.jmolecules.set.ring.classical.infrastructure.Infrastructure1;
import org.jqassistant.contrib.plugin.jmolecules.set.violation.layer1.Layer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.buschmais.jqassistant.plugin.java.test.matcher.TypeDescriptorMatcher.typeDescriptor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IllegalRingDependencyIT extends AbstractJavaPluginIT {

    @Test
    public void testIllegalRingDependencies() throws RuleException {
        scanClassPathDirectory(getClassesDirectory(ClassicalRingApp.class));
        Result<Constraint> result = validateConstraint("jmolecules-onion-classical:IllegalRingDependency");
        List<Map<String, Object>> rows = result.getRows();
        store.beginTransaction();
        assertEquals(6, rows.size());
        assertThat((TypeDescriptor) rows.get(0).get("SourceType")).is(new HamcrestCondition<>(typeDescriptor(ApplicationService1.class)));
        assertThat((TypeDescriptor) rows.get(0).get("TargetType")).is(new HamcrestCondition<>(typeDescriptor(Infrastructure1.class)));

        assertThat((TypeDescriptor) rows.get(1).get("SourceType")).is(new HamcrestCondition<>(typeDescriptor(DomainModel1.class)));
        assertThat((TypeDescriptor) rows.get(1).get("TargetType")).is(new HamcrestCondition<>(typeDescriptor(ApplicationService1.class)));
        assertThat((TypeDescriptor) rows.get(2).get("SourceType")).is(new HamcrestCondition<>(typeDescriptor(DomainModel1.class)));
        assertThat((TypeDescriptor) rows.get(2).get("TargetType")).is(new HamcrestCondition<>(typeDescriptor(DomainService1.class)));
        assertThat((TypeDescriptor) rows.get(3).get("SourceType")).is(new HamcrestCondition<>(typeDescriptor(DomainModel1.class)));
        assertThat((TypeDescriptor) rows.get(3).get("TargetType")).is(new HamcrestCondition<>(typeDescriptor(Infrastructure1.class)));

        assertThat((TypeDescriptor) rows.get(4).get("SourceType")).is(new HamcrestCondition<>(typeDescriptor(DomainService1.class)));
        assertThat((TypeDescriptor) rows.get(4).get("TargetType")).is(new HamcrestCondition<>(typeDescriptor(ApplicationService1.class)));
        assertThat((TypeDescriptor) rows.get(5).get("SourceType")).is(new HamcrestCondition<>(typeDescriptor(DomainService1.class)));
        assertThat((TypeDescriptor) rows.get(5).get("TargetType")).is(new HamcrestCondition<>(typeDescriptor(Infrastructure1.class)));
        store.commitTransaction();
    }

}
