package Assign1;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Assign11 {
	
	public static boolean isValidIdentifier(String text){
		
		//Case 1: single non-digit character
		if(text.length() == 1 && Character.isDigit(text.charAt(0))){
			return false;
		}
		// case 2: words that are all caps are not identifiers with no numbers are keywords
		else if(isKeyword(text)){
			return false;
		}
		// case 3: if word is all caps and it does not contain a number 
		else if(isKeyword(text) && !containsDigit(text)){
			return false;
		}
		// case 4: words that start with a digit 
		else if(Character.isDigit(text.charAt(0))){
			return false;
		}
		// case 5: quoted strings
		else if(text.charAt(0) == '"'){
			return false;
		}
		// case 6: word is commented i.e if it starts with // or /* 
		else if(text.charAt(0) == '/' || (text.charAt(0) == '/' && text.charAt(1) == '/')||(text.charAt(0) == '/' && text.charAt(1) == '*') ){
			return false;
		}
		return true;
	}
	
	public static boolean isKeyword(String input){
		//boolean allCaps;
		
		for(int i = 0; i<input.length(); i++){
			if(!Character.isUpperCase(input.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	/*Checks a string to ensure it contains a number and 
	 * the number is not the first character in the string.
	 * 
	 * Words that begin with a digit is not a valid identifier
	 * */
	public static boolean containsDigit(String input){
		
		for(int i = 0; i<input.length(); i++){
			if(Character.isDigit(input.charAt(i)) && i!= 0){
				return true;
			}
		}
		return false;
	}
	
	/*This function validates each word in the file checking for valid identifiers*/
	public static void processFile(BufferedReader Reader){
		String line = null;
		String Delimeters = ";:()+-*/:===!=, ";
		HashMap identifierMap = new HashMap();
		int numberOfIdentifiers = 0;
		try {
			while ( (line = Reader.readLine()) != null){
				StringTokenizer tokens = new StringTokenizer(line, Delimeters);
				while(tokens.hasMoreTokens()){
					String wordFromLine = tokens.nextToken();	
					if(isValidIdentifier(wordFromLine)){
						// the hashmap helps count each identifier uniquely
						if(!(identifierMap.containsKey(wordFromLine))){
							identifierMap.put(wordFromLine, 1);
							numberOfIdentifiers ++;
						}
					}
				}
			}
			  writeAnswerToFile(numberOfIdentifiers); // because the filewriter also uses try catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeAnswerToFile(int fileContent) throws IOException{
		String content = "identifiers:"+fileContent;
		try {
			BufferedWriter writerObject = new BufferedWriter(new FileWriter("A1.output"));
			writerObject.write(content);
			writerObject.close();
			//System.out.println("File content: "+ content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static int getNumberOfIdentifiers(HashMap storage){
		int sumOfFlags = 0;
		for(Object identifiers: storage.keySet()){
//				System.out.print("key: "+ (String)identifiers + " Value: " + (int) storage.get(identifiers));
//				System.out.println();
				int hashMapValues = (int) storage.get(identifiers);
				sumOfFlags += hashMapValues;
		}
		
		return sumOfFlags;
	}
	public static void main(String[] args) {
		try {
			/*TODO: ensure that the file name comes from the argument variable*/
			//System.out.println("filename: "+ args[0]);
			BufferedReader readerObject = new BufferedReader(new FileReader(args[0]));
			processFile(readerObject);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
