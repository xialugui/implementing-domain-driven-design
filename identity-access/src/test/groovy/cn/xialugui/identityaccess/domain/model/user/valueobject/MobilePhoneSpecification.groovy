package cn.xialugui.identityaccess.domain.model.user.valueobject

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll


/**
 *
 * @author 夏露桂* @since 2021/8/23 16:42
 */
@Title("手机号单元测试")
@Subject([MobilePhone, MobilePhoneType])
class MobilePhoneSpecification extends Specification {

    @Unroll("手机号：#input")
    def "手机号需符合规范"() {
        when: "初始化手机号"
        new MobilePhone(input)
        then: "不符合规范抛出异常"
        def error = thrown(IllegalArgumentException)
        error.message == errorMessage
        where:
        input             || errorMessage
        "138123456"       || "不符合手机号规范"
        "138123456123123" || "不符合手机号规范"
        "ada38123456"     || "不符合手机号规范"

    }

    @Unroll("手机号：#input，类型：#type")
    def "识别手机号类型"() {

        when: "初始化手机号"
        MobilePhone mobilePhone = new MobilePhone(input)
        then: "识别手机类型"
        mobilePhone.getMobilePhoneType() == type
        where:
        input         || type
        "13312345678" || MobilePhoneType.CTCC
        "14912345678" || MobilePhoneType.CTCC
        "15312345678" || MobilePhoneType.CTCC
        "17312345678" || MobilePhoneType.CTCC
        "17412345678" || MobilePhoneType.CTCC
        "17712345678" || MobilePhoneType.CTCC
        "18012345678" || MobilePhoneType.CTCC
        "18112345678" || MobilePhoneType.CTCC
        "18912345678" || MobilePhoneType.CTCC
        "19112345678" || MobilePhoneType.CTCC
        "19312345678" || MobilePhoneType.CTCC
        "13412345678" || MobilePhoneType.CMCC
        "13512345678" || MobilePhoneType.CMCC
        "13612345678" || MobilePhoneType.CMCC
        "13712345678" || MobilePhoneType.CMCC
        "13812345678" || MobilePhoneType.CMCC
        "13912345678" || MobilePhoneType.CMCC
        "14712345678" || MobilePhoneType.CMCC
        "14812345678" || MobilePhoneType.CMCC
        "15012345678" || MobilePhoneType.CMCC
        "15112345678" || MobilePhoneType.CMCC
        "15212345678" || MobilePhoneType.CMCC
        "15712345678" || MobilePhoneType.CMCC
        "15812345678" || MobilePhoneType.CMCC
        "15912345678" || MobilePhoneType.CMCC
        "17212345678" || MobilePhoneType.CMCC
        "17812345678" || MobilePhoneType.CMCC
        "18212345678" || MobilePhoneType.CMCC
        "18312345678" || MobilePhoneType.CMCC
        "18412345678" || MobilePhoneType.CMCC
        "18712345678" || MobilePhoneType.CMCC
        "18812345678" || MobilePhoneType.CMCC
        "19512345678" || MobilePhoneType.CMCC
        "19812345678" || MobilePhoneType.CMCC
        "13012345678" || MobilePhoneType.CUCC
        "13112345678" || MobilePhoneType.CUCC
        "13212345678" || MobilePhoneType.CUCC
        "14512345678" || MobilePhoneType.CUCC
        "14612345678" || MobilePhoneType.CUCC
        "15512345678" || MobilePhoneType.CUCC
        "15612345678" || MobilePhoneType.CUCC
        "16612345678" || MobilePhoneType.CUCC
        "17512345678" || MobilePhoneType.CUCC
        "17612345678" || MobilePhoneType.CUCC
        "18512345678" || MobilePhoneType.CUCC
        "18612345678" || MobilePhoneType.CUCC
        "19612345678" || MobilePhoneType.CUCC
        "19012345678" || MobilePhoneType.CBN
        "19212345678" || MobilePhoneType.CBN
        "19712345678" || MobilePhoneType.CBN

    }
}
