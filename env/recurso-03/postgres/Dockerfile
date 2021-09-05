FROM postgres
ENV POSTGRES_DB lol
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres
COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/