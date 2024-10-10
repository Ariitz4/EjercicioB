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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador para gestionar la vista de la tabla de personas.
 * Permite agregar nuevas personas a la tabla mediante un formulario.
 * Implementa Initializable para inicializar los componentes al cargar la vista.
 *
 * @author Aritz
 * @version 1.0
 */
public class PersonaController implements Initializable {

    // Botón para agregar una nueva persona
    @FXML
    private Button btnAgregar;

    // Tabla que mostrará las personas agregadas
    @FXML
    private TableView<Persona> tblvTabla;

    // Columnas de la tabla: Nombre, Apellidos, Edad
    @FXML
    private TableColumn<Persona, String> tblcApellidos;

    @FXML
    private TableColumn<Persona, Integer> tblcEdad;

    @FXML
    private TableColumn<Persona, String> tblcNombre;

    // Campos de texto para ingresar los datos de la persona
    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    // Lista observable que se mostrará en la tabla
    private ObservableList<Persona> listaPersonas;

    /**
     * Inicializa los componentes de la vista, como la lista observable y las columnas de la tabla.
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Inicialización de la lista observable
        listaPersonas = FXCollections.observableArrayList();

        // Configuración de las columnas de la tabla para asociarlas con los atributos de Persona
        tblcNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        tblcApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
        tblcEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));

        // Asignar la lista observable a la tabla
        tblvTabla.setItems(listaPersonas);
    }

    /**
     * Método llamado cuando se presiona el botón "Agregar".
     * Valida los campos del formulario y agrega una nueva persona a la tabla si no está ya presente.
     *
     * @param event
     */
    @FXML
    void agregarPersona(ActionEvent event) {
        String camposNulos = "";
        try {
            // Validar si el campo nombre está vacío
            if (txtNombre.getText().equals("")) {
                camposNulos = "El campo nombre es obligatorio\n";
            }
            // Validar si el campo apellidos está vacío
            if (txtApellidos.getText().equals("")) {
                camposNulos += "El campo apellidos es obligatorio\n";
            }
            // Validar si el campo edad está vacío
            if (txtEdad.getText().isEmpty()) {
                camposNulos += "El campo edad es obligatorio";
            }

            // Crear una nueva persona con los datos del formulario
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            Integer edad = Integer.parseInt(txtEdad.getText());
            Persona p = new Persona(nombre, apellidos, edad);

            // Verificar si la persona ya está en la lista
            if (!listaPersonas.contains(p)) {
                // Agregar la nueva persona a la lista y refrescar la tabla
                listaPersonas.add(p);
                tblvTabla.refresh();
                // Mostrar un mensaje de éxito
                ventanaAlerta("C", "Persona añadida correctamente");
            } else {
                // Mostrar un mensaje de error si la persona ya existe
                ventanaAlerta("E", "La persona ya existe");
            }
        } catch (NullPointerException e) {
            // Mostrar un mensaje de error si faltan campos obligatorios
            ventanaAlerta("E", camposNulos);
        } catch (NumberFormatException e) {
            // Mostrar un mensaje de error si la edad no es un número válido
            ventanaAlerta("E", "El valor de edad debe ser un número mayor que cero");
        }
    }

    /**
     * Muestra una ventana de alerta con un mensaje dado.
     *
     * @param tipoAlerta
     * @param mensaje
     */
    void ventanaAlerta(String tipoAlerta, String mensaje) {
        Alert alert = null;
        // Determinar el tipo de alerta según el parámetro
        if (tipoAlerta.equals("E")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        // Mostrar el mensaje en la alerta
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
