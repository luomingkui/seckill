package com.force4us.web;

import com.force4us.dto.Exposer;
import com.force4us.dto.SeckillExecution;
import com.force4us.dto.SeckillResult;
import com.force4us.entity.Seckill;
import com.force4us.enums.SeckillStatEnum;
import com.force4us.exception.RepeatKillException;
import com.force4us.exception.SeckillCloseException;
import com.force4us.exception.SeckillException;
import com.force4us.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Repeat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by chengjinqian on 2017/4/19.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;



    @RequestMapping(value = "/list",method= RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    //ajax ,json暴露秒杀接口的方法
    @RequestMapping(value="/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;

        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e) {
            e.printStackTrace();
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }


    @RequestMapping(value="/{seckillId}/{md5}/execution", method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value="killPhone", required = false) Long phone){
        if(phone == null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }

        SeckillResult<SeckillExecution> result;

        try {
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId,phone, md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        } catch (RepeatKillException e1) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,execution);
        } catch(SeckillCloseException e2){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch(Exception e){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }


    @RequestMapping("/test")
    public String test(){
        return "helloworld";
    }
}
