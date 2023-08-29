package org.fugerit.java.yaml.doc;

import org.fugerit.java.doc.freemarker.process.FreemarkerDocProcessConfig;
import org.fugerit.java.doc.freemarker.process.FreemarkerDocProcessConfigFacade;

public class FjDocFacade {

	private FjDocFacade() {}

	private static final FreemarkerDocProcessConfig INSTANCE = 
			FreemarkerDocProcessConfigFacade.loadConfigSafe( "cl://doc-facade/fm-process-config-yaml.xml" );
	
	public static FreemarkerDocProcessConfig getInstance() {
		return INSTANCE;
	}

}
