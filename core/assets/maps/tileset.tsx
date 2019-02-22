<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tileset SYSTEM "http://mapeditor.org/dtd/1.0/map.dtd">
<tileset name="tileset" tilewidth="8" tileheight="8" tilecount="49" columns="7">
 <image source="tileset.png" width="56" height="56"/>
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
 <tile id="7" terrain="1,0,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="8" terrain="0,0,0,0"/>
 <tile id="9" terrain="0,1,0,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="14" terrain="1,0,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="15" terrain="0,0,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="16" terrain="0,1,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="21" terrain="0,0,0,1"/>
 <tile id="22" terrain="0,0,1,0"/>
 <tile id="23" terrain="1,1,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="28" terrain="0,1,0,0"/>
 <tile id="29" terrain="1,0,0,0"/>
</tileset>
