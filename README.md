# Trabalho Prático

## Descrição
Este projeto é uma aplicação Java desenvolvida para gerenciar dados de funcionários, categorias e produtos, com uma interface gráfica para login. Utilizando Maven para gerenciar dependências, a aplicação é projetada para garantir a integridade dos dados por meio de validações específicas, como CPF inválido, strings vazias e números negativos. O sistema é ideal para pequenos negócios que desejam organizar e otimizar seu gerenciamento interno.

## Funcionalidades Principais
- Persistência de Dados: Gerencia a adição, remoção, atualização e leitura de informações sobre funcionários, categorias e produtos.
- Interface Gráfica com Swing: Fornece uma interface amigável para o login de usuários, construída utilizando a biblioteca Swing, facilitando a interação com a aplicação.
- Tratamento de Exceções: Implementa exceções personalizadas para garantir que dados inválidos não sejam processados, contribuindo para a robustez da aplicação.

## Estrutura do Projeto
- `src/main/java/trabalhoPratico/model`: Contém as classes de modelo.
  - `Funcionario.java`: Representa um funcionário.
  - `Categoria.java`: Representa uma categoria de produto.
  - `Produto.java`: Representa um produto.
  - `Cpf.java`: Valida um CPF.

- `src/main/java/trabalhoPratico/persistence`: Contém as classes de persistência.
  - `FuncionarioPersistence.java`: Gerencia a persistência de dados de funcionários.
  - `CategoriaPersistence.java`: Gerencia a persistência de dados de categorias.
  - `ProdutoPersistence.java`: Gerencia a persistência de dados de produtos.
  - `Archive.java`: Utilitário para leitura e escrita de arquivos.
  - `Persistence.java`: Gerencia a persistência de dados em diferentes classes de modelo.

- `src/main/java/trabalhoPratico/controller`: Contém as classes de controle.
  - `FazerLogin.java`: Gerencia a lógica de login.
  - `GerenciarCategoria.java`: Gerencia a lógica de categorias, incluindo adicionar, remover e editar categorias.
  - `GerenciarFuncionarios.java`: Gerencia a lógica de funcionários, incluindo adicionar, remover e editar funcionários.
  - `EnterToLogin.java`: Gerencia a ação de pressionar Enter para realizar o login.
  - `AdicionarCategoria.java`: Gerencia a lógica de adicionar uma nova categoria.
  - `AbrirTelaFuncionarios.java`: Gerencia a ação de abrir a tela de funcionários.
  - `AbrirInformacoesProduto.java`: Gerencia a ação de abrir informações de um produto.

- `src/main/java/trabalhoPratico/view`: Contém as classes de visualização.
  - `TelaLogin.java`: Interface gráfica para login de usuários.
  - `TelaFuncionario.java`: Interface gráfica para gerenciar funcionários.
  - `TelaTabelaProdutos.java`: Interface gráfica para exibir a tabela de produtos.
  - `TelaCategoria.java`: Interface gráfica para exibir e gerenciar categorias.

- `src/main/java/trabalhoPratico/exception`: Contém as classes de exceção.
  - `CpfException.java`: Exceção para CPF inválido.
  - `EmptyStrException.java`: Exceção para strings vazias.
  - `NegativeNumberException.java`: Exceção para números negativos.

## Instalação
Para instalar e configurar o ambiente, siga os passos abaixo:

1. Clone o repositório:
    ```bash
    git clone https://github.com/leo-prata/sistema-estoque
    cd ./sistema-estoque/
    ```

2. Instale as dependências usando Maven:
    ```bash
    mvn install
    ```


## Funcionalidades Adicionais
- Persistência de Funcionários: Adicionar, remover, atualizar e ler dados de funcionários.
- Persistência de Categorias: Adicionar, remover, atualizar e ler dados de categorias.
- Persistência de Produtos: Adicionar, remover, atualizar e ler dados de produtos.
- Interface de Login: Interface gráfica para login de usuários, construída com a biblioteca Swing.
- Tratamento de Exceções: Lida com exceções específicas como CPF inválido, strings vazias e números negativos.

### Exceções
- CpfException: Lançada quando um CPF inválido é fornecido.
- EmptyStrException: Lançada quando uma string vazia é fornecida.
- NegativeNumberException: Lançada quando um número negativo é fornecido.
