package bit.gd.controller;

import bit.gd.common.ServerResponse;
import bit.gd.service.ICpuMonitorService;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Hao HOU on 2018/3/8.
 */
@Controller
@RequestMapping("/api")
public class CpuMonitorController {
    @Autowired
    ICpuMonitorService iCpuMonitorService;

    @RequestMapping("get_cpu_info.do")
    @ResponseBody
    public ServerResponse getCpuInfo() throws SigarException {
        return iCpuMonitorService.getAllCpusInfo();
    }
}
