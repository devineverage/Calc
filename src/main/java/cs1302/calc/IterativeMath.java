package cs1302.calc;

/* Implement the Math interface using iteration here. For each method, replace
 * the throw statement with your implementation.
 */

/**
 * Contains iterative methods that return a result integer that is computed using an iterative operation
 *
 * The following operations are supported: addition (+), 
 * subtraction (-), multiplication (*), division(/), factorial(!) and exponentiation (^).
 *
 *
 *
 * @author Devin Everage and Daniel Tomlinson
 *
 */
public class IterativeMath implements Math {

    public int inc(int n) {
        return n + 1;
        //throw new UnsupportedOperationException();
    } // inc

    public int dec(int n) {
        
        return (n == 0) ? n : n - 1;
        //throw new UnsupportedOperationException();
    } // dec

    public int add(int lhs, int rhs) {
      while	(rhs	!=	0)	{
        lhs	=	inc(lhs);
        rhs	=	dec(rhs);	
				}	//	while
      return lhs;
        //throw new UnsupportedOperationException();
    } // add

    public int sub(int lhs, int rhs) {
				if	(rhs	>	lhs)	return	0;
		    while	(rhs	!=	0)	{
				  lhs	=	dec(lhs);
				  rhs	=	dec(rhs);	
				}	//	while
			  return lhs;
        //throw new UnsupportedOperationException();
    } // sub

    public int mul(int lhs, int rhs) {
      int sum = lhs;
      if (rhs == 0) return 0;
      while (rhs > 1) {
        sum = add(sum, lhs);
        rhs = dec(rhs);
        } // while
        return sum;
        //throw new UnsupportedOperationException();
    } // mul

    public int div(int lhs, int rhs) {
        int counter = 0;
         if(rhs == 0){
          throw new UnsupportedOperationException();}else{
        while (lhs >= rhs) {
          lhs = sub(lhs, rhs);
          counter = succ(counter);
          } // while
        }
        return counter;
       
    } // div

    public int fac(int n) {
        int acc = 1;
        while (n > 0) {
          acc = acc * n;
          n = n - 1;
        } // while
        return acc;
        //throw new UnsupportedOperationException();
    } // fac
    
    public int pow(int lhs, int rhs) {
        int acc = 1;
        while (rhs > 0) {
        acc = mul(acc, lhs);
        rhs = pred(rhs);
        } // while
        return acc;
        //throw new UnsupportedOperationException();
    } // pow
    
    public int lshift(int lhs, int rhs) {
        return lhs << rhs;
        //throw new UnsupportedOperationException();
    } // lshift
    
    public int rshift(int lhs, int rhs) {
        if(rhs ==0)
        return lhs;
        else
        return lhs >> rhs;
        //throw new UnsupportedOperationException();
    } // rshift

} // IterativeMath

