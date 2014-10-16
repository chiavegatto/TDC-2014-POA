Feature: Feature para cadastro, visualizar, alterar e exclusão de abastecimentos 
	Como usuário do sistema Desempenho Mensal
	Desejo cadastrar, alterar, visualizar e excluir Abastecimentos 
	Então conseguirei realizar as operações na funcionalidade Abastecimentos.

Scenario: Cadastrar abastecimento com a data atual do sitema 
	Given Estou acessando a aplicação Desempenho Mensal 
	When Clico no menu "Adicionar Abastecimentos"
	And Preencho o abastecimento com os seguintes valores: 
		|KM Abastecimento|Quantidade Litros|Valor |
		|22950           |34.14            |106.86|
	And Clico no botão "Cadastrar" 
	Then Deve ser exibido a mensagem "Abastecimento cadastrado com sucesso." 
	
Scenario: Cadastrar abastecimento com a data diferente da data atual do sistema 
	Given Estou acessando a aplicação Desempenho Mensal 
	When Clico no menu "Adicionar Abastecimentos" 
	And Preencho o abastecimento com os seguintes valores: 
		|KM Abastecimento|Quantidade Litros|Valor |
		|22950           |34.14            |106.86|
	And Preencho a data do abastecimento com a data "08/10/2014" 
	And Clico no botão "Cadastrar" 
	Then Deve ser exibido a mensagem "Abastecimento cadastrado com sucesso." 

Scenario: Editar abastecimento cadastrado 
	Given Estou acessando a aplicação Desempenho Mensal 
	When Clico no menu "Adicionar Abastecimentos" 
	And Preencho o abastecimento com os seguintes valores: 
		|KM Abastecimento|Quantidade Litros|Valor |
		|25540           |44.15            |120.86|
	And Preencho a data do abastecimento com a data "08/10/2014" 
	And Clico no botão "Cadastrar" 
	Then Deve ser exibido a mensagem "Abastecimento cadastrado com sucesso." 
	When Clico no menu "Abastecimentos Cadastrados"
	And Pressiono com um click no abastecimento no KM abastecido "25540"
	Then Deve ser exibido as informações referente ao abastecimento:
		|KM Abastecimento|Quantidade Litros|Valor |Data Abastecimento|
		|25540           |44.15            |120.86|08/10/2014        | 
	When Altero o abastecimento com as seguintes informações: 
		|KM Abastecimento|Quantidade Litros|Valor |Data Abastecimento|
		|35540           |50.02            |140.32|15/10/2014        |
	And Clico no botão "Salvar"
	Then Deve ser exibido a mensagem "Abastecimento alterado com sucesso."

@Dev
Scenario: Excluir um abastecimento cadastrado 
	Given Estou acessando a aplicação Desempenho Mensal 
	When Clico no menu "Adicionar Abastecimentos" 
	And Preencho o abastecimento com os seguintes valores: 
		|KM Abastecimento|Quantidade Litros| Valor|
		|25540           |44.15            |120.86|
	And Clico no botão "Cadastrar" 
	Then Deve ser exibido a mensagem "Abastecimento cadastrado com sucesso." 
	When Clico no menu "Abastecimentos Cadastrados" 
	And Pressiono com um click longo no abastecimento no KM abastecido "25540" 
	Then Deve ser exibido um modal de confirmação da exclusão  
	When Clico no botão "Sim" 
	Then Deve ser exibido a mensagem "Abastecimento excluído com sucesso." 
	
#Scenario: Exibição apenas de abastecimentos do mês atual 
#	Given Estou acessando a aplicação Desempenho Mensal 
#	When Clico no menu "Adicionar Abastecimentos" 
#	And Preencho o abastecimento com os seguintes valores: 
#		|KM Abastecimento|Quantidade Litros|Valor |
#		|22950           |34.14            |106.86|
#	And Clico no botão "Cadastrar" 
#	Then Deve ser exibido a mensagem "Abastecimento cadastrado com sucesso."
#	And Deve ser exibido as seguintes informações no desempenho de abastecimento:
#	|KM Abastecimento|Quantidade Litros|Valor |Quantidade Abastecimentos|Quantidade KM|
#	|0               |34.14            |106.86|1 vez(es)/Mês            |0            |
	
	
	
																			