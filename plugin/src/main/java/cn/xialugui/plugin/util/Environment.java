package cn.xialugui.plugin.util;

import org.springframework.boot.system.ApplicationHome;

/**
 * @author 夏露桂
 * @since 2021/12/30 14:25
 */
public class Environment {
    private static final ApplicationHome APPLICATION_HOME = new ApplicationHome();

    public static String path() {
        return APPLICATION_HOME.getDir().toString();
    }

}
