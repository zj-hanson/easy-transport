package com.lightshell.transport.controller;

import com.lightshell.transport.common.ResponseData;
import com.lightshell.transport.common.ResponseMessage;
import com.lightshell.transport.entity.DeliveryAddress;
import com.lightshell.transport.entity.Tenant;
import com.lightshell.transport.entity.WechatSession;
import com.lightshell.transport.service.DeliveryAddressServiceImpl;
import com.lightshell.transport.service.TenantServiceImpl;
import com.lightshell.transport.service.WechatSessionServiceImpl;
import com.lightshell.comm.BaseLib;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/delivery-address"})
public class DeliveryAddressController {

    @Autowired
    private WechatSessionServiceImpl wechatSessionService;

    @Autowired
    private DeliveryAddressServiceImpl deliveryAddressService;

    @Autowired
    private TenantServiceImpl tenantService;

    @PostMapping(path = "/mini-program")
    @ResponseBody
    public ResponseMessage addByMiniPrg(@RequestBody DeliveryAddress entity, @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            entity.setCredate(BaseLib.getDate());
            entity.setOptdate(entity.getCredate());
            deliveryAddressService.save(entity);
            return new ResponseMessage("200", "新增成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @PutMapping(path = "/mini-program/{uid}")
    @ResponseBody
    public ResponseMessage updateByMiniPrg(@RequestBody DeliveryAddress entity, @PathVariable String uid,
        @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            DeliveryAddress deliveryAddress = deliveryAddressService.findByUID(uid);
            if (deliveryAddress == null) {
                return new ResponseMessage("404", "对象不存在");
            }
            if (deliveryAddress.getOptdate() != null
                && deliveryAddress.getOptdate().compareTo(entity.getOptdate()) != 0) {
                return new ResponseMessage("409", "修改冲突");
            }
            entity.setOptdate(BaseLib.getDate());
            deliveryAddressService.save(entity);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @DeleteMapping(path = "/mini-program/{uid}")
    @ResponseBody
    public ResponseMessage deleteByMiniPrg(@PathVariable String uid, @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            DeliveryAddress entity = deliveryAddressService.findByUID(uid);
            if (entity == null) {
                return new ResponseMessage("404", "更新对象不存在");
            }
            deliveryAddressService.deleteById(entity.getId());
            return new ResponseMessage("200", "删除成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }

    }

    @GetMapping(path = "/mini-program")
    @ResponseBody
    public ResponseData<DeliveryAddress> findBySessionId(@RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        ResponseData<DeliveryAddress> ret;
        if (wechatSession != null) {
            List<DeliveryAddress> data = deliveryAddressService.findByOpenId(wechatSession.getOpenId());
            ret = new ResponseData<>("200", "success");
            ret.setData(data);
            ret.setCount(data != null ? data.size() : 0);
        } else {
            ret = new ResponseData<>("403", "Get SessionInfo Failure");
        }
        return ret;
    }

    @GetMapping(path = "/mini-program/tenant-address")
    @ResponseBody
    public ResponseData<Tenant> findTenantBySessionId(@RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        ResponseData<Tenant> ret;
        if (wechatSession != null) {
            List<Tenant> data = tenantService.findAll(null);
            ret = new ResponseData<>("200", "success");
            ret.setData(data);
            ret.setCount(data != null ? data.size() : 0);
        } else {
            ret = new ResponseData<>("403", "Get SessionInfo Failure");
        }
        return ret;
    }

}
