package ldap.ssh;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SSHConnectionTest {

    SSHConnection conn;
    
    @Before
    public void setUp() throws Exception {
        conn = new SSHConnection();
        conn.connectAndAuthenticate();
    }

    @After
    public void tearDown() throws Exception {
        conn.connectionClose();
    }

    @Test
    public void testTransferLdiffAndExecuteCommand() {
        conn.transferLdiffAndExecuteCommand("Esto es una prueba");
    }

}
