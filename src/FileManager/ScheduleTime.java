package FileManager;

import java.io.Serializable;

public class ScheduleTime implements Serializable, Comparable<ScheduleTime>, Cloneable
{//日期结构体
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int second;


    public int compareTo(ScheduleTime o) {
        int ans;
        if(year > o.year || year == o.year && month > o.month ||
                year == o.year && month == o.month && day > o.day ||
                year == o.year && month == o.month && day == o.day && hour > o.hour ||
                year == o.year && month == o.month && day == o.day && hour == o.hour && minute > o.minute ||
                year == o.year && month == o.month && day == o.day && hour == o.hour && minute == o.minute && second > o.second)
        {
            ans = 1;
        }
        else if(year == o.year && month == o.month && day == o.day && hour == o.hour && minute == o.minute && second == o.second)
        {
            ans = 0;
        }
        else
        {
            ans = -1;
        }

        return ans;
    }
    @Override
    public String toString()
    {
        return year + "/" + month + "/" + day + "-" + hour + ":" + minute + ":" + second;
    }
    @Override
    public ScheduleTime clone() throws CloneNotSupportedException {
        return (ScheduleTime) super.clone();
    }

};
