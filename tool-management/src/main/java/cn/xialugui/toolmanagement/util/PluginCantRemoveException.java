package cn.xialugui.toolmanagement.util;

/**
 * @author 夏露桂
 * @since 2021/12/30 17:09
 */
public class PluginCantRemoveException extends RuntimeException {
    public PluginCantRemoveException(String message) {
        super(message);
    }
}
