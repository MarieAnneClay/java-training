package util;

public class LocalThreadManager {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public LocalThreadManager() {
        super();
    }

    public static void setComputer(String name) {
        threadLocal.set(name);
    }

    public static String getComputer() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
