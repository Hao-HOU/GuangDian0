package bit.gd.vo;

import java.util.List;

/**
 * Created by Hao HOU on 2018/3/9.
 */
public class CpuInfoWithTimeStamp {
    private List<CpuInfoVo> cpuInfoVoList;
    private long timeStamp;

    public List<CpuInfoVo> getCpuInfoVoList() {
        return cpuInfoVoList;
    }

    public void setCpuInfoVoList(List<CpuInfoVo> cpuInfoVoList) {
        this.cpuInfoVoList = cpuInfoVoList;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
