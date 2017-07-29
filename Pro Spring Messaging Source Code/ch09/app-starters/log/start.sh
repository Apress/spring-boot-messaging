#!/bin/bash
java -jar log-sink-rabbit-1.1.1.RELEASE.jar --spring.cloud.stream.bindings.input.destination=simple-demo --server.port=8081
