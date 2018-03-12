package bit.gd.service;

import bit.gd.vo.FilenameAndHashVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by Hao HOU on 2018/3/7.
 */
public interface IFileService {
    FilenameAndHashVo upload(MultipartFile file, String path);
    String uploadMatlabOutputFile(String matlabOutputFilename, String matlabOutputPath, String ftpResultPath);
    boolean copySmoIntermediateResult();
}
