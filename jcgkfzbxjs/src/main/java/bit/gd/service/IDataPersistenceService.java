package bit.gd.service;

import bit.gd.pojo.GDParameterSmo;
import bit.gd.pojo.GDResultSmo;
import bit.gd.pojo.GDSimulationRecord;

/**
 * Created by Hao HOU on 2018/3/7.
 */
public interface IDataPersistenceService {
    GDParameterSmo storeSmoParameters(GDParameterSmo gdParameterSmo);
    GDParameterSmo checkSmoParametersSimulated(GDParameterSmo gdParameterSmo);
    GDResultSmo storeSmoResult(GDResultSmo gdResultSmo);
    GDSimulationRecord storeSimulationRecord(GDSimulationRecord gdSimulationRecord);
}
