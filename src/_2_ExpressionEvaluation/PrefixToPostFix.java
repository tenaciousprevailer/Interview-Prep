package _2_ExpressionEvaluation;

import java.util.Stack;

public class PrefixToPostFix {

	public static void main(String[] args) {
		//String prefix = "*-A/BC-/AKL"; // postfix abcd^e-fgh*+^*+i- // ABC/-AK/L-*.... A - (b/c)  *  (a/k) - l
		String prefix = "+AB";
		System.out.println("Prefix: " + prefix);

		String postFix = convertToPostFix(prefix);
		System.out.println("Postfix: " + postFix);
	}

	private static String convertToPostFix(String prefix) {
		char ch;
		Stack<String> stack = new Stack<>();
		for(int i=prefix.length()-1;i>-1;i--) {
			ch = prefix.charAt(i);
			if(Character.isLetterOrDigit(ch)) {
				stack.push(ch + "");
			} else { //operator
				stack.push(stack.pop() + stack.pop() + ch);
			}
		}
		
		return stack.pop();
	}

}
