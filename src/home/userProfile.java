package home;

import FileManager.SchedulBlock;

/**
 * �û�����
 * �����û��ǳơ��û��˺š��û�����
 * �˺�ɴӴ˽ṹ���л�ȡ����Ӧ��Ϣ
 */

public class userProfile
{
    private String user_id;
    private String user_account;
    private String user_password;
    private SchedulBlock schedule = new SchedulBlock(); //�û����ճ���Ϣ



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
