package com.lightshell.transport.controller;

import java.util.*;

import com.lightshell.transport.common.*;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lightshell.comm.BaseLib;
import com.lightshell.transport.entity.SystemUser;
import com.lightshell.transport.service.SystemUserServiceImpl;

/**
 * @author kevindong
 */
@RestController
@RequestMapping(path = "/user")
public class SystemUserController {

    @Autowired
    private SystemUserServiceImpl systemUserServiceImpl;

    @Autowired
    private OAuth2Util oauth2Util;

    @PostMapping()
    @ResponseBody
    public ResponseMessage add(@RequestBody SystemUser entity) {
        try {
            String pwd = Arrays.toString(Base64.decodeBase64(entity.getPassword()));
            entity.setPassword(BaseLib.securityMD5(pwd));
            entity.setCredate(BaseLib.getDate());
            systemUserServiceImpl.save(entity);
            return new ResponseMessage("201", "新增成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", ex.getMessage());
        }
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseObject<Login> login(@RequestBody Login entity) {
        ResponseObject<Login> res = null;
        String userid, pwd, type;
        userid = entity.getUserName();
        pwd = new String(Base64.decodeBase64(entity.getPassword()));
        type = entity.getType();
        Login login = new Login();
        try {
            SystemUser user = systemUserServiceImpl.findByUserid(userid);
            if (user == null) {
                login.setStatus("error");
                return new ResponseObject<>("404", "此用戶不存在", login);
            }
            pwd = BaseLib.securityMD5(pwd);
            if (pwd.equals(user.getPassword())) {
                JSONObject jsonObject = oauth2Util.getToken();
                login.setUserName(userid);
                login.setStatus("success");
                login.setType(type);
                login.setUID(user.getUID());
                login.getCurrentAuthority().add("admin");
                if (jsonObject != null && jsonObject.has("access_token")) {
                    login.setAccessToken(jsonObject.getString("access_token"));
                    login.setExpires(jsonObject.getInt("expires_in"));
                }
                res = new ResponseObject<>("200", "验证成功", login);
            } else {
                login.setStatus("error");
                login.setType(type);
                res = new ResponseObject<>("401", "验证失败", login);
            }
        } catch (Exception ex) {
            login.setStatus("error");
            login.setType(type);
            res = new ResponseObject<>("500", ex.getMessage(), login);
        }
        return res;

    }

    @PostMapping(path = "/token")
    @ResponseBody
    public ResponseObject<Token> getToken() {
        ResponseObject<Token> res = null;
        try {
            JSONObject jsonObject = oauth2Util.getToken();
            if (jsonObject != null && jsonObject.has("access_token")) {
                Token token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpires(jsonObject.getInt("expires_in"));
                res = new ResponseObject<>("200", "success", token);
            }
        } catch (Exception ex) {
            res = new ResponseObject<>("500", ex.getMessage());
        }
        return res;
    }

    @PutMapping(path = "/{uid}")
    @ResponseBody
    public ResponseMessage update(@RequestBody SystemUser entity, @PathVariable String uid) {
        try {
            SystemUser user = systemUserServiceImpl.findByUID(uid);
            if (user == null) {
                return new ResponseMessage("404", "对象不存在");
            }
            entity.setOptdate(BaseLib.getDate());
            systemUserServiceImpl.save(entity);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", ex.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseMessage delete(@PathVariable String id, @RequestParam String appid, @RequestParam String token) {
        try {
            systemUserServiceImpl.deleteByUID(id);
            return new ResponseMessage("200", "删除成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", ex.getMessage());
        }
    }

    @GetMapping(path = "/fetch")
    @ResponseBody
    public ResponseObject<Map<String, Object>> fetchYun(@RequestParam("uid") String uid) {
        ResponseObject<Map<String, Object>> res;
        SystemUser systemUser = systemUserServiceImpl.findByUID(uid);
        if (systemUser != null) {
            HashMap<String, Object> user = new HashMap<>();
            user.put("userid", systemUser.getUserid());
            user.put("name", systemUser.getUsername());
            user.put("avatar", "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
            user.put("email", systemUser.getEmail());
            user.put("deptno", "开发部");
            user.put("group", "灵犀相通");
            user.put("title", "斗师");
            user.put("phone", systemUser.getPhone());
            user.put("address", "灵溪大道");
            user.put("uid", systemUser.getUID());

            List<Map<String, String>> tags = new ArrayList<>();
            Map<String, String> tag;
            tag = new HashMap<>();
            tag.put("key", "0");
            tag.put("label", "系统顾问");
            tags.add(tag);
            tag = new HashMap<>();
            tag.put("key", "1");
            tag.put("label", "Java架构师");
            tags.add(tag);
            tag = new HashMap<>();
            tag.put("key", "3");
            tag.put("label", "React先行者");
            tags.add(tag);
            user.put("tags", tags);

            try {
                // 首页雷达图数据
                List<Map<String, Object>> radarData = new ArrayList<>();
                Map<String, Object> chart;

                chart = new HashMap<>();
                chart.put("name", "个人");
                chart.put("label", "技术");
                chart.put("value", 10);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "个人");
                chart.put("label", "口碑");
                chart.put("value", 8);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "个人");
                chart.put("label", "产量");
                chart.put("value", 4);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "个人");
                chart.put("label", "贡献");
                chart.put("value", 5);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "个人");
                chart.put("label", "热度");
                chart.put("value", 7);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "部门");
                chart.put("label", "技术");
                chart.put("value", 5);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "部门");
                chart.put("label", "口碑");
                chart.put("value", 7);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "部门");
                chart.put("label", "产量");
                chart.put("value", 10);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "部门");
                chart.put("label", "贡献");
                chart.put("value", 5);
                radarData.add(chart);

                chart = new HashMap<>();
                chart.put("name", "部门");
                chart.put("label", "热度");
                chart.put("value", 2);
                radarData.add(chart);

                res = new ResponseObject<>("200", "success", user);
                res.getExtData().put("menu", null);
                res.getExtData().put("radarData", radarData);

                List<String> auth = new ArrayList<>();
                auth.add("admin");
                res.getExtData().put("currentAuthority", auth);

                return res;
            } catch (Exception ex) {
                res = new ResponseObject<>("500", "系统错误");
                res.getExtData().put("error", ex.toString());
                return res;
            }
        } else {
            return new ResponseObject<>("404", "此用戶不存在", null);
        }

    }

    @GetMapping(path = "/pagination/{f}/{s}")
    @ResponseBody
    public ResponseData findAll(@PathVariable String f,
        @MatrixVariable(pathVar = "f", required = false) Map<String, Object> filters, @PathVariable String s,
        @MatrixVariable(pathVar = "s", required = false) Map<String, String> sorts,
        @RequestParam("offset") Integer offset, @RequestParam("pageSize") Integer pageSize) {

        ResponseData res;
        try {
            List<SystemUser> data = systemUserServiceImpl.findAll(filters, offset, pageSize, sorts, true);
            long cnt = systemUserServiceImpl.getCount(filters, false);
            res = new ResponseData<>("200", "success");
            res.setData(data);
            res.setCount(cnt);
            return res;
        } catch (Exception ex) {
            return new ResponseData<>("500", ex.getMessage());
        }
    }

}
