package cn.xialugui.identityaccess.infrastructure.constraint.validator

import cn.xialugui.identityaccess.ValidatableSpecification
import spock.lang.Title

import javax.validation.ConstraintViolation
import java.lang.reflect.Method

/**
 *
 * @author 夏露桂
 * @since 2021/10/15 16:57
 */
@Title("存在验证器说明")
class ExistValidatorSpecification extends ValidatableSpecification {


    def '对象不存在时抛出异常'() {
        given: '建立验证方法和参数'
        TestObject testObject = new TestObject()
        Method method = TestObject.getMethod('test', TestObject)
        Set<ConstraintViolation> constraintViolations =
                validateParameters(testObject, method, new Object[]{inputObject})
        expect: '提示'
        ifViolated(constraintViolations, {
            with(constraintViolations[0]) {
                messageTemplate == '{cn.xialugui.identity-access.Exist.message}'
                message == result
            }
        })
        where:
        inputObject || result
        null        || '测试不存在'

    }

    class TestObject {

        @Check
        def test(TestObject input) {

        }
    }
}
