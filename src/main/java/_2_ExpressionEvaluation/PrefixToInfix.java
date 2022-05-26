package _2_ExpressionEvaluation;

import java.util.Stack;

public class PrefixToInfix {

	public static void main(String[] args) {
		String prefix = "+a-*b^-^cde+f*ghi"; //  a+b*(c^d-e)^(f+g*h)-i   /// (a+((b*(((c^d)-e)^(f+(g*h))))-i))
		System.out.println("Prefix: " + prefix);

		String infix = convertToInFix(prefix);
		System.out.println("Infix: " + infix);
	}

	private static String convertToInFix(String prefix) {

		char ch;
		Stack<String> stack = new Stack<>();
		for(int i = prefix.length()-1; i>-1;i--) {
			ch = prefix.charAt(i);
			if(Character.isLetterOrDigit(ch)) {
				stack.push(ch+"");
			} else {
				stack.push("(" + stack.pop() + ch + stack.pop() + ")");
			}
		}

		return stack.pop();
	}
}
