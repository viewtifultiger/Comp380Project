module comp380project {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens comp380project to javafx.fxml;
    exports comp380project;
}
