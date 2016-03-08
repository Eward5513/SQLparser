package test;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import sqlparser.SqlParser;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectItem;

public class Test {

	static String[] sql=new String[]{
			"select cola,colb from table_a,table_b where a>=b and c<=d group by e,f having e<=123 or f>='456'",
			"create table tabl_a (cola int auto_increment, colb varchar (20),primary key (cola), foreign key (colb) references table_b(cola))",
			"create table table_a as select a,b from c,d",
			"delete from table_a where a = 1 and b = 1",
			"drop index index_a cascade",
			"drop table table_a",
			"insert into table_a(col1,col2) value(123,'456')",
			"insert into table_a select cola,colb from table_b",
			"insert into table_a value(123,'abc')",
			"update table_a set cola = '123' where colb = 456",
			"create index ind ON tablea (col1,col2)",
			"create view view_a as select cola,colb from table_a,table_b"
			};
	public static void main(String[] args) {
		
		SqlParser parser=new SqlParser();
		for(int i=0;i<12;++i)
		{
			System.out.println(sql[i]);
			parser.parser(sql[i]);
			System.out.println();
		}
	}

}
