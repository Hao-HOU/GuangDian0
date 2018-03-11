
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import bit.gd.util.PropertiesUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * FTP服务器工具类
 *
 * @author 码农先生
 * @version 1.0
 * @date 2013-11-18
 * @see 参数 地址1：http://blog.csdn.net/hbcui1984/article/details/2720204
 * @see 参数 地址2:http://blog.csdn.net/yibing548/article/details/38586073
 * @see 参考地址3：http://blog.csdn.net/for_china2012/article/details/16820607
 * @see 参考地址3：http://blog.csdn.net/kardelpeng/article/details/6588284
 * @see 参考地址3：http://commons.apache.org/proper/commons-net/apidocs/org/apache/commons/net/ftp/FTPClient.html
 *
 */
@SuppressWarnings("all")
public class FtpUtilTest {
    protected static final Logger logger = LoggerFactory.getLogger(FtpUtilTest.class);
    private static FtpUtilTest FtpUtilTests;
    private FTPClient ftpClient;

    private String serverUrl; //服务器地址
    private String port; // 服务器端口
    private String username; // 用户登录名
    private String password; // 用户登录密码
    private InputStream is; // 文件下载输入流

    /**
     * 私有构造方法
     */
    private FtpUtilTest() {
        initConfig();
        if (null == ftpClient) {
            ftpClient = new FTPClient();
            ftpClient.setControlEncoding("UTF-8");
        }
    }

    /**
     * 获取FtpUtilTests对象实例
     *
     * @return FtpUtilTests对象实例
     */
    public synchronized static FtpUtilTest getInstance() {
        if (null == FtpUtilTests) {
            FtpUtilTests = new FtpUtilTest();
        }
        return FtpUtilTests;
    }

    /**
     * 初始化FTP服务器连接属性
     */
    public void initConfig() {
        // 构造Properties对象
        Properties properties = new Properties();
        // 定义配置文件输入流
        serverUrl =  PropertiesUtil.getProperty("ftp.server.ip");; // 设置服务器地址
        port = PropertiesUtil.getProperty("ftp.server.port"); // 设置端口
        username = PropertiesUtil.getProperty("ftp.user"); // 设置用户名
        password = PropertiesUtil.getProperty("ftp.pass"); // 设置密码
    }

