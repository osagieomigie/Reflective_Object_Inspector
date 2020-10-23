/**
 * CPSC 501
 * Inspector starter class
 *
 * @author Jonathan Hudson, Osa Omigie
 */

public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	// print declaring class
    	System.out.println("CLASS");
    	System.out.println(String.format("Class: %s", c.getName()));
    	
    	Class superClass = c.getSuperclass();
  
    	// print super class 
    	if(superClass != null) {
    			System.out.println("SUPERCLASS -> Recursively Inspect");
    			System.out.println(String.format("Super Class: %s", superClass.getName()));
    			inspectClass(superClass, obj, recursive, depth+1);
    	}else {
    		System.out.println("SuperClass: NONE");
    	}
    	
    }

}