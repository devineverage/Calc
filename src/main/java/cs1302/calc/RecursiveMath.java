package cs1302.calc;

/* Implement the Math interface using recursion here. For each method, replace
 * the throw statement with your implementation.
 */

/**
 * Contains pure tail recursive methods that return a result integer that is computed using a recursive operation
 *
 * The following operations are supported:  addition (+), 
 * subtraction (-), multiplication (*), division(/), factorial(!) and exponentiation (^).
 *
 * The multiplication, division, exponentiation, and factorial buttons all have an acc method in order to make sure it is purely tail recursive
 *
 *
 * @author Devin Everage and Daniel Tomlinson
 *
 */
 
public class RecursiveMath implements Math {

    public int inc(int n) {
        return n+1;
        //throw new UnsupportedOperationException();
    } // inc

    public int dec(int n) {
        return (n == 0) ? n : n - 1;
        //throw new UnsupportedOperationException();
    } // dec

    public int add(int lhs, int rhs) {
        if (rhs==0) return lhs;
        return add(inc(lhs), dec(rhs));
        //throw new UnsupportedOperationException();
    } // add

    public int sub(int lhs, int rhs) {
        if(rhs==0) return lhs;
        if(rhs>lhs) return 0;
        return sub(dec(lhs), dec(rhs));
        //throw new UnsupportedOperationException();
    } // sub

    public int mul(int lhs, int rhs) {
        return mul_acc(lhs, lhs, rhs);
        //throw new UnsupportedOperationException();
    } // mul
    
    public int	mul_acc(int	sum,	int	lhs,	int	rhs)	{
				if	(rhs	==	0)	return	0;
				if	(rhs	==	1)	return	sum;
				return	mul_acc(add(sum,	lhs),	lhs,	pred(rhs));
    }	//	mul_acc

    public int div(int lhs, int rhs) {
        return div_acc(0,lhs,rhs);
        //throw new UnsupportedOperationException();
    } // div
    
    public int div_acc(int counter, int lhs, int rhs) {
        if(rhs ==0) throw new UnsupportedOperationException();
        if(lhs < rhs) return counter;
        return div_acc(inc(counter), sub(lhs,rhs), rhs);
    
    }

    public int fac(int n) {
        return factorial_acc(1,n);
       // throw new UnsupportedOperationException();
    } // fac
    
    public int factorial_acc(int acc, int n) {
        if (n == 0) return acc;
        return factorial_acc(n * acc, n - 1);
        } // factorial_acc
    
    public int pow(int lhs, int rhs) {
        return pow_acc(1, lhs, rhs);
    } // pow
    
    int	pow_acc(int	acc,	int	lhs,	int	rhs)	{
				if	(rhs	==	0)	return	acc;
				return	pow_acc(mul(acc,	lhs),	lhs,	pred(rhs));
    }	// pow_acc

    public int lshift(int lhs, int rhs) {
        //throw new UnsupportedOperationException();
        return lhs << rhs;
    } // lshift
    
    public int rshift(int lhs, int rhs) {
        //throw new UnsupportedOperationException();
        if(rhs == 0)
        return lhs;
        else
        return lhs >> rhs;
    } // rshift

} // RecursiveMath