    /**
     * 连接（配置通用连接属性）至服务器
     *
     * @param serverName
     *            服务器名称
     * @param remotePath
     *            当前访问目录
     * @return <b>true</b>：连接成功 <br/>
     *         <b>false</b>：连接失败
     */
    public boolean connectToTheServer(String remotePath) {
        // 定义返回值
        boolean result = false;
        try {
            // 连接至服务器，端口默认为21时，可直接通过URL连接
            ftpClient.connect(serverUrl, Integer.parseInt(port));
            // 登录服务器
            ftpClient.login(username, password);
            // 判断返回码是否合法
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                // 不合法时断开连接
                ftpClient.disconnect();
                // 结束程序
                return result;
            }
            //设置文件传输模式
            //被动模式
//			ftpClient.enterLocalPassiveMode();
            //创建目录
            ftpClient.makeDirectory(remotePath);
            // 设置文件操作目录
            result = ftpClient.changeWorkingDirectory(remotePath);
            // 设置文件类型，二进制
            result = ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 设置缓冲区大小
            ftpClient.setBufferSize(3072);
            // 设置字符编码
            ftpClient.setControlEncoding("UTF-8");

        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
            throw new RuntimeException("连接FTP服务器异常" , e);
        }
        return result;
    }

    /**
     * 上传文件至FTP服务器
     *
     * @param serverName
     *            服务器名称
     * @param storePath
     *            上传文件存储路径
     * @param fileName
     *            上传文件存储名称
     * @param is
     *            上传文件输入流
     * @return <b>true</b>：上传成功 <br/>
     *         <b>false</b>：上传失败
     */
    public boolean storeFile( String storePath,	String fileName, InputStream is) {
        boolean result = false;
        try {
            // 连接至服务器
            result = connectToTheServer(storePath);
            // 判断服务器是否连接成功
            if (result) {
                // 上传文件
                result = ftpClient.storeFile(fileName, is);
            }
            // 关闭输入流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 判断输入流是否存在
            if (null != is) {
                try {
                    // 关闭输入流
                    is.close();
                } catch (IOException e) {
                    logger.error("上传文件至FTP异常"+e.getMessage());
                    throw new RuntimeException("上传文件至FTP异常" , e);
                }
            }
            // 登出服务器并断开连接
            FtpUtilTests.logout();
        }
        return result;
    }

    /**
     * 下载FTP服务器文件至本地<br/>
     * 操作完成后需调用logout方法与服务器断开连接
     *
     * @param serverName
     *            服务器名称
     * @param remotePath
     *            下载文件存储路径
     * @param fileName
     *            下载文件存储名称
     * @return <b>InputStream</b>：文件输入流
     */
    public InputStream retrieveFile(String remotePath,String fileName) {
        try {
            boolean result = false;
            // 连接至服务器
            result = connectToTheServer(remotePath);
            // 判断服务器是否连接成功
            if (result) {
                // 获取文件输入流
                is = ftpClient.retrieveFileStream(fileName);
            }
        } catch (IOException e) {
            logger.error("从FTP下载文件到本地异常"+e.getMessage());
            throw new RuntimeException("从FTP下载文件到本地异常" , e);
        }
        return is;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param fileName
     *            要下载的文件名
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public boolean retrieveFile(String remotePath, String fileName, String localPath) {
        // 初始表示下载失败
        boolean success = false;
        //表示是否连接成功
        boolean result = false;
        File file = new File(localPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            // 连接至服务器
            result = connectToTheServer(remotePath);
            if(result){
                // 列出该目录下所有文件
                FTPFile[] fs = ftpClient.listFiles();
                // 遍历所有文件，找到指定的文件
                for (FTPFile ff : fs) {
                    if (ff.getName().equals(fileName)) {
                        // 根据绝对路径初始化文件
                        File localFile = new File(localPath + "/" + ff.getName());
                        //输出流
                        OutputStream is = new FileOutputStream(localFile);
                        //下载文件
                        success=ftpClient.retrieveFile(ff.getName(), is);
                        is.close();
                    }
                }
            }

        } catch (IOException e) {
            logger.error("从FTP服务器下载文件异常",e);
        } finally {
            // 登出服务器并断开连接
            FtpUtilTests.logout();
        }
        return success;
    }


    /**
     * 删除FTP服务器文件
     *
     * @param serverName
     *            服务器名称
     * @param remotePath
     *            当前访问目录
     * @param fileName
     *            文件存储名称
     * @return <b>true</b>：删除成功 <br/>
     *         <b>false</b>：删除失败
     */
    public boolean deleteFile(String remotePath,String fileName) {
        boolean result = false;
        // 连接至服务器
        result = connectToTheServer(remotePath);
        // 判断服务器是否连接成功
        if (result) {
            try {
                // 删除文件
                result = ftpClient.deleteFile(fileName);
            } catch (IOException e) {
                logger.error("删除FTP服务器上的 文件异常"+e.getMessage());
                throw new RuntimeException("删除FTP服务器上的 文件异常" , e);
            } finally {
                // 登出服务器并断开连接
                FtpUtilTests.logout();
            }
        }
        return result;
    }

    /**
     * 检测FTP服务器文件是否存在
     *
     * @param serverName
     *            服务器名称
     * @param remotePath
     *            检测文件存储路径
     * @param fileName
     *            检测文件存储名称
     * @return <b>true</b>：文件存在 <br/>
     *         <b>false</b>：文件不存在
     */
    public boolean checkFile( String remotePath,String fileName) {
        boolean result = false;
        try {
            // 连接至服务器
            result = connectToTheServer(remotePath);
            // 判断服务器是否连接成功
            if (result) {
                // 默认文件不存在
                result = false;
                // 获取文件操作目录下所有文件名称
                String[] remoteNames = ftpClient.listNames();
                // 循环比对文件名称，判断是否含有当前要下载的文件名
                for (String remoteName : remoteNames) {
                    if (fileName.equals(remoteName)) {
                        result = true;
                    }
                }
            }
        } catch (IOException e) {
            logger.error("检查FTP文件是否存在异常"+e.getMessage());
            throw new RuntimeException("检查FTP文件是否存在异常" , e);
        } finally {
            // 登出服务器并断开连接
            FtpUtilTests.logout();
        }
        return result;
    }

    /**
     * 登出服务器并断开连接
     *
     * @param ftp
     *            FTPClient对象实例
     * @return <b>true</b>：操作成功 <br/>
     *         <b>false</b>：操作失败
     */
    public boolean logout() {
        boolean result = false;
        if (null != is) {
            try {
                // 关闭输入流
                is.close();
            } catch (IOException e) {
                logger.error("登录FTP服务器异常"+e.getMessage());
                throw new RuntimeException("登录FTP服务器异常" , e);
            }
        }
        if (null != ftpClient) {
            try {
                // 登出服务器
                result = ftpClient.logout();
            } catch (IOException e) {
                logger.error("登录FTP服务器异常"+e.getMessage());
                throw new RuntimeException("登录FTP服务器异常" , e);
            } finally {
                // 判断连接是否存在
                if (ftpClient.isConnected()) {
                    try {
                        // 断开连接
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        logger.error("关闭FTP服务器异常"+e.getMessage());
                        throw new RuntimeException("关闭FTP服务器异常" , e);
                    }
                }
            }
        }
        return result;
    }





    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param fileName
     *            要下载的文件名
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String url, int port, String username,
                                   String password, String remotePath, String fileName,
                                   String localPath) {
        // 初始表示下载失败
        boolean success = false;
        // 创建FTPClient对象
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            // 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.connect(url, port);
            reply = ftp.getReplyCode();
            /*
             * 判断是否连接成功
             */
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            } else {
                // 登录ftp
                if (ftp.login(username, password)) {
                    // 转到指定下载目录
                    ftp.changeWorkingDirectory(remotePath);
                    // 列出该目录下所有文件
                    FTPFile[] fs = ftp.listFiles();
                    // 遍历所有文件，找到指定的文件
                    for (FTPFile ff : fs) {
                        if (ff.getName().equals(fileName)) {
                            // 根据绝对路径初始化文件
                            File localFile = new File(localPath + "/"
                                    + ff.getName());
                            // 输出流
                            OutputStream is = new FileOutputStream(localFile);
                            // 下载文件
                            ftp.retrieveFile(ff.getName(), is);
                            is.close();
                        }
                    }
                    // 退出ftp
                    ftp.logout();
                    // 下载成功
                    success = true;
                }
            }
        } catch (IOException e) {
            logger.error("从FTP服务器下载文件异常"+e.getMessage());
            throw new RuntimeException("从FTP服务器下载文件异常" , e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("关闭FTP连接异常"+ioe.getMessage());
                    throw new RuntimeException("关闭FTP连接异常" , ioe);
                }
            }
        }
        return success;
    }

    /**
     * 读取本地TXT
     *
     * @param filepath
     *            txt文件目录即文件名
     */

    public ArrayList<String> readtxt(String filepath) {
        ArrayList<String> readList = new ArrayList<String>();
        ArrayList retList = new ArrayList();
        try {
            String temp = null;
            File f = new File(filepath);
            String adn = "";
            // 指定读取编码用于读取中文
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    f), "UTF-8");
            BufferedReader reader = new BufferedReader(read);
            // bufReader = new BufferedReader(new FileReader(filepath));
            do {
                temp = reader.readLine();
                readList.add(temp);
            } while (temp != null);
            read.close();
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("读取本地txt异常"+e.getMessage());
            throw new RuntimeException("读取本地txt异常" , e);
        }
        return readList;
    }


    /**
     * @param properties
     * @param batchNo
     * @param status
     * @param financeChannel
     * @param fileDownUtil
     * @return
     * @throws Exception
     */
