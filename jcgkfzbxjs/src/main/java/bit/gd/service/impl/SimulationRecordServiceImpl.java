package bit.gd.service.impl;

import bit.gd.common.Const;
import bit.gd.common.ServerResponse;
import bit.gd.dao.*;
import bit.gd.pojo.*;
import bit.gd.service.ISimulationRecordService;
import bit.gd.util.FTPUtil;
import bit.gd.util.JMatIOUtil;
import bit.gd.util.PropertiesUtil;
import bit.gd.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hao HOU on 2018/3/8.
 */
@Service
public class SimulationRecordServiceImpl implements ISimulationRecordService{
    @Autowired
    GDSimulationRecordMapper gdSimulationRecordMapper;

    @Autowired
    GDUserMapper gdUserMapper;

    @Autowired
    GDParameterSmoMapper gdParameterSmoMapper;

    @Autowired
    GDResultSmoMapper gdResultSmoMapper;

    @Autowired
    GDParameterOpcMapper gdParameterOpcMapper;

    @Autowired
    GDResultOpcMapper gdResultOpcMapper;

    public ServerResponse<PageInfo> getAllSimulationRecords(SearchSimulationRecordsRequest searchSimulationRecordsRequest) {
        PageHelper.startPage(searchSimulationRecordsRequest.getPageNum(), searchSimulationRecordsRequest.getPageSize());

        List<GDSimulationRecord> gdSimulationRecordList = gdSimulationRecordMapper.selectAllSimulationRecords(searchSimulationRecordsRequest);
        List<SimulationRecordShowVo> simulationRecordShowVoList = new ArrayList<>();
        for (GDSimulationRecord gdSimulationRecord : gdSimulationRecordList) {
            SimulationRecordShowVo simulationRecordShowVo = assembleSimulationRecordShowVo(gdSimulationRecord);
            simulationRecordShowVoList.add(simulationRecordShowVo);
        }

        PageInfo pageInfo = new PageInfo(gdSimulationRecordList);
        pageInfo.setList(simulationRecordShowVoList);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<PageInfo> getAuthorizedModulesSimulationRecords(SearchSimulationRecordsRequest searchSimulationRecordsRequest) {
        PageHelper.startPage(searchSimulationRecordsRequest.getPageNum(), searchSimulationRecordsRequest.getPageSize());

        List<GDSimulationRecord> gdSimulationRecordList =
                gdSimulationRecordMapper.selectAuthorizedModulesSimulationRecords(searchSimulationRecordsRequest);
        List<SimulationRecordShowVo> simulationRecordShowVoList = new ArrayList<>();
        for (GDSimulationRecord gdSimulationRecord : gdSimulationRecordList) {
            SimulationRecordShowVo simulationRecordShowVo = assembleSimulationRecordShowVo(gdSimulationRecord);
            simulationRecordShowVoList.add(simulationRecordShowVo);
        }

        PageInfo pageInfo = new PageInfo(gdSimulationRecordList);
        pageInfo.setList(simulationRecordShowVoList);

        return ServerResponse.createBySuccess(pageInfo);

    }

    private SimulationRecordShowVo assembleSimulationRecordShowVo(GDSimulationRecord gdSimulationRecord) {
        SimulationRecordShowVo simulationRecordShowVo = new SimulationRecordShowVo();
        simulationRecordShowVo.setId(gdSimulationRecord.getId());
        simulationRecordShowVo.setUserNo(gdSimulationRecord.getUserNo());

        GDUser gdUser = gdUserMapper.selectByUserNo(gdSimulationRecord.getUserNo());

        simulationRecordShowVo.setUserName(gdUser.getName());
        simulationRecordShowVo.setPhone(gdUser.getPhone());

        simulationRecordShowVo.setModuleName(gdSimulationRecord.getModuleName());
        simulationRecordShowVo.setParametersId(gdSimulationRecord.getParametersId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simulationRecordShowVo.setStartTime(simpleDateFormat.format(gdSimulationRecord.getStartTime()));
        simulationRecordShowVo.setEndTime(simpleDateFormat.format(gdSimulationRecord.getEndTime()));

        simulationRecordShowVo.setResultId(gdSimulationRecord.getResultId());

        return simulationRecordShowVo;
    }

    public ServerResponse getSimulationRecordDetail(RecordDetailRequest recordDetailRequest) throws IOException {
        switch (recordDetailRequest.getModuleName()) {
            case Const.Module.MODULE_SMO:
                return ServerResponse.createBySuccess(getSmoSimulationRecordDetail(recordDetailRequest.getParametersId(),
                        recordDetailRequest.getResultId()));
            case Const.Module.MODULE_OPC:
                return ServerResponse.createBySuccess(getOpcSimulationRecordDetailVo(recordDetailRequest.getParametersId(),
                        recordDetailRequest.getResultId()));
            case Const.Module.MODULE_PDOD:
                return ServerResponse.createBySuccessMessage("PDOD模块暂未实现");
            case Const.Module.MODULE_SMPWO:
                return ServerResponse.createBySuccessMessage("SMPWO模块暂未实现");
            default:
                return ServerResponse.createByErrorMessage("模块名字错误");
        }

    }

    private SmoSimulationRecordDetailVo getSmoSimulationRecordDetail(int parametersId, int resultId) throws IOException {
        SmoSimulationRecordDetailVo smoSimulationRecordDetailVo = new SmoSimulationRecordDetailVo();
        
        String uploadPath = System.getProperty("bit.gd") + File.separator + Const.UPLOAD_FILE_PATH;

        GDParameterSmo gdParameterSmo = gdParameterSmoMapper.selectByPrimaryKey(parametersId);
        FTPUtil.moveFile(Const.UPLOAD_FILE_PATH, gdParameterSmo.getInputMask(), uploadPath);
        smoSimulationRecordDetailVo.setGdParameterSmo(gdParameterSmo);

        String smoResultPath = System.getProperty("bit.gd") + File.separator + Const.RESULT_PATH_SMO;
        
        GDResultSmo gdResultSmo = gdResultSmoMapper.selectByPrimaryKey(resultId);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getErrorMat(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getErrorWeightMat(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getErrorConvergenceWeightPng(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getMaskPatternMat(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getPrintImageMat(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getSourcePatternMat(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getMaskPatternPng(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getPrintImagePng(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getSourcePatternPng(), smoResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_SMO, gdResultSmo.getErrorConvergencePng(), smoResultPath);

        String errorMatPath = smoResultPath + File.separator + gdResultSmo.getErrorMat();
        smoSimulationRecordDetailVo.setError(JMatIOUtil.getErrorMatValue(errorMatPath));

        smoSimulationRecordDetailVo.setGdResultSmo(gdResultSmo);

        smoSimulationRecordDetailVo.setIp(PropertiesUtil.getProperty("tomcat.ip"));

        return smoSimulationRecordDetailVo;
    }

    private OpcSimulationRecordDetailVo getOpcSimulationRecordDetailVo(int parametersId, int resultId) throws IOException {
        OpcSimulationRecordDetailVo opcSimulationRecordDetailVo = new OpcSimulationRecordDetailVo();

        String uploadPath = System.getProperty("bit.gd") + File.separator + Const.UPLOAD_FILE_PATH;
        GDParameterOpc gdParameterOpc = gdParameterOpcMapper.selectByPrimaryKey(parametersId);
        FTPUtil.moveFile(Const.UPLOAD_FILE_PATH, gdParameterOpc.getInputMask(), uploadPath);
        opcSimulationRecordDetailVo.setGdParameterOpc(gdParameterOpc);

        String opcResultPath = System.getProperty("bit.gd") + File.separator + Const.RESULT_PATH_OPC;

        GDResultOpc gdResultOpc = gdResultOpcMapper.selectByPrimaryKey(resultId);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getErrorMat(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getErrorWeightMat(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getErrorConvergenceWeightPng(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getMaskPatternMat(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getPrintImageMat(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getSourcePatternMat(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getMaskPatternPng(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getPrintImagePng(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getSourcePatternPng(), opcResultPath);
        FTPUtil.moveFile(Const.RESULT_PATH_OPC, gdResultOpc.getErrorConvergencePng(), opcResultPath);

        String errorMatPath = opcResultPath + File.separator + gdResultOpc.getErrorMat();

        opcSimulationRecordDetailVo.setError(JMatIOUtil.getErrorMatValue(errorMatPath));

        opcSimulationRecordDetailVo.setGdResultOpc(gdResultOpc);

        opcSimulationRecordDetailVo.setIp(PropertiesUtil.getProperty("tomcat.ip"));

        return opcSimulationRecordDetailVo;
    }
}
