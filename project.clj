(defproject cake "0.4.16-SNAPSHOT"
  :description "Save your fork, there's cake!"
  :dependencies [[clojure "1.2.0"]
                 [clojure-contrib "1.2.0"]
                 [ordered-set "0.1.0"]
                 [com.jcraft/jsch "0.1.42"]
                 [org.apache.ant/ant "1.8.1"]
                 [org.clojars.ninjudd/maven-ant-tasks "2.1.0" :exclusions [ant/ant]]
                 [org.apache.maven.plugins/maven-shade-plugin "1.3.3"]])
