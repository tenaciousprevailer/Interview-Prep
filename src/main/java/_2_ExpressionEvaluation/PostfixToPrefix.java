package _2_ExpressionEvaluation;

import java.util.Stack;

public class PostfixToPrefix {

	public static void main(String[] args) {
		String postfix = "ABC/-AK/L-*"; // infix a+b*(c^d-e)^(f+g*h)-i
		System.out.println("Postfix: " + postfix);

		String prefix = convertToPreFix(postfix);
		System.out.println("prefix: " + prefix);
	}

	private static String convertToPreFix(String postfix) {

		char ch;
		Stack<String> stack = new Stack<>();
		for(int i=0;i<postfix.length();i++) {
			ch = postfix.charAt(i);
			if(Character.isLetterOrDigit(ch)) {
				stack.push(ch+"");
			} else {
				String op2 = stack.pop();
				String op1 = stack.pop();
				stack.push(ch + op1 + op2);
			}
		}
		
		return stack.pop();
	}

}
