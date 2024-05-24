import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread
{
	Socket s;
	int clientNo;

	ServerThread(Socket insocket, int counter)
	{
		s = insocket;
		clientNo = counter;
	}

	public void run()
	{
		try
		{
			System.out.println("Server is listening, waiting for client\n");
			// Create text output and input streams
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());

			// Send text message
			dos.writeUTF("\nConnection Established");
			dos.flush();

			String x = null, numberld = null, neww = null;
			
			//Use the next line for upload
		String fileUpload = "/home/asfiyamisba/Desktop/Assignment2/ServerFiles/upload.txt";
		
		
		//Use the next line for download
		String fileDownload = "/home/asfiyamisba/Desktop/Assignment2/ServerFiles/download.txt";
		
		//Use the next line for delete
		String fileDelete = "/home/asfiyamisba/Desktop/Assignment2/ServerFiles/fileToDelete.txt";
		
		//Use the next line for rename
		 // numberld = "C:\\Users\\asfiy\\eclipse-workspace\\Assignment1\\src\\Server Files\\Test.txt";
		String fileRename = "/home/asfiyamisba/Desktop/Assignment2/ServerFiles/Test.txt";
			
			
			// byte array to read the file contents
			byte []b = new byte[1024];

			while(true)
			{
				// Send text message
		dos.writeUTF("\nEnter the file operation to be performed:\n1. Upload\n2. Download\n3. Delete\n4. Rename\n5. Exit\n");
				dos.flush();

				// Receive choice
				x = dis.readUTF();

				if(x.equals("1"))
				{
					// UPLOAD

					//Receive file
					InputStream is = s.getInputStream();

					// Read and write file at specified location
					is.read(b, 0, b.length);
					//FileOutputStream fos = new FileOutputStream("C:\\Server Database\\Test.txt");
					FileOutputStream fos = new FileOutputStream(fileUpload);
					fos.write(b, 0, b.length);
					fos.close();
				}

				if(x.equals("2"))
				{
					// DOWNLOAD

					// Get file
					FileInputStream fis = new FileInputStream(fileDownload);
					fis.read(b, 0, b.length);

					// Send file
					OutputStream os = s.getOutputStream();
					os.write(b, 0, b.length);
					fis.close();
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
					dos.writeUTF("Enter a new file name:\n");
					dos.flush();

					// Receive new file name
					neww = dis.readUTF();

					// Read old file
					FileInputStream fis2 = new FileInputStream(fileRename);
					fis2.read(b, 0, b.length);

					// Write to new file
					//neww = "C:\\Server Database\\"+neww;
					neww = "/home/asfiyamisba/Desktop/Assignment2/ServerFiles/"+neww;
					FileOutputStream fos2 = new FileOutputStream(neww);
					fos2.write(b, 0, b.length);
					fis2.close();
					fos2.close();

					// Delete old file
					File file1 = new File(fileDelete);
					file1.delete();

					fileDelete=neww;

					//Send text message
					dos.writeUTF("File successfully renamed\n");
					dos.flush();
				}

				if(x.equals("5"))
				{
					// EXIT

					//Send text message
					dos.writeUTF("Connection closed\n");
					dos.flush();
					System.exit(0);

					break;
				}
			}
		}

		catch (Exception ex)
		{
			System.out.println(ex);
		}

		finally
		{
			System.out.println("Client: "+clientNo+" Exit");
		}
	}
}

