package runner;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.internal.runners.statements.RunBefores;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import static java.lang.String.format;

/**
 * JUnit runner that load from files in classpath content add inject it to fields.
 * Test class should have two fields thar names're "source" and "expected".
 * Runner find files with names that corresponds to testMethod name and look like that pattern
 * [testMethod].[extension]
 * Where extension is string that takes such values:
 * ex - for expected field
 * src - for source field
 */
public class Runner extends BlockJUnit4ClassRunner {

    public Runner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Statement methodBlock(FrameworkMethod method) {
        try {
            Statement create = super.methodBlock(method);
            Statement statement = create;
            if (statement instanceof RunBefores) {
                statement = (Statement) getFieldValue(statement, "fNext");
            }
            if (statement instanceof InvokeMethod) {
                FrameworkMethod fMethod = (FrameworkMethod) getFieldValue(statement, "fTestMethod");

                Object test = getFieldValue(statement, "fTarget");

                setFieldValue(test, loadSource(fMethod.getName()), "source");
                setFieldValue(test, loadExpected(fMethod.getName()), "expected");
            }
            return create;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String loadSource(String name) throws IOException {
        return readResource(name + ".src");
    }

    private String loadExpected(String testName) throws IOException {
        return readResource(testName + ".ex");
    }

    private String readResource(String name) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(name);
        if (is == null) {
            System.err.println(format("File with name \"%s\" is not found.", name));
            return "";
        }
        byte[] bytes = new byte[0xff];
        StringBuilder stringBuilder = new StringBuilder();
        for (int readen; (readen = is.read(bytes)) != -1; ) {
            stringBuilder.append(new String(bytes, 0, readen));
        }
        return stringBuilder.toString();
    }

    @Override
    protected Object createTest() throws Exception {
        System.out.println();
        return super.createTest();
    }

    private void setFieldValue(Object obj, Object value, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(obj, value);
    }

    private Object getFieldValue(Object obj, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return f.get(obj);
    }
}
