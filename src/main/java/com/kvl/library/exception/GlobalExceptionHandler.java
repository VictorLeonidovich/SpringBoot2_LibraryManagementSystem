package com.kvl.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${app.errors.show-details:false}")
    private boolean showDetails;

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public String handleNotFoundError(Exception ex, Model model) {
        log.error("The UI page or static resource was not found: {}", ex.getMessage());
        model.addAttribute("errorMessage", "Запрашиваемая страница не найдена.");
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        log.error("An unhandled UI exception occurred: ", ex);
        if (showDetails) {
            model.addAttribute("errorMessage", "Произошла внутренняя ошибка сервера. Пожалуйста, попробуйте позже.");
            model.addAttribute("technicalDetails", ex.getMessage()); // Не рекомендуется показывать пользователям в продакшене
        } else {
            model.addAttribute("technicalDetails", null);
        }
        return "error/500";
    }
}