package Assign1;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assign12 {
	/* The regular expression is set up to match both valid identifiers and 
	 * text within quotes. The program manually exclude quoted strings returned
	 * from the match 
	 * */
	public static void processFile(BufferedReader Reader){
		HashMap validIdentifiers = new HashMap();
		String regex ="\"(.*?)\"|([a-z]+)([0-9]+)?([a-z]+)?|([A-Z]+[a-z]+)([0-9]+)?([a-zA-Z]+)?|([A-Z]+[0-9]+)([a-zA-z]+)"";
		Pattern regexPattern = Pattern.compile(regex);
		String line = null;
		Matcher regexMatcher = null;
		int identifierCounter = 0;
		try {
			while ( (line = Reader.readLine()) != null){
					regexMatcher = regexPattern.matcher(line);
				while(regexMatcher.find()){
					String matchFound = regexMatcher.group();
					if(matchFound.charAt(0) != '"'){
						if(!validIdentifiers.containsKey(matchFound)){
							validIdentifiers.put(matchFound, 1);
							identifierCounter ++;
						}	
					}	
				}
			}
			 Assign11.writeAnswerToFile(identifierCounter);
			//System.out.println("Number of Identifiers: " + identifierCounter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String fileName = "A1.tiny";
		try {
			/*TODO: ensure that the file name comes from the argument variable*/
			//System.out.println("filename: "+ args[0]);
			BufferedReader readerObject = new BufferedReader(new FileReader(fileName));
			processFile(readerObject);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
