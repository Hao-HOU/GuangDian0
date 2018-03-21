package bit.gd.service;

import bit.gd.common.ServerResponse;
import bit.gd.pojo.GDRunningState;

/**
 * Created by Hao HOU on 2018/3/21.
 */
public interface IRunningStateService {
    ServerResponse getIntermediateFile(String moduleName, String userNo, GDRunningState gdRunningState);
}
