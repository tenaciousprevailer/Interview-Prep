package _2_ExpressionEvaluation;

import java.util.Stack;

public class PostfixToInfix {

	public static void main(String[] args) {
		String postfix = "abcd^e-fgh*+^*+i-"; // infix a+b*(c^d-e)^(f+g*h)-i
		System.out.println("Postfix: " + postfix);

		String inFix = convertToInFix(postfix);
		System.out.println("infix: " + inFix);
	}

	private static String convertToInFix(String postfix) {

		Stack<String> stack = new Stack<>();
		for(int i = 0; i<postfix.length();i++) {
			char ch = postfix.charAt(i);
			if(Character.isLetterOrDigit(ch)) {
				stack.add(String.valueOf(ch));
			} else {
				String operator2 = stack.pop();
				String operator1 = stack.pop();
				String tmp = "(" +  operator1 + ch +  operator2 + ")";
				stack.add(tmp);
			}
		}
		return stack.pop();
	}

}
