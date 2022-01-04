package cn.xialugui.toolmanagement.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author 夏露桂
 * @since 2021/12/30 15:23
 */
@Slf4j
public class PluginUtil {
    private final static String PATH = Environment.path() + File.separator;

    public static void unpack(String filename, String destination) {
        try {
            ZipUtil.unpack(new File(filename), new File(destinationPath(destination)));
        } catch (Exception e) {
            log.error("插件解压失败！位置：{}，信息：{}", destinationPath(destination), e.toString());
            throw new PluginCantUnpackException(e.toString());
        }
        log.info("插件解压成功！位置：{}", destinationPath(destination));
    }

    private static String destinationPath(String destination) {
        return PATH + destination;
    }

    private static String destinationPath(Long destination) {
        return destinationPath(destination.toString());
    }

    public static void remove(Long pluginId) {
        try {
            FileUtils.forceDeleteOnExit(new File(destinationPath(pluginId)));
        } catch (IOException e) {
            log.error("插件删除失败！位置：{}，信息：{}",
                    destinationPath(pluginId),
                    e.toString()
            );
            throw new PluginCantRemoveException(e.toString());
        }
        log.info("插件删除成功！位置：{}", destinationPath(pluginId));
    }
}
