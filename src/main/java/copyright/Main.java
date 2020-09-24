package copyright;

public class Main {
	public static void main(String[] args) throws Exception
	{
		Search s=new Search();
		s.getWmic();
		s.getRegedit();
		s.getCPFx86();
		s.getCPF();
		s.getOthers();
		
		InputSqlite in=new InputSqlite();
		in.clear();
		
		String sql[]=new String[s.getD().size()];
		
		
		for(int i=0;i<s.getD().size();i++) 
		{
			sql[i]="insert into result values(datetime('now','localtime'),'"+s.getD().get(i)[0].replace(" ","")+"','"+s.getD().get(i)[1].replace(" ","")+"')";
		}
		in.insert(sql);
		
		Thread.sleep(1000);
		Result r=new Result();
		r.searchResult();
		r.aresult();
		
	}
}
