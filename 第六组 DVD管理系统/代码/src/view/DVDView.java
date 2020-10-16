package view;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Dao.DVDDao;

/**
 * @Description--dvd操作：输入和输出
 * @author 第六小组
 * @version
 * @Date 2020年7月3日下午13:41:21
 *
 */

public class DVDView 
{
	//全局变量
	Scanner sc = new Scanner(System.in);
	DVDDao dao = new DVDDao();
	
	//普通用户
	public void commonMenu() 
	{
		int input;
		do
		{
			System.out.println("\n");
			System.out.println("---------------普通用户界面---------");
			System.out.println("1、显示所有DVD");
			System.out.println("2、借阅DVD");
			System.out.println("3、归还DVD");
			System.out.println("4、DVD排行榜");
			System.out.println("5、查询个人借阅");
			System.out.println("0、退出系统");
			System.out.println("请输入您的选项【0-5】");
			input=sc.nextInt();
			switch(input) 
			{
				case 0:System.out.println("欢迎下次光临！");break;
				case 1:
					showAllDVD();
					break;
				case 2:
					borDvd();
					break;
				case 3:
					returnDvd();
					break;
				case 4:
					showDvdOrder();
					break;
				case 5:
					checkSelf();
					break;
				default:System.out.println("输入选项错误，请确定后重新输入！");
				    break;
			}
		}while(input!=0);
	}
	
	//管理员的界面
	public void manageMenu() 
	{
		int input;
		do
		{
			System.out.println("\n");
			System.out.println("------------管理员界面-----------");
			System.out.println("1、显示所有DVD");
			System.out.println("2、DVD排行榜");
			System.out.println("3、新增DVD");
			System.out.println("4、删除DVD");
			System.out.println("0、退出系统");
			System.out.println("请输入您的选项【0-4】");
			input=sc.nextInt();
			switch(input) 
			{
				case 0:System.out.println("欢迎下次光临！");break;
				case 1:
					showAllDVD();
					break;
				case 2:
					showDvdOrder();
					break;
				case 3:
					InsertDvd();
					break;
				case 4:
					DeltDvd();
					break;
				default:System.out.println("输入选项错误，请确定后重新输入！");
				    break;
			}
		}while(input!=0);
	}
	
	//显示所有DVD
	public void showAllDVD() 
	{
		List<List<Object>> list = dao.queryAllDvd();
		if (list == null || list.size() == 0) 
		{
			System.out.println("没有查询到数据");
		} 
		else 
		{
			System.out.println("编号\t名字\t价格       出版社\t\t是否借出 借阅人\t借阅日期\t     借阅次数");
			for (int i = 0; i < list.size(); i++) 
			{
				for (int j = 0; j < list.get(i).size(); j++) 
				{
					System.out.print(list.get(i).get(j) + "\t");
				}
				System.out.println();
			}
		}
		
	}

	//排行榜
	public void showDvdOrder() 
	{
		 List<List<Object>> list = dao.queryByOrder();
		if (list == null || list.size() == 0) 
		{
			System.out.println("没有查询到数据");
		} 
		else 
		{
			System.out.println("编号\t名字\t价格\t出版社\t\t是否借出");
			for (int i = 0; i < list.size(); i++) 
			{
				for (int j = 0; j < list.get(i).size(); j++) 
				{
					System.out.print(list.get(i).get(j) + "\t");
				}
				System.out.println();
			}
		}
		
	}
	
	//借阅
	public void borDvd() 
	{
		System.out.println("请输入借阅dvd的编号：");
		int id=sc.nextInt();
		//查询dvd是否存在
		List<List<Object>> list = dao.queryById(id);
		
		//没查到
		if(list==null||list.size()==0) 
		{
			System.out.println("编号为："+id+"的DVD未找到，请确定后再继续");
		}
		//查到
		else 
		{
			//被借出
			if((int)list.get(0).get(4)==1) 
			{
				System.out.println("编号为："+id+"的DVD已经被借出，不能再次被借");
			}
			
			//未被借出，可借
			else 
			{
				System.out.println("请输入借阅者的姓名：");
				String name=sc.next();
				System.out.println("请输入借阅的日期，格式2020-1-23");
				String date=sc.next();
				int row=dao.borrow(id, name, date);
				if(row==0) 
				{
					System.out.println("借阅失败！");
				}
				else 
				{
					System.out.println("借阅成功！");
				}
			}
		}
	}
	
