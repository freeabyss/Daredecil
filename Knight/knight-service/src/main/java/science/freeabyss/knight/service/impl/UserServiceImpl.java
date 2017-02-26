package science.freeabyss.knight.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import science.freeabyss.knight.dao.UserEntityDao;
import science.freeabyss.knight.model.UserEntity;
import science.freeabyss.knight.service.UserService;

/**
 * Created by abyss on 12/16/16.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserEntityDao userEntityDao;


    @Override
    public List<UserEntity> queryAll() {
        return userEntityDao.queryAll();
    }
}
