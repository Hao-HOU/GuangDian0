package bit.gd.vo;

import bit.gd.pojo.GDParameterSmo;
import bit.gd.pojo.GDResultSmo;

/**
 * Created by Hao HOU on 2018/3/8.
 */
public class SmoSimulationRecordDetailVo {
    private GDParameterSmo gdParameterSmo;
    private GDResultSmo gdResultSmo;

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
