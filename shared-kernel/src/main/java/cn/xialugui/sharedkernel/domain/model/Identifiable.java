package cn.xialugui.sharedkernel.domain.model;

import java.io.Serializable;

/**
 * @author 夏露桂
 * @since 2021/8/12 15:56
 */
public interface Identifiable<NID extends Serializable> {
    NID naturalId();
}
