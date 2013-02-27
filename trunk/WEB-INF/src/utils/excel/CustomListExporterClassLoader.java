package utils.excel;

import java.net.MalformedURLException;
import java.net.URL;

public class CustomListExporterClassLoader extends ClassLoader {

    private URL baseURL;
    
    CustomListExporterClassLoader(URL baseURL)
    {
        this.baseURL = baseURL;
    }
    
    @Override
    protected URL findResource(String name) {
        
        if(name.endsWith(".cfg.xml") || name.endsWith(".hbm.xml"))
        {
            URL url = null;
            try {
                url = new URL(baseURL.toExternalForm() + name);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                
            if(url != null)
            {
                return url;
            }
        }
        
        return super.findResource(name);
        
    }

}
