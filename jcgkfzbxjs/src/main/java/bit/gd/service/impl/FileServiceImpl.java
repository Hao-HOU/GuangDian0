package bit.gd.service.impl;

import bit.gd.common.Const;
import bit.gd.service.IFileService;
import bit.gd.util.FTPUtil;
import bit.gd.util.FileMD5Util;
import bit.gd.util.PropertiesUtil;
import bit.gd.vo.FilenameAndHashVo;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Hao HOU on 2018/3/7.
 */
@Service
public class FileServiceImpl implements IFileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IFileService.class);

    public FilenameAndHashVo upload(MultipartFile file, String path) {
        FilenameAndHashVo filenameAndHashVo = new FilenameAndHashVo();
        String fileName = file.getOriginalFilename();

        //获取文件扩展名
        //abc.jpg
        //String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        String uploadFileName =  UUID.randomUUID().toString() + "-" + fileName;
        LOGGER.info("开始上传文件，上传的文件名：{}，上传的路径：{}，新文件名：{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件已经上传成功

            //将targetFile上传到我们的FTP服务器上
            FTPUtil.uploadFile(Lists.newArrayList(targetFile), Const.UPLOAD_FILE_PATH);
            //已经上传到FTP服务器上

            //上传完之后，删除upload下面的文件
            targetFile.delete();

        } catch (IOException e) {
            LOGGER.error("上传文件异常", e);
            return null;
        }

        //计算上传文件Hash值
        try {
            InputStream inputStream = FTPUtil.getFile(Const.UPLOAD_FILE_PATH, targetFile.getName());
            if (inputStream == null) {
                LOGGER.info("文件Hash值计算失败");
            } else {
                filenameAndHashVo.setFileHash(FileMD5Util.getMD5Checksum(inputStream));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        filenameAndHashVo.setTargetFileName(targetFile.getName());

        return filenameAndHashVo;
    }


    public String uploadMatlabOutputFile(String matlabOutputFilename, String matlabOutputPath, String ftpResultPath) {
        File file = new File(matlabOutputPath + matlabOutputFilename);
        File rename = new File(matlabOutputPath + UUID.randomUUID().toString() + "-" + file.getName());
        if (file.renameTo(rename)) {
            try {
                FTPUtil.uploadFile(Lists.newArrayList(rename), ftpResultPath);
                String newFilename =  rename.getName();
                rename.delete();
                return newFilename;
            } catch (IOException e) {
                LOGGER.info("仿真结果文件上传失败，原因：", e.getMessage());
                return null;
            }
        } else {
            LOGGER.info("文件名修改失败,未上传");
            return null;
        }
    }
}
