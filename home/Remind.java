package home;

import FileManager.FileIndex;
import FileManager.FileManage;
import FileManager.SchedulBlock;
import FileManager.FileManage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import static java.lang.Thread.sleep;


public class Remind extends Thread{
    @Override
    public void run() {
        {
            JFrame jf = new JFrame("提醒");
            // 从文件中读取图像
            try {
                // 从文件中读取图像
                File file = new File("src/images/remind.jpg"); // 图像文件的路径
                Image image = ImageIO.read(file);
                // 创建一个标签并设置图像为标签的图标
//                Container container = jf.getContentPane();
                JLabel label = new JLabel(new ImageIcon(image));
//                JLabel label = new JLabel("有任务超时");
//                label.setHorizontalAlignment(SwingConstants.CENTER);
//                container.add(label);
                // 将标签添加到窗口的内容面板中
                // 设置窗口的大小和位置
                jf.getContentPane().add(label);
                jf.pack();
//                jf.setSize(200,150);
//                jf.setSize(400,400);
                jf.setLocationRelativeTo(null);
                // 设置窗口的可见性和关闭操作
                jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (Exception e) {
                e.printStackTrace();
            }


            boolean flag = false;
            while (Data.threadRunFlag) {

                synchronized (Data.mutex) {
                    if (Data.sch_Last != null && Data.sch_Last.end.compareTo(Data.getNow()) <= 0) {
                        jf.setVisible(true);
                        flag = true;

                    } else {
                        flag = false;

                    }
                }
                try {
                    if (flag) {
                        sleep(60000);
                    } else {
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                jf.setVisible(false);
            }
        }
        System.out.println("线程：remind结束");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.exit(0);
        // 输出主线程组的名称
        //System.out.println("主线程组的名称：" + mainGroup.getName());
//        mainGroup.notifyAll();
//        mainGroup.stop();
    }
}
