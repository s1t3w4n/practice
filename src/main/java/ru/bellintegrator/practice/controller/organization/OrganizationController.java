package ru.bellintegrator.practice.controller.organization;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.organization.OrganizationService;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.organization.OrganizationView;
import ru.bellintegrator.practice.view.organization.OrganizationViewFilter;
import ru.bellintegrator.practice.view.organization.OrganizationViewList;
import ru.bellintegrator.practice.view.organization.OrganizationViewSave;
import ru.bellintegrator.practice.view.organization.OrganizationViewUpdate;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер организации
 */
@RestController
@AllArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * Получить все организации в определённом формате соответствующие фильтру
     * @param filter фильтр по котору происходит поиск
     * @return список организаций в определённом формате
     */
    @PostMapping(value = "api/organization/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationViewList> getOrganizationBy(@RequestBody @Valid OrganizationViewFilter filter) {
        return organizationService.getAllOrganizationsBy(filter);
    }

    /**
     * Получить организацию по id
     * @param id уникальный идентификатор организации
     * @return возвращаемый view организации
     */
    @GetMapping(value = "api/organization/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrganizationView getOrganization(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * Обновить организацию
     * @param organization данные для обновления
     * @return Вью успешного результата
     */
    @PostMapping(value = "api/organization/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultSuccessView updateOrganization(@RequestBody @Valid OrganizationViewUpdate organization) {
        return organizationService.updateOrganization(organization);
    }

    /**
     * Сохранить организацию
     * @param organization данные для сохранения
     * @return Вью успешного результата
     */
    @PostMapping(value = "api/organization/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultSuccessView saveOrganization(@RequestBody @Valid OrganizationViewSave organization) {
        return organizationService.saveOrganization(organization);
    }
}
