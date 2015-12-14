package com.doadway.framework.util;

public class BacktrackingUtil {
	static boolean isLegal(int a[],int c[],int n,int y,int k)
	{
	     int i;
	     int sum=0;
	     for(i=0;i<n;i++)
		 {
	          if(c[i]==1)
			  {
	                sum=sum+a[i];
	          }
	     }

	     if((sum==y)&&(k<=n-1))
		 {
	        return true;
	     }
		 else
		 {
	        return false;
	     }
	}

	static boolean isPart(int a[],int c[],int n,int y,int k)
	{
	     int i;
	     int sum=0;
	     for(i=0;i<n;i++)
		 {
	          if(c[i]==1)
			  {
	                sum=sum+a[i];
	          }
	     }

	     if((sum<y)&&(k<n-1))
		 {
	        return true;
	     }
		 else
		 {
	        return false;
	     }
	}

	static boolean partition(int A[],int c[],int n,int y)
	{
	    /*回溯迭代算法*/
	    int k,j;
	    boolean flag=true;
	    k=0;
	    while(k>=0)
		{
	        while(c[k]<=0)
			{
	            c[k]=c[k]+1;
	            if(isLegal(A,c,n,y,k))
				{
	                flag=true;
	                /*储存或输出解向量*/
	                System.out.println();
	                for(j=0;j<n;j++)
					{
	                    if(c[j]<0)
						{
							c[j]++;
						}
	   					System.out.print(c[j]+" ");

					}


				}
	            else if(isPart(A,c,n,y,k))
				{
	                k=k+1;

				}
			}

	        c[k]=-1;
	        k=k-1;
	    }
	    return flag;
	}
	public static void main(String args[]){
	    int[] A,c;
	    A=new int[]{10,20,30,40,50,60};
	    boolean flag=false;
	    c=new int[]{-1,-1,-1,-1,-1,-1};
		for(int i=0;i<6;i++) System.out.print(A[i]+" ");
	    flag=partition(A,c,6,60);
	    if(flag)
		{
	        
			System.out.println("Find the answer!");
	    }
	    else
		{
	        System.out.println("can not find the answer!");
	    }

	    
	}
}
