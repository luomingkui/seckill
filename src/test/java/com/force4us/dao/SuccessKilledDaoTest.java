package com.force4us.dao;

import com.force4us.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by chengjinqian on 2017/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1000L;
        long phone = 18810464493L;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount= " + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1000L;
        long phone = 18810464493L;

        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println("-------------------------------");
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}