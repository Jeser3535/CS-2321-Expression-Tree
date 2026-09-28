package cs2321;
import net.datastructures.*;
/*
* Course: CS2321 Fall 2024
* Section: (sec01)
* Assignment: Tree
* Author: (Jesse Sergent)
* Email: (jtsergen@mtu.edu)
*/
public class ExpressionTree {
	
	//Given a binary tree associated with an arithmetic expressions with operators: +,-,*,/ and decimal numbers
	//Evaluate the result and return the result. You may assume the given tree is in correct form. 
	
	//Example 1:
	//    +
	//  /  \
	// 2  4.5
	//
	// (2+4.5) = 6.5
	// Expected output: 6.5
	
	
	//Example 2:
	//
	//        +
	//      /   \
	//    *       *
	//  /  \     /  \
	//  2   -   3   2
	//     / \
	//     5  1 
	//
	// ((2*(5-1))+(3*2)) = 14
	// Expected output: 14
	//
	// Use Double.parseDouble(string) to convert string to double. 
	// For example to convert string "6.5" to double 6.5. 
	//
	
	public static double eval(BinaryTree<String> tree) {
		if (tree.isEmpty()) return 0.0;
		return evalHelper(tree, tree.root());
	}
	
	private static double evalHelper(BinaryTree<String> t, Position<String> p) {
		String element = p.getElement();
		
		// leaf node → numeric value
		if (t.left(p) == null && t.right(p) == null) {
			return Double.parseDouble(element);
		}
		
		// recursive evaluation
		double leftVal = evalHelper(t, t.left(p));
		double rightVal = evalHelper(t, t.right(p));
		
		switch (element) {
			case "+": return leftVal + rightVal;
			case "-": return leftVal - rightVal;
			case "*": return leftVal * rightVal;
			case "/": return leftVal / rightVal;
			default: throw new IllegalArgumentException("Invalid operator: " + element);
		}
	}
	
	//Given a binary tree associated with an arithmetic expressions with operators: +,-,*,/ 
	//and decimal numbers or variables
	
	// Generate the expression with parenthesis around all sub expressions except the leave nodes.  
	// You may assume the given tree is in correct form. 
	// Example:
	//        +
	//      /   \
	//    *       *
	//  /  \     /  \
	//  2   -   3   b
	//     / \
	//     a  1 
	//
	// Expected output: ((2*(a-1))+(3*b)) 
	
	public static String toExpression(BinaryTree<String> tree) {
		if (tree.isEmpty()) return "";
		return toExprHelper(tree, tree.root());
	}
	
	private static String toExprHelper(BinaryTree<String> t, Position<String> p) {
		String element = p.getElement();
		
		// leaf node → variable or number
		if (t.left(p) == null && t.right(p) == null) {
			return element;
		}
		
		// recursive build with parentheses
		String leftExpr = toExprHelper(t, t.left(p));
		String rightExpr = toExprHelper(t, t.right(p));
		return "(" + leftExpr + element + rightExpr + ")";
	}
}
