package org.example.Vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.Database.DatabaseHelper;
import org.example.Modelo.Reseña;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReseñasAppFX extends Application {

    private VBox panelReseñas;
    private ComboBox<String> comboFiltro;
    private int idPaquete = 1; // reemplaza con el que necesites

    @Override
    public void start(Stage stage) {
        stage.setTitle("Reseñas del Paquete " + idPaquete);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e1e;");

        // Filtro
        comboFiltro = new ComboBox<>();
        comboFiltro.getItems().addAll("Más recientes", "Mejor valoradas");
        comboFiltro.setValue("Más recientes");

        Button btnFiltrar = new Button("Filtrar");
        btnFiltrar.setStyle("-fx-background-color: #6495ED; -fx-text-fill: white;");
        btnFiltrar.setOnAction(e -> cargarReseñas());

        HBox filtroBar = new HBox(10, new Label("Ordenar por:"), comboFiltro, btnFiltrar);
        filtroBar.setPadding(new Insets(10));
        filtroBar.setAlignment(Pos.CENTER_LEFT);
        filtroBar.setStyle("-fx-background-color: #2c2c2c;");
        filtroBar.getChildren().forEach(node -> node.setStyle("-fx-text-fill: white;"));

        // Reseñas
        panelReseñas = new VBox(10);
        panelReseñas.setPadding(new Insets(10));

        ScrollPane scroll = new ScrollPane(panelReseñas);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: #1e1e1e;");

        root.setTop(filtroBar);
        root.setCenter(scroll);

        cargarReseñas();

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void cargarReseñas() {
        panelReseñas.getChildren().clear();

        String filtro = comboFiltro.getValue().equals("Mejor valoradas") ? "top" : "recent";
        List<Reseña> reseñas = DatabaseHelper.getReseñas(idPaquete, filtro);

        if (reseñas.isEmpty()) {
            Label empty = new Label("No hay reseñas para este paquete");
            empty.setStyle("-fx-text-fill: white;");
            panelReseñas.getChildren().add(empty);
            return;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Reseña r : reseñas) {
            VBox card = new VBox(5);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: #2c2c2c; -fx-background-radius: 10;");
            card.setMaxWidth(Double.MAX_VALUE);

            // Avatar
            Circle avatarCircle = new Circle(20, Color.LIGHTGRAY);
            Label avatarLabel = new Label("U" + r.getIdUsuario());
            avatarLabel.setStyle("-fx-text-fill: black;");
            StackPane avatar = new StackPane(avatarCircle, avatarLabel);

            // Header
            Label stars = new Label("★".repeat(r.getRating()));
            stars.setTextFill(Color.GOLD);

            Label userLabel = new Label("Usuario #" + r.getIdUsuario());
            userLabel.setStyle("-fx-text-fill: white;");
            Label fechaLabel = new Label(r.getFecha().format(dtf));
            fechaLabel.setStyle("-fx-text-fill: gray;");

            HBox top = new HBox(10, avatar, stars, userLabel, fechaLabel);
            top.setAlignment(Pos.CENTER_LEFT);

            // Comentario
            TextArea comentario = new TextArea(r.getComentario());
            comentario.setWrapText(true);
            comentario.setEditable(false);
            comentario.setStyle("-fx-control-inner-background: #2c2c2c; -fx-text-fill: white;");

            // Botón útil
            Button btnUtil = new Button("👍 Útil (" + r.getUtil() + ")");
            btnUtil.setStyle("-fx-background-color: #6495ED; -fx-text-fill: white;");
            btnUtil.setOnAction(e -> {
                if (DatabaseHelper.marcarReseñaUtil(r.getId())) {
                    r.setUtil(r.getUtil() + 1);
                    btnUtil.setText("👍 Útil (" + r.getUtil() + ")");
                    btnUtil.setDisable(true);
                }
            });

            HBox bottom = new HBox(btnUtil);
            bottom.setAlignment(Pos.CENTER_RIGHT);

            card.getChildren().addAll(top, comentario, bottom);
            panelReseñas.getChildren().add(card);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

