import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import java.util.ArrayList;

import variables.MyVariables as MyVariables

ArrayList<String> listName = new ArrayList<String>();
listName.add("Backlog");
listName.add("ToDo");
listName.add("in%20Progress");
listName.add("Testing");
listName.add("Done");

for (i=0; i<=4; i++){

	String endpoint =  MyVariables.boards + MyVariables.idBoard +'/lists?name=' + listName.get(i) + '&pos=bottom'+ MyVariables.apiKey + MyVariables.apiToken
	
	System.out.println(endpoint)
	RequestObject request = findTestObject('Object Repository/Post/PostList')
	
	request.setRestUrl(endpoint)
	
	'Enviando requisição'
	ResponseObject response = WS.sendRequest(request)
	
	'Validando se o Status Code de retorno é 200'
	WS.verifyResponseStatusCode(response, 200)
}