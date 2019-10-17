import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper
import variables.MyVariables as MyVariables
import java.io.FileInputStream as FileInputStream
import java.io.FileNotFoundException as FileNotFoundException
import java.io.IOException as IOException
import java.util.Date as Date
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.lang.String as String
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

String NomeBoard = 'QuadroTeste'

RequestObject request = findTestObject('Object Repository/Post/PostBoard')

String requestUrl = ((((MyVariables.boards + '?name=') + NomeBoard) + '&defaultLabels=true&defaultLists=true&keepFromSource=none&prefs_permissionLevel=private&prefs_voting=disabled&prefs_comments=members&prefs_invitations=members&prefs_selfJoin=true&prefs_cardCovers=true&prefs_background=blue&prefs_cardAging=regular') + 
MyVariables.apiKey) + MyVariables.apiToken

//Monta a url para requisição na api de acordo com as variaveis informadas
request.setRestUrl(requestUrl)

//Enviando requisição
ResponseObject response = WS.sendRequest(request)

//Validando se o Status Code de retorno é 200
WS.verifyResponseStatusCode(response, 200)

System.out.println(response.getResponseBodyContent())

String jsonString = response.getResponseBodyContent()

JsonSlurper slurper = new JsonSlurper()

Map parsedJson = slurper.parseText(jsonString)

String idBoard = parsedJson.id

WS.comment(idBoard)




