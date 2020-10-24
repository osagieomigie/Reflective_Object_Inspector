/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson, Osa Omigie 
 */

package reflective_object_inspector_cpsc_501;
import java.lang.reflect.*;
import java.util.*;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }
    

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	// print declaring class
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	System.out.println(tmpTab + "CLASS");
    	System.out.println(tmpTab + String.format("Class: %s", c.getName()));
    	
    	Class superClass = c.getSuperclass();
  
    	// print super class 
    	if(superClass != null) {
    			System.out.println(tmpTab + "SUPERCLASS -> Recursively Inspect");
    			System.out.println(tmpTab + String.format("Super Class: %s", superClass.getName()));
    			inspectClass(superClass, obj, recursive, depth+1);
    	}else {
    		System.out.println(tmpTab + "SuperClass: NONE");
    	}
    	
    	inspectInterfaces(c, obj, recursive, depth);
    }
    
    private void inspectInterfaces(Class c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	Class<?>[] interfaces = c.getInterfaces();
    	System.out.println(tmpTab + String.format("INTERFACES( %s )", c.getName()));
    	if (interfaces.length > 0) {
    		System.out.println(tmpTab + "Interfaces-> ");
	        for (Class<?> anInterface : interfaces) {
	            System.out.println(tmpTab +" " + anInterface.getName());
	        }
    	}else {
    		System.out.println(tmpTab + "Interfaces-> NONE");
    	}
    }

}
