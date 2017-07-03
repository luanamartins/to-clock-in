Descrição:

O processo consiste em criar um serviço REST no qual funcionários possam adicionar e consultar as suas batidas do ponto eletrônico.

Regras de uso:
    Os endpoints deverão ser protegidos por autenticação (BASIC);
    As batidas deverão estar agrupadas por dia;
    Deverá ser informado o montante de horas trabalhadas no dia e mês;
    Deverá ser informado se está faltando algum intervalo de descanso;

Regras de negócio:
  Pode-se considerar o PIS como identificador do usuário.
  Deverá ser checado se a batida já existe, só é permitido uma batida por minuto no sistema.

Deverá ser calculado os minutos trabalhados de acordo com regra:
  De segunda a sexta feira a cada 60 minutos trabalhados são contabilizados 60 minutos.
  Aos sábados a cada 60 minutos trabalhados são contabilizados 90 minutos.
  Aos domingos a cada 60 minutos trabalhados são contabilizados 120 minutos.
  Para trabalho realizado entre as 22:00 e 06:00 a cada 60 minutos trabalhados são contabilizados 72 minutos.

Deverá ser calculado se os intervalos de descanso foram respeitados de acordo com a regra:
  Abaixo de 4 horas trabalhas não é necessário descanso.
  Acima de 4 horas e abaixo de 6 horas trabalhadas é necessário um descanso mínimo de 15 minutos.
  Acima de 6 horas trabalhadas é necessário um descanso mínimo de 1 hora.

Regras de execução:
  O back-end deve ser escrito em JAVA;
  A persistência dos dados deve ser feita via um SGBD a sua escolha;
  O servidor de aplicação fica a sua escolha;

Critérios de avaliação:
Organização do código.
Organização dos commits.
O cumprimento de todas as regras estabelecidas.
A criação de testes.
A modelagem e persistência dos dados.

Observação:

Existe um cliente para fins de testes que envia os dados para servidor, ele está disponível em: http://github.com/inacionery/interviewclient
