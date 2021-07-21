# Chat-System

### Java Concepts used:Multi-Threading, Networking, GUI(swings), Collections(LinkedHashMap, Sets, etc) ###

---
## General concepts which are used:
- Throughout the communication between server-client or client-server, 3-way handshake method is used.

E.G. for server-client:
1 - Server notifies the client that its about to send information.
2 - Client acknowledges about the information.
3 - Server sends the information.

This ensures that no information is lost.

- BufferedReader is used for reading the output from any socket, and PrintWriter is used with autoflush enabled(This was the only reason PrintWriter was used because of its autoflush feature).

''' SideNote:We know that the file structure is not good...but the distribution of work is done pretty well through different classes.. '''


## Server-Side

### Establishing connection to the server ###

- The server's main thread continously listnes to the port for incoming connections.
- Once a connection is detected, Server sends a "Who are you" message to the client - inorder to identify that client using that name.
- And once the client replys, an entry of that name along with the socket is stored in a hashmap of clients.
- Each client trying to connect to the port through socket gets assigned 2 threads for communicating through the server...reason for that will be explained later.
- Once the hashmap entries are done..the client can send multiple signals depending on what service it needs.(Various services will be covered later)

### class Information ###
- This stores the information related to each client with each of its Object.

### class Option ###
- This is the real "WorkArea" for each client

- This class has a switch case which indicates which service the client has requested to the server.

- The working explained is so as a normal person should understand, to understand the technical stuff...read the code..it is well documented.(Not yet..its a work in progress)

## Cases:

- "Send Message": The working starts with a 3-way handshake as explained earlier, and the corresponding message is sent to the other client.
- "Send Who is online": This is used by the refresh button available on each clients'gui: It sends all the available clients connected to the server...(Since the project does not
contain any Database-once the server shuts down - all clients are disconnected and the info is lost

- "Typing" - This is same as watsapp i.e. when a client is typing any message to other client the other client's gui highlights that the client is typing something.

- "Broadcasting" - This is used to broadcast a message to all the clients connected to the server.(Kind of like watsapp but with no restriction on the number of clients :D)

# Client Side

- Once the client runs the program..the code will automatically try to connect to the server on the specified port and refresh the "Who is online" feed.
- Same as server, 2 threads are ran simultaneous own having their own reason.(Explained later)

### GUI ###

- The GUI is made with the help of swings....it is not the best, but it has its moments :D.

### ServerInfo ###

- This class is used to store the relevant info used by the client to communicate.

## Options ###

- This does the same things as Server but in the reverse manner(Meaning here the client is the one to initiate the service request).
- Now this has two different switch cases one for Incoming and one for outgoing requests.(Reason will be explained later).
- 

### ClientInfos ###
- This is used to store the infos of the clients.
- This class also containes some functions to help out with the gui inorder to display the user the information about the client.(Like typing, etc)

### RelevantInfos ###
- This is used to store some variables which can come handy all over the file.


### toastr###

- This is used to display a tostr indicating an incoming message from some user.


## Overall:
Overall The file cotaines several functions like addClient, checkClients which help in displaying relevant, Updated information to the user by updating the GUI.

# Reasoning Behind 2 threads and 2 switch cases:
- Explaining this as a seperate section is because this is something which makes my project standoff from many server-client projects out there.
- Lets Understand a situation here - 
A client A is typing a message and to client B and at the same time client C is sending a message to client A.

And a typing service is used when a client types even a key to the textField provided and thusit should be very fast to cope up with the typing speed.

- So the two threads represent that whenever A types a message to B A' outgoing thread will focus on sending information to the server to tell B that i am typing.
- At the same time A' incoming thread will recieve the information that C is typing to him/her.
- And the server has send/recieve from A about all this stuuf which requires 2 individual threads to do so.
- In future stuff, if this application grows...we can have 2 multiple servers to deal with incoming/outgoing action going on...and another server to manage these both server
but this discussion is for the future.


