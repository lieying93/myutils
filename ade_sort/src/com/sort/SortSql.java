package com.sort;

public class SortSql {
      public static String   getQuery(String content){
    	  //把所有的中文符号替换为英文符号
   
    	  content = content.replace("（", "(");
    	  content = content.replace("）", ")");
    	  String str[] = content.split(",");
    	  //定义模板样子
    	  StringBuffer sb = new StringBuffer();
    	  StringBuffer sbSort=new StringBuffer();
    	  sbSort.append(" order by instr('");
    	  sb.append("select * from awe_do_library where dono=''  and colheader in (");
    	  for(String field:str){
    		  sb.append("'");
    		  sb.append(field);
    		  sb.append("'");
    		  sb.append(",");
    		  //添加到order by 子句后面
    		  sbSort.append(field);
    		  sbSort.append(",");
    		  
    	  }
    	  sb.deleteCharAt(sb.length() - 1);
    	  sb.append(") ");
    	  sbSort.deleteCharAt(sbSort.length() - 1);
    	  sbSort.append("',colheader);");
    	  return sb.toString()+sbSort.toString();
      }
     
}
