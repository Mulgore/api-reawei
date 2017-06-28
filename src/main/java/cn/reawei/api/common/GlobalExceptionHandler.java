package cn.reawei.api.common;

import cn.reawei.api.common.utils.AjaxResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个类的作用是:
 * 全局异常处理类
 * <p>
 * Created by xingwu on 2017/6/8.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个类的作用是: 处理异常类，返回友好的异常页面
     * <p>
     * Created by xingwu on 2017/6/8.
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView viewErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个类的作用是: 处理异常类，返回JSON字符串
     * <p>
     * Created by xingwu on 2017/6/8.
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public AjaxResult jsonErrorHandler(HttpServletRequest request, RuntimeException e) throws Exception {
        e.printStackTrace();
        String message = e.getMessage().substring(e.getMessage().lastIndexOf(".") + 1, e.getMessage().length());
        switch (message) {
            case "NullPointerException":
                return new AjaxResult(false, "空指针异常");
            case "ArrayIndexOutOfBoundsException":
                return new AjaxResult(false, "数组下标越界");
            case "ArithmeticExecption":
                return new AjaxResult(false, "算术异常类");
            case "FileNotFoundException":
                return new AjaxResult(false, "文件未找到异常");
            default:
                return new AjaxResult(false, "系统内部异常");
        }
    }
}
