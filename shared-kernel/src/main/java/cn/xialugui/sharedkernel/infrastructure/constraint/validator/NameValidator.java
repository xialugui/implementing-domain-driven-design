package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.internal.engine.messageinterpolation.util.InterpolationHelper;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;


/**
 * 名称验证器，旨在提供通用名称验证，对象通常为{@code String}。
 *
 * @author 夏露桂
 * @since 2021/10/15 11:48
 */
public class NameValidator implements ConstraintValidator<Name, CharSequence> {
    private java.util.regex.Pattern pattern;
    private static final Log LOG = LoggerFactory.make(MethodHandles.lookup());
    private String escapedRegexp;

    @Override
    public void initialize(Name name) {
        try {
            pattern = java.util.regex.Pattern.compile(name.regexp());
        } catch (PatternSyntaxException e) {
            throw LOG.getInvalidRegularExpressionException(e);
        }

        escapedRegexp = InterpolationHelper.escapeMessageParameter(name.regexp());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        if (constraintValidatorContext instanceof HibernateConstraintValidatorContext) {

            constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter("regexp", escapedRegexp);
        }

        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
