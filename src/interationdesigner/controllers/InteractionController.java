/**
 * Used to control the creation and management of an
 * interaction system. 
 * Connect two actions, check if two actions can be connected,
 * automatically add branch actions after conditional actions
 */

package interactiondesigner.controllers;

import interactiondesigner.models.InteractionTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import interactiondesigner.InteractionDesigner;
import interactiondesigner.models.*;

public class InteractionController{

	String filepath;

	InteractionTable interactionTable;
	Interaction interaction;

	/**
	 * A null filepath signifies that a new file is being created
	 */
	public InteractionController(){				
	}

	/**
	 * Load the file. A null file signifies a new file
	 */
	public void load(String filepath) throws FileNotFoundException, IOException{
		if(filepath == null){
			this.interaction = new Interaction(null);
			this.interactionTable = new InteractionTable(null);
			return;
		}	
		this.filepath = filepath;
		File file = new File(filepath);
		Scanner input = new Scanner(file);
		String delim = InteractionDesigner.DELIM;
		this.interaction = new Interaction(null);

		String metadata = input.nextLine();//this line contains the metadata
		HashMap<Integer, Integer[]> table = new HashMap<>();
		while(input.hasNextLine()){
			String line = input.nextLine();
			String[] data = line.split(delim);
			Action action;
			final String name = data[1];
			final int id = Integer.parseInt(data[0]);
			final int propertyCount = Integer.parseInt(data[2]);
			final HashMap<String,Object> props = new HashMap<>();		

			for(int i = 0; i < propertyCount;i++){ // read the properties
				String key = data[3+i*2];
				String value = data[4+i*2];
				props.put(key, value);				
			}

			action = new Action(){ // create the action
				public void initProperties(){
					for(String key : props.keySet()){
						props.put(key, props.get(key));
					}
				}

				public String getName(){
					return name;
				}
			};
			interaction.add(action);

			int conStart = propertyCount*2 + 3; // starting index for connections
			int connectionCount = Integer.parseInt(data[conStart]);
			Integer[] cons = new Integer[connectionCount];
			for(int i = 0; i < connectionCount;i++){
				cons[i] = Integer.parseInt(data[i + conStart + 1]);
			}
			table.put(id, cons); // add to the interaction table
		}

		this.interactionTable = new InteractionTable(table); // create the interaction table container
		
	}

	public void addAction(Action action){
		interaction.add(action);
	}

	public Action getAction(int id){
		return interaction.get(id);
	}

	public Set<Integer> getActionIds(){
		return interaction.idSet();
	}

	/**
	 * Saves a file at <code>path</code> with the following format
	 * META, 1, version, xx.xx.xx, name, abc
	 * action name, action id, property count, <properties>, destination count, <destinations>
	 * @return true on success
	 */
	public boolean save(String path) throws IOException{				
		if(path == null){
			path = this.filepath;
		}
		File file = new File(path);
		if(this.filepath == null || !this.filepath.equals(path)){
			file.createNewFile();
		}
		String delim = InteractionDesigner.DELIM;
		FileWriter writer = new FileWriter(file);
		String meta = "META"+delim+"1"+delim+"version"+delim+InteractionDesigner.VERSION_STRING + delim + "name" + delim + interaction.name;
		writer.write(meta+"\n");
		for(Integer id: this.interaction.idSet()){
			writer.write(interaction.get(id).propertyString()+"");
			Integer[] destinations =  this.interactionTable.getDestinations(id);
			if(destinations == null){
				writer.write(delim + "0");
			}else{
				writer.write(delim+ destinations.length);
				for(Integer dest : destinations){
					writer.write(delim + dest.intValue());
				}
			}
			writer.write("\n");
		}
		writer.close();
		return true;
	}
}