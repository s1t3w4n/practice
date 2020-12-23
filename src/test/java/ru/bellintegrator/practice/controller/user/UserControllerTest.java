package ru.bellintegrator.practice.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.view.user.UserViewFilter;
import ru.bellintegrator.practice.view.user.UserViewList;
import ru.bellintegrator.practice.view.user.UserViewSave;
import ru.bellintegrator.practice.view.user.UserViewUpdate;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Контроллер пользователей должен: ")
class UserControllerTest {

    private static final String DATA_ROOT = "$.data";
    private static final String DATA_SECOND_NAME_ROOT = "$.data.secondName";
    private static final String DATA_FIRST_NAME_ROOT = "$.data.firstName";
    private static final String DATA_MIDDLE_NAME_ROOT = "$.data.middleName";
    private static final String RESULT_ROOT = "$.data.result";
    private static final String ERROR_ROOT = "$.error";
    private static final String DATA_ID_ROOT = "$.data.id";
    private static final long DEFAULT_ID = 1L;
    private static final long UNDEFINED_ID = 10L;
    private static final String UUID = " UUID: ";
    private static final String TYPE = "application/json;charset=UTF-8";
    private static final String RESULT_SUCCESS = "success";
    private static final String NEW_VALUE = "NEW_VALUE_";
    private static final String DEFAULT_FIRST_NAME = "Иванов";
    private static final String DEFAULT_SECOND_NAME = "Иван";
    private static final String DEFAULT_MIDDLE_NAME = "Иванович";
    private static final String DEFAULT_POSITION = "Директор";
    private static final String DEFAULT_DOC_NAME = "Паспорт гражданина Российской Федерации";
    private static final int DEFAULT_CITIZENSHIP_CODE = 356;
    private static final int DEFAULT_DOC_CODE = 12;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @DisplayName("возвращать пользователей по фильтру")
    @SneakyThrows
    @Test
    void shouldGetUserByFilter() {
        this.mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserViewFilter(DEFAULT_ID, null, null, null, null, null, null)))
                .accept(MediaType.parseMediaType(TYPE)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_ROOT).isArray())
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(new UserViewList(DEFAULT_ID, DEFAULT_FIRST_NAME, DEFAULT_SECOND_NAME, DEFAULT_MIDDLE_NAME, DEFAULT_POSITION)))));
    }

    @DisplayName("возвращать 400 ошибку по фильтру без id офиса")
    @SneakyThrows
    @Test
    void returnExceptionWithEmptyOfficeId() {
        this.mockMvc.perform(post("/api/user/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserViewFilter(null, "Иван", null, null, null, null, null))))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("возвращать пользователя с id в url")
    @SneakyThrows
    @Test
    void shouldReturnUserById() {
        this.mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_SECOND_NAME_ROOT).value(DEFAULT_SECOND_NAME))
                .andExpect(jsonPath(DATA_FIRST_NAME_ROOT).value(DEFAULT_FIRST_NAME))
                .andExpect(jsonPath(DATA_MIDDLE_NAME_ROOT).value(DEFAULT_MIDDLE_NAME))
                .andExpect(jsonPath(DATA_ID_ROOT).value(DEFAULT_ID));
    }

    @DisplayName("возвращать ошибку 404 для несуществующей сущности")
    @SneakyThrows
    @Test
    void shouldReturnErrorAfterGetForUndefinedUserId() {
        this.mockMvc.perform(get("/api/user/10"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("обновлять существующего пользователя")
    @SneakyThrows
    @Test
    void shouldUpdateUser() {
        this.mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new UserViewUpdate(DEFAULT_ID, DEFAULT_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, DEFAULT_DOC_NAME, NEW_VALUE, new Date(), DEFAULT_CITIZENSHIP_CODE, Boolean.TRUE))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESULT_ROOT).isNotEmpty())
                .andExpect(jsonPath(RESULT_ROOT).value(RESULT_SUCCESS));
        this.mockMvc.perform(get("/api/user/" + DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(DATA_ROOT).isNotEmpty())
                .andExpect(jsonPath(DATA_SECOND_NAME_ROOT).value(NEW_VALUE))
                .andExpect(jsonPath(DATA_FIRST_NAME_ROOT).value(NEW_VALUE))
                .andExpect(jsonPath(DATA_MIDDLE_NAME_ROOT).value(NEW_VALUE))
                .andExpect(jsonPath(DATA_ID_ROOT).value(DEFAULT_ID));

    }

    @DisplayName("возвращать ошибку 404 для несуществующей сущности")
    @SneakyThrows
    @Test
    void shouldReturnErrorAfterUpdateForUndefinedUserId() {
        this.mockMvc.perform(post("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new UserViewUpdate(UNDEFINED_ID, DEFAULT_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, DEFAULT_DOC_NAME, NEW_VALUE, new Date(), DEFAULT_CITIZENSHIP_CODE, Boolean.TRUE))))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }

    @DisplayName("создавать нового пользователя")
    @SneakyThrows
    @Test
    void shouldCreateNewUser() {
        this.mockMvc.perform(post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new UserViewSave(DEFAULT_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, DEFAULT_DOC_CODE, DEFAULT_DOC_NAME, NEW_VALUE, new Date(), DEFAULT_CITIZENSHIP_CODE, Boolean.TRUE))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESULT_ROOT).isNotEmpty())
                .andExpect(jsonPath(RESULT_ROOT).value(RESULT_SUCCESS));
    }

    @DisplayName("возвращать ошибку, если нет офиса с таким id офиса")
    @SneakyThrows
    @Test
    void shouldReturnErrorIfOfficeIdIsInvalid() {
        this.mockMvc.perform(post("/api/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new UserViewSave(UNDEFINED_ID, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, NEW_VALUE, DEFAULT_DOC_CODE, DEFAULT_DOC_NAME, NEW_VALUE, new Date(), DEFAULT_CITIZENSHIP_CODE, Boolean.TRUE))))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(ERROR_ROOT).isNotEmpty())
                .andExpect(content().string(containsString(UUID)));
    }
}