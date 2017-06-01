import java.io.*;
import java.util.*;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minimumpossiblecost("-1@10@-1#10@2@10#-1@10@-1"));
	}
	public static int minimumpossiblecost(String input1)
	  {
	    String arr[]=input1.split("#");
	    int c=arr[0].split("@").length;
	    int r=arr.length;
	    int intarr[][]= new int[r][];
	    for(int i=0;i<r;i++)
	    {
	        intarr[i]= new int[c];
	    }
	    for(int i=0;i<arr.length;i++)
	    {
	        String row[]=arr[i].split("@");
	        for(int j=0;j<row.length;j++)
	        {
	            intarr[i][j]=Integer.parseInt(row[j]);
	            System.out.println(intarr[i][j]);
	        }
	    }
	    
	    int temp=0;
	    int ans=0;
	    for(int i=0;i<r;i++)
	    {
	        for(int j=0;j<r;j++) 
	        {

	            temp=0;
	           if(intarr[i][j]==-1)
	           {
		            if(i==0 && j==0)
		            {
		                temp=minDiff(intarr[i][j+1],intarr[i+1][j],intarr[i+1][j+1]);
		                if(temp==intarr[i][j+1])
		                    intarr[i][j+1]=-1;
		                else if(temp==intarr[i+1][j])
		                    intarr[i+1][j]=-1;
		                else if(temp==intarr[i+1][j+1])
		                    intarr[i+1][j+1]=-1;
		                ans=ans+temp;
	
	
		            }
		            else if(i==0 && j>0 && j<c-1)
		            {
		                temp=minDiff(intarr[i][j-1],intarr[i][j+1],intarr[i+1][j],intarr[i+1][j-1],intarr[i+1][j+1]);
		                if(temp==intarr[i][j-1])
		                    intarr[i][j-1]=-1;
		                else if(temp==intarr[i][j+1])
		                    intarr[i][j+1]=-1;
		                else if(temp==intarr[i+1][j])
		                    intarr[i+1][j]=-1;
		                else if(temp==intarr[i+1][j-1])
		                    intarr[i+1][j-1]=-1;
		                else if(temp==intarr[i+1][j])
		                    intarr[i+1][j+1]=-1;
		                ans=ans+temp;
	
		            }
		            else if(i==0 && j==c-1)
		            {
		                temp=minDiff(intarr[i+1][j],intarr[i][j-1],intarr[i+1][j-1]);
		                if(temp==intarr[i+1][j])
		                    intarr[i+1][j]=-1;
		                else if(temp==intarr[i][j-1])
		                    intarr[i][j-1]=-1;
		                else if(temp==intarr[i+1][j-1])
		                    intarr[i+1][j-1]=-1;
		                ans=ans+temp;
	
		            }
		            else if(i>0 && i<r-1 && j==0)
		            {
		                temp=minDiff(intarr[i+1][j],intarr[i-1][j],intarr[i][j+1],intarr[i-1][j+1],intarr[i+1][j+1]);
		                if(temp==intarr[i+1][j])
		                    intarr[i+1][j]=-1;
		                else if(temp==intarr[i-1][j])
		                    intarr[i-1][j]=-1;
		                else if(temp==intarr[i][j+1])
		                    intarr[i][j+1]=-1;
		                else if(temp==intarr[i-1][j+1])
		                    intarr[i-1][j+1]=-1;
		                else if(temp==intarr[i+1][j+1])
		                    intarr[i+1][j+1]=-1;
		                ans=ans+temp;
	
		            }
		            else if(i>0 && i<r-1 && j==c-1)
		            {
	
		            temp=minDiff(intarr[i][j],intarr[i+1][j],intarr[i][j-1],intarr[i-1][j],intarr[i-1][j-1],intarr[i+1][j-1]);
		                if(temp==intarr[i][j])
		                    intarr[i][j]=-1;
		                else if(temp==intarr[i+1][j])
		                    intarr[i+1][j]=-1;
		                else if(temp==intarr[i][j-1])
		                    intarr[i][j-1]=-1;
		                else if(temp==intarr[i-1][j])
		                    intarr[i-1][j]=-1;
		                else if(temp==intarr[i-1][j-1])
		                    intarr[i-1][j-1]=-1;
		                else if(temp==intarr[i+1][j-1])
		                    intarr[i+1][j-1]=-1;
		                ans=ans+temp;
	
		            }
		            else if(i==r-1 && j==0)
		            {
		                temp=minDiff(intarr[i-1][j],intarr[i][j+1],intarr[i-1][j+1]);
		                if(temp==intarr[i-1][j])
		                    intarr[i-1][j]=-1;
		                else if(temp==intarr[i][j+1])
		                    intarr[i][j+1]=-1;
		                else if(temp==intarr[i-1][j+1])
		                    intarr[i-1][j+1]=-1;
		                ans=ans+temp;
		            }
		            else if(i==r-1 && j>0 && j<c-1)
		            {
		                temp=minDiff(intarr[i-1][j],intarr[i][j-1],intarr[i][j+1],intarr[i-1][j-1],intarr[i-1][j+1]);
		                if(temp==intarr[i-1][j])
		                    intarr[i-1][j]=-1;
		                else if(temp==intarr[i][j-1])
		                    intarr[i][j-1]=-1;
		                else if(temp==intarr[i][j+1])
		                    intarr[i][j+1]=-1;
		                else if(temp==intarr[i-1][j-1])
		                    intarr[i-1][j-1]=-1;
		                else if(temp==intarr[i-1][j+1])
		                    intarr[i-1][j+1]=-1;
		                ans=ans+temp;
		            }
		            else if(i==r-1 && j==c-1)
		            {
		                temp=minDiff(intarr[i][j-1],intarr[i-1][j],intarr[i-1][j-1]);
		                if(temp==intarr[i][j-1])
		                    intarr[i][j-1]=-1;
		                else if(temp==intarr[i-1][j])
		                    intarr[i-1][j]=-1;
		                else if(temp==intarr[i-1][j-1])
		                    intarr[i-1][j-1]=-1;
		                ans=ans+temp;
	
		            }
		            else
		            {
		                temp=minDiff(intarr[i+1][j],intarr[i-1][j],intarr[i][j+1],intarr[i][j-1],intarr[i-1][j+1],intarr[i-1][j-1],intarr[i+1][j+1],intarr[i+1][j-1]);
		                if(temp==intarr[i+1][j])
		                    intarr[i+1][j]=-1;
		                else if(temp==intarr[i-1][j])
		                    intarr[i-1][j]=-1;
		                else if(temp==intarr[i][j+1])
		                    intarr[i][j+1]=-1;
		                else if(temp==intarr[i][j-1])
		                    intarr[i][j-1]=-1;
		                else if(temp==intarr[i-1][j+1])
		                    intarr[i-1][j+1]=-1;
		                else if(temp==intarr[i-1][j-1])
		                    intarr[i-1][j-1]=-1;
		                else if(temp==intarr[i+1][j+1])
		                    intarr[i+1][j+1]=-1;
		                else if(temp==intarr[i+1][j-1])
		                    intarr[i+1][j-1]=-1;
		                ans=ans+temp;
	
		            }
	         }
	        }

	    }
	    return ans;
	  }
	  static int minDiff(int ...x)
	  {
	    int flag=0,min=10000;
	    for(int i=0;i<x.length;i++)
	    {
	        if(x[i]==-1)
	        {
	            flag=1;
	            break;
	        }

	    }
	    if(flag==1)
	    return 0;
	    else
	    {
	        for(int i=0;i<x.length;i++)
	        {
	            if(x[i]<min)
	            {
	                min=x[i];
	            }

	        }   
	    }
	    return min;
	  }
}
