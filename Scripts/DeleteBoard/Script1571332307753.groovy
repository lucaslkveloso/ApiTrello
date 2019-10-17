import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import variables.MyVariables as MyVariables


RequestObject request = findTestObject('Object Repository/Delete/DeleteBoard')
String requestUrl = MyVariables.boards + MyVariables.idBoard + MyVariables.apiKeyDelete + MyVariables.apiToken
//Monta a url para requisição na api de acordo com as variaveis informadas
request.setRestUrl(requestUrl)

//Enviando requisição
ResponseObject response = WS.sendRequest(request)

//Validando se o Status Code de retorno é 200
WS.verifyResponseStatusCode(response, 200)