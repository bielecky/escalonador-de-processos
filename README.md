# Escalonador de Processos em Java

## Introdução

Este projeto apresenta a implementação de um escalonador de processos em Java, utilizando a biblioteca Swing para criar uma interface gráfica interativa. O escalonador é capaz de executar quatro algoritmos de escalonamento: 

- **First-Come, First-Served (FCFS)**
- **Round-Robin**
- **Shortest Job First (SJF)**
- **Escalonamento por Prioridade**

## Descrição do Projeto

O escalonador foi projetado para gerenciar e executar processos em um ambiente simulado, onde cada processo tem um identificador, tempo de execução, prioridade e um valor de quantum (para o algoritmo Round-Robin). A interface gráfica permite que os usuários adicionem processos e escolham o algoritmo de escalonamento desejado.

### Funcionalidades

- Adição de processos através da interface gráfica.
- Execução dos processos com diferentes algoritmos de escalonamento.
- Exibição do status de cada processo durante a execução, incluindo se estão em execução ou finalizados.

## Tecnologias Utilizadas

- **Java:** Linguagem de programação para a implementação do escalonador.
- **Swing:** Biblioteca para construção da interface gráfica.
- **Git:** Sistema de controle de versão utilizado para gerenciar o código-fonte.

## Instruções para Execução

```bash
1. Clone o repositório:
   git clone https://github.com/bielecky/escalonador-de-processos.git

2. Navegue até o diretório do projeto:
   cd escalonador-de-processos

3. Compile o projeto:
   javac Main.java

4. Execute o programa:
   java Main
```

## Contribuições
Este projeto foi desenvolvido em colaboração com minha colega de turma, Maria Eduarda de Souza, como parte de um trabalho acadêmico. Agradecemos qualquer feedback ou contribuição para melhorar o projeto!
