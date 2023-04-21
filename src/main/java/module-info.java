module help.desk.project {
    requires lombok;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    exports dao;
    exports model;
    exports controller;
    exports view;
}