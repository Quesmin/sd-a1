module com.a1.a1 {

    requires java.persistence;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.hibernate.orm.core;

    exports com.a1.a1.presentation;
    exports com.a1.a1.model;
    exports com.a1.a1.controller;
    exports com.a1.a1.repository;
    exports com.a1.a1.service;
    opens com.a1.a1.presentation to javafx.fxml;
    opens com.a1.a1.model;
}