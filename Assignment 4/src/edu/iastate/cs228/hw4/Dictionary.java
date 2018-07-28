package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Matthew Martin
 * 
 *         An application class
 */
public class Dictionary {
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File(args[0]);
		Scanner in = new Scanner(input);
		EntryTree<Character, String> tree = new EntryTree<>();
		String command;
		Character[] key  = null;
		
		while(in.hasNext()){
			
			command = in.next();

			
			String k = "";
			String v = "";
			String searchTerm = "";
			String prefixTerm = "";
			String removeTerm = "";
			
			if(command.equals("add")){
				k = in.next();
				v = in.next();
			}else if(command.equals("search")){
				searchTerm = in.next();
			}else if(command.equals("prefix")){
				prefixTerm = in.next();
			}else if(command.equals("remove")){
				removeTerm = in.next();
			}
			
			if(command.equals("showTree")){
				System.out.println("Command: " + command);
				System.out.println();
				System.out.println("Result from a showTree:");
				tree.showTree();
				System.out.println();
			}
			
			if(command.equals("add")){
				char[] temp = k.toCharArray();
				key = new Character[temp.length];
				for(int i = 0; i < temp.length; i++){
					key[i] = temp[i];
				}
				
				System.out.println("Command: " + command + " " + k + " " + v);
				boolean result = tree.add(key, v);
				System.out.println("Result from an " + command + ": " + result);
				System.out.println();
				k = "";
				v = "";
			}else if(command.equals("search")){
				char[] temp = searchTerm.toCharArray();
				key = new Character[temp.length];
				for(int i = 0; i < temp.length; i++){
					key[i] = temp[i];
				}
				
				System.out.println("Command: " + command + " " + searchTerm);
				String result = tree.search(key);
				System.out.println("Result from a " + command + ": " + result);
				System.out.println();
				searchTerm = "";
			}else if(command.equals("prefix")){
				char[] temp = prefixTerm.toCharArray();
				key = new Character[temp.length];
				for(int i = 0; i < temp.length; i++){
					key[i] = temp[i];
				}
				
				System.out.println("Command: " + command + " " + prefixTerm);
				Character[] result = tree.prefix(key);
				String r = "";
				if(result != null){
					for(int i = 0; i < result.length; i++){
						r = r + result[i];
					}
				}
				System.out.println("Result from a " + command + ": " + r);
				System.out.println();
				prefixTerm = "";
			}else if(command.equals("remove")){
				char[] temp = removeTerm.toCharArray();
				key = new Character[temp.length];
				for(int i = 0; i < temp.length; i++){
					key[i] = temp[i];
				}
				
				System.out.println("Command: " + command + " " + removeTerm);
				String result = tree.remove(key);
				System.out.println("Result from a " + command + ": " + result);
				System.out.println();
				removeTerm = "";
			}
		}
		
		in.close();
	}
}
