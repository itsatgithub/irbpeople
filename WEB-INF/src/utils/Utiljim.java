package utils;

public class Utiljim {

    /**
     * Efficient string replace function. Replaces instances of the substring
     * find with replace in the string subject. karl@xk72.com
     * 
     * @param subject
     *            The string to search for and replace in.
     * @param find
     *            The substring to search for.
     * @param replace
     *            The string to replace instances of the string find with.
     */
    public static String replace(String subject, String find, String replace) {
        StringBuffer buf = new StringBuffer();
        int l = find.length();
        int s = 0;
        int i = subject.indexOf(find);
        while (i != -1) {
            buf.append(subject.substring(s, i));
            buf.append(replace);
            s = i + l;
            i = subject.indexOf(find, s);
        }
        buf.append(subject.substring(s));
        return buf.toString();
    }
    
}
