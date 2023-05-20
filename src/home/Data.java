package home;

import FileManager.FileManage;
import FileManager.SchedulBlock;
import FileManager.ScheduleTime;
import FileManager.scheduleStatus;
import sun.reflect.misc.FieldUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Data {
    static List<SchedulBlock> futureList;
    static List<SchedulBlock> historyList;
    static String imgUrl = "";

    static boolean threadRunFlag = false;

    static Object mutex = new Object();

    static SchedulBlock sch_Last;

    static int curIndex;
    static int historyIndex;

    static boolean isLogin = false;

    static double x;
    static double y;

    static String userName="";
    static String password="";
    static String NickName="ÓÎ¿Í";

    static void init()
    {
        futureList = new ArrayList<>();
        historyList = new ArrayList<>();
        FileManage fm = new FileManage();
        fm.init(futureList);
        fm.getHistory(historyList);
        curIndex = 0;
    }

    static void update() throws CloneNotSupportedException, IOException {
        ScheduleTime sct = getNow();
        for (int i = 0; i <historyList.size(); i++) {
            if(historyList.get(i).end.compareTo(sct) > 0)
            {
                futureList.add(historyList.get(i));
                historyList.remove(i);
            }
        }
        FileManage fm = new FileManage();
        fm.update(futureList);
        fm.updateHistory(historyList);
        synchronized (mutex)
        {
            sch_Last=null;
            for(int i = 0;i < futureList.size();i++)
            {
                if(futureList.get(i).status != scheduleStatus.Completed)
                {
                    sch_Last=new SchedulBlock();
                    sch_Last.end = futureList.get(i).end.clone();
                    break;
                }
            }
        }
        Path source = Paths.get(URI.create(imgUrl));
        File target = new File("src/images/Áõê»È».jpg");

        Files.copy(source, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    static ScheduleTime getNow()
    {
        ScheduleTime sct = new ScheduleTime();
        Calendar cal = Calendar.getInstance();
        sct.year = cal.get(Calendar.YEAR);
        sct.month = cal.get(Calendar.MONTH) + 1;
        sct.day = cal.get(Calendar.DATE);
        sct.hour = cal.get(Calendar.HOUR_OF_DAY);
        sct.minute = cal.get(Calendar.MINUTE);
        sct.second = cal.get(Calendar.SECOND);
        return sct;
    }

    static int getTodayCount()
    {
        ScheduleTime sctBegin = getNow();
        ScheduleTime sctEnd = getNow();
        sctEnd.day++;
        sctEnd.hour=0;
        sctEnd.minute=0;
        sctEnd.second = 0;
        int cnt = 0;
        for(SchedulBlock sch:futureList)
        {
            if(sch.end.compareTo(sctBegin) >= 0 && sch.end.compareTo(sctEnd) < 0)
            {
                cnt++;
            }
        }
        return cnt;
    }
    static int getMonthCount()
    {
        ScheduleTime sctBegin = getNow();
        ScheduleTime sctEnd = getNow();
        sctEnd.month++;
        sctEnd.day=1;
        sctEnd.hour=0;
        sctEnd.minute=0;
        sctEnd.second = 0;
        int cnt = 0;
        for(SchedulBlock sch:futureList)
        {
            if(sch.end.compareTo(sctBegin) >= 0 && sch.end.compareTo(sctEnd) < 0)
            {
                cnt++;
            }
        }
        return cnt;
    }
    static int getWeekCount()
    {
        ScheduleTime sctBegin = getNow();
        ScheduleTime sctEnd = getNow();
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = (dayOfWeek+5)%7+1;
        sctEnd.day+=7-dayOfWeek+1;
        if(sctEnd.day > cal.getActualMaximum(Calendar.DAY_OF_MONTH))
        {
            sctEnd.month++;
            sctEnd.day -= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        sctEnd.hour=0;
        sctEnd.minute=0;
        sctEnd.second = 0;
        int cnt = 0;
        for(SchedulBlock sch:futureList)
        {
            if(sch.end.compareTo(sctBegin) >= 0 && sch.end.compareTo(sctEnd) < 0)
            {
                cnt++;
            }
        }
        return cnt;
    }
}
