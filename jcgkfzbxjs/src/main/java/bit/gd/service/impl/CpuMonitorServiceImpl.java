package bit.gd.service.impl;

import bit.gd.common.ServerResponse;
import bit.gd.service.ICpuMonitorService;
import bit.gd.vo.CpuInfoVo;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hao HOU on 2018/3/8.
 */
@Service
public class CpuMonitorServiceImpl implements ICpuMonitorService {

    public ServerResponse getAllCpusInfo() throws SigarException {
        List<CpuInfoVo> cpuInfoVoList = new ArrayList<>();

        Sigar sigar = new Sigar();
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
            cpuInfoVoList.add(getCpuPerc(cpuList[i], i));
        }

        if (cpuInfoVoList.isEmpty()) {
            return ServerResponse.createByErrorMessage("获取CPU使用情况失败");
        }

        return ServerResponse.createBySuccess(cpuInfoVoList);
    }
    private CpuInfoVo getCpuPerc(CpuPerc cpu, int id) {
        CpuInfoVo cpuInfoVo = new CpuInfoVo();
        cpuInfoVo.setId(id);
        cpuInfoVo.setUserRatio(cpu.getUser());
        cpuInfoVo.setSysRatio(cpu.getSys());
        cpuInfoVo.setWaitRatio(cpu.getWait());
        cpuInfoVo.setIdleRatio(cpu.getIdle());
        cpuInfoVo.setCombined(cpu.getCombined());

        return cpuInfoVo;
    }
}
