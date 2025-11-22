#!/bin/bash
set -e

RESOURCE_GROUP_NAME="rg-nexo"
WEBAPP_NAME="gs-nexo"
APP_SERVICE_PLAN="plangs"
LOCATION="brazilsouth"
RUNTIME="JAVA:17-java17"
SERVER_NAME="sqlserver-nexo"
USERNAME="sandufe"
PASSWORD="Fiap@Nexo01"
DBNAME="nexodb"

az group create --name $RESOURCE_GROUP_NAME --location $LOCATION

az sql server create -l $LOCATION -g $RESOURCE_GROUP_NAME -n $SERVER_NAME -u $USERNAME -p $PASSWORD --enable-public-network true

az sql db create -g $RESOURCE_GROUP_NAME -s $SERVER_NAME -n $DBNAME --service-objective Basic --backup-storage-redundancy Local --zone-redundant false

az sql server firewall-rule create -g $RESOURCE_GROUP_NAME -s $SERVER_NAME -n AllowAll --start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255

sqlcmd \
    -S "$SERVER_NAME.database.windows.net" \
    -d "$DBNAME" \
    -U "$USERNAME" \
    -P "$PASSWORD" \
    -l 60 \
    -N \
    -b \
    -i "$(dirname "$0")/script-bd.sql"

az appservice plan create \
  --name "$APP_SERVICE_PLAN" \
  --resource-group "$RESOURCE_GROUP_NAME" \
  --location "$LOCATION" \
  --sku F1 \
  --is-linux

az webapp create \
  --name "$WEBAPP_NAME" \
  --resource-group "$RESOURCE_GROUP_NAME" \
  --plan "$APP_SERVICE_PLAN" \
  --runtime "$RUNTIME"

DB_URL="jdbc:sqlserver://$SERVER_NAME.database.windows.net:1433;database=$DBNAME;user=$USERNAME@$SERVER_NAME;password=$PASSWORD;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"

az webapp config appsettings set \
    --name "$WEBAPP_NAME" \
    --resource-group "$RESOURCE_GROUP_NAME" \
    --settings \
     DB_URL="$DB_URL" \
     DB_USERNAME="$USERNAME" \
     DB_PASSWORD="$PASSWORD" \
     SPRING_FLYWAY_ENABLED=true

az webapp restart --name "$WEBAPP_NAME" --resource-group "$RESOURCE_GROUP_NAME"
