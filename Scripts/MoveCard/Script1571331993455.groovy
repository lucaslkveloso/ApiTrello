import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import variables.MyVariables as MyVariables

ArrayList<String> listName = new ArrayList<String>();
listName.add("Backlog");
listName.add("ToDo");
listName.add("in Progress");
listName.add("Testing");
listName.add("Done");

for (x=0; x<=4; x++){
//Chama o objeto com o metodo GET
RequestObject requestObj = findTestObject('Object Repository/Get/GetCards')
String requestCards = MyVariables.boards + MyVariables.idBoard + '/cards/?limit=2&fields=name&members=true&member_fields=fullName'+ MyVariables.apiKey + MyVariables.apiToken

//Seta a url para requisição na api
requestObj.setRestUrl(requestCards)

//Enviando requisição
ResponseObject response = WS.sendRequest(requestObj)

//Validando se o Status Code de retorno é 200
WS.verifyResponseStatusCode(response, 200)

String jsonString  = response.getResponseBodyContent()
JsonSlurper slurper = new JsonSlurper()
ArrayList parsedJson = slurper.parseText(jsonString)
String getCard = parsedJson[0].id

//Buscar listas
RequestObject requestList = findTestObject('Object Repository/Get/GetList')
String requestUrlList = MyVariables.boards + MyVariables.idBoard + '/lists?cards=all&card_fields=all&filter=open&fields=all'+ MyVariables.apiKey + MyVariables.apiToken
System.out.println(requestUrlList)
//Seta a url para requisição na api
requestList.setRestUrl(requestUrlList)

//Enviando requisição
ResponseObject response0 = WS.sendRequest(requestList)

//Validando se o Status Code de retorno é 200
WS.verifyResponseStatusCode(response0, 200)

String jsonString1  = response0.getResponseBodyContent()
ArrayList parsedJson1 = slurper.parseText(jsonString1)
System.out.println(parsedJson1);
System.out.println(parsedJson1.size());

String idList = ""

for (i=0; i<parsedJson1.size(); i++) {
	if(parsedJson1[i].name == listName.get(x)) {
		idList = parsedJson1[i].id		
	}
}

RequestObject requestMoveCard = findTestObject('Object Repository/Put/PutCard')
String requestUrlMoveCard = MyVariables.cards + getCard +'?idList='+ idList + MyVariables.apiKey + MyVariables.apiToken
System.out.println(requestUrlMoveCard)

//Monta a url para requisição na api de acordo com as variaveis informadas
requestMoveCard.setRestUrl(requestUrlMoveCard)

//Enviando requisição
ResponseObject response1 = WS.sendRequest(requestMoveCard)

//Validando se o Status Code de retorno é 200
WS.verifyResponseStatusCode(response1, 200)
}

