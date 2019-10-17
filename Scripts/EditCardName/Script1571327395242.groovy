import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import variables.MyVariables as MyVariables

//Seta nome desejado
String NomeCard = 'Teste%20DBServer'

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


RequestObject request = findTestObject('Object Repository/Put/PutCard')
String requestUrl = MyVariables.cards + getCard +'?name='+ NomeCard + MyVariables.apiKey + MyVariables.apiToken
//Monta a url para requisição na api de acordo com as variaveis informadas
request.setRestUrl(requestUrl)

//Enviando requisição
ResponseObject response1 = WS.sendRequest(request)

//Validando se o Status Code de retorno é 200
WS.verifyResponseStatusCode(response1, 200)


