package cn.xialugui.systeminformation.domain.model.token.command;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

/**
 * @author 夏露桂
 * @since 2021/12/8 16:47
 */
@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class IssueTokenCommand extends TokenCommand {
    String name;
    String value;
}
