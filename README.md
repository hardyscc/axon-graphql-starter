# axon-graphql-starter

## start supporting servers

```bash
docker run -d --name some-axon-server -p 8024:8024 -p 8124:8124 axoniq/axonserver   
docker run -d -e "MYSQL_ROOT_PASSWORD=Admin12345" -e "MYSQL_USER=usr" -e "MYSQL_PASSWORD=User12345" -e "MYSQL_DATABASE=development" -p 3306:3306 --name some-mysql bitnami/mysql:5.7.27
```

## stop supporting servers

```bash
docker stop some-axon-server; docker rm some-axon-server
docker stop some-mysql; docker rm some-mysql       
```