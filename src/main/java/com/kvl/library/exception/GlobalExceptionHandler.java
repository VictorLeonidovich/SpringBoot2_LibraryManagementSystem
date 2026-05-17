package com.kvl.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${app.errors.show-details:false}")
    private boolean showDetails;

    /*@ExceptionHandler(value = {MyException.class})
    public String handleRecordNotFound(Exception ex, Model model) {
        log.error("MyException occurred: ", ex);
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404";
    }*/

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundError(Model model) {
        log.error("The page not found.");
        model.addAttribute("errorMessage", "Запрашиваемая страница не найдена.");
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        log.error("An unhandled exception occurred: ", ex);
        if (showDetails) {
            model.addAttribute("errorMessage", "Произошла внутренняя ошибка сервера. Пожалуйста, попробуйте позже.");
            model.addAttribute("technicalDetails", ex.getMessage()); // Не рекомендуется показывать пользователям в продакшене
        } else {
            model.addAttribute("technicalDetails", null);
        }
        return "error/500.html";
    }

}