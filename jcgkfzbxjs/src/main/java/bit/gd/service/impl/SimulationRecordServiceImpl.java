package bit.gd.service.impl;

import bit.gd.common.Const;
import bit.gd.common.ServerResponse;
import bit.gd.dao.GDParameterSmoMapper;
import bit.gd.dao.GDResultSmoMapper;
import bit.gd.dao.GDSimulationRecordMapper;
import bit.gd.dao.GDUserMapper;
import bit.gd.pojo.GDParameterSmo;
import bit.gd.pojo.GDSimulationRecord;
import bit.gd.pojo.GDUser;
import bit.gd.service.ISimulationRecordService;
import bit.gd.vo.RecordDetailRequest;
import bit.gd.vo.SimulationRecordShowVo;
import bit.gd.vo.SmoSimulationRecordDetailVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ServerResponse<PageInfo> getAllSimulationRecords(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<GDSimulationRecord> gdSimulationRecordList = gdSimulationRecordMapper.selectAllSimulationRecords();
        List<SimulationRecordShowVo> simulationRecordShowVoList = new ArrayList<>();
        for (GDSimulationRecord gdSimulationRecord : gdSimulationRecordList) {
            SimulationRecordShowVo simulationRecordShowVo = assembleSimulationRecordShowVo(gdSimulationRecord);
            simulationRecordShowVoList.add(simulationRecordShowVo);
        }

        PageInfo pageInfo = new PageInfo(gdSimulationRecordList);
        pageInfo.setList(simulationRecordShowVoList);

        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse<PageInfo> getAuthorizedModulesSimulationRecords(int pageNum, int pageSize,
                                                                          List<String> roles) {
        PageHelper.startPage(pageNum, pageSize);

        List<GDSimulationRecord> gdSimulationRecordList =
                gdSimulationRecordMapper.selectAuthorizedModulesSimulationRecords(roles);
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

    public ServerResponse getSimulationRecordParameters(int parametersId, String moduleName) {
        switch (moduleName) {
            case Const.Module.MODULE_SMO:
                return ServerResponse.createBySuccess(getSmoSimulationRecordParameters(parametersId));
            case Const.Module.MODULE_OPC:
                return ServerResponse.createBySuccessMessage("OPC模块暂未实现");
            case Const.Module.MODULE_PDOD:
                return ServerResponse.createBySuccessMessage("PDOD模块暂未实现");
            case Const.Module.MODULE_SMPWO:
                return ServerResponse.createBySuccessMessage("SMPWO模块暂未实现");
            default:
                return ServerResponse.createByErrorMessage("模块名字错误");
        }

    }

    private GDParameterSmo getSmoSimulationRecordParameters(int parametersId) {
        return gdParameterSmoMapper.selectByPrimaryKey(parametersId);
    }

    public ServerResponse getSimulationRecordDetail(RecordDetailRequest recordDetailRequest) {
        switch (recordDetailRequest.getModuleName()) {
            case Const.Module.MODULE_SMO:
                return ServerResponse.createBySuccess(getSmoSimulationRecordDetail(recordDetailRequest.getParametersId(),
                        recordDetailRequest.getResultId()));
            case Const.Module.MODULE_OPC:
                return ServerResponse.createBySuccessMessage("OPC模块暂未实现");
            case Const.Module.MODULE_PDOD:
                return ServerResponse.createBySuccessMessage("PDOD模块暂未实现");
            case Const.Module.MODULE_SMPWO:
                return ServerResponse.createBySuccessMessage("SMPWO模块暂未实现");
            default:
                return ServerResponse.createByErrorMessage("模块名字错误");
        }

    }

    private SmoSimulationRecordDetailVo getSmoSimulationRecordDetail(int parametersId, int resultId) {
        SmoSimulationRecordDetailVo smoSimulationRecordDetailVo = new SmoSimulationRecordDetailVo();
        smoSimulationRecordDetailVo.setGdParameterSmo(gdParameterSmoMapper.selectByPrimaryKey(parametersId));
        smoSimulationRecordDetailVo.setGdResultSmo(gdResultSmoMapper.selectByPrimaryKey(resultId));

        return smoSimulationRecordDetailVo;
    }
}
