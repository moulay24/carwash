#!/bin/bash

# Vérification des arguments
if [ -z "$1" ]; then
  echo "Usage: ./android_dev.sh NomDeLActivite"
  exit 1
fi

ACTIVITY_NAME=$1
PACKAGE_NAME="com.example.carwash"  # Change si besoin
SRC_PATH="app/src/main/java/com/example/carwash/activities"
LAYOUT_PATH="app/src/main/res/layout"
MANIFEST_PATH="app/src/main/AndroidManifest.xml"

echo "📂 Création de l'activité $ACTIVITY_NAME..."

# Création de l'activité
mkdir -p $SRC_PATH
echo "package $PACKAGE_NAME.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ${ACTIVITY_NAME} extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_${ACTIVITY_NAME,,});
    }
}" > $SRC_PATH/${ACTIVITY_NAME}.java

# Création du fichier layout
mkdir -p $LAYOUT_PATH
echo "<?xml version=\"1.0\" encoding=\"utf-8\"?>
<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"
    android:layout_width=\"match_parent\"
    android:layout_height=\"match_parent\"
    android:orientation=\"vertical\">

    <TextView
        android:layout_width=\"wrap_content\"
        android:layout_height=\"wrap_content\"
        android:text=\"Hello from $ACTIVITY_NAME!\" />
</LinearLayout>" > $LAYOUT_PATH/activity_${ACTIVITY_NAME,,}.xml

# Ajout dans AndroidManifest.xml
sed -i "/<application>/a \ \ \ \ <activity android:name=\".$PACKAGE_NAME.activities.${ACTIVITY_NAME}\"/>" $MANIFEST_PATH

echo "📌 Ajout dans AndroidManifest.xml terminé."

# Commit Git
git add .
git commit -m "Ajout de l'activité $ACTIVITY_NAME"
git push origin main  # Change la branche si nécessaire

# Compilation et installation
echo "🚀 Compilation et installation de l’application..."
./gradlew installDebug

echo "✅ Tout est prêt ! Lance ton app et teste $ACTIVITY_NAME."
