package utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class Glob implements FilenameFilter {
    
    private String pathPattern;
    
    public Glob(String globPattern)
    {
        String pat = globPattern.replace(".", "\\.");
        
        pat= pat.replace("*", ".*?");
        pat= pat.replace("?", ".??");
        pat= pat.replace("[", "\\[");
        pat= pat.replace("]", "\\]");
        pat= pat.replace("(", "\\(");
        pat= pat.replace(")", "\\)");
        pat= pat.replace("{", "\\{");
        pat= pat.replace("}", "\\}");
        
        
        
        pathPattern = pat;
    }

   
    
    public boolean accept(File dir, String name) {
        
        return name.matches(pathPattern);
    }

}
