package bit.gd.service;

import bit.gd.common.ServerResponse;
import bit.gd.vo.RecordDetailRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Hao HOU on 2018/3/8.
 */
public interface ISimulationRecordService {
    ServerResponse<PageInfo> getAllSimulationRecords(int pageNum, int pageSize);
    ServerResponse<PageInfo> getAuthorizedModulesSimulationRecords(int pageNum, int pageSize, List<String> roles);
    ServerResponse getSimulationRecordParameters(int parametersId, String moduleName);
    ServerResponse getSimulationRecordDetail(RecordDetailRequest recordDetailRequest);
}
