package org.fugerit.java.yaml.doc;

import org.fugerit.java.doc.base.facade.ProcessDocFacade;

public class FjDocFacade {

	public final static String PATH_DOC_PROCESS_CONFIG = "cl://doc-facade/doc-process-yaml.xml";
	
	public final static String PATH_DOC_HANDLER_FACTORY_CONFIG = "cl://doc-facade/doc-handler-yaml.xml";
	
	public final static String CATALOG_ID = "yaml-default";
	
	private static ProcessDocFacade init() {
		ProcessDocFacade facade = null;
		try {
			facade = ProcessDocFacade.newFacade( PATH_DOC_PROCESS_CONFIG, PATH_DOC_HANDLER_FACTORY_CONFIG, CATALOG_ID );
		} catch (Exception e) {
			throw new RuntimeException( e );
		}
		return facade;
	}
	
	private static ProcessDocFacade INSTANCE = init();

	public static ProcessDocFacade getInstance() {
		return INSTANCE;
	}

}
