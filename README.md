# ccc-shop-backend

Backend for online shopping system **CCC Shop** of database systems course.

## How to Run ?

### Install MariaDb

[Download mariaDb](https://mariadb.org/download/?t=mariadb&p=mariadb&r=10.6.5&os=windows&cpu=x86_64&pkg=msi&m=blendbyte) and set username and password to root

or you can run mariaDb with docker
```cmd
docker run --name mariadb -p 3306:3306 -e MARIADB_USER=root MARIADB_ROOT_PASSWORD=root -v /data/mariadb/data:/var/lib/mysql -d mariadb
```

### Create database and add initial value

run `create_table.sql` and `initial_value.sql` in your database

### Install IntelliJ IDEA

[Download Path](https://www.jetbrains.com/idea/)

#### Open project with IntelliJ IDEA

![](https://i.imgur.com/hvHsQIY.png)


#### Add project SDK

- Add openjdk-17 in "File -> Project Structure"
![](https://i.imgur.com/X9ra1n7.png)
![](https://i.imgur.com/BuwKjCI.png)

#### Run application

- Right click on `CccShopApplication`
- Run `CccShopApplication`
![](https://i.imgur.com/7bFJes3.png)


Done!