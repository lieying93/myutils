package com.sort;

public class SortSql {
      public static String   getQuery(String content){
    	  //�����е����ķ����滻ΪӢ�ķ���
   
    	  content = content.replace("��", "(");
    	  content = content.replace("��", ")");
    	  String str[] = content.split(",");
    	  //����ģ������
    	  StringBuffer sb = new StringBuffer();
    	  StringBuffer sbSort=new StringBuffer();
    	  sbSort.append(" order by instr('");
    	  sb.append("select * from awe_do_library where dono=''  and colheader in (");
    	  for(String field:str){
    		  sb.append("'");
    		  sb.append(field);
    		  sb.append("'");
    		  sb.append(",");
    		  //��ӵ�order by �Ӿ����
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
