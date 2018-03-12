import bit.gd.service.IFileService;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Hao HOU on 2018/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CopyFileTest {
    @Autowired
    IFileService iFileService;

    @Test
    public void copyFile() {
        String oldPath = "E:\\ztest\\target4.mat";
        String newPath = "E:\\ztest\\copy\\target4.mat";
        File source = new File(oldPath);
        File dest = new File(newPath);
        if (dest.exists()) {
            try {
                FileUtils.copyFile(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                File fileParent = dest.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();
                }
                FileUtils.copyFile(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        Files.copy(oldPath, newPath);
    }

    @Test
    public void copyInter() {
        Assert.assertTrue(iFileService.copySmoIntermediateResult());

    }
}
