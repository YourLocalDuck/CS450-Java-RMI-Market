# Project Introduction

This project is a Java RMI-based market system created for CS450. It uses Remote Method Invocation (RMI) to allow a client-server architecture where clients can interact with a server-side store. The design employs the Abstract Factory pattern and the Front Controller pattern to handle user and store interactions.

# How to Compile

Before compiling, ensure that line 11 in `Client.java` is edited to reflect the machine where the server will run. By default, it is set to `localhost` for testing convenience.

To compile the project, use the following command:

```sh
make
```

This will create `Client.jar` and `Server.jar`.

# How to Run

### Running the Server
To run the server, use the following command:
```sh
make run-server
```

Alternatively, you can run the server by executing:
```sh
java -jar Server.jar
```

### Running the Client
To run the client, use the following command:
```sh
make run-client
```

Alternatively, you can run the client by executing:
```sh
java -jar Client.jar
```

Ensure that the client is set to connect to the correct server address as specified in `Client.java`.

# Input/Output

### Input
- **Client:** Handles user input and sends requests to the server via the FrontController.
- **Server:** Processes requests and manages store operations and user interactions.

### Output
- **Client:** Displays menus and results based on user interactions.
- **Server:** Manages store and user data, processes client requests, and maintains persistent data if implemented.

# Reference

### Client
- **Client.jar** and `Client.java`: Handle user input and menu interactions. Requests are forwarded to the `FrontController` for processing.

### Server
- **Server.jar** and `StoreServer.java`: Start the RMI server, initialize the store, and create the first two users. They may also handle persistent data if implemented.

### Design Patterns
- **Abstract Factory Pattern:** Used for creating user types (Admin or Customer) dynamically at runtime.
- **Front Controller Pattern:** Provides an abstraction layer, so Client.java handles requests without managing store or user details directly. It invokes relevant methods from the FrontController.

### UML Diagram

The UML diagram below illustrates the design of the project:
![UML](https://github.com/YourLocalDuck/CS450-Java-RMI-Market/assets/90874972/5708b90a-6026-480f-ad2e-5cb072422522)
The diagram is also available in the project folder.

### Sample Workflow
A reproducible example workflow demonstrating the functionality of the patterns can be found in `sampleCode.txt`.

# Team
This project was created by a single developer as a final project for CS450.
