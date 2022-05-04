package conates.util.gui;

public class Console {

   public static String readString() {

	 int ch;
     String r= "";
     boolean done= false;
     while (!done) {
	   try {
		 ch = System.in.read();
	     if (ch < 0 || (char)ch == '\n')
	        done = true;
	      else if ((char)ch != '\r')
	        r = r + (char) ch;
	   }
	   catch(java.io.IOException e){
		 done = true;
	   }
     }
     return r;
   }

   public static int readInt() {

	 while(true) {
	   try {
		 return Integer.valueOf(readString().trim()).intValue();
	   }
	   catch(NumberFormatException e){
		 System.out.println("Not an	integer. Please	try again!");
	   }
     }
   }
   
   public static float readFloat() {

		 while(true) {
		   try {
			 return Float.valueOf(readString().trim()).floatValue();
		   }
		   catch(NumberFormatException e){
			 System.out.println("Not an	float. Please try again!");
		   }
	     }
	   }
}