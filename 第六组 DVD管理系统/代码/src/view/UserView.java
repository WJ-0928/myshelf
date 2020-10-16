
package view;

import java.util.List;
import java.util.Scanner;

import com.view.DVDView;

import Dao.UserDao;

/**
 * @Description
 * @author 第六组
 * @version
 * @Date 2020年7月3日下午13:43:21
 *
 */
public class UserView {
	public void beginMenu() {
		System.out.println("*******************欢迎进入DVD管理系统*******************");
		int judge;
		do {
		System.out.println("如果您是新用户请先注册(1.继续输入\t2.注册\t0.退出)");
		Scanner sc1= new Scanner(System.in);
		judge=sc1.nextInt();
		switch(judge){
		case 1:
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入用户名");
		String userName=sc.next();
		System.out.println("请输入登录密码");
		String password =sc.next();
		//登录功能：select
		List<List<Object>> login=new UserDao().login(userName,password);
		if(login==null||login.size()==0) {
			System.out.println("登录失败！");
		}else {
			System.out.println("登录成功！");
			if((int)(login.get(0).get(3))==1) {
				//管理员登录
				new DVDView().manageMenu();
			}else {
				//普通用户登录
				new DVDView().commonMenu();
			}
		}break;
		case 2://注册
			System.out.println("请输入用户名");
			Scanner sc2=new Scanner(System.in);
			String userName2=sc2.next();
			System.out.println("请输入密码");
			sc2=new Scanner(System.in);
			String passWord2=sc2.next();
			System.out.println("请输入角色（0.普通用户）\t(1.管理员）");
			int role=sc2.nextInt();
			int flag=0;
			while(flag==0) {
			if(role==0||role==1)
			{
				List<List<Object>> regis=new UserDao().registerjudge(userName2,passWord2,role);
				flag=1;
			}
			else {System.out.println("请确认角色为0/1");}
			}
			break;
		case 0:System.out.println("欢迎下次再来");break;
		default:System.out.println("请重新选择");
	}//(switch
		}while(judge!=0);
	}
}
/*
public class UserView 
{
	public void beginMenu() 
	{
		System.out.println("********欢迎进入DVD管理系统*******");
		System.out.println("请输入登录用户名：");
		Scanner sc = new Scanner(System.in);
		String userName=sc.next();
		System.out.println("请输入登录密码：");
		String passWord=sc.next();
		
		//登录功能：select
		List<List<Object>> login = new UserDao().login(userName,passWord);
		if(login==null||login.size()==0) 
		{
			System.out.println("登录失败");
		}
		else 
		{
			System.out.println("登录成功！");
			//获取登录者的角色，从第一行第四列中获取
			if((int)(login.get(0).get(3))==1) 
			{
				//管理员登录
				new DVDView().manageMenu();
			}
			else 
			{
				//普通用户登录
				new DVDView().commonMenu();
			}
		}
	}

}
*/
