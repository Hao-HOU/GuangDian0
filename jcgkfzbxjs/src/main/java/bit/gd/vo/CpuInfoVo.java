package bit.gd.vo;

/**
 * Created by Hao HOU on 2018/3/8.
 */
public class CpuInfoVo {
    private int id;
    private double userRatio;
    private double sysRatio;
    private double waitRatio;
    private double idleRatio;
    private double combined;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUserRatio() {
        return userRatio;
    }

    public void setUserRatio(double userRatio) {
        this.userRatio = userRatio;
    }

    public double getSysRatio() {
        return sysRatio;
    }

    public void setSysRatio(double sysRatio) {
        this.sysRatio = sysRatio;
    }

    public double getWaitRatio() {
        return waitRatio;
    }

    public void setWaitRatio(double waitRatio) {
        this.waitRatio = waitRatio;
    }

    public double getIdleRatio() {
        return idleRatio;
    }

    public void setIdleRatio(double idleRatio) {
        this.idleRatio = idleRatio;
    }

    public double getCombined() {
        return combined;
    }

    public void setCombined(double combined) {
        this.combined = combined;
    }
}
