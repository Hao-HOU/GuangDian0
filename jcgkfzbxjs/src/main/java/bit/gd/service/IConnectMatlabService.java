package bit.gd.service;

import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDParameterSmo;

/**
 * Created by Hao HOU on 2018/3/6.
 */
public interface IConnectMatlabService {
    ServerResponse executeSmoSimulation(GDParameterSmo gdParameterSmo);
}
