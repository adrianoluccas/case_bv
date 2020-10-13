# case_bv - Teste Técnico Maquina de Snacks
A API desenvolvida em SpringBoot.

-IDE para desenvolvimento: STS4 (SpringToolSuite)
-Banco de dados: MariaDB

#Descrição
O projeto foi desenvolvido, pensando na utilização de serviços disponíveis na nuvem. Dessa forma o dispositivo que fará a interface com o usuário poderá ter diversas características, de acordo com a máquina escolhida pelo comprador.
Neste case, está administrando a quantidade de produtos e dinheiro que possui dentro da máquina.
Toda movimentação financeira é registrada.

#Conceito
Para a maquina de Snacks, será necessário definir uma tecnologia de front-end (sugiro algum dispositivo como tablet, ou até mesmo um frontend que possa executar em um computador de baixa capacidade, ex: Raspberry). Para função administrativa, pode-se pensar em utilizar tecnologias web, para acesso direto pelo navegador.
A maquina terá um id definido e poderá inserir as quantidades de produtos e moedas de acordo com os dispositivos que estão conectados.
No caso desse exemplo, não foi incluído as permissões de acesso nem autenticação. Sugiro que utilize o oAuth2. Dessa forma não terá impactos quando for necessário escalar a infraestrutura de backend.
Não foi realizado a implementação de fila (RabbitMQ), por ocorrer a movimentação financeira no instante da compra. Esse cenario no caso deveria ser melhor estudado, pois corremos o risco dos dados não ficarem atualizados com a base de dados.

#EXECUTAR O PROJETO.
-Realizar o download o STS4 através do site: https://spring.io/tools
-Instalar MariaDB através do site: https://mariadb.org/download/
-Fazer o import do banco de dados através do arquivo dump.sql
-importar o projeto como Maven dentro do STS.
-executar a classe SnackApplication.java