/*
 * File: ToOtherBase.java
 * --------------------
 * This program converts numbers to a given base notation
 */

package PR;

import acm.program.*;

public class ToOtherBase extends ConsoleProgram {

	public void run() {
		// Enter number 
		int n = readInt("Enter number: ");
		// Enter base
		int base = readInt("Enter base: ");
		
		// Display converted number
		println(toOtherBase(n, base));
	}
	
	/*
	 * Pre-Condition : None
	 * 
	 * Post-Condition : Returns string of a given number in base notation
	 */
	public String toOtherBase(int num, int base){
		String res = "";
		while(num != 0){
			int residue = num % base;
			res = valueToDigit(residue) + res;
			num /= base;
		}
		return res;
	}
	
	/*
	 * Pre-Condition : Must define which value corresponds to which digit
	 * 
	 * Post-Condition : Returns the digit of the corresponding value
	 */
	public char valueToDigit(int val){
		if(val < 10){
			return (char)('0'+val);
		}else if(val < 10+('z'-'a')){
			return (char)('a'+(val-10));
		}else{
			return (char)('A'+(val-('z'-'a')-10));
		}
	}
}
