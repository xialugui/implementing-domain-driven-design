package cn.xialugui.toolmanagement.util;

/**
 * @author 夏露桂
 * @since 2021/12/30 17:09
 */
public class ToolCantRemoveException extends RuntimeException {
    public ToolCantRemoveException(String message) {
        super(message);
    }
}
