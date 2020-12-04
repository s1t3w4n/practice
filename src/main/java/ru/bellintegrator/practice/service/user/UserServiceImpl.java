package ru.bellintegrator.practice.service.user;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserView;
import ru.bellintegrator.practice.view.user.UserViewFilter;
import ru.bellintegrator.practice.view.user.UserViewList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
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
        User persisted = userDao.findUserById(id);
        UserView user = mapperFactory.getMapperFacade().map(persisted, UserView.class);
        user.setDocDate(persisted.getIdentity().getDocDate());
        user.setDocNumber(persisted.getIdentity().getDocNumber());
        user.setDocName(persisted.getIdentity().getDoc().getName());
        user.setCitizenshipCode(persisted.getCitizenship().getCode());
        user.setCitizenshipName(persisted.getCitizenship().getName());
        return user;
    }
}
