"# avalpatterns" 

ALUNOS: CAUÃ WILL QUADROS SILVA DO SACRAMENTO, THIAGO QUADROS SILVA DO SACRAMENTO
------------------------------------------------------------------------------------------------------------------------------------------------------------
QUESTÃO 1

PADRÂO ESCOLHIDO: STRATEGY

PROBLEMA: A classe de autenticador possuí uma grande sequência de if-elses,
em que cada condicional refere-se a uma forma diferente de autenticar um documento de acordo com o cliente quer.

SOLUÇÃO: cada condicional torna-se um algoritimo diferente e cabe ao cliente decidir qual algoritimo ele quer utilizar.

PARTICIPANTES DO PADRÃO:

Context (AbstractGerenciadorDocumentosUI): 

- É a classe responsável por escolher a estratégia utilizada (que é repassada para a controller para que esta aplique a estratégia no autenticador). 

Strategy (AutenticadorStrategy):

- Define a interface utilizada por todos os algoritmos;

ConcreteStrategy (ConfidencialAutenticadorStrategy, CriminalAutenticadorStrategy, ...): 

- Implementa o algoritimo usando a interface de strategy.

------------------------------------------------------------------------------------------------------------------------------------------------------------

QUESTÃO  2

PADRÂO ESCOLHIDO: COMMAND 

PROBLEMA: A chamada de operações diretamente (como assinar()) não é desejável para o sistema, pois diversas requisitos estão sendo impostas para a execução dessas
operações, o que levaria a alteração do código em diversas partes do projeto, dificultando a manutenibilidade e escalabilidade.

SOLUÇÃO: O padrão Command encapsula essas requisições como objetos, permitindo parametrizar clientes com diferentes solicitações, enfileirar ou fazer o registro (log) de solicitações e suportar operações que podem ser desfeitas. Desta forma, o código fica centralizado, facilitando a manutenção e adição de novas funcionalidades.

PARTICIPANTES DO PADRÃO:

Command (Command, LoggerCommand):

- Declara a interface (ou comportamento padrão) para a execução de uma operação;

ConcreteCommand (CreateCommand, SignCommand, ...):

- Defini a vinculação entre os objetos receiver e uma ação, além de implementar a operação atráves das correspondentes em receiver;

Client (MyGerenciadorDocumentoUi):

- Cria o ConcreteCommand e estabelece o(s) receptor(es).

Invoker (MyGerenciadorDocumentoUi):

- Solicita ao Command a execução da solicitação;

Receiver (GerenciadorDocumentoModel, entre outros):

- São as classes que sabem como executar as operações e são passadas para os ConcreteCommands.

ADIÇÕES: COMPOSITE

- Utilizamos o padrão composite para a construção do macro commands. O padrão forneceu uma interface transparente para que os objetos folha (commands) e os nós intermediários fossem tratados da mesma forma pelos cliente.
