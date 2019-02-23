<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tileset SYSTEM "http://mapeditor.org/dtd/1.0/map.dtd">
<tileset name="tileset" tilewidth="8" tileheight="8" spacing="2" margin="2" tilecount="40" columns="8">
 <image source="tileset.png" width="82" height="52"/>
 <terraintypes>
  <terrain name="GRASS" tile="9"/>
  <terrain name="WATER" tile="26"/>
 </terraintypes>
 <tile id="0" terrain="1,1,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="1" terrain="1,1,0,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="2" terrain="1,1,0,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="3" terrain="0,0,0,0" probability="0.3"/>
 <tile id="4" terrain="0,0,0,0" probability="0.3"/>
 <tile id="5" terrain="0,0,0,0" probability="0.3"/>
 <tile id="8" terrain="1,0,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="9" terrain="0,0,0,0">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="10" terrain="0,1,0,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="11" terrain="0,0,0,0" probability="0.3"/>
 <tile id="12" terrain="0,0,0,0" probability="0.3"/>
 <tile id="13" terrain="0,0,0,0" probability="0.3"/>
 <tile id="16" terrain="1,0,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="17" terrain="0,0,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="18" terrain="0,1,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="24" terrain="0,0,0,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="25" terrain="0,0,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="26" terrain="1,1,1,1">
  <animation>
   <frame tileid="26" duration="500"/>
   <frame tileid="27" duration="500"/>
   <frame tileid="28" duration="500"/>
   <frame tileid="29" duration="500"/>
   <frame tileid="30" duration="500"/>
   <frame tileid="29" duration="500"/>
   <frame tileid="28" duration="500"/>
   <frame tileid="27" duration="500"/>
  </animation>
 </tile>
 <tile id="32" terrain="0,1,0,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="33" terrain="1,0,0,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
</tileset>
