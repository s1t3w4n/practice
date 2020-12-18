package ru.bellintegrator.practice.service.user;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.dao.doc.DocDao;
import ru.bellintegrator.practice.dao.identity.IdentityDao;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.exception.IdNotFoundException;
import ru.bellintegrator.practice.exception.WrongDataException;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.Identity;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.user.UserView;
import ru.bellintegrator.practice.view.user.UserViewFilter;
import ru.bellintegrator.practice.view.user.UserViewList;
import ru.bellintegrator.practice.view.user.UserViewSave;
import ru.bellintegrator.practice.view.user.UserViewUpdate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final DocDao docDao;
    private final UserDao userDao;
    private final IdentityDao identityDao;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserViewList> getAllUserBy(UserViewFilter filter) {
        return userDao.findAllUserBy(filter).stream()
                .map(mapperFactory.getMapperFacade(User.class, UserViewList.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getUserById(Long id) {
        User user = userDao.findUserById(id);
        if (Objects.nonNull(user)) {
            mapperFactory.classMap(User.class, UserView.class)
                    .field("identity.docDate", "docDate")
                    .field("identity.docNumber", "docNumber")
                    .field("identity.doc.name", "docName")
                    .field("citizenship.name", "citizenshipName")
                    .field("citizenship.code", "citizenshipCode")
                    .byDefault()
                    .register();
            return mapperFactory.getMapperFacade().map(user, UserView.class);
        } else {
            throw new IdNotFoundException(User.class.getSimpleName());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView updateUser(UserViewUpdate view) {
        User user = userDao.findUserById(view.getId());
        if (Objects.nonNull(user)) {
            try {
                user.setOffice(checkIfExistsOffice(officeDao.findOfficeById(view.getOfficeId())));
                user.getIdentity().setDoc(checkIfExistsDoc(docDao.findDocByName(view.getDocName())));
                user.setCitizenship(checkIfExistsCountry(countryDao.findCountryByCode(view.getCitizenshipCode())));
            } catch (RuntimeException exception) {
                throw new WrongDataException(exception.getMessage());
            }

            mapperFactory.classMap(UserViewUpdate.class, User.class)
                    .mapNulls(false)
                    .field("docDate","identity.docDate")
                    .field("docNumber", "identity.docNumber")
                    .byDefault()
                    .register();
            mapperFactory.getMapperFacade().map(view, user);
            return new ResultSuccessView();
        } else {
            throw new IdNotFoundException(User.class.getSimpleName());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView saveUser(UserViewSave user) {
        User newUser = mapperFactory.getMapperFacade().map(user, User.class);
        Identity identity = new Identity();
        try {
            newUser.setOffice(checkIfExistsOffice(officeDao.findOfficeById(user.getOfficeId())));
            newUser.setCitizenship(checkIfExistsCountry(countryDao.findCountryByCode(user.getCitizenshipCode())));
            identity.setDoc(checkIfExistsDoc(docDao.findDocByNameAndCode(user.getDocName(), user.getDocCode())));

        } catch (RuntimeException e) {
            throw new WrongDataException(e.getMessage());
        }
        userDao.saveUser(newUser);

        identity.setDocNumber(user.getDocNumber());
        identity.setDocDate(user.getDocDate());
        identity.setUser(newUser);
        identityDao.saveIdentity(identity);

        return new ResultSuccessView();
    }

    private Office checkIfExistsOffice(Office office) {
        if (Objects.isNull(office)) {
            throw new RuntimeException("Нет такого офиса");
        } else {
            return office;
        }
    }

    private Doc checkIfExistsDoc(Doc doc) {
        if (Objects.nonNull(doc)) {
            return doc;
        } else {
            throw new RuntimeException("Нет такого документа");
        }
    }

    private Country checkIfExistsCountry(Country country) {
        if (Objects.nonNull(country)) {
            return country;
        } else {
            throw new RuntimeException("Нет такой страны в справочнике стран");
        }
    }

}
