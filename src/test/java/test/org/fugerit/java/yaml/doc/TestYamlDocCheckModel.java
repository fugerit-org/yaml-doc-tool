package test.org.fugerit.java.yaml.doc;

import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.util.result.Result;
import org.fugerit.java.yaml.doc.YamlDocCheckModel;
import org.fugerit.java.yaml.doc.YamlDocMain;
import org.junit.Assert;
import org.junit.Test;

import io.swagger.client.model.SampleResult;

public class TestYamlDocCheckModel {

	@Test
	public void testOk() throws ConfigException {
		Properties params = new Properties();
		params.setProperty( YamlDocMain.ARG_INPUT_YAML , "src/test/resources/sample/sample.yaml" );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_SCHEMA , SampleResult.class.getSimpleName() );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_TYPE , SampleResult.class.getName() );
		params.setProperty( YamlDocMain.ARG_OUTPUT_FILE , "target/report.md" );
		int res = YamlDocCheckModel.handleModelCheck(params);
		Assert.assertEquals( Result.RESULT_CODE_OK , res );
	}
	
	@Test
	public void testKo() throws ConfigException {
		Properties params = new Properties();
		params.setProperty( YamlDocMain.ARG_INPUT_YAML , "src/test/resources/sample/sample_check1.yaml" );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_SCHEMA , SampleResult.class.getSimpleName() );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_TYPE , SampleResult.class.getName() );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_ONCE , BooleanUtils.BOOLEAN_TRUE );
		params.setProperty( YamlDocCheckModel.ARG_PRINT_ONLY_KO , BooleanUtils.BOOLEAN_TRUE );
		int res = YamlDocCheckModel.handleModelCheck(params);
		Assert.assertNotEquals( Result.RESULT_CODE_OK , res );
	}
	
	@Test
	public void testKo2() throws ConfigException {
		Properties params = new Properties();
		params.setProperty( YamlDocMain.ARG_INPUT_YAML , "src/test/resources/sample/sample_check2.yaml" );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_SCHEMA , SampleResult.class.getSimpleName() );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_TYPE , SampleResult.class.getName() );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_ONCE , BooleanUtils.BOOLEAN_TRUE );
		params.setProperty( YamlDocCheckModel.ARG_PRINT_ONLY_KO , BooleanUtils.BOOLEAN_TRUE );
		int res = YamlDocCheckModel.handleModelCheck(params);
		Assert.assertNotEquals( Result.RESULT_CODE_OK , res );
	}
	
	@Test
	public void testNoParam() throws ConfigException {
		Properties params = new Properties();
		Assert.assertThrows( ConfigException.class , () -> YamlDocCheckModel.handleModelCheck(params) );
		params.setProperty( YamlDocMain.ARG_INPUT_YAML , "src/test/resources/sample/sample_check1.yaml" );
		Assert.assertThrows( ConfigException.class , () -> YamlDocCheckModel.handleModelCheck(params) );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_TYPE , "not found" );
		Assert.assertThrows( ConfigException.class , () -> YamlDocCheckModel.handleModelCheck(params) );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_SCHEMA , SampleResult.class.getSimpleName() );
		Assert.assertThrows( ConfigException.class , () -> YamlDocCheckModel.handleModelCheck(params) );
		params.setProperty( YamlDocCheckModel.ARG_CHECK_SCHEMA , "not found" );
		Assert.assertThrows( ConfigException.class , () -> YamlDocCheckModel.handleModelCheck(params) );
		
	}
	
	@Test
	public void testMain() {
		String[] args = {
				ArgUtils.getArgString( YamlDocMain.ARG_MODE ), YamlDocMain.ARG_MODE_CHECK_MODEL,
				ArgUtils.getArgString( YamlDocMain.ARG_INPUT_YAML ), "src/test/resources/sample/sample.yaml",
				ArgUtils.getArgString( YamlDocCheckModel.ARG_CHECK_SCHEMA ), SampleResult.class.getSimpleName(),
				ArgUtils.getArgString( YamlDocCheckModel.ARG_CHECK_TYPE ), SampleResult.class.getName(),
		};
		YamlDocMain.main(args);
		Assert.assertNotNull( args );
	}
	
}
