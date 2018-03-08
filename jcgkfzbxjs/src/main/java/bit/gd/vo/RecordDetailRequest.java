package bit.gd.vo;

/**
 * Created by Hao HOU on 2018/3/8.
 */
public class RecordDetailRequest {
    private String moduleName;
    private int parametersId;
    private int resultId;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getParametersId() {
        return parametersId;
    }

    public void setParametersId(int parametersId) {
        this.parametersId = parametersId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
}
