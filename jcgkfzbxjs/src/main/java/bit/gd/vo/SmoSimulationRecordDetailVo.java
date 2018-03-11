package bit.gd.vo;

import bit.gd.pojo.GDParameterSmo;
import bit.gd.pojo.GDResultSmo;

/**
 * Created by Hao HOU on 2018/3/8.
 */
public class SmoSimulationRecordDetailVo {
    private GDParameterSmo gdParameterSmo;
    private GDResultSmo gdResultSmo;
    private String ip;
    private double error;

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public GDParameterSmo getGdParameterSmo() {
        return gdParameterSmo;
    }

    public void setGdParameterSmo(GDParameterSmo gdParameterSmo) {
        this.gdParameterSmo = gdParameterSmo;
    }

    public GDResultSmo getGdResultSmo() {
        return gdResultSmo;
    }

    public void setGdResultSmo(GDResultSmo gdResultSmo) {
        this.gdResultSmo = gdResultSmo;
    }
}
