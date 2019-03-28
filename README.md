# com.iec.trabalhoFinal.homeBroker
Trabalho final da disciplina Arquitetura com JavaEE - Curso: Arquitetura de sistemas distribuidos, oferta 10 

Instruções para execução

O trabalho foi desenvolvido utilizando a máquina virtual disponibilizada pelo professor em sala de aula. Desta forma, 
as versões do MongoDb e RabbitMQ utilizadas para testes são as mesmas disponibilizadas na máquina virtual.

Para executar os containers disponibilizados basta logar na máquina virtual e executar os comandos abaixo:

* docker run -p 27017:27017 --name mongodb -d mongo
* docker run -d --hostname rabbitmq --name rabbitmq-management -p 15672:15672 -p 5671:5671 -p 5672:5672 rabbitmq:management

Caso a execução seja em alguma outra máquina (máquina local por exemplo) será necessário a instalação de ambos os sistemas
e posterior configuração no código (instalações e configuração não serão tratadas neste projeto).

Foram utilizadas as bibliotecas listadas abaixo para implementação do sistema:

* springboot
* mongodb
* RabbitMQ
* Lombok
* Swagger
* javax.Mail

Configurações de Email

Para envio de email, o e-mail que irá ser utilizado para envio e sua senha deverão ser configurados no arquivo: application.properties
nos campos: emailFrom e emailPassword

Funcionalidades

O sistema controla a compra e venda de ações por acionistas. Por se tratar de um modelo conceitual, as ações que podem ser executadas
foram mantidas da forma mais simples possível.

É possível cadastrar empresas, cadastrar acionistas, gerar ações para venda, comprar ações disponiveis e vender ações já compradas.
O sistema não valida ações relativas a valores.

Um acionista pode comprar quaisquer ações disponíveis para venda e pode vender quaisquer ações que lhe pertençam.
Uma empresa pode disponibilizar mais ações no mercado, porem não pode retirar as que já foram disponibilizadas.

Para um primeiro uso do sistema, já é disponibilizado uma empresa cadastrada com 5 ações a venda e um acionista cadastrado,
descritos abaixo:

* Empresa
[descrever]

* Acionista
[descrever]

Controllers


