package bit.gd.controller;

import add.Matlab2Java;
import bit.gd.common.Const;
import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDParameterSmo;
import bit.gd.service.IConnectMatlabService;
import bit.gd.service.IDataPersistenceService;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;
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
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectMatlabController.class);

    @Autowired
    IConnectMatlabService iConnectMatlabService;

    @Autowired
    IDataPersistenceService iDataPersistenceService;

    @RequestMapping("smo.do")
    @ResponseBody
    public ServerResponse smo(@RequestBody GDParameterSmo gdParameterSmo) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Const.Role.ROLE_ADMIN) || subject.hasRole(Const.Role.ROLE_SMO)) {
            gdParameterSmo.setUserNo((String) subject.getPrincipal());
            if (iDataPersistenceService.checkSmoParametersSimulated(gdParameterSmo) != null) {
                return ServerResponse.createByErrorMessage("该组参数已仿真过，可直接查看");
            }
            if (iDataPersistenceService.storeSmoParameters(gdParameterSmo) > 0) {
                LOGGER.info("用户[{}]运行SMO模块所用参数存储成功，仿真开始...", subject.getPrincipal());
//                return iConnectMatlabService.firstTrySMO();
                return ServerResponse.createBySuccessMessage("测试参数传输，暂不运行仿真");
            } else {
                LOGGER.info("用户[{}]运行SMO模块所用参数存储失败", subject.getPrincipal());
                return ServerResponse.createByErrorMessage("参数存储失败，仿真不能开始");
            }
        } else {
            return ServerResponse.createByErrorMessage("无权限使用该模块，请联系管理员");
        }
    }
}
