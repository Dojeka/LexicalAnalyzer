import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static String nextWord = "";
    public static String charClass = "";
    public static char[] lexeme = new char[100];
    public static Token nextToken;
    public enum Token {
       KEYWORD, INT_LIT, STRING_LIT, IDENT, ASSIGN_OP, ADD_OP, SUB_OP, MULT_OP, DIV_OP, LEFT_PAREN, RIGHT_PAREN, PUNCTUATOR, EOF;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Analyze Python[1] or Java[2] file? Type 1 or 2. 0 to exit: ");
            choice = sc.nextInt();

            String pyFile = "src/inputPy.txt";
            String jaFile = "src/InputJa.txt";

            String filePath = (choice == 1) ? pyFile : (choice == 2) ? jaFile : null;

            if (filePath != null) {
                try (Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
                    while (fileScanner.hasNext()) {
                        nextWord = "";
                        charClass = "";
                        nextToken = null;

                        String word = fileScanner.next();
                        lexeme = word.toCharArray();

                        parseWord();

                        switch (nextWord){
                            case "def":
                                nextToken = Token.KEYWORD;
                                break;

                            case "print":
                                nextToken = Token.KEYWORD;
                                break;

                            case "public":
                                nextToken = Token.KEYWORD;
                                break;

                            case "class":
                                nextToken = Token.KEYWORD;
                                break;

                            case "static":
                                nextToken = Token.KEYWORD;
                                break;

                            case "void":
                                nextToken = Token.KEYWORD;
                                break;

                            case "main":
                                nextToken = Token.KEYWORD;
                                break;

                            case "String":
                                nextToken = Token.KEYWORD;
                                break;

                            case "args":
                                nextToken = Token.KEYWORD;
                                break;

                            case "int":
                                nextToken = Token.KEYWORD;
                                break;

                            case "System":
                                nextToken = Token.KEYWORD;
                                break;

                            case "out":
                                nextToken = Token.KEYWORD;
                                break;

                            case "println":
                                nextToken = Token.KEYWORD;
                                break;

                            default:
                                break;
                        }

                        if (charClass.equals("Letter") || charClass.equals("Digit")) {
                            System.out.println("Lexeme: " + nextWord + "      Token: " + nextToken + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }while(choice != 0);
    }

//    public static void readWord(String filePath){
//        String word;
//        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
//            while (scanner.hasNext()) {
//                nextWord = "";
//                word = scanner.next();
//                lexeme = word.toCharArray();
//                parseWord();
//
//                if (charClass.equals("Letter") || charClass.equals("Digit")) {
//                    System.out.println("Lexeme: " + nextWord + "      Token: " + nextToken + "\n");
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void parseWord(){
        String word = new String(lexeme);

        if(word.startsWith("\"") || word.endsWith("\"") && word.length() >=2){
            nextToken = Token.STRING_LIT;
            System.out.println("Lexeme: " + word + "      Token: "  + nextToken + "\n");
            return;
        }

        for (char c : lexeme) {
            if (Character.isLetter(c)) {
                nextWord += c;
                charClass = "Letter";
            } else if (Character.isDigit(c)) {
                nextWord += c;
                charClass = "Digit";
            } else if (!Character.isWhitespace(c)) {
                System.out.print("Lexeme: " + c + "         ");
                System.out.print("Token: " + lookUp(c) + "\n");
            }
        }

        if (charClass.equals("Letter")) {
            nextToken = Token.IDENT;
        } else if (charClass.equals("Digit")) {
            nextToken = Token.INT_LIT;
        }
    }

    public static Token lookUp(char ch) {
        switch (ch){
            case '(':
                nextToken = Token.LEFT_PAREN;
                break;

            case ')':
                nextToken = Token.RIGHT_PAREN;
                break;
            case '{':
                nextToken = Token.LEFT_PAREN;
                break;

            case '}':
                nextToken = Token.RIGHT_PAREN;
                break;

            case ']':
                nextToken = Token.RIGHT_PAREN;
                break;

            case '[':
                nextToken = Token.RIGHT_PAREN;
                break;

            case '+':
                nextToken = Token.ADD_OP;
                break;

            case '-':
                nextToken = Token.SUB_OP;
                break;

            case '*':
                nextToken = Token.MULT_OP;
                break;

            case '/':
                nextToken = Token.DIV_OP;
                break;

            case '=':
                nextToken = Token.ASSIGN_OP;
                break;

            case ',':
                nextToken = Token.PUNCTUATOR;
                break;

            case ':' :
                nextToken = Token.PUNCTUATOR;
                break;

             case ';':
                 nextToken = Token.PUNCTUATOR;
                 break;

            case '\"':
                nextToken = Token.PUNCTUATOR;
                break;

            case '_':
                nextToken = Token.PUNCTUATOR;
                break;

            case '.':
                nextToken = Token.PUNCTUATOR;
                break;

            case '\'':
                nextToken = Token.PUNCTUATOR;
                break;

            default:
                nextToken = Token.EOF;
                break;
        }
        return nextToken;
    }
}