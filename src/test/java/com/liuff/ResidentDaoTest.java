package com.liuff;

import com.liuff.entity.Resident;
import com.liuff.dao.ResidentDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liufengfang on 2019/10/14.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ResidentDaoTest {
    @Autowired
    private ResidentDao residentDao;

    @Test
    public void save() {
        Resident resident = new Resident();
        resident.setId(1L);
        resident.setUsername("zzzz");
        resident.setPassword("bbbb");
        resident.setSex(1);
        resident.setAge(18);
        // 返回插入的记录数 ，期望是1条 如果实际不是一条则抛出异常
        Assert.assertEquals(1, residentDao.save(resident));
    }

    @Test
    public void update() {
        Resident resident = new Resident();
        resident.setId(1L);
        resident.setPassword("pwd");
        // 返回更新的记录数 ，期望是1条 如果实际不是一条则抛出异常
        Assert.assertEquals(1, residentDao.update(resident));
    }

    @Test
    public void selectById() {
        Assert.assertNotNull(residentDao.selectById(1L));
    }

    @Test
    public void deleteById() {
        Assert.assertEquals(1, residentDao.deleteById(1L));
    }
}
