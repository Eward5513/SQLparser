/**
 * 
 */
package sqlparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;

/**
 * @author zteng
 *
 */
public class SqlParser {

	/**
	 * @param args
	 */
	CCJSqlParser sqlParser;
	public void parser(String sql)
	{
		System.out.println("SqlParser: void parser(String sql)");
		sqlParser=new CCJSqlParser(new StringReader(sql));
		try {
			Statement statement=sqlParser.Statement();
			if(statement instanceof Select)
			{
				parser_select((Select)statement);
			}
			else if(statement instanceof Delete)
			{
				parser_delete((Delete)statement);
			}
			else if(statement instanceof Drop)
			{
				parser_drop((Drop)statement);
			}
			else if(statement instanceof Insert)
			{
				parser_insert((Insert)statement);
			}
			else if(statement instanceof Update)
			{
				parser_update((Update)statement);
			}
			else if(statement instanceof Truncate)
			{
				parser_truncate((Truncate)statement);
			}
			else if(statement instanceof CreateTable)
			{
				parser_createTable((CreateTable)statement);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
	private void parser_select(Select select)
	{
		System.out.println("SqlParser: void parser_select(Select)");
		SelectBody selectBody=select.getSelectBody();
		System.out.println("selectBody: "+selectBody);
		List<WithItem> withItemsList=select.getWithItemsList();
		if(withItemsList != null)
		{
			Iterator<WithItem> it=select.getWithItemsList().iterator();
			System.out.println("withItemsList:");
			while(it.hasNext())
			{
				System.out.println("*"+it.next());
			}
			System.out.println();		
		}
		if(selectBody instanceof PlainSelect)
		{
			PlainSelect plainSelect=(PlainSelect)selectBody;
			System.out.println("PalinSelect");
			//selectItem
			List<SelectItem> selectItem=plainSelect.getSelectItems();
			if(selectItem != null)
			{
				Iterator<SelectItem> it=selectItem.iterator();
				System.out.println("selectItems:");
				while(it.hasNext())
				{
					System.out.println("*"+it.next());
				}
			}
			//intoTables
			List<Table> intoTables=plainSelect.getIntoTables();
			if(intoTables != null)
			{
				Iterator<Table> it=intoTables.iterator();
				System.out.println("intoTables:");
				while(it.hasNext())
				{
					System.out.println("*"+it.next());
				}
			}		
			//FromItem
			FromItem fromItem=plainSelect.getFromItem();
			System.out.println("fromItem:"+fromItem);
			//joins
			List<Join> joins=plainSelect.getJoins();
			if(joins != null)
			{
				Iterator<Join> it=joins.iterator();
				System.out.println("joins");
				while(it.hasNext())
				{
					System.out.println("*"+it.next());
				}
			}
			//where
			Expression where=plainSelect.getWhere();
			System.out.println("where:"+where);
			//groupByColumnReferences
			List<Expression> groupByColumnReferences=plainSelect.getGroupByColumnReferences();
			if(groupByColumnReferences != null)
			{
				System.out.println("groupByColumnReferences:");
				Iterator<Expression> it=groupByColumnReferences.iterator();
				while(it.hasNext())
				{
					System.out.println("*"+it.next());
				}
			}
			//orderByElements
			List<OrderByElement> orderByElements=plainSelect.getOrderByElements();
			if(orderByElements != null)
			{
				Iterator<OrderByElement> it=orderByElements.iterator();
				while(it.hasNext())
				{
					System.out.println("*"+it.next());
				}
			}
			//having
			Expression having = plainSelect.getHaving();
			System.out.println("having:"+having);
		}
		//else if(selectBody instanceof )
	}
	private void parser_delete(Delete delete)
	{
		System.out.println("SqlParser: void parser_delete(Delete)");
		//to do something
	}
	private void parser_drop(Drop drop)
	{
		System.out.println("SqlParser: void parser_drop(Drop)");
		//to do something
	}
	private void parser_insert(Insert insert)
	{
		System.out.println("SqlParser: void parser_insert(Insert)");
//		to do something
	}
	private void parser_update(Update update)
	{
		System.out.println("SqlParser: void parser_update(Update)");
		//to do something
	}
	private void parser_truncate(Truncate truncate)
	{
		System.out.println("SqlParser: void parser_truncate(Truncate)");
		//to do something
	}
	private void parser_createTable(CreateTable createTable)
	{
		System.out.println("SqlParser: void parser_createTable(CreateTable)");
		//tableOptionStrings
		System.out.println("tableOptionsStrings:");
		List<?> tableOptionStrings=createTable.getTableOptionsStrings();
		if(tableOptionStrings != null)
			{
				Iterator<?> it=tableOptionStrings.iterator();
				while(it.hasNext())
				{
					System.out.println("*"+it.next());
				}
			}
		//ColumnDefinitions
		List<ColumnDefinition> columnDefinitions=createTable.getColumnDefinitions();
		if(columnDefinitions != null)
		{
			System.out.println("columnDefinitions:");
			Iterator<ColumnDefinition> it=columnDefinitions.iterator();
			while(it.hasNext())
			{
				ColumnDefinition columnDifinition=it.next();
				StringBuilder show=new StringBuilder();
				show.append("*columnName:").append(columnDifinition.getColumnName()).append(" ");
				//colDataType
				ColDataType colDataType=columnDifinition.getColDataType();
				//dataType
				show.append("dataType:").append(colDataType.getDataType()).append(" ");
				//argumentsStringList
				List<String> argumentsStringList=colDataType.getArgumentsStringList();
				if(argumentsStringList != null)
				{
					Iterator<String> iter=argumentsStringList.iterator();
					show.append("argumentsStirngList: ");
					while(iter.hasNext())
					{
						show.append(iter.next()).append(" ");
					}
				}
				//stringSet
				show.append("CharacterSet:").append(colDataType.getCharacterSet());
				List<String> columnSpecStrings=columnDifinition.getColumnSpecStrings();
				if(columnSpecStrings != null)
				{
					show.append(" columnSpecString: ");
					Iterator<String> ite=columnSpecStrings.iterator();
					while(ite.hasNext())
					{
						show.append(ite.next()).append(" ");
					}
				}
				System.out.println(show);
			}
		}
		//table
		Table table=createTable.getTable();
		System.out.println("table:"+table);
		//select
		Select select=createTable.getSelect();
		System.out.println("Select:"+select);
	}
	public static void main(String[] args) {
		System.out.println("yes");
		System.out.println("no");
		System.out.println("yes no");
		SqlParser test=new SqlParser();
		String sql;
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		try {
			while(true)
			{
				sql=in.readLine();
				test.parser(sql);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
