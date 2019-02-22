<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tileset SYSTEM "http://mapeditor.org/dtd/1.0/map.dtd">
<tileset name="tileset" tilewidth="8" tileheight="8" tilecount="98" columns="14">
 <image source="tileset.png" width="112" height="56"/>
 <terraintypes>
  <terrain name="GRASS" tile="8"/>
  <terrain name="WATER" tile="23"/>
 </terraintypes>
 <tile id="0" terrain="1,1,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="1" terrain="1,1,0,0">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="2" terrain="1,1,0,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="3" terrain="0,0,0,0" probability="0.4"/>
 <tile id="4" terrain="0,0,0,0" probability="0.4"/>
 <tile id="5" terrain="0,0,0,0" probability="0.4"/>
 <tile id="14" terrain="1,0,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="15" terrain="0,0,0,0"/>
 <tile id="16" terrain="0,1,0,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="17" terrain="0,0,0,0" probability="0.4"/>
 <tile id="18" terrain="0,0,0,0" probability="0.4"/>
 <tile id="19" terrain="0,0,0,0" probability="0.4"/>
 <tile id="28" terrain="1,0,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="29" terrain="0,0,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="30" terrain="0,1,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="42" terrain="0,0,0,1"/>
 <tile id="43" terrain="0,0,1,0"/>
 <tile id="44" terrain="1,1,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="44" duration="500"/>
   <frame tileid="45" duration="500"/>
   <frame tileid="46" duration="500"/>
   <frame tileid="47" duration="500"/>
   <frame tileid="48" duration="500"/>
  </animation>
 </tile>
 <tile id="56" terrain="0,1,0,0"/>
 <tile id="57" terrain="1,0,0,0"/>
</tileset>
