package home;

import FileManager.SchedulBlock;

/**
 * 用户资料
 * 包含用户昵称、用户账号、用户密码
 * 此后可从此结构体中获取到相应信息
 */

public class userProfile
{
    private String user_id;
    private String user_account;
    private String user_password;
    private SchedulBlock schedule = new SchedulBlock(); //用户的日程信息



    public String getUserAccount()
    {
        return this.user_account;
    }

    public String getUser_id(){ return this.user_id; }

    public String getUser_password(){ return this.user_password; }

    public SchedulBlock getSchedule()
    {
        return this.schedule;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setSchedule(SchedulBlock schedule) {
        this.schedule = schedule;
    }
}
