module comp380project {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;
    requires org.jsoup;

    opens comp380project to javafx.fxml;
    exports comp380project;
}
