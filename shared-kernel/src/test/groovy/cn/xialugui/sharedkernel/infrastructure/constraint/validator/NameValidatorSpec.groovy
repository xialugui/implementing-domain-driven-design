package cn.xialugui.sharedkernel.infrastructure.constraint.validator

import cn.xialugui.sharedkernel.ValidatableSpecification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

import javax.validation.ConstraintViolation

/**
 *
 * @author 夏露桂* @since 2021/11/18 17:26
 */
@Title("名称验证器说明")
@Subject(NameValidator)
class NameValidatorSpec extends ValidatableSpecification {

    @Unroll("名称：#name")
    def '名称不符合规范时抛出异常'() {
        given: '建立验证方法和参数'
        def realResult = '名称不符合规则："^[\\u4e00-\\u9fa5_a-zA-Z0-9]{2,16}$"'
        Test test = new Test(name)
        Set<ConstraintViolation> constraintViolations =
                validate(test)
        expect: '提示'
        ifViolatedOrElse(constraintViolations,
                { ConstraintViolation c ->
                    c.with {
                        it.message == realResult
                    }

                },
                { boolean r ->
                    !r
                }
        )
        where:
        name << [
                null,
                "",
                " ",
                "13812345678123456",
                "hello",
                "你好",
                "你"]

    }

    class Test {
        @Name
        String name

        Test(String name) {
            this.name = name
        }
    }
}
