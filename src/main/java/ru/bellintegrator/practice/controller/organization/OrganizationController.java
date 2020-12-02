package ru.bellintegrator.practice.controller.organization;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.organization.OrganizationService;
import ru.bellintegrator.practice.view.organization.OrganizationView;
import ru.bellintegrator.practice.view.organization.OrganizationViewWithFilterIn;
import ru.bellintegrator.practice.view.organization.OrganizationViewWithFilterOut;

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
     * @param filterIn фильтра по котору происходит поиск
     * @return список организаций в определённом формате
     */
    @PostMapping(value = "api/organization/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationViewWithFilterOut> getOrganizationBy(@RequestBody OrganizationViewWithFilterIn filterIn) {
        return organizationService.getAllOrganizationsBy(filterIn);
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
}
