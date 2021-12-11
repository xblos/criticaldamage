package net.xblos.crit.integration;

public class Integration {

    public static boolean isModLoaded(String classpath) {
        try {
            Class.forName(classpath, false, Integration.class.getClassLoader());
            return true;
        }
        catch (LinkageError | ClassNotFoundException e) {
            return false;
        }
    }
}