	//归还
	public void returnDvd() 
	{
		System.out.println("请输入归还dvd的编号：");
		int id=sc.nextInt();
		//查询dvd是否存在
		List<List<Object>> list = dao.queryById(id);
		//没查到
		if(list==null||list.size()==0) 
		{
			System.out.println("编号为："+id+"的DVD未找到，请确定后再继续");
		}
		//查到了
		else 
		{
			if((int)list.get(0).get(4)==0) 
			{
				//未借出
				System.out.println("编号为："+id+"的DVD未借出，不用归还");
			}
			else 
			{
				//已经借出可以归还
				// 计算借阅时间差。单位为‘天’
				Date nowDate = new Date();//获取系统当前日期。获得数单位为‘毫秒’
				String borDate=(String)(list.get(0).get(6));//借阅日期，字符串型
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期和字符串转换
				try 
				{
					Date date = sdf.parse(borDate);//将字符串（往上2第二行）转换为日期
					System.out.println(nowDate + "--------------" + date);
					long ms =nowDate.getTime() - date.getTime();//获得数单位为‘毫秒’
					int day =(int)(ms / (1000 * 60 * 60 * 24));//毫秒转换为天
					System.out.println("编号为："+id+"已经借出"+day+"天");
				} 
				catch (ParseException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//判断是否顺利归还
				int row=dao.returnDvd(id);
				if(row==0) {
					System.out.println("归还失败！");
				}else {
					System.out.println("归还成功！");
				}
			}
		}
	}
	
	//新增
	public void InsertDvd() 
	{
		System.out.println("请输入新增dvd的名字：");
		String name=sc.next();
		System.out.println("请输入新增dvd的价格：");
		Double price=sc.nextDouble();
		System.out.println("请输入新增dvd的出版社：");
		String publish=sc.next();
		int row=dao.insert(name, price, publish);
		if(row>=1) 
		{
			System.out.println("新增成功");
		}
		else 
		{
			System.out.println("新增失败");
		}
	}
	//删除
	public void DeltDvd() 
	{
		System.out.println("请输入删除dvd的id：");
		int id=sc.nextInt();
		int row=dao.Del(id);
		if(row>=1) 
		{
			System.out.println("删除成功");
		}
		else 
		{
			System.out.println("删除失败");
		}
	}
	public void checkSelf() {
		System.out.println("请输入借阅人的名字：");
		String name=sc.next();
		 List<List<Object>> list=dao.queryByName(name);
		if (list == null || list.size() == 0) 
		{
			System.out.println("没有查询到数据");
		} 
		else 
		{
			System.out.println("借阅人\tDVD名字\t 借阅日期");
			for (int i = 0; i < list.size(); i++) 
			{
				for (int j = 0; j < list.get(i).size(); j++) 
				{
					System.out.print(list.get(i).get(j)+"\t");
				}
				System.out.println();
			}
			//已经借出可以归还
			// 计算借阅时间差。单位为‘天’
			Date nowDate = new Date();//获取系统当前日期。获得数单位为‘毫秒’
			String borDate=(String)(list.get(0).get(2));//借阅日期，字符串型
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期和字符串转换
			try 
			{
				Date date = sdf.parse(borDate);//将字符串（往上2第二行）转换为日期
				System.out.println(nowDate + "---" + date);
				long ms =nowDate.getTime() - date.getTime();//获得数单位为‘毫秒’
				int day =(int)(ms / (1000 * 60 * 60 * 24));//毫秒转换为天
				System.out.println("已经借出"+day+"天");
			} 
			catch (ParseException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
