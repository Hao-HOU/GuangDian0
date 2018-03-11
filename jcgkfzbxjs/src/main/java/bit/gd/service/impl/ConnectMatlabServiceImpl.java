package bit.gd.service.impl;

import bit.gd.common.Const;
import bit.gd.common.ServerResponse;
import bit.gd.common.SmoSingleton;
import bit.gd.dao.GDParameterSmoMapper;
import bit.gd.dao.GDResultSmoMapper;
import bit.gd.pojo.GDParameterSmo;
import bit.gd.pojo.GDResultSmo;
import bit.gd.pojo.GDSimulationRecord;
import bit.gd.service.IConnectMatlabService;
import bit.gd.service.IDataPersistenceService;
import bit.gd.service.IFileService;
import bit.gd.util.PropertiesUtil;
import com.mathworks.toolbox.javabuilder.MWException;
import euvsmosyy.SMO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * Created by Hao HOU on 2018/3/6.
 */
@Service
public class ConnectMatlabServiceImpl implements IConnectMatlabService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IConnectMatlabService.class);

    private static final String matlabOutputPath = PropertiesUtil.getProperty("matlab.output.path");

    @Autowired
    IDataPersistenceService iDataPersistenceService;

    @Autowired
    IFileService iFileService;

    @Autowired
    GDParameterSmoMapper gdParameterSmoMapper;

    @Autowired
    GDResultSmoMapper gdResultSmoMapper;

    public ServerResponse executeSmoSimulation(GDParameterSmo gdParameterSmo) {
        Date startTime = new Date();

        if (!SmoSingleton.INSTANCE.singletonExecuteSMO(gdParameterSmo)) {
            gdParameterSmoMapper.deleteByPrimaryKey(gdParameterSmo.getId());
            return ServerResponse.createByErrorMessage("仿真失败");
        }

//        try {
//            Thread.sleep(10000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Date endTime = new Date();

        Subject subject = SecurityUtils.getSubject();

        LOGGER.info("存储仿真结果...");
        GDResultSmo gdResultSmo = new GDResultSmo();
        gdResultSmo.setParametersId(gdParameterSmo.getId());
        gdResultSmo.setUserNo((String) subject.getPrincipal());
        gdResultSmo = fillGDResultSmoFilepath(gdResultSmo);

        if (iDataPersistenceService.storeSmoResult(gdResultSmo) == null) {
            gdParameterSmoMapper.deleteByPrimaryKey(gdParameterSmo.getId());
            return ServerResponse.createByErrorMessage("仿真结果存储失败");
        }

        LOGGER.info("存入历史记录...");
        GDSimulationRecord gdSimulationRecord = new GDSimulationRecord();

        gdSimulationRecord.setUserNo((String) subject.getPrincipal());
        gdSimulationRecord.setModuleName(Const.Module.MODULE_SMO);
        gdSimulationRecord.setParametersId(gdParameterSmo.getId());
        gdSimulationRecord.setResultId(gdResultSmo.getId());
        gdSimulationRecord.setStartTime(startTime);
        gdSimulationRecord.setEndTime(endTime);

        if (iDataPersistenceService.storeSimulationRecord(gdSimulationRecord) == null) {
            gdParameterSmoMapper.deleteByPrimaryKey(gdParameterSmo.getId());
            gdResultSmoMapper.deleteByPrimaryKey(gdResultSmo.getId());
            return ServerResponse.createByErrorMessage("仿真记录存储失败");
        }

        return ServerResponse.createBySuccess("仿真成功", gdResultSmo);
    }

    private GDResultSmo fillGDResultSmoFilepath(GDResultSmo gdResultSmo) {
        gdResultSmo.setErrorMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Error_Mat, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setErrorConvergencePng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Error_Convergence_Png, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setErrorWeightMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Error_Weight_Mat, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setMaskBinaryPng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Mask_Binary_Png, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setMaskPatternMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Mask_Pattern_Mat, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setPrintImageMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Print_Image_Mat, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setPrintImagePng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Print_Image_Png, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setSourcePatternMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Source_Pattern_Mat, matlabOutputPath, Const.RESULT_PATH_SMO));
        gdResultSmo.setSourcePatternPng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Source_Pattern_Png, matlabOutputPath, Const.RESULT_PATH_SMO));

        return gdResultSmo;
    }
}
