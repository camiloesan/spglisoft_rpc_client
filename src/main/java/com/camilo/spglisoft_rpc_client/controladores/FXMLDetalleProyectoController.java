package com.camilo.spglisoft_rpc_client.controladores;

import com.camilo.spglisoft_rpc_client.modelo.RpcClient;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.camilo.spglisoft_rpc_client.utils.SidebarDesarrollador;
import com.camilo.spglisoft_rpc_client.utils.SidebarRepresentante;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import spglisoft.Spglisoft;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class FXMLDetalleProyectoController implements Initializable {
    @FXML
    private Label lblFechaInicio;

    @FXML
    private Label lblFechaFin;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblRepresentanteProyecto;

    @FXML
    private Label lblNombreProyecto;

    @FXML
    private Label lblEstadoProyecto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearInformacion();
    }

    private void formatearInformacion() {
        Spglisoft.ProjectInfo proyecto = null;
        if (SingletonLogin.getInstance().getDesarrollador() != null) {

            ManagedChannel managedChannel = Grpc
                    .newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
            Spglisoft.ProjectInfo response;
            try {
                RpcClient client = new RpcClient(managedChannel);
                response = client
                        .getProjectByDevId(SingletonLogin.getInstance().getDeveloper().getIdDesarrollador());
            }
            finally {
                try {
                    managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            }
            proyecto = response;
        } else {
            proyecto = SingletonLogin.getInstance().getProyectoActual();
        }

        assert proyecto != null;
        lblNombreProyecto.setText("Proyecto: " + proyecto.getNombreProyecto());
        lblDescripcion.setText(proyecto.getDescripcion());
        lblFechaInicio.setText(proyecto.getFechaInicio());
        lblFechaFin.setText(proyecto.getFechaFin());
        lblEstadoProyecto.setText(proyecto.getEstado());
        lblRepresentanteProyecto.setText(proyecto.getNombreRepresentante()
                + " " + proyecto.getApellidoPaterno()
                + " " + proyecto.getApellidoMaterno());
    }

    @FXML
    private void btnRegresar() {
        if (SingletonLogin.getInstance().getDesarrollador() != null) {
            SidebarDesarrollador.irMenuActividades();
        } else {
            SidebarRepresentante.irMenuActividades();
        }
    }
}
