package bit.gd.service;

import bit.gd.common.ServerResponse;
import org.hyperic.sigar.SigarException;

/**
 * Created by Hao HOU on 2018/3/8.
 */
public interface ICpuMonitorService {
    ServerResponse getAllCpusInfo() throws SigarException;
}
