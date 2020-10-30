/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson, Osa Omigie 
 */

import java.lang.reflect.*;

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class<?> c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }
    
    public StringBuilder calculateTabDepth(int depth) {
    	StringBuilder tmpTab = new StringBuilder("");
    	if (depth > 0) { 
	    	for(int i = 0; i < depth; i++) {
	    		tmpTab.append("\t");
	    	}
    	}
    	
    	return tmpTab;
    }
    
    private void inspectClass(Class<?> c, Object obj, boolean recursive, int depth) {
    	// print declaring class
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	System.out.println(tabDepth + "CLASS");
    	System.out.println(tabDepth + String.format("Class: %s", c.getName()));
    	
    	if (c.isArray()) {
    		inspectArray(c, obj, recursive, depth);
    	}else {
    	
	    	Class<?> superClass = c.getSuperclass();
	  
	    	// print super class 
	    	if(superClass != null) {
	    			System.out.println(tabDepth + "SUPERCLASS -> Recursively Inspect");
	    			System.out.println(tabDepth + String.format("SuperClass: %s", superClass.getName()));
	    			inspectClass(superClass, obj, recursive, depth+1);
	    	}else {
	    		System.out.println(tabDepth + "SuperClass: NONE");
	    	}
	    	
	    	inspectInterfaces(c, obj, recursive, depth);
	    	inspectConstructors(c, obj, recursive, depth);
	    	inspectMethods(c, obj, recursive, depth);
	    	inspectFields(c, obj, recursive, depth);
    	}
    }
    
    private void inspectInterfaces(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	Class<?>[] interfaces = c.getInterfaces();
    	System.out.println(tabDepth + String.format("INTERFACES( %s )", c.getName()));
    	if (interfaces.length > 0) {
    		System.out.println(tabDepth + "Interfaces-> ");
	        for (Class<?> anInterface : interfaces) {
	        	System.out.println(tabDepth + " INTERFACE -> Recursively Inspect");
	            System.out.println(tabDepth +" " + anInterface.getName());
	            // recursively recurse on interfaces 
	            inspectClass(anInterface, obj, recursive, depth+1);
	        }
    	}else {
    		System.out.println(tabDepth + "Interfaces-> NONE");
    	}
    }
    
    public void printExceptions(Class<?> ref [], StringBuilder tmpTab) {
    	 
		if (ref.length > 0) {
			System.out.println(tmpTab + "  Exceptions-> ");
			for (Class <?> tmpExp : ref) {
				System.out.println(tmpTab +"  class " + tmpExp.getName());
			}
		}else {
			System.out.println(tmpTab + "  Exceptions-> NONE");
		}
    }
    
    public void printParameterTypes(Class<?> ref[], StringBuilder tmpTab) {
    	if (ref.length > 0) {
        	System.out.println(tmpTab +"  Parameter types:");
        	for(Class<?> parameter : ref) {
        		if(parameter.isPrimitive())
        			System.out.println(tmpTab + "   "+ parameter.getName());
        		else
        			System.out.println(tmpTab + "   class "+ parameter.getName());
        	}
        }else {
        	System.out.println(tmpTab +"  Parameter types-> NONE");
        }
    }
    
    private void inspectConstructors(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate tab depth 
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	// print constructors 
    	Constructor<?>[] constructors = c.getDeclaredConstructors();
    	System.out.println(tabDepth + String.format("CONSTRUCTORS( %s )", c.getName()));
    	if (constructors.length > 0) {
    		System.out.println(tabDepth + "Constructors-> ");
	        for (Constructor<?> aConstructor : constructors) {
	        	System.out.println(tabDepth + " CONSTRUCTOR");
	        	
	        	// print name of constructor
	            System.out.println(tabDepth +"  Name: " + aConstructor.getName());
	            
	            // print exceptions of constructor 
	            Class<?>[] tmpExceptions = aConstructor.getExceptionTypes();
	            printExceptions(tmpExceptions, tabDepth);
	            
	            // print parameter types of constructor 
	            Class<?>[] tmpParameters = aConstructor.getParameterTypes();
	            printParameterTypes(tmpParameters, tabDepth);
	            
	            // print modifiers of constructor 
	            if ( Modifier.toString(aConstructor.getModifiers()).isEmpty()) {
	            	System.out.println(tabDepth +"  Modifiers: NONE");
	            }else {
	            	System.out.println(tabDepth + "  Modifiers: " + Modifier.toString(aConstructor.getModifiers()));
	            }
	        }
    	}else {
    		System.out.println(tabDepth + "Constructors-> NONE");
    	}
    }
    
    private void inspectMethods(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	System.out.println(tabDepth + String.format("METHODS( %s )", c.getName()));
    	
    	Method [] methods = c.getDeclaredMethods();
    	
    	if(methods.length > 0) {
    		System.out.println(tabDepth + "Methods-> ");
	    	for (Method m : methods) {
	    		System.out.println(tabDepth + " METHOD");
	    		
	    		// print method name
	    		System.out.println(tabDepth + "  Name: " + m.getName());
	    		
	    		// print method exceptions 
	    		Class<?> [] exps = m.getExceptionTypes();
	    		if (exps.length > 0) {
	    			System.out.println(tabDepth + "  Exceptions-> ");
	    			for (Class <?> tmpExp : exps) {
	    				System.out.println(tabDepth +"  class " + tmpExp.getName());
	    			}
	    		}else {
	    			System.out.println(tabDepth + "  Exceptions-> NONE");
	    		}
	    		
	    		// print parameter types of method 
	            Class<?>[] tmpParameters = m.getParameterTypes();
	            if (tmpParameters.length > 0) {
	            	System.out.println(tabDepth +"  Parameter types:");
	            	for(Class<?> parameter : tmpParameters) {
	            		if(parameter.isPrimitive())
	            			System.out.println(tabDepth + "  "+ parameter.getName());
	            		else
	            			System.out.println(tabDepth + "  class "+ parameter.getName());
	            	}
	            }else {
	            	System.out.println(tabDepth +"  Parameter types-> NONE");
	            }
	            
	            // print method return type 
	            System.out.println(tabDepth + "  Return type: " + m.getReturnType().getName());
	            
	            // print modifiers of constructor 
	            if ( Modifier.toString(m.getModifiers()).isEmpty()) {
	            	System.out.println(tabDepth +"  Modifiers: NONE");
	            }else {
	            	System.out.println(tabDepth + "  Modifiers: " + Modifier.toString(m.getModifiers()));
	            }
	    	}
    	}else {
    		System.out.println(tabDepth + "Methods-> NONE");
    	}
	    	
    }
    
    private void inspectFields(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	System.out.println(tabDepth + String.format("FIELDS( %s )", c.getName()));
    	
    	Field [] field = c.getDeclaredFields();
    	
    	if(field.length > 0) {
    		System.out.println(tabDepth + "Fields-> ");
    		
    		// inspect each field 
    		for (Field f : field) {
    			System.out.println(tabDepth + " FIELD");
    			f.setAccessible(true);
    			inspectField(f, obj, recursive, depth);
    		}
    	}else
    		System.out.println(tabDepth + "Fields-> NONE");
    }
    
    private Object getValue(Field f, Object obj) {
    	try {
    		return f.get(obj);
    	}catch (IllegalArgumentException | IllegalAccessException e){
    		System.err.println("Can't access field " + obj);
    		
    	}
    	
    	return null;
    }
    
    private void inspectField(Field f, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	// print field name
    	System.out.println(tabDepth + "  Name: " + f.getName());
    	
    	// print field type
    	Class<?> type = f.getType();
    	System.out.println(tabDepth + "  Type: " + type);
    	
    	// print field modifiers 
    	if ( Modifier.toString(f.getModifiers()).isEmpty()) {
        	System.out.println(tabDepth +"  Modifiers: NONE");
        }else {
        	System.out.println(tabDepth + "  Modifiers: " + Modifier.toString(f.getModifiers()));
        }
    	
    	// determine fields type, and print current value(s)
    	if(type.isPrimitive()) { 
    		// print fields value 
    		Object value = getValue(f, obj);
    		if(value != null) {
    			System.out.println(tabDepth + "  Value: " + value); 
    		}else {
    			System.out.println(tabDepth + "  Value: null"); 
    		}
    	}else if (type.isArray()) {
    		// inspect array
    		inspectFieldArray(f, obj, recursive, depth);
    	}else { 
    		// inspect object
    		Object value = getValue(f, obj);
    		if (value != null) {
	    		System.out.println(tabDepth + "  Value (ref): " + value.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(value)) );
				
				// inspect class if recursive true 
				if (recursive) {
					System.out.println(tabDepth + "    -> Recursively inspect");
					inspectClass(value.getClass(), value, recursive, depth+1);
				}
    		}else {
    			System.out.println(tabDepth + "  Value: null"); 
    		}
    	}
    	
    }
    
    private void inspectFieldArray(Field f, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	// array object
    	Object array = getValue(f, obj);
    	
    	// inspect array
    	inspectArray(f.getType(), array, recursive, depth);
    }
    
    private void inspectArray(Class<?> c, Object obj, boolean recursive, int depth) {
    	// calculate depth 
    	StringBuilder tabDepth = calculateTabDepth(depth);
    	
    	// print component type
    	System.out.println(tabDepth + "  Component Type: " + c.getComponentType());
    	
    	// print array length
    	int arrLength = Array.getLength(obj);
    	System.out.println(tabDepth + "  Length: " + arrLength);
    	
    	// inspect array elements
    	System.out.println(tabDepth + "  Entries-> ");
    	for (int i = 0; i < arrLength; i++) {
    		Object arrValue = Array.get(obj, i);
    		if(arrValue != null) {
    			// primitive object
    			if (c.getComponentType().isPrimitive())
    				System.out.println(tabDepth + "   Value: " + arrValue);
    			// class object
    			else {
    				System.out.println(tabDepth + "   Value (ref): " + arrValue.getClass().getName() + "@" + Integer.toHexString(arrValue.hashCode()) );
    				
    				// inspect class if recursive true 
    				if (recursive) {
    					System.out.println(tabDepth + "    -> Recursively inspect");
    					inspectClass(arrValue.getClass(), arrValue, recursive, depth+1);
    				}
    			}
    				
    		}else {
    			System.out.println(tabDepth + "   Value: null"); 
    		}
    		
    	}
    }
}
