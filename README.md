1. What is an exception’s hierarchy?

All exceptions are throwables, and then within exceptions there are subclasses of exceptions that are either checked or unchecked exceptions.
Some of these subclasses include Runtime Exceptions, Class Not Found Exceptions, Clone Not Supported Exceptions, and IO Exceptions.

2. Why do we need the exception hierarchy?

One of the benefits to having an exception hierarchy is that it simplifies the need for having many catch clauses that can instead be caught by a single super-type exception.

3. What exception types do you know?

There are mainly two common types of exceptions. These are checked, and unchecked exceptions. Unchecked exceptions are exceptions that occur during runtime, and therefore cannot be predicted.
Checked exceptions are exceptions that occur at compile time, so they must be handled through try, catch, and finally code blocks before the code will compile.

4. How we can throw an exception in Java?

In a method declaration, the method can declare an exception that it can throw, and then instead of needing a try, catch block, the exception from the method call is thrown and then handled in a method further up the call stack. 

5. How we can handle exceptions in Java (2 ways)?

One way we can handle exceptions in Java is through try, catch, and finally blocks. These blocks let you try to execute a block of code, and if an exception is thrown, then the catch block can handle the exception in various ways.
Another way to handle exceptions is with a try-catch with resources block. For this you must instantiate an object within the try block parameters, and then this will automatically close any resources after the code executes. 

6. When we can use try-catch with resources? What is the requirement?

For a try-catch with resources block, you must instantiate a new object within the try block parameters. Then once the code is executed, the try-catch with resources block will automatically close any resources still open associated with the object.
Finally, the catch block will catch and handle any further exceptions that are thrown.

7. When finally block will be executed?

The finally block will always be executed after any try-catch blocks. The finally will always execute no matter what. 

8. Will you handle RuntimeExceptions in try-catch block?

Runtime exceptions are usually never handled in the same way as checked exceptions unless there are certain specific scenarios that require handling runtime exceptions. 

9. How to create a custom exception? 

Custom exceptions can be created by creating a class that extends the Exception class or any of its subclasses.
Then within the new class you can create constructors or methods to handle the new custom exception.            

10. What is a logger? Logger levels? What log aggregators do you know?

A logger is what is used to designate what types of information you want to log from your executed code. The logger creates a log event that can then be stored and referenced at a later time.
The logger levels are used to define, manage, and categorize the importance of a given logged event. Loki, Rsyslog, Splunk, Elk.
