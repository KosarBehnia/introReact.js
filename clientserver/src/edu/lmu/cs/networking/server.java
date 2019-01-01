	package edu.lmu.cs.networking;
	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
import java.util.Date;
public class server {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 System.out.println("The server is running.");
	        int clientNumber = 0;
	        ServerSocket listener = new ServerSocket(9899);
	        try {
	            while (true) {
	                new Calculator(listener.accept(), clientNumber++).start();
	            }
	        } finally {
	            listener.close();
	        }
	    }

	    /**
	     * A private thread to handle calculator requests on a particular
	     * socket.  The client terminates the dialogue by sending a single line
	     * containing only a period.
	     */
	    private static class Calculator extends Thread {
	        private Socket socket;
	        private int clientNumber;

	        public Calculator(Socket socket, int clientNumber) {
	            this.socket = socket;
	            this.clientNumber = clientNumber;
	            log("New connection with client# " + clientNumber + " at " + socket);
	        }

	        /**
	         * Services this thread's client by first sending the
	         * client a welcome message then repeatedly reading strings
	         * and sending back the result of operation.
	         */
	        public void run() {
	            try {

	                // Decorate the streams so we can send characters
	                // and not just bytes.  Ensure output is flushed
	                // after every newline.
	                BufferedReader in = new BufferedReader(
	                new InputStreamReader(socket.getInputStream()));
	                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	                // Send a welcome message to the client.
	                out.println("Hello, you are client #" + clientNumber + ".");
	                out.println("Enter your desired operation\n");
	                double result = 0;
	                // Get messages from the client, line by line; return them
	                // calculation
	                while (true) {
	                    String input = in.readLine();
	                    String[] parts = input.split("\\$");
	                    if (input == null || input.equals(".")) {
	                        break;
	                    }
	                    if((parts[1].equals("add")) || (parts[1].equals("ADD")))
	                    {
	                      if( parts.length < 3)
	                    	{
	                    	  out.println("where is parameters ?????");
	                    	  break;
	                    	}
	                    	if((parts[2].equals("")) || (parts[3].equals("")))
	                    	{
	                    		out.println("where is parameters ?????");
	                    	    break;
	                    	}
	                    
	                     result = add(parts[2],parts[3]);
	                     out.println(result);
	                    }
	                    else
	                    	if((parts[1].equals("subtract")) || (parts[1].equals("SUBTRACT")))
	                    	{
	                    		if( parts.length < 3)
		                    	{
		                    	  out.println("where is parameters ?????");
		                    	  break;
		                    	}
		                    	if((parts[2].equals("")) || (parts[3].equals("")))
		                    	{
		                    		out.println("where is parameters ?????");
		                    	    break;
		                    	}
	                    		
	                    		result =  subtract(parts[2],parts[3]);
	                    		out.println(result);
	                    	}
	                   else 
	   	                   if((parts[1].equals("DIVIDE")) || (parts[1].equals("divide")))
	   	                   {
	   	                	  if( parts.length < 3)
	                    	 {
	                    	  out.println("where is parameters ?????");
	                    	  break;
	                    	  }
	                    	 if((parts[2].equals("")) || (parts[3].equals("")) || parts[3].equals("0"))
	                    	 {
	                    		out.println("where is parameters ?????");
	                    	    break;
	                    	 }
	                    	 result = divide(parts[2],parts[3]);
	   	                     out.println(result);
	   	                   }
	   	                else 
	                    	if((parts[1].equals("MULTIPLY")) || (parts[1].equals("multiply")))
	                    	{
	  	   	                	  if( parts.length < 3)
	  	                    	 {
	  	                    	  out.println("where is parameters ?????");
	  	                    	  break;
	  	                    	  }
	  	                    	 if((parts[2].equals("")) || (parts[3].equals("")))
	  	                    	 {
	  	                    		out.println("where is parameters ?????");
	  	                    	    break;
	  	                    	 }
	  	                    	 result = multiply(parts[2],parts[3]);
	  	                    	 out.println(result);
	  	   	                   }
	                    	
	                    else 
	   	                   if((parts[1].equals("sin")) || (parts[1].equals("SIN")))
	   	                   {
	   	                	   
	   	                   
	   	                  if( parts.length > 3)
                   	       {
                   	         out.println("we only need one parameter !!!!!!!");
                   	         break;
                   	        }
	                    	 if((parts[2].equals("")))
	                    	 {
	                    		out.println("where is parameters ?????");
	                    	    break;
	                    	 }
	                    	 result = sinfunc(parts[2]);
	                    	 out.println("result is" + result);
	   	                   
	   	                   }
	   	                else 
	                    	if((parts[1].equals("cos")) || (parts[1].equals("COS")))
	                    	{  
	                    		if( parts.length > 3)
	                   	       {
	                   	         out.println("we only need one parameter !!!!!!!");
	                   	         break;
	                   	        }
		                    	 if((parts[2].equals("")))
		                    	 {
		                    		out.println("where is parameters ?????");
		                    	    break;
		                    	 }
		                    	 result = cosfunc(parts[2]);
		                    	 out.println("result is" + result);
		   	                   
		   	                   }
	                    		
	                    else 
	   	                    if((parts[1].equals("tan")) || (parts[1].equals("TAN")))
	   	                    {
	   	                    	if( parts.length > 3)
		                   	       {
		                   	         out.println("we only need one parameter !!!!!!!");
		                   	         break;
		                   	        }
			                    	 if((parts[2].equals("")))
			                    	 {
			                    		out.println("where is parameters ?????");
			                    	    break;
			                    	 }
			                    	 result = tanfunc(parts[2]);
			                    	 out.println("result is" + result);
	   	                    	
	   	                    }
	   	                 else 
		                    	if((parts[1].equals("cot")) || (parts[1].equals("COT")))
		                    	{
		                    		if( parts.length > 3)
			                   	       {
			                   	         out.println("we only need one parameter !!!!!!!");
			                   	         break;
			                   	        }
				                    	 if((parts[2].equals("")))
				                    	 {
				                    		out.println("where is parameters ?????");
				                    	    break;
				                    	 }
				                    	 result = cotanfunc(parts[2]);
				                    	 out.println("result is" + result);
		                    		
		                    	}
		                  else
		   	                    out.println("Sorry it is not among our services :c");
	                }
	            } catch (IOException e) {
	                log("Error handling client# " + clientNumber + ": " + e);
	            } finally {
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                    log("Couldn't close a socket, what's going on?");
	                }
	                log("Connection with client# " + clientNumber + " closed");
	            }
	        }

	        /**
	         * Logs a simple message.  In this case we just write the
	         * message to the server applications standard output.
	         */
	        private double add(String num1, String num2 )
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double number2 = Double.parseDouble(num2);
	        	return (number1 + number2);
	        }
	        private double subtract(String num1, String num2 )
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double number2 = Double.parseDouble(num2);
	        	return (number1 - number2);
	        }
	        private double divide(String num1, String num2 )
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double number2 = Double.parseDouble(num2);
	        	double d = number1/number2;
	        	return (d);
	        }
	        private double multiply(String num1, String num2 )
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double number2 = Double.parseDouble(num2);
	        	double d = number1*number2;
	        	return (d);
	        }
	        private double sinfunc(String num1)
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double d = Math.sin(number1);
	        	return (d);
	        }
	        private double cosfunc(String num1)
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double d = Math.cos(number1);
	        	return (d);
	        }
	        private double tanfunc(String num1)
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double d = Math.tan(number1);
	        	return (d);
	        }
	        private double cotanfunc(String num1)
	        {
	        	double number1 = Double.parseDouble(num1);
	        	double d = (Math.cos(number1))/Math.sin(number1);
	        	return (d);
	        }
	        private void log(String message) {
	            System.out.println(message);
	        }

	}

}
