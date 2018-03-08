package bit.gd.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Hao HOU on 2018/3/7.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
