package pt.scalablesolutions.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "pt.scalablesolutions.Project";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}
