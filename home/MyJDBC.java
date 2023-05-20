package home;

import FileManager.SchedulBlock;
import FileManager.ScheduleTime;
import FileManager.importanceLevel;
import FileManager.scheduleStatus;

import java.sql.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 类JdbcDemo封装数据库操作及相关变量
 * conn 获取连接
 * sql 建立连接
 * res 获取结果
 */
public class MyJDBC
{
    static Connection   conn;
    static Statement    sql;
    static ResultSet    res;
    /**
     * getConnection() 方法 获取数据库连接并返回Connection
     * @return
     */
    public static Connection getConnection()
    {
        try
        {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            //追踪异常并打印
            e.printStackTrace();
        }
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist_new?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","pyl20021124");
            System.out.println("数据库连接成功");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭资源
     */
    public static void closeResource() throws SQLException {
        if (res != null)
        {
            res.close();
        }
        if (sql != null)
        {
            sql.close();
        }
        if (conn != null)
        {
            conn.close();
        }
        System.out.println("数据释放成功");
    }

    /**
     * 查询用户所有信息并返回结果集
     * @param user
     * @return
     */
    public static void selectUserAllData(userProfile user) throws SQLException {
        sql = conn.createStatement();
        //执行SQL语句,查询指定用户所有信息,三个表等值连接，返回结果集
        String user_account = user.getUserAccount();
        res = sql.executeQuery( "select * " +
                " from user_data" +
                " inner join start_time on user_data.user_name = start_time.user_data_name " +
                " and user_data.title = start_time.user_data_title" +
                " inner join end_time on user_data.user_name = end_time.user_data_name " +
                " and user_data.title = end_time.user_data_title" +
                " inner join establish_time on user_data.user_name = establish_time.user_data_name" +
                " and user_data.title = establish_time.user_data_title" +
                " where user_data.user_name = '" + user_account +"'");
        //sql.close();
        //res.close();
    }

    public static void updateLocal(List<SchedulBlock> dataList,userProfile user) throws SQLException
    {
        //selectUserAllData(user);
        sql = conn.createStatement();
        //执行SQL语句,查询指定用户所有信息,三个表等值连接，返回结果集
        res = sql.executeQuery( "select * " +
                " from user_data" +
                " inner join start_time on user_data.user_name = start_time.user_data_name " +
                " and user_data.title = start_time.user_data_title" +
                " inner join end_time on user_data.user_name = end_time.user_data_name " +
                " and user_data.title = end_time.user_data_title" +
                " inner join establish_time on user_data.user_name = establish_time.user_data_name" +
                " and user_data.title = establish_time.user_data_title" +
                " where user_data.user_name = '" + user.getUserAccount() +"'" );

        //sql.close();

        while (res.next())
        {
            /**
             * 以下为从数据库中获取信息
             * 基本信息
             */
            String title = res.getString("title");//文本标题
            String content = res.getString("content");//文本内容
            String importance = res.getString("importance");//紧急程度
            String status = res.getString("status");//事件状态
            /**
             * 开始时间 年、月、日、时、分、秒
             */
            int start_time_year = res.getInt("start_time_year");
            int start_time_month = res.getInt("start_time_month");
            int start_time_day = res.getInt("start_time_day");
            int start_time_hour = res.getInt("start_time_hour");
            int start_time_minute = res.getInt("start_time_minute");
            int start_time_second = res.getInt("start_time_second");
            /**
             * 结束时间 年、月、日、时、分、秒
             */
            int end_time_year = res.getInt("end_time_year");
            int end_time_month = res.getInt("end_time_month");
            int end_time_day = res.getInt("end_time_day");
            int end_time_hour = res.getInt("end_time_hour");
            int end_time_minute = res.getInt("end_time_minute");
            int end_time_second = res.getInt("end_time_second");

            /**
             * 建立日程时间（最后一次修改时间） 年、月、日、时、分、秒
             */
            int establish_time_year = res.getInt("establish_time_year");
            int establish_time_month = res.getInt("establish_time_month");
            int establish_time_day = res.getInt("establish_time_day");
            int establish_time_hour = res.getInt("establish_time_hour");
            int establish_time_minute = res.getInt("establish_time_minute");
            int establish_time_second = res.getInt("establish_time_second");

            /**
             * 以下为新建对象
             * 并给对象赋值
             */

            SchedulBlock newData = new SchedulBlock();
            newData.importance = importanceLevel.valueOf(importance);
            newData.title = title;
            newData.text = content;
            newData.status = scheduleStatus.valueOf(status);



            ScheduleTime end_time = new ScheduleTime();
            ScheduleTime start_time = new ScheduleTime();
            ScheduleTime establish_time = new ScheduleTime();

            end_time.year = end_time_year;
            end_time.month = end_time_month;
            end_time.day = end_time_day;
            end_time.hour = end_time_hour;
            end_time.minute = end_time_minute;
            end_time.second = end_time_second;

            start_time.year = start_time_year;
            start_time.month = start_time_month;
            start_time.day = start_time_day;
            start_time.hour = start_time_hour;
            start_time.minute = start_time_minute;
            start_time.second = start_time_second;

            establish_time.year = establish_time_year;
            establish_time.month = establish_time_month;
            establish_time.day = establish_time_day;
            establish_time.hour = establish_time_hour;
            establish_time.minute = establish_time_minute;
            establish_time.second = establish_time_second;

            /**
             * 以下为原有本地文件的ScheduleBlock中的信息
             * 本地信息存放于List<SchedulBlock> dataList中
             * 首先判断从数据库下载的信息是否存在于本地文件中
             * 再判断谁的最后更新时间更晚
             */
            int i = 0;
            newData.end = end_time;
            newData.start = start_time;
            newData.establish = establish_time;
            boolean isFind = false;
            boolean isupdata = false;
            for(;i < dataList.size();i++)
            loop:{
                if (dataList.get(i).title.equals(title)) //表示本地文件和数据库中有相同日程
                {
                    if (dataList.get(i).establish.compareTo(establish_time) < 0) //表示本地文件时间早于数据库时间
                    {
                        /**
                         * 更新dataList
                         */
                        dataList.set(i, newData);
                        isFind = true;
                        isupdata = true;
                    }
                    else
                    {
                        isFind = true;
                        break loop;
                    }
                }
            }
            //for循环结束表示没有找到，直接加入dataList
            if (i == dataList.size() && isFind == false && isupdata == false)
            {
                dataList.add(newData);
            }
        }
        Collections.sort(dataList, new Comparator<SchedulBlock>()
        {
            @Override
            public int compare(SchedulBlock o1, SchedulBlock o2)
            {
                ScheduleTime o1_end = o1.end;
                ScheduleTime o2_end = o2.end;
                return o1.end.compareTo(o2.end);
            }
        });
        closeResource();
    }

    /**
     *  insertData()向表中添加数据
     *  返回true 更新成功
     *  返回false 更新失败
     */
    public boolean updataDataBase(List<SchedulBlock> dataList,userProfile user) throws SQLException
    {
        //selectUserAllData(user);

        for(int index = 0;index < dataList.size();index++)
        Loop:{
            sql = conn.createStatement();
            //执行SQL语句,查询指定用户所有信息,三个表等值连接，返回结果集
            res = sql.executeQuery( "select * " +
                    " from user_data " +
                    " inner join start_time on user_data.user_name = start_time.user_data_name " +
                    " and user_data.title = start_time.user_data_title" +
                    " inner join end_time on user_data.user_name = end_time.user_data_name " +
                    " and user_data.title = end_time.user_data_title" +
                    " inner join establish_time on user_data.user_name = establish_time.user_data_name" +
                    " and user_data.title = establish_time.user_data_title" +
                    " where user_data.user_name ='" + user.getUserAccount()+"'");
            int  reOne = -1;
            int  reTwo = -1;
            int  reThree = -1;
            int  reFour = -1;
            while(res.next())
            {
                String title = res.getString("title");//从数据库中获取文本标题
                if (dataList.get(index).title.equals(title))//说明数据库中已经存在该条数据，可能需要更新数据库
                {
                    ScheduleTime establish = new ScheduleTime();
                    establish.year = res.getInt("establish_time_year");
                    establish.month = res.getInt("establish_time_month");
                    establish.day = res.getInt("establish_time_day");
                    establish.hour = res.getInt("establish_time_hour");
                    establish.minute = res.getInt("establish_time_minute");
                    establish.second = res.getInt("establish_time_second");
                    if (dataList.get(index).establish.compareTo(establish) > 0)//表示本地文件时间晚于数据库时间，需要更新数据库
                    {
                        sql = conn.createStatement();
                        /**
                         * 更新了数据库中的文本、重要程度、状态
                         */
                        reOne = sql.executeUpdate("update user_data set content = '"+ dataList.get(index).text +
                                "' ,importance = '"+ dataList.get(index).importance +
                                "' ,status = '"+dataList.get(index).status +
                                "' where user_name = '"+user.getUserAccount()+"' and title = '" + title+"'");
                        //sql.close();
                        if (reOne > 0)//表示更新成功
                        {
                            sql = conn.createStatement();
                            /**
                             * 更新了日程的开始时间
                             */
                            reTwo = sql.executeUpdate("update start_time set start_time_year = " + dataList.get(index).start.year +
                                    " ,start_time_month = "+dataList.get(index).start.month +
                                    " ,start_time_day = "+dataList.get(index).start.day +
                                    " ,start_time_hour = "+dataList.get(index).start.hour +
                                    " ,start_time_minute = "+dataList.get(index).start.minute +
                                    " ,start_time_second = "+dataList.get(index).start.second +
                                    " where start_time.user_data_name = '" +user.getUserAccount() + "'"+" and start_time.user_data_title = '" + title +"'");
                            sql.close();
                            if (reTwo > 0)
                            {
                                sql = conn.createStatement();
                                /**
                                 * 更新了日程结束时间
                                 */
                                reThree = sql.executeUpdate("update end_time set end_time_year = " + dataList.get(index).end.year +
                                        " ,end_time_month = "+ dataList.get(index).end.month +
                                        " ,end_time_day = "+ dataList.get(index).end.day +
                                        " ,end_time_hour = "+dataList.get(index).end.hour +
                                        " ,end_time_minute = "+dataList.get(index).end.minute +
                                        " ,end_time_second = "+dataList.get(index).end.second +
                                        " where end_time.user_data_name = '" +user.getUserAccount() + "'"+" and end_time.user_data_title = '" + title+"'");
                                sql.close();
                                if (reThree > 0)
                                {
                                    sql = conn.createStatement();
                                    /**
                                     * 更新了日程的最后建立时间
                                     */
                                    reFour = sql.executeUpdate("update establish_time set establish_time_year = " + dataList.get(index).establish.year +
                                            " ,establish_time_month = "+ dataList.get(index).establish.month +
                                            " ,establish_time_day = "+ dataList.get(index).establish.day +
                                            " ,establish_time_hour = "+dataList.get(index).establish.hour +
                                            " ,establish_time_minute = "+dataList.get(index).establish.minute +
                                            " ,establish_time_second = "+dataList.get(index).establish.second +
                                            " where establish_time.user_data_name = '" +user.getUserAccount() + "'"+" and establish_time.user_data_title = '" + title+"'");
                                    sql.close();
                                    if (reFour > 0)
                                    {
                                        System.out.println("更新成功");
                                        selectUserAllData(user);
                                        break;
                                    }
                                    else
                                    {
                                        System.out.println("更新失败");
                                        return false;
                                    }
                                }
                                else
                                {
                                    System.out.println("更新失败");
                                    return false;
                                }
                            }
                            else
                            {
                                System.out.println("更新失败");
                                return false;
                            }
                        }
                        else
                        {
                            System.out.println("更新失败");
                            return false;
                        }
                    }
                    else
                    {
                        break Loop;
                    }
                }
            }
            if (!res.next())//表示数据库中不存在本地文件的数据
            {

                sql = conn.createStatement();
                sql.executeUpdate("insert into user_data(user_name,title,content,importance,status)" +
                        "values ('"+user.getUserAccount()+"','"+dataList.get(index).title+"','"+dataList.get(index).text+"','"+
                        dataList.get(index).importance+"','"+dataList.get(index).status+"')");
                sql.close();

                sql = conn.createStatement();
                sql.executeUpdate("insert into start_time(user_data_name,user_data_title,start_time_year,start_time_month,start_time_day,start_time_hour,start_time_minute,start_time_second)" +
                        "values ('"+user.getUserAccount()+"','"+dataList.get(index).title+"','"+dataList.get(index).start.year+"','"+dataList.get(index).start.month+"','"+dataList.get(index).start.day
                        +"','"+dataList.get(index).start.hour+"','"+dataList.get(index).start.minute+"','"+dataList.get(index).start.second+"')");
                sql.close();

                sql = conn.createStatement();
                sql.executeUpdate("insert into end_time(user_data_name,user_data_title,end_time_year,end_time_month,end_time_day,end_time_hour,end_time_minute,end_time_second)"+
                        "values ('"+user.getUserAccount()+"','"+dataList.get(index).title+"','"+dataList.get(index).end.year+"','"+dataList.get(index).end.month+"','"+dataList.get(index).end.day
                        +"','"+dataList.get(index).end.hour+"','"+dataList.get(index).end.minute+"','"+dataList.get(index).end.second+"')");
                sql.close();

                sql = conn.createStatement();
                sql.executeUpdate("insert into establish_time(user_data_name,user_data_title,establish_time_year,establish_time_month,establish_time_day,establish_time_hour,establish_time_minute,establish_time_second)"+
                        "values ('"+user.getUserAccount()+"','"+dataList.get(index).title+"','"+dataList.get(index).establish.year+"','"+dataList.get(index).establish.month+"','"+dataList.get(index).establish.day +"','"+
                        dataList.get(index).establish.hour+"','"+dataList.get(index).establish.minute+"','"+dataList.get(index).establish.second+"')");
            }
        }
        closeResource();
        return true;
    }

    public void changinfo(String nickname, String password) throws SQLException {
        sql = conn.createStatement();
        int s = 0;
        s = sql.executeUpdate("update user_info set user_nickname = '" + nickname + "'" +
                ",user_password = '" + password + "'" +
                " where user_info.user_name = '" + Data.userName + "'");
        closeResource();
    }
}
