package bit.gd.service;

import bit.gd.common.ServerResponse;
import bit.gd.vo.RecordDetailRequest;
import bit.gd.vo.SearchSimulationRecordsRequest;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hao HOU on 2018/3/8.
 */
public interface ISimulationRecordService {
    ServerResponse<PageInfo> getAllSimulationRecords(SearchSimulationRecordsRequest searchSimulationRecordsRequest);
    ServerResponse<PageInfo> getAuthorizedModulesSimulationRecords(SearchSimulationRecordsRequest searchSimulationRecordsRequest);
    ServerResponse getSimulationRecordDetail(RecordDetailRequest recordDetailRequest) throws IOException;
}
