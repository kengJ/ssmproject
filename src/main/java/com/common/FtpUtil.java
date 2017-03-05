package com.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FtpUtil {
	private FTPClient  ftpClient = new FTPClient ();
	private OutputStream os = null;
	private InputStream is = null;
	
	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}
	/**
	 * 链接ftp
	 */
	public void connect(String hostName,int port,String FtpUserName,String FtpPassword){
		try {
			ftpClient.connect(hostName,port);
			int reply = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){  //ftp拒绝链接
	            //logger.error("ftp拒绝链接...");
				System.out.println("ftp拒绝链接...");
	            ftpClient.disconnect();
	        }
			ftpClient.login(FtpUserName, FtpPassword);
			ftpClient.enterLocalPassiveMode();       //设置被动模式    通知server端开通端口传输数据
            ftpClient.setBufferSize(256);//设置上传附件缓冲区大小
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置附件的类型
            ftpClient.setControlEncoding("utf-8");//设置字符编码
            //logger.info("登录成功！");
            System.out.println("登录成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放资源方法
	 * 释放缓存资源和ftp账号
	 */
	public void display(){
		try {
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.flush();//刷新缓存
				os.close();
			}
			ftpClient.logout();//退出登录
			ftpClient.disconnect();//关闭链接
			System.out.println("ftp链接关闭");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * ftp下载
	 */
	public void DownLoadFile(){
		
	}
	
	
	public void upload(MultipartFile file,String FilePath){
		try {
			boolean isdir = ftpClient.changeWorkingDirectory(FilePath);//判断是否有该文件夹
			if(!isdir){
				ftpClient.makeDirectory(FilePath);//创建文件夹
			}
			
			//ftpClient.changeWorkingDirectory(file.getOriginalFilename().getBytes("utf-8"));
			
			//String[] files = file.list();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 检测是否存在该文件夹，如果存在返回1
	 * 如果该文件夹不存在，自动创建该文件夹，返回2
	 * 错误返回0
	 * @param FilePath 文件路径
	 */
	public int IsDir(String FilePath){
		try {
			boolean check = ftpClient.changeWorkingDirectory(FilePath);
			return check?1:2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
