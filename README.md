# apifirst
Teste de api

#Docker
Para executar o docker, abra um terminal no diretório raiz.
Execute o comando: 
docker-compose up -d

Depois de a imagem e container estiver sendo executado, execute o comando:
winpty docker exec -it apifirst-db-1 mysql -p (no windows via Git Bash )
docker exec -it apifirst-db-1 mysql -p (no terminal linux)

No terminal do MySql, digite o comando:
mysql> CREATE DATABASE first_api;

Para sair do terminal do MySql, digite o comando:
mysql> quit
