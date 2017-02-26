package science.freeabyss.knight.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by abyss on 12/17/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/knight-dao-test.xml"})
@Transactional
public class UserEntityDaoTest {
    @Autowired
    private UserEntityDao userEntityDao;
    @Test
    public void queryAll() throws Exception {
        userEntityDao.queryAll();
    }

}