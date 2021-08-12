package cn.xialugui.identityaccess.domain;

/**
 * @author 夏露桂
 * @since 2021/8/12 17:51
 */
public abstract class Validator {
    private ValidationNotificationHandler notificationHandler;

    public Validator(ValidationNotificationHandler validationNotificationHandler) {
        this.setNotificationHandler(validationNotificationHandler);
    }


    public abstract void validate();

    protected ValidationNotificationHandler notificationHandler() {
        return this.notificationHandler;
    }


    private void setNotificationHandler(ValidationNotificationHandler aHandler) {
        this.notificationHandler = aHandler;
    }
}
