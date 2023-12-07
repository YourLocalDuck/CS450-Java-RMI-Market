JFLAGS = -g
JC = javac
JAR = jar
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = $(wildcard *.java)

default: classes Server.jar Client.jar

classes: $(CLASSES:.java=.class)

Server.jar: classes
	$(JAR) cvfe Server.jar StoreServer *.class

Client.jar: classes
	$(JAR) cvfe Client.jar Client *.class

run-server: Server.jar
	java -jar Server.jar

run-client: Client.jar
	java -jar Client.jar

clean:
	$(RM) *.class *.jar

cleanclasses:
	$(RM) *.class