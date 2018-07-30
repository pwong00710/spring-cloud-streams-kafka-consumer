mvn clean package -DskipTests
docker build -t kafka_consumer:latest -f Dockerfile_Kafka_Consumer .
