package _2_ExpressionEvaluation;

import java.util.Stack;

public class InfixToPostfix {
	
	// a+b*c => abc*+
	// a*b+c => ab*c+
	// a*(b+c) => abc+*
	public static void main(String[] args) {
		//String infix = "a+b*(c^d-e)^(f+g*h)-i";
		String infix = "a*(b+c)";
		System.out.println("Infix: " + infix);
		String postfix = convertToPostFix(infix);
		System.out.println("Postfix: " + postfix);
	}

	public static String convertToPostFix(String exp) {
		StringBuilder result = new StringBuilder();
		char[] charArr = exp.toCharArray();
		Stack<Character> stack = new Stack<>();
		for(char c : charArr) {
			
			if(Character.isLetterOrDigit(c)) {
				result.append(c);
			} else if(c == '(') {
				stack.add(c);
			} else if(c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					//result.append(stack.pop());
					result.append(stack.pop());
				if (!stack.isEmpty() && stack.peek() != '(')
					return ("Invalid expression.");
				else
					stack.pop();				
			} else {
				// its operator
				while(stack.size()>0 && Prec(c) <= Prec(stack.peek()))
					result.append(stack.pop());
				stack.push(c);
			}			
		}
		
		while(stack.size()>0)
			result.append(stack.pop());
		
		return result.toString();
	}
	
	public static int Prec(char ch)
    {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }

}
