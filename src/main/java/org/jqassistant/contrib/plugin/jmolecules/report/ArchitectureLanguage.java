package org.jqassistant.contrib.plugin.jmolecules.report;

import com.buschmais.jqassistant.core.report.api.SourceProvider;
import com.buschmais.jqassistant.core.report.api.model.Language;
import com.buschmais.jqassistant.core.report.api.model.LanguageElement;
import org.jqassistant.contrib.plugin.jmolecules.descriptor.architecture.layered.LayerDescriptor;
import org.jqassistant.contrib.plugin.jmolecules.descriptor.architecture.onion.OnionDescriptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the language elements for "Architecture"
 */
@Language
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ArchitectureLanguage {

    ArchitectureLanguageElement value();

    enum ArchitectureLanguageElement implements LanguageElement {

        Layer {
            @Override
            public SourceProvider<LayerDescriptor> getSourceProvider() {
                return new SourceProvider<LayerDescriptor>() {
                    @Override
                    public String getName(LayerDescriptor descriptor) {
                        return descriptor.getName();
                    }

                    @Override
                    public String getSourceFile(LayerDescriptor descriptor) {
                        return null;
                    }

                    @Override
                    public Integer getLineNumber(LayerDescriptor descriptor) {
                        return null;
                    }
                };
            }
        },
        Onion {
            @Override
            public SourceProvider<OnionDescriptor> getSourceProvider() {
                return new SourceProvider<OnionDescriptor>() {
                    @Override
                    public String getName(OnionDescriptor descriptor) {
                        return descriptor.getName();
                    }

                    @Override
                    public String getSourceFile(OnionDescriptor descriptor) {
                        return null;
                    }

                    @Override
                    public Integer getLineNumber(OnionDescriptor descriptor) {
                        return null;
                    }
                };
            }
        };

        @Override
        public String getLanguage() {
            return "Architecture";
        }

    }
}
