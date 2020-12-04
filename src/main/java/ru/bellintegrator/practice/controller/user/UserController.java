package ru.bellintegrator.practice.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.user.UserService;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.user.UserView;
import ru.bellintegrator.practice.view.user.UserViewFilter;
import ru.bellintegrator.practice.view.user.UserViewList;
import ru.bellintegrator.practice.view.user.UserViewSave;
import ru.bellintegrator.practice.view.user.UserViewUpdate;

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

    /**
     * Получить пользователя по id
     * @param id уникальный идентификатор пользователя
     * @return возвращаемый view пользователя
     */
    @GetMapping(value = "api/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView getUser(@PathVariable Long id) {
            return userService.getUserById(id);
    }

    /**
     * Обновить пользователя
     * @param user данные для обновления
     * @return Вью успешного результата
     */
    @PostMapping(value = "api/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultSuccessView updateUser(@RequestBody @Valid UserViewUpdate user) {
        return userService.updateUser(user);
    }

    /**
     * Сохранить пользователя
     * @param user данные для сохранения
     * @return Вью успешного результата
     */
    @PostMapping(value = "api/user/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultSuccessView saveUser(@RequestBody @Valid UserViewSave user) {
        return userService.saveUser(user);
    }
}
