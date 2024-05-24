import java.net.*;
import java.io.*;
import java.util.*;
public class Client {

	public static void main(String[] args) throws Exception {
		// Request connection to server
				Socket s = new Socket("localhost", 6008);

				// Create text output and input streams
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());

				String messagein, n = "Test.txt", x, neww = null;
				
				//Use the next line for Upload
				String fileUpload = "/home/asfiyamisba/Desktop/Assignment1/ClientFiles/upload.txt";
				
				//Use the next line for Download
				String fileDownload = "/home/asfiyamisba/Desktop/Assignment1/ClientFiles/download.txt";
				
				//Use the next line for Rename
				// neww = "C:\\Users\\asfiy\\eclipse-workspace\\Assignment1\\src\\Server Files\\Test.txt";
				String fileRename = "/home/asfiyamisba/Desktop/Assignment1/ServerFiles/Test.txt";
				
				// Receive text message
				messagein = dis.readUTF();
				System.out.println(messagein);

				Scanner k = new Scanner(System.in);
				byte b[] = new byte[1024];

				while(true)
				{
					// Receive text message
					messagein = dis.readUTF();

					System.out.println(messagein);
					x = k.nextLine();

					// Send choice
					dos.writeUTF(x);
					dos.flush();

					if(x.equals("1"))
					{
						// UPLOAD
						
						// Get file
	//FileInputStream fis = new FileInputStream("/home/asfiyamisba/Desktop/Assignment1/ClientFiles/upload.txt");
	FileInputStream fis = new FileInputStream(fileUpload);
						fis.read(b, 0, b.length);

						// Send file
						OutputStream os = s.getOutputStream();
						os.write(b, 0, b.length);
						fis.close();
						System.out.println("File successfully uploaded");
					}
					if(x.equals("2"))
					{
						// DOWNLOAD
						//working
						// Receive file
						InputStream is = s.getInputStream();

						// Read and write file at specified location
						is.read(b, 0, b.length);
						FileOutputStream fos = new FileOutputStream(fileDownload);
						fos.write(b, 0, b.length);
						fos.close();
						System.out.println("File successfully downloaded");
					}
					//C:\Users\asfiy\eclipse-workspace\DS_Project1\src\Client Files

					if(x.equals("3"))
					{
						//working
						// DELETE file

						// Receive text message
						messagein = dis.readUTF();

						System.out.println(messagein);
					}

					if(x.equals("4"))
					{
						// RENAME
						//working
						// Receive text message
						messagein = dis.readUTF();

						System.out.println(messagein);
						n = k.nextLine();
						//neww="C:\\Users\\asfiy\\eclipse-workspace\\Assignment1\\src\\Server Files\\"+n;
						neww ="/home/asfiyamisba/Desktop/Assignment1/ServerFiles/"+n;

						// Send new file name
						dos.writeUTF(n);
						dos.flush();

						// Receive text message
						messagein = dis.readUTF();

						System.out.println(messagein);
					}

					if(x.equals("0"))
					{
						// EXIT
						//working
						//Receive text message
						messagein = dis.readUTF();
						System.out.println(messagein);
						System.out.println("Connection closed\n");
						System.exit(0);
						break;
					}
				}
				k.close();
				s.close();
	}
}

