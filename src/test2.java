import java.io.*;
import java.util.*;
public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minimumpossiblecost("-1@10@-1#10@2@10#-1@10@-1"));
	}

	public static int minimumpossiblecost(String input1)
	{
		String arr[]=input1.split("#");
		int intarr[][]= new int[arr.length][];
		for(int i=0;i<arr.length;i++)
	    {
	        String row[]=arr[i].split("@");
	        for(int j=0;j<row.length;j++)
	        {
	            intarr[i][j]=Integer.parseInt(row[j]);
	            System.out.println(intarr[i][j]);
	        }
	    }
		return 0;
	}

}
