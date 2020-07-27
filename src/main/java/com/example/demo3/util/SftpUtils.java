package com.example.demo3.util;

import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtils {
    /**
     * 利用JSch包实现SFTP下载、上传文件
     * @param ip 主机IP
     * @param username 主机登陆用户名
     * @param password 主机登陆密码
     * @param port 主机ssh2登陆端口
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2018/05/28。文件的路径为basePath+filePath
     * @param fileName 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadBySftp(String ip, String username, String password, int port, String basePath, String filePath, String fileName, InputStream input) throws Exception {
        boolean result = false;
        Session session = null;
        Channel channel = null;
        String fullPath = null;

        JSch jsch = new JSch();

        // 采用指定的端口连接服务器
        session = jsch.getSession(username, ip, port);

        // 如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("session is null");
        }

        // 设置登陆主机的密码
        session.setPassword(password); // 设置密码
        // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        // 设置登陆超时时间
        session.connect(10000);

        try {
            // 创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;

            fullPath = basePath + filePath;

            // 判断存放目录是否存在，如不存在就创建目录
            // try {
            // sftp.ls(fullPath);
            // } catch (Exception e) {
            // sftp.mkdir(fullPath);
            // }
            //使用JSch远程创建递归目录，最后得到可以直接进入且存放图片的path
            String[] folders = fullPath.split("/");
            //返回的结果数组比预计的数组多了一个元素，第一个元素为空
            System.out.println(folders.length);
            String path = "";
            //第一个元素是空字符串，所以从第二个元素开始遍历，即i=1
            for (int i=1; i<folders.length; i++) {
                String folder = folders[i];
                path = path + "/" + folder;
                try {
                    //用是否能进入该目录来判断该目录是否存在
                    sftp.cd(path);
                } catch (SftpException e) {
                    //上面不能进入则报错，执行以下的创建命令
                    sftp.mkdir(path);
                }
            }
            System.out.println(path);

            // 进入服务器指定的文件夹
            sftp.cd(path);

            // 以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
            OutputStream os = sftp.put(fileName);
            // InputStream instream = new FileInputStream(new
            // File("c:/print.txt"));

            byte b[] = new byte[1024];
            int n;
            while ((n = input.read(b)) != -1) {
                os.write(b, 0, n);
            }
            result = true;
            os.flush();
            os.close();
            input.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            session.disconnect();
            channel.disconnect();
        }
    }
}