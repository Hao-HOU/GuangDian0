package bit.gd.common;

import bit.gd.pojo.GDParameterSmo;
import bit.gd.util.PropertiesUtil;
import com.mathworks.toolbox.javabuilder.MWException;
import euvsmosyy.SMO;

import java.io.File;

/**
 * Created by Hao HOU on 2018/3/11.
 */
public enum SmoSingleton {
    INSTANCE;
    SMO smo = null;
    public boolean singletonExecuteSMO(GDParameterSmo gdParameterSmo) {
        try {
            if (smo == null) {
                smo = new SMO();
            }
            smo.EUV_Pixelated_SMO_MAIN(4, gdParameterSmo.getCoreNum(), gdParameterSmo.getMaskDimension(),
                    gdParameterSmo.getPixelSize(), gdParameterSmo.getReflect(), gdParameterSmo.getAbsorb(),
                    gdParameterSmo.getShadowNear(), gdParameterSmo.getShadowFar(), gdParameterSmo.getWavelength(),
                    gdParameterSmo.getSigmaIn(), gdParameterSmo.getSigmaOut(), gdParameterSmo.getTis(),
                    gdParameterSmo.getNa(), gdParameterSmo.getRatio(), gdParameterSmo.getStepSource(),
                    gdParameterSmo.getOmegaSourceQua(), gdParameterSmo.getStepMaskMain(), gdParameterSmo.getStepMaskSraf(),
                    gdParameterSmo.getOmegaMaskQua(), gdParameterSmo.getMaxloopSmo(), gdParameterSmo.getThreshold(),
                    gdParameterSmo.getTr(), gdParameterSmo.getaSource(),
                    PropertiesUtil.getProperty("ftp.server.path") + Const.UPLOAD_FILE_PATH + File.separator + gdParameterSmo.getInputMask());
        } catch (MWException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (smo != null) {
                smo.dispose();
            }
        }

        return true;
    }
}
