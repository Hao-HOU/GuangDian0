package bit.gd.controller;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDParameterSmo;
import bit.gd.service.IConnectMatlabService;
import bit.gd.service.IDataPersistenceService;
import bit.gd.vo.SimulatedVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Hao HOU on 2018/3/6.
 */
@Controller
@RequestMapping("/api")
public class ConnectMatlabController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectMatlabController.class);

    @Autowired
    IConnectMatlabService iConnectMatlabService;

    @Autowired
    IDataPersistenceService iDataPersistenceService;

    @RequestMapping("smo_simulate.do")
    @ResponseBody
    public ServerResponse smoSimulate(@RequestBody GDParameterSmo gdParameterSmo) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN) || subject.hasRole(Const.Role.ROLE_SMO)) {
            gdParameterSmo.setUserNo((String) subject.getPrincipal());

            if (iDataPersistenceService.storeSmoParameters(gdParameterSmo) != null) {
                LOGGER.info("用户[{}]运行SMO模块所用参数存储成功，仿真开始...", subject.getPrincipal());
                return iConnectMatlabService.executeSmoSimulation(gdParameterSmo);
            } else {
                LOGGER.info("用户[{}]运行SMO模块所用参数存储失败", subject.getPrincipal());
                return ServerResponse.createByErrorMessage("参数存储失败，仿真不能开始");
            }
        } else {
            return ServerResponse.createByErrorMessage("无权限使用该模块，请联系管理员");
        }
    }

    @RequestMapping("smo_parameters_simulated.do")
    @ResponseBody
    public ServerResponse checkSmoParametersSimulated(@RequestBody GDParameterSmo gdParameterSmo) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN) || subject.hasRole(Const.Role.ROLE_SMO)) {
            if (iDataPersistenceService.checkSmoParametersSimulated(gdParameterSmo) != null) {
                SimulatedVo simulatedVo = iDataPersistenceService.getSimulatedVo(Const.Module.MODULE_SMO,
                        iDataPersistenceService.checkSmoParametersSimulated(gdParameterSmo).getId());
                if (simulatedVo == null) {
                    return ServerResponse.createByErrorMessage("获取相同参数的历史仿真结果失败");
                }
                return ServerResponse.createBySuccessCodeMessage(ResponseCode.SIMULATED.getCode(),
                        "参数已仿真过", simulatedVo);
            } else {
                return ServerResponse.createBySuccessMessage("参数未仿真过");
            }
        } else {
            return ServerResponse.createByErrorMessage("无权限使用该模块，请联系管理员");
        }
    }
}
