package me.will.community.advice;

import me.will.community.dto.ResultDTO;
import me.will.community.exception.CustomErrorCode;
import me.will.community.exception.CustomException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * If there is any type exception, redirect to error page.
 *
 * @author will
 */

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handle(Throwable e, Model model, HttpServletRequest request) {

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            // return json data

            if (e instanceof CustomException) {
                return ResultDTO.errorOf((CustomException) e);
            } else {
                return ResultDTO.errorOf(CustomErrorCode.SYS_ERROR);
            }
        } else {
            // redirect to error page

            if (e instanceof CustomException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }

}
