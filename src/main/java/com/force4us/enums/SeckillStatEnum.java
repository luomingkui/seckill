package com.force4us.enums;

/**
 * Created by chengjinqian on 2017/4/18.
 */
public enum SeckillStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATE_REWRITE(-3,"数据篡改");

    private int state;

    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        for(SeckillStatEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
