#!/bin/bash
STOREPASS="forgotten"
for ENT in ordersServer client1Order client2Order client3Order client4Order client5Order client6Order client7Order client7Order client8Order client9Order client10Order client11Order client12Order client13Order client14Order; do
 rm -f ${ENT}.jks ${ENT}.pem
 echo -e "${ENT}\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias ${ENT} -keyalg RSA -keysize 2048 \
    -validity 365 -keystore ${ENT}.jks -storepass ${STOREPASS}
 keytool -exportcert -alias ${ENT} -keystore ${ENT}.jks -storepass ${STOREPASS} -rfc -file ${ENT}.pem
done
####
echo "Creating trust relations"
### IMPORTING TRUSTED CERTIFICATES
### (Every client trusts server_J)
for ENT in client1Order client2Order client3Order client4Order client5Order client6Order client7Order client8Order client9Order client10Order client11Order client12Order client13Order client14Order; do
 echo "yes"|keytool -import -alias ${ENT} -keystore ordersServer.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias ordersServer -keystore ${ENT}.jks -file ordersServer.pem -storepass ${STOREPASS}
done

keytool -list -keystore ordersServer.jks -storepass ${STOREPASS}
#######