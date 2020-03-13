package uniovi.cgg.persistance;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Persitance {

	private static final String FOLDER = "../bbdd";
	private static final String NAME = "bbdd.json";
	private static final String FILE = FOLDER + File.separator + NAME;

	/**
	 * Si solo se pone /, la carpeta la crear� en la ra�z de la unidad, con ../ la
	 * crea en el directorio superior
	 * 
	 * @param folder
	 */
	private void createFolder(String folder) {
		new File(folder).mkdirs();
	}

	/**
	 * Solo crea el fichero y su carpeta la primera vez, es decir, en caso de que no
	 * exista. Si el fichero va dentro de una carpeta, hay que crear esa carpeta
	 * primero o fallar�
	 */
	private void createFile() {
		// To create the file you need to create the parent directories first
		createFolder(FOLDER);

		File file = new File(FILE);

		try {
			if (file.createNewFile()) {
				System.out.println(FILE + " created");
			} else {
				System.out.println(FILE + " already exists");
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	private void saveFile(String file, String data) {
		FileWriter outputFile = null;

		try {
			outputFile = new FileWriter(file);
			outputFile.write(data);
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				outputFile.close();
			} catch (IOException e1) {
				System.out.println(e1);
				e1.printStackTrace();
			}
		}
	}

	private JSONObject loadFileToJSON(String file) {
		Reader inputFile = null;

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			inputFile = new FileReader(file);
			jsonObject = (JSONObject) parser.parse(inputFile);
		} catch (ParseException e) {
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				inputFile.close();
			} catch (IOException e1) {
				System.out.println(e1);
				e1.printStackTrace();
			}
		}
		
		return jsonObject;
	}

	public static void main(String[] args) {

		Persitance main = new Persitance();

		main.createFile();

		JSONObject obj = new JSONObject();
		obj.put("company", "UniOvi");
		obj.put("company2", "MDE Research Group");

		JSONArray list = new JSONArray();
		list.add("UniOvi");
		list.add("peque�a");
		list.add("10 empleados");
		list.add("educaci�n");

		obj.put("messages", list);

		main.saveFile(FILE, obj.toJSONString());
		
		JSONObject json = main.loadFileToJSON(FILE);
		
		System.out.println(json);

		System.out.print(obj);

	}

}
