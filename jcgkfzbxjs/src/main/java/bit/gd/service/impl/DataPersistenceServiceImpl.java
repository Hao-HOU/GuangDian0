package bit.gd.service.impl;

import bit.gd.dao.*;
import bit.gd.pojo.*;
import bit.gd.service.IDataPersistenceService;
import bit.gd.vo.SimulatedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hao HOU on 2018/3/7.
 */
@Service
public class DataPersistenceServiceImpl implements IDataPersistenceService {
    @Autowired
    GDParameterSmoMapper gdParameterSmoMapper;

    @Autowired
    GDResultSmoMapper gdResultSmoMapper;

    @Autowired
    GDSimulationRecordMapper gdSimulationRecordMapper;

    @Autowired
    GDParameterOpcMapper gdParameterOpcMapper;

    @Autowired
    GDResultOpcMapper gdResultOpcMapper;

    public GDParameterSmo storeSmoParameters(GDParameterSmo gdParameterSmo) {
        if (gdParameterSmoMapper.insert(gdParameterSmo) > 0) {
            return gdParameterSmo;
        }
        return null;
    }

    public GDParameterOpc storeOpcParameters(GDParameterOpc gdParameterOpc) {
        if (gdParameterOpcMapper.insert(gdParameterOpc) > 0) {
            return gdParameterOpc;
        }
        return null;
    }

    public GDParameterSmo checkSmoParametersSimulated(GDParameterSmo gdParameterSmo) {
        List<GDParameterSmo> gdParameterSmoList = gdParameterSmoMapper.selectByRecord(gdParameterSmo);
        if (gdParameterSmoList.isEmpty()) {
            return null;
        } else {
            return gdParameterSmoList.get(gdParameterSmoList.size() - 1);
        }
    }

    public GDParameterOpc checkOpcParametersSimulated(GDParameterOpc gdParameterOpc) {
        List<GDParameterOpc> gdParameterOpcList = gdParameterOpcMapper.selectByRecord(gdParameterOpc);
        if (gdParameterOpcList.isEmpty()) {
            return null;
        } else {
            return gdParameterOpcList.get(gdParameterOpcList.size() - 1);
        }
    }

    public GDResultSmo storeSmoResult(GDResultSmo gdResultSmo) {
        if (gdResultSmoMapper.insert(gdResultSmo) > 0) {
            return gdResultSmo;
        }
        return null;
    }

    public GDResultOpc storeOpcResult(GDResultOpc gdResultOpc) {
        if (gdResultOpcMapper.insert(gdResultOpc) > 0) {
            return gdResultOpc;
        }

        return null;
    }

    public GDSimulationRecord storeSimulationRecord(GDSimulationRecord gdSimulationRecord) {
        if (gdSimulationRecordMapper.insert(gdSimulationRecord) > 0) {
            return gdSimulationRecord;
        }

        return null;
    }

    public SimulatedVo getSimulatedVo(String moduleName, int parametersId) {
        SimulatedVo simulatedVo = new SimulatedVo();
        simulatedVo.setParametersId(parametersId);

        GDSimulationRecord gdSimulationRecord = gdSimulationRecordMapper.selectByModuleNameAndParametersId(moduleName, parametersId);
        if (gdSimulationRecord != null) {
            simulatedVo.setResultId(gdSimulationRecord.getResultId());
        } else {
            return null;
        }

        return simulatedVo;
    }
}
