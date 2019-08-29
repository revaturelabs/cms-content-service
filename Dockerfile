FROM postgres:10
ENV POSTGRES_USER projectthree
ENV POSTGRES_PASSWORD password
ADD schema.sql /docker-entrypoint-initdb.c
EXPOSE 5432