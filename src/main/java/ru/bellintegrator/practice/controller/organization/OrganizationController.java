package ru.bellintegrator.practice.controller.organization;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.organization.OrganizationService;
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
     *
     */
    @PostMapping(value = "api/organization/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationViewWithFilterOut> getOrganizationBy(@RequestBody OrganizationViewWithFilterIn filterIn) {
        return organizationService.getAllOrganizationsBy(filterIn);
    }
}
