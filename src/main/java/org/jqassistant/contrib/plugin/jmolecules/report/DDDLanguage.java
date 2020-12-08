package org.jqassistant.contrib.plugin.jmolecules.report;

import com.buschmais.jqassistant.core.report.api.SourceProvider;
import com.buschmais.jqassistant.core.report.api.model.Language;
import com.buschmais.jqassistant.core.report.api.model.LanguageElement;
import org.jqassistant.contrib.plugin.jmolecules.descriptor.ddd.BoundedContextDescriptor;
import org.jqassistant.contrib.plugin.jmolecules.descriptor.ddd.ModuleDescriptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the language elements for "DDD"
 */
@Language
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DDDLanguage {

    DDDLanguageElement value();

    enum DDDLanguageElement implements LanguageElement {
        BoundedContext {
            @Override
            public SourceProvider<BoundedContextDescriptor> getSourceProvider() {
                return new SourceProvider<BoundedContextDescriptor>() {
                    @Override
                    public String getName(BoundedContextDescriptor descriptor) {
                        return descriptor.getName();
                    }

                    @Override
                    public String getSourceFile(BoundedContextDescriptor descriptor) {
                        return null;
                    }

                    @Override
                    public Integer getLineNumber(BoundedContextDescriptor descriptor) {
                        return null;
                    }
                };
            }
        },
        Module {
            @Override
            public SourceProvider<ModuleDescriptor> getSourceProvider() {
                return new SourceProvider<ModuleDescriptor>() {
                    @Override
                    public String getName(ModuleDescriptor descriptor) {
                        return descriptor.getName();
                    }

                    @Override
                    public String getSourceFile(ModuleDescriptor descriptor) {
                        return null;
                    }

                    @Override
                    public Integer getLineNumber(ModuleDescriptor descriptor) {
                        return null;
                    }
                };
            }
        };

        @Override
        public String getLanguage() {
            return "DDD";
        }

    }
}
