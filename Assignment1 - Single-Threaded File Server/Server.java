import java.net.*;
import java.io.*;
public class Server {

	public static void main(String[] args) throws Exception{
		
		//Socket s;
		//int clientNo;
		
		System.out.println("Server is starting\n");
		
		ServerSocket s = new ServerSocket(6008);
		Socket sr = s.accept();
		
		System.out.println("Server is listening\n");
		
		DataInputStream dis = new DataInputStream(sr.getInputStream());
		DataOutputStream dos = new DataOutputStream(sr.getOutputStream());


		dos.writeUTF("Connection established\n");
		dos.flush();

		String x = null, numberld = null, neww = null;
		
		//Use the next line for upload
		String fileUpload = "/home/asfiyamisba/Desktop/Assignment1/ServerFiles/upload.txt";
		
		
		//Use the next line for download
		String fileDownload = "/home/asfiyamisba/Desktop/Assignment1/ServerFiles/download.txt";
		
		//Use the next line for delete
		String fileDelete = "/home/asfiyamisba/Desktop/Assignment1/ServerFiles/fileToDelete.txt";
		
		//Use the next line for rename
		 // numberld = "C:\\Users\\asfiy\\eclipse-workspace\\Assignment1\\src\\Server Files\\Test.txt";
		String fileRename = "/home/asfiyamisba/Desktop/Assignment1/ServerFiles/Test.txt";
		
		// byte array to read the file contents
		byte []b = new byte[1024];

		while(true)
		{
			// Menu for the user to choose the file operations
			dos.writeUTF("\nEnter the file operation to be performed:\n1. Upload\n2. Download\n3. Delete\n4. Rename\n5. Exit\n");
			dos.flush();

			// Read choice
			x = dis.readUTF();

			if(x.equals("1"))
			{
				// UPLOAD

				// 04. Receive file
				InputStream is = sr.getInputStream();
				//String fname = "C:\\Users\\asfiy\\eclipse-workspace\\Assignment1\\src\\Server Files\\upload.txt";
				// Read and write file at specified location
				is.read(b, 0, b.length);
	//FileOutputStream fos = new FileOutputStream("/home/asfiyamisba/Desktop/Assignment1/ServerFiles/upload.txt");
	FileOutputStream fos = new FileOutputStream(fileUpload);
				fos.write(b, 0, b.length);
				fos.close();
				
				//dos.writeUTF("File successfully uploaded\n");
				//dos.flush();
			}

			if(x.equals("2"))
			{
				// DOWNLOAD

				// Get file
				FileInputStream fis = new FileInputStream(fileDownload);
				fis.read(b, 0, b.length);

				// Send file
				OutputStream os = sr.getOutputStream();
				os.write(b, 0, b.length);
				fis.close();
				
				//dos.writeUTF("File successfully downloaded\n");
				//dos.flush();
				
			}

			if(x.equals("3"))
			{
				// DELETE file

				File file2 = new File(fileDelete);
				file2.delete();

				//Send text message
				dos.writeUTF("File successfully deleted\n");
				dos.flush();
			}

			if(x.equals("4"))
			{
				// RENAME

				// Send text message
				dos.writeUTF("\nEnter a new file name:");
				dos.flush();

				// Receive new file name
				neww = dis.readUTF();

				// Read old file
				FileInputStream fis2 = new FileInputStream(fileRename);
				fis2.read(b, 0, b.length);

				// Write to new file
				//neww = "C:\\Users\\asfiy\\eclipse-workspace\\Assignment1\\src\\Server Files\\"+neww;
				neww = "/home/asfiyamisba/Desktop/Assignment1/ServerFiles/"+neww;
				FileOutputStream fos2 = new FileOutputStream(neww);
				fos2.write(b, 0, b.length);
				fis2.close();
				fos2.close();

				// Delete old file
				File file1 = new File(fileRename);
				file1.delete();

				fileRename=neww;

				// Send text message
				dos.writeUTF("File successfully renamed\n");
				dos.flush();
			}
			if(x.equals("5"))
			{
				// EXIT
				// Send text message
				dos.writeUTF("Connection closed\n");
				dos.flush();
				System.exit(0);
				break;
			}
		}
		s.close();
	}
}

