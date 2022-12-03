module com.example.demo {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires eu.hansolo.tilesfx;
	requires com.almasb.fxgl.all;
	requires java.datatransfer;
	requires java.desktop;

	opens com.example.demo to javafx.fxml;
	exports com.example.demo;
}