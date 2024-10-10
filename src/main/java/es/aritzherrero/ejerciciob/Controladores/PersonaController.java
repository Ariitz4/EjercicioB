package es.aritzherrero.ejerciciob.Controladores;
import java.net.URL;
import java.util.ResourceBundle;

import es.aritzherrero.ejerciciob.Model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class PersonaController implements Initializable {
    @FXML
    private Button btnAgregar;

    @FXML
    private TableView<Persona> tblvTabla;

    @FXML
    private TableColumn<Persona, String> tblcApellidos;

    @FXML
    private TableColumn<Persona, Integer> tblcEdad;

    @FXML
    private TableColumn<Persona, String> tblcNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    private ObservableList<Persona> listaPersonas;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaPersonas = FXCollections.observableArrayList();

        tblcNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        tblcApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
        tblcEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
        tblvTabla.setItems(listaPersonas);

    }

    @FXML
    void agregarPersona(ActionEvent event) {
        String camposNulos = "";
        try {
            if (txtNombre.getText().equals("")) {
                camposNulos = "El campo nombre es obligatorio\n";
            }
            if (txtApellidos.getText().equals("")) {
                camposNulos += "El campo apellidos es obligatorio\n";
            }
            if (txtEdad.getText().isEmpty()) {
                camposNulos += "El campo apellidos es obligatorio";
            }

            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            Integer edad = Integer.parseInt(txtEdad.getText());
            Persona p = new Persona(nombre, apellidos, edad);

            if (listaPersonas.contains(p) == false) {
                listaPersonas.add(p);
                tblvTabla.refresh();
                ventanaAlerta("C", "Persona añadida correctamente");
            } else {
                ventanaAlerta("E", "La persona ya existe");
            }
        } catch (NullPointerException e) {
            ventanaAlerta("E", camposNulos);
        } catch (NumberFormatException e) {
            ventanaAlerta("E", "El valor de edad debe ser un número mayor que cero");
        }

    }


    void ventanaAlerta(String tipoAlerta, String mensaje) {
        Alert alert = null;
        if (tipoAlerta == "E") {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
