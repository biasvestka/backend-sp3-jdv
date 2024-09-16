# API Java para Gestão de Clientes e Empresas

## Descrição

Esta API foi desenvolvida em **Java 17** e permite realizar operações de CRUD (Criar, Ler, Atualizar, Deletar) em duas entidades: **Clientes** e **Empresas**. A aplicação está hospedada no **Azure App Service** e utiliza um **SQL Server** no Azure para armazenar os dados.

---

## Pré-requisitos

- Conta no [Azure](https://azure.microsoft.com/).
- Ferramenta de linha de comando **Azure CLI** instalada ([Instalar Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli)).
- Ambiente de desenvolvimento Java (JDK 17).
- Ferramenta de build Maven ou Gradle.
- Postman (ou outra ferramenta para testar APIs HTTP).

---

## Passos para Deploy no Azure

### 1. Criar Grupo de Recursos no Azure

```bash
## Faça login no Azure CLI:#
az login
## Criar Grupo de Recursos no Azure
az group create --name rg-anallyzersql --location brazilsouth
## Criar o SQL Server no Azure
az sql server create -l brazilsouth -g rg-anallyzersql -n sqlserver-rm551534 -u admsql -p anallyzer@Fiap2tds --enable-public-network true
## Criar o Banco de Dados SQL
az sql db create -g rg-anallyzersql -s sqlserver-rm551534 -n backend-sp3-jdv --service-objective Basic --backup-storage-redundancy Local --zone-redundant false
##  Configurar Regra de Firewall para o SQL Server
az sql server firewall-rule create -g rg-anallyzersql -s sqlserver-rm551534 -n AllowAll --start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255
## Crie um Azure App Service Plan:
az appservice plan create --name planAnallyzer --resource-group rg-anallyzersql --sku B1 --is-windows
## Crie o App Service para a API:
az webapp create --resource-group rg-anallyzersql --plan planAnallyzer --name anallyzer-sp3 --runtime "JAVA:17-java17"
## Configure as variáveis de ambiente da conexão com o banco de dados no App Service:
az webapp config appsettings set --resource-group rg-anallyzersql --name anallyzer-sp3 --settings "DB_URL=jdbc:sqlserver://sqlserver-rm551534.database.windows.net:1433;database=backend-sp3-jdv;user=admsql;password=anallyzer@Fiap2tds"
## Fazer o deploy da aplicação usando Git ou FTP. Aqui está um exemplo usando Git:
git remote add azure https://<DEPLOYMENT_USERNAME>@anallyzer-sp3.scm.azurewebsites.net/anallyzer-sp3.git
git push azure master
```

---

### 2. Testes da API 
  ## 1 - Criar Clientes (POST /clientes)
  POST /clientes
{
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "telefone": "11999999999"
}
## 2 - Listar Clintes (GET /clientes)
  GET /clientes
  {
    "id" : 1,
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "telefone": "11999999999"
}
## 3 - Atualizar Cliente (PUT /clientes/{id})
  PUT /clientes/1
{
    "nome": "João Silva Atualizado",
    "email": "joao.atualizado@example.com",
    "telefone": "11988888888"
}
## 4 - Deletar Cliente (DELETE /clientes/{id})
  DELETE /clientes/1


### Para Empresas (CRUD)
 ## 1 - Criar Empresa (POST /empresas)
 POST /empresas
{
    "nome": "Empresa XYZ",
    "cnpj": "12345678000100",
    "endereco": "Rua A, 123"
}
## 2 - Listar Empresas (GET /empresas)
[
  {
    "id": 1,
    "nome": "Empresa XYZ",
    "cnpj": "12345678000100",
    "endereco": "Rua A, 123"
  }
]
## 3 -  Atualizar Empresa (PUT /empresas/{id})
PUT /empresas/1
{
    "nome": "Empresa XYZ Atualizada",
    "cnpj": "12345678000100",
    "endereco": "Rua B, 456"
}

## 4 - Deletar Empresa (DELETE /empresas/{id})
  DELETE /empresas/1

  ---

### Conclusão
Essa API foi desenvolvida para fornecer um ambiente de fácil implementação e teste no Azure. Através das instruções e scripts fornecidos, a aplicação pode ser implantada e testada em um ambiente de nuvem, garantindo uma solução escalável e segura.

