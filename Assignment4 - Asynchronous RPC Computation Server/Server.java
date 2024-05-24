import java.io.*;
import java.net.*;
import java.util.*;
// Assignment 4
public class Server {
	
	// Method to calculate the value of pi
public static double calculate_pi()
{
	double PI = 3;
	double sign = 1;
	double n = 2;
	
	for (int i = 0; i <= 1000000; i++) {
        PI = PI + (sign * (4 / ((n) * (n + 1)
                                * (n + 2))));
 
        sign = sign * (-1);
        n += 2;
    }
	return PI;
}

// Method to add two numbers
public static int add(int a, int b)
{
	return a+b;
}

// Method to swap
public static void swap(int[] a, int i, int j)
{
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

// Method to partition the array
public static int partition(int[] arr, int low, int high)
{

    int pivot = arr[high];
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) 
    {
        if (arr[j] < pivot) 
        {
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i + 1, high);
    return (i + 1);
}

// Method to sort the array
static void quickSort(int[] arr, int low, int high)
{
    if (low < high) 
    {  
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

// Method to multiply two matrices
public static int[][] matrix_mult(int m, int n, int p, int q, int a[][], int b[][])
{
			int [][] res = new int[m][q];
	  
	  for (int i = 0; i < m; i++) {
          for (int j = 0; j < q; j++) {
              res[i][j] = 0;
              for (int k = 0; k < p; k++)
                  res[i][j] += a[i][k] * b[k][j];
              //System.out.print(res[i][j] + " ");
          }
          //System.out.println("");
      }
	  return res;
}
	public static void main(String[] args) throws Exception{
		System.out.println("Server is listening, waiting for client\n");
		@SuppressWarnings({ "unused", "resource" })
		Scanner sc = new Scanner(System.in);
		@SuppressWarnings("resource")
		ServerSocket s = new ServerSocket(6012);
		
		Socket sr = s.accept();
		DataInputStream dis = new DataInputStream(sr.getInputStream());
		DataOutputStream dos = new DataOutputStream(sr.getOutputStream());
		//System.out.println("Server is listening\n");
		
		String x = dis.readUTF();
		//System.out.println(x);
		
		while(true)
		{
				if(x.equals("1"))
				{
					//double sum=0;
					//int n;
					//n = dis.readInt();
					double PI = calculate_pi();
					
					// Writing the result back on the client
					dos.writeDouble(PI);
					break;
				}						
				if(x.equals("2"))
				{
					//System.out.println("Enter two numbers:\n");
					int a,b;
					a = dis.readInt();
					b = dis.readInt();
					int res = add(a,b);
					
					// Writing the result back on the client
					dos.writeInt(res);
					break;
				}
				if(x.equals("3"))
				{
					//int n = 0;
					//n = dis.readInt();
					
					//int a[] = new int[n];
					
					 InputStream is = sr.getInputStream();  
					  ObjectInputStream ois = new ObjectInputStream(is);  
					  int[] a = (int[])ois.readObject();
					  int n = a.length;
					  //Arrays.sort(a);
					  
					  quickSort(a, 0, n - 1);    // Method call to sort the array
					  
					// Writing the result back on the client
					  OutputStream os = sr.getOutputStream();  
					  ObjectOutputStream oos = new ObjectOutputStream(os);  
					  oos.writeObject(a);
					  
					  break;
					
				}
				if(x.equals("4"))
				{
					
					// Dimensions of the two matrices
					
					int m,n,p,q;
					m = dis.readInt();
					n = dis.readInt();
					p = dis.readInt();
					q = dis.readInt();
					
					 InputStream is = sr.getInputStream();  
					  ObjectInputStream ois = new ObjectInputStream(is);  
					  int[][] a = (int[][])ois.readObject();
					  
					  int [][] b = (int[][])ois.readObject();
					  
					  int [][] res = new int[m][q];
					  
					  res = matrix_mult(m,n,p,q,a,b);  // Method call to multiply the two matrices
					  
					  
					  //Send the dimensions of the resultant matrix to the client
					  dos.writeInt(m);
					  dos.writeInt(q);
					  
					  // Sending the resultant matrix to the client
					  OutputStream os = sr.getOutputStream();  
					  ObjectOutputStream oos = new ObjectOutputStream(os);  
					  oos.writeObject(res); 
					  
					break;
				}
				
				if(x.equals("5"))
				{
					dos.writeUTF("Connection closed\n");
					dos.flush();
					break;
				}
				if(x.equals("6"))
				{
					dos.writeUTF("Waiting for the solution\n");
					dos.flush();
				}
				
				
		}

	}
	

}
