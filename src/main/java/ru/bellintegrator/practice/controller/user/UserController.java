package ru.bellintegrator.practice.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.user.UserService;
import ru.bellintegrator.practice.view.user.UserViewFilter;
import ru.bellintegrator.practice.view.user.UserViewList;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер пользователя
 */
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Получить всех пользователей в определённом формате соответствующие фильтру
     * @param filter фильтр по котору происходит поиск
     * @return список пользователей в определённом формате
     */
    @PostMapping(value = "api/user/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserViewList> getUserBy(@RequestBody @Valid UserViewFilter filter) {
        return userService.getAllUserBy(filter);
    }
}
