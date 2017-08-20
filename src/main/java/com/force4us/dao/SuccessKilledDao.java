package com.force4us.dao;

import com.force4us.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chengjinqian on 2017/4/17.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品ID查询明细SuccessKilled对象， 携带了Seckill秒杀产品对象
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
