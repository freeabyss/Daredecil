package science.freeabyss.template.spa.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截请求，根据标注的 I18nPrefix 注解，设置国际化资源文件的路径
 *
 * @author abyss
 * @date 2019-01-23 11:11
 */
public class MessageResourceInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse rep, Object handler, ModelAndView modelAndView) {

        // 在方法中设置i18路径
        if (null != req.getAttribute(MessageResourceExtension.I18N_ATTRIBUTE)) {
            return;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            // 在method上注解了i18
            I18nPrefix i18nMethod = method.getMethodAnnotation(I18nPrefix.class);
            if (null != i18nMethod) {
                req.setAttribute(MessageResourceExtension.I18N_ATTRIBUTE, i18nMethod.value());
                return;
            }

            // 在Controller上注解了i18
            I18nPrefix i18nController = method.getBeanType().getAnnotation(I18nPrefix.class);
            if (null != i18nController) {
                req.setAttribute(MessageResourceExtension.I18N_ATTRIBUTE, i18nController.value());
                return;
            }
            req.setAttribute(MessageResourceExtension.I18N_ATTRIBUTE, "messages");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) {
        // 在跳转到该方法先清除request中的国际化信息
        req.removeAttribute(MessageResourceExtension.I18N_ATTRIBUTE);
        return true;
    }
}
