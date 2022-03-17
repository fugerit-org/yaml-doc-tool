package test.org.fugerit.java.yaml.doc;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.Reader;

import org.fugerit.java.doc.base.config.DocConfig;
import org.fugerit.java.yaml.doc.YamlDocConfig;
import org.fugerit.java.yaml.doc.YamlDocFacade;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestYamlDocFacade {

	protected final static Logger logger = LoggerFactory.getLogger( TestYamlDocFacade.class );
	
	@Test
	public void testSample() {
		String outputFormat = DocConfig.TYPE_PDF;		
		try ( Reader reader = new FileReader( "src/test/resources/sample/sample.yaml" );
				OutputStream os = new FileOutputStream( new File( "target/sample."+outputFormat ) )) {
			YamlDocConfig config = new YamlDocConfig( outputFormat );
			YamlDocFacade facade = new YamlDocFacade();
			int result = facade.handle(reader, os, config);
			logger.info( "result -> {}", result );
		} catch (Exception e) {
			String message = "Error: "+e.getMessage();
			logger.error( message, e );
			fail( message );
		}
	}
}
