package Assign1;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class A11 {
	
	public static boolean isValidIdentifier(String text){
		//System.out.println("Input: " + text + " IsEmpty: " + text.isEmpty());
		// case 1: if its a keyword its false
		if(isKeyword(text)){
			return false;
		} 	
		// checks if the string contains a digit and the digit is not the first character
		else if(containsDigit(text)){
			return true;
		}
//		// case 3: // if it contains characters like "@,),("
//		else if(containsSymbol(text)){
//			return false;
//		}
		// case 4: single characters like x, y, z 
		else if(text.length() == 1 && !Character.isDigit(text.charAt(0))){
			return true;
		}
		// case 5: just normal words like "abc" or "john" or "JOHN"
		else if(text.length() > 1 && !text.isEmpty()){
			return true;
		}
		else{
			return false;
		}	
	}
	
	public static boolean containsSymbol(String Input){
		Character[] KeyboardSymbols = {'!','@','#','$','%','^','&','*','(',')','-','+','_','+','{','}','[',']'};
		
		for(int x = 0; x<Input.length(); x++){
			// if the input string has any character in the symbol array
			if(Arrays.asList(KeyboardSymbols).contains(Input.charAt(x))){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isKeyword(String input){
		ArrayList keyWords = new ArrayList<String>();
		keyWords.add("IF");
		keyWords.add("READ");
		keyWords.add("ELSE");
		keyWords.add("RETURN");
		keyWords.add("BEGIN");
		keyWords.add("END");
		keyWords.add("MAIN");
		keyWords.add("STRING");
		keyWords.add("INT");
		keyWords.add("REAL");
		keyWords.add("WRITE");
		
		if((keyWords.indexOf((String)input)) != -1){
			return true;
		}
		return false;
	}
	
	public static boolean containsDigit(String input){
		
		for(int i = 0; i<input.length(); i++){
			if(Character.isDigit(input.charAt(i)) && i!= 0){
				return true;
			}
		}
		return false;
	}
	
//	public static void printCharacters(StringBuilder test){
//		System.out.println("Current Characters: ");
//		for(int k =0; k<test.length(); k++){
//			System.out.println("Index: " + k + " Char: " + test.charAt(k));
//		}
//	}
	
	public static String removeQuotedString(String text){
		StringBuilder newString = new StringBuilder(text);
		String returnString = "";
		boolean openQuote= false, closeQuote = false;
		int startIndex =0, endIndex =0;
		for(int x =0; x<newString.length(); x++){
			//System.out.println("In for loop");
			//System.out.println("index " + x + " char At index: " + newString.charAt(x));
			if(newString.charAt(x) == '"'){ // can be close or end
				if(!openQuote){
					openQuote = true;
					startIndex = x;
				}
				else if(!closeQuote && openQuote){
					endIndex = x+1;
					closeQuote = true;
				}
				if(openQuote && closeQuote){
					//System.out.println("Start Index: " + startIndex + " End Index: " + endIndex);
					newString.delete(startIndex, endIndex);
					closeQuote = false;
					openQuote = false;
					x = 0; // reset the index because deleting reorders the indices
				}	
			}
		}
		
		//System.out.println("Formatted string: " + newString.toString());
		returnString = newString.toString();
		return returnString;
	}
	
	public static void processFile(BufferedReader Reader){
		String line = null;
		String formattedString;
		String Delimeters = ";:()+-*/:===!=,&#%_ ";
		HashMap identifierMap = new HashMap();
		int numberOfIdentifiers = 0;
		try {
			while ( (line = Reader.readLine()) != null){
				formattedString = removeQuotedString(line);
				StringTokenizer tokens = new StringTokenizer(formattedString, Delimeters);
				while(tokens.hasMoreTokens()){
					String wordFromLine = tokens.nextToken();	
					if(isValidIdentifier(wordFromLine)){
						System.out.println("identifiers: " + wordFromLine);
						if(!(identifierMap.containsKey(wordFromLine))){
							identifierMap.put(wordFromLine, 1);
							numberOfIdentifiers ++;
						}
					}
				}
			}
			System.out.println("Number of Identifiers: " + numberOfIdentifiers);
			//writeAnswerToFile(numberOfIdentifiers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeAnswerToFile(int fileContent) throws IOException{
		String content = "identifiers:"+fileContent;
		try {
			BufferedWriter writerObject = new BufferedWriter(new FileWriter("A1.output"));
			writerObject.write(content);
			writerObject.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String fileName = "A1.tiny";
		try {
			BufferedReader readerObject = new BufferedReader(new FileReader(fileName));
			processFile(readerObject);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
