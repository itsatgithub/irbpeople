package ldap.ssh;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import bussineslogic.controlers.UseCase;
import bussineslogic.excepciones.InternalException;
import bussineslogic.excepciones.ValidationFailedException;

import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import com.trilead.ssh2.Connection;
import com.trilead.ssh2.InteractiveCallback;
import com.trilead.ssh2.SFTPv3Client;
import com.trilead.ssh2.SFTPv3FileHandle;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

public class SSHConnection {

    private static ResourceBundle bundle = ResourceBundle.getBundle("MainConfiguration");

    private static org.apache.log4j.Category log = org.apache.log4j.Logger.getLogger(SSHConnection.class);

    private Connection conn;

    private boolean active=false;
    
    public SSHConnection() {

        String sshActive = bundle.getString("sshActive");
        
        if(sshActive.equals("yes")) active=true;
        
        if(active)
        {
            conn = new Connection(bundle.getString("sshHostName"));
        }
    }

    public void connectAndAuthenticate() throws ValidationFailedException {
        if(!active) return;
        
        try {

            conn.connect();

            String username = bundle.getString("sshUsername");
            final String password = bundle.getString("sshPassword");

            boolean isAuthenticated = false;

            if (conn.isAuthMethodAvailable(username, "password")) {
                isAuthenticated = conn.authenticateWithPassword(username, password);
            } else if (conn.isAuthMethodAvailable(username, "keyboard-interactive")) {
                isAuthenticated = conn.authenticateWithKeyboardInteractive(username, new InteractiveCallback() {

                    int promptCount = 0;

                    String lastError;

                    String pwd = password;

                    /*
                     * the callback may be invoked several times, depending on
                     * how many questions-sets the server sends
                     */
                    public String[] replyToChallenge(String name, String instruction, int numPrompts, String[] prompt,
                            boolean[] echo) throws IOException {
                        String[] result = new String[numPrompts];

                        for (int i = 0; i < numPrompts; i++) {
                            /*
                             * Often, servers just send empty strings for "name"
                             * and "instruction"
                             */

                            String[] content = new String[] { lastError, name, instruction, prompt[i] };

                            if (lastError != null) {
                                /* show lastError only once */
                                lastError = null;
                            }

                            result[i] = pwd;
                            promptCount++;
                        }

                        return result;
                    }

                    /*
                     * We maintain a prompt counter - this enables the detection
                     * of situations where the ssh server is signaling
                     * "authentication failed" even though it did not send a
                     * single prompt.
                     */

                    public int getPromptCount() {
                        return promptCount;
                    }

                });

            }

            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");

        } catch (Exception e) {
            log.error("Error connecting or authenticating SSH.", e);
            Map<String, List<String>> result = new Hashtable<String, List<String>>();
            result.put("propertyError.existingValue", Arrays
                    .asList(new String[] { "locationcode" }));
            throw new ValidationFailedException(result);
        }
    }

    public void transferLdiffAndExecuteCommand(String ldiff) {
        
        if(!active) return;
        
        SFTPv3Client sftpClient;
        try {
            sftpClient = new SFTPv3Client(conn);

            String pathAndFile = bundle.getString("sshFilenameWithPath");

            Date today = new Date();
            
            String processedPathAndFile = MessageFormat.format(pathAndFile, today, today );

            SFTPv3FileHandle sftpHandle = sftpClient.createFile(processedPathAndFile);

            sftpClient.write(sftpHandle, 0, ldiff.getBytes(), 0, ldiff.getBytes().length);

            sftpClient.closeFile(sftpHandle);

            sftpClient.close();

            /* Create a session */

            Session sess = conn.openSession();

            
            String processedCommand = MessageFormat.format( bundle.getString("sshCommand"), processedPathAndFile);
            
            sess.execCommand(processedCommand);

            /*
             * This basic example does not handle stderr, which is sometimes
             * dangerous (please read the FAQ).
             */

            InputStream stdout = new StreamGobbler(sess.getStdout());
            
            InputStream stderr = new StreamGobbler(sess.getStderr()); //simplemente para consumir stderr.

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

            String resultMessage = "";
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                else
                    resultMessage += line;
            }

            log.info(resultMessage);

            /* Show exit status, if available (otherwise "null") */

            log.info("ExitCode: " + sess.getExitStatus());

            
            //log errors

            BufferedReader err = new BufferedReader(new InputStreamReader(stderr));

            String errorMessage = "";
            while (true) {
                String line = err.readLine();
                if (line == null)
                    break;
                else
                    errorMessage += line;
            }

            if(!errorMessage.trim().equals(""))
            {
                log.error(errorMessage);            
            }

            /* Close this session */

            sess.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void connectionClose() {
        if(active)
        {
            conn.close();
        }
    }
}
