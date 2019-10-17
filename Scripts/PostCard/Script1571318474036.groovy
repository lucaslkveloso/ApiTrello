import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import variables.MyVariables as MyVariables

RequestObject request = findTestObject('Object Repository/Get/GetList')
String requestUrl = 'https://api.trello.com/1/boards/' + MyVariables.idBoard +'/lists?cards=none&card_fields=all&filter=open&fields=all' + MyVariables.apiKey + MyVariables.apiToken
//Monta a url para requisição na api de acordo com as variaveis informadas
System.out.println(requestUrl)
request.setRestUrl(requestUrl)

//Enviando requisição
ResponseObject response = WS.sendRequest(request)

//Validando se o Status Code de retorno é 200
WS.verifyResponseStatusCode(response, 200)

//Recebe o showtime id do Json de acordo com a posição desejada

System.out.println(response.getResponseBodyContent())
String jsonString  = response.getResponseBodyContent()
JsonSlurper slurper = new JsonSlurper()
ArrayList parsedJson = slurper.parseText(jsonString)
String listBacklog = parsedJson[0].id

String endpoint =  'https://api.trello.com/1/cards?name='+ 'Teste' +'&idList='+ listBacklog + '&keepFromSource=all' + MyVariables.apiKey + MyVariables.apiToken

System.out.println(endpoint)
RequestObject newrequest = findTestObject('Object Repository/Post/PostCard')

newrequest.setRestUrl(endpoint)

'Enviando requisição'
ResponseObject response1 = WS.sendRequest(newrequest)

'Validando se o Status Code de retorno é 200'
WS.verifyResponseStatusCode(response1, 200)



