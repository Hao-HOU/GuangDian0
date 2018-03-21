package bit.gd.service.impl;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDRunningState;
import bit.gd.service.IFileService;
import bit.gd.service.IRunningStateService;
import bit.gd.util.JMatIOUtil;
import bit.gd.util.PropertiesUtil;
import bit.gd.vo.OpcIntermediateFileVo;
import bit.gd.vo.SmoIntermediateFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by Hao HOU on 2018/3/21.
 */
@Service
public class RunningStateServiceImpl implements IRunningStateService {
    @Autowired
    IFileService iFileService;

    public ServerResponse getIntermediateFile(String moduleName, String userNo, GDRunningState gdRunningState) {
        switch (moduleName) {
            case Const.Module.MODULE_SMO:
                return getSmoIntermediateFile(userNo, gdRunningState);
            case Const.Module.MODULE_OPC:
                return getOpcIntermediateFile(userNo, gdRunningState);
            case Const.Module.MODULE_PDOD:
                return ServerResponse.createBySuccessMessage("PDOD模块暂未实现");
            case Const.Module.MODULE_SMPWO:
                return ServerResponse.createBySuccessMessage("SMPWO模块暂未实现");
            default:
                return ServerResponse.createByErrorMessage("模块名字错误");
        }
    }

    private ServerResponse getSmoIntermediateFile(String userNo, GDRunningState gdRunningState) {
        if (iFileService.copySmoIntermediateResult(userNo)) {
            SmoIntermediateFileVo smoIntermediateFileVo = new SmoIntermediateFileVo(userNo);
            double error = JMatIOUtil.getErrorMatValue( PropertiesUtil.getProperty("matlab.output.path.smo")
                    + userNo + File.separator + Const.SmoMatlabOutputFilename.SMO_Error_Mat);
            smoIntermediateFileVo.setError(error);

            double iterationCount = JMatIOUtil.getIterationCount(PropertiesUtil.getProperty("matlab.output.path.smo")
                    + userNo + File.separator + Const.SmoMatlabOutputFilename.SMO_Iteration_Times_Mat);
            smoIntermediateFileVo.setIterationCount(iterationCount);

            return ServerResponse.createBySuccessCodeMessage(ResponseCode.RUNNING.getCode(), "已有中间结果", smoIntermediateFileVo);
        } else {
            return ServerResponse.createBySuccessCodeMessage(ResponseCode.RUNNING.getCode(), "暂无中间结果", gdRunningState);
        }
    }

    private ServerResponse getOpcIntermediateFile(String userNo, GDRunningState gdRunningState) {
        if (iFileService.copyOpcIntermediateResult(userNo)) {
            OpcIntermediateFileVo opcIntermediateFileVo = new OpcIntermediateFileVo(userNo);
            double error = JMatIOUtil.getErrorMatValue( PropertiesUtil.getProperty("matlab.output.path.opc")
                    + userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Error_Mat);
            opcIntermediateFileVo.setError(error);

            double errorWeight =JMatIOUtil.getErrorWeight(PropertiesUtil.getProperty("matlab.output.path.opc")
                    + userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Error_Weight_Mat);
            opcIntermediateFileVo.setErrorWeight(errorWeight);

            double iterationCount = JMatIOUtil.getIterationCount(PropertiesUtil.getProperty("matlab.output.path.opc")
                    + userNo + File.separator + Const.OpcMatlabOutputFilename.OPC_Iteration_Times_Mat);
            opcIntermediateFileVo.setIterationCount(iterationCount);

            return ServerResponse.createBySuccessCodeMessage(ResponseCode.RUNNING.getCode(), "已有中间结果", opcIntermediateFileVo);
        } else {
            return ServerResponse.createBySuccessCodeMessage(ResponseCode.RUNNING.getCode(), "暂无中间结果", gdRunningState);
        }
    }
}
