Java Chat Application (CLI + GUI)  
Developed By  

Tanisha Sharma  

Project Description  

This project is a real-time chat application created using Java Socket Programming. It allows communication between a client and server over a network using both Command Line Interface (CLI) and Graphical User Interface (GUI).  

The application showcases fundamental networking concepts such as TCP communication, multithreading, and GUI development using Swing.  

Features  
- Real-time messaging between client and server  
- CLI-based chat system  
- GUI-based chat system using Java Swing  
- Multi-threaded communication with simultaneous read/write  
- Simple and user-friendly interface  
- Exit command to safely end connection  

Technologies Used  
- Java  
- Socket Programming (TCP/IP)  
- Multithreading  
- Swing (GUI)  
- BufferedReader & PrintWriter  

Project Structure  
chat_app_project/  
│  
├── src/  
│   └── project_implementation/  
│       ├── Client.java  
│       ├── Server.java  
│       ├── ClientGUI.java  
│       └── ServerGUI.java  


How to Run  
Step 1: Compile  
javac src/project_implementation/*.java
Step 2: Run Server  
java project_implementation.ServerGUI  
Step 3: Run Client  
java project_implementation.ClientGUI  

Important Instructions  
Start the server first.  
Then start the client.  
Both should run on the same machine or network.  

Learning Outcomes  
- Understanding of client-server architecture  
- Hands-on experience with Java sockets  
- Multithreading concepts in real-time applications  
- GUI development using Swing  

Future Enhancements  
- Multi-client support  
- Private messaging  
- Chat history storage  
- User authentication system  
