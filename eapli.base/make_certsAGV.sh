#!/bin/bash
STOREPASS="123abc456def"
for ENT in serverExecutor cliente1Executor; do
 rm -f ${ENT}.jks ${ENT}.pem
 echo -e "${ENT}\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias ${ENT} -keyalg RSA -keysize 2048 \
	-validity 365 -keystore ${ENT}.jks -storepass ${STOREPASS}
 keytool -exportcert -alias ${ENT} -keystore ${ENT}.jks -storepass ${STOREPASS} -rfc -file ${ENT}.pem
done
####
echo "Creating trust relations"
### IMPORTING TRUSTED CERTIFICATES
### (Every client trusts serverExecutor)
# shellcheck disable=SC2043
for ENT in cliente1Executor; do
 echo "yes"|keytool -import -alias ${ENT} -keystore serverExecutor.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias serverExecutor -keystore ${ENT}.jks -file serverExecutor.pem -storepass ${STOREPASS}
done

keytool -list -keystore serverExecutor.jks -storepass ${STOREPASS}
#######