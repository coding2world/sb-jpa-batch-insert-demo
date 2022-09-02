## How to run this application ?

### install docker and run below commands.

```shell

docker run --name postgres --restart always -d -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=test_db -p 5432:5432  postgres

```

### create table.

```sql

CREATE TABLE
    city(id serial PRIMARY KEY, name VARCHAR(255), population integer);

```

### Just run the main method in the Application class.