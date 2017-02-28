package ssmProject;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.net.SocketException;  
  
import org.apache.commons.io.FileUtils;  
import org.apache.commons.io.IOUtils;  
import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPClientConfig;  
import org.apache.commons.net.ftp.FTPFile;  
import org.apache.commons.net.ftp.FTPReply;

public class ListFtpFile {  
	   
    private FTPClient ftpClient = new FTPClient();  
   
    private OutputStream outSteam = null;  
   
    /** 
     * ftp服务器地址 
     */  
    private String hostName = "192.168.117.164";  
    private int port = 21;  
   
    /** 
     * 登录名 
     */  
    private String userName = "anonymous";//匿名登录，空字符串不行  
    /** 
     * 登录密码 
     */  
    private String password = "";//随便一个地址  
   
    /** 
     * 需要访问的远程目录 
     */  
    private String remoteDir = "/test/";  
   
    /** 
     * 下载 
     */  
    private void download() {  
        try {  
            // 链接到ftp服务器  
            ftpClient.connect(hostName,port);  
            System.out.println("连接到ftp服务器：" + hostName + " 成功..开始登录");  
            // 登录.用户名 密码  
            boolean b = ftpClient.login(userName, password);  
            System.out.println("登录成功." + b);  
               
//          检测连接是否成功  
            int reply = ftpClient.getReplyCode();  
            if(!FTPReply.isPositiveCompletion(reply)) {  
                ftpClient.disconnect();  
                System.err.println("FTP server refused connection.");  
                System.exit(1);  
            }  
               
            ftpClient.setControlEncoding("GBK");  
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);   
            conf.setServerLanguageCode("zh");  
            FTPFile[] remoteFiles = ftpClient.listFiles(remoteDir);  
            if (remoteFiles != null) {  
                for (int i = 0; i < remoteFiles.length; i++) {  
                    String name = remoteFiles[i].getName();  
  
                    //下载  
                    File localFile = new File("e:/practice/ftpdownload/" + name);  
                    OutputStream is = new FileOutputStream(localFile);  
                    //retrieveFile的第一个参数需要是 ISO-8859-1 编码,并且必须是完整路径！  
                    String fileName = remoteDir + name;  
                    ftpClient.retrieveFile(new String(fileName.getBytes("GBK"),"ISO-8859-1"), is);  
                    is.close();  
  
                    //打印  
                    long length = remoteFiles[i].getSize();  
                    String readableLength = FileUtils.byteCountToDisplaySize(length);  
                    System.out.println(name + ":\t"+remoteFiles[i].isFile()+"\t" + readableLength);  
                       
                }  
            }  
   
            ftpClient.logout();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            IOUtils.closeQuietly(outSteam);  
            try {  
                ftpClient.disconnect();  
            } catch (IOException ioe) {  
                ioe.printStackTrace();  
            }  
        }  
    }  
       
    /** 
     * 上传 
     * */  
    public void upload(){  
        String srcUrl = "e:/message.txt";  
        String targetFileName = "message.txt";  
        try {  
            ftpClient.connect(hostName,port);  
            boolean b = ftpClient.login(userName, password);  
            // 检测连接是否成功  
            int reply = ftpClient.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftpClient.disconnect();  
                System.err.println("FTP server refused connection.");  
                System.exit(1);  
            }  
               
            ftpClient.setControlEncoding("GBK");  
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);   
            conf.setServerLanguageCode("zh");  
               
            File srcFile = new File(srcUrl);  
            FileInputStream fis = null;  
            fis = new FileInputStream(srcFile);  
   
            // 设置上传目录  
            ftpClient.changeWorkingDirectory(remoteDir);  
            ftpClient.setBufferSize(1024);  
            ftpClient.setControlEncoding("GBK");  
   
            // 设置文件类型（二进制）  
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            // 上传  
            b = ftpClient.storeFile(targetFileName, fis);  
            IOUtils.closeQuietly(fis);  
  
            /*boolean bool = ftpClient.changeWorkingDirectory("/NC"); 
            System.out.println("changeWorkingDirectory="+bool); 
            bool = ftpClient.makeDirectory("/NC"); 
            System.out.println("makeDirectory="+bool);*/  
  
            ftpClient.logout();  
        } catch (SocketException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                ftpClient.disconnect();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
           
    }  
   
    /** 
     * 测试 
     * */  
    public static void main(String[] args) {  
        ListFtpFile listFtpfiles = new ListFtpFile();  
        //listFtpfiles.download();  
        listFtpfiles.upload();  
    }  
}  