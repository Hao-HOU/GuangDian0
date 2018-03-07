package bit.gd.service.impl;

import bit.gd.dao.GDParameterSmoMapper;
import bit.gd.pojo.GDParameterSmo;
import bit.gd.service.IDataPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hao HOU on 2018/3/7.
 */
@Service
public class DataPersistenceServiceImpl implements IDataPersistenceService {
    @Autowired
    GDParameterSmoMapper gdParameterSmoMapper;

    public int storeSmoParameters(GDParameterSmo gdParameterSmo) {
        return gdParameterSmoMapper.insert(gdParameterSmo);
    }

    public GDParameterSmo checkSmoParametersSimulated(GDParameterSmo gdParameterSmo) {
        return gdParameterSmoMapper.selectIdByRecord(gdParameterSmo);
    }
}
