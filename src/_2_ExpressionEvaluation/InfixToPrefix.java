package _2_ExpressionEvaluation;

public class InfixToPrefix {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String expr = "a+b*(c^d-e)^(f+g*h)-i"; //abcd^e-fgh*+^*+i-
		String expr = "a+b*c";
		System.out.println("Infix:" + expr);
		String postFix = InfixToPostfix.convertToPostFix(expr);
		System.out.println("Postfix: " + postFix);
		
		String prefix = convertToPrefix(expr); // ++A*BCD
		System.out.println("Prefix:" + prefix);
	}

	private static String convertToPrefix(String expr) {
		String reversedExpr = reverseBrackets(reverseString(expr));
		
		String reversedPostfixExpr = InfixToPostfix.convertToPostFix(reversedExpr);
		return reverseString(reversedPostfixExpr);
	}

	private static String reverseBrackets(String expr) {
		char[] chArray = expr.toCharArray();
		for(int i=0; i<(chArray.length); i++){
			if(chArray[i] == ')' || chArray[i] == '(')
				chArray[i] = flipBracket(chArray[i]);
		}
		return String.valueOf(chArray);
	}

	private static char flipBracket(char ch) {
		if(ch == ')') return '(';
		else return ')';
	}

	private static String reverseString(String expr) {
		char[] chArray = expr.toCharArray();
		for(int i=0; i<(chArray.length/2); i++){
			char char1st = chArray[i];
			char char2nd = chArray[expr.length() - 1 - i];
			chArray[i] = char2nd;
			chArray[expr.length() - 1 - i] = char1st;
		}
		return String.valueOf(chArray);
	}
	
}
