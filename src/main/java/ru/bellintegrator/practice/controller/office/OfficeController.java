package ru.bellintegrator.practice.controller.office;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.service.office.OfficeService;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.office.OfficeView;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;
import ru.bellintegrator.practice.view.office.OfficeViewList;
import ru.bellintegrator.practice.view.office.OfficeViewUpdate;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер офиса
 */
@RestController
@AllArgsConstructor
public class OfficeController {
    private final OfficeService officeService;

    /**
     * Получить все офиса в определённом формате соответствующие фильтру
     * @param filter фильтр по котору происходит поиск
     * @return список офисов в определённом формате
     */
    @PostMapping(value = "api/office/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OfficeViewList> getOrganizationBy(@RequestBody @Valid OfficeViewFilter filter) {
        return officeService.getAllOfficeBy(filter);
    }

    /**
     * Получить офис по id
     * @param id уникальный идентификатор офиса
     * @return возвращаемый view организации
     */
    @GetMapping(value = "api/office/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OfficeView getOrganization(@PathVariable Long id) {
        return officeService.getOfficeById(id);
    }

    /**
     * Обновить офис
     * @param office данные для обновления
     * @return Вью успешного результата
     */
    @PostMapping(value = "api/office/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultSuccessView updateOrganization(@RequestBody @Valid OfficeViewUpdate office) {
        return officeService.updateOffice(office);
    }
}
