package cn.xialugui.identityaccess.domain.model.user.valueobject

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

/**
 *
 * @author 夏露桂* @since 2021/8/23 14:30
 */
@Title("邮箱类型值对象单元测试")
@Subject([Email, EmailType])
class EmailSpecification extends Specification {

    @Unroll("邮箱：#input,类型：#type")
    def "根据邮箱判断类型"() {
        when: "初始化邮箱"
        Email email = new Email(input)
        then: "类型匹配"
        email.getEmailType() == type
        where:
        input                 || type
        "xialugui@qq.com"     || EmailType.TENCENT
        "xialugui@163.com"    || EmailType.NET_EASE
        "xialugui@aliyun.com" || EmailType.ALIBABA
        "xialugui@139.com"    || EmailType.CMCC
        "xialugui@baidu.com"  || EmailType.BAIDU
        "xialugui@gmail.com"  || EmailType.GOOGLE
        "xialugui@qq1.com"    || EmailType.UNKNOWN
    }

    @Unroll("非法邮箱：#input")
    def "邮箱格式必须合法"() {
        when: "创建邮箱"
        new Email(input)
        then: "不合法抛出异常"
        def error = thrown(IllegalArgumentException)
        error.message == "邮箱格式不正确"

        where:
        input << [
                "xialu@gui@qq.com",
                "xialu,gui@163.com",
                "@aliyun.com",
                "xialugui139.com",
                ".com",
                " ",
                "",
        ]

    }
}
