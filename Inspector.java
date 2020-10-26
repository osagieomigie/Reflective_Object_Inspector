/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson, Osa Omigie 
 */

import java.lang.reflect.*;
//import java.util.*;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class<?> c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }
    

    private void inspectClass(Class<?> c, Object obj, boolean recursive, int depth) {
    	// print declaring class
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	System.out.println(tmpTab + "CLASS");
    	System.out.println(tmpTab + String.format("Class: %s", c.getName()));
    	
    	Class<?> superClass = c.getSuperclass();
  
    	// print super class 
    	if(superClass != null) {
    			System.out.println(tmpTab + "SUPERCLASS -> Recursively Inspect");
    			System.out.println(tmpTab + String.format("SuperClass: %s", superClass.getName()));
    			inspectClass(superClass, obj, recursive, depth+1);
    	}else {
    		System.out.println(tmpTab + "SuperClass: NONE");
    	}
    	
    	inspectInterfaces(c, obj, recursive, depth);
    	inspectConstructors(c, obj, recursive, depth);
    }
    
    private void inspectInterfaces(Class<?> c, Object obj, boolean recursive, int depth) {
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
	        	System.out.println(tmpTab + " INTERFACE -> Recursively Inspect");
	            System.out.println(tmpTab +" " + anInterface.getName());
	            // recursively recurse on interfaces 
	            inspectClass(anInterface, obj, recursive, depth+1);
	        }
    	}else {
    		System.out.println(tmpTab + "Interfaces-> NONE");
    	}
    }
    
    private void inspectConstructors(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	// print constructors 
    	Constructor<?>[] constructors = c.getDeclaredConstructors();
    	System.out.println(tmpTab + String.format("CONSTRUCTORS( %s )", c.getName()));
    	if (constructors.length > 0) {
    		System.out.println(tmpTab + "Constructors-> ");
	        for (Constructor<?> aConstructor : constructors) {
	        	System.out.println(tmpTab + " CONSTRUCTOR");
	        	
	        	// print name of constructor
	            System.out.println(tmpTab +"  Name: " + aConstructor.getName());
	            
	            // print exceptions of constructor 
	            Class<?>[] tmpExceptions = aConstructor.getExceptionTypes();
	            if (tmpExceptions.length > 0) {
	            	System.out.println(tmpTab +"  Exceptions->");
	            	for(Class<?> exception : tmpExceptions) {
	            		System.out.println(tmpTab +"  class " + exception.getName());
	            	}
	            }else {
	            	System.out.println(tmpTab +"  Exceptions-> NONE");
	            }
	            
	            // print parameter types of constructor 
	            Class<?>[] tmpParameters = aConstructor.getParameterTypes();
	            if (tmpParameters.length > 0) {
	            	System.out.println(tmpTab +"  Parameter types:");
	            	for(Class<?> parameter : tmpParameters) {
	            		if(parameter.isPrimitive())
	            			System.out.println(tmpTab + "   "+ parameter.getName());
	            		else
	            			System.out.println(tmpTab + "   class "+ parameter.getName());
	            	}
	            }else {
	            	System.out.println(tmpTab +"  Parameter types-> NONE");
	            }
	            
	            // print modifiers of constructor 
	            if ( Modifier.toString(aConstructor.getModifiers()).isEmpty()) {
	            	System.out.println(tmpTab +"  Modifiers: NONE");
	            }else {
	            	System.out.println(tmpTab + "  Modifiers: " + Modifier.toString(aConstructor.getModifiers()));
	            }
	            
	        }
    	}else {
    		System.out.println(tmpTab + "Constructors-> NONE");
    	}
    	
    }

}
