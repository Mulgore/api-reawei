package cn.reawei.api.common;

import cn.reawei.api.controller.sys.SuperController;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Component
public class SSOInterceptor extends SuperController implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * <p>
     * 该方法返回true，才会继续执行后续的Interceptor和Controller
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        SSOToken ssoToken = SSOHelper.getSSOToken(httpServletRequest);
        if (ssoToken == null) {
            switch (httpServletRequest.getRequestURI()) {
                case "/api/v1/user":
                    return true;
                case "/api/v1/user/login":
                    return true;
            }
            return false;
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后)
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
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
}
