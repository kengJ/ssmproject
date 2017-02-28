package ssmProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

public class test {
	private FTPClient ftpClient = new FTPClient(); 
	private String hostName = "192.168.117.164";  
    private int port = 21;  
    private String userName = "anonymous";
    private String password = "";
    @Test
    public void test1(){
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
            ftpClient.changeWorkingDirectory("/HEYAABM9GZ/");  
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
  
            //ftpClient.logout();  
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
    
}
