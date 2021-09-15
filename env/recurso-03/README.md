### Executando Banco

Gerar a build da imagem:
```
sudo docker image build postgres -t r3-db
```
Criar e executar o container:
```
sudo docker run -d --name banco-postgres r3-db
```

### Executando Aplicação Web

Gerar a build da imagem:
```
sudo docker image build app -t r3-app
```
Criar e executar o container:
```
sudo docker run -d -p 8080:8080 --name app-jsf --link banco-postgres:host-banco r3-app
```

Você poderá acessar a aplicação web [aqui](http://localhost:8080/app/).


OBS: Caso haja alguma mudança, certifique-se de compilar o conteúdo no diretório `app` com o comando:
```
mvn clean package
```