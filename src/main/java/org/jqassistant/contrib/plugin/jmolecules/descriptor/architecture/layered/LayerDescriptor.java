package org.jqassistant.contrib.plugin.jmolecules.descriptor.architecture.layered;

import com.buschmais.xo.neo4j.api.annotation.Label;
import org.jqassistant.contrib.plugin.jmolecules.report.ArchitectureLanguage;

import static org.jqassistant.contrib.plugin.jmolecules.report.ArchitectureLanguage.ArchitectureLanguageElement.Layer;

@ArchitectureLanguage(Layer)
@Label("Layer")
public interface LayerDescriptor extends LayeredDescriptor {

    String getName();

}
