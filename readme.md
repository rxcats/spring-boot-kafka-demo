# install docker

* pull docker scripts and run
```
git clone https://github.com/wurstmeister/kafka-docker.git
docker-compose -f docker-compose-single-broker.yml up
```

## Troubleshooting
* Unable to determine mirror for downloading Kafka, the service may be down
* edit download-kafka.sh
```
if [[ "$MAJOR_VERSION" == "0" && "$MINOR_VERSION" -lt "11" ]]; then
        echo "Version prior to 0.10.2.1 - downloading direct"
        #url="https://archive.apache.org/dist/kafka/${KAFKA_VERSION}/${FILENAME}"
        url="http://mirror.navercorp.com/apache/kafka/${KAFKA_VERSION}/${FILENAME}"
else
        #url=$(curl --stderr /dev/null "https://www.apache.org/dyn/closer.cgi?path=/kafka/${KAFKA_VERSION}/${FILENAME}&as_json=1" | jq -r '"\(.preferred)\(.path_info)"')
        url="http://mirror.navercorp.com/apache/kafka/${KAFKA_VERSION}/${FILENAME}"
fi
```
