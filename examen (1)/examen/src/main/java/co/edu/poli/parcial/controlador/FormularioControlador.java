package co.edu.poli.parcial.controlador;

import co.edu.poli.parcial.model.*;
import co.edu.poli.parcial.servicios.ImplementacionCRUD;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class FormularioControlador {

    @FXML private TextField txtPlaca;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtCapacidad;
    @FXML private TextField txtConductor;
    @FXML private TextField txtGenero;
    @FXML private TextField txtTemporadas;
    @FXML private ComboBox<String> cmbTipo;

    @FXML private TableView<Vehiculo> tabla;
    @FXML private TableColumn<Vehiculo, String> colPlaca;
    @FXML private TableColumn<Vehiculo, String> colMarca;
    @FXML private TableColumn<Vehiculo, String> colModelo;
    @FXML private TableColumn<Vehiculo, String> colConductor;
    @FXML private TableColumn<Vehiculo, String> colTipo;
    @FXML private TableColumn<Vehiculo, String> colExtra;

    private ObservableList<Vehiculo> datos;
    private ImplementacionCRUD servicio;

    @FXML
    public void initialize() {
        servicio = new ImplementacionCRUD();
        datos = FXCollections.observableArrayList();

        cmbTipo.setItems(FXCollections.observableArrayList("Camion", "Bus"));
        cmbTipo.getSelectionModel().selectFirst();

        colPlaca.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getPlaca()));
        colMarca.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMarca()));
        colModelo.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getFechaEstreno())));
        colConductor.setCellValueFactory(c -> {
            Conductor d = c.getValue().getConductor();
            return new SimpleStringProperty(d != null ? d.getNombre() : "");
        });
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue() instanceof Camion ? "Camion" : "Bus"));
        colExtra.setCellValueFactory(c -> {
            Vehiculo obj = c.getValue();
            if (obj instanceof Camion) {
                Camion p = (Camion) obj;
                return new SimpleStringProperty("Género:" + p.getGenero() + " / " + p.getCapacidadMin() + "m");
            } else if (obj instanceof Bus) {
                Bus s = (Bus) obj;
                return new SimpleStringProperty("Temp: " + s.getNumeroTemporadas() + " / Dur:" + s.getCapacidadMin() + "m");
            }
            return new SimpleStringProperty("");
        });

        tabla.setItems(datos); // Tabla vacía al iniciar
    }

    @FXML
    void accionCrear() {
        try {
            String placa = txtPlaca.getText().trim();
            if (placa.isEmpty()) { alerta("Código requerido"); return; }
            if (servicio.buscarPorPlaca(placa) != null) { alerta("Código ya existe"); return; }

            String marca = txtMarca.getText().trim();
            int modelo = Integer.parseInt(txtModelo.getText().trim());
            String tipo = cmbTipo.getValue();
            String conductorNombre = txtConductor.getText().trim();
            Conductor conductor = new Conductor("DIR-" + placa, conductorNombre, "Desconocida");

            Vehiculo pa;
            if ("Camion".equals(tipo)) {
                int dur = Integer.parseInt(txtCapacidad.getText().trim());
                String genero = txtGenero.getText().trim();
                pa = new Camion(placa, marca, modelo, dur, conductor, genero);
            } else {
                int dur = txtCapacidad.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCapacidad.getText().trim());
                int temp = Integer.parseInt(txtTemporadas.getText().trim());
                pa = new Bus(placa, marca, modelo, dur, conductor, temp);
            }

            datos.add(pa);
            servicio.crear(pa);
            clear();
            info("Creado correctamente");

        } catch (Exception ex) {
            alerta("Error en los datos: " + ex.getMessage());
        }
    }

    @FXML
    void accionBuscar() {
        String placa = txtPlaca.getText().trim();
        if (placa.isEmpty()) { alerta("Ingrese placa para buscar"); return; }

        Vehiculo p = servicio.buscarPorPlaca(placa);
        if (p == null) { alerta("No encontrado"); return; }

        txtMarca.setText(p.getMarca());
        txtModelo.setText(String.valueOf(p.getFechaEstreno()));
        txtCapacidad.setText(String.valueOf(p.getCapacidadMin()));
        txtConductor.setText(p.getConductor() != null ? p.getConductor().getNombre() : "");

        if (p instanceof Camion) {
            cmbTipo.getSelectionModel().select("Camion");
            txtGenero.setText(((Camion) p).getGenero());
            txtTemporadas.clear();
        } else {
            cmbTipo.getSelectionModel().select("Bus");
            txtTemporadas.setText(String.valueOf(((Bus) p).getNumeroTemporadas()));
            txtGenero.clear();
        }
    }

    @FXML
    void accionModificar() {
        try {
            Vehiculo seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) { alerta("Seleccione un registro"); return; }

            String placa = seleccionado.getPlaca();
            String marca = txtMarca.getText().trim();
            int modelo = Integer.parseInt(txtModelo.getText().trim());
            String conductorNombre = txtConductor.getText().trim();
            Conductor conductor = new Conductor("DIR-" + placa, conductorNombre, "Desconocida");

            Vehiculo pa;
            if (cmbTipo.getValue().equals("Camion")) {
                int dur = Integer.parseInt(txtCapacidad.getText().trim());
                String genero = txtGenero.getText().trim();
                pa = new Camion(placa, marca, modelo, dur, conductor, genero);
            } else {
                int dur = txtCapacidad.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCapacidad.getText().trim());
                int temp = Integer.parseInt(txtTemporadas.getText().trim());
                pa = new Bus(placa, marca, modelo, dur, conductor, temp);
            }

            int index = datos.indexOf(seleccionado);
            datos.set(index, pa);
            servicio.modificar(placa, pa);
            clear();
            info("Modificado correctamente");

        } catch (Exception ex) {
            alerta("Error al modificar: " + ex.getMessage());
        }
    }

    @FXML
    void accionEliminar() {
        try {
            Vehiculo seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) { alerta("Seleccione un registro"); return; }

            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el registro?", ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> result = a.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                servicio.eliminar(seleccionado.getPlaca());
                datos.remove(seleccionado);
                clear();
                info("Eliminado correctamente");
            }
        } catch (Exception ex) {
            alerta("Error al eliminar: " + ex.getMessage());
        }
    }

    @FXML
    void accionSerializar() {
        servicio.guardarArchivo();
        info("Datos guardados en data.dat");
    }

    @FXML
    void accionDeserializar() {
        servicio.cargarArchivo(); // solo carga en memoria, no se muestra
        info("Datos cargados desde data.dat");
    }

    @FXML
    void accionListar() {
        datos.setAll(servicio.listarTodos()); // ahora sí se muestran en la tabla
        tabla.refresh();
    }

    @FXML
    void displaySelected(MouseEvent event) {
        Vehiculo p = tabla.getSelectionModel().getSelectedItem();
        if (p == null) return;

        txtPlaca.setText(p.getPlaca());
        txtMarca.setText(p.getMarca());
        txtModelo.setText(String.valueOf(p.getFechaEstreno()));
        txtCapacidad.setText(String.valueOf(p.getCapacidadMin()));
        txtConductor.setText(p.getConductor() != null ? p.getConductor().getNombre() : "");

        if (p instanceof Camion) {
            cmbTipo.getSelectionModel().select("Camion");
            txtGenero.setText(((Camion) p).getGenero());
            txtTemporadas.clear();
        } else {
            cmbTipo.getSelectionModel().select("Bus");
            txtTemporadas.setText(String.valueOf(((Bus) p).getNumeroTemporadas()));
            txtGenero.clear();
        }
    }

    private void clear() {
        txtPlaca.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtCapacidad.clear();
        txtConductor.clear();
        txtGenero.clear();
        txtTemporadas.clear();
        cmbTipo.getSelectionModel().selectFirst();
    }

    private void alerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }

    private void info(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
