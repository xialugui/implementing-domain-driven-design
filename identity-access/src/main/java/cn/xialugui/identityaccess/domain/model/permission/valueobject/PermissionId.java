package cn.xialugui.identityaccess.domain.model.permission.valueobject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 夏露桂
 * @since 2021/9/6 21:00
 */
@Embeddable
@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PermissionId {
    private static final char[] chars = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    };

    private @NotNull @Pattern(regexp = "^[0-9a-zA-Z]{2,16}$") String value;

    public PermissionId(String value) {
        setValue(value);
    }

    public static PermissionId random() {
        StringBuilder result = new StringBuilder();
        int max = chars.length;
        for (int i = 0; i < 16; i++) {
            result.append(chars[(int) (Math.random() * max)]);
        }
        return new PermissionId(result.toString());
    }
}
