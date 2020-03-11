#!/bin/sh -l

module=${1}
gpg_passphrase=${4}
gpg_private_key=${5}
export MAVEN_NEXUS_USER=${2}
export MAVEN_NEXUS_PASSWORD=${3}

echo "${gpg_private_key}" > private-key.txt && gpg --import --no-tty --batch --yes private-key.txt
./mvnw deploy -Dgpg.passphrase="${gpg_passphrase}" -DskipTests -P deploy --file "${module}"/pom.xml
