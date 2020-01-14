# ApiTrello
Automação da api do trello efetuada com Katalon
Para utilizar a api basta acessar a página https://developers.trello.com/reference/#introduction e seguir as instruções

##### Instruções para utilizações do projeto abaixo:
- Efetuar o download do Katalon Studio https://www.katalon.com
- Clonar o repositório
- Abrir o repositorio em File> Open Project
- Gerar ApiKey através da api do trello https://trello.com/app-key
- Gerar o Token através seguindo instruções da mesma página
- No projeto abrir a pasta Keywords > variables > MyVariables
- *Incluir os tokens gerados na string após o **&key=**, **?key=** e **&token=**
- Para executar os testes você pode criar um quadro manualmente no Trello e copiar o id da url, incluindo o id no arquivo MyVariables em **idBoard**
- Você também pode criar um novo quadro rodando o arquivo Test Cases/CreateBoard. Após rodar o arquivo pegar o id que será exibido no **Log Viewer**, copiar o id e colar no arquivo MyVariables
- Para executar os damais testes abrir a pasta Test Suits, abrir e executar o arquivo RUN

*Essa etapa é fundamental pois os Tokens do projeto não  são reais.
