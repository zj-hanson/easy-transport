package com.lightshell.transport.controller;

import com.lightshell.comm.BaseLib;
import com.lightshell.transport.common.ResponseData;
import com.lightshell.transport.common.ResponseMessage;
import com.lightshell.transport.common.ResponseObject;
import com.lightshell.transport.entity.TransportInfo;
import com.lightshell.transport.entity.TransportTime;
import com.lightshell.transport.entity.WechatSession;
import com.lightshell.transport.service.TransportInfoServiceImpl;
import com.lightshell.transport.service.TransportTimeServiceImpl;
import com.lightshell.transport.service.WechatSessionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping({"/transport-info"})
public class TransportInfoController {

    @Autowired
    private WechatSessionServiceImpl wechatSessionService;

    @Autowired
    private TransportInfoServiceImpl transportInfoService;

    @Autowired
    private TransportTimeServiceImpl transportTimeService;

    @PostMapping(path = "/mini-program")
    @ResponseBody
    public ResponseMessage addByMiniPrg(@RequestBody TransportInfo entity, @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            entity.setCredate(BaseLib.getDate());
            entity.setOptdate(entity.getCredate());
            transportInfoService.save(entity);
            return new ResponseMessage("200", "新增成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @PutMapping(path = "/mini-program/{uid}")
    @ResponseBody
    public ResponseMessage updateByMiniPrg(@RequestBody TransportInfo entity, @PathVariable String uid,
        @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            TransportInfo transportInfo = transportInfoService.findByUID(uid);
            if (transportInfo == null) {
                return new ResponseMessage("404", "对象不存在");
            }
            if (transportInfo.getOptdate() != null && transportInfo.getOptdate().compareTo(entity.getOptdate()) != 0) {
                return new ResponseMessage("409", "修改冲突");
            }
            entity.setOptdate(BaseLib.getDate());
            transportInfoService.save(entity);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @PutMapping(path = "/mini-program/transport-time/{uid}")
    @ResponseBody
    public ResponseMessage updateByMiniPrg(@RequestBody TransportTime entity, @PathVariable String uid,
        @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        if (wechatSession == null) {
            return new ResponseMessage("404", "Get SessionInfo Failure");
        }
        try {
            TransportTime transportInfo = transportInfoService.findTransportTimeByUID(uid);
            if (transportInfo == null) {
                return new ResponseMessage("404", "对象不存在");
            }
            transportInfoService.saveTransportTime(entity);
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
            TransportInfo entity = transportInfoService.findByUID(uid);
            if (entity == null) {
                return new ResponseMessage("404", "更新对象不存在");
            }
            if (!wechatSession.getOpenId().equals(entity.getOpenId())) {
                return new ResponseMessage("403", "OpenId不匹配");
            }
            transportInfoService.deleteById(entity.getId());
            return new ResponseMessage("200", "删除成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @GetMapping(path = "/mini-program")
    @ResponseBody
    public ResponseData<TransportInfo> findBySessionId(@RequestParam String session, @RequestParam String status) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        ResponseData<TransportInfo> ret;
        if (wechatSession != null) {
            List<TransportInfo> data = transportInfoService.findByOpenIdAndStatus(wechatSession.getOpenId(), status);
            ret = new ResponseData<>("200", "success");
            ret.setData(data);
            ret.setCount(data != null ? data.size() : 0);
        } else {
            ret = new ResponseData<>("403", "Get SessionInfo Failure");
        }
        return ret;
    }

    @GetMapping(path = "/mini-program/transport-time/{uid}")
    @ResponseBody
    public ResponseObject<TransportTime> findTransportTimeByUId(@PathVariable String uid,
        @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        ResponseObject<TransportTime> ret;
        if (wechatSession != null) {
            TransportTime entity = transportInfoService.findTransportTimeByUID(uid);
            if (entity == null) {
                return new ResponseObject<>("404", "对象不存在");
            }
            ret = new ResponseObject<>("200", "success", entity);
        } else {
            ret = new ResponseObject<>("403", "Get SessionInfo Failure");
        }
        return ret;
    }

    @GetMapping(path = "/mini-program/transport-time/pagination/{f}/{s}")
    @ResponseBody
    public ResponseData<TransportTime> findTransportTimeByFilters(@PathVariable String f,
        @MatrixVariable(pathVar = "f", required = false) Map<String, Object> filters, @PathVariable String s,
        @MatrixVariable(pathVar = "s", required = false) Map<String, String> sorts,
        @RequestParam("equal") boolean equal, @RequestParam("offset") Integer offset,
        @RequestParam("pageSize") Integer pageSize, @RequestParam String session) {
        WechatSession wechatSession = wechatSessionService.findBySessionId(session);
        ResponseData<TransportTime> ret;
        if (wechatSession != null) {
            try {
                List<TransportTime> data = transportTimeService.findAll(filters, offset, pageSize, sorts, equal);
                long cnt = transportTimeService.getCount(filters, equal);
                ret = new ResponseData<>("200", "success");
                ret.setData(data);
                ret.setCount(cnt);
            } catch (Exception ex) {
                return new ResponseData<>("500", ex.getMessage());
            }
        } else {
            return new ResponseData<>("403", "Get SessionInfo Failure");
        }
        return ret;
    }

    @GetMapping(path = "/transport-time/pagination/{f}/{s}")
    @ResponseBody
    public ResponseData<TransportTime> findTransportTimeByTenant(@PathVariable String f,
        @MatrixVariable(pathVar = "f", required = false) Map<String, Object> filters, @PathVariable String s,
        @MatrixVariable(pathVar = "s", required = false) Map<String, String> sorts,
        @RequestParam("equal") boolean equal, @RequestParam("offset") Integer offset,
        @RequestParam("pageSize") Integer pageSize) {
        ResponseData<TransportTime> ret;
        if (filters.containsKey("tenantId")) {
            try {
                List<TransportTime> data = transportTimeService.findAll(filters, offset, pageSize, sorts, equal);
                long cnt = transportTimeService.getCount(filters, equal);
                ret = new ResponseData<>("200", "success");
                ret.setData(data);
                ret.setCount(cnt);
                return ret;
            } catch (Exception ex) {
                return new ResponseData<>("500", ex.getMessage());
            }
        } else {
            return new ResponseData<>("404", "Get Tenant Failure");
        }
    }

}
