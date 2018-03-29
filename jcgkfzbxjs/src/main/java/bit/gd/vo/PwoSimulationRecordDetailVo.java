package bit.gd.vo;

import bit.gd.pojo.GDParameterPwo;
import bit.gd.pojo.GDResultPwo;

/**
 * Created by Hao HOU on 2018/3/29.
 */
public class PwoSimulationRecordDetailVo {
    private GDParameterPwo gdParameterPwo;
    private GDResultPwo gdResultPwo;
    private String ip;
    private double error;

    public GDParameterPwo getGdParameterPwo() {
        return gdParameterPwo;
    }

    public void setGdParameterPwo(GDParameterPwo gdParameterPwo) {
        this.gdParameterPwo = gdParameterPwo;
    }

    public GDResultPwo getGdResultPwo() {
        return gdResultPwo;
    }

    public void setGdResultPwo(GDResultPwo gdResultPwo) {
        this.gdResultPwo = gdResultPwo;
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
