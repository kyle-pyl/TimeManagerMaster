package FileManager;

import java.io.*;

public class SchedulBlock implements Serializable,Comparable<SchedulBlock>
{
    //日程结构
    public String title;

    public ScheduleTime start = new ScheduleTime();
    public ScheduleTime end = new ScheduleTime();
    public ScheduleTime establish = new ScheduleTime();

    public scheduleStatus status = scheduleStatus.NotStarted;
    public importanceLevel importance = importanceLevel.Middle;
    public String text;

//    public SchedulBlock myclone() {
//        SchedulBlock sch = null;
//        try{
//            //将该对象序列化成流
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(baos);
//            oos.writeObject(this);
//
//            //将流序列化成对象
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//            sch = (SchedulBlock) objectInputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return sch;
//    }

    @Override
    public int compareTo(SchedulBlock o)
    {
        if(end.compareTo(o.end) == 0)
        {
            return title.compareTo(o.title);
        }
        else
        {
            return end.compareTo(o.end);
        }
    }

    @Override
    public String toString()
    {
        return ">>" + title + "\n状态:" + status.name() + "\t重要性:" + importance.name() +
                "\n建立时间:" + establish.toString() + "\t开始时间:" + start.toString() + "\t截止时间:" + end.toString() + "\n" +
                text + "\n";
    }
}
