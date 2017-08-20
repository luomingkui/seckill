package com.force4us.service;

import com.force4us.dto.Exposer;
import com.force4us.dto.SeckillExecution;
import com.force4us.entity.Seckill;
import com.force4us.exception.RepeatKillException;
import com.force4us.exception.SeckillCloseException;
import com.force4us.exception.SeckillException;

import java.util.List;

/**业务接口:站在使用者(程序员)的角度设计接口
 * 三个方面:1.方法定义粒度，方法定义的要非常清楚2.参数，要越简练越好
 * 3.返回类型(return 类型一定要友好/或者return异常，我们允许的异常)
 * Created by chengjinqian on 2017/4/18.
 */
public interface SeckillService {

    /**
     * 查询全部秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 在秒杀开启时输出秒杀接口的地址，否则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     *  执行秒杀操作，有可能失败，有可能成功，所以要抛出我们允许的异常
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;


    SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

}
