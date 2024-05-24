import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	public static void main(String[] args) throws Exception{
	double pi = 0;
	int addres=0;
	int flag1=0, flag2=0, flag3=0, flag4=0;
		@SuppressWarnings("resource")
		Socket s = new Socket("localhost", 6012);
		
		System.out.println("Connection established\n");
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter your choice:\n1. Calculate Pi\n2. Add\n3. Sort\n4. Matrix Multiplication\n5. Exit\n6. Show Result\n");
		String x = sc.nextLine();
		
		dos.writeUTF(x);
		dos.flush();
		
		while(true)
		{
				if(x.equals("1"))
				{
					//int n;
					//System.out.println("Enter the number of terms to display in the calculation of the Pi value\n");
					 //n = sc.nextInt();
					//dos.writeInt(n);
					//dos.flush();
					
					// Reading the result from the server
					pi = dis.readDouble();
					flag1=1;
					System.out.println("Client is waiting for the result...\n");
					System.out.println("Pi = "+pi);
					break;
				}
			
				if(x.equals("2"))
				{
					System.out.println("Enter two numbers\n");
					int a,b;
					a = sc.nextInt();
					b = sc.nextInt();
					
					//Sending the two numbers to the server for calculation
					dos.writeInt(a);
					dos.flush();
					dos.writeInt(b);
					dos.flush();
					
					//Reading the sum from the server
					addres = dis.readInt();
					flag2=1;
					System.out.println("Client is waiting for the result...\n");
					System.out.println("Result = "+addres);
					
					break;
				}
				if(x.equals("3"))
				{
					int n;
					System.out.println("Enter the size of the array to be sorted\n");
					n = sc.nextInt();
					//dos.writeInt(n);
					//dos.flush();
					
					int a[] = new int[n];
					System.out.println("Enter the array elements to be sorted\n");
					for(int i=0; i<n; i++)
						a[i] = sc.nextInt();
					
					
					OutputStream os = s.getOutputStream();  
					  ObjectOutputStream oos = new ObjectOutputStream(os);  
					  oos.writeObject(a);
					  
					  // Reading the sorted array from the server
					  InputStream is = s.getInputStream();  
					  ObjectInputStream ois = new ObjectInputStream(is);  
					  int[] res = (int[])ois.readObject();
					  
					  System.out.println("Client is waiting for the result...\n");
					  System.out.println("Sorted array is: \n");
					  for(int i=0; i < res.length; i++)
						  System.out.println(res[i] + " ");
					
					
					flag3=1;
					break;
				}
				if(x.equals("4"))
				{
					int m,n,p,q;  // For matrix dimensions
					System.out.println("Enter the dimensions of the first matrix\n");
					m = sc.nextInt();
					n = sc.nextInt();
					
					
					System.out.println("Enter the dimensions of the second matrix\n");
					p = sc.nextInt();
					q = sc.nextInt();
					
					int[][] a = new int[m][n];
					int[][] b = new int[p][q];
					
					System.out.println("Enter the elements of the first matrix\n");
					for(int i=0; i<m; i++)
						for(int j=0; j<n; j++)
							a[i][j] = sc.nextInt();
					
					
					
					
					System.out.println("Enter the elements of the second matrix\n");
					for(int i=0; i<p; i++)
						for(int j=0; j<q; j++)
							b[i][j] = sc.nextInt();
					
					// Sending the dimensions
					dos.writeInt(m);
					dos.flush();
					dos.writeInt(n);
					dos.flush();
					dos.writeInt(p);
					dos.flush();
					dos.writeInt(q);
					dos.flush();
					
					// Sending the two matrices	
					OutputStream os = s.getOutputStream();  
					  ObjectOutputStream oos = new ObjectOutputStream(os);  
					  oos.writeObject(a);
					  oos.writeObject(b);
					  
					  int rows = dis.readInt();
					  int cols = dis.readInt();
					  
					  
					  // Reading the resultant matrix from the server
					  InputStream is = s.getInputStream();  
					  ObjectInputStream ois = new ObjectInputStream(is);  
					  int[][] res = (int[][])ois.readObject();
					  System.out.println("Client is waiting for the result...\n");
					  System.out.println("\nResultant Matrix is:\n");
					  
					  for(int i=0; i<rows; i++)
					  {
						  for(int j=0; j<cols; j++)
						  {
							  System.out.print(res[i][j]+" ");
						  }
						  System.out.print("\n");
					  }
					  flag4=1;
					  break;
					
				}
				
				if(x.equals("5"))
				{
					System.out.println("Connection closed\n");
					System.exit(0);
				}
				//s.close();
				if(x.equals("6") || x.equals("1") || x.equals("2") || x.equals("3") ||x.equals("4"))
				{
					if(flag1 == 1)
					{
						System.out.println("Pi = "+pi);
					}
					if(flag2 == 1)
					{
						System.out.println("Result = "+addres);
					}
					if(flag3 == 1)
					{
						//System.out.println(" = "+res);
					}
					if(flag4 == 1)
					{
						//System.out.println("Pi = "+res);
					}
				
				}
				
		}
		
		
		//System.out.println("Thank you!\n");
		

	}

}

