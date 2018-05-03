package science.freeabyss.knight.dao;

import java.util.List;

import science.freeabyss.knight.model.UserEntity;

/**
 * Created by abyss.li on 12/14/2016.
 */
public interface UserEntityDao {
    List<UserEntity> queryAll();
}
