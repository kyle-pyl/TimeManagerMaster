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
            JFrame jf = new JFrame("����");
            // ���ļ��ж�ȡͼ��
            try {
                // ���ļ��ж�ȡͼ��
                File file = new File("src/images/remind.jpg"); // ͼ���ļ���·��
                Image image = ImageIO.read(file);
                // ����һ����ǩ������ͼ��Ϊ��ǩ��ͼ��
//                Container container = jf.getContentPane();
                JLabel label = new JLabel(new ImageIcon(image));
//                JLabel label = new JLabel("������ʱ");
//                label.setHorizontalAlignment(SwingConstants.CENTER);
//                container.add(label);
                // ����ǩ��ӵ����ڵ����������
                // ���ô��ڵĴ�С��λ��
                jf.getContentPane().add(label);
                jf.pack();
//                jf.setSize(200,150);
//                jf.setSize(400,400);
                jf.setLocationRelativeTo(null);
                // ���ô��ڵĿɼ��Ժ͹رղ���
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
        System.out.println("�̣߳�remind����");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.exit(0);
        // ������߳��������
        //System.out.println("���߳�������ƣ�" + mainGroup.getName());
//        mainGroup.notifyAll();
//        mainGroup.stop();
    }
}
