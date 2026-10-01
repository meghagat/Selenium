package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class DataReader {
	
	public void getJsonDataToMap(String filePath) throws IOException 
	{
		//Read json to string
		
		String jsonContent = FileUtils.readFileToString(
			    new File(System.getProperty("user.dir") + "//src//test//java//Data//PurchaseOrder.json"),
			    StandardCharsets.UTF_8
			);	
		
		//Convert json to HashMap
		ObjectMapper mapper = new ObjectMapper();

		
		List<HashMap<String, String>> data =
			    mapper.readValue(
			        jsonContent,
			        new TypeReference<List<HashMap<String, String>>>() {}
			    );
		
	}

}
