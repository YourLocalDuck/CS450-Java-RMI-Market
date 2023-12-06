# 450MarketAssignment
## To use, edit line 11 on Client.java to the machine where the Server will run. It is currently set to localhost for convenience in testing.
## After modification, use command `make` to compile the project. This will create a Client.jar and a Server.jar. `make run-server` and `make run-client` will run the server and client respectively, however, it is also possible to just transport the jar files into the context of the server and client machines, and simply run `java -jar Client.jar` or `java -jar Server.jar`.

## Reference
Client.jar and the Client.java file are the client files respectively, and handles the menu, and handles any request directly from the User. The appropriate request is then made to FrontController, which coordinates the Users or Items to proceed with the request.

Server.jar and StoreServer.java are the Server files, and their only purpose is to start the RMI server, and make the store, and first two initial users. If persistent data is added, then perhaps it will also be responsible for that.

The design of this project uses the Abstract Factory pattern and the Front Controller pattern. Front controller provides an abstraction, such that Client.java is free from handling store, and users, and just has to invoke the relevant method from the Front Controller. Abstract Factory handles the creation of Users, as the types (Admin or Customer) is unknown at runtime, so whichever type of user ends up being needed, that factory is called.

Most of the operations are done on the Store class, which is why operations on the StoreInterface object first obtained is so ubiquitous. These operations are, however, hidden from Client because of the addition of FrontController.

Here is a UML diagram of the design:
![UML.webp](https://raw.github.iu.edu/prajarub/450MarketAssignment/main/UML.webp?token=GHSAT0AAAAAAAAAU6M6SNYJVGDDJAUORR7IZLRA7GQ)