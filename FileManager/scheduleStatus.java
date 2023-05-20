package FileManager;

import java.io.Serializable;

public enum scheduleStatus implements Serializable
{//日程状态枚举变量
    Completed,
    Ongoing,
    TimeOut,
    NotStarted
};