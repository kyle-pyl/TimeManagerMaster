package FileManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileIndex implements Serializable {
    public int numOfFile = 0;
    List<ScheduleTime> dataList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
}
