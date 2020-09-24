package copyright;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Result {
	private String res="";
	public void searchResult() 
	{
		try 
		{
			ArrayList<String> keyword=new ArrayList();
			ArrayList<String> result=new ArrayList();
			Class.forName("org.sqlite.JDBC");
			Connection connection=DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\db\\r.db");
			Statement statement=connection.createStatement();
			ResultSet r1=statement.executeQuery("select distinct softname from prohibit");
			while(r1.next()) 
			{
				keyword.add(r1.getString(1));
			}
			
			for(int i=0;i<keyword.size();i++) 
			{
				ResultSet resultset=statement.executeQuery("select address,softname from result where softname like '%"+keyword.get(i).replace(" ", "")+"%'");
				while(resultset.next()) 
				{
					result.add(resultset.getString(1));
					result.add(resultset.getString(2));
				}
			}
			
			if(result.size()==0) 
			{
				res="未找到违规程序";
			}
			else 
			{
				for(int i=0;i<result.size();i++) 
				{
					res+=result.get(i)+"\n";
				}
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	
		
	}
	
	public void aresult() 
	{
		JFrame jframe=new JFrame();
		jframe.setLayout(new BorderLayout());
		JLabel t1=new JLabel("扫描结果如下");
		jframe.add(t1,BorderLayout.NORTH);
		JTextArea t2=new JTextArea();
		t2.setLineWrap(true);
		JScrollPane jsp_t2=new JScrollPane(t2);
		t2.setText(res);
		jframe.add(jsp_t2,BorderLayout.CENTER);
		jframe.setTitle("查询结果");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(0, 0, 400, 300);
		jframe.setVisible(true);
	}
}
