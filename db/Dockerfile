# Use the latest MySQL image
FROM mysql

# Set the working directory to /tmp inside the container
WORKDIR /tmp

# Copy the SQL file into the container
COPY world-db/world.sql /tmp

# Copy the SQL file to docker-entrypoint-initdb.d.
# This is specific to MySQL images and automatically executes SQL files on container startup.
COPY world-db/world.sql /docker-entrypoint-initdb.d

# Set the root password
ENV MYSQL_ROOT_PASSWORD example