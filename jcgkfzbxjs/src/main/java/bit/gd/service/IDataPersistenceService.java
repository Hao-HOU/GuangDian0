package bit.gd.service;

import bit.gd.pojo.GDParameterSmo;

/**
 * Created by Hao HOU on 2018/3/7.
 */
public interface IDataPersistenceService {
    int storeSmoParameters(GDParameterSmo gdParameterSmo);
    GDParameterSmo checkSmoParametersSimulated(GDParameterSmo gdParameterSmo);
}
