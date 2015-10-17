package test;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectItem;

public class Test {

	public static void main(String[] args) {
		CCJSqlParserManager parserManager=new CCJSqlParserManager();
		String sql="select time,date from tableA,tableB";
		try {
			Select select=(Select) parserManager.parse(new StringReader(sql));
			System.out.println(select.getSelectBody());
			SelectBody selectBody=select.getSelectBody();
			if(selectBody instanceof PlainSelect)
				{
					System.out.println("PlainSelect");
					PlainSelect plainSelect = (PlainSelect)selectBody;
					List<SelectItem> items=plainSelect.getSelectItems();
					Iterator it=items.iterator();
					while(it.hasNext())
					{
						System.out.println((SelectItem)it.next());
					}
					
				}
			//else if(selectBody instanceof )
			
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
