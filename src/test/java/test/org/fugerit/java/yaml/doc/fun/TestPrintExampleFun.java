package test.org.fugerit.java.yaml.doc.fun;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.yaml.doc.fun.PrintExampleFun;
import org.junit.Assert;
import org.junit.Test;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import test.org.fugerit.java.BasicTest;

@Slf4j
public class TestPrintExampleFun extends BasicTest {

	private boolean worker( Object param, String expected ) {
		PrintExampleFun fun = new PrintExampleFun();
		List<Object> arguments = new ArrayList<Object>();
		if ( param != null ) {
			arguments.add( param );
		}
		boolean ok = false;
		try {
			Object result = fun.exec(arguments);
			log.info( "result {}", result );
			if ( result instanceof SimpleScalar ) {
				SimpleScalar value = (SimpleScalar)result;
				ok = value.getAsString().equals( expected );
			}
		} catch (TemplateModelException e) {
			this.failEx(e);
		}
		return ok;
	}
	
	@Test
	public void testNoArgs() {
		boolean ok = this.worker( null, "" );
		Assert.assertTrue( ok );
	}

	@Test
	public void testTrue() {
		boolean ok = this.worker( TemplateBooleanModel.TRUE, "true" );
		Assert.assertTrue( ok );
	}
	
	@Test
	public void testFalse() {
		boolean ok = this.worker( TemplateBooleanModel.FALSE, "false" );
		Assert.assertTrue( ok );
	}
	
	@Test
	public void testSimpleValue() {
		boolean ok = this.worker( "Simple", "Simple" );
		Assert.assertTrue( ok );
	}
	
}
