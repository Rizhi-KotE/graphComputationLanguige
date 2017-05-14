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
 * JUnit runner that load from files in classpath and injects its content into fields.
 * Test class should have two fields which names are "source" and "expected".
 * Runner find files with names that corresponds into testMethod name and look like that pattern
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
    protected Object createTest() throws Exception {
        System.out.println();
        return super.createTest();
    }

    @Override
    protected Statement methodBlock(FrameworkMethod method) {
        Statement create = super.methodBlock(method);
        try {
            Statement statement = create;
            if (statement instanceof RunBefores) {
                statement = (Statement) getFieldValue(statement, "fNext");
            }
            if (statement instanceof InvokeMethod) {
                FrameworkMethod fMethod = (FrameworkMethod) getFieldValue(statement, "fTestMethod");

                Object test = getFieldValue(statement, "fTarget");

                String canonicalName = test.getClass().getCanonicalName();

                String filePlace = format("%s.%s", canonicalName, fMethod.getName());
                setFieldValue(test, loadSource(filePlace), "source");
                setFieldValue(test, loadExpected(filePlace), "expected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return create;
    }

    private Object getFieldValue(Object obj, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return f.get(obj);
    }

    private void setFieldValue(Object obj, Object value, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(obj, value);
    }

    private String loadSource(String name) throws IOException {
        String replace = name.replace(".", "/");
        return readResource(replace + ".src");
    }

    private String loadExpected(String name) throws IOException {
        String replace = name.replace(".", "/");
        return readResource(replace + ".ex");
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
}
