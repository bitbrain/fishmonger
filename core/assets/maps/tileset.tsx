<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE tileset SYSTEM "http://mapeditor.org/dtd/1.0/map.dtd">
<tileset name="tileset" tilewidth="8" tileheight="8" spacing="2" margin="2" tilecount="120" columns="8">
 <image source="tileset.png" width="82" height="152"/>
 <terraintypes>
  <terrain name="GRASS" tile="9"/>
  <terrain name="WATER" tile="26"/>
 </terraintypes>
 <tile id="0" terrain="1,1,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="0" duration="600"/>
   <frame tileid="40" duration="600"/>
   <frame tileid="41" duration="600"/>
   <frame tileid="42" duration="600"/>
   <frame tileid="41" duration="600"/>
   <frame tileid="40" duration="600"/>
  </animation>
 </tile>
 <tile id="1" terrain="1,1,0,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="1" duration="600"/>
   <frame tileid="88" duration="600"/>
   <frame tileid="89" duration="600"/>
   <frame tileid="90" duration="600"/>
   <frame tileid="89" duration="600"/>
   <frame tileid="88" duration="600"/>
  </animation>
 </tile>
 <tile id="2" terrain="1,1,0,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="2" duration="600"/>
   <frame tileid="64" duration="600"/>
   <frame tileid="65" duration="600"/>
   <frame tileid="66" duration="600"/>
   <frame tileid="65" duration="600"/>
   <frame tileid="64" duration="600"/>
  </animation>
 </tile>
 <tile id="3" terrain="0,0,0,0" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="4" terrain="0,0,0,0" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="5" terrain="0,0,0,0" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="6">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="7">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="8" terrain="1,0,1,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="8" duration="600"/>
   <frame tileid="48" duration="600"/>
   <frame tileid="49" duration="600"/>
   <frame tileid="50" duration="600"/>
   <frame tileid="49" duration="600"/>
   <frame tileid="48" duration="600"/>
  </animation>
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
  <animation>
   <frame tileid="10" duration="600"/>
   <frame tileid="72" duration="600"/>
   <frame tileid="73" duration="600"/>
   <frame tileid="74" duration="600"/>
   <frame tileid="73" duration="600"/>
   <frame tileid="72" duration="600"/>
  </animation>
 </tile>
 <tile id="11" terrain="0,0,0,0" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="12" terrain="0,0,0,0" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="13" terrain="0,0,0,0" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="14">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="15">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="16" terrain="1,0,1,1" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="16" duration="600"/>
   <frame tileid="56" duration="600"/>
   <frame tileid="57" duration="600"/>
   <frame tileid="58" duration="600"/>
   <frame tileid="57" duration="600"/>
   <frame tileid="56" duration="600"/>
  </animation>
 </tile>
 <tile id="17" terrain="0,0,1,1" probability="0.3">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="17" duration="600"/>
   <frame tileid="96" duration="600"/>
   <frame tileid="97" duration="600"/>
   <frame tileid="98" duration="600"/>
   <frame tileid="97" duration="600"/>
   <frame tileid="96" duration="600"/>
  </animation>
 </tile>
 <tile id="18" terrain="0,1,1,1">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="18" duration="600"/>
   <frame tileid="80" duration="600"/>
   <frame tileid="81" duration="600"/>
   <frame tileid="82" duration="600"/>
   <frame tileid="81" duration="600"/>
   <frame tileid="80" duration="600"/>
  </animation>
 </tile>
 <tile id="19">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="20">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="21">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="22">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="23">
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
  <animation>
   <frame tileid="25" duration="600"/>
   <frame tileid="112" duration="600"/>
   <frame tileid="113" duration="600"/>
   <frame tileid="114" duration="600"/>
   <frame tileid="113" duration="600"/>
   <frame tileid="112" duration="600"/>
  </animation>
 </tile>
 <tile id="26" terrain="1,1,1,1">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
  <animation>
   <frame tileid="26" duration="600"/>
   <frame tileid="27" duration="600"/>
   <frame tileid="28" duration="600"/>
   <frame tileid="29" duration="600"/>
   <frame tileid="30" duration="600"/>
   <frame tileid="29" duration="600"/>
   <frame tileid="28" duration="600"/>
   <frame tileid="27" duration="600"/>
  </animation>
 </tile>
 <tile id="27" terrain=",,,0">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="28">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="29">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="30">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="31">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="32" terrain="0,1,0,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
  <animation>
   <frame tileid="32" duration="600"/>
   <frame tileid="104" duration="600"/>
   <frame tileid="105" duration="600"/>
   <frame tileid="106" duration="600"/>
   <frame tileid="105" duration="600"/>
   <frame tileid="104" duration="600"/>
  </animation>
 </tile>
 <tile id="33" terrain="1,0,0,0">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="34" terrain="1,1,1,1" probability="0.4">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
  <animation>
   <frame tileid="34" duration="600"/>
   <frame tileid="35" duration="600"/>
   <frame tileid="36" duration="600"/>
   <frame tileid="37" duration="600"/>
   <frame tileid="36" duration="600"/>
   <frame tileid="35" duration="600"/>
  </animation>
 </tile>
 <tile id="35">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="36">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="37">
  <properties>
   <property name="collision" type="bool" value="false"/>
  </properties>
 </tile>
 <tile id="38">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="39">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="40">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="41">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="42">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="48">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="49">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="50">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="51">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="56">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="57">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="58">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="64">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="65">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="66">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="72">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="73">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="74">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="80">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="81">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="82">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="88">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="89">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="90">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="96">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="97">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="98">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="104">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="105">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="106">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="112">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="113">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
 <tile id="114">
  <properties>
   <property name="collision" type="bool" value="true"/>
  </properties>
 </tile>
</tileset>
