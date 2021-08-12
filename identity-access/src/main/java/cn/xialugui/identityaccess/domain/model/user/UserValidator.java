package cn.xialugui.identityaccess.domain.model.user;

import cn.xialugui.identityaccess.domain.ValidationNotificationHandler;
import cn.xialugui.identityaccess.domain.Validator;
import cn.xialugui.identityaccess.domain.model.user.aggregate.User;

/**
 * @author 夏露桂
 * @since 2021/8/12 17:57
 */
public class UserValidator extends Validator {
    private User user;

    public UserValidator(User user, ValidationNotificationHandler validationNotificationHandler) {
        super(validationNotificationHandler);
        this.setUser(user);
    }

    private void setUser(User user) {
        this.user = user;
    }

    @Override
    public void validate() {
        if (!user.getUsername().get号().equals(user.getNickname().getNickname())) {
            notificationHandler().handleError("昵称和号不一致");
        }
    }
}
