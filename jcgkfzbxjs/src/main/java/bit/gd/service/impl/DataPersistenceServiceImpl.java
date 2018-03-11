package bit.gd.service.impl;

import bit.gd.dao.GDParameterSmoMapper;
import bit.gd.dao.GDResultSmoMapper;
import bit.gd.dao.GDSimulationRecordMapper;
import bit.gd.pojo.GDParameterSmo;
import bit.gd.pojo.GDResultSmo;
import bit.gd.pojo.GDSimulationRecord;
import bit.gd.service.IDataPersistenceService;
import bit.gd.vo.SimulatedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public GDParameterSmo storeSmoParameters(GDParameterSmo gdParameterSmo) {
        if (gdParameterSmoMapper.insert(gdParameterSmo) > 0) {
            return gdParameterSmo;
        }
        return null;
    }

    public GDParameterSmo checkSmoParametersSimulated(GDParameterSmo gdParameterSmo) {
        return gdParameterSmoMapper.selectIdByRecord(gdParameterSmo);
    }

    public GDResultSmo storeSmoResult(GDResultSmo gdResultSmo) {
        if (gdResultSmoMapper.insert(gdResultSmo) > 0) {
            return gdResultSmo;
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
