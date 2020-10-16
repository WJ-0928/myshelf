package Dao;

import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCUtil;

/**
 * @Description
 * @author 第六组
 * @version
 * @Date 2020年7月3日下午11:47:48
 *
 */

public class DVDDao 
{
	JDBCUtil jdbc = new JDBCUtil();
	//查询所有dvd
	public List<List<Object>> queryAllDvd()
	{
		List<List<Object>> query = jdbc.query("select * from dvd", null);
		return query;
	}
	//新增功能：根据输入人名查历史借阅
	public List<List<Object>> queryByName(String borName)
	{
		List<Object> list=new ArrayList<Object>();
		list.add(borName);
		return jdbc.query("select borname,name,bordate from dvd where borname=?", list);
	}
	
	//根据借阅次数从大到小,查询所有dvd
	public List<List<Object>> queryByOrder()
	{
		return jdbc.query("select id,name,price,publish,state from dvd order by times desc", null);
	}
	
	//根据id，查询dvd
	public List<List<Object>> queryById(int id)
	{
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		return jdbc.query("select *from dvd where id=?", list);
	}
	
	//借阅
	public int borrow(int id,String name,String date) 
	{
		List<Object> list=new ArrayList<Object>();
		list.add(name);
		list.add(date);
		list.add(id);
		return jdbc.update("update dvd set state=1,borname=?,bordate=?,times=times+1 where id=?", list);
	}
	
	//归还
	public int returnDvd(int id) 
	{
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		return jdbc.update("update dvd set state=0,borname='',bordate='     'where id=?", list);
		
	}
	
	//新增
	public int insert(String name,Double price,String publish) 
	{
		List<Object> list=new ArrayList<Object>();
		list.add(name);
		list.add(price);
		list.add(publish);
		return jdbc.update("insert into dvd values(null,?,?,?,0,'','',0)", list);
	}
	
	//删除
	public int Del(int id) 
	{
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		return jdbc.update("delete from dvd where id=?", list);
	}
}
