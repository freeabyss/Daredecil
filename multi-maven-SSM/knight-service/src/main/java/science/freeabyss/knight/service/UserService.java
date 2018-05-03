package science.freeabyss.knight.service;

import java.util.List;

import science.freeabyss.knight.model.UserEntity;

/**
 * Created by abyss on 12/16/16.
 */
public interface UserService {
    List<UserEntity> queryAll();
}
