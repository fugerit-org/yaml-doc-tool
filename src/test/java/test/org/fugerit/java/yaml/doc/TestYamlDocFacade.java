package test.org.fugerit.java.yaml.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Locale;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.doc.base.config.DocConfig;
import org.fugerit.java.yaml.doc.YamlDocConfig;
import org.fugerit.java.yaml.doc.YamlDocFacade;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestYamlDocFacade {

    private final static Logger logger = LoggerFactory.getLogger(TestYamlDocFacade.class);

    private static final Locale LOCALE_EN = Locale.ENGLISH;

    private static final Locale LOCALE_IT = Locale.ITALIAN;

    private static final String FILE_ENCODING = "utf-8";

    public boolean testWorkerSample(Locale locale, String encoding, String... outputFormats) {
        return SafeFunction.get(() -> {
            boolean ok = false;
            System.setProperty("file.encoding", encoding);
            for (String outputFormat : outputFormats) {
                try (Reader reader = new FileReader("src/test/resources/sample/sample.yaml");
                        OutputStream os = new FileOutputStream(
                                new File("target/sample_facade_" + locale + "." + outputFormat))) {
                    YamlDocConfig config = new YamlDocConfig(outputFormat);
                    config.setLocale(locale);
                    config.setLabelsOverride(PropsIO.loadFromClassLoader("sample/sample-label-override.properties"));
                    config.setExcelTryAutoresize(true);
                    YamlDocFacade facade = new YamlDocFacade();
                    int result = facade.handle(reader, os, config);
                    logger.info("result -> {}", result);
                    ok = true;
                }
            }
            return ok;
        });
    }

    @Test
    public void testSampleEN() {
        boolean ok = this.testWorkerSample(LOCALE_EN, FILE_ENCODING, DocConfig.TYPE_XML, DocConfig.TYPE_FO, DocConfig.TYPE_PDF,
                DocConfig.TYPE_XLSX);
        Assert.assertTrue(ok);
    }

    @Test
    public void testSampleIT() {
        boolean ok = this.testWorkerSample(LOCALE_IT, FILE_ENCODING, DocConfig.TYPE_XML, DocConfig.TYPE_FO, DocConfig.TYPE_PDF,
                DocConfig.TYPE_XLSX);
        Assert.assertTrue(ok);
    }

}
