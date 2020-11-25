package ru.bellintegrator.practice.service.doc;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.doc.DocDao;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.view.doc.DocView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class DocServiceImpl implements DocService {

    private final DocDao docDao;
    private final MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocView> getAllDoc() {
        return docDao.getAll().stream()
                .map(mapperFactory.getMapperFacade(Doc.class, DocView.class)::map)
                .collect(Collectors.toList());
    }
}
