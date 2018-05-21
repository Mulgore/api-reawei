package cn.reawei.api.common;

import cn.reawei.api.controller.sys.SuperController;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 拦截器
 *
 * @author qigong
 */
@Component
public class SsoInterceptor extends SuperController implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * <p>
     * 该方法返回true，才会继续执行后续的Interceptor和Controller
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Permission pm = method.getAnnotation(Permission.class);
        if (Objects.nonNull(pm)) {
            if (Objects.equals(pm.action(), Action.Skip)) {
                // 权限拦截忽略
                return true;
            } else {
                return true;
            }
        } else {
            return sendResponse(httpServletResponse);
        }
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后)
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        String  callback = httpServletRequest.getParameter("callback");
//        String jsoncallback = callback + "({'result':})";
//        PrintWriter out =  httpServletResponse.getWriter();
//        out.print(jsoncallback);
//        out.flush();
//        out.close();
    }

    private boolean sendResponse(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;UTF-8");
        httpServletResponse.getWriter().write(callbackFail("没有访问权限"));
        return false;
    }
}
