package jim.test;

import java.io.PrintStream;

public class Jim {
	
	private static final String JIM_YELLOW_PREFIX = "[\033[33mJIM\033[0m] ";
	private static final String HEADER = " --- \033[32mJim's trace output \033[0m\n";
	private static final String SEPARATOR = "\n\n\n" + JIM_YELLOW_PREFIX + HEADER;
	
	// **********************************
	
	public static void trace(String msg){
		if(traceEnabled()) print(msg);
	}
	public static void stackTrace(String msg){
		if(!traceEnabled()) return;
		
		PrintStream p = getPrint();
		p.println(SEPARATOR + JIM_YELLOW_PREFIX + "StackTrace : " + msg);
		
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		for (StackTraceElement stackTraceElement : st) {
			p.println(JIM_YELLOW_PREFIX+"\t* "+stackTraceElement);
		}
	}
	public static void stackTrace(){
		stackTrace("");
	}
	
	// **********************************
	
	public static String explain(Object o){
		String content;
		if(o == null){
			content = "null";
		} else {
			content = o.getClass().getSimpleName() + " : " + o.toString();
		}
		return "\n\t[ " + content + " ]";
	}
	
	private static void print(String msg){
		getPrint().println(SEPARATOR+JIM_YELLOW_PREFIX+msg);
	}
	private static boolean traceEnabled(){
		return true; // TODO pouvoir changer
	}
	private static PrintStream getPrint(){	
		return System.out; // TODO pouvoir changer
	}


}
