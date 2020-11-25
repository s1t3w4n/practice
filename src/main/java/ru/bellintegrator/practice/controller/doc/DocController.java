package ru.bellintegrator.practice.controller.doc;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.doc.DocService;
import ru.bellintegrator.practice.view.doc.DocView;

import java.util.List;

/**
 * Контроллер документа
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/docs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocController {

    private final DocService docService;

    /**
     * Получение списка документов
     * @return список документов
     */
    @PostMapping
    public List<DocView> getAllDoc(){
        return docService.getAllDoc();
    }
}
