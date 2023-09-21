package test.org.fugerit.java.yaml.doc;

import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.yaml.doc.YamlDocMain;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestYamlDocConfig {

	private final static Logger logger = LoggerFactory.getLogger( TestYamlDocConfig.class );

	private static final String FILE_ENCODING = "utf-8";

	private static final String CONFIG_FILE = "src/test/resources/yaml-doc-config.xml";

	private static final String ID_CATALOG = "sample";
	
	private static final String ID_CATALOG_IT = "sample_it";
	
	public boolean testWorkerSample( String encoding, String idCatalog ) {
		return SafeFunction.get( () -> {
			boolean ok = false;
			System.setProperty( "file.encoding", FILE_ENCODING );
			Properties props = new Properties();
			props.setProperty(YamlDocMain.ARG_MODE, YamlDocMain.ARG_MODE_CONFIG);
			props.setProperty(YamlDocMain.ARG_CONFIG_PATH, CONFIG_FILE);
			props.setProperty(YamlDocMain.ARG_ID_CATALOG, idCatalog);
			YamlDocMain.worker( props );
			logger.info( "Generation complete! {}" , CONFIG_FILE );
			ok = true;
			return ok;
		} );
	}
	
	@Test
	public void testSample() {
		boolean ok = this.testWorkerSample( FILE_ENCODING, ID_CATALOG );
		Assert.assertTrue( ok );
	}

	@Test
	public void testSampleIt() {
		boolean ok = this.testWorkerSample( FILE_ENCODING, ID_CATALOG_IT );
		Assert.assertTrue( ok );
	}
	
	@Test
	public void testSampleFail1() {
		Assert.assertThrows( ConfigRuntimeException.class , () -> this.testWorkerSample( FILE_ENCODING, null ) );
	}
	
	@Test
	public void testSampleFail2() {
		Properties props = new Properties();
		props.setProperty(YamlDocMain.ARG_MODE, YamlDocMain.ARG_MODE_SINGLE );
		Assert.assertThrows( ConfigException.class , () -> YamlDocMain.worker( props ) );
	}
	
	@Test
	public void testSampleFail3() {
		Properties props = new Properties();
		props.setProperty(YamlDocMain.ARG_MODE, YamlDocMain.ARG_MODE_SINGLE );
		props.setProperty(YamlDocMain.ARG_INPUT_YAML, "src/test/resources/sample/sample_01.yaml" );
		Assert.assertThrows( ConfigException.class , () -> YamlDocMain.worker( props ) );
	}

}
