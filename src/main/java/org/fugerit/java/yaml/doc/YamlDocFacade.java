package org.fugerit.java.yaml.doc;

import java.io.OutputStream;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.fugerit.java.core.util.result.Result;
import org.fugerit.java.doc.base.facade.ProcessDocFacade;
import org.fugerit.java.doc.base.process.DocProcessContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class YamlDocFacade {

	private static final Logger logger = LoggerFactory.getLogger( YamlDocFacade.class );
	
	@SuppressWarnings("unchecked")
	public int handle( Reader inputYaml, OutputStream outputData, YamlDocConfig config ) throws Exception  {
		int result = Result.RESULT_CODE_OK;
		Yaml yaml = new Yaml();
		Map<String, Object> fullYaml = yaml.load( inputYaml );
		Map<String, Object> components = (Map<String, Object>)fullYaml.get( "components" );
		Map<String, Object> schemas = (Map<String, Object>)components.get( "schemas" );
		// labels - start
		Properties labels = new Properties();
		ResourceBundle bundleLabels = ResourceBundle.getBundle( "lang.label", config.getLocale() );
		Enumeration<String> keys = bundleLabels.getKeys();
		while ( keys.hasMoreElements() ) {
			String k = keys.nextElement();
			labels.setProperty( k , bundleLabels.getString( k ) );
		}
		// labels - end
		YamlModel yamlModel = new YamlModel();
		yamlModel.setSchemas( schemas );
		yamlModel.setLabels( labels );
		ProcessDocFacade docFacade = FjDocFacade.getInstance();
		logger.info( "docFacade -> {}", docFacade );
		DocProcessContext context = DocProcessContext.newContext( YamlModel.ATT_NAME, yamlModel );
		docFacade.process( "yaml-doc-template", config.getOutputFormat(), context, outputData ); 
		return result;
	}
	
}
