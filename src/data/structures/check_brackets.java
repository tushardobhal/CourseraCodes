package data.structures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

public class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int pos = 0;
        boolean flag = true;
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
            	opening_brackets_stack.add(new Bracket(next, position));
            }
            else{
                if (next == ')' || next == ']' || next == '}') {
                    // Process closing bracket, write your code here
                	if(opening_brackets_stack.empty()) {
                		pos = position;
                		flag = false;
                		break;
                	}
                	else {
                		Bracket top = opening_brackets_stack.pop();
                		if(!top.Match(next)) {
                			pos = position;
                			flag = false;
                			break;
                		}
                	}   
                }
            }
        }
        if(!opening_brackets_stack.empty() && pos == 0) {
        	pos = opening_brackets_stack.peek().position;
        }
        // Printing answer, write your code here
        if(flag && opening_brackets_stack.empty()) {
        	System.out.println("Success");
        }
        else {
        	System.out.println(pos + 1);
        }
    }
}
