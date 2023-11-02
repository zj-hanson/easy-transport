package com.lightshell.transport.controller;

import com.lightshell.transport.common.ResponseData;
import com.lightshell.transport.common.ResponseMessage;
import com.lightshell.transport.entity.VehicleInfo;
import com.lightshell.transport.entity.WechatSession;
import com.lightshell.transport.service.VehicleInfoServiceImpl;
import com.lightshell.transport.service.WechatSessionServiceImpl;
import com.lightshell.comm.BaseLib;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/vehicle-info"})
public class VehicleInfoController {

    @Autowired
    private WechatSessionServiceImpl wechatSessionService;

    @Autowired
    private VehicleInfoServiceImpl vehicleInfoService;

    @PostMapping(path = "/mini-program")
    @ResponseBody
    public ResponseMessage add(@RequestBody VehicleInfo entity, @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            entity.setCredate(BaseLib.getDate());
            entity.setOptdate(entity.getCredate());
            vehicleInfoService.save(entity);
            return new ResponseMessage("200", "新增成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @PutMapping(path = "/mini-program/{uid}")
    @ResponseBody
    public ResponseMessage update(@RequestBody VehicleInfo entity, @PathVariable String uid,
        @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            VehicleInfo vehicleInfo = vehicleInfoService.findByUID(uid);
            if (vehicleInfo == null) {
                return new ResponseMessage("404", "对象不存在");
            }
            if (vehicleInfo.getOptdate() != null && vehicleInfo.getOptdate().compareTo(entity.getOptdate()) != 0) {
                return new ResponseMessage("409", "修改冲突");
            }
            entity.setOptdate(BaseLib.getDate());
            vehicleInfoService.save(entity);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @DeleteMapping(path = "/mini-program/{uid}")
    @ResponseBody
    public ResponseMessage delete(@PathVariable String uid, @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            VehicleInfo entity = vehicleInfoService.findByUID(uid);
            if (entity == null) {
                return new ResponseMessage("404", "更新对象不存在");
            }
            vehicleInfoService.deleteById(entity.getId());
            return new ResponseMessage("200", "删除成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @GetMapping(path = "/mini-program")
    @ResponseBody
    public ResponseData<VehicleInfo> findBySessionId(@RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        ResponseData<VehicleInfo> ret;
        if (wechatSession != null) {
            List<VehicleInfo> data = vehicleInfoService.findByOpenId(wechatSession.getOpenId());
            ret = new ResponseData<>("200", "success");
            ret.setData(data);
            ret.setCount(data != null ? data.size() : 0);
        } else {
            ret = new ResponseData<>("403", "Get SessionInfo Failure");
        }
        return ret;
    }

}
