export COMPOSE_PROJECT_NAME=kafka_demo
export CONSUMER_INSTANCE_COUNT=2
export PRODUCER_PORT=8080
export HOSTNAME_COMMAND=`ifconfig en0 | grep inet | grep netmask | awk '{print $2}'`
export KAFKA_HOSTNAME=localhost
