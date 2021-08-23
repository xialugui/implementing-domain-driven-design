package cn.xialugui.identityaccess.domain.model.user.valueobject;

import java.util.HashMap;
import java.util.Map;

/**
 * 电话号码类型
 *
 * @author 夏露桂
 * @since 2021/8/20 21:21
 */
public enum MobilePhoneType {
    CMCC,//中国移动
    CUCC,//中国联通
    CBN,//中国广电
    CTCC,//中国电信
    UNKNOWN,//未知
    ;

    public static MobilePhoneType of(String email) {
        return MAP.getOrDefault(email.substring(0, 3), UNKNOWN);
    }

    private static final Map<String, MobilePhoneType> MAP = new HashMap<>(
    ) {{
        put("133", CTCC);
        put("149", CTCC);
        put("153", CTCC);
        put("173", CTCC);
        put("174", CTCC);
        put("177", CTCC);
        put("180", CTCC);
        put("181", CTCC);
        put("189", CTCC);
        put("191", CTCC);
        put("193", CTCC);

        put("134", CMCC);
        put("135", CMCC);
        put("136", CMCC);
        put("137", CMCC);
        put("138", CMCC);
        put("139", CMCC);
        put("147", CMCC);
        put("148", CMCC);
        put("150", CMCC);
        put("151", CMCC);
        put("152", CMCC);
        put("157", CMCC);
        put("158", CMCC);
        put("159", CMCC);
        put("172", CMCC);
        put("178", CMCC);
        put("182", CMCC);
        put("183", CMCC);
        put("184", CMCC);
        put("187", CMCC);
        put("188", CMCC);
        put("195", CMCC);
        put("198", CMCC);

        put("130", CUCC);
        put("131", CUCC);
        put("132", CUCC);
        put("145", CUCC);
        put("146", CUCC);
        put("155", CUCC);
        put("156", CUCC);
        put("166", CUCC);
        put("175", CUCC);
        put("176", CUCC);
        put("185", CUCC);
        put("186", CUCC);
        put("196", CUCC);

        put("190", CBN);
        put("192", CBN);
        put("197", CBN);
    }};
}
