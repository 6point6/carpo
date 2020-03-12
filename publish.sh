#!/usr/bin/env bash

gpg_passphrase=${1}
version=${2}
modules=( carpo-parent carpo-common carpo-case-data carpo-relational-common carpo-case-data-relational carpo-mi carpo-mi-spring-boot carpo-serialization carpo-finance carpo-finance-relational )

./mvnw versions:set -DnewVersion="${version}" -f carpo-parent/pom.xml
./mvnw clean install
for i in "${modules[@]}"
do
   :
   ./mvnw deploy -Dgpg.passphrase="${gpg_passphrase}" -DskipTests -P deploy --file "${i}"/pom.xml
done
