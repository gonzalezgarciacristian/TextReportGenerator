package uniovi.cgg.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import uniovi.cgg.logic.models.Options;
import uniovi.cgg.logic.models.UseCase;
import uniovi.cgg.persistance.Persistance;

public class MainActions {

	public UseCase loadFile(File file) {
		UseCase useCase = new UseCase();		
		Persistance persistance = Persistance.getInstance();
		JSONArray json = persistance.stringToJSONArray(persistance.loadFileToString(file.toString()));

		List<Options> options = new ArrayList<Options>();
		for (int i = 0, length = json.size(); i < length; i++) {
			options.add(Persistance.getInstance().loadJSONToObject((JSONObject) json.get(i), useCase));
		}

		UseCase useCaseFinal = new UseCase(options);
		//System.out.println(useCaseFinal.toString());

		return useCaseFinal;
	}	

}