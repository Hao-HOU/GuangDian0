package bit.gd.service.impl;

import bit.gd.common.ServerResponse;
import bit.gd.service.IConnectMatlabService;
import euvsmosyy.SMO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Hao HOU on 2018/3/6.
 */
@Service
public class ConnectMatlabServiceImpl implements IConnectMatlabService {
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectMatlabServiceImpl.class);

    public ServerResponse firstTrySMO() {
        double N = 201;//Mask Dimension
        double Pixel = 11;//Pixel Size
        double reflect = Math.sqrt(64/100);//Reflect
        double absorb = Math.sqrt(0.5/100);//Absorb
        double b_max_near_shadowing = 1.424;//Shadow Near
        double b_max_far_shadowing = 1.835;//Shadow Far

//source parameters////
        double lamda=13.5;  //Wavelength
        double r1=0.24;//Sigma in                                                                     //内相干因子
        double r2=0.84;//Sigma out
        double TIS = 16.9/100;
        double NAi=0.25; //NA
        double R = 4;//Ratio

        //Optimization Parameters////
        double step_source = 0.03;
        double omega_source_qua = 0.001;
        double step_mask_main = 0.1;  // weight: try:60, best:20; noweight:40
        double step_mask_SRAF = 0.1;
        double omega_mask_qua=0.0005;
        double maxloop_SMO = 1;
        double threshold = 100;
        double tr=0.25; //0.02;
        double a_source = 25;

        int CoreNum = 4;
        String input_mask = "E:/ztest/target4";


       try {
           SMO smo = new SMO();
           smo.EUV_Pixelated_SMO_MAIN(4, CoreNum, N, Pixel, reflect, absorb, b_max_near_shadowing, b_max_far_shadowing,
                   lamda, r1, r2, TIS, NAi, R, step_source, omega_source_qua, step_mask_main, step_mask_SRAF,
                   omega_mask_qua, maxloop_SMO, threshold, tr, a_source,input_mask);
       } catch (Exception e) {
           return ServerResponse.createByErrorMessage(e.getMessage());
       }

       LOGGER.info("仿真结束");
       return ServerResponse.createBySuccess();
    }
}
