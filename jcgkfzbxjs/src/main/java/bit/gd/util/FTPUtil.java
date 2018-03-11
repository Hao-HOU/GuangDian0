package bit.gd.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * Created by Hao HOU on 2017/8/4.
 */
public class FTPUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static int ftpPort = Integer.parseInt(PropertiesUtil.getProperty("ftp.server.port"));
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public static InputStream getFile(String remotePath, String fileName) {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, ftpPort, ftpUser, ftpPass);
        return  ftpUtil.retrieveFile(remotePath, fileName);
    }
    private InputStream retrieveFile(String remotePath, String fileName) {
        InputStream is = null;
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.changeWorkingDirectory(remotePath);
                is = ftpClient.retrieveFileStream(fileName);
            } catch (IOException e) {
                LOGGER.info("读取FTP上文件异常：{}", e.getMessage());
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            LOGGER.info("连接FTP服务器异常");
        }

        return is;
    }

    public static boolean moveFile(String remotePath, String fileName, String localPath) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, ftpPort, ftpUser, ftpPass);
        return ftpUtil.retrieveFile(remotePath, fileName, localPath);
    }

    private boolean retrieveFile(String remotePath, String fileName, String localPath) throws IOException {
        // 初始表示下载失败
        boolean success = false;
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            File file = new File(localPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            ftpClient.changeWorkingDirectory(remotePath);
            FTPFile[] fs = ftpClient.listFiles();
            // 遍历所有文件，找到指定的文件
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    // 根据绝对路径初始化文件
                    File localFile = new File(localPath + File.separator + ff.getName());
                    //输出流
                    OutputStream is = new FileOutputStream(localFile);
                    //下载文件
                    success=ftpClient.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
        } else {
            LOGGER.info("连接FTP服务器异常");
        }
        return success;
    }

    public static boolean uploadFile(List<File> fileList, String remotePath) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, ftpPort, ftpUser, ftpPass);
        LOGGER.info("开始连接FTP服务器");
        boolean result = ftpUtil.uploadFile(remotePath, fileList);
        LOGGER.info("结束上传，上传结果:{}", result);
        return result;

    }
    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                ftpClient.makeDirectory(remotePath);
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalActiveMode();
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException e) {
                LOGGER.error("上传文件异常", e);
                uploaded = false;
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        } else {
            return false;
        }
        return uploaded;
    }
    private boolean connectServer(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(ip, port);
            isSuccess = ftpClient.login(user, pwd);
        } catch (Exception e) {
            LOGGER.error("连接FTP服务器异常", e);
        }
        return isSuccess;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
