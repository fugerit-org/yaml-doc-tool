package test.org.fugerit.java.yaml.doc;

import static org.junit.Assert.fail;

import java.util.Properties;

import org.fugerit.java.yaml.doc.YamlDocMain;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestYamlDocConfig {

	private final static Logger logger = LoggerFactory.getLogger( TestYamlDocConfig.class );

	private static final String FILE_ENCODING = "utf-8";

	private static final String CONFIG_FILE = "src/test/resources/yaml-doc-config.xml";

	private static final String ID_CATALOG = "sample";
	
	public void testWorkerSample( String encoding ) {
		System.setProperty( "file.encoding", FILE_ENCODING );
		Properties props = new Properties();
		props.setProperty(YamlDocMain.ARG_MODE, YamlDocMain.ARG_MODE_CONFIG);
		props.setProperty(YamlDocMain.ARG_CONFIG_PATH, CONFIG_FILE);
		props.setProperty(YamlDocMain.ARG_ID_CATALOG, ID_CATALOG);
		try {
			YamlDocMain.worker( props );
			logger.info( "Generation complete! {}" , CONFIG_FILE );
		} catch (Exception e) {
			String message = "Error: "+e.getMessage();
			logger.error( message, e );
			fail( message );
		}
	}
	
	@Test
	public void testSample() {
		this.testWorkerSample( FILE_ENCODING );
	}

}
