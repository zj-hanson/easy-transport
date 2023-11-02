package com.lightshell.transport.controller;

import java.util.List;
import java.util.Map;

import com.lightshell.transport.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lightshell.comm.BaseLib;
import com.lightshell.transport.common.ResponseData;
import com.lightshell.transport.common.ResponseMessage;
import com.lightshell.transport.service.TenantServiceImpl;
import com.lightshell.transport.service.TransportTimeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping({"/tenant"})
public class TenantController {

    @Autowired
    private TenantServiceImpl tenantService;

    @Autowired
    private TransportTimeServiceImpl transportTimeService;

    @PostMapping()
    @ResponseBody
    public ResponseMessage add(@RequestBody Tenant entity) {
        try {
            entity.setCredate(BaseLib.getDate());
            tenantService.save(entity);
            return new ResponseMessage("201", "新增成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", ex.getMessage());
        }
    }

    @PutMapping(path = "/{uid}")
    @ResponseBody
    public ResponseMessage update(@RequestBody Tenant entity, @PathVariable String uid) {
        try {
            Tenant tenant = tenantService.findByUID(uid);
            if (tenant == null) {
                return new ResponseMessage("404", "对象不存在");
            }
            if (tenant.getOptdate() != null && tenant.getOptdate().compareTo(entity.getOptdate()) != 0) {
                return new ResponseMessage("409", "修改冲突");
            }
            entity.setOptdate(BaseLib.getDate());
            tenantService.save(entity);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", ex.getMessage());
        }
    }

    @DeleteMapping(path = "/{uid}/user/{parent}")
    @ResponseBody
    public ResponseMessage deleteByUIDAndParentId(@PathVariable String uid, @PathVariable String parent) {
        try {
            Tenant entity = tenantService.findByUIDAndParentId(uid, parent);
            if (entity == null) {
                return new ResponseMessage("404", "删除对象不存在");
            }
            tenantService.deleteByUID(uid);
            return new ResponseMessage("200", "删除成功");
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseMessage("500", ex.toString());
        }
    }

    @GetMapping(path = "/user/{uid}/pagination/{f}/{s}")
    @ResponseBody
    public ResponseData<Tenant> findByUserUID(@PathVariable String uid, @PathVariable String f,
        @MatrixVariable(pathVar = "f", required = false) Map<String, Object> filters, @PathVariable String s,
        @MatrixVariable(pathVar = "s", required = false) Map<String, String> sorts,
        @RequestParam("equal") boolean equal, @RequestParam("offset") Integer offset,
        @RequestParam("pageSize") Integer pageSize) {
        ResponseData<Tenant> ret;
        try {
            if (!filters.containsKey("parentId")) {
                filters.put("parentId", uid);
            }
            List<Tenant> data = tenantService.findAll(filters, offset, pageSize, sorts, equal);
            long cnt = transportTimeService.getCount(filters, equal);
            ret = new ResponseData<>("200", "success");
            ret.setData(data);
            ret.setCount(cnt);
        } catch (Exception ex) {
            return new ResponseData<>("500", ex.getMessage());
        }
        return ret;
    }

    @GetMapping(path = "/{uid}/transport-time/pagination/{f}/{s}")
    @ResponseBody
    public ResponseData<TransportTime> findTransportTimeByFilters(@PathVariable String uid, @PathVariable String f,
        @MatrixVariable(pathVar = "f", required = false) Map<String, Object> filters, @PathVariable String s,
        @MatrixVariable(pathVar = "s", required = false) Map<String, String> sorts,
        @RequestParam("equal") boolean equal, @RequestParam("offset") Integer offset,
        @RequestParam("pageSize") Integer pageSize) {

        ResponseData<TransportTime> ret;
        try {
            List<TransportTime> data = transportTimeService.findAll(filters, offset, pageSize, sorts, equal);
            long cnt = transportTimeService.getCount(filters, equal);
            ret = new ResponseData<>("200", "success");
            ret.setData(data);
            ret.setCount(cnt);
        } catch (Exception ex) {
            return new ResponseData<>("500", ex.getMessage());
        }
        return ret;
    }

}
