package bit.gd.controller;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.service.ISimulationRecordService;
import bit.gd.service.IUserManageService;
import bit.gd.vo.RecordDetailRequest;
import bit.gd.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Hao HOU on 2018/3/8.
 */
@Controller
@RequestMapping("/api")
public class SimulationRecordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationRecordController.class);
    @Autowired
    IUserManageService iUserManageService;

    @Autowired
    ISimulationRecordService iSimulationRecordService;

    @RequestMapping("get_all_simulation_records.do")
    @ResponseBody
    public ServerResponse getAllSimulationRecords(@RequestBody Map<String,Object> map) {
        if (map.get("pageNum") == null || map.get("pageSize") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }
        int pageNum = (int) map.get("pageNum");
        int pageSize = (int) map.get("pageSize");

        Subject subject = SecurityUtils.getSubject();
        UserVo currentUser = iUserManageService.getCurrentUserInfo((String) subject.getPrincipal());
        if (currentUser.getRoles().isEmpty()) {
            return ServerResponse.createByErrorMessage("没有使用任何模块的权限，无法查看仿真历史");
        } else {
            LOGGER.info("用户[{}]查看了仿真历史", subject.getPrincipal());
            if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
               return iSimulationRecordService.getAllSimulationRecords(pageNum, pageSize);
            } else {
                List<String> roles = new ArrayList<>();
                roles.addAll(currentUser.getRoles());
                return iSimulationRecordService.getAuthorizedModulesSimulationRecords(pageNum, pageSize, roles);
            }
        }
    }

    @RequestMapping("get_record_parameters.do")
    @ResponseBody
    public ServerResponse getRecordParameters(@RequestBody Map<String,Object> map) {
        if (map.get("parametersId") == null || map.get("moduleName") == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                    "参数错误");
        }

        int parametersId = (int) map.get("parametersId");
        String moduleName = map.get("moduleName").toString();

        return iSimulationRecordService.getSimulationRecordParameters(parametersId, moduleName);
    }

    @RequestMapping("get_record_detail.do")
    @ResponseBody
    public ServerResponse getRecordDetail(@RequestBody RecordDetailRequest recordDetailRequest) {
        return iSimulationRecordService.getSimulationRecordDetail(recordDetailRequest);
    }
}
