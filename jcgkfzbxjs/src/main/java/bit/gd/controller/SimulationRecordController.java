package bit.gd.controller;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.service.ISimulationRecordService;
import bit.gd.service.IUserManageService;
import bit.gd.vo.RecordDetailRequest;
import bit.gd.vo.SearchSimulationRecordsRequest;
import bit.gd.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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
    public ServerResponse getAllSimulationRecords(@RequestBody SearchSimulationRecordsRequest searchSimulationRecordsRequest) {
        Subject subject = SecurityUtils.getSubject();
        UserVo currentUser = iUserManageService.getCurrentUserInfo((String) subject.getPrincipal());
        if (currentUser.getRoles().isEmpty()) {
            return ServerResponse.createByErrorMessage("没有使用任何模块的权限，无法查看仿真历史");
        } else {
            LOGGER.info("用户[{}]查看了仿真历史", subject.getPrincipal());
            String endDay = searchSimulationRecordsRequest.getEndDay();
            if (StringUtils.isNotBlank(endDay)) {
                searchSimulationRecordsRequest.setEndDay(endDay + Const.LAST_SECOND);
            }
            if (subject.hasRole(Const.Role.ROLE_ADMIN)) {
               return iSimulationRecordService.getAllSimulationRecords(searchSimulationRecordsRequest);
            } else {
                List<String> roles = new ArrayList<>();
                roles.addAll(currentUser.getRoles());
                searchSimulationRecordsRequest.setRoles(roles);
                return iSimulationRecordService.getAuthorizedModulesSimulationRecords(searchSimulationRecordsRequest);
            }
        }
    }

    @RequestMapping("get_record_detail.do")
    @ResponseBody
    public ServerResponse getRecordDetail(@RequestBody RecordDetailRequest recordDetailRequest) throws IOException {
        return iSimulationRecordService.getSimulationRecordDetail(recordDetailRequest);
    }

}
