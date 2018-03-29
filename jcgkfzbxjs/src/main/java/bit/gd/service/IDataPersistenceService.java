package bit.gd.service;

import bit.gd.pojo.*;
import bit.gd.vo.SimulatedVo;

/**
 * Created by Hao HOU on 2018/3/7.
 */
public interface IDataPersistenceService {
    GDParameterSmo storeSmoParameters(GDParameterSmo gdParameterSmo);
    GDParameterOpc storeOpcParameters(GDParameterOpc gdParameterOpc);
    GDParameterPwo storePwoParameters(GDParameterPwo gdParameterPwo);
    GDParameterSmo checkSmoParametersSimulated(GDParameterSmo gdParameterSmo);
    GDParameterOpc checkOpcParametersSimulated(GDParameterOpc gdParameterOpc);
    GDParameterPwo checkPwoParametersSimulated(GDParameterPwo gdParameterPwo);
    GDResultSmo storeSmoResult(GDResultSmo gdResultSmo);
    GDResultOpc storeOpcResult(GDResultOpc gdResultOpc);
    GDResultPwo storePwoResult(GDResultPwo gdResultPwo);
    GDSimulationRecord storeSimulationRecord(GDSimulationRecord gdSimulationRecord);
    SimulatedVo getSimulatedVo(String moduleName, int parametersId);
}
