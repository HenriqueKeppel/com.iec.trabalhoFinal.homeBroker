# com.iec.trabalhoFinal.homeBroker
Trabalho final da disciplina Arquitetura com JavaEE - Curso: Arquitetura de sistemas distribuidos, oferta 10 

** Instruções para execução **

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

É possível cadastrar empresas, cadastrar acionistas, gerar ações para venda, comprar ações disponiveis e vender ações já compradas e
disponibilizadas para compra novamente. O sistema não realiza validação de valores.

Um acionista pode comprar quaisquer ações disponíveis para venda e pode vender quaisquer ações que lhe pertençam.
Uma empresa pode disponibilizar mais ações no mercado, porem não pode retirar as que já foram disponibilizadas.

Para um primeiro uso do sistema, já é disponibilizado uma empresa cadastrada com 5 ações a venda e um acionista cadastrado,
descritos abaixo:

* Empresa
id: 123456789
name: AWS
ativo: true

* Acionista
id: gerado automaticamente
name: Exemplo
ativo: true
email: emailExample@mail.com

Controllers

Em cada um dos controllers existem funções basicas de CRUD. As demais serão descritas neste documento:

** Empresa controller **
Apenas funções de CRUD

** Acionista controller **
Apenas funcções de CRUD

** Acao controller **
As funções de CRUD básico não serão descritas neste documento.

* GET: /api/v1/acoes/acionista/{acionistaId} Lista todas as ações pertencentes ao acionista, a venda ou não
* GET: /api/v1/acoes/empresa/{empresaId} Lista todas as ações de uma empresa, independente do acionista proprietario e se esta a venda ou nao
* POST: /api/v1/acoes/createRange/{qtd} Cria N ações conforme objeto (json) enviado no corpo do request
* PATCH /api/v1/acoes/acionista/{acionistaId}/empresa/{empresaId}/vender/{qtd} Coloca a venda a quantidade de ações descritas
no parametro {qtd} que sejam de propriedade do acionista descrito em {acionistaId} e da empresa descrita em {empresaId}
* PATCH /api/v1/acoes/acionista/{acionistaId}/empresa/{empresaId}/comprar/{qtd} Realiza solicitação de compra de N ações
(quantidade descrita pelo parametro {qtd}) que pertençam a empresa descrita pelo parametro {empresaId} em nome do acionista
descrito pelo parametro {acionistaId}

Os dois métodos PATCH deste controller são os métodos que realizam compra e venda de ações no sistema.

