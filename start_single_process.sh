#!/bin/bash
mvn clean compile
java -cp target/classes SingleProcessCommunication
