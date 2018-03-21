package bit.gd.vo;

import bit.gd.pojo.GDParameterOpc;
import bit.gd.pojo.GDResultOpc;

/**
 * Created by Hao HOU on 2018/3/21.
 */
public class OpcSimulationRecordDetailVo {
    private GDParameterOpc gdParameterOpc;
    private GDResultOpc gdResultOpc;
    private String ip;
    private double error;

    public GDParameterOpc getGdParameterOpc() {
        return gdParameterOpc;
    }

    public void setGdParameterOpc(GDParameterOpc gdParameterOpc) {
        this.gdParameterOpc = gdParameterOpc;
    }

    public GDResultOpc getGdResultOpc() {
        return gdResultOpc;
    }

    public void setGdResultOpc(GDResultOpc gdResultOpc) {
        this.gdResultOpc = gdResultOpc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
