import bit.gd.common.Const;
import bit.gd.service.IFileService;
import bit.gd.util.FTPUtil;
import bit.gd.util.PropertiesUtil;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Hao HOU on 2018/3/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ResultFileUploadTest {
    @Autowired
    IFileService iFileService;

    @Test
    public void uploadResultFile() {
        String path = "E:\\ztest\\SMO_result\\";
        File file = new File(path + Const.SmoMatlabOutputFilename.SMO_Error_Convergence_Png);
        File rename = new File(path + UUID.randomUUID().toString() + "-" + file.getName());
        if (file.renameTo(rename)) {
            System.out.println("文件名修改成功");
            System.out.println("新文件名：" + rename.getName());
            try {
                FTPUtil.uploadFile(Lists.newArrayList(rename), Const.RESULT_PATH_SMO);
//                rename.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件名修改失败");
        }

        //file.renameTo(path + UUID.randomUUID().toString() + "-" + file.getName());
        //file.delete();

    }
}