//	public  String downCmbcCallBackFile(Properties properties,FTPClient ftpClient, String batchNo, String status, FinanceChannel financeChannel, FileDownUtil fileDownUtil) throws Exception {
//		FTPClient ftp = new FTPClient();
//		FileOutputStream is = null;
//		File localEncoFile = null;
//		File dirLocalEncoPath = null;
//		String fileName = "";
//		String filePath = "";
//		String romoteEncoPeerPath = properties.getProperty("romoteEncoPeerPath") + batchNo.split("_")[0];
//		String romoteEncoCorssPath = properties.getProperty("romoteEncoCorssPath") + batchNo.split("_")[0];
//		String localEncoPeerPath = this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + batchNo.split("_")[0];
//		String localEncoCorssPath = this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + batchNo.split("_")[0];
//		try {
//			int reply;
//			ftp.connect(properties.getProperty("url"));
//			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//			ftp.login(properties.getProperty("ftpusername"), properties.getProperty("ftppassword"));// 登录
//			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//			reply = ftp.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(reply)) {
//				ftp.disconnect();
//				return null;
//			}
//			if ("Peer".equals(financeChannel.getSameOrCorssBank().name())) {
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + batchNo.split("_")[0] + "/");// 加密目录
//				ftp.changeWorkingDirectory(properties.getProperty("romotePeerPath") + batchNo.split("_")[0] + "/");// 转移到FTP服务器目录
//				localEncoPeerPath =this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				romoteEncoPeerPath = properties.getProperty("romoteEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/");// 加密目录
//				if (!dirLocalEncoPath.exists()) {
//					dirLocalEncoPath.mkdirs();
//					logger.info("创建本地目录成功:" + dirLocalEncoPath);
//				}
//			} else {
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + batchNo.split("_")[0] + "/");// 加密目录
//				ftp.changeWorkingDirectory(properties.getProperty("romoteCorssPath") + batchNo.split("_")[0] + "/");// 转移到FTP服务器目录
//				localEncoCorssPath = this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				romoteEncoCorssPath = properties.getProperty("romoteEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/");// 加密目录
//
//				if (!dirLocalEncoPath.exists()) {
//					dirLocalEncoPath.mkdirs();
//					logger.info("创建本地目录成功:" + dirLocalEncoPath);
//				}
//			}
//			FTPFile[] fs = ftp.listFiles();
//			for (FTPFile ff : fs) {
//				if ("Peer".equals(financeChannel.getSameOrCorssBank().name())) {
//					if (ff.getName().equals("res_" + batchNo + ".txt")) {
//						String newBatchNo = CmbcUtil.modifyBatchNo(batchNo, DateUtil.formatDate(new Date(), "yyyyMMdd"));
//						localEncoFile = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/" + "res_" + newBatchNo + ".txt");
//						is = new FileOutputStream(localEncoFile);
//						ftp.retrieveFile(ff.getName(), is);
//						fileName = "res_" + newBatchNo + ".txt";
//						// 先用生产key解密文件
//						CmbcUtil.decodeAESFile(properties.getProperty("deProPassword").getBytes(), localEncoPeerPath + "/" + "dePeer.txt", localEncoPeerPath + "/" + fileName);
//						// 再用测试key加密文件
//						CmbcUtil.encodeAESFile(properties.getProperty("dePassword").getBytes(), localEncoPeerPath + "/" + "dePeer.txt", localEncoPeerPath + "/" + fileName);
//						filePath = fileDownUtil.ftpUploadFile(ftpClient,properties,localEncoPeerPath, romoteEncoPeerPath, fileName);
//						break;
//					}
//				} else {
//					if (ff.getName().equals("res_outer_" + batchNo + ".txt")) {
//						String newBatchNo = CmbcUtil.modifyBatchNo(batchNo, DateUtil.formatDate(new Date(), "yyyyMMdd"));
//						localEncoFile = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/" + "res_outer_" + newBatchNo + ".txt");
//						is = new FileOutputStream(localEncoFile);
//						ftp.retrieveFile(ff.getName(), is);
//						fileName = "res_outer_" + newBatchNo + ".txt";
//						// 先用生产key解密文件
//						CmbcUtil.decodeAESFile(properties.getProperty("deProPassword").getBytes(), localEncoCorssPath + "/" + "deCorss.txt", localEncoCorssPath + "/" + fileName);
//						// 再用测试key加密文件
//						CmbcUtil.encodeAESFile(properties.getProperty("dePassword").getBytes(), localEncoCorssPath + "/" + "deCorss.txt", localEncoCorssPath + "/" + fileName);
//						filePath = fileDownUtil.ftpUploadFile(ftpClient,properties,localEncoCorssPath, romoteEncoCorssPath, fileName);
//						break;
//					}
//				}
//			}
//
//		} catch (IOException e) {
//			logger.error("关闭FTP服务器连接异常", e);
//			throw new RuntimeException("从FTP服务器下载文件异常", e);
//
//		} finally {
//			if (null != ftp) {
//				try {
//					ftp.logout();
//				} catch (IOException e) {
//					logger.error("关闭FTP服务器连接异常", e);
//				}
//			}
//			if (ftp.isConnected()) {
//				try {
//					ftp.disconnect();
//				} catch (IOException e) {
//					logger.error("关闭FTP服务器连接异常", e);
//				}
//			}
//			if (null != is) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error("关闭FTP服务器连接异常", e);
//				}
//			}
//		}
//		return filePath;
//	}




    /**
     * 把配置文件先写到本地的一个文件中取 
     *
     * @param ftpPath
     * @param str
     * @return
     */
    public boolean write(String fileName, String fileContext,
                         String writeTempFielPath) {
        try {
            logger.info("开始写配置文件");
            File f = new File(writeTempFielPath + "/" + fileName);
            if(!f.exists()){
                if(!f.createNewFile()){
                    logger.info("文件不存在，创建失败!");
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
            bw.write(fileContext.replaceAll("\n", "\r\n"));
            bw.flush();
            bw.close();
            return true;
        } catch (Exception e) {
            logger.error("写文件没有成功");
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @Description： 测试连接ftP服务器
     * @author: GuXiYang
     * @date: 2015-6-18 下午4:28:15
     * @param args
     */
    public static void main(String[] args) {
        FtpUtilTest ftp = FtpUtilTest.getInstance();
        String localPath="E:\\ztest\\中文阿萨德.mat";
        String remotePath = "/txt";
        String fileName = "中文阿萨德.mat";
        //文件上传
//				try {
//					FileInputStream in=new FileInputStream(new File(localPath));
//					boolean flag = ftp.storeFile(remotePath, fileName, in);
//					logger.info("文件上传结果：" + flag);
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}

//        文件下载
//        		InputStream in=ftp.retrieveFile(remotePath, fileName);
//        		StringBuffer resultBuffer = new StringBuffer();
//        		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//        		String data = null;
//        		try {
//        			while ((data = br.readLine()) != null) {
//        				resultBuffer.append(data + "\n");
//        			}
//        		} catch (IOException e) {
//        			logger.error("文件读取错误。");
//        			e.printStackTrace();
//        		}
//             System.out.println(resultBuffer.toString());


        //在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
//		try {  
//			InputStream input = new ByteArrayInputStream("一切只能靠自己！".getBytes("utf-8"));  
//			boolean flag = ftp.storeFile(remotePath,"test.txt",input);  
//			System.out.println(flag);  
//		} catch (UnsupportedEncodingException e) {  
//			e.printStackTrace();  
//		}  


    }
}