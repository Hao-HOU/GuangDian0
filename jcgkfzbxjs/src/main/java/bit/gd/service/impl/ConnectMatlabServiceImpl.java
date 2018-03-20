package bit.gd.service.impl;

import bit.gd.common.Const;
import bit.gd.common.ResponseCode;
import bit.gd.common.ServerResponse;
import bit.gd.dao.GDParameterSmoMapper;
import bit.gd.dao.GDResultSmoMapper;
import bit.gd.dao.GDRunningStateMapper;
import bit.gd.pojo.GDParameterSmo;
import bit.gd.pojo.GDResultSmo;
import bit.gd.pojo.GDSimulationRecord;
import bit.gd.service.IConnectMatlabService;
import bit.gd.service.IDataPersistenceService;
import bit.gd.service.IFileService;
import bit.gd.util.PropertiesUtil;
import com.mathworks.toolbox.javabuilder.MWException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syysmo.Smo;

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

    @Autowired
    GDRunningStateMapper gdRunningStateMapper;

    public ServerResponse executeSmoSimulation(GDParameterSmo gdParameterSmo) {
        Subject subject = SecurityUtils.getSubject();
        String userNo = (String) subject.getPrincipal();
        gdRunningStateMapper.updateByUserNoAndModuleName(userNo, Const.Module.MODULE_SMO, Const.RunningState.RUNNING);

        File outputDir = new File(matlabOutputPath + userNo);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        Date startTime = new Date();

        Smo smo = null;
        try {
            smo = new Smo();
            smo.EUV_Pixelated_SMO_MAIN(4, gdParameterSmo.getCoreNum(), gdParameterSmo.getMaskDimension(),
                    gdParameterSmo.getPixelSize(), gdParameterSmo.getReflect(), gdParameterSmo.getAbsorb(),
                    gdParameterSmo.getShadowNear(), gdParameterSmo.getShadowFar(), gdParameterSmo.getWavelength(),
                    gdParameterSmo.getSigmaIn(), gdParameterSmo.getSigmaOut(),
                    gdParameterSmo.getNa(), gdParameterSmo.getRatio(), gdParameterSmo.getStepSource(),
                    gdParameterSmo.getOmegaSourceQua(), gdParameterSmo.getStepMaskMain(), gdParameterSmo.getStepMaskSraf(),
                    gdParameterSmo.getOmegaMaskQua(), gdParameterSmo.getMaxloopSmo(), gdParameterSmo.getThreshold(),
                    gdParameterSmo.getTr(), gdParameterSmo.getaSource(),
                    PropertiesUtil.getProperty("ftp.server.path") + Const.UPLOAD_FILE_PATH + File.separator + gdParameterSmo.getInputMask(),
                    outputDir + File.separator);
        } catch (MWException e) {
            e.printStackTrace();
            gdParameterSmoMapper.deleteByPrimaryKey(gdParameterSmo.getId());
            gdRunningStateMapper.updateByUserNoAndModuleName(userNo, Const.Module.MODULE_SMO, Const.RunningState.IDLE);
            return ServerResponse.createByErrorMessage("仿真失败");
        }  finally {
            if (smo != null) {
                smo.dispose();
            }
        }

//        Struct maskPa = new Struct("N", gdParameterSmo.getMaskDimension(),
//                "Pixel", gdParameterSmo.getPixelSize(), "reflect", gdParameterSmo.getReflect(),
//                "absorb", gdParameterSmo.getAbsorb(), "b_max_near_shadowing", gdParameterSmo.getShadowNear(),
//                "b_max_far_shadowing", gdParameterSmo.getShadowFar());
//
//        try {
//            Smo smonew = new Smo();
//            smonew.EUV_Pixelated_SMO_MAIN(4, maskPa, gdParameterSmo.getWavelength(),
//                    gdParameterSmo.getSigmaIn(), gdParameterSmo.getSigmaOut(), gdParameterSmo.getTis(),
//                    gdParameterSmo.getNa(), gdParameterSmo.getRatio(), gdParameterSmo.getStepSource(),
//                    gdParameterSmo.getOmegaSourceQua(), gdParameterSmo.getStepMaskMain(), gdParameterSmo.getStepMaskSraf(),
//                    gdParameterSmo.getOmegaMaskQua(), gdParameterSmo.getMaxloopSmo(), gdParameterSmo.getThreshold(),
//                    gdParameterSmo.getTr(), gdParameterSmo.getaSource(),
//                    PropertiesUtil.getProperty("ftp.server.path") + Const.UPLOAD_FILE_PATH + File.separator + gdParameterSmo.getInputMask());
//        } catch (MWException e) {
//            e.printStackTrace();
//            gdParameterSmoMapper.deleteByPrimaryKey(gdParameterSmo.getId());
//            gdRunningStateMapper.updateByUserNoAndModuleName(userNo, Const.Module.MODULE_SMO, Const.RunningState.IDLE);
//            return ServerResponse.createByErrorMessage("仿真失败");
//        }
//
//        try {
//
//            smo.EUV_Pixelated_SMO_MAIN(4, gdParameterSmo.getCoreNum(), gdParameterSmo.getMaskDimension(),
//                    gdParameterSmo.getPixelSize(), gdParameterSmo.getReflect(), gdParameterSmo.getAbsorb(),
//                    gdParameterSmo.getShadowNear(), gdParameterSmo.getShadowFar(), gdParameterSmo.getWavelength(),
//                    gdParameterSmo.getSigmaIn(), gdParameterSmo.getSigmaOut(), gdParameterSmo.getTis(),
//                    gdParameterSmo.getNa(), gdParameterSmo.getRatio(), gdParameterSmo.getStepSource(),
//                    gdParameterSmo.getOmegaSourceQua(), gdParameterSmo.getStepMaskMain(), gdParameterSmo.getStepMaskSraf(),
//                    gdParameterSmo.getOmegaMaskQua(), gdParameterSmo.getMaxloopSmo(), gdParameterSmo.getThreshold(),
//                    gdParameterSmo.getTr(), gdParameterSmo.getaSource(),
//                    PropertiesUtil.getProperty("ftp.server.path") + Const.UPLOAD_FILE_PATH + File.separator + gdParameterSmo.getInputMask());
//        } catch (MWException e) {
//            e.printStackTrace();
//            gdParameterSmoMapper.deleteByPrimaryKey(gdParameterSmo.getId());
//            gdRunningStateMapper.updateByUserNoAndModuleName(userNo, Const.Module.MODULE_SMO, Const.RunningState.IDLE);
//            return ServerResponse.createByErrorMessage("仿真失败");
//        } finally {
//            if (smo != null) {
//                smo.dispose();
//            }
//        }

        Date endTime = new Date();

        LOGGER.info("存储仿真结果...");
        GDResultSmo gdResultSmo = new GDResultSmo();
        gdResultSmo.setParametersId(gdParameterSmo.getId());
        gdResultSmo.setUserNo(userNo);
        gdResultSmo = fillGDResultSmoFilepath(gdResultSmo);

        if (iDataPersistenceService.storeSmoResult(gdResultSmo) == null) {
            gdParameterSmoMapper.deleteByPrimaryKey(gdParameterSmo.getId());
            gdRunningStateMapper.updateByUserNoAndModuleName(userNo, Const.Module.MODULE_SMO, Const.RunningState.IDLE);
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
            gdRunningStateMapper.updateByUserNoAndModuleName(userNo, Const.Module.MODULE_SMO, Const.RunningState.IDLE);
            return ServerResponse.createByErrorMessage("仿真记录存储失败");
        }

        gdRunningStateMapper.executeSuccessResetAndPlus(userNo, Const.Module.MODULE_SMO, Const.RunningState.IDLE);
        return ServerResponse.createBySuccessCodeMessage(ResponseCode.FINISHED.getCode(), "仿真成功", gdResultSmo);
    }

    private GDResultSmo fillGDResultSmoFilepath(GDResultSmo gdResultSmo) {
        String sourcePath = matlabOutputPath + gdResultSmo.getUserNo() + File.separator;
        gdResultSmo.setErrorMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Error_Mat, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setErrorConvergencePng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Error_Convergence_Png, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setErrorConvergenceWeightPng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Error_Convergence_Weight_Png, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setErrorWeightMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Error_Weight_Mat, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setMaskPatternPng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Mask_Pattern_Png, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setMaskPatternMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Mask_Pattern_Mat, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setPrintImageMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Print_Image_Mat, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setPrintImagePng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Print_Image_Png, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setSourcePatternMat(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Source_Pattern_Mat, sourcePath, Const.RESULT_PATH_SMO));
        gdResultSmo.setSourcePatternPng(iFileService
                .uploadMatlabOutputFile(Const.SmoMatlabOutputFilename.SMO_Source_Pattern_Png, sourcePath, Const.RESULT_PATH_SMO));

        return gdResultSmo;
    }
}
