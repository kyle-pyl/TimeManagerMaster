package FileManager;


import java.io.*;
import java.util.*;

public class FileManage {
    private String historyDir = "history";
    private String futureDir = "future";

    private boolean Create() {
        File dir = new File(historyDir);
        boolean mkdirs = dir.mkdirs();
        File dir2 = new File(futureDir);
        boolean mkdirs2 = dir2.mkdirs();
        return mkdirs2 && mkdirs;
    }

    /**
     * 初始化文件，并读取当前未截止的日程
     * @param dataList 用于获取需要的数据的List
     */
    public void init(List<SchedulBlock> dataList) {
        Create();
        FileIndex historyIndex = new FileIndex();
        FileIndex futureIndex = new FileIndex();
        FileIO<FileIndex> indexIO = new FileIO<>();
        historyIndex = indexIO.readLog(historyDir + "\\index", historyIndex);
        futureIndex = indexIO.readLog(futureDir + "\\index", futureIndex);
        FileIO<SchedulBlock> scheduleIO = new FileIO<>();
        Calendar cal = Calendar.getInstance();
        FileIndex newFutureIndex = new FileIndex();
        if (!dataList.isEmpty())
            dataList.clear();
        for (int i = 0; i < futureIndex.numOfFile; i++) {

            SchedulBlock sch = new SchedulBlock();
            sch = scheduleIO.readLog(futureDir + "\\" + i, sch);
            if (sch.end.year > cal.get(Calendar.YEAR) || sch.end.year == cal.get(Calendar.YEAR) && sch.end.month > cal.get(Calendar.MONTH) + 1 ||
                    sch.end.year == cal.get(Calendar.YEAR) && sch.end.month == cal.get(Calendar.MONTH) + 1 && sch.end.day > cal.get(Calendar.DATE) ||
                    sch.end.year == cal.get(Calendar.YEAR) && sch.end.month == cal.get(Calendar.MONTH) + 1 && sch.end.day == cal.get(Calendar.DATE) && sch.end.hour > cal.get(Calendar.HOUR_OF_DAY) ||
                    sch.end.year == cal.get(Calendar.YEAR) && sch.end.month == cal.get(Calendar.MONTH) + 1 && sch.end.day == cal.get(Calendar.DATE) && sch.end.hour == cal.get(Calendar.HOUR_OF_DAY) && sch.end.minute > cal.get(Calendar.MINUTE) ||
                    sch.end.year == cal.get(Calendar.YEAR) && sch.end.month == cal.get(Calendar.MONTH) + 1 && sch.end.day == cal.get(Calendar.DATE) && sch.end.hour == cal.get(Calendar.HOUR_OF_DAY) && sch.end.minute == cal.get(Calendar.MINUTE) && sch.end.second >= cal.get(Calendar.SECOND)) {
                dataList.add(sch);
                newFutureIndex.numOfFile++;
                newFutureIndex.titleList.add(sch.title);
                newFutureIndex.dataList.add(sch.end);
            } else {
                if(sch.status != scheduleStatus.Completed)
                    sch.status = scheduleStatus.TimeOut;
                historyIndex.numOfFile++;
                historyIndex.titleList.add(sch.title);
                historyIndex.dataList.add(sch.end);
                scheduleIO.writeLog(historyDir + "\\" + (historyIndex.numOfFile - 1), sch);
            }
        }
        indexIO.writeLog(historyDir + "\\index", historyIndex);
        indexIO.writeLog(futureDir + "\\index", newFutureIndex);
        for (int i = 0; i < newFutureIndex.numOfFile; i++) {
            scheduleIO.writeLog(futureDir + "\\" + i, dataList.get(i));
        }
    }

    /**
     * 用dataList更新存储还未完成日程的文件夹
     * @param dataList 用于更新日程文件夹的List
     */
    public void update(List<SchedulBlock> dataList)
    {
        Set<SchedulBlock> dataSet = new TreeSet<>();
        dataSet.addAll(dataList);
        dataList.clear();
        dataList.addAll(dataSet);
        Collections.sort(dataList);
        FileIndex futureIndex = new FileIndex();
        FileIO<SchedulBlock> scheduleIO = new FileIO<>();
        for(int i = 0;i < dataList.size();i++)
        {
            SchedulBlock sch = dataList.get(i);
            scheduleIO.writeLog(futureDir + "\\" + i, sch);
            futureIndex.numOfFile++;
            futureIndex.titleList.add(sch.title);
            futureIndex.dataList.add(sch.end);
        }
        FileIO<FileIndex> indexIO = new FileIO<>();
        indexIO.writeLog(futureDir + "\\index", futureIndex);

    }

    /**
     * 将历史文件读取到List中
     * @param dataList 用于获取历史日程数据的List
     */
    public void getHistory(List<SchedulBlock> dataList)
    {
        FileIndex historyIndex = new FileIndex();
        FileIO<FileIndex> indexIO = new FileIO<>();
        historyIndex = indexIO.readLog(historyDir + "\\index", historyIndex);
        FileIO<SchedulBlock> scheduleIO = new FileIO<>();
        if (!dataList.isEmpty())
            dataList.clear();
        Set<SchedulBlock> dataSet = new TreeSet<>();
        FileIndex newHistoryIndex = new FileIndex();
        for(int i = 0;i<historyIndex.numOfFile;i++)
        {
            SchedulBlock sch = new SchedulBlock();
            sch = scheduleIO.readLog(historyDir + "\\" + i, sch);
            dataSet.add(sch);
        }
        dataList.addAll(dataSet);
        Collections.sort(dataList);
        for(int i = 0;i < dataList.size();i++)
        {
            newHistoryIndex.dataList.add(dataList.get(i).end);
            newHistoryIndex.titleList.add(dataList.get(i).title);
            scheduleIO.writeLog(historyDir + "\\" + newHistoryIndex.numOfFile++, dataList.get(i));
        }
    }

    /**
     * 用dataList更新覆盖掉原本的日程文件
     * @param dataList 用于更新历史日程文件的List
     */
    public void updateHistory(List<SchedulBlock> dataList)
    {
        Set<SchedulBlock> dataSet = new TreeSet<>();
        dataSet.addAll(dataList);
        dataList.clear();
        dataList.addAll(dataSet);
        Collections.sort(dataList);
        FileIndex historyIndex = new FileIndex();
        FileIO<SchedulBlock> scheduleIO = new FileIO<>();
        for(int i = 0;i < dataList.size();i++)
        {
            SchedulBlock sch = dataList.get(i);
            scheduleIO.writeLog(historyDir + "\\" + i, sch);
            historyIndex.numOfFile++;
            historyIndex.titleList.add(sch.title);
            historyIndex.dataList.add(sch.end);
        }
        FileIO<FileIndex> indexIO = new FileIO<>();
        indexIO.writeLog(historyDir + "\\index", historyIndex);
    }
}
class FileIO<T> {
    public void writeLog(String name,T e) {
        try {
            File file;
            file = new File(name);

            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(e);
            os.flush();
            out.close();
            os.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public T readLog(String name,T e)  {
        try {
            File file;
            file = new File(name);
            if(!file.exists()) return e;
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream ds = new ObjectInputStream(in);
            e = (T) ds.readObject();
            in.close();
        }catch(Exception e1)
        {
            e1.printStackTrace();
        }
        return e;
    }
}
