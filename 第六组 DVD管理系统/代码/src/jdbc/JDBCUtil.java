
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description--jdbc工具类：增删改，查的封装
 * @author 第六组
 * @version
 * @Date 2020年7月3日下午13:41:19
 *
 */
public class JDBCUtil 
{
	//增删改的封装：参数sql，列表。返回值int，影响行数
	public int update(String sql, List<Object> list) 
	{
		try 
		{
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2、获取连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dvd", "root", "123456");
			// 3、预执行
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 说明有？号
			if (list != null) 
			{
				for (int i = 0; i < list.size(); i++) 
				{
					ps.setObject(i + 1, list.get(i));
				}
			}
			
			// 4、执行
			int row = ps.executeUpdate();
			// 5、释放
			ps.close();
			conn.close();

			return row;
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block 出现错误，失败
			e.printStackTrace();
			return 0;
		}

	}
	//查的封装：参数sql，列表。返回值：List<List<Object>>二维数组
	public List<List<Object>> query(String sql, List<Object> list) 
	{
		try 
		{
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2、获取连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dvd", "root", "123456");
			// 3、预执行
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// 说明有？号
			if (list != null) 
			{
				for (int i = 0; i < list.size(); i++) 
				{
					ps.setObject(i + 1, list.get(i));
				}
			}
			
			// 4、执行
			ResultSet rs = ps.executeQuery();
			
			//定义二维数组：一张表格 
			//int [][] a;list中嵌套一个list相当于二维数组
			List<List<Object>> table = new ArrayList<List<Object>>();;//前一个List代表表，后一个List代表行
			while(rs.next()) 
			{
				//执行次数为行数
				//定义一维数组，一行
				List<Object> row=new ArrayList<Object>();
				//获取结果集中列的个数
				int columnCount = rs.getMetaData().getColumnCount();
				//将一行填充完成
				for(int i=1;i<=columnCount;i++) 
				{
					row.add(rs.getObject(i));//执行次数为列数
				}
				//将当前行放入表格
				table.add(row);
			}
			
			// 5、释放
			rs.close();
			ps.close();
			conn.close();

			return table;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}


