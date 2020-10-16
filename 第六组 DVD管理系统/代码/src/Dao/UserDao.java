
package Dao;

import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;

/**
 * @Description
 * @author 第六组
 * @version
 * @Date 2020年7月3日下午13:42:21
 *
 */
public class UserDao 
{
	//登录功能。参数：用户名和密码，返回值：查询的二维数组
	public List<List<Object>> login(String userName,String passWord)
	{
		JDBCUtil jdbc = new JDBCUtil();
		List<Object> list=new ArrayList<Object>();
		list.add(userName);
		list.add(passWord);
		List<List<Object>> query = jdbc.query("select * from users where username=? and password=?", list);
		return query;
	}
	
	public List<List<Object>>registerjudge(String userName,String Password,int role){
		JDBCUtil jdbc=new JDBCUtil();
		List<Object> list=new ArrayList<Object>();
		list.add(userName);
		List<List<Object>> judge=jdbc.query("select * from users where username=?", list);
		if(judge==null||judge.size()==0) {
			//继续注册
			//login表中增加新数据
			list.add(Password);
			list.add(role);
			int row=jdbc.update("insert into users values(null,?,?,?)", list);
			if(row==1)
			{
				System.out.println("注册成功！");
			}else {
				System.out.println("注册失败！");
			}
		}else {
			//用户名已经存在重新输入
			System.out.println("用户名已经存在！请重新输入");
		}
		return null;
	}

}
