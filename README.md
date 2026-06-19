# Autodrive

Passo a passo para configurar, executar e testar a aplicação localmente.

---

## Passo a Passo

### 1. Criar o Banco de Dados
Abra o cliente ou terminal do seu gerenciador de banco de dados MYSQL e crie um novo banco de dados para a aplicação.

Exemplo de comando SQL:
```sql
CREATE DATABASE IF NOT EXISTS autodrive;
```

### 2. Faça as alterações necessárias
Em autodrive/resources/application.properties, preencha seus dados respectivamente com:

```
spring.datasource.url= url_banco_de_dados
spring.datasource.username= seu_user
spring.datasource.password= sua_senha
```

### 3. Executar o programa
Após preencher os dados necessários, execute o arquivo AutodriveApplication e espera sua conclusão.

### 4. Realizar os testes HTTP
Tanto pode ser executado pelo arquivo teste.http quando pegando os códigos lá disponibilizados e testando por um programa externo
(Postman / Insomnia)