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
    	inspectMethods(c, obj, recursive, depth);
    	inspectFields(c, obj, recursive, depth);
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
    
    private void inspectMethods(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	System.out.println(tmpTab + String.format("METHODS( %s )", c.getName()));
    	
    	Method [] methods = c.getDeclaredMethods();
    	
    	if(methods.length > 0) {
    		System.out.println(tmpTab + "Methods-> ");
	    	for (Method m : methods) {
	    		System.out.println(tmpTab + " METHOD");
	    		
	    		// print method name
	    		System.out.println(tmpTab + "  Name: " + m.getName());
	    		
	    		// print method exceptions 
	    		Class<?> [] exps = m.getExceptionTypes();
	    		if (exps.length > 0) {
	    			System.out.println(tmpTab + "  Exceptions-> ");
	    			for (Class <?> tmpExp : exps) {
	    				System.out.println(tmpTab +"  class " + tmpExp.getName());
	    			}
	    		}else {
	    			System.out.println(tmpTab + "  Exceptions-> NONE");
	    		}
	    		
	    		// print parameter types of method 
	            Class<?>[] tmpParameters = m.getParameterTypes();
	            if (tmpParameters.length > 0) {
	            	System.out.println(tmpTab +"  Parameter types:");
	            	for(Class<?> parameter : tmpParameters) {
	            		if(parameter.isPrimitive())
	            			System.out.println(tmpTab + "  "+ parameter.getName());
	            		else
	            			System.out.println(tmpTab + "  class "+ parameter.getName());
	            	}
	            }else {
	            	System.out.println(tmpTab +"  Parameter types-> NONE");
	            }
	            
	            // print method return type 
	            System.out.println(tmpTab + "  Return type: " + m.getReturnType().getName());
	            
	            // print modifiers of constructor 
	            if ( Modifier.toString(m.getModifiers()).isEmpty()) {
	            	System.out.println(tmpTab +"  Modifiers: NONE");
	            }else {
	            	System.out.println(tmpTab + "  Modifiers: " + Modifier.toString(m.getModifiers()));
	            }
	    	}
    	}else {
    		System.out.println(tmpTab + "Methods-> NONE");
    	}
	    	
    }
    
    private void inspectFields(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	System.out.println(tmpTab + String.format("FIELDS( %s )", c.getName()));
    	
    	Field [] field = c.getDeclaredFields();
    	
    	if(field.length > 0) {
    		System.out.println(tmpTab + "Fields-> ");
    		
    		// inspect each field 
    		for (Field f : field) {
    			System.out.println(tmpTab + " FIELD");
    			f.setAccessible(true);
    			inspectField(f, obj, recursive, depth);
    		}
    	}else
    		System.out.println(tmpTab + "Fields-> NONE");
    }
    
    private Object getValue(Field f, Object obj) {
    	try {
    		return f.get(obj);
    	}catch (IllegalArgumentException | NullPointerException | IllegalAccessException e){
    		System.err.println("Can't access field " + obj);
    	}
    	
    	return null;
    }
    
    private void inspectField(Field f, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	// print field name
    	System.out.println(tmpTab + "  Name: " + f.getName());
    	
    	// print field type
    	Class<?> type = f.getType();
//    	if(!type.isPrimitive()) {
//    		System.out.println(tmpTab + "  Type: " + type);
//    	}else 
    	System.out.println(tmpTab + "  Type: " + type);
    	
    	// print field modifiers 
    	if ( Modifier.toString(f.getModifiers()).isEmpty()) {
        	System.out.println(tmpTab +"  Modifiers: NONE");
        }else {
        	System.out.println(tmpTab + "  Modifiers: " + Modifier.toString(f.getModifiers()));
        }
    	
    	// determine fields type, and print current value(s)
    	if(type.isPrimitive()) { 
    		// print fields value 
    		Object value = getValue(f, obj);
    		if(value != null) {
    			System.out.println(tmpTab + "  Value: " + value); 
    		}else {
    			System.out.println(tmpTab + "  Value: null"); 
    		}
    	}else if (type.isArray()) {
    		// inspect array
    		inspectFieldArray(f, obj, recursive, depth);
    	}else { 
    		// inspect object 
    	}
    	
    }
    
    
    private Object getArrayValue(Object obj, int index) {
    	try {
    		return Array.get(obj, index);
    	}catch (IllegalArgumentException | NullPointerException | ArrayIndexOutOfBoundsException  e){
    		System.err.println("Can't access array field " + obj);
    	}
    	
    	return null;
    }
    
    private void inspectFieldArray(Field f, Object obj, boolean recursive, int depth) {
    	// array object
    	Object array = getValue(f, obj);
    	
    	// inspect array
    	inspectArray(f.getType(), array, recursive, depth);
    	
    	
//    	// print component type
//    	System.out.println(tmpTab + "  Component Type: " + f.getType().getComponentType());
//    	
//    	// print array length
//    	int arrLength = Array.getLength(array);
//    	System.out.println("  Length: " + arrLength);
//    	
//    	// inspect array elements
//    	System.out.println("  Entries-> ");
//    	for (int i = 0; i < arrLength; i++) {
//    		Object arrValue = getArrayValue(array, i);
//    		if(arrValue != null) {
//    			// primitive object
//    			if (arrValue.getClass().getComponentType().isPrimitive())
//    				System.out.println(tmpTab + "   Value: " + arrValue);
//    			// class object
//    			else {
//    				System.out.println(tmpTab + "   Value (ref): " + arrValue.getClass().getName() + Integer.toHexString(obj.hashCode()) );
//    				
//    				// inspect class if recursive true 
//    				if (recursive) {
//    					System.out.println("    -> Recursively inspect");
//    					inspectClass(arrValue.getClass(), array, recursive, depth+1);
//    				}
//    			}
//    				
//    		}else {
//    			System.out.println(tmpTab + "   Value: null"); 
//    		}
//    		
//    	}
    }
    
    private void inspectArray(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	// print component type
    	System.out.println(tmpTab + "  Component Type: " + c.getComponentType());
    	
    	// print array length
    	int arrLength = Array.getLength(obj);
    	System.out.println(tmpTab + "  Length: " + arrLength);
    	
    	// inspect array elements
    	System.out.println(tmpTab + "  Entries-> ");
    	for (int i = 0; i < arrLength; i++) {
    		Object arrValue = getArrayValue(obj, i);
    		if(arrValue != null) {
    			// primitive object
    			if (c.getComponentType().isPrimitive())
    				System.out.println(tmpTab + "   Value: " + arrValue);
    			// class object
    			else {
    				System.out.println(tmpTab + "   Value (ref): " + arrValue.getClass().getName() + "@" + Integer.toHexString(arrValue.hashCode()) );
    				
    				// inspect class if recursive true 
    				if (recursive) {
    					System.out.println(tmpTab + "    -> Recursively inspect");
    					inspectClass(arrValue.getClass(), arrValue, recursive, depth+1);
    				}
    			}
    				
    		}else {
    			System.out.println(tmpTab + "   Value: null"); 
    		}
    		
    	}
    }
}
